/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.GeneroMusicaDAO;
import br.com.senac.Domain.GeneroMusica;
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
public class GeneroMusicaBean implements Serializable {

    private GeneroMusica generoMusica;
    private List<GeneroMusica> generoMusicas;

    public GeneroMusica getGeneroMusica() {
        return generoMusica;
    }

    public void setGeneroMusica(GeneroMusica generoMusica) {
        this.generoMusica = generoMusica;
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
            GeneroMusicaDAO generoMusicaDao = new GeneroMusicaDAO();
            generoMusicas = generoMusicaDao.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Genero");
            erro.printStackTrace();
        }
    }

    public void novo() {
        generoMusica = new GeneroMusica();
    }

    public void salvar() {
        try {
            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicaDAO.merge(generoMusica);

            generoMusica = new GeneroMusica();
            generoMusicas = generoMusicaDAO.listar();

            Messages.addGlobalInfo("Genero salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Genero");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            generoMusica = (GeneroMusica) evento.getComponent().getAttributes().get("generoMusicaSelecionado");

            GeneroMusicaDAO generoMusicaDAO = new GeneroMusicaDAO();
            generoMusicaDAO.excluir(generoMusica);

            generoMusicas = generoMusicaDAO.listar();

            Messages.addGlobalInfo("Estado removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        generoMusica = (GeneroMusica) evento.getComponent().getAttributes().get("generoMusicaSelecionado");
    }
}
