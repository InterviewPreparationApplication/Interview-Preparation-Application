import { useEffect, useState } from "react";
import axios from 'axios';
import Navbar from "../users/Navbar1";
import { Link } from "react-router-dom";
import { getCurrentUser, myheaders } from "../routes/auth";

function GetInterviewers() {
    const [interviewers, setInterviewers] = useState([]);

    useEffect(() => {
        const headers = myheaders();

        axios.get("http://localhost:9997/interview/allInterviewer", { headers })
            .then(res => setInterviewers(res.data.result))
            .catch(err => console.log(err));
    }, []);

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
                   <Link to="/login/user/scheduleInterview">Schedule Interview</Link> <br/><br/>
                    <a href="#" className="card-link">Check Feedbacks</a>
                </div>
            </div>
        ));
    };

    return (
        
        <>
            
            <center>
                 <Navbar />

                <div className="container">
                    {renderInterviewers()}
                </div>
            </center>
        </>
    );
}

export default GetInterviewers;
