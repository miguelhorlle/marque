<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" version="2.0">
<jsp:directive.page language="java"	contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" />
<jsp:directive.page isELIgnored="false" />
<jsp:text><![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]></jsp:text>
<jsp:text><![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]></jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
    	<meta http-equiv="content-language" content="cs" />    	
   		<link rel="stylesheet" href="marque.css" type="text/css" />
   		<link rel="stylesheet" href="menu.css" type="text/css" />
   		<title>MarqueSexo</title>		
   		
	</head>
	<body>
	
	    <jsp:include page="header.jsp" flush="true" />
	    
	    <div class="TextoTitulo">
	    	<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">S</span>urubas
	    </div>
   		<div class="FundoCadastro">
   		
   			<br />
			<a href="${pageContext.request.contextPath}/busca_surubas_disponiveis.jsp" class="botao" style="margin-left: 15px"><span>Buscar Surubas Disponíveis</span></a>
			
			<br /><br />
			<a href="${pageContext.request.contextPath}/minhassurubas" class="botao" style="margin-left: 15px"><span>Minhas Surubas</span></a>
			
			<br /><br />
			<a href="${pageContext.request.contextPath}/organizarsuruba" class="botao" style="margin-left: 15px"><span>Organizar Suruba</span></a>
			
			<br /><br />
			<a href="${pageContext.request.contextPath}/surubasconfirmadas" class="botao" style="margin-left: 15px"><span>Surubas Confirmadas para Ir</span></a>
			
			<br /><br />
			<a href="${pageContext.request.contextPath}/surubasquisparticipar" class="botao" style="margin-left: 15px"><span>Surubas que Você Quis Participar</span></a>
			
			<br /><br />
			<a href="${pageContext.request.contextPath}/busca_surubas_finalizadas.jsp" class="botao" style="margin-left: 15px"><span>Buscar Surubas Finalizadas</span></a>
			
			<br /><br />
			
		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="2" height="2" frameborder="0" />
</html>
</jsp:root>