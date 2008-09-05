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
	 		<img src="img/setinha_vermelha.jpg" />
			<span class="style3" >p</span>agamento
	 	</div>
		<div id="divTexto">	


				<br />
				Se você ainda não efetuou o pagamento no valor de <b>R$9,90</b>,
				clique no botão <br />abaixo e siga os passos.<br />
				Quando seu pagamento for confirmado, você receberá um e-mail e <br />sua conta será liberada!
				<br /><br /><br />			
					
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

		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>