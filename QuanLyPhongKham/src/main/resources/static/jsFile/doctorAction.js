/**
 * 
 */
$(document).ready(function() {
	getListDoctors();

	$("#searchDoctors").on("input", function() {
		listDoctors();
	});

	getListDepartments();

	$("#addDoctorForm").submit(function(evt) {
		evt.preventDefault();
		addDoctor($(this));
	});

	$("#editDoctorForm").submit(function(evt) {
		evt.preventDefault();
		editDoctor($(this));
	});

	$("#deleteDoctorForm").submit(function(evt) {
		evt.preventDefault();
		deleteDoctor($(this));
	});

	$(document).on("click", "#doctorListTable tbody tr", function() {
		var doctorID = $(this).find("td:eq(0)").text();
		var doctorName = $(this).find("td:eq(1)").text();
		var gender = $(this).find("td:eq(2)").text();
		var dateOfBirth = $(this).find("td:eq(3)").text();
		var phoneNumber = $(this).find("td:eq(4)").text();
		var identityCardNumber = $(this).find("td:eq(5)").text();
		var address = $(this).find("td:eq(6)").text();
		var salary = $(this).find("td:eq(7)").text();
		var department = $(this).find("td:eq(8)").text();
		var departmentID = $(this).find("td:eq(9)").text();
		$("#editDoctorID").val(doctorID);
		$("#editDoctorName").val(doctorName);
		$("#editDoctorGender").val(gender);
		$("#editDoctorDateOfBirth").val(dateOfBirth);
		$("#editDoctorPhoneNumber").val(phoneNumber);
		$("#editDoctorIdentityCardNumber").val(identityCardNumber);
		$("#editDoctorAddress").val(address);
		$("#editDoctorSalary").val(salary);
		$("#editDoctorDepartment").val(department);
		$("#deleteDoctorID").val(doctorID);
		$("#deleteDoctorName").val(doctorName);
		$("#deleteDoctorGender").val(gender);
		$("#deleteDoctorDateOfBirth").val(dateOfBirth);
		$("#deleteDoctorPhoneNumber").val(phoneNumber);
		$("#deleteDoctorIdentityCardNumber").val(identityCardNumber);
		$("#deleteDoctorAddress").val(address);
		$("#deleteDoctorSalary").val(salary);
	});
});

function getListDoctors() {
	$("#doctorListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listDoctors"
	}).done(function(result) {
		$.each(result, function(index, doctor) {
			var row = "<tr><td hidden = 'hidden'>" + doctor.id + "</td><td>" + doctor.doctorName + "</td><td>" + doctor.gender + "</td><td>" + doctor.dateOfBirth + "</td><td>" + doctor.phoneNumber + "</td><td>" + doctor.identityCardNumber + "</td><td>" + doctor.address + "</td><td>" + doctor.salary + "</td><td>" + doctor.department.departmentName + "</td><td hidden = 'hidden'>" + doctor.department.id + "</td></tr>";
			$("#doctorListTable tbody").append(row);
		});
	});
}

function listDoctors() {
	if ($("#searchDoctors").val() == "") {
		getListDoctors();
	}
	else {
		$("#doctorListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listDoctors"
		}).done(function(result) {
			$.each(result, function(index, doctor) {
				if (doctor.doctorName.toLowerCase().includes($("#searchDoctors").val().toLowerCase()) || doctor.phoneNumber == $("#searchDoctors").val()) {
					var row = "<tr><td hidden = 'hidden'>" + doctor.id + "</td><td>" + doctor.doctorName + "</td><td>" + doctor.gender + "</td><td>" + doctor.dateOfBirth + "</td><td>" + doctor.phoneNumber + "</td><td>" + doctor.identityCardNumber + "</td><td>" + doctor.address + "</td><td>" + doctor.salary + "</td><td>" + doctor.department.departmentName + "</td><td hidden = 'hidden'>" + doctor.department.id + "</td></tr>";
					$("#doctorListTable tbody").append(row);
				}
			});
		});
	}
}

function getListDepartments() {
	$.ajax({
		type: "GET",
		url: contextPath + "listDepartments"
	}).done(function(result) {
		$.each(result, function(index, department) {
			var row = "<option value =" + department.id + ">" + department.departmentName + "</option>";
			$("#doctorDepartment").append(row);
			$("#editDoctorDepartment").append(row);
		});
	});
}

function addDoctor(link) {
	var formData = {
		doctorName: $("#doctorName").val(),
		gender: $("#doctorGender option:selected").val(),
		dateOfBirth: $("#doctorDateOfBirth").val(),
		phoneNumber: $("#doctorPhoneNumber").val(),
		identityCardNumber: $("#doctorIdentityCardNumber").val(),
		address: $("#doctorAddress").val(),
		salary: $("#doctorSalary").val(),
		department: {
			id: $("#doctorDepartment  option:selected").val()
		}
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addDoctor",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		getListDoctors();
	}).fail(function() {
		alert("aaa");
	});
}

function editDoctor(link) {
	var formData = {
		doctorName: $("#editDoctorName").val(),
		gender: $("#editDoctorGender option:selected").val(),
		dateOfBirth: $("#editDoctorDateOfBirth").val(),
		phoneNumber: $("#editDoctorPhoneNumber").val(),
		identityCardNumber: $("#editDoctorIdentityCardNumber").val(),
		address: $("#editDoctorAddress").val(),
		salary: $("#editDoctorSalary").val(),
		department: {
			id: $("#editDoctorDepartment  option:selected").val(),
			departmentName: $("#editDoctorDepartment  option:selected").text()
		}
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editDoctor/" + $("#editDoctorID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#editDoctorID").val('');
		$("#editDoctorName").val('');
		$("#editDoctorGender").val('');
		$("#editDoctorDateOfBirth").val('');
		$("#editDoctorPhoneNumber").val('');
		$("#editDoctorIdentityCardNumber").val('');
		$("#editDoctorAddress").val('');
		$("#editDoctorSalary").val('');
		$("#editDoctorDepartment").val('');
		$("#deleteDoctorID").val('');
		$("#deleteDoctorName").val('');
		$("#deleteDoctorGender").val('');
		$("#deleteDoctorDateOfBirth").val('');
		$("#deleteDoctorPhoneNumber").val('');
		$("#deleteDoctorIdentityCardNumber").val('');
		$("#deleteDoctorAddress").val('');
		$("#deleteDoctorSalary").val('');
		getListDoctors();
	}).fail(function() {
		alert("aaa");
	});
}

function deleteDoctor(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deleteDoctor/" + $("#deleteDoctorID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#editDoctorID").val('');
			$("#editDoctorName").val('');
			$("#editDoctorGender").val('');
			$("#editDoctorDateOfBirth").val('');
			$("#editDoctorPhoneNumber").val('');
			$("#editDoctorIdentityCardNumber").val('');
			$("#editDoctorAddress").val('');
			$("#editDoctorSalary").val('');
			$("#editDoctorDepartment").val('');
			$("#deleteDoctorID").val('');
			$("#deleteDoctorName").val('');
			$("#deleteDoctorGender").val('');
			$("#deleteDoctorDateOfBirth").val('');
			$("#deleteDoctorPhoneNumber").val('');
			$("#deleteDoctorIdentityCardNumber").val('');
			$("#deleteDoctorAddress").val('');
			$("#deleteDoctorSalary").val('');
			getListDoctors();
		}).fail(function() {

		});
	}
}