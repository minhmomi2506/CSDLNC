/**
 * 
 */
$(document).ready(function() {
	getListBills();
});

function getListBills(){
	$("#billListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listBills"
	}).done(function(result) {
		$.each(result, function(index, bill) {
			var row = "<tr><td hidden = 'hidden'>" + bill.id + "</td><td>" + bill.medicine.medicineName + "</td><td>" + bill.medicine.price + "</td><td>" + bill.quantity + "</td><td>" + bill.toTal + "</td><td>" + bill.phoneNumber + "</td><td>" + bill.address + "</td><td>" + bill.buyDate + "</td></tr>";
			$("#billListTable tbody").append(row);
		});
	});
}

