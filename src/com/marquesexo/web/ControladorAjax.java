package com.marquesexo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.directwebremoting.WebContextFactory;

import com.marquesexo.chat.ChatRoom;
import com.marquesexo.chat.ChatRoomList;
import com.marquesexo.control.MarqueSexoControl;


public class ControladorAjax{

	private static final long serialVersionUID = 1L;

	public String carregaPagina(String url) throws ServletException, IOException{  
		 return WebContextFactory.get().forwardToString(url);  
	}  
	
	public List checkMessages(String nickname, String codigo_sexual){
		
		List<String> result = new ArrayList<String>();
		
		ChatRoomList roomList = null;
		ChatRoom chatRoom = null;
		String roomName = "";
	
		if(nickname != null)
		{
			try{
				roomList = ManageChatServlet.rooms;

				ChatRoom[] rooms = roomList.getRoomsOfChatter(nickname);
				long differencetime = 0;
				
				for (int i = 0; i < rooms.length; i++)
				{
					chatRoom = (ChatRoom)rooms[i];		
					if(chatRoom != null){
						roomName = chatRoom.getName();
						differencetime = chatRoom.getDifferenceTimeLastMessageNotMine(new java.util.Date().getTime(), nickname);				
						
						if(differencetime < 4500)					
						{
							String[] aux = roomName.split("_");
							String nickname_membro = "";
							if(aux.length == 1){
								result.add(roomName);
								result.add("chat?room="+ roomName);
								result.add(roomName);																	
							}else{
	
								String roomNameAux1 = codigo_sexual + "_" + aux[1];
								String roomNameAux2 =  aux[0] + "_" + codigo_sexual;
								String roomNameAux3 = codigo_sexual + "_" + aux[0];
								MarqueSexoControl marque = ManageChatServlet.controlador;
								
								String nomeMembro= "";
								
								if(roomName.equals(roomNameAux1) == true){
									nomeMembro = marque.getApelidoMembro(new Long(aux[1]).longValue());
									nickname_membro = nomeMembro + "(" + aux[1] + ")";
									
									result.add(roomName);
									result.add("chat?room="+ roomName);
									result.add(nickname_membro);		
								
								}else if(roomName.equals(roomNameAux2) == true){
									nomeMembro = marque.getApelidoMembro(new Long(aux[0]).longValue());
									nickname_membro = nomeMembro + "(" + aux[0] + ")";
									
									result.add(roomNameAux3);
									result.add("chat?room="+ roomName);
									result.add(nickname_membro);	
				
								}
							}
						
						}
					}
				
				}		
	
			}catch(Exception e){
				e.printStackTrace();				
			}
		}
		return result;
	}	

}
