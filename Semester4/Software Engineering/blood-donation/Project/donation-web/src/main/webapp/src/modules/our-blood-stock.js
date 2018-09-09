import React from "react";
import Navbar from "../components/navbar";
import Button from "../components/button";
import BlockButton from "../components/block-button";
import ImagelessBox from "../components/imageless-box";
import Form from "../components/form";
import Input from "../components/input";
const stockApi = require("../api/stock-api");

class OurStock extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            zeroPositiveUsable: 0,
            zeroPositiveExpired: 0,
            zeroNegativeUsable: 0,
            zeroNegativeExpired: 0,

            apositiveUsable: 0,
            apositiveExpired: 0,
            anegativeUsable: 0,
            anegativeExpired: 0,

            bpositiveUsable: 0,
            bpositiveExpired: 0,
            bnegativeUsable: 0,
            bnegativeExpired: 0,

            abpositiveUsable: 0,
            abpositiveExpired: 0,
            abnegativeUsable: 0,
            abnegativeExpired: 0,

            thrombocyteUsable: 0,
            thrombocyteExpired: 0,

            plasmaUsable: 0,
            plasmaExpired: 0
        };

        this.loadStockInfo = stockApi.getStockOfCenter.bind(this);
        this.diminishStocks = stockApi.diminishStock.bind(this);
        this.addToStock = this.addToStock.bind(this);
        this.removeExpired = stockApi.removeAllExpired.bind(this);
        this.replenishStock = stockApi.replenishStock.bind(this);
        this.removeAllExpired = this.removeAllExpired.bind(this);
        this.handleChangedBloodGroup = this.handleChangedBloodGroup.bind(this);
        this.handleChangedRh = this.handleChangedRh.bind(this);
        this.handleChangedComponent = this.handleChangedComponent.bind(this);
        this.handleChangedTextField = this.handleChangedTextField.bind(this);
        this.removeFromStock = this.removeFromStock.bind(this);
    }

    componentWillMount() {
        this.loadStockInfo(localStorage.loggedInCenterId)
            .then((stock) => {
                console.log(stock);
                this.setState({
                    zeroPositiveUsable: stock.data.zeroPositiveUsable,
                    zeroPositiveExpired: stock.data.zeroPositiveExpired,
                    zeroNegativeUsable: stock.data.zeroNegativeUsable,
                    zeroNegativeExpired: stock.data.zeroNegativeExpired,

                    apositiveUsable: stock.data.apositiveUsable,
                    apositiveExpired: stock.data.apositiveExpired,
                    anegativeUsable: stock.data.anegativeUsable,
                    anegativeExpired: stock.data.anegativeExpired,

                    bpositiveUsable: stock.data.bpositiveUsable,
                    bpositiveExpired: stock.data.bpositiveExpired,
                    bnegativeUsable: stock.data.bnegativeUsable,
                    bnegativeExpired: stock.data.bnegativeExpired,

                    abpositiveUsable: stock.data.abpositiveUsable,
                    abpositiveExpired: stock.data.abpositiveExpired,
                    abnegativeUsable: stock.data.abnegativeUsable,
                    abnegativeExpired: stock.data.abnegativeExpired,

                    thrombocyteUsable: stock.data.thrombocyteUsable,
                    thrombocyteExpired: stock.data.thrombocyteExpired,

                    plasmaUsable: stock.data.plasmaUsable,
                    plasmaExpired: stock.data.plasmaExpired,

                    bloodGroup: '',
                    rh: '',
                    componentType: '',
                    howManyToRemove: 0,

                    containerExpiryDate: ''
                });
                console.log("state=");
                console.log(this.state);
            });
    }

    removeAllExpired() {
        this.removeExpired().then(() => {window.location.reload();});
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

    handleChangedComponent(event) {
        if (event.target.id==='redCells' && event.target.checked) {
            let myInfo = this.state;
            myInfo['componentType']='redCells';
            this.setState(myInfo);
        } else if (event.target.id==='thrombocytes' && event.target.checked) {
            let myInfo = this.state;
            myInfo['componentType']='thrombocytes';
            this.setState(myInfo);
        } else if (event.target.id==='plasma' && event.target.checked) {
            let myInfo = this.state;
            myInfo['componentType']='plasma';
            this.setState(myInfo);
        }
    }

    handleChangedTextField(event) {
        let field = event.target.name;
        let value = event.target.value;
        let myInfo = this.state;
        myInfo[field]=value;
        this.setState(myInfo);
    }

    removeFromStock(event) { //TODO check if all fields are inputted.
        event.preventDefault();
        let toSend = {
            containerDto:
                {
                bloodGroup: this.state.bloodGroup,
                rh: this.state.rh,
                componentType: this.state.componentType
            },
            howManyToRemove: this.state.howManyToRemove
        };
        this.diminishStocks(toSend).then(() => {window.location.reload();});
    }

    addToStock(event) { //TODO check if all fields are inputted.
        event.preventDefault();
        let toSend = {
            containerDto:{
                bloodGroup: this.state.bloodGroup,
                rh: this.state.rh,
                componentType: this.state.componentType,
                expirationDate: this.state.containerExpiryDate
            },
        };
        this.replenishStock(toSend).then(() => {window.location.reload();});
    }

    render() {
        return (
            <div>
                <Navbar notLoggedIn={false} extraLinks={[
                    {text: "REQUESTS", reference: "/received-requests", extraClasses: ''},
                    {text: "DONORS", reference: "/received-donation-forms", extraClasses: ''},
                    {text: "STOCK", reference: "/our-blood-stock", extraClasses: 'active-navbar-link'},
                    {text: "CITY STOCKS", reference: "/city-blood-stocks", extraClasses: ''},
                    {text: "FIND DONOR", reference: "/find-donor", extraClasses: ''},
                    {text: "TESTS", reference: "/upload-test-results", extraClasses: ''}
                ]}/>

                {/* View Stock */}
                <div className="container" style={{paddingTop: '60px', paddingBottom: '20px'}}>
                    <div className="col-12">
                        <h4>OUR BLOOD STOCK</h4>
                        <br/>
                        <br/>
                        <h5 style={{fontWeight: 'lighter', textAlign: 'center'}}>RED BLOOD CELL CONTAINERS</h5>
                        <br/>
                    </div>

                    {/* RED BLOOD CELLS TABLE */}
                    <div className="table-responsive">
                        <table className="table table-hover stock-table">
                            <thead>
                                <tr>
                                    <th scope="col">Blood Group /<br/>Rh</th>
                                    <th scope="col">0 (I)</th>
                                    <th scope="col">A (II)</th>
                                    <th scope="col">B (III)</th>
                                    <th scope="col">AB(IV)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row" style={{textAlign: 'center'}}>+</th>
                                    <td>
                                        {this.state.zeroPositiveUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.zeroPositiveExpired} expired</span>
                                    </td>
                                    <td>
                                        {this.state.apositiveUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.apositiveExpired} expired</span>
                                    </td>
                                    <td>
                                        {this.state.bpositiveUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.bpositiveExpired} expired</span>
                                    </td>
                                    <td>
                                        {this.state.abpositiveUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.abpositiveExpired} expired</span>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" style={{textAlign: 'center'}}>-</th>
                                    <td>
                                        {this.state.zeroNegativeUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.zeroNegativeExpired} expired</span>
                                    </td>
                                    <td>
                                        {this.state.anegativeUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.anegativeExpired} expired</span>
                                    </td>
                                    <td>
                                        {this.state.bnegativeUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.bnegativeExpired} expired</span>
                                    </td>
                                    <td>
                                        {this.state.abnegativeUsable} usable
                                        <br/>
                                        <span style={{color: '#ec0a0b'}}>{this.state.abnegativeExpired} expired</span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    {/* THR. & PLASMA */}
                    <div className="col-12" style={{marginTop: '40px'}}>
                        <ImagelessBox title='THROMBOCYTE CONTAINERS'>
                            {this.state.thrombocyteUsable} usable
                            <br/>
                            <span style={{color: '#ec0a0b'}}>{this.state.thrombocyteExpired} expired</span>
                        </ImagelessBox>

                        <ImagelessBox title='PLASMA CONTAINERS'>
                            {this.state.plasmaUsable} usable
                            <br/>
                            <span style={{color: '#ec0a0b'}}>{this.state.plasmaExpired} expired</span>
                        </ImagelessBox>

                        <ImagelessBox title="Recommended:">
                            <Button onClick={this.removeAllExpired} color="#ec0a0b" width="200px" fontFamily="Questrial, sans-serif">REMOVE ALL EXPIRED</Button>
                        </ImagelessBox>
                    </div>
                </div>

                <hr/>

                {/* Remove from Stock */}
                <div className="container" style={{paddingTop: '40px', paddingBottom: '20px'}}>
                    <div className="col-12">
                        <h4>REMOVE FROM STOCK</h4>
                        <p style={{fontWeight: 'lighter'}}>* Need more blood? Access the "Find Donor" page!</p>
                        <br/>
                        <br/>

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

                        {/* Component Type */}
                        <p style={{
                            fontWeight: 'lighter',
                            fontSize: '18px',
                            textAlign: 'left',
                            paddingTop: '25px',
                            marginBottom: '5px'
                        }}>Component type</p>
                        <div className="form-check"
                             style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                            <input className="form-check-input" type="radio" name="componentOptions" id="thrombocytes"
                                   value="thrombocytes" ref="thrombocytes" onChange={this.handleChangedComponent}/>
                            <label className="form-check-label">Thrombocytes</label>
                        </div>
                        <div className="form-check" style={{
                            textAlign: 'left',
                            marginLeft: '20px',
                            marginTop: '-7px',
                            fontWeight: 'lighter'
                        }}>
                            <input className="form-check-input" type="radio" name="componentOptions" id="redCells"
                                   value="redCells" ref="redCells" onChange={this.handleChangedComponent}/>
                            <label className="form-check-label">Red blood cells</label>
                        </div>
                        <div className="form-check" style={{
                            textAlign: 'left',
                            marginLeft: '20px',
                            marginTop: '-7px',
                            fontWeight: 'lighter'
                        }}>
                            <input className="form-check-input" type="radio" name="componentOptions" id="plasma"
                                   value="plasma" ref="plasma" onChange={this.handleChangedComponent}/>
                            <label className="form-check-label">Plasma</label>
                        </div>

                        <Form handleSubmit={this.removeFromStock} onChange={this.handleChangedTextField}>
                            <Input name="howManyToRemove" label="How many units/containers should be removed?" type="text"/>
                            <br/>
                            <br/>
                            <BlockButton onClick={this.removeFromStock} color="#ec0a0b" width="220px" fontFamily="Questrial">REMOVE FROM STOCK</BlockButton>
                        </Form>

                    </div>
                </div>

                <hr/>

                {/* Replenish Stock */}
                <div className="container" style={{paddingTop: '40px', paddingBottom: '20px'}}>
                    <div className="col-12">
                        <h4>REPLENISH STOCK</h4>
                        <br/>
                        <br/>

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

                        {/* Component Type */}
                        <p style={{
                            fontWeight: 'lighter',
                            fontSize: '18px',
                            textAlign: 'left',
                            paddingTop: '25px',
                            marginBottom: '5px'
                        }}>Component type</p>
                        <div className="form-check"
                             style={{textAlign: 'left', marginLeft: '20px', fontWeight: 'lighter'}}>
                            <input className="form-check-input" type="radio" name="componentOptions" id="thrombocytes"
                                   value="thrombocytes" ref="thrombocytes" onChange={this.handleChangedComponent}/>
                            <label className="form-check-label">Thrombocytes</label>
                        </div>
                        <div className="form-check" style={{
                            textAlign: 'left',
                            marginLeft: '20px',
                            marginTop: '-7px',
                            fontWeight: 'lighter'
                        }}>
                            <input className="form-check-input" type="radio" name="componentOptions" id="redCells"
                                   value="redCells" ref="redCells" onChange={this.handleChangedComponent}/>
                            <label className="form-check-label">Red blood cells</label>
                        </div>
                        <div className="form-check" style={{
                            textAlign: 'left',
                            marginLeft: '20px',
                            marginTop: '-7px',
                            fontWeight: 'lighter'
                        }}>
                            <input className="form-check-input" type="radio" name="componentOptions" id="plasma"
                                   value="plasma" ref="plasma" onChange={this.handleChangedComponent}/>
                            <label className="form-check-label">Plasma</label>
                        </div>

                        <Form handleSubmit={this.addToStock} onChange={this.handleChangedTextField}>
                            <Input name="containerExpiryDate" label="Expiry date for the container (dd-mm-yyyy)" type="text"/>
                            <br/>
                            <br/>
                            <BlockButton onClick={this.addToStock} color="#ec0a0b" width="220px" fontFamily="Questrial">ADD TO STOCK</BlockButton>
                        </Form>

                    </div>
                </div>
            </div>
        );
    }
}

export default OurStock;
