package LogBean;

import Logic.*;
import java.io.IOException;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "MBSesion")
@ViewScoped
@SessionScoped
public class BeanSesion {

    /**
     * variables para llamar y mantener sesiones*
     */
    /**
     * Variables Implicitas*
     */
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    LogAdministracion Admin = new LogAdministracion();
    LogEmpleado Emp = new LogEmpleado();
    FacesContext Msj = FacesContext.getCurrentInstance();
    //

    /**
     * Variables para el manejo de los usuarios que presentan una sesion*
     */
    ResultSet Dat = null;
    private String cod_empleado;
    private String nombres;
    private String documento;
    private String rol;
    private String Mensaje;
    private String NewMensaje;
    private String calvalidador;
    private String sesion;

    /**
     * Variable tipo BeanLogin para traer los atributos y metodos de el
     * ManagedBean BeanLogin.java
     *
     *
     * @see BeanLogin.java
     */
    @ManagedProperty("#{MBLogin}")
    private BeanLogin mBLogin;

    public BeanLogin getmBLogin() {
        return mBLogin;
    }

    public void setmBLogin(BeanLogin mBLogin) {
        this.mBLogin = mBLogin;
    }

    /**
     * Variable tipo BeanTodero para traer los atributos y metodos de el
     * ManagedBean BeanTodero.java
     *
     *
     * @see BeanTodero.java
     */
    @ManagedProperty("#{MBTodero}")
    private BeanTodero mbTodero;

    public BeanTodero getMbTodero() {
        return mbTodero;
    }

    public void setMbTodero(BeanTodero mbTodero) {
        this.mbTodero = mbTodero;
    }

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return
     */
    public String getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(String cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getNewMensaje() {
        return NewMensaje;
    }

    public void setNewMensaje(String NewMensaje) {
        this.NewMensaje = NewMensaje;
    }

    public String getCalvalidador() {
        return calvalidador;
    }

    public void setCalvalidador(String calvalidador) {
        this.calvalidador = calvalidador;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }
    //GCH - ago2016
    public String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * FIN Metodos get y set de todas las variables / atributos, listas, etc que
     * se utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodos de funcionalidad de la clase /** FIN Metodos get y set de todas
     * las variables / atributos, listas, etc que se utilizaran para enviar y
     * traer datos a las respectivas variables ---funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo constructor de la clase que permite validar si ya se tiene una
     * sesion establecida o creada, de lo contrario asigna a las variables el
     * valor de las variables dde sesion creadas en en el ingreso de un usuario
     *
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @throws java.io.IOException
     * @since 01-05-2015
     * @see BeanLogin.java > method: login
     */
    public BeanSesion() throws IOException {
        try {
            this.Mensaje = Admin.cargaMen();
            faceContext = FacesContext.getCurrentInstance();
            httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
            if (httpServletRequest.getSession().getAttribute("sessionCodEmp") != null) {
                this.Mensaje = Admin.cargaMen();
                cod_empleado = httpServletRequest.getSession().getAttribute("sessionCodEmp").toString();
                documento = httpServletRequest.getSession().getAttribute("sessionDoc").toString();
                nombres = httpServletRequest.getSession().getAttribute("sessionNombresEmpl").toString();
                rol = httpServletRequest.getSession().getAttribute("sessionRol").toString();
                if (httpServletRequest.getSession().getAttribute("sessionFoto") != null) {
                    foto = httpServletRequest.getSession().getAttribute("sessionFoto").toString();
                } //GCH -AGO2016
                else {
                    foto = "Sin-foto.jpg";
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".BeanSesion()' causado por: " + e.getMessage());
            mbTodero.error();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        }
    }

    /**
     * Metodo que se utiliza para cerrar la sesion de un usuario de el sistema,
     * cancela y elimina todas las variables de sesion y retorna al formulario
     * de index.xhtml
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     *
     * @throws java.io.IOException
     * @since 01-10-2014
     */
    public void cerrarSession() throws IOException {
        try {
            httpServletRequest.getSession().removeAttribute("sessionRol");
            httpServletRequest.getSession().removeAttribute("sessionCodEmp");
            httpServletRequest.getSession().removeAttribute("sessionDoc");
            httpServletRequest.getSession().removeAttribute("sessionNombresEmpl");
            httpServletRequest.getSession().removeAttribute("sessionFoto"); //GCH -AGO2016
            mbTodero.setMens("!Sesion cerrarda correctamente !!!!");
            mbTodero.info();
            mBLogin.setResulto("Bien");
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            mBLogin.setResulto("mal");
            mbTodero.setMens("!Sesion cerrarda correctamente !!!!");
            mbTodero.info();
        }
    }

    /**
     * Metodo que se utiliza para la actualización del mensaje empresarial o
     * corpotativo que aparece en el costado derecho del boton cerrar sesion y
     * de el nombre de usuario que se encuentra en la sesion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void actualizarMensaje() {
        try {
            Admin.setMensaje_corp(this.NewMensaje);
            Admin.setCodEmp(Integer.parseInt(this.cod_empleado));
            Admin.actualizarMensaje();
            RequestContext.getCurrentInstance().execute("PF(dlgCambioMensaje).hide()");
            mbTodero.setMens("Mensaje actualizado");
            mbTodero.info();
            this.Mensaje = getNewMensaje();
            NewMensaje = "";
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarMensaje()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que obtiene el codigo de la sesion del empleado que este en el
     * sistema
     *
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return int que retorna el numero de codigo del empleado
     * @since 01-05-2015
     */
    public Integer codigoMiSesion() {
        Integer codigo = 0;
        try {
            codigo = Integer.parseInt(this.cod_empleado);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".codigoMiSesion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return codigo;
    }
}
