$(document).ready(function() {
	getListMedicines();

	$("#searchMedicines").on("input", function() {
		listMedicines();
	});

	$(document).on("click", "#medicineListTable tbody tr", function() {
		var medicineID = $(this).find("td:eq(0)").text();
		var medicineName = $(this).find("td:eq(1)").text();
		var price = $(this).find("td:eq(2)").text();
		$("#medicineID").val(medicineID);
		$("#medicineName").val(medicineName);
		$("#medicinePrice").val(price);
	});
	
	$("#buyMedicine").on("click", function() {
		addMedicineToCart();
	});
});

function addMedicineToCart(){
	$.ajax({
			type: "POST",
			url: contextPath +"addMedicineToCart/"+$("#medicineID").val()+"/"+$("#medicineNumber").val(),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function(addedQuantity) {
			alert(" Thuốc đã được thêm vào giỏ hàng thành công");
		}).fail(function() {
			alert("Fail");
		})
}

function getListMedicines() {
	$("#medicineListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listMedicines"
	}).done(function(result) {
		$.each(result, function(index, medicine) {
			var row = "<tr><td hidden = 'hidden'>" + medicine.id + "</td><td>" + medicine.medicineName + "</td><td>" + medicine.price + "</td></tr>";
			$("#medicineListTable tbody").append(row);
		});
	});
}

function listMedicines() {
	if ($("#searchMedicines").val() == "") {
		getListMedicines();
	}
	else {
		$("#medicineListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listMedicines"
		}).done(function(result) {
			$.each(result, function(index, medicine) {
				if (medicine.medicineName.toLowerCase().includes($("#searchMedicines").val().toLowerCase())) {
					var row = "<tr><td hidden = 'hidden'>" + medicine.id + "</td><td>" + medicine.medicineName + "</td><td>" + medicine.price + "</td></tr>";
					$("#medicineListTable tbody").append(row);
				}
			});
		});
	}
}