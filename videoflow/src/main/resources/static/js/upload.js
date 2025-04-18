
document.addEventListener('DOMContentLoaded', function() {
    // DOM elements
    const uploadArea = document.getElementById('upload-area');
    const videoUpload = document.getElementById('video-upload');
    const uploadForm = document.getElementById('upload-form');
    const titleInput = document.getElementById('title');
    const descriptionInput = document.getElementById('description');
    const categorySelect = document.getElementById('category');
    const keywordInput = document.getElementById('keyword');

    // File object to store the selected video
    let selectedVideoFile = null;

    // Activate file selection when clicking on the upload area
    uploadArea.addEventListener('click', () => videoUpload.click());

    // Handle file selection
    videoUpload.addEventListener('change', (e) => {
        handleFileSelection(e.target.files);
    });

    // Drag and drop functionality
    uploadArea.addEventListener('dragover', (e) => {
        e.preventDefault();
        uploadArea.style.backgroundColor = '#e0eeff';
        uploadArea.style.borderColor = '#4b86b4';
    });

    uploadArea.addEventListener('dragleave', () => {
        uploadArea.style.backgroundColor = '';
        uploadArea.style.borderColor = '';
    });

    uploadArea.addEventListener('drop', (e) => {
        e.preventDefault();
        uploadArea.style.backgroundColor = '';
        uploadArea.style.borderColor = '';
        handleFileSelection(e.dataTransfer.files);
    });

    // Form submission
    uploadForm.addEventListener('submit', (e) => {
        e.preventDefault();

        if (!selectedVideoFile) {
            alert('Please select a video to upload.');
            return;
        }

        // Validate required fields
        if (!titleInput.value.trim()) {
            alert('Please enter a title for the video.');
            titleInput.focus();
            return;
        }

        if (!descriptionInput.value.trim()) {
            alert('Please enter a description for the video.');
            descriptionInput.focus();
            return;
        }

        // Create FormData object to send to server
        const formData = new FormData();
        formData.append('video', selectedVideoFile);
        formData.append('title', titleInput.value.trim());
        formData.append('description', descriptionInput.value.trim());
        formData.append('category', categorySelect.value);
        formData.append('keyword', keywordInput.value.trim());

        // Show loading state
        const submitBtn = uploadForm.querySelector('button[type="submit"]');
        const originalBtnText = submitBtn.textContent;
        submitBtn.disabled = true;
        submitBtn.textContent = 'Uploading...';

        // Here we would send the data to the server
        // For now, we'll simulate an upload with a timeout
        setTimeout(() => {
            console.log('Video file:', selectedVideoFile);
            console.log('Form data:', {
                title: titleInput.value,
                description: descriptionInput.value,
                category: categorySelect.value,
                keyword: keywordInput.value
            });

            // In a real application, we would use fetch API
            /*
            fetch('/api/videos/upload', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Redirect to the video page or show success message
                window.location.href = '/videos/' + data.videoId;
            })
            .catch(error => {
                console.error('Error uploading video:', error);
                alert('Error uploading video. Please try again.');

                // Reset button state
                submitBtn.disabled = false;
                submitBtn.textContent = originalBtnText;
            });
            */

            // For demo purposes, we'll just show an alert
            alert('Video uploaded successfully!');

            // Reset form
            uploadForm.reset();
            resetUploadArea();
            submitBtn.disabled = false;
            submitBtn.textContent = originalBtnText;

        }, 2000);
    });

    // Handle file selection
    function handleFileSelection(files) {
        if (!files || files.length === 0) return;

        const file = files[0];

        // Validate file type
        if (!file.type.startsWith('video/')) {
            alert('Please select a valid video file.');
            return;
        }

        // Store the selected file
        selectedVideoFile = file;

        // Update the upload area
        const fileSize = formatFileSize(file.size);
        uploadArea.innerHTML = `
            <div style="text-align: center;">
                <i class="fas fa-video" style="font-size: 32px; margin-bottom: 10px;"></i>
                <p style="margin: 5px 0;"><strong>${file.name}</strong></p>
                <p style="margin: 5px 0; color: #666;">${fileSize}</p>
                <button id="change-video" style="background: none; border: none; color: #0066cc; cursor: pointer; margin-top: 10px;">Change video</button>
            </div>
        `;

        // Add event listener to the change video button
        document.getElementById('change-video').addEventListener('click', (e) => {
            e.stopPropagation(); // Prevent triggering the upload area click
            videoUpload.click();
        });

        // Try to extract title from filename
        const filenameWithoutExt = file.name.split('.').slice(0, -1).join('.');
        if (filenameWithoutExt && !titleInput.value) {
            titleInput.value = filenameWithoutExt.replace(/[_-]/g, ' ');
        }
    }

    // Format file size for display
    function formatFileSize(bytes) {
        if (bytes === 0) return '0 Bytes';

        const k = 1024;
        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        const i = Math.floor(Math.log(bytes) / Math.log(k));

        return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
    }

    // Reset upload area to initial state
    function resetUploadArea() {
        selectedVideoFile = null;
        uploadArea.innerHTML = `
            <i class="fas fa-cloud-upload-alt upload-icon"></i>
            <p>Upload video</p>
        `;
    }
});