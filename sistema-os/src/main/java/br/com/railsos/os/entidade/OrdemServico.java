package br.com.railsos.os.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrdemServico extends GenericDomain {

   
    @Column(length = 100, nullable = false)
    private String descricao;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(length = 50, nullable = false)
    private String status;
    @Column(length = 50, nullable = false)
    private String hora;
    @OneToOne
    @JoinColumn( nullable = false)
    private ClienteNew cliente;
    @OneToOne
    @JoinColumn( nullable = false)
    private Funcionario atendente;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ClienteNew getCliente() {
        return cliente;
    }

    public void setCliente(ClienteNew cliente) {
        this.cliente = cliente;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

   
    

}
