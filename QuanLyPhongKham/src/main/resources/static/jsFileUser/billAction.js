/*/**
 * 
 */
$(document).ready(function() {
	$(".link-detail").on("click", function(evt) {
		evt.preventDefault();
		getListBillDetail($(this));
	})
});

function getListBillDetail(link) {
	rowNumber = link.attr("rowNumber");
	$("#billDetailTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listBillDetail/" + rowNumber
	}).done(function(result) {
		$.each(result, function(index, billDetail) {
			var row = "<tr><td>" + billDetail.medicine.medicineName + "</td><td>" + billDetail.medicinePrice + "</td><td>" + billDetail.quantity + "</td><td>" + billDetail.money + "</td></tr>";
			$("#billDetailTable tbody").append(row);
		});
	});
}

