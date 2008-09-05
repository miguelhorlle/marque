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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
				DWRUtil.removeAllOptions("cidade");
      			DWRUtil.addOptions("cidade", lista);   
         	}else{
				var sel = document.getElementById("cidade");
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
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviarorganizarsuruba";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		
			
		</script>
		
	</head>
	<body>
	
	    <jsp:include page="header.jsp" flush="true" />
	    
	    <div class="TextoTitulo">
	    	<img src="img/setinha_vermelha.jpg" />
			<span class="style3" >o</span>rganizar Suruba
	 	</div>

			<form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/enviarorganizarsuruba" method="post">				    
		   		
		   		<div class="FundoCadastro">
		   		
					<p>
						<label for="di">Título:</label>
						<input name="titulo" id="titulo" value="" size="75" maxlength="75" type="text" />	
					</p>
					
					<p>
						<label for="di">Dia:</label>
						<select name="dia" id="dia" size="1">							
							<c:forEach begin="1" end="31" var="i" >
								<c:if test='${dia_atual == i}'>	
									<option value="${i}" selected="true">${i}</option>
								</c:if>
								<c:if test='${dia_atual != i}'>	
									<option value="${i}">${i}</option>
								</c:if>
							</c:forEach>						
						</select>
						&#160;&#160;
						Mês: 
						<select name="mes" id="mes" size="1">							
							<c:forEach begin="1" end="12" var="j" >
								<c:if test='${mes_atual == j}'>	
									<option value="${j}" selected="true">${j}</option>
								</c:if>
								<c:if test='${mes_atual != j}'>	
									<option value="${j}">${j}</option>
								</c:if>
							</c:forEach>						
						</select>
						&#160;&#160;
						Ano:
						<select name="ano" id="ano" size="1">
							<c:forEach begin="2008" end="2009" var="k" >
								<c:if test='${ano_atual == k}'>	
									<option value="${k}" selected="true">${k}</option>
								</c:if>
								<c:if test='${ano_atual != k}'>	
									<option value="${k}">${k}</option>
								</c:if>
							</c:forEach>	
						</select>
					</p>
					
					<p>
						<label for="hor">Horário:</label>
						<select name="hora" id="hora" size="1" value="">
							<option value="00">00</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
						</select>
						:
						&#160;<select name="minuto" id="minuto" size="1" value="">
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="45">45</option>					
						</select>
					</p>
					
					<p>
						<label for="est">Estado:</label>
						<select name="estado" id="estado" size="1" value="" onchange="selectCidades(this.value);">
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
						<label for="loc">Cidade:</label>
						<select name="cidade" id="cidade" size="1">
							<option value="" selected="true">Selecione o estado e aguarde</option>
						</select>
					</p>
					
					<p>
						<label for="loc">Local:</label>
						<input name="local" id="local" value="" size="80" maxlength="100" type="text" />	
					</p>
					
					<p>
						<label for="ob">Observação:</label>
						<input name="obs" id="obs" value="" size="50" maxlength="100" type="text" />
					</p>
					
					<br />
					<div style="margin-left: 140px; margin-top:10px;">
						<div class="buttonwrapper">
					  		<a href="javascript:enviar();" class="botao"><span>confirmar</span></a>
							<a href="javascript:voltar();" class="botao" style="margin-left: 6px"><span>cancelar</span></a>
					  	</div>						
					</div>
							  
		     		
				</div>
				
			</form>


			<br /><br />									


		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>