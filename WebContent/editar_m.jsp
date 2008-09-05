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
 			
			var codigo_sexual_editar_aux = document.getElementById("codigo_sexual_editar").value;
			
	  		if ((codigo_sexual_editar_aux == "")){
				alert("Informe o codigo sexual");
				return false;
		  	}else{
		  		return true;
		  	}
			  	      			
		}

		function editar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/editarm";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		function excluir(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/excluirm";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		function salvar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviareditarm";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
			
		</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
	 	<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >e</span>ditar Membro
	 	</div>

	    <form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/editarm" method="post">
	     	<div class="FundoCadastro">
	     	
				<p>
					<label for="cod">Código Sexual:</label>
					<input name="codigo_sexual_editar" id="codigo_sexual_editar" value="${membro.codigo_sexual}" size="15" type="text" /> 
				</p>
					
				<p>
					<label for="nome">Nome:</label>
					<input name="nome_editar" id="nome_editar" value="${membro.nome}" size="35" type="text" /> 
				</p>
					
				<p>
					<label for="dt">Data: ${membro.data}</label>
				</p>
				
				<p>
					<label for="email">E-mail: ${membro.email}</label>
				</p>
				
				<p>
					<label for="ape">Apelido: ${membro.senha}</label>
				</p>
				
				<p>
					<label for="sit">Situacao:</label>			
					<input name="situacao_editar" id="situacao_editar" value="${membro.situacao}" size="20" type="text" /> 
				</p>
				<br />

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