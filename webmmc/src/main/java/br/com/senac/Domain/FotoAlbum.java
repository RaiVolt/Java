/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author Rafael
 */
@Entity
public class FotoAlbum extends GenericDomain{
    
    @Lob
    @Column( name = "FOTO_IMAGEM", columnDefinition = "LONGBLOB")
    private byte[] imagem;
    
    @Column(nullable = false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Album album;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    
}
