<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/favicon_16.ico">
	<title>Kuisioner Per Periode</title>
	
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
		href="${pageContext.servletContext.contextPath}/resources/plugins/jquery.datatables/media/css/jquery.dataTables.min.css"
		rel="stylesheet" type="text/css" />
	<script
		src="${pageContext.servletContext.contextPath}/resources/plugins/jquery.datatables/media/js/jquery.dataTables.min.js"></script>
		
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
	<div class="page-inner">
		<div class="page-title">
			<h3>Laporan Kuisioner Per Periode</h3>
			<div class="page-breadcrumb">
				<ol class="breadcrumb">
					<li><a href="${pageContext.servletContext.contextPath}/">Beranda</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/laporan_kuisioner_periode/">Daftar Laporan Kuisioner</a></li>
					<li class="active">Laporan Kuisioner Per Periode</li>
				</ol>
			</div>
		</div>
		<div id="main-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-white">
							<div class="panel-heading">
								<h4 class="panel-title">Laporan Kuisioner Per Periode</h4>
							</div>
							<div class="panel-body">
								<form method="post" action="">
									<div class="form-group">
										<select class="form-control" name="idTglSmt">
											<c:forEach var="tglSmt" items="${daftarTglSmt}">
												<option value="${tglSmt.getIdTglSmt()}"><c:out value="${tglSmt.getSmt().getNmSmt()} ${tglSmt.getThnAjaran().getThnThnAjaran()}"></c:out></option>
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
								<h4 class="panel-title">Laporan Kuisioner</h4>
							</div>
							<div class="panel-body">
								<table class="table">
									<thead>
										<tr>
											<th rowspan="2">Nama Kelas</th>
											<th rowspan="2">Nama Dosen</th>
											<th rowspan="2">Nilai IPD</th>
											<th colspan="${fn:length(daftarKuisioner)}">Kuisioner</th>									
										</tr>
										<tr>
											<c:forEach var="kuisioner" items="${daftarKuisioner}">
												<th><c:out value="${kuisioner.getNmKuisioner()}"></c:out></th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="pemb" items="${daftarPemb}">
											<tr id="${pemb.getIdPemb()}">
												<td><c:out value="${pemb.getMk().getNamaMK()} ${pemb.getNmPemb()}"></c:out></td>
												<c:forEach var="dosen" items="${daftarKetuaPendidik}">
													<c:if test="${dosen.getPemb().getIdPemb() == pemb.getIdPemb()}">
														<td><c:out value="${dosen.getPtk().getNmPtk()}"></c:out></td>
														<td><fmt:formatNumber value="${dosen.getNilaiIpd()}" maxFractionDigits="2"></fmt:formatNumber></td>
													</c:if>
												</c:forEach>
												<c:forEach var="kuisioner" items="${daftarKuisioner}">
													<c:forEach var="nilai" items="${daftarNilai}">
														<c:if test="${nilai.getIdPemb() == pemb.getIdPemb() && nilai.getIdKuisioner() == kuisioner.getIdKuisioner()}">
															<td><fmt:formatNumber value="${nilai.getNilai()}" maxFractionDigits="2"></fmt:formatNumber></td>
														</c:if>
													</c:forEach>
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
	<!-- end of content -->
	
	<script>
	$(document).ready(function() {
		$("table").DataTable();
	});
	</script>
	
	<%@include file="footer.jsp" %>
</body>
</html>