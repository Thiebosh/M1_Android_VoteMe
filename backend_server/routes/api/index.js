var express = require('express');
var router = express.Router();

var users = require('./Users')
const form = require('./Forms')



router.use('/users',users)
router.use('/forms',form)




module.exports= router