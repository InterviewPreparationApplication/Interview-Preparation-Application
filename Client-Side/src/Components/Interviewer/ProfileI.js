import Navbar from "./Navbar";
import Select from "react-select"
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/UserProfile.css";
import { toast } from 'react-toastify'
import { myheaders, getCurrentUserid, DobChange } from "../routes/auth"
import axios from 'axios';

function ProfileI() {
  const headers = myheaders()
  const [userData, setUserData] = useState("")
  const [image, setImage] = useState("")
  const userid = getCurrentUserid()
  const [editMode, setEditMode] = useState(false);
  const [datachanged, setDatachanged] = useState(false);
  const [value, setValue] = useState(null)
  const [skill,setSkill] = useState(null)
  const [skillupdated,setSkillUpdated]=useState(false)

  useEffect(() => {
    GetUserData(userid);
    GetAllSkills()
  }, [])

  const navigate = useNavigate()

  const GetUserData = (id) => {
    const url = `http://localhost:9997/interviewer/getinterviewerbyid/${id}`
    try {
      axios.get(url, { headers })
        .then((res) => {
          // console.log(res.data.result[0].Profile)

          const data = res.data.result[0];
          setUserData(data)
          const imagepath = `http://localhost:9997/${res.data.result[0].Profile}`
          //console.log(imagepath)
          setImage(imagepath)

        })
        .catch(err => console.log(err))
    }
    catch (err) {
      console.log(err)
    }
  }

  const GetAllSkills = ()=>{
    axios.get("http://127.0.0.1:9997/interviewer/getallskills", { headers })
    .then(res => setSkill(res.data.result))
    .catch(err => console.log(err));
  }

  const OnCancelButtonClick = () => {
    GetUserData(userid);
    setEditMode(false);
  };

  const OnDeleteButtonClicked = () => {
    const murl = `http://127.0.0.1:9997/interviewer/delete/${userid}`

    axios.delete(murl, { headers })
      .then((result) => {
        if (result.data.status == "success") {
          toast.success('Profile Deleted')
          navigate("/")
        }
      })
      .catch((err) => {
        toast.error('Something went wrong')
        console.log(err)
      })
  }

  const OnEditButtonClick = () => {
    setEditMode(!editMode);
  };
  const OnSaveButtonClick = () => {
    if (userData.FirstName.length === 0) {
      toast.error('First Name cannot be empty')
    }
    else if (userData.LastName.length === 0) {
      toast.error('Last Name cannot be empty')
    }
    else if (userData.Mobile.length === 0) {
      toast.error('Please Enter Mobile No.')
    }
    else if (userData.Mobile.length === 0) {
      toast.error('Please Enter Mobile No.')
    }
    else if (userData.Address.length === 0) {
      toast.error('Please Enter Address')
    }
    else if (userData.CompanyPosition.length === 0) {
      toast.error('Please Enter CompanyPosition Details')
    }
    else {
      if (datachanged) {
        const murl = `http://127.0.0.1:9997/interviewer/edit/${userid}`

        axios.put(murl, userData, { headers })
          .then((result) => {
            if (result.data.status == "success") {
              toast.success('Profile Updated Successfully')
            }
          })
          .catch((err) => {
            toast.error('Something went wrong')
            console.log(err)
          })

      }
    }
    setEditMode(false);
  };
  const OnDataChange = (args) => {
    var inputDataCopy = { ...userData };
    inputDataCopy[args.target.name] = args.target.value
    //console.log(inputDataCopy)
    console.log(inputDataCopy)
    setUserData(inputDataCopy)
    setDatachanged(true)
    // setFormUserData(inputDataCopy)

  }

  const renderSkills = () => {
    if (Array.isArray(skill)) {
      return skill.map(item => (
        {value:item.skillid,label:item.skill}
      ));
    } else {
      return {value:"",label:""}
    }
  };

  const setInterviewerSkill=(id)=>{
    const url = `http://127.0.0.1:9997/interviewer/interviewerskill`
    const body= {
      "interviewer_id":userid,
      "skillid":id
    }
    try{
        axios.post(url,body,{headers})
        .then((res)=>{
          //console.log(res)
          setSkillUpdated(false)
        })
        .catch((err)=>{
          console.log(err)
        })
    }
    catch(ex)
    {
      toast.error("something went wrong")
      console.log(ex)
    }
  }
  const options = renderSkills()

  const setSkills=()=>{
   if(value.length>0)
    {
      //console.log(value.length)
      for(var i=0;i<value.length;i++)
      {
      console.log(value[i].value,value[i].label)
        setInterviewerSkill(value[i].value)
      }
      toast.success("Skills updated")
    }
  else
  {
    toast.error("Select any skill")
  }
  }

  const openSelectBox=()=>{
    setSkillUpdated(true)
  }
  return (
    <>
      <Navbar />
      <div className="profile-container" style={{ backgroundColor: "grey" }}>
        <div className="profile-header">
        <h2 style={{color:"white",}}>User Profile</h2>
        </div>
        <div className="profile-content">
          <div className="profile-avatar">
            <img src={image} alt="User Avatar" />
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
                  onChange={OnDataChange} />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <input type="text"
                  class="form-control"
                  value={userData.Email}
                  readOnly />
              </div>
              <div class="col">
                <input type="text"
                  class="form-control"
                  value={userData.Mobile}
                  name="Mobile"
                  readOnly={!editMode}
                  minLength="10"
                  maxLength="10"
                  onChange={OnDataChange} />
              </div>
            </div>

            <div class="row">
              <div class="col">
                <input type="text"
                  class="form-control"
                  value={userData.Address}
                  name="Address"
                  readOnly={!editMode}
                  onChange={OnDataChange} />
              </div>
              <div class="col">
                <input type="text" class="form-control" value={userData.Dob != null && DobChange(userData.Dob)} aria-label="Last name" />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <input type="text"
                  class="form-control"
                  value={userData.CompanyPosition}
                  name="CompanyPosition"
                  readOnly={!editMode}
                  onChange={OnDataChange} />
              </div>
              <div class="col">
                <input type="text" class="form-control" value={userData.Gender} aria-label="Last name" />
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
              <button className="btn btn-danger" style={{ marginLeft: '10px' }} onClick={OnDeleteButtonClicked}>Delete</button>
            </div>

          </div>
        </div>
      </div>
      <center><button className="btn btn-primary" onClick={openSelectBox}>Set Skills</button></center>
      {skillupdated &&
      <div className="">
      <Select
        options={options}
        placeholder="Select Your Intrests"
        defaultValue={value}
        isMulti
        onChange={setValue}
      />
      <button className="btn btn-primary" onClick={setSkills}>Submit</button>
    </div>
      }

    </>
  );
}

export default ProfileI;