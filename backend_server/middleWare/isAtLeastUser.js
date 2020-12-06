const rights = require('./rights');

module.exports = async function (req, res, next) {
    console.log(req.user.type )
    if (req.user.type !== undefined && req.user.type  >= rights["user"]) {
        next();
    } else {
        res.status(202).send(
            {
                message: "You need user rights to do this action",
            });
    }

};
