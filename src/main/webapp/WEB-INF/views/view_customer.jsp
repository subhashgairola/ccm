
<div class="col-md-12">
<form id="userForm" method="post" style="display: none;">

				<div class="form-group">
					<label class="col-md-3 control-label">ID</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="id"
							disabled="disabled" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">Name</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="name" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Email</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="email" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">Password</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="password" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Phone No.</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="phoneNum" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">Birthdate</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="birthDate" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Gender</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="gender" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">Creation Date</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="creationDate" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Last Login date</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="lastLogin" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">IP Address</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="ipAddress" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-3 control-label">Country</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="country" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">City</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="city" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-3 control-label">Zip</label>
					<div class="col-md-3">
						<input type="text" class="form-control" name="zip" />
					</div>
				</div>

				<!-- <div class="form-group">
					<div class="col-md-3 col-xs-offset-3">
						<button id="saveBtn" type="button" class="btn btn-default">Save</button>
					</div>
				</div> -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
			</form>
</div>