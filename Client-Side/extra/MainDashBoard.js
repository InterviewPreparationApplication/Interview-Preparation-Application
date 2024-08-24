import { Link,Switch,Route,useHistory} from 'react-router-dom/cjs/react-router-dom.min';
import './dashboard.css';
import './Login';
import './register'
import Login from './Login';
import register from './register';
function MainDashBoard() {
    return (  
      <div>
      <center>
          
          <nav navbar navbar-expand-lg bg-body-tertiary >
            <div class="header">
              <a href="/" className="logo">Interview App</a>
                <div className="header-right">
                <ul className="nav ">
                    <li className="nav-item">
                      <a className="nav-link" href="#home">Home</a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#whyus">Why Us</a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#ourexpert">Our Experts</a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#about">About Us</a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#contact">Contact Us</a>
                    </li>
                    <li className="nav-item linktag mybutton">
                    <Link to="/loginpage">Login</Link>
                    </li>
                    <li className="nav-item mybutton">
                    <Link to="/registerpage">Register</Link>
                    </li>
                    <Switch>
                        <Route path="/" exact 
                               component={Login}/>
                        <Route path="/home" exact 
                               component={register}/>
                       
                    </Switch>                
               </ul>
              </div>
          </div>
          </nav>
          
          <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" className="scrollspy-example bg-body-tertiary p-3 rounded-2" tabindex="0">
            <h4 id="home">First heading</h4>
            <p>
          <img src='img1.png' className="image1" ></img>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod 
              tempor incididunt ut labore et dolore magna aliqua. Pretium vulputate sapien nec sagittis
               aliquam malesuada. Viverra maecenas accumsan lacus vel facilisis volutpat est velit egestas
               . Cursus in hac habitasse platea. Fusce id velit ut tortor pretium. Pellentesque diam volut
               pat commodo sed egestas egestas. Ornare arcu dui vivamus arcu. Et tortor consequat id porta
                nibh venenatis cras sed felis. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. 
                Nisl nunc mi ipsum faucibus vitae aliquet nec ullamcorper.</p>
            </p>

            <h1 id="whyus">Why Us?</h1>
            <p>
            <img src='img1.png' className="image2"></img>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod 
              tempor incididunt ut labore et dolore magna aliqua. Pretium vulputate sapien nec sagittis
               aliquam malesuada. Viverra maecenas accumsan lacus vel facilisis volutpat est velit egestas
               . Cursus in hac habitasse platea. Fusce id velit ut tortor pretium. Pellentesque diam volut
               pat commodo sed egestas egestas. Ornare arcu dui vivamus arcu. Et tortor consequat id porta
                nibh venenatis cras sed felis. Bibendum ut tristique et egestas quis ipsum suspendisse ultrices. 
                Nisl nunc mi ipsum faucibus vitae aliquet nec ullamcorper.</p>
            </p>
            
            <h4 id="ourexpert">Third heading</h4>
            <p></p>
            
            <h4 id="about">Fourth heading</h4>
            <p></p>
            
            <h4 id="contact">Fifth heading</h4>
            <p>
              
            </p>
  </div>
                   
                   

      </center>
      </div>
      
        );
}

export default MainDashBoard;



<Switch>
                    {/* <Route path="/" exact component={Header} /> */}
                <Route path="/whyus" exact component={Whyus} />
                <Route path="/about" component={About} />
                <Route path="/contact" component={Contact} />
                <Route path="/login" component={Login} />
                <Route path="/register" component={Register} />
      </Switch>