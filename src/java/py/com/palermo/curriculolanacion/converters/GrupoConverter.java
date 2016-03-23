/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.converters;

import javax.faces.convert.FacesConverter;
import py.com.palermo.curriculolanacion.entities.Grupo;
import py.com.palermo.curriculolanacion.generico.ConverterGenerico;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Grupo.class,value = "grupoConverter")
public class GrupoConverter extends ConverterGenerico<Grupo> {

    @Override
    public String getBeanName() {
        return "grupoBean";
    }
}
