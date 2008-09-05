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
		
		<script language="javascript">
							
			function voltar(){   													 					
				history.go(-1);
			}
			
			function popupf(foto, cod) {
				var janela=window.open("${pageContext.request.contextPath}/visualizar.jsp?foto="+foto+"&#38;cod="+cod,null,"status=yes,toolbar=no,menubar=no,location=no");
				janela.focus();	
			}
		
		</script>
		
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo">
			<img src="img/setinha_vermelha.jpg" /><span class="style3">á</span>lbum de <span class="style3">F</span>otos de ${apelido_perfil}
	 	</div>
	 	
		<div class="FundoCadastro" >


			<c:choose>
	    	<c:when test="${fotos.foto1 == null  &#38;&#38; fotos.foto2 == null &#38;&#38; fotos.foto3 == null}">
	    		<h3 class="TextoCaixa ">N&#227;o h&#225; fotos no álbum.</h3>
	    		
	    		<div class="buttonwrapper">
					<br /><br />
	  				<a href="javascript:voltar();" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
	  				<br /><br /><br /><br />
	  			</div> 
	  			
			</c:when>
	    	<c:otherwise>							

				<br />			
				<table>
					<tr>					
				
					<c:if test='${fotos.foto1 != null}'> 	
						<td>
						<a class="LinksBranco" href="javascript:popupf('1','${fotos.codigo_sexual}');"><img border="0" src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=1" style="width: 128px; height: 96px;" alt="" /></a>
						</td>
						<td>
						&#160;&#160;
						</td>
					</c:if>
					
					<c:if test='${fotos.foto2 != null}'> 	
						<td>
						<a class="LinksBranco" href="javascript:popupf('2','${fotos.codigo_sexual}');"><img border="0" src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=2" style="width: 128px; height: 96px;" alt="" /></a>
						</td>
						<td>
						&#160;&#160;
						</td>				
					</c:if>
					
					<c:if test='${fotos.foto3 != null}'>
						<td>
						<a class="LinksBranco" href="javascript:popupf('3','${fotos.codigo_sexual}');"><img border="0"  src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=3" style="width: 128px; height: 96px;" alt="" /></a>
						</td>
						<td>
						&#160;&#160;
						</td>
					</c:if>
					
					</tr>		
				</table>
				<br />
				
				<div class="buttonwrapper">
					<br /><br />
	  				<a href="javascript:voltar();" title="voltar" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
	  			</div> 

						
	
			</c:otherwise>
			</c:choose>				


		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>