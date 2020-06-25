let sessionID = $("#sessionid").value;

let package = {
    durata:0,
    persone:0,
    hotel: {},
    restaurants: [],
    tours: [],
    costo: 0
}

//Step del form
jQuery().ready(function () {
    let v = $("#form")
    let cittaScelta = document.getElementById('citta').value
    let durataInput = document.getElementById('durata');
    let personeInput = document.getElementById('persone');
    $(".open1").click(function () {
        if(v) {
            if(durataInput.value != '' && personeInput.value != '') {
                $(".frm").hide("fast");
                $("#sf2").show("slow");
                showHotel(cittaScelta);
                package.durata = durataInput.value;
                package.persone = personeInput.value;
            }
        }
    });
    $(".open2").prop('disabled', true).click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf3").show("slow");
            showRestaurants(cittaScelta)
        }
    });

    $(".open3").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf4").show("slow");
            showTour(cittaScelta)
        }
    });
    $(".open4").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf5").show("slow");
            showRiepilogo();
        }
    })
    $(".back2").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf1").show("slow");
        }
    });
    $(".back3").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf2").show("slow");
        }
    });
    $(".back4").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf3").show("slow");
        }
    });
    $(".back5").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf4").show("slow");
        }
    });
    $(".open5").click(function () {
        if(v) {
            $("#pacchetto").val(JSON.stringify(package))
            return true;
        }
    });

})


const showHotel = (citta) => {
    $.getJSON(`ComponentsPackServlet;jsessionid=${sessionID}?component=hotel`, function (data, status) {
        if(status === 'success') {
            let filtered = data.filter((element) => {
                return element.citta.toLowerCase() == citta;
            })
            createHotelCard(filtered);
            $(".sceltaSingola").click(function () {
                $(".open2").prop('disabled', false);
                package.hotel = JSON.parse(this.value);
            })
        }
    })
}

const showRestaurants = (citta) => {
    $.getJSON(`ComponentsPackServlet;jsessionid=${sessionID}?component=ristoranti`, function (data, status) {
        if(status === 'success') {
            let filtered = data.filter((element) => {
                return element.citta.toLowerCase() == citta;
            })
            createRestaurantCard(filtered);
            $(".sceltaMultipla").click(function () {
                package.restaurants.push(JSON.parse(this.value))
                console.log(package.restaurants);
                $(".open3").text(`${package.restaurants.length} Ristoranti vanno bene`)
            })
        }
    })
}

const showTour = (citta) => {
    $.getJSON(`ComponentsPackServlet;jsessionid=${sessionID}?component=tour`, function (data, status) {
        if(status === 'success') {
            let array = [];
            createArrayOfHash(data, array);
            console.log(array)
            let filtered = array.filter((element) => {
                return element.citta.toLowerCase() == citta;
            })
            console.log(filtered)
            createCardFromHash(filtered)
            $(".sceltaMultipla").click(function () {
                package.tours.push(JSON.parse(this.value))
                console.log(package.tours);
                $(".open4").text(`${package.tours.length} Tour vanno bene`)
            })
        }
    })
}
const showRiepilogo = () => {
    let costoTotale = 0;
    $("#riepilogo").html("");
    const tableRow = document.createElement("tr")
    $("#riepilogo").append(tableRow)
    const tabledata1 = document.createElement("td");
    tabledata1.innerText = "Hotel";
    const tabledata2 = document.createElement("td");
    tabledata2.innerText = package.hotel.nome;
    const tabledata3 = document.createElement("td");
    tabledata3.innerText = package.hotel.costoNotte;
    tableRow.appendChild(tabledata1);
    tableRow.appendChild(tabledata2);
    tableRow.appendChild(tabledata3);
    costoTotale = package.hotel.costoNotte * package.durata * package.persone;

    package.restaurants.forEach((restaurant) => {
        const tableRowRestaurant = document.createElement('tr');
        $("#riepilogo").append(tableRowRestaurant);
        const tabledataRestaurant1 = document.createElement("td");
        tabledataRestaurant1.innerText = "Ristorante";
        const tabledataRestaurant2 = document.createElement("td");
        tabledataRestaurant2.innerText = restaurant.nome;
        const tabledataRestaurant3 = document.createElement("td");
        tabledataRestaurant3.innerText = restaurant.costo;
        tableRowRestaurant.appendChild(tabledataRestaurant1);
        tableRowRestaurant.appendChild(tabledataRestaurant2);
        tableRowRestaurant.appendChild(tabledataRestaurant3);
        costoTotale += restaurant.costo * package.persone;
    })

    package.tours.forEach((tours) => {
        const tableRowTour = document.createElement('tr');
        $("#riepilogo").append(tableRowTour);
        const tabledataTour1 = document.createElement("td");
        tabledataTour1.innerText = "Tour";
        const tabledataTour2 = document.createElement("td");
        tabledataTour2.innerText = `Visita a ${tours.nome_luogo}`;
        const tabledataTour3 = document.createElement("td");
        tabledataTour3.innerText = tours.costo;
        tableRowTour.appendChild(tabledataTour1);
        tableRowTour.appendChild(tabledataTour2);
        tableRowTour.appendChild(tabledataTour3);
        costoTotale += tours.costo * package.persone;
    })
    package.costo = (Math.round(costoTotale * 100) / 100).toFixed(2);
    let paragrafoRep = `Il costo totale di questo wonderbox per ${package.persone == 1 ? 'Una persona' : package.persone + ' persone' }, dalla durata di ${package.durata} notti costa ${package.costo}`
    $("#paragrafoRep").text(paragrafoRep);
}
const createArrayOfHash = (data, hashes) => {
    for (let propt in data) {
        let hash = {
            id : data[propt][0].id,
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

const createHotelCard = (data) => {
    $("#hotelFormGroup").html("");
    data.forEach((hotel) =>{
        const card = document.createElement('div')
        card.classList.add('card')
        const cardHeader = document.createElement('h5');
        cardHeader.classList.add('card-header')
        cardHeader.innerText = hotel.citta
        card.appendChild(cardHeader)
        $("#hotelFormGroup").append(card)
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
        const cardButton = document.createElement('button')
        cardButton.classList.add("btn", "btn-primary", "sceltaSingola");
        cardButton.value = JSON.stringify(hotel);
        cardButton.innerText = "Scegli"
        cardButton.type = "button"
        cardBody.appendChild(cardText2)
        cardBody.appendChild(cardButton)
    })
}
const createRestaurantCard = (data) => {
    $("#restaurantFormGroup").html("")
    data.forEach((ristorante) =>{
        const card = document.createElement('div')
        card.classList.add('card')
        const cardHeader = document.createElement('h5');
        cardHeader.classList.add('card-header')
        cardHeader.innerText = ristorante.citta
        card.appendChild(cardHeader)
        $("#restaurantFormGroup").append(card)
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
        const cardButton = document.createElement('button')
        cardButton.classList.add("btn", "btn-primary", "sceltaMultipla");
        cardButton.value = JSON.stringify(ristorante);
        cardButton.innerText = "Aggiungi"
        cardButton.type = "button"
        cardBody.appendChild(cardText2)
        cardBody.appendChild(cardButton)

    })
}

const createCardFromHash = (hashes) => {
    $("#tourFormGroup").text("")
    hashes.forEach((hash) => {
        const card = document.createElement('div')
        card.classList.add('card')
        const cardHeader = document.createElement('h5');
        cardHeader.classList.add('card-header')
        cardHeader.innerText = hash.citta
        card.appendChild(cardHeader)
        $("#tourFormGroup").append(card)
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
        const cardButton = document.createElement('button')
        cardButton.classList.add("btn", "btn-primary", "sceltaMultipla");
        cardButton.value = JSON.stringify(hash);
        cardButton.innerText = "Aggiungi"
        cardButton.type = "button"
        cardBody.appendChild(cardText2)
        cardBody.appendChild(cardButton)
    })
}