<div class="col-md-20">
	<form id="userForm" method="post" style="display:none;">
		<table>
			<tbody>
				<tr>
					<td class="col-padding">ID</td>
					<td><input type="text" name="id" style="height:30px;"
						readonly="readonly" /></td>
					<td class="col-padding">Name</td>
					<td>
							<input type="text" style="height:30px;" name="name" />
					</td>
				</tr>
				<tr>
					<td class="col-padding">Email</td>
					<td><input type="text" style="height:30px;" name="email" /></td>
					<td class="col-padding">Password</td>
					<td><input type="text" style="height:30px;" name="password" /></td>
				</tr>

				<tr>
					<td class="col-padding">Phone No.</td>
					<td><input type="text" style="height:30px;" name="phoneNum" /></td>
					<td class="col-padding">Birthdate</td>
					<td><input type="text" style="height:30px;" name="birthDate" />
					</td>
				</tr>

				<tr>
					<td class="col-padding">Gender</td>
					<td><input type="text" style="height:30px;" name="gender" /></td>
					<td class="col-padding">Creation Date</td>
					<td><input type="text" style="height:30px;" readonly="readonly" name="creationDate" />
					</td>
				</tr>

				<tr>
					<td class="col-padding">Last Login date</td>
					<td><input type="text" style="height:30px;" readonly="readonly"  name="lastLogin" />
					</td>
					<td class="col-padding">IP Address</td>
					<td><input type="text" style="height:30px;" name="ipAddress" />
					</td>
				</tr>
				<tr>
					<td class="col-padding">Country</td>
					<td><input type="text" style="height:30px;" name="country" /></td>
					<td class="col-padding">City</td>
					<td><input type="text" style="height:30px;" name="city" /></td>
				</tr>
				<tr>
					<td class="col-padding">Zip</td>
					<td><input type="text" style="height:30px;" name="zip" /></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td class="col-padding">
						<button id="saveBtn" name="saveBtn" onclick="save(); return false;" type="button"
							class="btn btn-info">Save</button>
					</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="customerDetailId" />
		<input type="hidden" name="source" />
		<input type="hidden" name="jobTitle" />
		<input type="hidden" name="company" />
		<input type="hidden" name="mobileNum" />
		<input type="hidden" name="updatedBy" />
		<input type="hidden" name="location" />
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</div>