/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.Domain.Usuario;
import br.com.senac.Util.Autenticacao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private Usuario usuario = new Usuario();

    private List<Usuario> usuarios;
    private Autenticacao alt;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Autenticacao getAlt() {
        return alt;
    }

    public void setAlt(Autenticacao alt) {
        this.alt = alt;
    }

    public String verificaDados() {
        Autenticacao alt = new Autenticacao();
        Usuario us;
        String resultado = "";

        try {
            us = alt.altentication(usuario);
            setUsuario(usuario);
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("usuario", us);
                resultado = "principal?faces-redirect=true";
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
                .get("usuario") == null) {
            estado = false;

        } else {
            estado = true;
        }
        return estado;
    }
    
    public String fecharSessao(){
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return  "login?faces-redirect=true";
    }

}
