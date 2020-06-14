const pancake= document.getElementById('pancack')
const citta = document.getElementById('citta')
const form = document.getElementById('form-filter')
let cittaInserite = []
window.onload = () => {
    let xmlHttpRequest = new XMLHttpRequest()
    xmlHttpRequest.open('GET', `ComponentsPackServlet;jsessionid=${form.sessionid.value}?component=${document.title.toLowerCase()}`, true)
    xmlHttpRequest.send()

    xmlHttpRequest.onreadystatechange = function() {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            if (document.title.toLowerCase() === 'tour') {
                console.log('tour')
                let data = JSON.parse(xmlHttpRequest.responseText);
                let hashes = [];
                createArrayOfHash(data, hashes)
                createCardFromHash(hashes)
            }
            else{
                let data = JSON.parse(xmlHttpRequest.responseText)
                if(document.title.toLowerCase() === 'ristoranti'){
                    createRestaurantCard(data);
                } else if(document.title.toLowerCase() === 'hotel') {
                    createHotelCard(data);
                }
            }
        }
    }
}

const createCardFromHash = (hashes) => {
    pancake.innerHTML = '';
    hashes.forEach((hash) => {
        const card = document.createElement('div')
        card.classList.add('card')
        const cardHeader = document.createElement('h5');
        cardHeader.classList.add('card-header')
        cardHeader.innerText = hash.citta
        card.appendChild(cardHeader)
        pancake.appendChild(card)
        const cardImg = document.createElement('img')
        cardImg.classList.add('card-img-top')
        cardImg.src = hash.immagine
        cardImg.alt = `immagine di ${hash.nome_luogo}`
        card.appendChild(cardImg)
        const cardBody = document.createElement('div')
        cardBody.classList.add('card-body')
        card.appendChild(cardBody)
        const cardTitle = document.createElement('h5')
        cardTitle.classList.add('card-title')
        cardTitle.innerText = `Visita guidata a ${hash.nome_luogo}`
        cardBody.appendChild(cardTitle)
        const cardText1 = document.createElement('p')
        const cardText2 = document.createElement('p')
        cardText1.classList.add('card-text')
        cardText2.classList.add('card-text')
        cardText1.innerText = `${hash.descrizione.substr(0,hash.descrizione.length/3)}...`
        cardBody.appendChild(cardText1)
        cardText2.innerText = `per soli: ${hash.costo} euro a persona`
        cardBody.appendChild(cardText2)
        const option = document.createElement('option');
        if(!cittaInserite.includes(hash.citta)) {
            option.value = hash.citta.toLowerCase();
            option.innerText = hash.citta
            cittaInserite.push(hash.citta);
            citta.appendChild(option)
        }
    })
}

const createRestaurantCard = (data) => {

    pancake.innerHTML = '';
    data.forEach((ristorante) =>{
        const card = document.createElement('div')
        card.classList.add('card')
        const cardHeader = document.createElement('h5');
        cardHeader.classList.add('card-header')
        cardHeader.innerText = ristorante.citta
        card.appendChild(cardHeader)
        pancake.appendChild(card)
        const cardImg = document.createElement('img')
        cardImg.classList.add('card-img-top')
        cardImg.src = ristorante.immagine
        cardImg.alt = `immagine di ${ristorante.nome}`
        card.appendChild(cardImg)
        const cardBody = document.createElement('div')
        cardBody.classList.add('card-body')
        card.appendChild(cardBody)
        const cardTitle = document.createElement('h5')
        cardTitle.classList.add('card-title')
        cardTitle.innerText = `Pranzo/Cena da ${ristorante.nome}`
        cardBody.appendChild(cardTitle)
        const cardText2 = document.createElement('p')
        cardText2.classList.add('card-text')
        cardText2.innerText = `per soli ${ristorante.costo} euro a persona`
        cardBody.appendChild(cardText2)
        const option = document.createElement('option');
        if(!cittaInserite.includes(ristorante.citta)) {
            option.value = ristorante.citta.toLowerCase();
            option.innerText = ristorante.citta
            cittaInserite.push(ristorante.citta);
            citta.appendChild(option)
        }

    })
}

const createHotelCard = (data) => {
    pancake.innerHTML = '';
    data.forEach((hotel) =>{
        const card = document.createElement('div')
        card.classList.add('card')
        const cardHeader = document.createElement('h5');
        cardHeader.classList.add('card-header')
        cardHeader.innerText = hotel.citta
        card.appendChild(cardHeader)
        pancake.appendChild(card)
        const cardImg = document.createElement('img')
        cardImg.classList.add('card-img-top')
        cardImg.src = hotel.immagine
        cardImg.alt = `immagine di ${hotel.nome}`
        card.appendChild(cardImg)
        const cardBody = document.createElement('div')
        cardBody.classList.add('card-body')
        card.appendChild(cardBody)
        const cardTitle = document.createElement('h5')
        cardTitle.classList.add('card-title')
        cardTitle.innerText = `Soggiorno da ${hotel.nome}, ${hotel.stelle} stelle`
        cardBody.appendChild(cardTitle)
        const cardText2 = document.createElement('p')
        cardText2.classList.add('card-text')
        cardText2.innerText = `per soli ${hotel.costoNotte} euro a persona per notte`
        cardBody.appendChild(cardText2)
        const option = document.createElement('option');
        if(!cittaInserite.includes(hotel.citta)) {
            option.value = hotel.citta.toLowerCase();
            option.innerText = hotel.citta
            cittaInserite.push(hotel.citta);
            citta.appendChild(option)
        }

    })
}

const createArrayOfHash = (data, hashes) => {
    for (let propt in data) {
        let hash = {
            id_tour : data[propt][0].id,
            id_luogo: data[propt][0].id_luogo,
            costo: data[propt][0].costo,
            partecipanti: data[propt][0].partecipanti,
            nome_luogo: data[propt][1].nome,
            indirizzo: data[propt][1].indirizzo,
            citta: data[propt][1].citta,
            descrizione: data[propt][1].descrizione,
            immagine: data[propt][1].immagine
        }
        hashes.push(hash)
    }
}


const btn = document.getElementById('btn')
btn.addEventListener('click', () => {
    console.log('click')
    console.log(form.citta.value)
    let xmlHttpRequest = new XMLHttpRequest()
    if(document.title.toLowerCase() == 'hotel') {
        if(form.prezzo[0].checked){
            xmlHttpRequest.open('GET', `ComponentsPackServlet;jsessionid=${form.sessionid.value}?component=${document.title.toLowerCase()}&filter=costoNotte&order=ASC`, true)
            xmlHttpRequest.send()
        } else if (form.prezzo[1].checked) {
            xmlHttpRequest.open('GET', `ComponentsPackServlet;jsessionid=${form.sessionid.value}?component=${document.title.toLowerCase()}&filter=costoNotte&order=DESC`, true)
            xmlHttpRequest.send()
        }
    } else {
        console.log(form.prezzo)
        if(form.prezzo[0].checked){
            xmlHttpRequest.open('GET', `ComponentsPackServlet;jsessionid=${form.sessionid.value}?component=${document.title.toLowerCase()}&filter=costo&order=ASC`, true)
            xmlHttpRequest.send()
        } else if (form.prezzo[1].checked) {
            xmlHttpRequest.open('GET', `ComponentsPackServlet;jsessionid=${form.sessionid.value}?component=${document.title.toLowerCase()}&filter=costo&order=DESC`, true)
            xmlHttpRequest.send()
        }
    }


    xmlHttpRequest.onreadystatechange = function() {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            if (document.title.toLowerCase() === 'tour') {
                let data = JSON.parse(xmlHttpRequest.responseText);
                let hashes = [];
                createArrayOfHash(data, hashes)
                let filtered = hashes.filter((element) => {
                    if(form.citta.value == 'all') {
                        return true
                    }
                    return element.citta.toLowerCase() == form.citta.value;
                })
                createCardFromHash(filtered)
            }
            else{
                let data = JSON.parse(xmlHttpRequest.responseText)
                if(document.title.toLowerCase() === 'ristoranti'){
                    let filtered = data.filter((element) => {
                        if(form.citta.value == 'all') {
                            return true
                        }
                        return element.citta.toLowerCase() == form.citta.value;
                    })
                    console.log(filtered)
                    createRestaurantCard(filtered);
                } else if(document.title.toLowerCase() === 'hotel') {
                    let filtered = data.filter((element) => {
                        if(form.citta.value == 'all') {
                            return true
                        }
                        return element.citta.toLowerCase() == form.citta.value;
                    })
                    createHotelCard(filtered);
                }
            }
        }
    }
})