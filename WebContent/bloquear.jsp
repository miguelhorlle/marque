<%@ page import="com.marquesexo.chat.*" errorPage="erro.jsp" %>
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/chat.css">
<META http-equiv="pragma" content="no-cache">
<meta name="Author" content="MarqueSexo">	

<script language="JavaScript" type="text/javascript">


<%
	String nickname = (String)session.getAttribute("nickname");
	String codigo_sexual_bloqueado = (String)request.getParameter("codigo_sexual_bloqueado");

	ChatRoomList roomList = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
	ChatRoom[] rooms = roomList.getRoomsOfChatter(nickname);
	ChatRoom chatRoom = null;
	String roomName = "";
	String roomNameAux = "";


	for (int i = 0; i < rooms.length; i++)
	{
		chatRoom = (ChatRoom)rooms[i];		
		if(chatRoom != null){
			roomName = chatRoom.getName();
			
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
			
			if((roomName.startsWith("Sala") == false)&&(roomName.startsWith("Rede") == false)){
				Chatter another = chatRoom.getAnotherChatter(nickname);
				
				if(another != null){
					if(another.getCod().equals(codigo_sexual_bloqueado)){
						%>
						parent.fechar("<%=roomNameAux%>");
						<%
			
						chatRoom.addMessage(new Message("Sistema", nickname + " saiu da conversa.", new java.util.Date().getTime()));
						chatRoom.removeChatter(nickname);		
						
					}													
				}
			}			
		}
	}


	%>
	
	
	location.href= "${pageContext.request.contextPath}/bloqueados";
	


</script>
</HEAD>

<BODY>
</BODY>
</HTML>