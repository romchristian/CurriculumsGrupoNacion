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
import py.com.palermo.curriculolanacion.entities.EstadoCivil;
import py.com.palermo.curriculolanacion.generico.ABMService;
import py.com.palermo.curriculolanacion.generico.QueryParameter;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IEstadoCivilDAO;


/*
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EstadoCivilDAO implements IEstadoCivilDAO {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public EstadoCivil create(EstadoCivil entity, String usuario) {
        return abmService.create(entity, usuario);
    }

    @Override
    public EstadoCivil edit(EstadoCivil entity, String usuario) {
        return abmService.update(entity, usuario);
    }

    @Override
    public void remove(EstadoCivil entity, String usuario) {
        abmService.delete(entity, usuario);
    }

    @Override
    public EstadoCivil find(Object id) {
        return abmService.find(id, EstadoCivil.class);
    }

    @Override
    public List<EstadoCivil> findAll() {
        return abmService.getEM().createQuery("select obj from EstadoCivil obj").getResultList();
    }

    @Override
    public List<EstadoCivil> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }

    @Override
    public List<EstadoCivil> findAllActive() {
        return abmService.getEM().createQuery("select obj from EstadoCivil obj WHERE OBJ.estado = :estado")
                .setParameter("estado", Estado.ACTIVO)
                .getResultList();
    }

    @Override
    public List<EstadoCivil> findAllActive(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
