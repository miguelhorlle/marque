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
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">r</span>ss
	 	</div>
   		<div id="divTexto2">

		<span class="style5">O</span> RSS é amplamente utilizado para compartilhar as últimas novidades de um site.<br />
		É fácil você ficar informado o tempo todo! Basta acessar um arquivo RSS de um site que o 
		disponibilize e se preferir, adicione nos "Favoritos" do seu navegador!<br />
		No MarqueSexo, temos dois arquivos RSS:
		<br /><br /><br />
		
		<span class="LinkTextoHome"><strong>
		1 - Informa quantas pessoas estão online no site sem você precisar estar logado.
		</strong></span><br />
		Clique no link para se inscrever: <br />
		<a style="link {color: #fff;text-decoration:none}; visited {color: #cc6666;text-decoration:none;}; active {color: #cc6666;text-decoration:none};hover {color: #cc6666;text-decoration:underline};" href="http://feeds.feedburner.com/NroDeUsuariosOnline" target="_blank">http://feeds.feedburner.com/NroDeUsuariosOnline</a>
		
		<br /><br /><br />
		<span class="LinkTextoHome"><strong>
		2 - Informa os apelidos de todas as pessoas que estão online no site sem você precisar estar logado.
		</strong></span><br />
		Clique no link para se inscrever: <br />
		<a href="http://feeds.feedburner.com/UsuariosOnlineNoMarqueSexo" target="_blank">http://feeds.feedburner.com/UsuariosOnlineNoMarqueSexo</a>
		
		<br /><br /><br /><br />			

		</div>	
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>