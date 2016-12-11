/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.CidadeDAO;
import br.com.senac.DAO.EstadoDAO;
import br.com.senac.DAO.PlaylistDAO;
import br.com.senac.DAO.UsuarioDAO;
import br.com.senac.Domain.Cidade;
import br.com.senac.Domain.Playlist;
import br.com.senac.Domain.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class PlaylistBean implements Serializable {

    private Playlist playlist;
    private List<Playlist> playlists;
    private List<Usuario> usuarios;

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

//    public void novo() {
//		try {
//			cidade = new Cidade();
//
//			EstadoDAO estadoDAO = new EstadoDAO();
//			estados = estadoDAO.listar();
//		} catch (RuntimeException erro) {
//			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
//			erro.printStackTrace();
//		}
//	}
    @PostConstruct
    public void salvar() {
        try {
            PlaylistDAO playlistDAO = new PlaylistDAO();
            playlistDAO.merge(playlist);

            playlist = new Playlist();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar();

            playlists = playlistDAO.listar();

            Messages.addGlobalInfo("Playlist salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova playlist");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            playlist = (Playlist) evento.getComponent().getAttributes().get("playlistSelecionada");

            PlaylistDAO playlistDAO = new PlaylistDAO();
            playlistDAO.excluir(playlist);

            playlists = playlistDAO.listar();

            Messages.addGlobalInfo("Playlist removida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a playlist");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            playlist = (Playlist) evento.getComponent().getAttributes().get("playlistSelecionada");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma playlist");
            erro.printStackTrace();
        }
    }
}
