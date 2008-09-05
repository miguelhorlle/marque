<%@ page isErrorPage="false" errorPage="erro.jsp"%>
<% 
	String foto = new String(request.getParameter("foto"));
	String cod = new String(request.getParameter("cod"));
%>
<html>
	<head>
	<title>Foto</title>	
	</head>
	<body>
	<div style="padding:5px 5px 5px 5px;overflow:auto;">
		<img src="/marquesexo/foto?codigo_sexual_foto=<%=cod%>&nro_foto=<%=foto%>" />
	</div>
	</body>
</html>
