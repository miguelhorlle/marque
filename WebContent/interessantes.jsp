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
	   		<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">i</span><span class="style6">nteressantes
	        </span>
	 	</div>
	 		
	 	<div class="FundoCadastro">

			<c:choose>
	    	<c:when test="${empty listaInteressantes}">
	    		<h3 class="TextoCaixa ">N&#227;o h&#225; membros que você achou interessantes.</h3>
			</c:when>
	    	<c:otherwise>
	    	
	    		<h3 class="TextoCaixa ">Você acha esses membros interessantes!</h3>		
	    	
				<c:forEach var="membro" items="${listaInteressantes}" varStatus="s">
				
					<div id="divCxInteressante2">
				        <div id="divDadosInteressante">		        
				        	<b>${membro.apelido}(${membro.codigo_sexual})</b><br />
				           	${membro.idade} anos,
				           	
				           	<c:if test="${membro.sexo == 0}">
								<c:if test='${membro.estado_civil == 0}'>
									Solteiro, 
								</c:if>
								<c:if test='${membro.estado_civil == 1}'>
									Casado, 
								</c:if>
								<c:if test='${membro.estado_civil == 2}'>
									Divorciado, 
								</c:if>
								<c:if test='${membro.estado_civil == 3}'>
									Viúvo, 
								</c:if>	
							</c:if>
							<c:if test="${membro.sexo != 0}">
								<c:if test='${membro.estado_civil == 0}'>
									Solteira, 
								</c:if>
								<c:if test='${membro.estado_civil == 1}'>
									Casada, 
								</c:if>
								<c:if test='${membro.estado_civil == 2}'>
									Divorciada, 
								</c:if>
								<c:if test='${membro.estado_civil == 3}'>
									Viúva, 
								</c:if>	
							</c:if>
							
							${membro.cidade} - ${membro.estado}<br />
							<c:if test='${membro.faz_sexo_com == 0}'>
								Faz sexo com: Mulheres<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 1}'>
								Faz sexo com: Homens<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 2}'>
								Faz sexo com: Transexuais<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 3}'>
								Faz sexo com: Homens e Mulheres<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 4}'>
								Faz sexo com: Homens e Transexuais<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 5}'>
								Faz sexo com: Mulheres e Transexuais<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 6}'>
								Faz sexo com: Todos<br />
							</c:if>
							
							<c:if test='${membro.qtd_pegou != 0}'>
								Já pegou ${membro.qtd_pegou} pessoa(s)!<br />
							</c:if>
							<c:if test='${membro.qtd_pegou == 0}'>
								Ainda não pegou ninguém<br />
							</c:if>
							
							<c:if test='${(membro.texto != "")&#38;&#38;(membro.texto != null)}'>
								<i>${membro.texto}</i><br />
							</c:if>



				           	
				           	<br />
				           	<div class="buttonwrapper">
				           	
							  	<a href="${pageContext.request.contextPath}/perfil?cod=${membro.codigo_sexual}" target="_self" class="botao"><span>ver perfil</span></a>
							  	<a href="${pageContext.request.contextPath}/excluirinteressante?codigo_sexual_interessante=${membro.codigo_sexual}" class="botao" target="_self" style="margin-left: 6px"><span>excluir da lista </span></a>
							  	
							  	
							  	<c:if test='${membro.online == true}'>
									<!-- 
									<a href="javascript:parent.popup_talk('${codigo_sexual}_${membro.codigo_sexual}', '${pageContext.request.contextPath}/chat?cod_sexual=${membro.codigo_sexual}','${membro.apelido}(${membro.codigo_sexual})" target="_self" class="botao" style="margin-left: 6px"><span>conversar</span></a>
									-->
									<c:if test='${membro.webcam_habilitada == true}'>	
										&#160;&#160;<b>Online c/ Webcam</b>
									</c:if>	
									<c:if test='${membro.webcam_habilitada == false}'>	
										&#160;&#160;<b>Online</b>
									</c:if>	
				
								</c:if>
								<c:if test='${membro.online == false}'>
									<a href="${pageContext.request.contextPath}/mensagem?codigo_sexual_mensagem=${membro.codigo_sexual}" target="_self" class="botao" style="margin-left: 6px"><span>enviar mensagem</span></a>
								</c:if>	
							  	
							</div>
						</div>
				    </div>
				<br />			      				      				
				</c:forEach>									
	
			</c:otherwise>
			</c:choose>	
			
			<br /><br />
			
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>