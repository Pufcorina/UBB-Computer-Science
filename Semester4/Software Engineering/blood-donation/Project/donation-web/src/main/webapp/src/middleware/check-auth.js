import React from "react";
import {Redirect} from "react-router-dom";
import AccessDenied from "../modules/access-denied";

/*
  EnsureLoggedIn Middleware (basically, a function)
  Description:
    returns a component if the user is logged in and has access;
    otherwise, redirects the user to the login page or shows an appropriate "access denied" page

  Input:
    @props {object} Component = the React component to be rendered if logged in
    @props {string} currentPath = the path from which the user is redirected to the login page
    @props props = properties to pass on to the component
*/

const permissions = {
    "administrator": ['/admin-dashboard', '/logout'],
    "donor": ['/user-dashboard', '/next-donation', '/my-info', '/test-results-history', '/logout'],
    "doctor": ['/new-request', '/sent-requests', '/patient-blood-need', '/city-blood-stocks', '/logout'],
    "hospital personnel": ['/our-blood-stock', '/find-donor', '/city-blood-stocks', '/received-requests', '/process-request', '/received-donation-forms', '/upload-test-results', '/logout']
};

const EnsureCorrectUserLoggedIn = (Component, currentPath, props) => {
    //if not logged in at all:
    if (!localStorage.loggedInUser) {
        return <Redirect to="/login"/>
    } else {
        //checking permissions
        if (localStorage.loggedInUserRole && (permissions[(localStorage.loggedInUserRole).toLowerCase()].indexOf(currentPath) !== -1)) {
            return <Component {...props}/>;
        } else {
            return <AccessDenied/>
        }
    }
};

const EnsureNotLoggedIn = (Component, currentPath, props) => {
    if (!localStorage.loggedInUser) {
        return <Component {...props}/>;
    } else {
        return <Redirect to="/user-dashboard"/>
    }
};

export {EnsureCorrectUserLoggedIn, EnsureNotLoggedIn};
