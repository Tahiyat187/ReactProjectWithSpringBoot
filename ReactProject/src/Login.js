import { useState, useEffect } from "react";
import "./Components/registerForm.css";
import {Redirect,useHistory} from "react-router-dom"
import axios from "axios";

export default function Login(){
    const [user, setUser] = useState({name:'',password:''});
  const history = useHistory();
    const set = name => {
        return ({ target: { value } }) => {
            setUser(oldValues => ({...oldValues, [name]: value }));
        }
      };

    const handleSubmit= async (e) =>{
        e.preventDefault();
        const res = await axios.post(`http://localhost:8080/authenticate`,
        JSON.stringify({username:user.name,password:user.password}),{
            headers: {
              'Content-Type': 'application/json'
            }
          });
        window.localStorage.setItem("token","Bearer "+res.data.jwtToken);
        window.localStorage.setItem("role",res.data.role);
        window.localStorage.setItem("isLogged","true");
        if(res.status === 200){
          window.location.href = "/";
        }
    }

    return (
        <div className="register-div">
        <h2 className="register-heading">Login</h2>
        <form action="" className="register-form" onSubmit={handleSubmit}>
            <label className="register-label">Name: </label>
            <input className="register-input" type="text" name="name" required pattern="[a-zA-Z0-9 ]+" onChange={set('name')}/>
            <br />
            <label className="register-label">Password: </label>
            <input className="register-input" type="password" name="password" required pattern="[a-zA-Z0-9 ]+" onChange={set('password')}/>
            <br/>
            <input type="submit" className="register-button"></input>
        </form>
        </div>
    )
}