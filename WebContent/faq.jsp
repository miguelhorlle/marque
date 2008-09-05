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
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" /><span class="style3 style6">F</span>aq</div>
   		<div id="divTexto">

		<span class="LinkTextoHome"><strong>
		1 - As conversas que acontecem dentro do site são gravadas?
		</strong></span><br />
        <strong>Resposta:</strong> 
		Não, as conversas não são gravadas, assim garantimos a privacidade
		dos usuários!		
		<br /><br />
		
		<span class="LinkTextoHome"><strong>
		2 - Quando adiciono algúem na minha lista de Interessantes, está pessoa fica sabendo?
		</strong></span><br />
		<strong>Resposta:</strong> 
		Não, você pode incluir e excluir as pessoas da sua lista de Interessantes e ela
		nunca saberá disso!		
		<br /><br />

		<span class="LinkTextoHome"><strong>
		3 - E o que acontece quando eu bloqueio algúem?
		</strong></span><br />
        <strong>Resposta:</strong> 		
		Você simplesmente não existirá mais para a pessoa bloqueada, ou seja, ela não poderá ver
		seu perfil nem conversar e muito menos te mandar mensagens. Entretanto, ainda pode acontecer de 
		vocês se encontrarem na Sala Sexual.		
		<br /><br />
		
		<span class="LinkTextoHome"><strong>
		4 - Como eu faço para configurar minha webcam para ela aparecer na internet?
		</strong></span><br />
        <strong>Resposta:</strong> 		
        É simples configurar sua webcam! Nossa equipe preparou um pequeno tutorial
		que explica como confiugar. Clique <a href="${pageContext.request.contextPath}/como_configurar.jsp" target="_blank">aqui</a>  
		para saber!		
		<br /><br />
		
		<span class="LinkTextoHome"><strong>
		5 - Por que quando eu mudo de página e estou conversando com alguém, a janelinha de conversa some?
		</strong></span><br />
        <strong>Resposta:</strong> 	
        Isso acontece porque você provavelmente está com várias janelas do MarqueSexo aberta!
		É importante salientar que o site funciona corretamente somente se tiver uma janela do MarqueSexo aberta, 
		por isso, não abra os links do site em outras abas ou janelas!		
		<br /><br />
		
		<span class="LinkTextoHome"><strong>
		6 - Como funciona o "Já Peguei!" ?
		</strong></span><br />
        <strong>Resposta:</strong> 	
        O Já Peguei! é uma lista de todas as pessoas do site que você já pegou.
		No perfil de cada usuário tem o botão do Já Peguei!. Você confirma que você realmente pegou aquela
		pessoa e nós mandamos um e-mail pra ela contando. Se ela confirmar que você a pegou mesmo, ela 
		aparece na sua lista Já Peguei! e você aparece na lista dela! Legal né!
		
					
		<br /><br /><br />

		
		<b>Envie também sua pergunta pelo 'Fale Conosco'!</b>
		<br /><br />
		
				
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