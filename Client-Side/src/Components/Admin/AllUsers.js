import { myheaders,getCurrentUserid } from "../routes/auth";
import NavbarA from "./NavbarA";
import { useEffect,useState } from "react";
import axios from "axios";
import  { toast } from 'react-toastify';
  

function AllUsers() {
    const [users, setUsers] = useState([]);
   // const userid = getCurrentUserid();
    useEffect(() => {
        const headers = myheaders();

        axios.get("http://localhost:9997/admin/getallusers", { headers })
            .then(res => setUsers(res.data.result))
            .catch(err => console.log(err));
    }, []);

    const deleteUser = async (id) => {
        try {
            const headers = myheaders();
            await axios.delete(`http://127.0.0.1:9997/admin/deleteUser/${id}`,{headers})
            .then((result)=>{
                if(result.data.status=="success")
                {
                  toast.success('Profile Deleted')
                  fetchUsers();
                }
              }) 
        } catch (error) {
            console.error('Error deleting user:', error);
            throw error; 
        }
    };

const fetchUsers = async () => {
    try {
        const headers = myheaders();
        const response = await axios.get("http://localhost:9997/admin/getallusers", { headers });
        setUsers(response.data.result);
    } catch (error) {
        console.error('Error fetching users:', error);
    }
};
    
    const renderInterviewers = () => {
        return users.map((user) => (
            <div className="card" style={{ "width": "18rem", "margin": "10px" }} key={user.Userid}>
                <div className="card-body">
                    <h5 className="card-title">{user.FirstName} {user.LastName}</h5>
                </div>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item">{user.Email}</li>
                    <li className="list-group-item">{user.Address}</li>
                    <li className="list-group-item">{user.Qualification}</li>
                    <li className="list-group-item">{user.Gender}</li>
                </ul>
                <div className="card-body">
                  <button onClick={() => deleteUser(user.Userid)}>Delete</button>
                </div>
            </div>
        ));
    };
    return ( <>
    <NavbarA/>
    <center>
    <h3>All Users</h3>
                <button onClick={() => console.log(users)}>My users button</button>
                <div className="container">
                    {renderInterviewers()}
                </div>
            </center>
    </>  );
}

export default AllUsers;