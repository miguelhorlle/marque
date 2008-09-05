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
   		<title>MarqueSexo</title>		
		
		<script language="javascript">
		
			function check_upload() {
		  		var ext = document.arquivo_video.value;
		  		
		  		if(ext != ""){
			 		ext = ext.substring(ext.length-3,ext.length);
			  		ext = ext.toLowerCase();
			  		if((ext != "mpeg")&#38;&#38;(ext != "mpg")&#38;&#38;(ext != "avi")&#38;&#38;(ext != "wmv")) {
			    		alert("Você selecionou um arquivo ."+ext+"; por favor, selecione um arquivo de vídeo válido!");
			    		return false; 
			    	}
			  		else
			  		    return true; 
		  		}
		  		else
			  		return true; 
		  	}
		
			function enviar(){
	  			if (check_upload() == true){												 					
					document.upform.action = "${pageContext.request.contextPath}/enviararquivo";			
					document.upform.method = "post";	
					document.upform.submit();					
				}
				
			}
		
		</script>
		
	</head>
	<body bgcolor="black">
	
	 	<jsp:include page="header.jsp" flush="true" />


			<h3>Enviar Vídeos</h3>
			
			<br />
			<br />
			
			<fieldset style="border: 1px solid; border-color: #FF8C00; ">	
				<br />
				<b>Vídeo: </b><br /><br />
				Você pode participar da TV MarqueSexo enviando seu vídeo para nós!
				Selecione o arquivo (mpeg, mpg, wmv ou avi, com até 10MB) e clique em enviar! Se
				o vídeo não conter cenas violentas e que ofendam a moral de alguém, o 
				seu vídeo será exibido na TV MarqueSexo!
				<br /><br />
				<form method="post" action="${pageContext.request.contextPath}/enviararquivo" name="upform" enctype="multipart/form-data">
					Arquivo: 
					<input type="file" name="arquivo_video" accept="video/*;" /><br /><br />
					<input type="submit" value="Enviar" />					
					
				</form>
				<br />
			</fieldset>
			<br />				
			<br />
						
					
		
		<jsp:include page="footer.jsp" flush="true" />

		
	</body>
	<iframe src="chat_listener.jsp" width="1" height="1" frameborder="0" />
</html>
</jsp:root>