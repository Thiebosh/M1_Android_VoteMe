const rights = require('./rights');

module.exports = function (req, res, next) {
    if (req.user.type  === rights["admin"]) {
        next();
    } else {
        res.status(202).send(
            {
                message: "You need admin rights to do this action",
            });
    }

};
