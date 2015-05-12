<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<title>Nilai Mahasiswa</title>
</head>
<body style="background:url(${pageContext.servletContext.contextPath}/resources/img/wild_flowers.png) repeat 0 0">
	<div class="container">
		<div class="wrapper">
			<%@include file="header.jsp" %>		
			
			<div class="row">
				<div class="container">
					<div class="col-md-12" style="margin-bottom:10px;">
						<ol class="breadcrumb">
							<li>
								<a href="${pageContext.servletContext.contextPath}/">Beranda</a>
							</li>
							<li>
								<a href="${pageContext.servletContext.contextPath}/kelola_nilai/">Kelola Nilai Mahasiswa</a>
							</li>
							<li class="active"><c:out value="${namaKelas}"></c:out></li>
						</ol>
					</div>
				</div>
			</div>
			
			<!-- content -->
			<div class="row">
				<div class="container">
					<form action="simpan_nilai/">
						<div class="row">
							<div class="container">
								<div class="col-md-12" class="content">						
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
															<input type="text" class="form-control" value='${resultNilai}' />															
														</td>
													</c:forEach>
													<td><input type="text" class="form-control" value='' disabled/></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="container">
								<div class="col-md-12">
									<div class="pull-right">
										<button type="button" class="btn btn-primary" id="tombolSimpanNilai">Simpan</button>
										<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalKomponen">Atur Komponen</button>
									</div>
								</div>
							</div>
						</div>
					</form>
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
												<tr class="komponen-modal" id="modal-${komp.getIdKomponen()}">													
													<td><input type="text" class="form-control nama-komponen" value="<c:out value="${komp.getNamaKomponen()}"></c:out>"/></td>
													<td><input type="text" class="form-control persentase-komponen" value="<c:out value="${komp.getPersentaseKomponen()}"></c:out>"/></td>
													<td><button type="button" class="btn btn-danger tombolHapusKomponen" name="${komp.getIdKomponen()}"><i class="glyphicon glyphicon-minus"></i></button></td>
												</tr>
												</c:forEach>
												<tr id="newRowKomponen">
													<td><input type="text" class="form-control" id="namaKomponenNew"/></td>
													<td><input type="text" class="form-control" id="persentaseKomponenNew"/></td>
													<td><button type="button" class="btn btn-success" id="tombolTambahKomponen"><i class="glyphicon glyphicon-plus"></i></button></td>
												</tr>
											</tbody>
										</table>
										<button type="button" class="btn btn-primary pull-right" id="tombolSimpanKomponen">Simpan</button>
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
			
			<!-- script ajax-->
			<script>
				//script tambah komponen
				$(document).ready(function() {
					$("#tombolTambahKomponen").click(function() {
						if($("#namaKomponenNew").val() != "" && $("#persentaseKomponenNew").val() != "") {
							var namaKomp = $("#namaKomponenNew").val();
							var persenKomp = $("#persentaseKomponenNew").val();
							var totalPersen = 0.0;
							
							$("input.persentase-komponen").each(function(index, element) {
								totalPersen += parseFloat($(element).val());
							});
							
							if(parseFloat(totalPersen + parseFloat(persenKomp)) > 100.0) {
								$.gritter.add({
									title : 'Notifikasi',
									text : 'Total persentase tidak boleh di atas 100',
									sticky : true
								});
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
									url : "tambah_komponen/",
									type : "POST",
									contentType: "application/json",
									data : JSON.stringify(komp),
									success : function(data) {
										if(data.status == "ok") {
											$("#newRowKomponen").before('<tr>'
												+ '<td><input type="text" class="form-control nama-komponen" value="' + namaKomp + '"/></td>'
												+ '<td><input type="text" class="form-control persentase-komponen" value="' + persenKomp +'"/></td>'
												+ '<td><button type="button" class="btn btn-danger tombolKurangKomponen" name="'+ data.data +'"><i class="glyphicon glyphicon-minus"></i></button></td>'
												+ '<tr>'
											);
											$.gritter.add({
												title : 'Notifikasi',
												text : data.message,
												sticky : false,
												time : 200
											});
										}
									}
								});
							}
							
							$("#namaKomponenNew").val("");
							$("#persentaseKomponenNew").val("");
						}
					});
					
					//hapus komponen
					$(".tombolHapusKomponen").click(function() {
						var idKomponen = $(this).attr('name');
						$.ajax({
							url : "hapus_komponen/",
							type : "POST",
							contentType : "application/json",
							data : JSON.stringify(idKomponen),
							success : function(data) {
								$("#modal-" + idKomponen).closest("tr").remove();
								console.log($(this));
								$.gritter.add({
									title : 'Notifikasi',
									text : data.message,
									sticky : false,
									time : 200
								});
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
							$.gritter.add({
								title : 'Notifikasi',
								text : 'Total persentase tidak boleh di atas 100',
								sticky : true
							});
						}
						else {
							var listKomponen = new Array();
							$("tr.komponen-modal").each(function(index, element) {
								var idKomponen = $(element).find("button").attr("name");
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
								url : "simpan_komponen/",
								type : "POST",
								contentType : "application/json",
								data : JSON.stringify(listKomponen),
								success : function(data) {
									if(data.status == "ok") {
										$.gritter.add({
											title : 'Notifikasi',
											text : data.message,
											sticky : false,
											time : 200
										});
										location.reload();
									}
								}
							});
						}
					});
					
					//submit nilai
					$("#tombolSimpanNilai").click(function() {
						var listNilai = new Array();
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
							url : "simpan_nilai/",
							type : "POST",
							contentType : "application/json",
							data : JSON.stringify(listNilai),
							success : function(data) {
								if(data.status == "ok") {
									$.gritter.add({
										title : 'Notifikasi',
										text : data.message,
										sticky : false,
										time : 200
									});
								}
							}
						});
					});
				});
			</script>
			<!-- end of script ajax -->
			
			<%@include file="footer.jsp" %>
		</div>
	</div>	
</body>
</html>