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
				"defaultContent" : "<button>View</button>&nbsp;&nbsp;<button>Edit</button>"
			}/* , {
				"targets" : [ 6 ],
				"data" : null,
				"defaultContent" : "<button>Edit</button>"
			} */],
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
	                    title: 'Edit Customer',
	                    message: $('#userForm'),
	                    width: 900,
	                    height: 1000,
	                    modal: true,
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
							<th width="33%;" >Name</th>
							<th width="25%;">Email</th>
							<th width="20%;">Phone No.</th>
							<th width="10%;">Source</th>
							<th width="12%;">&nbsp;</th>
							
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Phone No.</th>
							<th>Source</th>
							<th></th>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- The form which is used to populate the item data -->
			<jsp:include page="view_customer.jsp"/>
			

			<div class="tab-pane fade" id="tab_b">
			<jsp:include page="upload_customer.jsp"/>
			
			</div>
		</div>
		<!-- tab content -->
	</div>
	<!-- container -->

</body>
</html>
