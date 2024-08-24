import DashboardNavbar from "./DashboardNavbar";
import { useState, useEffect } from "react";
import axios from "axios";
import { myheaders } from "../routes/auth";
import { toast } from "react-toastify";


function AddQuestions() {
    const [question, setQuestion] = useState("");
    const [answer, setAnswer] = useState("");
    const [subjectId, setSubjectId] = useState("");
    const [subjects, setSubjects] = useState([]);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    useEffect(() => {
        //setSubjects(res.data.result)
        const fetchSubjects = async () => {
            try {
                const headers = myheaders();
                axios.get("http://127.0.0.1:9997/interviewer/getallskills", { headers })
                .then(res => setSubjects(res.data.result) )
                .catch(err => console.log(err));
            } catch (error) {
                console.error("Error fetching subjects:", error);
                setError("Failed to fetch subjects");
            }
        };
        fetchSubjects();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const headers = myheaders();
            console.log("Questions:",question)
            console.log("answer:",answer)
            console.log("Title:",subjectId)   
            const response = await axios.post(
                "http://localhost:9997/admin/addQuestion",
                { Question: question, Answer: answer, skillid: subjectId },
                { headers }
           );
            if (response.data.status === "success") {
                toast.success("Question added successfully");
                setQuestion("");
                setAnswer("");
                setSubjectId("");
            } else {
                toast.error("something went wrong")
            }
        } catch (error) {
            console.error("Error adding question:", error);
            toast.error("something went wrong")
        }
    };

    return ( <>
    <DashboardNavbar/>
    <div>
            <h2>Add Demo Question</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Question:</label>
                    <input type="text" value={question} onChange={(e) => setQuestion(e.target.value)} required />
                </div>
                <div>
                    <label>Answer:</label>
                    <input type="text" value={answer} onChange={(e) => setAnswer(e.target.value)} required />
                </div>
                <div>
                    <label>Subject:</label>
                    <select value={subjects.skillid} onChange={(e) => setSubjectId(e.target.value)} required>
                        <option value="">Select a subject</option>
                        {subjects.map((subject) => (
                            <option key={subject.skillid} value={subject.skillid}>
                                {subject.skill}
                            </option>
                        ))}
                    </select>
                </div>
                <button type="submit">Submit</button>
            </form>
            {message && <div>{message}</div>}
            {error && <div>{error}</div>}
        </div>
    </>  );
}

export default AddQuestions;