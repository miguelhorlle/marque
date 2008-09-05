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
		
	 		function validar(){
	 			
				var codigo_aux = document.getElementById("codigo_sexual_esqueci").value;
				var email_aux = document.getElementById("email_esqueci").value;
					
				if ((codigo_aux == "")&#38;&#38;(email_aux == "")){
					alert("Informe seu codigo sexual ou e-mail.");
					return false;
			  	}else{
				  	if ((codigo_aux != "")&#38;&#38;(email_aux != "")){
						alert("Informe somente seu código sexual ou seu e-mail.");
						return false;
				  	}else{
			  			return true;
			  		}
			  	}      			
			}
	
			function enviar(){
	  			if (validar() == true){										 					
					document.getElementById("frm").action = "${pageContext.request.contextPath}/enviaresqueciminhasenha";			
					document.getElementById("frm").method = "post";	
					document.getElementById("frm").submit();					
				}
			}
		</script>
	</head>
	<body bgcolor="black">
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" /><span class="style3 style6">e</span>squeci <span class="style3">M</span>inha <span class="style3">s</span>enha</div>
   		<div id="divTexto">

            <form id="frm" name="frm" action="${pageContext.request.contextPath}/enviaresqueciminhasenha" method="post">
				Informe seu Código Sexual: <input id="codigo_sexual_esqueci" name="codigo_sexual_esqueci" class="TextBox3"  value="" size="10" type="text" /> 
				<br /><br />
				ou informe seu E-mail:<input id="email_esqueci" name="email_esqueci" value="" class="TextBox3" size="55" type="text" /> 
				
				<div class="buttonwrapper">
					<br />
	  				<a href="javascript:enviar();" class="botao"><span>enviar</span></a>
				<a href="${pageContext.request.contextPath}/index.jsp" class="botao" style="margin-left: 6px"><span>cancelar</span></a>
	  			</div>   			
				
				<br /><br /><br />	
				
			</form>
		</div>
		
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
</html>
</jsp:root>