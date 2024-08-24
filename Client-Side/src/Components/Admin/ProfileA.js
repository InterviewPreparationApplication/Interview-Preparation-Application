import { myheaders,getCurrentUserid } from "../routes/auth";
import NavbarA from "./NavbarA";
import { useState,useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import {toast} from 'react-toastify';

import axios from "axios";
function ProfileA() {
    const headers = myheaders()
    const [userData,setUserData]= useState("")
    const userid = getCurrentUserid()
    const [editMode, setEditMode] = useState(false); 
    const [datachanged, setDatachanged] = useState(false); 
    const [admin, setAdmin] = useState(null);
    const [error, setError] = useState(null);

    useEffect(()=>{
        GetUserData(userid);
      },[])
      
      const navigate = useNavigate()
      
      const GetUserData=(id)=>
      {
        const url = `http://127.0.0.1:9997/admin/getadminbyid/${id}`
        try{
          axios.get(url,{headers})
          .then((res)=>{
           console.log(res.data)
          
            const data = res.data.result;
            setUserData(data)  
        })
        .catch(err => console.log(err))
      }
      catch(err)
      {
        console.log(err)
      }
    }
  
      
      const OnDeleteButtonClicked = ()=>{
        const murl = `http://127.0.0.1:9997/admin/delete/${userid}`
      
        axios.delete(murl,{headers})
        .then((result)=>{
          if(result.data.status=="success")
          {
            toast.success('Profile Deleted')
            navigate("/")
          }
        })
        .catch((err)=>{
          toast.error('Something went wrong')
          console.log(err)})
      }
      const OnDataChange = (e) => {
        const { name, value } = e.target;
        setUserData(prevData => ({ ...prevData, [name]: value }));
        setDatachanged(true);
      }
 

    return ( <>
    <NavbarA/>
    <div className="profile-container">
            <div className="profile-header">
              <h2>Admin Profile</h2>
            </div>
            <div className="profile-content">
              <div className="profile-details">
              <div class="row">
                <div class="col">
                  <input type="text"
                   class="form-control"
                    value={userData?.Name || ''}
                     name="Name" 
                     readOnly={!editMode}
                     onChange={OnDataChange}
                     />
                </div>
                <div class="col">
                  <input type="text" 
                  class="form-control" 
                  value={userData?.Email || ''} 
                  name="Email" 
                  readOnly={!editMode}
                  onChange={OnDataChange}/>
                </div>
              </div>
              <div style={{ textAlign: 'center' }}>
               <button className="btn btn-danger"style={{ marginLeft: '10px' }} onClick={OnDeleteButtonClicked}>Delete</button>   
            </div>
            
            </div>
            </div>
          </div>
    </> );
}

export default ProfileA;