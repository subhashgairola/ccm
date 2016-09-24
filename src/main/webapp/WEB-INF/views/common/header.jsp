<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#header{
	background-color: #B2A7A7; 
	height:54px;
	/* moz-border-radius: 5px;
   	webkit-border-radius: 5px;
   	border-radius: 5px; */
  
}

#header h1{
	color: #FFFFFF; 
	margin-top:0px;
	margin-left:20px;
	font-size: 24;
	font-family: serif;
	font-weight: bolder;
}
#header h5{
	color: #FFFFFF; 
	margin-top:0px;
	margin-left:20px;
	font-size: 12;
	font-family:Arial;
	text-align: right;
		
	
}
</style>



<div id="header" >
<table width="100%" border="0" class="ui-widget-header" > <tr> <td valign="top" width="40%"><h1>JavaTechDevelopers.com</h1>
<%-- <img src="<%=request.getContextPath()%>/images/logo.jpg" width="200" height="70" style="margin: 0;"/> --%>
</td> <td width="60%" valign="bottom"><h5>
 <c:if test="${pageContext.request.userPrincipal.name != null}">
		Welcome : ${pageContext.request.userPrincipal.name} | <a href="../login/logout" style="text-decoration: none;"><font color="white">Logout</font></a> 
</c:if>
</h5></td> </tr></table>

</div>

<%-- <img src="<%=request.getContextPath()%>/images/logo-facile.jpg" width="170" height="60" /> --%>