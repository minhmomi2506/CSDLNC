$(document).ready(function() {
	getListMedicines();

	$("#addMedicineForm").submit(function(evt) {
		evt.preventDefault();
		addMedicine($(this));
	});

	$("#editMedicineForm").submit(function(evt) {
		evt.preventDefault();
		editMedicine($(this));
	});

	$("#searchMedicines").on("input", function() {
		listMedicines();
	});

	$(document).on("click", "#medicineListTable tbody tr", function() {
		var medicineID = $(this).find("td:eq(0)").text();
		var medicineName = $(this).find("td:eq(1)").text();
		var price = $(this).find("td:eq(2)").text();
		$("#deleteMedicineID").val(medicineID);
		$("#deleteMedicineName").val(medicineName);
		$("#deleteMedicinePrice").val(price);
		$("#editMedicineID").val(medicineID);
		$("#editMedicineName").val(medicineName);
		$("#editMedicinePrice").val(price);
	});

	$("#deleteMedicineForm").submit(function(evt) {
		evt.preventDefault();
		deleteMedicine($(this));
	});
});

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

function addMedicine(link) {
	var formData = {
		medicineName: $("#medicineName").val(),
		price: $("#medicinePrice").val()
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addMedicine",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#medicineName").val('');
		$("#medicinePrice").val('');
		getListMedicines();
	});
}

function editMedicine(link) {
	var formData = {
		medicineName: $("#editMedicineName").val(),
		price: $("#editMedicinePrice").val()
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editMedicine/" + $("#editMedicineID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#deleteMedicineID").val('');
		$("#deleteMedicineName").val('');
		$("#deleteMedicinePrice").val('');
		$("#editMedicineID").val('');
		$("#editMedicineName").val('');
		$("#editMedicinePrice").val('');
		getListMedicines();
	});
}

function deleteMedicine(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deleteMedicine/" + $("#deleteMedicineID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#deleteMedicineID").val('');
			$("#deleteMedicineName").val('');
			$("#deleteMedicinePrice").val('');
			$("#editMedicineID").val('');
			$("#editMedicineName").val('');
			$("#editMedicinePrice").val('');
			getListMedicines();
		}).fail(function() {

		});
	}
}