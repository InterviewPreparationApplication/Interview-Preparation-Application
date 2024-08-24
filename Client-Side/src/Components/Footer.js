import "./css/Footer.css"
function Footer() {
    return (  
        <div> 
        <div className="myfooter">
            <div className="footer-section">
                <h3>Terms of Service</h3>
                <div className="content">
                    <p>Review our terms and conditions for using InterviewPreparationWebApp.</p>
                </div>
            </div>
            <div className="footer-section">
                <h3>Privacy Policy</h3>
                <div className="content">
                    <p>Understand how we collect, use, and protect your personal information.</p>
                </div>
            </div>
            <div className="footer-section">
                <h3>FAQ</h3>
                <div className="content">
                    <p>Find answers to commonly asked questions about InterviewPreparationWebApp and our services.</p>
                </div>
            </div>
            
             </div>
            <div className='myfooter1'>
          <hr/><hr/>
            <div className="footer-section">
              <hr/>
                <h5>&copy; {new Date().getFullYear()} InterviewPreparationWebApp. All rights reserved.</h5>
            </div>
            </div>
        </div>
    );
}

export default Footer;