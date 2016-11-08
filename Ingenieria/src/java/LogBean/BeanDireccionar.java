package LogBean;


import Logic.LogAdministracion;

import Logic.LogAvaluo;


import Logic.LogCliente;
import Logic.LogEmpleado;
import Logic.LogEntidad;

import Logic.LogPermiso;
import Logic.LogPreRadicacion;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de los direccionamientos que
 * maneja la aplicacion de menus, submenus etc </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-12-2014 1.0.0
 *
 *
 */
@ManagedBean
@ViewScoped
public class BeanDireccionar {

    private String TemaSeleccionado;

    /**
     * Variables para el manejo de los titulos, url y visibilidades que se
     * muestran en toda la aplicacion
     *
     */
    private String nombre_pagina;
    private String nombre_pagina_interna;
    private String url_pagina = "fondoSIAI.xhtml";
    private String tituloMenu = "Bienvenidos a SIAI 2.0";
    private String url_pagina_Administracion;
    private String url_pagina_Pre_Radicacion;
    private String url_pagina_Radicacion;
    private String url_pagina_Anticipo;
    public String url_pagina_Seguimiento;
    public String url_pagina_Entrega;
    public String url_pagina_Revision;
    public String url_pagina_Facturacion;
    public String url_pagina_Cartera;
    private boolean opcionPNLValidacion = true;
    private boolean opcionPNLInformacion = false;
    private boolean opcionPNLRevGen = false;
    private boolean opcionPNLRevJur = false;
    private boolean opcionPNLRevTec = false;
    private int po1 = 486;
    private int po2 = 264;
    private int aquita = 0;
    private String PassValida;
    private boolean EstadotabRadicacion;
    private String tamañoPantalla1 = "auto";
    private String tamañoPantalla2 = "1%";
    ResultSet valCom = null;

    /**
     * Formatos de fechas y hora *
     */
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat FormatCompleto = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat FormatHora = new SimpleDateFormat("HH:mm");

    private String seleccionPreradica;

    ResultSet Dat = null;
    private boolean estadoActivacionGestor;
    private boolean ActivacionAprobacionRevision = false;
    private List<LogAdministracion> ListMenu = null;
    private LogAdministracion obcionseleccionada;

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogAdministracion Adm = new LogAdministracion();

    LogAvaluo Ava = new LogAvaluo();
    LogEmpleado Emp = new LogEmpleado();
    LogPreRadicacion PreRad = new LogPreRadicacion();
  
    LogPermiso Perm = new LogPermiso();
   


    private ResultSet DatInfoGeneralRevision;
  

    @PostConstruct
    public void init() {
        this.aquita = 0;
        RolGestor(mBSesion.codigoMiSesion());
    }

    /**
     * Constructor de la clase que carga segun el usuario los menus a los que
     * tiene acceso
     *
     *
     * @throws java.lang.Exception
     */
    public BeanDireccionar() throws Exception {
        try {
            BeanSesion MBSes = new BeanSesion();
            Adm.setCodEmp(Integer.parseInt(MBSes.getCod_empleado()));
            this.ListMenu = Adm.ConsulProcesEmple();
        } catch (IOException | NumberFormatException ex) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    /**
     * Variable tipo BeanEmpleado para traer los atributos y metodos de el
     * ManagedBean BeanEmpleado.java
     *
     *
     * @see BeanEmpleado.java
     */
    @ManagedProperty("#{MBEmpleado}")
    private BeanEmpleado mBEmpleado;

    public BeanEmpleado getmBEmpleado() {
        return mBEmpleado;
    }

    public void setmBEmpleado(BeanEmpleado mBEmpleado) {
        this.mBEmpleado = mBEmpleado;
    }

    /**
     * Variable tipo BeanPreRadicacion para traer los atributos y metodos de el
     * ManagedBean BeanPreRadicacion.java
     *
     *
     * @see BeanPreRadicacion.java
     */
    @ManagedProperty("#{MBPreRadicacion}")
    private BeanPreRadicacion mBPreRadicacion;

    public BeanPreRadicacion getmBPreRadicacion() {
        return mBPreRadicacion;
    }

    public void setmBPreRadicacion(BeanPreRadicacion mBPreRadicacion) {
        this.mBPreRadicacion = mBPreRadicacion;
    }


    /**
     * Variable tipo BeanAdministracion para traer los atributos y metodos de el
     * ManagedBean BeanAdministracion.java
     *
     *
     * @see BeanAdministracion.java
     */
    @ManagedProperty("#{MBAdministacion}")
    private BeanAdministracion mBAdministacion;

    public BeanAdministracion getmBAdministacion() {
        return mBAdministacion;
    }

    public void setmBAdministacion(BeanAdministracion mBAdministacion) {
        this.mBAdministacion = mBAdministacion;
    }

    /**
     * Variable tipo BeanEntidad para traer los atributos y metodos de el
     * ManagedBean BeanEntidad.java
     *
     *
     * @see BeanEntidad.java
     */
    @ManagedProperty("#{MBEntidad}")
    private BeanEntidad mBEntidad;

    public BeanEntidad getmBEntidad() {
        return mBEntidad;
    }
    public void setmBEntidad(BeanEntidad mBEntidad) {
        this.mBEntidad = mBEntidad;
    }
    /**
     * Variable tipo BeanSesion para traer los atributos y metodos de el
     * ManagedBean BeanSesion.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBSesion}")
    private BeanSesion mBSesion;

    public BeanSesion getmBSesion() {
        return mBSesion;
    }

    public void setmBSesion(BeanSesion mBSesion) {
        this.mBSesion = mBSesion;
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
     * Variable tipo BeanAvaluo para traer los atributos y metodos de el
     * ManagedBean BeanAvaluo.java
     *
     *
     * @see BeanAvaluo.java
     */
    @ManagedProperty("#{MBAvaluo}")
    private BeanAvaluo mbAvaluo;

    public BeanAvaluo getMbAvaluo() {
        return mbAvaluo;
    }

    public void setMbAvaluo(BeanAvaluo mbAvaluo) {
        this.mbAvaluo = mbAvaluo;
    }

    /**
     * Variable tipo BeanPermiso para traer los atributos y metodos de el
     * ManagedBean BeanPermiso.java
     *
     *
     * @see BeanPermiso.java
     */
    @ManagedProperty("#{MBPermiso}")
    private BeanPermiso mbPermiso;

    public BeanPermiso getMbPermiso() {
        return mbPermiso;
    }

    public void setMbPermiso(BeanPermiso mbPermiso) {
        this.mbPermiso = mbPermiso;
    }

    /**
     * Variable tipo BeanArchivos para traer los atributos y metodos de el
     * ManagedBean BeanArchivos.java
     *
     *
     * @see BeanArchivos.java
     */
    

    

  
 

    
    /**
     * Variable tipo BeanCliente para traer los atributos y metodos de el
     * ManagedBean BeanCliente.java
     *
     *
     * @see BeanCliente.java
     */
    @ManagedProperty("#{MBCliente}")
    private BeanCliente mBCliente;

    public BeanCliente getmBCliente() {
        return mBCliente;
    }

    public void setmBCliente(BeanCliente mBCliente) {
        this.mBCliente = mBCliente;
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
    public String getUrl_pagina_Cartera() {
        return url_pagina_Cartera;
    }

    public void setUrl_pagina_Cartera(String url_pagina_Cartera) {
        this.url_pagina_Cartera = url_pagina_Cartera;
    }

    public LogAdministracion getAdm() {
        return Adm;
    }

    public void setAdm(LogAdministracion Adm) {
        this.Adm = Adm;
    }

    public String getTemaSeleccionado() {
        return TemaSeleccionado;
    }

    public void setTemaSeleccionado(String TemaSeleccionado) {
        this.TemaSeleccionado = TemaSeleccionado;
    }

    public String getNombre_pagina() {
        return nombre_pagina;
    }

    public void setNombre_pagina(String nombre_pagina) {
        this.nombre_pagina = nombre_pagina;
    }

    public String getNombre_pagina_interna() {
        return nombre_pagina_interna;
    }

    public void setNombre_pagina_interna(String nombre_pagina_interna) {
        this.nombre_pagina_interna = nombre_pagina_interna;
    }

    public String getUrl_pagina() {
        return url_pagina;
    }

    public void setUrl_pagina(String url_pagina) {
        this.url_pagina = url_pagina;
    }

    public String getTituloMenu() {
        return tituloMenu;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    public String getUrl_pagina_Administracion() {
        return url_pagina_Administracion;
    }

    public void setUrl_pagina_Administracion(String url_pagina_Administracion) {
        this.url_pagina_Administracion = url_pagina_Administracion;
    }

    public String getUrl_pagina_Pre_Radicacion() {
        return url_pagina_Pre_Radicacion;
    }

    public void setUrl_pagina_Pre_Radicacion(String url_pagina_Pre_Radicacion) {
        this.url_pagina_Pre_Radicacion = url_pagina_Pre_Radicacion;
    }

    public String getUrl_pagina_Radicacion() {
        return url_pagina_Radicacion;
    }

    public void setUrl_pagina_Radicacion(String url_pagina_Radicacion) {
        this.url_pagina_Radicacion = url_pagina_Radicacion;
    }

    public String getUrl_pagina_Anticipo() {
        return url_pagina_Anticipo;
    }

    public void setUrl_pagina_Anticipo(String url_pagina_Anticipo) {
        this.url_pagina_Anticipo = url_pagina_Anticipo;
    }

    public String getUrl_pagina_Entrega() {
        return url_pagina_Entrega;
    }

    public void setUrl_pagina_Entrega(String url_pagina_Entrega) {
        this.url_pagina_Entrega = url_pagina_Entrega;
    }

    public String getUrl_pagina_Revision() {
        return url_pagina_Revision;
    }

    public void setUrl_pagina_Revision(String url_pagina_Revision) {
        this.url_pagina_Revision = url_pagina_Revision;
    }

    public String getUrl_pagina_Seguimiento() {
        return url_pagina_Seguimiento;
    }

    public void setUrl_pagina_Seguimiento(String url_pagina_Seguimiento) {
        this.url_pagina_Seguimiento = url_pagina_Seguimiento;
    }

    public String getUrl_pagina_Facturacion() {
        return url_pagina_Facturacion;
    }

    public void setUrl_pagina_Facturacion(String url_pagina_Facturacion) {
        this.url_pagina_Facturacion = url_pagina_Facturacion;
    }

    public boolean isEstadoActivacionGestor() {
        return estadoActivacionGestor;
    }

    public void setEstadoActivacionGestor(boolean estadoActivacionGestor) {
        this.estadoActivacionGestor = estadoActivacionGestor;
    }

    public boolean isActivacionAprobacionRevision() {
        return ActivacionAprobacionRevision;
    }

    public void setActivacionAprobacionRevision(boolean ActivacionAprobacionRevision) {
        this.ActivacionAprobacionRevision = ActivacionAprobacionRevision;
    }

    public void asignar_url(String url_inicial) {
        this.nombre_pagina = url_inicial;
    }

    public List<LogAdministracion> getListMenu() {
        return ListMenu;
    }

    public void setListMenu(List<LogAdministracion> ListMenu) {
        this.ListMenu = ListMenu;
    }

    public LogAdministracion getObcionseleccionada() {
        return obcionseleccionada;
    }

    public void setObcionseleccionada(LogAdministracion obcionseleccionada) {
        this.obcionseleccionada = obcionseleccionada;
    }

    public boolean isOpcionPNLValidacion() {
        return opcionPNLValidacion;
    }

    public void setOpcionPNLValidacion(boolean opcionPNLValidacion) {
        this.opcionPNLValidacion = opcionPNLValidacion;
    }

    public boolean isOpcionPNLInformacion() {
        return opcionPNLInformacion;
    }

    public void setOpcionPNLInformacion(boolean opcionPNLInformacion) {
        this.opcionPNLInformacion = opcionPNLInformacion;
    }

    public boolean isOpcionPNLRevGen() {
        return opcionPNLRevGen;
    }

    public void setOpcionPNLRevGen(boolean opcionPNLRevGen) {
        this.opcionPNLRevGen = opcionPNLRevGen;
    }

    public boolean isOpcionPNLRevJur() {
        return opcionPNLRevJur;
    }

    public void setOpcionPNLRevJur(boolean opcionPNLRevJur) {
        this.opcionPNLRevJur = opcionPNLRevJur;
    }

    public boolean isOpcionPNLRevTec() {
        return opcionPNLRevTec;
    }

    public void setOpcionPNLRevTec(boolean opcionPNLRevTec) {
        this.opcionPNLRevTec = opcionPNLRevTec;
    }

    public int getPo1() {
        return po1;
    }

    public void setPo1(int po1) {
        this.po1 = po1;
    }

    public int getPo2() {
        return po2;
    }

    public void setPo2(int po2) {
        this.po2 = po2;
    }

    public int getAquita() {
        return aquita;
    }

    public void setAquita(int aquita) {
        this.aquita = aquita;
    }

    public String getPassValida() {
        return PassValida;
    }

    public void setPassValida(String PassValida) {
        this.PassValida = PassValida;
    }

    public boolean isEstadotabRadicacion() {
        return EstadotabRadicacion;
    }

    public void setEstadotabRadicacion(boolean EstadotabRadicacion) {
        this.EstadotabRadicacion = EstadotabRadicacion;
    }

    public String getTamañoPantalla1() {
        return tamañoPantalla1;
    }

    public void setTamañoPantalla1(String tamañoPantalla1) {
        this.tamañoPantalla1 = tamañoPantalla1;
    }

    public String getTamañoPantalla2() {
        return tamañoPantalla2;
    }

    public void setTamañoPantalla2(String tamañoPantalla2) {
        this.tamañoPantalla2 = tamañoPantalla2;
    }

    public String getSeleccionPreradica() {
        return seleccionPreradica;
    }

    public void setSeleccionPreradica(String seleccionPreradica) {
        this.seleccionPreradica = seleccionPreradica;
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
     * Metodo que se utiliza para la verificacion del rol de gestor del formato
     * de control y seguimiento
     *
     * @author Maira Alejandra Carvajal
     * @param Id int que contiene el codigo del empleado
     * @since 01-05-2015
     */
    private void RolGestor(int Id) {
        try {
            Logic.LogPermiso Permi = new LogPermiso();
            Dat = Permi.consulRolEmpleado(Id, "Gestor");
            if (Dat.next()) {
                this.estadoActivacionGestor = true;
            } else if (!Dat.next()) {
                this.estadoActivacionGestor = false;
            }
            Conexion.Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".RolGestor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para validar informacion de si el parametro de aprobacion se
     * encuentra activo o se encuentra false
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    private void AprobacionRevision() {
        try {
            LogAdministracion Admn = new LogAdministracion();
            Dat = Admn.ConsulAprobRevi();
            if (Dat.next()) {
                this.ActivacionAprobacionRevision = "true".equals(Dat.getString("Resultado_Parametro"));
            }
            Conexion.Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".AprobacionRevision()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que le asigna valor false a al variable estadoActivacionGestor
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    private void lim() {
        try {
            this.estadoActivacionGestor = false;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".lim()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que selecciona una url y realiza su respectiva redireccion a la
     * misma
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param url_inicial String que recibe como parametro wl nombre del modulo
     * al cual redireccionara
     * @since 01-05-2015
     */
    public void seleccionar_url_menuprincipal(String url_inicial) {
        try {
            this.nombre_pagina = "";
            this.nombre_pagina = url_inicial;
            switch (this.nombre_pagina) {
                case "Administracion":
                    this.tituloMenu = "Administracion";
                    this.url_pagina = "../Menus/Administracion/Menu_Administrativo.xhtml";
                    break;
                case "Pre-Radicacion":
                    mBPreRadicacion.consultaTotalRegistrosTablas();
                    this.tituloMenu = "Pre-Radicacion";
                    this.url_pagina = "../Menus/Radicacion/Menu_Pre_Radicacion.xhtml";
                    break;
                default:
                    this.tituloMenu = "";
                    this.url_pagina = "../NoAlcansada.xhtml";
                    break;
            }
            this.url_pagina_Pre_Radicacion = "../Blanco.xhtml";
            ListMenu.clear();
            ListMenu = Adm.ConsulProcesEmple();
            this.tamañoPantalla1 = "auto";
            this.tamañoPantalla2 = "auto";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionar_url_menuprincipal()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que redirecciona a las url internas de el menu de administración
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param url_interna que contiene a que tipo de formulario se direccionara
     * @since 01-05-2015
     */
    public void url_MenusInternos(String url_interna) {
        try {
            this.nombre_pagina_interna = "";
            this.nombre_pagina_interna = url_interna;
            switch (this.nombre_pagina_interna) {

                case "Mensaje_Corp":
                    mBSesion.setNewMensaje("");
                    RequestContext.getCurrentInstance().execute("PF('dlgCambioMensaje').show()");
                    break;

                case "Gestion_Empleado":
                    mBEmpleado.clearResetComponets();

                    mBEmpleado.getTipDocEmp().clear();
                    mBEmpleado.setTipDocEmp(mBEmpleado.getConsulTipDocEmp());

                    mBEmpleado.getCargaDep().clear();
         

                    mBEmpleado.getCargEmp().clear();
                    mBEmpleado.setCargEmp(mBEmpleado.getConsulCargEmp());

                    mBEmpleado.getCargpisos().clear();
                    mBEmpleado.setCargpisos(mBEmpleado.getConsulPisos());

                    mBEmpleado.getEstEmp().clear();
                    mBEmpleado.setEstEmp(mBEmpleado.getConsulEstEmp());

                    mBEmpleado.getTipDocEmp().clear();
                    mBEmpleado.setTipDocEmp(mBEmpleado.getConsulTipDocEmp());

                    mBEmpleado.getCargJefes().clear();
                    mBEmpleado.setCargJefes(mBEmpleado.getCargJefeEmp());

                    mBEmpleado.setListEmp(new ArrayList<>());
                    mBEmpleado.setListEmp(mBEmpleado.Emp.ConsulAllEmpleados());

                    mBEmpleado.setCargEmpSeguimiento(mBEmpleado.getCargEmplSeguimiento());

                    mBEmpleado.getcargaRol();
                    mBEmpleado.getCargaCiu().clear();
                    mbPermiso.getcargaProEnt().clear();
                    mbPermiso.getcargaProEnt();

                    this.url_pagina_Administracion = "../Persona/GestionEmpleados.xhtml";
                    break;

          
                default:
                    this.url_pagina_Administracion = "../NoAlcansada.xhtml";
                    break;
            }
            this.url_pagina_Pre_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Seguimiento = "../Blanco.xhtml";
            this.url_pagina_Entrega = "../Blanco.xhtml";
            this.url_pagina_Revision = "../Blanco.xhtml";
            this.url_pagina_Facturacion = "../Blanco.xhtml";
            this.url_pagina_Cartera = "../Blanco.xhtml";
            this.url_pagina_Anticipo = "../Blanco.xhtml";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".url_MenusInternos()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que muestra un mensaje tipo growl
     *
     * @author Maira Alejandra Carvajal
     * @param summary String que contiene el mensaje que se mostrara
     * @since 01-05-2015
     */
    public void addMessage(String summary) {
        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionar_url_menuprincipal()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que redirecciona a los difererentes submenus de el modulo de
     * Pre-Radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param url_interna_preradica String que contiene el tipo de submenu al
     * que redireccionara
     * @since 01-05-2015
     */
    public void url_Menu_prRadica(String url_interna_preradica) {
        try {
            this.nombre_pagina_interna = "";
            this.nombre_pagina_interna = url_interna_preradica;
            Calendar fecha = new GregorianCalendar();
            Date fecha1 = new Date();
            mBPreRadicacion.consultaTotalRegistrosTablas();
            switch (this.nombre_pagina_interna) {
                case "Registro":
                    mBPreRadicacion.cleear();
                    fecha1 = fecha.getTime();
                    mBPreRadicacion.setFecha_actual(Format.format(fecha1));
                    mbAvaluo.setFechaActual(fecha1.toString());
                    //mBPreRadicacion.setListPreRadicados(mBPreRadicacion.PreRad.ConsulPendientesRadicar(mBSesion.codigoMiSesion()));

                 
                    mBPreRadicacion.setListAllMisAsignados(mBPreRadicacion.PreRad.getConsultarAllAnalistas(mBAdministacion.Adm.getCodProEnt(), 1));

                    //Cargar lo de Entidades
                    LogEntidad Ent = new LogEntidad();
                    mBEntidad.setCargaEntTodo(new ArrayList<>());
                    mBEntidad.setCargaEntTodo(Ent.getEntidad());
                    LogEntidad EntEnt = new LogEntidad();
                    EntEnt.setCodEntidad(Adm.ConsulCodigoPrincipal("Cod_Entidad", "Entidad"));

                    mBEntidad.setCodEntidad1("0");
                    mBEntidad.setCodEntidad2("0");
                    mBEntidad.setCodEntidad3("0");
                    mBEntidad.setCodEntidad4("0");
                    mBEntidad.setCodEntidad5("0");
                    mBEntidad.cargEntRadic(1);

                    //Cargar sucursales 
                    mBEntidad.setCargaSucurTodo(new ArrayList<>());
                    mBEntidad.setCargaSucurTodo(Ent.getSucursalAll());
                    LogEntidad Entsucu = new LogEntidad();
                    Entsucu.setCodSucursal(Adm.ConsulCodigoPrincipal("Cod_Sucursal", "Sucursal"));

                    //Cargar Asesores
                    mBEntidad.setCargaAsesTodo(new ArrayList<>());
                    mBEntidad.setCargaAsesTodo(Ent.getAsesorAll());
                    LogEntidad EntAse = new LogEntidad();
                    EntAse.setCodAsesor(Adm.ConsulCodigoPrincipal("Cod_Asesor", "Asesor"));

                    this.url_pagina_Pre_Radicacion = "../Radicacion/FormPR-Registro.xhtml";
                    break;

                
           
                default:
                    this.url_pagina_Pre_Radicacion = "../NoAlcansada.xhtml";
                    break;
            }
            this.url_pagina_Administracion = "../Blanco.xhtml";
         
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".url_Menu_prRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

  
    

    /**
     * Metodo que verifica el password de un empleado, consultando toda la
     * informacion que este tenga registrada
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void verificaPassword() {
        try {
            if ("".equals(this.PassValida)) {
                mbTodero.setMens("Ingrese la constraseña de su cuenta");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
            } else {
                Emp.setCodEmp(mBSesion.codigoMiSesion());
                Emp.setPassEmp(PassValida);
                boolean Result = Emp.ConsulPass();
                if (Result == true) {
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').hide()");
                    opcionPNLValidacion = false;
                    po1 = 290;
                    po2 = 145;
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
                    opcionPNLInformacion = true;
                    Emp.setCodEmp(mBSesion.codigoMiSesion());
                    try {
                        Dat = Emp.ConsulEmp();
                        if (Dat.next()) {
                            mBEmpleado.getTipDocEmp().clear();
                            mBEmpleado.getConsulTipDocEmp();
                            mBEmpleado.setNombreEmp(Dat.getString("Nombre_Empleados"));
                            mBEmpleado.setApellidoEmp(Dat.getString("Apellido_Empleados"));
                            mBEmpleado.setTipDoc(Dat.getString("FK_Cod_TipDocumento"));
                            mBEmpleado.setDocEmp(Dat.getString("Documento_Empleados"));
                            mBEmpleado.setTelPerEmp(Dat.getString("Telefo_Personal_Empleados"));
                            mBEmpleado.setCelPerEmp(Dat.getString("Celular_Personal_Empleados"));
                            mBEmpleado.setMailPerEmp(Dat.getString("Correo_Personal_Empleados"));
                            mBEmpleado.setNombreDepto(Dat.getString("Cod_Departamento"));
                
                            mBEmpleado.setCuidad(Dat.getString("E.FK_Cod_Ciudad"));
                            mBEmpleado.setNumContacEmp(Dat.getString("Numero_Contacto_Empleados"));
                            mBEmpleado.setNomContacEmp(Dat.getString("Nombre_Contacto_Empleados"));

                            mBEmpleado.setEstado(Dat.getString("Nombre_Estado"));
                            mBEmpleado.setCargo(Dat.getString("Nombre_Cargo"));
                            mBEmpleado.setExtEmp(Dat.getString("Extension_Empleados"));
                            mBEmpleado.setCelEmp(Dat.getString("Celular_Empresa_Empleados"));
                            mBEmpleado.setUbicaEmp(Dat.getString("Resultado_Parametro"));
                            if ("".equals(Dat.getString("Correo_Corporativo_Empleados")) || Dat.getString("Correo_Corporativo_Empleados") == null) {
                                mBEmpleado.setMailEmp("No registra");
                            } else {
                                mBEmpleado.setMailEmp(Dat.getString("Correo_Corporativo_Empleados"));
                            }
                            mBEmpleado.setEmpleado(Dat.getString("Jefe"));
                            mBEmpleado.setUsuarEmp(Dat.getString("Username_Empleados"));

                            mbPermiso.setListPermisosAsig(Perm.consultaRolesAsignados(mBSesion.codigoMiSesion()));

                            mbPermiso.setListProdEntiAsig(Perm.getProEntAsignadoooo(mBSesion.codigoMiSesion()));

                        }
                        Conexion.Conexion.CloseCon();
                    } catch (SQLException e) {
                        mbTodero.setMens("Error: " + e.getMessage());
                        mbTodero.fatal();
                    }
                } else {
                    this.PassValida = "";
                    mbTodero.setMens("La contraseña no es la correcta , favor ingresela nuevamente");
                    mbTodero.warn();
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificaPassword()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que confrma el password y habilita los paneles de informacion y
     * abre el dialog con la informacion de el empleado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void confirmapass() {
        try {
            this.opcionPNLValidacion = true;
            this.opcionPNLInformacion = false;
            this.PassValida = "";
            po1 = 486;
            po2 = 264;
            RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".confirmapass()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que actualiza la informacion del empleado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void llamarBeanEmp() {
        try {
            this.PassValida = "";
            mBEmpleado.actualizacionEmp();
            this.opcionPNLInformacion = false;
            this.opcionPNLValidacion = true;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".llamarBeanEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }
    
}
