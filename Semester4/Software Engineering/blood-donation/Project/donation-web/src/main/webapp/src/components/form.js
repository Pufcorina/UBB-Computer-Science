import React from 'react';

/*
  Form Component
*/
class Form extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = props.handleSubmit;
    this.onChange = props.onChange;
  }

  render() {
    return (
      <form onSubmit = {this.handleSubmit} onChange={this.onChange}>
        {this.props.children}
      </form>
    );
  }
}

export default Form;
