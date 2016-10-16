<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%> 

<html>
	<head>
		<%-- <tiles:insertAttribute name="title" ignore="true" /> --%>
		<title>Customer Contact Management	</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
		<link type="text/css" rel="stylesheet"	href="../css/bootstrap.min.css" />
	<style>
		#wrapper { width: 90%; background: #FCFCFC;margin:auto; overflow: hidden; min-height: 550px; }   
		#header { background: #F0F0F0;  color: white;  border-bottom: 1px solid #ccc;}   
		#footer { margin-top:10px; clear: both; background: #F2F2F2;border-top: 1px solid #CCC; height: 30px; }
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