$(document).ready(function() {
	getListCart();

	$(".link-remove").on("click", function() {
		alert("aaa");
	});

	$(document).on("click", "#cartListTable tbody tr", function() {
		var medicineID = $(this).find("td:eq(0)").text();
		result = confirm("Delete?");
		if (result) {
			url = contextPath + "deleteCart/" + medicineID;
			$.ajax({
				type: "DELETE",
				url: url,
				beforeSend: function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				}
			}).done(function() {
				getListCart();
			}).fail(function() {

			});
		}
	});
});

function getListCart() {
	$("#cartListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listCart"
	}).done(function(result) {
		let totalMoney = 0;
		$.each(result, function(index, cart) {
			totalMoney += cart.total;
			var row = "<tr><td hidden = 'hidden'>" + cart.id + "</td><td>" + cart.medicine.medicineName + "</td><td>" + cart.medicine.price + "</td><td>" + cart.quantity + "</td><td>" + cart.total + "</td><td>Click here to delete from cart</td></tr>";
			$("#cartListTable tbody").append(row);
		});
		document.getElementById("totalMoney").innerHTML = "Tổng tiền hóa đơn:" + totalMoney + " VND";
	});
}