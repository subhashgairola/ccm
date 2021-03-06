<style>
td {
	font-size: 12px;
}
</style>

<div class="col-md-20">
	<form id="userForm" method="post" style="display: none;">
	    <span id="email-error" class="error-span"></span><br>
		<table>
			<tbody>
				<tr>
					<td class="col-padding">ID</td>
					<td><input type="text" name="id" id="id" style="height: 30px;"
						readonly="readonly" /></td>
					<td class="col-padding">Name</td>
					<td><input type="text" style="height: 30px;" name="name" /></td>
				</tr>
				<tr>
					<td class="col-padding">Email</td>
					<td><input type="email" style="height: 30px;" name="email" />
					</td>
					<td class="col-padding">Password</td>
					<td><input type="text" style="height: 30px;" name="password" /></td>
				</tr>
				<tr>
					<td class="col-padding">Phone</td>
					<td><input type="text" style="height: 30px;" name="phoneNum" /></td>
					<td class="col-padding">Birthday</td>
					<td><input type="text" style="height: 30px;"  class="datepicker" id="birthDate"
						name="birthDate" /></td>
				</tr>
				<tr>
					<td class="col-padding">Gender</td>
					<td> <input type="radio" id ="gender_1" name="gender" value="1"> Male
                         <input type="radio" id ="gender_2" name="gender" value="2"> Female
                     </td>
					<td class="col-padding">City</td>
					<td><input type="text" style="height: 30px;" name="city" /></td>
				</tr>
				<tr>
					<td class="col-padding">State</td>
					<td><select id="stateId" name="stateId" style="width: 205px;">
					</select></td>
					<td class="col-padding">Zip</td>
					<td><input type="text" style="height: 30px;" name="zip" /></td>
				</tr>
				<tr>
					<td class="col-padding">Country</td>
					<td><input type="text" style="height: 30px;" name="country" /></td>
					<td class="col-padding">IP Address</td>
					<td><input type="text" style="height: 30px;" name="ipAddress" />
					</td>
				</tr>
				<tr>	
					<td class="col-padding">Last Login</td>
					<td><input type="text" style="height: 30px;"
						readonly="readonly" id="lastLogin" name="lastLogin" /></td>
					<td class="col-padding">Creation Date</td>
					<td><input type="text" style="height: 30px;"
						readonly="readonly" id="creationDate" name="creationDate" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td class="col-padding" align="right">
						<button id="saveBtn" name="saveBtn"
							onclick="save(); return false;" type="button"
							class="btn btn-info">Update</button>
						<button id="cancelBtn" name="cancelBtn" data-dismiss="modal"
							aria-hidden="true" type="cancel" class="btn btn-info">Close</button>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="customerDetailId" /> <input type="hidden"
			name="source" /> <input type="hidden" name="jobTitle" /> <input
			type="hidden" name="company" /> <input type="hidden"
			name="mobileNum" /> <input type="hidden" name="updatedBy" /> <input
			type="hidden" name="location" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>