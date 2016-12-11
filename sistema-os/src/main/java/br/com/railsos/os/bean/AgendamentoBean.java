/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.bean;

import br.com.railsos.os.dao.AgendamentoDAO;
import br.com.railsos.os.dao.ClienteNewDAO;
import br.com.railsos.os.dao.FuncionarioDAO;
import br.com.railsos.os.dao.OrdemServicoDAO;
import br.com.railsos.os.entidade.Agendamento;
import br.com.railsos.os.entidade.ClienteNew;
import br.com.railsos.os.entidade.Funcionario;
import br.com.railsos.os.entidade.OrdemServico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Messages;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {

    private Agendamento agendamento = new Agendamento();
    private List<Agendamento> agendamentos;

    private OrdemServico ordemServico = new OrdemServico();
    private List<OrdemServico> os;

    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> fs;

    private List<ClienteNew> cns;

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<OrdemServico> getOs() {
        return os;
    }

    public void setOs(List<OrdemServico> os) {
        this.os = os;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFs() {
        return fs;
    }

    public void setFs(List<Funcionario> fs) {
        this.fs = fs;
    }

    public List<ClienteNew> getCns() {
        return cns;
    }

    public void setCns(List<ClienteNew> cns) {
        this.cns = cns;
    }

    @PostConstruct
    public void listar() {
        try {
            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            agendamentos = agendamentoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar o agendamento");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            Date data = new Date();
            agendamento = new Agendamento();

            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            os = ordemServicoDAO.listar();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            fs = funcionarioDAO.listar("nome");
            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            cns = clienteNewDAO.listar();

            this.agendamento.setInicio(data);

            //fs = new ArrayList<Funcionario>();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma novo Usuário");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            agendamentoDAO.merge(agendamento);

            agendamento = new Agendamento();

            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            os = ordemServicoDAO.listar();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            fs = funcionarioDAO.listar();
            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            cns = clienteNewDAO.listar();

            agendamentos = agendamentoDAO.listar();

            Messages.addGlobalInfo("Agendado salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o agendamento");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");

            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            agendamentoDAO.excluir(agendamento);

            agendamentos = agendamentoDAO.listar();

            Messages.addGlobalInfo("Usuário removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Usuário");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            agendamento = (Agendamento) evento.getComponent().getAttributes().get("agendamentoSelecionado");

            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            os = ordemServicoDAO.listar();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            fs = funcionarioDAO.listar();
            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            cns = clienteNewDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Agendamento");
            erro.printStackTrace();
        }
    }

    public void popular() {
        try {
            if (ordemServico != null) {
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                fs = funcionarioDAO.buscaPorTecnico(ordemServico.getId());
            } else {
                fs = new ArrayList<>();
            }
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar filtrar o técnico");
            erro.printStackTrace();
        }
    }

}
