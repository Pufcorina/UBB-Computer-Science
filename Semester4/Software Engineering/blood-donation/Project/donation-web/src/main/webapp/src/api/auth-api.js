const axios = require('axios');

function redirectToFirstPage() {
    let currentRole = localStorage.getItem("loggedInUserRole");
    if (currentRole.toLowerCase() === "donor")
        this.props.history.push('/user-dashboard');
    else if (currentRole.toLowerCase() === "doctor")
        this.props.history.push('/sent-requests');
    else if ((currentRole.toLowerCase() === "hospital personnel"))
        this.props.history.push('/received-requests');
    else
        this.props.history.push('/admin-dashboard');
}

function login() {
    axios.post('/login', { username: this.state.username, password: this.state.password })
        .then((response) => {
            if (!response.data.isError) {
                axios.get('/users/'+this.state.username)
                    .then((result) => {
                        // setting up the local storage with useful information about the user:
                        localStorage.setItem("loggedInUser", this.state.username);
                        localStorage.setItem("loggedInUserRole", result.data.role);
                        localStorage.setItem("loggedInCenterId", result.data.centerId);       //TODO: check if it works

                        // redirect to the previous component (which redirected me to the login page), if it exists:
                        redirectToFirstPage.bind(this)();
                    })
                    .catch(err => { alert(err); });
            } else {
                alert(response.data.message);
            }
        })
        .catch(function(err) { alert(err); });
}

function logout() {
    return axios.post('/logout', { username: localStorage.loggedInUser })
        .then(() => {
            localStorage.removeItem("loggedInUser");
            localStorage.removeItem("loggedInUserRole");
            localStorage.removeItem("loggedInCenterId");
            this.setState({ success: true });
        })
        .catch(function(err) {
            alert(err);
        });
}

module.exports = {
    login,
    logout
};