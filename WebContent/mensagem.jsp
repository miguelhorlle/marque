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
		
			function voltar(){   	
				history.go(-1);
			}
	
 			
 			function validar(){
 				var assunto_aux = document.getElementById("assunto_mensagem").value;
 				var texto_aux = document.getElementById("texto_mensagem").value;
 				
 				if ((assunto_aux == "")||(texto_aux == "")){
					alert("Preencha o assunto e o texto da mensagem.");
					return false;
			  	}else{
			  		return true;
			  	}
			  	
       			
			}

			function enviar(){
   				if (validar() == true){													 					
					document.getElementById("frm").action = "${pageContext.request.contextPath}/enviarmensagem";			
					document.getElementById("frm").method = "post";	
					document.getElementById("frm").submit();					
				}
 			}
	</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
	 	<span class="style3 style6">E</span>nviar <span class="style3">m</span>ensagem para ${membro.apelido}(${membro.codigo_sexual})</div>
	 	
		<div id="divTexto">
		
			<div class="FundoFale">
				<form id="frm" name="frm" action="${pageContext.request.contextPath}/enviarmensagem" method="post">
					<table border="0" cellspacing="2" cellpadding="0">					
				  	<tr>
				    	<td height="24" valign="top" class="classh3">Assunto:</td>
				    	<td><input id="assunto_mensagem" name="assunto_mensagem" type="text" class="TextBox2" /></td>
				  	</tr>
				  	<tr>
				    	<td height="74" valign="top" class="classh3">Mensagem:</td>
				    	<td>
				    	<jsp:text><![CDATA[ <textarea id="texto_mensagem" name="texto_mensagem" rows="5" class="TextBox2"></textarea>]]></jsp:text>
				      	</td>
				  	</tr>
				  	<tr>
				    <td>&#160;</td>
				    <td>
						<br />
		
				    	<a href="javascript:enviar();" target="_self" class="botao"><span>enviar</span></a>				    		    		
				    	<a href="javascript:voltar();" target="_self" class="botao" style="margin-left: 6px"><span>voltar</span></a>
    	
				    </td>
				    <td><input id="codigo_sexual_mensagem" name="codigo_sexual_mensagem" value="${membro.codigo_sexual}" type="hidden" /></td>
				  	</tr>
					</table>
					 				
				</form>
			</div>			
		</div>	

		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>