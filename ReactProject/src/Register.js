import { useState, useEffect } from "react";
import "./Components/registerForm.css";
import axios from "axios";
const Register = () => {
    const [student, setStudent] = useState({name:'',password:'',role:'',age:'',address:''});
    
    const set = name => {
        return ({ target: { value } }) => {
          setStudent(oldValues => ({...oldValues, [name]: value }));
        }
      };

    const handleSubmit= async (e) =>{
        e.preventDefault();
        const response = await axios.post(`/register`,
        JSON.stringify({name:student.name,password:student.password,role:student.role,age:student.age,address:student.address}),{
            headers: {
              'Content-Type': 'application/json',
              'Authorization': window.localStorage.getItem("token")
            }
          });
          console.log(response);
    }

    return (
        <div className="register-div">
        <h2 className="register-heading">Register</h2>
        <form action="" className="register-form" onSubmit={handleSubmit}>
            <label className="register-label">Name: </label>
            <input className="register-input" type="text" name="name" required pattern="[a-zA-Z0-9 ]+" onChange={set('name')}/>
            <br />
            <label className="register-label">Password </label>
            <input className="register-input" type="text" name="password" min="8" required pattern="[a-zA-Z0-9 ]+" onChange={set('password')}/>
            <br />
            <label className="register-label">Role </label>
            <select name="role" onChange={set('role')} className="register-input">
              <option value="ROLE_ADMIN" className="register-input">ADMIN</option>
              <option value="ROLE_STUDENT" className="register-input">STUDENT</option>
            </select>
            <br />
            <label className="register-label">Age: </label>
            <input className="register-input" type="number" name="age" required min={1} pattern="[0-9]" onChange={set('age')}></input>
            <br />
            <label className="register-label">Address: </label>
            <textarea className="register-input" name="address" onChange={set('address')}/>
            <br/>
            <input type="submit" className="register-button"></input>
        </form>
        </div>
    )
}

export default Register