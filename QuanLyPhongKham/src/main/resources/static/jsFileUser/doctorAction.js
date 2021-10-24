/**
 * 
 */
$(document).ready(function() {
	getListDoctors();
});

function getListDoctors() {
	$("#doctorListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listDoctors"
	}).done(function(result) {
		$.each(result, function(index, doctor) {
			var row = "<tr><td>" + doctor.doctorName + "</td><td>" + doctor.gender + "</td><td>" + doctor.phoneNumber + "</td><td>" + doctor.department.departmentName + "</td></tr>";
			$("#doctorListTable tbody").append(row);
		});
	}).fail(function(){
		alert("aaa");
	});
}