import React from "react";
import Navbar from "../components/navbar";
import Button from "../components/button";
import Dropzone from 'react-dropzone';
import Form from '../components/form';
import Input from '../components/input';
const uploadsApi = require("../api/uploads-api");

class UploadTestResults extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            uploadedFileUrl: '',
            cnp: '',
            illnessDiscovered: '',
            illnessInfo: ''
        };

        this.sendTestResults = this.sendTestResults.bind(this);
        this.handleDrop = uploadsApi.uploadToCloudinary.bind(this);
        this.handleChangedIllness = this.handleChangedIllness.bind(this);
        this.handleChangedTextField = this.handleChangedTextField.bind(this);
    }

    sendTestResults() {
        uploadsApi.sendTestResults(this.state).then(() => {window.location.reload();});
    }

    handleChangedTextField(event) {
        let field = event.target.name;
        let value = event.target.value;
        let myInfo = this.state;
        myInfo[field]=value;
        this.setState(myInfo);
    }

    handleChangedIllness(event) {
        let myInfo = this.state;
        if (event.target.id==='illnessYes' && event.target.checked) {
            myInfo["illnessDiscovered"] = true;
            this.setState(myInfo);
        } else {
            myInfo["illnessDiscovered"] = false;
            this.setState(myInfo);
        }
        console.log(this.state);
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
                    {text: "TESTS", reference: "/upload-test-results", extraClasses: 'active-navbar-link'}
                ]}/>

                {/* Request Info */}
                <div className="container">
                    <div className="row align-text-center justify-content-center" style={{paddingTop: '60px', paddingBottom: '55px'}}>
                        <div className="col-11">
                            <p style={{fontSize: '25px', fontWeight: 'lighter', textAlign: 'center'}}>UPLOAD TEST RESULTS</p>
                        </div>
                    </div>
                    <div className="row align-items-center justify-content-center questrial-font">
                        <div className="col-sm-11 col-md-8">
                            <Dropzone
                                onDrop={this.handleDrop}
                                multiple={false}
                                accept="application/pdf"
                                style={{backgroundColor: '#dbd5d4', height: '300px', width: '100%', borderRadius: '10%'}}
                            >
                                <div className="row align-items-center justify-content-center" id="drop-files-div">
                                    <div className="col-sm-11 col-md-8">
                                        <p style={{textAlign: 'center'}}>Drop your file or click here to upload</p>
                                    </div>
                                </div>
                            </Dropzone>
                        </div>
                    </div>
                </div>

                <div className="container">
                    <div className="row align-items-center justify-content-center" style={{paddingTop: '40px'}}>
                        <div className="col-11 col-sm-8 col-md-9 col-lg-7" style={{textAlign: 'center'}}>
                            <Form onChange={this.handleChangedTextField}>
                                <Input name="cnp" label="CNP" type="text"/>
                            </Form>
                            <p style={{fontWeight: 'lighter', fontSize: '18px', textAlign: 'left', paddingTop: '25px', marginBottom: '5px'}}>Have any illnesses been discovered?</p>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px'}}>
                                <input className="form-check-input" type="radio" name="illnessOptions" id="illnessYes" value="yes" ref="illnessYes" onChange={this.handleChangedIllness}/>
                                <label className="form-check-label">Yes</label>
                            </div>
                            <div className="form-check" style={{textAlign: 'left', marginLeft: '20px', marginTop: '-7px'}}>
                                <input className="form-check-input" type="radio" name="illnessOptions" id="illnessNo" value="no" ref="illnessNo" onChange={this.handleChangedIllness}/>
                                <label className="form-check-label">No</label>
                            </div>
                            <Form onChange={this.handleChangedTextField}>
                                <Input name="illnessInfo" label="Illness discovered (if applicable)" type="text"/>
                            </Form>
                            <div style={{marginTop: '30px', marginBottom: '60px'}}>
                                <Button onClick={this.sendTestResults} color="#ec0a0b" width="220px" fontFamily="Questrial">SEND RESULTS</Button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UploadTestResults;
