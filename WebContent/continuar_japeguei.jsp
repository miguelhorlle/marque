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
   		
   		<meta http-equiv="Pragma" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Expires" content="0" />
		
   		<title>MarqueSexo</title>	
   		
   		<script language="javascript">		
				
			function voltar(){   													 					
					history.go(-1);
			}
		</script>
   				
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >j</span>á Pegou??
	 	</div>
		<div id="divTexto">	

			<br />
			
			<form id="frm" name="frm" action="${pageContext.request.contextPath}/enviarjapeguei" method="post">
				Você está dizendo que você e ${pegado.apelido}(${pegado.codigo_sexual}) já se pegaram??<br />
				Se foi isso mesmo que você quis dizer, clique em "Confirmar", senão clique em "Voltar".
				

				<br /><br />

				<a href="${pageContext.request.contextPath}/enviarjapeguei?codigo_pegado=${pegado.codigo_sexual}" class="botao"><span>confirmar</span></a>
				<a href="javascript:voltar();" class="botao" style="margin-left: 6px"><span>voltar</span></a>
				
				<br /><br /><br />

			</form>			

		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />

</html>
</jsp:root>