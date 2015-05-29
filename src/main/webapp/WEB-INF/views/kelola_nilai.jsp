<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/favicon_16.ico">
	<title>Kelola Nilai Mahasiswa</title>
	
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
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-white">
					<div class="panel-heading">
						<h4 class="panel-title">Daftar Kelas</h4>
					</div>
					<div class="panel-body">						
						<form method="post" action="">
							<div class="form-group">
								<select class="form-control" name="idPemb">
									<c:forEach var="kelas" items="${listKelas}">
									<option value="${kelas.getIdPemb()}"><c:out value="${kelas.getMk().getNamaMK()} ${kelas.getNmPemb()}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<button type="submit" class="btn btn-primary pull-right">Buka</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-white">
					<div class="panel-heading">
						<h4 class="panel-title">Kelola Nilai Kelas ${namaKelas}</h4>
					</div>
					<div class="panel-body">
						<form action="simpan_nilai/">					
							<table class="table" id="tabelNilai">
								<thead>
									<tr>
										<th>NRP</th>
										<th>Nama Mahasiswa</th>
										<c:forEach var="komponen" items="${listKomponen}">
											<th style="width:10%"><c:out value="${komponen.getNamaKomponen()}"></c:out></th>
										</c:forEach>
										<th>Nilai Akhir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="krs" items="${krsInfo}">
										<tr class="mahasiswa" name="${krs.getIdKrs()}">
											<td><c:out value="${krs.getPd().getNimPd()}"></c:out></td>
											<td><c:out value="${krs.getPd().getNmPd()}"></c:out></td>
											<c:forEach var="komponen" items="${listKomponen}">
												<td class="komponen-nilai" name="${komponen.getIdKomponen()}">
													<c:set var="resultNilai" value="0" scope="page"></c:set>
													<c:forEach var="nilai" items="${listNilai}">
														<c:if test="${nilai.getKrs().getIdKrs() == krs.getIdKrs() && nilai.getKomponenNilai().getIdKomponen() ==  komponen.getIdKomponen()}">
															<c:set var="resultNilai" value="${nilai.getNilai()}" scope="page"></c:set>
														</c:if>
													</c:forEach>
													<input type="text" class="form-control nilai" value='${resultNilai}' />
												</td>
											</c:forEach>
											<td><input type="text" class="form-control nilai-akhir" value="${krs.getNilaiAkhir()}" disabled/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="pull-right">
								<button type="button" class="btn btn-primary" id="tombolSimpanNilai">Simpan</button>
								<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalKomponen">Atur Komponen</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of content -->
	
	<!-- modal -->
	<div class="modal fade" id="modalKomponen" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modalTitle">Atur Komponen Nilai</h4>
				</div>
				<div class="modal-body">
					<p>Silahkan atur nama komponen penilaian dan persentase setiap komponen di bawah. 
					Total persentase komponen tidak boleh melebihi 100%.</p>
					<div class="row">
						<div class="col-md-12">
							<form id="formKomponen">
								<table class="table" id="tabelKomponen">
									<thead>
										<tr>
											<th>Nama Komponen</th>
											<th>Persentase Komponen</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="komp" items="${listKomponen }">
										<tr class="komponen-modal" id="${komp.getIdKomponen()}" name="${komp.getIdKomponen()}">													
											<td><input type="text" class="form-control nama-komponen" value="<c:out value="${komp.getNamaKomponen()}"></c:out>"/></td>
											<td><input type="text" class="form-control persentase-komponen" value="<c:out value="${komp.getPersentaseKomponen()}"></c:out>"/></td>
											<td><button type="button" class="btn btn-danger tombolHapusKomponen"><i class="glyphicon glyphicon-minus"></i></button></td>
										</tr>
										</c:forEach>
										<tr id="newRowKomponen">
											<td><input type="text" class="form-control" id="namaKomponenNew"/></td>
											<td><input type="text" class="form-control" id="persentaseKomponenNew"/></td>
											<td><button type="button" class="btn btn-success" id="tombolTambahKomponen"><i class="glyphicon glyphicon-plus"></i></button></td>
										</tr>
									</tbody>
								</table>
								<button type="button" class="btn btn-primary pull-right" id="tombolSimpanKomponen" data-loading-text="Menyimpan...">Simpan</button>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
	<!-- end of modal -->
	
	<!-- script khusus-->
	<script>		
		$(document).ready(function() {
			var idPemb = "${idPemb}";
			toastr.options = {
					  "closeButton": true,
					  "debug": false,
					  "newestOnTop": false,
					  "progressBar": false,
					  "positionClass": "toast-top-right",
					  "preventDuplicates": true,
					  "onclick": null,
					  "showDuration": "300",
					  "hideDuration": "1000",
					  "timeOut": "5000",
					  "extendedTimeOut": "1000",
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut"
					}
			
			//script merubah nilai akhir di tabel
			$(".nilai").change(function() {
				var baris = $(this).closest("tr");
				var elemenNilai = $(baris).find("input.nilai");
				var nilaiAkhir = 0;
				
				$(elemenNilai).each(function(index, element) {
					var nilai = $(element).val();
					var idKomp = $(element).closest("td").attr("name");
					var persenKomp = $("#" + idKomp).find("input.persentase-komponen").val();
					
					nilaiAkhir += (nilai*persenKomp)/100;
				});
				
				$(baris).find("input.nilai-akhir").val(nilaiAkhir);
			});
			
			//script tambah komponen
			$("#tombolTambahKomponen").click(function() {
				if($("#namaKomponenNew").val() != "" && $("#persentaseKomponenNew").val() != "") {
					var namaKomp = $("#namaKomponenNew").val();
					var persenKomp = $("#persentaseKomponenNew").val();
					var totalPersen = 0.0;
					
					$("input.persentase-komponen").each(function(index, element) {
						totalPersen += parseFloat($(element).val());
					});
					
					if(parseFloat(totalPersen + parseFloat(persenKomp)) > 100.0) {
						toastr["warning"]("Total persentase tidak boleh di atas 100", "Peringatan");
					}
					else {
						var komp = {
								"idKomponen" : null,
								"namaKomponen" : namaKomp,
								"persentaseKomponen" : persenKomp,
								"aKompAktif" : true,
								"pemb" : null
						};
						
						$.ajax({
							url : idPemb + "/tambah_komponen/",
							type : "POST",
							contentType: "application/json",
							data : JSON.stringify(komp),
							success : function(data) {
								if(data.status == "ok") {
									$("#newRowKomponen").before('<tr id="' + data.data + '"' + 'name="'+ data.data + '">'
										+ '<td><input type="text" class="form-control nama-komponen" value="' + namaKomp + '"/></td>'
										+ '<td><input type="text" class="form-control persentase-komponen" value="' + persenKomp +'"/></td>'
										+ '<td><button type="button" class="btn btn-danger tombolHapusKomponen"><i class="glyphicon glyphicon-minus"></i></button></td>'
										+ '</tr>'
									);
									toastr["success"](data.message, "Sukses");
								}
							}
						});
					}
					
					$("#namaKomponenNew").val("");
					$("#persentaseKomponenNew").val("");
				}
				else {
					toastr["warning"]("Data komponen nilai baru kurang lengkap", "Peringatan");
				}
			});
			
			//hapus komponen
			$("body").on("click", ".tombolHapusKomponen", function() {
				var idKomponen = $(this).closest("tr").attr('name');
				var button = $(this);
				$.ajax({
					url : "hapus_komponen/",
					type : "POST",
					dataType : "json",
					data : {"idKomp" : idKomponen},
					success : function(data) {
						$(button).closest("tr").remove();
						toastr["success"](data.message, "Sukses");
					}
				});
			});
			
			//simpan komponen
			$("#tombolSimpanKomponen").click(function() {
				var totalPersen = 0.0;
				$("input.persentase-komponen").each(function(index, element) {
					totalPersen += parseFloat($(element).val());
				});
				
				if(totalPersen > 100) {
					toastr["warning"]("Total persentase tidak boleh di atas 100", "Peringatan");
				}
				else if(totalPersen < 100) {
					toastr["warning"]("Total persentase tidak boleh di bawah 100", "Peringatan");
				}
				else {
					$("#tombolSimpanKomponen").button("loading");
					var listKomponen = new Array();
					$("tr.komponen-modal").each(function(index, element) {
						var idKomponen = $(element).attr("name");
						var namaKomponen = $(element).find("input.nama-komponen").val();
						var persentaseKomponen = $(element).find("input.persentase-komponen").val();
						
						var komponen = {
								"idKomponen" : idKomponen,
								"namaKomponen" : namaKomponen,
								"persentaseKomponen" : persentaseKomponen,
								"aKompAktif" : true,
								"pemb" : null
						};
						
						listKomponen.push(komponen);
					});
					
					$.ajax({
						url : idPemb + "/simpan_komponen/",
						type : "POST",
						contentType : "application/json",
						data : JSON.stringify(listKomponen),
						success : function(data) {
							if(data.status == "ok") {
								$("#tombolSimpanKomponen").button("reset");
								toastr["success"](data.message, "Sukses");
								location.reload();
							}
						}
					});
				}
			});
			
			//submit nilai
			$("#tombolSimpanNilai").click(function() {
				var listNilai = new Array();
				$.blockUI({message : '<p>Sedang menyimpan nilai...</p>'});
				$("tr.mahasiswa").each(function(index, element) {
					var idKrs = $(element).attr("name");
					var komponens = $(element).find("td.komponen-nilai");
					
					$(komponens).each(function(index, element) {
						var idKomp = $(element).attr("name");
						var nilai = $(element).find("input").val();
						
						var objNilai = {
								"idKrs" : idKrs,
								"idKomp" : idKomp,
								"nilai" : nilai
						};
						
						listNilai.push(objNilai);
					});
				});
				
				$.ajax({
					url : idPemb + "/simpan_nilai/",
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify(listNilai),
					success : function(data) {
						if(data.status == "ok") {
							$.unblockUI();
							toastr["success"](data.message, "Sukses");
						}
					}
				});
			});
		});
	</script>
	<!-- end of script -->
	
	<%@include file="footer.jsp" %>
</body>
</html>