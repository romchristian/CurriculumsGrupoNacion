/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.Empresa;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IEmpresaDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class EmpresaBean extends BeanGenerico<Empresa> implements Serializable{

    @EJB private IEmpresaDAO ejb;
    
    
    @Override
    public AbstractDAO<Empresa> getEjb() {
        return ejb;
    }

    @Override
    public Empresa getNuevo() {
        return new Empresa();
    }

    
}
