/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Conexion.Conexion;
import Logic.LogAdministracion;
import Logic.LogEntidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de las entidades, suscursales y
 * asesores que se manejan en la organizaci[on </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-12-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBEntidad")
@ViewScoped
public class BeanEntidad {

    private int cod_preRadic;
    private int cod_avaluo;
    private int num_entidad;
    ArrayList<LogEntidad> CargaEntidadesTodo = new ArrayList<>();

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */

    LogEntidad Ent = new LogEntidad();
    LogEntidad EntEnt = new LogEntidad();
    LogEntidad Entsucu = new LogEntidad();
    LogEntidad EntAse = new LogEntidad();
    LogAdministracion Adm = new LogAdministracion();
    ResultSet Dat = null;

    /**
     * Variables de lista para cargar las diferentes consultas de entidades,
     * sucursales y asesores.*
     */
    private ArrayList<SelectItem> CargaEnt = new ArrayList<>();
    private ArrayList<SelectItem> CargaEnt1 = new ArrayList<>();
    private ArrayList<SelectItem> CargaEnt2 = new ArrayList<>();
    private ArrayList<SelectItem> CargaEnt3 = new ArrayList<>();
    private ArrayList<SelectItem> CargaEnt4 = new ArrayList<>();
    private ArrayList<SelectItem> CargaEnt5 = new ArrayList<>();

    private ArrayList<SelectItem> CargaSucur = new ArrayList<>();

    private ArrayList<SelectItem> CargaSucur1 = new ArrayList<>();
    private ArrayList<SelectItem> CargaSucur2 = new ArrayList<>();
    private ArrayList<SelectItem> CargaSucur3 = new ArrayList<>();
    private ArrayList<SelectItem> CargaSucur4 = new ArrayList<>();
    private ArrayList<SelectItem> CargaSucur5 = new ArrayList<>();

    private ArrayList<SelectItem> CargaAses = new ArrayList<>();

    private ArrayList<SelectItem> CargaAses1 = new ArrayList<>();
    private ArrayList<SelectItem> CargaAses2 = new ArrayList<>();
    private ArrayList<SelectItem> CargaAses3 = new ArrayList<>();
    private ArrayList<SelectItem> CargaAses4 = new ArrayList<>();
    private ArrayList<SelectItem> CargaAses5 = new ArrayList<>();

    private ArrayList<SelectItem> CargaCiu = new ArrayList<>();
    private ArrayList<SelectItem> CargaRegimen = new ArrayList<>();

    /**
     * Variables*
     */
    /**
     * entidad*
     */
    private int codEntidad;
    private String numEntidad;
    private String nombreEntidad;
    private String codRegimen;
    private String codTipRegimen;

    /**
     * Sucursal*
     */
    private int codSucursal;
    private String codOficina;
    private String nombreSucursal;
    private String telefonoSucursal;
    private String direccionSucursal;
    private String codDeptoSucursal;
    private String codCiudadSucursal;
    private String codEntidadSucursal;


    /**
     * Asesor*
     */
    private int codAsesor;
    private String nombreAsesor;
    private String cargoAsesor;
    private String mailAsesor;
    private String telefonoAsesor;
    private String estadoAsesor;
    private String fk_entidadAsesor;
    private String fk_sucursalAsesor;
    private int codRef = 1;
    /*
   Declaracion de varibles para l
     */
    /**
     * listas *
     */
    private ArrayList<LogEntidad> CargaEntTodo = new ArrayList<>();
    private ArrayList<LogEntidad> CargaSucurTodo = new ArrayList<>();
    private ArrayList<LogEntidad> CargaAsesTodo = new ArrayList<>();

    private ArrayList<SelectItem> CargaSucurTodoCombo = new ArrayList<>();

    /**
     * estados / renderer *
     */
    private String estadoRadioSeleccion = "Entidades";
    private boolean estPanelEntidad;
    private boolean estPanelSucursal;
    private boolean estPanelAsesor;

    private String tituloFormEntidad;
    private String tituloFormSucursal;
    private String tituloFormAsesor;

    private boolean estadoRegistroEntidad;
    private boolean estadoRegistroSucursal;
    private boolean estadoRegistroAsesor;

    private boolean estadoActualizarEntidad;
    private boolean estadoActualizarSucursal;
    private boolean estadoActualizarAsesor;

    /**
     * Radicacion*
     */
    private String opcionMostrarEntidad;
    private boolean opcionRadiosMostrarEntidad = true;

    /**
     * Entidad*
     */
    private boolean estadoVisibleEnti1;
    private boolean estadoVisibleEnti2;
    private boolean estadoVisibleEnti3;
    private boolean estadoVisibleEnti4;
    private boolean estadoVisibleEnti5;

    private String codEntidad1 = "";
    private String codEntidad2 = "";
    private String codEntidad3 = "";
    private String codEntidad4 = "";
    private String codEntidad5 = "";

    private String nombreEntidad1;
    private String nombreEntidad2;
    private String nombreEntidad3;
    private String nombreEntidad4;
    private String nombreEntidad5;

    private String documentoEntidad1;
    private String documentoEntidad2;
    private String documentoEntidad3;
    private String documentoEntidad4;
    private String documentoEntidad5;

    /**
     * Sucursal*
     */
    private String codSucursal1 = "";
    private String codSucursal2 = "";
    private String codSucursal3 = "";
    private String codSucursal4 = "";
    private String codSucursal5 = "";

    private String nombreSucursal1;
    private String nombreSucursal2;
    private String nombreSucursal3;
    private String nombreSucursal4;
    private String nombreSucursal5;

    private String telefonoSucursal1;
    private String telefonoSucursal2;
    private String telefonoSucursal3;
    private String telefonoSucursal4;
    private String telefonoSucursal5;

    /**
     * Asesor*
     */
    private String codAsesor1 = "";
    private String codAsesor2 = "";
    private String codAsesor3 = "";
    private String codAsesor4 = "";
    private String codAsesor5 = "";

    private String nombreAsesor1;
    private String nombreAsesor2;
    private String nombreAsesor3;
    private String nombreAsesor4;
    private String nombreAsesor5;

    private String cargoAsesor1;
    private String cargoAsesor2;
    private String cargoAsesor3;
    private String cargoAsesor4;
    private String cargoAsesor5;

    private String mailAsesor1;
    private String mailAsesor2;
    private String mailAsesor3;
    private String mailAsesor4;
    private String mailAsesor5;

    private String telefonoAsesor1;
    private String telefonoAsesor2;
    private String telefonoAsesor3;
    private String telefonoAsesor4;
    private String telefonoAsesor5;

    private boolean estadoInfoAsesor1 = false;
    private boolean estadoInfoAsesor2 = false;
    private boolean estadoInfoAsesor3 = false;
    private boolean estadoInfoAsesor4 = false;
    private boolean estadoInfoAsesor5 = false;

    /**
     * Entidadfacturar*
     */
    private String codEntidadfactu1;
    private String codEntidadfactu2;
    private String codEntidadfactu3;
    private String codEntidadfactu4;
    private String codEntidadfactu5;

    private String nombreEntidadfactu1;
    private String nombreEntidadfactu2;
    private String nombreEntidadfactu3;
    private String nombreEntidadfactu4;
    private String nombreEntidadfactu5;

    private String documentoEntidadfactu1;
    private String documentoEntidadfactu2;
    private String documentoEntidadfactu3;
    private String documentoEntidadfactu4;
    private String documentoEntidadfactu5;

    /**
     * Sucursal*
     */
    private String codSucursalfactu1;
    private String codSucursalfactu2;
    private String codSucursalfactu3;
    private String codSucursalfactu4;
    private String codSucursalfactu5;

    private String nombreSucursalfactu1;
    private String nombreSucursalfactu2;
    private String nombreSucursalfactu3;
    private String nombreSucursalfactu4;
    private String nombreSucursalfactu5;

    private String telefonoSucursalfactu1;
    private String telefonoSucursalfactu2;
    private String telefonoSucursalfactu3;
    private String telefonoSucursalfactu4;
    private String telefonoSucursalfactu5;

    /**
     * Asesor*
     */
    private String codAsesorfactu1;
    private String codAsesorfactu2;
    private String codAsesorfactu3;
    private String codAsesorfactu4;
    private String codAsesorfactu5;

    private String nombreAsesorfactu1;
    private String nombreAsesorfactu2;
    private String nombreAsesorfactu3;
    private String nombreAsesorfactu4;
    private String nombreAsesorfactu5;

    private String cargoAsesorfactu1;
    private String cargoAsesorfactu2;
    private String cargoAsesorfactu3;
    private String cargoAsesorfactu4;
    private String cargoAsesorfactu5;

    private boolean estadoAgregarFilasEntidad1 = false;
    private boolean estadoAgregarFilasEntidad2 = false;
    private boolean estadoAgregarFilasEntidad3 = false;
    private boolean estadoAgregarFilasEntidad4 = false;
    private boolean estadoAgregarFilasEntidad5 = false;

    private boolean entidadFact1;
    private boolean entidadFact2;
    private boolean entidadFact3;
    private boolean entidadFact4;
    private boolean entidadFact5;

    private boolean DeshabilitarAsesor1 = false;
    private boolean DeshabilitarElimia1 = false;
    private boolean DeshabilitarAsesor2 = false;
    private boolean DeshabilitarAsesor3 = false;
    private boolean DeshabilitarAsesor4 = false;
    private boolean DeshabilitarAsesor5 = false;

    private boolean EditarFila1 = false;
    private boolean EditarFila2 = false;
    private boolean EditarFila3 = false;
    private boolean EditarFila4 = false;
    private boolean EditarFila5 = false;

    private boolean AgregarFila1 = true;
    private boolean AgregarFila2 = false;
    private boolean AgregarFila3 = false;
    private boolean AgregarFila4 = false;

    private boolean QuitarFila1 = true;
    private boolean QuitarFila2 = false;
    private boolean QuitarFila3 = false;
    private boolean QuitarFila4 = false;
    private boolean QuitarFila5 = false;

    private boolean AceptarFila1 = false;
    private boolean AceptarFila2 = false;
    private boolean AceptarFila3 = false;
    private boolean AceptarFila4 = false;
    private boolean AceptarFila5 = false;

    /**
     * Tarifas pactadas de entidades (si el check 'facturar' es true)*
     */
    private boolean estadoAgregarTarifasPactEnti1 = false;
    private boolean estadoAgregarTarifasPactEnti2 = false;
    private boolean estadoAgregarTarifasPactEnti3 = false;
    private boolean estadoAgregarTarifasPactEnti4 = false;
    private boolean estadoAgregarTarifasPactEnti5 = false;

    private String opcionRadioTarifasPactadosEnti1;
    private String opcionRadioTarifasPactadosEnti2;
    private String opcionRadioTarifasPactadosEnti3;
    private String opcionRadioTarifasPactadosEnti4;
    private String opcionRadioTarifasPactadosEnti5;

    private boolean estadoRadioAgregarTarifaEnti1;
    private boolean estadoRadioAgregarTarifaEnti2;
    private boolean estadoRadioAgregarTarifaEnti3;
    private boolean estadoRadioAgregarTarifaEnti4;
    private boolean estadoRadioAgregarTarifaEnti5;
    private boolean estadoTarifasPactEnti1;
    private boolean estadoTarifasPactEnti2;
    private boolean estadoTarifasPactEnti3;
    private boolean estadoTarifasPactEnti4;
    private boolean estadoTarifasPactEnti5;

    private String tipoTarifaEnti1;
    private String tipoTarifaEnti2;
    private String tipoTarifaEnti3;
    private String tipoTarifaEnti4;
    private String tipoTarifaEnti5;

    private String valorTarifaEnti1;
    private String valorTarifaEnti2;
    private String valorTarifaEnti3;
    private String valorTarifaEnti4;
    private String valorTarifaEnti5;

    private String tipoTarifaEntidad;
    private String valorTarifaEnti;

    private boolean enviarEntidadCarta;

    private boolean enviarCartaEnti1;
    private boolean enviarCartaEnti2;
    private boolean enviarCartaEnti3;
    private boolean enviarCartaEnti4;
    private boolean enviarCartaEnti5;

    private String correoEnti1;
    private String correoEnti2;
    private String correoEnti3;
    private String correoEnti4;
    private String correoEnti5;

    /**
     * post-radicacion*
     */
    private ArrayList<LogEntidad> CargaEntidCarta = new ArrayList<>();

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

    public int getCodRef() {
        return codRef;
    }

    public void setCodRef(int codRef) {
        this.codRef = codRef;
    }

    public BeanSesion getmBsesion() {
        return mBsesion;
    }

    public void setmBsesion(BeanSesion mBsesion) {
        this.mBsesion = mBsesion;
    }

    public ArrayList<SelectItem> getCargaEnt1() {
        return CargaEnt1;
    }

    public void setCargaEnt1(ArrayList<SelectItem> CargaEnt1) {
        this.CargaEnt1 = CargaEnt1;
    }

    public ArrayList<SelectItem> getCargaEnt2() {
        return CargaEnt2;
    }

    public void setCargaEnt2(ArrayList<SelectItem> CargaEnt2) {
        this.CargaEnt2 = CargaEnt2;
    }

    public ArrayList<SelectItem> getCargaEnt3() {
        return CargaEnt3;
    }

    public void setCargaEnt3(ArrayList<SelectItem> CargaEnt3) {
        this.CargaEnt3 = CargaEnt3;
    }

    public ArrayList<SelectItem> getCargaEnt4() {
        return CargaEnt4;
    }

    public void setCargaEnt4(ArrayList<SelectItem> CargaEnt4) {
        this.CargaEnt4 = CargaEnt4;
    }

    public ArrayList<SelectItem> getCargaEnt5() {
        return CargaEnt5;
    }

    public void setCargaEnt5(ArrayList<SelectItem> CargaEnt5) {
        this.CargaEnt5 = CargaEnt5;
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
    public ArrayList<LogEntidad> getCargaEntidadesTodo() {
        return CargaEntidadesTodo;
    }

    public void setCargaEntidadesTodo(ArrayList<LogEntidad> CargaEntidadesTodo) {
        this.CargaEntidadesTodo = CargaEntidadesTodo;
    }

    public ArrayList<SelectItem> getCargaEnt() {
        return CargaEnt;
    }

    public void setCargaEnt(ArrayList<SelectItem> CargaEnt) {
        this.CargaEnt = CargaEnt;
    }

    public ArrayList<SelectItem> getCargaSucur() {
        return CargaSucur;
    }

    public void setCargaSucur(ArrayList<SelectItem> CargaSucur) {
        this.CargaSucur = CargaSucur;
    }

    public ArrayList<SelectItem> getCargaAses() {
        return CargaAses;
    }

    public void setCargaAses(ArrayList<SelectItem> CargaAses) {
        this.CargaAses = CargaAses;
    }

    public LogEntidad getEnt() {
        return Ent;
    }

    public void setEnt(LogEntidad Ent) {
        this.Ent = Ent;
    }

    public LogEntidad getEntEnt() {
        return EntEnt;
    }

    public void setEntEnt(LogEntidad EntEnt) {
        this.EntEnt = EntEnt;
    }

    public LogEntidad getEntsucu() {
        return Entsucu;
    }

    public void setEntsucu(LogEntidad Entsucu) {
        this.Entsucu = Entsucu;
    }

    public LogEntidad getEntAse() {
        return EntAse;
    }

    public void setEntAse(LogEntidad EntAse) {
        this.EntAse = EntAse;
    }

    public int getCodEntidad() {
        return codEntidad;
    }

    public void setCodEntidad(int codEntidad) {
        this.codEntidad = codEntidad;
    }

    public String getNumEntidad() {
        return numEntidad;
    }

    public void setNumEntidad(String numEntidad) {
        this.numEntidad = numEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getCodRegimen() {
        return codRegimen;
    }

    public void setCodRegimen(String codRegimen) {
        this.codRegimen = codRegimen;
    }

    public String getCodTipRegimen() {
        return codTipRegimen;
    }

    public void setCodTipRegimen(String codTipRegimen) {
        this.codTipRegimen = codTipRegimen;
    }

    public String getCodOficina() {
        return codOficina;
    }

    public void setCodOficina(String codOficina) {
        this.codOficina = codOficina;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public String getCodDeptoSucursal() {
        return codDeptoSucursal;
    }

    public void setCodDeptoSucursal(String codDeptoSucursal) {
        this.codDeptoSucursal = codDeptoSucursal;
    }

    public String getCodCiudadSucursal() {
        return codCiudadSucursal;
    }

    public void setCodCiudadSucursal(String codCiudadSucursal) {
        this.codCiudadSucursal = codCiudadSucursal;
    }

    public String getCodEntidadSucursal() {
        return codEntidadSucursal;
    }

    public void setCodEntidadSucursal(String codEntidadSucursal) {
        this.codEntidadSucursal = codEntidadSucursal;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public String getCargoAsesor() {
        return cargoAsesor;
    }

    public void setCargoAsesor(String cargoAsesor) {
        this.cargoAsesor = cargoAsesor;
    }

    public String getMailAsesor() {
        return mailAsesor;
    }

    public void setMailAsesor(String mailAsesor) {
        this.mailAsesor = mailAsesor;
    }

    public String getTelefonoAsesor() {
        return telefonoAsesor;
    }

    public void setTelefonoAsesor(String telefonoAsesor) {
        this.telefonoAsesor = telefonoAsesor;
    }

    public String getEstadoAsesor() {
        return estadoAsesor;
    }

    public void setEstadoAsesor(String estadoAsesor) {
        this.estadoAsesor = estadoAsesor;
    }

    public String getFk_entidadAsesor() {
        return fk_entidadAsesor;
    }

    public void setFk_entidadAsesor(String fk_entidadAsesor) {
        this.fk_entidadAsesor = fk_entidadAsesor;
    }

    public String getFk_sucursalAsesor() {
        return fk_sucursalAsesor;
    }

    public void setFk_sucursalAsesor(String fk_sucursalAsesor) {
        this.fk_sucursalAsesor = fk_sucursalAsesor;
    }

    public ArrayList<LogEntidad> getCargaEntTodo() {
        return CargaEntTodo;
    }

    public void setCargaEntTodo(ArrayList<LogEntidad> CargaEntTodo) {
        this.CargaEntTodo = CargaEntTodo;
    }

    public ArrayList<LogEntidad> getCargaSucurTodo() {
        return CargaSucurTodo;
    }

    public void setCargaSucurTodo(ArrayList<LogEntidad> CargaSucurTodo) {
        this.CargaSucurTodo = CargaSucurTodo;
    }

    public ArrayList<LogEntidad> getCargaAsesTodo() {
        return CargaAsesTodo;
    }

    public void setCargaAsesTodo(ArrayList<LogEntidad> CargaAsesTodo) {
        this.CargaAsesTodo = CargaAsesTodo;
    }

    public String getEstadoRadioSeleccion() {
        return estadoRadioSeleccion;
    }

    public void setEstadoRadioSeleccion(String estadoRadioSeleccion) {
        this.estadoRadioSeleccion = estadoRadioSeleccion;
    }

    public boolean isEstPanelEntidad() {
        return estPanelEntidad;
    }

    public void setEstPanelEntidad(boolean estPanelEntidad) {
        this.estPanelEntidad = estPanelEntidad;
    }

    public boolean isEstPanelSucursal() {
        return estPanelSucursal;
    }

    public void setEstPanelSucursal(boolean estPanelSucursal) {
        this.estPanelSucursal = estPanelSucursal;
    }

    public boolean isEstPanelAsesor() {
        return estPanelAsesor;
    }

    public void setEstPanelAsesor(boolean estPanelAsesor) {
        this.estPanelAsesor = estPanelAsesor;
    }

    public ArrayList<SelectItem> getCargaCiu() {
        return CargaCiu;
    }

    public void setCargaCiu(ArrayList<SelectItem> CargaCiu) {
        this.CargaCiu = CargaCiu;
    }

    public ArrayList<SelectItem> getCargaRegimen() {
        return CargaRegimen;
    }

    public void setCargaRegimen(ArrayList<SelectItem> CargaRegimen) {
        this.CargaRegimen = CargaRegimen;
    }

    public String getTituloFormEntidad() {
        return tituloFormEntidad;
    }

    public void setTituloFormEntidad(String tituloFormEntidad) {
        this.tituloFormEntidad = tituloFormEntidad;
    }

    public String getTituloFormSucursal() {
        return tituloFormSucursal;
    }

    public void setTituloFormSucursal(String tituloFormSucursal) {
        this.tituloFormSucursal = tituloFormSucursal;
    }

    public String getTituloFormAsesor() {
        return tituloFormAsesor;
    }

    public void setTituloFormAsesor(String tituloFormAsesor) {
        this.tituloFormAsesor = tituloFormAsesor;
    }

    public boolean isEstadoRegistroEntidad() {
        return estadoRegistroEntidad;
    }

    public void setEstadoRegistroEntidad(boolean estadoRegistroEntidad) {
        this.estadoRegistroEntidad = estadoRegistroEntidad;
    }

    public boolean isEstadoRegistroSucursal() {
        return estadoRegistroSucursal;
    }

    public void setEstadoRegistroSucursal(boolean estadoRegistroSucursal) {
        this.estadoRegistroSucursal = estadoRegistroSucursal;
    }

    public boolean isEstadoRegistroAsesor() {
        return estadoRegistroAsesor;
    }

    public void setEstadoRegistroAsesor(boolean estadoRegistroAsesor) {
        this.estadoRegistroAsesor = estadoRegistroAsesor;
    }

    public boolean isEstadoActualizarEntidad() {
        return estadoActualizarEntidad;
    }

    public void setEstadoActualizarEntidad(boolean estadoActualizarEntidad) {
        this.estadoActualizarEntidad = estadoActualizarEntidad;
    }

    public boolean isEstadoActualizarSucursal() {
        return estadoActualizarSucursal;
    }

    public void setEstadoActualizarSucursal(boolean estadoActualizarSucursal) {
        this.estadoActualizarSucursal = estadoActualizarSucursal;
    }

    public boolean isEstadoActualizarAsesor() {
        return estadoActualizarAsesor;
    }

    public void setEstadoActualizarAsesor(boolean estadoActualizarAsesor) {
        this.estadoActualizarAsesor = estadoActualizarAsesor;
    }

    public ArrayList<SelectItem> getCargaSucurTodoCombo() {
        return CargaSucurTodoCombo;
    }

    public void setCargaSucurTodoCombo(ArrayList<SelectItem> CargaSucurTodoCombo) {
        this.CargaSucurTodoCombo = CargaSucurTodoCombo;
    }

    public int getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(int codSucursal) {
        this.codSucursal = codSucursal;
    }

    public int getCodAsesor() {
        return codAsesor;
    }

    public void setCodAsesor(int codAsesor) {
        this.codAsesor = codAsesor;
    }

    public String getOpcionMostrarEntidad() {
        return opcionMostrarEntidad;
    }

    public boolean getOpcionRadiosMostrarEntidad() {
        return opcionRadiosMostrarEntidad;
    }

    public void setOpcionRadiosMostrarEntidad(boolean opcionRadiosMostrarEntidad) {
        this.opcionRadiosMostrarEntidad = opcionRadiosMostrarEntidad;
    }

    public void setOpcionMostrarEntidad(String opcionMostrarEntidad) {
        this.opcionMostrarEntidad = opcionMostrarEntidad;
    }

    public int getCod_preRadic() {
        return cod_preRadic;
    }

    public void setCod_preRadic(int cod_preRadic) {
        this.cod_preRadic = cod_preRadic;
    }

    public int getCod_avaluo() {
        return cod_avaluo;
    }

    public void setCod_avaluo(int cod_avaluo) {
        this.cod_avaluo = cod_avaluo;
    }

    public String getCodEntidad1() {
        return codEntidad1;
    }

    public void setCodEntidad1(String codEntidad1) {
        this.codEntidad1 = codEntidad1;
    }

    public String getCodEntidad2() {
        return codEntidad2;
    }

    public void setCodEntidad2(String codEntidad2) {
        this.codEntidad2 = codEntidad2;
    }

    public String getCodEntidad3() {
        return codEntidad3;
    }

    public void setCodEntidad3(String codEntidad3) {
        this.codEntidad3 = codEntidad3;
    }

    public String getCodEntidad4() {
        return codEntidad4;
    }

    public void setCodEntidad4(String codEntidad4) {
        this.codEntidad4 = codEntidad4;
    }

    public String getCodEntidad5() {
        return codEntidad5;
    }

    public void setCodEntidad5(String codEntidad5) {
        this.codEntidad5 = codEntidad5;
    }

    public String getNombreEntidad1() {
        return nombreEntidad1;
    }

    public void setNombreEntidad1(String nombreEntidad1) {
        this.nombreEntidad1 = nombreEntidad1;
    }

    public String getNombreEntidad2() {
        return nombreEntidad2;
    }

    public void setNombreEntidad2(String nombreEntidad2) {
        this.nombreEntidad2 = nombreEntidad2;
    }

    public String getNombreEntidad3() {
        return nombreEntidad3;
    }

    public void setNombreEntidad3(String nombreEntidad3) {
        this.nombreEntidad3 = nombreEntidad3;
    }

    public String getNombreEntidad4() {
        return nombreEntidad4;
    }

    public void setNombreEntidad4(String nombreEntidad4) {
        this.nombreEntidad4 = nombreEntidad4;
    }

    public String getNombreEntidad5() {
        return nombreEntidad5;
    }

    public void setNombreEntidad5(String nombreEntidad5) {
        this.nombreEntidad5 = nombreEntidad5;
    }

    public String getDocumentoEntidad1() {
        return documentoEntidad1;
    }

    public void setDocumentoEntidad1(String documentoEntidad1) {
        this.documentoEntidad1 = documentoEntidad1;
    }

    public String getDocumentoEntidad2() {
        return documentoEntidad2;
    }

    public void setDocumentoEntidad2(String documentoEntidad2) {
        this.documentoEntidad2 = documentoEntidad2;
    }

    public String getDocumentoEntidad3() {
        return documentoEntidad3;
    }

    public void setDocumentoEntidad3(String documentoEntidad3) {
        this.documentoEntidad3 = documentoEntidad3;
    }

    public String getDocumentoEntidad4() {
        return documentoEntidad4;
    }

    public void setDocumentoEntidad4(String documentoEntidad4) {
        this.documentoEntidad4 = documentoEntidad4;
    }

    public String getDocumentoEntidad5() {
        return documentoEntidad5;
    }

    public void setDocumentoEntidad5(String documentoEntidad5) {
        this.documentoEntidad5 = documentoEntidad5;
    }

    public String getCodSucursal1() {
        return codSucursal1;
    }

    public void setCodSucursal1(String codSucursal1) {
        this.codSucursal1 = codSucursal1;
    }

    public String getCodSucursal2() {
        return codSucursal2;
    }

    public void setCodSucursal2(String codSucursal2) {
        this.codSucursal2 = codSucursal2;
    }

    public String getCodSucursal3() {
        return codSucursal3;
    }

    public void setCodSucursal3(String codSucursal3) {
        this.codSucursal3 = codSucursal3;
    }

    public String getCodSucursal4() {
        return codSucursal4;
    }

    public void setCodSucursal4(String codSucursal4) {
        this.codSucursal4 = codSucursal4;
    }

    public String getCodSucursal5() {
        return codSucursal5;
    }

    public void setCodSucursal5(String codSucursal5) {
        this.codSucursal5 = codSucursal5;
    }

    public String getNombreSucursal1() {
        return nombreSucursal1;
    }

    public void setNombreSucursal1(String nombreSucursal1) {
        this.nombreSucursal1 = nombreSucursal1;
    }

    public String getNombreSucursal2() {
        return nombreSucursal2;
    }

    public void setNombreSucursal2(String nombreSucursal2) {
        this.nombreSucursal2 = nombreSucursal2;
    }

    public String getNombreSucursal3() {
        return nombreSucursal3;
    }

    public void setNombreSucursal3(String nombreSucursal3) {
        this.nombreSucursal3 = nombreSucursal3;
    }

    public String getNombreSucursal4() {
        return nombreSucursal4;
    }

    public void setNombreSucursal4(String nombreSucursal4) {
        this.nombreSucursal4 = nombreSucursal4;
    }

    public String getNombreSucursal5() {
        return nombreSucursal5;
    }

    public void setNombreSucursal5(String nombreSucursal5) {
        this.nombreSucursal5 = nombreSucursal5;
    }

    public String getTelefonoSucursal1() {
        return telefonoSucursal1;
    }

    public void setTelefonoSucursal1(String telefonoSucursal1) {
        this.telefonoSucursal1 = telefonoSucursal1;
    }

    public String getTelefonoSucursal2() {
        return telefonoSucursal2;
    }

    public void setTelefonoSucursal2(String telefonoSucursal2) {
        this.telefonoSucursal2 = telefonoSucursal2;
    }

    public String getTelefonoSucursal3() {
        return telefonoSucursal3;
    }

    public void setTelefonoSucursal3(String telefonoSucursal3) {
        this.telefonoSucursal3 = telefonoSucursal3;
    }

    public String getTelefonoSucursal4() {
        return telefonoSucursal4;
    }

    public void setTelefonoSucursal4(String telefonoSucursal4) {
        this.telefonoSucursal4 = telefonoSucursal4;
    }

    public String getTelefonoSucursal5() {
        return telefonoSucursal5;
    }

    public void setTelefonoSucursal5(String telefonoSucursal5) {
        this.telefonoSucursal5 = telefonoSucursal5;
    }

    public String getCodAsesor1() {
        return codAsesor1;
    }

    public void setCodAsesor1(String codAsesor1) {
        this.codAsesor1 = codAsesor1;
    }

    public String getCodAsesor2() {
        return codAsesor2;
    }

    public void setCodAsesor2(String codAsesor2) {
        this.codAsesor2 = codAsesor2;
    }

    public String getCodAsesor3() {
        return codAsesor3;
    }

    public void setCodAsesor3(String codAsesor3) {
        this.codAsesor3 = codAsesor3;
    }

    public String getCodAsesor4() {
        return codAsesor4;
    }

    public void setCodAsesor4(String codAsesor4) {
        this.codAsesor4 = codAsesor4;
    }

    public String getCodAsesor5() {
        return codAsesor5;
    }

    public void setCodAsesor5(String codAsesor5) {
        this.codAsesor5 = codAsesor5;
    }

    public String getNombreAsesor1() {
        return nombreAsesor1;
    }

    public void setNombreAsesor1(String nombreAsesor1) {
        this.nombreAsesor1 = nombreAsesor1;
    }

    public String getNombreAsesor2() {
        return nombreAsesor2;
    }

    public void setNombreAsesor2(String nombreAsesor2) {
        this.nombreAsesor2 = nombreAsesor2;
    }

    public String getNombreAsesor3() {
        return nombreAsesor3;
    }

    public void setNombreAsesor3(String nombreAsesor3) {
        this.nombreAsesor3 = nombreAsesor3;
    }

    public String getNombreAsesor4() {
        return nombreAsesor4;
    }

    public void setNombreAsesor4(String nombreAsesor4) {
        this.nombreAsesor4 = nombreAsesor4;
    }

    public String getNombreAsesor5() {
        return nombreAsesor5;
    }

    public void setNombreAsesor5(String nombreAsesor5) {
        this.nombreAsesor5 = nombreAsesor5;
    }

    public String getCargoAsesor1() {
        return cargoAsesor1;
    }

    public void setCargoAsesor1(String cargoAsesor1) {
        this.cargoAsesor1 = cargoAsesor1;
    }

    public String getCargoAsesor2() {
        return cargoAsesor2;
    }

    public void setCargoAsesor2(String cargoAsesor2) {
        this.cargoAsesor2 = cargoAsesor2;
    }

    public String getCargoAsesor3() {
        return cargoAsesor3;
    }

    public void setCargoAsesor3(String cargoAsesor3) {
        this.cargoAsesor3 = cargoAsesor3;
    }

    public String getCargoAsesor4() {
        return cargoAsesor4;
    }

    public void setCargoAsesor4(String cargoAsesor4) {
        this.cargoAsesor4 = cargoAsesor4;
    }

    public String getCargoAsesor5() {
        return cargoAsesor5;
    }

    public void setCargoAsesor5(String cargoAsesor5) {
        this.cargoAsesor5 = cargoAsesor5;
    }

    public String getMailAsesor1() {
        return mailAsesor1;
    }

    public void setMailAsesor1(String mailAsesor1) {
        this.mailAsesor1 = mailAsesor1;
    }

    public String getMailAsesor2() {
        return mailAsesor2;
    }

    public void setMailAsesor2(String mailAsesor2) {
        this.mailAsesor2 = mailAsesor2;
    }

    public String getMailAsesor3() {
        return mailAsesor3;
    }

    public void setMailAsesor3(String mailAsesor3) {
        this.mailAsesor3 = mailAsesor3;
    }

    public String getMailAsesor4() {
        return mailAsesor4;
    }

    public void setMailAsesor4(String mailAsesor4) {
        this.mailAsesor4 = mailAsesor4;
    }

    public String getMailAsesor5() {
        return mailAsesor5;
    }

    public void setMailAsesor5(String mailAsesor5) {
        this.mailAsesor5 = mailAsesor5;
    }

    public String getTelefonoAsesor1() {
        return telefonoAsesor1;
    }

    public void setTelefonoAsesor1(String telefonoAsesor1) {
        this.telefonoAsesor1 = telefonoAsesor1;
    }

    public String getTelefonoAsesor2() {
        return telefonoAsesor2;
    }

    public void setTelefonoAsesor2(String telefonoAsesor2) {
        this.telefonoAsesor2 = telefonoAsesor2;
    }

    public String getTelefonoAsesor3() {
        return telefonoAsesor3;
    }

    public void setTelefonoAsesor3(String telefonoAsesor3) {
        this.telefonoAsesor3 = telefonoAsesor3;
    }

    public String getTelefonoAsesor4() {
        return telefonoAsesor4;
    }

    public void setTelefonoAsesor4(String telefonoAsesor4) {
        this.telefonoAsesor4 = telefonoAsesor4;
    }

    public String getTelefonoAsesor5() {
        return telefonoAsesor5;
    }

    public void setTelefonoAsesor5(String telefonoAsesor5) {
        this.telefonoAsesor5 = telefonoAsesor5;
    }

    public boolean isEstadoInfoAsesor1() {
        return estadoInfoAsesor1;
    }

    public void setEstadoInfoAsesor1(boolean estadoInfoAsesor1) {
        this.estadoInfoAsesor1 = estadoInfoAsesor1;
    }

    public boolean isEstadoInfoAsesor2() {
        return estadoInfoAsesor2;
    }

    public void setEstadoInfoAsesor2(boolean estadoInfoAsesor2) {
        this.estadoInfoAsesor2 = estadoInfoAsesor2;
    }

    public boolean isEstadoInfoAsesor3() {
        return estadoInfoAsesor3;
    }

    public void setEstadoInfoAsesor3(boolean estadoInfoAsesor3) {
        this.estadoInfoAsesor3 = estadoInfoAsesor3;
    }

    public boolean isEstadoInfoAsesor4() {
        return estadoInfoAsesor4;
    }

    public void setEstadoInfoAsesor4(boolean estadoInfoAsesor4) {
        this.estadoInfoAsesor4 = estadoInfoAsesor4;
    }

    public boolean isEstadoInfoAsesor5() {
        return estadoInfoAsesor5;
    }

    public void setEstadoInfoAsesor5(boolean estadoInfoAsesor5) {
        this.estadoInfoAsesor5 = estadoInfoAsesor5;
    }

    public String getCodEntidadfactu1() {
        return codEntidadfactu1;
    }

    public void setCodEntidadfactu1(String codEntidadfactu1) {
        this.codEntidadfactu1 = codEntidadfactu1;
    }

    public String getCodEntidadfactu2() {
        return codEntidadfactu2;
    }

    public void setCodEntidadfactu2(String codEntidadfactu2) {
        this.codEntidadfactu2 = codEntidadfactu2;
    }

    public String getCodEntidadfactu3() {
        return codEntidadfactu3;
    }

    public void setCodEntidadfactu3(String codEntidadfactu3) {
        this.codEntidadfactu3 = codEntidadfactu3;
    }

    public String getCodEntidadfactu4() {
        return codEntidadfactu4;
    }

    public void setCodEntidadfactu4(String codEntidadfactu4) {
        this.codEntidadfactu4 = codEntidadfactu4;
    }

    public String getCodEntidadfactu5() {
        return codEntidadfactu5;
    }

    public void setCodEntidadfactu5(String codEntidadfactu5) {
        this.codEntidadfactu5 = codEntidadfactu5;
    }

    public String getNombreEntidadfactu1() {
        return nombreEntidadfactu1;
    }

    public void setNombreEntidadfactu1(String nombreEntidadfactu1) {
        this.nombreEntidadfactu1 = nombreEntidadfactu1;
    }

    public String getNombreEntidadfactu2() {
        return nombreEntidadfactu2;
    }

    public void setNombreEntidadfactu2(String nombreEntidadfactu2) {
        this.nombreEntidadfactu2 = nombreEntidadfactu2;
    }

    public String getNombreEntidadfactu3() {
        return nombreEntidadfactu3;
    }

    public void setNombreEntidadfactu3(String nombreEntidadfactu3) {
        this.nombreEntidadfactu3 = nombreEntidadfactu3;
    }

    public String getNombreEntidadfactu4() {
        return nombreEntidadfactu4;
    }

    public void setNombreEntidadfactu4(String nombreEntidadfactu4) {
        this.nombreEntidadfactu4 = nombreEntidadfactu4;
    }

    public String getNombreEntidadfactu5() {
        return nombreEntidadfactu5;
    }

    public void setNombreEntidadfactu5(String nombreEntidadfactu5) {
        this.nombreEntidadfactu5 = nombreEntidadfactu5;
    }

    public String getDocumentoEntidadfactu1() {
        return documentoEntidadfactu1;
    }

    public void setDocumentoEntidadfactu1(String documentoEntidadfactu1) {
        this.documentoEntidadfactu1 = documentoEntidadfactu1;
    }

    public String getDocumentoEntidadfactu2() {
        return documentoEntidadfactu2;
    }

    public void setDocumentoEntidadfactu2(String documentoEntidadfactu2) {
        this.documentoEntidadfactu2 = documentoEntidadfactu2;
    }

    public String getDocumentoEntidadfactu3() {
        return documentoEntidadfactu3;
    }

    public void setDocumentoEntidadfactu3(String documentoEntidadfactu3) {
        this.documentoEntidadfactu3 = documentoEntidadfactu3;
    }

    public String getDocumentoEntidadfactu4() {
        return documentoEntidadfactu4;
    }

    public void setDocumentoEntidadfactu4(String documentoEntidadfactu4) {
        this.documentoEntidadfactu4 = documentoEntidadfactu4;
    }

    public String getDocumentoEntidadfactu5() {
        return documentoEntidadfactu5;
    }

    public void setDocumentoEntidadfactu5(String documentoEntidadfactu5) {
        this.documentoEntidadfactu5 = documentoEntidadfactu5;
    }

    public String getCodSucursalfactu1() {
        return codSucursalfactu1;
    }

    public void setCodSucursalfactu1(String codSucursalfactu1) {
        this.codSucursalfactu1 = codSucursalfactu1;
    }

    public String getCodSucursalfactu2() {
        return codSucursalfactu2;
    }

    public void setCodSucursalfactu2(String codSucursalfactu2) {
        this.codSucursalfactu2 = codSucursalfactu2;
    }

    public String getCodSucursalfactu3() {
        return codSucursalfactu3;
    }

    public void setCodSucursalfactu3(String codSucursalfactu3) {
        this.codSucursalfactu3 = codSucursalfactu3;
    }

    public String getCodSucursalfactu4() {
        return codSucursalfactu4;
    }

    public void setCodSucursalfactu4(String codSucursalfactu4) {
        this.codSucursalfactu4 = codSucursalfactu4;
    }

    public String getCodSucursalfactu5() {
        return codSucursalfactu5;
    }

    public void setCodSucursalfactu5(String codSucursalfactu5) {
        this.codSucursalfactu5 = codSucursalfactu5;
    }

    public String getNombreSucursalfactu1() {
        return nombreSucursalfactu1;
    }

    public void setNombreSucursalfactu1(String nombreSucursalfactu1) {
        this.nombreSucursalfactu1 = nombreSucursalfactu1;
    }

    public String getNombreSucursalfactu2() {
        return nombreSucursalfactu2;
    }

    public void setNombreSucursalfactu2(String nombreSucursalfactu2) {
        this.nombreSucursalfactu2 = nombreSucursalfactu2;
    }

    public String getNombreSucursalfactu3() {
        return nombreSucursalfactu3;
    }

    public void setNombreSucursalfactu3(String nombreSucursalfactu3) {
        this.nombreSucursalfactu3 = nombreSucursalfactu3;
    }

    public String getNombreSucursalfactu4() {
        return nombreSucursalfactu4;
    }

    public void setNombreSucursalfactu4(String nombreSucursalfactu4) {
        this.nombreSucursalfactu4 = nombreSucursalfactu4;
    }

    public String getNombreSucursalfactu5() {
        return nombreSucursalfactu5;
    }

    public void setNombreSucursalfactu5(String nombreSucursalfactu5) {
        this.nombreSucursalfactu5 = nombreSucursalfactu5;
    }

    public String getTelefonoSucursalfactu1() {
        return telefonoSucursalfactu1;
    }

    public void setTelefonoSucursalfactu1(String telefonoSucursalfactu1) {
        this.telefonoSucursalfactu1 = telefonoSucursalfactu1;
    }

    public String getTelefonoSucursalfactu2() {
        return telefonoSucursalfactu2;
    }

    public void setTelefonoSucursalfactu2(String telefonoSucursalfactu2) {
        this.telefonoSucursalfactu2 = telefonoSucursalfactu2;
    }

    public String getTelefonoSucursalfactu3() {
        return telefonoSucursalfactu3;
    }

    public void setTelefonoSucursalfactu3(String telefonoSucursalfactu3) {
        this.telefonoSucursalfactu3 = telefonoSucursalfactu3;
    }

    public String getTelefonoSucursalfactu4() {
        return telefonoSucursalfactu4;
    }

    public void setTelefonoSucursalfactu4(String telefonoSucursalfactu4) {
        this.telefonoSucursalfactu4 = telefonoSucursalfactu4;
    }

    public String getTelefonoSucursalfactu5() {
        return telefonoSucursalfactu5;
    }

    public void setTelefonoSucursalfactu5(String telefonoSucursalfactu5) {
        this.telefonoSucursalfactu5 = telefonoSucursalfactu5;
    }

    public String getCodAsesorfactu1() {
        return codAsesorfactu1;
    }

    public void setCodAsesorfactu1(String codAsesorfactu1) {
        this.codAsesorfactu1 = codAsesorfactu1;
    }

    public String getCodAsesorfactu2() {
        return codAsesorfactu2;
    }

    public void setCodAsesorfactu2(String codAsesorfactu2) {
        this.codAsesorfactu2 = codAsesorfactu2;
    }

    public String getCodAsesorfactu3() {
        return codAsesorfactu3;
    }

    public void setCodAsesorfactu3(String codAsesorfactu3) {
        this.codAsesorfactu3 = codAsesorfactu3;
    }

    public String getCodAsesorfactu4() {
        return codAsesorfactu4;
    }

    public void setCodAsesorfactu4(String codAsesorfactu4) {
        this.codAsesorfactu4 = codAsesorfactu4;
    }

    public String getCodAsesorfactu5() {
        return codAsesorfactu5;
    }

    public void setCodAsesorfactu5(String codAsesorfactu5) {
        this.codAsesorfactu5 = codAsesorfactu5;
    }

    public String getNombreAsesorfactu1() {
        return nombreAsesorfactu1;
    }

    public void setNombreAsesorfactu1(String nombreAsesorfactu1) {
        this.nombreAsesorfactu1 = nombreAsesorfactu1;
    }

    public String getNombreAsesorfactu2() {
        return nombreAsesorfactu2;
    }

    public void setNombreAsesorfactu2(String nombreAsesorfactu2) {
        this.nombreAsesorfactu2 = nombreAsesorfactu2;
    }

    public String getNombreAsesorfactu3() {
        return nombreAsesorfactu3;
    }

    public void setNombreAsesorfactu3(String nombreAsesorfactu3) {
        this.nombreAsesorfactu3 = nombreAsesorfactu3;
    }

    public String getNombreAsesorfactu4() {
        return nombreAsesorfactu4;
    }

    public void setNombreAsesorfactu4(String nombreAsesorfactu4) {
        this.nombreAsesorfactu4 = nombreAsesorfactu4;
    }

    public String getNombreAsesorfactu5() {
        return nombreAsesorfactu5;
    }

    public void setNombreAsesorfactu5(String nombreAsesorfactu5) {
        this.nombreAsesorfactu5 = nombreAsesorfactu5;
    }

    public String getCargoAsesorfactu1() {
        return cargoAsesorfactu1;
    }

    public void setCargoAsesorfactu1(String cargoAsesorfactu1) {
        this.cargoAsesorfactu1 = cargoAsesorfactu1;
    }

    public String getCargoAsesorfactu2() {
        return cargoAsesorfactu2;
    }

    public void setCargoAsesorfactu2(String cargoAsesorfactu2) {
        this.cargoAsesorfactu2 = cargoAsesorfactu2;
    }

    public String getCargoAsesorfactu3() {
        return cargoAsesorfactu3;
    }

    public void setCargoAsesorfactu3(String cargoAsesorfactu3) {
        this.cargoAsesorfactu3 = cargoAsesorfactu3;
    }

    public String getCargoAsesorfactu4() {
        return cargoAsesorfactu4;
    }

    public void setCargoAsesorfactu4(String cargoAsesorfactu4) {
        this.cargoAsesorfactu4 = cargoAsesorfactu4;
    }

    public String getCargoAsesorfactu5() {
        return cargoAsesorfactu5;
    }

    public void setCargoAsesorfactu5(String cargoAsesorfactu5) {
        this.cargoAsesorfactu5 = cargoAsesorfactu5;
    }

    public ArrayList<SelectItem> getCargaSucur1() {
        return CargaSucur1;
    }

    public void setCargaSucur1(ArrayList<SelectItem> CargaSucur1) {
        this.CargaSucur1 = CargaSucur1;
    }

    public ArrayList<SelectItem> getCargaSucur2() {
        return CargaSucur2;
    }

    public void setCargaSucur2(ArrayList<SelectItem> CargaSucur2) {
        this.CargaSucur2 = CargaSucur2;
    }

    public ArrayList<SelectItem> getCargaSucur3() {
        return CargaSucur3;
    }

    public void setCargaSucur3(ArrayList<SelectItem> CargaSucur3) {
        this.CargaSucur3 = CargaSucur3;
    }

    public ArrayList<SelectItem> getCargaSucur4() {
        return CargaSucur4;
    }

    public void setCargaSucur4(ArrayList<SelectItem> CargaSucur4) {
        this.CargaSucur4 = CargaSucur4;
    }

    public ArrayList<SelectItem> getCargaSucur5() {
        return CargaSucur5;
    }

    public void setCargaSucur5(ArrayList<SelectItem> CargaSucur5) {
        this.CargaSucur5 = CargaSucur5;
    }

    public ArrayList<SelectItem> getCargaAses1() {
        return CargaAses1;
    }

    public void setCargaAses1(ArrayList<SelectItem> CargaAses1) {
        this.CargaAses1 = CargaAses1;
    }

    public ArrayList<SelectItem> getCargaAses2() {
        return CargaAses2;
    }

    public void setCargaAses2(ArrayList<SelectItem> CargaAses2) {
        this.CargaAses2 = CargaAses2;
    }

    public ArrayList<SelectItem> getCargaAses3() {
        return CargaAses3;
    }

    public void setCargaAses3(ArrayList<SelectItem> CargaAses3) {
        this.CargaAses3 = CargaAses3;
    }

    public ArrayList<SelectItem> getCargaAses4() {
        return CargaAses4;
    }

    public void setCargaAses4(ArrayList<SelectItem> CargaAses4) {
        this.CargaAses4 = CargaAses4;
    }

    public ArrayList<SelectItem> getCargaAses5() {
        return CargaAses5;
    }

    public void setCargaAses5(ArrayList<SelectItem> CargaAses5) {
        this.CargaAses5 = CargaAses5;
    }

    public boolean isEstadoAgregarFilasEntidad1() {
        return estadoAgregarFilasEntidad1;
    }

    public void setEstadoAgregarFilasEntidad1(boolean estadoAgregarFilasEntidad1) {
        this.estadoAgregarFilasEntidad1 = estadoAgregarFilasEntidad1;
    }

    public boolean isEstadoAgregarFilasEntidad2() {
        return estadoAgregarFilasEntidad2;
    }

    public void setEstadoAgregarFilasEntidad2(boolean estadoAgregarFilasEntidad2) {
        this.estadoAgregarFilasEntidad2 = estadoAgregarFilasEntidad2;
    }

    public boolean isEstadoAgregarFilasEntidad3() {
        return estadoAgregarFilasEntidad3;
    }

    public void setEstadoAgregarFilasEntidad3(boolean estadoAgregarFilasEntidad3) {
        this.estadoAgregarFilasEntidad3 = estadoAgregarFilasEntidad3;
    }

    public boolean isEstadoAgregarFilasEntidad4() {
        return estadoAgregarFilasEntidad4;
    }

    public void setEstadoAgregarFilasEntidad4(boolean estadoAgregarFilasEntidad4) {
        this.estadoAgregarFilasEntidad4 = estadoAgregarFilasEntidad4;
    }

    public boolean isEstadoAgregarFilasEntidad5() {
        return estadoAgregarFilasEntidad5;
    }

    public void setEstadoAgregarFilasEntidad5(boolean estadoAgregarFilasEntidad5) {
        this.estadoAgregarFilasEntidad5 = estadoAgregarFilasEntidad5;
    }

    public int getNum_entidad() {
        return num_entidad;
    }

    public void setNum_entidad(int num_entidad) {
        this.num_entidad = num_entidad;
    }

    public boolean isEstadoVisibleEnti1() {
        return estadoVisibleEnti1;
    }

    public void setEstadoVisibleEnti1(boolean estadoVisibleEnti1) {
        this.estadoVisibleEnti1 = estadoVisibleEnti1;
    }

    public boolean isEstadoVisibleEnti2() {
        return estadoVisibleEnti2;
    }

    public void setEstadoVisibleEnti2(boolean estadoVisibleEnti2) {
        this.estadoVisibleEnti2 = estadoVisibleEnti2;
    }

    public boolean isEstadoVisibleEnti3() {
        return estadoVisibleEnti3;
    }

    public void setEstadoVisibleEnti3(boolean estadoVisibleEnti3) {
        this.estadoVisibleEnti3 = estadoVisibleEnti3;
    }

    public boolean isEstadoVisibleEnti4() {
        return estadoVisibleEnti4;
    }

    public void setEstadoVisibleEnti4(boolean estadoVisibleEnti4) {
        this.estadoVisibleEnti4 = estadoVisibleEnti4;
    }

    public boolean isEstadoVisibleEnti5() {
        return estadoVisibleEnti5;
    }

    public void setEstadoVisibleEnti5(boolean estadoVisibleEnti5) {
        this.estadoVisibleEnti5 = estadoVisibleEnti5;
    }

    //Tarifas pactadas de la entidad
    public boolean isEntidadFact1() {
        return entidadFact1;
    }

    public void setEntidadFact1(boolean entidadFact1) {
        this.entidadFact1 = entidadFact1;
    }

    public boolean isEntidadFact2() {
        return entidadFact2;
    }

    public void setEntidadFact2(boolean entidadFact2) {
        this.entidadFact2 = entidadFact2;
    }

    public boolean isEntidadFact3() {
        return entidadFact3;
    }

    public void setEntidadFact3(boolean entidadFact3) {
        this.entidadFact3 = entidadFact3;
    }

    public boolean isEntidadFact4() {
        return entidadFact4;
    }

    public void setEntidadFact4(boolean entidadFact4) {
        this.entidadFact4 = entidadFact4;
    }

    public boolean isEntidadFact5() {
        return entidadFact5;
    }

    public void setEntidadFact5(boolean entidadFact5) {
        this.entidadFact5 = entidadFact5;
    }

    public boolean isDeshabilitarAsesor1() {
        return DeshabilitarAsesor1;
    }

    public void setDeshabilitarAsesor1(boolean DeshabilitarAsesor1) {
        this.DeshabilitarAsesor1 = DeshabilitarAsesor1;
    }

    public boolean isDeshabilitarElimia1() {
        return DeshabilitarElimia1;
    }

    public void setDeshabilitarElimia1(boolean DeshabilitarElimia1) {
        this.DeshabilitarElimia1 = DeshabilitarElimia1;
    }

    public boolean isDeshabilitarAsesor2() {
        return DeshabilitarAsesor2;
    }

    public void setDeshabilitarAsesor2(boolean DeshabilitarAsesor2) {
        this.DeshabilitarAsesor2 = DeshabilitarAsesor2;
    }

    public boolean isDeshabilitarAsesor3() {
        return DeshabilitarAsesor3;
    }

    public void setDeshabilitarAsesor3(boolean DeshabilitarAsesor3) {
        this.DeshabilitarAsesor3 = DeshabilitarAsesor3;
    }

    public boolean isDeshabilitarAsesor4() {
        return DeshabilitarAsesor4;
    }

    public void setDeshabilitarAsesor4(boolean DeshabilitarAsesor4) {
        this.DeshabilitarAsesor4 = DeshabilitarAsesor4;
    }

    public boolean isDeshabilitarAsesor5() {
        return DeshabilitarAsesor5;
    }

    public void setDeshabilitarAsesor5(boolean DeshabilitarAsesor5) {
        this.DeshabilitarAsesor5 = DeshabilitarAsesor5;
    }

    public boolean isEditarFila1() {
        return EditarFila1;
    }

    public void setEditarFila1(boolean EditarFila1) {
        this.EditarFila1 = EditarFila1;
    }

    public boolean isEditarFila2() {
        return EditarFila2;
    }

    public void setEditarFila2(boolean EditarFila2) {
        this.EditarFila2 = EditarFila2;
    }

    public boolean isEditarFila3() {
        return EditarFila3;
    }

    public void setEditarFila3(boolean EditarFila3) {
        this.EditarFila3 = EditarFila3;
    }

    public boolean isEditarFila4() {
        return EditarFila4;
    }

    public void setEditarFila4(boolean EditarFila4) {
        this.EditarFila4 = EditarFila4;
    }

    public boolean isEditarFila5() {
        return EditarFila5;
    }

    public void setEditarFila5(boolean EditarFila5) {
        this.EditarFila5 = EditarFila5;
    }

    public boolean isAgregarFila1() {
        return AgregarFila1;
    }

    public void setAgregarFila1(boolean AgregarFila1) {
        this.AgregarFila1 = AgregarFila1;
    }

    public boolean isAgregarFila2() {
        return AgregarFila2;
    }

    public void setAgregarFila2(boolean AgregarFila2) {
        this.AgregarFila2 = AgregarFila2;
    }

    public boolean isAgregarFila3() {
        return AgregarFila3;
    }

    public void setAgregarFila3(boolean AgregarFila3) {
        this.AgregarFila3 = AgregarFila3;
    }

    public boolean isAgregarFila4() {
        return AgregarFila4;
    }

    public void setAgregarFila4(boolean AgregarFila4) {
        this.AgregarFila4 = AgregarFila4;
    }

    public boolean isQuitarFila1() {
        return QuitarFila1;
    }

    public void setQuitarFila1(boolean QuitarFila1) {
        this.QuitarFila1 = QuitarFila1;
    }

    public boolean isQuitarFila2() {
        return QuitarFila2;
    }

    public void setQuitarFila2(boolean QuitarFila2) {
        this.QuitarFila2 = QuitarFila2;
    }

    public boolean isQuitarFila3() {
        return QuitarFila3;
    }

    public void setQuitarFila3(boolean QuitarFila3) {
        this.QuitarFila3 = QuitarFila3;
    }

    public boolean isQuitarFila4() {
        return QuitarFila4;
    }

    public void setQuitarFila4(boolean QuitarFila4) {
        this.QuitarFila4 = QuitarFila4;
    }

    public boolean isQuitarFila5() {
        return QuitarFila5;
    }

    public void setQuitarFila5(boolean QuitarFila5) {
        this.QuitarFila5 = QuitarFila5;
    }

    public boolean isAceptarFila1() {
        return AceptarFila1;
    }

    public void setAceptarFila1(boolean AceptarFila1) {
        this.AceptarFila1 = AceptarFila1;
    }

    public boolean isAceptarFila2() {
        return AceptarFila2;
    }

    public void setAceptarFila2(boolean AceptarFila2) {
        this.AceptarFila2 = AceptarFila2;
    }

    public boolean isAceptarFila3() {
        return AceptarFila3;
    }

    public void setAceptarFila3(boolean AceptarFila3) {
        this.AceptarFila3 = AceptarFila3;
    }

    public boolean isAceptarFila4() {
        return AceptarFila4;
    }

    public void setAceptarFila4(boolean AceptarFila4) {
        this.AceptarFila4 = AceptarFila4;
    }

    public boolean isAceptarFila5() {
        return AceptarFila5;
    }

    public void setAceptarFila5(boolean AceptarFila5) {
        this.AceptarFila5 = AceptarFila5;
    }

    public boolean isEstadoAgregarTarifasPactEnti1() {
        return estadoAgregarTarifasPactEnti1;
    }

    public void setEstadoAgregarTarifasPactEnti1(boolean estadoAgregarTarifasPactEnti1) {
        this.estadoAgregarTarifasPactEnti1 = estadoAgregarTarifasPactEnti1;
    }

    public boolean isEstadoAgregarTarifasPactEnti2() {
        return estadoAgregarTarifasPactEnti2;
    }

    public void setEstadoAgregarTarifasPactEnti2(boolean estadoAgregarTarifasPactEnti2) {
        this.estadoAgregarTarifasPactEnti2 = estadoAgregarTarifasPactEnti2;
    }

    public boolean isEstadoAgregarTarifasPactEnti3() {
        return estadoAgregarTarifasPactEnti3;
    }

    public void setEstadoAgregarTarifasPactEnti3(boolean estadoAgregarTarifasPactEnti3) {
        this.estadoAgregarTarifasPactEnti3 = estadoAgregarTarifasPactEnti3;
    }

    public boolean isEstadoAgregarTarifasPactEnti4() {
        return estadoAgregarTarifasPactEnti4;
    }

    public void setEstadoAgregarTarifasPactEnti4(boolean estadoAgregarTarifasPactEnti4) {
        this.estadoAgregarTarifasPactEnti4 = estadoAgregarTarifasPactEnti4;
    }

    public boolean isEstadoAgregarTarifasPactEnti5() {
        return estadoAgregarTarifasPactEnti5;
    }

    public void setEstadoAgregarTarifasPactEnti5(boolean estadoAgregarTarifasPactEnti5) {
        this.estadoAgregarTarifasPactEnti5 = estadoAgregarTarifasPactEnti5;
    }

    public String getOpcionRadioTarifasPactadosEnti1() {
        return opcionRadioTarifasPactadosEnti1;
    }

    public void setOpcionRadioTarifasPactadosEnti1(String opcionRadioTarifasPactadosEnti1) {
        this.opcionRadioTarifasPactadosEnti1 = opcionRadioTarifasPactadosEnti1;
    }

    public String getOpcionRadioTarifasPactadosEnti2() {
        return opcionRadioTarifasPactadosEnti2;
    }

    public void setOpcionRadioTarifasPactadosEnti2(String opcionRadioTarifasPactadosEnti2) {
        this.opcionRadioTarifasPactadosEnti2 = opcionRadioTarifasPactadosEnti2;
    }

    public String getOpcionRadioTarifasPactadosEnti3() {
        return opcionRadioTarifasPactadosEnti3;
    }

    public void setOpcionRadioTarifasPactadosEnti3(String opcionRadioTarifasPactadosEnti3) {
        this.opcionRadioTarifasPactadosEnti3 = opcionRadioTarifasPactadosEnti3;
    }

    public String getOpcionRadioTarifasPactadosEnti4() {
        return opcionRadioTarifasPactadosEnti4;
    }

    public void setOpcionRadioTarifasPactadosEnti4(String opcionRadioTarifasPactadosEnti4) {
        this.opcionRadioTarifasPactadosEnti4 = opcionRadioTarifasPactadosEnti4;
    }

    public String getOpcionRadioTarifasPactadosEnti5() {
        return opcionRadioTarifasPactadosEnti5;
    }

    public void setOpcionRadioTarifasPactadosEnti5(String opcionRadioTarifasPactadosEnti5) {
        this.opcionRadioTarifasPactadosEnti5 = opcionRadioTarifasPactadosEnti5;
    }

    public boolean isEstadoRadioAgregarTarifaEnti1() {
        return estadoRadioAgregarTarifaEnti1;
    }

    public void setEstadoRadioAgregarTarifaEnti1(boolean estadoRadioAgregarTarifaEnti1) {
        this.estadoRadioAgregarTarifaEnti1 = estadoRadioAgregarTarifaEnti1;
    }

    public boolean isEstadoRadioAgregarTarifaEnti2() {
        return estadoRadioAgregarTarifaEnti2;
    }

    public void setEstadoRadioAgregarTarifaEnti2(boolean estadoRadioAgregarTarifaEnti2) {
        this.estadoRadioAgregarTarifaEnti2 = estadoRadioAgregarTarifaEnti2;
    }

    public boolean isEstadoRadioAgregarTarifaEnti3() {
        return estadoRadioAgregarTarifaEnti3;
    }

    public void setEstadoRadioAgregarTarifaEnti3(boolean estadoRadioAgregarTarifaEnti3) {
        this.estadoRadioAgregarTarifaEnti3 = estadoRadioAgregarTarifaEnti3;
    }

    public boolean isEstadoRadioAgregarTarifaEnti4() {
        return estadoRadioAgregarTarifaEnti4;
    }

    public void setEstadoRadioAgregarTarifaEnti4(boolean estadoRadioAgregarTarifaEnti4) {
        this.estadoRadioAgregarTarifaEnti4 = estadoRadioAgregarTarifaEnti4;
    }

    public boolean isEstadoRadioAgregarTarifaEnti5() {
        return estadoRadioAgregarTarifaEnti5;
    }

    public void setEstadoRadioAgregarTarifaEnti5(boolean estadoRadioAgregarTarifaEnti5) {
        this.estadoRadioAgregarTarifaEnti5 = estadoRadioAgregarTarifaEnti5;
    }

    public boolean isEstadoTarifasPactEnti1() {
        return estadoTarifasPactEnti1;
    }

    public void setEstadoTarifasPactEnti1(boolean estadoTarifasPactEnti1) {
        this.estadoTarifasPactEnti1 = estadoTarifasPactEnti1;
    }

    public boolean isEstadoTarifasPactEnti2() {
        return estadoTarifasPactEnti2;
    }

    public void setEstadoTarifasPactEnti2(boolean estadoTarifasPactEnti2) {
        this.estadoTarifasPactEnti2 = estadoTarifasPactEnti2;
    }

    public boolean isEstadoTarifasPactEnti3() {
        return estadoTarifasPactEnti3;
    }

    public void setEstadoTarifasPactEnti3(boolean estadoTarifasPactEnti3) {
        this.estadoTarifasPactEnti3 = estadoTarifasPactEnti3;
    }

    public boolean isEstadoTarifasPactEnti4() {
        return estadoTarifasPactEnti4;
    }

    public void setEstadoTarifasPactEnti4(boolean estadoTarifasPactEnti4) {
        this.estadoTarifasPactEnti4 = estadoTarifasPactEnti4;
    }

    public boolean isEstadoTarifasPactEnti5() {
        return estadoTarifasPactEnti5;
    }

    public void setEstadoTarifasPactEnti5(boolean estadoTarifasPactEnti5) {
        this.estadoTarifasPactEnti5 = estadoTarifasPactEnti5;
    }

    public String getTipoTarifaEnti1() {
        return tipoTarifaEnti1;
    }

    public void setTipoTarifaEnti1(String tipoTarifaEnti1) {
        this.tipoTarifaEnti1 = tipoTarifaEnti1;
    }

    public String getTipoTarifaEnti2() {
        return tipoTarifaEnti2;
    }

    public void setTipoTarifaEnti2(String tipoTarifaEnti2) {
        this.tipoTarifaEnti2 = tipoTarifaEnti2;
    }

    public String getTipoTarifaEnti3() {
        return tipoTarifaEnti3;
    }

    public void setTipoTarifaEnti3(String tipoTarifaEnti3) {
        this.tipoTarifaEnti3 = tipoTarifaEnti3;
    }

    public String getTipoTarifaEnti4() {
        return tipoTarifaEnti4;
    }

    public void setTipoTarifaEnti4(String tipoTarifaEnti4) {
        this.tipoTarifaEnti4 = tipoTarifaEnti4;
    }

    public String getTipoTarifaEnti5() {
        return tipoTarifaEnti5;
    }

    public void setTipoTarifaEnti5(String tipoTarifaEnti5) {
        this.tipoTarifaEnti5 = tipoTarifaEnti5;
    }

    public String getValorTarifaEnti1() {
        return valorTarifaEnti1;
    }

    public void setValorTarifaEnti1(String valorTarifaEnti1) {
        this.valorTarifaEnti1 = valorTarifaEnti1;
    }

    public String getValorTarifaEnti2() {
        return valorTarifaEnti2;
    }

    public void setValorTarifaEnti2(String valorTarifaEnti2) {
        this.valorTarifaEnti2 = valorTarifaEnti2;
    }

    public String getValorTarifaEnti3() {
        return valorTarifaEnti3;
    }

    public void setValorTarifaEnti3(String valorTarifaEnti3) {
        this.valorTarifaEnti3 = valorTarifaEnti3;
    }

    public String getValorTarifaEnti4() {
        return valorTarifaEnti4;
    }

    public void setValorTarifaEnti4(String valorTarifaEnti4) {
        this.valorTarifaEnti4 = valorTarifaEnti4;
    }

    public String getValorTarifaEnti5() {
        return valorTarifaEnti5;
    }

    public void setValorTarifaEnti5(String valorTarifaEnti5) {
        this.valorTarifaEnti5 = valorTarifaEnti5;
    }

    public String getTipoTarifaEntidad() {
        return tipoTarifaEntidad;
    }

    public void setTipoTarifaEntidad(String tipoTarifaEntidad) {
        this.tipoTarifaEntidad = tipoTarifaEntidad;
    }

    public String getValorTarifaEnti() {
        return valorTarifaEnti;
    }

    public void setValorTarifaEnti(String valorTarifaEnti) {
        this.valorTarifaEnti = valorTarifaEnti;
    }

    public ArrayList<LogEntidad> getCargaEntidCarta() {
        return CargaEntidCarta;
    }

    public void setCargaEntidCarta(ArrayList<LogEntidad> CargaEntidCarta) {
        this.CargaEntidCarta = CargaEntidCarta;
    }

    public boolean isEnviarEntidadCarta() {
        return enviarEntidadCarta;
    }

    public void setEnviarEntidadCarta(boolean enviarEntidadCarta) {
        this.enviarEntidadCarta = enviarEntidadCarta;
    }

    public boolean isEnviarCartaEnti1() {
        return enviarCartaEnti1;
    }

    public void setEnviarCartaEnti1(boolean enviarCartaEnti1) {
        this.enviarCartaEnti1 = enviarCartaEnti1;
    }

    public boolean isEnviarCartaEnti2() {
        return enviarCartaEnti2;
    }

    public void setEnviarCartaEnti2(boolean enviarCartaEnti2) {
        this.enviarCartaEnti2 = enviarCartaEnti2;
    }

    public boolean isEnviarCartaEnti3() {
        return enviarCartaEnti3;
    }

    public void setEnviarCartaEnti3(boolean enviarCartaEnti3) {
        this.enviarCartaEnti3 = enviarCartaEnti3;
    }

    public boolean isEnviarCartaEnti4() {
        return enviarCartaEnti4;
    }

    public void setEnviarCartaEnti4(boolean enviarCartaEnti4) {
        this.enviarCartaEnti4 = enviarCartaEnti4;
    }

    public boolean isEnviarCartaEnti5() {
        return enviarCartaEnti5;
    }

    public void setEnviarCartaEnti5(boolean enviarCartaEnti5) {
        this.enviarCartaEnti5 = enviarCartaEnti5;
    }

    public String getCorreoEnti1() {
        return correoEnti1;
    }

    public void setCorreoEnti1(String correoEnti1) {
        this.correoEnti1 = correoEnti1;
    }

    public String getCorreoEnti2() {
        return correoEnti2;
    }

    public void setCorreoEnti2(String correoEnti2) {
        this.correoEnti2 = correoEnti2;
    }

    public String getCorreoEnti3() {
        return correoEnti3;
    }

    public void setCorreoEnti3(String correoEnti3) {
        this.correoEnti3 = correoEnti3;
    }

    public String getCorreoEnti4() {
        return correoEnti4;
    }

    public void setCorreoEnti4(String correoEnti4) {
        this.correoEnti4 = correoEnti4;
    }

    public String getCorreoEnti5() {
        return correoEnti5;
    }

    public void setCorreoEnti5(String correoEnti5) {
        this.correoEnti5 = correoEnti5;
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
     * Metodo que carga las entidades que tengan correo segun el numero de
     * radicacion, para el envio del correo
     *
     * @author Jhojan Stiven Rodriguez
     * @param cod_avaluo int que contiene el numero de avaluo para cargar las
     * entidades que existan en ese avaluo
     * @since 01-05-2015
     */
    public void cargarEntidadesCorreoCarta(int cod_avaluo) {
        try {
            if (enviarEntidadCarta == true) {
                CargaEntidCarta = Ent.ConsultEntRadica(cod_avaluo, 1);
            } else {
                enviarCartaEnti1 = false;
                enviarCartaEnti2 = false;
                enviarCartaEnti3 = false;
                enviarCartaEnti4 = false;
                enviarCartaEnti5 = false;

                correoEnti1 = "";
                correoEnti2 = "";
                correoEnti3 = "";
                correoEnti4 = "";
                correoEnti5 = "";
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarEntidadesCorreoCarta()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga los correos de las entidades segun el numero de
     * radicacion, para el envio del correo
     *
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene la posicion donde se ingresara el correo de
     * las entidades
     * @since 01-05-2015
     */
    public void cargarCorreosEntidad(int fila) {
        try {
            if (fila == 1) {
                correoEnti1 = CargaEntidCarta.get(0).getMailAsesor();
            }
            if (fila == 2) {
                correoEnti2 = CargaEntidCarta.get(1).getMailAsesor();
            }
            if (fila == 3) {
                correoEnti3 = CargaEntidCarta.get(2).getMailAsesor();
            }
            if (fila == 4) {
                correoEnti4 = CargaEntidCarta.get(3).getMailAsesor();
            }
            if (fila == 5) {
                correoEnti5 = CargaEntidCarta.get(4).getMailAsesor();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarCorreosEntidad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga los tipos de calificacion/regimen de las entidades
     *
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList con los datos consultados de las calificaciones/regimen
     * de las entidades
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> getConsulCalifiRegEntid() {
        try {

            Iterator<LogAdministracion> Ite;
            Ite = Adm.getCalificacion(Integer.parseInt(this.codRegimen)).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargaRegimen.add(new SelectItem(MBAdm.getCodCalificacion(), MBAdm.getNombreCalificacion()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulCalifiRegEntid()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaRegimen;

    }

    /**
     * Metodo tipo ajax que carga las los tipos de calificacion de las
     * entidades, de acuerdo a un regimen
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void onCalifiReg() {
        try {
            if (this.codRegimen != null || this.codRegimen.equals("")) {
                CargaRegimen.clear();
                getConsulCalifiRegEntid();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCalifiReg()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

 


    public void ClearVaribles(String Parametro) {

        try {

            switch (Parametro) {
                case "Entidades":

                    EntAse.getNombreEntidadAsesor();
                    EntAse.getNombreEntidadSucursal();
                    EntAse.getNombreAsesor();
                    EntAse.getCargoAsesor();
                    EntAse.getMailAsesor();
                    EntAse.getTelefonoAsesor();
                    EntAse.getNombreEstadoAsesor();

                    Entsucu.setNombreEntidadSucursal("");
                    Entsucu.setNombreOfic("");
                    Entsucu.setTelefonoOfic("");
                    Entsucu.setDireccionOfic("");
                    Entsucu.setNombreDeptoSucursal("");
                    Entsucu.setNombreCiudadSucursal("");
                    Entsucu.setCodOfic("");
                    break;

                case "Sucursales":
                    EntAse.getNombreEntidadAsesor();
                    EntAse.getNombreEntidadSucursal();
                    EntAse.getNombreAsesor();
                    EntAse.getCargoAsesor();
                    EntAse.getMailAsesor();
                    EntAse.getTelefonoAsesor();
                    EntAse.getNombreEstadoAsesor();

                    EntEnt.setNombreEntidad("");
                    EntEnt.setNombreRegimen("");

                    break;
                case "Asesores":
                    Entsucu.setNombreEntidadSucursal("");
                    Entsucu.setNombreOfic("");
                    Entsucu.setTelefonoOfic("");
                    Entsucu.setDireccionOfic("");
                    Entsucu.setNombreDeptoSucursal("");
                    Entsucu.setNombreCiudadSucursal("");
                    Entsucu.setCodOfic("");

                    EntEnt = new LogEntidad();
                    Ent.setDocumentoEntidad("");
                    EntEnt.setNombreEntidad("");
                    EntEnt.setNombreRegimen("");
                    break;
            }
            estPanelEntidad = true;
        } catch (Exception e) {
        }
    }

    /**
     * Metodo que hace visible los paneles segun la opcion seleccionada, en el
     * formulario de entidades
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void visiblePanel() {
        try {
            switch (estadoRadioSeleccion) {
                case "Entidades":
                    estPanelEntidad = true;
                    estPanelSucursal = false;
                    estPanelAsesor = false;
                    mbTodero.resetTable("FrmGestionEnti:EntidadTable");
                    mbTodero.resetTable("FRegistro:EntidadTable");
                    mbTodero.resetTable("PreRadica:EntidadTable");
                    this.CargaEntTodo = new ArrayList<>();
                    this.CargaEntTodo = Ent.getEntidad();
                    LogEntidad EntEnt1 = new LogEntidad();
                    EntEnt1.setCodEntidad(Adm.ConsulCodigoPrincipal("Cod_Entidad", "Entidad"));
                    fk_entidadAsesor = "";
                    break;
                case "Sucursales":
                    estPanelEntidad = false;
                    estPanelSucursal = true;
                    estPanelAsesor = false;
                    mbTodero.resetTable("FrmGestionEnti:SucursalTable");
                    mbTodero.resetTable("FRegistro:SucursalTable");
                    mbTodero.resetTable("PreRadica:SucursalTable");
                    /*
                    this.CargaSucurTodo = new ArrayList<>();
                    this.CargaSucurTodo = Ent.getSucursalAll();
                     */
                    LogEntidad Entsucu1 = new LogEntidad();
                    Entsucu1.setCodSucursal(Adm.ConsulCodigoPrincipal("Cod_Sucursal", "Sucursal"));
                    this.CargaEnt.clear();
                    this.CargaEnt = cargEntidad();
                    fk_entidadAsesor = "";
                    break;
                case "Asesores":
                    estPanelEntidad = false;
                    estPanelSucursal = false;
                    estPanelAsesor = true;
                    mbTodero.resetTable("FrmGestionEnti:AsesorTable");
                    mbTodero.resetTable("FRegistro:AsesorTable");
                    mbTodero.resetTable("PreRadica:AsesorTable");
                    LogEntidad EntAse1 = new LogEntidad();
                    EntAse1.setCodAsesor(Adm.ConsulCodigoPrincipal("Cod_Asesor", "Asesor"));
                    this.CargaEnt.clear();
                    this.CargaEnt = cargEntidad();
                    this.CargaSucur.clear();
                    fk_entidadAsesor = "";
                    break;

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".visiblePanel()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void ConsultInfo(String Prmtro) {
        if (Prmtro.equals("Asesor")) {
            LogEntidad Ent = new LogEntidad();
            Ent.setNombreEntidad(getFk_entidadAsesor());
            this.CargaAsesTodo = new ArrayList<>();
            this.CargaAsesTodo = Ent.getAsesorAll();
        } else if (Prmtro.equals("Sucursal")) {
            LogEntidad Ent = new LogEntidad();
            Ent.setNombreEntidad(getFk_entidadAsesor());
            this.CargaSucurTodo = new ArrayList<>();
            this.CargaSucurTodo = Ent.getSucursalAll();
        }
    }

    /**
     * Metodo que abre el dialog cuando selecciona algun registro y carga
     * informacion para actualizacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param valor int que recibe como valor, para cargar informacion de= 1:
     * Entidades,2: Sucursales ,3: asesores
     * @since 01-05-2015
     */
    public void validarDatatable(int valor) {
        try {
            switch (valor) {
                case 1:
                    if (EntEnt == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        estadoRegistroEntidad = false;
                        estadoActualizarEntidad = true;
                        codEntidad = EntEnt.getCodEntidad();
                        numEntidad = EntEnt.getDocumentoEntidad();
                        nombreEntidad = EntEnt.getNombreEntidad();
                        codRegimen = String.valueOf(EntEnt.getCodRegimen());
                        onCalifiReg();
                        codTipRegimen = String.valueOf(EntEnt.getCodTipoRegim());
                        tituloFormEntidad = "Modificar entidad";
                        RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");
                    }
                    break;
                case 2:
                    if (Entsucu == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        estadoRegistroSucursal = false;
                        estadoActualizarSucursal = true;
                        codSucursal = Entsucu.getCodSucursal();
                        codEntidadSucursal = Entsucu.getCodEntidadSucursal();
                        codOficina = Entsucu.getCodOfic();
                        nombreSucursal = Entsucu.getNombreOfic();
                        telefonoSucursal = Entsucu.getTelefonoOfic();
                        direccionSucursal = Entsucu.getDireccionOfic();
                        if (Entsucu.getCodDeptoSucursal() != null) {
                            codDeptoSucursal = Entsucu.getCodDeptoSucursal();

                            codCiudadSucursal = Entsucu.getCodCiudadSucursal();
                        }
                        tituloFormSucursal = "Modificar Sucursal";
                        RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
                    }
                    break;
                case 3:
                    if (EntAse == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        estadoRegistroAsesor = false;
                        estadoActualizarAsesor = true;
                        codAsesor = EntAse.getCodAsesor();
                        fk_entidadAsesor = EntAse.getFk_EntidadAsesor();
                        onSucursalRegis();
                        fk_sucursalAsesor = EntAse.getFk_sucursalAsesor();
                        nombreAsesor = EntAse.getNombreAsesor();
                        cargoAsesor = EntAse.getCargoAsesor();
                        telefonoAsesor = EntAse.getTelefonoAsesor();
                        mailAsesor = EntAse.getMailAsesor();
                        estadoAsesor = EntAse.getCodEstadoAsesor();
                        tituloFormAsesor = "Modificar Asesor";
                        RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
                    }
                    break;

                case 4:
                    //Este case permite limpiar y reiniciar todas las variables necesarias para evitar que se borre nuestra interfaz grafica
                    CargaEntTodo.clear();
                    CargaSucurTodo.clear();
                    CargaAsesTodo.clear();
                    estPanelEntidad = true;
                    this.CargaEntTodo = Ent.getEntidad();
                    LogEntidad EntEnt = new LogEntidad();
                    EntEnt.setCodEntidad(Adm.ConsulCodigoPrincipal("Cod_Entidad", "Entidad"));
                    this.CargaSucurTodo = new ArrayList<>();
                    this.CargaSucurTodo = Ent.getSucursalAll();
                    LogEntidad Entsucu = new LogEntidad();
                    Entsucu.setCodSucursal(Adm.ConsulCodigoPrincipal("Cod_Sucursal", "Sucursal"));
                    this.CargaAsesTodo = new ArrayList<>();
                    this.CargaAsesTodo = Ent.getAsesorAll();
                    LogEntidad EntAse = new LogEntidad();
                    EntAse.setCodAsesor(Adm.ConsulCodigoPrincipal("Cod_Asesor", "Asesor"));
                    RequestContext.getCurrentInstance().execute("PF('DlgEntidades').hide()");
                    break;
                case 5:
                    limpiar();
                    CargaEntTodo.clear();
                    CargaSucurTodo.clear();
                    CargaAsesTodo.clear();
                    estPanelEntidad = true;
                    fk_entidadAsesor = "";
                    estadoRadioSeleccion = "";

                    /*
                    this.CargaEnt.clear();
                    
                    this.CargaSucur.clear();
                    CargaAses.clear();
                     */
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarDatatable()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre los dialogos de registro de informacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que carga los dialogos segun = [1 = entidad , 2=
     * sucursal. 3= asesor]
     * @since 01-05-2015
     */
    public void abrirDialogRegistrar(int tipo) {
        try {
            if (tipo == 1) {
                limpiarCajasEntidad();
                estadoRegistroEntidad = true;
                estadoActualizarEntidad = false;
                tituloFormEntidad = "Registrar Entidades";

                RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");
            } else if (tipo == 2) {
                limpiarCajasSucursales();
                estadoRegistroSucursal = true;
                estadoActualizarSucursal = false;
                tituloFormSucursal = "Registrar Sucursales";
                RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
            }
            if (tipo == 3) {
                limpiarCajasAsesor();
                estadoRegistroAsesor = true;
                estadoActualizarAsesor = false;
                tituloFormAsesor = "Registrar Asesores";
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogRegistrar()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

//GCH - AGO2016 - UTILITARIO PARA VALIDAR UN INTEGER    
public static boolean esNumero(String cadena){
    try {
            Integer.parseInt(cadena);
            return true;
    } catch (NumberFormatException e){
            return false;
    }
}

    /**
     * Metodo que permite registrar o actualizar una entidad
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_sentencia int que contiene informacion para [Registro = 1,
     * Actualizar = 2]
     * @since 01-05-2015
     */
    //Bandera
    int S = 0;

    public void regisEntidad(int tipo_sentencia) {
        try {
            mbTodero.resetTable("FrmGestionEnti:EntidadTable");
            mbTodero.resetTable("FRegistro:EntidadTable");
            mbTodero.resetTable("PreRadica:EntidadTable");
            Ent = new LogEntidad();
            if (("".equals(numEntidad) || numEntidad == null) && ("".equals(nombreEntidad) || nombreEntidad == null)) {
                mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");
            } else if (("".equals(numEntidad) || numEntidad == null)) {
                mbTodero.setMens("El campo 'N° documento' es obligatorio");
                mbTodero.warn();
            //GCH - AGO2016 - VALIDACION PARA ACEPTAR IDENTIFICACION DE ENTIDAD CON FORMATO NUMERICO SIN PUNTOS O RAYAS
            } else if ( !esNumero(numEntidad) ) {
                mbTodero.setMens("El campo 'N° documento' es numérico y no puede contener caracteres tipo ( / . , * @ A - Z )");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");
            } else if (  ("1".equals(codTipRegimen)) && esNumero(numEntidad) && (numEntidad.length() != 9 ) )   { // SI ES NIT VALIDA QUE SEAN SOLO 9 DIGITOS
                mbTodero.setMens("El NIT debe contener 9 digitos exclusivamente, ingreselo sin el digito de verificacion ");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");             
            } else if (("".equals(nombreEntidad) || nombreEntidad == null)) {
                mbTodero.setMens("El campo 'Nombre entidad' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");
            
            } else {
               
                Dat = Ent.ConsultEntidad(numEntidad) ;
                
                if (Dat.first()) { // GCH - NO PERMITE REGISTRAR DUPLICADOS
                   String men="Ya existe la Entidad: " + Dat.getString("Nombre_Entidad") + " registrada con el documento : " + Dat.getString("Documento_Entidad");
                   mbTodero.setMens(men);
                   mbTodero.warn();
                   RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').show()");
                } else {   
                
                    //Consultar de nuevo la informacion de las entidades
                    CargaEntTodo = Ent.getEntidad();
                    for (int i = 0; i < CargaEntTodo.size(); i++) {
                        if (String.valueOf(CargaEntTodo.get(i).getDocumentoEntidad()).equals(numEntidad)) {

                            S = 1;
                            break;
                        } else {
                            S = 0;
                        }
                    }
                    if (S == 0) {
                        Ent.setDocumentoEntidad(numEntidad);
                        Ent.setNombreEntidad(nombreEntidad);
                        Ent.setCodTipoRegim(Integer.parseInt(codTipRegimen));
                        Ent.InserModifEntidad(tipo_sentencia, mBsesion.codigoMiSesion(), codEntidad);
                        if (tipo_sentencia == 1) {

                            mbTodero.setMens("Entidad registrada");
                            mbTodero.info();
                            RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').hide()");

                        } else if (tipo_sentencia == 2) {

                            mbTodero.setMens("Entidad actualizada");
                            mbTodero.info();
                            RequestContext.getCurrentInstance().execute("PF('DlgCrearEntidad').hide()");
                        }
                        limpiarCajasEntidad();

                        //Verificar el codigo de Referencia
                        switch (getCodRef()) {
                            case 1:
                                if (!"0".equals(codEntidad1)) {
                                    setCodRef(2);
                                    break;
                                } else {
                                    codEntidad1 = "0";
                                    codSucursal1 = "0";
                                    codAsesor1 = "0";
                                    estadoInfoAsesor1 = true;
                                    cargoAsesor1 = "0";
                                    cargEntRadic(1);
                                }
                                break;

                            case 2:
                                codEntidad2 = "0";
                                codSucursal2 = "0";
                                codAsesor2 = "0";
                                estadoInfoAsesor2 = true;
                                cargEntRadic(2);
                                break;

                            case 3:
                                codEntidad3 = "0";
                                codSucursal3 = "0";
                                codAsesor3 = "0";
                                estadoInfoAsesor3 = true;
                                cargEntRadic(3);
                                break;

                            case 4:
                                codEntidad4 = "0";
                                codSucursal4 = "0";
                                codAsesor4 = "0";
                                estadoInfoAsesor4 = true;
                                cargEntRadic(4);
                                break;

                            case 5:
                                codEntidad5 = "0";
                                codSucursal5 = "0";
                                codAsesor5 = "0";
                                estadoInfoAsesor5 = true;
                                cargoAsesor5 = "0";
                                cargEntRadic(5);
                                break;
                        }
                        mbTodero.resetTable("FrmGestionEnti:EntidadTable");
                        mbTodero.resetTable("FRegistro:EntidadTable");
                        mbTodero.resetTable("PreRadica:EntidadTable");
                    } else {
                        mbTodero.setMens("Verifique el Nit , ya que este ya se encuentra registrado");
                        mbTodero.info();
                        limpiarCajasEntidad();
                    }
                }    
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisEntidad()' causado por: " + e.getMessage());
            mbTodero.error();
        }  catch (SQLException e ) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisEntidad()' causado por: " + e.getMessage());
            //mbTodero.error();
            mbTodero.warn();
        }
    }

    /**
     * Metodo que permite registrar o actualizar una sucursal
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_sentencia int que contiene informacion para [Registro = 1,
     * Actualizar = 2]
     * @since 01-05-2015
     */
    public void regisSucursal(int tipo_sentencia) {
        try {
            mbTodero.resetTable("FrmGestionEnti:SucursalTable");
            mbTodero.resetTable("FRegistro:SucursalTable");
            mbTodero.resetTable("PreRadica:SucursalTable");
            Ent = new LogEntidad();
            if (("".equals(codEntidadSucursal) || codEntidadSucursal == null) && ("".equals(nombreSucursal) || nombreSucursal == null)
                    && ("".equals(codDeptoSucursal) || codDeptoSucursal == null) && ("".equals(codCiudadSucursal) || codCiudadSucursal == null)) {
                mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
            } else if (("".equals(codEntidadSucursal) || codEntidadSucursal == null)) {
                mbTodero.setMens("El campo 'Entidad' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
            } else if (("".equals(nombreSucursal) || nombreSucursal == null)) {
                mbTodero.setMens("El campo 'Nombre' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
            } else if (("".equals(codDeptoSucursal) || codDeptoSucursal == null)) {
                mbTodero.setMens("El campo 'Departamento' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
            } else if (("".equals(codCiudadSucursal) || codCiudadSucursal == null)) {
                mbTodero.setMens("El campo 'Ciudad' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').show()");
            } else {
                Ent.setCodEntidadSucursal(codEntidadSucursal);
                Ent.setCodOfic(codOficina);
                Ent.setNombreOfic(nombreSucursal);
                Ent.setDireccionOfic(direccionSucursal);
                Ent.setTelefonoOfic(telefonoSucursal);
                Ent.setCodCiudadSucursal(codCiudadSucursal);
                Ent.InserModifSucursal(tipo_sentencia, mBsesion.codigoMiSesion(), codSucursal);
                if (tipo_sentencia == 1) {
                    mbTodero.setMens("Sucursal registrada");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').hide()");
                } else if (tipo_sentencia == 2) {
                    mbTodero.setMens("Sucursal actualizada");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgCrearSucursal').hide()");
                }
                limpiarCajasSucursales();
                CargaSucurTodoCombo.clear();
                this.CargaSucur.clear();
                CargaSucurTodo = new ArrayList<>();
                //   CargaSucurTodoCombo = cargSucursalTodoCombo();
                mbTodero.resetTable("FrmGestionEnti:SucursalTable");
                mbTodero.resetTable("FRegistro:SucursalTable");
                mbTodero.resetTable("PreRadica:SucursalTable");
                CargaSucurTodo = Ent.getSucursalAll();
                switch (codRef) {
                    case 1:
                        codEntidad1 = "0";
                        codSucursal1 = "0";
                        codAsesor1 = "0";
                        estadoInfoAsesor1 = true;
                        cargoAsesor1 = "";
                        cargSucursalRadic(1);
                        break;

                    case 2:
                        codEntidad2 = "0";
                        codSucursal2 = "0";
                        codAsesor2 = "0";
                        estadoInfoAsesor2 = true;
                        cargSucursalRadic(2);
                        break;

                    case 3:
                        codEntidad3 = "0";
                        codSucursal3 = "0";
                        codAsesor3 = "0";
                        estadoInfoAsesor3 = true;
                        cargSucursalRadic(3);
                        break;

                    case 4:
                        codEntidad4 = "0";
                        codSucursal4 = "0";
                        codAsesor4 = "0";
                        estadoInfoAsesor4 = true;
                        cargSucursalRadic(4);
                        break;

                    case 5:
                        codEntidad5 = "0";
                        codSucursal5 = "0";
                        codAsesor5 = "0";
                        estadoInfoAsesor5 = true;
                        cargoAsesor5 = "0";
                        cargSucursalRadic(5);
                        break;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisSucursal()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void regisAsesor(int tipo_sentencia) {
        try {
            mbTodero.resetTable("FrmGestionEnti:AsesorTable");
            mbTodero.resetTable("FRegistro:AsesorTable");
            mbTodero.resetTable("PreRadica:AsesorTable");
            Ent = new LogEntidad();
            if (("".equals(fk_entidadAsesor) || fk_entidadAsesor == null) && ("".equals(fk_sucursalAsesor) || fk_sucursalAsesor == null) && ("".equals(nombreAsesor) || nombreAsesor == null)
                    && ("".equals(cargoAsesor) || cargoAsesor == null)
                    && ("".equals(estadoAsesor) || estadoAsesor == null)) {
                mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
            } else if (("".equals(fk_entidadAsesor) || fk_entidadAsesor == null)) {
                mbTodero.setMens("El campo 'Entidad' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
            } else if (("".equals(fk_sucursalAsesor) || fk_sucursalAsesor == null)) {
                mbTodero.setMens("El campo 'Sucursal' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
            } else if (("".equals(nombreAsesor) || nombreAsesor == null)) {
                mbTodero.setMens("El campo 'Nombre' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
            } else if (("".equals(cargoAsesor) || cargoAsesor == null)) {
                mbTodero.setMens("El campo 'Cargo' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
            } else if (("".equals(estadoAsesor) || estadoAsesor == null)) {
                mbTodero.setMens("El campo 'Estado' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");

            } else if (("".equals(mailAsesor) || mailAsesor == null)) { //GCH --Ago2016 - desde aqui
                mbTodero.setMens("El campo 'Correo' es obligatorio");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
                          
            } else {  
                 
                Dat = Ent.ConsultAsesor(mailAsesor) ;
                if (Dat.first()) {
                   String men="Ya existe el Asesor: " + Dat.getString("Nombre_Asesor") + " registrado con el correo : " + Dat.getString("Mail_Asesor");
                   mbTodero.setMens(men);
                   mbTodero.warn();
                   RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').show()");
                } else {
                // gch hasta aqui
                        Ent.setFk_sucursalAsesor(fk_sucursalAsesor);
                        Ent.setNombreAsesor(nombreAsesor);
                        Ent.setCargoAsesor(cargoAsesor);
                        Ent.setMailAsesor(mailAsesor);
                        Ent.setTelefonoAsesor(telefonoAsesor);
                        Ent.setCodEstadoAsesor(estadoAsesor);
                        Ent.InserModifAsesor(tipo_sentencia, mBsesion.codigoMiSesion(), codAsesor);
                        if (tipo_sentencia == 1) {
                            mbTodero.setMens("Asesor registrado(a)");
                            mbTodero.info();
                            RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').hide()");
                        } else if (tipo_sentencia == 2) {
                            mbTodero.setMens("Asesor actualizado(a)");
                            mbTodero.info();
                            RequestContext.getCurrentInstance().execute("PF('DlgCrearAsesor').hide()");
                        }

                        // limpiarCajasAsesor();
                        mbTodero.resetTable("FrmGestionEnti:AsesorTable");
                        mbTodero.resetTable("FRegistro:AsesorTable");
                        mbTodero.resetTable("PreRadica:AsesorTable");

                        /*
                         //Reseteo de variables para cuando se registra en registro, y en preradicacion
                         codEntidad1 = "";
                         codEntidad2 = "";
                         codEntidad3 = "";
                         codEntidad4 = "";
                         codEntidad5 = "";

                         codSucursal1 = "";
                         codSucursal2 = "";
                         codSucursal3 = "";
                         codSucursal4 = "";
                         codSucursal5 = "";

                         codAsesor1 = "";
                         codAsesor2 = "";
                         codAsesor3 = "";
                         codAsesor4 = "";
                         codAsesor5 = "";
                         */
                        estPanelEntidad = true;

                        CargaEnt = new ArrayList<>();
                        CargaEnt = cargEntidad();

                        CargaAsesTodo = new ArrayList<>();
                        CargaAsesTodo = Ent.getAsesorAll();

                        //Limpiar las variables para recargar la informacion 
                        switch (codRef) {
                            case 1:
                                codEntidad1 = "0";
                                codSucursal1 = "0";
                                codAsesor1 = "0";
                                estadoInfoAsesor1 = true;
                                cargoAsesor1 = "";
                                break;

                            case 2:
                                codEntidad2 = "0";
                                codSucursal2 = "0";
                                codAsesor2 = "0";
                                estadoInfoAsesor2 = true;
                                break;

                            case 3:
                                codEntidad3 = "0";
                                codSucursal3 = "0";
                                codAsesor3 = "0";
                                estadoInfoAsesor3 = true;
                                break;

                            case 4:
                                codEntidad4 = "0";
                                codSucursal4 = "0";
                                codAsesor4 = "0";
                                estadoInfoAsesor4 = true;
                                break;

                            case 5:
                                codEntidad5 = "0";
                                codSucursal5 = "0";
                                codAsesor5 = "0";
                                estadoInfoAsesor5 = true;
                                cargoAsesor5 = "0";
                                break;
                        }
                    //   ConsultInfo("Asesor");
                }
            }      
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisAsesor()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga las entidades en un SelectOneMenu
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList que retorna las entidades
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> cargEntidad() {
        try {
            Iterator<LogEntidad> Ite;
            Ite = Ent.getEntidad().iterator();
            while (Ite.hasNext()) {
                LogEntidad MBEnt = Ite.next();
                this.CargaEnt.add(new SelectItem(MBEnt.getCodEntidad(), MBEnt.getNombreEntidad()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargEntidad()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaEnt;

    }

    /**
     * Metodo que carga las entidades en un SelectOneMenu segun la entidad
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList que retorna las entidades
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> cargSucursal() {
        try {
            Iterator<LogEntidad> Ite;
            Ite = Ent.getSucursal().iterator();
            while (Ite.hasNext()) {
                LogEntidad MBEnt = Ite.next();
                this.CargaSucur.add(new SelectItem(MBEnt.getCodSucursal(), MBEnt.getNombreOfic()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargSucursal()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaSucur;

    }

    /**
     * Metodo que carga las entidades segun la entidad y la fila que llame el
     * metodo
     *
     * @author Tatiana Lopez Moreno
     * @param fila int que contiene el numero de la fila a la cual se le va a
     * cargar la informacion
     * @since 10-08-2016
     */
    public void cargEntRadic(int fila) {
        try {
            LogEntidad Ent = new LogEntidad();
            setCargaEntTodo(new ArrayList<>());
            setCargaEntTodo(Ent.getEntidad());
            Iterator<LogEntidad> Ite;
            switch (fila) {
                case 1:
                    // setCodEntidad1(String.valueOf(CargaEntTodo.get(0).getCodEntidad()));
                    setCodRef(fila);
                    setCodEntidad1("0");
                    Ent.setCodEntidad(Integer.parseInt(codEntidad1));
                    break;
                case 2:
                    setCodRef(fila);
                    setCodEntidad2("0");
                    Ent.setCodEntidad(Integer.parseInt(codEntidad2));
                    break;
                case 3:
                    setCodRef(fila);
                    setCodEntidad3("0");
                    Ent.setCodEntidad(Integer.parseInt(codEntidad3));
                    break;
                case 4:
                    setCodRef(fila);
                    setCodEntidad4("0");
                    Ent.setCodEntidad(Integer.parseInt(codEntidad4));
                    break;
                case 5:
                    setCodRef(fila);
                    setCodEntidad5("0");
                    Ent.setCodEntidad(Integer.parseInt(codEntidad5));
                    break;
                default:
                    break;
            }
            Ite = Ent.getEntidadSelectOne().iterator();
            while (Ite.hasNext()) {
                LogEntidad MBEnt = Ite.next();
                switch (fila) {
                    case 1:
                        this.CargaEnt1.add(new SelectItem(MBEnt.getCodEntidad(), MBEnt.getNombreEntidad()));
                        break;
                    case 2:
                        this.CargaEnt2.add(new SelectItem(MBEnt.getCodEntidad(), MBEnt.getNombreEntidad()));
                        break;
                    case 3:
                        this.CargaEnt3.add(new SelectItem(MBEnt.getCodEntidad(), MBEnt.getNombreEntidad()));
                        break;
                    case 4:
                        this.CargaEnt4.add(new SelectItem(MBEnt.getCodEntidad(), MBEnt.getNombreEntidad()));
                        break;
                    case 5:
                        this.CargaEnt5.add(new SelectItem(MBEnt.getCodEntidad(), MBEnt.getNombreEntidad()));
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargAsesorRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga las sucursales en un SelectOneMenu segun la entidad y la
     * fila que llame el metodo
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila a la cual se le va a
     * cargar la informacion
     * @since 01-05-2015
     */
    public void cargSucursalRadic(int fila) {
        try {
            Iterator<LogEntidad> Ite;
            switch (fila) {

                case 1:
                    if (codEntidad1 != null) {
                        Ent.setCodEntidad(Integer.parseInt(codEntidad1));
                    } else {
                        mbTodero.setMens("Seleccione una entidad");
                        mbTodero.warn();
                    }

                    break;
                case 2:
                    if (codEntidad2 != null) {
                        Ent.setCodEntidad(Integer.parseInt(codEntidad2));
                    } else {
                        mbTodero.setMens("Seleccione una entidad");
                        mbTodero.warn();
                    }
                    break;
                case 3:

                    if (codEntidad3 != null) {
                        Ent.setCodEntidad(Integer.parseInt(codEntidad3));
                    } else {
                        mbTodero.setMens("Seleccione una entidad");
                        mbTodero.warn();
                    }
                    break;
                case 4:
                    if (codEntidad4 != null) {
                        Ent.setCodEntidad(Integer.parseInt(codEntidad4));
                    } else {
                        mbTodero.setMens("Seleccione una entidad");
                        mbTodero.warn();
                    }
                    break;
                case 5:
                    if (codEntidad5 != null) {
                        Ent.setCodEntidad(Integer.parseInt(codEntidad5));
                    } else {
                        mbTodero.setMens("Seleccione una entidad");
                        mbTodero.warn();
                    }
                    break;
                default:
                    break;
            }
            Ite = Ent.getSucursal().iterator();
            while (Ite.hasNext()) {
                LogEntidad MBEnt = Ite.next();
                switch (fila) {
                    case 1:
                        this.CargaSucur1.add(new SelectItem(MBEnt.getCodSucursal(), MBEnt.getNombreOfic()));
                        break;
                    case 2:
                        this.CargaSucur2.add(new SelectItem(MBEnt.getCodSucursal(), MBEnt.getNombreOfic()));
                        break;
                    case 3:
                        this.CargaSucur3.add(new SelectItem(MBEnt.getCodSucursal(), MBEnt.getNombreOfic()));
                        break;
                    case 4:
                        this.CargaSucur4.add(new SelectItem(MBEnt.getCodSucursal(), MBEnt.getNombreOfic()));
                        break;
                    case 5:
                        this.CargaSucur5.add(new SelectItem(MBEnt.getCodSucursal(), MBEnt.getNombreOfic()));
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargSucursalRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga las sucursal en un SelectOneMenu segun la entidad y
     * sucursal
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList que retorna los asesores
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> cargAsesor() {
        try {
            Iterator<LogEntidad> Ite;
            Ite = Ent.getAsesor().iterator();
            while (Ite.hasNext()) {
                LogEntidad MBEnt = Ite.next();
                this.CargaAses.add(new SelectItem(MBEnt.getCodAsesor(), MBEnt.getNombreAsesor()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargAsesor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaAses;
    }

    /**
     * Metodo que carga los asesores segun sucursal y la fila que llame el
     * metodo
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila a la cual se le va a
     * cargar la informacion
     * @since 01-05-2015
     */
    public void cargAsesorRadic(int fila) {
        try {
            Iterator<LogEntidad> Ite;
            switch (fila) {
                case 1:

                    Ent.setCodSucursal(Integer.parseInt(codSucursal1));
                    break;
                case 2:

                    Ent.setCodSucursal(Integer.parseInt(codSucursal2));
                    break;
                case 3:

                    Ent.setCodSucursal(Integer.parseInt(codSucursal3));
                    break;
                case 4:

                    Ent.setCodSucursal(Integer.parseInt(codSucursal4));
                    break;
                case 5:

                    Ent.setCodSucursal(Integer.parseInt(codSucursal5));
                    break;
                default:
                    break;
            }
            Ite = Ent.getAsesor().iterator();
            while (Ite.hasNext()) {
                LogEntidad MBEnt = Ite.next();
                if (fila == 1) {
                    this.CargaAses1.add(new SelectItem(MBEnt.getCodAsesor(), MBEnt.getNombreAsesor()));
                } else if (fila == 2) {
                    this.CargaAses2.add(new SelectItem(MBEnt.getCodAsesor(), MBEnt.getNombreAsesor()));
                } else if (fila == 3) {
                    this.CargaAses3.add(new SelectItem(MBEnt.getCodAsesor(), MBEnt.getNombreAsesor()));
                } else if (fila == 4) {
                    this.CargaAses4.add(new SelectItem(MBEnt.getCodAsesor(), MBEnt.getNombreAsesor()));
                } else if (fila == 5) {
                    this.CargaAses5.add(new SelectItem(MBEnt.getCodAsesor(), MBEnt.getNombreAsesor()));
                }

            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargAsesorRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo tipo ajax para cargar las sucursales segun la entidad seleccionada
     * segun la fila de la que se este enviando a llamar el metodo
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila a la cual se le va a
     * cargar la informacion
     * @since 01-05-2015
     */
    public void onSucursalRadic(int fila) {
        try {
            switch (fila) {
                case 1:
                    if (this.codEntidad1 != null || "".equals(this.codEntidad1)) {
                        CargaSucur1.clear();
                        codAsesor1 = "";
                        cargSucursalRadic(fila);
                        codSucursal1 = "";
                        setCodRef(fila);
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 2:
                    if (this.codEntidad2 != null || "".equals(this.codEntidad2)) {
                        CargaSucur2.clear();
                        codAsesor2 = "";
                        cargSucursalRadic(fila);
                        codSucursal2 = "";
                        setCodRef(fila);
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 3:
                    if (this.codEntidad3 != null || "".equals(this.codEntidad3)) {
                        CargaSucur3.clear();
                        codAsesor3 = "";
                        cargSucursalRadic(fila);
                        codSucursal3 = "";
                        setCodRef(fila);
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 4:
                    if (this.codEntidad4 != null || "".equals(this.codEntidad4)) {
                        CargaSucur4.clear();
                        codAsesor4 = "";
                        cargSucursalRadic(fila);
                        codSucursal4 = "";
                        setCodRef(fila);
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 5:
                    if (this.codEntidad5 != null || "".equals(this.codEntidad5)) {
                        CargaSucur5.clear();
                        codAsesor5 = "";
                        cargSucursalRadic(fila);
                        codSucursal5 = "";
                        setCodRef(fila);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onSucursalRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo tipo ajax que carga la sucursal cuando se va agregar un asesor
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void onSucursalRegis() {
        try {
            if (this.fk_entidadAsesor != null || "".equals(fk_entidadAsesor)) {
                CargaSucur.clear();
                Ent.setCodEntidad(Integer.parseInt(fk_entidadAsesor));
                cargSucursal();
            } else {
                mbTodero.setMens("Error");
                mbTodero.error();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onSucursalRegis()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo tipo ajax para cargar los asesores segun la sucursal seleccionada
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void onAsesor() {
        try {
            if (String.valueOf(Ent.getCodSucursal()) != null) {
                CargaAses.clear();
                cargAsesor();
            } else {
                mbTodero.setMens("Error");
                mbTodero.error();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onAsesor()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga la informacion de asesores, como correo y telefono, en
     * los paneles de pre-radicacion, registro, radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param op int que contine el numero de asesor que se esta cargando
     * @since 01-05-2015
     */
    public void InfoAsesor(int op) {
        try {
            switch (op) {
                case 1:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad1));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal1));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor1));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor1 = true;
                        this.mailAsesor1 = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor1 = Dat.getString("Telefono_Asesor");
                    }
                    Conexion.CloseCon();
                    break;
                case 2:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad2));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal2));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor2));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor2 = true;
                        this.mailAsesor2 = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor2 = Dat.getString("Telefono_Asesor");
                    }
                    Conexion.CloseCon();
                    break;
                case 3:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad3));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal3));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor3));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor3 = true;
                        this.mailAsesor3 = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor3 = Dat.getString("Telefono_Asesor");
                    }
                    Conexion.CloseCon();
                    break;
                case 4:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad4));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal4));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor4));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor4 = true;
                        this.mailAsesor4 = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor4 = Dat.getString("Telefono_Asesor");
                    }
                    Conexion.CloseCon();
                    break;
                case 5:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad5));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal5));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor5));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor5 = true;
                        this.mailAsesor5 = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor5 = Dat.getString("Telefono_Asesor");
                    }
                    Conexion.CloseCon();
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".InfoAsesor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo tipo ajax para cargar los asesores segun la sucursal seleccionada
     * dependiendo la fila en que sea llamado el metodo
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de fila donde se encuentra el
     * asesor
     * @since 01-05-2015
     */
    public void onAsesorRadic(int fila) {
        try {
            switch (fila) {
                case 1:
                    if (this.codSucursal1 != null || "".equals(this.codSucursal1)) {
                        CargaAses1.clear();
                        //valida que la sucursal selecciionada en esta fila no se encuentre seleccionada en otra
                        if (codSucursal1.equals(codSucursal2) || codSucursal1.equals(codSucursal3) || codSucursal1.equals(codSucursal4) || codSucursal1.equals(codSucursal5)) {
                            mbTodero.setMens("Esta sucursal ya fue seleccionada");
                            mbTodero.warn();
                            CargaSucur1.clear();
                            codEntidad1 = "";
                        } else {
                            //Carga el asesor
                            cargAsesorRadic(fila);
                        }
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 2:
                    if (this.codSucursal2 != null || "".equals(this.codSucursal2)) {
                        CargaAses2.clear();
                        //valida que la sucursal selecciionada en esta fila no se encuentre seleccionada en otra
                        if (codSucursal2.equals(codSucursal1) || codSucursal2.equals(codSucursal3) || codSucursal2.equals(codSucursal4) || codSucursal2.equals(codSucursal5)) {
                            mbTodero.setMens("Esta sucursal ya fue seleccionada");
                            mbTodero.warn();
                            CargaSucur2.clear();
                            codEntidad2 = "";
                        } else {
                            //carga el asesor
                            cargAsesorRadic(fila);
                        }
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 3:
                    if (this.codSucursal3 != null || "".equals(this.codSucursal3)) {
                        CargaAses3.clear();
                        //valida que la sucursal selecciionada en esta fila no se encuentre seleccionada en otra
                        if (codSucursal3.equals(codSucursal1) || codSucursal3.equals(codSucursal2) || codSucursal3.equals(codSucursal4) || codSucursal3.equals(codSucursal5)) {
                            mbTodero.setMens("Esta sucursal ya fue seleccionada");
                            mbTodero.warn();
                            CargaSucur3.clear();
                            codEntidad3 = "";
                        } else {
                            //carga el asesor
                            cargAsesorRadic(fila);
                        }
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 4:
                    if (this.codSucursal4 != null || "".equals(this.codSucursal4)) {
                        CargaAses4.clear();
                        //valida que la sucursal selecciionada en esta fila no se encuentre seleccionada en otra
                        if (codSucursal4.equals(codSucursal1) || codSucursal4.equals(codSucursal2) || codSucursal4.equals(codSucursal3) || codSucursal4.equals(codSucursal5)) {
                            mbTodero.setMens("Esta sucursal ya fue seleccionada");
                            mbTodero.warn();
                            CargaSucur4.clear();
                            codEntidad4 = "";
                        } else {
                            //carga el asesor
                            cargAsesorRadic(fila);
                        }
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                case 5:
                    if (this.codSucursal5 != null || "".equals(this.codSucursal5)) {
                        CargaAses5.clear();
                        //valida que la sucursal selecciionada en esta fila no se encuentre seleccionada en otra
                        if (codSucursal5.equals(codSucursal1) || codSucursal5.equals(codSucursal2) || codSucursal5.equals(codSucursal3) || codSucursal5.equals(codSucursal4)) {
                            mbTodero.setMens("Esta sucursal ya fue seleccionada");
                            mbTodero.warn();
                            CargaSucur5.clear();
                            codEntidad5 = "";
                        } else {
                            //carga el asesor
                            cargAsesorRadic(fila);
                        }
                    } else {
                        mbTodero.setMens("Error");
                        mbTodero.error();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onAsesorRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga la entidad de la preradicacion en radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param proceso int utilizado para cargar informacion para los prcesos:
     * 1=Desde Pre-Radicacion, 2=Desde Radicacion
     * @since 01-05-2015
     */
    //
    public void cargarEntidadesPreRadic(int proceso) {
        try {
            if (proceso == 1) {
                CargaEntidadesTodo = Ent.ConsultEntPreRadicaTodos(cod_preRadic);
                Conexion.CloseCon();
            } else if (proceso == 2) {
                CargaEntidadesTodo = Ent.ConsultEntRadica(cod_avaluo, 2);

                //Cargar la informacion de Anticipos Tatiana
            }
            if (CargaEntidadesTodo.size() <= 0) {
                opcionRadiosMostrarEntidad = true;
            } else {
                for (int i = 0; i <= CargaEntidadesTodo.size() - 1; i++) {
                    estPanelEntidad = true;
                    opcionRadiosMostrarEntidad = false;
                    opcionMostrarEntidad = "Si1";

                    if ("".equals(codEntidad1) || codEntidad1 == null) {

                        estadoVisibleEnti1 = true;

                        if (proceso == 1) {

                            codEntidad1 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            onSucursalRadic(1);
                            codSucursal1 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            onAsesorRadic(1);
                            codAsesor1 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());
                            InfoAsesor(1);

                            DeshabilitarAsesor1 = true;
                            EditarFila1 = true;
                            QuitarFila1 = false;
                            AgregarFila1 = true;
                            AceptarFila1 = false;

                        } else if (proceso == 2) {

                            codEntidad1 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            codSucursal1 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            codAsesor1 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());

                            documentoEntidad1 = String.valueOf(CargaEntidadesTodo.get(i).getDocumentoEntidad());
                            nombreEntidad1 = String.valueOf(CargaEntidadesTodo.get(i).getNombreEntidad());

                            nombreSucursal1 = String.valueOf(CargaEntidadesTodo.get(i).getNombreOfic());
                            telefonoSucursal1 = String.valueOf(CargaEntidadesTodo.get(i).getTelefonoOfic());

                            nombreAsesor1 = String.valueOf(CargaEntidadesTodo.get(i).getNombreAsesor());
                            cargoAsesor1 = String.valueOf(CargaEntidadesTodo.get(i).getCargoAsesor());

                            mailAsesor1 = String.valueOf(CargaEntidadesTodo.get(i).getMailAsesor());

                        }

                        if ("Facturar".equals(CargaEntidadesTodo.get(i).getTipo_entidad())) {
                            entidadFact1 = true;
                            estadoAgregarTarifasPactEnti1 = true;
                            if (("".equals(CargaEntidadesTodo.get(i).getTipo_tarifa()) || CargaEntidadesTodo.get(i).getTipo_tarifa() == null) && ("".equals(CargaEntidadesTodo.get(i).getValor_tarifa()) || CargaEntidadesTodo.get(i).getValor_tarifa() == null)) {

                            } else {
                                opcionRadioTarifasPactadosEnti1 = "Si_Enti1";
                                estadoRadioAgregarTarifaEnti1 = true;

                                estadoTarifasPactEnti1 = true;
                                tipoTarifaEnti1 = CargaEntidadesTodo.get(i).getTipo_tarifa();
                                valorTarifaEnti1 = CargaEntidadesTodo.get(i).getValor_tarifa();
                            }
                        }

                    } else if ("".equals(codEntidad2) || codEntidad2 == null) {
                        estadoAgregarFilasEntidad2 = true;
                        estadoVisibleEnti2 = true;

                        if (proceso == 1) {

                            codEntidad2 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            onSucursalRadic(2);
                            codSucursal2 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            onAsesorRadic(2);
                            codAsesor2 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());
                            InfoAsesor(2);

                            DeshabilitarAsesor2 = true;
                            EditarFila2 = true;
                            QuitarFila2 = false;

                            AgregarFila1 = false;
                            AgregarFila2 = true;

                            AceptarFila2 = false;

                        } else if (proceso == 2) {

                            codEntidad2 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            codSucursal2 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            codAsesor2 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());

                            documentoEntidad2 = String.valueOf(CargaEntidadesTodo.get(i).getDocumentoEntidad());
                            nombreEntidad2 = String.valueOf(CargaEntidadesTodo.get(i).getNombreEntidad());

                            nombreSucursal2 = String.valueOf(CargaEntidadesTodo.get(i).getNombreOfic());
                            telefonoSucursal2 = String.valueOf(CargaEntidadesTodo.get(i).getTelefonoOfic());

                            nombreAsesor2 = String.valueOf(CargaEntidadesTodo.get(i).getNombreAsesor());
                            cargoAsesor2 = String.valueOf(CargaEntidadesTodo.get(i).getCargoAsesor());

                            mailAsesor2 = String.valueOf(CargaEntidadesTodo.get(i).getMailAsesor());
                        }

                        if ("Facturar".equals(CargaEntidadesTodo.get(i).getTipo_entidad())) {
                            entidadFact2 = true;
                            estadoAgregarTarifasPactEnti2 = true;
                            if (("".equals(CargaEntidadesTodo.get(i).getTipo_tarifa()) || CargaEntidadesTodo.get(i).getTipo_tarifa() == null) && ("".equals(CargaEntidadesTodo.get(i).getValor_tarifa()) || CargaEntidadesTodo.get(i).getValor_tarifa() == null)) {

                            } else {
                                opcionRadioTarifasPactadosEnti2 = "Si_Enti2";
                                estadoRadioAgregarTarifaEnti2 = true;
                                estadoTarifasPactEnti2 = true;
                                tipoTarifaEnti2 = CargaEntidadesTodo.get(i).getTipo_tarifa();
                                valorTarifaEnti2 = CargaEntidadesTodo.get(i).getValor_tarifa();
                            }
                        }

                    } else if ("".equals(codEntidad3) || codEntidad3 == null) {
                        estadoAgregarFilasEntidad3 = true;
                        estadoVisibleEnti3 = true;

                        if (proceso == 1) {
                            codEntidad3 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            onSucursalRadic(3);
                            codSucursal3 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            onAsesorRadic(3);
                            codAsesor3 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());
                            InfoAsesor(3);

                            DeshabilitarAsesor3 = true;
                            EditarFila3 = true;
                            QuitarFila3 = false;

                            AgregarFila2 = false;
                            AgregarFila3 = true;

                            AceptarFila3 = false;

                        } else if (proceso == 2) {

                            codEntidad3 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            codSucursal3 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            codAsesor3 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());

                            documentoEntidad3 = String.valueOf(CargaEntidadesTodo.get(i).getDocumentoEntidad());
                            nombreEntidad3 = String.valueOf(CargaEntidadesTodo.get(i).getNombreEntidad());

                            nombreSucursal3 = String.valueOf(CargaEntidadesTodo.get(i).getNombreOfic());
                            telefonoSucursal3 = String.valueOf(CargaEntidadesTodo.get(i).getTelefonoOfic());

                            nombreAsesor3 = String.valueOf(CargaEntidadesTodo.get(i).getNombreAsesor());
                            cargoAsesor3 = String.valueOf(CargaEntidadesTodo.get(i).getCargoAsesor());

                            mailAsesor3 = String.valueOf(CargaEntidadesTodo.get(i).getMailAsesor());
                        }

                        if ("Facturar".equals(CargaEntidadesTodo.get(i).getTipo_entidad())) {
                            entidadFact3 = true;
                            estadoAgregarTarifasPactEnti3 = true;
                            if (("".equals(CargaEntidadesTodo.get(i).getTipo_tarifa()) || CargaEntidadesTodo.get(i).getTipo_tarifa() == null) && ("".equals(CargaEntidadesTodo.get(i).getValor_tarifa()) || CargaEntidadesTodo.get(i).getValor_tarifa() == null)) {

                            } else {
                                opcionRadioTarifasPactadosEnti3 = "Si_Enti3";
                                estadoRadioAgregarTarifaEnti3 = true;
                                estadoTarifasPactEnti3 = true;
                                tipoTarifaEnti3 = CargaEntidadesTodo.get(i).getTipo_tarifa();
                                valorTarifaEnti3 = CargaEntidadesTodo.get(i).getValor_tarifa();

                            }
                        }

                    } else if ("".equals(codEntidad4) || codEntidad4 == null) {
                        estadoAgregarFilasEntidad4 = true;
                        estadoVisibleEnti4 = true;

                        if (proceso == 1) {

                            codEntidad4 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            codSucursal4 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            codAsesor4 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());

                            codEntidad4 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            onSucursalRadic(4);
                            codSucursal4 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            onAsesorRadic(4);
                            codAsesor4 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());
                            InfoAsesor(4);

                            DeshabilitarAsesor4 = true;
                            EditarFila4 = true;
                            QuitarFila4 = false;

                            AgregarFila3 = false;
                            AgregarFila4 = true;

                            AceptarFila4 = false;

                        } else if (proceso == 2) {

                            codEntidad4 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            codSucursal4 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            codAsesor4 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());

                            documentoEntidad4 = String.valueOf(CargaEntidadesTodo.get(i).getDocumentoEntidad());
                            nombreEntidad4 = String.valueOf(CargaEntidadesTodo.get(i).getNombreEntidad());

                            nombreSucursal4 = String.valueOf(CargaEntidadesTodo.get(i).getNombreOfic());
                            telefonoSucursal4 = String.valueOf(CargaEntidadesTodo.get(i).getTelefonoOfic());

                            nombreAsesor4 = String.valueOf(CargaEntidadesTodo.get(i).getNombreAsesor());
                            cargoAsesor4 = String.valueOf(CargaEntidadesTodo.get(i).getCargoAsesor());

                            mailAsesor4 = String.valueOf(CargaEntidadesTodo.get(i).getMailAsesor());
                        }

                        if ("Facturar".equals(CargaEntidadesTodo.get(i).getTipo_entidad())) {
                            entidadFact4 = true;
                            estadoAgregarTarifasPactEnti4 = true;
                            if (("".equals(CargaEntidadesTodo.get(i).getTipo_tarifa()) || CargaEntidadesTodo.get(i).getTipo_tarifa() == null) && ("".equals(CargaEntidadesTodo.get(i).getValor_tarifa()) || CargaEntidadesTodo.get(i).getValor_tarifa() == null)) {

                            } else {
                                opcionRadioTarifasPactadosEnti4 = "Si_Enti4";
                                estadoRadioAgregarTarifaEnti4 = true;
                                estadoTarifasPactEnti4 = true;
                                tipoTarifaEnti4 = CargaEntidadesTodo.get(i).getTipo_tarifa();
                                valorTarifaEnti4 = CargaEntidadesTodo.get(i).getValor_tarifa();
                            }
                        }

                    } else if ("".equals(codEntidad5) || codEntidad5 == null) {
                        estadoAgregarFilasEntidad5 = true;
                        estadoVisibleEnti5 = true;

                        if (proceso == 1) {
                            codEntidad5 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            onSucursalRadic(5);
                            codSucursal5 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            onAsesorRadic(5);
                            codAsesor5 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());
                            InfoAsesor(5);

                            DeshabilitarAsesor5 = true;
                            EditarFila5 = true;
                            QuitarFila5 = false;

                            AgregarFila4 = false;

                            AceptarFila5 = false;
                        } else if (proceso == 2) {

                            codEntidad5 = String.valueOf(CargaEntidadesTodo.get(i).getCodEntidad());
                            codSucursal5 = String.valueOf(CargaEntidadesTodo.get(i).getCodSucursal());
                            codAsesor5 = String.valueOf(CargaEntidadesTodo.get(i).getCodAsesor());

                            documentoEntidad5 = String.valueOf(CargaEntidadesTodo.get(i).getDocumentoEntidad());
                            nombreEntidad5 = String.valueOf(CargaEntidadesTodo.get(i).getNombreEntidad());

                            nombreSucursal5 = String.valueOf(CargaEntidadesTodo.get(i).getNombreOfic());
                            telefonoSucursal5 = String.valueOf(CargaEntidadesTodo.get(i).getTelefonoOfic());

                            nombreAsesor5 = String.valueOf(CargaEntidadesTodo.get(i).getNombreAsesor());
                            cargoAsesor5 = String.valueOf(CargaEntidadesTodo.get(i).getCargoAsesor());

                            mailAsesor5 = String.valueOf(CargaEntidadesTodo.get(i).getMailAsesor());
                        }

                        if ("Facturar".equals(CargaEntidadesTodo.get(i).getTipo_entidad())) {
                            entidadFact5 = true;
                            estadoAgregarTarifasPactEnti5 = true;
                            if (("".equals(CargaEntidadesTodo.get(i).getTipo_tarifa()) || CargaEntidadesTodo.get(i).getTipo_tarifa() == null) && ("".equals(CargaEntidadesTodo.get(i).getValor_tarifa()) || CargaEntidadesTodo.get(i).getValor_tarifa() == null)) {

                            } else {

                                opcionRadioTarifasPactadosEnti5 = "Si_Enti5";
                                estadoRadioAgregarTarifaEnti5 = true;
                                estadoTarifasPactEnti5 = true;
                                tipoTarifaEnti5 = CargaEntidadesTodo.get(i).getTipo_tarifa();
                                valorTarifaEnti5 = CargaEntidadesTodo.get(i).getValor_tarifa();
                            }
                        }

                    }

                }

            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarEntidadesPreRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga los campos de entidades en radicacion segun la fila
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila que contiene el numero de entidad a la que se va a agregar la
     * información
     * @since 01-05-2015
     */
    public void agregarCamposEntidad(int fila) {
        try {
            switch (fila) {
                case 1:
                    cargEntRadic(2);
                    setCodRef(fila);

                    if (("".equals(codEntidad1) || codEntidad1 == null) && ("".equals(codSucursal1) || codSucursal1 == null) && ("".equals(codAsesor1) || codAsesor1 == null)) {
                        mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                        mbTodero.warn();
                    } else if (("".equals(codEntidad1) || codEntidad1 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                    } else if (("".equals(codSucursal1) || codSucursal1 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                    } else if (("".equals(codAsesor1) || codAsesor1 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                    } else {
                        this.estadoAgregarFilasEntidad2 = true;
                        this.EditarFila1 = true;
                        this.QuitarFila1 = false;
                        this.QuitarFila2 = true;
                        this.AgregarFila1 = false;
                        this.AgregarFila2 = estadoAgregarFilasEntidad3 != true;
                        this.AceptarFila2 = true;
                        this.DeshabilitarAsesor1 = true;
                        this.DeshabilitarAsesor2 = false;
                        this.DeshabilitarAsesor3 = true;
                        this.DeshabilitarAsesor4 = true;
                        this.DeshabilitarAsesor5 = true;

                    }
                    break;
                case 2:
                    setCodRef(fila);
                    cargEntRadic(3);
                    if (("".equals(codEntidad2) || codEntidad2 == null) && ("".equals(codSucursal2) || codSucursal2 == null) && ("".equals(codAsesor2) || codAsesor2 == null)) {
                        mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                        mbTodero.warn();
                    } else if (("".equals(codEntidad2) || codEntidad2 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                    } else if (("".equals(codSucursal2) || codSucursal2 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                    } else if (("".equals(codAsesor2) || codAsesor2 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                    } else {
                        estadoAgregarFilasEntidad3 = true;
                        this.EditarFila2 = true;
                        this.QuitarFila2 = false;
                        this.QuitarFila3 = true;
                        this.AgregarFila2 = false;
                        this.AgregarFila3 = estadoAgregarFilasEntidad4 != true;
                        this.AceptarFila3 = true;
                        this.DeshabilitarAsesor1 = true;
                        this.DeshabilitarAsesor2 = true;
                        this.DeshabilitarAsesor3 = false;
                        this.DeshabilitarAsesor4 = true;
                        this.DeshabilitarAsesor5 = true;

                    }
                    break;
                case 3:
                    cargEntRadic(4);
                    setCodRef(fila);
                    if (("".equals(codEntidad3) || codEntidad3 == null) && ("".equals(codSucursal3) || codSucursal3 == null) && ("".equals(codAsesor3) || codAsesor3 == null)) {
                        mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                        mbTodero.warn();
                    } else if (("".equals(codEntidad3) || codEntidad3 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                    } else if (("".equals(codSucursal3) || codSucursal3 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                    } else if (("".equals(codAsesor3) || codAsesor3 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                    } else {
                        estadoAgregarFilasEntidad4 = true;
                        this.EditarFila3 = true;
                        this.QuitarFila3 = false;
                        this.QuitarFila4 = true;
                        this.AgregarFila3 = false;
                        this.AgregarFila4 = estadoAgregarFilasEntidad5 != true;
                        this.AceptarFila4 = true;
                        this.DeshabilitarAsesor1 = true;
                        this.DeshabilitarAsesor2 = true;
                        this.DeshabilitarAsesor3 = true;
                        this.DeshabilitarAsesor4 = false;
                        this.DeshabilitarAsesor5 = true;
                    }
                    break;
                case 4:
                    setCodRef(fila);
                    cargEntRadic(5);
                    if (("".equals(codEntidad4) || codEntidad4 == null) && ("".equals(codSucursal4) || codSucursal4 == null) && ("".equals(codAsesor4) || codAsesor4 == null)) {
                        mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                        mbTodero.warn();
                    } else if (("".equals(codEntidad4) || codEntidad4 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                    } else if (("".equals(codSucursal4) || codSucursal4 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                    } else if (("".equals(codAsesor4) || codAsesor4 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                    } else {
                        estadoAgregarFilasEntidad5 = true;

                        this.EditarFila4 = true;

                        this.QuitarFila4 = false;
                        this.QuitarFila5 = true;

                        this.AgregarFila4 = false;

                        this.AceptarFila5 = true;

                        this.DeshabilitarAsesor1 = true;
                        this.DeshabilitarAsesor2 = true;
                        this.DeshabilitarAsesor3 = true;
                        this.DeshabilitarAsesor4 = true;
                        this.DeshabilitarAsesor5 = false;

                    }
                    break;
                case 5:
                    setCodRef(fila);
                    if (("".equals(codEntidad5) || codEntidad5 == null)
                            && ("".equals(codSucursal5) || codSucursal5 == null)
                            && ("".equals(codAsesor5) || codAsesor5 == null)) {
                        mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                        mbTodero.warn();
                    } else if (("".equals(codEntidad5) || codEntidad5 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                    } else if (("".equals(codSucursal5) || codSucursal5 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                    } else if (("".equals(codAsesor5) || codAsesor5 == null)) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                    }

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarCamposEntidad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que se utiliza para habilitar por medio de los estados de cada
     * fila las cajas y botones necesarios para editar la informacion ingresada
     * de una entidad
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila donde se encuentra la
     * entidad a modificar
     * @since 01-05-2015
     */
    public void editarEntidades(int fila) {
        try {
            switch (fila) {
                case 1:
                    this.EditarFila1 = false;
                    this.AceptarFila1 = true;
                    this.QuitarFila1 = true;
                    this.DeshabilitarAsesor1 = false;
                    this.DeshabilitarAsesor2 = true;
                    this.DeshabilitarAsesor3 = true;
                    this.DeshabilitarAsesor4 = true;
                    this.DeshabilitarAsesor5 = true;
                    break;
                case 2:
                    this.EditarFila2 = false;
                    this.AceptarFila2 = true;
                    QuitarFila2 = true;
                    this.DeshabilitarAsesor1 = true;
                    this.DeshabilitarAsesor2 = false;
                    this.DeshabilitarAsesor3 = true;
                    this.DeshabilitarAsesor4 = true;
                    this.DeshabilitarAsesor5 = true;
                    break;
                case 3:
                    this.EditarFila3 = false;
                    this.AceptarFila3 = true;
                    QuitarFila3 = true;
                    this.DeshabilitarAsesor1 = true;
                    this.DeshabilitarAsesor2 = true;
                    this.DeshabilitarAsesor3 = false;
                    this.DeshabilitarAsesor4 = true;
                    this.DeshabilitarAsesor5 = true;
                    break;
                case 4:
                    this.EditarFila4 = false;
                    this.AceptarFila4 = true;
                    QuitarFila4 = true;
                    this.DeshabilitarAsesor1 = true;
                    this.DeshabilitarAsesor2 = true;
                    this.DeshabilitarAsesor3 = true;
                    this.DeshabilitarAsesor4 = false;
                    this.DeshabilitarAsesor5 = true;
                    break;
                case 5:
                    QuitarFila5 = true;
                    this.EditarFila5 = false;
                    this.AceptarFila5 = true;
                    this.DeshabilitarAsesor1 = true;
                    this.DeshabilitarAsesor2 = true;
                    this.DeshabilitarAsesor3 = true;
                    this.DeshabilitarAsesor4 = true;
                    this.DeshabilitarAsesor5 = false;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".editarEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que se utiliza para habilitar por medio de los estados de cada
     * fila las cajas y botones necesarios para quitar/eliminar la informacion
     * ingresada de una entidad
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila donde se encuentra la
     * entidad a eliminar
     * @since 01-05-2015
     */
    public void QuitarEntidades(int fila) {
        try {
            switch (fila) {
                case 1:
                    setCodRef(fila);
                    this.codEntidad1 = "";
                    this.codSucursal1 = "";
                    this.codAsesor1 = "";
                    this.entidadFact1 = false;
                    this.estadoInfoAsesor1 = false;
                    this.mailAsesor1 = "";
                    this.telefonoAsesor1 = "";
                    opcionRadioTarifasPactadosEnti1 = "";
                    tipoTarifaEnti1 = "";
                    valorTarifaEnti1 = "";
                    estadoAgregarTarifasPactEnti1 = false;
                    estadoTarifasPactEnti1 = false;
                    //  cargEntRadic(getCodRef());
                    break;
                case 2:
                    setCodRef(fila - 1);
                    this.codEntidad2 = "";
                    this.codSucursal2 = "";
                    this.codAsesor2 = "";
                    this.estadoInfoAsesor2 = false;
                    this.mailAsesor2 = "";
                    this.telefonoAsesor2 = "";
                    opcionRadioTarifasPactadosEnti2 = "";
                    tipoTarifaEnti2 = "";
                    valorTarifaEnti2 = "";
                    estadoAgregarTarifasPactEnti2 = false;
                    estadoTarifasPactEnti2 = false;
                    this.estadoAgregarFilasEntidad2 = false;
                    this.QuitarFila2 = false;
                    this.EditarFila2 = false;
                    this.AgregarFila1 = true;
                    this.AgregarFila2 = false;
                    this.AceptarFila2 = false;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                        this.DeshabilitarElimia1 = false;
                        this.EditarFila1 = false;
                        this.AceptarFila1 = false;
                        this.AgregarFila1 = true;
                    }
                    //   cargEntRadic(getCodRef());
                    break;
                case 3:
                    setCodRef(fila - 1);
                    this.codEntidad3 = "";
                    this.codSucursal3 = "";
                    this.codAsesor3 = "";
                    this.estadoInfoAsesor3 = false;
                    this.mailAsesor3 = "";
                    this.telefonoAsesor3 = "";
                    opcionRadioTarifasPactadosEnti3 = "";
                    tipoTarifaEnti3 = "";
                    valorTarifaEnti3 = "";
                    estadoAgregarTarifasPactEnti3 = false;
                    estadoTarifasPactEnti3 = false;
                    this.estadoAgregarFilasEntidad3 = false;
                    this.QuitarFila3 = false;
                    this.EditarFila3 = false;
                    this.AgregarFila2 = true;
                    this.AgregarFila3 = false;
                    this.AceptarFila3 = false;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                        this.EditarFila1 = false;
                        this.AceptarFila1 = false;
                        this.AgregarFila1 = true;
                    }
                    //  cargEntRadic(getCodRef());
                    break;
                case 4:
                    setCodRef(fila - 1);
                    this.codEntidad4 = "";
                    this.codSucursal4 = "";
                    this.codAsesor4 = "";
                    this.estadoInfoAsesor4 = false;
                    this.mailAsesor4 = "";
                    this.telefonoAsesor4 = "";
                    opcionRadioTarifasPactadosEnti4 = "";
                    tipoTarifaEnti4 = "";
                    valorTarifaEnti4 = "";
                    estadoAgregarTarifasPactEnti4 = false;
                    estadoTarifasPactEnti4 = false;
                    this.estadoAgregarFilasEntidad4 = false;
                    this.QuitarFila4 = false;
                    this.EditarFila4 = false;
                    this.AgregarFila3 = true;
                    this.AgregarFila4 = false;
                    this.AceptarFila4 = false;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                        this.EditarFila1 = false;
                        this.AceptarFila1 = false;
                        this.AgregarFila1 = true;
                    }
                    //  cargEntRadic(getCodRef());
                    break;
                case 5:
                    setCodRef(fila - 1);
                    this.codEntidad5 = "";
                    this.codSucursal5 = "";
                    this.codAsesor5 = "";
                    this.estadoInfoAsesor5 = false;
                    this.mailAsesor5 = "";
                    this.telefonoAsesor5 = "";
                    opcionRadioTarifasPactadosEnti5 = "";
                    tipoTarifaEnti5 = "";
                    valorTarifaEnti5 = "";
                    estadoAgregarTarifasPactEnti5 = false;
                    estadoTarifasPactEnti5 = false;
                    this.estadoAgregarFilasEntidad5 = false;
                    this.QuitarFila5 = false;
                    this.EditarFila5 = false;
                    this.AgregarFila4 = true;
                    this.AceptarFila5 = false;
                    if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                        this.EditarFila1 = false;
                        this.AceptarFila1 = false;
                        this.AgregarFila1 = true;
                    }
                    //cargEntRadic(getCodRef());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".QuitarEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que se utiliza para habilitar por medio de los estados de cada
     * fila las cajas y botones necesarios para aceptar y verificar la
     * informacion ingresada de una entidad/sucursal/asesor
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila donde se encuentra la
     * entidad a ser verificada y aceptada
     * @since 01-05-2015
     */
    public void aceptarEntidades(int fila) {
        try {
            if (fila == 1) {
                if (("".equals(codEntidad1) || codEntidad1 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codSucursal1) || codSucursal1 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codAsesor1) || codAsesor1 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else {
                    this.EditarFila1 = true;
                    this.AceptarFila1 = false;
                    this.QuitarFila1 = false;
                    this.DeshabilitarAsesor1 = true;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    }
                }
            } else if (fila == 2) {
                if (("".equals(codEntidad2) || codEntidad2 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codSucursal2) || codSucursal2 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codAsesor2) || codAsesor2 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else {
                    this.EditarFila2 = true;
                    this.AceptarFila2 = false;
                    this.QuitarFila2 = false;
                    this.DeshabilitarAsesor2 = true;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                    }
                }

            } else if (fila == 3) {
                if (("".equals(codEntidad3) || codEntidad3 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codSucursal3) || codSucursal3 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codAsesor3) || codAsesor3 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else {
                    this.EditarFila3 = true;
                    this.AceptarFila3 = false;
                    this.QuitarFila3 = false;
                    this.DeshabilitarAsesor3 = true;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                    }
                }

            } else if (fila == 4) {
                if (("".equals(codEntidad4) || codEntidad4 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codSucursal4) || codSucursal4 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codAsesor4) || codAsesor4 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else {
                    this.EditarFila4 = true;
                    this.AceptarFila4 = false;
                    this.QuitarFila4 = false;
                    this.DeshabilitarAsesor4 = true;
                    if (this.QuitarFila5 == true) {
                        this.DeshabilitarAsesor5 = false;
                    } else if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                    }
                }

            } else if (fila == 5) {
                if (("".equals(codEntidad5) || codEntidad5 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codSucursal5) || codSucursal5 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else if (("".equals(codAsesor5) || codAsesor5 == null)) {
                    mbTodero.setMens("Debe seleccionar toda la información de la entidad");
                    mbTodero.info();
                } else {
                    this.EditarFila5 = true;
                    this.AceptarFila5 = false;
                    this.QuitarFila5 = false;
                    this.DeshabilitarAsesor5 = true;
                    if (this.QuitarFila2 == true) {
                        this.DeshabilitarAsesor2 = false;
                    } else if (this.QuitarFila4 == true) {
                        this.DeshabilitarAsesor4 = false;
                    } else if (this.QuitarFila3 == true) {
                        this.DeshabilitarAsesor3 = false;
                    } else if (this.QuitarFila1 == true) {
                        this.DeshabilitarAsesor1 = false;
                    }
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".aceptarEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica y carga la información de un asesor al seleccionarlo
     * desde la lista en pre-radicacion y registro
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param op int que contiene el numero de asesor de su posicion en el
     * formulario
     * @since 01-05-2015
     */
    public void VerInfoAsesor(int op) {
        try {
            switch (op) {
                case 1:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad1));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal1));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor1));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor1 = true;

                        this.numEntidad = Dat.getString("Documento_Entidad");
                        this.nombreEntidad = Dat.getString("Nombre_Entidad");

                        this.codOficina = Dat.getString("Cod_Oficina");
                        this.nombreSucursal = Dat.getString("Nombre_Oficina");
                        this.telefonoSucursal = Dat.getString("Telefono_Oficina");
                        this.direccionSucursal = Dat.getString("Direccion_Oficina");

                        this.nombreAsesor = Dat.getString("Nombre_Asesor");
                        this.mailAsesor = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor = Dat.getString("Telefono_Asesor");
                        this.cargoAsesor = Dat.getString("Cargo_Asesor");

                        RequestContext.getCurrentInstance().execute("PF('InfoAsesor').show()");
                    }
                    Conexion.CloseCon();
                    break;
                case 2:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad2));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal2));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor2));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor2 = true;
                        this.nombreAsesor = Dat.getString("Nombre_Asesor");
                        this.mailAsesor = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor = Dat.getString("Telefono_Asesor");
                        this.cargoAsesor = Dat.getString("Cargo_Asesor");
                        this.nombreEntidad = Dat.getString("Nombre_Entidad");
                        this.nombreSucursal = Dat.getString("Nombre_Oficina");
                        RequestContext.getCurrentInstance().execute("PF('InfoAsesor').show()");
                    }
                    Conexion.CloseCon();
                    break;
                case 3:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad3));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal3));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor3));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor3 = true;
                        this.nombreAsesor = Dat.getString("Nombre_Asesor");
                        this.mailAsesor = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor = Dat.getString("Telefono_Asesor");
                        this.cargoAsesor = Dat.getString("Cargo_Asesor");
                        this.nombreEntidad = Dat.getString("Nombre_Entidad");
                        this.nombreSucursal = Dat.getString("Nombre_Oficina");
                        RequestContext.getCurrentInstance().execute("PF('InfoAsesor').show()");
                    }
                    Conexion.CloseCon();
                    break;
                case 4:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad4));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal4));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor4));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor4 = true;
                        this.nombreAsesor = Dat.getString("Nombre_Asesor");
                        this.mailAsesor = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor = Dat.getString("Telefono_Asesor");
                        this.cargoAsesor = Dat.getString("Cargo_Asesor");
                        this.nombreEntidad = Dat.getString("Nombre_Entidad");
                        this.nombreSucursal = Dat.getString("Nombre_Oficina");
                        RequestContext.getCurrentInstance().execute("PF('InfoAsesor').show()");
                    }
                    Conexion.CloseCon();
                    break;
                case 5:
                    Ent.setCodEntidad(Integer.parseInt(this.codEntidad5));
                    Ent.setCodSucursal(Integer.parseInt(this.codSucursal5));
                    Ent.setCodAsesor(Integer.parseInt(this.codAsesor5));
                    Dat = Ent.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        this.estadoInfoAsesor5 = true;
                        this.nombreAsesor = Dat.getString("Nombre_Asesor");
                        this.mailAsesor = Dat.getString("Mail_Asesor");
                        this.telefonoAsesor = Dat.getString("Telefono_Asesor");
                        this.cargoAsesor = Dat.getString("Cargo_Asesor");
                        this.nombreEntidad = Dat.getString("Nombre_Entidad");
                        this.nombreSucursal = Dat.getString("Nombre_Oficina");
                        RequestContext.getCurrentInstance().execute("PF('InfoAsesor').show()");
                    }
                    Conexion.CloseCon();
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".VerInfoAsesor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo utilizado para mostrar la informacion para agregar a las personas
     * a facturar
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que contiene el numero de la persona a facturar en su
     * posicion en el formulario
     * @since 01-05-2015
     */
    public void mostrarAgregarTarPac(int tipo) {
        try {
            switch (tipo) {
                case 1:
                    if (entidadFact1 == true) {
                        estadoAgregarTarifasPactEnti1 = true;
                    } else {
                        estadoAgregarTarifasPactEnti1 = false;
                        opcionRadioTarifasPactadosEnti1 = "";
                        estadoTarifasPactEnti1 = false;
                        tipoTarifaEnti1 = "";
                        valorTarifaEnti1 = "";
                    }
                    break;
                case 2:
                    if (entidadFact2 == true) {
                        estadoAgregarTarifasPactEnti2 = true;
                    } else {
                        estadoAgregarTarifasPactEnti2 = false;
                        opcionRadioTarifasPactadosEnti2 = "";
                        estadoTarifasPactEnti2 = false;
                        tipoTarifaEnti2 = "";
                        valorTarifaEnti2 = "";
                    }
                    break;
                case 3:
                    if (entidadFact3 == true) {
                        estadoAgregarTarifasPactEnti3 = true;
                    } else {
                        estadoAgregarTarifasPactEnti3 = false;
                        opcionRadioTarifasPactadosEnti3 = "";
                        estadoTarifasPactEnti3 = false;
                        tipoTarifaEnti3 = "";
                        valorTarifaEnti3 = "";
                    }
                    break;
                default:
                    break;
            }
            if (tipo == 4) {
                if (entidadFact1 == true) {
                    estadoAgregarTarifasPactEnti1 = true;
                } else {
                    estadoAgregarTarifasPactEnti1 = false;
                    opcionRadioTarifasPactadosEnti1 = "";
                    estadoTarifasPactEnti1 = false;
                    tipoTarifaEnti1 = "";
                    valorTarifaEnti1 = "";
                }
            }
            if (tipo == 5) {
                if (entidadFact5 == true) {
                    estadoAgregarTarifasPactEnti5 = true;
                } else {
                    estadoAgregarTarifasPactEnti5 = false;
                    opcionRadioTarifasPactadosEnti5 = "";
                    estadoTarifasPactEnti5 = false;
                    tipoTarifaEnti5 = "";
                    valorTarifaEnti5 = "";
                }
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarAgregarTarPac()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que abre el formulario tipo dialog para ingresar las tarifas
     * pactadas con las entidades que sean personas a facturar en el form de
     * radicación
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de la fila de la entidad a la que
     * se le va a agregar las tarifas pactadas
     * @since 01-05-2015
     */
    public void agregarTarifasPactadas(int fila) {

        this.num_entidad = fila;
        try {
            if (fila == 1) {
                switch (opcionRadioTarifasPactadosEnti1) {
                    case "Si_Enti1":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').show()");
                        tipoTarifaEntidad = tipoTarifaEnti1;
                        valorTarifaEnti = valorTarifaEnti1;
                        break;
                    case "No_Enti1":
                        estadoTarifasPactEnti1 = false;
                        tipoTarifaEnti1 = "";
                        valorTarifaEnti1 = "";
                        tipoTarifaEntidad = "";
                        valorTarifaEnti = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').hide()");
                        break;
                }
            }

            if (fila == 2) {
                switch (opcionRadioTarifasPactadosEnti2) {
                    case "Si_Enti2":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').show()");
                        tipoTarifaEntidad = tipoTarifaEnti2;
                        valorTarifaEnti = valorTarifaEnti2;
                        break;
                    case "No_Enti2":
                        estadoTarifasPactEnti2 = false;
                        tipoTarifaEnti2 = "";
                        valorTarifaEnti3 = "";
                        tipoTarifaEntidad = "";
                        valorTarifaEnti = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').hide()");
                        break;
                }
            }

            if (fila == 3) {
                switch (opcionRadioTarifasPactadosEnti3) {
                    case "Si_Enti3":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').show()");
                        tipoTarifaEntidad = tipoTarifaEnti3;
                        valorTarifaEnti = valorTarifaEnti3;
                        break;
                    case "No_Enti3":
                        estadoTarifasPactEnti3 = false;
                        tipoTarifaEnti3 = "";
                        valorTarifaEnti3 = "";
                        tipoTarifaEntidad = "";
                        valorTarifaEnti = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').hide()");
                        break;
                }
            }

            if (fila == 4) {
                switch (opcionRadioTarifasPactadosEnti4) {
                    case "Si_Enti4":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').show()");
                        tipoTarifaEntidad = tipoTarifaEnti4;
                        valorTarifaEnti = valorTarifaEnti4;
                        break;
                    case "No_Enti4":
                        estadoTarifasPactEnti4 = false;
                        tipoTarifaEnti4 = "";
                        valorTarifaEnti4 = "";
                        tipoTarifaEntidad = "";
                        valorTarifaEnti = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').hide()");
                        break;
                }
            }

            if (fila == 5) {
                switch (opcionRadioTarifasPactadosEnti5) {
                    case "Si_Enti5":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').show()");
                        tipoTarifaEntidad = tipoTarifaEnti5;
                        valorTarifaEnti = valorTarifaEnti5;
                        break;
                    case "No_Enti5":
                        estadoTarifasPactEnti5 = false;
                        tipoTarifaEnti5 = "";
                        valorTarifaEnti5 = "";
                        tipoTarifaEntidad = "";
                        valorTarifaEnti = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').hide()");
                        break;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarTarifasPactadas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo utilizado para agregar la tarifa seleccionada al formulario de
     * radicacion, validando previamente la informacion ingresada
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void agregarTarifasEntidades() {
        try {
            if (("".equals(tipoTarifaEntidad) || tipoTarifaEntidad == null) && ("".equals(valorTarifaEnti) || valorTarifaEnti == null)) {
                mbTodero.setMens("Debe llenar los campos para el tipo de tarifa");
                mbTodero.warn();
            } else if (("".equals(tipoTarifaEntidad) || tipoTarifaEntidad == null)) {
                mbTodero.setMens("Debe llenar el campo Tipo de tarifa");
                mbTodero.warn();
            } else if (("".equals(valorTarifaEnti) || valorTarifaEnti == null)) {
                mbTodero.setMens("Debe llenar el campo Valor de tarifa");
                mbTodero.warn();
            } else {
                if (num_entidad == 1) {
                    estadoTarifasPactEnti1 = true;
                    tipoTarifaEnti1 = tipoTarifaEntidad;
                    valorTarifaEnti1 = valorTarifaEnti;
                }
                if (num_entidad == 2) {
                    estadoTarifasPactEnti2 = true;
                    tipoTarifaEnti2 = tipoTarifaEntidad;
                    valorTarifaEnti2 = valorTarifaEnti;
                }
                if (num_entidad == 3) {
                    estadoTarifasPactEnti3 = true;
                    tipoTarifaEnti3 = tipoTarifaEntidad;
                    valorTarifaEnti3 = valorTarifaEnti;
                }
                if (num_entidad == 4) {
                    estadoTarifasPactEnti4 = true;
                    tipoTarifaEnti4 = tipoTarifaEntidad;
                    valorTarifaEnti4 = valorTarifaEnti;
                }
                if (num_entidad == 5) {
                    estadoTarifasPactEnti5 = true;
                    tipoTarifaEnti5 = tipoTarifaEntidad;
                    valorTarifaEnti5 = valorTarifaEnti;
                }
                tipoTarifaEntidad = "";
                valorTarifaEnti = "";
                RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaEntidad').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarTarifasEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia el formulario de Registrar / modificar entidades
     * (administración, form registro, form preradicacion)
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    //
    public void limpiarCajasEntidad() {
        try {
            numEntidad = "";
            nombreEntidad = "";
            codRegimen = "";
            codTipRegimen = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCajasEntidad()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que limpia el formulario de Registrar / modificar sucursales
     * (administración, form registro, form preradicacion)
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    //
    public void limpiarCajasSucursales() {
        try {
            codEntidadSucursal = "0";
            codOficina = "";
            nombreSucursal = "";
            direccionSucursal = "";
            telefonoSucursal = "";
            codCiudadSucursal = "0";
            codDeptoSucursal = "0";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCajasSucursales()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia el formulario de Registrar / modificar asesores
     * (administración, form registro, form preradicacion)
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void limpiarCajasAsesor() {
        try {
            fk_entidadAsesor = "0";
            fk_sucursalAsesor = "0";
            nombreAsesor = "";
            cargoAsesor = "";
            mailAsesor = "";
            telefonoAsesor = "";
            estadoAsesor = "";

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCajasAsesor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo utilizado para poner en estado false los paneles que muestrasn la
     * informacion para agregar, eliminar, modificar, aceptar y verificar las
     * entidades, sucursales y asesores
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void limpiar() {
        try {
            this.estadoRadioSeleccion = "";
            this.estPanelAsesor = false;
            this.estPanelEntidad = false;
            this.estPanelSucursal = false;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiar()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void LimpiarEntiFac() {

        // listEntidadesPersonasAFacturarAntic
        setNombreEntidad("");
        setNombreEntidad1("");
        setNombreEntidad2("");
        setNombreEntidad3("");
        setNombreEntidad4("");
        setNombreEntidad5("");
        setNombreSucursal("");
        setNombreSucursal1("");
        setNombreSucursal2("");
        setNombreSucursal3("");
        setNombreSucursal4("");
        setNombreSucursal5("");
        setNombreAsesor("");
        setNombreAsesor1("");
        setNombreAsesor2("");
        setNombreAsesor3("");
        setNombreAsesor4("");
        setNombreAsesor5("");
        setEntidadFact1(false);
        setEntidadFact2(false);
        setEntidadFact3(false);
        setEntidadFact4(false);
        setEntidadFact5(false);
        setTipoTarifaEnti1("");
        setTipoTarifaEnti2("");
        setTipoTarifaEnti3("");
        setTipoTarifaEnti4("");
        setTipoTarifaEnti5("");
        setValorTarifaEnti1("");
        setValorTarifaEnti2("");
        setValorTarifaEnti3("");
        setValorTarifaEnti4("");
        setValorTarifaEnti5("");
        setEstadoTarifasPactEnti1(false);
    }


}
