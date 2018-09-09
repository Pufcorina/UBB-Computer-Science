import React from 'react';

class ImagelessBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title : props.title
        };
    }

    componentWillReceiveProps(newProps) {
        this.setState({title: newProps.title});
    }

    render() {
        return (
            <div className="col-12 col-md-4 bordered-box" style={{padding: '10px', textAlign: 'center', display:'inline-block'}}>
                <div className="row align-items-center justify-content-center" style={{minHeight: '100px'}}>
                    <div>
                        <h5 style={{marginBottom: '20px', fontWeight: 'lighter'}}>{this.state.title}</h5>
                        <p style={{fontWeight: 'lighter', fontSize: 'larger', fontFamily: 'Questrial, sans-serif'}}>
                            {this.props.children}
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}

export default ImagelessBox;
