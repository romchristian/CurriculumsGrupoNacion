/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.sesionbeans.interfaces;

import java.util.List;
import javax.ejb.Local;
import py.com.palermo.curriculolanacion.entities.Area;
import py.com.palermo.curriculolanacion.entities.Empresa;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.QueryParameter;

/**
 *
 * @author Acer
 */
@Local
public interface IAreaDAO extends AbstractDAO<Area> {

    @Override
    Area create(Area entity, String usuario);

    @Override
    Area edit(Area entity, String usuario);

    @Override
    Area find(Object id);

    @Override
    List<Area> findAll();

    @Override
    List<Area> findAll(String query, QueryParameter params);

    List<Area> findAllSinEmpresa();

    List<Area> findAllConEmpresa(Empresa empresa);

    @Override
    void remove(Area entity, String usuario);

}
