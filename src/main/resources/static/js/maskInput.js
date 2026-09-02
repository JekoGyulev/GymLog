let birthdateInput = document.getElementById("birthdate");


function maskInput(value, pattern) {
    let i = 0;
    return pattern.replace(/#/g, () => value[i++] || '')
}




birthdateInput.addEventListener("input", () => {

    let value = birthdateInput.value.replace(/\D/g, '');

    if (!value) {
        birthdateInput.value = '';
        return;
    }

    birthdateInput.value = maskInput(value, '##/##/####');
});