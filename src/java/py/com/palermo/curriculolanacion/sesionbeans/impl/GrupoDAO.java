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
import py.com.palermo.curriculolanacion.entities.Grupo;
import py.com.palermo.curriculolanacion.generico.ABMService;
import py.com.palermo.curriculolanacion.generico.QueryParameter;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IGrupoDAO;


/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GrupoDAO implements IGrupoDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Grupo create(Grupo entity, String usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public Grupo edit(Grupo entity, String usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(Grupo entity, String usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public Grupo find(Object id) {
        return abmService.find(id, Grupo.class);
    }

    @Override
    public List<Grupo> findAll() {
        return abmService.getEM().createQuery("select obj from Grupo obj").getResultList();
    }

    @Override
    public List<Grupo> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<Grupo> findAllActive() {
        return abmService.getEM().createQuery("select obj from Grupo obj WHERE OBJ.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<Grupo> findAllActive(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
