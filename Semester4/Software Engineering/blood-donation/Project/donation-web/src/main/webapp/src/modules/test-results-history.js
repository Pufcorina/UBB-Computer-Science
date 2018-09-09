import React from "react";
import ReactDOM from "react-dom";
import Navbar from "../components/navbar";
import ReactPaginate from 'react-paginate';
import TestResultsTableBody from '../components/test-results-table-body';
const resultsApi = require('../api/results-api');

class TestResultsHistory extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: localStorage.getItem("loggedInUser"),
            loadedResults: [],
            selectedResults: [],
            noOfDonations: 0
        };

        this.noResultsPerPage = 10;
        this.loadResults = resultsApi.getResults.bind(this);
        this.handlePageClick = this.handlePageClick.bind(this);
    }

    componentWillMount() {
        this.loadResults().then((data) => {
            //let results = data.results.default;       //TODO: change this to the next line of code, this one is for testing purposes
            let results = data.data.results;
            this.setState({loadedResults: results, selectedResults: results.slice(0, this.noResultsPerPage), noOfDonations: results.length});
        });
    }

    componentDidUpdate() {
        ReactDOM.findDOMNode(this).scrollIntoView();
    }

    handlePageClick(data) {
        let selectedPage = data.selected;
        let offset = selectedPage * this.noResultsPerPage;
        this.setState({selectedResults: this.state.loadedResults.slice(offset, offset + this.noResultsPerPage)});
    };

    render() {
        if (this.state.loadedResults.length === 0) {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                        {text: "NEXT DONATION", reference: "/next-donation", extraClasses: ''},
                        {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: 'active-navbar-link'},
                        {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                    ]}/>

                    {/* No Results Section*/}
                    <div className="container">
                        <div className="row align-items-center justify-content-center" style={{minHeight: '88vh'}}>
                            <div className="col-11 col-sm-8 col-md-6 col-lg-4" style={{textAlign: 'center'}}>
                                <h1 style={{marginBottom: '40px', fontWeight: 'lighter', fontSize: '35px'}}>Currently you have no blood test results.</h1>
                            </div>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "DASHBOARD", reference: "/user-dashboard", extraClasses: ''},
                        {text: "NEXT DONATION", reference: "/next-donation", extraClasses: ''},
                        {text: "TEST RESULTS", reference: "/test-results-history", extraClasses: 'active-navbar-link'},
                        {text: "MY INFORMATION", reference: "/my-info", extraClasses: ''}
                    ]}/>

                    <div className="container">
                        <div className="row align-text-center justify-content-center" style={{paddingTop: '60px'}}>
                            <div className="col-11">
                                <p style={{fontSize: '25px', fontWeight: 'lighter', textAlign: 'center'}}>
                                    Thank you for your contribution!
                                    <br/>
                                    You have donated {this.state.noOfDonations} times so far. Keep it up, people need your help!
                                </p>
                            </div>
                        </div>
                    </div>

                    {/* Received Results Table */}
                    <div>
                        <div className="container" style={{padding: '0px 10px', marginTop: '60px', marginBottom: '60px'}}>
                            <div className="row">
                                <div className="col-12">
                                    <table className="requests-table">
                                        <thead>
                                        <tr>
                                            <th>
                                                <h5>
                                                    Test Results History
                                                </h5>
                                            </th>
                                            <th/>
                                        </tr>
                                        </thead>
                                        <TestResultsTableBody ref="rows" rows={this.state.selectedResults} history={this.props.history}/>
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
                                               pageCount={Math.ceil(this.state.loadedResults.length / this.noResultsPerPage)}
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

export default TestResultsHistory;
