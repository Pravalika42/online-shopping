const username = document.getElementById("usrname");
const password = document.getElementById("psw");
const conPassword = document.getElementById("c-psw");
const email = document.getElementById("email");
const sub = document.getElementById("submit");

username.addEventListener('input', function(e) {
	sub.disabled = true;
	email.disabled = true;
	password.disabled = true;
	conPassword.disabled = true;
	var regex1 = /^[a-zA-Z ]{2,30}$/;
	if (!regex1.test(e.target.value)) {
		msg1.innerHTML = "name must contain only alphabets";
		msg1.setAttribute("class","redtext");
	}
	else {
		email.disabled = false;
		msg1.innerHTML = "";
	}
})

email.addEventListener('input', function(e) {
		var regex2 = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!regex2.test(e.target.value)) {
			msg2.innerHTML = "enter valid email id";
	        msg2.setAttribute("class","redtext");
			}
		else {
				
			msg2.innerHTML = "";
			password.disabled = false;
			conPassword.disabled = false;
			}
		}
		)


function check() {
	if (document.getElementById("psw").value == document.getElementById("c-psw").value) {
		msg.innerHTML = "";
		document.getElementById("submit").disabled = false;
	}
	else {
		document.getElementById("submit").disabled = true;
		msg.innerHTML = "password must be same";
		msg.setAttribute("class","redtext");
	}
}


