import "../css/Login.css";
import { Fragment, useEffect, useState } from "react";
import "../css/Register.css"
import axios from "axios";
import Navbar from "../Navbar";
import Footer from "../Footer";
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify'

function Register() {

    const [FormUserData,setFormUserData] = useState({FirstName:"",LastName:"",
                                             Email:"",Password:"",
                                             CnfPassowrd:"",Mobile:"",
                                             Address:"",Dob:"",
                                            Qualification:"",Gender:""
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
    const [ErrQualification,setErrQualification] = useState("");
    const [ErrGender,setErrGender] = useState("");

    const navigate = useNavigate()

    //on text change
   const OnTextChange = (args)=>{
        var inputDataCopy = {...FormUserData};
        inputDataCopy[args.target.name]=args.target.value
        //console.log(inputDataCopy)
       // console.log(inputDataCopy)
        setFormUserData(inputDataCopy)
    }

    const handleImageChange = (e) => {
      const file =e.target.files[0]
      setImage(file);
    };


    const submitReg =async (e)=>
    {
      e.preventDefault()
      if(validForm() && validDataForm())
      {
        const formData = new FormData();
        for (const [key, value] of Object.entries(FormUserData)) {
          formData.append(key, value);
        }
       formData.append('Image',image)
      //  for (const [key, value] of formData.entries()) {
      //   console.log(`${key}: ${value}`);
      //  }
      
        try{
          axios.post("http://localhost:9997/user/register",formData).then((result)=>{
            // console.log(result.data);
            // console.log(result.data.status);
            // console.log(result)
             if(result.data.status=="success")
             {
               toast.success("User Registerd Successfully")
               navigate("/login")
             }
             else if (result.data.status=="error")
             {
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
        ErrQualification === "" &&
        ErrGender === ""
      );
      
    }

    const validDataForm=()=>{
      return (
        FormUserData.FirstName !== "" &&
        FormUserData.LastName !== "" &&
        FormUserData.Email !== ""&&
        FormUserData.Password !== "" &&
        FormUserData.CnfPassowrd !== "" &&
        FormUserData.Mobile !== "" &&
        FormUserData.Address !== "" &&
        FormUserData.Dob !== "" &&
        FormUserData.Qualification !== "" &&
        FormUserData.Gender !== ""
      );
      
    }

//validation code
   const validateField=(event)=>
   { //debugger;
    const { name, id, value } = event.target;
    switch(name)
    {
        case 'FirstName':
          const isValidInput = /\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+/.test(FormUserData.FirstName);
          if(FormUserData.FirstName=="")
          {
            setErrFirstName("First Name Cannot Be Blank")
          }
          else if(FormUserData.FirstName.length<4 &&FormUserData.FirstName.length<19){
          setErrFirstName("Name should not less than 4 and not greater than 19")
          }
          else if(!isValidInput){
            setErrFirstName("Name cannot be number or special symbols")
          }
          else{
            setErrFirstName("")
          }
        break;

        case 'LastName':
          const isValidLastName = /\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+/.test(FormUserData.LastName);
          if(FormUserData.LastName=="")
          {
            setErrLastName("Last Name Cannot Be Blank")
          }
          else if(FormUserData.LastName.length<4 &&FormUserData.LastName.length<19){
            setErrLastName("Name should not less than 4 and not greater than 19")
          }
          else if(!isValidLastName){
            setErrLastName("Name cannot be number or special symbols")
          }
          else{
            setErrLastName("")
          }
        break;

        case 'Email':
          const EmailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(FormUserData.Email);
          if(FormUserData.Email=="")
          {
            setErrEmail("Email Cannot Be Blank")
          }
          // else if(FormUserData.LastName.length<4 &&FormUserData.LastName.length<19){
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
          if(FormUserData.Password=="")
          {
            setErrPassword("Password Cannot Be Blank")
          }
          else{
            setErrPassword("")
          }
          break;

        case 'CnfPassowrd':
          var notblank =false;
          if(FormUserData.CnfPassowrd=="")
          {
            notblank = false;
            setCnfErrPassword("Confirm Password Cannot Be Blank")
          }
          else{
            notblank = true;
            setCnfErrPassword("")
          }
          if(notblank &&FormUserData.Password == FormUserData.CnfPassowrd)
          {
            setCnfErrPassword("")
          }
          else
          {
            setCnfErrPassword("Password Not Match")
          }
          break;
          
        case 'Mobile':
            if(FormUserData.Mobile=="")
          {
            setErrMobile("Mobile Cannot Be Blank")
          }
          else if(parseInt(FormUserData.Mobile)==NaN)
          {
            alert()
          }
          else{
            setErrMobile("")
          }
          break;
         
        case  'Address':
          if(FormUserData.Address=="")
          {
            setErrAddress("Address Cannot Be Blank")
          }
          else{
            setErrAddress("")
          }
          break;
        
        case 'Dob':
          if(FormUserData.Dob=="")
          {
            setErrDob("Dob Cannot Be Blank")
          }
          else{
            setErrDob("")
          }
          break;
        
        case  'Qualification':
          if(FormUserData.Qualification=="")
          {
            setErrQualification("Qualification Cannot Be Blank")
          }
          else{
            setErrQualification("")
          }
          break;
        case 'Gender':
          if(FormUserData.Gender=="")
          {
            setErrGender("Gender Cannot Be Blank")
          }
          else{
            setErrGender("")
          }
          break;

    }
   }//end of validation code

   //clear data on clear button
    const clearData = ()=>{
        setFormUserData({
            FirstName:"",LastName:"",
            Email:"",Password:"",
            CnfPassowrd:"",Mobile:"",
            Address:"",Dob:"",
            Qualification:"",Gender:""
        })
    } 
  
    
    return ( 
        <center>
           <Navbar/>
            <form  onSubmit={submitReg} >
            <div className="LoginBox ">
                <h2> Register</h2>
                <div>
                <label htmlFor="FirstName">First Name:</label>
                  <input 
                  className="form-control" 
                  type="text" 
                  value={FormUserData.FirstName} 
                  name="FirstName" 
                  onChange={OnTextChange}
                  onBlur={validateField}
                  minlength="2"
                  maxlength="18"
                  placeholder="@Ex. User"
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
                  value={FormUserData.LastName}
                  name="LastName" 
                  placeholder="@Ex. User"
                  minlength="1"
                  maxlength="18"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrLastName}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="email">Email</label>
                  <input
                  id="email"
                  className="form-control"
                  type="Email"
                  placeholder="newuser@gmail.com"
                  value={FormUserData.Email}
                  name="Email"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrEmail}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="password">Password</label>
                  <input
                  id="password"
                  className="form-control"
                  type="Password"
                  placeholder="xxxxxxx"
                  value={FormUserData.Password}
                  name="Password"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrPassword}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="cnfpassword">Confirm Password</label>
                  <input
                  id="cnfpassword"
                  className="form-control"
                  type="text"
                  placeholder="Test@123"
                  value={FormUserData.CnfPassowrd}
                  name="CnfPassowrd"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrCnfPassword}</span>
                  <br/>
                </div>

                <div>
                <label htmlFor="mobileno">Mobile No:</label>
                  <input
                  id="mobileno"
                  className="form-control"
                  type="text"
                  placeholder="eg@91xxxxx09"
                  value={FormUserData.Mobile}
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
                  value={FormUserData.Address} 
                  name="Address" 
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrAddress}</span>
                  
                </div>

                <div>
                <label htmlFor="DOB">Date of Birth:</label>
                  <input
                 id="DOB"
                  className="form-control"
                  type="date"
                  value={FormUserData.Dob}
                  name="Dob"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrDob}</span>
                  
                </div>

                <div>
                <label htmlFor="Qualification">Highest Qualification:</label>
                  <input
                  id="Qualification"
                  type="text"
                  className="form-control"
                  placeholder="Eg@B.Tech/B.Sc"
                  value={FormUserData.Qualification}
                  name="Qualification"
                  required
                  onBlur={validateField}
                  onChange={OnTextChange}>
                  </input>
                  <span>{ErrQualification}</span>
                  
                </div>

                <div>
                <label htmlFor="gender">Gender:</label>
                <select id="gender" 
                name="Gender"
                className="form-select"
                placeholder="Select Gender"
                value={FormUserData.Gender}
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
                    <input type="file" onChange={handleImageChange} value={FormUserData.imagename} id="imagename"/>
                </div>
                  
                <div>
                  <input type="Submit" value="Submit" onClick={submitReg}></input>
                  {"  "}{"  "}{"    "}
                  <input type="button" value="Clear" onClick={clearData}></input>
                  <br/><br/>
                </div>
                
            </div>
            </form>
            <Footer/>
            

        </center>
        
     );
    }
export default Register;