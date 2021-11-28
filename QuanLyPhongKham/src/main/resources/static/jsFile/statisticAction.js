/**
 * 
 */

$(document).ready(function() {
	$("#statistic").on("click", function(evt) {
		evt.preventDefault();
		watchStatistic($(this));
	});
	
	$("#medicalNoteStatistic").on("click", function(evt) {
		evt.preventDefault();
		watchMedicalNoteStatistic($(this));
	});

	/*	$("#addProductForm").submit(function(evt) {
			evt.preventDefault();
			ajaxAddProduct($(this));
		});*/
});

function watchStatistic(link) {
	month = $("#month option:selected").text();
	year = $("#year option:selected").text();
	url = contextPath + "statistic/" + month + "/" + year;
	$.ajax({
		type: "GET",
		url: url
	}).done(function(result) {
		billsByMonthAndYear(month, year);
		document.getElementById("statisticResult").innerHTML = "STATISTIC: " + result + " VND";
	});
}

function billsByMonthAndYear(month, year) {
	document.getElementById("billsByMonthAndYearTitle").innerHTML = "STATISTIC TABLE IN " + month + "/" + year;
	$("#medicineTbody").empty();
	url = contextPath + "billsByMonthAndYear/" + month + "/" + year;
	$.ajax({
		type: "GET",
		url: url
	}).done(function(result) {
		$.each(result , function(i , bill) {
			$("#medicineTbody").append("<tr><td>"+bill.medicine.medicineName+"</td><td>"+bill.quantity+"</td><td>"+bill.medicine.price+"</td><td>"+bill.toTal+"</td><td>"+bill.buyDate+"</td><td>"+bill.phoneNumber+"</td><td>"+bill.user.username+"</td><td>"+bill.address+"</td></tr>");
		});
	});
}

function watchMedicalNoteStatistic(link){
	month = $("#medicalNoteMonth option:selected").text();
	year = $("#medicalNoteYear option:selected").text();
	url = contextPath + "medicalNoteStatistic/" + month + "/" + year;
	$.ajax({
		type: "GET",
		url: url
	}).done(function(result) {
		medicalNotesByMonthAndYear(month,year);
		document.getElementById("medicalNoteStatisticResult").innerHTML = "STATISTIC: " + result + " VND";
	});
}

function medicalNotesByMonthAndYear(month,year){
	$("#medicalNoteTbody").empty();
	url = contextPath + "listMedicalNoteStatistic/" + month + "/" + year;
	$.ajax({
		type: "GET",
		url: url
	}).done(function(result) {
		$.each(result , function(i , bill) {
			$("#medicalNoteTbody").append("<tr><td>"+bill.patient.fullName+"</td><td>"+bill.patient.phoneNumber+"</td><td>"+bill.symptom+"</td><td>"+bill.conclusion+"</td><td>"+bill.date+"</td><td>"+bill.examination.examinationName+"</td><td>"+bill.totalMoney+"</td></tr>");
		});
	});
}