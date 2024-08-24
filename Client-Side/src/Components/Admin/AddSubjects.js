import { toast } from "react-toastify";
import { myheaders } from "../routes/auth";
import { useState } from "react";
import axios from "axios";
import DashboardNavbar from "./DashboardNavbar";

function AddSubjects() {
  const [title, setTitle] = useState("");
  const [subjects, setSubjects] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  
  const handleSubmit = async (e) => {
    e.preventDefault();
      
    try {
       
      const headers = myheaders();
      
      const response = await axios.post("http://127.0.0.1:9997/admin/addSubjects", {
      Subjects: subjects,
        Title: title},
        {headers});
        
      if(response.data.status == "success"){
        
      toast.success(" Subjects Added  successfully!");
      setSubjects("");
      setTitle("");
    } 
    else {
        toast.error("Failed to add question");
    }
}
    catch (error) {
      setError("Failed to post data. Please try again.");
    }
  };
    return ( <>
    <DashboardNavbar/>
    <div>
      <h2>Add Subjects</h2>
      
      <form onSubmit={handleSubmit}>
        <div>
          <label>Subjects:</label>
          <input
            type="text"
            value={subjects}
            onChange={(e) => setSubjects(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Title:</label>
          <input
          type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <button type="submit">Submit</button>
      </form>
      
      {message && <div>{message}</div>}
      {error && <div>{error}</div>}
    </div>
    </> );
}

export default AddSubjects;