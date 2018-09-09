import React from 'react';
import Button from "../components/button";
import {printBool} from "../utils/bools";
import {printStandardDate} from "../utils/dates";

class TestResultsTableBody extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            rows: props.rows
        };
        this.downloadPdf = this.downloadPdf.bind(this);
    }

    componentWillReceiveProps(newProps) {
        this.setState({rows: newProps.rows});
    }

    downloadPdf(result) {
        window.open(result.result_pdf ,'_blank');  //TODO: asta va fi linkul de cloudinary
        //window.open('https://res.cloudinary.com/bloodyfast/image/upload/v1528584706/Lab_1_en_ambebw.pdf' ,'_blank');
    }

    render() {
        return (
            <tbody>
            {this.state.rows.map((result, index) =>
                <tr key={result.id}>
                    <td>
                        <div>
                            <h5 style={{color: '#ec0a0b'}}>Donation date: {printStandardDate(result.appointmentDate)}</h5>
                            <h5 style={{fontWeight: 'lighter', fontSize: '20px'}}>Illness discovered: {printBool(result.illnessDiscovered)}</h5>
                            <h6 style={{fontWeight: 'lighter'}}>{result.illnessInfo}</h6>       {/* TODO: illnessInfo should start with 'We are sorry to inform you that you might suffer from ... */}
                        </div>
                    </td>
                    <td>
                        <Button onClick={this.downloadPdf.bind(this, result)} color="#ec0a0b" width="160px" fontFamily="Questrial, sans-serif">
                            DOWNLOAD PDF
                        </Button>
                    </td>
                </tr>
            )}
            </tbody>
        );
    }
}

export default TestResultsTableBody;