/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import Logic.LogEmpleado;
import Logic.LogPermiso;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de empleados de la organizacion
 * </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-10-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBEmpleado")
@ViewScoped
@SessionScoped
public class BeanEmpleado {

    /**
     * Variables Implicitas*
     */
    private boolean estadoTab1 = false;
    private boolean estadoTab2 = true;
    private boolean estadoTab3 = true;
    private boolean estadoTab4 = true;
    private boolean estadoTab5 = true;

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogEmpleado Emp = new LogEmpleado();
    LogEmpleado EmpConsulta = new LogEmpleado();
    LogPermiso Per = new LogPermiso();

    /**
     * Variable tipo MBPermiso para traer los atributos y metodos de el
     * ManagedBean MBPermiso.java
     *
     *
     * @see MBPermiso.java
     */
    @ManagedProperty("#{MBPermiso}")
    private BeanPermiso beanPer;

    public BeanPermiso getBeanPer() {
        return beanPer;
    }

    public void setBeanPer(BeanPermiso BeanPer) {
        this.beanPer = BeanPer;
    }

    /**
     * Variable tipo BeanCorreo para traer los atributos y metodos de el
     * ManagedBean BeanCorreo.java
     *
     *
     * @see BeanCorreo.java
     */
    @ManagedProperty("#{MBCorreo}")
    private BeanCorreo beanCorreo;

    public BeanCorreo getBeanCorreo() {
        return beanCorreo;
    }

    public void setBeanCorreo(BeanCorreo beanCorreo) {
        this.beanCorreo = beanCorreo;
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
     * Variable tipo BeanSesion para traer los atributos y metodos de el
     * ManagedBean BeanSesion.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBSesion}")
    private BeanSesion mBsesion;

    public BeanSesion getmBsesion() {
        return mBsesion;
    }

    public void setmBsesion(BeanSesion mBsesion) {
        this.mBsesion = mBsesion;
    }

    /**
     * Variables tipo ArrayList Y List para consultar y cargar informacion sobre
     * los empleados
     *
     */
    List<LogPermiso> roles_target;
    private ArrayList<SelectItem> TipDocEmp = new ArrayList<>();
    private ArrayList<SelectItem> EstEmp = new ArrayList<>();
    private ArrayList<SelectItem> CargEmp = new ArrayList<>();
    private ArrayList<SelectItem> Cargpisos = new ArrayList<>();
    private ArrayList<SelectItem> CargJefes = new ArrayList<>();
    private ArrayList<SelectItem> CargPersonalACargo = new ArrayList<>();
    private ArrayList<SelectItem> CargEmpSeguimiento = new ArrayList<>();
    private List<LogPermiso> RolesEmpl = new ArrayList<>();
    private List<LogPermiso> ProdEntiEmpl = new ArrayList<>();
    private List<LogEmpleado> ListEmp = null;
    private List<SelectItem> ListEmpAuditoria = new ArrayList<>();
    private List<LogEmpleado> FiltroEmp;
    ResultSet Dat = null;
    private List<LogEmpleado> ListEmpLasy = null;
    private String estadoSeleccionFoto = "No";
    private List<LogEmpleado> datasource;
    private int RowCount;

    //Variables
    //@NotNull
    private boolean avilitarRegistro = true;
    //@Size(min=1,max=15)
    private String DocEmp;//Numero Documento Empleado

    /**
     * Variables para el uso de el registro , consulta y actualizacion de
     * empleados
     *
     */
    private String NombreEmp;
    private String ApellidoEmp;
    private String MailPerEmp;
    private String TelPerEmp;
    private String CelPerEmp;
    private String NomContacEmp;
    private String NumContacEmp;
    private UploadedFile FotoEmp;
    private String FotoEmpPath;
    private String MailEmp;
    private String ExtEmp;
    private String CelEmp;
    private String UbicaEmp;
    private String UsuarEmp;
    private String PassEmp;
    private String PassEmp1;
    private StreamedContent Emplea;
    private String TipDoc;//FK_Tipo Documento Empleado
    private String NombreDepto;
    private String Cuidad;//FK_ Tabla Cuidad
    private String Estado;//FK_ Tabla de Estado
    private String Cargo;//FK_ Tabla de Cargo
    private String Empleado = null;//FK_ Tabla Empleado
    //
    private DualListModel<LogPermiso> teamsDualListModel;
    private DualListModel<LogPermiso> prodEntDualListModel;
    private ArrayList<SelectItem> CargaDep = new ArrayList<>();
    private ArrayList<SelectItem> CargaCiu = new ArrayList<>();
    List<LogPermiso> CargaRoles;  //Carga los roles  (source)
    List<LogPermiso> CargaRolesAsig = new ArrayList<>(); //Carga los roles que van a ser asignados (target) 
    private int CodRoles;
    private String NombreRol;
    private int cod_traido;
    private boolean estadoActualizar = false;
    private boolean estadoRegistrar = true;
    private UploadedFile pathFIle;
    private boolean disablePass = false;
    private String email_personal;
    private String pass_empleado;
    private String mail_corporativo;
    private String email_enviar_Correo;
    private String nombre_empleado;
    private String usuario_empleado;
    private String apellidoEmpleado;
    private FacesMessage message;

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return
     */
    public List<LogEmpleado> getListEmpLasy() {
        return ListEmpLasy;
    }

    public void setListEmpLasy(List<LogEmpleado> ListEmpLasy) {
        this.ListEmpLasy = ListEmpLasy;
    }

    public LogEmpleado getEmp() {
        return Emp;
    }

    public void setEmp(LogEmpleado Emp) {
        this.Emp = Emp;
    }

    public LogEmpleado getEmpConsulta() {
        return EmpConsulta;
    }

    public void setEmpConsulta(LogEmpleado EmpConsulta) {
        this.EmpConsulta = EmpConsulta;
    }

    public boolean isAvilitarRegistro() {
        return avilitarRegistro;
    }

    public void setAvilitarRegistro(boolean avilitarRegistro) {
        this.avilitarRegistro = avilitarRegistro;
    }

    public ArrayList<SelectItem> getTipDocEmp() {
        return TipDocEmp;
    }

    public void setTipDocEmp(ArrayList<SelectItem> TipDocEmp) {
        this.TipDocEmp = TipDocEmp;
    }

    public ArrayList<SelectItem> getEstEmp() {
        return EstEmp;
    }

    public void setEstEmp(ArrayList<SelectItem> EstEmp) {
        this.EstEmp = EstEmp;
    }

    public ArrayList<SelectItem> getCargEmp() {
        return CargEmp;
    }

    public void setCargEmp(ArrayList<SelectItem> CargEmp) {
        this.CargEmp = CargEmp;
    }

    public ArrayList<SelectItem> getCargpisos() {
        return Cargpisos;
    }

    public void setCargpisos(ArrayList<SelectItem> Cargpisos) {
        this.Cargpisos = Cargpisos;
    }

    public ArrayList<SelectItem> getCargJefes() {
        return CargJefes;
    }

    public void setCargJefes(ArrayList<SelectItem> CargJefes) {
        this.CargJefes = CargJefes;
    }

    public ArrayList<SelectItem> getCargPersonalACargo() {
        return CargPersonalACargo;
    }

    public void setCargPersonalACargo(ArrayList<SelectItem> CargPersonalACargo) {
        this.CargPersonalACargo = CargPersonalACargo;
    }

    public ArrayList<SelectItem> getCargEmpSeguimiento() {
        return CargEmpSeguimiento;
    }

    public void setCargEmpSeguimiento(ArrayList<SelectItem> CargEmpSeguimiento) {
        this.CargEmpSeguimiento = CargEmpSeguimiento;
    }

    public List<LogEmpleado> getListEmp() {
        return ListEmp;
    }

    public void setListEmp(List<LogEmpleado> ListEmp) {
        this.ListEmp = ListEmp;
    }

    public List<SelectItem> getListEmpAuditoria() {
        return ListEmpAuditoria;
    }

    public void setListEmpAuditoria(List<SelectItem> ListEmpAuditoria) {
        this.ListEmpAuditoria = ListEmpAuditoria;
    }

    public List<LogEmpleado> getFiltroEmp() {
        return FiltroEmp;
    }

    public void setFiltroEmp(List<LogEmpleado> FiltroEmp) {
        this.FiltroEmp = FiltroEmp;
    }

    public String getDocEmp() {
        return DocEmp;
    }

    public void setDocEmp(String DocEmp) {
        this.DocEmp = DocEmp;
    }

    public String getNombreEmp() {
        return NombreEmp;
    }

    public void setNombreEmp(String NombreEmp) {
        this.NombreEmp = NombreEmp;
    }

    public String getApellidoEmp() {
        return ApellidoEmp;
    }

    public void setApellidoEmp(String ApellidoEmp) {
        this.ApellidoEmp = ApellidoEmp;
    }

    public String getMailPerEmp() {
        return MailPerEmp;
    }

    public void setMailPerEmp(String MailPerEmp) {
        this.MailPerEmp = MailPerEmp;
    }

    public String getTelPerEmp() {
        return TelPerEmp;
    }

    public void setTelPerEmp(String TelPerEmp) {
        this.TelPerEmp = TelPerEmp;
    }

    public String getCelPerEmp() {
        return CelPerEmp;
    }

    public void setCelPerEmp(String CelPerEmp) {
        this.CelPerEmp = CelPerEmp;
    }

    public String getNomContacEmp() {
        return NomContacEmp;
    }

    public void setNomContacEmp(String NomContacEmp) {
        this.NomContacEmp = NomContacEmp;
    }

    public String getNumContacEmp() {
        return NumContacEmp;
    }

    public void setNumContacEmp(String NumContacEmp) {
        this.NumContacEmp = NumContacEmp;
    }

    public UploadedFile getFotoEmp() {
        return FotoEmp;
    }

    public void setFotoEmp(UploadedFile FotoEmp) {
        this.FotoEmp = FotoEmp;
    }

    public String getFotoEmpPath() {
        return FotoEmpPath;
    }

    public void setFotoEmpPath(String FotoEmpPath) {
        this.FotoEmpPath = FotoEmpPath;
    }

    public String getMailEmp() {
        return MailEmp;
    }

    public void setMailEmp(String MailEmp) {
        this.MailEmp = MailEmp;
    }

    public String getExtEmp() {
        return ExtEmp;
    }

    public void setExtEmp(String ExtEmp) {
        this.ExtEmp = ExtEmp;
    }

    public String getCelEmp() {
        return CelEmp;
    }

    public void setCelEmp(String CelEmp) {
        this.CelEmp = CelEmp;
    }

    public String getUbicaEmp() {
        return UbicaEmp;
    }

    public void setUbicaEmp(String UbicaEmp) {
        this.UbicaEmp = UbicaEmp;
    }

    public String getUsuarEmp() {
        return UsuarEmp;
    }

    public void setUsuarEmp(String UsuarEmp) {
        this.UsuarEmp = UsuarEmp;
    }

    public String getPassEmp() {
        return PassEmp;
    }

    public void setPassEmp(String PassEmp) {
        this.PassEmp = PassEmp;
    }

    public String getPassEmp1() {
        return PassEmp1;
    }

    public void setPassEmp1(String PassEmp1) {
        this.PassEmp1 = PassEmp1;
    }

    public String getTipDoc() {
        return TipDoc;
    }

    public void setTipDoc(String TipDoc) {
        this.TipDoc = TipDoc;
    }

    public String getNombreDepto() {
        return NombreDepto;
    }

    public void setNombreDepto(String NombreDepto) {
        this.NombreDepto = NombreDepto;
    }

    public ArrayList<SelectItem> getCargaCiu() {
        return CargaCiu;
    }

    public void setCargaCiu(ArrayList<SelectItem> CargaCiu) {
        this.CargaCiu = CargaCiu;
    }

    public ArrayList<SelectItem> getCargaDep() {
        return CargaDep;
    }

    public void setCargaDep(ArrayList<SelectItem> CargaDep) {
        this.CargaDep = CargaDep;
    }

    public String getCuidad() {
        return Cuidad;
    }

    public void setCuidad(String Cuidad) {
        this.Cuidad = Cuidad;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    //Aparte
    public StreamedContent getEmplea() {
        return Emplea;
    }

    public void setEmplea(StreamedContent Emplea) {
        this.Emplea = Emplea;
    }

    public DualListModel<LogPermiso> getTeamsDualListModel() {
        return teamsDualListModel;
    }

    public void setTeamsDualListModel(DualListModel<LogPermiso> teamsDualListModel) {
        this.teamsDualListModel = teamsDualListModel;
    }

    public LogPermiso getPer() {
        return Per;
    }

    public void setPer(LogPermiso Per) {
        this.Per = Per;
    }

    public List<LogPermiso> getCargaRoles() {
        return CargaRoles;
    }

    public void setCargaRoles(List<LogPermiso> CargaRoles) {
        this.CargaRoles = CargaRoles;
    }

    public List<LogPermiso> getCargaRolesAsig() {
        return CargaRolesAsig;
    }

    public void setCargaRolesAsig(List<LogPermiso> CargaRolesAsig) {
        this.CargaRolesAsig = CargaRolesAsig;
    }

    public int getCodRoles() {
        return CodRoles;
    }

    public void setCodRoles(int CodRoles) {
        this.CodRoles = CodRoles;
    }

    public String getNombreRol() {
        return NombreRol;
    }

    public void setNombreRol(String NombreRol) {
        this.NombreRol = NombreRol;
    }

    public boolean isEstadoTab1() {
        return estadoTab1;
    }

    public void setEstadoTab1(boolean estadoTab1) {
        this.estadoTab1 = estadoTab1;
    }

    public boolean isEstadoTab2() {
        return estadoTab2;
    }

    public void setEstadoTab2(boolean estadoTab2) {
        this.estadoTab2 = estadoTab2;
    }

    public boolean isEstadoTab3() {
        return estadoTab3;
    }

    public void setEstadoTab3(boolean estadoTab3) {
        this.estadoTab3 = estadoTab3;
    }

    public boolean isEstadoTab4() {
        return estadoTab4;
    }

    public void setEstadoTab4(boolean estadoTab4) {
        this.estadoTab4 = estadoTab4;
    }

    public boolean isEstadoTab5() {
        return estadoTab5;
    }

    public void setEstadoTab5(boolean estadoTab5) {
        this.estadoTab5 = estadoTab5;
    }

    public boolean isEstadoActualizar() {
        return estadoActualizar;
    }

    public void setEstadoActualizar(boolean estadoActualizar) {
        this.estadoActualizar = estadoActualizar;
    }

    public boolean isEstadoRegistrar() {
        return estadoRegistrar;
    }

    public void setEstadoRegistrar(boolean estadoRegistrar) {
        this.estadoRegistrar = estadoRegistrar;
    }

    public boolean isDisablePass() {
        return disablePass;
    }

    public void setDisablePass(boolean disablePass) {
        this.disablePass = disablePass;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }

    public int getRowCount() {
        return RowCount;
    }

    public void setRowCount(int RowCount) {
        this.RowCount = RowCount;
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
     * Metodo que se utiliza para la verificacion del rol de gestor del formato
     * de control y seguimiento
     *
     * @author Maira Alejandra Carvajal
     * @param value
     * @param filter
     * @param locale
     * @return
     * @since 01-05-2015
     * @deprecated
     */
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        try {
            if (filterText == null || filterText.equals("")) {
                return true;
            }

            if (value == null) {
                return false;
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".filterByPrice()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;

    }

    /**
     * Metodo para cargar los Tipos de Documento de la tabla Tipo_Documento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los tipos de documentos
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulTipDocEmp() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getTipDocumento().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.TipDocEmp.add(new SelectItem(MBEmp.getCodTipDoc(), MBEmp.getTipDoc()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulTipDocEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.TipDocEmp;
    }

    /**
     * Metodo Para Cargar los Tipos de Estados de la tabla Estados.
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los estados
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulEstEmp() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getEstado().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.EstEmp.add(new SelectItem(MBEmp.getCodEst(), MBEmp.getNomEst()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulEstEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return EstEmp;
    }

    /**
     * Metodo Para Cargar los cargos empresariales.
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los cargos empresariales
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulCargEmp() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getCargo().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.CargEmp.add(new SelectItem(MBEmp.getCodCarg(), MBEmp.getNomCarg()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulCargEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargEmp;

    }

    /**
     * Metodo Para Cargar los cargos empresariales.
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los cargos empresariales
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulPisos() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getPisos().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.Cargpisos.add(new SelectItem(MBEmp.getCodPiso(), MBEmp.getNomPiso()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulPisos()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.Cargpisos;
    }

    /**
     * Metodo para cargar los jefes de area
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los jefes de area
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getCargJefeEmp() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getJefeEm().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.CargJefes.add(new SelectItem(MBEmp.getDocEmp(), MBEmp.getNombreEmp()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getCargJefeEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargJefes;

    }

    /**
     * Metodo para cargar los empleados en auditoria
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los jefes de area
     * @since 01-11-2014
     */
    public List<SelectItem> getCargEmpAuditoria() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.EmpleadosTodosCombo().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.ListEmpAuditoria.add(new SelectItem(MBEmp.getCodEmp(), MBEmp.getNombreEmp()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getCargEmpAuditoria()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return ListEmpAuditoria;

    }

    /**
     * Metodo para cargar todos los empleados que se encuentra con el Rol de
     * Seguimiento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Con la informacion de los empleados con rol de
     * seguimiento
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getCargEmplSeguimiento() {
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getSeguimientoEm().iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.CargEmpSeguimiento.add(new SelectItem(MBEmp.getCodEmp(), MBEmp.getNombreEmp()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getCargEmplSeguimiento()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargEmpSeguimiento;
    }

    /**
     * Metodo para cargar todos los empleados que se encuentra con el Rol de
     * Seguimiento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @param tab int que contiene el numero de tab por posiscion al que se le
     * va a validar y acceder
     * @since 01-11-2014
     */
    public void cambiarEstadoTab(int tab) {
        try {
            Emp = new LogEmpleado();
            //tab 1 siguiente
            if (tab == 1) {
                if ("0".equals(this.TipDoc)) {
                    mbTodero.setMens("Debe seleccionar un tipo de documento");
                    mbTodero.warn();
                } else if ("".equals(this.DocEmp)) {
                    mbTodero.setMens("Debe llenar el campo 'N° de documento'");
                    mbTodero.warn();
                } else if ("".equals(this.NombreEmp)) {
                    mbTodero.setMens("Debe llenar el campo 'Nombres'");
                    mbTodero.warn();
                } else if ("".equals(this.ApellidoEmp)) {
                    mbTodero.setMens("Debe llenar el campo 'Apellidos'");
                    mbTodero.warn();
                } else if ("0".equals(this.NombreDepto)) {
                    mbTodero.setMens("Debe seleccionar un 'Departamento'");
                    mbTodero.warn();
                } else if ("0".equals(this.Cuidad)) {
                    mbTodero.setMens("Debe seleccionar una 'Ciudad'");
                    mbTodero.warn();
                } else {
                    String doc = this.DocEmp;
                    String tipdoc = this.TipDoc;
                    if (this.estadoActualizar == false) {
                        if (Emp.ConsulEmpleadoVerif(doc, tipdoc) == true) {
                            mbTodero.setMens("Este numero de documento ya se encuentra registrado con el mismo tipo de documento, verifique los datos");
                            mbTodero.warn();
                        } else {
                            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(0)");
                            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(1)");
                            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(1)");
                        }
                    } else {
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(0)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(1)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(1)");
                    }
                }
            } //tab 2 atras
            else if (tab == 21) {
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(0)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(0)");
            } //tab 2 siguiente
            else if (tab == 2) {
                if ("".equals(this.NomContacEmp) || this.NomContacEmp == null) {
                    mbTodero.setMens("Debe llenar el campo 'Nombre' de la persona a contactar");
                    mbTodero.warn();
                } else if ("".equals(this.NumContacEmp) || this.NumContacEmp == null) {
                    mbTodero.setMens("Debe llenar el campo 'Telefono' de la persona a contactar");
                    mbTodero.warn();
                } else {
                    RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(1)");
                    RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(2)");
                    RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(2)");
                }
            } //tab 3 atras
            else if (tab == 31) {
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(1)");
            } //tab 3 siguiente
            else if (tab == 3) {
                Emp = new LogEmpleado();
                String estadoUsuario = "";
                String estaCorreo = "";
                if ("".equals(this.Estado)) {
                    mbTodero.setMens("Debe seleccionar un 'Estado'");
                    mbTodero.warn();
                } else if ("0".equals(this.Cargo) || this.Cargo == null) {
                    mbTodero.setMens("Debe seleccionar un 'Cargo'");
                    mbTodero.warn();
                } else if ("".equals(this.ExtEmp) || this.ExtEmp == null) {
                    mbTodero.setMens("Debe llenar el campo 'Extensión'");
                    mbTodero.warn();
                } else if ("0".equals(this.UbicaEmp) || this.UbicaEmp == null) {
                    mbTodero.setMens("Debe llenar el campo 'Ubicación'");
                    mbTodero.warn();
                } else if ("".equals(this.UsuarEmp) || this.UsuarEmp == null) {
                    mbTodero.setMens("Debe llenar el campo 'Usuario'");
                    mbTodero.warn();
                } else {
                    if (this.estadoActualizar == false) {
                        Emp.setUsuarEmp(UsuarEmp);
                        Dat = Emp.ConsulRegistroUsuari();
                        if (Dat.next()) {
                            mbTodero.setMens("Ya hay registro con este usuario \n favor intente otro diferente");
                            mbTodero.warn();
                            this.UsuarEmp = "";
                        } else {
                            estadoUsuario = "Ok";
                        }
                    } else {
                        if ("".equals(this.MailEmp) && "".equals(this.MailPerEmp)) {
                            mbTodero.setMens("Debe ingresar al menos un correo electronico (Personal o corporativo)");
                            mbTodero.warn();
                        } else {
                            estaCorreo = "Ok";
                        }
                        if ("Ok".equals(estaCorreo)) {
                            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(2)");
                            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(3)");
                            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(3)");
                            avilitarRegistro = false;
                        }
                    }
                    if ("".equals(this.MailEmp) && "".equals(this.MailPerEmp)) {
                        mbTodero.setMens("Debe ingresar al menos un correo electronico (Personal o corporativo)");
                        mbTodero.warn();
                    } else {
                        estaCorreo = "Ok";
                    }
                    if ("Ok".equals(estadoUsuario) && "Ok".equals(estaCorreo)) {
                        //this.estadoTab3 = true;
                        //this.estadoTab4 = false;
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(2)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(3)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(3)");
                        avilitarRegistro = false;
                        if ("".equals(this.MailEmp)) {
                            mbTodero.setMens("Tenga en cuenta que se tomará como correo principal el correo personal si no se ingresa correo corporativo"); //ok
                            mbTodero.info();
                        }
                    }
                }
            } //tab 4 atras
            else if (tab == 41) {
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(2)");
                avilitarRegistro = true;
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambiarEstadoTab()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para Cargar los roles en la lista tipo picklist
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */
    public void getcargaRol() {
        try {
            CargaRoles = new ArrayList<>();   //Instancia de arraylist que carga todos los roles de la clase logpermiso
            CargaRolesAsig = new ArrayList<>();  //Instancia de un array q ue carga todos los roles asignados de la clase logpermiso   
            CargaRoles = Per.getRol();
            //Asigna los datos del arraylist al duallistmodel, componente del pickerlist
            teamsDualListModel = new DualListModel<>(CargaRoles, CargaRolesAsig);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getcargaRol()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para consultar todos los empleados que se encuentren registrados
     * (Tabla Empleados)
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */
    public void consultaTodosEmpleados() {
        try {
            mbTodero.resetTable("formempleado:EmplTable");
            ListEmp.clear();
            this.ListEmp = Emp.ConsulAllEmpleados();
            RequestContext.getCurrentInstance().execute("PF('ConsultaEmpleado').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaTodosEmpleados()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para insertar / modificar a un empleado, validando la onformacion
     * ingresada
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @param tipo_insertar int que contiene el tipo de proceso que se va a
     * realizar, 1: insersion, 2: modificacion
     * @since 01-11-2014
     */
    public void inserEmp(int tipo_insertar) {  //ok
        try {
            Emp = new LogEmpleado();
            roles_target = teamsDualListModel.getTarget();
            if (this.roles_target.size() <= 0 || beanPer.getNombreProEnt().size() <= 0) {
                mbTodero.setMens("Debe llenar los campos obligatorios (*) para completar el registro");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(0)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(3)");
            } else {
                
                //GCH - AGO2016
                if ("No".equals(this.estadoSeleccionFoto)) {
                            //si el empleado no selecciono foto, carga por defecto la foto en blanco
                            actualizarSinFoto(Integer.parseInt(this.DocEmp));
                            
                        }
                
                Emp.setDocEmp(DocEmp);
                Emp.setNombreEmp(NombreEmp);
                Emp.setApellidoEmp(ApellidoEmp);
                Emp.setMailPerEmp(MailPerEmp);
                Emp.setTelPerEmp(TelPerEmp);
                Emp.setCelPerEmp(CelPerEmp);
                Emp.setNomContacEmp(NomContacEmp);
                Emp.setNumContacEmp(NumContacEmp);
                Emp.setMailEmp(MailEmp);
                Emp.setExtEmp(ExtEmp);
                Emp.setCelEmp(CelEmp);
                Emp.setUbicaEmp(UbicaEmp);
                Emp.setFotoEmp(FotoEmpPath);
                Emp.setUsuarEmp(UsuarEmp);
                Emp.setPassEmp(PassEmp);
                Emp.setCodTipDoc(Integer.parseInt(this.TipDoc));
                Emp.setCodCiu(Integer.parseInt(this.Cuidad));
                Emp.setCodCarg(Integer.parseInt(this.Cargo));
                Emp.setFK_CodEmp(Integer.parseInt(this.Empleado));
                Emp.setFotoEmp(FotoEmpPath);
                
                if (tipo_insertar == 1) {
                    Emp.setCodEst(Integer.parseInt("1"));
                    if ("".equals(MailEmp)) {
                        Emp.InserEmplea(((mBsesion.codigoMiSesion())), 2);
                    } else {
                        Emp.InserEmplea(((mBsesion.codigoMiSesion())), 1);
                    }
                    //ok
                    Dat = Emp.ConsulDoc(1);
                    if (Dat.next()) {
                        cod_traido = Dat.getInt("Cod_Empleados");
                        nombre_empleado = Dat.getString("Nombre_Empleados");
                        apellidoEmpleado = Dat.getString("Apellido_Empleados");
                        email_personal = Dat.getString("Correo_Personal_Empleados");
                        pass_empleado = Dat.getString("Password_Empleados");
                        mail_corporativo = Dat.getString("Correo_Corporativo_Empleados");
                        usuario_empleado = Dat.getString("Username_Empleados");
                        if ("".equals(mail_corporativo)) {
                            email_enviar_Correo = email_personal;
                        } else {
                            email_enviar_Correo = mail_corporativo;
                        }
                        beanCorreo.setAsunto("Notificación de creación de empleado SIAI 2.0");
                        beanCorreo.setMensaje("Cordial Saludo,<br/><br/>Se ha completado exitosamente el registro de empleado: " + nombre_empleado + " " + apellidoEmpleado + "<br/> " + "<br/>"
                                + "Su usuario: " + usuario_empleado + "<br/>"
                                + "Su contraseña: " + pass_empleado + "<br/><br/>"
                                + "Para su primer ingreso se le pedira realizar el cambio de contraseña, favor no perder la contraseña generada <br/>"
                                + "Por favor no eliminar este correo <br/><br/>"
                                + " ---------------------------------------POR FAVOR NO RESPONDER ESTE CORREO - Mensaje Generado Automáticamente--------------------------------------<br/>"
                                + "<br/>"
                                + "Este correo es únicamente informativo y es de uso exclusivo del destinatario(a), puede contener información privilegiada y/o confidencial. Si no es usted el destinatario(a) deberá borrarlo inmediatamente.");
                        beanCorreo.setMailDestino(email_enviar_Correo);
                        beanCorreo.enviarCorreo(1);
                        if ("No".equals(this.estadoSeleccionFoto)) {
                            //si el empleado no selecciono foto, carga por defecto la foto en blanco
                            actualizarSinFoto(Integer.parseInt(this.DocEmp));
                            
//GCH - despues de insertar foto actualizar path
                        }
                    }
                    SelectItem cod_prod;
                    for (int i = 0; i <= beanPer.getCargaProducEntAsignado().size() - 1; i++) { //Carga los productos entidad seleccionados y los recorre
                        for (int j = 0; j <= roles_target.size() - 1; j++) { //carga los roles que fueron seleccionados para el empleado
                            cod_prod = beanPer.getCargaProducEntAsignado().get(i);
                            String value = cod_prod.getValue().toString(); //obtiene el elemento value de la lista select item
                            Emp.setCodProdEnt(Integer.parseInt(cod_prod.getValue().toString()));
                            LogPermiso prueba = roles_target.get(j); //Carga en una instancia tipo Logpermiso el codigo del rol que se asigno
                            int aaa = prueba.getCodRoles();
                            Emp.setCodRol(prueba.getCodRoles()); //Guarda el codigo del rol en la calse Log Empleado
                            Emp.setEstado_Permisos(1); //define estado activo al permiso para el empleado
                            Emp.InserActualizarPermisos(1, cod_traido, mBsesion.codigoMiSesion());
                            //Emp.InserPermisos(cod_traido);//Realiza la insersion en la tabla permisos
                            //ok 
                        }
                    }
                    clearResetComponets();
                    mbTodero.setMens("Empleado registrado"); //ok
                    mbTodero.info();
                    mbTodero.setMens("Su información de acceso al sistema fue enviada al correo registrado (" + this.email_enviar_Correo + "), por favor verifique en un promedio de 2 a 5 minutos"); //ok
                    mbTodero.info();
                    mbTodero.resetTable("formempleado:EmplTable");
                    ListEmp.clear();
                    this.ListEmp = Emp.ConsulAllEmpleados();
                } else if (tipo_insertar == 2) {
                    Emp.setCodEst(Integer.parseInt(this.Estado));
                    if (Emp.ActualizarEmplea(1, mBsesion.codigoMiSesion(), cod_traido) == true) {
                        Per.getCambiarEstadoPermisosEmpleado(cod_traido);
                        SelectItem cod_prod;
                        //consulta los roles que tiene asignado 
                        for (int i = 0; i <= beanPer.getCargaProducEntAsignado().size() - 1; i++) { //Carga los productos entidad seleccionados y los recorre
                            for (int j = 0; j <= roles_target.size() - 1; j++) { //carga los roles que fueron seleccionados para el empleado
                                cod_prod = beanPer.getCargaProducEntAsignado().get(i);
                                String value = cod_prod.getValue().toString(); //obtiene el elemento value de la lista select item
                                Emp.setCodProdEnt(Integer.parseInt(cod_prod.getValue().toString()));
                                LogPermiso prueba = roles_target.get(j); //Carga en una instancia tipo Logpermiso el codigo del rol que se asigno
                                int aaa = prueba.getCodRoles();
                                Emp.setCodRol(prueba.getCodRoles()); //Guarda el codigo del rol en la calse Log Empleado
                                Emp.setEstado_Permisos(1); //define estado activo al permiso para el empleado
                                ResultSet datos;
                                //consulta todos los permisos con el fin de verificar cuales ya existen para actualizarlos o para crearlos
                                datos = Emp.consultaVerificarActualizarRoles(cod_traido);
                                //si la consulta trae resultados actualiza el registro
                                if (datos.next()) {
                                    Emp.InserActualizarPermisos(2, cod_traido, mBsesion.codigoMiSesion());
                                    //Emp.ActualizarPermisosEmpleado(cod_traido);
                                } else {
                                    //si no encuentra el registro lo crea y lo actualiza con estado = 1
                                    Emp.InserActualizarPermisos(1, cod_traido, mBsesion.codigoMiSesion());
                                }
                                //ok 
                            }
                        }
                        mbTodero.setMens("Empleado Actualizado"); //ok
                        mbTodero.info();
                        clearResetComponets();
                        this.ListEmp.clear();
                        this.ListEmp = Emp.ConsulAllEmpleados();
                    } else if (Emp.ActualizarEmplea(1, mBsesion.codigoMiSesion(), cod_traido) == false) {
                        mbTodero.setMens("No es posible actualizar el empleado puede que este duplicando un documento ya existente"); //ok
                        mbTodero.warn();
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(3)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(1)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').disable(2)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').enable(0)");
                        RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(0)");
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".inserEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para limpiar los componentes utilizados para la creacion /
     * modificacion de empleados
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */
    public void clearResetComponets() {  //ok
        try {
            DocEmp = "";
            NombreEmp = "";
            ApellidoEmp = "";
            MailPerEmp = "";
            TelPerEmp = "";
            CelPerEmp = "";

            NomContacEmp = "";
            NumContacEmp = "";

            MailEmp = "";
            ExtEmp = "";
            CelEmp = "";
            UbicaEmp = "";
            FotoEmpPath = "";
            UsuarEmp = "";

            this.TipDoc = "0";
            this.Cuidad = "0";
            this.Estado = "0";
            this.Cargo = "0";
            this.NombreDepto = "0";
            this.Empleado = "0";

            beanPer.setNombreProEnt(null);
            beanPer.setCargaProducEntAsignado(null);
            getcargaRol();

            this.estadoActualizar = false;
            this.estadoRegistrar = true;
            this.disablePass = false;

            this.estadoTab1 = false;
            this.estadoTab2 = true;
            this.estadoTab3 = true;
            this.estadoTab4 = true;
            RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(0)");
            this.avilitarRegistro = true;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".clearResetComponets()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo de prueba para comprobar que la actualizacion de la foto se este
     * realizando correctamente
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */
    public void actualizarFotoPrueba() {
        try {

            if (this.FotoEmp.getSize() <= 0) {
                //  JOptionPane.showConfirmDialog(null, "ok");
                actualizarSinFoto(1);
                //JOptionPane.showMessageDialog(null, "OK sin foto");
            } else {
                //JOptionPane.showConfirmDialog(null, "ok");
                actualizarFoto(1);
                //JOptionPane.showMessageDialog(null, "OK");
            }
        } catch (HeadlessException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarFotoPrueba()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para actualizar la foto de un empleado con el archivo
     * "Sin-foto.jpg"
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @param cod_empl int que contiene el codigo del empleado al que se le
     * agregara la foto Sin-foto.jpg
     * @since 01-11-2014
     */
    public void actualizarSinFoto(int cod_empl) {
        InputStream input = null;
        OutputStream output = null;
        try {

            //GCH - define path en el servidor
            String path = File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "DBFotos" + File.separator;
            
            //Intenta crear directorio si no existe
            //File Ruta = new File(path);
            //if (!Ruta.exists()) {
            //    Ruta.mkdirs();
            //}
            
            // GCH - Por defecto se carga foto plantilla Sin-foto.jpg
            input = new FileInputStream(new File(path + "Sin-foto.jpg"));
            output = new FileOutputStream(new File(path + cod_empl + "-Sin-foto.jpg")); // GCH

            //GCH - lee y escribe archivo y lo guarda para cada empleado
            int read;
            byte[] bytes = new byte[1024];
                            
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
                        
            //Emp.setFotoEmp(path + cod_empl + "-Sin-foto.jpg");
            //Emp.ActualizarPath(cod_empl);
            
            //GCH - AGO2016 - Actualiza variable global con el path a ser almacenado en la BD
            this.FotoEmpPath= path + cod_empl + "-Sin-foto.jpg";

        } catch (HeadlessException e) {

            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarSinFoto()' causado por: " + e.getMessage());
            mbTodero.error();
        }  // catch (IOException | SQLException e) {
            catch (IOException  e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarSinFoto()' causado por: " + e.getMessage());
            mbTodero.error(); } 
         finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarSinFoto()' causado por: " + e.getMessage());
                mbTodero.error();
            }
            
        }
    }

    /**
     * Metodo para actualizar la foto de un empleado con el archivo que haya
     * seleccionado como archivo de imagen
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @param cod_empl int que contiene el codigo del empleado al que se le
     * agregara la foto Sin-foto.jpg
     * @return boolean con informacion true o false si se actualizo o no la foto
     * respectivamente
     * @since 01-11-2014
     */
    public boolean actualizarFoto(int cod_empl) {
        boolean opcion = true;
        Emp = new LogEmpleado();
        InputStream input = null;
        OutputStream output = null;
        try {
            if (!this.FotoEmp.getFileName().endsWith(".jpg")) {
                mbTodero.setMens("Debe seleccionar una foto de extencion .jpg");
                mbTodero.warn();
                opcion = false;
            } else if (this.FotoEmp.getSize() >= 2097152) {
                mbTodero.setMens("La foto no debe superar 2 Mg de tamaño");
                mbTodero.warn();
                opcion = false;
            } else {
                
                //GCH - 05AGO2016
                //Define ruta para guardar fotos
                //ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                //String path = (String) sContext.getRealPath("/"); //fotos
                
                
                //input = new FileInputStream(new File(path + cod_empl + ".jpg"));
                
                //GCH - Archivo cargado por el usuario
                input = this.FotoEmp.getInputstream();
                
                String path = File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "DBFotos" + File.separator;
            
                output = new FileOutputStream(new File(path + cod_empl + ".jpg"));
                
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    output.write(bytes, 0, read);
                }
                
                /*Emp.setFotoEmp(path + cod_empl + ".jpg");
                Emp.ActualizarPath(cod_empl);*/
                
                //GCH - AGO2016 - Actualiza variable global con el path a ser almacenado en la BD
                
                this.FotoEmpPath= path + cod_empl + ".jpg";
                
                opcion = true;
            }

        } //catch (HeadlessException | SQLException | IOException e) {
            catch (HeadlessException  | IOException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarFoto()' causado por: " + e.getMessage());
            mbTodero.error();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarFoto()' causado por: " + e.getMessage());
                mbTodero.error();
            }

        }
        return opcion;
    }

   

    /**
     * Metodo para actualizar la foto ya existente de un empleado
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */
    public void actualizarLaFoto() {
        try {
            actualizarFoto(cod_traido);
            mbTodero.setMens("Foto actualizada");
            mbTodero.info();
            this.estadoTab1 = true;
            this.estadoTab2 = true;
            this.estadoTab3 = false;
            this.estadoTab4 = true;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizarLaFoto()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para cargar y mostrar la informacion de un empleado seleccionado
     * en la tabla de empleados
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */
    public void cargarInfo() {
        try {

            if (this.EmpConsulta == null) {
                RequestContext.getCurrentInstance().execute("PF('ConsultaEmpleado').show()");
                mbTodero.setMens("Debe seleccionar un empleado");
                mbTodero.warn();
            } else {
                RequestContext.getCurrentInstance().execute("PF('ConsultaEmpleado').hide()");
                RequestContext.getCurrentInstance().execute("PF('tabEmpleados').select(0)");
                this.estadoTab1 = false;
                this.estadoTab2 = true;
                this.estadoTab3 = true;
                this.estadoTab4 = true;

                this.estadoActualizar = true;
                this.estadoRegistrar = false;

                this.cod_traido = EmpConsulta.getCodEmp();
                this.TipDoc = String.valueOf(EmpConsulta.getCodTipDoc());
                this.DocEmp = EmpConsulta.getDocEmp();
                this.NombreEmp = EmpConsulta.getNombreEmp();
                this.ApellidoEmp = EmpConsulta.getApellidoEmp();
                this.TelPerEmp = EmpConsulta.getTelPerEmp();
                this.CelPerEmp = EmpConsulta.getCelPerEmp();
                this.MailPerEmp = EmpConsulta.getMailPerEmp();
                this.NombreDepto = String.valueOf(EmpConsulta.getCodDepto());
   
                this.Cuidad = String.valueOf(EmpConsulta.getCodCiu());
                // BeanUbi.NomDep = String.valueOf(Emp.getCodDepto());
                //this.Cuidad = String.valueOf(Emp.getCodCiu());

                this.NomContacEmp = EmpConsulta.getNomContacEmp();
                this.NumContacEmp = EmpConsulta.getNumContacEmp();

                this.Estado = String.valueOf(EmpConsulta.getCodEst());
                this.Cargo = String.valueOf(EmpConsulta.getCodCarg());
                this.ExtEmp = EmpConsulta.getExtEmp();
                this.CelEmp = EmpConsulta.getCelEmp();
                this.UbicaEmp = EmpConsulta.getUbicaEmp();
                this.MailEmp = EmpConsulta.getMailEmp();
                this.Empleado = String.valueOf(EmpConsulta.getFK_CodEmp());

                this.UsuarEmp = EmpConsulta.getUsuarEmp();
                this.disablePass = true;

                ProdEntiEmpl = new ArrayList<>();

                ProdEntiEmpl = Per.getProEntAsignadoooo(cod_traido);

                List<Integer> codigos = new ArrayList<>();

                for (int i = 0; i <= ProdEntiEmpl.size() - 1; i++) {
                    Integer cod_prodEnti = ProdEntiEmpl.get(i).getCodProEnt();

                    codigos.add(cod_prodEnti);

                }

                beanPer.setNombreProEnt(codigos);
                beanPer.getcargaProEntAsignado();
                RolesEmpl = new ArrayList<>();
                //consulta los roles que tiene asignado 
                RolesEmpl = Per.consultaRolesAsignados(cod_traido);

                //crea una lista donde se almacena la comparacion de codigos de roles
                List<Integer> comparar = new ArrayList<>();

                //ciclo para recorrer todos los roles existentes
                for (int i = 0; i <= CargaRoles.size() - 1; i++) {

                    //ciclo interno que recorreo los roles asignados
                    for (int j = 0; j <= RolesEmpl.size() - 1; j++) {

                        //compara los roles (codigos) que encuentra iguales en el mismo index y almacena el numero en que encontro el registro igual y lo guarda en la lista comparar 
                        if (CargaRoles.get(i).getCodRoles().equals(RolesEmpl.get(j).getCodRoles())) {
                            comparar.add(i);  //guarda la posicion en donde encuentra el registro igual para luego este eliminarlo
                            //ok

                        }
                    }
                }

                //recorre la lista de comparar y con el index traido elimina los que enontro repetidos y deja los que no
                //el ciclo se recorre a la inversa por que cada vez que se elimina un registro se corre una posicion en la lista
                for (int i = comparar.size() - 1; i >= 0; i--) {
                    int index = comparar.get(i);
                    CargaRoles.remove(index);  //ok
                }

                //llena el source y target del elemento pick list con la informacion de roles por asignar y asignados
                //ok 
                teamsDualListModel = new DualListModel<>(CargaRoles, RolesEmpl);

                //vuelve a llenar la lista con todos los roles existentes para volver a realizar la comparacion si es necesario 
                CargaRoles = Per.getRol();
                //this.PassEmp = Emp.getPassEmp();
                //  this.Emp = new LogEmpleado();
            }
            mbTodero.resetTable("formempleado:EmplTable");

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para realizar validaciones de numeros
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     * @deprecated No se utiliza
     */
    public void validarNumeros() {
        try {
            RequestContext requestContext = RequestContext.getCurrentInstance();

            requestContext.execute("return validarSiNumero(event);");
            requestContext.addCallbackParam("value", "return validarSiNumero(event);");
            boolean dsd;
            dsd = requestContext.isAjaxRequest();
            if (dsd == false) {
                mbTodero.setMens("Este campo no acepta letras");
                mbTodero.warn();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarNumeros()' causado por: " + e.getMessage());
            mbTodero.error();
        }

        //RequestContext.getCurrentInstance ().execute("return validarnumeros(event)");            
    }

    /**
     * Metodo para cargar la foto de in empleado especificp
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @param event FileUploadEvent que captura el evento de archivo subido para
     * obtener valores del mismo
     * @since 01-11-2014
     */
    public void cargarFoto(FileUploadEvent event) {
        try {
            if (this.estadoRegistrar == true) {

                this.FotoEmp = event.getFile();
                this.estadoSeleccionFoto = "SI";
                if (actualizarFoto(Integer.parseInt(this.DocEmp)) == true) {
                    mbTodero.setMens("Foto Cargada");
                    mbTodero.info();
                }

            } else if (this.estadoActualizar == true) {
                this.FotoEmp = event.getFile();
                if (actualizarFoto(Integer.parseInt(this.DocEmp)) == true) {
                    mbTodero.setMens("Foto actualizada");
                    mbTodero.info();
                }

                this.estadoTab1 = true;
                this.estadoTab2 = true;
                this.estadoTab3 = false;
                this.estadoTab4 = true;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarFoto()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cargar las personas que estan a cargo de otras
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @return ArrayList Que contiene la informacion obtenida de las personas a
     * cargo
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getPersonaCargo() {
        this.CargPersonalACargo.clear();
        try {
            Iterator<LogEmpleado> Ite;
            Ite = Emp.getPersonasACargo(mBsesion.codigoMiSesion()).iterator();
            while (Ite.hasNext()) {
                LogEmpleado MBEmp = Ite.next();
                this.CargPersonalACargo.add(new SelectItem(MBEmp.getDocEmp(), MBEmp.getNombreEmp()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getPersonaCargo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargPersonalACargo;
    }

    /**
     * Metodo para realizar la actualizacion de un empleado
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @since 01-11-2014
     */  
    public void actualizacionEmp() {
        try {
            if ("0".equals(this.TipDoc) || "".equals(this.DocEmp) || "".equals(this.NombreEmp) || "".equals(ApellidoEmp) || "".equals(NombreDepto) || "".equals(this.Cuidad) || "".equals(this.NomContacEmp) || "".equals(this.NumContacEmp)) {
                mbTodero.setMens("Falta informacion obligatoria (*), favor ingrese todos los datos requeridos");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
            } else {
                if (!this.PassEmp.equals(PassEmp1)) {
                    mbTodero.setMens("Las contraseñas no coinciden, verifiquela nuevamente");
                    mbTodero.warn();
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
                } else {
                    boolean Rta = false;
                    Emp = new LogEmpleado();
                    Emp.setCodEmp(mBsesion.codigoMiSesion());
                    Emp.setNombreEmp(NombreEmp);
                    Emp.setApellidoEmp(ApellidoEmp);
                    Emp.setNomDepto(NombreDepto);
                    Emp.setTipDoc(TipDoc);
                    Emp.setDocEmp(DocEmp);
                    Emp.setTelPerEmp(TelPerEmp);
                    Emp.setCelPerEmp(CelPerEmp);
                    Emp.setMailPerEmp(MailPerEmp);
                    Emp.setCodCiu(Integer.parseInt(this.Cuidad));
                    Emp.setNomContacEmp(NomContacEmp);
                    Emp.setNumContacEmp(NumContacEmp);
                    Emp.setPassEmp(PassEmp);
                    Rta = Emp.ActualizarEmplea(2, 0, mBsesion.codigoMiSesion());
                    if (Rta == true) {
                        mbTodero.setMens("La informacion se ha actualizado correctamente");
                        mbTodero.info();
                        RequestContext.getCurrentInstance().execute("PF('DlgPassword').hide()");
                    } else {
                        mbTodero.setMens("Error en la actualizacion del Empleado");
                        mbTodero.warn();
                        RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".actualizacionEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cargar las ciudades
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez Doncel
     * @param event ActionEvent que contiene el evento que el usuario realizo al momento de generar el reporte
     * @since 01-11-2014
     */
    public void generarReporteEstudio(ActionEvent event) {
        try {
            String nombre = "RPTE_Empleados";
            String titulo = "Reporte general de empleados";
            mbTodero.info();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".generarReporteEstudio()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }
    
        /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

}
