/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.bean;

import br.com.railsos.os.dao.FuncionarioDAO;
import br.com.railsos.os.entidade.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{
    
    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> funcionarios;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    @PostConstruct
    public void listar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarios = funcionarioDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionario");
            erro.printStackTrace();

        }
    }
    
    public void novo() {
        funcionario = new Funcionario();
    }
    
     public void salvar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.merge(funcionario);
            
            funcionario = new Funcionario();
            
            funcionarios = funcionarioDAO.listar();
            
            Messages.addGlobalInfo("Funcionario salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionario");
            erro.printStackTrace();

        }
    }
     
     public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionario removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o funcionario");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
	}
    
}
