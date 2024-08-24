import Navbar from "../users/Navbar1";
import "../css/Sceduleinterview.css"
import { getCurrentUserid,getCurrentUser, myheaders,DobChangeWithTime } from "../routes/auth";
import { useEffect, useState } from "react";
import axios from 'axios';
import DatePicker from 'react-datepicker'
import "react-datepicker/dist/react-datepicker.css"
import {toast}from "react-toastify"

//import "../css/ScheduleInterview.css"


function ScheduleInterview() {
  const headers = myheaders();

    const [interviewers, setInterviewers] = useState([]);
    const [scheduled,setScheduled] = useState([])
    const [FormData,setFormData] = useState({Date:"",Title:"",InterviewerSelection:""})
     const [title,setTitle] = useState("")
     const [skillName,setSkillname]= useState("")
    //const [selectinterviewer,setselectedinterviewer] = useState("")

    useEffect(() => {
            // axios.get("http://localhost:9997/interview/getmyinterviews", { headers })
            // .then(res =>console.log(res.data))
            // .catch(err => console.log(err));
            
    }, []);


    useEffect(() => {
       
      axios.get("http://127.0.0.1:9997/interviewer/getallskills", { headers })
          .then(res => setTitle(res.data.result))
          .catch(err => console.log(err));
  }, []);

    useEffect(() => {
        const token = myheaders();  
           const id = getCurrentUserid();
           const apiurl = `http://localhost:9997/interview/getmyinterviews/${id}`
            //console.log(apiurl)
          //console.log(headers)
          axios.get(apiurl, {headers: token})
           .then((result)=>{
           // console.log(result.data);
            //console.log(result.data.result);
            if(result.data.status=="success")
            {
                const arr =result.data.result
                console.log(arr)
                setScheduled(arr)
                //console.log(arr)
            }
            else if (result.data.status=="error")
            {
              alert("Some Problem Occured")
            }
            else
            {
              alert("Something went wrong")
            }
      })
            .catch(err => console.error(err));
            
    }, []);
   
    const getInterviewerBySkill=(id)=>{
      const url = `http://localhost:9997/interview/getinterviewerbyskill/${id}`
      axios.get(url,{ headers })
            .then(res => setInterviewers(res.data.result))
            .catch(err => console.log(err));
    }

    
    const OnTextChange = (args)=>{
        var inputDataCopy = {...FormData};
        inputDataCopy[args.target.name]=args.target.value
        console.log(inputDataCopy.Title)
        getInterviewerBySkill(inputDataCopy.Title)

       // console.log(inputDataCopy)
        setFormData(inputDataCopy)
    }
    
    
    const maxDate = new Date("2024-12")
    const handledatechange=(date)=>
    {
        
        setFormData({
            ...FormData,
            Date:date
        })
    }
    const handleSave = (e)=>{
    if(!FormData.Date || !FormData.Title || !FormData.InterviewerSelection){
      e.preventDefault();
        alert("All Field is mandatory")
    }
    else{
     // e.preventDefault();
        console.log(FormData)
        const sqlFormattedDate = FormData.Date.toJSON().slice(0, 19).replace("T", " ");
        const data = getCurrentUserid()
       const headers = myheaders();
        const requestBody = {
             Date:sqlFormattedDate,
             Title: FormData.Title,
             Interviewerid: FormData.InterviewerSelection,
             Userid:data
           };
         const url ="http://localhost:9997/interview/setInteview" ;
          // console.log(requestBody)
        try{
            axios.post(url,requestBody ,{ headers })
             .then((result)=>{
                //console.log(result.data);
                //console.log(result.data.status);
                if(result.data.status=="success")
                {
                  toast.success("Interview Sceduled")
                }
                else if (result.data.status=="error")
                {
                  toast.error("Some Problem Occured")
                 // alert("Some Problem Occured")
                }
                else
                {
                  toast.error("Something went wrong")
                //  alert("Something went wrong")
                }
          })
            .catch(err => console.log(err));
        }catch(ex)
        {
            alert("Something went wrong")
            console.log(ex)
        }
  
    }
    
    }

    const gettinginterviewer=()=>{
        const dateObject = new Date(scheduled[0].Date.toLocaleString());

        console.log(scheduled[0].Date.toLocaleString());
        console.log(dateObject)
        // const sqlFormattedDate = selectedDate.toISOString().slice(0, 19).replace('T', ' ');
        // console.log(sqlFormattedDate);
        
        
    }

    const gettinginterviewDate=(Date)=>{
      const dateObject = new Date(Date.toLocaleString());
        console.log(dateObject)
      //console.log(scheduled[0].Date.toLocaleString());
      //console.log(dateObject)
      // const sqlFormattedDate = selectedDate.toISOString().slice(0, 19).replace('T', ' ');
      // console.log(sqlFormattedDate);   
  }
    const renderInterviewers = () => {
        return interviewers.map(item => (
                <option value={item.Interviewerid} key={item.Interviewerid}>{item.FirstName} {item.LastName}</option>       
        ));
      };

      const renderTitles = () => {
        //console.log(Array.isArray(title))
        if (Array.isArray(title)) {
          
          return title.map(item => (
            <option value={item.skillid} key={item.skillid}>{item.skill}</option>
          ));
        } else {
          // Handle the case where title is not an array, e.g., show an error message
          return <option value="">Error: Skills not available</option>;
        }
      };

      const renderSceduled = () => {
        return scheduled.map( item => (
    
            <div class="icard" key={item.Interviewid}>
              <p> Title:<strong>{item.Title}</strong></p>
              <p>Date:-{item.Date && DobChangeWithTime(item.Date)}</p>
              <p style={getStatusStyle(item)}>{item.Status}</p>
              {item.Status == 'approved' && <button className="btn btn-success">Join</button>}
            </div>       
        ));
      };

      const getStatusStyle = (item) => {
        if (item.Status === 'pending') {
          return { color: 'red' };
        } else if (item.Status === 'approved') {
          return { color: 'green' };
        } else {
          // Add a default style if neither pending nor approved
          return { color: 'black' };
        }
      };
      const DateStyle = (item)=>{
        if(item.Date)
        {
          console.log(Date)
        }
      }
    return ( 
        <>
        <Navbar/> 
        
        {/* card for viewing interview sceduled and approved or not */}
        <div className="card-container">
            {scheduled.length>0&&renderSceduled()}
        </div>
        <form className="container" onSubmit={handleSave}>
        <div className="container-form">
      
      <form>
        <h2 className="text-center">Sceduling Interview</h2>
        <div className="form-group">
          <label htmlFor="date">Date:</label>
          <DatePicker
            selected={FormData.Date}
            onChange={handledatechange}
            dateFormat="Pp"
            minDate={(new Date())}
           maxDate={maxDate}
            showTimeSelect
            timeIntervals={30}
            timeFormat='hh:mm'
            
            />
        </div>
        
        <div className="form-group">
        <label htmlFor="title">Title:</label>
                    <div class="input-group mb-3">
                <select class="form-select"
                        name="Title"
                        id="inputGroupSelect03"
                        aria-label="Example select with button addon"
                        value={title.skillid}
                        onChange={OnTextChange}>
                    <option  selected>Choose...</option>
                    {renderTitles()}
                </select>
            </div>
        </div>

        <div className="form-group">
                    <div class="input-group mb-3">
                <select class="form-select"
                        name="InterviewerSelection"
                        id="inputGroupSelect03"
                        aria-label="Example select with button addon"
                        value={FormData.InterviewerSelection}
                        onChange={OnTextChange}>
                    <option  selected>Choose...</option>
                    {interviewers.length > 0 && renderInterviewers()}
                </select>
            </div>
        </div>
        <button type="submit" className="btn btn-primary btn-block" onClick={handleSave}>Submit</button>
        
      </form>
    </div>
            

        </form>
       
        </>
     );
}

export default ScheduleInterview;
{/* <div className="header">
                <h2>Schedule Interview</h2>
            </div>
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <br/><br/>
            <div>
                Date: <input type="date" name="Date" value={FormData.Date} onChange={OnTextChange}></input>
            </div>
            <br/><br/>
            <div>
                Title: <input type="text" placeholder="Enter ur title" name="Title" value={FormData.Title} onChange={OnTextChange}>
                </input>
            </div>
            <br/><br/>
            <div>
                Interviewer Selection: <input type="text" placeholder="Select your interviewer" name="InterviewerSelection" value={FormData.InterviewerSelection} onChange={OnTextChange}>
                </input>
            </div>
            <br/><br/>
            <div>
                <button type="submit">Save</button>
            </div> */}