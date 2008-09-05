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
			<img src="img/setinha_vermelha.jpg" /><span class="style3">o</span>utros <span class="style3">m</span>embros&#160;
	   		<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">j</span><span class="style6">á Peguei!
	        </span>
	 	</div>
	 	
	 	<div class="FundoCadastro">
		
			<c:choose>
	    	<c:when test="${empty listaSexosPegadoAguardando}">
			</c:when>
	    	<c:otherwise>							
	    	
	    		<h3 class="TextoCaixa ">Pessoas que dizem que pegaram você:</h3>		

				<c:forEach var="sexo1" items="${listaSexosPegadoAguardando}" varStatus="s">	
						
					<div id="divCxInteressante">
						<div id="divDadosInteressante">							
			    
							<br />
							<b>${sexo1.pegador.apelido}(${sexo1.pegador.codigo_sexual})</b>
							
							<br />
							<div class="buttonwrapper">								
								<a href="${pageContext.request.contextPath}/confirmarjapegou?id_sexo=${sexo1.id}" target="_self" class="botao"><span>é verdade!</span></a>
							  	<a href="${pageContext.request.contextPath}/desmentirjapegou?id_sexo=${sexo1.id}" class="botao" target="_self" style="margin-left: 6px"><span>é mentira!</span></a>
							  	<a href="${pageContext.request.contextPath}/perfil?cod=${sexo1.pegador.codigo_sexual}" target="_self" class="botao" style="margin-left: 6px"><span>ver perfil</span></a> 	
							</div>
							
							<c:if test='${(sexo1.comentario_pegador != "")&#38;&#38;(sexo1.comentario_pegador != null)}'>
								<br />
								Comentário de ${sexo1.pegador.apelido}: &quot;<i>${sexo1.comentario_pegador}</i>&quot;
							</c:if>	
			      			
			      		</div>
					</div>
				</c:forEach>						
				
				<br /><br />									
	
			</c:otherwise>
			</c:choose>	
			
			<c:choose>
	    	<c:when test="${empty listaSexosPegadorAguardando}">
			</c:when>
	    	<c:otherwise>						
	    	
	    		<h3 class="TextoCaixa ">Pessoas que você disse que pegou e que ainda não confirmaram:</h3>		

				<c:forEach var="sexo2" items="${listaSexosPegadorAguardando}" varStatus="s">	
						
					<div id="divCxInteressante">
						<div id="divDadosInteressante">		
								
							<br />
							<b>${sexo2.pegado.apelido}(${sexo2.pegado.codigo_sexual})</b>
							
							<br />
							<div class="buttonwrapper">							
							  	<a href="${pageContext.request.contextPath}/perfil?cod=${sexo2.pegado.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a> 	
							</div>

					    	<br /><br />	
		
						</div>
					</div>	      					      				
				</c:forEach>
				
				<br /><br />								
	
			</c:otherwise>
			</c:choose>	
						
			<c:choose>
	    	<c:when test="${empty listaSexosConfirmados}">
	    		<h3 class="TextoCaixa ">Você ainda n&#227;o pegou ninguém!</h3>
			</c:when>
	    	<c:otherwise>							
	    			
	    		<h3 class="TextoCaixa ">Pessoas que você já pegou:</h3>		
	    		
    				<c:forEach var="sexo" items="${listaSexosConfirmados}" varStatus="s">
					
						<div id="divCxInteressante">
							<div id="divDadosInteressante">
						
								<br />
								<c:if test='${sexo.pegador.codigo_sexual != codigo_sexual}'>	    
									
									<b>${sexo.pegador.apelido}(${sexo.pegador.codigo_sexual})</b>
									<br />
									<div class="buttonwrapper">							
									  	<a href="${pageContext.request.contextPath}/perfil?cod=${sexo.pegador.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a> 	
									</div>
																
									<c:if test='${(sexo.comentario_pegador != "")&#38;&#38;(sexo.comentario_pegador != null)}'>
										<br />
										Comentário de ${sexo.pegador.apelido}: &quot;<i>${sexo.comentario_pegador}</i>&quot;
									</c:if>
		
								</c:if>	
								<c:if test='${sexo.pegado.codigo_sexual != codigo_sexual}'>	    
		
									<b>${sexo.pegado.apelido}(${sexo.pegado.codigo_sexual})</b>
									<br />
									<div class="buttonwrapper">							
									  	<a href="${pageContext.request.contextPath}/perfil?cod=${sexo.pegado.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a> 	
									</div>
									
									<c:if test='${(sexo.comentario_pegado != "")&#38;&#38;(sexo.comentario_pegado != null)}'>
										<br />
										Comentário de ${sexo.pegado.apelido}: &quot;<i>${sexo.comentario_pegado}</i>&quot;
									</c:if>
									
								</c:if>							
							</div>
						</div>	
      					      				
					</c:forEach>	
						
	
			</c:otherwise>
			</c:choose>	
		

		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>