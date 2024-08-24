import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import image1 from '../images/e.png';
import '../css/Navbar.css';

const Navbar = () => {
  const [clicked, setClicked] = useState(false);
  const navigate = useNavigate();

  const handleClick = () => {
    setClicked(!clicked);
  };

  const doLogout = () => {
    sessionStorage.removeItem('data');
    sessionStorage.removeItem('Userdata');
    navigate('/logout');
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
              <Link to="/ourexperts/login/interviewer/dashboard">Dashboard</Link>
            </li>
            <li>
              {' '}
              <Link to="/ourexperts/login/interviewer/scheduledinterviews">Scheduled Interview</Link>
            </li>
            <li>
              {' '}
              <Link to="/ourexperts/login/interviewer/pastinterview">Past Interviews</Link>
            </li>
            <li>
              {' '}
              <Link to="/ourexperts/login/interviewer/interviewrecords">Interview Records</Link>
            </li>
            <li>
              {' '}
              <Link to="/ourexperts/login/interviewer/profiles">Profile</Link>
            </li>
            <li>
              {' '}
              <Link to="/ourexperts" onClick={doLogout}>
                Logout
              </Link>
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

export default Navbar;