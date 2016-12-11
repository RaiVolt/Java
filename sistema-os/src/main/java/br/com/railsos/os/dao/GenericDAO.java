/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.dao;

import br.com.railsos.os.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rafael
 */
public class GenericDAO<Entidade> {

    private Class<Entidade> classe;
     private List<Entidade> list;

    public GenericDAO() {
        this.classe = (Class<Entidade>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Método Genérico Salvar Adiciona valores ao banco
     *
     * @author Rafael
     */
    public void salvar(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(entidade);
            transacao.commit();
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }

    /**
     * Método Genérico Listar Lista o Conteudo da tabela
     *
     * @author Rafael
     */
    public List<Entidade> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(classe);
            List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

    
     public List<Entidade> listar(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(entidade.getClass());
            List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
    /**
     * Método Genérico Listar Lista o Conteudo da tabela
     *
     * @author Rafael
     */
    public List<Entidade> listar(String campoOrdenacao) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(classe);
            consulta.addOrder(Order.asc(campoOrdenacao));
            List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

    /**
     * Método Buscar Busca pelo id a variavel
     *
     * @author Rafael
     */
    @SuppressWarnings("unchecked")
    public Entidade buscar(int id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(classe);
            consulta.add(Restrictions.idEq(id));
            Entidade resultado = (Entidade) consulta.uniqueResult();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

    /**
     * Metódo excluir Exclui variaveis do banco
     *
     * @author Rafael
     */
    public void excluir(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(entidade);
            transacao.commit();
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }

    /**
     * Metódo editar Edita variaveis do banco
     *
     * @author Rafael
     */
    public void editar(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(entidade);
            transacao.commit();
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }

    /**
     * Metódo Merge(fundir) Edita e Salva as variaveis do banco
     *
     * @author Rafael
     */
    public void merge(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.merge(entidade);
            transacao.commit();
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }

//    public List<Entidade> selectAll(Entidade entidade) {
////        /**
////         * Abre a sessão
////         */
//        Session sessao = HibernateUtil.getSessionFactory().openSession();
//        Transaction transacao = null;
////        /**
////         * Inicia entao uma transação, para cadastrar o usuario
////         */
//        Criteria criteria = sessao.createCriteria(entidade.getClass());
//        list = criteria.list();
//
//        return this.list;
//    }

    public ArrayList chart1(int tamanho) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        ArrayList<Number> listalista = new ArrayList<>();
        for (int i = 1; i <= tamanho; i++) {
            List lista = sessao.createQuery("from Ordemservico where atendente_id=" + i).list();
            listalista.add(lista.size());
        }

        return listalista;
    }

    public ArrayList chart11(int tamanho) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        ArrayList<String> listalista = new ArrayList<>();
//        sessao = HibernateUtil.getSessionFactory().openSession();
//        transaction = sessao.beginTransaction();
        String u = "";
        for (int i = 1; i <= tamanho; i++) {
            List lista = sessao.createQuery("SELECT f.nome from Funcionario f where f.id=" + i).list();
            u = (String) lista.get(0);
            listalista.add(u);
        }

        return listalista;
    }

    /**
     *
     * @return quantas questoes tem por dificuldade
     */
    public List chart2() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
//        sessao = HibernateUtil.getSessionFactory().openSession();
//        transaction = sessao.beginTransaction();
        List lista = sessao.createQuery("from Dificuldade d").list();
        List listagem = new ArrayList();
        for (int i = 1; i <= lista.size(); i++) {
            List listaquestoes = sessao.createQuery("from Questoes q where q.dificuldade=" + i).list();
            int tamanho = listaquestoes.size();
            listagem.add(tamanho);
        }
        return listagem;
    }

    /**
     *
     * @return restora as dificuldades;
     */
    public List chart22() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
//        sessao = HibernateUtil.getSessionFactory().openSession();
//        transaction = sessao.beginTransaction();
        List lista = sessao.createQuery("SELECT d.nivel from Dificuldade d").list();
        return lista;
    }
}
