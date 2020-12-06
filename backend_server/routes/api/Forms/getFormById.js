const ObjectId = require("mongoose").Types.ObjectId;
const Forms = require('../../../../models/Forms')


module.exports = function (req,res) {
    if (req.body._id !== undefined) {
        if (ObjectId.isValid(req.body._id)) {
            Forms.findById((req.body._id),function (err,doc) {
                if(err){
                    res.status(500).send(err);
                }
                else if(!doc) {
                    res.status(202).send({
                        message:"No form found with this ID"
                    });
                }
                else{
                    res.status(200).send(doc)
                }
            })
        } else {
            res.status(202).send({message: "bad id provided"});
        }
    } else {
        res.status(202).send({message: "missing id"});
    }

};