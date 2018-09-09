import React from "react";
import Navbar from "../components/navbar";
import Button from "../components/button";
import MatchingDonors from '../components/matching-donors';
const donationsApi = require("../api/donations-api");

class UploadTestResults extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            city: '',
            bloodGroup: '',
            rh: '',
            loadedDonors : []
        };

        this.handleChangedBloodGroup = this.handleChangedBloodGroup.bind(this);
        this.handleChangedRh = this.handleChangedRh.bind(this);
        this.findDonors = this.findDonors.bind(this);
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

    findDonors() {
        donationsApi.findDonors.bind(this)(localStorage.getItem("loggedInCenterId"), this.state.bloodGroup, this.state.rh)
            .then((res) => {
                this.setState({loadedDonors: res.data.donors});
            })
    }

    render() {
        return (
            <div>
                <Navbar notLoggedIn={false} extraLinks={[
                    {text: "REQUESTS", reference: "/received-requests", extraClasses: ''},
                    {text: "DONORS", reference: "/received-donation-forms", extraClasses: ''},
                    {text: "STOCK", reference: "/our-blood-stock", extraClasses: ''},
                    {text: "CITY STOCKS", reference: "/city-blood-stocks", extraClasses: ''},
                    {text: "FIND DONOR", reference: "/find-donor", extraClasses: 'active-navbar-link'},
                    {text: "TESTS", reference: "/upload-test-results", extraClasses: ''}
                ]}/>

                <div className="container">
                    <div className="row align-text-center justify-content-center" style={{paddingTop: '60px'}}>
                        <div className="col-11 col-sm-8 col-md-9 col-lg-7">
                            <p style={{fontSize: '25px', fontWeight: 'lighter', textAlign: 'center', marginBottom: '50px'}}>FIND MATCHING DONORS NEARBY</p>

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
                        </div>
                    </div>

                    <div className="row align-text-center justify-content-center" style={{marginTop: '30px', marginBottom: '60px'}}>
                        <Button onClick={this.findDonors} color="#ec0a0b" width="200px" fontFamily="Questrial">FIND DONORS</Button>
                    </div>

                    <MatchingDonors donors={this.state.loadedDonors}/>
                </div>
            </div>
        );
    }
}

export default UploadTestResults;
