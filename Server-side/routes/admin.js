const mysql=require('mysql2');
const express  = require('express')
const config = require('config')
const app = express.Router();
const jwt = require('jsonwebtoken')
const config2 = require('../config/configjwt')
var ConnectionDetails ={
    host:config.get("server"),
    database:config.get("database"),
    user:config.get("user"),
    password:config.get("password"),
 }
// Login Admin 
 app.post("/login",(request,response)=>{
    const {Email,Password}= request.body;
    var statement  = `Select Adminid,Name,Email from Admin WHERE Email='${Email}' AND Password='${Password}'`;
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
                console.log(users[0])
              const user = users[0]
              console.log(user)
              // create a payload for jwt token
              const payload = {
                Userid:user['Adminid'],
                name: user['Name'],
                type: 'admin',
              }
      
              // create a token
              const token = jwt.sign(payload, config2.secrete)
              var reply ={
                "status":"success",
                "token":token,
                  "AdminName":user['Name'],
                "Adminid":user['Userid']
              }
              response.send(JSON.stringify(reply))
              
            }
          }
     }) 
 });
// Register Admin 
 app.post("/register",(request,response)=>{
    if(request.body!=null){
    const {Name,Email,Password}=request.body;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `insert into Admin(Name,Email,Password,Role) values('${Name}','${Email}','${Password}','Admin')`;
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

//Edit  Admin
app.put("/edit/:id",(request,response)=>{
    var id = request.params.id;
    const {Name,Email,Password}=request.body;

    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `update Admin set Name='${Name}',Email='${Email}',Password='${Password}' where Adminid=${id}`;
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

// Delete Admin
app.delete("/delete/:id",(request,response)=>{
    const id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails)
    var statement = `DELETE From Admin WHERE Adminid=${id}`;
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

//getadminbyid
app.get("/getadminbyid/:No",(request,response)=>{
    var id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `SELECT Name,Email,Password from Admin WHERE Adminid= ${id}`;
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




// Add SubjectsTypes
app.post("/addSubjects",(request,response)=>{
    if(request.body!=null){
    const {Subjects,Title}=request.body;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `insert into SubjectsTypes(Subjects,Title) values('${Subjects}','${Title}')`;
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

// Delete SubjectsTypes
app.delete("/deleteSubjects/:id",(request,response)=>{
    const id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails)
    var statement = `DELETE From SubjectsTypes WHERE Subjectid=${id}`;
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

app.get("/getsubjectstypes",(request,response)=>{
    var connection  = mysql.createConnection(ConnectionDetails);
    var statement = `SELECT Subjectid,Subjects,Title FROM SubjectsTypes;`
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

//get demo questions
app.get("/demoquestions",(request,response)=>{
    var connection  = mysql.createConnection(ConnectionDetails);
    var statement = `Select Questionid,SubjectsTypes.Title,SubjectsTypes.Subjects,Question,Answer  
                    from SubjectsTypes,DemoQuestions 
                    where DemoQuestions.Subjectid = SubjectsTypes.Subjectid;`
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


//add demo questions;
app.post("/addDemoQuestion",(request,response)=>{
    const {Question,Answer,Subjectid}=request.body;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `INSERT INTO DemoQuestions (Question, Answer,Subjectid) values ("${Question}","${Answer}",${Subjectid});`;
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

//edit demo questions 
app.put("/editdemoquestion/:id",(request,response)=>{
    var id = request.params.id;
    const {Question,Answer}=request.body;

    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `Update DemoQuestions set Question="${Question}",Answer="${Answer}" where Questionid=${id}`;
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
//delete demo questions
app.delete("/deletedemoquestion/:id",(request,response)=>{
    const id = request.params.id
    var connection = mysql.createConnection(ConnectionDetails)
    var statement = `DELETE From DemoQuestions WHERE Questionid=${id}`;
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


app.get("/admin",(request,response)=>{
   // const id = request.params.id;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement =`select Interviewid,Date,Title,Userid from InterviewSceduled `;
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

//Questions for Users --------------------------------

//adding demo questions
app.post("/addQuestion",(request,response)=>{
    const {Question,Answer,skillid}=request.body;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `INSERT INTO QuestionsForUsers (Question, Answer,skillid) values ("${Question}","${Answer}",${skillid});`;
    console.log(statement)
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

//getting all demoquestion
app.get("/Question/:id",(request,response)=>{
    const skillid=request.params.id;
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `select Questionid,Question,Answer,skillid from QuestionsForUsers where skillid =${skillid};`;
   // console.log(statement)
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






//Get all students
app.get("/getallusers",(request,response)=>{
    var connection  = mysql.createConnection(ConnectionDetails);
    var statement = `Select Userid,FirstName,LastName,Email,Password,Mobile,Address,Dob,Qualification,Gender,Role from Users`
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
//Delete any student
app.delete("/deleteUser/:id",(request,response)=>{
    const userid = request.params.id
    var connection = mysql.createConnection(ConnectionDetails)
    var statement = `DELETE From Users WHERE Userid=${userid}`;
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
//Get all interviewer
app.get("/getallinterviewer",(request,response)=>{
    var connection = mysql.createConnection(ConnectionDetails);
    var statement = `select Interviewerid,FirstName,LastName,Email,Password,Mobile,Address,Dob,CompanyPosition,QualifiedDegree,ProfileUpdated,Gender,Role from Interviewers`;
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

//delete any interviewer
app.delete("/deleteinterviewer/:id",(request,response)=>{
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

 module.exports = app;