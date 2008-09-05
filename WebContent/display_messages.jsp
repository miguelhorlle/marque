<%@ page isErrorPage="false" errorPage="erro.jsp" import="java.util.Date,java.text.DateFormat,com.marquesexo.chat.*"%>
<html>
	<head>
	<meta name="Author" content="Marquesexo">	
	<link rel="stylesheet" type="text/css" href="chat.css">
	<script language="JavaScript" type="text/javascript">
	
	function reload(){
		window.location.reload();
	}
	
	function atualiza(){
		setTimeout('reload()',1000);
	}

<%
	String nickname = (String)session.getAttribute("nickname");
	String roomName = null;

	if (request.getParameter("room") != null) {
		roomName = new String(request.getParameter("room"));
	} else if(request.getAttribute("room") != null){
		roomName = (String) request.getAttribute("room");
	}

	ChatRoomList roomList = null;
	ChatRoom chatRoom = null;
	Chatter chatter = null;
	Message[] messages = null;

	if (nickname != null)
	{
		try
		{
			roomList = (ChatRoomList) application.getAttribute("chatroomlist");	
			if (roomName != null && roomName != "")
			{
				chatRoom = roomList.getRoom(roomName);				
				if (chatRoom != null)
				{
					
					chatter = chatRoom.getChatter(nickname);					
					
					if (chatter != null)
					{
						if(roomName.equals("SalaSexual") == false){
							long enteredAt = chatter.getEnteredInRoomAt();
							if (enteredAt != -1)
							{
								messages = chatRoom.getMessages(enteredAt);	
							}
							else
							{
								messages = chatRoom.getMessages(chatter.getLoginTime());
							}
						}else{
							messages = chatRoom.getMessages(chatRoom.getTimeFirstMessage());							
							
						}
					}

				}
				else
				{
%>

		parent.exibirerro();
<%					
				
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+ e.getMessage());
			throw new ServletException("Unable to get handle to ServletContext");
		}	
	
%>

	
	</script>
	</head>
	<body onLoad="window.location.hash='#current'" bgcolor="#FFFFFF">
		<input type="hidden" name="room" value="<%=roomName%>" />

		<table width="250" border="0" halign="left">
			<tr>
				<td width="100%" valign="top">
					<table>
						<tr>
							<td>
	
	<%
		
		if(messages != null && messages.length > 0)
		{
			
			for (int i = 0; i < messages.length; i++)
			{
				Message message = (Message)messages[i];
				String chatterName = message.getChatterName();
				String strmsg = message.getMessage();
				long time = message.getTimeStamp();
				Date date = new Date(time);
	
				if (chatterName.equalsIgnoreCase((String)session.getAttribute("nickname")))
				{
					out.write("<font face=\"Verdana\" size=\"2\" color=\"blue\"><b>" + chatterName + " ("+ DateFormat.getTimeInstance().format(date)+ ")</b></font><br> " + strmsg+"<br><br>\n");
				}
				else if (chatterName.equalsIgnoreCase("Sistema"))
				{
					out.write("<span class=\"error\"><b>Sistema: ("+ DateFormat.getTimeInstance().format(date)+ ")</b><br>" + strmsg+"</span><br><br>\n");
				}
				else
				{
					out.write("<font face=\"Verdana\" size=\"2\"><b>"+chatterName + " ("+ DateFormat.getTimeInstance().format(date)+ ")</b></font><br> " + strmsg + "<br><br>\n");
				}			
			}
			out.write("<a name=\"current\"></a>");
		}
		else
		{
			out.write("<font color=\"red\" face=\"Verdana\" size=\"2\">Não há mensagens</font><br>");
		}
		out.write("<a name=\"current\"></a>");
	}
	else
	{
		response.sendRedirect("erro_chat.jsp");
	}
		%>
							</td>
						</tr>
					</table>
				</td>
				
			</tr>
		</table>
	</body>
</html>