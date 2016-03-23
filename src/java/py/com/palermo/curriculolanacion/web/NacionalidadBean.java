/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.Nacionalidad;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.INacionalidadDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class NacionalidadBean extends BeanGenerico<Nacionalidad> implements Serializable{

    @EJB private INacionalidadDAO ejb;
    
    
    @Override
    public AbstractDAO<Nacionalidad> getEjb() {
        return ejb;
    }

    @Override
    public Nacionalidad getNuevo() {
        return new Nacionalidad();
    }

    
}
