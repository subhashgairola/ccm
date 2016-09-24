<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%> 

<html>
	<head>
		<%-- <tiles:insertAttribute name="title" ignore="true" /> --%>
		<title>JTD Spring Security 4.0.2 Login Example with mysql
	</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<style>
		#wrapper { width: 1000px; background: #FFFFFF;margin:auto; overflow: hidden; min-height: 400px; }   
		#header { background: black; color: white; }   
		#footer { margin-top:10px; clear: both; background: #F5F5F5;border-top: 1px solid #CCC; height: 30px; }
	</style>
 			
	</head>

	<body >
	
	<div id="wrapper">  
		<div id="header"> <tiles:insertAttribute name="header" /> </div>  
		<div id="wrapper"> <tiles:insertAttribute name="body" /> </div>  
		<div id="footer"> <tiles:insertAttribute name="footer" /></div>   
	</div> 
	
	</body>
</html>