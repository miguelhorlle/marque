<%@page isELIgnored="false" %>  
<%@page contentType="text/html"%>  
<%@page pageEncoding="UTF-8"%>   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
	<title>MarqueSexo</title>
		
	<script language="JavaScript" type="text/javascript" src="popup.js"></script>
	
	<script language="JavaScript">

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
		
		function alerta(){
			alert("P");
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
<body bgcolor="black">
teste
</body>

</html>