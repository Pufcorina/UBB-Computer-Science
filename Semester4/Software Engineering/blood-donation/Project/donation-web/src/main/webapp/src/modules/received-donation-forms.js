import React from "react";
import ReactDOM from "react-dom";
import Navbar from "../components/navbar";
import ReactPaginate from 'react-paginate';
import ReceivedDonationsTableBody from '../components/received-donations-table-body';
const donationsApi = require('../api/donations-api');

class ReceivedDonationForms extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: localStorage.getItem("loggedInUser"),
            loadedForms: [],
            selectedForms: []
        };

        this.noFormsPerPage = 5;
        this.loadForms = donationsApi.getReceivedForms.bind(this);
        this.handlePageClick = this.handlePageClick.bind(this);
    }

    componentWillMount() {
        this.loadForms().then((res) => {
            console.log(res);
            //let forms = data.forms.default;       //TODO: change this to the next line of code, this one is for testing purposes
            let forms = res.data.forms;
            this.setState({loadedForms: forms, selectedForms: forms.slice(0, this.noFormsPerPage)});
        });
    }

    componentDidUpdate() {
        ReactDOM.findDOMNode(this).scrollIntoView();
    }

    handlePageClick(data) {
        let selectedPage = data.selected;
        let offset = selectedPage * this.noFormsPerPage;
        this.setState({selectedForms: this.state.loadedForms.slice(offset, offset + this.noFormsPerPage)});
    };

    render() {
        if (this.state.loadedForms.length === 0) {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "REQUESTS", reference: "/received-requests", extraClasses: ''},
                        {text: "DONORS", reference: "/received-donation-forms", extraClasses: 'active-navbar-link'},
                        {text: "STOCK", reference: "/our-blood-stock", extraClasses: ''},
                        {text: "CITY STOCKS", reference: "/city-blood-stocks", extraClasses: ''},
                        {text: "FIND DONOR", reference: "/find-donor", extraClasses: ''},
                        {text: "TESTS", reference: "/upload-test-results", extraClasses: ''}
                    ]}/>

                    {/* No Forms Section*/}
                    <div className="container">
                        <div className="row align-items-center justify-content-center" style={{minHeight: '88vh'}}>
                            <div className="col-11 col-sm-8 col-md-6 col-lg-4" style={{textAlign: 'center'}}>
                                <h1 style={{marginBottom: '40px', fontWeight: 'lighter', fontSize: '35px'}}>Currently there are no received donation forms.</h1>
                            </div>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "REQUESTS", reference: "/received-requests", extraClasses: ''},
                        {text: "DONORS", reference: "/received-donation-forms", extraClasses: 'active-navbar-link'},
                        {text: "STOCK", reference: "/our-blood-stock", extraClasses: ''},
                        {text: "CITY STOCKS", reference: "/city-blood-stocks", extraClasses: ''},
                        {text: "FIND DONOR", reference: "/find-donor", extraClasses: ''},
                        {text: "TESTS", reference: "/upload-test-results", extraClasses: ''}
                    ]}/>

                    {/* Received Forms Table */}
                    <div>
                        <div className="container" style={{padding: '0px 10px', marginTop: '40px', marginBottom: '60px'}}>
                            <div className="row">
                                <div className="col-12">
                                    <table className="forms-table">
                                        <ReceivedDonationsTableBody ref="rows" rows={this.state.selectedForms} history={this.props.history}/>
                                    </table>
                                </div>
                            </div>

                            {/* Pagination */}
                            <div className="row justify-content-end"
                                 style={{marginTop: '20px', marginBottom: '60px'}}>
                                <ReactPaginate previousLabel={<i className="fa fa-angle-left" aria-hidden="true"/>}
                                               nextLabel={<i className="fa fa-angle-right" aria-hidden="true"/>}
                                               breakLabel={'...'}
                                               breakClassName={"break-me"}
                                               pageCount={Math.ceil(this.state.loadedForms.length / this.noFormsPerPage)}
                                               onPageChange={this.handlePageClick}
                                               containerClassName={"pagination"}
                                               subContainerClassName={"pages pagination"}
                                               activeClassName={"active-page"}
                                               marginPagesDisplayed={1}
                                               pageRangeDisplayed={2}/>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}

export default ReceivedDonationForms;
