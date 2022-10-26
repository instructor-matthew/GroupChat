$(document).ready(function(){
	console.log("lets fuck shit up!");	

	$(function(){
		$("#search").on('input', function(e){
			e.preventDefault();
			$.ajax({
				type:"GET",
				url: "/chat/search?value=" + this.value,
				processData: false,
				contentType: false,
				cache: false,
				timeout: 60000,
				success: function(data){
					if(data.length === 0){
						$("#result").html('');
						return;
					}
					console.log(data);
					for(var i = 0; i < data.length; i++){
						var deets = {id: data[0].id, pic: data[0].pic, name: data[0].fullName}
						var $listItem = $('<li class="list-group-item person"><a><img src="' + data[i].pic +'" height=25px width=25px"> ' + data[i].fullName + '</a></li>')
						
						$listItem.find('a').click(function(){
							addToChat(deets);
						});
						
						$('#result').append($listItem);			
					}
				}, 
				error: function(e){
					console.log("ERROR" + e);
				}
			})
		})
	})
})

var chats = [
	document.getElementById("chat1"),
	document.getElementById("chat2"),
	document.getElementById("chat3")
]

var box = [
	document.getElementById("box1"),
	document.getElementById("box2"),
	document.getElementById("box3")
]

var num = 0;
var newArr = [];


function addToChat(data){
	console.log(data);
	document.getElementById("search").value = "";
	if(chats[2].value !== ""){
		console.log("We full!");
		return;
	}
	
	document.querySelector("#result").innerHTML = "";


	newArr[num] = data.id;
	console.log(newArr);
	num++;
	
	for(let i = 0; i < chats.length; i++){
		if(chats[i].value === ""){
			chats[i].value = data.id;
			box[i].innerHTML = '<p>' + data.name + '</p><img src="' + data.pic + '"><br><button class="btn btn-primary onclick="removeFrom(' + data.id + '")>Remove</button>'; 
			console.log(chats);
			break;
		}		
	}

function populateParts(){
	var parts = document.getElementById("test");
	parts.value = newArr;
	console.log(parts);
}
	
	
}