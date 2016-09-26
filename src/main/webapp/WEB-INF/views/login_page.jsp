<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JTD Spring MVC 4.2.1 and Spring Security 4.0.2 Login Example</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/bootstrap.min.css" />" />
	<style>
	/*
	body {
    background:#ADD8E6;
}
*/
.form_bg {
    background-color:#ccffeb;
    color:#666;
    padding:20px;
    border-radius:2px;
    position: absolute;
    border:1px solid #fff;
    margin: auto;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    width: 250px;
    height: 220px;
}
.align-center {
    text-align:center;
}
	</style>
</head>
<body>

<div class="container" >
    <div class="row">
        <div class="form_bg">
           <form:form name='loginForm' id="loginForm" action="../j_spring_security_check" method='POST'>
                <center> <img src="../img/profile.png" style="width:60px;height:60px;"></center><br/>
                <div class="form-group ">
                  <input id="j_username" name="j_username" required type="text" class="form-control " style="height:12%;font-size:12px" placeholder="Username" autocomplete="on" autofocus="autofocus" >
                  
                </div>
                <div class="form-group">
                    <input id="j_password" name="j_password" required type="password" class="form-control" style="height:12%;font-size:12px" placeholder="Password" autocomplete="on"  >
                </div>
			
						<span style="font-size:10px;color:red;">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
					
                <div class="align-center">
                    <button type="submit" class="btn btn-info" style="font-size:12px;" id="subbtn">Sign In</button>
                </div>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	  </form:form>
        </div>
    </div>
</div>

<!--
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
	  --!>
</body>
</html>