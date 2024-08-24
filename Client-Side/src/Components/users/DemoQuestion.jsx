import React, { useState,useEffect } from 'react';
import axios from 'axios';
import { myheaders } from '../routes/auth';
import Navbar from './Navbar1';
import { toast } from 'react-toastify';



const DemoQuestion = () => {
  const [subjects, setSubjects] = useState([]);
  const [subject, setSubject] = useState('');
  const [demoQuestions, setDemoQuestions] = useState([]);
  const [error, setError] = useState('');
  

  useEffect(() => {
    const token = myheaders()  
    axios.get(`http://127.0.0.1:9997/interviewer/getallskills`, {headers:token})
      .then(response => {
        if (response.data.status === "success"){
        setSubjects(response.data.result);
        setError('');
  }
  else {
    toast.error("Can't Fetch Topics")
    setSubjects('');
  }
})
      .catch(error => {
        toast.error("Something Went Wrong")
      });
  }, []);

  const handleSubjectChange = (event) => {
    const selectedSubject = event.target.value;
    setSubject(selectedSubject);
   // console.log(selectedSubject)
    setDemoQuestions([]); // Reset demoQuestions when a new subject is selected
  };

  
  const handleSearch = () => {
    const token = myheaders()
    if (!subject) {
        setDemoQuestions("")
        toast.warn("Please select a subject")
        return;
    }
    else{
      //console.log(subject)
      axios.get(`http://127.0.0.1:9997/admin/Question/${subject}`, { headers: token })
        .then(response => {
            if (response.data.status === "success") {
             // console.log(response.data.message['length']==0)
              if(response.data.message['length']==0)
                toast.info("Questions Not Found")
               
                setDemoQuestions(response.data.message);
                
            } 
        })
        .catch(error => {
          toast.error("Error fetching demo questions")
            setDemoQuestions([]);
        });
    }
    
};

 // console.log('demoQuestions:', demoQuestions);
  return (
    <>
    <Navbar/>
    <div>
      
      <select value={subject} onChange={handleSubjectChange}>
        <option value="">Select a subject</option>
        {subjects && subjects.map(({skillid, skill }) => (
          <option key={skillid} value={skillid}>{skill}</option>
        ))}
      </select>
      <button onClick={handleSearch}>Search</button>
     
      {demoQuestions!=undefined &&demoQuestions.length > 0 && (
        <div>
          <h2>Demo Questions:</h2>
          <ul>
            {demoQuestions.map((demoQuestion, index) => (
              <li key={index}>
               
                <div class=" shadow p-3 mb-5 bg-body-tertiary rounded fw-bold">{demoQuestion.Question}
                <div class="p-3 mb-2 bg-success-subtle text-success-emphasis fw-semibold">
                  {demoQuestion.Answer}
                  </div>
                </div>
               
                <br/>
              </li>
            ))}
          </ul>
        </div>
      )}
      {error && <p>{error}</p>}
    </div>

    
  </>
  );
};





export default DemoQuestion;
