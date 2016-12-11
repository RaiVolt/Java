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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rafael
 */
@Entity
public class Avaliacao extends GenericDomain{
    
    @Column(length = 2, nullable = false)
    private int nota;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCad;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date tempoCad;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Musica musica;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Filme filme;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public Date getTempoCad() {
        return tempoCad;
    }

    public void setTempoCad(Date tempoCad) {
        this.tempoCad = tempoCad;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
