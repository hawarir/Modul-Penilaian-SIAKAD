<form class="search-form" action="#" method="GET">
		<div class="input-group">
			<input type="text" name="search" class="form-control search-input"
				placeholder="Search..."> <span class="input-group-btn">
				<button
					class="btn btn-default close-search waves-effect waves-button waves-classic"
					type="button">
					<i class="fa fa-times"></i>
				</button>
			</span>
		</div>
		<!-- Input Group -->
	</form>
	<!-- Search Form -->
	<main class="page-content content-wrap">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="sidebar-pusher">
				<a href="javascript:void(0);"
					class="waves-effect waves-button waves-classic push-sidebar"> <i
					class="fa fa-bars"></i>
				</a>
			</div>
			<div class="logo-box">
				<a href="index.html" class="logo-text"><span>Modern</span></a>
			</div>
			<!-- Logo Box -->
			<div class="search-button">
				<a href="javascript:void(0);"
					class="waves-effect waves-button waves-classic show-search"><i
					class="fa fa-search"></i></a>
			</div>
			<div class="topmenu-outer">
				<div class="top-menu">
					<ul class="nav navbar-nav navbar-left">
						<li><a href="javascript:void(0);"
							class="waves-effect waves-button waves-classic sidebar-toggle"><i
								class="fa fa-bars"></i></a></li>
						<li><a href="javascript:void(0);"
							class="waves-effect waves-button waves-classic toggle-fullscreen"><i
								class="fa fa-expand"></i></a></li>
						<li class="dropdown"><a href="#"
							class="dropdown-toggle waves-effect waves-button waves-classic"
							data-toggle="dropdown"> <i class="fa fa-cogs"></i>
						</a>
							<ul
								class="dropdown-menu dropdown-md dropdown-list theme-settings"
								role="menu">
								<li class="li-group">
									<ul class="list-unstyled">
										<li class="no-link" role="presentation">Fixed Header
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right fixed-header-check" checked>
											</div>
										</li>
									</ul>
								</li>
								<li class="li-group">
									<ul class="list-unstyled">
										<li class="no-link" role="presentation">Fixed Sidebar
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right fixed-sidebar-check">
											</div>
										</li>
										<li class="no-link" role="presentation">Horizontal bar
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right horizontal-bar-check" checked>
											</div>
										</li>
										<li class="no-link" role="presentation">Toggle Sidebar
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right toggle-sidebar-check">
											</div>
										</li>
										<li class="no-link" role="presentation">Compact Menu
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right compact-menu-check">
											</div>
										</li>
										<li class="no-link" role="presentation">Hover Menu
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right hover-menu-check">
											</div>
										</li>
									</ul>
								</li>
								<li class="li-group">
									<ul class="list-unstyled">
										<li class="no-link" role="presentation">Boxed Layout
											<div class="ios-switch pull-right switch-md">
												<input type="checkbox"
													class="js-switch pull-right boxed-layout-check">
											</div>
										</li>
									</ul>
								</li>
								<li class="no-link"><button
										class="btn btn-default reset-options">Reset Options</button></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="javascript:void(0);"
							class="waves-effect waves-button waves-classic show-search"><i
								class="fa fa-search"></i></a></li>
						<li class="dropdown"><a href="#"
							class="dropdown-toggle waves-effect waves-button waves-classic"
							data-toggle="dropdown"> <span class="user-name">${userPd.nmPd}${userPtk.nmPtk}<i
									class="fa fa-angle-down"></i></span> <img class="img-circle avatar"
								src="${pageContext.servletContext.contextPath}/resources/images/avatar1.png"
								width="40" height="40" alt="">
						</a>
							<ul class="dropdown-menu dropdown-list" role="menu">
								<li role="presentation"><a href="profile.html"><i
										class="fa fa-user"></i>Profile</a></li>
								<li role="presentation"><a href="calendar.html"><i
										class="fa fa-calendar"></i>Calendar</a></li>
								<li role="presentation"><a href="inbox.html"><i
										class="fa fa-envelope"></i>Inbox<span
										class="badge badge-success pull-right">4</span></a></li>
								<li role="presentation" class="divider"></li>
								<li role="presentation"><a href="lock-screen.html"><i
										class="fa fa-lock"></i>Lock screen</a></li>
								<li role="presentation"><a href="${pageContext.servletContext.contextPath}/logout/"><i
										class="fa fa-sign-out m-r-xs"></i>Log out</a></li>
							</ul></li>
						<li><a href="${pageContext.servletContext.contextPath}/logout/"
							class="log-out waves-effect waves-button waves-classic"> <span><i
									class="fa fa-sign-out m-r-xs"></i>Log out</span>
						</a></li>
					</ul>
					<!-- Nav -->
				</div>
				<!-- Top Menu -->
			</div>
		</div>
	</div>
	<!-- Navbar -->
	<div class="horizontal-bar sidebar">
		<div class="page-sidebar-inner slimscroll">
			<div class="sidebar-header">
				<div class="sidebar-profile">
					<a href="javascript:void(0);" id="profile-menu-link">
						<div class="sidebar-profile-image">
							<img
								src="${pageContext.servletContext.contextPath}/resources/images/avatar1.png"
								class="img-circle img-responsive" alt="">
						</div>
						<div class="sidebar-profile-details">
							<span>David Green<br> <small>Art Director</small></span>
						</div>
					</a>
				</div>
			</div>
			<ul class="menu accordion-menu">
				<c:forEach items="${listModul}" var="modul" varStatus="status">
					<li class="droplink">
						<a href="javascript:void(0)" class="waves-effect waves-button">
							<span class="menu-icon glyphicon glyphicon-education"></span>
							<p>${modul.namaModul}</p>
							<span class="arrow"></span>
						</a>
							<ul class="sub-menu">
								<c:forEach items="${listListMenu[status.index]}" var="listMenu">
									<li><a href="${pageContext.servletContext.contextPath}/${listMenu.urlMenu}">${listMenu.namaMenu}</a></li>
								</c:forEach>
							</ul>
					</li>
				</c:forEach>
				<li><a href="index.html" class="waves-effect waves-button"><span
						class="menu-icon glyphicon glyphicon-home"></span>
						<p>Dashboard</p></a></li>
				<li><a href="profile.html" class="waves-effect waves-button"><span
						class="menu-icon glyphicon glyphicon-user"></span>
						<p>Profile</p></a></li>
				<li class="droplink"><a href="#"
					class="waves-effect waves-button"><span
						class="menu-icon glyphicon glyphicon-education"></span>
						<p>Modul Penilaian</p> <span class="arrow"></span></a>
					<ul class="sub-menu">
						<li class="droplink"><a href="#"><p>Nilai</p> <span
								class="arrow"></span></a>
							<ul class="sub-menu">
								<li><a href="${pageContext.servletContext.contextPath}/kelola_nilai/">Kelola Nilai</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/lihat_nilai/">Lihat Nilai Per Kelas</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/lihat_nilai_periode/">Nilai Per Periode</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/lihat_transkrip/">Transkrip Nilai</a></li>
							</ul>
						</li>
						<li class="droplink"><a href="#"><p>Indeks Prestasi</p> <span
								class="arrow"></span></a>
							<ul class="sub-menu">
								<li><a href="${pageContext.servletContext.contextPath}/lihat_ips/">Peringkat IPS</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/lihat_ipk/">Peringkat IPK</a></li>
							</ul>
						</li>
						<li class="droplink"><a href="#"><p>Kuisioner Dosen</p> <span
								class="arrow"></span></a>
							<ul class="sub-menu">
								<li><a href="${pageContext.servletContext.contextPath}/kelola_kuisioner/">Kelola Kuisioner</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/isi_kuisioner/">Isi Kuisioner</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/laporan_kuisioner_periode/">Laporan Kuisioner Periodik</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/laporan_kuisioner_kelas/">Laporan Kuisioner Per Kelas</a></li>
							</ul>
						</li>
						<li class="droplink"><a href="#"><p>Pengaturan</p> <span
								class="arrow"></span></a>
							<ul class="sub-menu">
								<li><a href="${pageContext.servletContext.contextPath}/konversi_nilai/">Konversi Nilai</a></li>
								<li><a href="${pageContext.servletContext.contextPath}/update_ip/">Perbaharui IP</a></li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- Page Sidebar Inner -->
	</div>
	<!-- Page Sidebar --> <!-- Content -->