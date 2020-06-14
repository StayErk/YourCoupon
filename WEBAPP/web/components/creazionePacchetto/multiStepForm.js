let sessionID = $("#sessionid").value;


let package = {
    hotel: {},
    restaurants: [],
    tours: [],
}

//Step del form
jQuery().ready(function () {
    let v = $("#form")
    let cittaScelta = document.getElementById('citta').value

    $(".open1").click(function () {
        if(v) {
            $(".frm").hide("fast");
            $("#sf2").show("slow");
            showHotel(cittaScelta);
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
        }
    });
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
    $(".open4").click(function () {
        if(v) {
            return false;
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
        const option = document.createElement('option');
        const cardButton = document.createElement('button')
        cardButton.classList.add("btn", "btn-primary", "sceltaMultipla");
        cardButton.value = JSON.stringify(ristorante);
        cardButton.innerText = "Aggiungi"
        cardButton.type = "button"
        cardBody.appendChild(cardText2)
        cardBody.appendChild(cardButton)

    })
}