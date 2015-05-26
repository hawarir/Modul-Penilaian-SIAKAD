<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/favicon_16.ico">
	<link rel="bookmark" href="${pageContext.servletContext.contextPath}/resources/favicon_16.ico">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/site.min.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/sia.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/gritter/css/jquery.gritter.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/site.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/gritter/js/jquery.gritter.js"></script>
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js">
	</script>
		  <script src="assets/js/respond.min.js">
	</script>
	<![endif]-->
	<script>
		var context_path = "${pageContext.servletContext.contextPath}";
	</script>
	<title>Konversi Nilai</title>
</head>
<body style="background:url(${pageContext.servletContext.contextPath}/resources/img/wild_flowers.png) repeat 0 0">
	<div class="container">
		<div class="wrapper">
			<%@include file="header.jsp" %>
			
			<!-- breadcrumb -->
			<div class="row">
				<div class="container">
					<div class="col-md-12" style="margin-bottom:10px;">
						<ol class="breadcrumb">
							<li>
								<a href="${pageContext.servletContext.contextPath}/">Beranda</a>
							</li>
							<li>
								<a href="#">Konversi Nilai</a>
							</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- breadcrumb -->
			
			<!-- content -->
			<div class="row">
				<div class="container">
					<form>
						<div class="row">
							<div class="container">
								<div class="col-md-6 col-md-offset-3">
									<table class="table">
										<thead>
											<tr>
												<th>Indeks Huruf</th>
												<th>Indeks Nilai</th>
												<th>Batas Nilai</th>
												<th>Action</th>
											</tr>
										<thead>
										<tbody>
											<c:forEach var="konversi" items="${listKonversi}">
											<tr class="row-konversi">
												<td><input type="text" class="form-control hurufKonversi" value="${konversi.getHuruf()}" /></td>
												<td><input type="text" class="form-control nilaiKonversi" value="${konversi.getNilaiHuruf()}" /></td>
												<td><input type="text" class="form-control batasKonversi" value="${konversi.getBatasBawah()}" /></td>
												<td><button type="button" class="btn btn-danger tombolHapusKonversi" name="${konversi.getIdKonversi()}"><i class="glyphicon glyphicon-minus"></i></button></td>
											</tr>
											</c:forEach>
											<tr id="newKonversi">
												<td><input type="text" class="form-control" id="hurufKonversiNew" value="" /></td>
												<td><input type="text" class="form-control" id="nilaiKonversiNew" value="" /></td>
												<td><input type="text" class="form-control" id="batasKonversiNew" value="" /></td>
												<td><button type="button" class="btn btn-success" id="tombolTambahKonversi"><i class="glyphicon glyphicon-plus"></i></button></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="container">
								<div class="col-md-6 col-md-offset-3">
									<button type="button" class="btn btn-primary pull-right" id="tombolSimpanKonversi">Simpan</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- end of content -->
			
			<!-- script ajax -->
			<script>
				$(document).ready(function() {
					//script tambah konversi
					$("#tombolTambahKonversi").click(function() {
						if($("#hurufKonversiNew").val() != "" && $("#nilaiKonversiNew").val() != "" && $("#batasKonversiNew").val() != "") {
							var huruf = $("#hurufKonversiNew").val();
							var nilai = $("#nilaiKonversiNew").val();
							var batas = $("#batasKonversiNew").val();
							
							var konversi = {
									"idKonversi" : null,
									"huruf" : huruf,
									"nilaiHuruf" : nilai,
									"batasBawah" : batas,
									"aStatusKonversiAktif" : true
							};
							
							$.ajax({
								url : "tambah_konversi/",
								type : "POST",
								contentType : "application/json",
								data : JSON.stringify(konversi),
								success : function(data) {
									if(data.status == "ok") {
										$("#newKonversi").before('<tr class="row-konversi">'
											+ '<td><input type="text" class="form-control hurufKonversi" value="' + huruf + '" /></td>'
											+ '<td><input type="text" class="form-control nilaiKonversi" value="' + nilai + '" /></td>'
											+ '<td><input type="text" class="form-control batasKonversi" value="' + batas + '" /></td>'
											+ '<td><button type="button" class="btn btn-danger tombolHapusKonversi" name="' + data.data + '"><i class="glyphicon glyphicon-minus"></i></button></td>'
											+ '</tr>'
										);
										$("#hurufKonversiNew").val("");
										$("#nilaiKonversiNew").val("");
										$("#batasKonversiNew").val("");
										$.gritter.add({
											title : 'Notifikasi',
											text : data.message,
											sticky : false,
											time : 200
										});
									}
									else if(data.status == "fail") {
										$.gritter.add({
											title : 'Notifikasi',
											text : data.message,
											sticky : true,
											time : 200
										});
									}
								}
							});
						}
					});
					
					//script hapus konversi
					$(".tombolHapusKonversi").click(function() {
						var idKonversi = $(this).attr("name");
						var button = $(this);
						$.ajax({
							url : "hapus_konversi/",
							type : "POST",
							data : {"idKonversi" : idKonversi},
							success : function(data) {
								if(data.status == "ok") {
									$(button).closest("tr").remove();
									$.gritter.add({
										title : 'Notifikasi',
										text : data.message,
										sticky : false,
										time : 200
									});
								}
							},
							dataType : "json"
						});
					});
					
					//script simpan konversi
					$("#tombolSimpanKonversi").click(function() {
						var listKonversi = new Array();
						var aNilaiNolAda = false;
						
						$("tr.row-konversi").each(function(index, element) {
							var idKonversi = $(element).find("button").attr("name");
							var huruf = $(element).find("input.hurufKonversi").val();
							var nilai = $(element).find("input.nilaiKonversi").val();
							var batas = $(element).find("input.batasKonversi").val();
							
							if (nilai == 0) {
								aNilaiNolAda = true;
							}
							
							var konversi = {
									"idKonversi" : idKonversi,
									"huruf" : huruf,
									"nilaiHuruf" : nilai,
									"batasBawah" : batas,
									"aStatusKonversiAktif" : true
							};
							
							listKonversi.push(konversi);
						});
						
						if(aNilaiNolAda) {
							$.ajax({
								url : "simpan_konversi/",
								type : "POST",
								contentType : "application/json",
								data : JSON.stringify(listKonversi),
								success : function(data) {
									if(data.status == "ok") {
										$.gritter.add({
											title : 'Notifikasi',
											text : data.message,
											sticky : false,
											time : 200
										});
									}
									else if(data.status == "fail") {
										$.gritter.add({
											title : 'Notifikasi',
											text : data.message,
											sticky : true,
											time : 200
										});
									}
								}
							});
						}
						else {
							$.gritter.add({
								title : 'Notifikasi',
								text : 'Harus terdapat konversi nilai dengan batas bawah nol',
								sticky : true,
								time : 200
							});
						}
					});
				});
			</script>
			<!-- end of script ajax -->
			
			<%@include file="footer.jsp" %>
		</div>
	</div>
</body>
</html>