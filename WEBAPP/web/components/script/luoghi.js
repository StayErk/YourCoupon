const select = document.getElementById("luoghi")
const id = document.getElementById("sessionid")

function prendiLuoghi() {
    let xmlHttpRequest = new XMLHttpRequest()
    xmlHttpRequest.onreadystatechange = function () {
        if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200){
            let hash = JSON.parse(xmlHttpRequest.responseText)
            let array = []
            arrayFromHash(array, hash)
            creaOpzioni(array)
        }
    }
    xmlHttpRequest.open("get", `../ComponentsPackServlet;jsessionid=${id.value}?component=luogo`)
    xmlHttpRequest.send()
}

function arrayFromHash(array, hash) {
    for(let propt in hash){
        console.log(propt + ": "+ hash[propt])
        let element = {
            id : propt,
            nome : hash[propt]
        }
        array.push(element)
    }
}

function creaOpzioni(array) {
    array.forEach((element) => {
        const opzione = document.createElement("option")
        opzione.value = element.id
        opzione.innerText = element.nome
        select.appendChild(opzione)
    })
}

window.onload =  prendiLuoghi()