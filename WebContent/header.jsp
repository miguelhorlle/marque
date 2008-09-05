<%@page isELIgnored="false" %>  
<%@page contentType="text/html"%>  
<%@page pageEncoding="UTF-8"%>   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div id="maincontainer">

    
 

<div id="topo">
    <div class="logo">
	    <c:if test='${permissao == null}'>
			<a href="${pageContext.request.contextPath}/index.jsp" target="_self">
			<img src="img/logo.jpg" alt="Marque Sexo" border="0"/>
			</a>
		</c:if>
		<c:if test='${permissao != null}'>
			<c:if test='${discreto == 0 || discreto == null}'> 
				<a href="${pageContext.request.contextPath}/home" target="_self">
				<img src="img/logo.jpg" alt="Marque Sexo" border="0"/>
				</a>
			</c:if>
			<c:if test='${discreto == 1}'> 
				<a href="${pageContext.request.contextPath}/home" target="_self">
				<img src="img/logo2.jpg" alt="Marque Sexo" border="0"/>
				</a>
			</c:if>
		</c:if>
    </div>
    
    <c:if test='${permissao == null}'> 
	    <div class="login">
		<form id="frm_login" action="${pageContext.request.contextPath}/login" method="post">
			<table border="0" cellpadding="0" cellspacing="0" class="TableLogin">
		  	<tr>
		    	<td class="classh1">Login:</td>
		    	<td><input class="TextBox1" id="email_login" name="email_login" type="text" /></td>
		  	</tr>
		  	<tr>
		    	<td class="classh1">Senha:</td>
		    	<td><input class="TextBox1" id="senha_login" name="senha_login" type="password" onKeyDown="if(event.keyCode==13) enviar_login();"/></td>
		  	</tr>
		  	<tr>
		    	<td height="40" colspan="2">
			    	<table width="188" border="0" cellpadding="0" cellspacing="0">
			           	<tr>
			             	<td width="107" height="34"><h2>*<a href="${pageContext.request.contextPath}/esqueci_minha_senha.jsp" alt="Lembrar minha senha" class="LinksLogin">Esqueci a senha</a></h2></td>
			             	<td width="71" > 
			             	<a href="javascript:enviar_login();" target="_self" style="margin-left: 6px" class="botao"><span>entrar</span></a>
			           	</tr>
					    <tr>
			    			<td colspan="2"><h3>&#160;Não é cadastrado?&#160;&#160;<a href="${pageContext.request.contextPath}/cadastro.jsp" alt="Cadastro" class="LinksLogin">Clique aqui.</a> </h3></td>
						</tr>
		        	</table>
		        </td>
		  	</tr>	 	
			</table>
		</form>
		</div>
	</c:if>
	
	<c:if test='${permissao != null}'> 
	
	    <div class="style5" id="topomenu">
	    	
			<div class="nav-container-outer">			
				<img src="img/menusup/esq.gif" alt="" width="18" height="71" class="float-left" />
			   	<img src="img/menusup/dir.gif" alt="" class="float-right" />
			   	
			   	<ul class="nav-container" id="nav-container" name="nav-container">
		            <li><a class="item-primary" href="#" target="_self"><span class="style3">m</span>eu
		                perfil</a>
		                <ul style="width:150px;">
		                	<li><a href="${pageContext.request.contextPath}/editar" title="Editar dados" target="_self" >Editar
		                      dados</a></li>
		                  	<li><a href="${pageContext.request.contextPath}/editarfotos" title="Álbum de fotos" target="_self" >Álbum
		                      de fotos</a></li>
		                </ul>
		            </li>		            
		            <li><span class="divider divider-vert" ></span></li>		            
		            <li><a class="item-primary" href="#" target="_self"><span class="style3">o</span>utros
		                membros</a>
		                <ul style="width:150px;">
		                  <li><a href="${pageContext.request.contextPath}/busca.jsp" title="Buscar membros" target="_self" >Buscar
		                      membros</a></li>
		                  <li><a href="${pageContext.request.contextPath}/bloqueados" title="Bloqueados" target="_self" >Bloqueados</a></li>
		                  <li><a href="${pageContext.request.contextPath}/interessantes" title="Interessantes" target="_self" >Interessantes</a></li>
		                  <li><a href="${pageContext.request.contextPath}/japeguei" title="Já peguei!" target="_self" >Já peguei!</a></li>
		                </ul>
		            </li>
		            <li><span class="divider divider-vert" ></span></li>
		            <li><a class="item-primary" href="#" target="_self"><span class="style3">m</span>arque
		                sexo</a>
		                <ul style="width:150px;">
		                  <li><a href="${pageContext.request.contextPath}/online" title="Membros Online" target="_self" >Membros Online</a></li>
		                  <li><a href="${pageContext.request.contextPath}/sala" title="Sala Sexual" target="_self" >Sala Sexual</a></li>
		                  <li><a href="${pageContext.request.contextPath}/configurarwebcam" title="Webcam" target="_self" >Webcam</a></li>
		                  <c:if test='${permissao == 1}'> 
		            		<li><a href="${pageContext.request.contextPath}/cadastro_motel.jsp" title="cad" target="_self" >Cadastro Motel</a></li>
		                  	<li><a href="${pageContext.request.contextPath}/editar_motel.jsp" title="edt" target="_self" >Editar Motel</a></li>
		                  	<li><a href="${pageContext.request.contextPath}/editar_m.jsp" title="edtm" target="_self" >Editar M</a></li>
		                  	<li><a href="${pageContext.request.contextPath}/surubas.jsp" title="sur" target="_self" >Surubas</a></li>
		                  	<li><a href="${pageContext.request.contextPath}/verenquetes" title="ver" target="_self" >Enquetes</a></li>
		                  </c:if>
		                </ul>
		            </li>		            
		            <li><span class="divider divider-vert" ></span></li>
		            <li><a class="item-primary" href="${pageContext.request.contextPath}/faq.jsp" target="_self"><span class="style3">f</span>aq</a> </li>
		            <li><span class="divider divider-vert" ></span></li>
		            <li><a class="item-primary" href="${pageContext.request.contextPath}/sair" target="_self"><span class="style3">s</span>air</a> </li>
		            <li><span class="divider divider-vert" ></span></li>
		            <li class="clear"> </li>            
		            
			     </ul>
			</div>
		</div>
		
		<div id="loginApos">
			<a href="${pageContext.request.contextPath}/rss.jsp"><img src="img/feed-icon.png" border="0"/></a>
		    <span class="style5">o</span>lá ${nome}<br />
	        <span class="style5">s</span>eu código sexual é o ${codigo_sexual}<br />
	        <div class="buttonwrapper">
	              <a class="botao" href="${pageContext.request.contextPath}/sair" target="_self" title="sair do Marque Sexo"><span>sair</span></a>
	        </div>   			
		</div>
	</c:if>   
</div>

<div id="contentwrapper">
	<div id="contentcolumn">    	