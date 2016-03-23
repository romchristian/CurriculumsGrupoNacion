/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.TipoDocumento;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.ITipoDocumentoDAO;



/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class TipoDocumentoBean extends BeanGenerico<TipoDocumento> implements Serializable{

    @EJB private ITipoDocumentoDAO ejb;
    
    
    @Override
    public AbstractDAO<TipoDocumento> getEjb() {
        return ejb;
    }

    @Override
    public TipoDocumento getNuevo() {
        return new TipoDocumento();
    }

    
}
