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
	    	<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">S</span>urubas que <span class="style3">p</span>articipou
	    </div>
   		
   		<div class="FundoCadastro">

			<c:if test='${qtdSurubasNaoFoi != 0}'>
				<h3 class="TextoCaixa">Disse que ia em ${qtdSurubasNaoFoi} suruba(s) e não foi!</h3><br /><br />
			</c:if>
			<c:if test='${qtdSurubasDesistiu != 0}'>
				<h3 class="TextoCaixa">Desistiu de ir em ${qtdSurubasDesistiu} suruba(s)!</h3><br /><br />
			</c:if>
			<c:if test='${qtdSurubasRecusado != 0}'>
				<h3 class="TextoCaixa">Não foi aceito para participar de ${qtdSurubasRecusado} suruba(s)!</h3><br /><br />
			</c:if>
	
			
			<c:choose>
	    	<c:when test="${empty listaSurubasParticipou}">
	    		<h3 class="TextoCaixa">Ainda não foi em nenhuma suruba!</h3>
	    		<br /><br /><br /><br />
			</c:when>
	    	<c:otherwise>							

				<c:forEach var="suruba1" items="${listaSurubasParticipou}" varStatus="s">	
					<div id="divCxInteressante">
						<div id="divDadosInteressante">		

							<b>${suruba1.titulo}</b> , <fmt:formatDate value='${suruba1.data}' type="date" pattern="dd/MM/yy"/> às ${suruba1.hora}:${suruba1.minuto}
							<br /><br />
  
			      			<div class="buttonwrapper">				           	
							  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba1.id}" target="_self" class="botao"><span>visualizar</span></a>
							</div>
						</div>
				    </div>
					<br />	

				</c:forEach>	

				
				<br /><br />									
	
			</c:otherwise>
			</c:choose>			
			
			<br />
	 		<div class="buttonwrapper">
				<a href="${pageContext.request.contextPath}/surubas.jsp" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
			</div> 
			<br /><br /><br />		
		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>