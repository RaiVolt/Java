/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.TipoDAO;
import br.com.senac.Domain.Tipo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class TipoBean implements Serializable {

    private Tipo tipo;
    private List<Tipo> tipos;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    @PostConstruct
    public void listar() {
        try {
            TipoDAO tipoDAO = new TipoDAO();
            tipos = tipoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Tipos");
            erro.printStackTrace();
        }
    }

    public void novo() {
        tipo = new Tipo();
    }

    public void salvar() {
        try {
            TipoDAO tipoDAO = new TipoDAO();
            tipoDAO.merge(tipo);

            tipo = new Tipo();
            tipos = tipoDAO.listar();

            Messages.addGlobalInfo("Tipo salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Tipo");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            tipo = (Tipo) evento.getComponent().getAttributes().get("tipoSelecionado");

            TipoDAO tipoDAO = new TipoDAO();
            tipoDAO.excluir(tipo);

            tipos = tipoDAO.listar();

            Messages.addGlobalInfo("Tipo removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Tipo");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        tipo = (Tipo) evento.getComponent().getAttributes().get("tipoSelecionado");
    }
}
