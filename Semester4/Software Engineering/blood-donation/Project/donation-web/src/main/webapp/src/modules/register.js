import React from "react";
import Form from '../components/form';
import Input from '../components/input';
import BlockButton from '../components/block-button';
const usersApi = require('../api/users-api');

/*
  Register Component
*/
class Register extends React.Component {
    constructor() {
        super();
        this.state = {
            username: '',
            email: '',
            password: ''
        };
        this.handleChangedUsername = this.handleChangedUsername.bind(this);
        this.handleChangedEmail = this.handleChangedEmail.bind(this);
        this.handleChangedPassword = this.handleChangedPassword.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.register = usersApi.register.bind(this);
    }

    handleChangedUsername(event) {
        this.setState({username: event.target.value});
    }

    handleChangedEmail(event) {
        this.setState({email: event.target.value});
    }

    handleChangedPassword(event) {
        this.setState({password: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        this.register();
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
                                <Input type="text" placeholder="Email" onChange={this.handleChangedEmail}/>
                                <Input type="password" placeholder="Password" onChange={this.handleChangedPassword}/>
                            </Form>

                            <br/>
                            <br/>

                            <BlockButton color='#ec0a0b' onClick={this.handleSubmit} fontFamily="Questrial">REGISTER DONATOR*</BlockButton>

                            <h6 style={{marginBottom: '50px', fontSize: '16px', fontWeight: 'lighter'}}>* doctors and donation centers must contact the admins for new accounts. See "Contact" for details.</h6>

                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div className="container" style={{minHeight: '100vh'}}>
                    <div className="row align-items-center justify-content-center" style={{minHeight: '100vh'}}>
                        <h3>You are already logged in!</h3>
                    </div>
                </div>
            );
        }
    }
}

export default Register;
