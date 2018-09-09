import React from 'react';

class DonorBox extends React.Component {
    constructor(props) {
        super(props);
        this.title = props.title;
    }

    render() {
        return (
            <div className="col-12 col-md-4 bordered-box" style={{padding: '0px 25px', textAlign: 'center', display:'inline-block'}}>
                <h4 style={{marginTop: '20px', marginBottom: '15px'}}>
                    <u style={{textDecoration: 'none', borderBottom: '1px solid black'}}>{this.title}</u>
                </h4>
                <p style={{fontWeight: 'lighter', fontSize: '17px', marginTop: '20px'}}>
                    {this.props.children}
                </p>
            </div>
        );
    }
}

export default DonorBox;
