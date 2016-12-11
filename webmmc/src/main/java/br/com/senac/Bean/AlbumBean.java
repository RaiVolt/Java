/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.AlbumDAO;
import br.com.senac.DAO.ArtistaDAO;
import br.com.senac.DAO.FotoAlbumDAO;
import br.com.senac.DAO.GeneroMusicaDAO;
import br.com.senac.Domain.Album;
import br.com.senac.Domain.Artista;
import br.com.senac.Domain.FotoAlbum;
import br.com.senac.Domain.GeneroMusica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Rafael
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AlbumBean implements Serializable {

    private Album album;
    private Album albumSelecionado;
    private List<Album> albums;
    private List<Artista> artistas;
    private List<GeneroMusica> generoMusicas;

    private FotoAlbum fotoAlbum;
    private List<FotoAlbum> fotoAlbums;

    public Album getAlbumSelecionado() {
        return albumSelecionado;
    }

    public void setAlbumSelecionado(Album albumSelecionado) {
        this.albumSelecionado = albumSelecionado;
    }

    public FotoAlbum getFotoAlbum() {
        return fotoAlbum;
    }

    public void setFotoAlbum(FotoAlbum fotoAlbum) {
        this.fotoAlbum = fotoAlbum;
    }

    public List<FotoAlbum> getFotoAlbums() {
        return fotoAlbums;
    }

    public void setFotoAlbums(List<FotoAlbum> fotoAlbums) {
        this.fotoAlbums = fotoAlbums;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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
            AlbumDAO albumDAO = new AlbumDAO();
            albums = albumDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            album = new Album();

            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova cidade");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            AlbumDAO albumDAO = new AlbumDAO();
            albumDAO.merge(album);

            album = new Album();

            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDAO.listar();

            albums = albumDAO.listar();

            Messages.addGlobalInfo("Album salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma novo Album");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            album = (Album) evento.getComponent().getAttributes().get("albumSelecionado");

            AlbumDAO albumDAO = new AlbumDAO();
            albumDAO.excluir(album);

            albums = albumDAO.listar();

            Messages.addGlobalInfo("Album removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Album");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            album = (Album) evento.getComponent().getAttributes().get("albumSelecionado");

            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Album");
            erro.printStackTrace();
        }
    }

    public void salvarFoto() {
        try {
            FotoAlbumDAO fotoAlbumDAO = new FotoAlbumDAO();
            fotoAlbumDAO.merge(fotoAlbum);

            Messages.addGlobalInfo("Foto salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Album");
            erro.printStackTrace();
        }
    }

    public void processFileUpload(FileUploadEvent uploadEvent) {
        try {
            fotoAlbum.setAlbum(albumSelecionado);
            fotoAlbum.setImagem(uploadEvent.getFile().getContents());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void listaFotosAlbum() {
        try {
            ServletContext sContext = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();

            FotoAlbumDAO fotoAlbumDAO = new FotoAlbumDAO();

            fotoAlbums = fotoAlbumDAO.listByAlbum(albumSelecionado.getId());

            File folder = new File(sContext.getRealPath("/temp"));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            for (FotoAlbum fablum : fotoAlbums) {
                String nomeArquivo = fablum.getId() + ".jpg";
                String arquivo = sContext.getRealPath("/temp") + File.separator
                        + nomeArquivo;

                criaArquivo(fablum.getImagem(), arquivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void criaArquivo(byte[] bytes, String arquivo) {
        FileOutputStream fos;
        
         try {
            fos = new FileOutputStream(arquivo);
            fos.write(bytes);
 
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
