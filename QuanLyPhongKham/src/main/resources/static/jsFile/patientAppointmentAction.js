/**
 * 
 */
$(document).ready(function() {
	getListAppointments();

	$("#searchAppointments").on("input", function() {
		listAppointments();
	});

	$(document).on("click", "#appointmentListTable tbody tr", function() {
		var appointmentID = $(this).find("td:eq(0)").text();
		var patientName = $(this).find("td:eq(1)").text();
		var patientGender = $(this).find("td:eq(2)").text();
		var patientDateOfBirth = $(this).find("td:eq(3)").text();
		var patientPhoneNumber = $(this).find("td:eq(4)").text();
		var patientAddress = $(this).find("td:eq(5)").text();
		var appointmentDate = $(this).find("td:eq(6)").text();
		$("#editAppointmentID").val(appointmentID);
		$("#editPatientName").val(patientName);
		$("#editPatientGender").val(patientGender);
		$("#editPatientDateOfBirth").val(patientDateOfBirth);
		$("#editPatientPhoneNumber").val(patientPhoneNumber);
		$("#editPatientAddress").val(patientAddress);
		$("#editAppointmentDate").val(appointmentDate);
		$("#deleteAppointmentID").val(appointmentID);
		$("#deletePatientName").val(patientName);
		$("#deletePatientGender").val(patientGender);
		$("#deletePatientDateOfBirth").val(patientDateOfBirth);
		$("#deletePatientPhoneNumber").val(patientPhoneNumber);
		$("#deletePatientAddress").val(patientAddress);
		$("#deleteAppointmentDate").val(appointmentDate);
	});

	$("#editAppointmentForm").submit(function(evt) {
		evt.preventDefault();
		editAppointment($(this));
	});

	$("#deleteAppointmentForm").submit(function(evt) {
		evt.preventDefault();
		deleteAppointment($(this));
	});

});

function getListAppointments() {
	$("#appointmentListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listAllAppointments"
	}).done(function(result) {
		$.each(result, function(index, appointment) {
			var row = "<tr><td hidden = 'hidden'>" + appointment.id + "</td><td>" + appointment.fullName + "</td><td>" + appointment.gender + "</td><td>" + appointment.dateOfBirth + "</td><td>" + appointment.phoneNumber + "</td><td>" + appointment.address + "</td><td>" + appointment.appointmentDate + "</td></tr>";
			$("#appointmentListTable tbody").append(row);
		});
	});
}

function listAppointments() {
	if ($("#searchAppointments").val() == "") {
		getListAppointments();
	}
	else {
		$("#appointmentListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listAllAppointments"
		}).done(function(result) {
			$.each(result, function(index, appointment) {
				if (appointment.fullName.toLowerCase().includes($("#searchAppointments").val().toLowerCase()) || appointment.phoneNumber.toLowerCase().includes($("#searchAppointments").val().toLowerCase())) {
					var row = "<tr><td hidden = 'hidden'>" + appointment.id + "</td><td>" + appointment.fullName + "</td><td>" + appointment.gender + "</td><td>" + appointment.dateOfBirth + "</td><td>" + appointment.phoneNumber + "</td><td>" + appointment.address + "</td><td>" + appointment.appointmentDate + "</td></tr>";
					$("#appointmentListTable tbody").append(row);
				}
			});
		});
	}
}

function editAppointment(link) {
	var formData = {
		fullName: $("#editPatientName").val(),
		gender: $("#editPatientGender option:selected").val(),
		dateOfBirth: $("#editPatientDateOfBirth").val(),
		phoneNumber: $("#editPatientPhoneNumber").val(),
		address: $("#editPatientAddress").val(),
		appointmentDate: $("#editAppointmentDate").val()
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editAppointment/" + $("#editAppointmentID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#editAppointmentID").val('');
		$("#editPatientName").val('');
		$("#editPatientGender").val('');
		$("#editPatientDateOfBirth").val('');
		$("#editPatientPhoneNumber").val('');
		$("#editPatientAddress").val('');
		$("#editAppointmentDate").val('');
		$("#deleteAppointmentID").val('');
		$("#deletePatientName").val('');
		$("#deletePatientGender").val('');
		$("#deletePatientDateOfBirth").val('');
		$("#deletePatientPhoneNumber").val('');
		$("#deletePatientAddress").val('');
		$("#deleteAppointmentDate").val('');
		getListAppointments();
	}).fail(function() {
		alert("aaa");
	});
}

function deleteAppointment(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deleteAppointment/" + $("#deleteAppointmentID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#editAppointmentID").val('');
			$("#editPatientName").val('');
			$("#editPatientGender").val('');
			$("#editPatientDateOfBirth").val('');
			$("#editPatientPhoneNumber").val('');
			$("#editPatientAddress").val('');
			$("#editAppointmentDate").val('');
			$("#deleteAppointmentID").val('');
			$("#deletePatientName").val('');
			$("#deletePatientGender").val('');
			$("#deletePatientDateOfBirth").val('');
			$("#deletePatientPhoneNumber").val('');
			$("#deletePatientAddress").val('');
			$("#deleteAppointmentDate").val('');
			getListAppointments();
		}).fail(function() {

		});
	}
}