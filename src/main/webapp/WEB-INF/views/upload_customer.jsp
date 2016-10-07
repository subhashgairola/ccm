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
	