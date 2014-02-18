var ws = null;

function startWebSocket() {
	
	var sender = document.getElementById('sender').value;
	var receiver = document.getElementById('receiver').value;
	var url = "ws://localhost:8080/wsim/friend.ws?sender=" + sender + "&receiver=" + receiver;
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