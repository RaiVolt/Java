/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.DAO;

import br.com.senac.DAO.GenericDAO;
import br.com.senac.Domain.Cidade;
import br.com.senac.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rafael
 */
public class CidadeDAO extends GenericDAO<Cidade>{
    public List<Cidade> buscaPorEstado(int estadoId){
          Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Cidade.class);
            consulta.add(Restrictions.eq("estado.id", estadoId ));
            consulta.addOrder(Order.asc("nome"));
            List<Cidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
