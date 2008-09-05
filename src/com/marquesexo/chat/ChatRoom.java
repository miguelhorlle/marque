package com.marquesexo.chat;

import java.util.Map;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;


/** This class represents a chat room in the Chat System
*@author Sukhwinder Singh(ssruprai@hotmail.com)
*/
public class ChatRoom
{
	/*
	* used to stroe name of the room
	*/
	
	private String name = null;
	/*
	* used to store description of the room
	*/
	private String description = null;
	
	/*
	* Map to store Chatter objects
	*/
	private Map chatters = new HashMap();
	/*
	* Linked list to store Message object
	*/
	private List messages = new LinkedList();
	
	/*
	* Used to set the maximum no of messages
	*/
	private int messages_size = 25;
	/**
	* This constructor takes a name and description
	* to create a new ChatRoom
	* @param name Name of the Room
	* @param descr Description of the Room
	*/
	public ChatRoom(String name, String descr)
	{
		this.name= name;
		this.description = descr;
	}
	
	/**
	* Returns name of the room
	* @return java.lang.String
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* Returns description of the room
	* @return java.lang.String
	*/
	public String getDescription()
	{
		return description;
	}
	
	/**
	* adds a Chatter object to list of Chatters
	* @param chatter Chatter object
	* @return void
	*/
	public synchronized void addChatter(Chatter chatter)
	{
		chatters.put(chatter.getName(), chatter);
	}
	/**
	* removes a Chatter object from list of Chatters
	* @param chatterName name of the chatter.
	* @return void
	*/
	public synchronized Object removeChatter(String chatterName)
	{
		return chatters.remove(chatterName);
	}
	
	/**
	* returns a Chatter object from chatters list.
	* @param chatterName name of the chatter
	* @return sukhwinder.chat.Chatter
	*/
	public Chatter getChatter(String chatterName)
	{
		return (Chatter)chatters.get(chatterName);
	}
	
	/**
	* returns a Chatter object from chatters list.
	* @param chatterName name of the chatter
	* @return sukhwinder.chat.Chatter
	*/
	public Chatter getAnotherChatter(String chatterName)
	{
		Set chatters = getChatters();
		Iterator chattersit = chatters.iterator();
		Chatter aux = null;
		Chatter another = null;
		while(chattersit.hasNext())
		{
			Map.Entry me = (Map.Entry)chattersit.next();
			aux = (Chatter)me.getValue();
			if(aux.getName().equals(chatterName) == false){
				another = aux;
			}
		}
		return another;
	}
	
	/**
	* checks whether a chatter exists or not
	* @param chatterName name of the chatter to check
	* @return boolean
	*/
	
	public boolean chatterExists(String chatterName)
	{
		return chatters.containsKey(chatterName);
	}
	
	/**
	* returns total number of chatters in this room
	* @return int
	*/
	public int getNoOfChatters()
	{
		return chatters.size();
	}
	
	/**
	* returns a Set containing all the Chatters in the room
	* @return java.util.Set
	*/
	public Set getChatters()
	{
		return chatters.entrySet();
	}
	
	/** returns an array containing all Chatter objects
	* @return sukhwinder.chat.Chatter[]
	*/
	public Chatter[] getChattersArray()
	{
		Chatter[] chattersArray = new Chatter[chatters.size()];
		Set chatters = getChatters();
		Iterator chattersit = chatters.iterator();
		int i = 0;
		while(chattersit.hasNext())
		{
			Map.Entry me = (Map.Entry)chattersit.next();
			String key = (String) me.getKey();
			chattersArray[i] = (Chatter)me.getValue();
			i++;
		}
		return chattersArray;
	}
	
	/** returns an array containing all Chatter objects
	* @return sukhwinder.chat.Chatter[]
	*/
	public String getChattersString()
	{
		String chattersstring = "";
		Set chatters = getChatters();
		Iterator chattersit = chatters.iterator();
		while(chattersit.hasNext())
		{
			Map.Entry me = (Map.Entry)chattersit.next();
			String key = (String) me.getKey();
			chattersstring = chattersstring + ((Chatter)me.getValue()).getName() + ", ";
		}
		String aux = chattersstring.substring(0, chattersstring.length()-2);
		return aux;
	}
	
	/** returns an array containing all Chatter objects
	* @return sukhwinder.chat.Chatter[]
	*/
	public String getChattersString(String name, int max)
	{
		String chattersstring = "";
		Set chatters = getChatters();
		Iterator chattersit = chatters.iterator();
		int i = 1;
		while(chattersit.hasNext() && i <= max)
		{
			Map.Entry me = (Map.Entry)chattersit.next();
			String key = (String) me.getKey();
			if(((Chatter)me.getValue()).getName().equalsIgnoreCase(name) == false){
				chattersstring = chattersstring + "<a href=\"/marquesexo/perfil?cod=" +  ((Chatter)me.getValue()).getCod() + "\">" + ((Chatter)me.getValue()).getName() + "</a>, ";
				i++;
			}
			
		}
		String aux = "";
		if(chattersstring.length() > 0){
		    aux = chattersstring.substring(0, chattersstring.length()-2);
		    if(chatters.size() > i){
		    	aux = aux + " entre outros, ";
		    }
		}
		return aux;
	}
	
	/** adds the message to the messages list
	* @param msg A Message Object
	* @return void
	*/
	public synchronized void addMessage(Message msg)
	{
		if(messages.size()==messages_size)
		{
			((LinkedList)messages).removeFirst();
		}
		
		String newmsg = "";
		if(msg.getMessage().contains(":)")){
			newmsg = msg.getMessage().replace(":)", "<img src=\"http://marquesexo.com/images/emoticons/1.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":D")){
			newmsg = msg.getMessage().replace(":D", "<img src=\"http://marquesexo.com/images/emoticons/2.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(";)")){
			newmsg = msg.getMessage().replace(";)", "<img src=\"http://marquesexo.com/images/emoticons/3.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":-O")){
			newmsg = msg.getMessage().replace(":-O", "<img src=\"http://marquesexo.com/images/emoticons/4.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":P")){
			newmsg = msg.getMessage().replace(":P", "<img src=\"http://marquesexo.com/images/emoticons/5.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":S")){
			newmsg = msg.getMessage().replace(":S", "<img src=\"http://marquesexo.com/images/emoticons/6.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":$")){
			newmsg = msg.getMessage().replace(":$", "<img src=\"http://marquesexo.com/images/emoticons/7.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":(")){
			newmsg = msg.getMessage().replace(":(", "<img src=\"http://marquesexo.com/images/emoticons/8.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":|)")){
			newmsg = msg.getMessage().replace(":|", "<img src=\"http://marquesexo.com/images/emoticons/9.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("^o)")){
			newmsg = msg.getMessage().replace("^o)", "<img src=\"http://marquesexo.com/images/emoticons/10.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(A)")){
			newmsg = msg.getMessage().replace("(A)", "<img src=\"http://marquesexo.com/images/emoticons/11.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(B)")){
			newmsg = msg.getMessage().replace("(B)", "<img src=\"http://marquesexo.com/images/emoticons/12.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(C)")){
			newmsg = msg.getMessage().replace("(C)", "<img src=\"http://marquesexo.com/images/emoticons/13.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(D)")){
			newmsg = msg.getMessage().replace("(D)", "<img src=\"http://marquesexo.com/images/emoticons/14.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(E)")){
			newmsg = msg.getMessage().replace("(E)", "<img src=\"http://marquesexo.com/images/emoticons/15.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(F)")){
			newmsg = msg.getMessage().replace("(F)", "<img src=\"http://marquesexo.com/images/emoticons/16.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(G)")){
			newmsg = msg.getMessage().replace("(G)", "<img src=\"http://marquesexo.com/images/emoticons/17.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(H)")){
			newmsg = msg.getMessage().replace("(H)", "<img src=\"http://marquesexo.com/images/emoticons/18.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(I)")){
			newmsg = msg.getMessage().replace("(I)", "<img src=\"http://marquesexo.com/images/emoticons/19.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(J)")){
			newmsg = msg.getMessage().replace("(J)", "<img src=\"http://marquesexo.com/images/emoticons/20.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(K)")){
			newmsg = msg.getMessage().replace("(K)", "<img src=\"http://marquesexo.com/images/emoticons/21.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(L)")){
			newmsg = msg.getMessage().replace("(L)", "<img src=\"http://marquesexo.com/images/emoticons/23.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(M)")){
			newmsg = msg.getMessage().replace("(M)", "<img src=\"http://marquesexo.com/images/emoticons/24.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(N)")){
			newmsg = msg.getMessage().replace("(N)", "<img src=\"http://marquesexo.com/images/emoticons/25.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(O)")){
			newmsg = msg.getMessage().replace("(O)", "<img src=\"http://marquesexo.com/images/emoticons/26.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(P)")){
			newmsg = msg.getMessage().replace("(P)", "<img src=\"http://marquesexo.com/images/emoticons/27.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(Q)")){
			newmsg = msg.getMessage().replace("(Q)", "<img src=\"http://marquesexo.com/images/emoticons/28.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(R)")){
			newmsg = msg.getMessage().replace("(R)", "<img src=\"http://marquesexo.com/images/emoticons/29.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(S)")){
			newmsg = msg.getMessage().replace("(S)", "<img src=\"http://marquesexo.com/images/emoticons/30.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(T)")){
			newmsg = msg.getMessage().replace("(T)", "<img src=\"http://marquesexo.com/images/emoticons/31.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(U)")){
			newmsg = msg.getMessage().replace("(U)", "<img src=\"http://marquesexo.com/images/emoticons/32.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(V)")){
			newmsg = msg.getMessage().replace("(V)", "<img src=\"http://marquesexo.com/images/emoticons/33.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(W)")){
			newmsg = msg.getMessage().replace("(W)", "<img src=\"http://marquesexo.com/images/emoticons/34.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(X)")){
			newmsg = msg.getMessage().replace("(X)", "<img src=\"http://marquesexo.com/images/emoticons/35.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(Y)")){
			newmsg = msg.getMessage().replace("(Y)", "<img src=\"http://marquesexo.com/images/emoticons/36.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(Z)")){
			newmsg = msg.getMessage().replace("(Z)", "<img src=\"http://marquesexo.com/images/emoticons/37.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(!)")){
			newmsg = msg.getMessage().replace("(!)", "<img src=\"http://marquesexo.com/images/emoticons/38.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(@)")){
			newmsg = msg.getMessage().replace("(@)", "<img src=\"http://marquesexo.com/images/emoticons/39.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(#)")){
			newmsg = msg.getMessage().replace("(#)", "<img src=\"http://marquesexo.com/images/emoticons/40.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("($)")){
			newmsg = msg.getMessage().replace("($)", "<img src=\"http://marquesexo.com/images/emoticons/41.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(%)")){
			newmsg = msg.getMessage().replace("(%)", "<img src=\"http://marquesexo.com/images/emoticons/42.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(^)")){
			newmsg = msg.getMessage().replace("(^)", "<img src=\"http://marquesexo.com/images/emoticons/43.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(&)")){
			newmsg = msg.getMessage().replace("(&)", "<img src=\"http://marquesexo.com/images/emoticons/44.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(*)")){
			newmsg = msg.getMessage().replace("(*)", "<img src=\"http://marquesexo.com/images/emoticons/45.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(?)")){
			newmsg = msg.getMessage().replace("(?)", "<img src=\"http://marquesexo.com/images/emoticons/46.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(|)")){
			newmsg = msg.getMessage().replace("(|)", "<img src=\"http://marquesexo.com/images/emoticons/47.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(+)")){
			newmsg = msg.getMessage().replace("(+)", "<img src=\"http://marquesexo.com/images/emoticons/48.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(-)")){
			newmsg = msg.getMessage().replace("(-)", "<img src=\"http://marquesexo.com/images/emoticons/49.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains("(=)")){
			newmsg = msg.getMessage().replace("(=)", "<img src=\"http://marquesexo.com/images/emoticons/50.gif\"/>");
			msg.setMessage(newmsg);			
		}
		if(msg.getMessage().contains(":@")){
			newmsg = msg.getMessage().replace(":@", "<img src=\"http://marquesexo.com/images/emoticons/51.gif\"/>");
			msg.setMessage(newmsg);			
		}
		messages.add(msg);
	}
	
	/**
	* returns a ListIterator object containing all the messages
	* @return java.util.ListIterator
	*/	
	public ListIterator getMessages()
	{
		return messages.listIterator();
	}

	/**
	* returns an array of messages sent after given time
	* @param afterTimeStamp Time in milliseconds.
	* @return array
	*/	
	public Message[] getMessages(long afterTimeStamp)
	{
		ListIterator li = messages.listIterator();
		List temp = new ArrayList();
		Message m;
		while (li.hasNext())
		{
			m = (Message)li.next();
			if (m.getTimeStamp() >= afterTimeStamp)
			{
				temp.add(m);
			}
		}
		Object o[] = temp.toArray();
		Message[] arr = new Message[o.length];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = (Message)o[i];
		}
		return arr;
	}
	
	/**
	*
	* @return long
	*/	
	public long getDifferenceTimeLastMessage(long afterTimeStamp)
	{
		ListIterator li = messages.listIterator();
		long last = 0;
		Message m;
		while (li.hasNext())
		{
			m = (Message)li.next();
			if (m.getTimeStamp() > last)
			{
				last = m.getTimeStamp();
			}
		}		
		return afterTimeStamp - last;
	}
	
	/**
	* 
	* @return long
	*/	
	public long getDifferenceTimeLastMessageNotMine(long afterTimeStamp, String nickname)
	{
		ListIterator li = messages.listIterator();
		long last = 0;
		Message m;
		while (li.hasNext())
		{
			m = (Message)li.next();
			if((m.getTimeStamp() > last)&&(m.getChatterName().equals(nickname) == false))
			{
				last = m.getTimeStamp();
			}
		}		
		return afterTimeStamp - last;
	}
	
	/**
	* 
	* @return long
	*/	
	public long getTimeFirstMessage()
	{
		ListIterator li = messages.listIterator();
		long first = 999999999;
		Message m;
		while (li.hasNext())
		{
			m = (Message)li.next();
			if((m.getTimeStamp() < first))
			{
				first = m.getTimeStamp();
			}
		}		
		return first;
	}

	/**
	* returns total number of messages in the messages List
	* @return int
	*/
	public int getNoOfMessages()
	{
		return messages.size();
	}
	
	/**
	* sets maxmium number of messages to this number.
	* @param size the maximum no of messages to hold at a time.
	* @return void
	*/
	public void setMaximumNoOfMessages(int size)
	{
		messages_size = size;
	}
	
	/**
	* remove all messages
	* @return int
	*/
	public void cleanMessages()
	{
		messages.clear();
	}
	
	/**
	* returns maxmium number of messages set.
	* @return int
	*/
	public int getMaxiumNoOfMessages()
	{
		return messages_size;
	}
}