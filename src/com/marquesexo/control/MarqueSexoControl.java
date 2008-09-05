package com.marquesexo.control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Bloqueado;
import com.marquesexo.bean.ComentarioSuruba;
import com.marquesexo.bean.Enquete;
import com.marquesexo.bean.Fotos;
import com.marquesexo.bean.Interessante;
import com.marquesexo.bean.Membro;
import com.marquesexo.bean.Motel;
import com.marquesexo.bean.ParticipacaoSuruba;
import com.marquesexo.bean.Resposta;
import com.marquesexo.bean.Sexo;
import com.marquesexo.bean.Suruba;
import com.marquesexo.bean.Voto;
import com.marquesexo.bean.Webcam;
import com.marquesexo.chat.ChatRoomList;
import com.marquesexo.dao.BloqueadoDAO;
import com.marquesexo.dao.ComentarioSurubaDAO;
import com.marquesexo.dao.EnqueteDAO;
import com.marquesexo.dao.FotosDAO;
import com.marquesexo.dao.InteressanteDAO;
import com.marquesexo.dao.MembroDAO;
import com.marquesexo.dao.MotelDAO;
import com.marquesexo.dao.ParticipacaoSurubaDAO;
import com.marquesexo.dao.RespostaDAO;
import com.marquesexo.dao.SexoDAO;
import com.marquesexo.dao.SurubaDAO;
import com.marquesexo.dao.VotoDAO;
import com.marquesexo.dao.WebcamDAO;


public class MarqueSexoControl {
	
	private BloqueadoDAO bloqueadoDAO;
	private FotosDAO fotosDAO;
	private InteressanteDAO interessanteDAO;
	private MembroDAO membroDAO;
	private MotelDAO motelDAO;
	private WebcamDAO webcamDAO;
	private SexoDAO sexoDAO;
    private SurubaDAO surubaDAO;
    private ParticipacaoSurubaDAO participacaoDAO;
    private ComentarioSurubaDAO comentarioDAO;
    private EnqueteDAO enqueteDAO;
    private VotoDAO votoDAO;
    private RespostaDAO respostaDAO;

	
	public MarqueSexoControl(){
		bloqueadoDAO = new BloqueadoDAO();
		fotosDAO = new FotosDAO();
		interessanteDAO = new InteressanteDAO();
		membroDAO = new MembroDAO();
		motelDAO = new MotelDAO();		
		webcamDAO = new WebcamDAO();
		sexoDAO = new SexoDAO();
        surubaDAO = new SurubaDAO();
        participacaoDAO = new ParticipacaoSurubaDAO();
        comentarioDAO = new ComentarioSurubaDAO();
        enqueteDAO = new EnqueteDAO();
        votoDAO = new VotoDAO();
        respostaDAO = new RespostaDAO();
	}
	
	
	public Membro autenticarMembro(String email, String senha){
		Membro membro = membroDAO.autenticarMembro(email, senha);
		return membro;
	}
	
	public boolean atualizarSituacao(long codigo_sexual, int situacao){
		return membroDAO.atualizarSituacao(codigo_sexual, situacao);		
	}
	
	public boolean habilitarWebcam(long codigo_sexual){
		return webcamDAO.habilitar(codigo_sexual);		
	}
	
	public boolean desabilitarWebcam(long codigo_sexual){
		return webcamDAO.desabilitar(codigo_sexual);		
	}
	
	public boolean isHabilitadoWebcam(long codigo_sexual){
		return webcamDAO.isHabilitado(codigo_sexual);		
	}
	
	public Webcam getWebcam(long codigo_sexual){
		return webcamDAO.getWebcam(codigo_sexual);		
	}
	
	public boolean atualizarWebcam(Webcam cam){
		boolean has = webcamDAO.hasWebcam(cam.getId_membro());
		if(has == true)
			return webcamDAO.atualizar(cam);
		else
			return webcamDAO.inserir(cam);
	}
	
	public boolean excluirFotos(long id_fotos){
		return fotosDAO.excluir(id_fotos);		
	}
	
	public boolean excluirBloqueado(long id_bloqueado){
		return bloqueadoDAO.excluir(id_bloqueado);		
	}
	
	public boolean excluirInteressante(long id_interessante){
		return interessanteDAO.excluir(id_interessante);		
	}
	
	public boolean excluirMembro(long codigo_sexual){
		return membroDAO.excluir(codigo_sexual);		
	}
	
	public boolean excluirMotel(long id_motel){
		return motelDAO.excluir(id_motel);		
	}
	
	public boolean inserirMembro(Membro membro){
		return membroDAO.inserir(membro);		
	}
	
	public boolean inserirInteressante(Interessante interessante){
		return interessanteDAO.inserir(interessante);		
	}
	
	public boolean inserirBloqueado(Bloqueado bloqueado){
		return bloqueadoDAO.inserir(bloqueado);		
	}
	
	public boolean inserirFotos(Fotos fotos){
		return fotosDAO.inserir(fotos);		
	}
	
	public boolean inserirMotel(Motel motel){
		return motelDAO.inserir(motel);		
	}
	
	public boolean atualizaMotel(Motel motel){
		return motelDAO.atualizar(motel);		
	}
	
	public boolean atualizaFoto(long codigo_sexual, int nro_foto, InputStream foto){
		return fotosDAO.atualizarFoto(codigo_sexual, nro_foto, foto);	
	}
	
	public boolean atualizarFotoPrincipal(long codigo_sexual,  InputStream foto){
		return fotosDAO.atualizarFotoPrincipal(codigo_sexual, foto);
	}
			
	public int getNroFotoVazia(long codigo_sexual){
		return fotosDAO.getNroFotoVazia(codigo_sexual);
	}
	
	public boolean atualizaMembro(Membro membro){
		return membroDAO.atualizar(membro);		
	}
	
	public boolean existeEmail(String email){
		return membroDAO.existeEmail(email);	
	}
	
	public Membro getMembroSimples(long codigo_sexual){
		return membroDAO.getMembroSimples(codigo_sexual);
	}
	
	public Membro getPerfilMembro(long codigo_sexual){
		return membroDAO.getPerfilMembro(codigo_sexual);
	}
	
	public Membro getMembroMaisSimples(long codigo_sexual){
		return membroDAO.getMembroMaisSimples(codigo_sexual);
	}
	
	public Membro getMembroMaisSimplesAinda(long codigo_sexual){
		return membroDAO.getMembroMaisSimplesAinda(codigo_sexual);
	}
		
	public Membro getMembro(long codigo_sexual){
		return membroDAO.getMembro(codigo_sexual);
	}
	
	public Membro getMembro(String email){
		return membroDAO.getMembro(email);
	}
	
	public String getNomeMembro(long codigo_sexual){
		return membroDAO.getNomeMembro(codigo_sexual);
	}
	
	public String getEmailMembro(long codigo_sexual){
		return membroDAO.getEmailMembro(codigo_sexual);
	}
	
	public String getApelidoMembro(long codigo_sexual){
		return membroDAO.getApelidoMembro(codigo_sexual);
	}
	
	public Motel getMotel(long id){
		return motelDAO.getMotel(id);
	}
	
	public Fotos getFotos(long codigo_sexual){
		return fotosDAO.getFotos(codigo_sexual);
	}
	
	public Interessante getInteressante(long codigo_sexual_interessado, long codigo_sexual_interessante){
		Interessante interessante= interessanteDAO.getInteressante(codigo_sexual_interessado, codigo_sexual_interessante);
		return interessante;
	}
	
	public long getIdInteressante(long codigo_sexual_interessado, long codigo_sexual_interessante){
		return interessanteDAO.getIdInteressante(codigo_sexual_interessado, codigo_sexual_interessante);
	}
	
	public Bloqueado getBloqueado(long codigo_sexual_bloqueante, long codigo_sexual_bloqueado){
		Bloqueado bloqueado= bloqueadoDAO.getBloqueado(codigo_sexual_bloqueante, codigo_sexual_bloqueado);
		return bloqueado;
	}
	
	public long getIdBloqueado(long codigo_sexual_bloqueante, long codigo_sexual_bloqueado){
		return bloqueadoDAO.getIdBloqueado(codigo_sexual_bloqueante, codigo_sexual_bloqueado);
	}
	
	public boolean isBloqueado(long codigo_sexual, long codigo_sexual_consultado){
		return bloqueadoDAO.isBloqueado(codigo_sexual, codigo_sexual_consultado);
	}
	
	public boolean isInteressante(long codigo_sexual, long codigo_sexual_consultado){
		return interessanteDAO.isInteressante(codigo_sexual, codigo_sexual_consultado);
	}
	
	public List getInteressantes(long codigo_sexual, ChatRoomList roomlist){
		ArrayList lista = (ArrayList)interessanteDAO.getInteressantes(codigo_sexual);
		ArrayList<Membro> lista_membros = new ArrayList<Membro>();
		String nickname = "";
		Interessante inter = null;
		Membro membro = null;
		for(int i = 0; i < lista.size(); i++){
			inter = (Interessante)lista.get(i);
			membro = membroDAO.getMembroSimples(inter.getCodigo_sexual_interessante());
			nickname = membro.getApelido() + "(" + membro.getCodigo_sexual() + ")";
			nickname = nickname.trim();
			membro.setOnline(roomlist.chatterExists(nickname));
			membro.setWebcam_habilitada(isHabilitadoWebcam(membro.getCodigo_sexual()));
			membro.setQtd_pegou(getQtdSexosConfirmados(membro.getCodigo_sexual()));
			lista_membros.add(membro);
		}
		return lista_membros;
	}
	
	public List getBloqueados(long codigo_sexual){
		ArrayList lista = (ArrayList)bloqueadoDAO.getBloqueados(codigo_sexual);
		ArrayList<Membro> lista_membros = new ArrayList<Membro>();
		String nickname = "";
		Bloqueado bloq = null;
		Membro membro = null;
		for(int i = 0; i < lista.size(); i++){
			bloq = (Bloqueado)lista.get(i);
			membro = membroDAO.getMembroSimples(bloq.getCodigo_sexual_bloqueado());
			nickname = membro.getApelido() + "(" + membro.getCodigo_sexual() + ")";
			nickname = nickname.trim();
			lista_membros.add(membro);
		}
		return lista_membros;
		
	}
	
	public List getMoteis(){
		return motelDAO.getMoteis();
	}
	
	public List<Membro> buscaMembros(int faz_sexo_com_busca,int idade_min_busca,int idade_max_busca, float altura_min_busca, float altura_max_busca, String cidade_busca,String estado_busca,int sexo_busca,int tipo_fisico_busca,int tom_pele_busca, int estado_civil_busca, long codigo_sexual_buscador, ChatRoomList roomlist){
		ArrayList lista = (ArrayList)membroDAO.buscaMembros(faz_sexo_com_busca, idade_min_busca, idade_max_busca, altura_min_busca, altura_max_busca,cidade_busca, estado_busca, sexo_busca, tipo_fisico_busca, tom_pele_busca,estado_civil_busca, codigo_sexual_buscador);
		ArrayList<Membro> lista_membros = new ArrayList<Membro>();
		String nickname = "";
        Membro membro = null;
        
		for(int i = 0; i < lista.size(); i++){
			membro = (Membro)lista.get(i);
			if(isBloqueado(membro.getCodigo_sexual(), codigo_sexual_buscador) == false){
				nickname = membro.getApelido() + "(" + membro.getCodigo_sexual()  + ")";
				nickname = nickname.trim();
				membro.setOnline(roomlist.chatterExists(nickname));
				membro.setWebcam_habilitada(isHabilitadoWebcam(membro.getCodigo_sexual()));
				membro.setQtd_pegou(getQtdSexosConfirmados(membro.getCodigo_sexual()));
				lista_membros.add(membro);
			}
		}

		return lista_membros;

	}
	
		
	public boolean excluirSexo(long id_sexo){
		return sexoDAO.excluir(id_sexo);		
	}
	
	public boolean inserirSexo(Sexo sexo){
		return sexoDAO.inserir(sexo);		
	}
	
	public boolean atualizarSexo(Sexo sexo){
		return sexoDAO.atualizar(sexo);		
	}
	
	public boolean atualizarStatusSexo(long id_sexo, int status){
		return sexoDAO.atualizarStatus(id_sexo, status);		
	}
	
	public Sexo getSexo(long id_sexo){
		return sexoDAO.getSexo(id_sexo);		
	}
	
	public Sexo getSexo(long codigo_sexual_pegador, long codigo_sexual_pegado){
		return sexoDAO.getSexo(codigo_sexual_pegador, codigo_sexual_pegado);
	}
	
	public List getSexosConfirmados(long codigo_sexual, ChatRoomList roomlist){
		ArrayList lista = (ArrayList)sexoDAO.getSexosConfirmados(codigo_sexual);
		ArrayList<Sexo> lista_sexos = new ArrayList<Sexo>();
		Membro pegador = null;
		Membro pegado = null;
		String nickname = "";
        Sexo sexo = null;
         
		for(int i = 0; i < lista.size(); i++){
			sexo = (Sexo)lista.get(i);
			
			if(sexo.getPegador().getCodigo_sexual() != codigo_sexual){
				pegador = membroDAO.getMembroMaisSimplesAinda(sexo.getPegador().getCodigo_sexual());	
				nickname = pegador.getApelido() + "(" + pegador.getCodigo_sexual()  + ")";
				nickname = nickname.trim();
				pegador.setOnline(roomlist.chatterExists(nickname));
				pegador.setWebcam_habilitada(isHabilitadoWebcam(pegador.getCodigo_sexual()));
				sexo.setPegador(pegador);			
			}else{
				
				pegado = membroDAO.getMembroMaisSimplesAinda(sexo.getPegado().getCodigo_sexual());				
				nickname = pegado.getApelido() + "(" + pegado.getCodigo_sexual()  + ")";
				nickname = nickname.trim();
				pegado.setOnline(roomlist.chatterExists(nickname));
				pegado.setWebcam_habilitada(isHabilitadoWebcam(pegado.getCodigo_sexual()));
				sexo.setPegado(pegado);
			}				
			
			lista_sexos.add(sexo);
		}
		return lista_sexos;
	}
	
	
	public List getSexosConfirmados(long codigo_sexual){
		ArrayList lista = (ArrayList)sexoDAO.getSexosConfirmados(codigo_sexual);
		ArrayList<Sexo> lista_sexos = new ArrayList<Sexo>();
		Membro pegador = null;
		Membro pegado = null;
        Sexo sexo = null;
         
		for(int i = 0; i < lista.size(); i++){
			sexo = (Sexo)lista.get(i);
			
			if(sexo.getPegador().getCodigo_sexual() != codigo_sexual){
				pegador = membroDAO.getMembroMaisSimplesAinda(sexo.getPegador().getCodigo_sexual());				
				sexo.setPegador(pegador);			
			}else{				
				pegado = membroDAO.getMembroMaisSimplesAinda(sexo.getPegado().getCodigo_sexual());				
				sexo.setPegado(pegado);
			}				
			
			lista_sexos.add(sexo);
		}
		return lista_sexos;
	}
	
	public List getSexosPegadorAguardando(long codigo_sexual){
		ArrayList lista = (ArrayList)sexoDAO.getSexosPegadorAguardando(codigo_sexual);
		ArrayList<Sexo> lista_sexos = new ArrayList<Sexo>();
		Membro pegado = null;
         Sexo sexo = null;
		for(int i = 0; i < lista.size(); i++){
			sexo = (Sexo)lista.get(i);
			pegado = membroDAO.getMembroMaisSimplesAinda(sexo.getPegado().getCodigo_sexual());
			sexo.setPegado(pegado);
			lista_sexos.add(sexo);
		}
		return lista_sexos;
	}
	
	public List getSexosPegadoAguardando(long codigo_sexual){
		ArrayList lista = (ArrayList)sexoDAO.getSexosPegadoAguardando(codigo_sexual);
		ArrayList<Sexo> lista_sexos = new ArrayList<Sexo>();
		Membro pegador = null;
        Sexo sexo = null;
		for(int i = 0; i < lista.size(); i++){
			sexo = (Sexo)lista.get(i);
			pegador = membroDAO.getMembroMaisSimplesAinda(sexo.getPegador().getCodigo_sexual());
			sexo.setPegador(pegador);
			lista_sexos.add(sexo);
		}
		return lista_sexos;
	}
	
	public boolean hasSexo(long codigo_sexual_pegador, long codigo_sexual_pegado){
		return sexoDAO.hasSexo(codigo_sexual_pegador, codigo_sexual_pegado);
	}
	
	public int getQtdSexosConfirmados(long codigo_sexual){
		return sexoDAO.getQtdSexosConfirmados(codigo_sexual);
	}
    
    
    public boolean excluirSuruba(long id_suruba){
        return surubaDAO.excluir(id_suruba);        
    }
    
    public boolean inserirSuruba(Suruba suruba){
        return surubaDAO.inserir(suruba);       
    }
    
    public boolean atualizarSuruba(Suruba suruba){
        return surubaDAO.atualizar(suruba);     
    }
    
    public boolean atualizarStatusSuruba(long id_suruba, int status){
        return surubaDAO.atualizarStatus(id_suruba, status);        
    }
    
    public Suruba getSuruba(long id_suruba){
        return surubaDAO.getSuruba(id_suruba);
    }
    
    public Suruba getSurubaSimples(long id_suruba){
        return surubaDAO.getSurubaSimples(id_suruba);
    }
    
    public String getTituloSuruba(long id_suruba){
        return surubaDAO.getTituloSuruba(id_suruba);
    }
    
    public Long getIdSuruba(String titulo, String cidade, long organizador){
        return surubaDAO.getIdSuruba(titulo, cidade, organizador);
    }
    
    public Membro getOrganizadorSuruba(long id_suruba){
        return surubaDAO.getOrganizadorSuruba(id_suruba);
    }
    
    public List buscaSurubasDisponives(String estado, String cidade){        
        return surubaDAO.buscaSurubasAbertas(estado, cidade);
    }
    
    public List buscaSurubasFinalizadas(String estado, String cidade){        
        return surubaDAO.buscaSurubasFinalizadas(estado, cidade);
    }
        
    public List getSurubasAbertasPeloOrganizador(long organizador){    
        return surubaDAO.getSurubasAbertasPeloOrganizador(organizador);
    }
    
    public List getSurubasFinalizadasPeloOrganizador(long organizador){    
        return surubaDAO.getSurubasFinalizadasPeloOrganizador(organizador);
    }
    
    public boolean excluirComentarioSuruba(long id_comentario_suruba){
        return comentarioDAO.excluir(id_comentario_suruba);        
    }
    
    public boolean inserirComentarioSuruba(ComentarioSuruba comentario){
        return comentarioDAO.inserir(comentario);       
    }
    
    public boolean atualizarComentarioSuruba(ComentarioSuruba comentario){
        return comentarioDAO.atualizar(comentario);     
    }
       
    public ComentarioSuruba getComentarioSuruba(long id_comentario){
        return comentarioDAO.getComentarioSuruba(id_comentario);
    }
    
    public List getComentariosSurubaPelaSuruba(long id_suruba){   
    	ArrayList lista = (ArrayList)comentarioDAO.getComentariosSurubaPelaSuruba(id_suruba);
        ArrayList<ComentarioSuruba> lista_comentarios = new ArrayList<ComentarioSuruba>();
        ComentarioSuruba comentario = null;
        ParticipacaoSuruba participacao = null;
        Membro participante = null;
        for(int i = 0; i < lista.size(); i++){
        	comentario = (ComentarioSuruba)lista.get(i);
            participacao = participacaoDAO.getParticipacaoSuruba(comentario.getParticipacao().getId());
            participante = membroDAO.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
            participacao.setParticipante(participante);
            comentario.setParticipacao(participacao);
            lista_comentarios.add(comentario);
        }
        return lista_comentarios;
    }
    
    public List getComentariosSurubaPelaParticipacao(long id_participacao){   
        return comentarioDAO.getComentariosSurubaPelaParticipacao(id_participacao);
    }

    public boolean excluirParticipacaoSuruba(long id_participacao){
        return participacaoDAO.excluir(id_participacao);        
    }
    
    public boolean inserirParticipacaoSuruba(ParticipacaoSuruba participacao){
        return participacaoDAO.inserir(participacao);       
    }
    
    public boolean atualizarParticipacaoSuruba(ParticipacaoSuruba participacao){
        return participacaoDAO.atualizar(participacao);     
    }
    
    public boolean atualizarStatusParticipacaoSuruba(long id_participacao, int status){
        return participacaoDAO.atualizarStatus(id_participacao, status);        
    }
    
    public ParticipacaoSuruba getParticipacaoSuruba(long id_participacao){
        return participacaoDAO.getParticipacaoSuruba(id_participacao);
    }
    
    public ParticipacaoSuruba getParticipacaoSurubaPelaSurubaPeloParticipante(long id_suruba, long codigo_sexual){
        return participacaoDAO.getParticipacaoSurubaPelaSurubaPeloParticipante(id_suruba, codigo_sexual);
    }
    
    public boolean hasParticipacaoSuruba(long id_suruba, long codigo_sexual){
        return participacaoDAO.hasParticipacaoSuruba(id_suruba,codigo_sexual); 
    }

    public List getParticipacoesSurubaPelaSuruba(long id_suruba){   
        ArrayList lista = (ArrayList)participacaoDAO.getParticipacoesSurubaPelaSuruba(id_suruba);
        ArrayList<ParticipacaoSuruba> lista_participacao = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Membro participante = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            participante = membroDAO.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
            participacao.setParticipante(participante);
            lista_participacao.add(participacao);
        }
        return lista_participacao;
    }
    
    public List getParticipacoesSurubaPeloStatus(int status){   
        return participacaoDAO.getParticipacoesSurubaPeloStatus(status);
    }
    
    public List getParticipacoesSurubaPeloStatusPelaSuruba(int status, long id_suruba){   
    	ArrayList lista = (ArrayList)participacaoDAO.getParticipacoesSurubaPeloStatusPelaSuruba(status, id_suruba);
        ArrayList<ParticipacaoSuruba> lista_participacao = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Membro participante = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            participante = membroDAO.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
            participacao.setParticipante(participante);
            lista_participacao.add(participacao);
        }
        return lista_participacao;
    }
    
    public List getParticipacoesSurubaAceitasPelaSuruba(long id_suruba){   
        ArrayList lista = (ArrayList)participacaoDAO.getParticipacoesSurubaPeloStatusPelaSuruba(1, id_suruba);
        ArrayList<ParticipacaoSuruba> lista_participacao = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Membro participante = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            participante = membroDAO.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
            participacao.setParticipante(participante);
            lista_participacao.add(participacao);
        }
        return lista_participacao;
    }
    
    public List getParticipacoesSurubaAceitasFoiPelaSuruba(long id_suruba){   
        ArrayList lista = (ArrayList)participacaoDAO.getParticipacoesSurubaPeloStatusPelaSuruba(1, id_suruba);
        ArrayList<ParticipacaoSuruba> lista_participacao = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Membro participante = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            participante = membroDAO.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
            participacao.setParticipante(participante);
            lista_participacao.add(participacao);
        }
        
        ArrayList lista2 = (ArrayList)participacaoDAO.getParticipacoesSurubaPeloStatusPelaSuruba(4, id_suruba);
        for(int i = 0; i < lista2.size(); i++){
            participacao = (ParticipacaoSuruba)lista2.get(i);
            participante = membroDAO.getMembroMaisSimplesAinda(participacao.getParticipante().getCodigo_sexual());
            participacao.setParticipante(participante);
            lista_participacao.add(participacao);
        }
        
        return lista_participacao;
    }
    
    public List getParticipacoesSurubaPeloStatusPeloParticipante(int status, long codigo_sexual){   
        return participacaoDAO.getParticipacoesSurubaPeloStatusPeloParticipante(status, codigo_sexual);
    }
    
    public int getQtdParticipacoesSurubaPeloStatusPeloParticipante(int status, long codigo_sexual){
        return participacaoDAO.getQtdParticipacoesSurubaPeloStatusPeloParticipante(status, codigo_sexual);
    }
    
    public int getQtdParticipacoesSurubaPeloStatusPelaSuruba(int status, long id_suruba){
        return participacaoDAO.getQtdParticipacoesSurubaPeloStatusPelaSuruba(status, id_suruba);
    }
       
    
    public List getSurubasParticipanteFoi(long codigo_sexual){        
        ArrayList lista = (ArrayList)getParticipacoesSurubaPeloStatusPeloParticipante(4, codigo_sexual);
        ArrayList<Suruba> lista_surubas = new ArrayList<Suruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            suruba = surubaDAO.getSurubaSimples(participacao.getSuruba().getId());
            lista_surubas.add(suruba);
        }
        return lista_surubas;
    }
    
    public List getSurubasParticipanteNaoFoi(long codigo_sexual){        
        ArrayList lista = (ArrayList)getParticipacoesSurubaPeloStatusPeloParticipante(5, codigo_sexual);
        ArrayList<Suruba> lista_surubas = new ArrayList<Suruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            suruba = surubaDAO.getSurubaSimples(participacao.getSuruba().getId());
            lista_surubas.add(suruba);
        }
        return lista_surubas;
    }
    
    public List getSurubasParticipanteAguardando(long codigo_sexual){        
        ArrayList lista = (ArrayList)getParticipacoesSurubaPeloStatusPeloParticipante(0, codigo_sexual);
        ArrayList<Suruba> lista_surubas = new ArrayList<Suruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            suruba = surubaDAO.getSurubaSimples(participacao.getSuruba().getId());
            lista_surubas.add(suruba);
        }
        return lista_surubas;
    }
    
    public List getSurubasParticipanteAceito(long codigo_sexual){        
        ArrayList lista = (ArrayList)getParticipacoesSurubaPeloStatusPeloParticipante(1, codigo_sexual);
        ArrayList<Suruba> lista_surubas = new ArrayList<Suruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            suruba = surubaDAO.getSurubaSimples(participacao.getSuruba().getId());
            lista_surubas.add(suruba);
        }
        return lista_surubas;
    }
    
    public List getSurubasParticipanteRecusado(long codigo_sexual){        
        ArrayList lista = (ArrayList)getParticipacoesSurubaPeloStatusPeloParticipante(2, codigo_sexual);
        ArrayList<Suruba> lista_surubas = new ArrayList<Suruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            suruba = surubaDAO.getSurubaSimples(participacao.getSuruba().getId());
            lista_surubas.add(suruba);
        }
        return lista_surubas;
    }
    
    public List getSurubasParticipanteDesistiu(long codigo_sexual){        
        ArrayList lista = (ArrayList)getParticipacoesSurubaPeloStatusPeloParticipante(3, codigo_sexual);
        ArrayList<Suruba> lista_surubas = new ArrayList<Suruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        for(int i = 0; i < lista.size(); i++){
            participacao = (ParticipacaoSuruba)lista.get(i);
            suruba = surubaDAO.getSurubaSimples(participacao.getSuruba().getId());
            lista_surubas.add(suruba);
        }
        return lista_surubas;
    }
    
    public boolean excluirEnquete(long id_enquete){
        return enqueteDAO.excluir(id_enquete);        
    }
    
    public boolean inserirEnquete(Enquete enquete){
        return enqueteDAO.inserir(enquete);       
    }
    
    public boolean atualizarEnquete(Enquete enquete){
        return enqueteDAO.atualizar(enquete);     
    }
    
    public List getEnquetes(){
        return enqueteDAO.getTodos();     
    }
    
    public Enquete getEnquete(long id_enquete){
        return enqueteDAO.getEnquete(id_enquete);     
    }
    
    public boolean excluirResposta(long id_resposta){
        return respostaDAO.excluir(id_resposta);        
    }
    
    public boolean inserirResposta(Resposta resposta){
        return respostaDAO.inserir(resposta);       
    }
    
    public boolean atualizarResposta(Resposta resposta){
        return respostaDAO.atualizar(resposta);     
    }
    
    public List getRespostas(){
        return respostaDAO.getTodos();     
    }
    
    public List getRespostasEnquete(long id_enquete){
        return respostaDAO.getTodosPelaEnquete(id_enquete);     
    }
    
    public Resposta getResposta(long id_resposta){
        return respostaDAO.getResposta(id_resposta);     
    }
    
    public boolean excluirVoto(long id_voto){
        return votoDAO.excluir(id_voto);        
    }
    
    public boolean inserirVoto(Voto voto){
        return votoDAO.inserir(voto);       
    }
    
    public boolean atualizarVoto(Voto voto){
        return votoDAO.atualizar(voto);     
    }
    
    public List getVotos(){
        return votoDAO.getTodos();     
    }
    
    public Voto getVoto(long id_voto){
        return votoDAO.getVoto(id_voto);
    }
    
    public int getQtdVotosEnquete(long id_enquete){
    	return votoDAO.getQtdVotosEnquete(id_enquete);
    }
    
    public int getQtdVotosResposta(long id_resposta){
    	return votoDAO.getQtdVotosResposta(id_resposta);
    }
}
