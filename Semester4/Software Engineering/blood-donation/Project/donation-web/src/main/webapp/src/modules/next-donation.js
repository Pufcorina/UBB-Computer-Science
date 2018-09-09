import React from "react";
import BlockButton from "../components/block-button";
import Button from "../components/button";
import Navbar from "../components/navbar";
import Form from "../components/form";
import Input from "../components/input";
const donationsApi = require('../api/donations-api');

class NextDonation extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            profileDto:{
                firstName: '',
                lastName: '',
                dateOfBirth: '',
                gender: '',
                cnp: '',
                bloodGroup: '',
                rh: '',
                email: '',
                phone: '',
                allergies: 'no',
                diseases: 'no',
                chronicIllness: 'no'
            },
            addressDto:{
                homeAddress: '',  //din buletin
                city: '',             //din buletin
                country: '',          //din buletin
                currentHomeAddress: '',
                currentCity: '',
                currentCountry: ''
            },
            username: localStorage.getItem("loggedInUser"),
            donationDto:{
                donation_id:'',
                status: '',
                rejectionReason: '',
                cancerPast5Years: '',
                bloodPressure: '',
                pulse: '',
                weight: '',
                recentTattoos: '',
                pregnantOrMenstruating: '',
                surgeryPast6Months: '',
                donationBeneficiary: ''
            },

            disabledSubmitButton: true   //ignore this in the back-end
        };

        this.handleChangedTextField = this.handleChangedTextField.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangedCancer = this.handleChangedCancer.bind(this);
        this.handleChangedPregnant = this.handleChangedPregnant.bind(this);
        this.handleChangedSurgeries = this.handleChangedSurgeries.bind(this);
        this.handleChangedTattoos = this.handleChangedTattoos.bind(this);
        this.handleChangedCorrectInfo = this.handleChangedCorrectInfo.bind(this);
        this.goToNewForm = this.goToNewForm.bind(this);
        this.submitDonation = donationsApi.submitDonation.bind(this);
        this.prefillFields = donationsApi.getDonationFormInfo.bind(this);
        this.updateDonation = donationsApi.updateDonation.bind(this);
        this.optionals = ["bloodPressure", "pulse", "donationBeneficiary"];
    }

    componentWillMount() {
        this.prefillFields().then((res)=>{
            console.log("res = ");
            console.log(res);
            this.setState(res.data, function(){
                let myInfo = this.state;
                if(this.state["donationDto"]["status"] === '' || this.state["donationDto"]["status"] === 'CLOSED') {
                    if (this.state["profileDto"]["allergies"] === 'yes') {
                        this.refs.allergiesYes.checked = true;
                    } else {
                        this.refs.allergiesNo.checked = true;
                        myInfo['profileDto']["allergies"] = 'no'
                    }

                    if (this.state["profileDto"]["diseases"] === 'yes') {
                        this.refs.diseasesYes.checked = true;
                    } else {
                        this.refs.diseasesNo.checked = true;
                        myInfo['profileDto']["diseases"] = 'no'
                    }

                    if (this.state["profileDto"]["chronicIllness"] === 'yes') {
                        this.refs.chronicYes.checked = true;
                    } else {
                        this.refs.chronicNo.checked = true;
                        myInfo['profileDto']["chronicIllness"] = 'no'
                    }
                    this.setState({username: localStorage.getItem("loggedInUser")});
                    console.log("Added username = ");
                    console.log(this.state);
                    this.setState({disabledSubmitButton: true});
                    console.log("this.state = ");
                    console.log(this.state);
                }
                console.log("this.state = ");
                console.log(this.state);
            })
        });
    }

    handleChangedTextField(event) {
        let field = event.target.name;
        let value = event.target.value;
        let myInfo = this.state;
        myInfo["donationDto"][field]=value;
        this.setState(myInfo);
    }

    handleChangedCancer(event) {
        let myInfo = this.state;
        if (event.target.id==='cancerYes' && event.target.checked) {
            myInfo["donationDto"]["cancerPast5Years"] = true;
            this.setState(myInfo);
        } else {
            myInfo["donationDto"]["cancerPast5Years"] = false;
            this.setState(myInfo);
        }
    }

    handleChangedTattoos(event) {
        let myInfo = this.state;
        if (event.target.id==='tattoosYes' && event.target.checked) {
            myInfo["donationDto"]["recentTattoos"] = true;
            this.setState(myInfo);
        } else {
            myInfo["donationDto"]["recentTattoos"] = false;
            this.setState(myInfo);
        }
    }

    handleChangedSurgeries(event) {
        let myInfo = this.state;
        if (event.target.id==='surgeriesYes' && event.target.checked) {
            myInfo["donationDto"]["surgeryPast6Months"] = true;
            this.setState(myInfo);
        } else {
            myInfo["donationDto"]["recentTattoos"] = false;
            this.setState(myInfo);
        }
    }

    handleChangedPregnant(event) {
        let myInfo = this.state;
        if (event.target.id==='pregnantYes' && event.target.checked) {
            myInfo["donationDto"]["pregnantOrMenstruating"] = true;
            this.setState(myInfo);
        } else {
            myInfo["donationDto"]["recentTattoos"] = false;
            this.setState(myInfo);
        }
    }

    handleChangedCorrectInfo(event) {
        if (event.target.checked)
            this.setState({disabledSubmitButton: false});
        else
            this.setState({disabledSubmitButton: true});
    }

    handleSubmit(event) { //TODO check if all fields are inputted.
        event.preventDefault();
        let ok = true;
        for (let field in this.state) {
            if (this.state.hasOwnProperty(field) && this.state[field] === '' && !(field in this.optionals)) {
                ok = false;
            }
        }
        //if (ok) {
        if(true){
            let toSend = {
                username: this.state.username,
                donationDto:{
                    donation_id:this.state["donationDto"]["donation_id"],
                    status: "PENDING",
                    cancerPast5Years: this.state["donationDto"]["cancerPast5Years"],
                    bloodPressure: this.state["donationDto"]["bloodPressure"],
                    pulse: this.state["donationDto"]["pulse"],
                    weight: this.state["donationDto"]["weight"],
                    recentTattoos: this.state["donationDto"]["recentTattoos"],
                    pregnantOrMenstruating: this.state["donationDto"]["pregnantOrMenstruating"],
                    surgeryPast6Months: this.state["donationDto"]["surgeryPast6Months"],
                    donationBeneficiary: this.state["donationDto"]["donationBeneficiary"]
                }
            };
            this.submitDonation(toSend);
            let myState = this.state;
            myState["donationDto"]["status"] = 'PENDING';
            this.setState(myState);
        } else {
            alert("Please fill in all the mandatory fields!");     //TODO: remove all alerts!
        }
    }

    goToNewForm() {
        let myState = this.state;
        myState["donationDto"]["status"] = 'CLOSED';
        this.setState(myState);
        let toSend = {
            username: this.state.username,
            donationDto:{
                donation_id:this.state["donationDto"]["donation_id"],
                status: "CLOSED",
                cancerPast5Years: this.state["donationDto"]["cancerPast5Years"],
                bloodPressure: this.state["donationDto"]["bloodPressure"],
                pulse: this.state["donationDto"]["pulse"],
                weight: this.state["donationDto"]["weight"],
                recentTattoos: this.state["donationDto"]["recentTattoos"],
                pregnantOrMenstruating: this.state["donationDto"]["pregnantOrMenstruating"],
                surgeryPast6Months: this.state["donationDto"]["surgeryPast6Months"],
                donationBeneficiary: this.state["donationDto"]["donationBeneficiary"],
                rejectionReason: this.state["donationDto"]["rejectionReason"]
            }
        };
        this.updateDonation(toSend).then(() => {window.location.reload();});
    }

    render() {
        if (this.state["donationDto"]["status"] === 'CLOSED' || this.state["donationDto"]["status"] === ''){
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                        {text: "NEXT DONATION", reference: "/next-donation", extraClasses: 'active-navbar-link'},
                        {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: ''},
                        {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                    ]}/>

                    <div className="container">
                        <div className="row above-footer align-items-center justify-content-center">
                            <div className="col-11" style={{textAlign: 'center'}}>
                                <h1 className="questrial-font" style={{marginBottom: '90px', marginTop: '100px', fontSize: '40px'}}>Donation Form</h1>
                            </div>
                            <div className="col-11 col-sm-8 col-md-9 col-lg-7" style={{textAlign: 'center'}}>
                                {/* My Info Accordion */}
                                <div id="accordion">

                                    {/* General Information */}
                                    <div className="card">
                                        <div className="card-header donation-form-card" id="headingOne">
                                            <h5 className="mb-0">
                                                <button className="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                    *General Information
                                                </button>
                                            </h5>
                                        </div>
                                        <div id="collapseOne" className="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                                            <div className="card-body" style={{padding: '15px'}}>
                                                <Form>
                                                    <Input name="firstName" label="First name" type="text" placeholder={this.state["profileDto"]["firstName"]} readOnly="yes"/>
                                                    <Input name="lastName" label="Last name" type="text" placeholder={this.state["profileDto"]["lastName"]} readOnly="yes"/>
                                                    <Input name="dateOfBirth" label="Date of birth" type="text" placeholder={this.state["profileDto"]["dateOfBirth"]} readOnly="yes"/>
                                                    <Input name="gender" label="Gender" type="text" placeholder={this.state["profileDto"]["gender"]} readOnly="yes"/>
                                                    <Input name="cnp" label="CNP" type="text" placeholder={this.state["profileDto"]["cnp"]} readOnly="yes"/>
                                                    <Input name="bloodGroup" label="Blood group" type="text" placeholder={this.state["profileDto"]["bloodGroup"]} readOnly="yes"/>
                                                    <Input name="rh" label="Rh" type="text"  placeholder={this.state["profileDto"]["rh"]} readOnly="yes"/>
                                                    <Input name="email" label="Email (one address, please)" type="text" placeholder={this.state["profileDto"]["email"]} readOnly="yes"/>
                                                    <Input name="phone" label="Phone number" type="text" placeholder={this.state["profileDto"]["phone"]} readOnly="yes"/>
                                                    <br/>
                                                </Form>
                                            </div>
                                        </div>
                                    </div>

                                    {/* Address */}
                                    <div className="card">
                                        <div className="card-header donation-form-card" id="headingTwo">
                                            <h5 className="mb-0">
                                                <button className="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                    *Address
                                                </button>
                                            </h5>
                                        </div>
                                        <div id="collapseTwo" className="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                            <div className="card-body" style={{padding: '15px'}}>
                                                <Form>
                                                    <Input name="homeAddress" label="Permanent home address (from your ID)" type="text" placeholder={this.state["addressDto"]["homeAddress"]} readOnly="yes"/>
                                                    <Input name="city" label="City (from your ID)" type="text" placeholder={this.state["addressDto"]["city"]} readOnly="yes"/>
                                                    <Input name="country" label="Country (from your ID)" type="text" placeholder={this.state["addressDto"]["country"]} readOnly="yes"/>
                                                    <Input name="currentHomeAddress" label="Current address" type="text" placeholder={this.state["addressDto"]["currentHomeAddress"]} readOnly="yes"/>
                                                    <Input name="currentCity" label="Current city" type="text" placeholder={this.state["addressDto"]["currentCity"]} readOnly="yes"/>
                                                    <Input name="currentCountry" label="Current country" type="text" placeholder={this.state["addressDto"]["currentCountry"]} readOnly="yes"/>
                                                    <br/>
                                                </Form>
                                            </div>
                                        </div>
                                    </div>

                                    {/* General Health Survey */}
                                    <div className="card">
                                        <div className="card-header donation-form-card" id="headingThree">
                                            <h5 className="mb-0">
                                                <button className="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                    *General Health Survey
                                                </button>
                                            </h5>
                                        </div>
                                        <div id="collapseThree" className="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                            <div className="card-body" style={{padding: '15px'}}>
                                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '5px', marginBottom: '5px'}}>Do you suffer from severe allergies?</p>
                                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                                    <input className="form-check-input" type="radio" name="allergiesRadioOptions" id="allergiesYes" value="yes" ref="allergiesYes" disabled/>
                                                    <label className="form-check-label">Yes</label>
                                                </div>
                                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                                    <input className="form-check-input" type="radio" name="allergiesRadioOptions" id="allergiesNo" value="no" ref="allergiesNo" disabled/>
                                                    <label className="form-check-label">No</label>
                                                </div>

                                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>
                                                    Do you or have you ever suffered from any of the following diseases:
                                                    <br/>
                                                    hepatitis, tuberculosis, malaria, syphilis, pox, mental/neurological illness, brucellosis, ulcer, rheumatism, hypertension, heart disease,
                                                    kidney disease, endocrine affections, diabetes, epilepsy, skin disease (psoriasis, vitiligo), myopia over +/- 6 diopters?
                                                </p>
                                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                                    <input className="form-check-input" type="radio" name="diseasesRadioOptions" id="diseasesYes" value="yes" ref="diseasesYes" disabled/>
                                                    <label className="form-check-label">Yes</label>
                                                </div>
                                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                                    <input className="form-check-input" type="radio" name="diseasesRadioOptions" id="diseasesNo" ref="diseasesNo" value="no" disabled/>
                                                    <label className="form-check-label">No</label>
                                                </div>

                                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>Are you under chronic medical treatment (antibiotics, etc.)?</p>
                                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                                    <input className="form-check-input" type="radio" name="chronicRadioOptions" id="chronicYes" value="yes" ref="chronicYes" disabled/>
                                                    <label className="form-check-label">Yes</label>
                                                </div>
                                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                                    <input className="form-check-input" type="radio" name="chronicRadioOptions" id="chronicNo" value="no" ref="chronicNo" disabled/>
                                                    <label className="form-check-label">No</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <h6 style={{marginBottom: '50px', fontSize: '16px', fontWeight: 'lighter'}}>* editable only in the "My Information" section.</h6>

                                {/* Other Required Information */}
                                <h3 className="questrial-font" style={{fontWeight: '100', marginBottom: '20px', marginTop: '50px'}}>Other Required Information</h3>
                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '5px', marginBottom: '5px'}}>Have you suffered from cancer in the past 5 years?</p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="cancerRadioOptions" id="cancerYes" value="yes" ref="cancerYes" onChange={this.handleChangedCancer}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="cancerRadioOptions" id="cancerNo" value="no" ref="cancerNo" onChange={this.handleChangedCancer}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '5px', marginBottom: '5px'}}>Did you get any tattoos in the last 6 months?</p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="tattoosRadioOptions" id="tattoosYes" value="yes" ref="tattoosYes" onChange={this.handleChangedTattoos}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="tattoosRadioOptions" id="tattoosNo" value="no" ref="tattoosNo" onChange={this.handleChangedTattoos}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '5px', marginBottom: '5px'}}>Have you had any surgeries in the last 6 months?</p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="surgeriesRadioOptions" id="surgeriesYes" value="yes" ref="surgeriesYes" onChange={this.handleChangedSurgeries}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="surgeriesRadioOptions" id="surgeriesNo" value="no" ref="surgeriesNo" onChange={this.handleChangedSurgeries}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '5px', marginBottom: '5px'}}>Are you currently pregnant or menstruating? (If male, please select "no".)</p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="pregnantRadioOptions" id="pregnantYes" value="yes" ref="pregnantYes" onChange={this.handleChangedPregnant}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="pregnantRadioOptions" id="pregnantNo" value="no" ref="pregnantNo" onChange={this.handleChangedPregnant}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <Form handleSubmit={this.handleSubmit} onChange={this.handleChangedTextField}>
                                    <Input name="bloodPressure" label="Systolic blood pressure (optional)" type="text"/>
                                    <Input name="pulse" label="Pulse (optional)" type="text"/>
                                    <Input name="weight" label="Weight (in kg)" type="text"/>
                                    <Input name="donationBeneficiary" label="Are you donating for someone? If so, specify their name here." type="text"/>
                                </Form>

                                <div style={{marginTop: '90px', marginBottom: '100px'}}>
                                    <input type="checkbox" id="checkedCorrectInfo" ref="checkedCorrectInfo" onChange={this.handleChangedCorrectInfo}/>
                                    <label style={{fontWeight: 'lighter', marginLeft: '5px'}}>I have checked that the submitted information is correct.</label>
                                    <BlockButton onClick={this.handleSubmit} color="#ec0a0b" width="220px" fontFamily="Questrial" disabled={this.state.disabledSubmitButton}>SUBMIT DONATION FORM</BlockButton>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );

        } else if(this.state["donationDto"]["status"] === 'PENDING') {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                        {text: "NEXT DONATION", reference: "/next-donation", extraClasses: 'active-navbar-link'},
                        {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: ''},
                        {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                    ]}/>

                    <div className="container">
                        <div className="row align-items-center justify-content-center" style={{height: '90vh'}}>
                            <h3>Status: pending approval.</h3>
                        </div>
                    </div>
                </div>
            );
        } else if(this.state["donationDto"]["status"] === 'APPROVED') {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                        {text: "NEXT DONATION", reference: "/next-donation", extraClasses: 'active-navbar-link'},
                        {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: ''},
                        {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                    ]}/>

                    <div className="container" style={{height: '90vh'}}>
                        <div className="row align-items-center justify-content-center" style={{height: '90vh'}}>
                            <p style={{textAlign: 'center'}}>
                                <h3>Status: approved for donation.</h3>
                                <br/>
                                <h5>You will soon be contacted by a donation center for an appointment.</h5>
                                <br/>
                                <div style={{textAlign: 'left', fontSize: '18px', width: '85%', display: 'inline-block'}}>
                                    Before donating:<br/>
                                    <ul>
                                        <li>The morning before blood donation, you may drink a cup of tea or coffee and eat a light breakfast of fruits and vegetables.</li>
                                        <li>Do not smoke an hour before and after blood donation.</li>
                                        <li>Do not consume alcohol or fat 48 hours prior to blood donation.</li>
                                        <li>Arrive at the donation center well slept, and not after a night of working or staying up.</li>
                                    </ul>
                                </div>
                            </p>
                        </div>
                    </div>
                </div>
            );
        } else if (this.state["donationDto"]["status"] === 'REJECTED') {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                        {text: "NEXT DONATION", reference: "/next-donation", extraClasses: 'active-navbar-link'},
                        {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: ''},
                        {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                    ]}/>

                    <div className="container" style={{height: '90vh'}}>
                        <div className="row align-items-center justify-content-center" style={{height: '90vh'}}>
                            <p style={{textAlign: 'center'}}>
                                <h3>Status: rejected.</h3>
                                <br/>
                                <h5>Reason for rejection: {this.state["donationDto"]["rejectionReason"]}</h5>
                                <br/>
                                <Button color="#ec0a0b" width="280px" fontFamily="Questrial, sans-serif" onClick={this.goToNewForm}>SUBMIT NEW DONATION FORM</Button>
                            </p>
                        </div>
                    </div>
                </div>
            );
        }
    }
}

export default NextDonation;