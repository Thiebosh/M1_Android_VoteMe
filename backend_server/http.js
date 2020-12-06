const app = require('./app.js')
const connectDb = require('./mongodbConnection.js');

connectDb().then(() => {
  app.listen(process.env.PORT ||3000, () => {
    console.log(`Connect to DB ${process.env.PORT}!`)
  })
})