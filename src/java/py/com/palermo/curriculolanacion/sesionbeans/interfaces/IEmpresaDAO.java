/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.sesionbeans.interfaces;

import java.util.List;
import javax.ejb.Local;
import py.com.palermo.curriculolanacion.entities.Empresa;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.QueryParameter;

/**
 *
 * @author Acer
 */
@Local
public interface IEmpresaDAO extends AbstractDAO<Empresa> {

    @Override
    Empresa create(Empresa entity, String usuario);

    @Override
    Empresa edit(Empresa entity, String usuario);

    @Override
    Empresa find(Object id);

    @Override
    List<Empresa> findAll();

    @Override
    List<Empresa> findAll(String query, QueryParameter params);
    
    boolean tieneArea(Empresa empresa);

    @Override
    void remove(Empresa entity, String usuario);

}
