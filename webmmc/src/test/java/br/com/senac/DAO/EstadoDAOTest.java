/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.DAO;

import br.com.senac.Domain.Estado;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Rafael
 */
public class EstadoDAOTest {

    @Test
    @Ignore
    public void salvar() {
        Estado estado = new Estado();
        estado.setNome("Rio Grande do Sul");
        estado.setSigla("RS");

        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.salvar(estado);
    }

    @Test
    @Ignore
    public void listar() {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> resultado = estadoDAO.listar();

        System.out.println("Total de Registros Encontrados: " + resultado.size());

        for (Estado estado : resultado) {
            System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void buscar() {
        int id = 2;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(id);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado");
        } else {
            System.out.println("Registro encontrado:");
            System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void excluir() {
        int id = 9;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(id);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado");
        } else {
            estadoDAO.excluir(estado);
            System.out.println("Registro removido:");
            System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void editar() {
        int id = 10;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(id);

        if (estado == null) {
            System.out.println("Nenhum registro encontrado");
        } else {
            System.out.println("Registro editado - Antes:");
            System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
           
            estado.setNome("Santa Catarina");
            estado.setSigla("SC");
            estadoDAO.editar(estado);

            System.out.println("Registro editado - Depois:");
            System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
        }

    }
}
