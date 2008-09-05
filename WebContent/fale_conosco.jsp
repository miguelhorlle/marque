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
 				var nome_aux = document.getElementById("nome_fale_conosco").value;
 				var assunto_aux = document.getElementById("assunto_fale_conosco").value;
 				var texto_aux = document.getElementById("texto_fale_conosco").value;
 				
 				if ((assunto_aux == "")||(texto_aux == "")||(nome_aux == "")){
					alert("Preencha o assunto, o seu nome e o texto da mensagem.");
					return false;
			  	}else{
			  		return true;
			  	}       			
			}

			function enviar(){
   				if (validar() == true){										 					
					document.getElementById("frm").action = "${pageContext.request.contextPath}/enviarfaleconosco";			
					document.getElementById("frm").method = "post";	
					document.getElementById("frm").submit();					
				}
 			}
	</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
	 	<span class="style3 style6">f</span>ale <span class="style3">c</span>onosco</div>
	 	
		<div id="divTexto">
			<span class="style5">E</span>ntre em contato conosco através do formulário abaixo, deixando
			suas opiniões, críticas, sugestões, etc. 
			<br /><br />
			
			<div class="FundoFale">
				<form id="frm" name="frm" action="${pageContext.request.contextPath}/enviarfaleconosco" method="post">
					<table border="0" cellspacing="2" cellpadding="0">
					<c:if test='${permissao == null}'>
					  	<tr>
					    	<td width="79" height="24" class="classh3"  valign="top">Nome:</td>			    	
					    	<td width="346"><input id="nome_fale_conosco" name="nome_fale_conosco" value="" type="text" class="TextBox2" /></td>
					  	</tr>
					  	<tr>
					    	<td class="classh3"  valign="top">E-mail:</td>
					    	<td><input id="email_fale_conosco" nome="email_fale_conosco" type="text" value="" class="TextBox2" /></td>
					  	</tr>
				  	</c:if>
				  	<c:if test='${permissao != null}'>
					  	<tr>
					    	<td width="79" height="24" class="classh3"  valign="top">Nome:</td>
					    	<td width="346"><input id="nome_fale_conosco" name="nome_fale_conosco" value="${nome}" type="text" class="TextBox2" /></td>
					  	</tr>
					  	<tr>
					    	<td class="classh3"  valign="top">E-mail:</td>
					    	<td><input id="email_fale_conosco" name="email_fale_conosco" value="${email}" type="text" class="TextBox2" /></td>
					  	</tr>
				  	</c:if>
				  	<tr>
				    	<td height="24" valign="top" class="classh3">Assunto:</td>
				    	<td><input id="assunto_fale_conosco" name="assunto_fale_conosco" type="text" class="TextBox2" /></td>
				  	</tr>
				  	<tr>
				    	<td height="74" valign="top" class="classh3">Mensagem:</td>
				    	<td>
				    	<jsp:text><![CDATA[ <textarea id="texto_fale_conosco" name="texto_fale_conosco" rows="5" class="TextBox2"></textarea>]]></jsp:text>
				      	</td>
				  	</tr>
				  	<tr>
				    <td>&#160;</td>
				    <td>
				   		<br />
				    	<a href="javascript:enviar();" target="_self" class="botao"><span>enviar</span></a>
				    	
				    	<c:if test='${permissao == null}'>				    		
				    		<a href="${pageContext.request.contextPath}/index.jsp" target="_self" class="botao" style="margin-left: 6px"><span>cancelar</span></a>
				    	</c:if>
				    	<c:if test='${permissao != null}'>
				    		<a href="${pageContext.request.contextPath}/home" target="_self" class="botao" style="margin-left: 6px"><span>cancelar</span></a>
				    	</c:if>

				    </td>
				    <td>&#160;</td>
				  	</tr>
					</table>	
					<br /><br />								
				</form>
				
			</div>			
		</div>	
		
		
		<jsp:include page="footer.jsp?fig=7" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>