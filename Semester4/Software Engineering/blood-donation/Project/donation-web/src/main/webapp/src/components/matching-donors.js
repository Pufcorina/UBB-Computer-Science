import React from 'react';
import ImagelessBox from './imageless-box';

class MatchingDonors extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            donors: props.donors
        };}

    componentWillReceiveProps(newProps) {
        this.setState({donors: newProps.donors});
    }

    render() {
        if (this.state.donors.length === 0)
            return (<div/>);
        else
            if(this.state.donors.length === 3)
                return (
                    <div className="col-12" style={{marginTop: '40px'}}>
                        <ImagelessBox title={this.state.donors[0].firstName + " " + this.state.donors[0].lastName}>
                            {this.state.donors[0].phone}
                            <br/>
                            {this.state.donors[0].email}
                            <br/>
                            {this.state.donors[0].currentHomeAddress}
                            <br/>
                            {this.state.donors[0].currentCity}
                            <br/>
                            Located at: {this.state.donors[0].distance} km
                        </ImagelessBox>

                        <ImagelessBox title={this.state.donors[1].firstName + " " + this.state.donors[1].lastName}>
                            {this.state.donors[1].phone}
                            <br/>
                            {this.state.donors[1].email}
                            <br/>
                            {this.state.donors[1].currentHomeAddress}
                            <br/>
                            {this.state.donors[1].currentCity}
                            <br/>
                            Located at: {this.state.donors[1].distance} km
                        </ImagelessBox>

                        <ImagelessBox title={this.state.donors[2].firstName + " " + this.state.donors[2].lastName}>
                            {this.state.donors[2].phone}
                            <br/>
                            {this.state.donors[2].email}
                            <br/>
                            {this.state.donors[2].currentHomeAddress}
                            <br/>
                            {this.state.donors[2].currentCity}
                            <br/>
                            Located at: {this.state.donors[2].distance} km
                        </ImagelessBox>

                        <br/>
                        <br/>
                    </div>
                );
        else
            if(this.state.donors.length === 2)
                return (
                    <div className="col-12" style={{marginTop: '40px'}}>
                        <ImagelessBox title={this.state.donors[0].firstName + " " + this.state.donors[0].lastName}>
                            {this.state.donors[0].phone}
                            <br/>
                            {this.state.donors[0].email}
                            <br/>
                            {this.state.donors[0].currentHomeAddress}
                            <br/>
                            {this.state.donors[0].currentCity}
                            <br/>
                            Located at: {this.state.donors[0].distance} km
                        </ImagelessBox>

                        <ImagelessBox title={this.state.donors[1].firstName + " " + this.state.donors[1].lastName}>
                            {this.state.donors[1].phone}
                            <br/>
                            {this.state.donors[1].email}
                            <br/>
                            {this.state.donors[1].currentHomeAddress}
                            <br/>
                            {this.state.donors[1].currentCity}
                            <br/>
                            Located at: {this.state.donors[1].distance} km
                        </ImagelessBox>

                        <br/>
                        <br/>
                    </div>
                );
        else
                return (
                    <div className="col-12" style={{marginTop: '40px'}}>
                        <ImagelessBox title={this.state.donors[0].firstName + " " + this.state.donors[0].lastName}>
                            {this.state.donors[0].phone}
                            <br/>
                            {this.state.donors[0].email}
                            <br/>
                            {this.state.donors[0].currentHomeAddress}
                            <br/>
                            {this.state.donors[0].currentCity}
                            <br/>
                            Located at: {this.state.donors[0].distance} km
                        </ImagelessBox>

                        <br/>
                        <br/>
                    </div>
                );
    }
}

export default MatchingDonors;