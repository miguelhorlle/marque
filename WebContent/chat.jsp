<%@ page session="true" errorPage="index.jsp"%>
<%
	String nickname = (String)session.getAttribute("nickname");
	String roomName = (String)request.getParameter("room");
	
	if (nickname != null && nickname.length() > 0)
	{

%>
	
<html>
<head>
	<title><%=roomName%></title>
	<meta name="Author" value="MarqueSexo" content="">
	<script language="javascript" type="text/javascript">
	
		function sair(room,nome) {			
			
			window.location.href = "${pageContext.request.contextPath}/logout.jsp?room="+room;
			parent.hidebox(nome);
			
			
		}
		
		
		function exibirerro() {		
			
			window.location.href = "${pageContext.request.contextPath}/erro_chat.jsp";

			
		}
		
		function fechar(nome) {			
			parent.hidebox(nome);		
			
		}	
		
		function verperfil(cod) {	
			if(!document.all){
				var fr = parent.document.getElementById("main");
				fr.src="${pageContext.request.contextPath}/perfil?cod="+cod;	
			}else{		
				parent.main.window.location.href="${pageContext.request.contextPath}/perfil?cod="+cod;		
			}
		}
		
<%
	}
	else
	{
		
%>

		exibirerro();
		
<%
	}
%>
	
	</script>
</head>
	<input type="hidden" name="room" value='<%=request.getParameter("room")%>' />
	<frameset rows="63%,37%">
	<frame SRC='display_messages.jsp?room=<%=request.getParameter("room")%>#current' name="MessageWin">
	<frame SRC='send_message.jsp?room=<%=request.getParameter("room")%>' name="TypeWin">
	</frameset>
	<noframes>
	<h2>Esta conversa requer um browse com suporte a frames</h2>
	</noframes>
</html>
