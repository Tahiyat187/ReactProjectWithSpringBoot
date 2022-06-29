import axios from "axios";
import { useEffect, useState } from "react";
import {Table} from "react-bootstrap";
import "./Components/AllStudents.css"
import StudentTable from "./StudentTable";
export default function AllStudent(){

  const [students,setStudents] = useState();

  useEffect(() =>  {
    axios.get(`/findAll`,{headers: {
      'Content-Type': 'application/json',
      'Authorization': window.localStorage.getItem("token")
    }})
    .then((data) => {setStudents(data.data);
      console.log(data);
    }) },[])

    return(
      <Table className="allStudents-table" striped bordered hover>
  <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Address</th>
      {window.localStorage.getItem("role") === "true" && <th>Age</th>}
    </tr>
  </thead>
  <tbody>
    {students?.map((student)=>{
      return(
        <><StudentTable std={student}/></>
        )
      }
    )}
  </tbody>
</Table>
      //  <></>
    )
}