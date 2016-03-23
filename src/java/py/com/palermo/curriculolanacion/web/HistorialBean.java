/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.Historial;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IHistorialDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class HistorialBean extends BeanGenerico<Historial> implements Serializable{

    @EJB private IHistorialDAO ejb;
    
    
    @Override
    public AbstractDAO<Historial> getEjb() {
        return ejb;
    }

    @Override
    public Historial getNuevo() {
        return new Historial();
    }

    
}
