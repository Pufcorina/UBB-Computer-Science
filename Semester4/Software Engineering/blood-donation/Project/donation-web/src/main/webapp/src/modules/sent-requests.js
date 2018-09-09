import React from "react";
import ReactDOM from "react-dom";
import Navbar from "../components/navbar";
import Button from "../components/button";
import ReactPaginate from 'react-paginate';
import SentRequestsTableBody from '../components/sent-requests-table-body';
const requestsApi = require('../api/requests-api');

class SentRequests extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: localStorage.getItem("loggedInUser"),
            loadedRequests: [],
            selectedRequests: []
        };

        this.noFormsPerPage = 10;
        this.loadForms = requestsApi.getSentRequests.bind(this);
        this.handlePageClick = this.handlePageClick.bind(this);
    }

    componentWillMount() {
        this.loadForms().then((res) => {
            //let requests = data.requests.default;       //TODO: change this to the next line of code, this one is for testing purposes
            let requests = res.data.requests;
            console.log(res);
            this.setState({loadedRequests: requests, selectedRequests: requests.slice(0, this.noFormsPerPage)});
            console.log(this.state);
        });
    }

    componentDidUpdate() {
        ReactDOM.findDOMNode(this).scrollIntoView();
    }

    handlePageClick(data) {
        let selectedPage = data.selected;
        let offset = selectedPage * this.noFormsPerPage;
        this.setState({selectedRequests: this.state.loadedRequest.slice(offset, offset + this.noFormsPerPage)});
    };

    render() {
        if (this.state.loadedRequests.length === 0) {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "NEW REQUEST", reference: "/new-request", extraClasses: ''},
                        {text: "MY REQUESTS", reference: "/sent-requests", extraClasses: 'active-navbar-link'},
                        {text: "PACIENTS IN NEED", reference: "/pacient-blood-need", extraClasses: ''},
                        {text: "CITY BLOOD STOCKS", reference: "/city-blood-stocks", extraClasses: ''}
                    ]}/>

                    {/* No Requests Section*/}
                    <div className="container">
                        <div className="row align-items-center justify-content-center" style={{minHeight: '88vh'}}>
                            <div className="col-11 col-sm-8 col-md-6 col-lg-4" style={{textAlign: 'center'}}>
                                <h1 style={{marginBottom: '40px', fontWeight: 'lighter', fontSize: '35px'}}>Currently there are no sent requests.</h1>
                                <Button onClick={this.redirectToSendRequest} color="#ec0a0b" width="240px" fontFamily="Questrial, sans-serif">
                                    <a href="/new-request" className="clean-link">SEND YOUR FIRST REQUEST</a>
                                </Button>
                            </div>
                        </div>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <Navbar notLoggedIn={false} extraLinks={[
                        {text: "NEW REQUEST", reference: "/new-request", extraClasses: ''},
                        {text: "MY REQUESTS", reference: "/sent-requests", extraClasses: 'active-navbar-link'},
                        {text: "PACIENTS IN NEED", reference: "/pacient-blood-need", extraClasses: ''},
                        {text: "CITY BLOOD STOCKS", reference: "/city-blood-stocks", extraClasses: ''}
                    ]}/>

                    {/* Sent Requests Table */}
                    <div>
                        <div className="container" style={{padding: '0px 10px', marginTop: '60px', marginBottom: '60px'}}>
                            <div className="row">
                                <div className="col-12">
                                    <table className="requests-table">
                                        <thead>
                                        <tr>
                                            <th>
                                                <h5>
                                                    Request Details
                                                </h5>
                                            </th>
                                            <th style={{textAlign: 'center'}}><h5>Urgency Level</h5></th>
                                            <th><h5>Status</h5></th>
                                        </tr>
                                        </thead>
                                        <SentRequestsTableBody ref="rows" rows={this.state.selectedRequests}/>
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
                                               pageCount={Math.ceil(this.state.loadedRequests.length / this.noFormsPerPage)}
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

export default SentRequests;
