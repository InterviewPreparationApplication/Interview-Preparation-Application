import "../css/Login.css";
import "../css/interviewer.css"
import { useState } from "react";
import axios from "axios";
import Navbar from "../Navbar";
import { Link, useNavigate} from "react-router-dom";
import Footer from "../Footer";
import { doLogin, doLoginInterviewer } from "../routes/auth";
import { toast } from 'react-toastify'

function IntLogin() {

    const [credentials,setcredentials] = useState({"Email":"","Password":""});
    const [isChecked,setIsChecked] = useState(false)
    const navigate = useNavigate();
   const OnTextChange = (args)=>{
        var inputDataCopy = {...credentials};
        inputDataCopy[args.target.name]=args.target.value
        //console.log(inputDataCopy.Email)
       // console.log(inputDataCopy.Password)
        setcredentials(inputDataCopy)
    }

    const OnCheckboxChange = ()=>{
        setIsChecked(!isChecked)
    }
   const onLogin = (e)=>
   {
    e.preventDefault()
    //alert(credentials.Email+"  "+credentials.Password+"  "+isChecked)
    var copyofInput = {...credentials}
    try{
        axios.post("http://127.0.0.1:9997/interviewer/login",copyofInput).then((result)=>{
          //console.log(result.data);
          //console.log(result.data.status);
          if(result.data.status=="success")
          {
            toast.success("User Login Successfully")
            console.log(result.data);
            //doLoginInterviewer(result.data)
            doLogin(result.data)
            navigate("interviewer/dashboard")
          }
          else if (result.data.status=="error")
          {
            toast.error("Username or Password is incorrect")
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





    return (  
        <>
        <Navbar/>
          <div className="d-flex justify-content-center align-items-center">
          <center>
           <h1> Login</h1>
                <form className="">
                <div className="register-box">
                    <div className="field">
                        <h2>Interviewer</h2>
                            <div className="">
                                <input type="text"
                                placeholder="Enter Your Email"
                                value={credentials.Email} 
                                name="Email" 
                                onChange={OnTextChange}/>
                                <span></span>
                            </div>
                            
                            <div className="">
                                <input type="password"
                                placeholder="Enter Your Password"
                                value={credentials.Password} 
                                name="Password" 
                                onChange={OnTextChange}/>
                                <span></span>
                            </div>
                            
                            <input
                                    id="rememberMe"
                                    type="checkbox" 
                                    name="checkbox"
                                    checked={isChecked}
                                    onChange={OnCheckboxChange}/>
                            <div className="">
                                    
                            </div>
                                <label class="" for="rememberMe">Remember Me</label>
                            

                            <div className="form-group">
                            <button onClick={onLogin} className="maroon-button">Login</button> <br/> <br/>
                            <Link to="/ourexperts/register">Register Here</Link>
                            <br/> <br/>
                            <Link to="/forgetpassword">Forget Password</Link>
                            </div>                    
                    </div>
                    </div>

                </form>
           </center>
          </div>
        <Footer/>
        </>
    );
}

export default IntLogin;