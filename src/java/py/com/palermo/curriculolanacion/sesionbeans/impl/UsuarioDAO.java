/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.sesionbeans.impl;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.com.palermo.curriculolanacion.entities.Estado;
import py.com.palermo.curriculolanacion.entities.Usuario;
import py.com.palermo.curriculolanacion.generico.ABMService;
import py.com.palermo.curriculolanacion.generico.QueryParameter;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IUsuarioDAO;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UsuarioDAO implements IUsuarioDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Usuario create(Usuario entity, String usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public Usuario edit(Usuario entity, String usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(Usuario entity, String usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public Usuario find(Object id) {
        return abmService.find(id, Usuario.class);
    }

    @Override
    public Usuario find(String login) {
        Usuario R = null;

        try {
            R = (Usuario) abmService.getEM().createQuery("SELECT u FROM Usuario u WHERE u.username = :login ")
                    .setParameter("login", login).getSingleResult();
        } catch (Exception e) {
        }

        return R;
    }

    @Override
    public List<Usuario> findAll() {
        return abmService.getEM().createQuery("select obj from Usuario obj").getResultList();
    }

    @Override
    public List<Usuario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Usuario> findAllActive() {
        return abmService.getEM().createQuery("select obj from Usuario obj WHERE OBJ.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Usuario> findAllActive(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

}
