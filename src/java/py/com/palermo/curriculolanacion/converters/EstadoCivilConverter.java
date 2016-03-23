/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.converters;

import javax.faces.convert.FacesConverter;
import py.com.palermo.curriculolanacion.entities.EstadoCivil;
import py.com.palermo.curriculolanacion.generico.ConverterGenerico;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = EstadoCivil.class)
public class EstadoCivilConverter extends ConverterGenerico<EstadoCivil> {

    @Override
    public String getBeanName() {
        return "estadoCivilBean";
    }
}
