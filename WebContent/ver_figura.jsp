<%@ page isErrorPage="false" errorPage="erro.jsp"%>
<% 
	String figura = new String(request.getParameter("figura"));
%>
<html>
	<head>
	<title>Foto</title>	
	</head>
	<body>
	<div style="padding:5px 5px 5px 5px;overflow:auto;">
		<img src="/marquesexo/img/<%=figura%>" />
	</div>
	</body>
</html>
