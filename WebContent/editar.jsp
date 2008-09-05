<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt" version="2.0">
<jsp:directive.page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" />
<jsp:directive.page isELIgnored="false" />
<jsp:text><![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]></jsp:text>
<jsp:text><![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]></jsp:text>
<jsp:text><![CDATA[ <script type="text/javascript" src="/marquesexo/dwr/interface/CidadesDAO.js"></script> ]]></jsp:text>
<jsp:text><![CDATA[ <script type="text/javascript" src="/marquesexo/dwr/engine.js"></script> ]]></jsp:text>
<jsp:text><![CDATA[ <script type="text/javascript" src="/marquesexo/dwr/util.js"></script> ]]></jsp:text>

<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.ArrayList" />

<jsp:scriptlet>
	List listaFisico = new ArrayList();   
	listaFisico.add("Magro");
	listaFisico.add("Médio");
	listaFisico.add("Em forma");
	listaFisico.add("Pouco acima do peso");
	listaFisico.add("Muito acima do peso");
	listaFisico.add("Musculoso");
	listaFisico.add("Pequeno");
	
	String[] listaFaz = new String[7];   
	listaFaz[0] = ("Mulher");
	listaFaz[1] = ("Homem");
	listaFaz[2] = ("Transexual/Travesti");
	listaFaz[3] = ("Homem e Mulher");
	listaFaz[4] = ("Homem e Transexual");
	listaFaz[5] = ("Mulher e Transexual");
	listaFaz[6] = ("Todos");
	
	List listaPele = new ArrayList();   
	listaPele.add("Branco");
	listaPele.add("Asiático Japonês");
	listaPele.add("Pardo/Mulato");
	listaPele.add("Preto/Africano");
	listaPele.add("Asiático Chinês");
	listaPele.add("Asiático Coreano");
	listaPele.add("Indiano");
	listaPele.add("Latino/Hispânico");
	listaPele.add("Oriente Médio");
	listaPele.add("Outros");
	
	List listaCivil = new ArrayList();   
	listaCivil.add("Solteiro(a)");
	listaCivil.add("Casado(a)");
	listaCivil.add("Divorciado(a)");
	listaCivil.add("Viúvo(a)");

	
	
	List listaAltura = new ArrayList();  
	listaAltura.add(new Float(1.4));
	listaAltura.add(new Float(1.41));
	listaAltura.add(new Float(1.42));
	listaAltura.add(new Float(1.43));
	listaAltura.add(new Float(1.44));
	listaAltura.add(new Float(1.45));
	listaAltura.add(new Float(1.46));
	listaAltura.add(new Float(1.47));
	listaAltura.add(new Float(1.48));
	listaAltura.add(new Float(1.49));
	listaAltura.add(new Float(1.5));
	listaAltura.add(new Float(1.51));
	listaAltura.add(new Float(1.52));
	listaAltura.add(new Float(1.53));
	listaAltura.add(new Float(1.54));
	listaAltura.add(new Float(1.55));
	listaAltura.add(new Float(1.56));
	listaAltura.add(new Float(1.57));
	listaAltura.add(new Float(1.58));
	listaAltura.add(new Float(1.59));
	listaAltura.add(new Float(1.6));
	listaAltura.add(new Float(1.61));
	listaAltura.add(new Float(1.62));
	listaAltura.add(new Float(1.63));
	listaAltura.add(new Float(1.64));
	listaAltura.add(new Float(1.65));
	listaAltura.add(new Float(1.66));
	listaAltura.add(new Float(1.67));
	listaAltura.add(new Float(1.68));
	listaAltura.add(new Float(1.69));
	listaAltura.add(new Float(1.7));
	listaAltura.add(new Float(1.71));
	listaAltura.add(new Float(1.72));
	listaAltura.add(new Float(1.73));
	listaAltura.add(new Float(1.74));
	listaAltura.add(new Float(1.75));
	listaAltura.add(new Float(1.76));
	listaAltura.add(new Float(1.77));
	listaAltura.add(new Float(1.78));
	listaAltura.add(new Float(1.79));
	listaAltura.add(new Float(1.8));
	listaAltura.add(new Float(1.81));
	listaAltura.add(new Float(1.82));
	listaAltura.add(new Float(1.83));
	listaAltura.add(new Float(1.84));
	listaAltura.add(new Float(1.85));
	listaAltura.add(new Float(1.86));
	listaAltura.add(new Float(1.87));
	listaAltura.add(new Float(1.88));
	listaAltura.add(new Float(1.89));
	listaAltura.add(new Float(1.9));
	listaAltura.add(new Float(1.91));
	listaAltura.add(new Float(1.92));
	listaAltura.add(new Float(1.93));
	listaAltura.add(new Float(1.94));
	listaAltura.add(new Float(1.95));
	listaAltura.add(new Float(1.96));
	listaAltura.add(new Float(1.97));
	listaAltura.add(new Float(1.98));
	listaAltura.add(new Float(1.99));
	listaAltura.add(new Float(2.0));
	listaAltura.add(new Float(2.01));
	listaAltura.add(new Float(2.02));
	listaAltura.add(new Float(2.03));
	listaAltura.add(new Float(2.04));
	listaAltura.add(new Float(2.05));
	listaAltura.add(new Float(2.06));
	listaAltura.add(new Float(2.07));
	listaAltura.add(new Float(2.08));
	listaAltura.add(new Float(2.09));
	listaAltura.add(new Float(2.1));
	listaAltura.add(new Float(2.11));
	listaAltura.add(new Float(2.12));
	listaAltura.add(new Float(2.13));
	listaAltura.add(new Float(2.14));
	listaAltura.add(new Float(2.15));
	listaAltura.add(new Float(2.16));
	listaAltura.add(new Float(2.17));
	listaAltura.add(new Float(2.18));
	listaAltura.add(new Float(2.19));
	listaAltura.add(new Float(2.2));
	listaAltura.add(new Float(2.21));
	listaAltura.add(new Float(2.22));
	listaAltura.add(new Float(2.23));
	listaAltura.add(new Float(2.24));
	listaAltura.add(new Float(2.25));
	listaAltura.add(new Float(2.26));
	listaAltura.add(new Float(2.27));
	listaAltura.add(new Float(2.28));
	listaAltura.add(new Float(2.29));
	
	pageContext.setAttribute("listaFaz",listaFaz);
	pageContext.setAttribute("listaPele",listaPele);
	pageContext.setAttribute("listaAltura",listaAltura);
	pageContext.setAttribute("listaFisico",listaFisico);
	pageContext.setAttribute("listaCivil",listaCivil);
	
	//http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/Servlet-Tutorial-JSP.html
</jsp:scriptlet>
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
 			
			var senha_aux = document.getElementById("senha").value;
			var nome_aux = document.getElementById("apelido").value;
			
			var obj1 = null;
			obj1 = document.getElementById("novo_estado");

			var estado_aux = "${membro.estado}";
			
			if(obj1 != null)
				estado_aux = obj1.value;				
			
	  		if ((nome_aux == "")||(senha_aux == "")||(estado_aux == "")){
				alert("Informe a sua senha, apelido e cidade e estado onde você mora.");
				return false;
		  	}else{
		  		return true;
		  	}
			  	      			
		}

		
		function salvar(){
			
  			if (validar() == true){											 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/enviareditar";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
			
		}

		<jsp:text><![CDATA[ 
		function alterarCidade(){

			var x = document.getElementById("alterar");
			x.innerHTML  = ("<br /><p><label for='novo_est'>Estado:</label><select id='novo_estado' name='novo_estado' size='1' onchange='selectCidades(this.value);' > "
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
										+ "</select></p>"
										+ "<p><label for='nova_cidade'>Cidade:</label>"
										+ "<select id='nova_cidade' name='nova_cidade'>"
											+ "<option value=''>Selecione o estado e aguarde</option>"										
										+ "</select></p><br />");
			
		
		}
		
		]]>
		</jsp:text>

			
		</script>
	</head>
	<body>
	
	 	<jsp:include page="header.jsp" flush="true" />
	 	
 			<div class="TextoTitulo">
	 			<img src="img/setinha_vermelha.jpg" /><span class="style3">m</span>eu <span class="style3">p</span>erfil&#160;
		   		<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">e</span><span class="style6">ditar Dados
		        </span>
 			
 			</div>

            <form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/enviareditar" method="post">
	             
	        	<div id="geral" class="FundoCadastro">
	             
	             	<p>
						<label for="nome">Código Sexual:</label>
						<b>${membro.codigo_sexual}</b>
					</p>
					
					<p>
						<label for="email">E-mail:</label>
						${membro.email}
					</p>
					
					<p>
						<label for="sen">Senha:</label>
						<input id="senha" name="senha" value="${membro.senha}" size="35" type="password" />
					</p>
	             
		            <p>
						<label for="nom">*Nome:</label>
						<input id="nome" name="nome" value="${membro.nome}" size="55" type="text" />
					</p>
	             
	             	<p>
	             		<span class="MsgCadastro2">
	             		* Seu nome não será fornecido para os outros membros
						</span>
					</p>
					
					<p>
						<label for="apel">Apelido:</label>
						<input id="apelido" name="apelido" value="${membro.apelido}" size="35" type="text" />
					</p>
					
					<p>
						<label for="sexo">Sexo:</label>
						<select id="sexo" name="sexo" value="${membro.sexo}" size="1">
							<c:if test="${membro.sexo == 0}">	
								<option value="0" selected="true">Masculino</option>
								<option value="1">Feminino</option>
								<option value="2">Transexual/Travesti</option>
							</c:if>
							<c:if test="${membro.sexo == 1}">	
								<option value="0">Masculino</option>
								<option value="1" selected="true">Feminino</option>
								<option value="2">Transexual/Travesti</option>
							</c:if>
							<c:if test="${membro.sexo == 2}">	
								<option value="0">Masculino</option>
								<option value="1">Feminino</option>
								<option value="2" selected="true">Transexual/Travesti</option>
							</c:if>
						</select>
					</p>
					
					<p>
						<label for="idade">Idade:</label>
						<select id="idade" name="idade" size="1">
							<c:forEach begin="18" end="80" var="j" >
								<c:if test="${membro.idade == j}">	
									<option	value="${j}" selected="true">${j}</option>
								</c:if>
								<c:if test="${membro.idade != j}">	
									<option value="${j}">${j}</option>
								</c:if>
							</c:forEach>						
						</select>
					</p>
					
					<p>
						<label for="civil">Estado Civil:</label>
						<select id="estado_civil" name="estado_civil" size="1">
							<c:forEach begin="0" end="3" var="k" >
								<c:if test="${membro.estado_civil == k}">	
									<option	value="${k}" selected="true">${listaCivil[k]}</option>
								</c:if>
								<c:if test="${membro.estado_civil != k}">	
									<option value="${k}">${listaCivil[k]}</option>
								</c:if>
							</c:forEach>
						</select>
					</p>
					
					<p>
						<label for="estado">Estado:</label>
						${membro.estado}
					</p>
					
					<p>
						<label for="estado">Cidade:</label>
						${membro.cidade}
					</p>
					<br />	
					<p>
					    <a href="javascript:alterarCidade();" class="botao"><span>alterar cidade</span></a>
					    <br />
	      				<div id="alterar"><jsp:text><![CDATA[  ]]></jsp:text></div>		    
					</p>
					
					<p>
						 <br />
						<label for="aparencia">Aparência do site:</label>
						<select id="discreto" name="discreto" size="1" >
							<c:if test="${membro.discreto == 0}">	
								<option value="0" selected="true">Normal</option>
								<option value="1">Discreta</option>
							</c:if>
							<c:if test="${membro.discreto == 1}">	
								<option value="0">Normal</option>
								<option value="1" selected="true">Discreta</option>
							</c:if>							
						</select>
					</p>
					
					<p>
	             		<span class="MsgCadastro2">
	             		* Na aparência discreta, o logo do site não é exibido
						</span>
					</p>
					
					<p>
						<label for="sobre">Sobre você:</label>
						<input id="texto" name="texto" value="${membro.texto}" size="55" maxlength="55" type="text" />
					</p>
					
					<p>
	             		<span class="MsgCadastro2">
	             		* Escreva algo sobre você para que todos vejam
						</span>
					</p>
					
					<p>
						<label for="peso">Peso:</label>
						<select id="peso" name="peso" size="1">							
							<c:forEach begin="40" end="150" var="j" >
								<c:if test="${membro.peso == j}">	
									<option	value="${j}" selected="true">${j}</option>
								</c:if>
								<c:if test="${membro.peso != j}">	
									<option value="${j}">${j}</option>
								</c:if>
							</c:forEach>						
						</select>&#160;kg.
					</p>
					

					<p>
						<label for="altura">Altura:</label>
						<select id="altura" name="altura" size="1">
							<c:forEach items="${listaAltura}" var="i">   							
								<c:if test="${membro.altura == i}">	
									<option value="${i}" selected="true">${i}</option>
								</c:if>
								<c:if test="${membro.altura != i}">	
									<option value="${i}">${i}</option>
								</c:if>
							</c:forEach>
						</select>&#160;m.
					</p>
					
					<p>
						<label for="fisico">Tipo Físico:</label>
						<select id="tipo_fisico" name="tipo_fisico" size="1">
							<c:forEach begin="0" end="6" var="j" >
								<c:if test="${membro.tipo_fisico == j}">	
									<option	value="${j}" selected="true">${listaFisico[j]}</option>
								</c:if>
								<c:if test="${membro.tipo_fisico != j}">	
									<option value="${j}">${listaFisico[j]}</option>
								</c:if>
							</c:forEach>							
						</select>
					</p>
					
					<p>
						<label for="pele">Tom de pele:</label>
						<select id="tom_pele" name="tom_pele" size="1">
							<c:forEach begin="0" end="9" var="j" >
								<c:if test="${membro.tom_pele == j}">	
									<option	value="${j}" selected="true">${listaPele[j]}</option>
								</c:if>
								<c:if test="${membro.tom_pele != j}">	
									<option value="${j}">${listaPele[j]}</option>
								</c:if>
							</c:forEach>
						</select>		
					</p>
					
					<p>
						<label for="pele">Faz sexo com:</label>
						<select id="faz_sexo_com" name="faz_sexo_com" size="1">							
							<c:forEach begin="0" end="6" var="k" >
								<c:if test="${membro.faz_sexo_com == k}">	
									<option	value="${k}" selected="true">${listaFaz[k]}</option>
								</c:if>
								<c:if test="${membro.faz_sexo_com != k}">	
									<option value="${k}">${listaFaz[k]}</option>
								</c:if>
							</c:forEach>
						</select>
					</p>
					<br />
					<div style="margin-left: 150px; margin-top:10px;">
						<div class="buttonwrapper">
					  	<a href="javascript:salvar();" class="botao"><span>enviar</span></a>
						<a href="${pageContext.request.contextPath}/home" class="botao" target="_self" style="margin-left: 6px"><span>cancelar</span></a>
					  	</div>
					</div>


				<br /><br />
				<span class="classh2">Se você deseja cancelar sua conta no site, clique <a href="${pageContext.request.contextPath}/cancelar_conta.jsp">aqui</a></span>

			</div>
		</form>

				
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<c:if test='${situacao != 0}'> 
		<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
	</c:if>

</html>
</jsp:root>
