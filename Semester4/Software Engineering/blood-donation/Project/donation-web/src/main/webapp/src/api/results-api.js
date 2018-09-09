const fooTestResults = require("../foo-data/foo-test-results");
const axios = require('axios');

function getResults() {
    return axios.get('/results/' + this.state.username);
    // return new Promise((resolve, reject) => {
    //     resolve({results: fooTestResults, noOfDonations: 18});                           //TODO: remove these 3 lines, sample data for front-end testing purposes
    // });
}

module.exports = {getResults};