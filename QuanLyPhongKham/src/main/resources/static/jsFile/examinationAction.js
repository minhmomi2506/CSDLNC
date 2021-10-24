/**
 * 
 */
$(document).ready(function() {
	$("#addExaminationForm").submit(function(evt) {
		evt.preventDefault();
		addExamination($(this));
	});

	$("#searchExaminations").on("input", function() {
		listExaminations();
	});

	getListExaminations();

	$(document).on("click", "#examinationListTable tbody tr", function() {
		var examinationID = $(this).find("td:eq(0)").text();
		var examinationName = $(this).find("td:eq(1)").text();
		var price = $(this).find("td:eq(2)").text();
		var description = $(this).find("td:eq(3)").text();
		$("#editExaminationID").val(examinationID);
		$("#editExaminationName").val(examinationName);
		$("#editExaminationPrice").val(price);
		$("#editExaminationDescription").val(description);
		$("#deleteExaminationID").val(examinationID);
		$("#deleteExaminationName").val(examinationName);
		$("#deleteExaminationPrice").val(price);
		$("#deleteExaminationDescription").val(description);
	});

	$("#EditExaminationForm").submit(function(evt) {
		evt.preventDefault();
		editExamination($(this));
	});

	$("#DeleteExaminationForm").submit(function(evt) {
		evt.preventDefault();
		deleteExamination($(this));
	});
});

function getListExaminations() {
	$("#examinationListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listExaminations"
	}).done(function(result) {
		$.each(result, function(index, examination) {
			var row = "<tr><td hidden = 'hidden'>" + examination.id + "</td><td>" + examination.examinationName + "</td><td>" + examination.price + "</td><td>" + examination.description + "</td></tr>";
			$("#examinationListTable tbody").append(row);
		});
	});
}

function listExaminations() {
	if ($("#searchExaminations").val() == "") {
		getListExaminations();
	}
	else {
		$("#examinationListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listExaminations"
		}).done(function(result) {
			$.each(result, function(index, examination) {
				if (examination.examinationName.toLowerCase().includes($("#searchExaminations").val().toLowerCase())) {
					var row = "<tr><td hidden = 'hidden'>" + examination.id + "</td><td>" + examination.examinationName + "</td><td>" + examination.price + "</td><td>" + examination.description + "</td></tr>";
					$("#examinationListTable tbody").append(row);
				}
			});
		});
	}
}

function addExamination(link) {
	var formData = {
		examinationName: $("#examinationName").val(),
		price: $("#examinationPrice").val(),
		description: $("#examinationDescription").val()
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addExamination",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		getListExaminations();
	});
}

function editExamination(link) {
	var formData = {
		examinationName: $("#editExaminationName").val(),
		price: $("#editExaminationPrice").val(),
		description: $("#editExaminationDescription").val()
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editExamination/" + $("#editExaminationID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#deleteExaminationID").val('');
		$("#deleteExaminationName").val('');
		$("#deleteExaminationPrice").val('');
		$("#deleteExaminationDescription").val('');
		$("#editExaminationID").val('');
		$("#editExaminationName").val('');
		$("#editExaminationPrice").val('');
		$("#editExaminationDescription").val('');
		getListExaminations();
	});
}

function deleteExamination(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deleteExamination/" + $("#deleteExaminationID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#deleteExaminationID").val('');
			$("#deleteExaminationName").val('');
			$("#deleteExaminationPrice").val('');
			$("#deleteExaminationDescription").val('');
			$("#editExaminationID").val('');
			$("#editExaminationName").val('');
			$("#editExaminationPrice").val('');
			$("#editExaminationDescription").val('');
			getListExaminations();
		}).fail(function() {

		});
	}
}
