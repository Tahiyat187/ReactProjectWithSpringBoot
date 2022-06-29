import { useState } from "react";
import {Container,Navbar,Nav,Form,FormControl,Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import axios from "axios";
import StudentTable from "./StudentTable";
export default function NavbarCustom(){
  const [searchItem,setSearch] = useState();
  const logout=()=>{
    localStorage.clear();
    window.location.href = "/";
  }

  const handleSearch= async ()=>{
    const response = await axios.get(`/search`,
    JSON.stringify({name:searchItem}),{
        headers: {
          'Content-Type': 'application/json',
          'Authorization': window.localStorage.getItem("token")
        }
      });
      console.log(response.data);
      return(
        <StudentTable std={response.data}/>
      )
  }

    return (
      <Navbar bg="dark" variant="dark" expand="md" className="justify-content-center">
      <Container fluid>
        <Navbar.Brand href="/">Student App</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '100px' }}
            navbarScroll
          >
            </Nav>
            {/* {window.localStorage.getItem("isLogged") === "true" && <Form className="d-flex">
            <FormControl
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            onChange={e => setSearch({val: e.target.value})}/>
            <Button variant="outline-success" onClick={handleSearch}>Search</Button>
          </Form>} */}
          {window.localStorage.getItem("role") === "true" && <text style={{color:"white"}}>User Role: Admin</text>}
          {window.localStorage.getItem("role") === "false" && <text style={{color:"white"}}>User Role: Student</text>}
          {window.localStorage.getItem("isLogged") === "true" &&<Nav.Link onClick={logout}>Logout</Nav.Link>}
          {window.localStorage.getItem("isLogged") !== "true" &&<Link className="list-group-item" to="/login">Login</Link>}
        </Navbar.Collapse>
      </Container>
    </Navbar>
    );
}