/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.palermo.curriculolanacion.generico;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import py.com.palermo.curriculolanacion.entities.Usuario;
import py.com.palermo.curriculolanacion.sesionbeans.interfaces.IUsuarioDAO;




/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class Autenticador implements Serializable {

    @EJB
    private IUsuarioDAO usuarioInterface;
    @Inject
    private Credencial credencial;
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public void keepSessionAlive() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        request.getSession();
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(username, password);
            
            Usuario u = usuarioInterface.find(username);
            System.out.println("Usuario en el login: " + u);
            credencial.setUsuario(u);
            
        } catch (ServletException ex) {
            Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/main/curriculo/listado.xhtml?faces-redirect=true";
    }

    public String logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.getSession(false).invalidate();
            request.logout();
            String projectPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(projectPath + "/nuevo.xhtml");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return null;
    }
}
