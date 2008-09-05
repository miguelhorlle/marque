<%@ page isErrorPage="false" errorPage="erro.jsp" import="com.marquesexo.chat.*"%>
<% 
	
	String nickname = (String)session.getAttribute("nickname");
	String roomName = null;
	String roomNameAux = "";
	String cod_sexual_another = "";
	
	if (request.getParameter("room") != null) {
		roomName = new String(request.getParameter("room"));
	} else if(request.getAttribute("room") != null){
		roomName = (String) request.getAttribute("room");
	}
	
	if (nickname != null && nickname.length() > 0)
	{
		ChatRoomList roomList = (ChatRoomList)application.getAttribute("chatroomlist");
		ChatRoom chatRoom = roomList.getRoom(roomName);
		if (chatRoom != null)
		{
			
			String[] aux = roomName.split("_");
			if(aux.length == 1){
				roomNameAux = roomName;										
			}else{

				String roomNameAux1 = ((Long)session.getAttribute("codigo_sexual")).toString() + "_" + aux[1];
				String roomNameAux2 =  aux[0] + "_" + ((Long)session.getAttribute("codigo_sexual")).toString();
				String roomNameAux3 = ((Long)session.getAttribute("codigo_sexual")).toString() + "_" + aux[0];				
				
				if(roomName.equals(roomNameAux1) == true){
					roomNameAux = roomName;
				}else if(roomName.equals(roomNameAux2) == true){
					roomNameAux = roomNameAux3;					
				}
			}
			
			if(roomName.startsWith("Sala") == false){
				Chatter another = chatRoom.getAnotherChatter(nickname);
				if(another != null)
					cod_sexual_another = another.getCod();
				else
					cod_sexual_another = "-1";
			}
			String msg = request.getParameter("messagebox");
			if ( msg != null && msg.length() > 0)
			{
				msg = msg.trim();
				chatRoom.addMessage(new Message(nickname, msg, new java.util.Date().getTime()));
			}
			
	
%>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="chat.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta name="Author" content="MarqueSexo">	
	<script language="JavaScript" type="text/javascript">
	
	function enviar(){
		document.msg.action = "send_message.jsp";			
		document.msg.method = "post";	
		document.msg.submit();
		parent.MessageWin.atualiza();
	}
	
	function sair(){
		parent.sair("<%=roomName%>","<%=roomNameAux%>");
		
	}	
	
	function verperfil(){
		parent.verperfil("<%=cod_sexual_another%>");
		
		
	}
	
	<%
		}
		else
		{
	%>
			parent.exibirerro();
	<%
		}
	}
	else
	{
	%>
		parent.exibirerro();
	<%
	}
	
%>
	
	
	
	</script>
	</head>
	<body onLoad="document.msg.messagebox.focus();" bgcolor="#FFFFFF">
		<form name="msg" action="send_message.jsp" method="post" onsubmit="return false" >
		<center>

			<input type="text" name="messagebox" maxlength="300" size="35" onkeypress="if(event.keyCode==13)enviar();"/>
			<input type="hidden" name="nickname" value='<%=session.getAttribute("nickname")%>' />
			<input type="hidden" name="room" value="<%=roomName%>" />
			<br />
			<input name="benviar" value="Enviar" type="button" onclick="javascript:enviar();" />
			<%
			if(roomName.startsWith("Sala")){			
			%>
			
			&#160;&#160;&#160;
			<input name="bsair" value="Sair da Sala" type="button" onclick="javascript:sair();" />
				
			<%				
			}else{		

			%>
			
			&#160;&#160;&#160;
			<input name="bver" value="Ver Perfil" type="button" onclick="javascript:verperfil();" />
			&#160;&#160;&#160;
			<input name="bsair" value="Sair" type="button" onclick="javascript:sair();" />
			
			
			<%		

			}		
			%>
									
		</center>
		</form>	
	</body>
</html>

