/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.AtorDAO;
import br.com.senac.DAO.DiretorDAO;
import br.com.senac.Domain.Acao;
import br.com.senac.Domain.Ator;
import br.com.senac.Domain.Diretor;
import br.com.senac.Domain.Log;
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
public class DiretorBean implements Serializable {

    private Diretor diretor;
    private List<Diretor> diretores;

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<Diretor> diretores) {
        this.diretores = diretores;
    }

    @PostConstruct
    public void listar() {
        try {
            DiretorDAO diretorDAO = new DiretorDAO();
            diretores = diretorDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Diretores");
            erro.printStackTrace();
        }
    }

    public void novo() {
        diretor = new Diretor();
    }

    public void salvar() {
        try {
            DiretorDAO diretorDAO = new DiretorDAO();
            diretorDAO.merge(diretor);

            diretor = new Diretor();

            diretores = diretorDAO.listar();

            Log log = new Log();
            Acao acao = new Acao();
            acao.setId(1);
            log.setAcao(acao);

            log.salvarLog();

            Messages.addGlobalInfo("Diretor salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Diretor");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            diretor = (Diretor) evento.getComponent().getAttributes().get("diretorSelecionado");

            DiretorDAO diretorDAO = new DiretorDAO();
            diretorDAO.excluir(diretor);

            diretores = diretorDAO.listar();

            Messages.addGlobalInfo("Diretor removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Diretor");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        diretor = (Diretor) evento.getComponent().getAttributes().get("diretorSelecionado");
    }
}
