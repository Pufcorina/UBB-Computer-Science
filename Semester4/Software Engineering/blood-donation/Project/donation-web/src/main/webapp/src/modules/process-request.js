import React from "react";
import Navbar from "../components/navbar";
import Box from "../components/box";
import Button from "../components/button";
const requestsApi = require("../api/requests-api");

class ProcessRequest extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            request: {
                id: '',
                activeDonor: '',
                urgencyLevel: '',
                locationHospital: '',
                bloodGroup: '',
                rh: '',
                thrombocyteUnits: '',
                redCellsUnits: '',
                plasmaUnits: ''
            }
        };

        this.markAsProcessed = this.markAsProcessed.bind(this);
    }

    componentWillMount() {
        const item = localStorage.getItem("requestBeingProcessed");
        this.setState({request: JSON.parse(item)});
    }

    markAsProcessed() {
        requestsApi.markRequestAsProcessed(this.state.request.id)
            .then(() => {
                this.props.history.push("/received-requests");
            });
        localStorage.removeItem("requestBeingProcessed");
    }

    render() {
        return (
            <div>
                <Navbar notLoggedIn={false} extraLinks={[
                    {text: "REQUESTS", reference: "/received-requests", extraClasses: ''},
                    {text: "DONORS", reference: "/received-donation-forms", extraClasses: ''},
                    {text: "STOCK", reference: "/our-blood-stock", extraClasses: ''},
                    {text: "CITY STOCKS", reference: "/city-blood-stocks", extraClasses: ''},
                    {text: "FIND DONOR", reference: "/find-donor", extraClasses: ''},
                    {text: "TESTS", reference: "/upload-test-results", extraClasses: ''}
                ]}/>

                {/* Request Info */}
                <div className="container">
                    <div className="row align-text-center justify-content-center" style={{paddingTop: '60px', paddingBottom: '55px'}}>
                        <div className="col-11">
                            <p style={{fontSize: '25px', fontWeight: 'lighter', textAlign: 'center'}}>You are about to process the following request:</p>
                        </div>
                    </div>
                    <div className="row align-items-center justify-content-center questrial-font">
                        <div className="col-8">
                            <div className="row align-items-center justify-content-center">
                                <p>
                                    <h5 style={{fontWeight: 'lighter', fontSize: '20px'}}>Request ID: {this.state.request.id}</h5>
                                    <h5 style={{color: '#ec0a0b'}}>For: {this.state.request.beneficiaryName}, active donor - {this.state.request.activeDonor}</h5>
                                    <h5 style={{color: '#ec0a0b'}}>Urgency level: {this.state.request.urgencyLevel}</h5>
                                    <h5 style={{fontWeight: 'lighter', fontSize: '20px'}}>Needed at: {this.state.request.locationHospital}</h5>
                                    <h6 style={{fontWeight: 'lighter'}}>Blood group: {this.state.request.bloodGroup}</h6>
                                    <h6 style={{fontWeight: 'lighter'}}>Rh: {this.state.request.rh}</h6>
                                    <h6 style={{fontWeight: 'lighter'}}>Thrombocyte units: {this.state.request.thrombocyteUnits}</h6>
                                    <h6 style={{fontWeight: 'lighter'}}>Red blood cell units: {this.state.request.redCellsUnits}</h6>
                                    <h6 style={{fontWeight: 'lighter'}}>Plasma units: {this.state.request.plasmaUnits}</h6>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Boxes */}
                <div className="container">
                    <div className="row align-items-center" style={{minHeight: '40vh', paddingTop: '40px', paddingBottom: '40px'}}>
                        <Box image='/images/blood_bags.jpg' title='Step 1. Diminish the Stock'>
                            Open <a href="/our-blood-stock" target="_blank">"Stock" in a new tab</a> and remove the blood container(-s).
                            <br/>
                            Not enough blood? Find the closest compatible donor on the <a href="/find-donor">"Find donor" page</a> and process this request at a later time.
                        </Box>

                        <Box image='/images/send_blood.jpg' title='Step 2. Send the Blood'>
                            Physically send the containers to the hospital.
                            <p className="filler-paragraph-for-box2"/>
                        </Box>

                        <Box image='/images/done.jpg' title='Step 3. Let Me Know When Done'>
                            Mark request as processed using the button below.
                            <br/>
                            <br/>
                            <Button onClick={this.markAsProcessed} color="#ec0a0b" width="200px" fontFamily="Questrial, sans-serif">MARK AS PROCESSED</Button>
                            <p className="filler-paragraph-for-box3"/>
                        </Box>
                    </div>
                </div>
            </div>
        );
    }
}

export default ProcessRequest;
