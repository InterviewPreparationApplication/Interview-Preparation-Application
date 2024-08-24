import { useEffect } from "react";
import Navbar from "../users/Navbar1";
import Footer1 from "../Footer";
import logo from "../images/icon2.png";
import "../css/Dashboard.css"  // Import your CSS file
import { useNavigate } from "react-router-dom";
function Dashboard() {
    const navigate = useNavigate()
    useEffect(() => {
        // Your side effects or data fetching code here
        // For example:
        // fetchData();
    }, []);




    return (
        <>
            <Navbar />
            
            <div className="dashboard-content">
            
                {/* Add your dashboard content here */}
                <div className="content-container">
                   
                   
                    <h2>Interview Preparation Dashboard</h2>
                    <p>Prepare for a job with PrepInsta’s Interview Preparation dashboard. This page is a complete guide to prepare for job interview for freshers and experienced candidates. .</p>
                    <button className="button" onClick={()=>{navigate("/login/user/demoquestion")}}>Check Out Questions</button>
                </div>
                <div>
                    <h3> How To Prepare For An Interview?</h3>
                    <p>
                  
                    Interviews are the most important round of placements. 
                    It is the final metric on which whether you are selected or not. Hence, 
                    interview preparation is crucial if you are preparing for placements.
                     Interviews are mainly divided into four rounds;-
                    <br/>
                    <br/>
                    <ul>
                     
                    <li>Technical Interview</li>
                    <br/>
                    <li>HR Interview </li>   
                    <br/>
                   <li>Managerial Interview</li>
                    <br/>
                    <li>Group Discussion</li>
                    </ul>
                    On this page, you will find complete information and details about all of the interview rounds, and more.
                    </p>
                </div>
                 
            </div>
            <Footer1 />
        </>
    );
}

export default Dashboard;


