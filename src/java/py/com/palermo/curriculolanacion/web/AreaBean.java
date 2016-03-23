/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.Area;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IAreaDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class AreaBean extends BeanGenerico<Area> implements Serializable{

    @EJB private IAreaDAO ejb;
    
    
    @Override
    public AbstractDAO<Area> getEjb() {
        return ejb;
    }

    @Override
    public Area getNuevo() {
        return new Area();
    }

    
}
