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
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">m</span>embros Online
	 	</div>
   		<div id="divTexto">
	 	
	 	<c:if test='${permissao != null}'> 
			<c:choose>
			<c:when test="${empty listaMembros}">
	    		<span class="LinkTextoHome"><strong>N&#227;o h&#225; ninguém online no momento! </strong></span><br />
	    		<br /><br />
	    		
	    		<br />	        
		        <div class="buttonwrapper">
		  			<a class="botao" href="${pageContext.request.contextPath}/online"><span>verificar novamente</span></a>
		  		</div> 
					    		
	    		
			</c:when>
	    	<c:otherwise>	

	    		<span class="LinkTextoHome"><strong>Os seguintes membros estão online:</strong></span><br /><br />			
	    		<table border="0">			
					<c:forEach var="membro" items="${listaMembros}" varStatus="s">
						<tr>
						<td>
							${membro.apelido}(${membro.codigo_sexual}), ${membro.cidade} - ${membro.estado}&#160;&#160;
						</td>
						<td>		
							<a href="${pageContext.request.contextPath}/perfil?cod=${membro.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a>
						</td>
						</tr>      				      				
					</c:forEach>
				</table>
				<br /><br />	        
		        <div class="buttonwrapper">
		  			<a class="botao" href="${pageContext.request.contextPath}/online"><span>atualizar lista</span></a>
		  		</div> 

			</c:otherwise>
			</c:choose>	

		</c:if>

		
		</div>
		<br />
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>