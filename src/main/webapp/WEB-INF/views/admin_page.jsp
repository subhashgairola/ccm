<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Admin Page</title>


<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootbox.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js"></script>



<link type="text/css" rel="stylesheet" src="../css/bootstrap.css" />
<link type="text/css" rel="stylesheet" src="../css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/jquery.dataTables.min.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/app.css" />" />

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
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr(
								"content");
						var table = $('#cust-table')
								.DataTable(
										{
											"columnDefs" : [ {
												"targets" : [ 5 ],
												"data" : null,
												"defaultContent" : "&nbsp;&nbsp;<button>Edit</button>"
											} ],
											"pagingType" : "simple_numbers",
											"processing" : true,
											"serverSide" : true,
											"initComplete" : function() {
												table
														.buttons()
														.container()
														.appendTo(
																$('#cust-table_wrapper .col-sm-6:eq(0)'));
											},
											"buttons" : [ 'excel' ],
											"ajax" : {
												url : "../customers",
												type : "POST",
												data : function(data) {
													return JSON.stringify(data);
												},
												"dataType" : "json",
												"processData" : false,
												beforeSend : function(xhr) {
													xhr.setRequestHeader(
															header, token);
												},
												contentType : 'application/json;charset=UTF-8'
											},

											"columns" : [ {
												"data" : "id"
											}, {
												"data" : "name"
											}, {
												"data" : "email"
											}, {
												"data" : "phoneNum"
											}, {
												"data" : "source"
											} ]
										});

						$('#cust-table tbody').on(
								'click',
								'button',
								function() {
									var data = table.row($(this).parents('tr'))
											.data();
									//alert(data.customerDetailId + "'s salary is: " + data.name);
									// Get the record's ID via attribute
									var id = $(this).attr('data-id');
									// Populate the form fields with the data returned from server
									$('#userForm').find(
											'[name="customerDetailId"]').val(
											data.customerDetailId).end().find(
											'[name="source"]').val(data.source)
											.end().find('[name="id"]').val(
													data.id).end().find(
													'[name="name"]').val(
													data.name).end().find(
													'[name="password"]').val(
													data.password).end().find(
													'[name="email"]').val(
													data.email).end().find(
													'[name="jobTitle"]').val(
													data.jobTitle).end().find(
													'[name="company"]').val(
													data.company).end().find(
													'[name="mobileNum"]').val(
													data.mobileNum).end().find(
													'[name="birthDate"]').val(
													data.birthDate).end().find(
													'[name="gender"]').val(
													data.gender).end().find(
													'[name="creationDate"]')
											.val(data.creationDate).end().find(
													'[name="ipAddress"]').val(
													data.ipAddress).end().find(
													'[name="country"]').val(
													data.country).end().find(
													'[name="city"]').val(
													data.city).end().find(
													'[name="updatedBy"]').val(
													data.updatedBy).end().find(
													'[name="zip"]').val(
													data.zip).end().find(
													'[name="phoneNum"]').val(
													data.phoneNum).end().find(
													'[name="location"]').val(
													data.location).end().find(
													'[name="lastLogin"]').val(
													data.lastLogin).end()

									// Show the dialog
									bootbox.dialog({
										title : 'Edit Customer',
										message : $('#userForm'),
										width : 900,
										height : 500,
										modal : true,
										show : false
									// We will show it manually later
									}).on('shown.bs.modal', function() {
										$('#userForm').show();
									}).on('hide.bs.modal', function(e) {
										$('#userForm').hide().appendTo('body');
									}).modal('show');
								});

						$.fn.serializeObject = function() {
							var o = {};
							var a = this.serializeArray();
							$.each(a, function() {
								if (o[this.name] !== undefined) {
									if (!o[this.name].push) {
										o[this.name] = [ o[this.name] ];
									}
									o[this.name].push(this.value || '');
								} else {
									o[this.name] = this.value || '';
								}
							});
							return o;
						};

						$('#uploadTrg')
								.load(
										function() {
											var response = this.contentWindow.document.body.innerText;
											if (response.indexOf('Error') != -1) {
												$("#successSpan").html('');
												$("#errorSpan").html(response);
											} else {
												$("#errorSpan").html('');
												$("#successSpan")
														.html(response);
											}
										});
					});

	function save() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var formData = JSON.stringify(jQuery('#userForm').serializeObject());
		console.log(formData);
		// The url and method might be different in your application
		$.ajax({
			url : '../customer',
			method : 'POST',
			data : formData,
			dataType : "json",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			contentType : 'application/json;charset=UTF-8'
		}).success(function(response) {
			// Get the cells
			var $custTable = $("#cust-table").dataTable({
				bRetrieve : true
			});
			$custTable.fnDraw();

			// Hide the dialog
			$('#userForm').parents('.bootbox').modal('hide');
			bootbox.alert('The Customer profile was successfully updated');
		});
	}
</script>
</head>
<body>

	<div class="container"
		style="border: 1px solid #cecece; margin-top: 10px; width: 100%">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab_a" data-toggle="tab">Customers</a></li>
			<li><a href="#tab_b" data-toggle="tab">Upload Customers</a></li>
		</ul>
		<div class="tab-content"
			style="height: 100%; padding: 10px; margin: 30px;">
			<div class="tab-pane fade in active" id="tab_a">
				<table id="cust-table" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th width="10%;">Id</th>
							<th width="25%;">Name</th>
							<th width="30%;">Email</th>
							<th width="20%;">Phone No.</th>
							<th width="10%;">Source</th>
							<th width="5%;">Edit</th>

						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Phone No.</th>
							<th>Source</th>
							<th>Edit</th>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- The form which is used to populate the item data -->
			<jsp:include page="view_customer.jsp" />
			<div class="tab-pane fade" id="tab_b">
				<jsp:include page="upload_customer.jsp" />
			</div>
		</div>
	</div>
</body>
</html>
