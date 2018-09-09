import React from 'react';
import Button from "../components/button";
import {printBool} from "../utils/bools";

class ReceivedRequestsTableBody extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            rows: props.rows
        };
        this.processRequest = this.processRequest.bind(this);
    }

    componentWillReceiveProps(newProps) {
        this.setState({rows: newProps.rows});
    }

    processRequest(request) {
        localStorage.setItem("requestBeingProcessed", JSON.stringify(request));
        this.props.history.push('/process-request');
    }

    render() {
        return (
            <tbody>
            {this.state.rows.map((request, index) =>
                <tr key={request.id}>
                    <td>
                        <div>
                            <h5 style={{color: '#ec0a0b'}}>For: {request.beneficiaryName}, active donor - {printBool(request.activeDonor)}</h5>
                            <h5 style={{fontWeight: 'lighter', fontSize: '20px'}}>Needed at: {request.locationHospital}</h5>
                            <h6 style={{fontWeight: 'lighter'}}>Blood group: {request.bloodGroup}</h6>
                            <h6 style={{fontWeight: 'lighter'}}>Rh: {request.rh}</h6>
                            <h6 style={{fontWeight: 'lighter'}}>Thrombocyte units: {request.thrombocyteUnits}</h6>
                            <h6 style={{fontWeight: 'lighter'}}>Red blood cell units: {request.redCellsUnits}</h6>
                            <h6 style={{fontWeight: 'lighter'}}>Plasma units: {request.plasmaUnits}</h6>
                        </div>
                    </td>
                    <td style={{fontWeight: 'lighter', textAlign: 'center'}}>{request.urgencyLevel}</td>
                    <td>
                        <Button onClick={this.processRequest.bind(this, request)} color="#ec0a0b" width="110px" fontFamily="Questrial, sans-serif">
                            PROCESS
                        </Button>
                    </td>
                </tr>
            )}
            </tbody>
        );
    }
}

export default ReceivedRequestsTableBody;