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
   		
   		<meta http-equiv="Pragma" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-cache" />
 		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Expires" content="0" />
		
   		<title>MarqueSexo</title>		
		
		<script language="javascript">
		
		function voltar(){		
			history.go(-1);
		}
		
		function popup(foto, cod) {
			var janela=window.open("${pageContext.request.contextPath}/visualizar.jsp?foto="+foto+"&#38;cod="+cod,null,"status=yes,toolbar=no,menubar=no,location=no");
			janela.focus();	
		}
		
		function popup_webcam(user, password) {
			if((user == "") &#38;&#38; (password == "")){
				alert("Se a página pedir usuário e senha, deixe-os em branco!");
			}else{
				alert("Se a página pedir usuário e senha, forneça: " + user + " e " + password);
			}			
			var janela=window.open("http://${webcam.ip}:${webcam.porta}",null,"status=yes,toolbar=no,menubar=no,location=no");
			janela.focus();	
		}
	
			
		</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo">	 	
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3">p</span>erfil de ${membro.apelido}
	 	</div>
		<div class="FundoCadastro">
		
			<div id="BotaoCadastroOn">
				<ul class="BtOpConteudo">
				<li><a href="${pageContext.request.contextPath}/fotos?codigo_sexual_foto=${membro.codigo_sexual}"><img src="img/aba_maisfotos.gif" alt="mais fotos"  border="0" /></a></li>
				</ul>
			</div>
			<div id="BotaoCadastroOff">
				<ul class="BtOpConteudo">
				<li><a href="${pageContext.request.contextPath}/verjapegou?codigo_pegador=${membro.codigo_sexual}"><img src="img/aba_quempegou.gif" alt="já pegou" border="0" /></a></li>
				</ul>
			</div>
			
			<br /><br />
			<!--
			&#160;&#160;&#160;
			<input name="bsurubas" type="button" value="Ver Surubas" onclick="location.href='${pageContext.request.contextPath}/surubasparticipou?codigo_sexual_suruba=${membro.codigo_sexual}'" />
			-->

       		<div id="divCxResultPerfil">
       		
	       		<div id="divCxFoto">
					<a href="javascript:popup('0','${membro.codigo_sexual}');"><img src="${pageContext.request.contextPath}/foto?codigo_sexual_foto=${membro.codigo_sexual}&#38;nro_foto=0" style="width: 85px; height: 113px;" alt="" border="0" /></a>
				</div>

				<div id="divCxMenuPerfil">
				
					<c:if test='${membro.bloqueado == true}'>				
						-&#062; Membro bloqueado
					</c:if>
					<c:if test='${membro.interessante == true}'>				
						-&#062; Membro interessante
					</c:if>
		   			
		   			<c:if test='${membro.bloqueado == false}'>	
						<c:if test='${membro.online == true}'>							
							-&#062; <a href="javascript:parent.popup_talk('${codigo_sexual}_${membro.codigo_sexual}', '${pageContext.request.contextPath}/chat?cod_sexual=${membro.codigo_sexual}','${membro.apelido}(${membro.codigo_sexual})')" class="LinksMenuHome" >Conversar</a><br />
							<c:if test='${membro.webcam_habilitada == true}'>									
								-&#062; <a href="javascript:popup_webcam('${webcam.username}','${webcam.senha}');" class="LinksMenuHome" >Assistir Webcam</a><br />
							</c:if>	
							
						</c:if>
						<c:if test='${membro.online == false}'>
							-&#062; <a href="${pageContext.request.contextPath}/mensagem?codigo_sexual_mensagem=${membro.codigo_sexual}" class="LinksMenuHome" >Enviar Mensagem</a><br />
						</c:if>	
					</c:if>
					
					<c:if test='${membro.interessante == false &#38;&#38; membro.bloqueado == false}'>		
						-&#062; <a href="${pageContext.request.contextPath}/adicionarinteressante?codigo_sexual_interessante=${membro.codigo_sexual}" target="_self" class="LinksMenuHome">Achei interessante</a><br />
						-&#062; <a href="${pageContext.request.contextPath}/adicionarbloqueado?codigo_sexual_bloqueado=${membro.codigo_sexual}" target="_self" class="LinksMenuHome">Bloquear Membro</a><br />
					</c:if>
					<c:if test='${membro.pegou == false &#38;&#38; membro.bloqueado == false}'>				
						-&#062; <a href="${pageContext.request.contextPath}/verificarjapeguei?codigo_pegado=${membro.codigo_sexual}" target="_self" class="LinksMenuHome">Já peguei!</a>
					</c:if>
					
			
		   		</div>
		   		
		   		<div id="divDadosResultBuscaPerfil">
		   		
		   			<table>
					
		   			<c:if test='${(membro.texto != "")&#38;&#38;(membro.texto != null)}'>
		   				<tr><td colspan="2">
						${membro.apelido} diz: 
						<span class="TextoMostraResult">&quot;${membro.texto}&quot;</span>
						</td></tr>
					</c:if>		
			   		
					<tr><td>
		         	Cód. sexual:
		         	</td><td>
		         	<span class="TextoMostraResult">
		         	${membro.codigo_sexual}
		         	</span>
		         	</td></tr>
		         	<tr><td>
		         	
		          	Sexo: 
		          	</td><td>
		          	<span class="TextoMostraResult">
		          	<c:if test="${membro.sexo == 0}">	
						Masculino
					</c:if>
					<c:if test="${membro.sexo == 1}">	
						Feminino
					</c:if>
					<c:if test="${membro.sexo == 2}">	
						Transexual/Travesti
					</c:if>
					</span>
					</td></tr>
		         	<tr><td>
	
		       		Idade:
		       		</td><td>
		       		<span class="TextoMostraResult">
		       		${membro.idade} anos
		       		</span>
		       		</td></tr>
		         	<tr><td>
		       		
		       		Cidade:
		       		</td><td>
		         	<span class="TextoMostraResult">
		         	${membro.cidade} - ${membro.estado}
		         	</span>
		         	</td></tr>
		         	<tr><td>
	
		       		Estado Civil:
		       		</td><td>
		       		<span class="TextoMostraResult">
		       		<c:if test="${membro.sexo == 0}">
						<c:if test="${membro.estado_civil == 0}">
							Solteiro
						</c:if>
						<c:if test="${membro.estado_civil == 1}">
							Casado
						</c:if>
						<c:if test="${membro.estado_civil == 2}">
							Divorciado
						</c:if>
						<c:if test="${membro.estado_civil == 3}">
							Viúvo
						</c:if>
					</c:if>
					<c:if test="${membro.sexo != 0}">
						<c:if test="${membro.estado_civil == 0}">
							Solteira
						</c:if>
						<c:if test="${membro.estado_civil == 1}">
							Casada
						</c:if>
						<c:if test="${membro.estado_civil == 2}">
							Divorciada
						</c:if>
						<c:if test="${membro.estado_civil == 3}">
							Viúva
						</c:if>
					</c:if>	       		
		       		</span>
		       		</td></tr>
		         	<tr><td>
		       		
		       		Peso:
		       		</td><td>
		       		<span class="TextoMostraResult">		       		
		       		${membro.peso} Kg.		       		
		       		</span>
		       		</td></tr>
		         	<tr><td>
		       		
		       		
		       		Altura:
		       		</td><td>
		       		<span class="TextoMostraResult">
		       		${membro.altura} m.
		       		</span>
		       		</td></tr>
		         	<tr><td>
	
		      		Tipo físico:
		      		</td><td>
		      		<span class="TextoMostraResult">
		      		<c:if test="${membro.tipo_fisico == 0}">
						Magro
					</c:if>
					<c:if test="${membro.tipo_fisico == 1}">
						Médio
					</c:if>
					<c:if test="${membro.tipo_fisico == 2}">
						Em forma
					</c:if>
					<c:if test="${membro.tipo_fisico == 3}">
						Pouco acima do peso
					</c:if>
					<c:if test="${membro.tipo_fisico == 4}">
						Muito acima do peso
					</c:if>
					<c:if test="${membro.tipo_fisico == 5}">
						Musculoso
					</c:if>
					<c:if test="${membro.tipo_fisico == 6}">
						Pequeno
					</c:if>
		       		</span>
		       		</td></tr>
		         	<tr><td>
		       		
		       		Tom de pele:
		       		</td><td>
		       		<span class="TextoMostraResult">
		       		<c:if test="${membro.tom_pele == 0}">
						Branco
					</c:if>
					<c:if test="${membro.tom_pele == 1}">
						Asiático Japonês
					</c:if>
					<c:if test="${membro.tom_pele == 2}">
						Pardo/Mulato
					</c:if>
					<c:if test="${membro.tom_pele == 3}">
						Negro/Africano
					</c:if>
					<c:if test="${membro.tom_pele == 4}">
						Asiático Chinês
					</c:if>
					<c:if test="${membro.tom_pele == 5}">
						Asiático Coreano
					</c:if>
					<c:if test="${membro.tom_pele == 6}">
						Indiano
					</c:if>
					<c:if test="${membro.tom_pele == 7}">
						Latino/Hispânico
					</c:if>
					<c:if test="${membro.tom_pele == 8}">
						Oriente Médio
					</c:if>
					<c:if test="${membro.tom_pele == 9}">
						Outros
					</c:if>
		       		</span>
		       		</td></tr>
		         	<tr><td>
		       		
		       		Faz	sexo com:&#160;&#160;
		       		</td><td>
		       		<span class="TextoMostraResult">
		       		<c:if test="${membro.faz_sexo_com == 0}">
						Mulheres
					</c:if>
					<c:if test="${membro.faz_sexo_com == 1}">
						Homens
					</c:if>
					<c:if test="${membro.faz_sexo_com == 2}">
						Transexual/Travestis
					</c:if>
					<c:if test="${membro.faz_sexo_com == 3}">
						Homens e Mulheres
					</c:if>
					<c:if test="${membro.faz_sexo_com == 4}">
						Homens e Travestis
					</c:if>
					<c:if test="${membro.faz_sexo_com == 5}">
						Mulheres e Travestis
					</c:if>
					<c:if test="${membro.faz_sexo_com == 6}">
						Todos
					</c:if>
		       		</span>
		       		</td></tr>
		         	</table>
		       		<br />

			   </div>
		 	</div>
		 	
		 	<div class="buttonwrapper">
  				<a href="javascript:voltar();" title="voltar ao resultado da busca" target="_self" class="botao" style="margin-left:10px;"><span>voltar</span></a>
  			</div>  

		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="2" height="2" frameborder="0" />
</html>
</jsp:root>