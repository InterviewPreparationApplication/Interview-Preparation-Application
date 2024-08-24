import Navbar from "../Navbar";
import About from "./About";
import Contact from "./Contact";
import Experts from "./Experts";
import Top from "./Top";
import Whyus from "./Whyus";
import Footer from "../Footer";
function Home() {
    return (  
        <>
        <Navbar/>
            <div style={{"margin-top":"80px"}}>
                <Top/>
            </div>
        <Footer/>
        </>
    );
}

export default Home; 