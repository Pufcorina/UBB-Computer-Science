import React from 'react';

/*
    UserOrButtons Component (for the Navbar)
 */
class UserOrButtons extends React.Component {
    constructor(props) {
        super(props);
        this.notLoggedIn = props.notLoggedIn;
    }

    render() {
        if(this.notLoggedIn) {
            return (
                <ul className="navbar-user navbar-nav my-navbar-list">
                    <li className="nav-item navbar-visible-link">
                        <a className="nav-link" href="/login">LOG IN</a>
                    </li>
                    <li className="nav-item navbar-visible-link">
                        <a className="nav-link" href="/register">SIGN UP</a>
                    </li>
                </ul>
            );
        } else {
            return (
                <ul className="navbar-user navbar-nav my-navbar-list">
                    <li className="nav-item dropdown navbar-visible-profile-link">
                        <a className="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            {localStorage.loggedInUser.toUpperCase()}
                        </a>
                        <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a className="dropdown-item" href="/logout">Log Out</a>
                        </div>
                    </li>
                </ul>
            );
        }
    }
}

export default UserOrButtons;
