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
import py.com.palermo.curriculolanacion.entities.Comentario;
import py.com.palermo.curriculolanacion.entities.Estado;
import py.com.palermo.curriculolanacion.generico.ABMService;
import py.com.palermo.curriculolanacion.generico.QueryParameter;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IComentarioDAO;


/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ComentarioDAO implements IComentarioDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Comentario create(Comentario entity, String usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public Comentario edit(Comentario entity, String usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(Comentario entity, String usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public Comentario find(Object id) {
        return abmService.find(id, Comentario.class);
    }

    @Override
    public List<Comentario> findAll() {
        return abmService.getEM().createQuery("select obj from Comentario obj").getResultList();
    }

    @Override
    public List<Comentario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
    
    @Override
    public List<Comentario> findAllActive() {
        return abmService.getEM().createQuery("select obj from Comentario obj WHERE OBJ.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Comentario> findAllActive(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

}
