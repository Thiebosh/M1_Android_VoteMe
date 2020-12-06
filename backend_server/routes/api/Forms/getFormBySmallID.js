const ObjectId = require("mongoose").Types.ObjectId;
const Forms = require('../../../../models/Forms')


module.exports = function (req, res) {
    if (req.body.smallID !== undefined) {
        let ip = req.header('x-forwarded-for') || req.connection.remoteAddress;
        Forms.findOne({smallId : req.body.smallID}).populate("widget").exec(async function (error,doc) {
            if(error){
                res.status(500).send(error)
            }
            else if(!doc){
                res.status(202).send({message: "Form unfindable"});
            }
            else if(doc.isClosed){
                res.status(202).send({message: "Form is closed"});
            }
            else{
                if(doc.IPAdressPeopleAnswered.indexOf(ip)>=0){
                    res.status(202).send({message: "You have already answered this form"});
                }
                else{
                    res.status(200).send(doc)
                }

            }
        })
    } else {
        res.status(202).send({message: "missing id"});
    }

}