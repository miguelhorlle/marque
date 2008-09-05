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
	    	<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">s</span>ala <span class="style3">S</span>exual
	    </div>
   		<div id="divTexto">
   		
   		&quot;<span class="style5">A</span>faga-me com tua
        luz, aquece-me com teu calor.<br />
        Meu instinto me conduz, me eleva ao teu fervor.<br />

        Prenda-me como um bicho ouu seduza-me com doçura.<br />
        Não me cobres compromisso, mas leva-me a loucura.<br />
        Desnude-se de preconceitos, realize tuas fantasias.<br />
        Explore os meus trejeitos, saiamos desta afasia.<br />
        São corpos nus que se entregam, em dança de puro erotismo.<br />

        Animais febris que se esfregam,Lancinantes sem puritanismo.<br />
        Hormônios lançados ao ar, Cheiros e multi-sabores.<br />
        Variações do verbo amar, perda total de pudores.<br />
        Tomemos pois uma atitude, <br />
        Nossos corpos demandam desejo&quot;<br />

        <br />
        <br />
		
		<c:choose>
    	<c:when test="${empty listaMembros}">

    		<span class="LinkTextoHome"><strong>N&#227;o h&#225; ninguém
	        na sala sexual no momento! </strong></span><br />
	
	        <br />	        
	        <div class="buttonwrapper">
	  			<a class="botao" href="${pageContext.request.contextPath}/sala"><span>verificar novamente</span></a>
	  			<a class="botao" href="javascript:parent.popup_talk('SalaSexual','${pageContext.request.contextPath}/chat?room=SalaSexual','Sala Sexual')" style="margin-left: 6px"><span>entrar</span></a>
	  		</div>    		
    		
		</c:when>
    	<c:otherwise>	
    		<span class="LinkTextoHome"><strong>As seguintes participantes estão na Sala Sexual: </strong></span><br /><br />
				
    		<table border="0">		
				<c:forEach var="membro" items="${listaMembros}" varStatus="s">
				<tr>
				<td>
					${membro.apelido}(${membro.codigo_sexual}), ${membro.cidade} - ${membro.estado}&#160;&#160;
				</td>
				<td>		
					<a class="botao" href="${pageContext.request.contextPath}/perfil?cod=${membro.codigo_sexual}"><span>ver perfil</span></a>
				</td>
				</tr>      				      				
				</c:forEach>
			</table>
			<br /><br />
			<div class="buttonwrapper">
				<a class="botao" href="${pageContext.request.contextPath}/sala"><span>atualizar lista</span></a>
				<a class="botao" href="javascript:parent.popup_talk('SalaSexual','${pageContext.request.contextPath}/chat?room=SalaSexual','Sala Sexual')" style="margin-left: 6px"><span>entrar</span></a>
			</div>

		</c:otherwise>
		</c:choose>	
		
		<br /><br /><br />		
		
		</div>		
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="2" height="2" frameborder="0" />
</html>
</jsp:root>