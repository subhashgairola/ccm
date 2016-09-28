<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#header{
	height:80px;
	/* moz-border-radius: 5px;
   	webkit-border-radius: 5px;
   	border-radius: 5px; */
}

#header h1{
color: #155092; 
		margin-top:0px;
	margin-left:0px;
	font-size: 32;
	font-family: serif;
	font-weight: bold;
}
#header h5{
	color: #155092; 
	margin-top:0px;
	margin-right:10px;
	margin-left:20px;
	font-size: 12;
	font-family:Arial;
	text-align: right;
		
	
}
</style>



<div id="header" >
<table width="100%" border="0" class="ui-widget-header" > <tr> <td valign="top" width="20%">
<img src="<%=request.getContextPath()%>/img/logo.png" width="200" height="70" style="margin: 0;"/> 
</td><td  width="50%"><h1>Customer Contact Management System</h1>
</td> <td width="30%" valign="bottom" ><h5 >
 <c:if test="${pageContext.request.userPrincipal.name != null}">
		Welcome : ${pageContext.request.userPrincipal.name} | <a href="../login/logout" style="text-decoration: none;">Logout</a> 
</c:if>
</h5></td> </tr></table>

</div>