let sessionID= $("#sessionid").val()

window.onload = function () {
    const table = document.getElementById("data");
    table.innerHTML = ''
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            let items = JSON.parse(xmlHttpRequest.responseText)
            riempiTabella(items, table)
            console.log(items)
            if(items.length > 0) {
                document.getElementById('procedialpagamento').classList.remove('d-none')
            }
        }
    }

    xmlHttpRequest.open("GET", `CarrelloServlet;jsessionid=${sessionID}?action=vedi`, true)
    xmlHttpRequest.send();


}

function refreshCarrello() {

    const table = document.getElementById("data");
    table.innerHTML = ''
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            let items = JSON.parse(xmlHttpRequest.responseText)
            riempiTabella(items, table)
        }
    }

    xmlHttpRequest.open("GET", `CarrelloServlet;jsessionid=${sessionID}?action=vedi`, true)
    xmlHttpRequest.send();
}

const riempiTabella = (data, table) => {
    data.forEach((elemento => {
        const riga = document.createElement('tr')
        table.appendChild(riga);
        const cittaScelta = document.createElement('td')
        cittaScelta.innerText = elemento.cittaDestinazione;
        riga.appendChild(cittaScelta);
        const durata = document.createElement('td')
        durata.innerText = `${elemento.pacchetto.durata} giorni`
        riga.appendChild(durata)
        const numeroPersone = document.createElement('td')
        numeroPersone.innerText = `${elemento.pacchetto.persone} persone`
        riga.appendChild(numeroPersone)
        const totalePacchetto = document.createElement('td')
        totalePacchetto.innerText = `${elemento.pacchetto.costo} €`
        riga.appendChild(totalePacchetto)
        const elimina = document.createElement('td')
        riga.appendChild(elimina)
        const eliminaBtn = document.createElement('button')
        eliminaBtn.innerText = 'Elimina'
        eliminaBtn.classList.add('btn', 'btn-danger')
        eliminaBtn.addEventListener('click', () => eliminaDalCarrello(elemento))
        elimina.appendChild(eliminaBtn)
    }))
}

function eliminaDalCarrello(elemento) {
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = () => {
        if(xmlHttpRequest.status == 200 && xmlHttpRequest.readyState == 4) {
            console.log('click')
            refreshCarrello()
        }
    }

    xmlHttpRequest.open("GET", `CarrelloServlet;jsessionid=${sessionID}?action=elimina&idpacchetto=${elemento.pacchetto.id}`, true)
    xmlHttpRequest.send();
}