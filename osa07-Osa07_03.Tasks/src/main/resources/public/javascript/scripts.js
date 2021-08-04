var url = contextRoot + "tasks"

var http = new XMLHttpRequest()

http.onreadystatechange = function() {
    if (this.readyState != 4) {
        return
    }
     response = JSON.parse(this.responseText)


    document.getElementById("received").innerHTML = response.map(x=>x.name)
}

function hae() {
    http.open("GET", url)
    http.send()
}
    
function laheta() {
    var data = {
        name: document.getElementById("name").value
    }
console.log(JSON.stringify(data))
    
    http.open("POST", url)
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify(data))
}


