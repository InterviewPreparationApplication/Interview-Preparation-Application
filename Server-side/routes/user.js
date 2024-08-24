const mysql=require('mysql2');
const express  = require('express')
const config = require('config')
const app = express.Router();
const jwt = require('jsonwebtoken')
const bodyParser = require('body-parser');
const multer = require('multer');
const config2 = require('../config/configjwt')
const path = require('path');


//getting connection details from config file
var ConnectionDetails ={
                        host:config.get("server"),
                        database:config.get("database"),
                        user:config.get("user"),
                        password:config.get("password"),
                     }

                     
  const storage = multer.diskStorage({
    destination: function (req, file, cb) {
      cb(null, 'public/images');
    },
    filename: function (req, file, cb) {
      cb(null, `${file.fieldname}-${Date.now()}${path.extname(file.originalname)}`);
    },
  });

  const upload = multer({ storage: storage });

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());



//user registration api
app.post("/register",upload.single('Image'),(request,response)=>{
   //console.log(request.file.path)
   // console.log(request.body)
    if(request.body!=null)
    {
      let Path ="";
        if(request.file)
             Path = request.file.path

       // console.log(request.body);
        const {FirstName,LastName,Email,Password,Mobile,Address,Dob,Qualification,Gender} = request.body;
        
       var connection = mysql.createConnection(ConnectionDetails);
         var statement = `INSERT INTO Users(FirstName,LastName,Email,Password,Mobile,Address,Dob,Qualification,Gender,Role,LastModified,Profile)VALUES
                                            ('${FirstName}','${LastName}','${Email}','${Password}','${Mobile}','${Address}','${Dob}','${Qualification}','${Gender}','User',now(),'${Path}') `;
        //console.log(statement);
        connection.query(statement,(error,result)=>{
            if(error==null)
            {
                response.setHeader("Content-type","application/json");
                var reply = {
                                "status":"success",
                                "message":result
                            }
                response.write(JSON.stringify(reply));
                connection.end();
                response.end();
            }

            else{
                response.setHeader("Content-type","application/json");
                var reply = {
                    "status":"error",
                    "message":error
                }
                response.write(JSON.stringify(reply));
                connection.end();
                response.end();
            }
        })
    }
    else{
        var reply ={
            "message" :"Failed to getting data",
            "data":"errror"
        }
    }
})

//login api
app.post("/login",(request,response)=>{ 
   const {Email,Password}= request.body;
   var statement  = `Select Userid,Role,Email from Users WHERE Email='${Email}' AND Password='${Password}'`;
    var connection = mysql.createConnection(ConnectionDetails);
    connection.query(statement,(error,users)=>{
        if (error) {
            var reply = {
                "status":"error",
                "result":error
            }
            response.send(JSON.stringify(reply))
          } else {
            // if there is any user found with email and password
            if (users.length == 0) {
              // no user found
              var reply = {
                "status":"error",
                "result":"user not found"
            }
              response.send(JSON.stringify(reply))
            } else {
               // console.log(users[0])
              const user = users[0]
              //console.log(user)
              // create a payload for jwt token
              const payload = {
                Userid:user['Userid'],
                name: user['FirstName'],
                type: 'user',
              }
      
              // create a token
              const token = jwt.sign(payload, config2.secrete)
              var reply ={
                "status":"success",
                "token":token,
                  "UserName":user['FirstName'],
                "Userid":user['Userid'],
                "Role":user['Role']
              }
              response.send(JSON.stringify(reply))
              
            }
          }
        })
      })   

//get user by id api
app.get("/getuserbyid/:No",(request,response)=>{
    var No = request.params.No;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `SELECT Userid,FirstName,LastName,Email,Mobile,Address,Dob,Qualification,Gender,Profile FROM Users WHERE Userid= ${No}`;
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
          const profileimage= result[0].Profile
          
            var reply = {
                            "status":"success",
                            "result":result,
                        }
            response.setHeader("Content-type","application/json");
            response.write(JSON.stringify(reply));
            connection.end();
            response.end();
        }
        else{
                response.setHeader("Content-type","application/json");
                response.write(JSON.stringify(error));
                connection.end();
                response.end();
        }
    })
   
})

//edit user profile api
app.put("/edituserprofile/:id",(req,response)=>{
 const No= req.params.id;
  const {FirstName,LastName,Mobile,Address,Qualification}= req.body;
  var connection = mysql.createConnection(ConnectionDetails);
  var statement = `UPDATE Users set FirstName='${FirstName}',LastName='${LastName}',Mobile='${Mobile}',
  Address='${Address}',Qualification ='${Qualification}' WHERE Userid=${No}`
 // console.log(statement)
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
            var reply = {
                            "status":"success",
                            "result":result
                        }
            response.setHeader("Content-type","application/json");
            response.write(JSON.stringify(reply));
            connection.end();
            response.end();
        }
        else{
                response.setHeader("Content-type","application/json");
                response.write(JSON.stringify(error));
                connection.end();
                response.end();
        }
    })
 

})


//delte user by id 
app.delete("/deleteUser/:id",(request,response)=>{
    const id = request.params.id
    var connection = mysql.createConnection(ConnectionDetails)
    var statement = `DELETE From Users WHERE Userid=${id}`;
    connection.query(statement,(error,result)=>{
        if(error==null)
        {
            var reply = {
                            "status":"success",
                            "result":result
                        }
            response.setHeader("Content-type","application/json");
            response.write(JSON.stringify(reply));
            connection.end();
            response.end();
        }
        else{
                response.setHeader("Content-type","application/json");
                response.write(JSON.stringify(error));
                connection.end();
                response.end();
        }
    })
    
    
})
module.exports= app;
