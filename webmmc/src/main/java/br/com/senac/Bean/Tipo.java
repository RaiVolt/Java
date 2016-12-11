/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.Domain.*;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Rafael
 */
@Entity
public class Tipo extends GenericDomain{
    
    @Column(length = 40, nullable = false)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
