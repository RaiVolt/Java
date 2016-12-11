/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Domain;

import br.com.senac.DAO.LogDAO;
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
public class Log extends GenericDomain{
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Acao acao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Diretor diretor;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
     public void salvarLog(){
         LogDAO logDAO = new LogDAO();
        
        data = new Date(System.currentTimeMillis());
        setData(data);
        setHora(data);
        logDAO.salvar(this);
    }
    
}
