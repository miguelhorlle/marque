package com.marquesexo.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.marquesexo.bean.Membro;
import com.marquesexo.chat.Chatter;

import com.marquesexo.chat.ChatRoom;
import com.marquesexo.chat.ChatRoomList;
import com.marquesexo.control.MarqueSexoControl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/** Allows users to add new rooms.
* At server startup this servlet is initialised.
* @author Sukhwinder Singh
*/
public class ManageChatServlet extends HttpServlet
{
	/**
	 * http://scriptasylum.com/misc/popup/popup.html
	 * http://www.devguru.com/Technologies/ecmaScript/quickref/window.html
	 */
	private static final long serialVersionUID = 1L;
	public static ChatRoomList rooms = new ChatRoomList();
	private String contextPath;
	public static MarqueSexoControl controlador;
	private int promocao;
	
	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro , "rafaelcl@gmail.com");
	}
	
	public void init() throws ServletException
	{
		controlador = new MarqueSexoControl();	
		promocao = 1;
		
		addNewRoom(rooms, "RedeMarqueSexo", "Sala de Entrada");		
		addNewRoom(rooms, "SalaSexual", "Sala Sexual");	
		System.out.println("Sistema criou as salas.");
		getServletContext().setAttribute("chatroomlist", rooms);
		getServletContext().setAttribute("promocao", promocao);
			
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");		
		contextPath = request.getContextPath();
		processRequest(request, response);
	}
	
	/**	Allows users to add new rooms after performing minimum validation.
	* Also saves information to chat.properties files if required by initialization parameter <code>saveRooms</code>.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);	
	}
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response){
		if(request.getRequestURI().endsWith("/chat")) {
			chat(request, response);
		}else if(request.getRequestURI().endsWith("/clean")) {
			clean(request, response);
		}
	}
	
	public void clean(HttpServletRequest request,
			HttpServletResponse response){
		try
		{
			if (request.getParameter("room") != null) {
				String roomName = new String(request.getParameter("room"));				
				if(rooms.roomExists(roomName) == true){
					ChatRoom chatRoom = rooms.getRoom(roomName); 
					chatRoom.cleanMessages();
					response.sendRedirect(request.getContextPath() + "/confirmacao.jsp");
				}else{
					response.sendRedirect(request.getContextPath() + "/erro.jsp");
				}						
			}else{
				response.sendRedirect(request.getContextPath() + "/erro.jsp");
			}
		}catch(Exception exception){
			try {
				response.sendRedirect(contextPath + "/erro.jsp");
			} catch (IOException e) {
				trataExcecao(e);
			}
		}
	}

		
	
	public void chat(HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession(false);
		int situacao = 0;
		
		String roomName = "";
		String roomNameAux = "";
		String roomDescr = "";
		
		if (request.getParameter("cod_sexual") != null) {
			roomName = session.getAttribute("codigo_sexual") + "_" + request.getParameter("cod_sexual");
			roomNameAux = request.getParameter("cod_sexual") + "_" + session.getAttribute("codigo_sexual");
			roomDescr = "Conversa entre " + session.getAttribute("codigo_sexual") + " e " + request.getParameter("cod_sexual");
			
			String nickname1 = (String)session.getAttribute("nickname");
			if(session.getAttribute("situacao") != null)
				situacao = ((Integer)session.getAttribute("situacao")).intValue();
						
			boolean isbloqueadopelo1 = controlador.isBloqueado(((Long)session.getAttribute("codigo_sexual")).longValue(), new Long(request.getParameter("cod_sexual")).longValue());
			boolean isbloqueadopelo2 = controlador.isBloqueado(new Long(request.getParameter("cod_sexual")).longValue(),((Long)session.getAttribute("codigo_sexual")).longValue());
					
			if((isbloqueadopelo1 == false)&&(isbloqueadopelo2 == false)){
				Membro membro = controlador.getMembroMaisSimples(new Long(request.getParameter("cod_sexual")).longValue());	
				if (membro != null) {
					
					long codigo_sexual = ((Long)session.getAttribute("codigo_sexual")).longValue();
					if(codigo_sexual != membro.getCodigo_sexual()){
							
						String nickname2 = membro.getApelido() + "(" + membro.getCodigo_sexual() + ")";
						nickname2 = nickname2.trim();
						String cod2 = String.valueOf(membro.getCodigo_sexual());
		
									
						try
						{
							if (rooms != null){
								if((rooms.roomExists(roomName) == false)&&(rooms.roomExists(roomNameAux) == false)){
																	
									boolean chatterexists = rooms.chatterExists(nickname2);
									int qtd = rooms.getNroRoomsChatter(nickname1);
									
									if (chatterexists){
										
										if((situacao == 2) && (qtd < 2)){
											addNewRoom(rooms, roomName, roomDescr);
											request.setAttribute("room", roomName);
											
											ChatRoom chatRoom = rooms.getRoom(roomName); 
											
											Chatter chatter1 = new Chatter(nickname1, String.valueOf(session.getAttribute("codigo_sexual")), new java.util.Date().getTime());
											chatRoom.addChatter(chatter1);		
											
											Chatter chatter2 = new Chatter(nickname2, cod2, new java.util.Date().getTime());
											chatRoom.addChatter(chatter2);		
											response.sendRedirect(request.getContextPath() + "/chat.jsp?room=" + roomName);
											
										}else if((situacao == 2) && (qtd >= 2)){
											response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
											
										}else{
											addNewRoom(rooms, roomName, roomDescr);
											request.setAttribute("room", roomName);
											
											ChatRoom chatRoom = rooms.getRoom(roomName); 
											
											Chatter chatter1 = new Chatter(nickname1, String.valueOf(session.getAttribute("codigo_sexual")), new java.util.Date().getTime());
											chatRoom.addChatter(chatter1);		
											
											Chatter chatter2 = new Chatter(nickname2, cod2, new java.util.Date().getTime());
											chatRoom.addChatter(chatter2);		
											response.sendRedirect(request.getContextPath() + "/chat.jsp?room=" + roomName);
										}
									}else{
										response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
									}								
									
									
								}else{
									if(rooms.roomExists(roomName) == true){
										boolean chatteronline = rooms.isChatterInRoom(nickname1, "RedeMarqueSexo");
										boolean chatterexists = rooms.isChatterInRoom(nickname1, roomName);
										if ((chatterexists == false) && (chatteronline == true)){
											ChatRoom chatRoom = rooms.getRoom(roomName); 									
											Chatter chatter1 = new Chatter(nickname1, String.valueOf(session.getAttribute("codigo_sexual")), new java.util.Date().getTime());
											chatRoom.addChatter(chatter1);										
										}
										
										chatteronline = rooms.isChatterInRoom(nickname2, "RedeMarqueSexo");
										chatterexists = rooms.isChatterInRoom(nickname2, roomName);
										if ((chatterexists == false) && (chatteronline == true)){
											ChatRoom chatRoom = rooms.getRoom(roomName); 									
											Chatter chatter2 = new Chatter(nickname2, cod2, new java.util.Date().getTime());
											chatRoom.addChatter(chatter2);										
										}

										response.sendRedirect(request.getContextPath() + "/chat.jsp?room=" + roomName);
										
									}else if(rooms.roomExists(roomNameAux) == true){
										boolean chatteronline = rooms.isChatterInRoom(nickname1, "RedeMarqueSexo");
										boolean chatterexists = rooms.isChatterInRoom(nickname1, roomNameAux);
										if ((chatterexists == false) && (chatteronline == true)){
											ChatRoom chatRoom = rooms.getRoom(roomNameAux); 									
											Chatter chatter1 = new Chatter(nickname1, String.valueOf(session.getAttribute("codigo_sexual")), new java.util.Date().getTime());
											chatRoom.addChatter(chatter1);										
										}
										chatterexists = rooms.isChatterInRoom(nickname2, roomNameAux);
										chatteronline = rooms.isChatterInRoom(nickname2, "RedeMarqueSexo");
										if ((chatterexists == false) && (chatteronline == true)){
											ChatRoom chatRoom = rooms.getRoom(roomNameAux); 									
											Chatter chatter2 = new Chatter(nickname2, cod2, new java.util.Date().getTime());
											chatRoom.addChatter(chatter2);										
										}
										response.sendRedirect(request.getContextPath() + "/chat.jsp?room=" + roomNameAux);
										
									}
								}
							}else{
								response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
							}									
							
		
						}
						catch(Exception exception){
							try {
								response.sendRedirect(contextPath + "/erro_chat.jsp");
							} catch (IOException e) {
								trataExcecao(e);
							}
						}
					}else{
						try {
							response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
						} catch (IOException e) {
							trataExcecao(e);
						}
					}
				}
				
			}else{
				try {
					response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
				} catch (IOException e) {
					trataExcecao(e);
				}
			}	
			
		} else{
			if (request.getParameter("room") != null) {
				roomName = new String(request.getParameter("room"));
				try
				{
					if(rooms.roomExists(roomName) == true){						
																	
						if(roomName.equals("SalaSexual") == true){
							String nickname1 = (String)session.getAttribute("nickname");
							if(session.getAttribute("situacao") != null)
								situacao = ((Integer)session.getAttribute("situacao")).intValue();
							
							boolean chatterexists = rooms.isChatterInRoom(nickname1, roomName);
							if ((chatterexists == false)&& (situacao==1)){
								ChatRoom chatRoom = rooms.getRoom(roomName); 									
								Chatter chatter1 = new Chatter(nickname1,String.valueOf(session.getAttribute("codigo_sexual")), new java.util.Date().getTime());
								chatRoom.addChatter(chatter1);										
							}
							response.sendRedirect(request.getContextPath() + "/chat.jsp?room=" + roomName);
							
						}else{
							String[] aux = roomName.split("_");
							String cod_sexual_aux = ((Long)session.getAttribute("codigo_sexual")).toString();
							boolean isbloqueadopelo1 = false;
							boolean isbloqueadopelo2 = false;
							if(cod_sexual_aux.equals(aux[0])){
								isbloqueadopelo1 = controlador.isBloqueado(((Long)session.getAttribute("codigo_sexual")).longValue(),new Long(aux[1]).longValue());
								isbloqueadopelo2 = controlador.isBloqueado(new Long(aux[1]).longValue(),((Long)session.getAttribute("codigo_sexual")).longValue());
							}else{
								isbloqueadopelo1 = controlador.isBloqueado(((Long)session.getAttribute("codigo_sexual")).longValue(),new Long(aux[0]).longValue());
								isbloqueadopelo2 = controlador.isBloqueado(new Long(aux[0]).longValue(),((Long)session.getAttribute("codigo_sexual")).longValue());
							}
							
							if((isbloqueadopelo1 == false)&&(isbloqueadopelo2 == false)){
								response.sendRedirect(request.getContextPath() + "/chat.jsp?room=" + roomName);	
							}else{
								response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
								rooms.removeRoom(roomName);
							}
								
								
		
						}
						
						
					}else{
						response.sendRedirect(request.getContextPath() + "/erro_chat.jsp");
					}	
				
				}catch(Exception exception){
					try {
						response.sendRedirect(contextPath + "/erro_chat.jsp");
					} catch (IOException e) {
						trataExcecao(e);
					}
				}
				
				
			} 

		}
		
		
				
	}
	
	
	
	
	/**
	* Adds a new Room to ChatRoomList object and saves it to chat.properties file if required.
	*/
	public void addNewRoom(ChatRoomList list, String roomName, String roomDescr)
	{
		//String s = getServletContext().getInitParameter("maxNoOfMessages");
		String s = null;
		int maxMessages = 12;
		if (s != null){
			try{
				maxMessages = Integer.parseInt(s);
			}catch (NumberFormatException nfe){
				trataExcecao(nfe);
			}
		}
		ChatRoom room = new ChatRoom(roomName, roomDescr);
		room.setMaximumNoOfMessages(maxMessages);
		rooms.addRoom(room);		
	}

	/** Called when servlet is being destroyed */

	public void destroy()
	{
		System.err.println("Destroying all rooms");
	}
}