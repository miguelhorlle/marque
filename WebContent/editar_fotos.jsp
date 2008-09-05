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
		
			function check_upload() {
		  		var ext = document.getElementById("arquivo_foto_principal").value;
		 		ext = ext.substring(ext.length-3,ext.length);
		  		ext = ext.toLowerCase();
		  		if((ext != "jpg")&#38;&#38;(ext != "jpeg")&#38;&#38;(ext != "bmp")&#38;&#38;(ext != "png")&#38;&#38;(ext != "gif")) {
		    		alert("Por favor, selecione um arquivo de figura.");
		    		return false; 
		    	}else {
		  		    return true; 
		  		}		  		
		  	}
		  	
		  	function check_upload_outras() {
		  		var ext = document.getElementById("arquivo_foto").value;
		 		ext = ext.substring(ext.length-3,ext.length);
		  		ext = ext.toLowerCase();
		  		if((ext != "jpg")&#38;&#38;(ext != "jpeg")&#38;&#38;(ext != "bmp")&#38;&#38;(ext != "png")&#38;&#38;(ext != "gif")) {
		    		alert("Por favor, selecione um arquivo de figura.");
		    		return false; 
		    	}else {
		  		    return true; 
		  		}		  		
		  	}
		
			function voltar(){   													 					
				history.go(-1);
			}
			
			function popup(foto, cod) {
				var janela=window.open("${pageContext.request.contextPath}/visualizar.jsp?foto="+foto+"&#38;cod="+cod,null,"status=yes,toolbar=no,menubar=no,location=no");
				janela.focus();	
			}
			
			function enviarprincipal(){		
				if(check_upload() == true){
					document.getElementById("upformprincipal").action = "${pageContext.request.contextPath}/atualizarfoto?nro=0";			
					document.getElementById("upformprincipal").method = "post";	
					document.getElementById("upformprincipal").enctype = "multipart/form-data";
					document.getElementById("upformprincipal").submit();		
				}			
			}
			
			function enviaroutras(){	
				if(check_upload_outras() == true){	
					document.getElementById("upformoutras").action = "${pageContext.request.contextPath}/atualizarfoto";			
					document.getElementById("upformoutras").method = "post";	
					document.getElementById("upformoutras").enctype = "multipart/form-data";
					document.getElementById("upformoutras").submit();					
				}
			}
			
			function exibirprincipal(){   													 					
				out = document.getElementById("outras");
			    var sty = out.style;
			    sty.display = "none";
			    
			    pri = document.getElementById("principal");
			    var st = pri.style;
			    st.display = "block";
			}
			
			function exibiroutras(){   													 					
				out = document.getElementById("outras");
			    var sty = out.style;
			    sty.display = "block";
			    
			    pri = document.getElementById("principal");
			    var st = pri.style;
			    st.display = "none";
			}
		
		</script>
		
	</head>
	<body onload="exibirprincipal();">
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo">
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3">m</span>eu <span class="style3">p</span>erfil&#160;
	   		<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">á</span><span class="style6">lbum de fotos
	        </span>
        
	 	</div>
	
    		
    	<c:if test='${fotos.codigo_sexual == codigo_sexual}'>
			<div id="principal" class="FundoCadastro" style="display: none;">	
				
 				<div id="BotaoCadastroOn">
					<ul class="BtOpConteudo">
					<li><a href="javascript:exibirprincipal();"><img src="img/bt_fotoprincipal.gif" alt="foto principal"  border="0" /></a></li>
					</ul>
				</div>
				<div id="BotaoCadastroOff">
					<ul class="BtOpConteudo">
					<li><a href="javascript:exibiroutras();"><img src="img/bt_outrasfotos.gif" alt="outras fotos" border="0" /></a></li>
					</ul>
				</div>
				
				<br /><br />
				
				<div id="divCxFoto">
					<a href="javascript:popup('0','${fotos.codigo_sexual}');"><img src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=0" style="width: 85px; height: 113px;" alt="" border="0" /></a>
				</div>
				
    			<div id="divCxAlbumFotos">
				
					<form class="cssform" id="upformprincipal" name="upformprincipal" method="post" action="${pageContext.request.contextPath}/atualizarfoto?nro=0" enctype="multipart/form-data">
						
						<span class="classh4">
						Escolha a foto:
						<input type="file" size="20" name="arquivo_foto_principal" id="arquivo_foto_principal" accept="image/jpeg; image/jpg; image/png; image/bmp;" /><br /><br />
						</span>
						<br /><br />
						
					</form>	
					
					<p class="MsgCadastro">
					   *Tamanho ideal da foto: até  65
					   kb, no estilo 3x4.<br />
					   Caso o tamanho da foto seja maior do que 65kb,<br />
					   o sistema tentará 
					   redimensioná-la.   
					</p>
					<br />

					<div class="buttonwrapper">
					     <a class="botao" href="javascript:enviarprincipal();"><span>atualizar</span></a>
					     <a class="botao" href="${pageContext.request.contextPath}/brancofoto?nro=0" style="margin-left: 6px"><span>definir foto em branco</span></a>
					</div>
					
				</div>
			</div>
			
			
			<div id="outras" class="FundoCadastro" style="display: none;">	
				
 				<div id="BotaoCadastroOn">
					<ul class="BtOpConteudo">
					<li><a href="javascript:exibirprincipal();"><img src="img/bt_fotoprincipal.gif" alt="foto principal"  border="0" /></a></li>
					</ul>
				</div>
				<div id="BotaoCadastroOff">
					<ul class="BtOpConteudo">
					<li><a href="javascript:exibiroutras();"><img src="img/bt_outrasfotos.gif" alt="outras fotos" border="0" /></a></li>
					</ul>
				</div>
				
				<br /><br />
				
				<div id="divTableCadastro">

					<form class="cssform" id="upformoutras" name="upformoutras" method="post" action="${pageContext.request.contextPath}/atualizarfoto" enctype="multipart/form-data">
						<span class="classh4">
						Escolha a foto:
						<input type="file" size="20" name="arquivo_foto" id="arquivo_foto" accept="image/jpeg; image/jpg; image/png; image/bmp; image/gif;" /><br /><br />
						</span>
						<br /><br />
					</form>
						
					<p class="MsgCadastro">
						*Tamanho ideal da foto: até  65
					   	kb.<br />
					   	Caso o tamanho da foto seja maior do que 65kb,<br />
					   	o sistema tentará 
					   	redimensioná-la. 
				   	</p>
				   	
					<br />
					
					<div class="buttonwrapper">
					    <a class="botao" href="javascript:enviaroutras();" title="enviar foto"><span>enviar foto</span></a>
						<br />
						<br />
						<br />	
					</div>					
					
				</div>
				
				<table>
					<tr>					
				
					<c:if test='${fotos.foto1 != null}'> 	
						<td>
						<a class="LinksBranco" href="javascript:popup('1','${fotos.codigo_sexual}');"><img border="0" src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=1" style="width: 128px; height: 96px;" alt="" /></a>
						<br /><br />
						<a class="botao" style="margin-left: 40px" title="excluir foto" href="${pageContext.request.contextPath}/brancofoto?nro=1" ><span>Excluir</span></a>	
						</td>
						<td>
						&#160;&#160;
						</td>
					</c:if>
					
					<c:if test='${fotos.foto2 != null}'> 	
						<td>
						<a class="LinksBranco" href="javascript:popup('2','${fotos.codigo_sexual}');"><img border="0" src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=2" style="width: 128px; height: 96px;" alt="" /></a>
						<br /><br />
						<a class="botao" style="margin-left: 40px" title="excluir foto" href="${pageContext.request.contextPath}/brancofoto?nro=2"><span>Excluir</span></a>	
		
						</td>
						<td>
						&#160;&#160;
						</td>				
					</c:if>
					
					<c:if test='${fotos.foto3 != null}'>
						<td>
						<a class="LinksBranco" href="javascript:popup('3','${fotos.codigo_sexual}');"><img border="0"  src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${fotos.codigo_sexual}&#38;nro_foto=3" style="width: 128px; height: 96px;" alt="" /></a>
						<br /><br />																	
						<a class="botao" style="margin-left: 40px" title="excluir foto" href="${pageContext.request.contextPath}/brancofoto?nro=3"><span>Excluir</span></a>	
							
						</td>
						<td>
						&#160;&#160;
						</td>
					</c:if>
					
					</tr>		
				</table>
				<br />
				
			</div>
		</c:if>							    

		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>