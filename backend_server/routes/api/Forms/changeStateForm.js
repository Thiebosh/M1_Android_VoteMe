const ObjectId = require("mongoose").Types.ObjectId;
const Forms = require('../../../../models/Forms')
const User =  require('../../../../models/User')

module.exports = async function (req, res) {
    
    if (req.body._id !== undefined &&  typeof(req.body.value=== "boolean") ) {
        if (ObjectId.isValid(req.body._id)) {
            let actualUser = await User.findById(req.user.id);
            if(actualUser){
                if(actualUser.forms.includes(req.body._id)){
                    Forms.findOne({_id: req.body._id},async function (err, doc) {
                        if (err) {
                            res.status(500).send(err);
                        } else if (doc) {
                            doc.isClosed = req.body.value;
                            await doc.save()
                            res.status(200).send({
                                message : "Success"
                            })

                        } else {
                            res.status(202).send({message: "No form found with this id"});
                        }
                    })
                }
                else{
                    res.status(202).send({message: "On essaye de hack c'est pas ouf"});
                }
            }
            else{
                res.status(202).send({message: "Erreur"});
            }
        } else {
            res.status(202).send({message: "bad id provided"});
        }
    } else {
        res.status(202).send({message: "missing id"});
    }
};