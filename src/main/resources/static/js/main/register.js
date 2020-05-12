const register = () => {
	$('body').on('click', '.register-btn', function(e) {
		e.preventDefault();
		
		let username = $('#username').val();
		let pass = $('#pass').val();
		let confPass = $('#con-pass').val();
		let fullname = $('#fullname').val();
		let email = $('#email').val();
		let phoneNumber = $('#phoneNumber').val();
		let address = $('#address').val();
		let sex = $('#male').is(':checked') ? 1 : 0;
		
		console.log(username, pass, confPass, fullname, email, phoneNumber, address, username, sex);
		
	})
}

$(function() {
	register();
})