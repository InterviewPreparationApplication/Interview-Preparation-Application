import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import image1 from '../images/e.png';
import "../css/NavbarA.css"
//import Dashboard from '../users/Dashboard';
const DashboardNavbar = () => {
    const [clicked, setClicked] = useState(false);
    const navigate = useNavigate();
  
    const handleClick = () => {
      setClicked(!clicked);
    };
  
   
    return (
      <>
        <nav>
          <a href="#">
            <Link to="/dashboard">
              <img src={image1} id="logo" alt="Logo" />
            </Link>
          </a>
          <div>
            <ul id="navbar" className={clicked ? '#navbar active' : '#navbar'}>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/demoquestion/allquestions">All Questions</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/demoquestion/allsubjects">All Subjects</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/demoquestion/addquestions">Add Questions</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/demoquestion/addsubjects">Add Subjects</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/dashboard">Back</Link>
              </li>
            </ul>
          </div>
          <div id="mobile" onClick={handleClick}>
            <i
              id="bars"
              className={clicked ? 'fas fa-times' : 'fas fa-bars'}
            ></i>
          </div>
        </nav>
      </>
    );
  };
  
  export default DashboardNavbar;