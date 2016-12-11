/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.DAO;

import br.com.senac.Domain.Estado;
import br.com.senac.Domain.Tipo;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Rafael
 */
public class TipoDAOTest {

    @Test
    @Ignore
    public void salvar() {
        Tipo tipo = new Tipo();
        tipo.setDescricao("Usuário Premium");

        TipoDAO tipoDAO = new TipoDAO();
        tipoDAO.salvar(tipo);
        
        System.out.println("Resultado: " + tipo.getDescricao());
    }

    @Test
    @Ignore
    public void listar() {
        TipoDAO tipoDAO = new TipoDAO();
        List<Tipo> resultado = tipoDAO.listar();

        System.out.println("Total de Registros Encontrados: " + resultado.size());

        for (Tipo tipo : resultado) {
            System.out.println(tipo.getId() + " - " + tipo.getDescricao());
        }
    }

}
