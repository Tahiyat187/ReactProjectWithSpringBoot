import './App.css';
import { Container,Row,Col } from 'react-bootstrap';
import Register from './Register';
import { BrowserRouter as Router,Route, Switch } from 'react-router-dom';
import Login from "./Login";
import Options from './Options';
import NavbarCustom from './NavbarCustom';
import AllStudent from './AllStudents';
import Home from './Home';
import Profile from './Profile';
function App() {
  return (
    
    <div>
      <Router>
      <Container>
      <NavbarCustom/>
        <Row>
          <Col md={3}><Options/></Col>
          <Col md={9}>
            <Switch>
            {window.localStorage.getItem("isLogged") === "true" &&<Route path="/profile" component={Profile}></Route>}
              {window.localStorage.getItem("isLogged") !== "true" && <Route path="/login" component={Login}></Route>}
              {window.localStorage.getItem("isLogged") === "true" && <Route path="/allStudents" component={AllStudent}></Route>}
              {window.localStorage.getItem("role") === "true" && <Route path="/register" component={Register}></Route>}
              <Route path="/" component={Home}></Route>
              
              </Switch>
          </Col>
        </Row>
      </Container>
      </Router>
      </div>
  );
}

export default App;
