function invia() {
    const form = document.getElementById("modificaImg")
    form.submit()
    console.log("click")
}

function inviaPagamento() {
    console.log('click')
    const form = document.getElementById("datipagamento")
    let regExp = /^((\d{4})-){3}(\d{4})$/
    if(regExp.test(form.numerocarta.value)) {
        form.submit();
    } else {
        console.log('non valido')
        const errormsg = document.getElementById("errore");
        errormsg.classList.remove('d-none');
    }
}

document.getElementById("btnpagamento").addEventListener('click', () => inviaPagamento())

