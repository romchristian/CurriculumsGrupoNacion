/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import py.com.palermo.curriculolanacion.entities.Area;
import py.com.palermo.curriculolanacion.entities.Curriculo;
import py.com.palermo.curriculolanacion.entities.EstadoCurriculo;
import py.com.palermo.curriculolanacion.entities.ExperienciaLaboral;
import py.com.palermo.curriculolanacion.entities.ReferenciaLaboral;
import py.com.palermo.curriculolanacion.entities.ReferenciaPersonal;
import py.com.palermo.curriculolanacion.generico.AbstractDAO;
import py.com.palermo.curriculolanacion.generico.BeanGenerico;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IAreaDAO;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.ICurriculoDAO;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IEmpresaDAO;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class CurriculoBean extends BeanGenerico<Curriculo> implements Serializable {

    @EJB
    private ICurriculoDAO ejb;
    @EJB
    private IAreaDAO areaDAO;
    @EJB
    private IEmpresaDAO empresaDAO;

    private UploadedFile file;

    @Override
    public Curriculo getActual() {
        Curriculo R = super.getActual();
        if (R.getId() == null || R.getId() == 0) {

            if (R.getReferenciasLaborales() == null) {
                List<ReferenciaLaboral> refsLaboral = new ArrayList<>();
                ReferenciaLaboral rl1 = new ReferenciaLaboral();
                rl1.setIndice(1);
                rl1.setCurriculo(R);
                ReferenciaLaboral rl2 = new ReferenciaLaboral();
                rl2.setCurriculo(R);
                rl2.setIndice(2);

                refsLaboral.add(rl1);
                refsLaboral.add(rl2);
                R.setReferenciasLaborales(refsLaboral);
            }

            if (R.getReferenciasPersonales() == null) {
                List<ReferenciaPersonal> refsPersonales = new ArrayList<>();

                ReferenciaPersonal rp1 = new ReferenciaPersonal();
                ReferenciaPersonal rp2 = new ReferenciaPersonal();
                rp1.setCurriculo(R);
                rp2.setCurriculo(R);
                rp1.setIndice(1);
                rp2.setIndice(2);

                refsPersonales.add(rp1);
                refsPersonales.add(rp2);

                R.setReferenciasPersonales(refsPersonales);
            }

            if (R.getExperienciasLaborales() == null) {
                List<ExperienciaLaboral> expsLaborales = new ArrayList<>();
                ExperienciaLaboral exp1 = new ExperienciaLaboral();
                ExperienciaLaboral exp2 = new ExperienciaLaboral();
                exp1.setIndice(1);
                exp2.setIndice(2);
                exp1.setCurriculo(R);
                exp2.setCurriculo(R);

                expsLaborales.add(exp1);
                expsLaborales.add(exp2);

                R.setExperienciasLaborales(expsLaborales);
            }
        }

        return R;
    }

    @Override
    public AbstractDAO<Curriculo> getEjb() {
        return ejb;
    }

    @Override
    public Curriculo getNuevo() {
        return new Curriculo();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public SelectItem[] getSelectItemsAreas() {

        List<Area> listaAreas;
        if (getActual().getEmpresa() == null) {
            listaAreas = areaDAO.findAllSinEmpresa();

        } else if (empresaDAO.tieneArea(getActual().getEmpresa())) {
            listaAreas = areaDAO.findAllConEmpresa(getActual().getEmpresa());
        } else {
            listaAreas = areaDAO.findAllSinEmpresa();
        }

        SelectItem[] items = new SelectItem[listaAreas.size() + 1];
        int i = 0;
        items[0] = new SelectItem("", "Todas las areas");
        i++;
        for (Area a : listaAreas) {
            items[i++] = new SelectItem(a, a.toString());
        }

        return items;
    }

    public SelectItem[] getSelectItemsDias() {
        SelectItem[] items = new SelectItem[32];
        items[0] = new SelectItem("", "--");
        for (int i = 1; i < 32; i++) {
            items[i] = new SelectItem("" + i, "" + i);
        }
        return items;
    }

    public SelectItem[] getSelectItemsAnios() {
        SelectItem[] items = new SelectItem[41];
        items[0] = new SelectItem("", "--");
        for (int i = 1; i < 41; i++) {
            items[i] = new SelectItem("" + (i + 1959), "" + (i + 1959));
        }
        return items;
    }

    public SelectItem[] getSelectItemsMes() {
        SelectItem[] items = new SelectItem[13];
        items[0] = new SelectItem("", "--");
        items[1] = new SelectItem("01", "Enero");
        items[2] = new SelectItem("02", "Febrero");
        items[3] = new SelectItem("03", "Marzo");
        items[4] = new SelectItem("04", "Abril");
        items[5] = new SelectItem("05", "Mayo");
        items[6] = new SelectItem("06", "Junio");
        items[7] = new SelectItem("07", "Julio");
        items[8] = new SelectItem("08", "Agosto");
        items[9] = new SelectItem("09", "Septiembre");
        items[10] = new SelectItem("10", "Octubre");
        items[11] = new SelectItem("11", "Noviembre");
        items[12] = new SelectItem("12", "Diciembre");

        return items;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        if (event.getFile() != null) {

            file = event.getFile();
            getActual().setAdjunto(file.getFileName());
            System.out.println("FILE: " + file);
        }
    }

    public void copyFile() {
        try {

            System.out.println("FILE en COPY: " + file);
            FacesContext fc = FacesContext.getCurrentInstance();
            String destination = fc.getExternalContext().getInitParameter("uploadDirectory");

            final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            destination += uuid;

            OutputStream out = new FileOutputStream(new File(destination + file.getFileName() + "." + (file.getFileName().split("\\."))[1]));

            System.out.println("DIR:  " + destination);
            int read = 0;
            byte[] bytes = new byte[1024];

            InputStream in = file.getInputstream();
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");

            getActual().setDirectoryUUID(uuid);

        } catch (IOException e) {
            getActual().setAdjunto(null);
            System.out.println(e.getMessage());
        }
    }

    public String guardar() {
        if (file != null) {
            copyFile();
        }

        LocalDate birthdate = LocalDate.parse(getActual().getAnioNac() + "-" + getActual().getMesNac() + "-" + getActual().getDiaNac(),
                DateTimeFormat.forPattern("yyyy-MM-dd"));
        getActual().setFechaNacimiento(birthdate.toDate());

        edit();
        return "/main/curriculo/listado";
    }

    public String guardar2() {
        if (file != null) {
            copyFile();
        }

        LocalDate birthdate = LocalDate.parse(getActual().getAnioNac() + "-" + getActual().getMesNac() + "-" + getActual().getDiaNac(),
                DateTimeFormat.forPattern("yyyy-MM-dd"));
        getActual().setFechaNacimiento(birthdate.toDate());

        edit();
        return "/confirmacion";
    }

    public String preseleccionar() {
        Long idActual = getActual().getId();
        getActual().setEstadoCurriculo(EstadoCurriculo.PRESELECCIONADO);
        getActual().setFechaSeleccionado(new Date());
        edit();
        return "vista.xhtml?faces-redirect=true&id=" + idActual;
    }

    public String contratar() {
        Long idActual = getActual().getId();
        getActual().setEstadoCurriculo(EstadoCurriculo.CONTRATADO);
        getActual().setFechaContratado(new Date());
        edit();
        return "vista.xhtml?faces-redirect=true&id=" + idActual;
    }

    public String pasarANuevo() {
        Long idActual = getActual().getId();
        getActual().setEstadoCurriculo(EstadoCurriculo.NUEVO);
        getActual().setFechaSeleccionado(null);
        getActual().setFechaContratado(null);
        edit();
        return "vista.xhtml?faces-redirect=true&id=" + idActual;
    }

    public Long getCountNuevos() {
        return ejb.countEstado(EstadoCurriculo.NUEVO);
    }

    public Long getCountPreseleccionado() {
        return ejb.countEstado(EstadoCurriculo.PRESELECCIONADO);
    }

    public Long getCountContratado() {
        return ejb.countEstado(EstadoCurriculo.CONTRATADO);
    }

}
