/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.AtorDAO;
import br.com.senac.Domain.Ator;
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
public class AtorBean implements Serializable{

    private Ator ator;
    private List<Ator> atores;

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    @PostConstruct
    public void listar() {
        try {
            AtorDAO atorDAO = new AtorDAO();
            atores = atorDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Atores");
            erro.printStackTrace();
        }
    }

    public void novo() {
        ator = new Ator();
    }

    public void salvar() {
        try {
            AtorDAO atorDAO = new AtorDAO();
            atorDAO.merge(ator);

            ator = new Ator();
            atores = atorDAO.listar();

            Messages.addGlobalInfo("Ator salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Ator");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            ator = (Ator) evento.getComponent().getAttributes().get("atorSelecionado");

            AtorDAO atorDAO = new AtorDAO();
            atorDAO.excluir(ator);

            atores = atorDAO.listar();

            Messages.addGlobalInfo("Ator removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Ator");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        ator = (Ator) evento.getComponent().getAttributes().get("atorSelecionado");
    }
}
