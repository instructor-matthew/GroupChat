var stompClient = null;

const value = moment('2014-08-20 15:30:00').format('MM/DD/YYYY h:mm a');

console.log(value); // 08/20/2014 3:30 pm
var objDiv = document.getElementById("chat");
objDiv.scrollTop = objDiv.scrollHeight;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}


function connect(num) {
	
	var chatRoom = num;
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings/' + num, function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName(num) {
	console.log(num);
    stompClient.send("/app/hello/" + num, {}, JSON.stringify({'name': $("#name").val()}));
    document.getElementById("name").value= "";
}

function showGreeting(message) {
	const time = moment(message.timestamp).format('MM/DD/YYYY h:mm a');
	if(document.getElementById("newChat").innerHTML !== "")
    $("#newChat").append("<tr><td width='15%'><img src=" + message.image + "></<td><td>" + message.sender + " - " + time + "<hr><p>" + message.content + "</p></td></tr>");
    var objDiv = document.getElementById("chat");
    objDiv.scrollTop = objDiv.scrollHeight;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(document.getElementById("chatNum").value); });
});