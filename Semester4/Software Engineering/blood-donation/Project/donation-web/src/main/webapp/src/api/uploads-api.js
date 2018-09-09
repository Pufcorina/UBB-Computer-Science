const axios = require('axios');

function uploadToCloudinary (files) {
    // Push all the axios request promise into a single array
    const uploaders = files.map(file => {
        // Initial FormData
        const formData = new FormData();
        formData.append("file", file);
        formData.append("tags", `codeinfuse, medium, gist`);
        formData.append("upload_preset", "wpslh9oy"); // preset name from Cloudinary account
        formData.append("api_key", "483637239792219"); // my own Cloudinary key
        formData.append("timestamp", (Date.now() / 1000) | 0);

        // Make an AJAX upload request using Axios (replace Cloudinary URL below with your own)
        return axios.post("https://api.cloudinary.com/v1_1/bloodyfast/image/upload", formData, {
            headers: { "X-Requested-With": "XMLHttpRequest" },
        }).then(response => {
                const data = response.data;
                const fileURL = data.secure_url; // this URL is the reference to the file
                this.setState({"uploadedFileUrl": fileURL});
            });
    });

    // Once all the files are uploaded
    axios.all(uploaders).then(() => {
        alert("File has been successfully uploaded to the cloud!");
    });
}

function sendTestResults(results) {
    return axios.post('/results', results);
    //alert(JSON.stringify(results));     //TODO: remove this shit
}

module.exports = {uploadToCloudinary, sendTestResults};