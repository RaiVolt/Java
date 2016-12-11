/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.CidadeDAO;
import br.com.senac.DAO.EstadoDAO;
import br.com.senac.DAO.TipoDAO;
import br.com.senac.DAO.UsuarioDAO;
import br.com.senac.Domain.Cidade;
import br.com.senac.Domain.Estado;
import br.com.senac.Domain.Tipo;
import br.com.senac.Domain.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Messages;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Tipo> tipos;

    private Estado estado;
    private List<Estado> estados;
    private List<Cidade> cidades;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioBean other = (UsuarioBean) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
        

    @PostConstruct
    public void listar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as Usuários");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            Date data = new Date();
            usuario = new Usuario();

            TipoDAO tipoDAO = new TipoDAO();
            tipos = tipoDAO.listar("descricao");
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");
 
            this.usuario.setDataCad(data);

            cidades = new ArrayList<Cidade>();
//            CidadeDAO cidadeDAO = new CidadeDAO();
//            cidades = cidadeDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma novo Usuário");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
            usuarioDAO.merge(usuario);

            usuario = new Usuario();

            
            TipoDAO tipoDAO = new TipoDAO();
            tipos = tipoDAO.listar();
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.listar();
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();

            usuarios = usuarioDAO.listar();

            Messages.addGlobalInfo("Usuário salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo Usuário");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuario);

            usuarios = usuarioDAO.listar();

            Messages.addGlobalInfo("Usuário removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Usuário");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            TipoDAO tipoDAO = new TipoDAO();
            tipos = tipoDAO.listar();
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Usuário");
            erro.printStackTrace();
        }
    }
    
    
    
    public void popular() {
        try {
            if (estado != null) {
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidades = cidadeDAO.buscaPorEstado(estado.getId());
            } else {
                cidades = new ArrayList<>();
            }
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar filtrar as cidade");
            erro.printStackTrace();
        }
    }

}
