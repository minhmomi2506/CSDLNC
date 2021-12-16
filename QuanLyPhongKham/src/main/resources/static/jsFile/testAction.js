$(document).ready(function() {
	getListTests();

	$("#addTestForm").submit(function(evt) {
		evt.preventDefault();
		addTest($(this));
	});

	$("#searchTests").on("input", function() {
		listTests();
	});

	$(document).on("click", "#testListTable tbody tr", function() {
		var examinationID = $(this).find("td:eq(0)").text();
		var examinationName = $(this).find("td:eq(1)").text();
		var price = $(this).find("td:eq(2)").text();
		$("#editTestID").val(examinationID);
		$("#editTestName").val(examinationName);
		$("#editTestPrice").val(price);
		$("#deleteTestID").val(examinationID);
		$("#deleteTestName").val(examinationName);
		$("#deleteTestPrice").val(price);
	});

	$("#editTestForm").submit(function(evt) {
		evt.preventDefault();
		editTest($(this));
	});

	$("#deleteTestForm").submit(function(evt) {
		evt.preventDefault();
		deleteTest($(this));
	});
});

function getListTests() {
	$("#testListTable tbody").empty();
	$.ajax({
		type: "GET",
		url: contextPath + "listTests"
	}).done(function(result) {
		$.each(result, function(index, test) {
			var row = "<tr><td hidden = 'hidden'>" + test.id + "</td><td>" + test.testName + "</td><td>" + test.price + "</td></tr>";
			$("#testListTable tbody").append(row);
		});
	});
}

function listTests() {
	if ($("#searchTests").val() == "") {
		getListTests();
	}
	else {
		$("#testListTable tbody").empty();
		$.ajax({
			type: "GET",
			url: contextPath + "listTests"
		}).done(function(result) {
			$.each(result, function(index, test) {
				if (test.testName.toLowerCase().includes($("#searchTests").val().toLowerCase())) {
					var row = "<tr><td hidden = 'hidden'>" + test.id + "</td><td>" + test.testName + "</td><td>" + test.price + "</td></tr>";
					$("#testListTable tbody").append(row);
				}
			});
		});
	}
}

function addTest(link) {
	var formData = {
		testName: $("#testName").val(),
		price: $("#testPrice").val()
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: contextPath + "addTest",
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		getListTests();
	}).fail(function() {

	});
}

function editTest(link) {
	var formData = {
		testName: $("#editTestName").val(),
		price: $("#editTestPrice").val()
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: contextPath + "editTest/" + $("#editTestID").val(),
		data: JSON.stringify(formData),
		dataType: 'json',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		}
	}).done(function() {
		$("#deleteTestID").val('');
		$("#deleteTestName").val('');
		$("#deleteTestPrice").val('');
		$("#editTestID").val('');
		$("#editTestName").val('');
		$("#editTestPrice").val('');
		getListTests();
	}).fail(function() {

	});
}

function deleteTest(link) {
	result = confirm("Delete?");
	if (result) {
		url = contextPath + "deleteTest/" + $("#deleteTestID").val();
		$.ajax({
			type: "DELETE",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
		}).done(function() {
			$("#deleteTestID").val('');
			$("#deleteTestName").val('');
			$("#deleteTestPrice").val('');
			$("#editTestID").val('');
			$("#editTestName").val('');
			$("#editTestPrice").val('');
			getListTests();
		}).fail(function() {

		});
	}
}