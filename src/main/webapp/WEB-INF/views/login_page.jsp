<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JTD Spring MVC 4.2.1 and Spring Security 4.0.2 Login Example</title>
</head>
<body>
<div style="margin-left:20px; margin-bottom:25px; font-size:25px; ">JTD Spring MVC 4.2.1 and Spring Security 4.0.2 Login Example</div>

<form:form name='loginForm' id="loginForm" action="../j_spring_security_check" method='POST'>
	  
	  	<table  width="50%" border="0" align="center" style="padding-bottom:50px;">
	  <tr><td colspan="2">&nbsp; </td></tr>
	<tr><td colspan="2" style="color:red;">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </td></tr>
	<tr><td colspan="2">&nbsp; </td></tr>
		<tr ><td colspan="2" style="color:blue;font-size: 20px;"> <b>Login Here </b></td></tr>
	
	
			<tr>
				<td  width="35%">Username</td>
				<td ><input id="j_username" name="j_username" type="text" placeholder="Username" size="30" autocomplete="on" autofocus="autofocus" > </td>
				</tr>
	  			<tr>
				<td >Password</td>   
	        	<td ><input id="j_password" name="j_password" type="password" placeholder="Password" size="30" autocomplete="on"  ></td>
	        	</tr>
	  		<tr><td colspan="2">&nbsp;</td></tr>
	  	
	   		<tr>
	   		<td>&nbsp; </td>
	        <td style="height:20px; " class="tdLabelC"> 
	           <button id="subbtn" >Sign In</button>
	        </td>
	        </tr>
	    </table>
	    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	  </form:form>
</body>
</html>