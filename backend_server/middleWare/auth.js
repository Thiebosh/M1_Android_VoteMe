const jwt = require("jsonwebtoken");
const rights = require('./rights');

module.exports = function(req, res, next){

    let token;

    let authHeaderContent = req.get('authorization');
    if(authHeaderContent && authHeaderContent.startsWith('Bearer ')){
        token = authHeaderContent.slice(7);
    }
    else if(req.cookies.headerPayload && req.cookies.signature){
        token = req.cookies.headerPayload + "." + req.cookies.signature;
    }

    if(token){
        // Verify token
        jwt.verify(token, process.env.TOKEN_SECRET, function(err, decoded) {
            // if is valid passes to next function
            if(!err){
                // populate req with user id
                req.user = {
                    id: decoded.id,
                    type: decoded.type
                };

                next();
            }
            // else send Unauthorized status with error message
            else{
                res.status(205).send({
                    message: "Token is not valid"
                });
            }
        })
    }
    else{
        res.status(202).send({
            message: "No token provided"
        });
    }
};
