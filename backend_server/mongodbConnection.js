const mongoose = require('mongoose');


const connectDb = () => {
    return mongoose.connect(process.env.DATABASE_URL, {  auth: {user: "Android",password: "Pmfh9bndrGvUZTc"},useNewUrlParser: true, useUnifiedTopology: true});
};



module.exports = connectDb;

