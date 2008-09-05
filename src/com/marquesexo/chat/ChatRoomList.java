package com.marquesexo.chat;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;


/**
* This class is used to manipulate and store ChatRoom objects.
* It provides methods to store and retrieve ChatRoom objects
* in this <code>ChatRoomList</code>.
*@author Sukhwinder Singh(ssruprai@hotmail.com)
*/
public class ChatRoomList
{
	/**
	* Stores all the ChatRoom objects
	*/
	private Map roomList;
	/**
	*/
	public ChatRoomList()
	{
		roomList = new HashMap();
	}
	/**
	* adds new chat room object to a list of Rooms.
	* @param room ChatRoom object
	* @return void
	*/
	public synchronized void addRoom(ChatRoom room)
	{
		roomList.put(room.getName(), room);
	}
	
	/**
	* Used to remove a ChatRoom object from the
	* list of ChatRooms.
	* @param name is a String object is the name of the
	* room to be removed from the list of rooms.
	* @return void
	*/
	public synchronized void removeRoom(String name)
	{
		roomList.remove(name);
	}
	
	/** Returns a ChatRoom object
	* @param name is the name of the ChatRoom object to be returned.
	* @return ChatRoom object.
	*/
	public ChatRoom getRoom(String name)
	{
		return (ChatRoom) roomList.get(name);
	}
	/** Finds the room of chatter having this name.
	* @param name is the name of the Chatter object.
	* @return ChatRoom object.
	*/
	public ChatRoom getRoomOfChatter(String name)
	{
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			boolean chatterexists = rooms[i].chatterExists(name);
			if (chatterexists)
			{
				return rooms[i];
			}
		}
		return null;
	}
	/** Returns a Set containing all the ChatRoom objects
	* @return Set
	*/
	
	public Set getRoomList()
	{
		return roomList.entrySet();
	}
	
	/** returns an array containing all ChatRoom objects
	* @return sukhwinder.chat.ChatRoom[]
	*/
	public ChatRoom[] getRoomListArray()
	{
		ChatRoom[] roomListArray = new ChatRoom[roomList.size()];
		Set roomlist = getRoomList();
		Iterator roomlistit = roomlist.iterator();
		int i = 0;
		while(roomlistit.hasNext())
		{
			Map.Entry me = (Map.Entry)roomlistit.next();
			String key = (String) me.getKey();
			roomListArray[i] = (ChatRoom)me.getValue();
			i++;
		}
		return roomListArray;
	}
	
	/**
	* searches each ChatRoom for existance of a chatter.
	* @param nickname Name of the chatter to find.
	* @return boolean
	*/
	public boolean chatterExists(String nickname)
	{
		boolean chatterexists = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			chatterexists = rooms[i].chatterExists(nickname);
			if (chatterexists)
			{
				break;
			}
		}
		return chatterexists;
	}
	
	/**
	* searches each ChatRoom for existance of a chatter.
	* @param nickname Name of the chatter to find.
	* @return boolean
	*/
	public boolean isChatterInRoom(String nickname, String roomname)
	{
		boolean chatterexists = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getName().equals(roomname)){
				chatterexists = rooms[i].chatterExists(nickname);
				break;
			}
		}
		return chatterexists;
	}
	
	/**
	* searches each ChatRoom for existance of a chatter.
	* @param nickname Name of the chatter to find.
	* @return boolean
	*/
	public boolean isRoomEmpty(String roomName)
	{
		boolean roomempty = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getName().equals(roomName)){
				if(rooms[i].getChattersArray().length == 0){
					roomempty = true;
					break;
				}			
			}
		}
		return roomempty;
	}
	
	public boolean isRoomHaveOneChatter(String roomName)
	{
		boolean roomempty = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getName().equals(roomName)){
				if(rooms[i].getChattersArray().length == 1){
					roomempty = true;
					break;
				}			
			}
		}
		return roomempty;
	}
	
	
	public int getNroRoomsChatter(String nickname)
	{
		boolean chatterexists = false;
		int qtd = 0;
		
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			chatterexists = rooms[i].chatterExists(nickname);
			if(chatterexists){
				qtd++;
			}
		}
		return qtd;
	}
	
	public int getNroChattersRoom(String roomName)
	{
		int qtd = 0;
		
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getName().equals(roomName)){
				qtd = rooms[i].getNoOfChatters();
			}
		}
		return qtd;
	}
	
	public int getNroRooms()
	{		
		ChatRoom[] rooms = this.getRoomListArray();		
		return rooms.length;
	}
	
	
	/**
	* searches each ChatRoom for existance of a chatter.
	* @param nickname Name of the chatter to find.
	* @return boolean
	*/
	public String geChattersOfRoom(String roomName)
	{
		String chatters = "";
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getName().equals(roomName)){
				chatters = rooms[i].getChattersString();
			}
		}
		return chatters;
	}
	
	/**
	* searches each ChatRoom for existance of a chatter.
	* @param nickname Name of the chatter to find.
	* @return boolean
	*/
	public String geChattersOfRoom(String roomName, String nickname, int max)
	{
		String chatters = "";
		ChatRoom[] rooms = this.getRoomListArray();
		
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getName().equals(roomName)){
				chatters = rooms[i].getChattersString(nickname,max);
			}
		}
		return chatters;
	}
	
	
	
	/**
	* searches each ChatRoom for existance of a chatter.
	* @param nickname Name of the chatter to find.
	* @return boolean
	*/
	public boolean roomExists(String roomName)
	{
		boolean roomexists = false;
		ChatRoom[] rooms = this.getRoomListArray();
		for (int i = 0; i < rooms.length; i++)
		{
			roomexists = rooms[i].getName().equals(roomName);
			if (roomexists)
			{
				break;
			}
		}
		return roomexists;
	}
	
	public ChatRoom[] getRoomsOfChatter(String nickname)
	{
		ChatRoom[] roomListArray = new ChatRoom[roomList.size()];
		ChatRoom[] rooms = this.getRoomListArray();
		int j = 0;
		for (int i = 0; i < rooms.length; i++)
		{
			boolean chatterexists = rooms[i].chatterExists(nickname);
			if (chatterexists)
			{
				roomListArray[j] = rooms[i];
				j++;
			}
		}
		return roomListArray;
	}
}