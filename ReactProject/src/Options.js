import {ListGroupItem,ListGroup} from "react-bootstrap"
import {Link} from "react-router-dom";
import "./Components/options.css"
export default function Options(){
    return(
        <div>
            <ListGroup className="options-list">
                <Link className="list-group-item list-group-item-action options-list-items" to="/home">Home</Link>
                {window.localStorage.getItem("isLogged") === "true" &&<Link className="list-group-item list-group-item-action options-list-items" to="/profile">Profile</Link>}
                {window.localStorage.getItem("role") === "true" && <Link className="list-group-item list-group-item-action options-list-items" to="/register">Register</Link>}
                {window.localStorage.getItem("isLogged") === "true" && <Link className="list-group-item list-group-item-action options-list-items" to="/allStudents">All Students</Link>}
            </ListGroup>
        </div>
    )
}