/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.EstadoCivil;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IEstadoCivilDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class EstadoCivilBean extends BeanGenerico<EstadoCivil> implements Serializable{

    @EJB private IEstadoCivilDAO ejb;
    
    
    @Override
    public AbstractDAO<EstadoCivil> getEjb() {
        return ejb;
    }

    @Override
    public EstadoCivil getNuevo() {
        return new EstadoCivil();
    }

    
}
