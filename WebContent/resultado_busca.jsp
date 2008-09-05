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
	   		<img src="img/setinha_vermelha.jpg" /><span class="style3">o</span>utros <span class="style3">m</span>embros&#160;
	   		<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">b</span><span class="style6">uscar
	        membros <img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /></span> <span class="style3 style6 style5">r</span><span class="style6">esultado
	       	busca</span>
       	</div>


	   	<div id="FundoResultado">
	   		
	   			<c:choose>
    			<c:when test="${empty listaMembros}">
		    		<h3 class="TextoCaixa2">Nenhum membro foi encontrado. Tente novamente!</h3>
		    		
		    		<div class="buttonwrapper">
	  				<a href="${pageContext.request.contextPath}/busca.jsp" title="voltar para página de busca" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
	  				</div> 
	  				<br /><br /><br /><br /><br /><br />
		    		
				</c:when>
		    	<c:otherwise>
		    	
		    		<display:table name="sessionScope.listaMembros" id="membro" pagesize="10">	
					<display:setProperty name="paging.banner.placement" value="both" />
		 			<display:column title="">

			       	<div id="divCxResult">
			       	
			       		<table>
			       		<tr><td>
				    	<div id="divCxFoto">
				    		<a href="${pageContext.request.contextPath}/perfil?cod=${membro.codigo_sexual}"><img src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${membro.codigo_sexual}&#38;nro_foto=0" style="width: 85px; height: 113px;" alt="" border="0" /></a>
				    	</div>
				    	</td><td>
					    <div id="divDadosResultBusca">

					       	${membro.apelido}(${membro.codigo_sexual})<br />
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
								Faz sexo com: Travestis<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 3}'>
								Faz sexo com: Homens e Mulheres<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 4}'>
								Faz sexo com: Homens e Travestis<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 5}'>
								Faz sexo com: Mulheres e Travestis<br />
							</c:if>
							<c:if test='${membro.faz_sexo_com == 6}'>
								Faz sexo com: Todos<br />
							</c:if>

					       	<c:if test='${(membro.texto != "")&#38;&#38;(membro.texto != null)}'>
								&quot;<i>${membro.texto}</i>&quot;<br />								
							</c:if>
							
														
							<br />
							<div class="buttonwrapper">
				            	<a href="${pageContext.request.contextPath}/perfil?cod=${membro.codigo_sexual}" target="_self" class="botao" style="color: #fff;text-decoration: none;"><span>ver perfil</span></a>
								<c:if test='${membro.bloqueado == false}'>
									<c:if test='${membro.online == true}'>						
										<!-- <input name="bconv" value="Conversar" type="button" onclick="javascript:parent.popup_talk('${codigo_sexual}_${membro.codigo_sexual}', '${pageContext.request.contextPath}/chat?cod_sexual=${membro.codigo_sexual}','${membro.apelido}(${membro.codigo_sexual})')" />
										-->								
										<c:if test='${membro.webcam_habilitada == true}'>	
											<span class="TextoMostraResult">&#160;&#160;Online c/ Webcam</span>
										</c:if>	
										<c:if test='${membro.webcam_habilitada == false}'>	
											<span class="TextoMostraResult">&#160;&#160;Online</span>
										</c:if>	
		
									</c:if>		
									<c:if test='${membro.online == false}'>										
										<a href="${pageContext.request.contextPath}/mensagem?codigo_sexual_mensagem=${membro.codigo_sexual}" target="_self" class="botao" style="color: #fff;text-decoration: none;margin-left: 10px"><span>enviar mensagem</span></a>
									</c:if>
								</c:if>	
								<br /><br />
						    </div>
			   
					   	</div>
					   </td></tr>
					   </table>
				   	</div>
				   	<br />
				   	
				   	</display:column>
		 	 		</display:table>	
					
					<br />
		 	 		<div class="buttonwrapper">
		  				<a href="${pageContext.request.contextPath}/busca.jsp" title="voltar para página de busca" target="_self" class="botao" style="color: #fff;text-decoration: none;margin-left:10px;"><span>voltar</span></a>
		  			</div> 
		  			<br /><br /><br />
		    	
		    	</c:otherwise>
				</c:choose>

		</div>			
	    <br />
					
		
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="2" height="2" frameborder="0" />
</html>
</jsp:root>