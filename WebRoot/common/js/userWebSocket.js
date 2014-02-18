var ws = null;

function startWebSocket() {
	var serverIP = "127.0.0.1";
	var userId = document.getElementById('userId').value;
	var url = "ws://" + serverIP + ":8080/wsim/user.ws?userId=" + userId;
	if('WebSocket' in window) {
		try {
			ws = new WebSocket(url);
		} catch(e) {
			alert("1");
		}
	} else if('MozWebSocket' in window) {
		try {
			ws = new WebSocket(url);
		} catch(e) {
			alert("1");
		}
	} else {
		alert("not support");
	}
	
	ws.onmessage = function(evt) {
		say(evt.data);
	}
	
	ws.onclose = function(evt) {
		alert("close");
	}
	
	ws.onopen = function(evt) {
		alert("open");
	}
	
}


function sendMsg() {
	ws.send(document.getElementById('writeMsg').value);
}


function say(msg) {
	var div = document.createElement("div");
	div.innerHTML = msg;
	document.body.appendChild(div);
}