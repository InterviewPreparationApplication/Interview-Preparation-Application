import { Fragment, useEffect, useState } from "react";
import axios from "axios";
import { Link,useNavigate } from "react-router-dom";
import "../css/interviewer.css"
import Navbar from "../Navbar";
import { toast } from 'react-toastify'

function IntRegister() {
    
    const [FormIntData,setFormIntData] = useState({FirstName:"",LastName:"",
                                             Email:"",Password:"",
                                             CnfPassowrd:"",Mobile:"",
                                             Address:"",Dob:"",
                                            QualifiedDegree:"",
                                            CompanyPosition:"",Gender:""
                                            });
    const [image, setImage] = useState("");

    const [Error,setError] = useState(false);                                                                
    const [isSubmit,setisSumbit] =useState(false);
    const [ErrFirstName,setErrFirstName] = useState("");
    const [ErrLastName,setErrLastName] = useState("");
    const [ErrEmail,setErrEmail] = useState("");
    const [ErrPassword,setErrPassword] = useState("");
    const [ErrCnfPassword,setCnfErrPassword] = useState("");
    const [ErrMobile,setErrMobile] = useState("");
    const [ErrAddress,setErrAddress] = useState("");
    const [ErrDob,setErrDob] = useState("");
    const [ErrQualifiedDegree,setErrQualifiedDegree] = useState("");
    const [ErrGender,setErrGender] = useState("");
    const [ErrCompany,setErrCompany] = useState("");

    const navigate = useNavigate()

    //on text change
   const OnTextChange = (args)=>{
        var inputDataCopy = {...FormIntData};
        inputDataCopy[args.target.name]=args.target.value
        //console.log(inputDataCopy)
       // console.log(inputDataCopy)
        setFormIntData(inputDataCopy)
    }

    const handleImageChange = (e) => {
      const file =e.target.files[0]
      setImage(file);
    };

    const submitReg = (e)=>
    {
      e.preventDefault()
      if(validForm() && validDataForm())
      {
        const formData = new FormData();
        for (const [key, value] of Object.entries(FormIntData)) {
          formData.append(key, value);
        }
       formData.append('Image',image)
      //  for (const [key, value] of FormIntData.entries()) {
      //   console.log(`${key}: ${value}`);
      //  }
        try{
          axios.post("http://127.0.0.1:9997/interviewer/register",formData).then((result)=>{
            //console.log(result.data);
            //console.log(result.data.status);
            //console.log(result)
            if(result.data.status=="success")
            {
              toast.success("User Registerd Successfully")
             navigate("/ourexperts/login")
            }
            else if (result.data.status=="error")
            {
              console.log(result)
              toast.error("Email is already used")
            }
            else
            {
              toast.error("Something went wrong")
            }
          })
        }
        catch(ex)
        {
          toast.error("Something went wrong")
            console.log(ex)
        }
      }
      else{
        toast.error("data is invalid")
      }
    }
    
    const validForm=()=>{
      return (
        ErrFirstName === "" &&
        ErrLastName === "" &&
        ErrEmail === "" &&
        ErrPassword === "" &&
        ErrCnfPassword === "" &&
        ErrMobile === "" &&
        ErrAddress === "" &&
        ErrDob === "" &&
        ErrQualifiedDegree === "" &&
        ErrGender === "" && ErrCompany === ""
      );
      
    }

    const validDataForm=()=>{
      return (
        FormIntData.FirstName !== "" &&
        FormIntData.LastName !== "" &&
        FormIntData.Email !== ""&&
        FormIntData.Password !== "" &&
        FormIntData.CnfPassowrd !== "" &&
        FormIntData.Mobile !== "" &&
        FormIntData.Address !== "" &&
        FormIntData.Dob !== "" &&
        FormIntData.QualifiedDegree !== "" &&
        FormIntData.Gender !== "" && FormIntData.CompanyPosition !==""
      );
      
    }

//validation code
   const validateField=(event)=>
   { //debugger;
    const { name, id, value } = event.target;
    switch(name)
    {
        case 'FirstName':
          //const isValidInput = /\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+/.test(FormIntData.FirstName);
          if(FormIntData.FirstName=="")
          {
            setErrFirstName("First Name Cannot Be Blank")
          }
          else if(FormIntData.FirstName.length<4 &&FormIntData.FirstName.length<19){
          setErrFirstName("Name should not less than 4 and not greater than 19")
          }
          else{
            setErrFirstName("")
          }
        break;

        case 'LastName':
          //const isValidLastName = /\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+/.test(FormIntData.LastName);
          if(FormIntData.LastName=="")
          {
            setErrLastName("Last Name Cannot Be Blank")
          }
          else if(FormIntData.LastName.length<4 &&FormIntData.LastName.length<19){
            setErrLastName("Name should not less than 4 and not greater than 19")
          }
          else{
            setErrLastName("")
          }
        break;

        case 'Email':
          const EmailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(FormIntData.Email);
          if(FormIntData.Email=="")
          {
            setErrEmail("Email Cannot Be Blank")
          }
          // else if(FormIntData.LastName.length<4 &&FormIntData.LastName.length<19){
          //   setErrEmail("Name should not less than 4 and not greater than 19")
          // }
          else if(!EmailRegex){
            setErrEmail("Email is Invalid")
          }
          else{
            setErrEmail("")
          }
        break;
        
        case 'Password':
          if(FormIntData.Password=="")
          {
            setErrPassword("Password Cannot Be Blank")
          }
          else{
            setErrPassword("")
          }
          break;

        case 'CnfPassowrd':
          var notblank =false;
          if(FormIntData.CnfPassowrd=="")
          {
            notblank = false;
            setCnfErrPassword("Confirm Password Cannot Be Blank")
          }
          else{
            notblank = true;
            setCnfErrPassword("")
          }
          if(notblank &&FormIntData.Password == FormIntData.CnfPassowrd)
          {
            setCnfErrPassword("")
          }
          else
          {
            setCnfErrPassword("Password Not Match")
          }
          break;
          
        case 'Mobile':
            if(FormIntData.Mobile=="")
          {
            setErrMobile("Mobile Cannot Be Blank")
          }
          else if(parseInt(FormIntData.Mobile)==NaN)
          {
            alert()
          }
          else{
            setErrMobile("")
          }
          break;
         
        case  'Address':
          if(FormIntData.Address=="")
          {
            setErrAddress("Address Cannot Be Blank")
          }
          else{
            setErrAddress("")
          }
          break;
        
        case 'Dob':
          if(FormIntData.Dob=="")
          {
            setErrDob("Dob Cannot Be Blank")
          }
          else{
            setErrDob("")
          }
          break;
        
        case  'QualifiedDegree':
          if(FormIntData.QualifiedDegree=="")
          {
            setErrQualifiedDegree("QualifiedDegree Cannot Be Blank")
          }
          else{
            setErrQualifiedDegree("")
          }
          break;
        case 'Gender':
          if(FormIntData.Gender=="")
          {
            setErrGender("Gender Cannot Be Blank")
          }
          else{
            setErrGender("")
          }
          break;

          case 'CompanyPosition':
          if(FormIntData.CompanyPosition.length==0)
          {
            setErrCompany("CompanyPosition Cannot Be Blank")
          }
          else{
            setErrCompany("")
          }
          break;

    }
   }//end of validation code

   //clear data on clear button
    const clearData = ()=>{
        setFormIntData({
            FirstName:"",LastName:"",
            Email:"",Password:"",
            CnfPassowrd:"",Mobile:"",
            Address:"",Dob:"",
            QualifiedDegree:"",Gender:""
        })
    } 
    
    
    return ( 
       
        <div>
            <Navbar/>
           
                <center>
            <form  onSubmit={submitReg}>
            <div className="register-box">
                <h2> Register Interviewer</h2>
                
                <button className="btn btn-secondary"><Link to="/ourexperts" className="GoLink">Go Back</Link></button>
                <div>
                <label htmlFor="FirstName">First Name:</label>
                  <input 
                  className="form-control" 
                  type="text" 
                  value={FormIntData.FirstName} 
                  name="FirstName" 
                  onChange={OnTextChange}
                  onBlur={validateField}
                  minlength="2"
                  maxlength="18"
                  required>
                  </input>
                  <span>{ErrFirstName}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="LastName">Last Name:</label>
                  <input
                  className="form-control"
                  type="text" 
                  value={FormIntData.LastName}
                  name="LastName" 
                  minlength="2"
                  maxlength="18"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrLastName}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="Email">Email</label>
                  <input
                  id="Email"
                  className="form-control"
                  type="Email"
                  placeholder="newuser@gmail.com"
                  value={FormIntData.Email}
                  name="Email"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrEmail}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="Password">Password</label>
                  <input
                  id="Password"
                  className="form-control"
                  type="Password"
                  placeholder="xxxxxxx"
                  value={FormIntData.Password}
                  name="Password"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrPassword}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="cnfPassword">Confirm Password</label>
                  <input
                  id="cnfPassword"
                  className="form-control"
                  type="text"
                  placeholder="Test@123"
                  value={FormIntData.CnfPassowrd}
                  name="CnfPassowrd"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrCnfPassword}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="Mobileno">Mobile No:</label>
                  <input
                  id="Mobileno"
                  className="form-control"
                  type="text"
                  placeholder="eg@91xxxxx09"
                  value={FormIntData.Mobile}
                  name="Mobile"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}
                  minlength="10"
                  maxlength="10">
                  
                  </input>
                  <span>{ErrMobile}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="Address">Address:</label>
                  <input
                  id="Address"
                  className="form-control"
                  type="text" 
                  value={FormIntData.Address} 
                  name="Address" 
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrAddress}</span>
                  
                </div>

                <div>
                <label htmlFor="Dob">Date of Birth:</label>
                  <input
                 id="Dob"
                  className="form-control"
                  type="date"
                  value={FormIntData.Dob}
                  name="Dob"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrDob}</span>
                  
                </div>

                <div>
                <label htmlFor="CompanyPosition">CompanyPosition</label>
                  <input
                 id="CompanyPosition"
                  className="form-control"
                  type="text"
                  value={FormIntData.CompanyPosition}
                  name="CompanyPosition"
                  placeholder="@ex HR recuriter"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrCompany}</span>
                  
                </div>

                <div>
                <label htmlFor="QualifiedDegree">Highest QualifiedDegree:</label>
                  <input
                  id="QualifiedDegree"
                  type="text"
                  className="form-control"
                  placeholder="Eg@B.Tech/B.Sc"
                  value={FormIntData.QualifiedDegree}
                  name="QualifiedDegree"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrQualifiedDegree}</span>
                  
                </div>

                <div>
                <label htmlFor="gender">Gender:</label>
                <select id="gender" 
                name="Gender"
                className="form-select"
                placeholder="Select Gender"
                value={FormIntData.Gender}
                onBlur={validateField}
                onChange={OnTextChange}>
                  <option value="" disabled selected>Select Gender</option>
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                  <option value="other">Other</option>
                </select>
                <span>{ErrGender}</span>
                </div>

                <div>
                  <label>Enter Profile photo</label>
                    <input type="file" onChange={handleImageChange} id="imagename"/>
                </div>
                <div>
                  <input type="Submit" value="Submit" onClick={submitReg}></input>
                  {"  "}{"  "}{"    "}
                  <input type="button" value="Clear" onClick={clearData}></input>
                  <br/><br/>
                </div>
                
            </div>
            </form>
           
            

        </center>

        </div>
        
     );
    }


export default IntRegister;