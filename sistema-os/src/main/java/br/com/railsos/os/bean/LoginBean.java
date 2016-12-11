/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.bean;

import br.com.railsos.os.dao.LoginDAO;
import br.com.railsos.os.entidade.Login;
import br.com.railsos.os.util.Autenticacao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Messages;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private Login login = new Login();

    private List<Login> logins;
    private Autenticacao alt;

    public Login getLogin() {
        return login;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public Autenticacao getAlt() {
        return alt;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    public void setAlt(Autenticacao alt) {
        this.alt = alt;
    }

    @PostConstruct
    public void listar() {
        try {
            LoginDAO loginDAO = new LoginDAO();
            logins = loginDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuário");
            erro.printStackTrace();

        }
    }

    public void novo() {
        login = new Login();
    }

    public void salvar() {
        try {
            LoginDAO loginDAO = new LoginDAO();
            login.setPassword(DigestUtils.md5Hex(login.getPassword()));
            loginDAO.merge(login);

            login = new Login();

            logins = loginDAO.listar();

            Messages.addGlobalInfo("Usuário salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo usuário");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            login = (Login) evento.getComponent().getAttributes().get("usuarioSelecionado");

            LoginDAO loginDAO = new LoginDAO();
            loginDAO.excluir(login);

            logins = loginDAO.listar();

            Messages.addGlobalInfo("Usuário removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o usuário");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        login = (Login) evento.getComponent().getAttributes().get("usuarioSelecionado");
    }

    public String verificaDados() {
        Autenticacao alt = new Autenticacao();
        Login us;
        String resultado = "";

        try {
            us = alt.altentication(login);
            setLogin(login);
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("login", us);
                resultado = "index?faces-redirect=true";
            } else {
                resultado = "login?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("result" + resultado);
        return resultado;
    }

    public boolean verificaSessao() {
        boolean estado;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("login") == null) {
            estado = false;

        } else {
            estado = true;
        }
        return estado;
    }

    public String fecharSessao() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

}
