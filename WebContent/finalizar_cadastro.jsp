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
		
			function validar_login(){	 
				var email_aux = document.getElementById("email_login").value;
				var senha_aux = document.getElementById("senha_login").value;
				
		  		if ((email_aux == "")||(senha_aux == "")){
					alert("Informe o seu e-mail e senha corretamente.");
					return false;
			  	}else{
			  		return true;
			  	}				  	      			
			}
		
			function enviar_login(){
	  			if (validar_login() == true){												 					
					document.getElementById("frm_login").action = "${pageContext.request.contextPath}/login";			
					document.getElementById("frm_login").method = "post";	
					document.getElementById("frm_login").submit();					
				}
			}
			
		</script>
		
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" /><span class="style3 style6">C</span>adastro <span class="style3">E</span>fetuado com <span class="style3">s</span>ucesso!</div>
   		<div id="divTexto">
		<span class="style5">U</span>m e-mail foi enviado para você com os seus dados, senha e código
		sexual.<br />
		
		<c:if test='${promocao != 0}'> 
			Em seguida, entre no site e comece a marcar sexo!
			<br /><br />
		</c:if>

		
		<c:if test='${promocao == 0}'> 
			Agora, para liberar o seu acesso, você precisa realizar o pagamento no valor de <b>R$9,90</b>
			pelo site PagSeguro. <br />Quando o pagamento for confirmado, você receberá um e-mail
			e a sua conta será liberada.<br />
			Clique no botão abaixo e siga os passos para efetuar o pagamento.<br />
			Seu código sexual é <b>${membro.codigo_sexual}</b><br />
			<br /><br />			
				
			<fieldset><br />
				<b>PagSeguro</b>
				
				<br /><br />
				
				<!-- INICIO FORMULARIO BOTAO PAGSEGURO -->
				<form target="pagseguro"
				action="https://pagseguro.uol.com.br/security/webpagamentos/webpagto.aspx"
				method="post">
				<input type="hidden" name="email_cobranca"
				value="marquesexo@gmail.com" />
				<input type="hidden" name="tipo" value="CP" />
				<input type="hidden" name="moeda" value="BRL" />
				<input type="hidden" name="item_id_1" value="1" />
				<input type="hidden" name="item_descr_1" value="Pagamento do Plano MarqueSexo de 6 meses - Código ${membro.codigo_sexual}" />
				<input type="hidden" name="item_quant_1" value="1" />
				<input type="hidden" name="item_valor_1" value="990" />
				<input type="hidden" name="item_frete_1" value="000" />
				<input type="submit" value="Pague pelo PagSeguro"					
				name="submit" alt="Pague com PagSeguro - é rápido, grátis e seguro!" />
				</form>
				<!-- FINAL FORMULARIO BOTAO PAGSEGURO -->
				<br /><br />			
				<!-- INICIO CODIGO PAGSEGURO -->
				<img src="https://pagseguro.uol.com.br/Security/Imagens/btnPreferenciaCartoesBR.gif" border="0" />
				<!-- FINAL CODIGO PAGSEGURO -->	
	
				<br /><br />
					
			</fieldset>			
		</c:if>	
		
		</div>

		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
</html>
</jsp:root>