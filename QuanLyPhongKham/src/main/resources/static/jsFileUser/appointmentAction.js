/**
 * 
 */
$(document).ready(function() {
	$("#addAppointmentForm").submit(function(evt) {
		evt.preventDefault();
		addAppointment($(this));
	});
	
	getListAppointments();
});

function getListAppointments(){
	$("#appointmentListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listAppointments"
	}).done(function(result) {
		$.each(result, function(index, appointment) {
			var row = "<tr><td hidden = 'hidden'>" + appointment.id + "</td><td>" + appointment.fullName + "</td><td>" + appointment.gender + "</td><td>" + appointment.dateOfBirth + "</td><td>" + appointment.phoneNumber + "</td><td>" + appointment.address + "</td><td>" + appointment.appointmentDate + "</td></tr>";
			$("#appointmentListTable tbody").append(row);
		});
	});
}

function addAppointment(link){
	var formData = {
		fullName: $("#fullName").val(),
		gender: $("#gender option:selected").val(),
		dateOfBirth: $("#dateOfBirth").val(),
		phoneNumber: $("#phoneNumber").val(),
		address: $("#address").val(),
		appointmentDate: $("#appointmentDate").val()
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addAppointment",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		alert("Set appointment successfully!");
		getListAppointments();
	}).fail(function() {
		alert("aaa");
	});
}
