const mysql=require('mysql2');
const express  = require('express')
const config = require('config')
const app = express.Router();
const jwt = require('jsonwebtoken')
const config2 = require('../config/configjwt')
const bodyParser = require('body-parser');
const multer = require('multer');
const path = require('path');

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

 //Interviewer register query
 app.post("/register",upload.single('Image'),(request,response)=>{
    if(request.body!=null){
    const {FirstName,LastName,Email,Password,Mobile,Address,Dob,CompanyPosition,QualifiedDegree,Gender}=request.body;
    let Path ="";
        if(request.file)
             Path = request.file.path
        
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `insert into Interviewers(FirstName,LastName,Email,Password,Mobile,Address,Dob,CompanyPosition,QualifiedDegree,Gender,Role,ProfileUpdated,Profile)
    values('${FirstName}','${LastName}','${Email}','${Password}','${Mobile}','${Address}','${Dob}','${CompanyPosition}','${QualifiedDegree}','${Gender}','Interviewer',now(),'${Path}')`;
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
    });

//Interviewer login
app.post("/login",(request,response)=>{
    const {Email,Password}= request.body;
    //console.log(request.body)
    var statement  = `Select Interviewerid,Role,FirstName,Email from Interviewers where Email='${Email}' and Password='${Password}'`;
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
                //console.log(users[0])
              const user = users[0]
             // console.log(user)
              // create a payload for jwt token
              const payload = {
                Userid:user['Interviewerid'],
                name: user['FirstName'],
                type: 'interviewer',
              }
      
              // create a token
              const token = jwt.sign(payload, config2.secrete)
              var reply ={
                "status":"success",
                "token":token,
                  "Role":user['Role'],
                "Userid":user['Interviewerid']
              }
              response.send(JSON.stringify(reply))
              
            }
          }
     }) 
 });


//get user by id api
app.get("/getinterviewerbyid/:No",(request,response)=>{
    var No = request.params.No;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `SELECT Interviewerid,FirstName,LastName,Email,Mobile,Address,Dob,CompanyPosition,QualifiedDegree,Gender,Profile FROM Interviewers  WHERE Interviewerid= ${No}`;
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

 //Edit profile api
app.put("/edit/:id",(request,response)=>{
    var id = request.params.id;
    const {FirstName,LastName,Mobile,Address,CompanyPosition}=request.body;

    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `update Interviewers set FirstName='${FirstName}',LastName='${LastName}',Mobile='${Mobile}',Address='${Address}',CompanyPosition='${CompanyPosition}' where Interviewerid=${id}`;
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
});

// delete Interviewers
app.delete("/delete/:id",(request,response)=>{
    const id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails)
    var statement = `DELETE From Interviewers WHERE Interviewerid=${id}`;
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
});

// InterviewScheduled
app.get("/InterviewSceduled/:id",(request,response)=>{
    const id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement =`SELECT 
    InterviewSceduled.Interviewid,
    InterviewSceduled.Date,
    Skills.skill AS Title,
    InterviewSceduled.Interviewerid,
    InterviewSceduled.Userid,
    InterviewSceduled.Status
FROM 
    InterviewSceduled
JOIN 
    Skills ON InterviewSceduled.Title = Skills.skillid
WHERE 
    InterviewSceduled.Interviewerid = ${id};`;;
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
});

// select by which Student
app.get("/UsersDetails/:id",(request,response)=>{
    const id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement =`Select Users.FirstName,Users.LastName,Users.Email,Users.Qualification from Users,InterviewSceduled,Interviewers where InterviewSceduled.Userid= Users.Userid and InterviewSceduled.Interviewerid=Interviewers.Interviewerid and Interviewers.Interviewerid=${id}; `;
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
});
app.patch("/updatestatus/:id",(request,response)=>{
    const Interviewid = request.params.id
        var connection = mysql.createConnection(ConnectionDetails);
        var statement = `Update InterviewSceduled set Status = 'approved' where Interviewid =  ${Interviewid};`;
        //console.log(statement)
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
})

// app.delete("/deletescheduled/:id",(request,response)=>{

// });

//set feedback
app.post("/setFeedback",(request,response)=>{
    if(request.body!=null){
        const {Interviewerid,Userid,Feedback}=request.body;
        var connection = mysql.createConnection(ConnectionDetails);
        var statement = `insert into FeedbackRecords(Interviewerid,Userid,Feedback) values(${Interviewerid},${Userid},'${Feedback}')`;
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
        });

//Get All Subjects Types
app.get("/getAllSubjectsTypes",(request,response)=>{
    var connection = mysql.createConnection(ConnectionDetails);
    var statement =`select Subjectid,Subjects,Title from SubjectsTypes`;
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
});

//get all skills
app.get("/getallskills",(request,response)=>{
    var connection = mysql.createConnection(ConnectionDetails);
    var statement =`select skillid,skill from Skills;`;
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
});

//get skill name by skill id
app.get("/getskillname/:id",(request,response)=>{
    const skillid = request.params.id
    var connection = mysql.createConnection(ConnectionDetails);
    var statement =`select skill from Skills where skillid =${skillid};`;
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
});
//interview skills update
app.post("/interviewerskill",(request,response)=>{
    if(request.body!=null){
        const {interviewer_id,skillid}=request.body;
        var connection = mysql.createConnection(ConnectionDetails);
        var statement = `insert into InterviewerSkill(interviewer_id,skillid) values(${interviewer_id},${skillid})`;
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
        });

module.exports= app;