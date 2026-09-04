let currentPasswordInput = document.getElementById('current-pass');
let newPasswordInput = document.getElementById('new-pass');
let confirmPasswordInput = document.getElementById('confirm-pass');


let passwordsMismatchErrorMessage = document.getElementById('passwords-mismatch-error-message');

let updatePasswordBtn = document.getElementById('update-password-btn');



function updatePasswordFields() {

    const isEmpty = currentPasswordInput.value.trim() === '';

    if (isEmpty) {
        newPasswordInput.classList.add('change-pwd-disabled');
        confirmPasswordInput.classList.add('change-pwd-disabled');
        newPasswordInput.disabled = true;
        confirmPasswordInput.disabled = true;
    } else {
        newPasswordInput.classList.remove('change-pwd-disabled');
        confirmPasswordInput.classList.remove('change-pwd-disabled');
        newPasswordInput.disabled = false;
        confirmPasswordInput.disabled = false;
    }

}


updatePasswordFields();

currentPasswordInput.addEventListener('input', updatePasswordFields);



updatePasswordBtn.addEventListener('click', e => {

   if (newPasswordInput.value !== confirmPasswordInput.value) {
       e.preventDefault();
       passwordsMismatchErrorMessage.style.display = 'block';
       passwordsMismatchErrorMessage.textContent = 'Your passwords do not match';
   } else {
       passwordsMismatchErrorMessage.style.display = 'none';
   }
});



