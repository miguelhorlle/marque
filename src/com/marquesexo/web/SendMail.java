package com.marquesexo.web;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	private static String server = "smtp.gmail.com";
    private static String user = "";
    private static String password = "";
    private static String from = "marquesexo@gmail.com";
    
	/**
	 * Este método envia a mensagem para um e-mail
	 * @return boolean
	 */
	public static boolean sendMail(String subject, String message, String to){

		String mailServer = server;

		Properties props = System.getProperties();

		try {
			
			props.put("mail.smtp.quitwait", "false" );
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtp.host", mailServer);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.mime.charset", "ISO-8859-1");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			
			

			Session session = Session.getDefaultInstance(props);// recebe props

			InternetAddress destinatario = new InternetAddress(to);
			InternetAddress remetente = new InternetAddress(from);

			Message msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			msg.setFrom(remetente);
			msg.setRecipient(Message.RecipientType.TO, destinatario);
			msg.setSubject(subject);
			msg.setContent(message.toString(), "text/plain");

			Transport transport = session.getTransport("smtp");
			transport.connect(mailServer, user, password);
			msg.saveChanges();
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			System.out.println("Error " + mex);
			return false;
		}

	}
    
    
    /**
     * Este método envia a mensagem para uma lista de e-mail
     * @return boolean
     */
    public static boolean sendMail(String subject, String message, String[] to){

        String mailServer = server;

        Properties props = System.getProperties();

        try {
            
            props.put("mail.smtp.quitwait", "false" );
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtp.host", mailServer);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.debug", "true");
            props.put("mail.mime.charset", "ISO-8859-1");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            
            

            Session session = Session.getDefaultInstance(props);// recebe props
            
            InternetAddress[] destinatario = new InternetAddress[to.length];

            for (int i=0; i<to.length;i++){
            destinatario[i] = new InternetAddress(to[i]);
            }

            InternetAddress remetente = new InternetAddress(from);

            Message msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            msg.setFrom(remetente);
            msg.setRecipients(Message.RecipientType.TO, destinatario);
            msg.setSubject(subject);
            msg.setContent(message.toString(), "text/plain");

            Transport transport = session.getTransport("smtp");
            transport.connect(mailServer, user, password);
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception mex) {
            System.out.println("Error " + mex);
            return false;
        }

    }
    
    
    
    /**
     * Este método envia a mensagem com anexo para um e-mail
     * @return boolean
     */
    public static boolean sendMail(String subject, String message, String to, File file){

        String mailServer = server;

        Properties props = System.getProperties();

        try {
            
            props.put("mail.smtp.quitwait", "false" );
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtp.host", mailServer);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.debug", "true");
            props.put("mail.mime.charset", "ISO-8859-1");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
                      

            Session session = Session.getDefaultInstance(props);// recebe props

            InternetAddress destinatario = new InternetAddress(to);
            InternetAddress remetente = new InternetAddress(from);

            Message msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            msg.setFrom(remetente);
            msg.setRecipient(Message.RecipientType.TO, destinatario);
            msg.setSubject(subject);
            // cria a primeira parte da mensagem
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(message);

            // cria a segunda parte da mensage
            MimeBodyPart mbp2 = new MimeBodyPart();

            // anexa o arquivo na mensagem
            FileDataSource fds = new FileDataSource(file);
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());

            // cria a Multipart
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);

            // adiciona a Multipart na mensagem
            msg.setContent(mp);
           
            Transport transport = session.getTransport("smtp");
            transport.connect(mailServer, user, password);
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();           
            return true;
            
        } catch (Exception mex) {
            System.out.println("Error " + mex);
            return false;
        }

    }

	
}
