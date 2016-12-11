/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.DAO;

import br.com.senac.DAO.GenericDAO;
import br.com.senac.Domain.FotoAlbum;
import br.com.senac.Util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rafael
 */
public class FotoAlbumDAO extends GenericDAO<FotoAlbum> {

    private Session session;
    
    @SuppressWarnings("unchecked")        
    public List<FotoAlbum> listByAlbum(int id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            return session.createCriteria(FotoAlbum.class, "falbum")
                    .createAlias("album", "a")
                    .add(Restrictions.eq("falbum.id", id)).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

}
