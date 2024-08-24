import { Outlet,Navigate } from "react-router-dom";
import { isLoggedIn } from "../routes/auth";
const PrivateRouteI=()=> {

  return isLoggedIn()?<Outlet/>: <Navigate to={"/ourexperts"}/>
}

export default PrivateRouteI;