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
 			
			var id_aux = document.getElementById("id_motel").value;
			
	  		if ((id_aux == "")){
				alert("Informe o id do motel");
				return false;
		  	}else{
		  		return true;
		  	}
			  	      			
		}

		function editar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/editarmotel";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		function excluir(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/excluirmotel";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		function salvar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviareditarmotel";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
			
		</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >e</span>ditar Motel
	 	</div>

        <form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/editarmotel" method="post">
            <div class="FundoCadastro">
            
     			<p>
					<label for="id">Id:</label>
					<input name="id_motel" id="id_motel" value="${motel.id}" size="15" type="text" /> 
				</p>
				
				<p>
					<label for="nom">Nome:</label>
					<input name="nome_motel" id="nome_motel" value="${motel.nome}" size="35" type="text" /> 
				</p>
				
				<p>
					<label for="end">Endereço:</label>
					<input name="endereco_motel" id="endereco_motel" value="${motel.endereco}" size="35" type="text" /> 
				</p>
				
				<p>
					<label for="bai">Bairro:</label>
					<input name="bairro_motel" id="bairro_motel" value="${motel.bairro}" size="35" type="text" />  
				</p>
				
				<p>
					<label for="cid">Cidade:</label>
					<input name="cidade_motel" id="cidade_motel" value="${motel.cidade}" size="35" type="text" />  
				</p>
				
				<p>
					<label for="tel">Tel:</label>
					<input name="telefone_motel" id="telefone_motel" value="${motel.telefone}" size="20" type="text" /> 
				</p>
				
				<p>
					<label for="sit">Site:</label>
					<input name="site_motel" id="site_motel" value="${motel.site}" size="25" type="text" /> 
				</p>
				
				<p>
					<label for="mai">E-mail:</label>
					<input name="email_motel" id="email_motel" value="${motel.email}" size="35" type="text" />
				</p>
				
				<p>
					<label for="tex">Texto:</label>
					<input name="texto_motel" id="texto_motel" value="${motel.texto}" size="35" type="text" />  
				</p>
				
				<p>
					<label for="pri">Prioridade:</label>
					<input name="prioridade_motel" id="prioridade_motel" value="${motel.prioridade}" size="15" type="text" />
				</p>
				
				<div style="margin-left: 150px; margin-top:10px;">
					<div class="buttonwrapper">
				  		<a href="javascript:editar();" class="botao"><span>editar</span></a>
						<a href="javascript:salvar();" class="botao" style="margin-left: 6px"><span>salvar</span></a>
						<a href="javascript:excluir();" class="botao" style="margin-left: 6px"><span>excluir</span></a>
				  	</div>
				</div>
				
			</div>

		</form>
		

		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>