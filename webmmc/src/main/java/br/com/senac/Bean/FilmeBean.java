/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Bean;

import br.com.senac.DAO.AlbumDAO;
import br.com.senac.DAO.ArtistaDAO;
import br.com.senac.DAO.AtorDAO;
import br.com.senac.DAO.DiretorDAO;
import br.com.senac.DAO.FilmeDAO;
import br.com.senac.DAO.GeneroFilmeDAO;
import br.com.senac.Domain.Ator;
import br.com.senac.Domain.Diretor;
import br.com.senac.Domain.Filme;
import br.com.senac.Domain.GeneroFilme;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Rafael
 */
@ManagedBean
@ViewScoped
public class FilmeBean implements Serializable {

    private Filme filme;
    private List<Filme> filmes;
    private List<Ator> atores;
    private List<Diretor> diretores;
    private List<GeneroFilme> generoFilmes;
    private UploadArquivo arquivo = new UploadArquivo();

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public void setDiretores(List<Diretor> diretores) {
        this.diretores = diretores;
    }

    public List<GeneroFilme> getGeneroFilmes() {
        return generoFilmes;
    }

    public void setGeneroFilmes(List<GeneroFilme> generoFilmes) {
        this.generoFilmes = generoFilmes;
    }

    @PostConstruct
    public void listar() {
        try {
            FilmeDAO filmeDAO = new FilmeDAO();
            filmes = filmeDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os filmes");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            filme = new Filme();
            this.arquivo = new UploadArquivo();
            Date data = new Date();

            AtorDAO atorDAO = new AtorDAO();
            atores = atorDAO.listar();
            DiretorDAO diretorDAO = new DiretorDAO();
            diretores = diretorDAO.listar();
            GeneroFilmeDAO generoFilmeDAO = new GeneroFilmeDAO();
            generoFilmes = generoFilmeDAO.listar();
            
            this.filme.setDataCad(data);
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo Filme");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            FilmeDAO filmeDAO = new FilmeDAO();
            filmeDAO.merge(filme);
            this.arquivo.gravar();

            filme = new Filme();
            this.arquivo = new UploadArquivo();

            AtorDAO atorDAO = new AtorDAO();
            atores = atorDAO.listar();
            DiretorDAO diretorDAO = new DiretorDAO();
            diretores = diretorDAO.listar();
            GeneroFilmeDAO generoFilmeDAO = new GeneroFilmeDAO();
            generoFilmes = generoFilmeDAO.listar();

            filmes = filmeDAO.listar();

            Messages.addGlobalInfo("Filme salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo filme");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            filme = (Filme) evento.getComponent().getAttributes().get("filmeSelecionado");

            FilmeDAO filmeDAO = new FilmeDAO();
            filmeDAO.excluir(filme);

            filmes = filmeDAO.listar();

            Messages.addGlobalInfo("Filme removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o filme");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            filme = (Filme) evento.getComponent().getAttributes().get("filmeSelecionado");

            AtorDAO atorDAO = new AtorDAO();
            atores = atorDAO.listar();
            DiretorDAO diretorDAO = new DiretorDAO();
            diretores = diretorDAO.listar();
            GeneroFilmeDAO generoFilmeDAO = new GeneroFilmeDAO();
            generoFilmes = generoFilmeDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um música");
            erro.printStackTrace();
        }
    }

    public void uploadAction(FileUploadEvent event) {
        this.arquivo.fileUpload(event, ".jpg", "/image/");
        this.filme.setFoto(this.arquivo.getNome());
    }
}
