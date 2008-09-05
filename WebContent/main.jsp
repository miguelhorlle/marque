<%@page isELIgnored="false" %>  
<%@page contentType="text/html"%>  
<%@page pageEncoding="UTF-8"%>   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
	<title>MarqueSexo</title>
	<meta name="Author" value="MarqueSexo">
    <meta name="robots" content="index,nofollow" />		
	<meta name="description" content="Na Rede Marque Sexo você conversa com pessoas que só querem um coisa: fazer sexo! Se cadastre agora e marque sexo com alguém hoje mesmo! " />
   	<meta name="keywords" content="Relacionamento Sexo Casual Grupal Casal Encontro Namoro" />
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />

	<script type="text/javascript" src="popup.js"></script>
	
	<script language="javascript">

		function sair() { 
			
			if ((event.clientX <= 0 && event.clientY <= 0)|| 
			(window.event.clientX <= 0 && window.event.clientY <= 0)) {
				window.location = "${pageContext.request.contextPath}/sair";
			} 
		 
		}
	
		function popup_talk(nome, link, titulo) {			
			if(exists(nome) == false){
				createPopup(nome, link, titulo);	
				pisca();	

			}else{
				if(ishidebox(nome)== true){				
					changecontent(nome,link);
					restorewindow(nome);		
					showbox(nome);			
				}else{
					reloadcontent(nome);
					restorewindow(nome);
					showbox(nome);	
				}

				pisca();	
			}
		}	
		
		function reload(){
			window.location.reload();
		}
		
		function fechar(nome) {			
			hidebox(nome);		
			
		}
		
		function pisca(){
  			window.focus();
 		}

			
	</script>
</head>
<body bgcolor="black" onunload="sair();">

<c:if test='${permissao == null}'> 
	<iframe id="main" src="${pageContext.request.contextPath}/index.jsp" width="100%" height="100%" frameborder="0" scrolling="auto" align="middle">
	</iframe>
</c:if>
<c:if test='${permissao != null}'> 
	<iframe id="main" src="${pageContext.request.contextPath}/home" width="100%" height="100%" frameborder="0" scrolling="auto" align="middle">
	</iframe>
</c:if>


</body>
</html>