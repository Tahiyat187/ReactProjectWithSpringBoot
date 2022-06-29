import {useState,useEffect} from 'react';
import axios from 'axios';
import './Components/profile.css'
export default function Profile(){
    const [student, setStudent] = useState({name:'',id:'',password:'',role:'',age:'',address:''});
    
    const set = name => {
        return ({ target: { value } }) => {
          setStudent(oldValues => ({...oldValues, [name]: value }));
        }
      };

      useEffect(() =>  {
        axios.get(`/profile`,{headers: {
          'Content-Type': 'application/json',
          'Authorization': window.localStorage.getItem("token")
        }})
        .then((data) => {setStudent(data.data);
          console.log(data);
        }) },[])

        const handleUpdate =()=>{
            const response = axios.put(`/update`,
        JSON.stringify({id:student.id,name:student.name,password:student.password,role:student.role,age:student.age,address:student.address}),{
            headers: {
              'Content-Type': 'application/json',
              'Authorization': window.localStorage.getItem("token")
            }
          });
        }

    return(
        <div className="update-div">
        <h2 className="update-heading">User Profile</h2>
        <form action="" className="update-form" onSubmit={handleUpdate}>
            <label className="update-label">ID: </label>
            <input className='update-input' value={student.id} disabled/>
            <br/>
            <label className="update-label">Name: </label>
            <input className="update-input" value={student.name} disabled/>
            <br />
            <label className="update-label">Password </label>
            <input className="update-input" value={student.password} type="text" name="password" min="8" required pattern="[a-zA-Z0-9 ]+" onChange={set('password')}/>
            <br />
            <label className="update-label">Role </label>
            <select name="role" value={student.role} onChange={set('role')} className="update-input">
              <option value="ROLE_ADMIN" className="update-input">ADMIN</option>
              <option value="ROLE_STUDENT" className="register-input">STUDENT</option>
            </select>
            <br />
            <label className="update-label">Age: </label>
            <input className="update-input" value={student.age} type="number" name="age" required min={1} pattern="[0-9]" onChange={set('age')}></input>
            <br />
            <label className="update-label">Address: </label>
            <textarea className="update-input" value={student.address} name="address" onChange={set('address')}/>
            <br/>
            <input type="submit" className="update-button" value="Update"></input>
        </form>
        </div>
    )
}