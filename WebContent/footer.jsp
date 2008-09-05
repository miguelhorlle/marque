<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%
	String nro = (String)request.getParameter("fig");
	if(nro == null){
		nro = String.valueOf((int)(Math.random()*13) + 1);
	}
%>
	   	<div id="footer">
		  	<div class="menuprinc">&#160;&#160;&#160;
		  		<c:if test='${permissao == null}'>
		    		<a href="${pageContext.request.contextPath}/index.jsp" alt="Home" class="LinksMenuHome"><span class="style3">h</span>ome</a>
		    	</c:if>
		    	<c:if test='${permissao != null}'>
		    		<a href="${pageContext.request.contextPath}/home" alt="Home" class="LinksMenuHome"><span class="style3">h</span>ome</a>
		    	</c:if>
		    	
				&#160;&#160;&#160;&#160; <a href="${pageContext.request.contextPath}/como_funciona.jsp" alt="Como funciona" class="LinksMenuHome"><span class="style3">c</span>omo
		    	funciona </a>
				&#160;&#160;&#160;&#160; <a href="${pageContext.request.contextPath}/onde" alt="Onde marcar" class="LinksMenuHome"><span class="style3">o</span>nde
		  		marcar</a>
		  		&#160;&#160;&#160;&#160; <a href="${pageContext.request.contextPath}/fale_conosco.jsp" alt="Fale conosco" class="LinksMenuHome"><span class="style3"> f</span>ale
		  		conosco </a>  
		  	</div>
		  	<br />
		</div>
	</div>
</div>

<div id="rightcolumn">

	<c:if test='${discreto != null}'> 
		<c:if test='${discreto == 0}'> 
			<br /><br /><br />
			<img style="width: 324px; height: 434px;"src="img/fig<%=nro%>.jpg"/>
		</c:if>
		<c:if test='${discreto == 1}'> 
			<br /><br /><br />
			<img style="width: 324px; height: 434px;"src="img/fig0.jpg"/>
		</c:if>
	</c:if>
	<c:if test='${discreto == null}'> 
		<img src="img/fig<%=nro%>.jpg"/>
	</c:if>


</div>

</div>