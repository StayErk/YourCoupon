function emailCheck(campo) {
    let regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    console.log(regex.test(campo.value))
    return regex.test(campo.value);
}

function nameChack(campo) {
    let regex = /^[\w\s\.]{2,20}$/
    return regex.test(campo.value);
}


function passwdCheck(campo) {
    let regex = /^\S{6,}$/;
    return regex.test(campo.value);
}

function cfCheck(campo) {
    let regex = /^([A-Za-z]{6})(\d{2})([A-Za-z])(\d{2})([A-Za-z])(\d{3})([A-Za-z])$/
    return regex.test(campo.value);
}

function telefonoFisso(campo) {
    let regex= /^(\d{3})-(\d{6,7})$/
    return  regex.test(campo.value);
}

function checkCampiFormLogin() {
    const form = document.getElementById("loginForm")
    const email = form.email;
    const passwd = form.password;

    let ch1 = emailCheck(email);
    let ch2 = passwdCheck(passwd);

    console.log(ch1 && ch2)
    return (ch1 && ch2);
}

function checkCampiRegistrazione() {
    const form = document.getElementById("signUpForm")
    const nome = form.nome;
    const cognome = form.cognome;
    const email = form.email;
    const passwd = form.password;

    let ch1 = emailCheck(email);
    let ch2 = passwdCheck(passwd);
    let ch3 = nameChack(nome);
    let ch4 = nameChack(cognome)


    return (ch1 && ch2 && ch4 && ch3)
}
