/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.com.palermo.curriculolanacion.entities.Area;
import py.com.palermo.curriculolanacion.entities.Curriculo;
import py.com.palermo.curriculolanacion.entities.Empresa;
import py.com.palermo.curriculolanacion.entities.EstadoCivil;
import py.com.palermo.curriculolanacion.entities.EstadoCurriculo;
import py.com.palermo.curriculolanacion.entities.EstadoNivelAcademico;
import py.com.palermo.curriculolanacion.entities.Nacionalidad;
import py.com.palermo.curriculolanacion.entities.NivelAcademico;
import py.com.palermo.curriculolanacion.entities.TipoDocumento;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.ICurriculoDAO;

/**
 *
 * @author christian.romero
 */
@Named
@ViewScoped
public class CurriculoFilter implements Serializable {

    @EJB
    private ICurriculoDAO dao;

    //actual seleccionado
    private Curriculo actual;

    //Filtros
    private Integer rangoEdad1 = 18;
    private Integer rangoEdad2 = 50;

    private NivelAcademico nivelAcademico;
    private EstadoNivelAcademico estadoNivelAcademico;
    private EstadoCivil estadoCivil;

    private Nacionalidad nacionalidad;
    private String sexo;
    private Empresa empresa;
    private Area area;
    private TipoDocumento tipoDocumento;

    private String tieneHijos;
    private String nombre;
    private EstadoCurriculo estadoCurriculo;

    //Param
    private int estado;

    //Resultado
    private List<Curriculo> resultado;

    //Getter y Setter
    public EstadoCurriculo getEstadoCurriculo() {
        return estadoCurriculo;
    }

    public void setEstadoCurriculo(EstadoCurriculo estadoCurriculo) {
        this.estadoCurriculo = estadoCurriculo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Curriculo getActual() {
        return actual;
    }

    public void setActual(Curriculo actual) {
        this.actual = actual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRangoEdad1() {
        return rangoEdad1;
    }

    public void setRangoEdad1(Integer rangoEdad1) {
        this.rangoEdad1 = rangoEdad1;
    }

    public Integer getRangoEdad2() {
        return rangoEdad2;
    }

    public void setRangoEdad2(Integer rangoEdad2) {
        this.rangoEdad2 = rangoEdad2;
    }

    public NivelAcademico getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(NivelAcademico nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public EstadoNivelAcademico getEstadoNivelAcademico() {
        return estadoNivelAcademico;
    }

    public void setEstadoNivelAcademico(EstadoNivelAcademico estadoNivelAcademico) {
        this.estadoNivelAcademico = estadoNivelAcademico;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTieneHijos() {
        return tieneHijos;
    }

    public void setTieneHijos(String tieneHijos) {
        this.tieneHijos = tieneHijos;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<Curriculo> getResultado() {
        return resultado;
    }

    public void setResultado(List<Curriculo> resultado) {
        this.resultado = resultado;
    }

    public String cargaEstado() {
        System.out.println("Estado Curriculo: " + estadoCurriculo);
        if (estado > 0) {
            switch (estado) {
                case 1:
                    estadoCurriculo = EstadoCurriculo.NUEVO;
                    break;
                case 2:
                    estadoCurriculo = EstadoCurriculo.PRESELECCIONADO;
                    break;
                case 3:
                    estadoCurriculo = EstadoCurriculo.CONTRATADO;
                    break;
            }

            busca();

        }

        return null;
    }

    private String filtroNombre() {
        String R = "";
        if (nombre != null && nombre.length() > 0) {
            R = " AND nombres like '%" + nombre + "%' ";
        }

        return R;
    }

    //Métodos para el filtro
    private String filtroEdad() {
        String R = "";
        if (rangoEdad1 != null && rangoEdad2 != null) {
            R = " AND date_part('year',age(fechanacimiento)) between " + rangoEdad1 + " and " + rangoEdad2 + " ";
        }

        return R;
    }

    private String filtroEstadoCivil() {
        String R = "";
        if (estadoCivil != null) {
            R = " AND estadocivil_id = " + estadoCivil.getId() + " ";
        }

        return R;
    }

    private String filtroEmpresa() {
        String R = "";
        if (empresa != null) {
            R = " AND empresa_id = " + empresa.getId() + " ";
        }

        return R;
    }

    private String filtroArea() {
        String R = "";
        if (area != null) {
            R = " AND area_id = " + area.getId() + " ";
        }

        return R;
    }

    private String filtroNivelAcademico() {
        String R = "";
        if (nivelAcademico != null) {
            R = " AND nivelacademico_id = " + nivelAcademico.getId() + " ";
        }

        return R;
    }

    private String filtroEstadoNivelAcademico() {
        String R = "";
        if (estadoNivelAcademico != null) {
            R = " AND estadonivelacademico_id = " + estadoNivelAcademico.getId() + " ";
        }

        return R;
    }

    private String filtroNacionalidad() {
        String R = "";
        if (nacionalidad != null) {
            R = " AND nacionalidad_id = " + nacionalidad.getId() + " ";
        }

        return R;
    }

    private String filtroSexo() {
        String R = "";
        if (sexo != null && sexo.length() > 0) {
            R = " AND sexo = '" + sexo + "' ";
        }

        return R;
    }

    private String filtroEstadoCurriculo() {
        String R = "";
        if (estadoCurriculo != null) {
            R = " AND (estadocurriculo = '" + estadoCurriculo.toString() + "' OR estadocurriculo is null)";
        }

        return R;
    }

    private String filtroTieneHijos() {
        String R = "";
        if (tieneHijos != null) {
            switch (tieneHijos) {
                case "CON HIJOS":
                    R = " AND cantidadHijos > 0 ";
                    break;
                case "SIN HIJOS":
                    R = " AND (cantidadHijos = 0  OR cantidadHijos is null) ";
                    break;
            }
        }
        return R;
    }

    private String construyeFiltro() {
        StringBuilder sb = new StringBuilder(" WHERE 1 = 1 ");
        sb.append(filtroNombre());
        sb.append(filtroEdad());
        sb.append(filtroArea());
        sb.append(filtroEmpresa());
        sb.append(filtroEstadoCivil());
        sb.append(filtroNivelAcademico());
        sb.append(filtroEstadoNivelAcademico());
        sb.append(filtroNacionalidad());
        sb.append(filtroTieneHijos());
        sb.append(filtroSexo());
        sb.append(filtroEstadoCurriculo());

        return sb.toString();
    }

    public void busca() {
        resultado = dao.findAllFilter(construyeFiltro());
    }

}
