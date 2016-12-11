/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.ArtistaDAO;
import br.com.senac.Domain.Artista;
import java.io.Serializable;
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
public class ArtistaBean implements Serializable{
    private Artista artista;
    private List<Artista> artistas;

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

   
    @PostConstruct
    public void listar() {
        try {
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistas = artistaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Tipo");
            erro.printStackTrace();
        }
    }

    public void novo() {
        artista = new Artista();
    }

    public void salvar() {
        try {
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistaDAO.merge(artista);

            artista = new Artista();
            artistas = artistaDAO.listar();

            Messages.addGlobalInfo("Artista salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Artista");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            artista = (Artista) evento.getComponent().getAttributes().get("artistaSelecionado");

            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistaDAO.excluir(artista);

            artistas = artistaDAO.listar();

            Messages.addGlobalInfo("Artista removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Artusta");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        artista = (Artista) evento.getComponent().getAttributes().get("artistaSelecionado");
    }
}


