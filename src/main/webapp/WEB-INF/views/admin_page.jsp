<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Admin Page</title>

<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/bootbox.min.js"></script>

<script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/jquery.dataTables.min.css" />" />
<link type="text/css" rel="stylesheet" src="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css" />
<link type="text/css" rel="stylesheet" src="../css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<c:url value="/css/app.css" />" />
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
    $(document)
        .ready(
            function() {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr(
                    "content");
                var table = $('#cust-table')
                    .DataTable({
                    	dom: 'Bfrtip',
                    	 buttons: [ {
                    		 extend:'excel',
                             text: 'Export Excel',
                             exportOptions: {
                                 columns: [0,1,2,3,4,5]
                             }
                         }
                    	 ],
                        "columnDefs": [{
                            "targets": [5],
                            "data": null,
                            "defaultContent": "&nbsp;<button onclick='openViewForm();return false;'>View</button>&nbsp;<button onclick='openEditForm();return false;'>Edit</button>"
                        }],
                        "pagingType": "simple_numbers",
                        "processing": true,
                        "serverSide": true,
                        "ajax": {
                            url: "../customers",
                            type: "POST",
                            data: function(data) {
                                return JSON.stringify(data);
                            },
                            "dataType": "json",
                            "processData": false,
                            beforeSend: function(xhr) {
                                xhr.setRequestHeader(
                                    header, token);
                            },
                            contentType: 'application/json;charset=UTF-8'
                        },

                        "columns": [{
                            "data": "id"
                        }, {
                            "data": "name"
                        }, {
                            "data": "email"
                        }, {
                            "data": "phoneNum"
                        }, {
                            "data": "source",
                        },{
                            "data": "password",
                            "visible":false
                        }, {
                            "data": "birthDate",
                            "visible":false
                        }, {
                            "data": "gender",
                            "visible":false
                        }, {
                            "data": "creationDate",
                            "visible":false
                        }, {
                            "data": "lastLogin",
                            "visible":false
                        }, {
                            "data": "ipAddress",
                            "visible":false
                        }, {
                            "data": "country",
                            "visible":false
                        }, {
                            "data": "city",
                            "visible":false
                        }, {
                            "data": "zip",
                            "visible":false
                        }, {
                            "data": "stateName",
                            "visible":false
                        }]
                    });

                $('#cust-table tbody').on(
                    'click',
                    'button',
                    function() {
                        var data = table.row($(this).parents('tr'))
                            .data();
                        // Get the record's ID via attribute
                        var id = $(this).attr('data-id');
                        
                        populateStatesDropDown(data.stateId);
                        
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
                                data.lastLogin).end();
                        
                        // Show the dialog
                        bootbox.dialog({
                        	id: 'userPopUp',
                            title: 'View Customer',
                            message: $('#userForm'),
                            width: 900,
                            height: 500,
                            modal: true,
                            show: false
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
                                o[this.name] = [o[this.name]];
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
                $('#birthDate').datepicker({
                	dateFormat: 'yy-mm-dd',
                	changeYear: true,
                	yearRange: "1900:"
           		 }).keypress(function(event) {event.preventDefault();});
               
            });
    	
    function save() {
    	 /* if (!$('#userForm')[0].checkValidity()) {
    		 console.log('Invalid');
             $('#userForm').find('input[type="email"]').click();
             return false;
         } */
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var formData = JSON.stringify(jQuery('#userForm').serializeObject());
        // The url and method might be different in your application
        $.ajax({
            url: '../customer',
            method: 'POST',
            data: formData,
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            contentType: 'application/json;charset=UTF-8'
        }).success(function(response) {
            // Get the cells
            var $custTable = $("#cust-table").dataTable({
                bRetrieve: true
            });
            $custTable.fnDraw();

            // Hide the dialog
            $('#userForm').parents('.bootbox').modal('hide');
            bootbox.alert('The Customer profile was successfully updated');
        });
    }

    function resetForm() {
        document.getElementById('uploadForm').reset();
        $("#successSpan").html('');
        $("#errorSpan").html('');
    }
    
    function openViewForm(){
    	$('#saveBtn').hide();
    	$('#birthDate').prop('disabled', 'disabled');
    	$('#stateId').prop('disabled', 'disabled');
    	$('#userForm :input').attr('readonly','readonly');
    }
    function openEditForm(){
    	$('#saveBtn').show();
    	$('#userForm :input').removeAttr('readonly');
    	$('#id').prop('readonly', 'readonly');
    	$('#lastLogin').prop('readonly', 'readonly');
    	$('#creationDate').prop('readonly', 'readonly');
    	$('#birthDate').prop('disabled', false);
    	$('#stateId').prop('disabled', false);
    }
    
    function populateStatesDropDown(selectedVal){
    	$.ajax({
            type: "GET",
            url: "../states",
            contentType: "application/json",              
            dataType: "json",
            success: function (data) {
                $("#stateId").empty();
                $("#stateId").prepend("<option value='0'>Select State</option>");
                $.each(data, function () {
                    $("#stateId").append($('<option></option>').val(this['stateId']).html(this['stateName']));
                });
                $('#stateId>option[value="' + selectedVal + '"]').prop('selected', true);
            }
        });
    }
    
    function refreshTable(){
    	var $custTable = $("#cust-table").dataTable({
            bRetrieve: true
        });
        $custTable.fnDraw();
    }
    
    
</script>
</head>
<body>
	<div class="container"
		style="border: 1px solid #cecece; margin-top: 10px; width: 100%">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab_a" onclick="refreshTable();" data-toggle="tab">Customers</a></li>
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
							<th width="37%;">Name</th>
							<th width="25%;">Email</th>
							<th width="18%;">Phone</th>
							<th width="8%;">Source</th>
							<th width="12%;"></th>
							<th></th>
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
