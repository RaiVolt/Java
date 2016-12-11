/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Rafael
 */
@Entity
public class Ator extends GenericDomain{
      
    @Column(length = 50, nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
