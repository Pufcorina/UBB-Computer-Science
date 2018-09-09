const axios = require('axios');

function register() {
    axios.post('/users', { username: this.state.username, email: this.state.email, password: this.state.password })
        .then((response) => {
            if (!response.data.isError) {
                axios.post('/login', { username: this.state.username, password: this.state.password })
                    .then((response) => {
                        if (!response.data.isError) {
                            axios.get('/users/'+this.state.username)
                                .then((result) => {
                                    // setting up the local storage with useful information about the user:
                                    localStorage.setItem("loggedInUser", this.state.username);
                                    localStorage.setItem("loggedInUserRole", result.data.role);
                                    localStorage.setItem("loggedInCenterId", result.data.centerId);
                                    //localStorage.setItem("loggedInUserEmail", result.data.rows[0].email);

                                    this.props.history.push('/user-dashboard');
                                })
                                .catch(err => { alert(err); });
                        } else {
                            alert(response.data.message);
                        }
                    })
                    .catch(function(err) { alert(err); });
            } else {
                alert(response.data.message);
            }
        })
        .catch(function(err) {
            alert(err);
        });
}

function submitMyInfo(myInfo) {
    return axios.post('/my-info', myInfo)
        .then((response) => {
            if (response.data.isError) {
                alert(response.data.message);       //TODO: get rid of alerts!
            }
        })
        .catch(function(err) {
            alert(err);
        });
}

function getMyInfo() {
    //return {"firstName": "Ada", "lastName": "Simion", "allergies": "yes", "diseases": "no", "chronicIllness": "no"};  //TODO: remove this test
    return axios.get('/my-info/'+localStorage.getItem("loggedInUser"));
}

function getDashboardInfo() {
    //TODO: remove these two test cases
    //return {"firstName": "John", "hasNewTestResults": true, "illnessDiscovered": true, "illnessInfo": "We are sorry to inform you that you might suffer from hepatitis.", "nextPossibleDonationDate": ''};
    //return {"firstName": "Hannah", "hasNewTestResults": false, "illnessDiscovered": false, "illnessInfo": "", "nextPossibleDonationDate": '03.06.2018'};
    return axios.get('/dashboard-info/'+localStorage.getItem("loggedInUser"));
}

function registerDoctorOrStaff(data){
    console.log(data);
    return axios.post('/register/doctor-staff',data);
}

module.exports = {register, submitMyInfo, getMyInfo, getDashboardInfo, registerDoctorOrStaff};