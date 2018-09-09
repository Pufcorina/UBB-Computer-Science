import React from "react";
import Navbar from "../components/navbar";
const usersApi = require('../api/users-api');

class UserDashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            hasNewTestResults: false,
            illnessDiscovered: false,
            illnessInfo: '',
            nextPossibleDonationDate: ''
        };
        this.prefillFields = usersApi.getDashboardInfo.bind(this);
    }

    componentWillMount() {
        this.prefillFields().then((res)=> {
            if(!res.data.isError){
                this.setState(res.data,function(){
                    console.log(this.state);
                    if(this.state.firstName ==="User") {
                        this.refs.welcomeMessage.innerHTML = "Welcome to BloodyFast!";
                        this.refs.newUser.innerHTML = "Please fill in your personal information <a href='/my-info' class='dashboard-link'>here.</a>";
                    }
                    else {
                        this.refs.welcomeMessage.innerHTML = "Welcome," + this.state.firstName + "!";
                        if (this.state.hasNewTestResults) {
                            this.refs.newTestResultsNotification.innerHTML = "You have new blood test results. Check them out <a href='/test-results-history' class='dashboard-link'>here.</a>";
                        } else {
                            this.refs.newTestResultsNotification.innerHTML = "You don't have any new blood test results.";
                        }
                        if (this.state.illnessDiscovered) {
                            this.refs.illnessDiscoveredNotification.innerHTML = this.state.illnessInfo + " Please contact your doctor for a check-up.";
                            this.refs.nextPossibleDonationDate.innerHTML = "Unfortunately, in your condition you cannot donate blood. If there has been a mistake, contact us using the information provided in the Contact page.";
                        } else {
                            this.refs.nextPossibleDonationDate.innerHTML = "Next possible donation date: " + this.state.nextPossibleDonationDate;
                        }
                    }
                })
            }
        })
    }

    render() {
        return (
            <div>
                {/* Navbar */}
                <Navbar notLoggedIn={false} extraLinks={[
                    {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: 'active-navbar-link'},
                    {text: "NEXT DONATION", reference: "/next-donation", extraClasses: ''},
                    {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: ''},
                    {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                ]}/>

                <div className="user-dashboard">
                    <div className="container">
                        <div className="row align-items-center justify-content-center" style={{minHeight: '90vh', color: '#560001'}}>
                            <div className="col-11 col-sm-10 col-md-10" style={{textAlign: 'right'}}>
                                <h1 ref="welcomeMessage" className="questrial-font" style={{marginBottom: '20px', fontWeight: 'lighter', fontSize: '48px'}}/>
                                <p ref="newUser" style={{marginBottom: '10px', fontWeight: 'lighter', fontSize: '25px'}}/>
                                <p ref="newTestResultsNotification" style={{marginBottom: '10px', fontWeight: 'lighter', fontSize: '25px'}}/>
                                <p ref="illnessDiscoveredNotification" style={{marginBottom: '10px', fontSize: '25px'}}/>
                                <p ref="nextPossibleDonationDate" style={{marginBottom: '10px', fontWeight: 'lighter', fontSize: '25px'}}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UserDashboard;