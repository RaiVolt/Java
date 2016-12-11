/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Rafael
 */
@SuppressWarnings("serial")
@Entity
public class Agendamento extends GenericDomain {

    @OneToOne
    @JoinColumn(nullable = false)
    private OrdemServico numeroOS;

    @OneToOne
    @JoinColumn(nullable = false)
    private Funcionario tecnico;

    @OneToOne
    @JoinColumn(nullable = false)
    private ClienteNew cliente;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fim;

    @Column(nullable = false)
    private Boolean sTecnico;

    @Column(nullable = false)
    private Boolean iSistema;

    @Column(nullable = false)
    private Boolean tDia;

    public OrdemServico getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(OrdemServico numeroOS) {
        this.numeroOS = numeroOS;
    }

    public Funcionario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Funcionario tecnico) {
        this.tecnico = tecnico;
    }

    public ClienteNew getCliente() {
        return cliente;
    }

    public void setCliente(ClienteNew cliente) {
        this.cliente = cliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Boolean getsTecnico() {
        return sTecnico;
    }

    public void setsTecnico(Boolean sTecnico) {
        this.sTecnico = sTecnico;
    }

    public Boolean getiSistema() {
        return iSistema;
    }

    public void setiSistema(Boolean iSistema) {
        this.iSistema = iSistema;
    }

    public Boolean gettDia() {
        return tDia;
    }

    public void settDia(Boolean tDia) {
        this.tDia = tDia;
    }

    @Transient
    public String getAtivoFormatado() {
        String ativoFormatado = "Não";

        if (iSistema) {
            ativoFormatado = "Sim";
        }

        return ativoFormatado;
    }

    @Transient
    public String getAtivoFormatadois() {
        String ativoFormatado = "Não";

        if (tDia) {
            ativoFormatado = "Sim";
        }

        return ativoFormatado;
    }

    @Transient
    public String getAtivoFormatadoday() {
        String ativoFormatado = "Não";

        if (sTecnico) {
            ativoFormatado = "Sim";
        }

        return ativoFormatado;
    }
}
