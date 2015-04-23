<!-- header -->
<div class="row">
	<div class="col-md-12">
		<img class="img-responsive" src="${pageContext.servletContext.contextPath}/resources/img/logo.png"></img>
	</div>
</div>
<nav class="navbar navbar-default navbar-sia" role="navigation" style="background-color: #3bafda;border-radius:0;border-color:#3bafda;">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button> 
			<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/">
				Beranda
			</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Pembelajaran <b class="caret">
				</b>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-submenu">
							<a href="#">Kelola Kalender Akademik</a>
							<ul class="dropdown-menu">
								<li><a href="#">Kelola Tahun Ajaran</a></li>
								<li><a href="#">Kelola Peride</a></li>
								<li><a href="#">Kelola Tanggal Penting</a></li>
							</ul>
						</li>
						<li>
							<a href="#">Kelola Pembelajaran</a>
						</li>
						<li>
							<a href="#">Perwalian</a>
						</li>
						<li>
							<a href="#">Rombongan Belajar</a>
						</li>
						<li>
							<a href="#">KRS</a>
						</li>
						<li>
							<a href="#">Absensi</a>
						</li>
					</ul>
				</li>	
			</ul>
			<ul  class="nav navbar-nav navbar-right">				
				<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Akun <b class="caret">
				</b>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li>
							<a href="#">Profil</a>
						</li>
						<li>
							<a href="#">Pilih hak akses</a>
						</li>
						<li>
							<a href="#">Keluar</a>
						</li>
					</ul>
				</li>	
			</ul>
		</div>
	</div>
</nav>
<!-- end of header -->