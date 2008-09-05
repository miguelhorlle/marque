package com.marquesexo.web;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.marquesexo.bean.Bloqueado;
import com.marquesexo.bean.ComentarioSuruba;
import com.marquesexo.bean.Enquete;
import com.marquesexo.bean.Fotos;
import com.marquesexo.bean.Interessante;
import com.marquesexo.bean.Membro;
import com.marquesexo.bean.Motel;
import com.marquesexo.bean.ParticipacaoSuruba;
import com.marquesexo.bean.Resposta;
import com.marquesexo.bean.ResultadoEnquete;
import com.marquesexo.bean.Sexo;
import com.marquesexo.bean.Suruba;
import com.marquesexo.bean.Voto;
import com.marquesexo.bean.Webcam;
import com.marquesexo.chat.ChatRoom;
import com.marquesexo.chat.ChatRoomList;
import com.marquesexo.chat.Chatter;
import com.marquesexo.control.MarqueSexoControl;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class MarqueSexoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private MarqueSexoControl controlador;
	private int promocao;
	private String [] senhas = {"querosexo", "safada", "adorosexo", "sacanagem", "querotransar", "sexoselvagem"};
	
	public MarqueSexoServlet(){
		super();
	}
	
	public void init() throws ServletException {
		controlador = new MarqueSexoControl();		
		promocao = ((Integer)getServletContext().getAttribute("promocao")).intValue();
		System.out.println("Sistema no ar.");
		getServletContext().setAttribute("controlador", controlador);
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
			String jsp = null;		
			HttpSession session = request.getSession(false);

			if(request.getRequestURI().endsWith("/")) {
				jsp = main(request);
			}else if(request.getRequestURI().endsWith("/login")) {
				jsp = login(request);
			}else if (request.getRequestURI().endsWith("/enviarcadastro")) {
				jsp = enviarcadastro(request);
			}else if (request.getRequestURI().endsWith("/enviarfaleconosco")) {
				jsp = enviarfaleconosco(request);
			}else if (request.getRequestURI().endsWith("/enviaresqueciminhasenha")) {
				jsp = enviaresqueciminhasenha(request);
			}else if (request.getRequestURI().endsWith("/moteis")) {
				jsp = moteis(request);
			}else if (request.getRequestURI().endsWith("/onde")) {
				jsp = onde(request);
			}else{
				if(session != null){
					Object codigo = session.getAttribute("codigo_sexual");
					if(codigo != null){
						long codigo_sexual = ((Long)codigo).longValue();
						
						if (request.getRequestURI().endsWith("/home")) {
							jsp = home(request, (String)session.getAttribute("nickname"));						
						}else if (request.getRequestURI().endsWith("/enviarcadastromotel")) {
							jsp = enviarcadastromotel(request);
						}else if (request.getRequestURI().endsWith("/resultadobusca")) {
							jsp = resultadobusca(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarfaleconosco")) {
							jsp = enviarfaleconosco(request);
						}else if (request.getRequestURI().endsWith("/sala")) {
							int situacao = ((Integer)session.getAttribute("situacao")).intValue();
							jsp = sala(request, situacao,codigo_sexual);
						}else if (request.getRequestURI().endsWith("/bloqueados")) {
							jsp = bloqueados(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/interessantes")) {
							jsp = interessantes(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/perfil")) {
							jsp = perfil(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/perfilsimples")) {
							jsp = perfilsimples(request);
						}else if (request.getRequestURI().endsWith("/fotos")) {
							jsp = fotos(request);
						}else if (request.getRequestURI().endsWith("/editarfotos")) {
							jsp = editarfotos(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/editar")) {
							jsp = editar(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviareditar")) {
							jsp = enviareditar(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/editarm")) {
							jsp = editarm(request);
						}else if (request.getRequestURI().endsWith("/enviareditarm")) {
							jsp = enviareditarm(request);
						}else if (request.getRequestURI().endsWith("/editarmotel")) {
							jsp = editarmotel(request);
						}else if (request.getRequestURI().endsWith("/enviareditarmotel")) {
							jsp = enviareditarmotel(request);
						}else if (request.getRequestURI().endsWith("/atualizarfoto")) {
							jsp = atualizarfoto(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/brancofoto")) {
							jsp = brancofoto(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/excluirmotel")) {
							jsp = excluirmotel(request);
						}else if (request.getRequestURI().endsWith("/excluirm")) {
							jsp = excluirm(request);
						}else if (request.getRequestURI().endsWith("/adicionarinteressante")) {
							jsp = adicionarinteressante(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/adicionarbloqueado")) {
							jsp = adicionarbloqueado(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/excluirinteressante")) {
							jsp = excluirinteressante(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/excluirbloqueado")) {
							jsp = excluirbloqueado(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/mensagem")) {
							jsp = mensagem(request);
						}else if (request.getRequestURI().endsWith("/enviarmensagem")) {
							jsp = enviarmensagem(request, ((String)session.getAttribute("nome")), codigo_sexual);
						}else if (request.getRequestURI().endsWith("/cancelarconta")) {
							jsp = cancelarconta(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/atualizarwebcam")) {
							jsp = atualizarwebcam(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/habilitarwebcam")) {
							jsp = habilitarwebcam(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/desabilitarwebcam")) {
							jsp = desabilitarwebcam(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/configurarwebcam")) {
							jsp = configurarwebcam(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviararquivo")) {
							jsp = enviararquivo(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarlink")) {
							jsp = enviarlink(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/japeguei")) {
							jsp = japeguei(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/verjapegou")) {
							jsp = verjapegou(request);
						}else if (request.getRequestURI().endsWith("/verificarjapeguei")) {
							jsp = verificarjapeguei(request);
						}else if (request.getRequestURI().endsWith("/enviarjapeguei")) {
							jsp = enviarjapeguei(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarcomentariojapeguei")) {
							jsp = enviarcomentariojapeguei(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/confirmarjapegou")) {
							jsp = confirmarjapegou(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/desmentirjapegou")) {
							jsp = desmentirjapegou(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/definirparticipantes")) {
							jsp = definirparticipantes(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/definirquemparticipou")) {
							jsp = definirquemparticipou(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/minhassurubas")) {
							jsp = minhassurubas(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/resultadobuscasurubasdisponiveis")) {
							jsp = resultadobuscasurubasdisponiveis(request);
						}else if (request.getRequestURI().endsWith("/resultadobuscasurubasfinalizadas")) {
							jsp = resultadobuscasurubasfinalizadas(request);
						}else if (request.getRequestURI().endsWith("/versuruba")) {
							jsp = versuruba(request);
						}else if (request.getRequestURI().endsWith("/editarsuruba")) {
							jsp = editarsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviareditarsuruba")) {
							jsp = enviareditarsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/organizarsuruba")) {
							jsp = organizarsuruba(request);
						}else if (request.getRequestURI().endsWith("/enviarorganizarsuruba")) {
							jsp = enviarorganizarsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/participarsuruba")) {
							jsp = participarsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarconfirmarparticipacao")) {
							jsp = enviarconfirmarparticipacao(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/comentarsuruba")) {
							jsp = comentarsuruba(request);
						}else if (request.getRequestURI().endsWith("/enviarcomentariosuruba")) {
							jsp = enviarcomentariosuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviardesistirsuruba")) {
							jsp = enviardesistirsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviaraceitarparticipantesuruba")) {
							jsp = enviaraceitarparticipantesuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarrecusarparticipantesuruba")) {
							jsp = enviarrecusarparticipantesuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarparticipantefoisuruba")) {
							jsp = enviarparticipantefoisuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarparticipantenaofoisuruba")) {
							jsp = enviarparticipantenaofoisuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/cancelarsuruba")) {
							jsp = cancelarsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/enviarcancelarsuruba")) {
							jsp = enviarcancelarsuruba(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/surubasparticipou")) {
							jsp = surubasparticipou(request);
						}else if (request.getRequestURI().endsWith("/surubasquisparticipar")) {
							jsp = surubasquisparticipar(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/surubasconfirmadas")) {
							jsp = surubasconfirmadas(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/verresultadoenquete")) {
							jsp = verresultadoenquete(request);
						}else if (request.getRequestURI().endsWith("/verenquetes")) {
							jsp = verenquetes(request);
						}else if (request.getRequestURI().endsWith("/enviarvotoenquete")) {
							jsp = enviarvotoenquete(request);
						}else if (request.getRequestURI().endsWith("/votarenquete")) {
							jsp = votarenquete(request);
						}else if (request.getRequestURI().endsWith("/cancelarconta")) {
							jsp = cancelarconta(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/online")) {
							jsp = online(request, codigo_sexual);
						}else if (request.getRequestURI().endsWith("/sair")) {
							jsp = sair(request);
						}else{
							jsp = "erro_permissao.jsp";
						}
					}else{						
						jsp = "erro_permissao.jsp";
					}
				}else{					
					jsp = main(request);
				}
			}
			
			if (jsp != null) {
				request.getRequestDispatcher(jsp).forward(request, response);
				//response.sendRedirect(contextPath + "/" + jsp);	
			}		
						
			/*
			response.setHeader("Cache-Control", "no-store, no-cache");
        	response.setHeader("Pragma", "no-cache");
        	response.setDateHeader("Expires", 0);
			 */

			response.flushBuffer();
		
		} catch (Exception e) {
			trataExcecao(e);
			try {
				request.getRequestDispatcher("erro.jsp").forward(request, response);
				response.flushBuffer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			} 

		}		
	}
	
	private boolean isNumber(String num){
		boolean result = false;
		try{
			Float.parseFloat(num);
			result = true;
		} catch (Exception e) {
			result = false;
		} 
		return result;
	}
	
	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro , "rafaelcl@gmail.com");
	}
	
	/**
	 * Prepara um documento JSP que exibe a página de login
	 * do sistema
	 * @return o endereço do documento JSP
	 */
	private String main(HttpServletRequest request)
			throws ServletException {
		
		String url = "main.jsp";
		return url;
		
	}
	
	public String login(HttpServletRequest request)throws ServletException {
		
        
		String email = "";
		if (request.getParameter("email_login") != null) {
			email = new String(request.getParameter("email_login"));
		} else if(request.getAttribute("email_login") != null){
			email = ((String) request.getAttribute("email_login"));
		}
		
		String senha = "";
		if (request.getParameter("senha_login") != null) {
			senha = new String(request.getParameter("senha_login"));
		} else if(request.getAttribute("senha_login") != null){
			senha = ((String) request.getAttribute("senha_login"));
		}
		
		String url = "";
		
		if((email.equals(""))||(senha.equals(""))){
			url = "erro_invalido.jsp";
		}else{
						
			Membro membro = null;
			membro = controlador.autenticarMembro(email, senha);
	
			if(membro != null){
				
				/*Timestamp data_atual = new Timestamp(new Date().getTime());
				Timestamp data_fim = membro.getFim();
				if(data_atual.after(data_fim)){
					SendMail.sendMail("MarqueSexo - Conta Expirou", 
							"A conta de " + membro.getNome() + "(" + membro.getCodigo_sexual() + ")" 
							+ " expirou! \nSua situação atual é: " + membro.getSituacao(), 
							"rafaelcl@gmail.com");
				}*/		
				
				
				String nickname = membro.getApelido() + "(" + membro.getCodigo_sexual() + ")";
				nickname = nickname.trim();
							
				try
				{
					ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
					boolean chatterexists = roomlist.chatterExists(nickname);
					if (chatterexists){
						url = "erro_online.jsp";
					}else{
						
						HttpSession session = request.getSession(true);
						int timeout = 300; // 5 minutes
						String t = getServletContext().getInitParameter("sessionTimeout"); // gets Minutes
						if (t != null){
							try{
								timeout = Integer.parseInt(t);
								timeout = timeout * 60;
							}
							catch (NumberFormatException nfe){			
								trataExcecao(nfe);
							}
						}
	
						session.setMaxInactiveInterval(timeout);
						session.setAttribute("codigo_sexual", membro.getCodigo_sexual());
						session.setAttribute("permissao", membro.getPermissao());
						session.setAttribute("situacao", membro.getSituacao());
						session.setAttribute("nome", membro.getNome());
						session.setAttribute("apelido", membro.getApelido());
						session.setAttribute("email", membro.getEmail());
						session.setAttribute("discreto", membro.getDiscreto());
						
						if(membro.getSexo() == 0)
							session.setAttribute("sexo", "Feminino");
						else if(membro.getSexo() == 1)
							session.setAttribute("sexo", "Masculino");
						else if(membro.getSexo() == 2)
							session.setAttribute("sexo", "Transexual");			
						
						String cod = String.valueOf(membro.getCodigo_sexual());
						session.setAttribute("nickname", nickname);
										
						ChatRoom chatRoom = roomlist.getRoom("RedeMarqueSexo"); 
						Chatter chatter = null;
						
						chatter = new Chatter(nickname, cod, new java.util.Date().getTime());
						chatRoom.addChatter(chatter);
						
						int nro_online = roomlist.getNroChattersRoom("RedeMarqueSexo");
						String chatters = roomlist.geChattersOfRoom("RedeMarqueSexo",nickname,6);
						request.setAttribute("nro_online",nro_online-1);
						request.setAttribute("chatters",chatters);
	
						CriaRSS rss = new CriaRSS(roomlist);
				        Thread th = new Thread(rss);
				        th.start();
				        th = null;
						
				        url = "home.jsp";
					}
				}
				catch(Exception e){
					trataExcecao(e);
					url = "erro.jsp";
				}
				
	
			}else
				url = "erro_login.jsp";
		}
		
		return url;
		
	}
	
	
	public String home(HttpServletRequest request, String nickname)throws ServletException {
		
        String url = "";		
		ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
		
		int nro_online = roomlist.getNroChattersRoom("RedeMarqueSexo");
		//String chatters = roomlist.geChattersOfRoom("RedeMarqueSexo",nickname,6);
		request.setAttribute("nro_online",nro_online-1);
		//request.setAttribute("chatters",chatters);

        url = "home.jsp";        

		return url;
		
	}
	
	
	public String enviarcadastro(HttpServletRequest request)throws ServletException {
		

		String email = "";
		if (request.getParameter("email") != null) {
			email = new String(request.getParameter("email"));
		} else if(request.getAttribute("email") != null){
			email = ((String) request.getAttribute("email"));
		}		
		
		String senha = senhas[(int)(Math.random()*6)];
		
		String nome = "";
		if (request.getParameter("nome") != null) {
			nome = new String(request.getParameter("nome"));
		} else if(request.getAttribute("nome") != null){
			nome = ((String) request.getAttribute("nome"));
		}
		
		String apelido = "";
		if (request.getParameter("apelido") != null) {
			apelido = new String(request.getParameter("apelido"));
		} else if(request.getAttribute("apelido") != null){
			apelido = ((String) request.getAttribute("apelido"));
		}
		
		String texto = "";
		if (request.getParameter("texto") != null) {
			texto = new String(request.getParameter("texto"));
		} else if(request.getAttribute("texto") != null){
			texto = ((String) request.getAttribute("texto"));
		}
		
		int faz_sexo_com = 0;
		if (request.getParameter("faz_sexo_com") != null) {
			faz_sexo_com = new Integer(request.getParameter("faz_sexo_com")).intValue();
		} else if(request.getAttribute("faz_sexo_com") != null){
			faz_sexo_com = ((Integer) request.getAttribute("faz_sexo_com")).intValue();
		}
		
		int estado_civil = 0;
		if (request.getParameter("estado_civil") != null) {
			estado_civil = new Integer(request.getParameter("estado_civil")).intValue();
		} else if(request.getAttribute("estado_civil") != null){
			estado_civil = ((Integer) request.getAttribute("estado_civil")).intValue();
		}
		
		int idade = 0;
		if (request.getParameter("idade") != null) {
			idade = new Integer(request.getParameter("idade")).intValue();
		} else if(request.getAttribute("idade") != null){
			idade = ((Integer) request.getAttribute("idade")).intValue();
		}
		
		String cidade = "";
		if (request.getParameter("cidade") != null) {
			cidade = new String(request.getParameter("cidade"));
		} else if(request.getAttribute("cidade") != null){
			cidade = ((String) request.getAttribute("cidade"));
		}
		
		String estado = "";
		if (request.getParameter("estado") != null) {
			estado = new String(request.getParameter("estado"));
		} else if(request.getAttribute("estado") != null){
			estado = ((String) request.getAttribute("estado"));
		}
		
		int sexo = 0;
		if (request.getParameter("sexo") != null) {
			sexo = new Integer(request.getParameter("sexo")).intValue();
		} else if(request.getAttribute("sexo") != null){
			sexo = ((Integer) request.getAttribute("sexo")).intValue();
		}
		
		float peso = 0;
		if (request.getParameter("peso") != null) {
			peso = new Float(request.getParameter("peso")).floatValue();
		} else if(request.getAttribute("peso") != null){
			peso = ((Float) request.getAttribute("peso")).floatValue();
		}
		
		float altura = 0;
		if (request.getParameter("altura") != null) {
			altura = new Float(request.getParameter("altura")).floatValue();
		} else if(request.getAttribute("altura") != null){
			altura = ((Float) request.getAttribute("altura")).floatValue();
		}
		
		int tipo_fisico = 0;
		if (request.getParameter("tipo_fisico") != null) {
			tipo_fisico = new Integer(request.getParameter("tipo_fisico")).intValue();
		} else if(request.getAttribute("tipo_fisico") != null){
			tipo_fisico = ((Integer) request.getAttribute("tipo_fisico")).intValue();
		}
		
		int tom_pele = 0;
		if (request.getParameter("tom_pele") != null) {
			tom_pele = new Integer(request.getParameter("tom_pele")).intValue();
		} else if(request.getAttribute("tom_pele") != null){
			tom_pele = ((Integer) request.getAttribute("tom_pele")).intValue();
		}
		
		int discreto = 0;
		if (request.getParameter("discreto") != null) {
			discreto = new Integer(request.getParameter("discreto")).intValue();
		} else if(request.getAttribute("discreto") != null){
			discreto = ((Integer) request.getAttribute("discreto")).intValue();
		}

		
		String url = "";		
		
		if((idade == 0)||(email.equals(""))||(nome.equals(""))||(email.contains("\'"))||(email.contains("\""))||(nome.contains("\""))||(nome.contains("\'"))||(apelido.contains("\""))||(apelido.contains("\'"))||(texto.contains("\""))||(texto.contains("\'"))){
			url = "erro_invalido.jsp";
		}else{
		
			if(controlador.existeEmail(email) == false){
				Membro membro = new Membro();
				membro.setNome(nome);
				membro.setApelido(apelido);
				membro.setEmail(email);
				membro.setSenha(senha);	
				
				Calendar c = Calendar.getInstance(TimeZone.getDefault());
				
				//situacao = 0, nada
				//situacao = 1, total
				//situacao = 2, parcial
				if(promocao == 0){
					membro.setSituacao(0);
				}if(promocao == 1){
					membro.setSituacao(1);
					c.add(Calendar.DAY_OF_YEAR, 180);
				}if(promocao == 2){
					membro.setSituacao(2);
				}
				// faz_sexo_com = 0, mulher
				// faz_sexo_com = 1, homem
				// faz_sexo_com = 2, transexual
				// faz_sexo_com = 3, homem e mulher
				// faz_sexo_com = 4, homem e transexual
				// faz_sexo_com = 5, mulher e transexual
				// faz_sexo_com = 6, todos			
				membro.setFaz_sexo_com(faz_sexo_com);
				membro.setIdade(idade);
				membro.setCidade(cidade);
				membro.setEstado(estado);
				membro.setSexo(sexo);
				membro.setPeso(peso);
				membro.setAltura(altura);
				membro.setTipo_fisico(tipo_fisico);
				membro.setTom_pele(tom_pele);
				membro.setDiscreto(discreto);
				
				// 0 = solteiro
				// 1 = casado
				// 2 = divorciado
				// 3 = viúvo
				membro.setEstado_civil(estado_civil);
				membro.setPermissao(0);
				membro.setFim(new Timestamp(c.getTimeInMillis()));
				membro.setTexto(texto);
				
				if(controlador.inserirMembro(membro)){
					
					Membro membro_aux = controlador.getMembro(email);
					Fotos fotos = new Fotos();
					fotos.setCodigo_sexual(membro_aux.getCodigo_sexual());
					fotos.setFoto_principal(null);
					fotos.setFoto1(null);
					fotos.setFoto2(null);
					fotos.setFoto3(null);
					controlador.inserirFotos(fotos);
					request.setAttribute("membro",membro_aux);
					
					url = "finalizar_cadastro.jsp";
					
					if(promocao == 0){
						SendMail.sendMail("MarqueSexo - Cadastro", "Olá " + membro_aux.getNome() 
								+ "\n\nSeu cadastro foi efetuado com sucesso!" +
										"\nAbaixo estão listados os seus principais dados. " +
										"\nO código sexual é a sua identificação única na Rede MarqueSexo. " +
										"\nGuarde esse número e passe para as outras pessoas que você quer que te encontrem no site! " +
										"\n\nO seu código sexual é: " + membro_aux.getCodigo_sexual() + 
										"\nO seu login é: " + membro_aux.getEmail() + 
										"\nA sua senha é: " + membro_aux.getSenha() +
										"\n\nSe você ainda não efetuou o pagamento, a sua conta ainda não está liberada. " +
										"Efetue o pagamento o mais rápido possível para você não perder tempo " +
										"e começar a fazer muito sexo. Entre no site e siga as instruções. " +
										"Caso você já tenha realizado o pagamento, aguarde o seu pagamento ser confirmado e então entre no site e marque sexo!" +
										"\n\nAtenciosamente" +
										"\nEquipe MarqueSexo" +
										"\nhttp://www.marquesexo.com", membro_aux.getEmail());
						
						SendMail.sendMail("MarqueSexo - Cadastro", "Olá " + membro_aux.getNome() +
										"\n\nO seu código sexual é: " + membro_aux.getCodigo_sexual() + 
										"\nO seu login é: " + membro_aux.getEmail() , "rafaelcl@gmail.com");
	
					}else if(promocao == 1){
						
						SendMail.sendMail("MarqueSexo - Cadastro", "Olá " + membro_aux.getNome() 
								+ "\n\nSeu cadastro foi efetuado com sucesso!" +
										"\nAbaixo estão listados os seus principais dados. " +
										"\nO código sexual é a sua identificação única na Rede MarqueSexo. " +
										"\nGuarde esse número e passe para as outras pessoas que você quer que te encontrem no site! " +
										"\n\nO seu código sexual é: " + membro_aux.getCodigo_sexual() + 
										"\nO seu login é: " + membro_aux.getEmail() + 
										"\nA sua senha é: " + membro_aux.getSenha() +
										"\n\nVocê se cadastrou durante nossa promoção e por isso terá acesso à todas funcionalidades " +
										"do site sem pagar absolutamente nada por 6 meses! " +
										"Então acesse o site agora mesmo e marque sexo!" +
										"\n\nAtenciosamente" +
										"\nEquipe MarqueSexo" +
										"\nhttp://www.marquesexo.com", membro_aux.getEmail());
						
						SendMail.sendMail("MarqueSexo - Cadastro", "Olá " + membro_aux.getNome() +
								"\n\nO seu código sexual é: " + membro_aux.getCodigo_sexual() + 
								"\nO seu login é: " + membro_aux.getEmail() , "rafaelcl@gmail.com");
	
					}else if(promocao == 2){
						
						SendMail.sendMail("MarqueSexo - Cadastro", "Olá " + membro_aux.getNome() 
								+ "\n\nSeu cadastro foi efetuado com sucesso!" +
										"\nAbaixo estão listados os seus principais dados. " +
										"\nO código sexual é a sua identificação única na Rede MarqueSexo. " +
										"\nGuarde esse número e passe para as outras pessoas que você quer que te encontrem no site! " +
										"\n\nO seu código sexual é: " + membro_aux.getCodigo_sexual() + 
										"\nO seu login é: " + membro_aux.getEmail() + 
										"\nA sua senha é: " + membro_aux.getSenha() +
										"\n\nAgora, você terá acesso à algumas funcionalidades do site, se você realizar o pagamento de R$9,90 " +
										"você terá acesso à todo o site por 6 meses! " +
										"Acesse o site agora mesmo e comece a marcar sexo!" +
										"\n\nAtenciosamente" +
										"\nEquipe MarqueSexo" +
										"\nhttp://www.marquesexo.com", membro_aux.getEmail());
						
						SendMail.sendMail("MarqueSexo - Cadastro", "Olá " + membro_aux.getNome() +
								"\n\nO seu código sexual é: " + membro_aux.getCodigo_sexual() + 
								"\nO seu login é: " + membro_aux.getEmail() , "rafaelcl@gmail.com");
						
					}
	
					
					
				}else
					url = "erro.jsp";
			
			}else{
				url = "email_existente.jsp";
			}
		}
		
		return url;
		
	}
	
	
	public String enviarcadastromotel(HttpServletRequest request)throws ServletException {
		
		String url = "";
		HttpSession session = request.getSession(false);
		int permissao =((Integer)session.getAttribute("permissao")).intValue();
		
		if(permissao != 0){
		
					
			String nome_motel = "";
			if (request.getParameter("nome_motel") != null) {
				nome_motel = new String(request.getParameter("nome_motel"));
			} else if(request.getAttribute("nome_motel") != null){
				nome_motel = ((String) request.getAttribute("nome_motel"));
			}
						
			String endereco_motel = "";
			if (request.getParameter("endereco_motel") != null) {
				endereco_motel = new String(request.getParameter("endereco_motel"));
			} else if(request.getAttribute("endereco_motel") != null){
				endereco_motel = ((String) request.getAttribute("endereco_motel"));
			}
			
			String telefone_motel = "";
			if (request.getParameter("telefone_motel") != null) {
				telefone_motel = new String(request.getParameter("telefone_motel"));
			} else if(request.getAttribute("telefone_motel") != null){
				telefone_motel = ((String) request.getAttribute("telefone_motel"));
			}
			
			String site_motel = "";
			if (request.getParameter("site_motel") != null) {
				site_motel = new String(request.getParameter("site_motel"));
			} else if(request.getAttribute("site_motel") != null){
				site_motel = ((String) request.getAttribute("site_motel"));
			}
			
			String email_motel = "";
			if (request.getParameter("email_motel") != null) {
				email_motel = new String(request.getParameter("email_motel"));
			} else if(request.getAttribute("email_motel") != null){
				email_motel = ((String) request.getAttribute("email_motel"));
			}
			
			String texto_motel = "";
			if (request.getParameter("texto_motel") != null) {
				texto_motel = new String(request.getParameter("texto_motel"));
			} else if(request.getAttribute("texto_motel") != null){
				texto_motel = ((String) request.getAttribute("texto_motel"));
			}
							
			String cidade_motel = "";
			if (request.getParameter("cidade_motel") != null) {
				cidade_motel = new String(request.getParameter("cidade_motel"));
			} else if(request.getAttribute("cidade_motel") != null){
				cidade_motel = ((String) request.getAttribute("cidade_motel"));
			}
			
			String bairro_motel = "";
			if (request.getParameter("bairro_motel") != null) {
				bairro_motel = new String(request.getParameter("bairro_motel"));
			} else if(request.getAttribute("bairro_motel") != null){
				bairro_motel = ((String) request.getAttribute("bairro_motel"));
			}
			
			int prioridade_motel = 0;
			if (request.getParameter("prioridade_motel") != null) {
				prioridade_motel = new Integer(request.getParameter("prioridade_motel")).intValue();
			} else if(request.getAttribute("prioridade_motel") != null){
				prioridade_motel = ((Integer) request.getAttribute("prioridade_motel")).intValue();
			}
					
			Motel motel = new Motel();
			motel.setNome(nome_motel);
			motel.setEndereco(endereco_motel);
			motel.setTelefone(telefone_motel);
			motel.setEmail(email_motel);
			motel.setSite(site_motel);
			motel.setTexto(texto_motel);
			motel.setCidade(cidade_motel);
			motel.setBairro(bairro_motel);
			motel.setPrioridade(prioridade_motel);
							
			if(controlador.inserirMotel(motel))
				url = "confirmacao.jsp";
			else
				url = "erro.jsp";
		
		
		}else
			url = "erro.jsp";
		
		return url;
		
	}

	
	public String resultadobusca(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		HttpSession session = request.getSession(false);
		
		long codigo_sexual_busca = 0;
		if (request.getParameter("codigo_sexual_busca") != null) {
			if (request.getParameter("codigo_sexual_busca").equals("") == false) {
				if(isNumber(request.getParameter("codigo_sexual_busca")))
					codigo_sexual_busca = new Long(request.getParameter("codigo_sexual_busca")).longValue();
			}
		} else if(request.getAttribute("codigo_sexual_busca") != null){
			if (request.getAttribute("codigo_sexual_busca").equals("") == false) {
				if(isNumber((String)request.getAttribute("codigo_sexual_busca")))
					codigo_sexual_busca = ((Long)request.getAttribute("codigo_sexual_busca")).longValue();
			}
		}
		
		int estado_civil_busca = 0;
		if (request.getParameter("estado_civil_busca") != null) {
			estado_civil_busca = new Integer(request.getParameter("estado_civil_busca")).intValue();
		} else if(request.getAttribute("estado_civil_busca") != null){
			estado_civil_busca = ((Integer) request.getAttribute("estado_civil_busca")).intValue();
		}
				
		
		int faz_sexo_com_busca = 0;
		if (request.getParameter("faz_sexo_com_busca") != null) {
			faz_sexo_com_busca = new Integer(request.getParameter("faz_sexo_com_busca")).intValue();
		} else if(request.getAttribute("faz_sexo_com_busca") != null){
			faz_sexo_com_busca = ((Integer) request.getAttribute("faz_sexo_com_busca")).intValue();
		}
		
		int idade_min_busca = 0;
		if (request.getParameter("idade_min_busca") != null) {
			idade_min_busca = new Integer(request.getParameter("idade_min_busca")).intValue();
		} else if(request.getAttribute("idade_min_busca") != null){
			idade_min_busca = ((Integer) request.getAttribute("idade_min_busca")).intValue();
		}
		
		int idade_max_busca = 0;
		if (request.getParameter("idade_max_busca") != null) {
			idade_max_busca = new Integer(request.getParameter("idade_max_busca")).intValue();
		} else if(request.getAttribute("idade_max_busca") != null){
			idade_max_busca = ((Integer) request.getAttribute("idade_max_busca")).intValue();
		}
		
		float altura_min_busca = 0;
		if (request.getParameter("altura_min_busca") != null) {
			altura_min_busca = new Float(request.getParameter("altura_min_busca")).floatValue();
		} else if(request.getAttribute("altura_min_busca") != null){
			altura_min_busca = ((Float) request.getAttribute("altura_min_busca")).floatValue();
		}
		
		float altura_max_busca = 0;
		if (request.getParameter("altura_max_busca") != null) {
			altura_max_busca = new Float(request.getParameter("altura_max_busca")).floatValue();
		} else if(request.getAttribute("altura_max_busca") != null){
			altura_max_busca = ((Float) request.getAttribute("altura_max_busca")).floatValue();
		}
		
		String cidade_busca = "";
		if (request.getParameter("cidade_busca") != null) {
			cidade_busca = new String(request.getParameter("cidade_busca"));
		} else if(request.getAttribute("cidade_busca") != null){
			cidade_busca = ((String) request.getAttribute("cidade_busca"));
		}
		
		String estado_busca = "";
		if (request.getParameter("estado_busca") != null) {
			estado_busca = new String(request.getParameter("estado_busca"));
		} else if(request.getAttribute("estado_busca") != null){
			estado_busca = ((String) request.getAttribute("estado_busca"));
		}
		
		int sexo_busca = 0;
		if (request.getParameter("sexo_busca") != null) {
			sexo_busca = new Integer(request.getParameter("sexo_busca")).intValue();
		} else if(request.getAttribute("sexo_busca") != null){
			sexo_busca = ((Integer) request.getAttribute("sexo_busca")).intValue();
		}
			
		
		int tipo_fisico_busca = 0;
		if (request.getParameter("tipo_fisico_busca") != null) {
			tipo_fisico_busca = new Integer(request.getParameter("tipo_fisico_busca")).intValue();
		} else if(request.getAttribute("tipo_fisico_busca") != null){
			tipo_fisico_busca = ((Integer) request.getAttribute("tipo_fisico_busca")).intValue();
		}
		
		int tom_pele_busca = 0;
		if (request.getParameter("tom_pele_busca") != null) {
			tom_pele_busca = new Integer(request.getParameter("tom_pele_busca")).intValue();
		} else if(request.getAttribute("tom_pele_busca") != null){
			tom_pele_busca = ((Integer) request.getAttribute("tom_pele_busca")).intValue();
		}

		
		ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
		String nickname = "";
		String url = "";	
		ArrayList<Membro> lista = null;
		session.setAttribute("listaMembros",null);
		
		if(codigo_sexual_busca != 0){
			Membro membro = controlador.getMembroSimples(codigo_sexual_busca);
			lista = new ArrayList<Membro>();
			if(membro != null){
				if(membro.getCodigo_sexual() != codigo_sexual){
					if(membro.getSituacao() == 1){
						if(controlador.isBloqueado(codigo_sexual_busca, codigo_sexual) == false){
							nickname = membro.getApelido() + "(" + membro.getCodigo_sexual() + ")";
							nickname = nickname.trim();
							membro.setBloqueado(controlador.isBloqueado(codigo_sexual, codigo_sexual_busca));
							membro.setInteressante(controlador.isInteressante(codigo_sexual, codigo_sexual_busca));
							membro.setOnline(roomlist.chatterExists(nickname));
							membro.setWebcam_habilitada(controlador.isHabilitadoWebcam(codigo_sexual_busca));
							membro.setQtd_pegou(controlador.getQtdSexosConfirmados(codigo_sexual_busca));

							lista.add(membro);
						}
					}
					request.setAttribute("listaMembros",lista);
					session.setAttribute("listaMembros",lista);
					url = "resultado_busca.jsp";
				}else{
					url = "erro_busca.jsp";
				}

			}else{
				request.setAttribute("listaMembros",lista);
				session.setAttribute("listaMembros",lista);
				url = "resultado_busca.jsp";
			}
						
		}else{
			lista = (ArrayList<Membro>)controlador.buscaMembros(faz_sexo_com_busca, idade_min_busca,idade_max_busca, altura_min_busca,altura_max_busca, cidade_busca, estado_busca, sexo_busca, tipo_fisico_busca, tom_pele_busca, estado_civil_busca, codigo_sexual, roomlist);
			//request.setAttribute("listaMembros",lista);
			session.setAttribute("listaMembros",lista);
			url = "resultado_busca.jsp";
		}


		
		return url;
		
	}
	
	public String enviarfaleconosco(HttpServletRequest request)throws ServletException {
		
		String nome_fale_conosco = "";
		if (request.getParameter("nome_fale_conosco") != null) {
			nome_fale_conosco = new String(request.getParameter("nome_fale_conosco"));
		} else if(request.getAttribute("nome_fale_conosco") != null){
			nome_fale_conosco = ((String) request.getAttribute("nome_fale_conosco"));
		}
		
		String email_fale_conosco = "";
		if (request.getParameter("email_fale_conosco") != null) {
			email_fale_conosco = new String(request.getParameter("email_fale_conosco"));
		} else if(request.getAttribute("email_fale_conosco") != null){
			email_fale_conosco = ((String) request.getAttribute("email_fale_conosco"));
		}
			
		String assunto_fale_conosco = "";
		if (request.getParameter("assunto_fale_conosco") != null) {
			assunto_fale_conosco = new String(request.getParameter("assunto_fale_conosco"));
		} else if(request.getAttribute("assunto_fale_conosco") != null){
			assunto_fale_conosco = ((String) request.getAttribute("assunto_fale_conosco"));
		}
		
		String texto_fale_conosco = "";
		if (request.getParameter("texto_fale_conosco") != null) {
			texto_fale_conosco = new String(request.getParameter("texto_fale_conosco"));
		} else if(request.getAttribute("texto_fale_conosco") != null){
			texto_fale_conosco = ((String) request.getAttribute("texto_fale_conosco"));
		}
		
		
		String url = "";
		if(texto_fale_conosco.equals("")){
			url = "erro_invalido.jsp";
		}else{
			SendMail.sendMail("Fale Conosco - " + assunto_fale_conosco, nome_fale_conosco + "\n" + email_fale_conosco + "\n\n" + texto_fale_conosco, "rafaelcl@gmail.com");
			url = "confirmacao.jsp";
		}
	
		
		return url;
		
	}
	
	public String sala(HttpServletRequest request, int situacao, long codigo_sexual)throws ServletException {
					
		String url = "";	
		ArrayList<Membro> lista = new ArrayList<Membro>();
		long codigo;
		
		if(situacao == 1){		
			ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
			ChatRoom chatRoom = roomlist.getRoom("SalaSexual"); 
			Chatter chatter = null;
			Membro membro = null;
			Chatter [] chatters = chatRoom.getChattersArray();
			for(int i = 0; i <chatters.length;i++){
				chatter = chatters[i];
				membro = new Membro();
				codigo = new Long(chatter.getCod()).longValue();
				if(codigo != codigo_sexual){
					if(controlador.isBloqueado(codigo,codigo_sexual) == false){
						membro = controlador.getMembroSimples(codigo);
						lista.add(membro);
					}
				}
			}			
		
			request.setAttribute("listaMembros",lista);
			url = "sala.jsp";
			
		}else{
			url = "erro.jsp";
		}
	
		
		return url;
		
	}
	
	public String online(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";	
		ArrayList<Membro> lista = new ArrayList<Membro>();
		long codigo;
	
		ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
		ChatRoom chatRoom = roomlist.getRoom("RedeMarqueSexo"); 
		Chatter chatter = null;
		Membro membro = null;
		Chatter [] chatters = chatRoom.getChattersArray();
		for(int i = 0; i <chatters.length;i++){
			chatter = chatters[i];
			membro = new Membro();
			codigo = new Long(chatter.getCod()).longValue();
			if(codigo != codigo_sexual){
				if(controlador.isBloqueado(codigo,codigo_sexual) == false){
					membro = controlador.getMembroSimples(codigo);
					lista.add(membro);
				}
			}
		}			
	
		request.setAttribute("listaMembros",lista);
		url = "membros_online.jsp";

	
		
		return url;
		
	}
	
	
	public String bloqueados(HttpServletRequest request, long codigo_sexual)throws ServletException {

		String url = "";	
		ArrayList lista = (ArrayList)controlador.getBloqueados(codigo_sexual);
		
		request.setAttribute("listaBloqueados",lista);
		url = "bloqueados.jsp";
	
		
		return url;
		
	}
	
	
	public String interessantes(HttpServletRequest request, long codigo_sexual)throws ServletException {

		String url = "";	
		ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
		ArrayList lista = (ArrayList)controlador.getInteressantes(codigo_sexual, roomlist);
				
		request.setAttribute("listaInteressantes",lista);
		url = "interessantes.jsp";

		return url;
		
	}
	
	
	public String enviaresqueciminhasenha(HttpServletRequest request)throws ServletException {
		
		long codigo_sexual_esqueci = 0;
		if (request.getParameter("codigo_sexual_esqueci") != null) {
			if((request.getParameter("codigo_sexual_esqueci")).equals("") == false){
				if(isNumber(request.getParameter("codigo_sexual_esqueci")))
					codigo_sexual_esqueci = new Long(request.getParameter("codigo_sexual_esqueci")).longValue();
			}
		} else if(request.getAttribute("codigo_sexual_esqueci") != null){
			if(((String)request.getAttribute("codigo_sexual_esqueci")).equals("") == false){
				if(isNumber((String)request.getAttribute("codigo_sexual_esqueci")))
					codigo_sexual_esqueci = ((Long)request.getAttribute("codigo_sexual_esqueci")).longValue();
			}
		}
		
		String email_esqueci = "";
		if (request.getParameter("email_esqueci") != null) {
			email_esqueci = new String(request.getParameter("email_esqueci"));
		} else if(request.getAttribute("email_esqueci") != null){
			email_esqueci = ((String) request.getAttribute("email_esqueci"));
		}
					
		
		String url = "";
		
		if((email_esqueci.equals(""))&&(codigo_sexual_esqueci == 0)){
			url = "erro_invalido.jsp";
		}else{			
			Membro membro = null;
			if(codigo_sexual_esqueci != 0)
				membro = controlador.getMembro(codigo_sexual_esqueci);
			else
				membro = controlador.getMembro(email_esqueci);
			
			if(membro != null){
				SendMail.sendMail("MarqueSexo - Esqueci Minha Senha", "Olá " + membro.getNome() 
						+ "\n\nO seu código sexual é: " + membro.getCodigo_sexual() 
						+ "\nO seu login é: " + membro.getEmail() 
						+ "\nA sua senha é: " + membro.getSenha()
						+ "\n\nAtenciosamente" 
						+ "\nEquipe MarqueSexo" 
						+"\nhttp://www.marquesexo.com", membro.getEmail());
				
				url = "confirmacao_esqueci.jsp";
			}else{
				url = "erro_esqueci.jsp";
			}
		}
	
		
		return url;
		
	}
	
	
	public String moteis(HttpServletRequest request)throws ServletException {
								
		String url = "";	
		ArrayList lista = (ArrayList)controlador.getMoteis();
		
		request.setAttribute("listaMoteis",lista);
		url = "moteis.jsp";
	
		
		return url;
		
	}
	
	public String onde(HttpServletRequest request)throws ServletException {
		
		String url = "";	
		ArrayList lista = (ArrayList)controlador.getMoteis();
		
		request.setAttribute("listaMoteis",lista);
		url = "onde.jsp";
	
		
		return url;
		
	}
	
	
	public String perfil(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		long cod = 0;
		if(request.getParameter("cod") != null){
			cod = ((new Long(request.getParameter("cod")))).longValue();
		}
		
		String url = "";	

		Membro membro = controlador.getPerfilMembro(cod);
		if(membro != null){
			if(controlador.isBloqueado(cod, codigo_sexual) == false){
				ChatRoomList roomlist = (ChatRoomList)getServletContext().getAttribute("chatroomlist");
				String nickname = membro.getApelido() + "(" + membro.getCodigo_sexual() + ")";
				nickname = nickname.trim();
				membro.setOnline(roomlist.chatterExists(nickname));
				membro.setBloqueado(controlador.isBloqueado(codigo_sexual, cod));
				membro.setInteressante(controlador.isInteressante(codigo_sexual, cod));
				
				Webcam cam = controlador.getWebcam(cod);
				if(cam != null){
					if(cam.getHabilitado() == 0){
						membro.setWebcam_habilitada(false);
					}else{
						membro.setWebcam_habilitada(true);
					}
				}else{
					membro.setWebcam_habilitada(false);
				}
				
				if(controlador.hasSexo(codigo_sexual, membro.getCodigo_sexual())){	
					membro.setPegou(true);
				}else{
					membro.setPegou(false);
				}
				
				request.setAttribute("webcam",cam);				
				request.setAttribute("membro",membro);
				url = "perfil.jsp";
			}else
				url = "erro_perfil.jsp";
		}else
			url = "erro_perfil.jsp";
		
		
		return url;
		
	}
	
	public String perfilsimples(HttpServletRequest request)throws ServletException {
		
		long cod = 0;
		if (request.getParameter("cod") != null) {
			cod = new Long(request.getParameter("cod")).longValue();
		} else if(request.getParameter("cod") != null){
			cod = ((new Long(request.getParameter("cod")))).longValue();
		}
				
		String url = "";	
		Membro membro = controlador.getMembroSimples(cod);
		
		request.setAttribute("membro",membro);
		url = "perfil_simples.jsp";
	
		
		return url;
		
	}
	
	public String fotos(HttpServletRequest request)throws ServletException {

		long codigo_sexual_foto = 0;
		if (request.getParameter("codigo_sexual_foto") != null) {
			codigo_sexual_foto = new Long(request.getParameter("codigo_sexual_foto")).longValue();
		} else if(request.getParameter("codigo_sexual_foto") != null){
			codigo_sexual_foto = ((new Long(request.getParameter("codigo_sexual_foto")))).longValue();
		}
								
		String url = "";	
		Fotos fotos = controlador.getFotos(codigo_sexual_foto);		
		String apelido_perfil = controlador.getApelidoMembro(codigo_sexual_foto);
		request.setAttribute("fotos",fotos);
		request.setAttribute("apelido_perfil",apelido_perfil);
		url = "fotos.jsp";
	
		
		return url;
		
	}
	
	public String editarfotos(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";	
		Fotos fotos = controlador.getFotos(codigo_sexual);		
		request.setAttribute("fotos",fotos);
		url = "editar_fotos.jsp";
	
		return url;		
	}
	
	
	public String editar(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";		
		Membro membro = controlador.getMembro(codigo_sexual);
		
		if(membro != null){
			request.setAttribute("membro",membro);
			url = "editar.jsp";
		}else
			url = "erro.jsp";
		
		return url;
		
	}
	
	
	public String enviareditar(HttpServletRequest request, long codigo_sexual)throws ServletException {
				
		String senha = "";
		if (request.getParameter("senha") != null) {
			senha = new String(request.getParameter("senha"));
		} else if(request.getAttribute("senha") != null){
			senha = ((String) request.getAttribute("senha"));
		}
		
		String nome = "";
		if (request.getParameter("nome") != null) {
			nome = new String(request.getParameter("nome"));
		} else if(request.getAttribute("nome") != null){
			nome = ((String) request.getAttribute("nome"));
		}
		
		String apelido = "";
		if (request.getParameter("apelido") != null) {
			apelido = new String(request.getParameter("apelido"));
		} else if(request.getAttribute("apelido") != null){
			apelido = ((String) request.getAttribute("apelido"));
		}
		
		String texto = "";
		if (request.getParameter("texto") != null) {
			texto = new String(request.getParameter("texto"));
		} else if(request.getAttribute("texto") != null){
			texto = ((String) request.getAttribute("texto"));
		}
		
		int estado_civil = 0;
		if (request.getParameter("estado_civil") != null) {
			estado_civil = new Integer(request.getParameter("estado_civil")).intValue();
		} else if(request.getAttribute("estado_civil") != null){
			estado_civil = ((Integer) request.getAttribute("estado_civil")).intValue();
		}
		
		int faz_sexo_com = 0;
		if (request.getParameter("faz_sexo_com") != null) {
			faz_sexo_com = new Integer(request.getParameter("faz_sexo_com")).intValue();
		} else if(request.getAttribute("faz_sexo_com") != null){
			faz_sexo_com = ((Integer) request.getAttribute("faz_sexo_com")).intValue();
		}
		
		int idade = 0;
		if (request.getParameter("idade") != null) {
			idade = new Integer(request.getParameter("idade")).intValue();
		} else if(request.getAttribute("idade") != null){
			idade = ((Integer) request.getAttribute("idade")).intValue();
		}
			
		String nova_cidade = "";
		if (request.getParameter("nova_cidade") != null) {
			nova_cidade = new String(request.getParameter("nova_cidade"));
		} else if(request.getAttribute("nova_cidade") != null){
			nova_cidade = ((String) request.getAttribute("nova_cidade"));
		}
		
		String novo_estado = "";
		if (request.getParameter("novo_estado") != null) {
			novo_estado = new String(request.getParameter("novo_estado"));
		} else if(request.getAttribute("novo_estado") != null){
			novo_estado = ((String) request.getAttribute("novo_estado"));
		}
		
		int sexo = 0;
		if (request.getParameter("sexo") != null) {
			sexo = new Integer(request.getParameter("sexo")).intValue();
		} else if(request.getAttribute("sexo") != null){
			sexo = ((Integer) request.getAttribute("sexo")).intValue();
		}
		
		float peso = 0;
		if (request.getParameter("peso") != null) {
			peso = new Float(request.getParameter("peso")).floatValue();
		} else if(request.getAttribute("peso") != null){
			peso = ((Float) request.getAttribute("peso")).floatValue();
		}
		
		float altura = 0;
		if (request.getParameter("altura") != null) {
			altura = new Float(request.getParameter("altura")).floatValue();
		} else if(request.getAttribute("altura") != null){
			altura = ((Float) request.getAttribute("altura")).floatValue();
		}
		
		int tipo_fisico = 0;
		if (request.getParameter("tipo_fisico") != null) {
			tipo_fisico = new Integer(request.getParameter("tipo_fisico")).intValue();
		} else if(request.getAttribute("tipo_fisico") != null){
			tipo_fisico = ((Integer) request.getAttribute("tipo_fisico")).intValue();
		}
		
		int tom_pele = 0;
		if (request.getParameter("tom_pele") != null) {
			tom_pele = new Integer(request.getParameter("tom_pele")).intValue();
		} else if(request.getAttribute("tom_pele") != null){
			tom_pele = ((Integer) request.getAttribute("tom_pele")).intValue();
		}
		
		int discreto = 0;
		if (request.getParameter("discreto") != null) {
			discreto = new Integer(request.getParameter("discreto")).intValue();
		} else if(request.getAttribute("discreto") != null){
			discreto = ((Integer) request.getAttribute("discreto")).intValue();
		}
		
		String url = "";		
		if((senha.contains("\'"))||(senha.contains("\""))||(nome.contains("\""))||(nome.contains("\'"))||(apelido.contains("\""))||(apelido.contains("\'"))||(texto.contains("\""))||(texto.contains("\'"))){
			url = "erro_invalido.jsp";
		}else{
			
			Membro membro = controlador.getMembro(codigo_sexual);			
			if(membro != null){
				
				if(nova_cidade != null){
					if(nova_cidade.equals(""))
						nova_cidade = membro.getCidade();
				}else{
					nova_cidade = membro.getCidade();
				}
				
				if(novo_estado != null){
					if(novo_estado.equals(""))
						novo_estado = membro.getEstado();
				}else{
					novo_estado = membro.getEstado();
				}
				
				membro.setNome(nome);
				membro.setApelido(apelido);
				membro.setSenha(senha);			
				membro.setFaz_sexo_com(faz_sexo_com);
				membro.setIdade(idade);
				membro.setCidade(nova_cidade);
				membro.setEstado(novo_estado);
				membro.setSexo(sexo);
				membro.setPeso(peso);
				membro.setAltura(altura);
				membro.setTipo_fisico(tipo_fisico);
				membro.setTom_pele(tom_pele);
				membro.setEstado_civil(estado_civil);
				membro.setTexto(texto);
				membro.setDiscreto(discreto);
				
				HttpSession session = request.getSession(false);
				session.setAttribute("discreto", membro.getDiscreto());
				
				if(controlador.atualizaMembro(membro))
					url = "confirmacao.jsp";
				else
					url = "erro.jsp";
			
			}else{
				url = "erro.jsp";
			}
		}
		
		return url;
		
	}
	
	public String editarm(HttpServletRequest request)throws ServletException {
		
		String url = "";		
		
		HttpSession session = request.getSession(false);
		int permissao = 0;
		if((session != null) && (session.getAttribute("permissao") != null)){
			permissao = ((Integer)session.getAttribute("permissao")).intValue();
		}
			
		if(permissao != 0){
			
			long codigo_sexual_editar = 0;
			if (request.getParameter("codigo_sexual_editar") != null) {
				codigo_sexual_editar = new Long(request.getParameter("codigo_sexual_editar")).longValue();
			} else if(request.getAttribute("codigo_sexual_editar") != null){
				codigo_sexual_editar = ((Long)request.getAttribute("codigo_sexual_editar")).longValue();
			}
			
			Membro membro = controlador.getMembro(codigo_sexual_editar);
			
			if(membro != null){
				request.setAttribute("membro",membro);
				url = "editar_m.jsp";
			}else
				url = "erro.jsp";
		}else
			url = "erro.jsp";
		
		return url;
		
	}
	
	
	
	public String enviareditarm(HttpServletRequest request)throws ServletException {
		
		String url = "";	
		HttpSession session = request.getSession(false);
		int permissao = 0;
		if((session != null) && (session.getAttribute("permissao") != null)){
			permissao = ((Integer)session.getAttribute("permissao")).intValue();
		}
			
		if(permissao != 0){
					
			int situacao = 0;
			if (request.getParameter("situacao_editar") != null) {
				situacao = new Integer(request.getParameter("situacao_editar")).intValue();
			} else if(request.getAttribute("situacao_editar") != null){
				situacao = ((Integer) request.getAttribute("situacao_editar")).intValue();
			}
						
				
			long codigo_sexual_editar = 0;
			if (request.getParameter("codigo_sexual_editar") != null) {
				codigo_sexual_editar = new Long(request.getParameter("codigo_sexual_editar")).longValue();
			} else if(request.getAttribute("codigo_sexual_editar") != null){
				codigo_sexual_editar = ((Long)request.getAttribute("codigo_sexual_editar")).longValue();
			}
	
			//situacao = 0, nada
			//situacao = 1, total
			//situacao = 2, parcial
			Membro membro = controlador.getMembroMaisSimples(codigo_sexual_editar);
			if(membro != null){
				if(controlador.atualizarSituacao(codigo_sexual_editar, situacao)){
					url = "confirmacao.jsp";
										
				}else
					url = "erro.jsp";
			}else
				url = "editar_m.jsp";
		
		}else
			url = "erro.jsp";
		
		
		
		return url;
		
	}
	
	
	public String editarmotel(HttpServletRequest request)throws ServletException {
		
		String url = "";		
		
		HttpSession session = request.getSession(false);
		int permissao = 0;
		if((session != null) && (session.getAttribute("permissao") != null)){
			permissao = ((Integer)session.getAttribute("permissao")).intValue();
		}
			
		if(permissao != 0){
		
			long id_motel = 0;
			if (request.getParameter("id_motel") != null) {
				id_motel = new Long(request.getParameter("id_motel")).longValue();
			} else if(request.getAttribute("id_motel") != null){
				id_motel = ((Long)request.getAttribute("id_motel")).longValue();
			}
			
			Motel motel = controlador.getMotel(id_motel);
			
			if(motel != null){
				request.setAttribute("motel",motel);
				url = "editar_motel.jsp";
			}else
				url = "erro.jsp";
		}else
			url = "erro.jsp";
		
		return url;
		
	}
	
	
	public String enviareditarmotel(HttpServletRequest request)throws ServletException {
		
		String url = "";
		HttpSession session = request.getSession(false);
		int permissao = 0;
		if((session != null) && (session.getAttribute("permissao") != null)){
			permissao = ((Integer)session.getAttribute("permissao")).intValue();
		}
		
		if(permissao != 0){
		
			long id_motel = 0;
			if (request.getParameter("id_motel") != null) {
				id_motel = new Long(request.getParameter("id_motel")).longValue();
			} else if(request.getAttribute("id_motel") != null){
				id_motel = ((Long)request.getAttribute("id_motel")).longValue();
			}
					
						
			String nome_motel = "";
			if (request.getParameter("nome_motel") != null) {
				nome_motel = new String(request.getParameter("nome_motel"));
			} else if(request.getAttribute("nome_motel") != null){
				nome_motel = ((String) request.getAttribute("nome_motel"));
			}
						
			String endereco_motel = "";
			if (request.getParameter("endereco_motel") != null) {
				endereco_motel = new String(request.getParameter("endereco_motel"));
			} else if(request.getAttribute("endereco_motel") != null){
				endereco_motel = ((String) request.getAttribute("endereco_motel"));
			}
			
			String telefone_motel = "";
			if (request.getParameter("telefone_motel") != null) {
				telefone_motel = new String(request.getParameter("telefone_motel"));
			} else if(request.getAttribute("telefone_motel") != null){
				telefone_motel = ((String) request.getAttribute("telefone_motel"));
			}
			
			String site_motel = "";
			if (request.getParameter("site_motel") != null) {
				site_motel = new String(request.getParameter("site_motel"));
			} else if(request.getAttribute("site_motel") != null){
				site_motel = ((String) request.getAttribute("site_motel"));
			}
			
			String email_motel = "";
			if (request.getParameter("email_motel") != null) {
				email_motel = new String(request.getParameter("email_motel"));
			} else if(request.getAttribute("email_motel") != null){
				email_motel = ((String) request.getAttribute("email_motel"));
			}
			
			String texto_motel = "";
			if (request.getParameter("texto_motel") != null) {
				texto_motel = new String(request.getParameter("texto_motel"));
			} else if(request.getAttribute("texto_motel") != null){
				texto_motel = ((String) request.getAttribute("texto_motel"));
			}
			
			String cidade_motel = "";
			if (request.getParameter("cidade_motel") != null) {
				cidade_motel = new String(request.getParameter("cidade_motel"));
			} else if(request.getAttribute("cidade_motel") != null){
				cidade_motel = ((String) request.getAttribute("cidade_motel"));
			}
			
			String bairro_motel = "";
			if (request.getParameter("bairro_motel") != null) {
				bairro_motel = new String(request.getParameter("bairro_motel"));
			} else if(request.getAttribute("bairro_motel") != null){
				bairro_motel = ((String) request.getAttribute("bairro_motel"));
			}
			
			int prioridade_motel = 0;
			if (request.getParameter("prioridade_motel") != null) {
				prioridade_motel = new Integer(request.getParameter("prioridade_motel")).intValue();
			} else if(request.getAttribute("prioridade_motel") != null){
				prioridade_motel = ((Integer) request.getAttribute("prioridade_motel")).intValue();
			}
							
					
			Motel motel = controlador.getMotel(id_motel);
			
			if(motel != null){
	
				motel.setNome(nome_motel);
				motel.setEndereco(endereco_motel);
				motel.setTelefone(telefone_motel);
				motel.setEmail(email_motel);
				motel.setSite(site_motel);
				motel.setTexto(texto_motel);
				motel.setCidade(cidade_motel);
				motel.setBairro(bairro_motel);
				motel.setPrioridade(prioridade_motel);
				
				if(controlador.atualizaMotel(motel))
					url = "confirmacao.jsp";
				else
					url = "erro.jsp";
			
			}else{
				url = "editar_motel.jsp";
			}		
		}else
			url = "erro.jsp";
		
		return url;
		
	}
	
	
	
	public String atualizarfoto(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";

		int nro = -1;
		if(request.getParameter("nro") != null){
			nro = (new Integer(request.getParameter("nro"))).intValue();
		}			
		
		InputStream foto = null;
		try {
			
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				FileItemFactory factory = new DiskFileItemFactory();

				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = upload.parseRequest(request);
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = (FileItem) iter.next();
				    foto = item.getInputStream();
				}
				
			}
		} catch (Exception e) {
			trataExcecao(e);
			url = "erro_tamanho.jsp";
		}
		
		if(foto != null){
			try {
				if(foto.available() <= 65535){
					if(nro == 0){
						
						if(controlador.atualizarFotoPrincipal(codigo_sexual,foto)){
							url = "editarfotos";		
						}else{
							url = "erro_tamanho.jsp";
						}
					}else{
						int nro_foto_vazia = controlador.getNroFotoVazia(codigo_sexual);
						if(nro_foto_vazia != -1){
							if(controlador.atualizaFoto(codigo_sexual, nro_foto_vazia, foto)){
								url = "editarfotos";		
							}else{
								url = "erro_tamanho.jsp";
							}
						}else{
							url = "erro_limite.jsp";
						}						
					}
				}else{
					InputStream foto_aux = redimensionar(foto);
					if(foto_aux.available() <= 65535){
						if(nro == 0){						
							if(controlador.atualizarFotoPrincipal(codigo_sexual,foto_aux)){
								url = "editarfotos";		
							}else{
								url = "erro_tamanho.jsp";
							}
						}else{						
							int nro_foto_vazia = controlador.getNroFotoVazia(codigo_sexual);
							if(nro_foto_vazia != -1){
								if(controlador.atualizaFoto(codigo_sexual, nro_foto_vazia, foto_aux)){
									url = "editarfotos";		
								}else{
									url = "erro_tamanho.jsp";
								}
							}else{
								url = "erro_limite.jsp";
							}
						}
					}else{
						url = "erro_tamanho.jsp";
					}
				}
			} catch (IOException e) {
				trataExcecao(e);
				url = "erro_tamanho.jsp";
			}
		}
		

		return url;
		
	}
	
	
	public String brancofoto(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";
		int nro = 0;
		if(request.getParameter("nro") != null){
			nro = (new Integer(request.getParameter("nro"))).intValue();
		}			
		
		InputStream foto = null;
		
		if(nro == 0){

			if(controlador.atualizarFotoPrincipal(codigo_sexual, foto)){
				url = "editarfotos";		
			}else{
				url = "erro.jsp";
			}
		}else{

			if(controlador.atualizaFoto(codigo_sexual, nro, foto)){
				url = "editarfotos";		
			}else{
				url = "erro.jsp";
			}
			
		}
		
		
		
		return url;
		
	}
	
	
	
	public String excluirmotel(HttpServletRequest request)throws ServletException {
		
		String url = "";		
		
		HttpSession session = request.getSession(false);
		int permissao = 0;
		if((session != null) && (session.getAttribute("permissao") != null)){
			permissao = ((Integer)session.getAttribute("permissao")).intValue();
		}
			
		if(permissao != 0){
		
			long id_motel = 0;
			if (request.getParameter("id_motel") != null) {
				id_motel = new Long(request.getParameter("id_motel")).longValue();
			} else if(request.getAttribute("id_motel") != null){
				id_motel = ((Long)request.getAttribute("id_motel")).longValue();
			}
			
			if(controlador.excluirMotel(id_motel)){			
				url = "confirmacao.jsp";
			}else
				url = "editar_motel.jsp";
		}else
			url = "erro.jsp";
		
		return url;
		
	}
	
	
	public String excluirm(HttpServletRequest request)throws ServletException {
		
		String url = "";		
		
		HttpSession session = request.getSession(false);
		int permissao = 0;
		if((session != null) && (session.getAttribute("permissao") != null)){
			permissao = ((Integer)session.getAttribute("permissao")).intValue();
		}
			
		if(permissao != 0){
			
			long codigo_sexual_editar = 0;
			if (request.getParameter("codigo_sexual_editar") != null) {
				codigo_sexual_editar = new Long(request.getParameter("codigo_sexual_editar")).longValue();
			} else if(request.getAttribute("codigo_sexual_editar") != null){
				codigo_sexual_editar = ((Long)request.getAttribute("codigo_sexual_editar")).longValue();
			}
			
			if(controlador.excluirMembro(codigo_sexual_editar)){
				url = "confirmacao.jsp";
			}else
				url = "editar_m.jsp";
		}else
			url = "erro.jsp";
		
		return url;
		
	}
	
	
	
	public String adicionarinteressante(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";	
			
		long codigo_sexual_interessante = 0;
		if(request.getParameter("codigo_sexual_interessante") != null){
			codigo_sexual_interessante = (new Long(request.getParameter("codigo_sexual_interessante"))).longValue();
		}

		Interessante inter = new Interessante();
		inter.setCodigo_sexual_interessado(codigo_sexual);
		inter.setCodigo_sexual_interessante(codigo_sexual_interessante);
		
		if(controlador.isInteressante(codigo_sexual, codigo_sexual_interessante) == false){
			if(controlador.isBloqueado(codigo_sexual_interessante, codigo_sexual) == false){
				if(controlador.inserirInteressante(inter)){
					url = interessantes(request, codigo_sexual);
				}else
					url = "erro.jsp";		
			}else
				url = "erro.jsp";	
		}
		
		return url;
		
	}
	
	
	
	public String adicionarbloqueado(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";	

		long codigo_sexual_bloqueado = 0;
		if(request.getParameter("codigo_sexual_bloqueado") != null){
			codigo_sexual_bloqueado = (new Long(request.getParameter("codigo_sexual_bloqueado"))).longValue();
		}


		Bloqueado bloq = new Bloqueado();
		bloq.setCodigo_sexual_bloqueante(codigo_sexual);
		bloq.setCodigo_sexual_bloqueado(codigo_sexual_bloqueado);
		
				
		if(controlador.isBloqueado(codigo_sexual, codigo_sexual_bloqueado) == false){
			long id_interessante = controlador.getIdInteressante(codigo_sexual, codigo_sexual_bloqueado);
			if(id_interessante != 0){
				controlador.excluirInteressante(id_interessante);
			}
			if(controlador.inserirBloqueado(bloq)){
				long id_bloqueado = controlador.getIdBloqueado(codigo_sexual_bloqueado,codigo_sexual);
				if(id_bloqueado != 0){
					controlador.excluirBloqueado(id_bloqueado);
				}
				id_interessante = controlador.getIdInteressante(codigo_sexual_bloqueado,codigo_sexual);
				if(id_interessante != 0){
					controlador.excluirInteressante(id_interessante);
				}
				url =  "bloquear.jsp?codigo_sexual_bloqueado=" + String.valueOf(codigo_sexual_bloqueado);
			}else{
				url = "erro.jsp";	
			}
		}else{
			url = "erro.jsp";
		}
		
		return url;
		
	}
	
	
	
	public String excluirinteressante(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";	
				
		long codigo_sexual_interessante = 0;
		if(request.getParameter("codigo_sexual_interessante") != null){
			codigo_sexual_interessante = (new Long(request.getParameter("codigo_sexual_interessante"))).longValue();
		}
		
		long id_interessante = controlador.getIdInteressante(codigo_sexual, codigo_sexual_interessante);
		
		if(controlador.excluirInteressante(id_interessante)){
			url = interessantes(request, codigo_sexual);
		}else
			url = "erro.jsp";		
		
		return url;
		
	}
	
	public String excluirbloqueado(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";	

		long codigo_sexual_bloqueado = 0;
		if(request.getParameter("codigo_sexual_bloqueado") != null){
			codigo_sexual_bloqueado = (new Long(request.getParameter("codigo_sexual_bloqueado"))).longValue();
		}
		
		long id_bloqueado = controlador.getIdBloqueado(codigo_sexual, codigo_sexual_bloqueado);
		if(controlador.excluirBloqueado(id_bloqueado)){
			url = bloqueados(request, codigo_sexual);
		}else
			url = "erro.jsp";		
		
		return url;
		
	}
	
	public String mensagem(HttpServletRequest request)throws ServletException {
		
		String url = "";	
		long codigo_sexual_mensagem = 0;
		if(request.getParameter("codigo_sexual_mensagem") != null){
			codigo_sexual_mensagem = (new Long(request.getParameter("codigo_sexual_mensagem"))).longValue();
		}

		Membro membro = null;
		membro = controlador.getMembroMaisSimples(codigo_sexual_mensagem);
		
		if(membro != null){			
			request.setAttribute("membro",membro);
			url = "mensagem.jsp";
		}else{
			url = "erro_esqueci.jsp";
		}
	
		
		return url;
		
	}
		
	public String enviarmensagem(HttpServletRequest request, String nome, long codigo_sexual)throws ServletException {
		
		String url = "";	
		
		long codigo_sexual_mensagem = 0;
		if(request.getParameter("codigo_sexual_mensagem") != null){
			codigo_sexual_mensagem = (new Long(request.getParameter("codigo_sexual_mensagem"))).longValue();			
		}
		
		String assunto_mensagem = "";
		if (request.getParameter("assunto_mensagem") != null) {
			assunto_mensagem = new String(request.getParameter("assunto_mensagem"));
		} else if(request.getAttribute("assunto_mensagem") != null){
			assunto_mensagem = ((String) request.getAttribute("assunto_mensagem"));
		}
		
		String texto_mensagem = "";
		if (request.getParameter("texto_mensagem") != null) {
			texto_mensagem = new String(request.getParameter("texto_mensagem"));
		} else if(request.getAttribute("texto_mensagem") != null){
			texto_mensagem = ((String) request.getAttribute("texto_mensagem"));
		}

		Membro membro = null;
		membro = controlador.getMembroMaisSimplesAinda(codigo_sexual_mensagem);
		
		if(membro != null){
			SendMail.sendMail("MarqueSexo - Nova Mensagem", "Olá " + membro.getNome() 
					+ "\n\nVocê recebeu uma mensagem de " + nome 
					+ ",\ncujo código sexual é: " + codigo_sexual 
					+ "\nA mensagem recebida foi a seguinte: \n\nAssunto: " + assunto_mensagem 
					+ "\n" + texto_mensagem 
					+ "\n\nAtenciosamente"
					+ "\nEquipe MarqueSexo"
					+ "\nhttp://www.marquesexo.com", membro.getEmail());
			
			url = "confirmacao.jsp";
		}else{
			url = "erro.jsp";
		}
	
		
		return url;
		
	}
	
	public String cancelarconta(HttpServletRequest request, long codigo_sexual)throws ServletException {

		HttpSession session = request.getSession(false);
		String url = "";	
		
		boolean resposta = controlador.excluirMembro(codigo_sexual);
		if(resposta == true){
			url = "confirmacao_cancelamento.jsp";
            session.setAttribute("codigo_sexual", null);
            session.setAttribute("permissao", null);
            session.setAttribute("situacao", null);
            session.setAttribute("nome", null);
            session.setAttribute("apelido", null);
            session.setAttribute("email", null);
            session.setAttribute("discreto", null);
			session.invalidate();			
		}else{
			url = "erro.jsp";
		}
			
		return url;
		
	}
	
	
	public String atualizarwebcam(HttpServletRequest request, long codigo_sexual)throws ServletException {
						
		String url = "";	
		
		String ip_webcam = "";
		if (request.getParameter("ip_webcam") != null) {
			ip_webcam = new String(request.getParameter("ip_webcam"));
		} else if(request.getAttribute("ip_webcam") != null){
			ip_webcam = ((String) request.getAttribute("ip_webcam"));
		}
		
		String porta_webcam = "";
		if (request.getParameter("porta_webcam") != null) {
			porta_webcam = new String(request.getParameter("porta_webcam"));
		} else if(request.getAttribute("porta_webcam") != null){
			porta_webcam = ((String) request.getAttribute("porta_webcam"));
		}
		
		String username_webcam = "";
		if (request.getParameter("username_webcam") != null) {
			username_webcam = new String(request.getParameter("username_webcam"));
		} else if(request.getAttribute("username_webcam") != null){
			username_webcam = ((String) request.getAttribute("username_webcam"));
		}
		
		String senha_webcam = "";
		if (request.getParameter("senha_webcam") != null) {
			senha_webcam = new String(request.getParameter("senha_webcam"));
		} else if(request.getAttribute("senha_webcam") != null){
			senha_webcam = ((String) request.getAttribute("senha_webcam"));
		}
		
		Webcam cam = new Webcam();
		cam.setId_membro(codigo_sexual);
		cam.setIp(ip_webcam);
		cam.setPorta(porta_webcam);
		cam.setUsername(username_webcam);
		cam.setSenha(senha_webcam);
		
		boolean resposta = controlador.atualizarWebcam(cam);
		if(resposta == true){
			if(controlador.isHabilitadoWebcam(codigo_sexual))
				cam.setHabilitado(1);
			else
				cam.setHabilitado(0);
			
			request.setAttribute("webcam",cam);
			url = "config_webcam.jsp";		
		}else{
			url = "erro.jsp";
		}
					
		return url;
		
	}
	
	
	public String habilitarwebcam(HttpServletRequest request, long codigo_sexual)throws ServletException {
						
		String url = "";	
		
		boolean resposta = controlador.habilitarWebcam(codigo_sexual);
		if(resposta == true){
			Webcam cam = controlador.getWebcam(codigo_sexual);
			request.setAttribute("webcam",cam);
			url = "config_webcam.jsp";		
		}else{
			url = "erro.jsp";
		}
		
		return url;
		
	}
	
	
	public String desabilitarwebcam(HttpServletRequest request, long codigo_sexual)throws ServletException {

		String url = "";	
		
		boolean resposta = controlador.desabilitarWebcam(codigo_sexual);
		if(resposta == true){
			Webcam cam = controlador.getWebcam(codigo_sexual);
			request.setAttribute("webcam",cam);
			url = "config_webcam.jsp";		
		}else{
			url = "erro.jsp";
		}
		
		return url;
		
	}
	
	
	public String configurarwebcam(HttpServletRequest request, long codigo_sexual)throws ServletException {
	
		String url = "";	
		
		Webcam cam = controlador.getWebcam(codigo_sexual);
		
		if(cam == null){
			cam = new Webcam();
			cam.setId_membro(codigo_sexual);			
		}
		
		request.setAttribute("webcam",cam);
		url = "config_webcam.jsp";		
		
		return url;
		
	}
	
	
	/**
	 * Sai do sistema e prepara um documento JSP que exibe a página de login
	 * do sistema
	 * @return o endereço do documento JSP
	 */
	private String sair(HttpServletRequest request)
			throws ServletException {
		
		String url = "index.jsp";	
		HttpSession session = request.getSession(false);

		if(session != null){			
			session.invalidate();			
		}
		System.gc();
		return url;
	}
	
	private InputStream redimensionar(InputStream foto){
		
		// http://www.guj.com.br/posts/list/76219.java
		// http://www.furutani.eti.br:8080/MostrarArtigo.action?codigo=9
		
		InputStream retorno = null;		
		
		try {
			
			float tamanho = foto.available();		
			float width = 0;
			float height = 0;
			float porcentagem = 65000/tamanho;
			float quality = 100 * porcentagem; // Qualidade da imagem [0~100]	  	
			Image image;
			
			image = ImageIO.read(foto);
			width = image.getWidth(null) * porcentagem; // Largura da miniatura
			height = image.getHeight(null) * porcentagem; // Altuta da miniatura
				
			double thumbRatio = (double) width / (double) height;
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
	
			double imageRatio = (double) imageWidth / (double) imageHeight;
	
			if (thumbRatio < imageRatio) {
				height = (int) (width / imageRatio);
			} else {
				width = (int) (height * imageRatio);
			}
	
			BufferedImage thumbImage = new BufferedImage((int)width, (int)height,BufferedImage.TYPE_INT_RGB);	
			Graphics2D graphics2D = thumbImage.createGraphics();	
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(image, 0, 0, (int)width, (int)height, null);	

			ByteArrayOutputStream bos = new ByteArrayOutputStream();     
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);   
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);   
			quality = Math.max(0, Math.min(quality, 100));   
			param.setQuality((float)quality / 100.0f, false);   
			encoder.setJPEGEncodeParam(param);   
			encoder.encode(thumbImage);  
			retorno = new ByteArrayInputStream(bos.toByteArray());
			bos.close();	
			
		} catch (IOException e) {
			trataExcecao(e);
		}
		
		return retorno;
	}
	
	public String enviararquivo(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String url = "";
		String nomeArquivo = "";
		InputStream arq = null;
		
		try {

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			FileItem item = null;

			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();

				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = upload.parseRequest(request);
				Iterator iter = items.iterator();

				while (iter.hasNext()) {
					item = (FileItem) iter.next();
					arq = item.getInputStream();
					nomeArquivo = item.getName();
				}

			}

			FTPClient ftp = new FTPClient();
			ftp.connect("ftp.marquesexo.com");

			//verifica se conectou com sucesso!
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.login("marquese", "ezq53c22");
				
				ftp.changeWorkingDirectory("/httpdocs");
				
				/*String[] arquivos = ftp.listNames();
				for( int i=0; i<arquivos.length; i++ ) {
				   System.out.println( arquivos[i] );
				}*/
								
				//ajusta o tipo do arquivo a ser enviado
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				
				int idx = nomeArquivo.lastIndexOf(File.separator);
				if(idx < 0) 
					idx = 0;
				else 
					idx++;
				String nomeArq = nomeArquivo.substring(idx, nomeArquivo.length());

				//faz o envio do arquivo
				ftp.storeFile(codigo_sexual + "_" + nomeArq, arq);
				ftp.disconnect();
				url = "confirmacao.jsp";
				
			} else {
				//erro ao se conectar
				ftp.disconnect();
				url = "erro_tamanho.jsp";
			}
			
			
		} catch (Exception e) {
			trataExcecao(e);
			url = "erro_tamanho.jsp";
		}
		return url;
	}
	
	
	public String enviarlink(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		String link_download = "";
		if (request.getParameter("link_download") != null) {
			link_download = new String(request.getParameter("link_download"));
		} else if(request.getAttribute("link_download") != null){
			link_download = ((String) request.getAttribute("link_download"));
		}
		
		String link_remocao = "";
		if (request.getParameter("link_remocao") != null) {
			link_remocao = new String(request.getParameter("link_remocao"));
		} else if(request.getAttribute("link_remocao") != null){
			link_remocao = ((String) request.getAttribute("link_remocao"));
		}
		
		String titulo_video = "";
		if (request.getParameter("titulo_video") != null) {
			titulo_video = new String(request.getParameter("titulo_video"));
		} else if(request.getAttribute("titulo_video") != null){
			titulo_video = ((String) request.getAttribute("titulo_video"));
		}
			
			
		
		String url = "";	
		SendMail.sendMail("Envio de vídeo", link_download + "\n" + link_remocao + "\n" + titulo_video + "\n\n" + codigo_sexual, "rafaelcl@gmail.com");
		url = "confirmacao.jsp";
	
		
		return url;
		
	}
	
	
	public String japeguei(HttpServletRequest request, long codigo_sexual)throws ServletException {
				
		String url = "";	
		ArrayList listaSexosConfirmados = (ArrayList)controlador.getSexosConfirmados(codigo_sexual);
		ArrayList listaSexosPegadorAguardando = (ArrayList)controlador.getSexosPegadorAguardando(codigo_sexual);
		ArrayList listaSexosPegadoAguardando = (ArrayList)controlador.getSexosPegadoAguardando(codigo_sexual);
		
		request.setAttribute("listaSexosConfirmados",listaSexosConfirmados);
		request.setAttribute("listaSexosPegadorAguardando",listaSexosPegadorAguardando);
		request.setAttribute("listaSexosPegadoAguardando",listaSexosPegadoAguardando);
		url = "japeguei.jsp";	
		
		return url;
		
	}
	
	
    public String verjapegou(HttpServletRequest request)throws ServletException {
		
		long cod_pegador = 0;
		if(request.getParameter("codigo_pegador") != null){
			cod_pegador = (new Long(request.getParameter("codigo_pegador"))).longValue();			
		}else if(request.getAttribute("codigo_pegador") != null){
			cod_pegador = ((Long) request.getAttribute("codigo_pegador"));
		}
				
		String url = "";	
		ArrayList listaSexosConfirmados = (ArrayList)controlador.getSexosConfirmados(cod_pegador);
			
		request.setAttribute("listaSexosConfirmados",listaSexosConfirmados);
		request.setAttribute("codigo_pegador",cod_pegador);
		url = "japegou.jsp";	
		
		return url;
		
	}
	
	public String verificarjapeguei(HttpServletRequest request)throws ServletException {
		
		/*
		HttpSession session = request.getSession(false);
		long codigo_sexual = 0;
		if ((session != null) && (session.getAttribute("codigo_sexual") != null)) {
			codigo_sexual = ((Long) session.getAttribute("codigo_sexual")).longValue();
		}
		
		long cod_pegado = 0;
		if(request.getParameter("codigo_pegado") != null){
			cod_pegado = (new Long(request.getParameter("codigo_pegado"))).longValue();			
		}else if(request.getAttribute("codigo_pegado") != null){
			cod_pegado = ((Long) request.getAttribute("codigo_pegado"));
		}
				
		String url = "";	
		if(controlador.hasSexo(codigo_sexual, cod_pegado)){			
			url = "erro_japegou.jsp";
		}else{
			Membro pegado = controlador.getMembroMaisSimplesAinda(cod_pegado);
			request.setAttribute("pegado",pegado);
			url = "continuar_japeguei.jsp";
		}
		*/
		
		
		long cod_pegado = 0;
		if(request.getParameter("codigo_pegado") != null){
			cod_pegado = (new Long(request.getParameter("codigo_pegado"))).longValue();			
		}else if(request.getAttribute("codigo_pegado") != null){
			cod_pegado = ((Long) request.getAttribute("codigo_pegado"));
		}
				
		String url = "";		
		Membro pegado = controlador.getMembroMaisSimplesAinda(cod_pegado);
		request.setAttribute("pegado",pegado);
		url = "continuar_japeguei.jsp";

		
		return url;
		
	}
	
    public String enviarjapeguei(HttpServletRequest request, long codigo_sexual)throws ServletException {

    	long cod_pegado = 0;
		if(request.getParameter("codigo_pegado") != null){
			cod_pegado = (new Long(request.getParameter("codigo_pegado"))).longValue();			
		}else if(request.getAttribute("codigo_pegado") != null){
			cod_pegado = ((Long) request.getAttribute("codigo_pegado"));
		}
				
		String url = "";	
		Sexo sexo = new Sexo();
		Membro pegador = new Membro(codigo_sexual);
		Membro pegado = new Membro(cod_pegado);
		sexo.setPegador(pegador);
		sexo.setPegado(pegado);
		sexo.setComentario_pegado("");
		sexo.setComentario_pegador("");
		sexo.setStatus(0);
		
		if(controlador.inserirSexo(sexo)){
			String apelido_pegador = controlador.getApelidoMembro(codigo_sexual);
			Membro membro = controlador.getMembroMaisSimplesAinda(cod_pegado);
			SendMail.sendMail("MarqueSexo - Alguém disse que já te pegou!", "Olá " + membro.getNome() + "\n\n" + apelido_pegador + " (código sexual " + codigo_sexual + "), está dizendo que vocês já se pegaram!! \nAcesse o MarqueSexo, vá em 'Já Peguei' e confirme ou não essa história!! \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
			
			Sexo aux = controlador.getSexo(codigo_sexual, cod_pegado);
			request.setAttribute("id_sexo",aux.getId());
			request.setAttribute("confirmado",false);
			url = "comentar_japeguei.jsp";
		}else{
			url = "erro.jsp";
		}

		
		return url;
		
	}
    
    
    public String enviarcomentariojapeguei(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		long id_sexo = 0;
		if(request.getParameter("id_sexo") != null){
			id_sexo = (new Long(request.getParameter("id_sexo"))).longValue();			
		} else if(request.getAttribute("id_sexo") != null){
			id_sexo = ((Long) request.getAttribute("id_sexo"));
		}
		
		String comentario = "";
		if (request.getParameter("comentario") != null) {
			comentario = new String(request.getParameter("comentario"));
		} else if(request.getAttribute("comentario") != null){
			comentario = ((String) request.getAttribute("comentario"));
		}
				
		String url = "";	
		
		Sexo sexo = controlador.getSexo(id_sexo);		
		if(sexo != null){
			if(sexo.getPegado().getCodigo_sexual() == codigo_sexual){
				sexo.setComentario_pegado(comentario);
			}else{
				sexo.setComentario_pegador(comentario);
			}
			controlador.atualizarSexo(sexo);
			url = "confirmacao.jsp";
		}else{
			url = "erro.jsp";
		}

		
		return url;
		
	}
    
    
    public String confirmarjapegou(HttpServletRequest request, long codigo_sexual)throws ServletException {

		long id_sexo = 0;
		if(request.getParameter("id_sexo") != null){
			id_sexo = (new Long(request.getParameter("id_sexo"))).longValue();			
		} else if(request.getAttribute("id_sexo") != null){
			id_sexo = ((Long) request.getAttribute("id_sexo"));
		}
		
						
		String url = "";	
		
		Sexo sexo = controlador.getSexo(id_sexo);		
		long cod_pegador = sexo.getPegador().getCodigo_sexual();
		
		if(sexo != null){
			sexo.setStatus(1);
			controlador.atualizarSexo(sexo);
			
			String apelido_pegado = controlador.getApelidoMembro(codigo_sexual);
			Membro membro = controlador.getMembroMaisSimplesAinda(cod_pegador);
			SendMail.sendMail("MarqueSexo - " + apelido_pegado + " confirmou que vocês se pegaram!", "Olá " + membro.getNome() + "\n\n" + apelido_pegado + " (código sexual " + codigo_sexual + "), disse que vocês realmente se pegaram!! \nParabéns! Esperamos que você continue pegando todo mundo! \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
			
			request.setAttribute("confirmado",true);
			request.setAttribute("id_sexo",id_sexo);
			
			url = "comentar_japeguei.jsp";
		}else{
			url = "erro.jsp";
		}
		
		return url;
		
	}
    
    
    public String desmentirjapegou(HttpServletRequest request, long codigo_sexual)throws ServletException {
		
		long id_sexo = 0;
		if(request.getParameter("id_sexo") != null){
			id_sexo = (new Long(request.getParameter("id_sexo"))).longValue();			
		} else if(request.getAttribute("id_sexo") != null){
			id_sexo = ((Long) request.getAttribute("id_sexo"));
		}
		
						
		String url = "";	
		Sexo sexo = controlador.getSexo(id_sexo);
		long cod_pegador = sexo.getPegador().getCodigo_sexual();
			
		if(controlador.excluirSexo(id_sexo)){
			String apelido_pegado = controlador.getApelidoMembro(codigo_sexual);
			Membro membro = controlador.getMembroMaisSimplesAinda(cod_pegador);
			SendMail.sendMail("MarqueSexo - " + apelido_pegado + " disse que você não pegou!", "Olá " + membro.getNome() + "\n\n" + apelido_pegado + " (código sexual " + codigo_sexual + "), disse que vocês não se pegaram!! \nQue é mentira sua! \nQue pena! \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
			
			url = "japeguei";
		}else{
			url = "erro.jsp";
		}
		
		return url;
		
	}
    
    
    public String definirparticipantes(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
        
        ArrayList lista = (ArrayList) controlador.getParticipacoesSurubaPelaSuruba(id_suruba);
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);
        String url = "";    
        
        if(codigo_sexual == organizador.getCodigo_sexual()){        
	        request.setAttribute("listaPartipacoes",lista);
	        request.setAttribute("id_suruba",id_suruba);
	        request.setAttribute("organizador",organizador);
	        
	        url = "definir_participantes.jsp";
        }else{
        	url = "erro_permissao.jsp";
        }
        
        return url;
    }
    
       
    
    public String definirquemparticipou(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
        
        ArrayList lista = (ArrayList) controlador.getParticipacoesSurubaAceitasPelaSuruba(id_suruba);
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);
        String url = "";    
        
        if(codigo_sexual == organizador.getCodigo_sexual()){        
        
	        request.setAttribute("listaPartipacoes",lista);
	        request.setAttribute("id_suruba",id_suruba);
	        request.setAttribute("organizador",organizador);
	        
	        url = "definir_quem_participou.jsp";
	        
	    }else{
	    	url = "erro_permissao.jsp";
	    }
        
        return url;
    }
    
      
    public String minhassurubas(HttpServletRequest request, long codigo_sexual)throws ServletException {
     
        String url = "";    
        ArrayList listaSurubasAbertas = (ArrayList)controlador.getSurubasAbertasPeloOrganizador(codigo_sexual);
        ArrayList listaSurubasFinalizadas = (ArrayList)controlador.getSurubasFinalizadasPeloOrganizador(codigo_sexual);
               
        request.setAttribute("listaSurubasAbertas",listaSurubasAbertas);
        request.setAttribute("listaSurubasFinalizadas",listaSurubasFinalizadas);

        url = "minhas_surubas.jsp";   
        
        return url;
        
    }
    
    
    public String resultadobuscasurubasdisponiveis(HttpServletRequest request)throws ServletException {
        
        HttpSession session = request.getSession(false);
                
        String cidade_busca = "";
        if (request.getParameter("cidade_busca") != null) {
            cidade_busca = new String(request.getParameter("cidade_busca"));
        } else if(request.getAttribute("cidade_busca") != null){
            cidade_busca = ((String) request.getAttribute("cidade_busca"));
        }
        
        String estado_busca = "";
        if (request.getParameter("estado_busca") != null) {
            estado_busca = new String(request.getParameter("estado_busca"));
        } else if(request.getAttribute("estado_busca") != null){
            estado_busca = ((String) request.getAttribute("estado_busca"));
        }
        
       
        ArrayList<Suruba> lista = null;
        session.setAttribute("listaSurubas",null);
        
        
        String url = "";
        lista = (ArrayList<Suruba>)controlador.buscaSurubasDisponives(estado_busca, cidade_busca);
        session.setAttribute("listaSurubas",lista);
        url = "resultado_busca_surubas_disp.jsp";
                
        return url;
        
    }
    
    
    public String resultadobuscasurubasfinalizadas(HttpServletRequest request)throws ServletException {
        
        HttpSession session = request.getSession(false);
                
        String cidade_busca = "";
        if (request.getParameter("cidade_busca") != null) {
            cidade_busca = new String(request.getParameter("cidade_busca"));
        } else if(request.getAttribute("cidade_busca") != null){
            cidade_busca = ((String) request.getAttribute("cidade_busca"));
        }
        
        String estado_busca = "";
        if (request.getParameter("estado_busca") != null) {
            estado_busca = new String(request.getParameter("estado_busca"));
        } else if(request.getAttribute("estado_busca") != null){
            estado_busca = ((String) request.getAttribute("estado_busca"));
        }
        
       
        ArrayList<Suruba> lista = null;
        session.setAttribute("listaSurubas",null);
        
        
        String url = "";
        lista = (ArrayList<Suruba>)controlador.buscaSurubasFinalizadas(estado_busca, cidade_busca);
        session.setAttribute("listaSurubas",lista);
        url = "resultado_busca_surubas_fin.jsp";
        

        
        return url;
        
    }
    
    
    public String versuruba(HttpServletRequest request)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
               
        String url = "";         
        Suruba suruba = controlador.getSuruba(id_suruba);
        List comentarios = controlador.getComentariosSurubaPelaSuruba(id_suruba);
        List participacoes = controlador.getParticipacoesSurubaAceitasFoiPelaSuruba(id_suruba);
        
        request.setAttribute("suruba",suruba);
        request.setAttribute("listaComentarios",comentarios);
        request.setAttribute("listaParticipacoes",participacoes);
        
        url = "ver_suruba.jsp";
        
        return url;
    }
    
    
    public String editarsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        Suruba suruba = controlador.getSuruba(id_suruba); 
               
        if(suruba != null){
        	Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
            if(codigo_sexual == organizador.getCodigo_sexual()){ 
            	request.setAttribute("suruba", suruba);
            	url = "editar_suruba.jsp";
            }else{
                url = "erro_permissao.jsp";
            } 
        }else{
            url = "erro.jsp";
        }        
        return url;
        
    }

    
    public String enviareditarsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
                
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
        
        String titulo = "";
        if (request.getParameter("titulo") != null) {
            titulo = new String(request.getParameter("titulo"));
        } else if(request.getAttribute("titulo") != null){
            titulo = ((String) request.getAttribute("titulo"));
        }
        
        boolean valido = true;
		int dia = 0;
		if (request.getParameter("dia") != null) {
			try {
				dia = new Long(request.getParameter("dia")).intValue();
			} catch (Exception e) {
				valido = false;
			}
			
		} else {
			try {
				dia = ((Long) request.getAttribute("dia")).intValue();
			} catch (Exception e) {
				valido = false;
			}
		}	
		
		int mes = 0;
		if (request.getParameter("mes") != null) {
			try {
				mes = new Long(request.getParameter("mes")).intValue();
			} catch (Exception e) {
				valido = false;
			}
			
		} else {
			try {
				mes = ((Long) request.getAttribute("mes")).intValue();
			} catch (Exception e) {
				valido = false;
			}
		}
		
		
		int ano = 0;
		if (request.getParameter("ano") != null) {
			try {
				ano = new Long(request.getParameter("ano")).intValue();
			} catch (Exception e) {
				valido = false;
			}
			
		} else {
			try {
				ano = ((Long) request.getAttribute("ano")).intValue();
			} catch (Exception e) {
				valido = false;
			}
		}

		
		Timestamp data = null;
		Calendar cal = new GregorianCalendar();
		try {
			if(valido == true){
				cal.set(ano, mes - 1, dia);
				data = new Timestamp(cal.getTime().getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String hora = "";
        if (request.getParameter("hora") != null) {
            hora = new String(request.getParameter("hora"));
        } else if(request.getAttribute("hora") != null){
            hora = ((String) request.getAttribute("hora"));
        }
        
        String minuto = "";
        if (request.getParameter("minuto") != null) {
            minuto = new String(request.getParameter("minuto"));
        } else if(request.getAttribute("minuto") != null){
            minuto = ((String) request.getAttribute("minuto"));
        }
        
        String local = "";
        if (request.getParameter("local") != null) {
            local = new String(request.getParameter("local"));
        } else if(request.getAttribute("local") != null){
            local = ((String) request.getAttribute("local"));
        }
        
        String cidade = "";
        if (request.getParameter("cidade") != null) {
            cidade = new String(request.getParameter("cidade"));
        } else if(request.getAttribute("cidade") != null){
            cidade = ((String) request.getAttribute("cidade"));
        }
        
        String estado = "";
        if (request.getParameter("estado") != null) {
            estado = new String(request.getParameter("estado"));
        } else if(request.getAttribute("estado") != null){
            estado = ((String) request.getAttribute("estado"));
        }
        
        String obs = "";
        if (request.getParameter("obs") != null) {
            obs = new String(request.getParameter("obs"));
        } else if(request.getAttribute("obs") != null){
            obs = ((String) request.getAttribute("obs"));
        }
                
        String url = "";    
        
        Suruba suruba = controlador.getSuruba(id_suruba); 
        if(suruba != null){
        	Membro organizador = controlador.getOrganizadorSuruba(id_suruba);   
        	if(codigo_sexual == organizador.getCodigo_sexual()){ 
        		if(titulo.equals("")){
                	url = "erro_invalido.jsp";
                }else{
		            suruba.setCidade(cidade);
		            suruba.setEstado(estado);
		            suruba.setData(data);
		            suruba.setHora(hora);
		            suruba.setMinuto(minuto);
		            suruba.setLocal(local);
		            suruba.setObs(obs);
		            suruba.setTitulo(titulo);
		            controlador.atualizarSuruba(suruba);
		            url = "minhassurubas";
                }
        	}else{
                url = "erro_permissao.jsp";
            }
        }else{
            url = "erro.jsp";
        }

        
        return url;
        
    }
    
    public String organizarsuruba(HttpServletRequest request)throws ServletException {
    	String url = "";   
    	url = "organizar_suruba.jsp";
    	request.setAttribute("dia_atual", String.valueOf(new Date().getDate()));
    	request.setAttribute("mes_atual", String.valueOf(new Date().getMonth()+1));
		request.setAttribute("ano_atual", String.valueOf(new Date().getYear()+1900));

    	return url;
    }
    
    
    public String enviarorganizarsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        String titulo = "";
        if (request.getParameter("titulo") != null) {
            titulo = new String(request.getParameter("titulo"));
        } else if(request.getAttribute("titulo") != null){
            titulo = ((String) request.getAttribute("titulo"));
        }
        
        boolean valido = true;
		int dia = 0;
		if (request.getParameter("dia") != null) {
			try {
				dia = new Long(request.getParameter("dia")).intValue();
			} catch (Exception e) {
				valido = false;
			}
			
		} else {
			try {
				dia = ((Long) request.getAttribute("dia")).intValue();
			} catch (Exception e) {
				valido = false;
			}
		}	
		
		int mes = 0;
		if (request.getParameter("mes") != null) {
			try {
				mes = new Long(request.getParameter("mes")).intValue();
			} catch (Exception e) {
				valido = false;
			}
			
		} else {
			try {
				mes = ((Long) request.getAttribute("mes")).intValue();
			} catch (Exception e) {
				valido = false;
			}
		}
		
		
		int ano = 0;
		if (request.getParameter("ano") != null) {
			try {
				ano = new Long(request.getParameter("ano")).intValue();
			} catch (Exception e) {
				valido = false;
			}
			
		} else {
			try {
				ano = ((Long) request.getAttribute("ano")).intValue();
			} catch (Exception e) {
				valido = false;
			}
		}

		
		Timestamp data = null;
		Calendar cal = new GregorianCalendar();
		try {
			if(valido == true){
				cal.set(ano, mes - 1, dia);
				data = new Timestamp(cal.getTime().getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String hora = "";
        if (request.getParameter("hora") != null) {
            hora = new String(request.getParameter("hora"));
        } else if(request.getAttribute("hora") != null){
            hora = ((String) request.getAttribute("hora"));
        }
        
        String minuto = "";
        if (request.getParameter("minuto") != null) {
            minuto = new String(request.getParameter("minuto"));
        } else if(request.getAttribute("minuto") != null){
            minuto = ((String) request.getAttribute("minuto"));
        }
        
        String local = "";
        if (request.getParameter("local") != null) {
            local = new String(request.getParameter("local"));
        } else if(request.getAttribute("local") != null){
            local = ((String) request.getAttribute("local"));
        }
        
        String cidade = "";
        if (request.getParameter("cidade") != null) {
            cidade = new String(request.getParameter("cidade"));
        } else if(request.getAttribute("cidade") != null){
            cidade = ((String) request.getAttribute("cidade"));
        }
        
        String estado = "";
        if (request.getParameter("estado") != null) {
            estado = new String(request.getParameter("estado"));
        } else if(request.getAttribute("estado") != null){
            estado = ((String) request.getAttribute("estado"));
        }
        
        String obs = "";
        if (request.getParameter("obs") != null) {
            obs = new String(request.getParameter("obs"));
        } else if(request.getAttribute("obs") != null){
            obs = ((String) request.getAttribute("obs"));
        }
                
        String url = "";    
        Membro membro = new Membro(codigo_sexual);
        
        if(titulo.equals("")){
        	url = "erro_invalido.jsp";
        }else{
        
	        Suruba suruba = new Suruba(); 
	        suruba.setOrganizador(membro);
	        suruba.setCidade(cidade);
	        suruba.setEstado(estado);
	        suruba.setData(data);
	        suruba.setHora(hora);
	        suruba.setMinuto(minuto);
	        suruba.setLocal(local);
	        suruba.setObs(obs);
	        suruba.setTitulo(titulo);
	        suruba.setStatus(1);
	        
	        if(controlador.inserirSuruba(suruba)){          	
	        	
	        	long id_suruba = controlador.getIdSuruba(titulo, cidade, codigo_sexual);
	        	suruba.setId(id_suruba);
	        	
	        	ParticipacaoSuruba participacao = new ParticipacaoSuruba();             
	            participacao.setSuruba(suruba);
	            participacao.setStatus(1); // aceito
	            participacao.setParticipante(membro);
	            controlador.inserirParticipacaoSuruba(participacao);        	
	            url = "confirmacao.jsp";
	        }else{
	            url = "erro.jsp";
	        }
        }

        
        return url;
        
    }
    
    
    public String participarsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {

        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        if(controlador.hasParticipacaoSuruba(id_suruba, codigo_sexual) == false){
            Suruba suruba = controlador.getSurubaSimples(id_suruba); 
            request.setAttribute("suruba", suruba);
            url = "confirmar_participacao.jsp";
        }else{
            url = "erro_participacao.jsp";
        }        
        return url;
        
    }
    
    
    
    public String enviarconfirmarparticipacao(HttpServletRequest request, long codigo_sexual)throws ServletException {

        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        ParticipacaoSuruba participacao = new ParticipacaoSuruba(); 
        Suruba suruba = controlador.getSurubaSimples(id_suruba); 
        Membro membro = controlador.getMembroMaisSimplesAinda(codigo_sexual);
        
        participacao.setSuruba(suruba);
        participacao.setStatus(0); // aguardando
        participacao.setParticipante(membro);
        
        if(controlador.inserirParticipacaoSuruba(participacao)){
            Membro organizador = controlador.getMembroMaisSimplesAinda(suruba.getOrganizador().getCodigo_sexual());
            SendMail.sendMail("MarqueSexo - " + membro.getApelido() + " quer participar da sua suruba!", "Olá " + organizador.getNome() + "\n\n" + membro.getApelido() + " (código sexual " + codigo_sexual + "), quer participar da suruba: " + suruba.getTitulo() + "! \nAcesse o MarqueSexo, vá em 'Surubas'->'Minhas Surubas'->'Definir Participantes' e aceite ou recuse essa pessoa!! \n\nAtenciosamente \nEquipe MarqueSexo", organizador.getEmail());
            url = "surubas.jsp";
        }else{
            url = "erro.jsp";
        }        
        return url;
        
    }
    
    
    public String comentarsuruba(HttpServletRequest request)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
        
        Suruba suruba = controlador.getSurubaSimples(id_suruba);
                
        String url = "";    
        request.setAttribute("suruba", suruba);
        request.setAttribute("id_suruba", id_suruba);
        url = "comentar_suruba.jsp";        
        return url;
        
    }
    
    public String enviarcomentariosuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
        
        String comentario = "";
        if (request.getParameter("comentario") != null) {
            comentario = new String(request.getParameter("comentario"));
        } else if(request.getAttribute("comentario") != null){
            comentario = ((String) request.getAttribute("comentario"));
        }
                
        String url = "";    
        
        Suruba suruba = new Suruba(id_suruba);
        ParticipacaoSuruba participacao = controlador.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sexual);
        
        if(participacao != null){
            ComentarioSuruba comentarios = new ComentarioSuruba();
            comentarios.setParticipacao(participacao);
            comentarios.setSuruba(suruba);
            comentarios.setComentario(comentario);
            
            if(controlador.inserirComentarioSuruba(comentarios)){
                url = "confirmacao.jsp";
            }else{
                url = "erro.jsp";
            }
        }else{
            url = "erro_permissao.jsp";
        }      
        return url;
        
    }

    

    
    public String enviardesistirsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        ParticipacaoSuruba participacao = controlador.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sexual);
        if(participacao != null){
	        Suruba suruba = controlador.getSuruba(participacao.getSuruba().getId());
	        participacao.setSuruba(suruba);
	                
	        if(controlador.atualizarStatusParticipacaoSuruba(participacao.getId(), 3)){
	            Membro organizador = controlador.getMembroMaisSimplesAinda(participacao.getSuruba().getOrganizador().getCodigo_sexual());
	            Membro membro = controlador.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
	            SendMail.sendMail("MarqueSexo - " + membro.getApelido() + " desistiu de participar da sua suruba!", "Olá " + organizador.getNome() + "\n\n" + membro.getApelido() + " (código sexual " + codigo_sexual + "), desistiu de participar da suruba: " + participacao.getSuruba().getTitulo() + "! \nQue pena!! \n\nAtenciosamente \nEquipe MarqueSexo", organizador.getEmail());
	            url = "minhassurubas";
	        }else{
	            url = "erro.jsp";
	        }
        }else{
            url = "erro_permissao.jsp";
        }        
        return url;
        
    }
    
    
    public String enviaraceitarparticipantesuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long codigo_sex = 0;
        if(request.getParameter("codigo_sex") != null){
        	codigo_sex = (new Long(request.getParameter("codigo_sex"))).longValue();          
        } else if(request.getAttribute("codigo_sex") != null){
        	codigo_sex = ((Long) request.getAttribute("codigo_sex"));
        }
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
        if(codigo_sexual == organizador.getCodigo_sexual()){ 
        
	        ParticipacaoSuruba participacao = controlador.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sex);
	        if(participacao != null){
		        Suruba suruba = controlador.getSuruba(participacao.getSuruba().getId());
		        participacao.setSuruba(suruba);
		                
		        if(controlador.atualizarStatusParticipacaoSuruba(participacao.getId(), 1)){            
		            Membro membro = controlador.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
		            SendMail.sendMail("MarqueSexo - Você foi aceito para participar da sua suruba!", "Olá " + membro.getNome() + "\n\n" + "Você foi aceito para participar da suruba: " + participacao.getSuruba().getTitulo() + "! \nAcesse o MarqueSexo e tenha mais informações desta suruba em 'Surubas Confirmadas para Ir'. \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
		            url = "minhassurubas";
		        }else{
		            url = "erro.jsp";
		        }   
	        }else{
		        url = "erro_permissao.jsp";
		    } 
	    }else{
	        url = "erro_permissao.jsp";
	    } 
        return url;
        
    }
    
    
    public String enviarrecusarparticipantesuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
    	long codigo_sex = 0;
        if(request.getParameter("codigo_sex") != null){
        	codigo_sex = (new Long(request.getParameter("codigo_sex"))).longValue();          
        } else if(request.getAttribute("codigo_sex") != null){
        	codigo_sex = ((Long) request.getAttribute("codigo_sex"));
        }
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
        if(codigo_sexual == organizador.getCodigo_sexual()){ 
	        ParticipacaoSuruba participacao = controlador.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sex);
	        if(participacao != null){
		        Suruba suruba = controlador.getSuruba(participacao.getSuruba().getId());
		        participacao.setSuruba(suruba);
		                
		        if(controlador.atualizarStatusParticipacaoSuruba(participacao.getId(), 2)){            
		            Membro membro = controlador.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
		            SendMail.sendMail("MarqueSexo - Você foi recusado para participar da sua suruba!", "Olá " + membro.getNome() + "\n\n" + "Você foi não foi aceito para participar da suruba: " + participacao.getSuruba().getTitulo() + "! \nQue pena!! \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
		            url = "minhassurubas";
		        }else{
		            url = "erro.jsp";
		        }    
	        }else{
		        url = "erro_permissao.jsp";
		    }
        }else{
	        url = "erro_permissao.jsp";
	    }
        return url;
        
    }
    
    
    public String enviarparticipantefoisuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
    	long codigo_sex = 0;
        if(request.getParameter("codigo_sex") != null){
        	codigo_sex = (new Long(request.getParameter("codigo_sex"))).longValue();          
        } else if(request.getAttribute("codigo_sex") != null){
        	codigo_sex = ((Long) request.getAttribute("codigo_sex"));
        }
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
        if(codigo_sexual == organizador.getCodigo_sexual()){ 

	        ParticipacaoSuruba participacao = controlador.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sex);
	        if(participacao != null){
		        Suruba suruba = controlador.getSuruba(participacao.getSuruba().getId());
		        participacao.setSuruba(suruba);
		                
		        if(controlador.atualizarStatusParticipacaoSuruba(participacao.getId(), 4)){                   	
		            Membro membro = controlador.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
		            if(organizador.getCodigo_sexual() != codigo_sex){
		            	SendMail.sendMail("MarqueSexo - Disseram que você realmente foi na suruba!", "Olá " + membro.getNome() + "\n\n" + "O organizador da suruba, " + organizador.getApelido() + "(" + organizador.getCodigo_sexual() + ")" + ", disse que você realmente foi na suruba: " + participacao.getSuruba().getTitulo() + "! \nQue legal!! Esperamos que tenha sido bom para você! \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
		            }
		            url = "minhassurubas";
		        }else{
		            url = "erro.jsp";
		        }    
	        }else{
		        url = "erro_permissao.jsp";
		    }
        }else{
	        url = "erro_permissao.jsp";
	    }
        return url;
        
    }
    
    
    public String enviarparticipantenaofoisuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
    	long codigo_sex = 0;
        if(request.getParameter("codigo_sex") != null){
        	codigo_sex = (new Long(request.getParameter("codigo_sex"))).longValue();          
        } else if(request.getAttribute("codigo_sex") != null){
        	codigo_sex = ((Long) request.getAttribute("codigo_sex"));
        }
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
        if(codigo_sexual == organizador.getCodigo_sexual()){ 
        
	        ParticipacaoSuruba participacao = controlador.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sex);
	        Suruba suruba = controlador.getSuruba(participacao.getSuruba().getId());
	        participacao.setSuruba(suruba);
	                
	        if(controlador.atualizarStatusParticipacaoSuruba(participacao.getId(), 5)){
	            Membro membro = controlador.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
	            if(organizador.getCodigo_sexual() != codigo_sex){
	            	SendMail.sendMail("MarqueSexo - Disseram que você não participou da suruba!", "Olá " + membro.getNome() + "\n\n" + "O organizador da suruba, " + organizador.getApelido() + "(" + organizador.getCodigo_sexual() + ")" + ", disse que você não foi na suruba: " + participacao.getSuruba().getTitulo() + "! \nSe realmente você não foi, fazer o quê né?! Mas se você foi, entre em contato com organizador e reclame!! \nInclua um comentário na suruba e explique o que aconteceu!\n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
	            }
	            url = "minhassurubas";
	        }else{
	            url = "erro.jsp";
	        }
        }else{
	        url = "erro_permissao.jsp";
	    }
        return url;
        
    }
    
    
    public String cancelarsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {

        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        Suruba suruba = controlador.getSuruba(id_suruba);
                      
        if(suruba != null){      
        	Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
            if(codigo_sexual == organizador.getCodigo_sexual()){ 
	            request.setAttribute("suruba", suruba);
	            url = "cancelar_suruba.jsp";
            }else{
    	        url = "erro_permissao.jsp";
    	    }
        }else{
            url = "erro.jsp";
        }        
        return url;
        
    }
    
    public String enviarcancelarsuruba(HttpServletRequest request, long codigo_sexual)throws ServletException {
        
        long id_suruba = 0;
        if(request.getParameter("id_suruba") != null){
            id_suruba = (new Long(request.getParameter("id_suruba"))).longValue();          
        } else if(request.getAttribute("id_suruba") != null){
            id_suruba = ((Long) request.getAttribute("id_suruba"));
        }
                               
        String url = "";    
        Membro organizador = controlador.getOrganizadorSuruba(id_suruba);            
        if(codigo_sexual == organizador.getCodigo_sexual()){ 
        
	        ArrayList lista = (ArrayList)controlador.getParticipacoesSurubaAceitasPelaSuruba(id_suruba);
	        String titulo_suruba = controlador.getTituloSuruba(id_suruba);
	        ParticipacaoSuruba participacao = null;
	        String apelido_organizador = controlador.getApelidoMembro(codigo_sexual);
	        Membro membro = null;
	        for(int i = 0; i < lista.size(); i++){
	            participacao = (ParticipacaoSuruba)lista.get(i);
	            membro = participacao.getParticipante();
	            SendMail.sendMail("MarqueSexo - " + apelido_organizador + " desistiu de participar da sua suruba!", "Olá " + membro.getNome() + "\n\n" + apelido_organizador + " (código sexual " + codigo_sexual + "), cancelou a suruba (" + titulo_suruba + ") que você ia participar! \nQue pena!! \n\nAtenciosamente \nEquipe MarqueSexo", membro.getEmail());
	        }
	                
	        if(controlador.excluirSuruba(id_suruba)){          
	            url = "minhassurubas";
	        }else{
	            url = "erro.jsp";
	        }        
        }else{
	        url = "erro_permissao.jsp";
	    }
        return url;
        
    }
    
    

    public String surubasparticipou(HttpServletRequest request)throws ServletException {
        
        long codigo_sexual_suruba = 0;
        if(request.getParameter("codigo_sexual_suruba") != null){
        	codigo_sexual_suruba = (new Long(request.getParameter("codigo_sexual_suruba"))).longValue();          
        } else if(request.getAttribute("codigo_sexual_suruba") != null){
        	codigo_sexual_suruba = ((Long) request.getAttribute("codigo_sexual_suruba"));
        }
        
        String url = "";    
        ArrayList listaSurubasParticipou = (ArrayList)controlador.getSurubasParticipanteFoi(codigo_sexual_suruba);
        int qtdSurubasNaoFoi = controlador.getQtdParticipacoesSurubaPeloStatusPeloParticipante(5, codigo_sexual_suruba);
        int qtdSurubasRecusado = controlador.getQtdParticipacoesSurubaPeloStatusPeloParticipante(2, codigo_sexual_suruba);
        int qtdSurubasDesistiu = controlador.getQtdParticipacoesSurubaPeloStatusPeloParticipante(3, codigo_sexual_suruba);
                  
        request.setAttribute("listaSurubasParticipou",listaSurubasParticipou);
        request.setAttribute("qtdSurubasNaoFoi",qtdSurubasNaoFoi);
        request.setAttribute("qtdSurubasRecusado",qtdSurubasRecusado);
        request.setAttribute("qtdSurubasDesistiu",qtdSurubasDesistiu);

        url = "surubas_participou.jsp";   
        
        return url;
        
    }
    
    public String surubasquisparticipar(HttpServletRequest request, long codigo_sexual)throws ServletException {
                
        String url = "";    
        ArrayList listaSurubasFoi = (ArrayList)controlador.getSurubasParticipanteFoi(codigo_sexual);
        ArrayList listaSurubasNaoFoi = (ArrayList)controlador.getSurubasParticipanteNaoFoi(codigo_sexual);
        ArrayList listaSurubasRecusado = (ArrayList)controlador.getSurubasParticipanteRecusado(codigo_sexual);
        ArrayList listaSurubasDesistiu = (ArrayList)controlador.getSurubasParticipanteDesistiu(codigo_sexual);
        ArrayList listaSurubasAguardando = (ArrayList)controlador.getSurubasParticipanteAguardando(codigo_sexual);
                  
        request.setAttribute("listaSurubasFoi",listaSurubasFoi);
        request.setAttribute("listaSurubasNaoFoi",listaSurubasNaoFoi);
        request.setAttribute("listaSurubasRecusado",listaSurubasRecusado);
        request.setAttribute("listaSurubasDesistiu",listaSurubasDesistiu);
        request.setAttribute("listaSurubasAguardando",listaSurubasAguardando);

        url = "surubas_quis_participar.jsp";   
        
        return url;
        
    }
    
    

    public String surubasconfirmadas(HttpServletRequest request, long codigo_sexual)throws ServletException {

        String url = "";    
        ArrayList listaSurubasAceito = (ArrayList)controlador.getSurubasParticipanteAceito(codigo_sexual);
                  
        request.setAttribute("listaSurubasAceito",listaSurubasAceito);


        url = "surubas_confirmadas.jsp";   
        
        return url;
        
    }
    
    public String verresultadoenquete(HttpServletRequest request)throws ServletException {
        
        long id = 0;
		if (request.getParameter("id_enquete") != null) {
			id = new Long(request.getParameter("id_enquete")).longValue();
		} else if (request.getAttribute("id_enquete") != null) {
			id = ((Long) request.getAttribute("id_enquete")).longValue();
		}	
		
		int qtd_total = controlador.getQtdVotosEnquete(id);
    	int qtd = 0;
    	
    	ArrayList respostas = (ArrayList) controlador.getRespostasEnquete(id);
    	ArrayList resultados = new ArrayList();
    	
		ResultadoEnquete resultadoEnquete = null;
		Resposta resposta = null;
		
		Iterator respostasIterator = respostas.iterator();
		while (respostasIterator.hasNext()) {
			
			resposta = (Resposta) respostasIterator.next();
			resultadoEnquete = new ResultadoEnquete();
			resultadoEnquete.setTexto(resposta.getTexto());
			qtd = controlador.getQtdVotosResposta(resposta.getId());
			resultadoEnquete.setQtd(qtd);
			if(qtd_total != 0)
				resultadoEnquete.setPorcentagem((qtd*100)/qtd_total);
			else
				resultadoEnquete.setPorcentagem(0);
			resultados.add(resultadoEnquete);
		}
                
        String url = "";    
        Enquete enquete = controlador.getEnquete(id);
                   
        request.setAttribute("resultados",resultados);
        request.setAttribute("enquete",enquete);

        url = "ver_resultado_enquete.jsp";   
        
        return url;
        
    }
    
    
    public String verenquetes(HttpServletRequest request)throws ServletException {
                               
        String url = "";    
        ArrayList enquetes = (ArrayList)controlador.getEnquetes();                  
        request.setAttribute("enquetes",enquetes);
        url = "ver_enquetes.jsp";   
        
        return url;
        
    }
    
    public String votarenquete(HttpServletRequest request)throws ServletException {
    	
    	long id = 0;
		if (request.getParameter("id_enquete") != null) {
			id = new Long(request.getParameter("id_enquete")).longValue();
		} else if (request.getAttribute("id_enquete") != null) {
			id = ((Long) request.getAttribute("id_enquete")).longValue();
		}
		
		Enquete enquete = controlador.getEnquete(id);
        
        String url = "";    
        ArrayList respostas = (ArrayList)controlador.getRespostasEnquete(id);
                  
        request.setAttribute("respostas",respostas);
        request.setAttribute("enquete",enquete);


        url = "votar_enquete.jsp";   
        
        return url;
        
    }
    
    
    public String enviarvotoenquete(HttpServletRequest request)throws ServletException {
                
        long id_resposta = 0;
		if (request.getParameter("voto") != null) {
			id_resposta = new Long(request.getParameter("voto")).longValue();
		} else {
			id_resposta = ((Long) request.getAttribute("voto")).longValue();
		}
		
		Resposta resposta = controlador.getResposta(id_resposta);
		Voto voto = new Voto();
		voto.setResposta(resposta);
		Enquete enquete = new Enquete();
		enquete.setId(resposta.getEnquete().getId());
		voto.setEnquete(enquete);

        String url = "";    
        if(controlador.inserirVoto(voto)){          
            url = "verenquetes";
        }else{
            url = "erro.jsp";
        }   
        
        return url;
        
    }
    

}
