import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import image1 from '../images/e.png';
import "../css/NavbarA.css"
const NavbarA = () => {
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
                <Link to="/QWRtaW4=/login/dashboard">All Interviews</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/allusers">All Users</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/allinterviewers">All Interviewers</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/demoquestion">Demo Questions</Link>
              </li>
              <li>
                {' '}
                <Link to="/QWRtaW4=/login/profiles">Profile</Link>
              </li>
              <li>
                {' '}
                <Link to="/" onClick={doLogout}>
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
  
  export default NavbarA;