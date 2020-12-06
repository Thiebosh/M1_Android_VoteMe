var express = require('express');
var router = express.Router();

const auth = require('../../../middleWare/auth');
const isAdmin = require('../../../middleWare/isAdmin');

const createUser = require('./createUser');
const deleteUser = require('./deleteUser');
const login = require('./login');
const signOut = require('./signOut');


router.post('/deleteUser',auth,isAdmin,deleteUser);
router.post('/createUser',auth,isAdmin,createUser);
router.post('/login',login);
router.post('/signOut',signOut);




module.exports= router;