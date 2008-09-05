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
	    
	    <div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >e</span>nquetes
	 	</div>
		<div class="FundoCadastro">

				
			<c:choose>
	    	<c:when test="${empty enquetes}">
	    		<h3 class="TextoCaixa">Não há nenhuma enquete em votação!</h3>
	    		<br /><br /><br /><br />
			</c:when>
	    	<c:otherwise>							

				<c:forEach var="enquete" items="${enquetes}" varStatus="s">	
				
					<div id="divCxInteressante">
						<div id="divDadosInteressante">	
								    
							<b>${enquete.pergunta}</b>
							<br /><br />
		
							<div class="buttonwrapper">				           	
								  	<a href="${pageContext.request.contextPath}/verresultadoenquete?id_enquete=${enquete.id}" target="_self" class="botao"><span>ver resultado</span></a>
								  	<a href="${pageContext.request.contextPath}/votarenquete?id_enquete=${enquete.id}" class="botao" target="_self" style="margin-left: 6px"><span>votar</span></a>
								</div>
						</div>
				    </div>
					<br />	  
				</c:forEach>	

			</c:otherwise>
			</c:choose>	
			
			<br />
	 		<div class="buttonwrapper">
				<a href="javascript:history.go(-1);" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
			</div> 
			<br /><br /><br />

		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>