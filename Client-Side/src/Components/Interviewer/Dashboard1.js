
import React, { useState } from "react";
import Navbar from "./Navbar";

import interviewer1Img from "../images/i1.jpg"; 
import interviewer2Img from "../images/i2.jpg";
import interviewer3Img from "../images/i3.jpg";
import "../css/Dashboard1.css";

function Dashboard1() {
  const [isChecked, setChecked] = useState(false);

  const handleCheckboxChange = () => {
    setChecked(!isChecked);
  };

  return (
    <>
      <Navbar />
      <strong>
        <h1 style={{ textAlign: "center", fontFamily: "fantasy", }} >Interviewers Dashboard</h1>
      </strong>

      <div className="interviewer-container">
        {/* Interviewer 1 */}
        <div className="interviewer-card">
          <img src={interviewer1Img} alt="Interviewer 1" className="interviewer-image" />
          <div className="interviewer-details">
            <p>Name: John Doe</p>
            <p>Position: Senior Software Engineer</p>
            <p>Contact Information:</p>
            <ul>
              <li>Email: john.doe@example.com</li>
              <li>Phone: +1234567890</li>
              <li>LinkedIn: <a href="https://www.linkedin.com/in/johndoe">John Doe's LinkedIn Profile</a></li>
            </ul>
          </div>
        </div>

        {/* Interviewer 2 */}
        <div className="interviewer-card">
          <img src={interviewer2Img} alt="Interviewer 2" className="interviewer-image" />
          <div className="interviewer-details">
            <p>Name: Jane Smith</p>
            <p>Position: HR Manager</p>
            <p>Contact Information:</p>
            <ul>
              <li>Email: jane.smith@example.com</li>
              <li>Phone: +1234567890</li>
              <li>LinkedIn: <a href="https://www.linkedin.com/in/janesmith">Jane Smith's LinkedIn Profile</a></li>
            </ul>
          </div>
        </div>

        {/* Interviewer 3 */}
        <div className="interviewer-card">
          <img src={interviewer3Img} alt="Interviewer 3" className="interviewer-image" />
          <div className="interviewer-details">
            <p>Name: Alex Johnson</p>
            <p>Position: Technical Lead</p>
            <p>Contact Information:</p>
            <ul>
              <li>Email: alex.johnson@example.com</li>
              <li>Phone: +1234567890</li>
              <li>LinkedIn: <a href="https://www.linkedin.com/in/alexjohnson">Alex Johnson's LinkedIn Profile</a></li>
            </ul>
          </div>
        </div>
      </div>
      <br />
      <div>
        <center>
          <h4> Our  interviewers can provide valuable insights and help you tailor your responses effectively.
            Our app provides key information about your interviewers, including their backgrounds, roles within the company, and any shared connections or interests.
            Armed with this knowledge, you can establish rapport more easily, anticipate their expectations, and showcase your qualifications in a way that resonates with their perspectives.
            Take advantage of this feature to make a memorable impression and increase your chances of interview success.</h4>

          <hr />
          <strong>
            <p>
              Moreover, our app provides tips and strategies for engaging with different types of interviewers, whether they prefer a more structured approach or value creative problem-solving. By understanding their roles within the company and their potential influence on the hiring decision, you can tailor your responses to address their specific concerns and priorities.
            </p>
          </strong>

        </center>
      </div>
    </>

  );
}

export default Dashboard1;
