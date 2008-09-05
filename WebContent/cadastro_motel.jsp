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

		function validar(){
 
			var nome_aux = document.getElementById("nome_motel").value;
			
	  		if ((nome_aux == "")){
				alert("Informe o nome do motel");
				return false;
		  	}else{
		  		return true;
		  	}
			  	      			
		}
		
		function enviar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviarcadastromotel";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		
			
		</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo">
	 		<img src="img/setinha_vermelha.jpg" /><span class="style3 style6">c</span>adastro Motel
	 	</div>
   		<div class="FundoCadastro">

            <form id="frm" name="frm" class="cssform" action="${pageContext.request.contextPath}/enviarcadastromotel" method="post">
				
				<p>
					<label for="nom">Nome:</label>
					<input name="nome_motel" id="nome_motel" value="" size="35" type="text" /> 
				</p>
				
				<p>
					<label for="end">Endereço:</label>
					<input name="endereco_motel" id="endereco_motel" value="" size="35" type="text" /> 					
				</p>
				
				<p>
					<label for="est">Bairro:</label>
					<input name="bairro_motel" id="bairro_motel" value="" size="35" type="text" />  
				</p>
				
				<p>
					<label for="cid">Cidade:</label>
					<input name="cidade_motel" id="cidade_motel" value="" size="35" type="text" />  
				</p>
				
				<p>
					<label for="tel">Tel:</label>
					<input name="telefone_motel" id="telefone_motel" value="" size="20" type="text" /> 
				</p>
				
				<p>
					<label for="sit">Site:</label>
					<input name="site_motel" id="site_motel" value="" size="25" type="text" /> 
				</p>
				
				<p>
					<label for="sit">E-mail:</label>
					<input name="email_motel" id="email_motel" value="" size="35" type="text" />
				</p>
				
				<p>
					<label for="sit">Texto:</label>
					<input name="texto_motel" id="texto_motel" value="" size="35" type="text" />  
				</p>
				
				<p>
					<label for="sit">Prioridade:</label>
					<input name="prioridade_motel" id="prioridade_motel" value="" size="15" type="text" />
				</p>
				<br />
				
				<div style="margin-left: 140px; margin-top:10px;">
					<div class="buttonwrapper">
				  		<a href="javascript:enviar();" class="botao"><span>enviar</span></a>
						<a href="${pageContext.request.contextPath}/home" class="botao" style="margin-left: 6px"><span>cancelar</span></a>
				  	</div>							  				
				</div>
				<br /><br /><br /><br />				
				
									
			</form>
		
		</div>	
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>