/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.AlbumDAO;
import br.com.senac.DAO.ArtistaDAO;
import br.com.senac.DAO.GeneroMusicaDAO;
import br.com.senac.DAO.MusicaDAO;
import br.com.senac.Domain.Album;
import br.com.senac.Domain.Artista;
import br.com.senac.Domain.GeneroMusica;
import br.com.senac.Domain.Musica;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class MusicaBean implements Serializable {

    private Musica musica;
    private List<Musica> musicas;
    private List<Album> albums;
    private List<Artista> artistas;
    private List<GeneroMusica> generoMusicas;

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<GeneroMusica> getGeneroMusicas() {
        return generoMusicas;
    }

    public void setGeneroMusicas(List<GeneroMusica> generoMusicas) {
        this.generoMusicas = generoMusicas;
    }

    @PostConstruct
    public void listar() {
        try {
            MusicaDAO musicaDAO = new MusicaDAO();
            musicas = musicaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as músicas");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            musica = new Musica();

            AlbumDAO albumDAO = new AlbumDAO();
            albums = albumDAO.listar();
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova música");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            MusicaDAO musicaDAO = new MusicaDAO();
            musicaDAO.merge(musica);

            musica = new Musica();

            AlbumDAO albumDAO = new AlbumDAO();
            albums = albumDAO.listar();
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDAO.listar();

            musicas = musicaDAO.listar();

            Messages.addGlobalInfo("Música salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova música");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            musica = (Musica) evento.getComponent().getAttributes().get("musicaSelecionado");

            MusicaDAO musicaDAO = new MusicaDAO();
            musicaDAO.excluir(musica);

            musicas = musicaDAO.listar();

            Messages.addGlobalInfo("Música removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a música");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            musica = (Musica) evento.getComponent().getAttributes().get("musicaSelecionado");

            AlbumDAO albumDAO = new AlbumDAO();
            albums = albumDAO.listar();
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um música");
            erro.printStackTrace();
        }
    }

}
