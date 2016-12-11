/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.DAO;

import br.com.senac.Domain.Cidade;
import br.com.senac.Domain.Estado;
import java.util.List;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author Rafael
 */
public class CidadeDAOTest {

    @Test
    @Ignore
    public void salvar() {
        int idEstado = 3;

        EstadoDAO estadoDAO = new EstadoDAO();

        Estado estado = estadoDAO.buscar(idEstado);

        Cidade cidade = new Cidade();
        cidade.setNome("Santa Maria");
        cidade.setEstado(estado);

        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.salvar(cidade);
    }

    @Test
    @Ignore
    public void listar() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> resultado = cidadeDAO.listar();

        System.out.println("Total de Registros Encontrados: " + resultado.size());

        for (Cidade cidade : resultado) {
            System.out.println("Código da Cidade: " + cidade.getId());
            System.out.println("Nome da Cidade: " + cidade.getNome());
            System.out.println("Código do Estado: " + cidade.getEstado().getId());
            System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
            System.out.println();
        }
    }

    @Test
    @Ignore
    public void buscar() {
        int id = 2;

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(id);

        System.out.println("Código da Cidade: " + cidade.getId());
        System.out.println("Nome da Cidade: " + cidade.getNome());
        System.out.println("Código do Estado: " + cidade.getEstado().getId());
        System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
    }

    @Test
    @Ignore
    public void excluir() {
        int id = 7;

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(id);

        cidadeDAO.excluir(cidade);

        System.out.println("Cidade Removida");
        System.out.println("Código da Cidade: " + cidade.getId());
        System.out.println("Nome da Cidade: " + cidade.getNome());
        System.out.println("Código do Estado: " + cidade.getEstado().getId());
        System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
    }

    @Test
    @Ignore
    public void editar() {
        int idCidade = 6;
        int idEstado = 11;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(idEstado);

        System.out.println("Código do Estado: " + estado.getId());
        System.out.println("Sigla do Estado: " + estado.getSigla());
        System.out.println("Nome do Estado: " + estado.getNome());

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(idCidade);

        System.out.println("Cidade A Ser Editada");
        System.out.println("Código da Cidade: " + cidade.getId());
        System.out.println("Nome da Cidade: " + cidade.getNome());
        System.out.println("Código do Estado: " + cidade.getEstado().getId());
        System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do Estado: " + cidade.getEstado().getNome());

        cidade.setNome("Guarapuava");
        cidade.setEstado(estado);

        cidadeDAO.editar(cidade);

        System.out.println("Cidade Editada");
        System.out.println("Código da Cidade: " + cidade.getId());
        System.out.println("Nome da Cidade: " + cidade.getNome());
        System.out.println("Código do Estado: " + cidade.getEstado().getId());
        System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
    }
}
