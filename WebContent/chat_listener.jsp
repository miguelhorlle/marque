<%@ page isErrorPage="false" errorPage="erro.jsp"%>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<script type="text/javascript" src="/marquesexo/dwr/interface/ControladorAjax.js"></script>
<script type="text/javascript" src="/marquesexo/dwr/engine.js"></script>
<script type="text/javascript" src="/marquesexo/dwr/util.js"></script>

<script language="JavaScript" type="text/javascript">
	
	function startCheck() {    	
    	self.setInterval("check()", 4000);
    	return true;
  	}

	<%
	String nickname = (String)session.getAttribute("nickname");
	String cod_sexual = ((Long)session.getAttribute("codigo_sexual")).toString();
	%>

	function check()
	{
	    ControladorAjax.checkMessages("<jsp:expression>nickname</jsp:expression>", "<jsp:expression>cod_sexual</jsp:expression>", gotMessages);
	}
	
	function gotMessages(messages)
	{
	    if(messages.length > 0)
	    {
	    	parent.parent.popup_talk(messages[0],messages[1],messages[2]);	    
	    }
	}
	
</script>
</head>
<body bgcolor="black" onload="startCheck();">
</body>
</html>