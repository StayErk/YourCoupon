window.onload = () => {
    let xmlHttpRequest = new XMLHttpRequest();
    console.log('chiamata')
    xmlHttpRequest.onreadystatechange = function () {
        if(xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            let data = JSON.parse(xmlHttpRequest.responseText)
            data.forEach((citta) => {
                const option = document.createElement("option");
                option.value = citta.toLowerCase();
                option.innerText = citta;
                document.getElementById("nomidicitta").appendChild(option)
                console.log("Prova")
            });
        }
    }
    xmlHttpRequest.open("GET", "../ComponentsPackServlet?component=citta", true)
    xmlHttpRequest.send();
}