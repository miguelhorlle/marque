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
				
			var codigo_aux = document.getElementById("codigo_sexual_busca").value;
			var estado_aux = document.getElementById("estado_busca").value;
			var resposta = true;
			
			if (codigo_aux == ""){
				if ((estado_aux == "")){
					alert("Informe pelo menos o estado ou o código sexual.");
					resposta = false;
			  	}
		  	}else{
		  		if(isNaN(codigo_aux )==false){
			  		if(codigo_aux &#060; 0){
			  			alert("O código informado não é válido!");
			  			resposta = false;
			  		}
			  	}else{
			  		alert("O código informado não é válido!");
			  		resposta = false;
			  	}
			}			
			return resposta;  	
		}
		
		function voltar(){   													 					
				history.go(-1);
		}

		function enviar(){
  			if (validar() == true){												 					
				document.getElementById("frm").action = "${pageContext.request.contextPath}/resultadobusca";			
				document.getElementById("frm").method = "post";	
				document.getElementById("frm").submit();					
			}
		}
		
		function exibirdetalhada(){   		
		
			document.getElementById("codigo_sexual_busca").value = "";
													 					
			bus = document.getElementById("busca");
		    var sty = bus.style;
		    sty.display = "none";
		    
		    cod = document.getElementById("codigo");
		    var s = cod.style;
		    s.display = "none";
		    
		    det = document.getElementById("detalhada");
		    var st = det.style;
		    st.display = "block";
		}
		
		function exibircodigo(){   													 					
			bus = document.getElementById("busca");
		    var sty = bus.style;
		    sty.display = "none";
		    
		    cod = document.getElementById("codigo");
		    var s = cod.style;
		    s.display = "block";
		    
		    det = document.getElementById("detalhada");
		    var st = det.style;
		    st.display = "none";
		}
		
		function exibirbusca(){   													 					
			bus = document.getElementById("busca");
		    var sty = bus.style;
		    sty.display = "block";
		    
		    cod = document.getElementById("codigo");
		    var s = cod.style;
		    s.display = "none";
		    
		    det = document.getElementById("detalhada");
		    var st = det.style;
		    st.display = "none";
		}

			
		</script>
	</head>
	<body onload="exibirbusca();">
	
	 	<jsp:include page="header.jsp" flush="true" />

			<div class="TextoTitulo">			
				<img src="img/setinha_vermelha.jpg" /><span class="style3">o</span>utros <span class="style3">m</span>embros&#160;
	   			<img src="img/setinha_vermelha.jpg" alt="setinha" width="17" height="18" /><span class="style3 style6 style5 style6 style6">b</span><span class="style6">uscar membros
	        	</span>
        	</div>
			
				<form name="frm" id="frm" class="cssform" action="${pageContext.request.contextPath}/resultadobusca" method="post">
			
					<div id="busca" class="FundoCadastro" style="display: none;">
						
						<br />
						<a href="javascript:exibirdetalhada();" class="botao" style="margin-left: 15px"><span>Busca Detalhada</span></a>
						<br /><br />
						<a href="javascript:exibircodigo();" class="botao" style="margin-left: 15px"><span>Busca pelo Código Sexual</span></a>
						<br /><br /><br /><br /><br />
					</div>
				
					<div id="codigo" class="FundoCadastro" style="display: none;">
						
						<br />	
						<p>
							<label for="cod">Código Sexual:</label>
							<input name="codigo_sexual_busca" id="codigo_sexual_busca" value="" size="10" type="text" /> 
						</p>
						<br />
												
						<div style="margin-left: 140px; margin-top:10px;">
							<div class="buttonwrapper">
						  		<a href="javascript:enviar();" class="botao"><span>Buscar</span></a>
								<a href="javascript:exibirbusca();" class="botao" style="margin-left: 6px"><span>Voltar</span></a>
						  	</div>							  				
						</div>
						<br /><br /><br /><br />	

					</div>
					
					<div id="detalhada" class="FundoCadastro" style="display: none;">
						<br />
						<p>
							<label for="por">Você busca por:</label>
							<select name="sexo_busca" id="sexo_busca" size="1">							
								<option value="0">Homem</option>
								<option value="1">Mulher</option>
								<option value="2">Transexual/Travesti</option>		
								<option value="3">Tanto Faz</option>					
							</select>	
						</p>
						
						<p>
							<label for="idademin">Idade Min.:</label>
							<select name="idade_min_busca" id="idade_min_busca" size="1">
								<c:forEach begin="18" end="80" var="j" >
									<option value="${j}">${j}</option>
								</c:forEach>						
							</select>	
						</p>
						
						<p>
							<label for="idademax">Idade Máx.:</label>
							<select name="idade_max_busca" id="idade_max_busca" size="1">
								<c:forEach begin="18" end="80" var="i" >
									<c:if test="${40 == i}">	
										<option	value="${i}" selected="true">${i}</option>
									</c:if>
									<c:if test="${40 != i}">	
										<option value="${i}">${i}</option>
									</c:if>
								</c:forEach>						
							</select>	
						</p>
						
						<p>
							<label for="estado">Estado:</label>
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
							<label for="cidade">Cidade:</label>
							<select name="cidade_busca" id="cidade_busca" size="1">
								<option value="" selected="true">Selecione o estado e aguarde</option>
							</select>
						</p>
					
						<p>
							<label for="civil">Estado Civil:</label>
							<select name="estado_civil_busca" id="estado_civil_busca" size="1">
								<option value="4">Tanto Faz</option>
								<option value="0">Solteiro(a)</option>
								<option value="1">Casado(a)</option>
								<option value="2">Divorciado(a)</option>
								<option value="3">Viúvo(a)</option>								
							</select>	
						</p>
						
						<p>
							<label for="alturamin">Altura Min.:</label>
							<select name="altura_min_busca" id="altura_min_busca" size="1">
								<option value="1.4">1.40</option>
								<option value="1.41">1.41</option>
								<option value="1.42">1.42</option>
								<option value="1.43">1.43</option>
								<option value="1.44">1.44</option>
								<option value="1.45">1.45</option>
								<option value="1.46">1.46</option>
								<option value="1.47">1.47</option>
								<option value="1.48">1.48</option>
								<option value="1.49">1.49</option>
								<option value="1.5">1.50</option>
								<option value="1.51">1.51</option>
								<option value="1.52">1.52</option>
								<option value="1.53">1.53</option>
								<option value="1.54">1.54</option>
								<option value="1.55">1.55</option>
								<option value="1.56">1.56</option>
								<option value="1.57">1.57</option>
								<option value="1.58">1.58</option>
								<option value="1.59">1.59</option>
								<option value="1.6">1.60</option>
								<option value="1.61">1.61</option>
								<option value="1.62">1.62</option>
								<option value="1.63">1.63</option>
								<option value="1.64">1.64</option>
								<option value="1.65">1.65</option>
								<option value="1.66">1.66</option>
								<option value="1.67">1.67</option>
								<option value="1.68">1.68</option>
								<option value="1.69">1.69</option>
								<option value="1.7">1.70</option>
								<option value="1.71">1.71</option>
								<option value="1.72">1.72</option>
								<option value="1.73">1.73</option>
								<option value="1.74">1.74</option>
								<option value="1.75">1.75</option>
								<option value="1.76">1.76</option>
								<option value="1.77">1.77</option>
								<option value="1.78">1.78</option>
								<option value="1.79">1.79</option>
								<option value="1.8">1.80</option>
								<option value="1.81">1.81</option>
								<option value="1.82">1.82</option>
								<option value="1.83">1.83</option>
								<option value="1.84">1.84</option>
								<option value="1.85">1.85</option>
								<option value="1.86">1.86</option>
								<option value="1.87">1.87</option>
								<option value="1.88">1.88</option>
								<option value="1.89">1.89</option>
								<option value="1.9">1.90</option>
								<option value="1.91">1.91</option>
								<option value="1.92">1.92</option>
								<option value="1.93">1.93</option>
								<option value="1.94">1.94</option>
								<option value="1.95">1.95</option>
								<option value="1.96">1.96</option>
								<option value="1.97">1.97</option>
								<option value="1.98">1.98</option>
								<option value="1.99">1.99</option>
								<option value="2.0">2.00</option>
								<option value="2.01">2.01</option>
								<option value="2.02">2.02</option>
								<option value="2.03">2.03</option>
								<option value="2.04">2.04</option>
								<option value="2.05">2.05</option>
								<option value="2.06">2.06</option>
								<option value="2.07">2.07</option>
								<option value="2.08">2.08</option>
								<option value="2.09">2.09</option>
								<option value="2.1">2.10</option>
								<option value="2.11">2.11</option>
								<option value="2.12">2.12</option>
								<option value="2.13">2.13</option>
								<option value="2.14">2.14</option>
								<option value="2.15">2.15</option>
								<option value="2.16">2.16</option>
								<option value="2.17">2.17</option>
								<option value="2.18">2.18</option>
								<option value="2.19">2.19</option>
								<option value="2.2">2.20</option>
								<option value="2.21">2.21</option>
								<option value="2.22">2.22</option>
								<option value="2.23">2.23</option>
								<option value="2.24">2.24</option>
								<option value="2.25">2.25</option>
								<option value="2.26">2.26</option>
								<option value="2.27">2.27</option>
								<option value="2.28">2.28</option>
								<option value="2.29">2.29</option>	
							</select>&#160;m.
						</p>
						
						<p>
							<label for="alturamax">Altura Máx.:</label>
								<select name="altura_max_busca" id="altura_max_busca" size="1">
								<option value="1.4">1.40</option>
								<option value="1.41">1.41</option>
								<option value="1.42">1.42</option>
								<option value="1.43">1.43</option>
								<option value="1.44">1.44</option>
								<option value="1.45">1.45</option>
								<option value="1.46">1.46</option>
								<option value="1.47">1.47</option>
								<option value="1.48">1.48</option>
								<option value="1.49">1.49</option>
								<option value="1.5">1.50</option>
								<option value="1.51">1.51</option>
								<option value="1.52">1.52</option>
								<option value="1.53">1.53</option>
								<option value="1.54">1.54</option>
								<option value="1.55">1.55</option>
								<option value="1.56">1.56</option>
								<option value="1.57">1.57</option>
								<option value="1.58">1.58</option>
								<option value="1.59">1.59</option>
								<option value="1.6">1.60</option>
								<option value="1.61">1.61</option>
								<option value="1.62">1.62</option>
								<option value="1.63">1.63</option>
								<option value="1.64">1.64</option>
								<option value="1.65">1.65</option>
								<option value="1.66">1.66</option>
								<option value="1.67">1.67</option>
								<option value="1.68">1.68</option>
								<option value="1.69">1.69</option>
								<option value="1.7">1.70</option>
								<option value="1.71">1.71</option>
								<option value="1.72">1.72</option>
								<option value="1.73">1.73</option>
								<option value="1.74">1.74</option>
								<option value="1.75">1.75</option>
								<option value="1.76">1.76</option>
								<option value="1.77">1.77</option>
								<option value="1.78">1.78</option>
								<option value="1.79">1.79</option>
								<option value="1.8">1.80</option>
								<option value="1.81">1.81</option>
								<option value="1.82">1.82</option>
								<option value="1.83">1.83</option>
								<option value="1.84">1.84</option>
								<option value="1.85">1.85</option>
								<option value="1.86">1.86</option>
								<option value="1.87">1.87</option>
								<option value="1.88">1.88</option>
								<option value="1.89">1.89</option>
								<option value="1.9" selected="true">1.90</option>
								<option value="1.91">1.91</option>
								<option value="1.92">1.92</option>
								<option value="1.93">1.93</option>
								<option value="1.94">1.94</option>
								<option value="1.95">1.95</option>
								<option value="1.96">1.96</option>
								<option value="1.97">1.97</option>
								<option value="1.98">1.98</option>
								<option value="1.99">1.99</option>
								<option value="2.0">2.00</option>
								<option value="2.01">2.01</option>
								<option value="2.02">2.02</option>
								<option value="2.03">2.03</option>
								<option value="2.04">2.04</option>
								<option value="2.05">2.05</option>
								<option value="2.06">2.06</option>
								<option value="2.07">2.07</option>
								<option value="2.08">2.08</option>
								<option value="2.09">2.09</option>
								<option value="2.1">2.10</option>
								<option value="2.11">2.11</option>
								<option value="2.12">2.12</option>
								<option value="2.13">2.13</option>
								<option value="2.14">2.14</option>
								<option value="2.15">2.15</option>
								<option value="2.16">2.16</option>
								<option value="2.17">2.17</option>
								<option value="2.18">2.18</option>
								<option value="2.19">2.19</option>
								<option value="2.2">2.20</option>
								<option value="2.21">2.21</option>
								<option value="2.22">2.22</option>
								<option value="2.23">2.23</option>
								<option value="2.24">2.24</option>
								<option value="2.25">2.25</option>
								<option value="2.26">2.26</option>
								<option value="2.27">2.27</option>
								<option value="2.28">2.28</option>
								<option value="2.29">2.29</option>	
							</select>&#160;m.
						</p>
						
						<p>
							<label for="fisico">Tipo Físico:</label>
							<select name="tipo_fisico_busca" id="tipo_fisico_busca" size="1">
								<option value="7">Tanto Faz</option>
								<option value="0">Magro</option>
								<option value="1">Médio</option>
								<option value="2">Em forma</option>
								<option value="3">Pouco acima do peso</option>
								<option value="4">Muito acima do peso</option>
								<option value="5">Musculoso</option>
								<option value="6">Pequeno</option>								
							</select>		
						</p>
												
						<p>
							<label for="pele">Tom de pele:</label>
							<select name="tom_pele_busca" id="tom_pele_busca" size="1">
								<option value="10">Tanto Faz</option>
								<option value="0">Branco</option>
								<option value="1">Asiático Japonês</option>
								<option value="2">Pardo/Mulato</option>
								<option value="3">Preto/Africano</option>
								<option value="4">Asiático Chinês</option>
								<option value="5">Asiático Coreano</option>
								<option value="6">Indiano</option>
								<option value="7">Latino/Hispânico</option>
								<option value="8">Oriente Médio</option>
								<option value="9">Outros</option>
							</select>		
						</p>
						
						<p>
							<label for="com">Que faça sexo com:</label>
							<select name="faz_sexo_com_busca" id="faz_sexo_com_busca" size="1">
								<option value="7">Tanto Faz</option>
								<option value="0">Mulher</option>
								<option value="1">Homem</option>
								<option value="2">Transexual</option>
								<option value="3">Homem e Mulher</option>
								<option value="4">Homem e Transexual</option>
								<option value="5">Mulher e Transexual</option>
								<option value="6">Todos</option>
							</select>		
						</p>			
						
						<br />
						
						<div style="margin-left: 140px; margin-top:10px;">
							<div class="buttonwrapper">
						  		<a href="javascript:enviar();" class="botao"><span>Buscar</span></a>
								<a href="javascript:exibirbusca();" class="botao" style="margin-left: 6px"><span>Voltar</span></a>
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