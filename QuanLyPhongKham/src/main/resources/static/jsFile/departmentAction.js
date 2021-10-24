/**
 * 
 */
$(document).ready(function() {
	getListDepartments();

	$("#searchDepartments").on("input", function() {
		listDepartments();
	});

	$("#addDepartmentForm").submit(function(evt) {
		evt.preventDefault();
		addDepartment($(this));
	});

	$("#editDepartmentForm").submit(function(evt) {
		evt.preventDefault();
		editDepartment($(this));
	});

	$("#deleteDepartmentForm").submit(function(evt) {
		evt.preventDefault();
		deleteDepartment($(this));
	});

	$(document).on("click", "#departmentListTable tbody tr", function() {
		var departmentID = $(this).find("td:eq(0)").text();
		var departmentName = $(this).find("td:eq(1)").text();
		$("#editDepartmentID").val(departmentID);
		$("#editDepartmentName").val(departmentName);
		$("#deleteDepartmentID").val(departmentID);
		$("#deleteDepartmentName").val(departmentName);
	});
});


function listDepartments() {
	if ($("#searchDepartments").val() == "") {
		getListDepartments()
	}
	else {
		$("#departmentListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listDepartments"
		}).done(function(result) {
			$.each(result, function(index, department) {
				if (department.departmentName.toLowerCase().includes($("#searchDepartments").val().toLowerCase())) {
					var row = "<tr><td hidden = 'hidden'>" + department.id + "</td><td>" + department.departmentName + "</td></tr>";
					$("#departmentListTable tbody").append(row);
				}
			});
		});
	}
}

function getListDepartments() {
	$("#departmentListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listDepartments"
	}).done(function(result) {
		$.each(result, function(index, department) {
			var row = "<tr><td hidden = 'hidden'>" + department.id + "</td><td>" + department.departmentName + "</td></tr>";
			$("#departmentListTable tbody").append(row);
		});
	});
}

function addDepartment(link) {
	var formData = {
		departmentName: $("#departmentName").val()
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addDepartment",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#departmentName").val('');
		getListDepartments();
	});
}

function editDepartment(link) {
	var formData = {
		departmentName: $("#editDepartmentName").val()
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editDepartment/" + $("#editDepartmentID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#deleteDepartmentID").val('');
		$("#deleteDepartmentName").val('');
		$("#editDepartmentID").val('');
		$("#editDepartmentName").val('');
		getListDepartments();
	});
}

function deleteDepartment(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deleteDepartment/" + $("#deleteDepartmentID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#deleteDepartmentID").val('');
			$("#deleteDepartmentName").val('');
			$("#editDepartmentID").val('');
			$("#editDepartmentName").val('');
			getListDepartments();
		}).fail(function() {

		});
	}
}
