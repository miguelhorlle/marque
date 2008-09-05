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
			<span class="style3" >d</span>efinir Participantes
	 	</div>
		<div class="FundoCadastro">
		
			<c:choose>
	    	<c:when test="${empty listaPartipacoes}">
	    		<h3 class="TextoCaixa2">Não há ninguém para participar desta suruba!</h3>
	    		<div class="buttonwrapper">
	  				<a href="javascript:history.go(-1);" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
	  			</div> 
	  			<br /><br /><br /><br /><br /><br />
			</c:when>
	    	<c:otherwise>							

				
				<c:forEach var="part" items="${listaPartipacoes}" varStatus="s">	
				
					<div id="divCxInteressante">
				        <div id="divDadosInteressante">			    
		    
						<b>${part.participante.apelido}(${part.participante.codigo_sexual})</b>
						<br /><br />

						
			           	<div class="buttonwrapper">
						
							<c:if test='${part.participante.codigo_sexual != organizador.codigo_sexual}'>						
								<a href="${pageContext.request.contextPath}/perfil?cod=${part.participante.codigo_sexual}" class="botao"><span>ver perfil</span></a>		
	
							</c:if>	
									
							<c:if test='${part.status == 0}'>						
	
								<a href="${pageContext.request.contextPath}/enviaraceitarparticipantesuruba?id_suruba=${part.suruba.id}&#38;codigo_sex=${part.participante.codigo_sexual}" class="botao" style="margin-left: 6px"><span>aceitar participante</span></a>
								<a href="${pageContext.request.contextPath}/enviarrecusarparticipantesuruba?id_suruba=${part.suruba.id}&#38;codigo_sex=${part.participante.codigo_sexual}" class="botao" style="margin-left: 6px"><span>recusar participante</span></a>
	
							</c:if>
							<c:if test='${part.status == 1}'>
								Participante Aceito
							</c:if>
							<c:if test='${part.status == 2}'>
								Participante Recusado
							</c:if>
							<c:if test='${part.status == 3}'>
								Participante Desistiu
							</c:if>
						
						</div>
			
	  
		      			</div>
				    </div>
					<br />	
		      		
				</c:forEach>	
	
			</c:otherwise>
			</c:choose>	
			
			<div class="buttonwrapper">
  				<a href="javascript:history.go(-1);" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
  			</div>  
			
		
		</div>		
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>