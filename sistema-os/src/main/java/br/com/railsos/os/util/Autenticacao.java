/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.railsos.os.util;




import br.com.railsos.os.util.HibernateUtil;
import br.com.railsos.os.entidade.Login;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rafaek
 */
public class Autenticacao {

    private Session sessao;
    private Transaction transaction;
    private Statement st;

    public Autenticacao() {
    }

    public Login altentication(Login l) {
        Login user= null;
        sessao = HibernateUtil.getSessionFactory().openSession();
        /**
         * Inicia entao uma transação, para cadastrar o usuario
         */
//        if(sessao.createQuery("form Usuario where tipo=admin" + u.getTipo())== null)
//        {}
        transaction = sessao.beginTransaction();
        List lista = sessao.createQuery("from Login where username='" 
                + l.getUsername()+ "' and password='" + DigestUtils.md5Hex(l.getPassword()) + "'").list();
        
        if(!lista.isEmpty()){
            user=(Login)lista.get(0);
        }
        return user;
    }
    
    
    
}
