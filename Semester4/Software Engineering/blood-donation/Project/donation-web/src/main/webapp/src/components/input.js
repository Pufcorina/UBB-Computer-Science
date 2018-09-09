import React from 'react';

/*
  Input Component
*/
class Input extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        name: props.name || '',
        label: props.label,
        type: props.type,
        placeholder: props.placeholder,
        readOnly: props.readOnly,
        onChange: props.onChange,
        value: props.placeholder
    }
  }

  componentWillReceiveProps(newProps){
      this.setState({placeholder:newProps.placeholder});
  }


  render() {
    if (this.state.readOnly==='yes') {
      return (
        <div style={{textAlign: 'left', fontWeight: 'lighter', marginTop: '20px'}}>
          <label style={{paddingTop: '5px', fontSize: '18px'}}>{this.state.label}</label>
          <input name={this.state.name} className="form-control" type="text" placeholder={this.state.placeholder} readOnly/>
        </div>
      )
    } else {
        return (
            <div style={{textAlign: 'left', fontWeight: 'lighter', marginTop: '20px'}}>
              <label style={{paddingTop: '5px', fontSize: '18px'}}>{this.state.label}</label>
              <input name={this.state.name} className="form-control" type={this.state.type} placeholder={this.state.placeholder} onChange={this.state.onChange}/>
            </div>
        );
    }
  }
}

export default Input;
