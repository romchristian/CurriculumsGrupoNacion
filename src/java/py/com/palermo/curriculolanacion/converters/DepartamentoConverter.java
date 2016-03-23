/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.converters;

import javax.faces.convert.FacesConverter;
import py.com.palermo.curriculolanacion.entities.Departamento;
import py.com.palermo.curriculolanacion.generico.ConverterGenerico;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Departamento.class)
public class DepartamentoConverter extends ConverterGenerico<Departamento> {

    @Override
    public String getBeanName() {
        return "departamentoBean";
    }
}
