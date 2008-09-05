<%@ page import="com.marquesexo.chat.*" errorPage="erro.jsp"%>
<html>
<head>
<title>Logout</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/chat.css">
<meta http-equiv="pragma" content="no-cache">
<meta name="Author" content="MarqueSexo">

<script language="JavaScript" type="text/javascript">
	
	function fechar(nome){
		parent.fechar(nome);
		
	}			
	
	
</script>

</head>

<body>
<div align="center">
<center>
<%
	String nickname = (String)session.getAttribute("nickname");
	String roomName = (String)request.getParameter("room");
	
	if (nickname != null && nickname.length() > 0)
	{
		ChatRoomList roomlist = (ChatRoomList) application.getAttribute("chatroomlist");
		ChatRoom chatRoom = roomlist.getRoom(roomName);
		
		if (chatRoom != null)
		{
			if(chatRoom.getNoOfMessages() != 0){
				chatRoom.addMessage(new Message("Sistema", nickname + " saiu da conversa.", new java.util.Date().getTime()));
			}
			chatRoom.removeChatter(nickname);
					
			if((chatRoom.getName().equals("RedeMarqueSexo") == false)
					&&(chatRoom.getName().equals("SalaSexual") == false)){
				if(chatRoom.getNoOfChatters() == 0){
					roomlist.removeRoom(chatRoom.getName());
				}
				
			}
			
			
		}
		else
		{
			response.sendRedirect("erro_chat.jsp");
		}
		
		
	}
	else
	{
		response.sendRedirect("erro_chat.jsp");
	}
	%>
</center>
</div>
</body>
</html>
