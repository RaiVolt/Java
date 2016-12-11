/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.bean;

import br.com.railsos.os.dao.ClienteNewDAO;
import br.com.railsos.os.dao.FuncionarioDAO;
import br.com.railsos.os.dao.OrdemServicoDAO;
import br.com.railsos.os.entidade.ClienteNew;
import br.com.railsos.os.entidade.Funcionario;
import br.com.railsos.os.entidade.OrdemServico;
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
public class OrdemServicoBean implements Serializable {

    private OrdemServico ordemServico = new OrdemServico();

    private List<OrdemServico> ordemServicos;
    private List<ClienteNew> cns;
    private List<Funcionario> fs;

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public List<OrdemServico> getOrdemServicos() {
        return ordemServicos;
    }

    public void setOrdemServicos(List<OrdemServico> ordemServicos) {
        this.ordemServicos = ordemServicos;
    }

    public List<ClienteNew> getCns() {
        return cns;
    }

    public void setCns(List<ClienteNew> cns) {
        this.cns = cns;
    }

    public List<Funcionario> getFs() {
        return fs;
    }

    public void setFs(List<Funcionario> fs) {
        this.fs = fs;
    }

    @PostConstruct
    public void novo() {

        ordemServico = new OrdemServico();

        ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
        cns = clienteNewDAO.listar();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        fs = funcionarioDAO.listar();

    }

    public void listar() {
        try {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            ordemServicos = ordemServicoDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as ordem de serviços");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            ordemServicoDAO.merge(ordemServico);

            ordemServico = new OrdemServico();

            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            cns = clienteNewDAO.listar();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            fs = funcionarioDAO.listar();

            ordemServicos = ordemServicoDAO.listar();

            Messages.addGlobalInfo("Ordem de serviço salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova ordem de serviço");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            ordemServico = (OrdemServico) evento.getComponent().getAttributes().get("ordemservicoSelecionado");

            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            ordemServicoDAO.excluir(ordemServico);

            ordemServicos = ordemServicoDAO.listar();

            Messages.addGlobalInfo("Ordem de serviço removida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a ordem de serviço");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            ordemServico = (OrdemServico) evento.getComponent().getAttributes().get("ordemservicoSelecionado");

            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            cns = clienteNewDAO.listar();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            fs = funcionarioDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma ordem de serviço");
            erro.printStackTrace();
        }
    }

}
