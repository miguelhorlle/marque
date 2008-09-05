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
	 		<span class="style3 style6">p</span>razer por prazer
	 	</div>
   		<div id="divTexto">
	 	
	 	<c:if test='${permissao != null}'> 

			<c:if test='${situacao == 1}'> 		
				<span class="style5">&quot;L</span>evante-se
				 e tire suas roupas! - ela disse.
				 <br /><br />
				Não pensei duas vezes, ou melhor, simplesmente não pensei.Tirei  peça por peça
				deixando-me embriagar pelo olhar sedento dela. As suas ordens juntavam-se
				as minhas fantasias mais sórdidas de ser dominado. Eu aceitava 
				e, aceitando, ela se excitava.
				<br /><br />
				As vestes estavam jogadas. Meu sexo rígido apontando para aquela materialização
				de paraíso. E ela encarava cada 
				detalhe do meu corpo como se eu fosse o mapa de um novo mundo. Era assim, lindo
				pela observação e seco de toque.
				<br /><br />
				 Eu queria me mexer, mas não podia, porque ela não havia ordenado. Tentei ver
				 por detrás da lingerie, apesar de me contentar 
				facilmente com a arte final de seu corpo emoldurado naquelas vestes. 
				<br /><br />
				- Faça o que quiser de mim! - Ela disse logo após deslizar vagarosamente a língua
				por meu ouvido esquerdo. 
				<br /><br />
				Fiquei numa espécie de transe. Eu podia ver o desenho exato de suas curvas em
				luz tênue. Ela estava molhada de vontade; 
				e eu, duro de apetite. Abracei-a conforme mandam as regras da sedução e, penetrei-a
				de pé, sem invadir um centímetro sequer 
				do teu sexo. Como eu fiz isso? Comendo o êxtase e deixando-a provar do meu.
				<br /><br />
				Ficamos parados, roubando calor e aquecendo um ao outro. 
				Vilipendiamos o orgasmo em forma e mimetizamos as sensações do prazer real.
				A luz tênue se apagou e sobramos, imersos na escuridão deliciosa de nossos corpos&quot;

				<br /><br /><br />

				<c:if test='${nro_online != 0}'>
					<br />
					<span class="LinkTextoHome"><strong>Há mais ${nro_online} pessoa(s) online</strong></span><br />
					<!-- ${chatters} 
					<c:if test='${nro_online == 1}'>
						está online!<br /><br />
					</c:if>
					<c:if test='${nro_online != 1}'>
						estão online!<br /><br />
					</c:if>
					 -->
				</c:if>				
							
					
			</c:if>
			
			<c:if test='${situacao == 0}'> 		
				O seu pagamento ainda não foi confirmado!<br />
				Portanto, as funcionalidades do site não estão liberadas para você!<br />
				Caso você ainda não tenha efetuado o pagamento, acesse a página de "Pagamentos"<br />
				e siga os passos.<br />
				Quando confirmarmos seu pagamento, você receberá um e-mail avisando!<br />
				Só lembrando, seu código sexual é o ${codigo_sexual}.
			
			</c:if>
			
			<c:if test='${situacao == 2}'> 		
				O seu pagamento ainda não foi confirmado!<br />
				Portanto, você não terá acesso à "Sala Sexual" e só poderá conversar com 1 pessoa por vez.<br />
				Caso você ainda não tenha efetuado o pagamento, acesse a página de "Pagamentos"<br />
				e siga os passos.<br />
				Quando confirmarmos seu pagamento, você receberá um e-mail avisando!<br />
				Só lembrando, seu código sexual é o ${codigo_sexual}.
				
				<br /><br /><br />
				<c:if test='${nro_online != 0}'>
					<br />
					<span class="LinkTextoHome"><strong>Há mais ${nro_online} pessoa(s) online</strong></span><br />
					<!-- ${chatters} 
					<c:if test='${nro_online == 1}'>
						está online!<br /><br />
					</c:if>
					<c:if test='${nro_online != 1}'>
						estão online!<br /><br />
					</c:if>
					 -->
				</c:if>				
							
			</c:if>
		
		</c:if>
		
		<c:if test='${permissao == null}'> 
			<h3>Erro!</h3>
				<br />
				Você precisa estar logado para acessar esta página.<br />
				
		</c:if>
		
		</div>
		<br />
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<c:if test='${permissao != null}'> 
		<c:if test='${situacao != 0}'> 
			<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
		</c:if>
	</c:if>
</html>
</jsp:root>