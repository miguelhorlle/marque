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
	    	<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">m</span>inhas <span class="style3">S</span>urubas
	    </div>
   		<div class="FundoCadastro">
		
		<c:choose>
    	<c:when test="${empty listaSurubasAbertas}">
    		<h3 class="TextoCaixa">Você não está organizando nenhuma suruba atualmente.</h3>
	    	<br /><br /><br /><br />
		</c:when>
    	<c:otherwise>							

    		<h3 class="TextoCaixa">Surubas que você está organizando:</h3>	

			<c:forEach var="suruba1" items="${listaSurubasAbertas}" varStatus="s">	
				<div id="divCxInteressante">
				        <div id="divDadosInteressante">						    

							<b>${suruba1.titulo}</b> , <fmt:formatDate value='${suruba1.data}' type="date" pattern="dd/MM/yy"/> às ${suruba1.hora}:${suruba1.minuto}
							<br /><br />

	      					<div class="buttonwrapper">
				           	
							  	<a href="${pageContext.request.contextPath}/editarsuruba?id_suruba=${suruba1.id}" target="_self" class="botao"><span>editar</span></a>
							  	<a href="${pageContext.request.contextPath}/definirparticipantes?id_suruba=${suruba1.id}" class="botao" target="_self" style="margin-left: 6px"><span>definir participantes</span></a>
							  	<a href="${pageContext.request.contextPath}/cancelarsuruba?id_suruba=${suruba1.id}" class="botao" target="_self" style="margin-left: 6px"><span>cancelar suruba</span></a>

							</div>
						</div>
				    </div>
				<br />		
			</c:forEach>	

			
			<br /><br /><br />								

		</c:otherwise>
		</c:choose>	
		
		<c:choose>
    	<c:when test="${empty listaSurubasFinalizadas}">
		</c:when>
    	<c:otherwise>							
    		
    		<h3 class="TextoCaixa">Surubas que você organizou:</h3>	

			<c:forEach var="suruba2" items="${listaSurubasFinalizadas}" varStatus="s">	
			
				<div id="divCxInteressante">
					<div id="divDadosInteressante">		

						<b>${suruba2.titulo}</b> , <fmt:formatDate value='${suruba2.data}' type="date" pattern="dd/MM/yy"/> às ${suruba2.hora}:${suruba2.minuto}
		  				<br /><br />
						
			           	<div class="buttonwrapper">
			           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba2.id}" target="_self" class="botao"><span>visualizar</span></a>
						  	<a href="${pageContext.request.contextPath}/definirquemparticipou?id_suruba=${suruba2.id}" class="botao" target="_self" style="margin-left: 6px"><span>definir quem participou</span></a>
						  	<a href="${pageContext.request.contextPath}/comentarsuruba?id_suruba=${suruba2.id}" class="botao" target="_self" style="margin-left: 6px"><span>comentar suruba</span></a>

						</div>
					</div>
				</div>
				<br />

			</c:forEach>	

					

		</c:otherwise>
		</c:choose>	
		
		
		
		<br />
 		<div class="buttonwrapper">
			<a href="${pageContext.request.contextPath}/surubas.jsp" title="voltar" target="_self" class="botao" style="color: #fff;text-decoration: none;margin-left:10px;"><span>voltar</span></a>
		</div> 
		<br /><br /><br />
			
		</div>	
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>