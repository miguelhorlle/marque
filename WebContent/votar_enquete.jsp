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
			
			function enviar(){		
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviarvotoenquete";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		</script>	
	</head>
	<body>
	
	    <jsp:include page="header.jsp" flush="true" />
	    
	    <div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >e</span>nquetes
	 	</div>

			<form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/enviarvotoenquete" method="post">
				
				<div id="divTexto" class="FundoCadastro">
				
					<b>${enquete.pergunta}</b>

					<br /><br />

			   		<table>
						<tbody>		 
			               <c:forEach var="resposta" items="${respostas}" varStatus="s">	
			               		 											  	
							  	<tr>								    
							    	<td>
							    		<input type="radio" id="voto" name="voto" value="${resposta.id}"><b>${resposta.texto}</b></input>
							    		<br />
								    </td>								    
								</tr>			
			
							</c:forEach>					
						</tbody>	
					</table> 
					
					<br />

					<div class="buttonwrapper">
				  		<a href="javascript:enviar();" class="botao" style="margin-left: 19px"><span>confirmar</span></a>
						<a href="javascript:voltar();" class="botao" style="margin-left: 10px"><span>voltar</span></a>
				  	</div>							  				
		
									
				</div>
			</form>

		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>