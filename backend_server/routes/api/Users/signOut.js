module.exports = function(req, res){
  res.clearCookie("headerPayload");
  res.clearCookie("signature");
  res.status(200)
      .send({
          message: "User logged out",
      });
};
