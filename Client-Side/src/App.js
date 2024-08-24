import Home from "./Components/routes/Home";
import Whyus from "./Components/routes/Whyus";
import About from "./Components/routes/About";
import Contact from "./Components/routes/Contact";
import Login from "./Components/routes/Login";
import Register from "./Components/routes/Register";
import Experts from './Components/routes/Experts';
import ForgetPassword from "./Components/routes/ForgetPassword";
import NotFound from "./Components/routes/NotFound";
import Dashboard from "./Components/users/Dashboard";
import PrivateRoute from "./Components/users/PrivateRoute";
import ScheduleInterview from "./Components/users/ScheduleInterview";
import GetInterviewers from "./Components/users/GetInterviewers";
import PastInterviews from "./Components/users/PastInterviews";
import Profile from "./Components/users/Profile";
import DemoQuestion from "./Components/users/DemoQuestion";
import { Route, Routes } from "react-router-dom";
import Footer from "./Components/Footer";
import IntRegister from "./Components/Interviewer/IntRegister";
import IntLogin from "./Components/Interviewer/IntLogin";
import Dashboard1 from "./Components/Interviewer/Dashboard1";
import PrivateRouteI from "./Components/Interviewer/PrivateRouteI";
import PastInterview from "./Components/Interviewer/PastInterview";
import ScheduledInterview from "./Components/Interviewer/ScheduledInterview";
import InterviewRecords from "./Components/Interviewer/InterviewRecords";
import ProfileI from "./Components/Interviewer/ProfileI";
import DashboardA from "./Components/Admin/DashboardA";
import Adminlogin from "./Components/Admin/Adminlogin";
import AllUsers from "./Components/Admin/AllUsers";
import AllInterviewers from "./Components/Admin/AllInterviewers";
import DemoQuestionA from "./Components/Admin/DemoQuestionA";
import ProfileA from "./Components/Admin/ProfileA";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AllQuestions from "./Components/Admin/AllQuestions";
import AllSubjects from "./Components/Admin/AllSubjects";
import AddQuestions from "./Components/Admin/AddQuestions";
import AddSubjects from "./Components/Admin/AddSubjects";

function App() {
    const token = sessionStorage['token']
    const status = sessionStorage['login']
    return ( 
    <div className="App">
            
                <Routes>
                       <Route  path="/"  element={<Home/>}/>
                        <Route  path="/whyus"  element={<Whyus/>}/>
                        <Route  path="/ourexperts"  element={<Experts/>}/>
                        <Route  path="/about"  element={<About/>}/> 
                        <Route  path="/login"  element={<Login/>}/>
                        <Route  path="/register"  element={<Register/>}/> 
                         <Route  path="/about"  element={<About/>}/>
                        <Route  path="/contact"  element={<Contact/>}/>
                        <Route  path="/forgetpassword" element={<ForgetPassword/>}/>
                        {/* private routing */}
                        <Route  path="/login/user" element={<PrivateRoute/>}>
                        interviewer          <Route  path="dashboard" element={<Dashboard/>}/> 
                            <Route  path="scheduleinterview"  element={<ScheduleInterview/>}/>
                            <Route  path="getinterviewers"  element={<GetInterviewers/>}/>
                            <Route  path="pastinterviews"  element={<PastInterviews/>}/>
                            <Route  path="demoquestion"  element={<DemoQuestion/>}/>
                            <Route  path="profile"  element={<Profile/>}/>
                            <Route  path="*"  element={<NotFound/>}/>
                            <Route  path="logout"  element={<PrivateRoute/>}/>
                        </Route>
                        <Route path="ourexperts/login/interviewer" element={<PrivateRoute/>}>
                            
                            <Route path="dashboard" element={<Dashboard1/>}/>
                            <Route path="scheduledinterviews" element={<ScheduledInterview/>}/>
                            <Route path="pastinterview" element={<PastInterview/>}/>
                            <Route path="interviewrecords" element={<InterviewRecords/>}/>
                            <Route path="profiles" element={<ProfileI/>}/>
                            <Route path="logout" element={<PrivateRouteI/>}/>
                        </Route>
                        <Route  path="/logout"  element={<Login/>}/>
                        <Route path="/ourexperts/register" element={<IntRegister/>}/>
                        <Route path="/ourexperts/login" element={<IntLogin/>}/>

                 <Route path ="/QWRtaW4=" element={<Adminlogin/>}/>
                    <Route path="QWRtaW4=/login" element={<PrivateRoute/>}>
                       <Route path="dashboard" element={<DashboardA/>}/>
                       <Route path="allusers" element={<AllUsers/>}/>
                       <Route path="allinterviewers" element={<AllInterviewers/>}/>
                       <Route path="demoquestion" element={<DemoQuestionA/>}/>
                       <Route path="profiles" element={<ProfileA/>}/>
                        </Route>
                        
                <Route path="/QWRtaW4=/login/demoquestion" element={<PrivateRoute/>}>
                    <Route path="allquestions" element={<AllQuestions/>}/>
                    <Route path="allsubjects" element={<AllSubjects/>}/>
                    <Route path="addquestions" element={<AddQuestions/>}/>
                    <Route path="addsubjects" element={<AddSubjects/>}/>
                    </Route>
                </Routes>
        
                <ToastContainer/>
    </div>
    
     );
}

export default App;