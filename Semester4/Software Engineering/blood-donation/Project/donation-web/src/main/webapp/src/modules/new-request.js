import React from "react";
import BlockButton from "../components/block-button";
import Navbar from "../components/navbar";
import Form from "../components/form";
import Input from "../components/input";
import Button from "../components/button";
const requestsApi = require('../api/requests-api');

class NewRequest extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            thrombocyteUnits: '',
            redCellsUnits: '',
            plasmaUnits: '',
            donationCenterId: '',
            locationHospital: '',
            beneficiaryName: '',
            activeDonor: '',
            urgencyLevel: '',
            bloodGroup: '',
            rh: '',
            username: localStorage.getItem("loggedInUser"),

            disabledSubmitButton: true     //ignore this in the back-end
        };

        this.handleChangedTextField = this.handleChangedTextField.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangedCorrectInfo = this.handleChangedCorrectInfo.bind(this);
        this.handleChangedBloodGroup = this.handleChangedBloodGroup.bind(this);
        this.handleChangedRh = this.handleChangedRh.bind(this);
        this.handleChangedUrgency = this.handleChangedUrgency.bind(this);
        this.handleChangedActiveDonor = this.handleChangedActiveDonor.bind(this);
        this.submitRequest = requestsApi.submitRequest.bind(this);
    }

    reloadPage () {
        window.location.reload();
    }

    handleChangedTextField(event) {
        let field = event.target.name;
        let value = event.target.value;
        let myInfo = this.state;
        myInfo[field]=value;
        this.setState(myInfo);
    }

    handleChangedActiveDonor(event) {
        if (event.target.id==='activeDonorYes' && event.target.checked) {
            this.setState({activeDonor: true});
        } else {
            this.setState({activeDonor: false});
        }
    }

    handleChangedUrgency(event) {
        console.log(event);
        if (event.target.id==='urgencyHigh' && event.target.checked) {
            this.setState({urgencyLevel: 'high'});
        } else if (event.target.id==='urgencyMedium' && event.target.checked) {
            this.setState({urgencyLevel: 'medium'});
        } else if (event.target.id==='urgencyLow' && event.target.checked) {
            this.setState({urgencyLevel: 'low'});
        }
    }

    handleChangedCorrectInfo(event) {
        if (event.target.checked)
            this.setState({disabledSubmitButton: false});
        else
            this.setState({disabledSubmitButton: true});
    }

    handleChangedBloodGroup(event) {
        if (event.target.id==='groupZero' && event.target.checked) {
            let myInfo = this.state;
            myInfo['bloodGroup']='0';
            this.setState(myInfo);
        } else if (event.target.id==='groupA' && event.target.checked) {
            let myInfo = this.state;
            myInfo['bloodGroup']='A';
            this.setState(myInfo);
        } else if (event.target.id==='groupB' && event.target.checked) {
            let myInfo = this.state;
            myInfo['bloodGroup']='B';
            this.setState(myInfo);
        } else if (event.target.id==='groupAB' && event.target.checked) {
            let myInfo = this.state;
            myInfo['bloodGroup']='AB';
            this.setState(myInfo);
        }
    }

    handleChangedRh(event) {
        if (event.target.id==='rhPositive' && event.target.checked) {
            let myInfo = this.state;
            myInfo['rh']='+';
            this.setState(myInfo);
        } else if (event.target.id==='rhNegative' && event.target.checked) {
            let myInfo = this.state;
            myInfo['rh']='-';
            this.setState(myInfo);
        }
    }

    handleSubmit(event) {
        console.log(this.state);
        event.preventDefault();
        let ok = true;
        for (let field in this.state) {
            if (this.state.hasOwnProperty(field) && this.state[field] === '') {
                ok = false;
            }
        }
        if (ok) {
            let toSend = {
                thrombocyteUnits: this.state.thrombocyteUnits,
                redCellsUnits: this.state.redCellsUnits,
                plasmaUnits: this.state.plasmaUnits,
                donationCenterId: this.state.donationCenterId,
                locationHospital: this.state.locationHospital,
                beneficiaryName: this.state.beneficiaryName,
                activeDonor: this.state.activeDonor,
                urgencyLevel: this.state.urgencyLevel,
                bloodGroup: this.state.bloodGroup,
                rh: this.state.rh,
                username: localStorage.getItem("loggedInUser"),
                status:"PENDING"
            };
            this.submitRequest(toSend);
            this.setState({status:"PENDING"});
        } else {
            alert("Please fill in all the mandatory fields!");     //TODO: remove all alerts!
        }
    }

    render() {
        if(this.state.status ==="PENDING"){
            return(
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "NEW REQUEST", reference: "/new-request", extraClasses: 'active-navbar-link'},
                        {text: "MY REQUESTS", reference: "/sent-requests", extraClasses: ''},
                        {text: "PACIENTS IN NEED", reference: "/pacient-blood-need", extraClasses: ''},
                        {text: "CITY BLOOD STOCKS", reference: "/city-blood-stocks", extraClasses: ''}
                    ]}/>
                    <div className="container" style={{height: '90vh'}}>
                        <div className="row align-items-center justify-content-center" style={{height: '90vh'}}>
                            <p style={{textAlign: 'center'}}>
                                <h3>Status: pending.</h3>
                                <br/>
                                <h5>Your request was sent to the desired center.</h5>
                                <br/>
                                <h5>Would you like to submit another request?</h5>
                                <br/>
                                <Button color="#ec0a0b" width="280px" fontFamily="Questrial, sans-serif" onClick={() => this.reloadPage()}>SUBMIT NEW REQUEST</Button>
                            </p>
                        </div>
                    </div>
                </div>
            );
        }
        else {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "NEW REQUEST", reference: "/new-request", extraClasses: 'active-navbar-link'},
                        {text: "MY REQUESTS", reference: "/sent-requests", extraClasses: ''},
                        {text: "PACIENTS IN NEED", reference: "/pacient-blood-need", extraClasses: ''},
                        {text: "CITY BLOOD STOCKS", reference: "/city-blood-stocks", extraClasses: ''}
                    ]}/>

                    <div className="container">
                        <div className="row above-footer align-items-center justify-content-center">
                            <div className="col-11" style={{textAlign: 'center'}}>
                                <h1 className="questrial-font"
                                    style={{marginBottom: '90px', marginTop: '100px', fontSize: '40px'}}>New Request
                                    Form</h1>
                            </div>
                            <div className="col-11 col-sm-8 col-md-9 col-lg-7" style={{textAlign: 'center'}}>

                                {/* Blood Group */}
                                <p style={{
                                    fontWeight: 'lighter',
                                    fontSize: '18px',
                                    textAlign: 'left',
                                    marginBottom: '5px'
                                }}>Blood group</p>
                                <div className="form-check"
                                     style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                    <input className="form-check-input" type="radio" name="bloodGroupOptions"
                                           id="groupZero" value="zero" ref="groupZero"
                                           onChange={this.handleChangedBloodGroup}/>
                                    <label className="form-check-label">0(I)</label>
                                </div>
                                <div className="form-check" style={{
                                    textAlign: 'left',
                                    marginLeft: '20px',
                                    marginTop: '-7px',
                                    fontWeight: 'lighter'
                                }}>
                                    <input className="form-check-input" type="radio" name="bloodGroupOptions"
                                           id="groupA" value="A" ref="groupA" onChange={this.handleChangedBloodGroup}/>
                                    <label className="form-check-label">A(II)</label>
                                </div>
                                <div className="form-check" style={{
                                    textAlign: 'left',
                                    marginLeft: '20px',
                                    marginTop: '-7px',
                                    fontWeight: 'lighter'
                                }}>
                                    <input className="form-check-input" type="radio" name="bloodGroupOptions"
                                           id="groupB" value="B" ref="groupB" onChange={this.handleChangedBloodGroup}/>
                                    <label className="form-check-label">B(III)</label>
                                </div>
                                <div className="form-check" style={{
                                    textAlign: 'left',
                                    marginLeft: '20px',
                                    marginTop: '-7px',
                                    fontWeight: 'lighter'
                                }}>
                                    <input className="form-check-input" type="radio" name="bloodGroupOptions"
                                           id="groupAB" value="AB" ref="groupAB"
                                           onChange={this.handleChangedBloodGroup}/>
                                    <label className="form-check-label">AB(IV)</label>
                                </div>

                                {/* Rh */}
                                <p style={{
                                    fontWeight: 'lighter',
                                    fontSize: '18px',
                                    textAlign: 'left',
                                    paddingTop: '25px',
                                    marginBottom: '5px'
                                }}>Rh</p>
                                <div className="form-check"
                                     style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                    <input className="form-check-input" type="radio" name="rhOptions" id="rhPositive"
                                           value="positive" ref="rhPositive" onChange={this.handleChangedRh}/>
                                    <label className="form-check-label">Positive (+)</label>
                                </div>
                                <div className="form-check" style={{
                                    textAlign: 'left',
                                    marginLeft: '20px',
                                    marginTop: '-7px',
                                    fontWeight: 'lighter'
                                }}>
                                    <input className="form-check-input" type="radio" name="rhOptions" id="rhNegative"
                                           value="negative" ref="rhNegative" onChange={this.handleChangedRh}/>
                                    <label className="form-check-label">Negative (-)</label>
                                </div>

                                {/* Units Needed & Other Info */}
                                <Form handleSubmit={this.handleSubmit} onChange={this.handleChangedTextField}>
                                    <Input name="thrombocyteUnits" label="No. of thrombocyte units needed" type="text"/>
                                    <Input name="redCellsUnits" label="No. of red cell units needed" type="text"/>
                                    <Input name="plasmaUnits" label="No. of plasma units needed" type="text"/>
                                    <Input name="donationCenterId"
                                           label='ID of the donation center which will receive the request (available at "City Blood Stocks")'
                                           type="text"/>
                                    <Input name="locationHospital" label="Address of requesting hospital" type="text"/>
                                    <Input name="beneficiaryName" label="Beneficiary full name" type="text"/>
                                </Form>

                                {/* Urgency level */}
                                <p style={{
                                    fontWeight: 'lighter',
                                    fontSize: '18px',
                                    textAlign: 'left',
                                    paddingTop: '25px',
                                    marginBottom: '5px'
                                }}>Urgency level</p>
                                <div className="form-check"
                                     style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                    <input className="form-check-input" type="radio" name="urgencyOptions"
                                           id="urgencyHigh" value="high" ref="urgencyHigh"
                                           onChange={this.handleChangedUrgency}/>
                                    <label className="form-check-label">High</label>
                                </div>
                                <div className="form-check"
                                     style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                    <input className="form-check-input" type="radio" name="urgencyOptions"
                                           id="urgencyMedium" value="medium" ref="urgencyMedium"
                                           onChange={this.handleChangedUrgency}/>
                                    <label className="form-check-label">Medium</label>
                                </div>
                                <div className="form-check"
                                     style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                    <input className="form-check-input" type="radio" name="urgencyOptions"
                                           id="urgencyLow" value="low" ref="urgencyLow"
                                           onChange={this.handleChangedUrgency}/>
                                    <label className="form-check-label">Low</label>
                                </div>

                                {/* Active Donor */}
                                <p style={{
                                    fontWeight: 'lighter',
                                    fontSize: '18px',
                                    textAlign: 'left',
                                    paddingTop: '15px',
                                    marginBottom: '5px'
                                }}>Is the beneficiary an active donor or not?</p>
                                <div className="form-check"
                                     style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                                    <input className="form-check-input" type="radio" name="activeDonorRadioOptions"
                                           id="activeDonorYes" value="yes" ref="activeDonorYes"
                                           onChange={this.handleChangedActiveDonor}/>
                                    <label className="form-check-label">Yes</label>
                                </div>
                                <div className="form-check" style={{
                                    textAlign: 'left',
                                    marginLeft: '20px',
                                    marginTop: '-7px',
                                    fontWeight: 'lighter'
                                }}>
                                    <input className="form-check-input" type="radio" name="activeDonorRadioOptions"
                                           id="activeDonorNo" value="no" ref="activeDonorNo"
                                           onChange={this.handleChangedActiveDonor}/>
                                    <label className="form-check-label">No</label>
                                </div>

                                <div style={{marginTop: '90px', marginBottom: '100px'}}>
                                    <input type="checkbox" id="checkedCorrectInfo" ref="checkedCorrectInfo"
                                           onChange={this.handleChangedCorrectInfo}/>
                                    <label style={{fontWeight: 'lighter', marginLeft: '5px'}}>I have checked that the
                                        submitted information is correct.</label>
                                    <BlockButton onClick={this.handleSubmit} color="#ec0a0b" width="220px"
                                                 fontFamily="Questrial" disabled={this.state.disabledSubmitButton}>SEND
                                        REQUEST</BlockButton>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}

export default NewRequest;