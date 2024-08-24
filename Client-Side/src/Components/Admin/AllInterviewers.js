import NavbarA from "./NavbarA";
import { useState,useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { myheaders } from "../routes/auth";

function AllInterviewers() {
    const [interviewers, setInterviewers] = useState([]);

    useEffect(() => {
        const headers = myheaders();

        axios.get("http://localhost:9997/admin/getallinterviewer", { headers })
            .then(res => setInterviewers(res.data.result))
            .catch(err => console.log(err));
    }, []);
    const deleteInterviewer = (id)=>{
        const murl = `http://127.0.0.1:9997/admin/deleteinterviewer/${id}`
      const headers = myheaders();
        axios.delete(murl,{headers})
        .then((result)=>{
          if(result.data.status=="success")
          {
            toast.success('Profile Deleted')
            fetchInterviewers();
          }
        })
        .catch((err)=>{
          toast.error('Something went wrong')
          console.log(err)})
      }
      const fetchInterviewers = async () => {
        try {
            const headers = myheaders();
            const response = await axios.get("http://localhost:9997/admin/getallinterviewer", { headers });
            setInterviewers(response.data.result);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const renderInterviewers = () => {
        return interviewers.map((interviewer) => (
            <div className="card" style={{ "width": "18rem", "margin": "10px" }} key={interviewer.Interviewerid}>
                <div className="card-body">
                    <h5 className="card-title">{interviewer.FirstName} {interviewer.LastName}</h5>
                </div>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item">{interviewer.Email}</li>
                    <li className="list-group-item">{interviewer.Address}</li>
                    <li className="list-group-item">{interviewer.CompanyPosition}</li>
                    <li className="list-group-item">{interviewer.QualifiedDegree}</li>
                </ul>
                <div className="card-body">
                 <button onClick={() => deleteInterviewer(interviewer.Interviewerid)}>Delete</button>
                </div>
            </div>
        ));
    };
    return ( <>
    <NavbarA/>
    <center>
    <h3>All Interviewers</h3>
                <button onClick={() => console.log(interviewers)}>My interviewers button</button>
                <div className="container">
                    {renderInterviewers()}
                </div>
            </center>
    </> );
}

export default AllInterviewers;