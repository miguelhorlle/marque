package com.marquesexo.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.marquesexo.dao.FotosDAO;

public class FotoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private FotosDAO fotosDAO;
	
	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro , "rafaelcl@gmail.com");
	}
	
	
	public void init() throws ServletException {
		fotosDAO = new FotosDAO();
	}
	
	/**
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response){

		try{
			HttpSession session = request.getSession(false);
			
			if(session != null){
				if(session.getAttribute("codigo_sexual") != null){
					if (request.getRequestURI().endsWith("/foto")) {
						foto(request, response);
					}
				}
			}
			
								
		
		} catch (Exception e) {
			trataExcecao(e);
		}
		
	}
				
	
	public void foto(HttpServletRequest request,HttpServletResponse response)throws ServletException {
		
		long codigo_sexual_foto = -1;
		if(request.getParameter("codigo_sexual_foto") != null){
			codigo_sexual_foto = (new Long(request.getParameter("codigo_sexual_foto"))).longValue();
		}
		
		int nro_foto = -1;
		if(request.getParameter("nro_foto") != null){
			nro_foto = (new Integer(request.getParameter("nro_foto"))).intValue();
		}
		
		try{
			
			InputStream in = null;
			if(nro_foto == 0){
				in = fotosDAO.getFotoPrincipal(codigo_sexual_foto);
			} else if(nro_foto > 0){
				in = fotosDAO.getFoto(nro_foto, codigo_sexual_foto);
			}
			
			if(in != null){		
				int fileSize = in.available();
				if(fileSize < 1){   
					try {
						//String realPath = this.getServletConfig().getServletContext().getRealPath("/" )+ "design" +System.getProperty("file.separator")+ "tmp_photo.gif";
						//in = new FileInputStream(realPath);
						java.net.URL url = new java.net.URL("http://marquesexo.com/marquesexo/img/branco.png");            
						URLConnection conn = url.openConnection();
						in = conn.getInputStream();
					} catch (Exception e) {

					}
				}
				 
				if(in != null){	
					// Output the blob to the HttpServletResponse
					response.setContentType("image/jpg");
					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
					byte by[] = new byte[32768];
					int index = in.read(by, 0, 32768);
					while (index != -1) {
						out.write(by, 0, index);
						index = in.read(by, 0, 32768);
					}
					out.flush();
				}
				
			}else{
				
				try {
					//String realPath = this.getServletConfig().getServletContext().getRealPath("/" )+ "agencia" +System.getProperty("file.separator")+ "design" +System.getProperty("file.separator")+ "tmp_photo.gif";
					//in = new FileInputStream(realPath);
					java.net.URL url = new java.net.URL("http://marquesexo.com/marquesexo/img/branco.png");          
					URLConnection conn = url.openConnection();
					in = conn.getInputStream();
				} catch (Exception e) {

				}
				
				if(in != null){	
					// Output the blob to the HttpServletResponse
					response.setContentType("image/jpg");
					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
					byte by[] = new byte[32768];
					int index = in.read(by, 0, 32768);
					while (index != -1) {
						out.write(by, 0, index);
						index = in.read(by, 0, 32768);
					}
					out.flush();
				}
				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
	
	}
	

}
