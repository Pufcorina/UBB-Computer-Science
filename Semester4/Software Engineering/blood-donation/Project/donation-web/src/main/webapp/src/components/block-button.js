import React from 'react';

/*
  BlockButton Component
*/
class BlockButton extends React.Component {
  constructor(props) {
    super(props);
    this.onClick = props.onClick;
    this.color = props.color;
    this.fontFamily = props.fontFamily;
    this.state = {disabled: props.disabled};
  }

  componentWillReceiveProps(newProps) {
    this.setState({disabled: newProps.disabled});
  }

  render() {
    if (this.state.disabled)
      return (
          <button className="btn btn-primary btn-block darken-on-hover"
                  disabled
                  onClick={this.onClick}
                  style={{backgroundColor: `${this.color}`, borderColor: `${this.color}`, fontFamily: `${this.fontFamily}`, marginBottom: '20px'}}>
                  {this.props.children}
          </button>
        );
    else
        return (
            <button className="btn btn-primary btn-block darken-on-hover"
                    onClick={this.onClick}
                    style={{backgroundColor: `${this.color}`, borderColor: `${this.color}`, fontFamily: `${this.fontFamily}`, marginBottom: '20px', cursor: 'pointer'}}>
                {this.props.children}
            </button>
        );
  }
}

export default BlockButton;
