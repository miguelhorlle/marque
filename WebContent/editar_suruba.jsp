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
		
		function selectCidades(valor){
      		CidadesDAO.getCidades(valor, carregaCidades);

		}

		function carregaCidades(lista){
			
			if(window.activexobject == true){
				DWRUtil.removeAllOptions("nova_cidade");
      			DWRUtil.addOptions("nova_cidade", lista);   
         	}else{
				var sel = document.getElementById("nova_cidade");
				for (i =  sel.length; i &#062; 0; i--) {
		            //Remove os itens ao select
		            //sel.options.remove(i);
		            sel.options[i] = null;
		        }

	            for (i = 0; i &#060; lista.length; i++) {
		            //Adiciona os itens ao select
		            sel.options[i] = new Option(lista[i], lista[i]);
		        }		        		        
         	}			
		}
	
		function validar(){
		
			var estado_aux = document.getElementById("estado").value;
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
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviareditarsuruba";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		
		<jsp:text><![CDATA[ 
		function alterarCidade(){

			var x = document.getElementById("alterar");
			x.innerHTML  = ("<br />Estado: <select name='novo_estado' id='novo_estado' size='1' onchange='selectCidades(this.value);' > "
											+ "<option value=''>--</option>"
											+ "<option value='ac'>AC</option>"
											+ "<option value='al'>AL</option>"
											+ "<option value='am'>AM</option>"
											+ "<option value='ap'>AP</option>"
											+ "<option value='ba'>BA</option>"
											+ "<option value='ce'>CE</option>"
											+ "<option value='df'>DF</option>"
											+ "<option value='es'>ES</option>"
											+ "<option value='go'>GO</option>"
											+ "<option value='ma'>MA</option>"
											+ "<option value='mg'>MG</option>"
											+ "<option value='ms'>MS</option>"
											+ "<option value='mt'>MT</option>"
											+ "<option value='pa'>PA</option>"
											+ "<option value='pb'>PB</option>"
											+ "<option value='pe'>PE</option>"
											+ "<option value='pi'>PI</option>"
											+ "<option value='pr'>PR</option>"
											+ "<option value='rj'>RJ</option>"
											+ "<option value='rn'>RN</option>"
											+ "<option value='ro'>RO</option>"
											+ "<option value='rr'>RR</option>"
											+ "<option value='rs'>RS</option>"
											+ "<option value='sc'>SC</option>"
											+ "<option value='se'>SE</option>"
											+ "<option value='sp'>SP</option>"
											+ "<option value='to'>TO</option>"
										+ "</select>&#160;&#160;"
										+ "Cidade: "
										+ "<select name='nova_cidade' id='nova_cidade'>"
											+ "<option value=''>Selecione o estado e aguarde</option>"										
										+ "</select>");
			
		
		}
		
		]]>
		</jsp:text>
		
		
			
		</script>
		
	</head>
	<body>
	
	<c:set var="fmtDia"><fmt:formatDate value='${suruba.data}' type="date" pattern="dd"/></c:set>
	<c:set var="fmtMes"><fmt:formatDate value='${suruba.data}' type="date" pattern="MM"/></c:set>
	<c:set var="fmtAno"><fmt:formatDate value='${suruba.data}' type="date" pattern="yyyy"/></c:set>
	
	    <jsp:include page="header.jsp" flush="true" />
	    
	    <div class="TextoTitulo"><img src="img/setinha_vermelha.jpg" />
			<span class="style3" >e</span>ditar Suruba
	 		</div>
			<div id="divTexto">	

		
	   		<br /><br />							
	
	
			<fieldset style="border: 1px solid; border-color: #FFFFFF;width:95%;margin-left:10px;">		
			
				<form name="frm" id="frm" action="${pageContext.request.contextPath}/enviareditarsuruba" method="post">				    
		   
					<br />
					&#160;Título: <input name="titulo" id="titulo" value="${suruba.titulo}" size="50" maxlength="50" type="text" />	
					
					<br /><br />
					&#160;
					Dia: 
					<select name="dia" id="dia" size="1">							
						<c:forEach begin="1" end="31" var="i" >
							<c:if test='${fmtDia == i}'>	
								<option value="${i}" selected="true">${i}</option>
							</c:if>
							<c:if test='${fmtDia != i}'>	
								<option value="${i}">${i}</option>
							</c:if>
						</c:forEach>						
					</select>
					&#160;&#160;
					Mês: 
					<select name="mes" id="mes" size="1">							
						<c:forEach begin="1" end="12" var="j" >
							<c:if test='${fmtMes == j}'>	
								<option value="${j}" selected="true">${j}</option>
							</c:if>
							<c:if test='${fmtMes != j}'>	
								<option value="${j}">${j}</option>
							</c:if>
						</c:forEach>						
					</select>
					&#160;&#160;
					Ano:
					<select name="ano" id="ano" size="1">
						<c:forEach begin="2008" end="2009" var="k" >
							<c:if test='${fmtAno == k}'>	
								<option value="${k}" selected="true">${k}</option>
							</c:if>
							<c:if test='${fmtAno != k}'>	
								<option value="${k}">${k}</option>
							</c:if>
						</c:forEach>	
					</select>
					
					<br /><br />
					&#160;Horário: &#160;<select name="hora" id="hora" size="1">
						<c:forEach begin="0" end="23" var="j" >
							<c:if test="${suruba.hora == j}">							
								<option	value="${j}" selected="true">
									<c:if test="${j &#060; 10}">	
										0${j}
									</c:if>
									<c:if test="${j &#062; 10}">	
										${j}
									</c:if>
								</option>							
							</c:if>
							<c:if test="${suruba.hora != j}">	
								<option	value="${j}">
									<c:if test="${j &#060; 10}">	
										0${j}
									</c:if>
									<c:if test="${j &#062; 10}">	
										${j}
									</c:if>
								</option>
							</c:if>
						</c:forEach>
					</select>
					:
					&#160;<select name="minuto" id="minuto" size="1">
						<c:if test="${suruba.minuto == 00}">	
							<option value="00" selected="true">00</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="45">45</option>
						</c:if>
						<c:if test="${suruba.minuto == 15}">	
							<option value="00">00</option>
							<option value="15" selected="true">15</option>
							<option value="30">30</option>
							<option value="45">45</option>
						</c:if>
						<c:if test="${suruba.minuto == 30}">	
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="30" selected="true">30</option>
							<option value="45">45</option>
						</c:if>
						<c:if test="${suruba.minuto == 45}">	
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="45" selected="true">45</option>
						</c:if>
					
					</select>
					
					<br /><br />
					&#160;Estado:<input name="estado" id="estado" value="${suruba.estado}" size="5" type="text" />
					&#160;&#160;
					Cidade:<input name="cidade" id="cidade" value="${suruba.cidade}" size="35" type="text" />
					<br /><br />
					&#160;<input id="alterarButton" type="button" value="Alterar Cidade" onclick="javascript:alterarCidade();" />
		   			<div id="alterar" ><jsp:text><![CDATA[  ]]></jsp:text></div>
					
					<br /><br />
					&#160;Local: <input name="local" id="local" value="${suruba.local}" size="50" maxlength="50" type="text" />	
					
					<br /><br />
					&#160;Observação: <input name="obs" id="obs" value="${suruba.obs}" size="50" maxlength="50" type="text" />
						
					<input type="hidden" value="${suruba.id}" name="id_suruba" id="id_suruba" />
					
					<br /><br /><br />
	
					&#160;<input name="bconfirmar" value="Confirmar" type="button" onclick="javascript:enviar();" />
					&#160;&#160;&#160;
					&#160;<input name="bcancelar" value="Cancelar" type="button" onclick="javascript:voltar();" />
							  
		     		
		
				</form>
			
	     	</fieldset>
			
			<br /><br /><br />
			<input name="bvoltar" value="Voltar" type="button" onclick="javascript:history.go(-1);" />
		
		</div>
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>