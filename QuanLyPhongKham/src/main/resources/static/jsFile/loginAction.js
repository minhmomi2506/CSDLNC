$(document).ready(function() {
/*
	$("#a").on("click", function() {
		alert("aaa");
	});*/
	$("#password").on("input", function() {
		checkLengthPassword();
	});
	
	$("#username").on("input", function() {
		checkDuplicateAccount();
	});
});

function checkLengthPassword() {
	if ($("#password").val().length < 4) {
		document.getElementById("passwordAlert").style.display = "block";
		document.getElementById("passwordAlert").innerHTML = "Mật khẩu phải dài ít nhất 4 kí tự";
		document.getElementById("signUp").disabled = true;
	}
	else {
		document.getElementById("passwordAlert").style.display = "none";
		document.getElementById("signUp").disabled = false;
		/*$.ajax({
			type: "GET",
			url: contextPath + "listUsers"
		}).done(function(result) {
			$.each(result, function(index, user) {
				alert(user.id);
			});
		});*/
	}
}

function checkDuplicateAccount() {
	$.ajax({
		type: "GET",
		url: contextPath + "listUsers"
	}).done(function(result) {
		let count = 0;
		$.each(result, function(index, user) {
			if (user.username == $("#username").val()) {
				document.getElementById("usernameAlert").style.display = "block";
				document.getElementById("usernameAlert").innerHTML = "Đã có tài khoản trùng username bạn đang đăng kí";
				document.getElementById("signUp").disabled = true;
				count++;
			}
		});
		if (count == 0) {
			document.getElementById("usernameAlert").style.display = "none";
			document.getElementById("signUp").disabled = false;
		}
	});
}
