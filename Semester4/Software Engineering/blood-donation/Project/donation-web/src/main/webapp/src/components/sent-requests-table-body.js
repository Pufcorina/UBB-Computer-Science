import React from 'react';

class SentRequestsTableBody extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            rows: props.rows
        };
    }

    componentWillReceiveProps(newProps) {
        this.setState({rows: newProps.rows});
    }

    render() {
        return (
            <tbody>
                {this.state.rows.map((request, index) =>
                    <tr key={index}>
                        <td>
                            <div>
                                <h5 style={{color: '#ec0a0b'}}>For: {request.beneficiaryName}</h5>
                                <h5 style={{fontWeight: 'lighter', fontSize: '20px'}}>Sent to: center {request["center"]["name"]}</h5>
                                <h6 style={{fontWeight: 'lighter'}}>Thrombocyte units: {request.thrombocyteUnits}</h6>
                                <h6 style={{fontWeight: 'lighter'}}>Red blood cell units: {request.redCellsUnits}</h6>
                                <h6 style={{fontWeight: 'lighter'}}>Plasma units: {request.plasmaUnits}</h6>
                            </div>
                        </td>
                        <td style={{fontWeight: 'lighter', textAlign: 'center'}}>{request.urgencyLevel}</td>
                        <td>{request.status}</td>
                    </tr>
                )}
            </tbody>
        );
    }
}

export default SentRequestsTableBody;