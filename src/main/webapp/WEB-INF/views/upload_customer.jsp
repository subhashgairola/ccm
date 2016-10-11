<iframe id="uploadTrg" name="uploadTrg" height="0" width="0"
	frameborder="0" scrolling="yes"></iframe>
<form class="container" method="POST" target="uploadTrg" id="uploadForm" 
	action="upload?${_csrf.parameterName}=${_csrf.token}"
	enctype="multipart/form-data">

	<span id="errorSpan" style="font-size: 11px; color: #FF5733"></span>
	<span id="successSpan" style="font-size: 11px; color: #7FFF00"></span>
    <br>
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
	<input type="file" class="btn-file" name="file" style="font-size: 12px"
		required>

	<div class="form-group">

		<input type="submit" value="Upload File" class="btn-info"
			style="font-size: 12px;margin-top: 10px;">
	</div>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
