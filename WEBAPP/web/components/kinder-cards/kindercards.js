const deck = document.getElementById('deck');
const btn = document.getElementById('provabtn');
const btn2 = document.getElementById('cerca-btn');
const form = document.getElementById('search');
const sessionId = form[4].value;

btn2.addEventListener('click', () => {


    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", `PacchettiServlet;jsessionid=${sessionId}?action=retrieve`, true);
    xmlHttpRequest.send();
    console.log('cosa sei: ' + sessionId)
    if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
        let data = JSON.parse(xmlHttpRequest.responseText);
        let hashes = [];
        createObjects(data, hashes)
        let filtered = hashes.filter((hash) => {
            return hash.durata <= form[2].value && hash.citta === form[0].value && hash.persone <= form[1].value
        })
        deck.children = '';
        createCard(filtered)
    }
})


window.onload = () => {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", `PacchettiServlet;jsessionid=${sessionId}?action=retrieve`, true);
    xmlHttpRequest.send();

    xmlHttpRequest.onreadystatechange = function () {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            console.log(xmlHttpRequest.getAllResponseHeaders())
            let data = JSON.parse(xmlHttpRequest.responseText);
            let hashes = [];
            createObjects(data, hashes)
            createCard(hashes);
        }
    }
}

function createCard(hashes) {
    hashes.forEach((hash) => {
        const card = document.createElement('div')
        card.classList.add('card')
        const cardImg = document.createElement('img');
        cardImg.classList.add('card-img-top')
        cardImg.src = hash.immagine;
        cardImg.alt = hash.nome_albergo;
        card.appendChild(cardImg);
        const cardBody = document.createElement('div');
        cardBody.classList.add('card-body');
        card.appendChild(cardBody);
        const cardTitle = document.createElement('h5');
        cardTitle.classList.add('card-title');
        cardTitle.innerText = `Pacchetto ad ${hash.citta}`
        cardBody.appendChild(cardTitle);
        const cardPar = document.createElement('p');
        cardPar.classList.add('card-text');
        cardPar.innerText= `Soggiorno ad ${hash.citta} in ${hash.nome_albergo} per ${hash.persone} persone della durata di ${hash.durata} giorni.`
        cardBody.appendChild(cardPar);
        const cardPar2 = document.createElement('p');
        cardPar2.classList.add('card-text');
        cardPar2.innerText= `Per soli: ${hash.costo_pacchetto} euro`
        cardBody.appendChild(cardPar2);
        const cardBtn = document.createElement('a');
        cardBtn.classList.add('btn');
        cardBtn.classList.add('btn-primary')
        cardBtn.innerText = 'Dettagli'
        cardBtn.href = `PacchettiServlet;jsessionid=${sessionId}?action=bykey&key=${hash.id_pacchetto}`
        cardBody.appendChild(cardBtn)
        deck.appendChild(card);
    })
}

function createObjects(data, hashes) {
    for(let propt in data) {
        let hash = {
            id_pacchetto: data[propt][0].id,
            costo_pacchetto: data[propt][0].costo,
            id_cliente: data[propt][0].id_cliente,
            durata: data[propt][0].durata,
            predefinito: data[propt][0].predefinito,
            persone: data[propt][0].persone,
            id_struttura: data[propt][1].id,
            nome_albergo: data[propt][1].nome,
            indirizzo: data[propt][1].indirizzo,
            citta: data[propt][1].citta,
            costoNotte: data[propt][1].costoNotte,
            stelle: data[propt][1].stelle,
            immagine: data[propt][1].immagine,
            email: data[propt][1].email,
            numeroTelefono: data[propt][1].numeroTelefono
        }
        hashes.push(hash);
    }
}
