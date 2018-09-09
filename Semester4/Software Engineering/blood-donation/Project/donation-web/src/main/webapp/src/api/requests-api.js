const fooSentRequests = require("../foo-data/foo-sent-requests");
const fooReceivedRequests = require("../foo-data/foo-received-requests");
const axios = require('axios');

function submitRequest(request) {
    return axios.post('/requests', request)
        .then((response) => {
            if (response.data.isError) {
                alert(response.data.message);       //TODO: get rid of alerts!
            }
        })
        .catch(function(err) {
            alert(err);
        });
}

function getSentRequests() {
    return axios.get('/requests/' + this.state.username);
    // return new Promise((resolve, reject) => {
    //     resolve({requests: fooSentRequests});                           //TODO: remove these 3 lines, sample data for front-end testing purposes
    // });
}

function getReceivedRequests() {
    return axios.get('/received-requests/' + this.state.username);
    // return new Promise((resolve, reject) => {
    //     resolve({requests: fooReceivedRequests});                           //TODO: remove these 3 lines, sample data for front-end testing purposes
    // });
}

function markRequestAsProcessed(_requestID) {
    return axios.put('/requests', {requestID: _requestID, status: 'PROCESSED'});
}

module.exports = {submitRequest, getSentRequests, getReceivedRequests, markRequestAsProcessed};