package LogBean;

import Conexion.Conexion;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Logic.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de login, ingreso y salida de los
 * usuarios a la aplicacion </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-10-2014 1.0.0
 *
 */
@ManagedBean(name = "MBLogin")
@SessionScoped
@ViewScoped
public class BeanLogin {

    public BeanLogin() {
    }

    /**
     * Variables Implicitas*
     */
    private HttpServletRequest HttpServletRequest;
    private FacesContext FContext;
    LogEmpleado Emp = new LogEmpleado();
    BeanCorreo MBCor = new BeanCorreo();
    ResultSet Dat = null;
    ResultSet Dat1 = null;
    ResultSet Tab = null;
    FacesContext Redir;
    private String[] Rol;
    private String Mens, thisIp = "";
    int CodEmp;
    int cc = 0;
    String Obser = "<br/> <br/> <br/> <br/> SEÑOR USUARIO FAVOR TENGA EN CUENTA QUE ESTA ES UNA CUENTA NO ATENDIDA FAVOR NO RESPONDER EL CORREO YA QUE NO SERA RESPONDIDO";

    /**
     * Variables utilizadas para el manejo de la sesion de los usuarios*
     */
    private String NomUsuario;
    private String NomUsuario1;
    private String PassUsuario;
    private String DocEmp;
    private String DocEmp1;
    private String DocEmp2;
    private String PassOld;
    private String PassOne;
    private String PassTwo;
    private String Novedad;
    private String resulto;
    int CodEntrada;
    private String Foto_emp; //GCH - AGO2016
    
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
    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String NomUsuario) {
        this.NomUsuario = NomUsuario;
    }

    public String getNomUsuario1() {
        return NomUsuario1;
    }

    public void setNomUsuario1(String NomUsuario1) {
        this.NomUsuario1 = NomUsuario1;
    }

    public String getPassUsuario() {
        return PassUsuario;
    }

    public void setPassUsuario(String PassUsuario) {
        this.PassUsuario = PassUsuario;
    }

    public String getDocEmp() {
        return DocEmp;
    }

    public void setDocEmp(String DocEmp) {
        this.DocEmp = DocEmp;
    }

    public String getDocEmp1() {
        return DocEmp1;
    }

    public void setDocEmp1(String DocEmp1) {
        this.DocEmp1 = DocEmp1;
    }

    public String getDocEmp2() {
        return DocEmp2;
    }

    public void setDocEmp2(String DocEmp2) {
        this.DocEmp2 = DocEmp2;
    }

    public int getCodEmp() {
        return CodEmp;
    }

    public void setCodEmp(int CodEmp) {
        this.CodEmp = CodEmp;
    }

    public String getPassOld() {
        return PassOld;
    }

    public void setPassOld(String PassOld) {
        this.PassOld = PassOld;
    }

    public String getPassOne() {
        return PassOne;
    }

    public void setPassOne(String PassOne) {
        this.PassOne = PassOne;
    }

    public String getPassTwo() {
        return PassTwo;
    }

    public void setPassTwo(String PassTwo) {
        this.PassTwo = PassTwo;
    }

    public String getNovedad() {
        return Novedad;
    }

    public void setNovedad(String Novedad) {
        this.Novedad = Novedad;
    }

    public String getResulto() {
        return resulto;
    }

    public void setResulto(String resulto) {
        this.resulto = resulto;
    }

    //GCH - AGO2016
    public String getFoto_emp() {
        return Foto_emp;
    }

    public void setFoto_emp(String Foto_emp) {
        this.Foto_emp = Foto_emp;
    }
    /**
     * FIN Metodos get y set de todas las variables / atributos, listas, etc que
     * se utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo para obtener ip y enviarsela al administrador cuando hay mas de
     * tres intentos en el ingreso de la aplicacion de control y seguimiento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @since 01-05-2015
     */
    public void obtenIP() {
        try {
            thisIp = InetAddress.getLocalHost().getHostAddress();
            envioIPIngreso(cc++);
        } catch (UnknownHostException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".obtenIP()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para enviar al administrador cuando hay mas de tres intentos en el
     * ingreso de la aplicacion de control y seguimiento, se le envia la ip del
     * pc que esta intentando ingresar
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @param P int
     * @since 01-05-2015
     */
    public void envioIPIngreso(int P) {
        try {
            if (P < 2) {
                MBCor.setAsunto("ISA - Problemas De Ingreso");
                MBCor.setMailDestino("administrador@isainmobiliaria.com.co");
                MBCor.setMensaje("Señor Administrador <br/> <br/> Intentan entrar a la aplicacion mas de tres veces desde la siguiente ip: \r" + thisIp + "<br/> <br/> Favor verificar el inconveniente");
                MBCor.enviarCorreo(1);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".envioIPIngreso()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para verificar, ingresar y crear las variables de sesion para que
     * un usuario ingrese a la aplicacion con el usuario y contraseña
     * correspondiente
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @return String que contiene la url a la que va a direccionar la pagina
     * @since 01-05-2015
     */
    public String login() {
        try {
            if (CodEntrada < 3) {
                if ("".equals(this.NomUsuario) || "".equals(this.PassUsuario)) {
                    mbTodero.setMens("Falta informacion para realizar el proceso");
                    mbTodero.warn();
                    this.resulto = "mal";
                } else {
                    Emp.setUsuarEmp(NomUsuario);
                    Emp.setPassEmp(PassUsuario);
                    Dat1 = null;
                    Dat1 = Emp.ConsultaTipoPassword();
                    if (Dat1.next()) {
                        if ("1".equals(Dat1.getString("Temporal_Empleados"))) {
                            CodEmp = Integer.parseInt(Dat1.getString("Cod_Empleados"));
                            Map<String, Object> options = new HashMap<>();
                            options.put("modal", true);
                            options.put("draggable", false);
                            options.put("resizable", false);
                            options.put("contentHeight", 280);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UsuarPass", Dat1.getString("Cod_Empleados"));
                            RequestContext.getCurrentInstance().openDialog("XHTML/Cuerpo/CambioPassword.xhtml", options, null);
                        } else {
                            Dat = Emp.ConsulRolUusario();
                            if (Dat.next()) {
                                int codEmp = Integer.parseInt(Dat.getString("Cod_Empleados"));
                                String nombre_rol = Dat.getString("Nombre_Rol");
                                String nombres_apellidos = Dat1.getString("Nombre_Empleados") + " " + Dat1.getString("Apellido_empleados");
                                String documento = Dat.getString("Documento_Empleados");
                                String foto = Dat.getString("Foto_Empleados"); //GCH - ago2016
                                //Establecer variables de sesion
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionRol", nombre_rol);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionCodEmp", codEmp);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionDoc", documento);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionNombresEmpl", nombres_apellidos);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionFoto", foto); //GCH - ago2016
                                return "CuerpoSiai.xhtml";

                            } else {
                                mbTodero.setMens("El usuario con el que desea ingresar a la aplicación no se encuentra activo o no se tiene registro");
                                mbTodero.warn();
                                this.resulto = "mal";
                                return "index.xhtml";
                            }
					
                        }
                    } else {
                        mbTodero.setMens("Usuario y/o contraseña incorrectos");
                        mbTodero.warn();
                        this.resulto = "mal";
                        CodEntrada++;
                        return "index.xhtml";
                    }
                    Conexion.CloseCon();
                }
            } else {
                mbTodero.setMens("Ha realizado mas de tres intentos favor realice alguna de las opciones de Ingreso al sistema o contacte al administrador");
                mbTodero.warn();
                this.resulto = "mal";
                CodEntrada = 0;
                //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                return "index.xhtml";
            }
        } catch (SQLException | NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".login()' causado por: " + e.getMessage());
            mbTodero.error();
        }

        return "../index.xhtml";
    }

    /**
     * Metodo que envia en un correo la contraseña en el proceso de
     * autenticacion cuando un usuario ha olvidado su contraseña
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @since 01-05-2015
     */
    public void envioPasswordCorreo() {
        try {
            if ("".equals(this.DocEmp) && "".equals(this.NomUsuario1)) {
                mbTodero.setMens("Debe ingresar toda la información requerida");
                mbTodero.warn();
            } else if ("".equals(this.DocEmp) || DocEmp == null) {
                mbTodero.setMens("Debe ingresar el numero de documento");
                mbTodero.warn();
            } else if ("".equals(this.NomUsuario1) || NomUsuario1 == null) {
                mbTodero.setMens("Debe ingresar el usuario");
                mbTodero.warn();
            } else {
                Emp.setDocEmp(DocEmp);
                Emp.setUsuarEmp(NomUsuario1);
                Dat = Emp.ConsulDoc(0);
                if (Dat.next()) {
                    String Pass = "", Usu = "", Nom = "";
                    int Cod = Integer.parseInt(Dat.getString("Cod_Empleados"));
                    String Corp = Dat.getString("Correo_Personal_Empleados");
                    String Cor = Dat.getString("Correo_Corporativo_Empleados");
                    Emp.setCodEmp(Cod);
                    Tab = Emp.ConsulCorreo();
                    if (Tab != null) {
                        if (Tab.next()) {
                            if ("".equals(Cor)) {
                                Pass = Tab.getString("Password_Empleados");
                                Usu = Tab.getString("Username_Empleados");
                                Nom = Tab.getString("Nombre_Empleados") + " " + Tab.getString("Apellido_Empleados");
                                MBCor.setAsunto("Notificación - Cambio de contraseña de acceso al sistema SIAI 2.0");
                                MBCor.setMailDestino(Corp);
                                MBCor.setMensaje("Señor/a: " + Nom + " "
                                        + "<br/><br/> Cordial Saludo, "
                                        + "<br/><br/>Se ha completado exitosamente la recuperación de su contraseña de acceso al sistema SIAI 2.0 de ISA Inmobiliaria "
                                        + "<br/><br/>su nueva contraseña es como se indica a continuación: "
                                        + "<br/><b> CONTRASEÑA es: </b>" + Pass + Obser);
                                MBCor.enviarCorreo(1);
                                mbTodero.setMens("Ha sido enviado el correo correctamente");
                                mbTodero.info();
                                RequestContext.getCurrentInstance().execute("PF('RecuperarPass').hide()");
                            } else {
                                Pass = Tab.getString("Password_Empleados");
                                Usu = Tab.getString("Username_Empleados");
                                Nom = Tab.getString("Nombre_Empleados") + " " + Tab.getString("Apellido_Empleados");
                                MBCor.setAsunto("Notificación - Cambio de contraseña de acceso al sistema SIAI 2.0");
                                MBCor.setMailDestino(Cor);
                                MBCor.setMensaje("Señor/a: " + Nom + " "
                                        + "<br/><br/> Cordial Saludo, "
                                        + "<br/><br/>Se ha completado exitosamente la recuperación de su contraseña de acceso al sistema SIAI 2.0 de ISA Inmobiliaria "
                                        + "<br/><br/>Su nueva contraseña es como se indica a continuación: "
                                        + "<br/>CONTRASEÑA es: " + Pass + Obser);
                                MBCor.enviarCorreo(1);
                                mbTodero.setMens("Ha sido enviado el correo correctamente");
                                mbTodero.info();
                                DocEmp = "";
                                NomUsuario1 = "";
                                RequestContext.getCurrentInstance().execute("PF('RecuperarPass').hide()");
                            }
                        }
                    }
                } else {
                    mbTodero.setMens("La información ingresada no se encuentra registrada, por favor verifique");
                    mbTodero.warn();
                    
                }
                Conexion.CloseCon();

            }

        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".envioPasswordCorreo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que envia en un correo el nombre de usuario en el proceso de
     * autenticacion cuando un usuario ha olvidado su nombre de usuario
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @since 01-05-2015
     */
    public void envioUsuarioCorreo() {
        try {
            if ("".equals(this.DocEmp1) || DocEmp1 == null) {
                mbTodero.setMens("Debe ingresar el numero de documento");
                mbTodero.warn();
            } else {
                Emp.setDocEmp(DocEmp1);
                Dat = Emp.ConsulDoc(1);
                if (Dat.next()) {
                    String Nom = "";
                    String Usu = Dat.getString("Username_Empleados");
                    String Corp = Dat.getString("Correo_Personal_Empleados");
                    String Cor = Dat.getString("Correo_Corporativo_Empleados");
                    if ("".equals(Cor)) {
                        Nom = Dat.getString("Nombre_Empleados") + " " + Dat.getString("Apellido_Empleados");
                        MBCor.setAsunto("Notificación - Recuperación de cuenta de acceso al sistema SIAI2.0");
                        MBCor.setMailDestino(Corp);
                        MBCor.setMensaje("Señor/a: " + Nom + ""
                                + "<br/><br/>Cordial Saludo, "
                                + "<br/><br/> Se ha completado exitosamente la recuperación de su cuenta de acceso al sistema SIAI 2.0 de ISA Inmobiliaria"
                                + "<br/> recuerde que su cuenta asignada es: " + Usu + Obser);
                        MBCor.enviarCorreo(1);
                        mbTodero.setMens("Ha sido enviado el correo correctamente");
                        mbTodero.info();
                        RequestContext.getCurrentInstance().execute("PF('RecuperarPass').hide()");
                        DocEmp1 = "";
                    } else {
                        Nom = Dat.getString("Nombre_Empleados") + " " + Dat.getString("Apellido_Empleados");
                        MBCor.setAsunto("Notificación - Recuperación de cuenta de acceso al sistema SIAI2.0");
                        MBCor.setMailDestino(Cor);
                        MBCor.setMensaje("Señor/a: " + Nom + ""
                                + "<br/><br/> Cordial Saludo, "
                                + "<br/><br/> Se ha completado exitosamente la recuperación de su cuenta de acceso al sistema SIAI 2.0 de ISA Inmobiliaria"
                                + "<br/> recuerde que su cuenta asignada es: " + Usu + Obser);
                        MBCor.enviarCorreo(1);
                        mbTodero.setMens("Ha sido enviado el correo correctamente");
                        mbTodero.info();
                        RequestContext.getCurrentInstance().execute("PF('RecuperarPass').hide()");
                        DocEmp1 = "";
                    }
                } else {
                    mbTodero.setMens("Este documento no se encuentra registrado, por favor verifique");
                    mbTodero.warn();
                }
               Conexion.CloseCon();
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".envioUsuarioCorreo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que envia un correo al administrador en el proceso de
     * autenticacion cuando un usuario desea notificar alguna situacion al
     * administrador
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @since 01-05-2015
     */
    public void envioAdminCorreo() {
        try {
            if ("".equals(this.DocEmp2) && "".equals(this.Novedad)) {
                mbTodero.setMens("Debe ingresar toda la información requerida");
                mbTodero.warn();
            } else if ("".equals(this.DocEmp2) || DocEmp2 == null) {
                mbTodero.setMens("Debe ingresar el numero de documento");
                mbTodero.warn();
            } else if ("".equals(this.Novedad) || Novedad == null) {
                mbTodero.setMens("Debe ingresar algun tipo de novedad");
                mbTodero.warn();
            } else {
                Emp.setDocEmp(DocEmp2);
                Dat = Emp.ConsulDoc(2);
                if (Dat.next()) {
                    int Cod = Dat.getInt("Cod_Empleados");
                    String Nom = Dat.getString("Nombre_Empleados") + " " + Dat.getString("Apellido_Empleados");
                    MBCor.setAsunto("Notificación Problemas De Ingreso empleado SIAI2.0");
                    MBCor.setMailDestino("backup@isainmobiliaria.com.co");
                    MBCor.setMensaje("El usuario: " + Nom + "<br/> Tiene los siguientes problemas para ingresar a la aplicacion: <br/> <br/>" + Novedad);
                    MBCor.enviarCorreo(1);
                    mbTodero.setMens("Ha sido enviado el correo correctamente");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('RecuperarPass').hide()");
                    DocEmp2 = "";
                    Novedad = "";
                } else {
                    mbTodero.setMens("Este documento no se encuentra registrado, por favor verifique");
                    mbTodero.error();
                }
                Conexion.CloseCon();
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".envioAdminCorreo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para cambio de contraseña cuando un usuario lo requiere
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Sriven Rodriguez
     * @since 01-05-2015
     */
    public void cambioClave() {
        try {
            if ("".equals(this.PassOld) || "".equals(this.PassOne) || "".equals(this.PassTwo)) {
                mbTodero.setMens("Por favor llene todos los campos, son obligatorios");
                mbTodero.warn();
            } else if (PassOne.equals(this.PassTwo)) {
                FContext = FacesContext.getCurrentInstance();
                HttpServletRequest = (HttpServletRequest) FContext.getExternalContext().getRequest();
                Emp.setCodEmp(Integer.parseInt(HttpServletRequest.getSession().getAttribute("UsuarPass").toString()));
                Emp.setPassEmp(PassOne);
                Emp.ActuaPassword();
                RequestContext.getCurrentInstance().closeDialog("XHTML/Cuerpo/CambioPassword.xhtml");
                HttpServletRequest.getSession().removeAttribute("UsuarPass");
                mbTodero.setMens("Contraseña generada correctamente");
                mbTodero.info();
                RequestContext.getCurrentInstance().update(":indexx:formLogin:msj4");
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } else {
                mbTodero.setMens("las contraseñas con coinciden, favor repetirlas");
                mbTodero.warn();
                this.PassOne = "";
                this.PassTwo = "";
            }
        } catch (NumberFormatException | IOException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambioClave()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
