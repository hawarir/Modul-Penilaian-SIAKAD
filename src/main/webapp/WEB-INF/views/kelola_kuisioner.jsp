<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/favicon_16.ico">
	<title>Kelola Kuisioner Dosen</title>
	
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<meta charset="UTF-8">
	<meta name="description" content="Admin Dashboard Template" />
	<meta name="keywords" content="admin,dashboard" />
	<meta name="author" content="Steelcoders" />
	
	<!-- Styles -->
	<link
		href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600'
		rel='stylesheet' type='text/css'>
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/uniform/css/uniform.default.min.css"
		rel="stylesheet" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/bootstrap/css/bootstrap.min.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/fontawesome/css/font-awesome.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/line-icons/simple-line-icons.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/waves/waves.min.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/switchery/switchery.min.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/3d-bold-navigation/css/style.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/slidepushmenus/css/component.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/toastr/toastr.min.css"
		rel="stylesheet" type="text/css" />
		<link href="${pageContext.servletContext.contextPath}/resources/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>
		
	<!-- Theme Styles -->
	<link
		href="${pageContext.servletContext.contextPath}/resources/css/modern.min.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/css/themes/white.css"
		class="theme-color" rel="stylesheet" type="text/css" />
	<link
		href="${pageContext.servletContext.contextPath}/resources/css/custom.css"
		rel="stylesheet" type="text/css" />
		
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/3d-bold-navigation/js/modernizr.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/offcanvasmenueffects/js/snap.svg-min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/jquery/jquery-2.1.3.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/toastr/toastr.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
	
	<!-- css dan js spesifik -->
	<link
		href="${pageContext.servletContext.contextPath}/resources/plugins/toastr/toastr.min.css"
		rel="stylesheet" />
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/toastr/toastr.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/twitter-bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	        <![endif]-->
</head>
<body style="page-header-fixed page-horizontal-bar">
	<%@include file="header.jsp" %>
	
	<!-- content -->
	<div class="row">
		<div class="container">
			<div class="col-md-8 col-md-offset-2">
				<div class="panel panel-white">
					<div class="panel-heading">
						<h4 class="panel-title">Kelola Kuisioner</h4>
					</div>
					<div class="panel-body">
						<table class="table">
							<thead>
								<tr>
									<th>Nama Kuisioner</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="kuisioner" items="${listKuisioner}">
								<tr id="${kuisioner.getIdKuisioner()}" name="${kuisioner.getIdKuisioner()}">
									<td><c:out value="${kuisioner.getNmKuisioner()}"></c:out></td>
									<td>
										<button type="button" class="btn btn-primary tombolUbahKuisioner" data-toggle="modal" data-target="#modalKuisioner">
											<i class="glyphicon glyphicon-pencil"></i>
										</button>
										<button type="button" class="btn btn-danger tombolHapusKuisioner">
											<i class="glyphicon glyphicon-minus"></i>
										</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#modalKuisioner">Buat Kuisioner Baru</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of content -->
	
	<!-- modal nama kuisioner -->
	<div class="modal fade" id="modalKuisioner" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">Nama Kuisioner</h4>
				</div>
				<div id="contentNamaKuisioner">					
					<div class="modal-body">
						<p>Silahkan masukkan nama kuisioner yang akan dibuat beserta skala penilaian yang akan digunakan.</p>
						<form id="formNamaKuisioner">
							<div class="form-group">
								<label for="namaKuisioner">Nama Kuisioner</label>
								<input type="text" class="form-control" id="namaKuisioner" placeholder="Nama Kuisioner"/>
							</div>
							<div class="form-group">
								<label for="skalaKuisioner">Skala Kuisioner</label>
								<input type="number" class="form-control" id="skalaKuisioner">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary pull-right" id="buttonSubmitNama">Next &gt;</button>
					</div>
				</div>
				
				<div class="hide" id="contentPertanyaanKuisioner">
					<div class="modal-body">
						<p>Silahkan masukkan pertanyaan-pertanyaan dari kuisioner yang akan dibuat.</p>
						<form id="formPertanyaanKuisioner">
							<table class="table">
								<thead>
									<tr>
										<th>Pertanyaan</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr id="rowPertanyaanBaru">
										<td><input type="text" class="form-control" id="pertanyaanBaru"/></td>
										<td><button type="button" class="btn btn-success" id="tombolTambahPertanyaan"><i class="glyphicon glyphicon-plus"></i></button></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary pull-right" id="buttonSubmitPertanyaan">Selesai</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of modal -->
	
	<!-- script khusus -->
	<script>
		$(document).ready(function() {
			var idKuisioner;
			toastr.options = {
					  "closeButton": true,
					  "debug": false,
					  "newestOnTop": false,
					  "progressBar": false,
					  "positionClass": "toast-top-center",
					  "preventDuplicates": true,
					  "showDuration": "300",
					  "hideDuration": "1000",
					  "timeOut": 0,
					  "extendedTimeOut": 0,
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut",
					  "tapToDismiss": true
					}
			
			// close modal
			$("button.close").click(function() {
				if($("#contentNamaKuisioner").hasClass("hide")) {
					$("#contentNamaKuisioner").toggleClass("hide");
				}
				if($("#contentPertanyaanKuisioner").hasClass("hide") == false) {
					$("#contentPertanyaanKuisioner").toggleClass("hide");
				}
				
				$("#namaKuisioner").val("");
				$("#skalaKuisioner").val("");
				$("#pertanyaanBaru").val("");
				
				$("tr.pertanyaan").each(function(index, element) {
					$(element).remove();
				});
			});
			
			// tambah kuisioner
			$("#buttonSubmitNama").click(function() {
				if($("#namaKuisioner").val() != "" && $("#skalaKuisioner").val() != "") {
					var namaKuisioner = $("#namaKuisioner").val();
					var skalaKuisioner = $("#skalaKuisioner").val();
					
					var kuisioner = {
							"idKuisioner" : null,
							"nmKuisioner" : namaKuisioner,
							"skalaKuisioner" : skalaKuisioner,
							"aKuisionerAktif" : true
					};
					
					$.ajax({
						url : "tambah_kuisioner/",
						type : "POST",
						contentType: "application/json",
						data : JSON.stringify(kuisioner),
						success : function(data) {
							if(data.status == "ok") {
								idKuisioner = data.data;
							}
						}
					});
					
					$("#contentNamaKuisioner, #contentPertanyaanKuisioner").toggleClass("hide");
				}
			});
			
			// hapus kuisioner
			$("body").on("click", ".tombolHapusKuisioner", function() {
				idKuisioner = $(this).closest("tr").attr("name");
				toastr["warning"]("Apakah anda yakin akan menghapus kuisioner?<br /><br /><button type='button' class='btn btn-success' id='konfirmasiHapusKuisioner'>Ya</button> <button type='button' class='btn btn-danger'>Tidak</button>", "Peringatan");
			});
			
			$("body").on("click", "#konfirmasiHapusKuisioner", function() {
				$.ajax({
					url : "hapus_kuisioner/",
					type : "POST",
					data : {"idKuisioner" : idKuisioner},
					success : function(data) {
						if(data.status == "ok") {
							$("body").find("#" + idKuisioner).remove();
							toastr["success"]("Kuisioner berhasil dihapus", "Sukses");
						}
					},
					dataType: "json"
				});
			});
			
			// tambah pertanyaan
			$("#tombolTambahPertanyaan").click(function() {
				if($("#pertanyaanBaru").val() != "") {
					var pertanyaan = $("#pertanyaanBaru").val();
					var jsonPertanyaan = {
						"idPertanyaan" : null,
						"pertanyaan" : pertanyaan,
						"idKuisioner" : idKuisioner
					};
					
					$.ajax({
						url : "tambah_pertanyaan/",
						type : "POST",
						contentType: "application/json",
						data : JSON.stringify(jsonPertanyaan),
						success : function(data) {
							if(data.status == "ok") {
								$("#rowPertanyaanBaru").before('<tr class="pertanyaan">'
									+ '<td><input type="text" class="form-control pertanyaanKuisionerBaru" value="' + pertanyaan + '"/></td>'
									+ '<td><button type="button" class="btn btn-danger tombolHapusPertanyaan" name="' + data.data + '"><i class="glyphicon glyphicon-minus"></i></button></td>'
									+ '</tr>'
								);
								$("#pertanyaanBaru").val("");
							}
						}
					});
				}
			});
			
			// hapus pertanyaan
			$("body").on("click", ".tombolHapusPertanyaan", function() {
				var idPertanyaan = $(this).attr('name');
				var tombol = $(this);
				
				$.ajax({
					url : "hapus_pertanyaan/",
					type : "POST",
					data : {"idPertanyaan" : idPertanyaan},
					success : function(data) {
						$(tombol).closest("tr").remove();
					},
					dataType : 'json'
				});
			});
			
			//simpan pertanyaan
			$("#buttonSubmitPertanyaan").click(function() {
				var listPertanyaan = new Array();
				$("tr.pertanyaan").each(function(index, element) {
					var idPertanyaan = $(element).find("button").attr("name");
					var pertanyaan = $(element).find("input").val();
					
					var jsonPertanyaan = {
						"idPertanyaan" : idPertanyaan,
						"pertanyaan" : pertanyaan,
						"idKuisioner" : idKuisioner
					};
					
					listPertanyaan.push(jsonPertanyaan);
				});
				
				$.ajax({
					url : "simpan_pertanyaan/",
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify(listPertanyaan),
					success : function(data) {
						location.reload();
					}
				});
			});
			
			//ambil pertanyaan
			$("body").on("click", ".tombolUbahKuisioner", function() {
				idKuisioner = $(this).closest("tr").attr("name");
				$("#contentNamaKuisioner, #contentPertanyaanKuisioner").toggleClass("hide");
				
				$.ajax({
					url : "ambil_pertanyaan/",
					type : "POST",
					data : {"idKuisioner" : idKuisioner},
					success : function(data) {
						for(i=0; i<data.data.length; i++) {
							$("#rowPertanyaanBaru").before('<tr class="pertanyaan">'
								+ '<td><input type="text" class="form-control pertanyaanKuisionerBaru" value="' + data.data[i].pertanyaan + '"/></td>'
								+ '<td><button type="button" class="btn btn-danger tombolHapusPertanyaan" name="' + data.data[i].idPertanyaanKuisioner + '"><i class="glyphicon glyphicon-minus"></i></button></td>'
								+ '</tr>'
							);
						}
					},
					dataType : "json"
				});
			});			
		});
	</script>
	<!-- end of script khusus -->
	
	<%@include file="footer.jsp" %>
</body>
</html>