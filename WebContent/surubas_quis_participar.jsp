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
	    	<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">S</span>urubas que <span class="style3">v</span>ocê Quis Participar
	    </div>
   		<div class="FundoCadastro">
		
		<c:choose>
    	<c:when test="${empty listaSurubasAguardando}">
   			<h3 class="TextoCaixa">Não há surubas que você queira participar agora</h3>
	    	<br />
		</c:when>
    	<c:otherwise>	
    	
    		<br /><br />
 	
    		<h3 class="TextoCaixa">Surubas aguadando aprovação do organizador:</h3>
    		<br />
			
			<c:forEach var="suruba5" items="${listaSurubasAguardando}" varStatus="s">	
				<div id="divCxInteressante">
					<div id="divDadosInteressante">	
						<br />
						<b>${suruba5.titulo}</b> , <fmt:formatDate value='${suruba5.data}' type="date" pattern="dd/MM/yy"/> às ${suruba5.hora}:${suruba5.minuto}
						<br /><br />

	      				<div class="buttonwrapper">				           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba5.id}" target="_self" class="botao"><span>visualizar</span></a>
						</div>
					</div>
			    </div>
				<br />
			</c:forEach>	

		</c:otherwise>
		</c:choose>	
		
		
		
		<c:choose>
    	<c:when test="${empty listaSurubasFoi}">
		</c:when>
    	<c:otherwise>		
    	
    		<br /><br />	
    		
    		<h3 class="TextoCaixa">Surubas que você realmente foi:</h3>
    		<br />							

			<c:forEach var="suruba1" items="${listaSurubasFoi}" varStatus="s">
				<div id="divCxInteressante">
					<div id="divDadosInteressante">	
						<br />
						<b>${suruba1.titulo}</b> , <fmt:formatDate value='${suruba1.data}' type="date" pattern="dd/MM/yy"/> às ${suruba1.hora}:${suruba1.minuto}
						<br /><br />

	      				<div class="buttonwrapper">				           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba1.id}" target="_self" class="botao"><span>visualizar</span></a>
						  	<a href="${pageContext.request.contextPath}/comentarsuruba?id_suruba=${suruba1.id}" class="botao" target="_self" style="margin-left: 6px"><span>comentar suruba</span></a>
						</div>
					</div>
			    </div>
				<br />						  

			</c:forEach>	
								

		</c:otherwise>
		</c:choose>	

		<c:choose>
    	<c:when test="${empty listaSurubasNaoFoi}">
		</c:when>
    	<c:otherwise>
    	
    		<br /><br />
    	
    		<h3 class="TextoCaixa">Surubas que você não foi:</h3>
    		<br />

			<c:forEach var="suruba2" items="${listaSurubasNaoFoi}" varStatus="s">	
				<div id="divCxInteressante">
					<div id="divDadosInteressante">	
	    
					<br />
					<b>${suruba2.titulo}</b> , <fmt:formatDate value='${suruba2.data}' type="date" pattern="dd/MM/yy"/> às ${suruba2.hora}:${suruba2.minuto}
					<br /><br />

				   	<div class="buttonwrapper">				           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba2.id}" target="_self" class="botao"><span>visualizar</span></a>
						</div>
					</div>
			    </div>
				<br />	
			</c:forEach>								

		</c:otherwise>
		</c:choose>	

		
		<c:choose>
    	<c:when test="${empty listaSurubasRecusado}">
		</c:when>
    	<c:otherwise>	
    	
    		<br /><br />	

    		<h3 class="TextoCaixa">Surubas que você não foi aceito para participar:</h3>
    		<br />

			<c:forEach var="suruba3" items="${listaSurubasRecusado}" varStatus="s">	
				<div id="divCxInteressante">
					<div id="divDadosInteressante">		
						<br />
						<b>${suruba3.titulo}</b> , <fmt:formatDate value='${suruba3.data}' type="date" pattern="dd/MM/yy"/> às ${suruba3.hora}:${suruba3.minuto}
						<br /><br />
	
					    <div class="buttonwrapper">				           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba3.id}" target="_self" class="botao"><span>visualizar</span></a>
						</div>
					</div>
			    </div>
			    
				<br />
			</c:forEach>						

		</c:otherwise>
		</c:choose>	
		
				
		
		<c:choose>
    	<c:when test="${empty listaSurubasDesistiu}">
    	
		</c:when>
    	<c:otherwise>		
    	
    		<br /><br />
    		
    		<h3 class="TextoCaixa">Surubas que você desistiu de participar:</h3>
    		<br />

			<c:forEach var="suruba4" items="${listaSurubasDesistiu}" varStatus="s">	
				<div id="divCxInteressante">
					<div id="divDadosInteressante">	
						<br />
						<b>${suruba4.titulo}</b> , <fmt:formatDate value='${suruba4.data}' type="date" pattern="dd/MM/yy"/> às ${suruba4.hora}:${suruba4.minuto}
						<br /><br />

				    	<div class="buttonwrapper">				           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba4.id}" target="_self" class="botao"><span>visualizar</span></a>
						</div>
					</div>
			    </div>
			    
				<br />

			</c:forEach>	


		</c:otherwise>
		</c:choose>	
		
		<br />

 		<div class="buttonwrapper">
			<a href="${pageContext.request.contextPath}/surubas.jsp" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
		</div> 
		<br />
			
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>