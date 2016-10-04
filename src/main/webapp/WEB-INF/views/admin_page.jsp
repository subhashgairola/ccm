<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Admin Page</title>


<script type="text/javascript" src="../js/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootbox.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/jquery.dataTables.min.css" />" />

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

	$(document).ready(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var table = $('#cust-table').DataTable({
			"columnDefs" : [ {
				"targets" : [ 0 ],
				"visible" : false,
			}, {
				"targets" : [ 5 ],
				"data" : null,
				"defaultContent" : "<button>View</button>"
			}/* , {
				"targets" : [ 6 ],
				"data" : null,
				"defaultContent" : "<button>Edit</button>"
			}  */],
			"pagingType" : "simple_numbers",
			"processing" : true,
			"serverSide" : true,
			"ajax" : {
				url : "../customers",
				type : "POST",
				data : function(data) {
					return JSON.stringify(data);
				},
				"dataType" : "json",
				"processData" : false,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				contentType : 'application/json;charset=UTF-8'
			},

			"columns" : [ {
				"data" : "customerDetailId"
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

		$('#cust-table tbody').on('click', 'button', function() {
			var data = table.row($(this).parents('tr')).data();
			//alert(data.customerDetailId + "'s salary is: " + data.name);
			 // Get the record's ID via attribute
	        var id = $(this).attr('data-id');
	            // Populate the form fields with the data returned from server
	            $('#userForm')
	                .find('[name="id"]').val(data.id).end()
	                .find('[name="name"]').val(data.name).end()
	                .find('[name="email"]').val(data.email).end()
	                .find('[name="password"]').val(data.password).end()
	                .find('[name="mobileNum"]').val(data.mobileNum).end()
	                .find('[name="phoneNum"]').val(data.phoneNum).end()
	                .find('[name="birthDate"]').val(data.birthDate).end()
	                .find('[name="gender"]').val(data.gender).end()
	                .find('[name="creationDate"]').val(data.creationDate).end()
	                .find('[name="lastLogin"]').val(data.lastLogin).end()
	                .find('[name="ipAddress"]').val(data.ipAddress).end()
	                .find('[name="country"]').val(data.country).end()
	                .find('[name="city"]').val(data.city).end()
	                .find('[name="zip"]').val(data.zip).end()
	                .find('[name="location"]').val(data.location).end()

	            // Show the dialog
	            bootbox
	                .dialog({
	                    title: 'View Customer',
	                    message: $('#userForm'),
	                    show: false // We will show it manually later
	                })
	                .on('shown.bs.modal', function() {
	                    $('#userForm')
	                        .show();
	                })
	                .on('hide.bs.modal', function(e) {
	                    $('#userForm').hide().appendTo('body');
	                })
	                .modal('show');
		});
	});
	$('#userForm').on('submit', function(e){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
	    alert("The paragraph was clicked.");
	
            // Save the form data via an Ajax request
            e.preventDefault();

            var $form = $(e.target),
                id    = $form.find('[name="id"]').val();

            // The url and method might be different in your application
            $.ajax({
                url: '/customer/' + id,
                method: 'PUT',
                data: $form.serialize(),
                beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				contentType : 'application/json;charset=UTF-8'
            }).success(function(response) {
                // Get the cells
                var $button = $('button[data-id="' + response.id + '"]'),
                    $tr     = $button.closest('tr'),
                    $cells  = $tr.find('td');

                // Update the cell data
               /*  $cells
                    .eq(1).html(response.name).end()
                    .eq(2).html(response.email).end() */

                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

                // You can inform the user that the data is updated successfully
                // by highlighting the row or showing a message box
                bootbox.alert('The user profile is updated');
            });
	});
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
							<th>Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Phone No.</th>
							<th>Source</th>
							<th>View</th>
							<!-- <th>Edit</th> -->
							
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Phone No.</th>
							<th>Source</th>
							<th>View</th>
							<!-- <th>Edit</th> -->
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- The form which is used to populate the item data -->
			<form id="userForm" method="post" style="display: none;">
				<div class="form-group">
					<label class="col-xs-3 control-label">ID</label>
					<div class="col-xs-3">
						<input type="text" class="form-control" name="id"
							disabled="disabled" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label">Name</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="name" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-3 control-label">Email</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="email" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Password</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="password" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-3 control-label">Phone No.</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="phoneNum" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Birthdate</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="birthDate" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-3 control-label">Gender</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="gender" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Creation Date</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="creationDate" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-3 control-label">Last Login date</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="lastLogin" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">IP Address</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="ipAddress" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-3 control-label">Country</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="country" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">City</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="city" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-3 control-label">Zip</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" name="zip" />
					</div>
				</div>

				<!-- <div class="form-group">
					<div class="col-xs-5 col-xs-offset-3">
						<button id="saveBtn" type="button" class="btn btn-default">Save</button>
					</div>
				</div> -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
			</form>


			<div class="tab-pane fade" id="tab_b">
				<form class="container" method="POST"
					action="upload?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data">

					<div class="form-group">
						<select name="sourceType" required style="font-size: 12px">
							<option value="" selected>Select Source</option>
							<option value="APSIS">APSIS</option>
							<option value="NAV">NAV</option>
							<option value="Magento">Magento</option>
							<option value="ReederID">ReederID</option>
							<option value="Zendesk">Zendesk</option>
						</select>
					</div>
					<input type="file" class="btn-file" name="file"
						style="font-size: 12px" required>

					<c:if test="${not empty error}">
						<span style="font-size: 10px; color: #FF5733"><br>
							${error}</span>
					</c:if>
					<c:if test="${not empty success}">
						<span style="font-size: 10px; color: #7FFF00"><br>
							${success}</span>
					</c:if>
					<div class="form-group">

						<input type="submit" value="Upload File" class="btn-info"
							style="font-size: 12px">
					</div>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<!-- tab content -->
	</div>
	<!-- container -->

</body>
</html>
