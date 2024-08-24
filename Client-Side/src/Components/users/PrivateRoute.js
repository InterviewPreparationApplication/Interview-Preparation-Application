import { Outlet,Navigate } from "react-router-dom";
import { isLoggedIn } from "../routes/auth";
const PrivateRoute=()=> {

  return isLoggedIn()?<Outlet/>: <Navigate to={"/login"}/>
}

export default PrivateRoute;