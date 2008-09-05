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
	
 			
 			function validar(){
 			
 				var download_aux = document.getElementById("link_download").value;
 				var remocao_aux = document.getElementById("link_remocao").value;
 				
 				if ((download_aux == "")||(remocao_aux == "")){
					alert("Informe o link de download e de remoção do seu vídeo.");
					return false;
			  	}else{
			  		return true;
			  	}       			
			}

			function enviar(){
   				if (validar() == true){											 					
					document.getElementById("frm").action = "${pageContext.request.contextPath}/enviarlink";			
					document.getElementById("frm").method = "post";	
					document.getElementById("frm").submit();					
				}
 			}
	</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >e</span>nviar Vídeo
	 		</div>
			<div id="divTexto">	

			<br />
			
			<form name="frm" id="frm" action="${pageContext.request.contextPath}/enviarlink" method="post">
			
				Você pode participar da TV MarqueSexo enviando seu vídeo para nós!<br />
				Se o vídeo não conter cenas violentas e que ofendam a moral de alguém, o 
				seu vídeo será exibido na TV MarqueSexo!<br />
				Acesse o site <a href="http://rapidshare.com" target="_blank">http://rapidshare.com</a> e faça o upload do seu vídeo.<br />
				Após o seu arquivo ter carregado, vão aparecer dois links pra você: o primeiro
				é o link de download do seu vídeo e o outro é o link de remoção do arquivo no site.<br />
				Copie e cole estes dois links nos campos abaixo e clique em Enviar.<br />
				Quando nossa equipe baixar seu vídeo, nós mesmos iremos removê-lo do rapidshare
				para você! <br />
				Informe também um título para seu vídeo!<br />
				Não perca tempo e nos envie logo seu vídeo!
				
				<br /><br /><br />
					
				Link para download: <input name="link_download" id="link_download" value="" size="70" type="text" /> 
				<br /><br />
				Link para remoção: <input name="link_remocao" id="link_remocao" value="" size="71" type="text" /> 
				<br /><br />
				Título do vídeo: <input name="titulo_video" id="titulo_video" value="" size="72" type="text" /> 

				<br /><br />
				<a href="javascript:enviar();" class="botao"><span>enviar</span></a>	
				<a href="${pageContext.request.contextPath}/home" class="botao" style="margin-left: 6px"><span>cancelar</span></a>
				<br /><br /><br />
				
			</form>			

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