const ObjectId = require("mongoose").Types.ObjectId;
const User = require("../../../../models/User");


module.exports = async function (req, res) {
    if (req.body._id !== undefined) {
        if (ObjectId.isValid(req.body._id)) {
            User.findOneAndRemove({_id: req.body._id}, function (err, doc) {
                if (err) {
                    res.status(500).send(err)
                } else if (doc === null) {
                    res.status(202).send({
                        message: "Unable to find user with this ID"
                    })
                } else {
                    res.status(200).send({
                        message: "Sucess"
                    })
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