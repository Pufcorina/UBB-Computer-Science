import React from "react";
import Navbar from "../components/navbar";

class FAQ extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                {/* Navbar */}
                <Navbar notLoggedIn={true} extraLinks={[
                    {text: "HOME", reference: "/home", extraClasses: ''},
                    {text: "FAQ", reference: "/faq", extraClasses: 'active-navbar-link'},
                    {text: "CONTACT", reference: "/contact", extraClasses: ''}
                ]}/>

                {/* Intro FAQ */}
                <div className="introFaq">
                    <div className="container">
                        <div className="row align-items-center justify-content-center" style={{minHeight: '88vh'}}>
                            <div className="col-11 col-sm-10 col-md-10" style={{textAlign: 'center'}}>
                                <h1 className="questrial-font" style={{color: '#560001'}}>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Donation FAQ
                                </h1>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="container faqs">
                    <div className="row align-items-center justify-content-center">
                        <div className="col-11 col-sm-10 col-md-10" style={{textAlign: 'justify', paddingTop: '60px', paddingBottom: '60px'}}>
                            <div>
                                <h3>Q: How can doctors and blood donation centers create accounts?</h3>
                                <p>A: They should contact our administrators and an account will be created for them. The “Sign Up” feature creates accounts for normal users (donators).</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: How does age affect my ability to donate?</h3>
                                <p>A: There is a minimum (18 years old) and maximum (60 years old) age; if you fall in-between these ages, you can donate.</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I had cancer, can I donate?</h3>
                                <p>A: In most cases, you can donate again if you remain free of cancer for five years after completing treatment. This is to protect your health by ensuring, as far as possible, that the cancer is gone and will not recur. Five years is the period most often used by doctors to define a cancer as presumed 'cured'. (<em>However</em>, if you have a history of diseases that involve blood, such as leukemia or lymphoma, you can never donate again.)</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I have had several alcoholic drinks prior to giving blood, can I donate?</h3>
                                <p>A: No, this is because being intoxicated can affect your ability to understand and answer the donor questionnaire and declaration. It also affects your body’s ability to tolerate blood being taken.</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I take antidepressants, can I donate?</h3>
                                <p>A: Normally, yes, as long as you are feeling well and experience no side effects of the medication.</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I am breastfeeding, can I donate?</h3>
                                <p>A: For your health, following childbirth you need to wait at least nine months and until your baby is significantly weaned (that is, getting most of his/her nutrition from solids) before you donate blood.</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I have just had a piercing, can I donate?</h3>
                                <p>A: It depends on the type of piercing. If you had your ear pierced, only plasma can be donated in the following 24 hours; following that time frame, there are no restrictions. In the case of any other piercing, you can only donate plasma for the following 4 months, after that, there are no restrictions.</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I have just gotten a tattoo, can I donate?</h3>
                                <p>A: It depends if the area in which you had the tattoo done enforces strict regulations for tattoo facilities. If that is the case, then yes, you can donate blood, otherwise it would be advisable to wait up to 12 months prior to donating blood.</p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I have just had acupuncture, can I donate?</h3>
                                <p>A: Yes, if the acupuncture was performed by a registered acupuncturist or a medical practitioner, you can only donate plasma in the first 24 hours after treatment. Following that, there are no restrictions. If the practitioner is not registered, you need to confirm whether the needles were sterile, single-use ones. If not, you can only donate 4 months after the acupuncture procedure. </p>
                            </div>

                            <br/>
                            <br/>

                            <div>
                                <h3>Q: I am a professional athlete, can I donate?</h3>
                                <p>A: Yes, but our recommendation is not to donate whole blood during a competitive period/season. You can donate plasma at any time, though.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default FAQ;