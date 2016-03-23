/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.Departamento;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IDepartamentoDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class DepartamentoBean extends BeanGenerico<Departamento> implements Serializable{

    @EJB private IDepartamentoDAO ejb;
    
    
    @Override
    public AbstractDAO<Departamento> getEjb() {
        return ejb;
    }

    @Override
    public Departamento getNuevo() {
        return new Departamento();
    }

    
}
