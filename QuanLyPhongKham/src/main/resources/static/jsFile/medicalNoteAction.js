/**
 * 
 */
$(document).ready(function() {
	$("#searchPatients").on("input", function() {
		listPatients();
	});

	$("#addMedicalNoteForm").submit(function(evt) {
		evt.preventDefault();
		addMedicalNote($(this));
	});

	getListPatients();

	$(document).on("click", "#patientListTable tbody tr", function() {
		var patientID = $(this).find("td:eq(0)").text();
		var fullName = $(this).find("td:eq(1)").text();
		var gender = $(this).find("td:eq(2)").text();
		var dateOfBirth = $(this).find("td:eq(3)").text();
		var phoneNumber = $(this).find("td:eq(4)").text();
		var address = $(this).find("td:eq(5)").text();
		$("#patientID").val(patientID);
		$("#patientName").val(fullName);
		$("#patientGender").val(gender);
		$("#patientDateOfBirth").val(dateOfBirth);
		$("#patientPhoneNumber").val(phoneNumber);
		$("#patientAddress").val(address);
	});

	$("#doctorNameDropdown").on("change", function() {
		$("#doctorID").val($("#doctorNameDropdown option:selected").val());
		getDoctorInfo();
	});

	$("#examinationNameDropdown").on("change", function() {
		$("#examinationID").val($("#examinationNameDropdown option:selected").val());
		getExaminationInfo();
	});

	getListDoctors();

	getListExaminations();
});

function addMedicalNote(link) {
	var formData = {
		symptom: $("#symptom").val(),
		conclusion: $("#conclusion").val(),
		totalMoney: $("#totalMoney").val(),
		patient: {
			id: $("#patientID").val()
		},
		doctor: {
			id: $("#doctorID").val()
		},
		examination: {
			id: $("#examinationID").val()
		}

	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addMedicalNote/"+$("#patientID").val()+"/"+$("#doctorID").val()+"/"+$("#examinationID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		alert("Create medical note successfully!");
	}).fail(function() {
		alert("aaa");
	});
}

function getListDoctors() {
	$.ajax({
		type: "GET",
		url: contextPath + "listDoctors"
	}).done(function(result) {
		$.each(result, function(index, doctor) {
			var row = "<option value =" + doctor.id + ">" + doctor.doctorName + "</option>";
			$("#doctorNameDropdown").append(row);
		});
	});
}

function getDoctorInfo() {
	$.ajax({
		type: "GET",
		url: contextPath + "findDoctor/" + $("#doctorID").val()
	}).done(function(result) {
		$("#doctorName").val(result.doctorName);
		$("#doctorPhoneNumber").val(result.phoneNumber);
	});
}

function getExaminationInfo() {
	$.ajax({
		type: "GET",
		url: contextPath + "findExamination/" + $("#examinationID").val()
	}).done(function(result) {
		$("#examinationName").val(result.examinationName);
		$("#examinationPrice").val(result.price);
		$("#totalMoney").val(result.price);
	});
}

function getListExaminations() {
	$.ajax({
		type: "GET",
		url: contextPath + "listExaminations"
	}).done(function(result) {
		$.each(result, function(index, examination) {
			var row = "<option value =" + examination.id + ">" + examination.examinationName + "</option>";
			$("#examinationNameDropdown").append(row);
		});
	});
}

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