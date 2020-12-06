const ObjectId = require("mongoose").Types.ObjectId;
const Form = require("../../../../models/Forms");
const Widget = require("../../../../models/Widget");


function onlyUnique(value, index, self) {
    return self.indexOf(value) === index;
}


module.exports = function (req, res) {
    let ip = req.header('x-forwarded-for') || req.connection.remoteAddress;

    req.body.result = JSON.parse(req.body.result)

    if (req.body._id !== undefined || req.body.result !== undefined) {
        if (ObjectId.isValid(req.body._id)) {
            Form.findById(req.body._id, async function (err, doc) {
                if (err) {
                    res.status(500).send(err);
                } else if (doc.isClosed) {
                    res.status(202).send({message: "Form is closed"});
                } else if (!doc) {
                    res.status(202).send({
                        message: "No Form found with this ID"
                    });
                } else {
                    if(doc.IPAdressPeopleAnswered.indexOf(ip)>=0){
                        res.status(202).send({message: "You have already answered this form"});
                    }
                    else{
                        let error = false;
                        let allWidget = []

                        var unique = req.body.result.filter(onlyUnique);

                        let isEqual = true;


                        for (elem of doc.widget) {
                            let find = false;
                            for (elem2 of unique) {
                                if (elem2._id === elem.toString()) {
                                    find = true
                                }
                            }
                            if (find === false) {
                                isEqual = false
                                break
                            }
                        }

                        if (!isEqual) {
                            res.status(202).send({
                                message: "Missing widget"
                            });
                        } else if (unique.length !== req.body.result.length) {
                            res.status(202).send({
                                message: "duplicate _id of widget in result"
                            });
                        } else {
                            for (let element of req.body.result) {
                                if (ObjectId.isValid(element._id)) {
                                    let widget = await Widget.findById(element._id);
                                    if (widget) {
                                        allWidget.push(widget);
                                        if (widget.type === 0) {
                                            widget.textFieldResult = widget.textFieldResult.concat(element.result)
                                        } else if (widget.type === 1) {
                                            widget.resultPoint = widget.resultPoint.concat( parseInt( element.result))
                                        }
                                    } else {
                                        error = true;
                                        break;
                                    }
                                } else {
                                    error = true;
                                    break;
                                }
                            }
                            if (error) {
                                res.status(202).send({
                                    message: "No widget found with this ID"
                                });
                            } else {

                                for (elem of allWidget) {
                                    elem.save()
                                }
                                doc.IPAdressPeopleAnswered = doc.IPAdressPeopleAnswered.concat(ip);
                                await doc.save()
                                res.status(200).send({
                                    message: "Sucess"
                                });
                            }
                        }

                    }


                }
            })
        } else {
            res.status(202).send({
                message: "Invalid ID"
            })
        }
    } else {
        res.status(202).send({
            message: "Incomplete body"
        })
    }
};