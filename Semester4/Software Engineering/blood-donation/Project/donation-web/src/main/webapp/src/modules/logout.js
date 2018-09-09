import React from "react";
const authApi = require('../api/auth-api');

/*
  Logout Component
*/
class Logout extends React.Component {
    state = {
        success: false,
        username: ''
    };

    componentWillMount() {
        if (localStorage.loggedInUser) {
            this.setState({username: localStorage.getItem("loggedInUser")});
            // calling the api for logout in database:
            (authApi.logout.bind(this))()
                .then (() => {setTimeout(() => {
                    this.props.history.push('/home');
                }, 700)});
        }
        //localStorage.removeItem("loggedInUser");  //TODO: remove this! for testing purposes only!
        //localStorage.removeItem("loggedInUserRole");  //TODO: remove this! for testing purposes only!
    }

    render() {
        return (
            <div className="container" style={{height: '100vh'}}>
                <div className="row align-items-center justify-content-center" style={{height: '100vh'}}>
                    <h3>Logging out {this.state.username}...</h3>
                </div>
            </div>
        );
    }
}

export default Logout;
