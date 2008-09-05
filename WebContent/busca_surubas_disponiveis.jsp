<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" version="2.0">
<jsp:directive.page language="java"	contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" />
<jsp:directive.page isELIgnored="false" />
<jsp:text><![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]></jsp:text>
<jsp:text><![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]></jsp:text>
<jsp:text><![CDATA[ <script type="text/javascript" src="/marquesexo/dwr/interface/CidadesDAO.js"></script> ]]></jsp:text>
<jsp:text><![CDATA[ <script type="text/javascript" src="/marquesexo/dwr/engine.js"></script> ]]></jsp:text>
<jsp:text><![CDATA[ <script type="text/javascript" src="/marquesexo/dwr/util.js"></script> ]]></jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
    	<meta http-equiv="content-language" content="cs" />    	
   		<link rel="stylesheet" href="marque.css" type="text/css" />
   		<link rel="stylesheet" href="menu.css" type="text/css" />
   		<title>MarqueSexo</title>	

		<script language="javascript">
		
		function selectCidades(valor){
      		CidadesDAO.getCidades(valor, carregaCidades);

		}		

		function carregaCidades(lista){
			
			if(window.activexobject == true){
				DWRUtil.removeAllOptions("cidade_busca");
      			DWRUtil.addOptions("cidade_busca", lista);   
         	}else{
				var sel = document.getElementById("cidade_busca");
				for (i =  sel.length; i &#062; 0; i--) {
		            //Remove os itens ao select
		            //sel.options.remove(i);
		            sel.options[i] = null;
		        }
		        
		        sel.options[0] = new Option("Tanto Faz", "");
	            for (i = 0; i &#060; lista.length; i++) {
		            //Adiciona os itens ao select
		            sel.options[i+1] = new Option(lista[i], lista[i]);
		        }		        		        
         	}
		}
	
		function validar(){
		
			var estado_aux = document.getElementById("estado_busca").value;
			var resposta = true;
			
			if ((estado_aux == "")){
				alert("Informe o estado.");
				resposta = false;
		  	}			
			return resposta;      			
		}
		
		function voltar(){   													 					
			history.go(-1);
		}

		
		function enviar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/resultadobuscasurubasdisponiveis";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}		
			
		</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />

			<div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >b</span>uscar Surubas Disponíveis
	 		</div>
			<div class="FundoCadastro">

				<form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/resultadobuscasurubasdisponiveis" method="post">
					<br />
					<p>
						<label for="est">Estado:</label>						
						<select name="estado_busca" id="estado_busca" size="1" value="" onchange="selectCidades(this.value);">
							<option value="">--</option>
							<option value="ac">AC</option>
							<option value="al">AL</option>
							<option value="am">AM</option>
							<option value="ap">AP</option>
							<option value="ba">BA</option>
							<option value="ce">CE</option>
							<option value="df">DF</option>
							<option value="es">ES</option>
							<option value="go">GO</option>
							<option value="ma">MA</option>
							<option value="mg">MG</option>
							<option value="ms">MS</option>
							<option value="mt">MT</option>
							<option value="pa">PA</option>
							<option value="pb">PB</option>
							<option value="pe">PE</option>
							<option value="pi">PI</option>
							<option value="pr">PR</option>
							<option value="rj">RJ</option>
							<option value="rn">RN</option>
							<option value="ro">RO</option>
							<option value="rr">RR</option>
							<option value="rs">RS</option>
							<option value="sc">SC</option>
							<option value="se">SE</option>
							<option value="sp">SP</option>
							<option value="to">TO</option>
						</select>
					</p>
					
					<p>
						<label for="cid">Cidade:</label>
						<select name="cidade_busca" id="cidade_busca" size="1">
							<option value="" selected="true">Selecione o estado e aguarde</option>
						</select>
						
					</p>
					<br />
					
					<div style="margin-left: 140px; margin-top:10px;">
						<div class="buttonwrapper">
					  		<a href="javascript:enviar();" class="botao"><span>Buscar</span></a>
							<a href="${pageContext.request.contextPath}/surubas.jsp" class="botao" style="margin-left: 6px"><span>Voltar</span></a>
					  	</div>							  				
					</div>
					<br /><br /><br />

																	
				</form>
		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>