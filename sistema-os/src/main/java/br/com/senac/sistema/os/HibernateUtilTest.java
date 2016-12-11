/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.sistema.os;

import br.com.railsos.os.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Rafael
 */
public class HibernateUtilTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
