<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Contact Management</title>
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
                <center> <img src="../img/profile.gif" style="width:60px;height:60px;"></center><br/>
                <div class="form-group ">
                  <input id="j_username" name="j_username" required type="text" class="form-control " style="height:12%;font-size:12px" placeholder="Username" autocomplete="on" autofocus="autofocus" >
                  
                </div>
                <div class="form-group">
                    <input id="j_password" name="j_password" required type="password" class="form-control" style="height:12%;font-size:12px" placeholder="Password" autocomplete="on"  >
                </div>
					<c:if test="${ not empty SPRING_SECURITY_LAST_EXCEPTION}">
						<span style="font-size:10px;color:#FF5733"> Your login attempt was not successful due to ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}.</span>
					</c:if>
					
					
                <div class="align-center">
                    <button type="submit" class="btn btn-info" style="font-size:12px;" id="subbtn">Sign In</button>
                </div>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
	  </form:form>
        </div>
    </div>
</div>

</body>
</html>