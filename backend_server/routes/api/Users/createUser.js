const User = require('../../../../models/User')
const bcrypt = require('bcrypt');

/*
    forms:{
        type: [FormsSchema],
        required: false,
    },
    userName:{
        type:String,
        required: true,
        unique:true,
    },
    creationDate :{
        type:Date,
        default:Date.now()
    },
    password: {
        type: String,
        required: true
    },
 */

const saltRounds = 10

module.exports = async function (req,res) {
    if(req.body.username !== undefined && req.body.password !== undefined){
        const username = req.body.username;
        const password = req.body.password;

        User.findOne({username:username},async function (err,doc) {
            if(err){
                res.status(500).send(err)
            }
            else if(doc) {
                res.status(202).send({
                    message: "Name already in database"
                })
            }
            else{
                await bcrypt.hash(password, saltRounds, async function(err, hash) {
                    if(err){
                        res.status(402).send(err)
                    }
                    else {
                        await User.create({
                            username : username,
                            password : hash,
                            type : 0,
                        });
                        res.status(200).send({
                            message : "Success"
                        })

                    }
                });
            }
        });
    }
    else{
        res.status(202).send({
            message : "Incomplete body"
        })
    }
};