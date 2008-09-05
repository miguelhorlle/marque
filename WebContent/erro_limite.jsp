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

		<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" /><span class="style3 style6">O</span>pss!</div>
   		<div id="divTexto2">
		<span class="style5">E</span>sta é a quinta foto que você tenta enviar!<br />
		Infelizmente, o limite de fotos do álbum é 4!<br />
		Se você quiser colocar mais fotos suas na internet, acesse agora mesmo <a href="http://www.noescuro.com.br" target="_blank">www.noescuro.com.br</a>!
		
		<br /><br />
		<a href="${pageContext.request.contextPath}/editarfotos" class="botao"><span>ok</span></a>	
		<br /><br /><br />
		
		</div>
	
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
</html>
</jsp:root>