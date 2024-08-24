import DashboardNavbar from "./DashboardNavbar";
import { useState,useEffect } from "react";
import { myheaders } from "../routes/auth";
import axios from "axios";
import { toast } from "react-toastify";
function AllQuestions() {
    const [questions, setQuestions] = useState([]);
    useEffect(() => {
        const headers = myheaders();

        axios.get("http://127.0.0.1:9997/admin/demoquestions", { headers })
            .then(res => setQuestions(res.data.result))
            .catch(err => console.log(err));
    }, []);

    const deleteUser = async (id) => {
        try {
            const headers = myheaders();
            await axios.delete(`http://127.0.0.1:9997/admin/deletedemoquestion/${id}`,{headers})
            .then((result)=>{
                if(result.data.status=="success")
                {
                  toast.success('Profile Deleted')
                  fetchQuestions();
                }
              }) 
        } catch (error) {
            console.error('Error deleting user:', error);
            throw error; 
        }
    };
    const fetchQuestions = async () => {
        try {
            const headers = myheaders();
            const response = await axios.get("http://127.0.0.1:9997/admin/demoquestions", { headers });
            setQuestions(response.data.result);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };
    const renderInterviewers = () => {
        return questions.map((question) => (
            <div className="card" style={{ "width": "18rem", "margin": "10px" }} key={question.Questionid}>
                <div className="card-body">
                    <h5 className="card-title">{question.Question} </h5>
                </div>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item">{question.Answer}</li>
                </ul>
                <div className="card-body">
                  <button onClick={() => deleteUser(question.Questionid)}>Delete</button>
                </div>
            </div>
        ));
    };
    return ( <>
        <DashboardNavbar/>
        <center>
    <h3>All Questions</h3>
                <button onClick={() => console.log(questions)}>My users button</button>
                <div className="container">
                    {renderInterviewers()}
                </div>
            </center>
    </>  );
}

export default AllQuestions;