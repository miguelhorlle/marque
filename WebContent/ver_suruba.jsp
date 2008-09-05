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
			<span class="style3" >s</span>uruba
	 	</div>
		<div id="divTexto" class="FundoCadastro">

			&#160;&#160;&#160;<b>${suruba.titulo}</b>
			<br /><br />
			&#160;&#160;&#160;${suruba.cidade} - ${suruba.estado}, dia &#160;<fmt:formatDate value='${suruba.data}' type="date" pattern="dd/MM/yy"/> às ${suruba.hora}:${suruba.minuto}
			<br />
			&#160;&#160;&#160;${suruba.local}
			<br />
			&#160;&#160;&#160;${suruba.obs}
										  
   			<br /><br />
   			
   			<c:choose>
	    	<c:when test="${empty listaParticipacoes}">
	    		<h3 class="TextoCaixa">Não há participantes para esta suruba!</h3>
	    		<br /><br /><br /><br />
			</c:when>
    		<c:otherwise>
	    	
	    		&#160;&#160;&#160;<i>Participantes: </i>
	    		&#160;
	    		<c:forEach var="part" items="${listaParticipacoes}" varStatus="s">
	    			<c:if test='${part.participante.codigo_sexual != codigo_sexual}'>
	    				<a href="/marquesexo/perfil?cod=${part.participante.codigo_sexual}">${part.participante.apelido}(${part.participante.codigo_sexual})</a> , &#160;
	    			</c:if>	
	    			<c:if test='${part.participante.codigo_sexual == codigo_sexual}'>
	    				${part.participante.apelido}(${part.participante.codigo_sexual}), &#160;
	    			</c:if>	
	    		</c:forEach>
	    	</c:otherwise>
			</c:choose>	
			
			
			<c:choose>
	    	<c:when test="${empty listaComentarios}">
			</c:when>
    		<c:otherwise>
	    		
	    		<br /><br />
	    		&#160;&#160;&#160;<i>Comentários: </i>
	    		<br />
	    		<c:forEach var="com" items="${listaComentarios}" varStatus="s">
	    			&#160;&#160;&#160;<b>${com.participacao.participante.apelido}(${com.participacao.participante.codigo_sexual})</b> disse: &quot;${com.comentario}&quot;<br />
	    		</c:forEach>
	    	</c:otherwise>
			</c:choose>	


			<br />
	 		<div class="buttonwrapper">
				<a href="javascript:history.go(-1);" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
			</div> 
			<br />
		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>