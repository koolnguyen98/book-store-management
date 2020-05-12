const isAccountValid = (payload) => {
	const emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	const phonePattern = /(03|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
	
	const { username, password, confPassword, fullname,
		email, phoneNumber, address } = payload;
		
	let formIsValid = true;
	
	$(".username-helper").html('');
	$(".password-helper").html('');
	$(".confPassword-helper").html('');
	$(".fullName-helper").html('');
	$(".email-helper").html('');
	$(".phoneNumber-helper").html('');
	$(".address-helper").html('');
	
	if (username.trim().length == 0) {
		$(".username-helper").html('Username is required');
		formIsValid = false;
	}
	
	if (password.trim().length == 0) {
		$(".password-helper").html('Password is required');
		formIsValid = false;
	}
	
	if (confPassword.trim().length == 0) {
		$(".confPassword-helper").html('Password is required');
		formIsValid = false;
	} else {
		if (password.trim() !== confPassword.trim()) {
			$(".confPassword-helper").html('Password does not match');
			formIsValid = false;
		}
	}
	
	if (fullname.trim().length == 0) {
		$(".fullName-helper").html('Fullname is required');
		formIsValid = false;
	}
	
	if (email.trim().length == 0) {
		$(".email-helper").html('Email is required');
		formIsValid = false;
	} else {
		if (!emailPattern.test(email.trim())) {
			$(".email-helper").html('Email is not valid');
			formIsValid = false;
		}
	}
	
	if (phoneNumber.trim().length == 0) {
		$(".phoneNumber-helper").html('Phone number is required');
		formIsValid = false;
	} else {
		if (!phonePattern.test(phoneNumber.trim())) {
			$(".phoneNumber-helper").html('Phone number is not valid');
			formIsValid = false;
		}
	}
	
	if (address.trim().length == 0) {
		$(".address-helper").html('Address is required');
		formIsValid = false;
	}
	
	
	return formIsValid;
}

const register = () => {
	$('body').on('click', '.register-btn', function(e) {
		e.preventDefault();
		
		let username = $('#username').val();
		let password = $('#pass').val();
		let confPassword = $('#con-pass').val();
		let fullname = $('#fullname').val();
		let email = $('#email').val();
		let phoneNumber = $('#phoneNumber').val();
		let address = $('#address').val();
		let sex = $('#male').is(':checked') ? 1 : 0;
		
		let payload = {
			username,password,confPassword,
			fullname, email, phoneNumber, address, sex
		};
		
		let isValid = isAccountValid(payload);
		
		if (isValid) {
			$('.register-form').submit();

		} 
	})
}

$(function() {
	register();
})