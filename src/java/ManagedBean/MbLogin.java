/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cabjr
 */
@ManagedBean
@RequestScoped
public class MbLogin {
 
    private String usuario;// get y set
    private String contrasenia;
    private final HttpServletRequest httpServletRequest; //request
    private final FacesContext faceContext; // acceder a vista de managedbean
    private FacesMessage facesMessage; //sirve para lanzar mensaje contexto vista
     
    public MbLogin() 
    {// inicializar nuestras variables
        faceContext=FacesContext.getCurrentInstance(); // consultores
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest(); 
    }
     
    public String login()//logica para validar logueo sin necesidad de base de datos
    {
        if(usuario.equals("CORE") && contrasenia.equals("1234"))
        {
            httpServletRequest.getSession().setAttribute("sessionUsuario", usuario);//almacenar sesion
            facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Exitoso", null);// envio mensaje informacion de logeo
            faceContext.addMessage(null, facesMessage);//
            return "acceso";// retorna el string
        }
        else//caso contrario en que no se pueda logear
        {
            facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Errados, intente de nuevo", null);//redireccionar al index con mensaje de error
            faceContext.addMessage(null, facesMessage);
            return "index";// regresar al index
        }
    }
 
    public String getUsuario() {
        return usuario;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
 
    public String getContrasenia() {
        return contrasenia;
    }
 
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }    
}