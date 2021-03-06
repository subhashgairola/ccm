<iframe id="uploadTrg" name="uploadTrg" height="0" width="0"
	frameborder="0" scrolling="yes"></iframe>
<form class="container" method="POST" target="uploadTrg" id="uploadForm" 
	action="upload?${_csrf.parameterName}=${_csrf.token}"
	enctype="multipart/form-data">

	<span id="errorSpan" class="error-span"></span>
	<span id="successSpan" class="success-span"></span>
    <br>
    <br>
	<div class="form-group">
		<select name="sourceType" required style="font-size: 12px" onchange="showFormatMgs(this.value)">
			<option value="" selected>Select Source</option>
			<option value="APSIS">APSIS</option>
			<option value="NAV">NAV</option>
			<option value="Magento">Magento</option>
			<option value="ReederID">ReederID</option>
			<option value="Zendesk">Zendesk</option>
		</select>
	</div>
	<div id="formatDiv" style="color: #00529B;"></div><br>
	<div class="form-group">
	<input type="file" class="btn-file" name="file" style="font-size: 12px"
		required>
	</div>
	<div class="form-group">
		<button type="submit" name="file" class="btn btn-default" style="font-size: 12px;margin-top: 10px;">Upload File</button>
	</div>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
