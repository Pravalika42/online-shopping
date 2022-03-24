/**
 * 
 */
 const email = document.getElementById('email');
 const password = document.getElementById('psw');
 const error = document.getElementById('msg');
 const sub = document.getElementById('submit');
  
email.addEventListener('input',function(e){
	var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(!regex.test(e.target.value)){
		msg.innerHTML = "Enter valid email id";
		password.disabled = true;
		sub.disabled = true;
		msg.setAttribute("class","redtext");
	}
	else{
		msg.style.diplay ='block';
		msg.innerHTML = "";
		password.disabled = false;
		sub.disabled = false;
	}
	
	
}



)