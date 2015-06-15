package ar.edu.uai.paradigms.dao;

import ar.edu.uai.model.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collection;


public class UsuarioDAOImplHibernateJPA<T> extends GenericDaoHibernateJPA<T> implements UsuarioDAO<T> {


    @Override
    public Usuario existeUsuario(String nombre_usuario) {
        try {

            Query consulta = this.entityManager.createQuery("select u.usuario from Usuario as u where u.usuario=?");
            consulta.setParameter(1, nombre_usuario);
            return (Usuario) consulta.getSingleResult();
        }
        catch (NoResultException e) {

            System.out.println("HAY UN USUARIO CON ESE NOMBRE");
            return null;
        }




    }


}
