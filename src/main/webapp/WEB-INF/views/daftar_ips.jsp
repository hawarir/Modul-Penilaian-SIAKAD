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
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/site.min.js"></script>
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js">
	</script>
		  <script src="assets/js/respond.min.js">
	</script>
	<![endif]-->
	<script>
		var context_path = "${pageContext.servletContext.contextPath}";
	</script>
	<title>Indeks Prestasi</title>
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
							<li class="active">
								<a href="#">Indeks Prestasi Mahasiswa</a>
							</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- end of breadcrumb -->
			
			<!-- toggle -->
			<div class="row">
				<div class="container">
					<div class="col-md-6 col-md-offset-3">
						<ul class="nav nav-pills">
							<li class="active"><a href="#">Indeks Kumulatif</a></li>
							<li><a href="#">Indeks Periodik</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- end of toggle -->
			
			<!-- content -->
			<div class="row">
				<div class="container">
					<div class="col-md-6 col-md-offset-3">
						<table class="table">
							<thead>
								<tr>
									<th>NRP</th>
									<th>Nama</th>
									<th>Indeks Prestasi</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="mhs" items="${listMhs}">
								<tr>
									<td><c:out value="${mhs.getNRP()}"></c:out></td>
									<td><c:out value="${mhs.getNama()}"></c:out></td>
									<td>4.00</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- end of content -->
			
			<!-- pagination -->
			<div class="row">
				<div class="container">
					<div class="col-md-4 col-md-offset-4">
						<ul class="pagination">
							<li class="active"><a href="#">First</a></li>
							<li><a href="#">&lt;&lt;</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">&gt;&gt;</a></li>
							<li class="active"><a href="#">Last</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- end of pagination -->
			
			<%@include file="footer.jsp" %>
		</div>
	</div>
</body>
</html>