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
	 	
	 	<div class="TextoTitulo">
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">o</span>nde <span class="style3">m</span>arcar
	 	</div>
   		<div id="divTexto">
   		<span class="style5">D</span>epois de um encontro virtual, que tal um encontro real?<br /><br />
    	<b>Motéis e Casas de Swing</b><br /><br />
    	
    	<c:choose>
    	<c:when test="${empty listaMoteis}">
    		N&#227;o h&#225; anúncios cadastrados.
		</c:when>
    	<c:otherwise>							
    	
					
			<c:forEach var="motel" items="${listaMoteis}" varStatus="s">					    
			
				:: ${motel.nome}<br />
				&#160;${motel.endereco} <br />		
				&#160;${motel.bairro} - ${motel.cidade} <br />					
				<c:if test='${motel.telefone != ""}'>
					&#160;Telefone: ${motel.telefone} <br />
				</c:if>
				<c:if test='${motel.site != ""}'>
					&#160;Site: <a href="${motel.site}" target="_blank" class="LinksLogin">${motel.site}</a> <br />
				</c:if>
				<c:if test='${motel.email != ""}'>
					&#160;E-mail: <a href="mailto:${motel.email}" class="LinksLogin">${motel.email}</a><br />
				</c:if>
				
				<c:if test='${permissao != null}'> 
					<c:if test='${motel.texto != ""}'>
						&#160;${motel.texto} <br />
					</c:if>	
				</c:if>	
				
				<br /><br />
      				      				
			</c:forEach>									

		</c:otherwise>
		</c:choose>

   		<br />
   		Para anunciantes, entre em <a href="${pageContext.request.contextPath}/fale_conosco.jsp" alt="Fale Conosco" class="LinksLogin">contato</a> conosco.
   		
   		</div>
   				

		<jsp:include page="footer.jsp?fig=3" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>