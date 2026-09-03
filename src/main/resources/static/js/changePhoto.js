let fileInput = document.getElementById('avatar-file-2');
let preview = document.getElementById('pfp-preview');

let uploadPhotoButton = document.getElementById('upload-photo-button');
let deletePhotoButton = document.getElementById('delete-photo-button');
let cancelButton = document.getElementById('cancel-photo-button');

let photoContainer = document.querySelector('.photo-edit');
let originalPhotoHTMLContent = photoContainer.innerHTML;
let hadOriginalPhoto = !!preview;



if (!preview) {
    deletePhotoButton.style.display = 'none';
    uploadPhotoButton.style.display = 'none';
    cancelButton.style.display = 'none';
} else {
    deletePhotoButton.style.display = 'block';
    uploadPhotoButton.style.display = 'none';
    cancelButton.style.display = 'none';
}


fileInput.addEventListener("change", (e) => {

    let image = fileInput.files[0];

    if (!image) return;

    uploadPhotoButton.style.display = 'block';
    deletePhotoButton.style.display = 'none';
    cancelButton.style.display = 'block';

    const imageURL = URL.createObjectURL(image);

    if (preview) {
        preview.src = imageURL;
    } else {
        let initials = document.querySelector(".photo-edit .preview");

        const imgContainer = document.createElement('div');
        imgContainer.classList.add('pic');
        imgContainer.setAttribute('role', 'img');
        imgContainer.setAttribute('aria-label', 'Profile picture');

        let img = document.createElement('img');
        img.src = imageURL;
        img.id = 'pfp-preview';
        img.alt = 'Profile picture';

        imgContainer.appendChild(img);

        initials.replaceWith(imgContainer);

        preview = img;
    }

});


cancelButton.addEventListener('click', () => {
    fileInput.value = '';

    photoContainer.innerHTML = originalPhotoHTMLContent;

    preview = document.getElementById('pfp-preview');

    cancelButton.style.display = 'none';
    uploadPhotoButton.style.display = 'none';

    if (hadOriginalPhoto) {
        deletePhotoButton.style.display = 'block';
    } else {
        deletePhotoButton.style.display = 'none';
    }

});






