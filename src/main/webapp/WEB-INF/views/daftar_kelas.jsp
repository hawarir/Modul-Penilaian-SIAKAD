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
	<title>Daftar Kelas</title>
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
								<a href="#">Daftar Kelas</a>
							</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- end of breadcrumb -->
			
			<!-- content -->
			<div class="row">
				<div class="container">
					<div class="col-md-6 col-md-offset-3" class="content">
						<div class="list-group">
						<c:forEach var="kelas" items="${listKelas}">
							<a href="${kelas.getIdPemb()}/" class="list-group-item"><c:out value="${kelas.getMk().getNamaMK()} ${kelas.getNmPemb()}"></c:out></a>
						</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<!-- end of content -->
			<%@include file="footer.jsp" %>
		</div>
	</div>
</body>
</html>