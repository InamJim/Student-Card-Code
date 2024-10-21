// Toggle the dropdown menu
function toggleDropdown() {
    const dropdownContent = document.querySelector(".dropdown-content");
    const isExpanded = dropdownContent.classList.toggle("show");

    // Toggle aria-expanded for accessibility
    document.querySelector(".hamburger-menu").setAttribute("aria-expanded", isExpanded);

    if (isExpanded) {
        document.addEventListener('click', closeDropdownOnClickOutside);
    } else {
        document.removeEventListener('click', closeDropdownOnClickOutside);
    }
}

// Close the dropdown when clicking outside of it
function closeDropdownOnClickOutside(event) {
    const dropdownContent = document.querySelector(".dropdown-content");
    const hamburgerMenu = document.querySelector(".hamburger-menu");

    // Check if the click was outside the dropdown or hamburger menu
    if (!dropdownContent.contains(event.target) && !hamburgerMenu.contains(event.target)) {
        dropdownContent.classList.remove('show');
        document.querySelector(".hamburger-menu").setAttribute("aria-expanded", "false");
        document.removeEventListener('click', closeDropdownOnClickOutside);
    }
}

// Allow keyboard navigation for the dropdown menu
document.querySelector(".hamburger-menu").addEventListener('keydown', (event) => {
    if (event.key === "Enter" || event.key === " ") {
        toggleDropdown();
        event.preventDefault();  // Prevent default spacebar scrolling
    }
});

// Access the user's webcam
navigator.mediaDevices.getUserMedia({ video: true })
    .then((stream) => {
        const video = document.getElementById('video');
        video.srcObject = stream;
    })
    .catch((error) => {
        console.error('Error accessing the camera:', error);
        document.getElementById('result').textContent = 'Unable to access the camera. Please check your permissions or try a different browser.';
    });

const video = document.getElementById('video');
const canvas = document.getElementById('canvas');
const context = canvas.getContext('2d');
const captureButton = document.querySelector('.capture');
const retakeButton = document.querySelector('.retake');
const resultText = document.getElementById('result');

// Capture image on "Capture" button click
captureButton.addEventListener('click', () => {
    // Draw the current video frame to the canvas
    context.drawImage(video, 0, 0, canvas.width, canvas.height);

    // Convert the canvas image to a blob
    canvas.toBlob((blob) => {
        const formData = new FormData();
        formData.append('file', blob, 'captured-image.png');
        formData.append('userId', 'user123');  // Replace with the actual user ID

        // Send the image to the backend for saving or replacing the old one
        fetch('/api/facial-recognition/upload', {
            method: 'POST',
            body: formData
        })
        .then(response => response.text())
        .then(data => {
            resultText.textContent = 'Image saved successfully: ' + data;
        })
        .catch(error => {
            resultText.textContent = 'Error: ' + error;
        });
    });

    // Display the captured image on the webpage
    const imagePreview = document.getElementById('imagePreview');
    const capturedImage = canvas.toDataURL('image/png');
    imagePreview.innerHTML = `<img src="${capturedImage}" width="200" height="200" />`;

    // Hide the "Capture" button and show the "Retake" button
    captureButton.style.display = 'none';
    retakeButton.style.display = 'inline-block';
});

// Retake functionality
retakeButton.addEventListener('click', () => {
    // Reset the canvas and video stream for retaking the picture
    const imagePreview = document.getElementById('imagePreview');
    imagePreview.innerHTML = '';  // Clear the previous image

    resultText.textContent = '';  // Clear the result text

    // Show the "Capture" button again and hide the "Retake" button
    captureButton.style.display = 'inline-block';
    retakeButton.style.display = 'none';
});
