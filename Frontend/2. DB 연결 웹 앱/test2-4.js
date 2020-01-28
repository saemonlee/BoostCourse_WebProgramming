var oReq = new XMLHttpRequest();
oReq.addEventListener("load", function() {
    console.log(this.responseText);
});

oReq.open("GET", "./json2-4.txt");
oReq.send();
