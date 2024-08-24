import { Component,useState } from "react";
import "./css/Navbar.css"
import image1 from"./images/logo2.png"
import { Link } from "react-router-dom";

class  Navbar extends Component {
    state = { clicked:false};
    handledClick = ()=>{
        this.setState({clicked:!this.state.clicked})
    }

    render(){
        return (
            <>
                <nav className="fixed-top">
                    <a href="#"><Link to="/">
                        <img src={image1} id="logo"></img>
                        </Link></a>
                    <div>
                    <ul id="navbar" className={this.state.clicked?"#navbar active":"#navbar"}>
                        <li> <Link to="/">Home</Link></li>
                        <li> <Link to="/whyus">Why Us?</Link></li>
                        <li> <Link to="/ourexperts">Our Experts</Link></li>
                        <li> <Link to="/about">About us</Link></li>
                        <li> <Link to="/contact">Contact Us</Link></li>
                        <li> <Link to="/login">Login</Link></li>
                        <li> <Link to="/register">Register</Link></li>
                        
                    </ul>

                    
                    </div>
                    <div id="mobile" onClick={this.handledClick}>
                        <i id="bars"
                        className={this.state.clicked ? "fas fa-times":"fas fa-bars"}
                        ></i>
                    </div>
                    
                </nav>
            </>
          );
}
}
export default Navbar;