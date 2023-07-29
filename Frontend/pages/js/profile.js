function userProfile(){
    document.getElementById("myForm").addEventListener("submit", function(event) {
        event.preventDefault();
    
        const formData = new FormData(event.target);
    
        // Convert form data to JSON object
        const jsonObject = {};
        formData.forEach((value, key) => {
            jsonObject[key] = value;
        });
    
        // Replace 'YOUR_API_ENDPOINT' with your actual API endpoint URL
        const apiEndpoint = 'localhost:8081/ems/user/register';
    
        fetch(apiEndpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonObject)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Handle the API response data if needed
            console.log(data);
            alert("Form data submitted successfully!");
        })
        .catch(error => {
            console.error('Error:', error);
            alert("An error occurred while submitting the form data.");
        });
    });
}
function check(){
    alert("Checking Purpose")
}

