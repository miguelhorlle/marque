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
			<span class="style3" >J</span>á Pegou!
	 	</div>
		<div class="FundoCadastro">
		
			<c:choose>
	    	<c:when test="${empty listaSexosConfirmados}">
	    		<h3 class="TextoCaixa ">Essa pessoa ainda n&#227;o pegou ninguém!</h3>
			</c:when>
	    	<c:otherwise>							
	    			
	    		<h3 class="TextoCaixa ">Pessoas que já pegaram:</h3>			    		
	
				<c:forEach var="sexo" items="${listaSexosConfirmados}" varStatus="s">
				
					<div id="divCxInteressante">
						<div id="divDadosInteressante">

							<br />
						
							<c:if test='${sexo.pegador.codigo_sexual != codigo_pegador}'>	    
			
								<b>${sexo.pegador.apelido}(${sexo.pegador.codigo_sexual})</b>
								
								<c:if test='${sexo.pegador.codigo_sexual != codigo_sexual}'>									  
									<br />
									<div class="buttonwrapper">							
									  	<a href="${pageContext.request.contextPath}/perfil?cod=${sexo.pegador.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a> 	
									</div>		
								</c:if>	
								<c:if test='${(sexo.comentario_pegador != "")&#38;&#38;(sexo.comentario_pegador != null)}'>
									<br />
									Comentário de ${sexo.pegador.apelido}: &quot;<i>${sexo.comentario_pegador}</i>&quot;
								</c:if>	
								
							</c:if>	
							<c:if test='${sexo.pegado.codigo_sexual != codigo_pegador}'>	    
		
								<b>${sexo.pegado.apelido}(${sexo.pegado.codigo_sexual})</b>
								
								<c:if test='${sexo.pegado.codigo_sexual != codigo_sexual}'>	  
									<br />
									<div class="buttonwrapper">							
									  	<a href="${pageContext.request.contextPath}/perfil?cod=${sexo.pegado.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a> 	
									</div>			
								</c:if>	
								<c:if test='${(sexo.comentario_pegado != "")&#38;&#38;(sexo.comentario_pegado != null)}'>
									<br /><br />
									Comentário de ${sexo.pegado.apelido}: &quot;<i>${sexo.comentario_pegado}</i>&quot;
								</c:if>	
								  
							</c:if>	
						</div>
					</div>		      				
				</c:forEach>	

			</c:otherwise>
			</c:choose>	

			
			<div class="buttonwrapper">
				<br /><br />
  				<a href="javascript:history.go(-1);" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
  			</div> 
  			<br /><br /><br />

		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>