import React from "react";
import Navbar from "../components/navbar";

class Contact extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                {/* Navbar */}
                <Navbar notLoggedIn={true} extraLinks={[
                    {text: "HOME", reference: "/home", extraClasses: ''},
                    {text: "FAQ", reference: "/faq", extraClasses: ''},
                    {text: "CONTACT", reference: "/contact", extraClasses: 'active-navbar-link'}
                ]}/>

                <div>
                    <div>
                        <div className="row align-items-center justify-content-center" style={{minHeight: '90vh'}}>
                            <h1>Contact Us! Still in progress</h1>
                    </div>

                    </div>
                </div>
            </div>
        );
    }
}

export default Contact;