import React from 'react';

class Box extends React.Component {
    constructor(props) {
        super(props);
        this.image = props.image;
        this.title = props.title;
    }

    render() {
        return (
            <div className="col-12 col-md-4 bordered-box" style={{padding: '50px 25px', textAlign: 'center', display:'inline-block'}}>
                <img src={this.image} alt={this.title + ' image'} style={{width:'100%'}} className="box-img rounded-borders" />
                <h5 style={{marginTop: '20px', marginBottom: '15px'}}>{this.title}</h5>
                <p style={{fontWeight: 'lighter', fontSize: '17px'}}>
                    {this.props.children}
                </p>
            </div>
        );
    }
}

export default Box;
