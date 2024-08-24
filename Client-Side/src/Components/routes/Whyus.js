import "../css/whyus.css"
import Navbar from "../Navbar"
import img1 from "../images/whyus.jpg"
import Footer from "../Footer";



function Whyus() {
    return (
        <>
        <Navbar/>
            <h3 style={{ "font-family": "Protest Riot, sans-serif" , color: "#325b5d",textAlign:"center", padding:"20px"}}>Why Us?</h3>
            <div className="container" id="whyus">
          <div className="left-image">
            <img src={img1} alt="Left Image" />
          </div>
          <div className="right-paragraph">
            <p style={{ "font-family": "system-ui", fontSize: 20}}>
            Our platform offers more than just interviews – it's a gateway to knowledge and inspiration.
    Engage with our community of learners, professionals, and enthusiasts who share your curiosity 
    and drive for growth. Whether you're seeking career advice, industry expertise, or simply looking to
      expand your horizons, we're here to fuel your curiosity and empower your journey.
      Join us at Interview Preparation WebApp and unlock a world of possibilities. Let our interviews be your guide 
      as you navigate the ever-changing landscape of your industry and beyond. Start your exploration
      today and elevate your understanding with Interview Preparation WebApp.
            </p>
          </div>
        </div>
        <Footer/>
        </>
      );
}

export default Whyus;