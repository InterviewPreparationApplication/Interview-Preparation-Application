import { useNavigate } from "react-router-dom";
import "../css/ForgetPassword.css"
import { useState } from "react";
import Navbar from "../Navbar";
import "../css/Login.css"
function ForgetPassword() {

const [Email,SetEmail] = useState('')
const [name,SetName] = useState('')
const [passowrd,SetPassword] = useState('')
const [Cpassowrd,SetCPassword] = useState('')


const navigate = useNavigate();

    const ChangePassword = ()=>
    {
        
    }

    const Cancel = ()=>
    {
        navigate("/login")
    }

    return ( 
        <>
        
           <center>
           
                <div id="container" className="LoginBox">
                    <h2>Change Your Password</h2>
                    <div>
                        <input type="text" placeholder="Enter Your Email" onChange={(e)=>{}}/> <br/><br/>
                    </div>
                    <div>
                        <input type="text" placeholder="Enter Your Name"/> <br/><br/>
                    </div>
                    <div>
                        <input type="text" placeholder="Enter Your Password"/> <br/><br/>
                    </div>
                    <div>
                        <input type="text" placeholder="Confirm Password"/><br/><br/>
                    </div>
                    <div>
                        <input type="button" value="Submit" onClick={ChangePassword}/> {"  "}
                        <input type="button" value="Cancel" onClick={Cancel}/><br/><br/>
                    </div>
                </div>
           </center>
        </>
     );
}

export default ForgetPassword;