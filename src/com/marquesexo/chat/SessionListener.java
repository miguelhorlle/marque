package com.marquesexo.chat;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletContext;

import com.marquesexo.web.CriaRSS;


public class SessionListener implements HttpSessionListener{

	  
	public void sessionCreated(HttpSessionEvent event){   

	}   
	  
	public void sessionDestroyed(HttpSessionEvent event){  

		HttpSession session = event.getSession();
		String nickname = (String) session.getAttribute("nickname");

		//SendMail.sendMail("MarqueSexo - Saiu", "Saiu " + nickname, "rafaelcl@gmail.com");
		
		if (nickname != null)
		{
			ServletContext application = session.getServletContext();
			if (application != null)
			{
				Object o = application.getAttribute("chatroomlist");		
				if (o != null)
				{
					ChatRoomList roomList = (ChatRoomList)o;
					
					ChatRoom[] rooms = roomList.getRoomsOfChatter(nickname);
					for (int i = 0; i < rooms.length; i++)
					{
						ChatRoom room = rooms[i];
						if (room != null)
						{
							Object chatter = room.removeChatter(nickname);	
							if(room.getName().equals("RedeMarqueSexo") == false){
								if(room.getNoOfMessages() != 0){
									room.addMessage(new Message("Sistema", nickname + " saiu da conversa.", new java.util.Date().getTime()));
								}								
							}
							
						}							
					}
					ChatRoom[] roomsaux = roomList.getRoomListArray();
					for (int i = 0; i < roomsaux.length; i++)
					{
						ChatRoom room = rooms[i];
						if (room != null)
						{
							if((room.getName().equals("RedeMarqueSexo") == false)
									&&(room.getName().equals("SalaSexual") == false)){
								if (roomList.isRoomEmpty(room.getName()))
								{
									roomList.removeRoom(room.getName());
								}
							}
						}
						
					}
                    
                    CriaRSS rss = new CriaRSS(roomList);
                    Thread th = new Thread(rss);
                    th.start();
                    th = null;
					
				}
			}
			else
			{
				System.out.println("ServletContext is null");
			}					
		}
		
		session.setAttribute("codigo_sexual", null);
		session.setAttribute("permissao", null);
		session.setAttribute("situacao", null);
		session.setAttribute("nome", null);
		session.setAttribute("apelido", null);
		session.setAttribute("email", null);
		session.setAttribute("nickname", null);
		session.setAttribute("listaMembros",null);
		
	}
	
}