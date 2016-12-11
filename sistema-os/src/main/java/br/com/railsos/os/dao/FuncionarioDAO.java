/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.dao;

import br.com.railsos.os.entidade.Funcionario;
import br.com.railsos.os.entidade.OrdemServico;
import br.com.railsos.os.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rafael
 */
public class FuncionarioDAO extends GenericDAO<Funcionario>{
    public List<Funcionario> buscaPorTecnico(int ordemServicoId){
          Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Funcionario.class);
            consulta.add(Restrictions.eq("ordemservico.id", ordemServicoId ));
            consulta.addOrder(Order.asc("nome"));
            List<Funcionario> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}

