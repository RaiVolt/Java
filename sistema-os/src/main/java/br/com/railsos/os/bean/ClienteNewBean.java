/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.bean;

import br.com.railsos.os.dao.ClienteNewDAO;
import br.com.railsos.os.entidade.ClienteNew;
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
public class ClienteNewBean implements Serializable {

    private ClienteNew clienteNew = new ClienteNew();
    private List<ClienteNew> clienteNews;

    public ClienteNew getClienteNew() {
        return clienteNew;
    }

    public void setClienteNew(ClienteNew clienteNew) {
        this.clienteNew = clienteNew;
    }

    public List<ClienteNew> getClienteNews() {
        return clienteNews;
    }

    public void setClienteNews(List<ClienteNew> clienteNews) {
        this.clienteNews = clienteNews;
    }

    @PostConstruct
    public void listar() {
        try {
            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            clienteNews = clienteNewDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os cliente");
            erro.printStackTrace();

        }
    }
    
     public void salvar() {
        try {
            ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
            clienteNewDAO.merge(clienteNew);
            
            clienteNew = new ClienteNew();
            clienteNews = clienteNewDAO.listar();
            
            Messages.addGlobalInfo("Cliente salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os cliente");
            erro.printStackTrace();

        }
    }
     
     public void excluir(ActionEvent evento) {
		try {
			clienteNew = (ClienteNew) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteNewDAO clienteNewDAO = new ClienteNewDAO();
			clienteNewDAO.excluir(clienteNew);
			
			clienteNews = clienteNewDAO.listar();

			Messages.addGlobalInfo("Cliente removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o cliente");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		clienteNew = (ClienteNew) evento.getComponent().getAttributes().get("clienteSelecionado");
	}
    
}
