import Navbar from "./Navbar1";

import React, { useEffect,useState} from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/UserProfile.css";
import { toast } from 'react-toastify'
import { myheaders,getCurrentUserid,DobChange} from "../routes/auth"
import axios from 'axios';

const Profile = () => {
const headers = myheaders()
const [userData,setUserData]= useState("")
const [image,setImage] = useState("")
const userid = getCurrentUserid()
const [editMode, setEditMode] = useState(false); 
const [datachanged, setDatachanged] = useState(false); 
useEffect(()=>{
  GetUserData(userid);
},[])

const navigate = useNavigate()

const GetUserData=(id)=>
{
  const url = `http://localhost:9997/user/getuserbyid/${id}`
  try{
    axios.get(url,{headers})
    .then((res)=>{
     console.log(res.data.result[0])
    
      const data = res.data.result[0];
      setUserData(data)
     const imagepath = `http://localhost:9997/${res.data.result[0].Profile}`
     //console.log(imagepath)
     setImage(imagepath)
    
    })
    .catch(err => console.log(err))
  }
  catch(err)
  {
    console.log(err)
  }
}

const OnCancelButtonClick = () => {
  GetUserData(userid);
  setEditMode(false);
};

const OnDeleteButtonClicked = ()=>{
  const murl = `http://localhost:9997/user/deleteUser/${userid}`

  axios.delete(murl,{headers})
  .then((result)=>{
    if(result.data.status=="success")
    {
      toast.success('Profile Deleted')
      navigate("/login")
    }
  })
  .catch((err)=>{
    toast.error('Something went wrong')
    console.log(err)})
}

const OnEditButtonClick = () => {
  setEditMode(!editMode); 
};
const OnSaveButtonClick = () => {
 if(userData.FirstName.length === 0)
 {
  toast.error('First Name cannot be empty')
 }
 else if(userData.LastName.length === 0)
 {
  toast.error('Last Name cannot be empty')
 }
 else if(userData.Mobile.length===0)
 {
  toast.error('Please Enter Mobile No.')
 }
 else if(userData.Mobile.length===0)
 {
  toast.error('Please Enter Mobile No.')
 }
 else if(userData.Address.length===0)
 {
  toast.error('Please Enter Address')
 }
 else if(userData.Qualification.length===0)
 {
  toast.error('Please Enter Qualification Details')
 }
 else{
  if(datachanged)
  {
    const murl = `http://localhost:9997/user/edituserprofile/${userid}`

    axios.put(murl,userData,{headers})
    .then((result)=>{
      if(result.data.status=="success")
      {
        toast.success('Profile Updated Successfully')
      }
    })
    .catch((err)=>{
      toast.error('Something went wrong')
      console.log(err)})
    
  }
 }
  setEditMode(false);
};
const OnDataChange=(args)=>{
    var inputDataCopy = {...userData};
        inputDataCopy[args.target.name]=args.target.value
        //console.log(inputDataCopy)
       //console.log(inputDataCopy)
       setUserData(inputDataCopy)
       setDatachanged(true)
       // setFormUserData(inputDataCopy)

}


  return (
    <>
      <Navbar/>
      <div className="profile-container">
        <div className="profile-header">
          <h2>User Profile</h2>
        </div>
        <div className="profile-content">
          <div className="profile-avatar">
            <img src={image} alt="User Avatar"/>
          </div>
          <div className="profile-details">
          <div class="row">
            <div class="col">
              <input type="text"
               class="form-control"
                value={userData.FirstName}
                 name="FirstName" 
                 readOnly={!editMode}
                 onChange={OnDataChange}
                 />
            </div>
            <div class="col">
              <input type="text" 
              class="form-control" 
              value={userData.LastName} 
              name="LastName" 
              readOnly={!editMode}
              onChange={OnDataChange}/>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <input type="text" 
              class="form-control" 
              value={userData.Email} 
               readOnly/>
            </div>
            <div class="col">
              <input type="text"
               class="form-control"
                value={userData.Mobile}
                name="Mobile" 
              readOnly={!editMode}
                minLength="10"
                  maxLength="10"
              onChange={OnDataChange}/>
            </div>
          </div>
          
          <div class="row">
            <div class="col">
              <input type="text" 
              class="form-control" 
              value={userData.Address} 
              name="Address" 
              readOnly={!editMode}
              onChange={OnDataChange}/>
            </div>
            <div class="col">
              <input type="text" class="form-control" value={userData.Dob !=null && DobChange(userData.Dob)} aria-label="Last name"/>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <input type="text"
               class="form-control" 
               value={userData.Qualification}
               name="Qualification" 
               readOnly={!editMode}
               onChange={OnDataChange}/>
            </div>
            <div class="col">
              <input type="text" class="form-control" value={userData.Gender} aria-label="Last name"/>
            </div>
          </div>
          <div style={{ textAlign: 'center' }}>
          {editMode ? (
                <>
                  <button className="btn btn-success" onClick={OnSaveButtonClick}>Save</button>
                  <button className="btn btn-secondary" onClick={OnCancelButtonClick}>Cancel</button>
                </>
              ) : (
                <button className="btn btn-primary" onClick={OnEditButtonClick}>Edit</button>
              )}
           <button className="btn btn-danger"style={{ marginLeft: '10px' }} onClick={OnDeleteButtonClicked}>Delete</button>   
        </div>
        
        </div>
        </div>
      </div>
      
    </>
  );
};

export default Profile;

