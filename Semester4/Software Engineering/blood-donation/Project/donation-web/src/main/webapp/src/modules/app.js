import React from "react";
import Home from "./home";
import FAQ from "./FAQ";
import Login from "./login";
import Logout from "./logout";
import Register from "./register";
import MyInfo from "./my-info";
import UserDashboard from "./user-dashboard";
import SentRequests from "./sent-requests";
import UploadTestResults from "./upload-test-results";
import OurStock from './our-blood-stock';
import ProcessRequest from "./process-request";
import ReceivedRequests from "./received-requests";
import ReceivedDonationForms from "./received-donation-forms";
import NextDonation from "./next-donation";
import FindDonor from "./find-donor";
import AdminDashboard from './admin-dashboard';
import NewRequest from "./new-request";
import TestResultsHistory from './test-results-history';
import {
    BrowserRouter,
    Route
} from "react-router-dom";
import {EnsureCorrectUserLoggedIn} from '../middleware/check-auth.js';
import {EnsureNotLoggedIn} from '../middleware/check-auth.js';
import Contact from "./contact";

class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <div>
                    <Route path="/home" render={(props) => EnsureNotLoggedIn(Home, "/home", props)}/>
                    <Route path="/faq" render={(props) => EnsureNotLoggedIn(FAQ, "/faq", props)}/>
                    <Route path="/contact" render={(props) => EnsureNotLoggedIn(Contact, "/contact", props)}/>
                    <Route path="/login" component={Login}/>
                    <Route path="/logout" render={(props) => EnsureCorrectUserLoggedIn(Logout, "/logout", props)}/>
                    <Route path="/register" component={Register}/>
                    <Route path="/my-info" render={(props) => EnsureCorrectUserLoggedIn(MyInfo, "/my-info", props)}/>
                    <Route path="/user-dashboard" render={(props) => EnsureCorrectUserLoggedIn(UserDashboard, "/user-dashboard", props)}/>
                    <Route path="/next-donation" render={(props) => EnsureCorrectUserLoggedIn(NextDonation, "/next-donation", props)}/>
                    <Route path="/new-request" render={(props) => EnsureCorrectUserLoggedIn(NewRequest, "/new-request", props)}/>
                    <Route path="/sent-requests" render={(props) => EnsureCorrectUserLoggedIn(SentRequests, "/sent-requests", props)}/>
                    <Route path="/received-requests" render={(props) => EnsureCorrectUserLoggedIn(ReceivedRequests, "/received-requests", props)}/>
                    <Route path="/process-request" render={(props) => EnsureCorrectUserLoggedIn(ProcessRequest, "/process-request", props)}/>
                    <Route path="/received-donation-forms" render={(props) => EnsureCorrectUserLoggedIn(ReceivedDonationForms, "/received-donation-forms", props)}/>
                    <Route path="/test-results-history" render={(props) => EnsureCorrectUserLoggedIn(TestResultsHistory, "/test-results-history", props)}/>
                    <Route path="/our-blood-stock" render={(props) => EnsureCorrectUserLoggedIn(OurStock, "/our-blood-stock", props)}/>
                    <Route path="/upload-test-results" render={(props) => EnsureCorrectUserLoggedIn(UploadTestResults, "/upload-test-results", props)}/>
                    <Route path="/find-donor" render={(props) => EnsureCorrectUserLoggedIn(FindDonor, "/find-donor", props)}/>
                    <Route path="/admin-dashboard" render={(props) => EnsureCorrectUserLoggedIn(AdminDashboard, "/admin-dashboard", props)}/>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;