/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.DAO;

import br.com.senac.Domain.Cidade;
import br.com.senac.Domain.Tipo;
import br.com.senac.Domain.Usuario;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Rafael
 */
public class UsuarioDAOTest {

    @Test
    @Ignore
    public void salvar() {
        Usuario usuario = new Usuario();

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(2);

        TipoDAO tipoDAO = new TipoDAO();
        Tipo tipo = tipoDAO.buscar(2);

        usuario.setNome("Rafael");
        usuario.setEmail("rafael@hotmail.com");
        usuario.setSenha("33333");
        usuario.setSexo('M');
        //usuario.setDataNac(102325/1992);
        usuario.setDataCad(new Date());
        usuario.setCidade(cidade);
        usuario.setTipo(tipo);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.salvar(usuario);

        System.out.println("Nome do Usuario:" + usuario.getNome());
        System.out.println("E-mail do Usuário: " + usuario.getEmail());
        System.out.println("Senha do Usuário: " + usuario.getSenha());
        System.out.println("Sexo do Usuário: " + usuario.getSexo());
        System.out.println("Idade do Usuário: " + usuario.getDataNac());
        System.out.println("Data de Cadastro do Usuário: " + usuario.getDataCad());
        System.out.println("Nome da Cidade: " + cidade.getNome());
        System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
        System.out.println("Tipo de Usuário: " + tipo.getDescricao());

        System.out.println();

    }

    @Test
    @Ignore
    public void listar() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> resultado = usuarioDAO.listar();

        System.out.println("Total de Registros Encontrados: " + resultado.size());

        for (Usuario usuario : resultado) {
            System.out.println("ID do Usuário:" + usuario.getId());
            System.out.println("Nome do Usuario:" + usuario.getNome());
            System.out.println("E-mail do Usuário: " + usuario.getEmail());
            System.out.println("Senha do Usuário: " + usuario.getSenha());
            System.out.println("Sexo do Usuário: " + usuario.getSexo());
            System.out.println("Idade do Usuário: " + usuario.getDataNac());
            System.out.println("Data de Cadastro do Usuário: " + usuario.getDataCad());
            System.out.println("Nome da Cidade: " + usuario.getCidade().getNome());
            System.out.println("Sigla do Estado: " + usuario.getCidade().getEstado().getSigla());
            System.out.println("Nome do Estado: " + usuario.getCidade().getEstado().getNome());
            System.out.println("Tipo de Usuário: " + usuario.getTipo().getDescricao());

        }
    }

    @Test
    @Ignore
    public void buscar() {
        int id = 1;

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscar(id);

        if (usuario == null) {
            System.out.println("Nenhum Registro Encontrado");
        } else {

            System.out.println("Registro encontrado: ");
            System.out.println("ID do Usuário:" + usuario.getId());
            System.out.println("Nome do Usuario:" + usuario.getNome());
            System.out.println("E-mail do Usuário: " + usuario.getEmail());
            System.out.println("Senha do Usuário: " + usuario.getSenha());
            System.out.println("Sexo do Usuário: " + usuario.getSexo());
            System.out.println("Idade do Usuário: " + usuario.getDataNac());
            System.out.println("Data de Cadastro do Usuário: " + usuario.getDataCad());
            System.out.println("Nome da Cidade: " + usuario.getCidade().getNome());
            System.out.println("Sigla do Estado: " + usuario.getCidade().getEstado().getSigla());
            System.out.println("Nome do Estado: " + usuario.getCidade().getEstado().getNome());
            System.out.println("Tipo de Usuário: " + usuario.getTipo().getDescricao());
        }
    }

}
