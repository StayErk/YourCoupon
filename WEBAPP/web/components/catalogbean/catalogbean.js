const pancake= document.getElementById('pancack')
window.onload = () => {
    const xmlHttpRequest = new XMLHttpRequest()
    xmlHttpRequest.open('GET', 'ComponentsPackServlet?component='+document.title.toLowerCase(), true)
    xmlHttpRequest.send()

    xmlHttpRequest.onreadystatechange = () => {
        if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {

        }
    }

}