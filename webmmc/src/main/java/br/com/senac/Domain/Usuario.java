/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rafael
 */
@Entity
public class Usuario extends GenericDomain {

    @Column(length = 40, nullable = false)
    private String nome;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 32, nullable = false)
    private String senha;

    @Column(nullable = false)
    private Character sexo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNac;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCad;

    @OneToOne
    @JoinColumn(nullable = false)
    private Cidade cidade;

    @OneToOne
    @JoinColumn(nullable = false)
    private Tipo tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getDataNac() {
        return dataNac;
    }

    public void setDataNac(Date dataNac) {
        this.dataNac = dataNac;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
