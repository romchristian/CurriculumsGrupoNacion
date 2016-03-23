/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.sesionbeans.interfaces;

import java.util.List;
import javax.ejb.Local;
import py.com.palermo.curriculolanacion.entities.Comentario;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.QueryParameter;

/**
 *
 * @author Acer
 */
@Local
public interface IComentarioDAO extends AbstractDAO<Comentario> {

    @Override
    Comentario create(Comentario entity, String usuario);

    @Override
    Comentario edit(Comentario entity, String usuario);

    @Override
    Comentario find(Object id);

    @Override
    List<Comentario> findAll();

    @Override
    List<Comentario> findAll(String query, QueryParameter params);

    @Override
    void remove(Comentario entity, String usuario);

}
