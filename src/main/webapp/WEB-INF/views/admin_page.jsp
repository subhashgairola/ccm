<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Admin Page</title>
<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/bootbox.min.js"></script>
<script type="text/javascript" src="../js/buttons.html5.min.js"></script>
<script type="text/javascript" src="../js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="../js/jszip.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>

<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/jquery.dataTables.min.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/buttons.dataTables.min.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/app.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/jquery-ui.css" />" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr(
								"content");
						var hideFromExport = [5];
						var table = $('#cust-table')
								.DataTable(
										{
											dom: 'lBfrtip',
											buttons : [ {
												extend : 'excel',
												text : 'Export Excel',
												exportOptions: {
													columns: function ( idx, data, node ) {
														var isNotForExport = $.inArray( idx, hideFromExport ) !== -1;
														return !isNotForExport ? true : false;
													}
												}
											} ],
											"columnDefs" : [ {
												"targets" : [ 5 ],
												"data" : null,
												"defaultContent" : "&nbsp;<button onclick='openViewForm();return false;'>View</button>&nbsp;<button onclick='openEditForm();return false;'>Edit</button>"
											},{
											    "targets": [6, 7, 8,9, 10,11,12,13,14,15],
											    "visible": false
											  } ],
											 "lengthMenu": [10, 50, 100, 200, 500],
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
											}, {
												"data" : null
											}, {
												"data" : "password"
											}, {
												"data" : "birthDate"
											}, {
												"data" : "gender"
											},{
												"data" : "city"
											}, {
												"data" : "zip"
											}, {
												"data" : "stateName"
											},{
												"data" : "country"
											},  {
												"data" : "ipAddress"
											}, {
												"data" : "creationDate"
											}, {
												"data" : "lastLogin"
											} 
											]
										});

						$('#cust-table tbody').on(
								'click',
								'button',
								function() {
									var data = table.row($(this).parents('tr'))
											.data();
									// Get the record's ID via attribute
									var id = $(this).attr('data-id');

									populateStatesDropDown(data);

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
													data.lastLogin).end();

									// Show the dialog
									bootbox.dialog({
										id : 'userPopUp',
										title : 'View Customer',
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
											$("#formatDiv").text('');
											if (response.indexOf('Error') != -1) {
												$("#successSpan").html('');
												$("#errorSpan").html(response);
											} else {
												$("#errorSpan").html('');
												$("#successSpan")
														.html(response);
											}
										});
						$('#birthDate').datepicker({
							dateFormat : 'dd-mm-yy',
							changeYear : true,
							changeMonth: true,
							yearRange : "1900:"
						}).on("keydown keypress keyup", false);
					});

	function save() {
		
		if (!$('#userForm')[0].checkValidity()) {
		    $('#email-error').text('The Email format is not correct!');
		    return false;
		} 
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var formData = JSON.stringify(jQuery('#userForm').serializeObject());
		$.ajax({
			url : '../customer',
			method : 'POST',
			data : formData,
			dataType : "text",
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			contentType : 'application/json;charset=UTF-8'
		}).success(function(response) {
			if(response == ''){
			// Get the cells
			var $custTable = $("#cust-table").dataTable({
				bRetrieve : true
			});
			$custTable.fnDraw();

			// Hide the dialog
			$('#userForm').parents('.bootbox').modal('hide');
			bootbox.alert('The Customer profile was successfully updated');
			} else{
				$('#email-error').text(response);
			}
		});
	}

	function resetForm() {
		document.getElementById('uploadForm').reset();
		$("#successSpan").html('');
		$("#errorSpan").html('');
		$("#formatDiv").text('');
	}

	function openViewForm() {
		$('#saveBtn').hide();
		$('#birthDate').prop('disabled', 'disabled');
		$('#stateId').prop('disabled', 'disabled');
		$('#userForm :input').attr('readonly', 'readonly');
		$('#email-error').html('');
	}
	function openEditForm() {
		$('#saveBtn').show();
		$('#userForm :input').removeAttr('readonly');
		$('#id').prop('readonly', 'readonly');
		$('#lastLogin').prop('readonly', 'readonly');
		$('#creationDate').prop('readonly', 'readonly');
		$('#birthDate').prop('disabled', false);
		$('#stateId').prop('disabled', false);
		$('#email-error').html('');
	}

	function populateStatesDropDown(json) {
		$.ajax({
			type : "GET",
			url : "../states",
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				$("#stateId").empty();
				$("#stateId")
						.prepend("<option value='0'>Select State</option>");
				$.each(data, function() {
					$("#stateId").append(
							$('<option></option>').val(this['stateId']).html(
									this['stateName']));
				});
				$('#stateId>option[value="' + json.stateId + '"]').prop(
						'selected', true);
			}
		});
		$("input:radio").removeAttr("checked");
		var id = '#gender_' + json.gender;
		$(id).prop('checked','checked');
	}

	function refreshTable() {
		var $custTable = $("#cust-table").dataTable({
			bRetrieve : true
		});
		$custTable.fnDraw();
	}
	
	function showFormatMgs(input){
		var msg = '';
		if(input == 'APSIS'){
			msg = 'Correct format for APSIS source is: Source[APSIS], ID[359812888], Name/Surname[mustafa akca], Email[raixakca57@hotmail.com], CreationDate[2015-12-03T14:05:55+02:00]';
		}else if(input == 'NAV') {
			msg = 'Correct format for NAV source is: Source[NAV], ID[22323232] , Name/Surname[CEMAL ERGİN], Phone[05326686178], Location[MERKEZ]';
		} else if(input == 'Magento'){
			msg = 'The correct format for Magento source is: Source[Magento], ID[22479], Name/Surname[halil kırtaş], Email[hakemh@hotmail.com], Phone[5453028712], CreationDate[2015-09-05T19:26:33+03:00], ZIP[34000], Country[Turkey], State[Antalya]';
		} else if(input == 'ReederID'){
			msg = 'The correct format for ReederID source is: Source[ReederID], ID[6366364763], Name/Surname[mehmet keskin], Email[ayse.keskin.02@gmail.com], Phone[5342018802], CreationDate[2013-08-01T17:16:45+03:00], Password[05376835333], Sex[1], isNews[1], Location[İstanbul], IP[85.102.74.190], Birthday[25-07-1985]';
		}else if(input == 'Zendesk'){
			msg = 'The correct format for Zendesk source is: Source[Zendesk], ID[778482662], Name/Surname[Call Center], Email[alpa@reeder-tr.com], CreationDate[2015-04-14T20:33:09+03:00], Last Login[2016-09-07T19:28:24+03:00]';
		}
		$('#formatDiv').text(msg);
		$('#errorSpan').html('');
		$('#successSpan').html('');
	}
</script>
</head>
<body>
	<div class="container"
		style="border: 1px solid #cecece; margin-top: 10px; width: 100%">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab_a" onclick="refreshTable();"
				data-toggle="tab">Customers</a></li>
			<li><a href="#tab_b" onclick="resetForm();" data-toggle="tab">Upload
					Customers</a></li>
		</ul>
		<div class="tab-content"
			style="height: 100%; padding: 10px; margin: 30px;">
			<div class="tab-pane fade in active" id="tab_a">
				<table id="cust-table" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th width="10%;">Id</th>
							<th width="25%;">Name</th>
							<th width="25%;">Email</th>
							<th width="20%;">Phone</th>
							<th width="8%;">Source</th>
							<th width="12%;"></th>
							<th>Password</th>
							<th>Birthday</th>
							<th>Sex</th>
							<th>City</th>
							<th>Zip</th>
							<th>State</th>
						    <th>Country</th>
						    <th>IP</th>
							<th>CreationDate</th>
							<th>Last Login</th>
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
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
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
