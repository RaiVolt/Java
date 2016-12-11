package br.com.railsos.os.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
//import org.primefaces.context.RequestContext;
//import org.primefaces.event.SelectEvent;

/**
 * @author Thamires
 */
@Entity
public class ClienteNew extends GenericDomain {

    @Column(length = 50, nullable = false)
    private String razaosocial;
    @Column(length = 50, nullable = false)
    private String nomefantasia;
    @Column(length = 50, nullable = false)
    private String cnpj;
    @Column(length = 50, nullable = false)
    private String responsavel;
    @Column(length = 50, nullable = false)
    private String cep;
    @Column(length = 50, nullable = false)
    private String endereco;
    @Column(length = 50, nullable = false)
    private String bairro;
    @Column(length = 50, nullable = false)
    private String cidade;
    @Column(length = 50, nullable = false)
    private String estado;
    @Column(length = 50, nullable = false)
    private String telefone;
    @Column(length = 50, nullable = false)
    private String celular;
    @Column(length = 50, nullable = false)
    private String email;

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
