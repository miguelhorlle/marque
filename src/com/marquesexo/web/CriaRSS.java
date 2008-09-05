package com.marquesexo.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.marquesexo.chat.ChatRoom;
import com.marquesexo.chat.ChatRoomList;
import com.marquesexo.chat.Chatter;

import de.nava.informa.core.ChannelExporterIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.exporters.RSS_2_0_Exporter;
import de.nava.informa.impl.basic.ChannelBuilder;


public class CriaRSS implements Runnable {

	private ChatRoomList roomList;

	public CriaRSS(ChatRoomList roomlist) {
		roomList = roomlist;
	}

	public void criarRssNroOnline() {

		ChannelBuilder builder = new ChannelBuilder();
        ChannelIF channel = builder.createChannel("Número de Usuários Online");
        channel.setDescription("");
        channel.setCreator("MarqueSexo");
        channel.setLanguage("pt-br");
        channel.setPubDate(new Date());
        channel.setCopyright("© MarqueSexo 2008");
        
        try {
            
        	int nro = roomList.getNroChattersRoom("RedeMarqueSexo");
        	
            String titulo = "";
            if (nro == 0) {
                titulo = "Ninguém Online";
            } else if (nro == 1) {
    			titulo = "Há 1 usuário Online";
    		} else {
    			titulo = "Há " + nro + " usuários Online";
    		}
            
            ItemIF item1 = builder.createItem(channel, titulo, "", new URL("http://marquesexo.com"));
            channel.addItem(item1);   
       
            ChannelExporterIF exporter;            
            
            StringWriter w = new StringWriter();

            exporter = new RSS_2_0_Exporter(w, "iso-8859-1");
            exporter.write(channel);            
            InputStream inputStream = new ByteArrayInputStream(w.toString().getBytes());
            enviarArquivo("feed_nro_online.xml", inputStream);
            inputStream.close();
           
            
        } catch (Exception e) {
        	trataExcecao(e);
        }
		
	}

	public void criarRssUsuariosOnline() {

		ChannelBuilder builder = new ChannelBuilder();
        ChannelIF channel = builder.createChannel("Usuários Online no MarqueSexo");
        channel.setDescription("");
        channel.setCreator("MarqueSexo");
        channel.setLanguage("pt-br");
        channel.setPubDate(new Date());
        channel.setCopyright("© MarqueSexo 2008");
        
        try {
            
            int nro = 1;
            String titulo = "";
            
            if (nro == 0) {
                
                titulo = "Ninguém Online";
                ItemIF item1 = builder.createItem(channel, titulo, "", new URL("http://marquesexo.com"));

                channel.addItem(item1);   
                
            } else {

            	ChatRoom chatRoom = roomList.getRoom("RedeMarqueSexo");
    			Chatter[] chatters = chatRoom.getChattersArray();
                ItemIF item1 = null;
                
                for(Chatter c: chatters){
                    titulo = c.getName();
                    item1 = builder.createItem(channel, titulo,"", new URL("http://marquesexo.com"));
                    channel.addItem(item1);   
                }
                
            }

            ChannelExporterIF exporter;
                        
            StringWriter w = new StringWriter();            
            exporter = new RSS_2_0_Exporter(w, "iso-8859-1");
            exporter.write(channel);            
            InputStream inputStream = new ByteArrayInputStream(w.toString().getBytes());  

            enviarArquivo("feed_online.xml", inputStream);
            inputStream.close();
            
        } catch (Exception e) {
        	trataExcecao(e);
        }
	}

	public void enviarArquivo(String nomeArquivo, InputStream arquivo) {

		try {

			FTPClient ftp = new FTPClient();
			ftp.connect("ftp.marquesexo.com");

			// verifica se conectou com sucesso!
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.login("marque", "marque");

				ftp.changeWorkingDirectory("/httpdocs");

				// ajusta o tipo do arquivo a ser enviado
				ftp.setFileType(FTPClient.ASCII_FILE_TYPE);
				//ftp.setControlEncoding("utf-8");

				// faz o envio do arquivo
				ftp.storeFile(nomeArquivo, arquivo);
				ftp.disconnect();

			} else {
				// erro ao se conectar
				ftp.disconnect();
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
	}

	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro, "rafaelcl@gmail.com");
	}

	public void run() {
		criarRssNroOnline();
		criarRssUsuariosOnline();

	}

}
