import Navbar from "../Navbar"
import Footer from "../Footer";
import img1 from "../images/aboutus.jpg"

function About() {
    return ( 
        <>
        <Navbar/>
        <br/><br/>
        {/* <br/>
        <h1>About Us</h1> */}
        <h2 style={{"font-family": "Protest Riot, sans-serif",color: "#325b5d",textAlign:"center", padding:"10px"}}>About Us</h2>
        <div className="container" id="About Us">
      <div className="left-image">
        <img src={img1} alt="Left Image" />
      </div>

        <div className="right-paragraph">
            
         <p style={{"font-family": "system-ui", fontSize: 18}} >
         Welcome to InterviewPreparationWebApp, your ultimate destination for mastering 
         the art of interview preparation. At InterviewPreparationWebApp, we understand 
         the challenges that job seekers face in today's competitive market. 
         That's why we've created a comprehensive platform designed to empower you 
         with the skills, knowledge, and confidence you need to ace your interviews 
         and land your dream job. Our mission is simple: to provide job seekers with 
         the tools and resources they need to excel in interviews and advance their careers.
         We believe that everyone deserves the opportunity to showcase their talents and secure 
         rewarding employment opportunities, regardless of their background or experience level. 
         Gain access to expert guidance from our team of experienced professionals and industry experts, 
         comprehensive resources including interview tips, sample questions, and case studies, and engage 
         with our interactive modules and practice exercises to hone your skills and build confidence.
         </p>
         </div>
         </div>
        <Footer/>
        </>
     );
}

export default About;