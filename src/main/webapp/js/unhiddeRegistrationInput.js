/**
 * 
 */
function showRegistrationInput() {
  var opt = document.getElementById("rol").value;
  
	if(opt == "Client"){
		
	  document.getElementById("toHide").removeAttribute("hidden");
	}
	else{
	  document.getElementById("toHide").setAttribute("hidden", "hidden");
	}
}