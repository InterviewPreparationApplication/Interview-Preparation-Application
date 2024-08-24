//import NavbarA from "./Navbar";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import NavbarAdmin from "./NavbarA"
import { DobChangeWithTime, myheaders } from '../routes/auth';

function DashboardA() {
    const [interviews, setInterviews] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchInterviews = async () => {
            try {
                const headers = myheaders();
                const response = await axios.get('http://localhost:9997/interview/fetchAllInterviews',{headers});
                const updatedInterviews = response.data.result.map(interview => {
                    return {
                        ...interview,
                        FirstName: interview.Interviewerid, 
                        FirstName: interview.Userid 
                    };
                });
                setInterviews(updatedInterviews);
            } catch (error) {
                setError(error.message);
            }
        };
     
        fetchInterviews();

        
        return () => {
            setInterviews([]);
            setError(null);
        };
    }, []);
    

    const renderInterview = () => {
        return interviews.map((interview) => (
            <div className="card" style={{ "width": "18rem", "margin": "10px", "marginTop":"30px" }} key={interview.Interviewid}>
            <div className="card-body">
                <h5 className="card-title">Title:{interview.Title} </h5>
            </div>
            <ul className="list-group list-group-flush">
                <li className="list-group-item">Date:{interview.Date && DobChangeWithTime(interview.Date)}</li>
                <li className='list-group-item'>Interviewer:{interview.FirstName}</li>
                <li className='list-group-item'>User:{interview.FirstName}</li>
                <li className='list-group-item'>Status:{interview.Status}</li>
            </ul>
        </div>
    ));
};
    return (
    <>
    <NavbarAdmin/>
    <div className="container" style={{paddingTop:"50px"}}>
                <div className="row">
                    {renderInterview()}
                </div>
            </div>
    </>
    );
    
}

export default DashboardA;