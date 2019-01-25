<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
response.setHeader("Cache-Control", "no-store"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires",0); 
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:: do do do olleh Partner SSO ::</title>
<link type="text/css" href="<%= request.getContextPath() %>/css/s.css" rel="stylesheet" />
</head>

<body class = "bg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

	<div id="wrap">
		<div id="header">
			<!-- Header Start-->
<tiles:insertAttribute name="header" />
			<!-- Header End-->
		</div>
			
		<div id="container">
			<div class="contents-box">
				
				<!-- LeftMenu Start -->
<tiles:insertAttribute name="left" />
				<!-- LeftMenu End -->
					
				<div class="contents">
					<!-- Menu NaviBar Start -->
<tiles:insertAttribute name="navibar" />
					<!-- Menu NaviBar End -->
					<!-- Body Start -->
<tiles:insertAttribute name="body" />
					<!-- Body End -->
				</div>
				<!-- Footer Start-->
				<!-- <div id="footer">Footer</div>-->
				<!-- Footer End-->
			</div>
		</div>
	</div>

</body>
</html>

<!-- <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date()) %> -->