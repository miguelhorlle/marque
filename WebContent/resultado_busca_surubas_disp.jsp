<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" xmlns:display="urn:jsptld:http://displaytag.sf.net" version="2.0">
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
		
		<script language="javascript">
							
			function voltar(){   													 					
				history.go(-1);
			}
		
		</script>
		
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo">
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3">s</span>urubas&#160;
	   		<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">b</span><span class="style6">uscar
	        suruba <img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /></span> <span class="style3 style6 style5">r</span><span class="style6">esultado
	       	busca</span>      	
      	</div>
	   	
	   	<div id="FundoResultado">


			<c:choose>
	    	<c:when test="${empty listaSurubas}">
	    		<h3 class="TextoCaixa2">Não foi encontrada nenhuma suruba nesta localidade! Tente outra!</h3>
		    		
	    		<div class="buttonwrapper">
  				<a href="${pageContext.request.contextPath}/busca_surubas_disponiveis.jsp" title="voltar pa" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
  				</div> 
  				<br /><br /><br /><br /><br /><br />
	  				
			</c:when>
	    	<c:otherwise>							
	    	
				<display:table name="sessionScope.listaSurubas" id="suruba" pagesize="10">	
			 	<display:column title="">
	  			
	 				<div id="divCxResult">						    

						<b>${suruba.titulo}</b> , <fmt:formatDate value='${suruba.data}' type="date" pattern="dd/MM/yy"/> às ${suruba.hora}:${suruba.minuto}
					    <br /><br />
		
						<div class="buttonwrapper">
				           	
						  	<a href="${pageContext.request.contextPath}/versuruba?id_suruba=${suruba.id}" target="_self" class="botao"><span>visualizar</span></a>
						  	<a href="${pageContext.request.contextPath}/participarsuruba?id_suruba=${suruba.id}" class="botao" target="_self" style="margin-left: 6px"><span>participar</span></a>

						</div>
						
					</div>
					
					<br />
				       				      				
				</display:column>
			 	</display:table>
			 	 
			 	<br />
		 	 	<div class="buttonwrapper">
  				<a href="${pageContext.request.contextPath}/busca_surubas_disponiveis.jsp" title="voltar pa" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
  				</div> 
		  		<br /><br /><br />		
			 	 		
			</c:otherwise>
			</c:choose>
							
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="2" height="2" frameborder="0" />
</html>
</jsp:root>