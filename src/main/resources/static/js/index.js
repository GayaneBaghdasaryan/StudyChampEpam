
fetch(`http://localhost:8082/study/champ/get/next/option/${lessonId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Process the retrieved data
        console.log('Data from Spring backend:', data);

        // Example: Display data in the HTML document
        // next(data);
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });

// Example function to display data in the HTML document
// function next(data) {
//     // Assuming you have an element with the id "data-container" in your HTML
//     const dataContainer = document.getElementById('data-container');
//
//     // Clear previous content
//     dataContainer.innerHTML = '';
//
//     // Create and append elements to display data
//     data.forEach(item => {
//         const listItem = document.createElement('li');
//         listItem.textContent = item.name; // Adjust this based on your data structure
//         dataContainer.appendChild(listItem);
//     });
// }
function next(lessonId) {
    // Assuming you have an element with the id "data-container" in your HTML
    const dataContainer = document.getElementById('data-container');

    // Clear previous content
    dataContainer.innerHTML = '';

    // Create and append elements to display data
    // Use the lessonId parameter as needed
    console.log('Lesson ID:', lessonId);

    // Process and display the retrieved data
    // Example: Display data in the HTML document
    data.forEach(item => {
        const listItem = document.createElement('li');
        listItem.textContent = item.name; // Adjust this based on your data structure
        dataContainer.appendChild(listItem);
    });
}

