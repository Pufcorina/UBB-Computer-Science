import React from "react";
import Form from '../components/form';
import Input from '../components/input';
import BlockButton from '../components/block-button';
import {Link} from 'react-router-dom';
const authApi = require('../api/auth-api');

/*
  Login Component
*/
class Login extends React.Component {
  constructor() {
      super();
      this.state = {
        username: '',
        password: ''
      };
      this.handleChangedUsername = this.handleChangedUsername.bind(this);
      this.handleChangedPassword = this.handleChangedPassword.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
      this.login = authApi.login.bind(this);
      //localStorage.setItem("loggedInUser", "admin");  //TODO: remove this! for testing purposes only!
      //localStorage.setItem("loggedInUserRole", "Administrator");  //TODO: remove this! for testing purposes only!
  }

  handleChangedUsername(event) {
    this.setState({username: event.target.value});
  }

  handleChangedPassword(event) {
    this.setState({password: event.target.value});
  }

  handleSubmit(event) {
      event.preventDefault();
      this.login();
  }

  render() {
    if (!localStorage.loggedInUser) {
      return (
        <div className="container" style={{minHeight: '100vh'}}>
          <div className="row align-items-center justify-content-center" style={{minHeight: '100vh'}}>
            <div className="col-9 col-sm-8 col-md-6 col-lg-4">
                <img src="/images/bloodyfast-logo.jpg" alt="BloodyFast logo" className="logo"/>

              <Form handleSubmit={this.handleSubmit}>
                <Input type="text" placeholder="Username" onChange={this.handleChangedUsername}/>
                <Input type="password" placeholder="Password" onChange={this.handleChangedPassword}/>
              </Form>

              <div style={{margin: '15px 0px'}}>
                <Link to='/register' style={{float: 'left', color: '#29aae1', fontSize: 'small'}}>Sign up</Link>
                <Link to='/login' style={{float: 'right', color: '#29aae1', fontSize: 'small'}}>Forgot Password?</Link> {/* TODO: change link */}
                <br/>
              </div>

              <BlockButton color='#ec0a0b' onClick={this.handleSubmit} fontFamily="Questrial">LOG IN</BlockButton>
            </div>
          </div>
        </div>
      );
    } else {
      return(
          <div className="container" style={{height: '100vh'}}>
            <div className="row align-items-center" style={{height: '100vh'}}>
              <div className="col-11" style={{textAlign: 'center'}}>
                <h2>You are already logged in!</h2>
              </div>
            </div>
          </div>
      );
    }
  }
}

export default Login;
