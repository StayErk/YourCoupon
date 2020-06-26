const deck = document.getElementById('deck');
const btn = document.getElementById('provabtn');
const btn2 = document.getElementById('cerca-btn');
const form = document.getElementById('search');
const sessionId = form[4].value;
const citta = form.citta;
let cittaInserite = [];


function filtra(){
    console.log("click");
    let xmlHttpRequest = new XMLHttpRequest();

    xmlHttpRequest.onreadystatechange = (data) => {
        if (xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            let data = JSON.parse(xmlHttpRequest.responseText);
            console.log(data)
            let hashes = [];
            createObjects(data, hashes)
            let filteredCitta = hashes.filter((hash) => {
                if (form[0].value == 'nil') {
                    return true
                } else return hash.citta === form[0].value;
            })
            let filteredPersone = filteredCitta.filter((hash) => {
                if (form[1].value == 0) {
                    return true;
                } else return hash.persone <= form[1].value;
            })
            let filteredDurata = filteredPersone.filter((hash) => {
                if (form[2].value == 0) {
                    return true;
                } else return hash.durata <= form[2].value;
            })
            deck.innerHTML = '';
            createCard(filteredDurata)
        }
    }

    xmlHttpRequest.open("GET", `PacchettiServlet;jsessionid=${sessionId}?action=retrieve`, true);
    xmlHttpRequest.send();
}


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
        if(hash.predefinito === true){
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
            cardPar.innerText = `Soggiorno ad ${hash.citta} in ${hash.nome_albergo} per ${hash.persone} persone della durata di ${hash.durata} giorni.`
            cardBody.appendChild(cardPar);
            const cardPar2 = document.createElement('p');
            cardPar2.classList.add('card-text');
            cardPar2.innerText = `Per soli: ${hash.costo_pacchetto} euro`
            cardBody.appendChild(cardPar2);
            const cardBtn = document.createElement('a');
            cardBtn.classList.add('btn');
            cardBtn.classList.add('btn-primary')
            cardBtn.innerText = 'Dettagli'
            cardBtn.href = `PacchettiServlet;jsessionid=${sessionId}?action=bykey&key=${hash.id_pacchetto}`
            cardBody.appendChild(cardBtn)
            const aggiungiAlCArrello = document.createElement('button');
            aggiungiAlCArrello.classList.add('btn', 'btn-success', 'ml-3');
            aggiungiAlCArrello.innerText = 'Aggiungi al Carrello'
            aggiungiAlCArrello.addEventListener('click', () => addCart(hash))
            cardBody.appendChild(aggiungiAlCArrello);
            deck.appendChild(card);
            const option = document.createElement('option')
                if(!cittaInserite.includes(hash.citta)) {
                    option.value = hash.citta.toLowerCase();
                    option.innerText = hash.citta
                    cittaInserite.push(hash.citta)
                    citta.appendChild(option);
                }
        }
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


function addCart(hash) {
    console.log('click')
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = function () {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            $("#aggiunto").text(hash.citta)
            $("#notification").toast('show')
        }
    }

    xmlHttpRequest.open("GET", `user/CarrelloServlet;jsessionid=${sessionId}?action=aggiungi&idpacchetto=${hash.id_pacchetto}`, true);
    xmlHttpRequest.send();
}


