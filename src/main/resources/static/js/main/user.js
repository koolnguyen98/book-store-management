const isUserValid = (payload) => {
	const emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	const phonePattern = /(03|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
	
	const { fullName, email, phoneNumber, address } = payload;
		
	let formIsValid = true;
	let coordinates;
	
	$(".username-helper").html('');
	$(".password-helper").html('');
	$(".confPassword-helper").html('');
	$(".fullName-helper").html('');
	$(".email-helper").html('');
	$(".phoneNumber-helper").html('');
	$(".address-helper").html('');
	
	
	if (fullName.trim().length == 0) {
		$(".fullName-helper").html('Fullname is required');
		formIsValid = false;
		
		if (!coordinates) {
			coordinates = $("#fullname").offset().top - 50;
		}
	}
	
	if (email.trim().length == 0) {
		$(".email-helper").html('Email is required');
		formIsValid = false;
		
		if (!coordinates) {
			coordinates = $("#email").offset().top - 50;
		}
	} else {
		if (!emailPattern.test(email.trim())) {
			$(".email-helper").html('Email is not valid');
			formIsValid = false;
			
			if (!coordinates) {
				coordinates = $("#email").offset().top - 50;
			}
		}
	}
	
	if (phoneNumber.trim().length == 0) {
		$(".phoneNumber-helper").html('Phone number is required');
		formIsValid = false;
		
		if (!coordinates) {
			coordinates = $("#phoneNumber").offset().top - 50;
		}
	} else {
		if (!phonePattern.test(phoneNumber.trim())) {
			$(".phoneNumber-helper").html('Phone number is not valid');
			formIsValid = false;
			
			if (!coordinates) {
				coordinates = $("#phoneNumber").offset().top - 50;
			}
		}
	}
	
	if (address.trim().length == 0) {
		$(".address-helper").html('Address is required');
		formIsValid = false;
	
		if (!coordinates) {
			coordinates = $("#address").offset().top - 50;
		}
	}
	
	$("html").animate({ scrollTop: coordinates }, 500);
	
	return formIsValid;
}

const updateUserProfile = () => {
	$('body').on('click', '.login-btn', function(e) {
		e.preventDefault();
		
		let fullname = $('#fullname').val();
		let email = $('#email').val();
		let phoneNumber = $('#phoneNumber').val();
		let address = $('#address').val();
		let sex = $('#male').is(':checked') ? 1 : 0;
		
		let payload = {
			fullName: fullname, email, phoneNumber, address, sex
		};
		
		let isValid = isUserValid(payload);
		
		if (isValid) {
			alert('valid');
			$('.login-btn').submit();
		} else {
			alert('invalid');
		}
	})
}

$(function() {
	alert('load')
	updateUserProfile();
})