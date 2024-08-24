import axios from "axios"
//get user by id
export  const  GetUserByid=async(id,headers)=>{
   try{
    if(id!=null)
    {
       
    }else {
        throw new Error("id is Blank");
    }
   }
   catch(error){
    console.error(error);
   }
    
}