import Navbar from "./Navbar";
import { Link } from "react-router-dom";
import "../css/Sceduleinterview.css";
import Modal from "./Modal"
import React, { useState, useEffect } from "react";
import axios from "axios";
import { myheaders, getCurrentUserid,DobChangeWithTime } from "../routes/auth";
import {toast} from 'react-toastify'
import myimage from "../images/backgound.jpg"

function ScheduledInterview() {
  const [interviewers, setInterviewers] = useState([]);
  const [UserDetail, setUserDetail] = useState();
  const [modalOpen, setModalOpen] = useState(false);

  const handleOpenModal = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };


  const headers = myheaders();
  const id = getCurrentUserid();

  useEffect(() => {
    myinterviews();
  }, []);

  const ApproveRequest = (event) => {
    const iid = event.target.value;
    const url = `http://127.0.0.1:9997/interviewer/updatestatus/${iid}`;
    axios.patch(url, {}, { headers })
      .then((res) => {
        
      })
      .catch((err) => console.log(err));
    window.location.reload();
    toast.success("Interview Approved")
  };

  const RejectedInterview=(event)=>{
    const iid = event.target.value;
    const url = `http://127.0.0.1:9997/interview/deleteinterview/${iid}`;
    axios.delete(url,{ headers })
      .then((res) => {
      })
      .catch((err) => console.log(err));
    window.location.reload();
    toast.error("Interview Rejected")
  }
  const myinterviews = () => {
    axios.get(`http://127.0.0.1:9997/interviewer/InterviewSceduled/${id}`, { headers })
      .then((res) => {
        setInterviewers(res.data.result);
      })
      .catch((err) => console.log(err));
  };

  const UserDetails = (event) => {
    
    const uid = event.target.value;
    axios.get(`http://127.0.0.1:9997/user/getuserbyid/${uid}`, { headers })
      .then((res) => {
        const data = res.data.result[0];
        setUserDetail(data);
        //alert("user details")
      })
      .catch((err) => console.log(err));
    
      handleOpenModal()
  };

 
  
  const getStatusStyle = (item) => {
    if (item.Status === 'pending') {
      return { color: 'red' };
    } else if (item.Status === 'approved') {
      return { color: 'green' };
    } else {
      // Add a default style if neither pending nor approved
      return { color: 'black' };
    }
  };

  const renderInterviewers = () => {
    return interviewers.map((interviewer) => (
     <>
     <div className="icard" style={{ "width": "18rem", "margin": "10px" }} key={interviewer.Interviewid}>
          <ul className="list-group list-group-flush">
            <li className="list-group-item">{interviewer.Date && DobChangeWithTime(interviewer.Date)}</li>
            <li className="list-group-item">{interviewer.Title}</li>
            <li className="list-group-item"><p style={getStatusStyle(interviewer)}>{interviewer.Status}</p></li>
            <li className="list-group-item">
            <button className="btn btn-secondary btn-sm"
               value={interviewer.Userid} 
               onClick={UserDetails}>
                User Details
              </button>
            </li>
            <li className="list-group-item"></li>
            </ul>
          
          <div>
          </div>
          <div className="card-body">
            {interviewer.Status !='approved' && <button onClick={ApproveRequest} value={interviewer.Interviewid} className="btn btn-success">Approve</button>}
            {interviewer.Status =='approved' && <button  value={interviewer.Interviewid} className="btn btn-info btn-lg">Join</button>}
          <button className="btn btn-danger" onClick={RejectedInterview} value={interviewer.Interviewid}>Reject</button>
          </div>
          {UserDetail!=null &&
          <Modal
          isOpen={modalOpen}
          onClose={handleCloseModal}
          title="User Details"
          userDetails={UserDetail}
          />
          }        
      </div>
      
    </>
    ));
  };

  return (
    <>
      <Navbar />
      
      <div className="card-container">
      
        {renderInterviewers()}
      </div>
      
      
    </>
  );
}

export default ScheduledInterview;
