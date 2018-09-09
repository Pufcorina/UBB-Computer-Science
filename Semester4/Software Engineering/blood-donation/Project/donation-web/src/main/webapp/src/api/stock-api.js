const axios = require('axios');

function getStockOfCenter() {
    return axios.get('/containers/1');
}

function diminishStock(info) {
    return axios.post('/diminish-stock/1', info)
        .then((response) => {
            if (response.data.isError) {
                alert(response.data.message);       //TODO: get rid of alerts!
            }
        })
        .catch(function(err) {
            alert(err);
        });
}

function replenishStock(info) {
    return axios.post('/replenish-stock/1', info)
        .then((response) => {
            if (response.data.isError) {
                alert(response.data.message);       //TODO: get rid of alerts!
            }
        })
        .catch(function(err) {
            alert(err);
        });
}

function removeAllExpired(){
    return axios.delete('/containers/1');
}
module.exports = {getStockOfCenter, diminishStock, replenishStock, removeAllExpired};