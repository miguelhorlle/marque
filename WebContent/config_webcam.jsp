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
   		
   		<script language="javascript" type="text/javascript">
   		
   		function validar(){
	 
			var ip_aux = document.getElementById("ip_webcam").value;
			
	  		if ((ip_aux == "")){
				alert("Informe o IP da sua webcam!");
				return false;
		  	}else{
	  			return true;
		  	}			  	     			
		}
   		   		
   		function atualizar(){
   			if (validar() == true){	
				document.getElementById("frm").action = "${pageContext.request.contextPath}/atualizarwebcam";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();	
			}
		}
		
		function habilitar(){
								 				
			document.getElementById("frm").action = "${pageContext.request.contextPath}/habilitarwebcam";			
			document.getElementById("frm").method = "post";	
			document.getElementById("frm").submit();					

		}
		
		function desabilitar(){
								 				
			document.getElementById("frm").action = "${pageContext.request.contextPath}/desabilitarwebcam";			
			document.getElementById("frm").method = "post";	
			document.getElementById("frm").submit();					

		}
		
		function exibirconfig(){   													 					
			ger = document.getElementById("geral");
		    var sty = ger.style;
		    sty.display = "none";
		    
		    con = document.getElementById("config");
		    var st = con.style;
		    st.display = "block";
		}
		
		function exibirgeral(){   													 					
			ger = document.getElementById("geral");
		    var sty = ger.style;
		    sty.display = "block";
		    
		    con = document.getElementById("config");
		    var st = con.style;
		    st.display = "none";
		}
		
		
			
		</script>
	</head>
	<body onload="exibirgeral();">
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >w</span>ebcam
	 	</div>
	 	
		<div id="divTexto">
		
			<div id="geral" style="display: none;">
			
				&quot;<span class="style5">V</span>ontade de te ver...<br />
		        E olhar bem o teu rosto,<br />
		        Talvez um beijo te dar,<br />
		        Conhecer de tua boca o gosto.<br />
		        <br />
		        Vontade de te ver...<br />
		        E tuas mãos de leve tocar<br />
		        Talvez te enlaçar num abraço<br />
		        E conhecer o teu abraçar.<br />
		        <br />
		        Vontade de te ver...<br />
		        E tocar teu corpo em arrepio.<br />
		        Fazer-te conhecer meu carinho<br />
		        E talvez preencher teu vazio.<br />
		        <br />
		        Vontade de te ver...<br />
		        Deixar-te meu corpo tocar.<br />
		        Trocar contigo energia,<br />
		        Descobrir novo modo de amar.<br />
		        <br />
		        Vontade de te ver...<br />
		        E a ti inteiro de me dar.<br />
		        Vontade de te ter...<br />
		        E por fim poder te tomar!&quot;<br />
		        <br />
		        <br />
		        <div class="buttonwrapper">
		  			<a href="javascript:exibirconfig();" target="_self" class="botao"><span>configurar webcam</span></a>  </div>
		        </div>
			
			</div>
			
			<div id="config" class="FundoCadastro" style="display: none;">
			
				<h3 class="TextoCaixa " >Informe os dados de sua webcam para que todos do site possam te ver:</h3>
				
			
				<form id="frm" name="frm" class="cssform" action="${pageContext.request.contextPath}/atualizarwebcam" method="post">
				
					<p>
					<label for="ip">IP:</label>
					<input type="text" name="ip_webcam" id="ip_webcam" value="${webcam.ip}" /></p>
					
					<p>
					<label for="porta">Porta:</label>
					<input type="text" name="porta_webcam" id="porta_webcam" value="${webcam.porta}" /></p>
					
					<p>
					<label for="username">Username:</label>
					<input type="text" name="username_webcam" id="username_webcam" value="${webcam.username}" />
					
					</p>
					
					<p>
					<label for="senha">Senha:</label>
					<input type="text" id="senha_webcam" name="senha_webcam" value="${webcam.senha}" />
					</p>
					
					<div style="margin-left: 145px; margin-top:10px;">
						<div class="buttonwrapper">
							<a href="javascript:atualizar();" class="botao"><span>atualizar web</span></a>
							<a href="javascript:exibirgeral();" target="_self" class="botao" style="margin-left: 6px"><span>voltar</span></a>
						</div>
					</div>

					<br /><br />
					
					<c:if test='${webcam.habilitado == 1}'>
						<h3 class="TextoCaixa " >Sua Webcam está habilitada no site.<br /><br />
						<a href="javascript:desabilitar();" class="botao"><span>desabilitar webcam</span></a>						
						<br /><br />
						</h3>
					</c:if>
					
					<c:if test='${webcam.habilitado == 0}'>
						<h3 class="TextoCaixa " >Sua Webcam está desabilitada no site.<br />
						<a href="javascript:habilitar();" class="botao"><span>habilitar webcam</span></a>						
						<br /><br />
						</h3>
					</c:if>
					
					<br /><br />
					
					<h3 class="TextoCaixa " >Se você não sabe como colocar sua webcam na internet, <br />
					<span>clique <a href="${pageContext.request.contextPath}/como_configurar.jsp" target="_blank" class="LinkTextoHome">aqui.</a></span></h3>
		
				</form>
				
			</div>
		
		<br /><br />
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>