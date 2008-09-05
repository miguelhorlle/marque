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
    	
    	<meta http-equiv="Pragma" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Expires" content="0" />
		
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
   		
   		</script>
   		<title>MarqueSexo</title>			
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	
   		<div id="divTexto">
   			
	   		<span class="style3">M</span>arque <span class="style3">S</span>exo é um site adulto de relacionamento.
			Aqui você pode conhecer solteiros(as) , casados(as) , esteja você 
			à procura de um bate-papo mais picante, webcams com nudez ou principalmente à procura de sexo!<br />
			Torne suas fantasias mais loucas em realidade!
			<br /><br />
			<p><span class="style5">I</span>nsinue. <span class="style4">Provoque.</span>
			<span class="style5"> P</span>rove sensações virtuais e reais...<br />
			<a href="${pageContext.request.contextPath}/cadastro.jsp" alt="Cadastro" class="LinksLogin">
			<span class="style5">C</span>adastre-se já. </a> <span class="style5">É</span> Grátis. </p>	
			<br /><br />
		
		</div>
		
		<jsp:include page="footer.jsp?fig=1" flush="true" />		
		
	</body>
</html>
</jsp:root>