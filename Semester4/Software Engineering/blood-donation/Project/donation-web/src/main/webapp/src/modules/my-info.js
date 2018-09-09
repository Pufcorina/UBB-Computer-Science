import React from "react";
import BlockButton from "../components/block-button";
import Navbar from "../components/navbar";
import Form from "../components/form";
import Input from "../components/input";
const usersApi = require('../api/users-api');

class MyInfo extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            profileDto: {
                firstName: '',
                lastName: '',
                dateOfBirth: '',
                gender: '',
                cnp: '',
                bloodGroup: '',
                rh: '',
                email: '',
                phone: '',
                allergies: '',
                diseases: '',
                chronicIllness: ''
            },
            addressDto: {
                homeAddress: '',      //din buletin
                city: '',             //din buletin
                country: '',          //din buletin
                currentHomeAddress: '',
                currentCity: '',
                currentCountry: ''
            },
            username: localStorage.getItem("loggedInUser")
        };

        this.handleChangedProfileTextField = this.handleChangedProfileTextField.bind(this);
        this.handleChangedAddressTextField = this.handleChangedAddressTextField.bind(this);
        this.handleChangedAllergies = this.handleChangedAllergies.bind(this);
        this.handleChangedDiseases = this.handleChangedDiseases.bind(this);
        this.handleChangedChronic = this.handleChangedChronic.bind(this);
        this.handleChangedBloodGroup = this.handleChangedBloodGroup.bind(this);
        this.handleChangedRh = this.handleChangedRh.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.submitMyInfo = usersApi.submitMyInfo.bind(this);
        this.prefillFields = usersApi.getMyInfo.bind(this);
    }

    componentWillMount() {

        this.prefillFields().then((res) => {
            if(!res.data.isError){
            this.setState(res.data, function() {
                let myInfo = this.state;

                if (this.state["profileDto"]["allergies"] === 'yes') {
                    this.refs.allergiesYes.checked = true;
                } else {
                    this.refs.allergiesNo.checked = true;
                    myInfo['profileDto']["allergies"] = 'no'
                }

                if (this.state["profileDto"]["diseases"] === 'yes') {
                    this.refs.diseasesYes.checked = true;
                } else
                {
                    this.refs.diseasesNo.checked = true;
                    myInfo['profileDto']["diseases"] = 'no'
                }

                if (this.state["profileDto"]["chronicIllness"] === 'yes') {
                    this.refs.chronicYes.checked = true;
                } else {
                    this.refs.chronicNo.checked = true;
                    myInfo['profileDto']["chronicIllness"] = 'no'
                }
                if (this.state["profileDto"]["rh"] === '+') {
                    this.refs.rhPositive.checked= true;
                } else {
                    this.refs.rhNegative.checked = true;
                    myInfo['profileDto']["rh"] = '-'
                }
                if (this.state["profileDto"]["bloodGroup"] === '0') {
                    this.refs.groupZero.checked= true;
                }

                if (this.state["profileDto"]["bloodGroup"] === 'A') {
                    this.refs.groupA.checked= true;
                }

                if (this.state["profileDto"]["bloodGroup"] === 'B') {
                    this.refs.groupB.checked= true;
                }
                if (this.state["profileDto"]["bloodGroup"] === 'AB') {
                    this.refs.groupAB.checked= true;
                }
                this.setState(myInfo,function() {
                    console.log(this.state);
                });
            });
        }});
    }

    handleChangedProfileTextField(event) {
        let field = event.target.name;
        let value = event.target.value;
        let myInfo = this.state;
        myInfo['profileDto'][field]=value;
        console.log(myInfo);
        this.setState(myInfo, function() {
            console.log(this.state);
        });

    }

    handleChangedAddressTextField(event) {
        let field = event.target.name;
        let value = event.target.value;
        let myInfo = this.state;
        myInfo['addressDto'][field]=value;
        this.setState(myInfo);
    }

    handleChangedAllergies(event) {
        if (event.target.id==='allergiesYes' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['allergies']='yes';
            this.setState(myInfo);
        } else {
            let myInfo = this.state;
            myInfo['profileDto']['allergies']='no';
            this.setState(myInfo);
        }
    }

    handleChangedDiseases(event) {
        if (event.target.id==='diseasesYes' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['diseases']='yes';
            this.setState(myInfo);
        } else {
            let myInfo = this.state;
            myInfo['profileDto']['diseases']='no';
            this.setState(myInfo);
        }
    }

    handleChangedChronic(event) {
        if (event.target.id==='chronicYes' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['chronicIllness']='yes';
            this.setState(myInfo);
        } else {
            let myInfo = this.state;
            myInfo['profileDto']['chronicIllness']='no';
            this.setState(myInfo);
        }
    }

    handleChangedBloodGroup(event) {
        if (event.target.id==='groupZero' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['bloodGroup']='0';
            this.setState(myInfo);
        } else if (event.target.id==='groupA' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['bloodGroup']='A';
            this.setState(myInfo);
        } else if (event.target.id==='groupB' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['bloodGroup']='B';
            this.setState(myInfo);
        } else if (event.target.id==='groupAB' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['bloodGroup']='AB';
            this.setState(myInfo);
        }
    }

    handleChangedRh(event) {
        if (event.target.id==='rhPositive' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['rh']='+';
            this.setState(myInfo);
        } else if (event.target.id==='rhNegative' && event.target.checked) {
            let myInfo = this.state;
            myInfo['profileDto']['rh']='-';
            this.setState(myInfo);
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        let ok = true;
        for (let field in this.state) {
            if (this.state.hasOwnProperty(field) && this.state[field] === '') {
                ok = false;
            }
        }
        if (ok) {
            this.submitMyInfo(this.state);
            window.location.reload();
        } else {
            alert("All fields are mandatory!");     //TODO: remove all alerts!
        }
    }

    render() {
        return (
            <div>
                <Navbar notLoggedIn={false} extraLinks={[
                    {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                    {text: "NEXT DONATION", reference: "/next-donation", extraClasses: ''},
                    {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: ''},
                    {text: "MY INFORMATION", reference: "/my-info", extraClasses: 'active-navbar-link'}
                ]}/>

                {/* Add Personal Information Form */}
                <div className="container">
                    <div className="row above-footer align-items-center justify-content-center">
                        <div className="col-11" style={{textAlign: 'center'}}>
                            <h1 className="questrial-font" style={{marginBottom: '90px', marginTop: '100px', fontSize: '40px'}}>Edit Personal Information</h1>
                        </div>
                        <div className="col-11 col-sm-8 col-md-9 col-lg-7" style={{textAlign: 'center'}}>
                            <Form handleSubmit={this.handleSubmit} onChange={this.handleChangedProfileTextField}>
                                <h3 className="questrial-font" style={{fontWeight: '100', marginBottom: '20px'}}>General Information</h3>
                                <Input name="firstName" label="First name" type="text" placeholder={this.state['profileDto']['firstName']}/>
                                <Input name="lastName" label="Last name" type="text" placeholder={this.state.profileDto.lastName}/>
                                <Input name="dateOfBirth" label="Date of birth (dd-mm-yyyy)" type="text" placeholder={this.state.profileDto['dateOfBirth']} ref="dob"/>
                                <Input name="gender" label="Gender" type="text" placeholder={this.state.profileDto.gender}/>
                                <Input name="cnp" label="CNP" type="text" placeholder={this.state.profileDto.cnp}/>
                                <Input name="email" label="Email (one address, please)" type="text" placeholder={this.state.profileDto.email}/>
                                <Input name="phone" label="Phone number" type="text" placeholder={this.state.profileDto.phone}/>
                            </Form>

                            <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>Blood group</p>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                <input className="form-check-input" type="radio" name="bloodGroupOptions" id="groupZero" value="zero" ref="groupZero" onChange={this.handleChangedBloodGroup}/>
                                <label className="form-check-label">0(I)</label>
                            </div>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px', fontWeight: 'lighter'}}>
                                <input className="form-check-input" type="radio" name="bloodGroupOptions" id="groupA" value="A" ref="groupA" onChange={this.handleChangedBloodGroup}/>
                                <label className="form-check-label">A(II)</label>
                            </div>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px', fontWeight: 'lighter'}}>
                                <input className="form-check-input" type="radio" name="bloodGroupOptions" id="groupB" value="B" ref="groupB" onChange={this.handleChangedBloodGroup}/>
                                <label className="form-check-label">B(III)</label>
                            </div>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px', fontWeight: 'lighter'}}>
                                <input className="form-check-input" type="radio" name="bloodGroupOptions" id="groupAB" value="AB" ref="groupAB" onChange={this.handleChangedBloodGroup}/>
                                <label className="form-check-label">AB(IV)</label>
                            </div>

                            <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>Rh</p>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                <input className="form-check-input" type="radio" name="rhOptions" id="rhPositive" value="positive" ref="rhPositive" onChange={this.handleChangedRh}/>
                                <label className="form-check-label">Positive (+)</label>
                            </div>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px', fontWeight: 'lighter'}}>
                                <input className="form-check-input" type="radio" name="rhOptions" id="rhNegative" value="negative" ref="rhNegative" onChange={this.handleChangedRh}/>
                                <label className="form-check-label">Negative (-)</label>
                            </div>

                            <Form handleSubmit={this.handleSubmit} onChange={this.handleChangedAddressTextField}>
                                <h3 className="questrial-font" style={{fontWeight: '100', marginBottom: '20px', marginTop: '50px'}}>Address</h3>
                                <Input name="homeAddress" label="Permanent home address (from your ID)" type="text" placeholder={this.state.addressDto.homeAddress}/>
                                <Input name="city" label="City (from your ID)" type="text" placeholder={this.state.addressDto.city}/>
                                <Input name="country" label="Country (from your ID)" type="text" placeholder={this.state.addressDto.country}/>
                                <Input name="currentHomeAddress" label="Current address" type="text" placeholder={this.state.addressDto.currentHomeAddress}/>
                                <Input name="currentCity" label="Current city" type="text" placeholder={this.state.addressDto.currentCity}/>
                                <Input name="currentCountry" label="Current country" type="text" placeholder={this.state.addressDto.currentCountry}/>
                            </Form>

                                <h3 className="questrial-font" style={{fontWeight: '100', marginBottom: '20px', marginTop: '50px'}}>General Health Survey</h3>

                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '5px', marginBottom: '5px'}}>Do you suffer from severe allergies?</p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="allergiesRadioOptions" id="allergiesYes" value="yes" ref="allergiesYes" onChange={this.handleChangedAllergies}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="allergiesRadioOptions" id="allergiesNo" value="no" ref="allergiesNo" onChange={this.handleChangedAllergies}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>
                                    Do you or have you ever suffered from any of the following diseases:
                                    <br/>
                                    hepatitis, tuberculosis, malaria, syphilis, pox, mental/neurological illness, brucellosis, ulcer, rheumatism, hypertension, heart disease,
                                    kidney disease, endocrine affections, diabetes, epilepsy, skin disease (psoriasis, vitiligo), myopia over +/- 6 diopters?
                                </p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="diseasesRadioOptions" id="diseasesYes" value="yes" ref="diseasesYes" onChange={this.handleChangedDiseases}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="diseasesRadioOptions" id="diseasesNo" ref="diseasesNo" value="no" onChange={this.handleChangedDiseases}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>Are you under chronic medical treatment (antibiotics, etc.)?</p>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                    <input className="form-check-input" type="radio" name="chronicRadioOptions" id="chronicYes" value="yes" ref="chronicYes" onChange={this.handleChangedChronic}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                    <input className="form-check-input" type="radio" name="chronicRadioOptions" id="chronicNo" value="no" ref="chronicNo" onChange={this.handleChangedChronic}/>
                                    <label className="form-check-label">No</label>
                                </div>

                            <div style={{marginTop: '90px', marginBottom: '100px'}}>
                                <BlockButton onClick={this.handleSubmit} color="#ec0a0b" width="220px" fontFamily="Questrial">SAVE CHANGES</BlockButton>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default MyInfo;