/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.Util;




import br.com.senac.Util.HibernateUtil;
import br.com.senac.Domain.Usuario;
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

    public Usuario altentication(Usuario u) {
        Usuario user= null;
        sessao = HibernateUtil.getSessionFactory().openSession();
        /**
         * Inicia entao uma transação, para cadastrar o usuario
         */
//        if(sessao.createQuery("form Usuario where tipo=admin" + u.getTipo())== null)
//        {}
        transaction = sessao.beginTransaction();
        List lista = sessao.createQuery("from Usuario where email='" 
                + u.getEmail()+ "' and senha='" + DigestUtils.md5Hex(u.getSenha()) + "'").list();
        
        if(!lista.isEmpty()){
            user=(Usuario)lista.get(0);
        }
        return user;
    }
    
    
    
}
