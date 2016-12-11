/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.dao;

import br.com.railsos.os.entidade.Agendamento;
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
public class OrdemServicoDAO extends GenericDAO<OrdemServico>{
//    public List<OrdemServico> buscaPorTecnico(int funcionarioId){
//          Session sessao = HibernateUtil.getSessionFactory().openSession();
//        try {
//            Criteria consulta = sessao.createCriteria(OrdemServico.class);
//            consulta.add(Restrictions.eq("funcionario.id", funcionarioId ));
//            consulta.addOrder(Order.asc("id"));
//            List<OrdemServico> resultado = consulta.list();
//            return resultado;
//        } catch (RuntimeException erro) {
//            throw erro;
//        } finally {
//            sessao.close();
//        }
//    }
}
