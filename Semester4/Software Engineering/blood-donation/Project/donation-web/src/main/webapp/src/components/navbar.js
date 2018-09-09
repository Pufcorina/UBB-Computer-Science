import React from 'react';
import UserOrButtons from "./user-or-buttons";

/*
    Navbar Component
 */
class Navbar extends React.Component {
    constructor(props) {
        super(props);
        this.extraLinks = props.extraLinks || [];
        this.notLoggedIn = props.notLoggedIn || false;
    }

    render() {
        return (
            <nav className="navbar navbar-toggleable-sm navbar-inverse my-navbar">
                <div className="container">
                    {/* Logo */}
                    <a className="navbar-brand" href="">
                        <img src="/images/bloodyfast-logo.jpg" alt="BloodyFast Logo" className="my-navbar-logo" />
                    </a>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <div className="row justify-content-end" style={{width: '100%'}}>
                            <ul className="navbar-nav my-navbar-list">
                                {  this.extraLinks.map((link, index) => <li key={index} className="nav-item navbar-visible-link">
                                                                            <a className={"nav-link " + link.extraClasses} href={link.reference}>{link.text}</a>
                                                                        </li>)  }
                                <UserOrButtons notLoggedIn={this.notLoggedIn}/>
                            </ul>
                        </div>
                    </div>
                </div>

                <button className="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"/>
                </button>
            </nav>
        );
    }
}

export default Navbar;
