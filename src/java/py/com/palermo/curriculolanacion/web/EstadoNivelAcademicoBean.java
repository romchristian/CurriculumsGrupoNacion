/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.EstadoNivelAcademico;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IEstadoNivelAcademicoDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class EstadoNivelAcademicoBean extends BeanGenerico<EstadoNivelAcademico> implements Serializable{

    @EJB private IEstadoNivelAcademicoDAO ejb;
    
    
    @Override
    public AbstractDAO<EstadoNivelAcademico> getEjb() {
        return ejb;
    }

    @Override
    public EstadoNivelAcademico getNuevo() {
        return new EstadoNivelAcademico();
    }

    
}
