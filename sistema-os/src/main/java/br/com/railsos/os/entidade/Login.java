/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Rafael
 */
@Entity
public class Login extends GenericDomain{
    
    @Column(length = 40, nullable = false)
    private String username;
    @Column(length = 40, nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
