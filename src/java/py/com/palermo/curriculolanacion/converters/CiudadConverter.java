/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.converters;

import javax.faces.convert.FacesConverter;
import py.com.palermo.curriculolanacion.entities.Ciudad;
import py.com.palermo.curriculolanacion.generico.ConverterGenerico;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Ciudad.class)
public class CiudadConverter extends ConverterGenerico<Ciudad> {

    @Override
    public String getBeanName() {
        return "ciudadBean";
    }
}
