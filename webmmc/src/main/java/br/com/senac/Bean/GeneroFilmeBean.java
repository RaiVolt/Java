/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.GeneroFilmeDAO;
import br.com.senac.Domain.GeneroFilme;
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
public class GeneroFilmeBean implements Serializable {

    private GeneroFilme generoFilme;
    private List<GeneroFilme> generoFilmes;

    public GeneroFilme getGeneroFilme() {
        return generoFilme;
    }

    public void setGeneroFilme(GeneroFilme generoFilme) {
        this.generoFilme = generoFilme;
    }

    public List<GeneroFilme> getGeneroFilmes() {
        return generoFilmes;
    }

    public void setGeneroFilmes(List<GeneroFilme> generoFilmes) {
        this.generoFilmes = generoFilmes;
    }

    @PostConstruct
    public void listar() {
        try {
            GeneroFilmeDAO generoFilmeDAO = new GeneroFilmeDAO();
            generoFilmes = generoFilmeDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Genero");
            erro.printStackTrace();
        }
    }

    public void novo() {
        generoFilme = new GeneroFilme();
    }

    public void salvar() {
        try {
            GeneroFilmeDAO generoFilmeDAO = new GeneroFilmeDAO();
            generoFilmeDAO.merge(generoFilme);

            generoFilme = new GeneroFilme();
            generoFilmes = generoFilmeDAO.listar();

            Messages.addGlobalInfo("Genero salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Genero");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            generoFilme = (GeneroFilme) evento.getComponent().getAttributes().get("generoFilmeSelecionado");

            GeneroFilmeDAO generoFilmeDAO = new GeneroFilmeDAO();
            generoFilmeDAO.excluir(generoFilme);

            generoFilmes = generoFilmeDAO.listar();

            Messages.addGlobalInfo("Estado removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Genero");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        generoFilme = (GeneroFilme) evento.getComponent().getAttributes().get("generoFilmeSelecionado");
    }
}
