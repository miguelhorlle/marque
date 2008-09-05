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
    	   	
    	<meta http-equiv="Pragma" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Expires" content="0" />
				 
   		<link rel="stylesheet" href="marque.css" type="text/css" />
   		<link rel="stylesheet" href="menu.css" type="text/css" />
   		<script language="javascript">
   		
   			function validar_login(){	 
				var email_aux = document.getElementById("email_login").value;
				var senha_aux = document.getElementById("senha_login").value;
				
		  		if ((email_aux == "")||(senha_aux == "")){
					alert("Informe o seu e-mail e senha corretamente.");
					return false;
			  	}else{
			  		return true;
			  	}				  	      			
			}
		
			function enviar_login(){
	  			if (validar_login() == true){												 					
					document.getElementById("frm_login").action = "${pageContext.request.contextPath}/login";			
					document.getElementById("frm_login").method = "post";	
					document.getElementById("frm_login").submit();					
				}
			}

			function popupf(figura) {
				var janela=window.open("${pageContext.request.contextPath}/ver_figura.jsp?figura="+figura,null,"status=yes,toolbar=no,menubar=no,location=no");
				janela.focus();	
			}
		
		</script>
   		<title>MarqueSexo</title>		
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />


   		<div class="TextoTitulo">
   			<img src="img/setinha_vermelha.jpg" />
   			<span class="style3 style6">c</span>omo <span class="style3">f</span>unciona
   		</div>
   		
   		<div id="divTexto">
   		<span class="style5">C</span>adastre-se no site, preenchendo seu perfil. Você receberá um código 
		sexual e poderá conversar com várias pessoas para 
		marcar sexo.<br />
		<span class="style5">A</span>qui você pode : procurar pessoas através de características que te 
		interessam, mandar mensagens, conversar online via chat e via webcam,
		marcar aquelas que você achar interessante, as que já pegou, bloquear 
		para que não te perturbem, marcar sexo em grupo ou à dois e muito mais. <br /><br />

		<a href="${pageContext.request.contextPath}/cadastro.jsp" alt="Cadastro" class="LinksLogin"><span class="style5">C</span>adastre-se 
		</a>hoje mesmo e libere as fantasias que há em você !   
		
		<br /><br />
		<b>Atenção:</b> Este site é somente para pessoas maiores de 18 anos.							
		<br />
		
		</div>
				

		<jsp:include page="footer.jsp?fig=2" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>