$(document).ready(function() {
	getListPatients();

	$("#addPatientForm").submit(function(evt) {
		evt.preventDefault();
		addPatient($(this));
	});

	$("#editPatientForm").submit(function(evt) {
		evt.preventDefault();
		editPatient($(this));
	});

	$("#searchPatients").on("input", function() {
		listPatients();
	});

	$(document).on("click", "#patientListTable tbody tr", function() {
		var patientID = $(this).find("td:eq(0)").text();
		var fullName = $(this).find("td:eq(1)").text();
		var gender = $(this).find("td:eq(2)").text();
		var dateOfBirth = $(this).find("td:eq(3)").text();
		var phoneNumber = $(this).find("td:eq(4)").text();
		var address = $(this).find("td:eq(5)").text();
		$("#editPatientID").val(patientID);
		$("#editPatientName").val(fullName);
		$("#editPatientGender").val(gender);
		$("#editPatientDateOfBirth").val(dateOfBirth);
		$("#editPatientPhoneNumber").val(phoneNumber);
		$("#editPatientAddress").val(address);
		$("#deletePatientID").val(patientID);
		$("#deletePatientName").val(fullName);
		$("#deletePatientGender").val(gender);
		$("#deletePatientDateOfBirth").val(dateOfBirth);
		$("#deletePatientPhoneNumber").val(phoneNumber);
		$("#deletePatientAddress").val(address);
	});

	$("#deletePatientForm").submit(function(evt) {
		evt.preventDefault();
		deletePatient($(this));
	});
});

function getListPatients() {
	$("#patientListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listPatients"
	}).done(function(result) {
		$.each(result, function(index, patient) {
			var row = "<tr><td hidden = 'hidden'>" + patient.id + "</td><td style='color: black'>" + patient.fullName + "</td><td style='color: black'>" + patient.gender + "</td><td style='color: black'>" + patient.dateOfBirth + "</td><td style='color: black'>" + patient.phoneNumber + "</td><td style='color: black'>" + patient.address + "</td></tr>";
			$("#patientListTable tbody").append(row);
		});
	});
}

function listPatients() {
	if ($("#searchPatients").val() == "") {
		getListPatients();
	}
	else {
		$("#patientListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listPatients"
		}).done(function(result) {
			$.each(result, function(index, patient) {
				if (patient.fullName.toLowerCase().includes($("#searchPatients").val().toLowerCase()) || patient.phoneNumber == $("#searchPatients").val()) {
					var row = "<tr><td hidden = 'hidden'>" + patient.id + "</td><td style='color: black'>" + patient.fullName + "</td><td style='color: black'>" + patient.gender + "</td><td style='color: black'>" + patient.dateOfBirth + "</td><td style='color: black'>" + patient.phoneNumber + "</td><td style='color: black'>" + patient.address + "</td></tr>";
					$("#patientListTable tbody").append(row);
				}
			});
		});
	}
}

function addPatient(link) {
	var formData = {
		fullName: $("#patientName").val(),
		gender: $("#patientGender option:selected").val(),
		dateOfBirth: $("#patientDateOfBirth").val(),
		phoneNumber: $("#patientPhoneNumber").val(),
		address: $("#patientAddress").val()
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addPatient",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		getListPatients();
	}).fail(function() {
		alert("aaa");
	});
}

function editPatient(link) {
	var formData = {
		fullName: $("#editPatientName").val(),
		gender: $("#editPatientGender option:selected").val(),
		dateOfBirth: $("#editPatientDateOfBirth").val(),
		phoneNumber: $("#editPatientPhoneNumber").val(),
		address: $("#editPatientAddress").val()
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editPatient/" + $("#editPatientID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		getListPatients();
	}).fail(function() {
		alert("aaa");
	});
}

function deletePatient(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deletePatient/" + $("#deletePatientID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#editPatientID").val('');
			$("#editPatientName").val('');
			$("#editPatientGender").val('');
			$("#editPatientDateOfBirth").val('');
			$("#editPatientPhoneNumber").val('');
			$("#editPatientAddress").val('');
			$("#deletePatientID").val('');
			$("#deletePatientName").val('');
			$("#deletePatientGender").val('');
			$("#deletePatientDateOfBirth").val('');
			$("#deletePatientPhoneNumber").val('');
			$("#deletePatientAddress").val('');
			getListPatients();
		}).fail(function() {

		});
	}
}