<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>

<script type="text/javascript" src="../js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css" />" />

<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<style>
.fade {
	opacity: 0;
	-webkit-transition: opacity 2.25s linear;
	-moz-transition: opacity 2.25s linear;
	-ms-transition: opacity 2.25s linear;
	-o-transition: opacity 2.25s linear;
	transition: opacity 2.25s linear;
}
</style>
</head>
<body>

	<div class="container" style="border: 1px solid #cecece; width: 999px">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab_a" data-toggle="tab">Imported
					Users</a></li>
			<li><a href="#tab_b" data-toggle="tab">Upload File</a></li>
			<li><a href="#tab_c" data-toggle="tab">Tab C</a></li>
			<li><a href="#tab_d" data-toggle="tab">Tab D</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="tab_a">
				<h4>Pane A</h4>
				<p>Pellentesque habitant morbi tristique senectus et netus et
					malesuada fames ac turpis egestas.</p>
			</div>
			<div class="tab-pane fade" id="tab_b">
				<h4>Import Users</h4>
				<form class="container" method="POST" action="upload?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data">
				<div class="form-group" >
				<select name="filetype" required style="font-size:12px">
					<option value="" selected>Select Source</option>
					<option value="APSIS">APSIS</option>
					<option value="NAV">NAV</option>
					<option value="Magento">Magento </option>
					<option value="ReederID">ReederID</option>
					<option value="Zendesk">Zendesk</option>
					</select>
					</div>
	
					<div class="form-group" style="font-size:12px">
					<span class=" btn btn-file">Browse: <input type="file" name="file" style="font-size:12px"></span></div>
					<div class="form-group"> <input type="submit"
						value="Upload" style="font-size:12px"></div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>
			<div class="tab-pane fade" id="tab_c">
				<h4>Pane C</h4>
				<p>Pellentesque habitant morbi tristique senectus et netus et
					malesuada fames ac turpis egestas.</p>
			</div>
			<div class="tab-pane fade" id="tab_d">
				<h4>Pane D</h4>
				<p>Pellentesque habitant morbi tristique senectus et netus et
					malesuada fames ac turpis egestas.</p>
			</div>
		</div>
		<!-- tab content -->
	</div>
	<!-- container -->

</body>
</html>
