package LogBean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import Logic.LogAdministracion;
import Logic.LogEmpleado;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import java.util.HashMap;
import org.primefaces.context.RequestContext;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso de administración de componentes del
 * aplicativo</b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-10-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBAdministacion")
@ViewScoped
public class BeanAdministracion {

    /**
     * Variables / que abarcan toda la clase administracion
     * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     */
    /**
     * -------------- Variables Implicitas *
     */
    /**
     * Variables tipo List que cargan la informacion en tablas (datatable) *
     */
    private ArrayList<SelectItem> CargarICA = new ArrayList<>();
    private ArrayList<SelectItem> CargarFuente = new ArrayList<>();
    private ArrayList<SelectItem> CargarReteIva = new ArrayList<>();
    private List<LogAdministracion> ListEstado = new ArrayList<>();
    private List<LogAdministracion> ListRegimen = new ArrayList<>();
    private List<LogAdministracion> ListTipoRegimen = new ArrayList<>();
    private List<LogAdministracion> ListTipDocumento = new ArrayList<>();
    private List<LogAdministracion> ListProducEnt = new ArrayList<>();
    private List<LogAdministracion> ListTipProducEnt = new ArrayList<>();
    private List<LogAdministracion> ListRoles = new ArrayList<>();
    private List<LogAdministracion> ListAcessos = new ArrayList<>();
    private List<LogAdministracion> ListAuditoria = new ArrayList<>();
    private List<LogAdministracion> ListSelecciAuditoria = new ArrayList<>();
    private List<LogAdministracion> ListArcAva = new ArrayList<>();
    private List<LogAdministracion> ListArcCli = new ArrayList<>();
    private List<LogAdministracion> ListCondicion = new ArrayList<>();
    private List<LogAdministracion> ListPreCondicion = new ArrayList<>();
    private List<LogAdministracion> ListArchivos = new ArrayList<>();
    private List<LogAdministracion> ListAnulaciones = new ArrayList<>();
    private List<LogAdministracion> ListChequeoItems = new ArrayList<>();
    private List<LogAdministracion> ListDevoluc = new ArrayList<>();
    private List<LogAdministracion> ListTipoConstrucc = new ArrayList<>();
    private List<LogAdministracion> ListModificacionInf = new ArrayList<>();
    private List<LogAdministracion> ListMedidas = new ArrayList<>();
    private List<LogAdministracion> ListTiemposAval = new ArrayList<>();
    private List<LogAdministracion> ListCargos = new ArrayList<>();
    private List<LogAdministracion> listEnvio = new ArrayList<>();
    private List<LogAdministracion> ListActividGest = new ArrayList<>();
    private List<LogAdministracion> ListRecordaGest = new ArrayList<>();
    private List<LogAdministracion> ListImpuestos = new ArrayList<>();
    private List<LogAdministracion> ListGestBancos = new ArrayList<>();
    private List<LogAdministracion> listTipoConcepto = new ArrayList<>();

    /*Variables para parametrizacion Bancos*/
    private String CuentaBancaria = "";
    private int CuentaContable = 0;

    /**
     * Variables tipo ArrayList Listas que cargan la informacion en componentes
     * de lista desplegable (selectedOneItem o combobox) *
     */
    private ArrayList<SelectItem> TipProEnt = new ArrayList<>();
    private ArrayList<SelectItem> ProEnt = new ArrayList<>();
    private ArrayList<SelectItem> ProEntAll = new ArrayList<>();
    private ArrayList<SelectItem> CargModulo = new ArrayList<>();
    private ArrayList<SelectItem> CargTipArchAva = new ArrayList<>();
    private ArrayList<SelectItem> CargTipArchCli = new ArrayList<>();
    private ArrayList<SelectItem> CargTabla = new ArrayList<>();
    private ArrayList<SelectItem> CargColumna = new ArrayList<>();
    private ArrayList<SelectItem> CargaTipoPredios = new ArrayList<>();
    private ArrayList<SelectItem> CargaTipoMaquinaria = new ArrayList<>();
    private ArrayList<SelectItem> CargaMotivoAnulaciones = new ArrayList<>();
    //Tatiana 28/03/2016
    private ArrayList<SelectItem> cargarfactConcepto = new ArrayList<>();
    private List SelectImpuestos = null;

    private List listaImpuestos = null;

    public String getCuentaBancaria() {
        return CuentaBancaria;
    }

    public void setCuentaBancaria(String CuentaBancaria) {
        this.CuentaBancaria = CuentaBancaria;
    }

    public int getCuentaContable() {
        return CuentaContable;
    }

    public void setCuentaContable(int CuentaContable) {
        this.CuentaContable = CuentaContable;
    }

    public ArrayList<SelectItem> getCargarfactConcepto() {
        return cargarfactConcepto;
    }

    public void setCargarfactConcepto(ArrayList<SelectItem> cargarfactConcepto) {
        this.cargarfactConcepto = cargarfactConcepto;
    }

    public ArrayList<SelectItem> getCargarBancos() {
        return CargarBancos;
    }

    public void setCargarBancos(ArrayList<SelectItem> CargarBancos) {
        this.CargarBancos = CargarBancos;
    }

    private ArrayList<SelectItem> CargarBancos = new ArrayList<>();
    /**
     * Variables tipo LogAdministracion que almacenan registros que sean
     * seleccionados de las diferentes tablas (datatable -> selection)
     *
     * @see LogAdministracion.java
     */
    LogAdministracion AdmTipDoc = new LogAdministracion();
    LogAdministracion AdmEstados = new LogAdministracion();
    LogAdministracion AdmCargos = new LogAdministracion();
    LogAdministracion AdmRoles = new LogAdministracion();
    LogAdministracion AdmProdEnt = new LogAdministracion();
    LogAdministracion AdmTipProdEnt = new LogAdministracion();
    LogAdministracion AdmRegimen = new LogAdministracion();
    LogAdministracion AdmTipRegimen = new LogAdministracion();
    LogAdministracion AdmEnvio = new LogAdministracion();
    LogAdministracion AdmArchivos = new LogAdministracion();
    LogAdministracion AdmTipoBienes = new LogAdministracion();
    LogAdministracion AdmAnulaciones = new LogAdministracion();
    LogAdministracion AdmListaChequeo = new LogAdministracion();
    LogAdministracion AdmDevoluc = new LogAdministracion();
    LogAdministracion AdmTipoConstrucc = new LogAdministracion();
    LogAdministracion AdmModificacInf = new LogAdministracion();
    LogAdministracion AdmMedidas = new LogAdministracion();
    LogAdministracion AdmTiermposAval = new LogAdministracion();
    LogAdministracion AdmArchivAval = new LogAdministracion();
    LogAdministracion AdmArchvClie = new LogAdministracion();
    LogAdministracion AdmArchCondi = new LogAdministracion();
    LogAdministracion AdmArchPreCond = new LogAdministracion();
    LogAdministracion AdmActidiGest = new LogAdministracion();
    LogAdministracion AdmRecordatGest = new LogAdministracion();
    LogAdministracion AdmImpuestos = new LogAdministracion();
    LogAdministracion AdmGesBancos = new LogAdministracion();
    //Tatiana 28/03/2016
    LogAdministracion AdmTipoFactConcep = new LogAdministracion();
    private ArrayList<SelectItem> CargarTipi = new ArrayList<>();

    /**
     * Instancia de objetos tipo LogAdministracion que obtienen los atributos y
     * metodos de la clase LogAdministracion.java
     *
     * @see LogAdministracion.java
     */
    LogAdministracion Adm = new LogAdministracion();
    LogAdministracion Admin = new LogAdministracion();
    LogAdministracion Ad = new LogAdministracion();
    /**
     * Instancia de objetos tipo LogUbicacion que obtienen los atributos y
     * metodos de la clase LogUbicacion.java
     *
     * @see LogUbicacion.java
     */

    /**
     * Instancia de objetos tipo LogEmpleado que obtienen los atributos y
     * metodos de la clase LogEmpleado.java
     *
     * @see LogEmpleado.java
     */
    LogEmpleado Emp = new LogEmpleado();
    /**
     * Variables utilizadas para almacenar los titulos de los distinto dialogos
     * del modulo de administración
     */
    private String TituloDialogTipDoc;
    private String TituloDialogEstado;
    private String TituloDialogCargo;
    private String TituloDialogRoles;
    private String TituloDialogProdEnti;
    private String TituloDialogTipProdEnt;
    private String TituloDialogRegimen;
    private String TituloDialogTipRegimen;
    private String TituloDialogEnvio;
    private String TituloDialogArchivos;
    private String TituloDialogAnulaciones;
    private String TituloDialogListChequeo;
    private String TituloDialogDevoluc;
    private String TituloDialogTipoConstrucc;
    private String TituloDialogModificacionInf;
    private String TituloDialogMedidas;
    private String TituloDialogTiemposAval;
    private String TituloDialogActividad;
    private String TituloDialogRecordat;
    private String TituloDialogImpuestos;
    private String TituloDialogGesBancos;

    //Tatiana 28/03/16
    private String TituloDialogTipConcp;
    private String TituloDialogTipValor;
    /**
     * Variables utilizadas para almacenar datos e información de los diferentes
     * procesos del modulo de administración
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    

 
    private int CodTable;
    private String NombreTable;
    private String DesTable;
    private String NombretipoProducto;
    private String Destipoproducto;
    private String CodProEnt;
    /**
     * variables para gestión de reglas de procesos *
     */
    private String OpcionRegla;
    private int TipoRegla;
    private int NumeroRegRegla;
    private String OpcionHabilitar;
    private boolean OpcionArchivoAvaluo = false;
    private boolean OpcionArchivoCliente = false;
    private boolean OpcionCondicion = false;
    private boolean OpcionListaChequeo = false;
    private String nombreTipAvaluo;
    private String nombre_Proceso;
    private String resultadoParametro;
    private int codModulo;
    private int codModulo1;
    private int codTipAvaluo;
    private int codTipProEnt;
    private int codParametro;
    private int codParametro1;
    private String estadoArcAva;
    private String estadoArcCli;
    int RegArcAva = 0;
    int CondProc = 0;
    /**
     * variables para gestión de reglas de archivos *
     */
    private String OpcionArchivo;
    private boolean OpcionAvaluo = false;
    private boolean OpcionAvaluoModif = false;
    /**
     * variables para gestión de producto entidad *
     */
    private String selectproent = "";
    private boolean estadoProent = false;
    private boolean estadoTiproent = false;
    /**
     * variables para gestión de regimen y tipo regimen *
     */
    private boolean selecRegimen = false;
    private String selecRegimennn = "";
    private boolean panelregimen = false;
    private boolean paneltiporegimen = false;
    private int CodTipoRegimen;
    private String NombreTipoRegimen;
    private String EstadoTipoRegimen;
    private int CodRegimen;
    /**
     * variables para gestión de envio *
     */
    private String nombreParametro;
    private boolean estadofrmEnvio;
    /**
     * Variables para la gestion de pisos *
     */
    private String selectcionParametro;
    private boolean estadofrmPisos;
    /**
     * variables para gestión de auditoria *
     */
    private String opcionFiltroAuditoria;
    private int empleadoAuditoria;
    private Date fechafinal;
    private String fechafinalString;
    private Date fechainicial;
    private String fechainicialString;
    private String fecha_actual;
    private boolean estadoTablaAuditoria;
    private List<SelectItem> ListEmpAuditoria = new ArrayList<>();
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat FormatHora = new SimpleDateFormat("HH:mm");
    /**
     * variables para gestión de actividades y recordatorios *
     */
    private int codActividad;
    private String nombreActividad;
    private int codRecordato;
    private String nombreRecordatori;
    private String estadoRadioSeleccionActiviYRecord;
    /**
     * variables para gestión de lista de chequeo *
     */
    private int codiItem;
    private String moduloItem;
    private String descripItem;
    /**
     * variables para gestión de devolucioones de avaluos *
     */
    private int codiDevol;
    private String moduloDevol;
    private String descripDevol;
    /**
     * variables para gestión de tipos de construccion *
     */
    private int codTipoConstruc;
    private String nombreTipoConstruc;
    /**
     * variables para gestión de modificacion de informes de avaluo *
     */
    private int codModificac;
    private String nombreModificacion;
    /**
     * variables para gestión de medidas*
     */
    private int codMedida;
    private String nombreMedida;
    /**
     * variables para gestión de tiempos de los avaluos*
     */
    private int codTiempoAval;
    private int codProdEntTiemAval;
    private String nombreTiempoAval;
    /**
     * variables para gestión de impuestos (facturacion)*
     */
    private int codImpuesto;
    private String nombreImpuesto;
    /**
     * variables para gestión de bancos (anticipos)*
     */
    private int codBanco;
    private String nombreBanco;
    /**
     * Variable para aprobacion de impresion en el area de revision *
     */
    private boolean AprobacionRevision;

    /* variables para gestión de Tipos de Concepto (Facturacion)*
     */
    private int codConcepto;
    private String nombreConcepto;
    /**
     *
     *
     *
     *
     * /**
     * Variable tipo ResultSet para traer los resultados de las consultas *
     */
    ResultSet Dat = null;

    private String DescrpCargo = "";
    /**
     *
     *
     *
     *
     *
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
  
 
    public String getDescrpCargo() {
        return DescrpCargo;
    }

    public void setDescrpCargo(String DescrpCargo) {
        this.DescrpCargo = DescrpCargo;
    }

   

    public String getTituloDialogTipValor() {
        return TituloDialogTipValor;
    }

    public void setTituloDialogTipValor(String TituloDialogTipValor) {
        this.TituloDialogTipValor = TituloDialogTipValor;
    }

    public ArrayList<SelectItem> getCargarTipi() {
        return CargarTipi;
    }

    public void setCargarTipi(ArrayList<SelectItem> CargarTipi) {
        this.CargarTipi = CargarTipi;
    }

    public List getSelectImpuestos() {
        return SelectImpuestos;
    }

    public void setSelectImpuestos(List SelectImpuestos) {
        this.SelectImpuestos = SelectImpuestos;
    }

    public ArrayList<SelectItem> getCargarReteIva() {
        return CargarReteIva;
    }

    public void setCargarReteIva(ArrayList<SelectItem> CargarReteIva) {
        this.CargarReteIva = CargarReteIva;
    }

    public List getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return List tipo LogAdministracion
     */
    //Tatiana 28/03/2016
    public String getTituloDialogTipConcp() {
        return TituloDialogTipConcp;
    }

    public void setTituloDialogTipConcp(String TituloDialogTipConcp) {
        this.TituloDialogTipConcp = TituloDialogTipConcp;
    }

    public List<LogAdministracion> getListTipoConcepto() {
        return listTipoConcepto;
    }

    public void setListTipoConcepto(List<LogAdministracion> listTipoConcepto) {
        this.listTipoConcepto = listTipoConcepto;
    }

    public LogAdministracion getAdmTipoFactConcep() {
        return AdmTipoFactConcep;
    }

    public void setAdmTipoFactConcep(LogAdministracion AdmTipoFactConcep) {
        this.AdmTipoFactConcep = AdmTipoFactConcep;
    }

    public int getCodConcepto() {
        return codConcepto;
    }

    public void setCodConcepto(int codConcepto) {
        this.codConcepto = codConcepto;
    }

    public String getNombreConcepto() {
        return nombreConcepto;
    }

    public void setNombreConcepto(String nombreConcepto) {
        this.nombreConcepto = nombreConcepto;
    }

    public ArrayList<SelectItem> getCargarFuente() {
        return CargarFuente;
    }

    public void setCargarFuente(ArrayList<SelectItem> CargarFuente) {
        this.CargarFuente = CargarFuente;
    }

    public ArrayList<SelectItem> getCargarICA() {
        return CargarICA;
    }

    public void setCargarICA(ArrayList<SelectItem> CargarICA) {
        this.CargarICA = CargarICA;
    }

    public List<LogAdministracion> getListEstado() {
        return ListEstado;
    }

    public void setListEstado(List<LogAdministracion> ListEstado) {
        this.ListEstado = ListEstado;
    }

    public List<LogAdministracion> getListRegimen() {
        return ListRegimen;
    }

    public void setListRegimen(List<LogAdministracion> ListRegimen) {
        this.ListRegimen = ListRegimen;
    }

    public List<LogAdministracion> getListTipoRegimen() {
        return ListTipoRegimen;
    }

    public void setListTipoRegimen(List<LogAdministracion> ListTipoRegimen) {
        this.ListTipoRegimen = ListTipoRegimen;
    }

    public List<LogAdministracion> getListTipDocumento() {
        return ListTipDocumento;
    }

    public void setListTipDocumento(List<LogAdministracion> ListTipDocumento) {
        this.ListTipDocumento = ListTipDocumento;
    }

    public List<LogAdministracion> getListProducEnt() {
        return ListProducEnt;
    }

    public void setListProducEnt(List<LogAdministracion> ListProducEnt) {
        this.ListProducEnt = ListProducEnt;
    }

    public List<LogAdministracion> getListTipProducEnt() {
        return ListTipProducEnt;
    }

    public void setListTipProducEnt(List<LogAdministracion> ListTipProducEnt) {
        this.ListTipProducEnt = ListTipProducEnt;
    }

    public List<LogAdministracion> getListRoles() {
        return ListRoles;
    }

    public void setListRoles(List<LogAdministracion> ListRoles) {
        this.ListRoles = ListRoles;
    }

    public List<LogAdministracion> getListAcessos() {
        return ListAcessos;
    }

    public void setListAcessos(List<LogAdministracion> ListAcessos) {
        this.ListAcessos = ListAcessos;
    }

    public List<LogAdministracion> getListAuditoria() {
        return ListAuditoria;
    }

    public void setListAuditoria(List<LogAdministracion> ListAuditoria) {
        this.ListAuditoria = ListAuditoria;
    }

    public List<LogAdministracion> getListSelecciAuditoria() {
        return ListSelecciAuditoria;
    }

    public void setListSelecciAuditoria(List<LogAdministracion> ListSelecciAuditoria) {
        this.ListSelecciAuditoria = ListSelecciAuditoria;
    }

    public List<LogAdministracion> getListArcAva() {
        return ListArcAva;
    }

    public void setListArcAva(List<LogAdministracion> ListArcAva) {
        this.ListArcAva = ListArcAva;
    }

    public List<LogAdministracion> getListArcCli() {
        return ListArcCli;
    }

    public void setListArcCli(List<LogAdministracion> ListArcCli) {
        this.ListArcCli = ListArcCli;
    }

    public List<LogAdministracion> getListCondicion() {
        return ListCondicion;
    }

    public void setListCondicion(List<LogAdministracion> ListCondicion) {
        this.ListCondicion = ListCondicion;
    }

    public List<LogAdministracion> getListPreCondicion() {
        return ListPreCondicion;
    }

    public void setListPreCondicion(List<LogAdministracion> ListPreCondicion) {
        this.ListPreCondicion = ListPreCondicion;
    }

    public List<LogAdministracion> getListArchivos() {
        return ListArchivos;
    }

    public void setListArchivos(List<LogAdministracion> ListArchivos) {
        this.ListArchivos = ListArchivos;
    }

    public List<LogAdministracion> getListAnulaciones() {
        return ListAnulaciones;
    }

    public void setListAnulaciones(List<LogAdministracion> ListAnulaciones) {
        this.ListAnulaciones = ListAnulaciones;
    }

    public List<LogAdministracion> getListChequeoItems() {
        return ListChequeoItems;
    }

    public void setListChequeoItems(List<LogAdministracion> ListChequeoItems) {
        this.ListChequeoItems = ListChequeoItems;
    }

    public List<LogAdministracion> getListDevoluc() {
        return ListDevoluc;
    }

    public void setListDevoluc(List<LogAdministracion> ListDevoluc) {
        this.ListDevoluc = ListDevoluc;
    }

    public List<LogAdministracion> getListTipoConstrucc() {
        return ListTipoConstrucc;
    }

    public void setListTipoConstrucc(List<LogAdministracion> ListTipoConstrucc) {
        this.ListTipoConstrucc = ListTipoConstrucc;
    }

    public List<LogAdministracion> getListModificacionInf() {
        return ListModificacionInf;
    }

    public void setListModificacionInf(List<LogAdministracion> ListModificacionInf) {
        this.ListModificacionInf = ListModificacionInf;
    }

    public List<LogAdministracion> getListMedidas() {
        return ListMedidas;
    }

    public void setListMedidas(List<LogAdministracion> ListMedidas) {
        this.ListMedidas = ListMedidas;
    }

    public List<LogAdministracion> getListTiemposAval() {
        return ListTiemposAval;
    }

    public void setListTiemposAval(List<LogAdministracion> ListTiemposAval) {
        this.ListTiemposAval = ListTiemposAval;
    }

    public List<LogAdministracion> getListCargos() {
        return ListCargos;
    }

    public void setListCargos(List<LogAdministracion> ListCargos) {
        this.ListCargos = ListCargos;
    }

    public List<LogAdministracion> getListEnvio() {
        return listEnvio;
    }

    public void setListEnvio(List<LogAdministracion> listEnvio) {
        this.listEnvio = listEnvio;
    }

    public List<LogAdministracion> getListActividGest() {
        return ListActividGest;
    }

    public void setListActividGest(List<LogAdministracion> ListActividGest) {
        this.ListActividGest = ListActividGest;
    }

    public List<LogAdministracion> getListRecordaGest() {
        return ListRecordaGest;
    }

    public void setListRecordaGest(List<LogAdministracion> ListRecordaGest) {
        this.ListRecordaGest = ListRecordaGest;
    }

    public List<LogAdministracion> getListImpuestos() {
        return ListImpuestos;
    }

    public void setListImpuestos(List<LogAdministracion> ListImpuestos) {
        this.ListImpuestos = ListImpuestos;
    }

    public List<LogAdministracion> getListGestBancos() {
        return ListGestBancos;
    }

    public void setListGestBancos(List<LogAdministracion> ListGestBancos) {
        this.ListGestBancos = ListGestBancos;
    }

    public ArrayList<SelectItem> getTipProEnt() {
        return TipProEnt;
    }

    public void setTipProEnt(ArrayList<SelectItem> TipProEnt) {
        this.TipProEnt = TipProEnt;
    }

    public ArrayList<SelectItem> getProEnt() {
        return ProEnt;
    }

    public void setProEnt(ArrayList<SelectItem> ProEnt) {
        this.ProEnt = ProEnt;
    }

    public ArrayList<SelectItem> getProEntAll() {
        return ProEntAll;
    }

    public void setProEntAll(ArrayList<SelectItem> ProEntAll) {
        this.ProEntAll = ProEntAll;
    }

    public ArrayList<SelectItem> getCargModulo() {
        return CargModulo;
    }

    public void setCargModulo(ArrayList<SelectItem> CargModulo) {
        this.CargModulo = CargModulo;
    }

    public ArrayList<SelectItem> getCargTipArchAva() {
        return CargTipArchAva;
    }

    public void setCargTipArchAva(ArrayList<SelectItem> CargTipArchAva) {
        this.CargTipArchAva = CargTipArchAva;
    }

    public ArrayList<SelectItem> getCargTipArchCli() {
        return CargTipArchCli;
    }

    public void setCargTipArchCli(ArrayList<SelectItem> CargTipArchCli) {
        this.CargTipArchCli = CargTipArchCli;
    }

    public ArrayList<SelectItem> getCargTabla() {
        return CargTabla;
    }

    public void setCargTabla(ArrayList<SelectItem> CargTabla) {
        this.CargTabla = CargTabla;
    }

    public ArrayList<SelectItem> getCargColumna() {
        return CargColumna;
    }

    public void setCargColumna(ArrayList<SelectItem> CargColumna) {
        this.CargColumna = CargColumna;
    }

    public ArrayList<SelectItem> getCargaTipoPredios() {
        return CargaTipoPredios;
    }

    public void setCargaTipoPredios(ArrayList<SelectItem> CargaTipoPredios) {
        this.CargaTipoPredios = CargaTipoPredios;
    }

    public ArrayList<SelectItem> getCargaTipoMaquinaria() {
        return CargaTipoMaquinaria;
    }

    public void setCargaTipoMaquinaria(ArrayList<SelectItem> CargaTipoMaquinaria) {
        this.CargaTipoMaquinaria = CargaTipoMaquinaria;
    }

    public ArrayList<SelectItem> getCargaMotivoAnulaciones() {
        return CargaMotivoAnulaciones;
    }

    public void setCargaMotivoAnulaciones(ArrayList<SelectItem> CargaMotivoAnulaciones) {
        this.CargaMotivoAnulaciones = CargaMotivoAnulaciones;
    }

    public LogAdministracion getAdmTipDoc() {
        return AdmTipDoc;
    }

    public void setAdmTipDoc(LogAdministracion AdmTipDoc) {
        this.AdmTipDoc = AdmTipDoc;
    }

    public LogAdministracion getAdmEstados() {
        return AdmEstados;
    }

    public void setAdmEstados(LogAdministracion AdmEstados) {
        this.AdmEstados = AdmEstados;
    }

    public LogAdministracion getAdmCargos() {
        return AdmCargos;
    }

    public void setAdmCargos(LogAdministracion AdmCargos) {
        this.AdmCargos = AdmCargos;
    }

    public LogAdministracion getAdmRoles() {
        return AdmRoles;
    }

    public void setAdmRoles(LogAdministracion AdmRoles) {
        this.AdmRoles = AdmRoles;
    }

    public LogAdministracion getAdmProdEnt() {
        return AdmProdEnt;
    }

    public void setAdmProdEnt(LogAdministracion AdmProdEnt) {
        this.AdmProdEnt = AdmProdEnt;
    }

    public LogAdministracion getAdmTipProdEnt() {
        return AdmTipProdEnt;
    }

    public void setAdmTipProdEnt(LogAdministracion AdmTipProdEnt) {
        this.AdmTipProdEnt = AdmTipProdEnt;
    }

    public LogAdministracion getAdmRegimen() {
        return AdmRegimen;
    }

    public void setAdmRegimen(LogAdministracion AdmRegimen) {
        this.AdmRegimen = AdmRegimen;
    }

    public LogAdministracion getAdmTipRegimen() {
        return AdmTipRegimen;
    }

    public void setAdmTipRegimen(LogAdministracion AdmTipRegimen) {
        this.AdmTipRegimen = AdmTipRegimen;
    }

    public LogAdministracion getAdmEnvio() {
        return AdmEnvio;
    }

    public void setAdmEnvio(LogAdministracion AdmEnvio) {
        this.AdmEnvio = AdmEnvio;
    }

    public LogAdministracion getAdmArchivos() {
        return AdmArchivos;
    }

    public void setAdmArchivos(LogAdministracion AdmArchivos) {
        this.AdmArchivos = AdmArchivos;
    }

    public LogAdministracion getAdmTipoBienes() {
        return AdmTipoBienes;
    }

    public void setAdmTipoBienes(LogAdministracion AdmTipoBienes) {
        this.AdmTipoBienes = AdmTipoBienes;
    }

    public LogAdministracion getAdmAnulaciones() {
        return AdmAnulaciones;
    }

    public void setAdmAnulaciones(LogAdministracion AdmAnulaciones) {
        this.AdmAnulaciones = AdmAnulaciones;
    }

    public LogAdministracion getAdmListaChequeo() {
        return AdmListaChequeo;
    }

    public void setAdmListaChequeo(LogAdministracion AdmListaChequeo) {
        this.AdmListaChequeo = AdmListaChequeo;
    }

    public LogAdministracion getAdmDevoluc() {
        return AdmDevoluc;
    }

    public void setAdmDevoluc(LogAdministracion AdmDevoluc) {
        this.AdmDevoluc = AdmDevoluc;
    }

    public LogAdministracion getAdmTipoConstrucc() {
        return AdmTipoConstrucc;
    }

    public void setAdmTipoConstrucc(LogAdministracion AdmTipoConstrucc) {
        this.AdmTipoConstrucc = AdmTipoConstrucc;
    }

    public LogAdministracion getAdmModificacInf() {
        return AdmModificacInf;
    }

    public void setAdmModificacInf(LogAdministracion AdmModificacInf) {
        this.AdmModificacInf = AdmModificacInf;
    }

    public LogAdministracion getAdmMedidas() {
        return AdmMedidas;
    }

    public void setAdmMedidas(LogAdministracion AdmMedidas) {
        this.AdmMedidas = AdmMedidas;
    }

    public LogAdministracion getAdmTiermposAval() {
        return AdmTiermposAval;
    }

    public void setAdmTiermposAval(LogAdministracion AdmTiermposAval) {
        this.AdmTiermposAval = AdmTiermposAval;
    }

    public LogAdministracion getAdmArchivAval() {
        return AdmArchivAval;
    }

    public void setAdmArchivAval(LogAdministracion AdmArchivAval) {
        this.AdmArchivAval = AdmArchivAval;
    }

    public LogAdministracion getAdmArchvClie() {
        return AdmArchvClie;
    }

    public void setAdmArchvClie(LogAdministracion AdmArchvClie) {
        this.AdmArchvClie = AdmArchvClie;
    }

    public LogAdministracion getAdmArchCondi() {
        return AdmArchCondi;
    }

    public void setAdmArchCondi(LogAdministracion AdmArchCondi) {
        this.AdmArchCondi = AdmArchCondi;
    }

    public LogAdministracion getAdmArchPreCond() {
        return AdmArchPreCond;
    }

    public void setAdmArchPreCond(LogAdministracion AdmArchPreCond) {
        this.AdmArchPreCond = AdmArchPreCond;
    }

    public LogAdministracion getAdmActidiGest() {
        return AdmActidiGest;
    }

    public void setAdmActidiGest(LogAdministracion AdmActidiGest) {
        this.AdmActidiGest = AdmActidiGest;
    }

    public LogAdministracion getAdmRecordatGest() {
        return AdmRecordatGest;
    }

    public void setAdmRecordatGest(LogAdministracion AdmRecordatGest) {
        this.AdmRecordatGest = AdmRecordatGest;
    }

    public LogAdministracion getAdmImpuestos() {
        return AdmImpuestos;
    }

    public void setAdmImpuestos(LogAdministracion AdmImpuestos) {
        this.AdmImpuestos = AdmImpuestos;
    }

    public LogAdministracion getAdmGesBancos() {
        return AdmGesBancos;
    }

    public void setAdmGesBancos(LogAdministracion AdmGesBancos) {
        this.AdmGesBancos = AdmGesBancos;
    }

    public LogAdministracion getAdm() {
        return Adm;
    }

    public void setAdm(LogAdministracion Adm) {
        this.Adm = Adm;
    }

    public LogAdministracion getAdmin() {
        return Admin;
    }

    public void setAdmin(LogAdministracion Admin) {
        this.Admin = Admin;
    }

    public LogAdministracion getAd() {
        return Ad;
    }

    public void setAd(LogAdministracion Ad) {
        this.Ad = Ad;
    }

   

    public LogEmpleado getEmp() {
        return Emp;
    }

    public void setEmp(LogEmpleado Emp) {
        this.Emp = Emp;
    }

    public String getTituloDialogTipDoc() {
        return TituloDialogTipDoc;
    }

    public void setTituloDialogTipDoc(String TituloDialogTipDoc) {
        this.TituloDialogTipDoc = TituloDialogTipDoc;
    }

    public String getTituloDialogEstado() {
        return TituloDialogEstado;
    }

    public void setTituloDialogEstado(String TituloDialogEstado) {
        this.TituloDialogEstado = TituloDialogEstado;
    }

    public String getTituloDialogCargo() {
        return TituloDialogCargo;
    }

    public void setTituloDialogCargo(String TituloDialogCargo) {
        this.TituloDialogCargo = TituloDialogCargo;
    }

    public String getTituloDialogRoles() {
        return TituloDialogRoles;
    }

    public void setTituloDialogRoles(String TituloDialogRoles) {
        this.TituloDialogRoles = TituloDialogRoles;
    }

    public String getTituloDialogProdEnti() {
        return TituloDialogProdEnti;
    }

    public void setTituloDialogProdEnti(String TituloDialogProdEnti) {
        this.TituloDialogProdEnti = TituloDialogProdEnti;
    }

    public String getTituloDialogTipProdEnt() {
        return TituloDialogTipProdEnt;
    }

    public void setTituloDialogTipProdEnt(String TituloDialogTipProdEnt) {
        this.TituloDialogTipProdEnt = TituloDialogTipProdEnt;
    }

    public String getTituloDialogRegimen() {
        return TituloDialogRegimen;
    }

    public void setTituloDialogRegimen(String TituloDialogRegimen) {
        this.TituloDialogRegimen = TituloDialogRegimen;
    }

    public String getTituloDialogTipRegimen() {
        return TituloDialogTipRegimen;
    }

    public void setTituloDialogTipRegimen(String TituloDialogTipRegimen) {
        this.TituloDialogTipRegimen = TituloDialogTipRegimen;
    }

    public String getTituloDialogEnvio() {
        return TituloDialogEnvio;
    }

    public void setTituloDialogEnvio(String TituloDialogEnvio) {
        this.TituloDialogEnvio = TituloDialogEnvio;
    }

    public String getTituloDialogArchivos() {
        return TituloDialogArchivos;
    }

    public void setTituloDialogArchivos(String TituloDialogArchivos) {
        this.TituloDialogArchivos = TituloDialogArchivos;
    }

    public String getTituloDialogAnulaciones() {
        return TituloDialogAnulaciones;
    }

    public void setTituloDialogAnulaciones(String TituloDialogAnulaciones) {
        this.TituloDialogAnulaciones = TituloDialogAnulaciones;
    }

    public String getTituloDialogListChequeo() {
        return TituloDialogListChequeo;
    }

    public void setTituloDialogListChequeo(String TituloDialogListChequeo) {
        this.TituloDialogListChequeo = TituloDialogListChequeo;
    }

    public String getTituloDialogDevoluc() {
        return TituloDialogDevoluc;
    }

    public void setTituloDialogDevoluc(String TituloDialogDevoluc) {
        this.TituloDialogDevoluc = TituloDialogDevoluc;
    }

    public String getTituloDialogTipoConstrucc() {
        return TituloDialogTipoConstrucc;
    }

    public void setTituloDialogTipoConstrucc(String TituloDialogTipoConstrucc) {
        this.TituloDialogTipoConstrucc = TituloDialogTipoConstrucc;
    }

    public String getTituloDialogModificacionInf() {
        return TituloDialogModificacionInf;
    }

    public void setTituloDialogModificacionInf(String TituloDialogModificacionInf) {
        this.TituloDialogModificacionInf = TituloDialogModificacionInf;
    }

    public String getTituloDialogMedidas() {
        return TituloDialogMedidas;
    }

    public void setTituloDialogMedidas(String TituloDialogMedidas) {
        this.TituloDialogMedidas = TituloDialogMedidas;
    }

    public String getTituloDialogTiemposAval() {
        return TituloDialogTiemposAval;
    }

    public void setTituloDialogTiemposAval(String TituloDialogTiemposAval) {
        this.TituloDialogTiemposAval = TituloDialogTiemposAval;
    }

    public String getTituloDialogActividad() {
        return TituloDialogActividad;
    }

    public void setTituloDialogActividad(String TituloDialogActividad) {
        this.TituloDialogActividad = TituloDialogActividad;
    }

    public String getTituloDialogRecordat() {
        return TituloDialogRecordat;
    }

    public void setTituloDialogRecordat(String TituloDialogRecordat) {
        this.TituloDialogRecordat = TituloDialogRecordat;
    }

    public String getTituloDialogImpuestos() {
        return TituloDialogImpuestos;
    }

    public void setTituloDialogImpuestos(String TituloDialogImpuestos) {
        this.TituloDialogImpuestos = TituloDialogImpuestos;
    }

    public String getTituloDialogGesBancos() {
        return TituloDialogGesBancos;
    }

    public void setTituloDialogGesBancos(String TituloDialogGesBancos) {
        this.TituloDialogGesBancos = TituloDialogGesBancos;
    }

    public int getCodImpuesto() {
        return codImpuesto;
    }

    public void setCodImpuesto(int codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    public int getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(int codBanco) {
        this.codBanco = codBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getCodTable() {
        return CodTable;
    }

    public void setCodTable(int CodTable) {
        this.CodTable = CodTable;
    }

    public String getNombreTable() {
        return NombreTable;
    }

    public void setNombreTable(String NombreTable) {
        this.NombreTable = NombreTable;
    }

    public String getDesTable() {
        return DesTable;
    }

    public void setDesTable(String DesTable) {
        this.DesTable = DesTable;
    }

    public String getNombretipoProducto() {
        return NombretipoProducto;
    }

    public void setNombretipoProducto(String NombretipoProducto) {
        this.NombretipoProducto = NombretipoProducto;
    }

    public String getDestipoproducto() {
        return Destipoproducto;
    }

    public void setDestipoproducto(String Destipoproducto) {
        this.Destipoproducto = Destipoproducto;
    }

    public String getCodProEnt() {
        return CodProEnt;
    }

    public void setCodProEnt(String CodProEnt) {
        this.CodProEnt = CodProEnt;
    }

    public String getOpcionRegla() {
        return OpcionRegla;
    }

    public void setOpcionRegla(String OpcionRegla) {
        this.OpcionRegla = OpcionRegla;
    }

    public int getTipoRegla() {
        return TipoRegla;
    }

    public void setTipoRegla(int TipoRegla) {
        this.TipoRegla = TipoRegla;
    }

    public int getNumeroRegRegla() {
        return NumeroRegRegla;
    }

    public void setNumeroRegRegla(int NumeroRegRegla) {
        this.NumeroRegRegla = NumeroRegRegla;
    }

    public String getOpcionHabilitar() {
        return OpcionHabilitar;
    }

    public void setOpcionHabilitar(String OpcionHabilitar) {
        this.OpcionHabilitar = OpcionHabilitar;
    }

    public boolean isOpcionArchivoAvaluo() {
        return OpcionArchivoAvaluo;
    }

    public void setOpcionArchivoAvaluo(boolean OpcionArchivoAvaluo) {
        this.OpcionArchivoAvaluo = OpcionArchivoAvaluo;
    }

    public boolean isOpcionArchivoCliente() {
        return OpcionArchivoCliente;
    }

    public void setOpcionArchivoCliente(boolean OpcionArchivoCliente) {
        this.OpcionArchivoCliente = OpcionArchivoCliente;
    }

    public boolean isOpcionCondicion() {
        return OpcionCondicion;
    }

    public void setOpcionCondicion(boolean OpcionCondicion) {
        this.OpcionCondicion = OpcionCondicion;
    }

    public boolean isOpcionListaChequeo() {
        return OpcionListaChequeo;
    }

    public void setOpcionListaChequeo(boolean OpcionListaChequeo) {
        this.OpcionListaChequeo = OpcionListaChequeo;
    }

    public String getNombreTipAvaluo() {
        return nombreTipAvaluo;
    }

    public void setNombreTipAvaluo(String nombreTipAvaluo) {
        this.nombreTipAvaluo = nombreTipAvaluo;
    }

    public String getNombre_Proceso() {
        return nombre_Proceso;
    }

    public void setNombre_Proceso(String nombre_Proceso) {
        this.nombre_Proceso = nombre_Proceso;
    }

    public String getResultadoParametro() {
        return resultadoParametro;
    }

    public void setResultadoParametro(String resultadoParametro) {
        this.resultadoParametro = resultadoParametro;
    }

    public int getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(int codModulo) {
        this.codModulo = codModulo;
    }

    public int getCodModulo1() {
        return codModulo1;
    }

    public void setCodModulo1(int codModulo1) {
        this.codModulo1 = codModulo1;
    }

    public int getCodTipAvaluo() {
        return codTipAvaluo;
    }

    public void setCodTipAvaluo(int codTipAvaluo) {
        this.codTipAvaluo = codTipAvaluo;
    }

    public int getCodTipProEnt() {
        return codTipProEnt;
    }

    public void setCodTipProEnt(int codTipProEnt) {
        this.codTipProEnt = codTipProEnt;
    }

    public int getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(int codParametro) {
        this.codParametro = codParametro;
    }

    public int getCodParametro1() {
        return codParametro1;
    }

    public void setCodParametro1(int codParametro1) {
        this.codParametro1 = codParametro1;
    }

    public String getEstadoArcAva() {
        return estadoArcAva;
    }

    public void setEstadoArcAva(String estadoArcAva) {
        this.estadoArcAva = estadoArcAva;
    }

    public String getEstadoArcCli() {
        return estadoArcCli;
    }

    public void setEstadoArcCli(String estadoArcCli) {
        this.estadoArcCli = estadoArcCli;
    }

    public int getRegArcAva() {
        return RegArcAva;
    }

    public void setRegArcAva(int RegArcAva) {
        this.RegArcAva = RegArcAva;
    }

    public int getCondProc() {
        return CondProc;
    }

    public void setCondProc(int CondProc) {
        this.CondProc = CondProc;
    }

    public String getOpcionArchivo() {
        return OpcionArchivo;
    }

    public void setOpcionArchivo(String OpcionArchivo) {
        this.OpcionArchivo = OpcionArchivo;
    }

    public boolean isOpcionAvaluo() {
        return OpcionAvaluo;
    }

    public void setOpcionAvaluo(boolean OpcionAvaluo) {
        this.OpcionAvaluo = OpcionAvaluo;
    }

    public boolean isOpcionAvaluoModif() {
        return OpcionAvaluoModif;
    }

    public void setOpcionAvaluoModif(boolean OpcionAvaluoModif) {
        this.OpcionAvaluoModif = OpcionAvaluoModif;
    }

    public String getSelectproent() {
        return selectproent;
    }

    public void setSelectproent(String selectproent) {
        this.selectproent = selectproent;
    }

    public boolean isEstadoProent() {
        return estadoProent;
    }

    public void setEstadoProent(boolean estadoProent) {
        this.estadoProent = estadoProent;
    }

    public boolean isEstadoTiproent() {
        return estadoTiproent;
    }

    public void setEstadoTiproent(boolean estadoTiproent) {
        this.estadoTiproent = estadoTiproent;
    }

    public boolean isSelecRegimen() {
        return selecRegimen;
    }

    public void setSelecRegimen(boolean selecRegimen) {
        this.selecRegimen = selecRegimen;
    }

    public String getSelecRegimennn() {
        return selecRegimennn;
    }

    public void setSelecRegimennn(String selecRegimennn) {
        this.selecRegimennn = selecRegimennn;
    }

    public boolean isPanelregimen() {
        return panelregimen;
    }

    public void setPanelregimen(boolean panelregimen) {
        this.panelregimen = panelregimen;
    }

    public boolean isPaneltiporegimen() {
        return paneltiporegimen;
    }

    public void setPaneltiporegimen(boolean paneltiporegimen) {
        this.paneltiporegimen = paneltiporegimen;
    }

    public int getCodTipoRegimen() {
        return CodTipoRegimen;
    }

    public void setCodTipoRegimen(int CodTipoRegimen) {
        this.CodTipoRegimen = CodTipoRegimen;
    }

    public String getNombreTipoRegimen() {
        return NombreTipoRegimen;
    }

    public void setNombreTipoRegimen(String NombreTipoRegimen) {
        this.NombreTipoRegimen = NombreTipoRegimen;
    }

    public String getEstadoTipoRegimen() {
        return EstadoTipoRegimen;
    }

    public void setEstadoTipoRegimen(String EstadoTipoRegimen) {
        this.EstadoTipoRegimen = EstadoTipoRegimen;
    }

    public int getCodRegimen() {
        return CodRegimen;
    }

    public void setCodRegimen(int CodRegimen) {
        this.CodRegimen = CodRegimen;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public boolean isEstadofrmEnvio() {
        return estadofrmEnvio;
    }

    public void setEstadofrmEnvio(boolean estadofrmEnvio) {
        this.estadofrmEnvio = estadofrmEnvio;
    }

    public String getSelectcionParametro() {
        return selectcionParametro;
    }

    public void setSelectcionParametro(String selectcionParametro) {
        this.selectcionParametro = selectcionParametro;
    }

    public boolean isEstadofrmPisos() {
        return estadofrmPisos;
    }

    public void setEstadofrmPisos(boolean estadofrmPisos) {
        this.estadofrmPisos = estadofrmPisos;
    }

    public String getOpcionFiltroAuditoria() {
        return opcionFiltroAuditoria;
    }

    public void setOpcionFiltroAuditoria(String opcionFiltroAuditoria) {
        this.opcionFiltroAuditoria = opcionFiltroAuditoria;
    }

    public int getEmpleadoAuditoria() {
        return empleadoAuditoria;
    }

    public void setEmpleadoAuditoria(int empleadoAuditoria) {
        this.empleadoAuditoria = empleadoAuditoria;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public String getFechafinalString() {
        return fechafinalString;
    }

    public void setFechafinalString(String fechafinalString) {
        this.fechafinalString = fechafinalString;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public String getFechainicialString() {
        return fechainicialString;
    }

    public void setFechainicialString(String fechainicialString) {
        this.fechainicialString = fechainicialString;
    }

    public String getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(String fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public boolean isEstadoTablaAuditoria() {
        return estadoTablaAuditoria;
    }

    public void setEstadoTablaAuditoria(boolean estadoTablaAuditoria) {
        this.estadoTablaAuditoria = estadoTablaAuditoria;
    }

    public List<SelectItem> getListEmpAuditoria() {
        return ListEmpAuditoria;
    }

    public void setListEmpAuditoria(List<SelectItem> ListEmpAuditoria) {
        this.ListEmpAuditoria = ListEmpAuditoria;
    }

    public int getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(int codActividad) {
        this.codActividad = codActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public int getCodRecordato() {
        return codRecordato;
    }

    public void setCodRecordato(int codRecordato) {
        this.codRecordato = codRecordato;
    }

    public String getNombreRecordatori() {
        return nombreRecordatori;
    }

    public void setNombreRecordatori(String nombreRecordatori) {
        this.nombreRecordatori = nombreRecordatori;
    }

    public String getEstadoRadioSeleccionActiviYRecord() {
        return estadoRadioSeleccionActiviYRecord;
    }

    public void setEstadoRadioSeleccionActiviYRecord(String estadoRadioSeleccionActiviYRecord) {
        this.estadoRadioSeleccionActiviYRecord = estadoRadioSeleccionActiviYRecord;
    }

    public int getCodiItem() {
        return codiItem;
    }

    public void setCodiItem(int codiItem) {
        this.codiItem = codiItem;
    }

    public String getModuloItem() {
        return moduloItem;
    }

    public void setModuloItem(String moduloItem) {
        this.moduloItem = moduloItem;
    }

    public String getDescripItem() {
        return descripItem;
    }

    public void setDescripItem(String descripItem) {
        this.descripItem = descripItem;
    }

    public int getCodiDevol() {
        return codiDevol;
    }

    public void setCodiDevol(int codiDevol) {
        this.codiDevol = codiDevol;
    }

    public String getModuloDevol() {
        return moduloDevol;
    }

    public void setModuloDevol(String moduloDevol) {
        this.moduloDevol = moduloDevol;
    }

    public String getDescripDevol() {
        return descripDevol;
    }

    public void setDescripDevol(String descripDevol) {
        this.descripDevol = descripDevol;
    }

    public int getCodTipoConstruc() {
        return codTipoConstruc;
    }

    public void setCodTipoConstruc(int codTipoConstruc) {
        this.codTipoConstruc = codTipoConstruc;
    }

    public String getNombreTipoConstruc() {
        return nombreTipoConstruc;
    }

    public void setNombreTipoConstruc(String nombreTipoConstruc) {
        this.nombreTipoConstruc = nombreTipoConstruc;
    }

    public int getCodModificac() {
        return codModificac;
    }

    public void setCodModificac(int codModificac) {
        this.codModificac = codModificac;
    }

    public String getNombreModificacion() {
        return nombreModificacion;
    }

    public void setNombreModificacion(String nombreModificacion) {
        this.nombreModificacion = nombreModificacion;
    }

    public int getCodMedida() {
        return codMedida;
    }

    public void setCodMedida(int codMedida) {
        this.codMedida = codMedida;
    }

    public String getNombreMedida() {
        return nombreMedida;
    }

    public void setNombreMedida(String nombreMedida) {
        this.nombreMedida = nombreMedida;
    }

    public int getCodTiempoAval() {
        return codTiempoAval;
    }

    public void setCodTiempoAval(int codTiempoAval) {
        this.codTiempoAval = codTiempoAval;
    }

    public int getCodProdEntTiemAval() {
        return codProdEntTiemAval;
    }

    public void setCodProdEntTiemAval(int codProdEntTiemAval) {
        this.codProdEntTiemAval = codProdEntTiemAval;
    }

    public String getNombreTiempoAval() {
        return nombreTiempoAval;
    }

    public void setNombreTiempoAval(String nombreTiempoAval) {
        this.nombreTiempoAval = nombreTiempoAval;
    }

    public boolean isAprobacionRevision() {
        return AprobacionRevision;
    }

    public void setAprobacionRevision(boolean AprobacionRevision) {
        this.AprobacionRevision = AprobacionRevision;
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
     * Metodo para cargar los Producto entidad de la tabla 'Producto_Entidad'
     *
     * @return ArrayList con la lista de Producto entidad
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulProEnt() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getProducEntidad(mBsesion.codigoMiSesion()).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.ProEnt.add(new SelectItem(MBAdm.getCodProEnt(), MBAdm.getNombreProEnt()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.ProEnt;
    }

    /**
     * Metodo para consultar todos los tipos de producto entidad
     *
     * @param Tip int que debe contiene un tipo de dato [1,2] para hacer una
     * consulta diferente dependiento del valor que tome
     * @return ArrayList con la lista de todos los Tipos de producto entidad
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulTipProEnt(int Tip) {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTipoProducEntidad(Adm.getCodProEnt(), Tip).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.TipProEnt.add(new SelectItem(MBAdm.getCodTipProEnt(), MBAdm.getNombre_TipProEnt()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulTipProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.TipProEnt;

    }

    /**
     * Metodo para consultar todos los producto entidad para la tabla
     * (datatable) de la gestion de producto entidad
     *
     * @return ArrayList con la lista de todos los Producto entidad
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulProEntAll() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getProducEntidadAll().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.ProEntAll.add(new SelectItem(MBAdm.getCodProEnt(), MBAdm.getNombreProEnt()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulProEntAll()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.ProEntAll;
    }

    /**
     * Metodo para consultar los nombres de la tabla Modulo
     *
     * @return ArrayList con la lista de todos los Nombres de la tabla Modulo
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulModulo() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getModulo().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargModulo.add(new SelectItem(MBAdm.getCodModulo(), MBAdm.getNombreModulo()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulModulo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargModulo;

    }

    /**
     * Metodo que funciona como evento ajax, para limpiar y consultar los
     * archivos de tipo 'Avalúo'
     *
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public void onChangeTipoAvaluo() {
        try {
            CargTipArchAva.clear();
            getConsulArcAva(codTipAvaluo);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onChangeTipoAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para consultar los nombre de la tabla Regla_ArcAvaluo de tipo de
     * archivo 'Avalúo'
     *
     * @param tipo_avaluo int que contiene un valor para consultar por
     * diferentes tipos de archivos
     * @return ArrayList con la lista de todos los Nombres de la tabla
     * regla_ArcAvaluo de tipo de archivo 'Avalúo'
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulArcAva(int tipo_avaluo) {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getArchivos(tipo_avaluo).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargTipArchAva.add(new SelectItem(MBAdm.getCodParametro(), MBAdm.getResultadoParametro()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulArcAva()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargTipArchAva;

    }

    /**
     * Metodo para consultar los nombre de la tabla Regla_ArcAvaluo tipo de
     * archivo 'Cliente'
     *
     * @return ArrayList con la lista de todos los Nombres de la tabla
     * regla_ArcAvaluo de archivo 'Cliente'
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulArcCli() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getArchivos(4).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargTipArchCli.add(new SelectItem(MBAdm.getCodParametro(), MBAdm.getResultadoParametro()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulArcCli()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargTipArchCli;

    }

    /**
     * Metodo para consultar los nombre de tabla condicion en el campo Tabla
     *
     * @return ArrayList con la lista de todos los Nombres de la tabla condicion
     * en el campo Tabla
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulTabla() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTabla().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargTabla.add(new SelectItem(MBAdm.getTablaCondicion(), MBAdm.getTablaCondicion()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulTabla()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargTabla;

    }

    /**
     * Metodo para consultar los nombre de tabla condicion en el campo Tabla
     *
     * @return ArrayList con la lista de todos los Nombre de tabla condicion en
     * el campo Tabla
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public ArrayList<SelectItem> getConsulColumn() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getColumna(Adm.getTablaCondicion()).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargColumna.add(new SelectItem(MBAdm.getCampoCondicion(), MBAdm.getCampoCondicion()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulColumn()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargColumna;

    }

    /**
     * Metodo que funciona como evento ajax, para limpiar y consultar elementos
     * de la tabla condicion en el campo Tabla
     *
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public void onTable() {
        try {
            if (!"".equals(Adm.getTablaCondicion())) {
                CargColumna.clear();
                getConsulColumn();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onTable()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica si existe alguna condicion de proceso creada
     * anteriormente
     *
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public void verifiCondicion() {
        mbTodero.resetTable("RegArcAva:CondicionTable");
        if (Adm.getCodModulo() == 0 || "".equals(Adm.getTablaCondicion()) || "".equals(Adm.getTablaCondicion()) || "".equals(Adm.getEstadoCondicion()) || "".equals(Adm.getDescripcionCondicion())) {
            mbTodero.setMens("Falta informacion para generar condicion");
            mbTodero.warn();
        } else {
            try {
                if (Adm.getCodModulo() != 0 && !"".equals(Adm.getTablaCondicion()) && "".equals(Adm.getCampoCondicion()) && Adm.getCodProEnt() <= 0 && Adm.getCodTipProEnt() <= 0) {
                    Dat = Adm.ConsulCondicion(1);
                    if (Dat.next()) {
                        CondProc = Integer.parseInt(Dat.getString("Resultado"));
                        if (CondProc >= 1) {
                            mbTodero.setMens("Ya existe una condicion similar a la que se desea ingresar");
                            mbTodero.warn();
                        } else {
                            Adm.InserCondicion(1, mBsesion.codigoMiSesion());
                            mbTodero.setMens("Se ha registrado la condicion de proceso satisfactoriamente");
                            mbTodero.info();
                        }
                    }
                    Conexion.Conexion.CloseCon();

                } else if (Adm.getCodModulo() != 0 && !"".equals(Adm.getTablaCondicion()) && !"".equals(Adm.getCampoCondicion()) && Adm.getCodProEnt() <= 0 && Adm.getCodTipProEnt() <= 0) {
                    Dat = Adm.ConsulCondicion(2);
                    if (Dat.next()) {
                        CondProc = Integer.parseInt(Dat.getString("Resultado"));
                        if (CondProc >= 1) {
                            mbTodero.setMens("Ya existe una condicion similar a la que se desea ingresar");
                            mbTodero.warn();
                        } else {
                            Adm.InserCondicion(2, mBsesion.codigoMiSesion());
                            mbTodero.setMens("Se ha registrado la condicion de proceso satisfactoriamente");
                            mbTodero.info();
                        }
                    }
                    Conexion.Conexion.CloseCon();
                } else if (Adm.getCodModulo() != 0 && !"".equals(Adm.getTablaCondicion()) && !"".equals(Adm.getCampoCondicion()) && Adm.getCodProEnt() >= 0 && Adm.getCodTipProEnt() <= 0) {
                    Dat = Adm.ConsulCondicion(3);
                    if (Dat.next()) {
                        CondProc = Integer.parseInt(Dat.getString("Resultado"));
                        if (CondProc >= 1) {
                            mbTodero.setMens("Ya existe una condicion similar a la que se desea ingresar");
                            mbTodero.warn();
                        } else {
                            Adm.InserCondicion(3, mBsesion.codigoMiSesion());
                            mbTodero.setMens("Se ha registrado la condicion de proceso satisfactoriamente");
                            mbTodero.info();
                        }
                    }
                    Conexion.Conexion.CloseCon();
                } else {
                    Dat = Adm.ConsulCondicion(4);
                    if (Dat.next()) {
                        CondProc = Integer.parseInt(Dat.getString("Resultado"));
                        if (CondProc >= 1) {
                            mbTodero.setMens("Ya existe una condicion similar a la que se desea ingresar");
                            mbTodero.warn();
                        } else {
                            Adm.InserCondicion(4, mBsesion.codigoMiSesion());
                            mbTodero.setMens("Se ha registrado la condicion de proceso satisfactoriamente");
                            mbTodero.info();
                        }
                    }
                    Conexion.Conexion.CloseCon();
                }
                mbTodero.resetTable("RegArcAva:CondicionTable");
                this.ListCondicion.clear();
                this.ListCondicion = Adm.ConsulCondicion();
                Conexion.Conexion.CloseCon();
                RequestContext.getCurrentInstance().execute("PF('DlgIngCondicion').hide()");
            } catch (SQLException e) {
                mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiCondicion()' causado por: " + e.getMessage());
                mbTodero.error();
            }
        }
    }

    /**
     * Metodo para consultar los motivos de devolución de los avalúos
     *
     * @return ArrayList con la lista de todos los motivos de devolución de los
     * avalúos
     * @author Jhojan Stiven Rodriguez
     * @since 15-06-2014
     */
    public ArrayList<SelectItem> getMotivoAnulacion() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getMotivosAnulaciones().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargaMotivoAnulaciones.add(new SelectItem(MBAdm.getCodAnulacion(), MBAdm.getDesAnulacion()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getMotivoAnulacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargaMotivoAnulaciones;
    }

    /**
     * Metodo para abrir los dialogs de modificacion de cada uno de los
     * diferentes procesos del modulo de administracion, carga la información
     * que el usuario selecciono en un formulario
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int que contiene un dato numerico para saber que
     * validacion y que dialog de modificacion debe abrir
     * @throws java.sql.SQLException
     * @since 01-03-2015
     */
    public void abrirInfodialog(int proceso) throws SQLException {
        try {
            switch (proceso) {
                case 1:
                    if (AdmTipDoc == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        CodTable = AdmTipDoc.getCodTipDocumento();
                        NombreTable = AdmTipDoc.getNomTipDocumento();
                        DesTable = AdmTipDoc.getDesTipDocumento();
                        TituloDialogTipDoc = "Modificar tipo de documento";
                        RequestContext.getCurrentInstance().execute("PF('DlgTipDocumento').show()");
                    }
                    break;
                case 2:
                    if (AdmEstados == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        TituloDialogEstado = "Modificar estado";
                        CodTable = AdmEstados.getCodEstado();
                        NombreTable = AdmEstados.getNombreEstado();
                        DesTable = AdmEstados.getDesEstado();
                        RequestContext.getCurrentInstance().execute("PF('DlgEstado').show()");
                    }
                    break;
                case 3:
                    if (AdmCargos == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        TituloDialogCargo = "Modificar cargo";
                        CodTable = AdmCargos.getCodCargo();
                        NombreTable = AdmCargos.getNombreCargo();
                        DesTable = AdmCargos.getDescripcionCargo();
                        RequestContext.getCurrentInstance().execute("PF('DlgCargo').show()");
                    }
                    break;
                case 4:
                    if (AdmProdEnt == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        TituloDialogProdEnti = "Modificar producto entidad";
                        CodTable = AdmProdEnt.getCodProEnt();
                        NombreTable = AdmProdEnt.getNombreProEnt();
                        DesTable = AdmProdEnt.getDescripcion_ProEnt();
                        RequestContext.getCurrentInstance().execute("PF('DlgProducEnti').show()");
                    }
                    break;
                case 5:
                    if (AdmTipProdEnt == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        TituloDialogTipProdEnt = "Modificar tipo de producto entidad";
                        codTipProEnt = AdmTipProdEnt.getCodTipProEnt();
                        NombretipoProducto = AdmTipProdEnt.getNombre_TipProEnt();
                        Destipoproducto = AdmTipProdEnt.getDescripcion_TipProEnt();
                        CodProEnt = String.valueOf(AdmTipProdEnt.getCodFKProEnt());
                        RequestContext.getCurrentInstance().execute("PF('DlgTipProducEnti').show()");
                    }
                    break;
                case 6:
                    if (AdmRegimen == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        TituloDialogRegimen = "Modificar regimen";
                        CodTable = AdmRegimen.getCodRegimen();
                        NombreTable = AdmRegimen.getNombreRegimen();
                        DesTable = AdmRegimen.getDescripcionRegimen();
                        RequestContext.getCurrentInstance().execute("PF('DlgRegimen').show()");
                    }
                    break;
                case 7:
                    if (AdmTipRegimen == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        TituloDialogTipRegimen = "Modificar tipo de regimen";
                        CodTipoRegimen = AdmTipRegimen.getCodTipoRegimen();
                        CodRegimen = AdmTipRegimen.getCodFKRegimen();
                        NombreTipoRegimen = AdmTipRegimen.getNombreTipoRegimen();
                        EstadoTipoRegimen = AdmTipRegimen.getEstadoTipoRegimen();
                        RequestContext.getCurrentInstance().execute("PF('DialogTipoRegimen').show()");
                    }
                    break;
                case 8:
                    if (AdmEnvio == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codParametro = AdmEnvio.getCodigoParametro();
                        nombreParametro = AdmEnvio.getNombreParametro();
                        TituloDialogEnvio = "Modificar envio del avaluo";
                        RequestContext.getCurrentInstance().execute("PF('DlgEnvio').show()");
                    }
                    break;
                case 9:
                    if (AdmArchivos == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        //  this.OpcionArchivo = "";
                        Adm.setCodParametro(AdmArchivos.getCodParametro());
                        codParametro = AdmArchivos.getCodParametro();
                        Dat = Adm.ConsulAllArchivos();
                        if (Dat.next()) {
                            switch (Dat.getString("Argumento_Parametro")) {
                                case "Sol_Cli":
                                case "Inf_Cli":
                                    this.OpcionAvaluoModif = false;
                                    if ("Sol_Cli".equals(Dat.getString("Argumento_Parametro"))) {
                                        nombre_Proceso = "Sol";
                                    } else {
                                        nombre_Proceso = "Inf";
                                    }
                                    break;
                                case "Sol_Pre":
                                case "Inf_Pre":
                                case "Sol_Maq":
                                case "Inf_Maq":
                                case "Sol_Ens":
                                case "Inf_Ens":
                                    this.OpcionAvaluoModif = true;
                                    switch (Dat.getString("Argumento_Parametro")) {
                                        case "Sol_Pre":
                                            nombre_Proceso = "Sol";
                                            nombreTipAvaluo = "Pre";
                                            break;
                                        case "Inf_Pre":
                                            nombre_Proceso = "Inf";
                                            nombreTipAvaluo = "Pre";

                                            break;
                                        case "Sol_Maq":
                                            nombre_Proceso = "Sol";
                                            nombreTipAvaluo = "Maq";
                                            break;
                                        case "Inf_Maq":
                                            nombre_Proceso = "Inf";
                                            nombreTipAvaluo = "Maq";
                                            break;
                                        case "Inf_Ens":
                                            nombre_Proceso = "Inf";
                                            nombreTipAvaluo = "Ens";
                                            break;
                                        default:
                                            nombre_Proceso = "Sol";
                                            nombreTipAvaluo = "Ens";
                                            break;
                                    }
                                    break;
                            }
                            resultadoParametro = Dat.getString("Resultado_Parametro");
                        }
                        Conexion.Conexion.CloseCon();
                        /*
                         codParametro = AdmArchivos.getCodParametro();
                         nombreTipAvaluo = AdmArchivos.getNombreTipAvaluo();
                         nombre_Proceso = AdmArchivos.getNombre_Proceso();
                         resultadoParametro = AdmArchivos.getResultadoParametro();*/
                        TituloDialogArchivos = "Modificar archivo";
                        RequestContext.getCurrentInstance().execute("PF('DlgArchivo').show()");
                    }
                    break;
                case 10:
                    if (AdmActidiGest == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codActividad = AdmActidiGest.getCodActividad();
                        nombreActividad = AdmActidiGest.getNombreActividad();
                        TituloDialogActividad = "Modificar actividad";
                        RequestContext.getCurrentInstance().execute("PF('DlgActividad').show()");
                    }
                    break;
                case 11:
                    if (AdmRecordatGest == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codRecordato = AdmRecordatGest.getCodRecordatorio();
                        nombreRecordatori = AdmRecordatGest.getNombreRecordatorio();
                        TituloDialogRecordat = "Modificar recordatorio";
                        RequestContext.getCurrentInstance().execute("PF('DlgRecordator').show()");
                    }
                    break;
                case 12:
                    if (AdmAnulaciones == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        CodTable = AdmAnulaciones.getCodAnulacion();
                        DesTable = AdmAnulaciones.getDesAnulacion();
                        TituloDialogAnulaciones = "Modificar motivo de anulación";
                        RequestContext.getCurrentInstance().execute("PF('DlgAnulaciones').show()");
                    }
                    break;
                case 13:
                    if (AdmListaChequeo == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codiItem = AdmListaChequeo.getCodItem();
                        moduloItem = AdmListaChequeo.getModuloItem();
                        descripItem = AdmListaChequeo.getDesItem();
                        TituloDialogListChequeo = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DlgListaChequeo').show()");
                    }
                    break;
                case 14:
                    if (AdmDevoluc == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codiDevol = AdmDevoluc.getCodDevoluc();
                        moduloDevol = AdmDevoluc.getModuloDevoluc();
                        descripDevol = AdmDevoluc.getDesDevoluc();
                        TituloDialogDevoluc = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DlgDevoluc').show()");
                    }
                    break;
                case 15:
                    if (AdmTipoConstrucc == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codTipoConstruc = AdmTipoConstrucc.getCodTipoConstruc();
                        nombreTipoConstruc = AdmTipoConstrucc.getNombreTipoConstrucc();
                        TituloDialogTipoConstrucc = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DlgTiposConstruc').show()");
                    }
                    break;
                case 16:
                    if (AdmModificacInf == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codModificac = AdmModificacInf.getCodModifica();
                        nombreModificacion = AdmModificacInf.getNombreModifica();
                        TituloDialogModificacionInf = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DlgModificacion').show()");
                    }
                    break;
                case 17:
                    if (AdmMedidas == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codMedida = AdmMedidas.getCodMedida();
                        nombreMedida = AdmMedidas.getNombreMedida();
                        TituloDialogMedidas = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DlgMedidas').show()");
                    }
                    break;
                case 18:
                    if (AdmTiermposAval == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codTiempoAval = AdmTiermposAval.getCodTiempoAval();
                        nombreTiempoAval = AdmTiermposAval.getNombreTiempoAval();
                        codProdEntTiemAval = AdmTiermposAval.getCodProdEntiTiemposAval();
                        TituloDialogTiemposAval = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DlgTiemposAval').show()");
                    }
                    break;
                case 19:
                    if (AdmImpuestos == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codImpuesto = AdmImpuestos.getCodImpuesto();
                        nombreImpuesto = AdmImpuestos.getNombreImpuesto();
                        TituloDialogImpuestos = "Modificar item";
                        RequestContext.getCurrentInstance().execute("PF('DialogImpuestos').show()");
                    }
                    break;
                case 20:
                    if (AdmGesBancos == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codBanco = AdmGesBancos.getCodBanco();
                        nombreBanco = AdmGesBancos.getNombreBanco();

                        CuentaContable = ((AdmGesBancos.getCuentaContable() == null) ? 0 : Integer.valueOf(AdmGesBancos.getCuentaContable()));
                        CuentaBancaria = AdmGesBancos.getFk_CodCuenta();
                        TituloDialogGesBancos = "Modificar banco";
                        RequestContext.getCurrentInstance().execute("PF('DialogGesBancos').show()");
                    }
                    break;
                case 21:
                    if (AdmTipoFactConcep == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codConcepto = AdmTipoFactConcep.getCodParametro();
                        nombreConcepto = AdmTipoFactConcep.getNombreParametro();
                        TituloDialogTipConcp = "Modificar Concepto";
                        RequestContext.getCurrentInstance().execute("PF('DlgTipConcepto').show()");
                    }
                    break;

                case 22:
                    if (AdmTipoFactConcep == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codConcepto = AdmTipoFactConcep.getCodParametro();
                        nombreConcepto = AdmTipoFactConcep.getNombreParametro();
                        TituloDialogTipConcp = "Modificar Valor adicional";
                        RequestContext.getCurrentInstance().execute("PF('DlgTipConcepto').show()");
                    }
                    break;

                case 23:
                    if (AdmGesBancos == null) {
                        mbTodero.setMens("Debe seleccionar un registro");
                        mbTodero.warn();
                    } else {
                        codBanco = AdmTipoFactConcep.getCodBanco();
                        nombreBanco = AdmTipoFactConcep.getNombreBanco();
                        DescrpCargo = AdmTipoFactConcep.getDescripcionCargo();
                        TituloDialogGesBancos = "Modificar Pago";
                        RequestContext.getCurrentInstance().execute("PF('DialogMedioPagos').show()");
                    }
                    break;

                default:
                    break;
            }

        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirInfodialog()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para abrir los dialogs de registro de cada uno de los diferentes
     * procesos del modulo de administracion, carga un un formulario para
     * diligenciar los datos
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int que contiene un dato numerico para saber que
     * validacion y que dialog de registro debe abrir
     * @since 01-03-2015
     */
    public void abrirInfodialogRegistro(int proceso) {
        try {
            switch (proceso) {
                case 1:
                    TituloDialogTipDoc = "Registrar nuevo tipo de documento";
                    RequestContext.getCurrentInstance().execute("PF('DlgTipDocumento').show()");
                    break;
                case 2:
                    TituloDialogEstado = "Registrar nuevo estado";
                    RequestContext.getCurrentInstance().execute("PF('DlgEstado').show()");
                    break;
                case 3:
                    TituloDialogCargo = "Registrar nuevo cargo";
                    RequestContext.getCurrentInstance().execute("PF('DlgCargo').show()");
                    break;
                case 4:
                    TituloDialogProdEnti = "Registrar nuevo producto entidad";
                    RequestContext.getCurrentInstance().execute("PF('DlgProducEnti').show()");
                    break;
                case 5:
                    NombretipoProducto = "";
                    Destipoproducto = "";
                    CodProEnt = "";
                    TituloDialogTipProdEnt = "Registrar nuevo tipo de producto entidad";
                    RequestContext.getCurrentInstance().execute("PF('DlgTipProducEnti').show()");
                    break;
                case 6:
                    TituloDialogRegimen = "Registrar nuevo regimen";
                    RequestContext.getCurrentInstance().execute("PF('DlgRegimen').show()");
                    break;
                case 7:
                    NombreTipoRegimen = "";
                    EstadoTipoRegimen = "";
                    CodRegimen = 0;
                    TituloDialogTipRegimen = "Registrar nuevo tipo de regimen";
                    RequestContext.getCurrentInstance().execute("PF('DialogTipoRegimen').show()");
                    break;
                case 8:
                    nombreParametro = "";
                    TituloDialogEnvio = "Registrar nuevo lugar para envio del avaluo";
                    RequestContext.getCurrentInstance().execute("PF('DlgEnvio').show()");
                    break;
                case 9:
                    this.OpcionArchivo = "";
                    cargaOpcionGestionArchivos();
                    nombreTipAvaluo = "";
                    nombre_Proceso = "";
                    resultadoParametro = "";
                    TituloDialogArchivos = "Registrar nuevo archivo";
                    RequestContext.getCurrentInstance().execute("PF('DlgArchivo').show()");
                    break;
                case 10:
                    codActividad = 0;
                    nombreActividad = "";
                    TituloDialogActividad = "Registrar nueva actividad";
                    RequestContext.getCurrentInstance().execute("PF('DlgActividad').show()");
                    break;
                case 11:
                    codRecordato = 0;
                    nombreRecordatori = "";
                    TituloDialogRecordat = "Registrar nuevo recordatorio";
                    RequestContext.getCurrentInstance().execute("PF('DlgRecordator').show()");
                    break;
                case 12:
                    CodTable = 0;
                    DesTable = "";
                    TituloDialogAnulaciones = "Registrar nuevo motivo de anulación";
                    RequestContext.getCurrentInstance().execute("PF('DlgAnulaciones').show()");
                    break;
                case 13:
                    codiItem = 0;
                    moduloItem = "";
                    descripItem = "";
                    TituloDialogListChequeo = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DlgListaChequeo').show()");
                    break;
                case 14:
                    codiDevol = 0;
                    moduloDevol = "";
                    descripDevol = "";
                    TituloDialogDevoluc = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DlgDevoluc').show()");
                    break;
                case 15:
                    codTipoConstruc = 0;
                    nombreTipoConstruc = "";
                    TituloDialogTipoConstrucc = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DlgTiposConstruc').show()");
                    break;
                case 16:
                    codModificac = 0;
                    nombreModificacion = "";
                    TituloDialogModificacionInf = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DlgModificacion').show()");
                    break;
                case 17:
                    codMedida = 0;
                    nombreMedida = "";
                    TituloDialogMedidas = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DlgMedidas').show()");
                    break;
                case 18:
                    codTiempoAval = 0;
                    nombreTiempoAval = "";
                    codProdEntTiemAval = 0;
                    TituloDialogTiemposAval = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DlgTiemposAval').show()");
                    break;
                case 19:
                    codImpuesto = 0;
                    nombreImpuesto = "";
                    TituloDialogImpuestos = "Registrar nuevo item";
                    RequestContext.getCurrentInstance().execute("PF('DialogImpuestos').show()");
                    break;
                //Tatiana 28/03/2016
                case 20:
                    codBanco = 0;
                    CuentaBancaria = "";
                    CuentaContable = 0;

                    nombreBanco = "";
                    TituloDialogGesBancos = "Registrar nuevo banco";
                    RequestContext.getCurrentInstance().execute("PF('DialogGesBancos').show()");
                    break;
                case 21:
                    nombreConcepto = "";
                    TituloDialogTipConcp = "Registrar nuevo Concepto";
                    RequestContext.getCurrentInstance().execute("PF('DlgTipConcepto').show()");
                    break;
                case 22:
                    TituloDialogTipConcp = "Registrar nuevo Valor Adicional";
                    descripItem = "";
                    nombreConcepto = "";
                    RequestContext.getCurrentInstance().execute("PF('DlgTipConcepto').show()");
                    break;

                case 23:
                    codBanco = 0;
                    CuentaBancaria = "";
                    nombreBanco = "";
                    TituloDialogGesBancos = "Registrar nuevo medio de pago";
                    RequestContext.getCurrentInstance().execute("PF('DialogMedioPagos').show()");
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirInfodialogRegistro()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        CodTable = 0;
        NombreTable = "";
        DesTable = "";

    }

    /**
     * Metodo que registra el tipo de documento, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-03-2015
     */
    public void regisTipDocumento() {
        try {
            mbTodero.resetTable("formTipDoc:TipDocTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre");
                mbTodero.warn();
            } else if ("".equals(DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setNomTipDocumento(NombreTable);
                Admi.setDesTipDocumento(DesTable);
                Admi.InserTipDocumento(mBsesion.codigoMiSesion());
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                this.NombreTable = "";
                this.DesTable = "";
                mbTodero.resetTable("formTipDoc:TipDocTable");
                ListTipDocumento.clear();
                ListTipDocumento = Admi.ConsulTipDoc();
                RequestContext.getCurrentInstance().execute("PF('DlgTipDocumento').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisTipDocumento()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el tipo de documento, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiTipDocument() {
        try {
            mbTodero.resetTable("formTipDoc:TipDocTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre");
                mbTodero.warn();
            } else if ("".equals(DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodTipDocumento(CodTable);
                Admi.setNomTipDocumento(NombreTable);
                Admi.setDesTipDocumento(DesTable);
                Admi.ActualizaTipDocumento(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DlgTipDocumento').hide()");
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                CodTable = 0;
                NombreTable = "";
                DesTable = "";
                mbTodero.resetTable("formTipDoc:TipDocTable");

                this.ListTipDocumento.clear();
                this.ListTipDocumento = Adm.ConsulTipDoc();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiTipDocument()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que selecciona el regimen o tipo_regimen, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void seleccionRegimen() {
        try {
            switch (selecRegimennn) {
                case "Regimen":
                    panelregimen = true;
                    paneltiporegimen = false;
                    this.ListRegimen = Adm.ConsulRegimen();
                    this.ListRegimen.clear();
                    this.ListRegimen = Adm.ConsulRegimen();
                    if (ListRegimen.size() > 0) {
                        AdmRegimen.setCodRegimen(Adm.ConsulCodigoPrincipal("Cod_Regimen", "Regimen"));
                    }

                    break;
                case "Tipo_Regimen":
                    panelregimen = false;
                    paneltiporegimen = true;
                    this.ListTipoRegimen = Adm.ConsulTipoRegimen();
                    this.ListTipoRegimen.clear();
                    this.ListTipoRegimen = Adm.ConsulTipoRegimen();
                    if (ListTipoRegimen.size() > 0) {
                        AdmTipRegimen.setCodTipoRegimen(Adm.ConsulCodigoPrincipal("Cod_Calificacion", "Calificacion_Regimen"));
                    }

                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionRegimen()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra el regimen, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void regisRegimen() {
        try {
            mbTodero.resetTable("formRegimen:regimenTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setNombreRegimen(NombreTable);
                Admi.setDescripcionRegimen(DesTable);
                Admi.InserRegimen(mBsesion.codigoMiSesion());
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                //  limpiarAdministracion();
                mbTodero.resetTable("formRegimen:regimenTable");
                this.ListRegimen.clear();
                this.ListRegimen = Admi.ConsulRegimen();
                RequestContext.getCurrentInstance().execute("PF('DlgRegimen').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisRegimen()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que registra el tipo_regimen, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void registipoRegimen() {
        try {
            mbTodero.resetTable("formRegimen:tiporegimenTable");
            if ("".equals(this.NombreTipoRegimen) && "".equals(this.EstadoTipoRegimen) && this.CodRegimen == 0) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTipoRegimen)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.EstadoTipoRegimen)) {
                mbTodero.setMens("Debe llenar el campo 'Estado'");
                mbTodero.warn();
            } else if (this.CodRegimen == 0) {
                mbTodero.setMens("Debe llenar el campo 'Regimen'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setNombreTipoRegimen(NombreTipoRegimen);
                switch (this.EstadoTipoRegimen) {
                    case "Habilitado":
                        Admi.setEstadoTipoRegimen("1");
                        break;
                    case "Deshabilitado":
                        Admi.setEstadoTipoRegimen("0");
                        break;
                }
                Admi.setCodRegimen(CodRegimen);
                Admi.InserTipoRegimen(mBsesion.codigoMiSesion());
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                mbTodero.resetTable("formRegimen:tiporegimenTable");
                ListTipoRegimen.clear();
                ListTipoRegimen = Admi.ConsulTipoRegimen();
                this.NombreTipoRegimen = "";
                this.EstadoTipoRegimen = "";
                this.CodRegimen = 0;
                RequestContext.getCurrentInstance().execute("PF('DialogTipoRegimen').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".registipoRegimen()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el tipo_regimen, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modificartiporegimen() {
        try {
            mbTodero.resetTable("formRegimen:tiporegimenTable");
            if ("".equals(this.NombreTipoRegimen) && "".equals(this.EstadoTipoRegimen) && this.CodRegimen == 0) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTipoRegimen)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.EstadoTipoRegimen)) {
                mbTodero.setMens("Debe llenar el campo 'Estado'");
                mbTodero.warn();
            } else if (this.CodRegimen == 0) {
                mbTodero.setMens("Debe llenar el campo 'Regimen'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodTipoRegimen(CodTipoRegimen);
                Admi.setNombreTipoRegimen(NombreTipoRegimen);
                switch (EstadoTipoRegimen) {
                    case "Habilitado":
                        Admi.setEstadoTipoRegimen("1");
                        break;
                    case "Deshabilitado":
                        Admi.setEstadoTipoRegimen("0");
                        break;
                }
                Admi.setCodFKRegimen(CodRegimen);
                Admi.ActuaTipoRegimen(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DialogTipoRegimen').hide()");
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("formRegimen:tiporegimenTable");

                ListTipoRegimen = Admi.ConsulTipoRegimen();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modificartiporegimen()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el regimen, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiRegimen() {
        try {
            mbTodero.resetTable("formRegimen:regimenTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodTipoRegimen(CodTable);
                Admi.setNombreRegimen(NombreTable);
                Admi.setDescripcionRegimen(DesTable);
                Admi.ActualizaRegimen(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DlgRegimen').hide()");
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("formRegimen:regimenTable");

                this.ListRegimen.clear();
                ListRegimen = Admi.ConsulRegimen();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiRegimen()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra el cargo, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void regisCargo() {
        try {
            mbTodero.resetTable("FRMGesCargo:CargoTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setNombreCargo(NombreTable);
                Admi.setDescripcionCargo(DesTable);
                Admi.InserCarg(mBsesion.codigoMiSesion());
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                limpiarAdministracion();
                this.NombreTable = "";
                this.DesTable = "";
                mbTodero.resetTable("FRMGesCargo:CargoTable");
                ListCargos.clear();
                ListCargos = Admi.ConsulCargo();
                Adm.setCodCargo(Adm.ConsulCodigoPrincipal("Cod_Cargo", "Cargo"));
                RequestContext.getCurrentInstance().execute("PF('DlgCargo').hide()");
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisCargo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el cargo, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiCargo() {
        try {
            mbTodero.resetTable("FRMGesCargo:CargoTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodCargo(CodTable);
                Admi.setNombreCargo(NombreTable);
                Admi.setDescripcionCargo(DesTable);
                Admi.ActualizaCarg(mBsesion.codigoMiSesion());
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("FRMGesCargo:CargoTable");
                this.ListCargos.clear();
                ListCargos = Admi.ConsulCargo();
                Adm.setCodCargo(Adm.ConsulCodigoPrincipal("Cod_Cargo", "Cargo"));
                RequestContext.getCurrentInstance().execute("PF('DlgCargo').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiCargo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra el estado, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void regisEstado() {
        mbTodero.resetTable("formEst:EstTable");
        try {
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setNombreEstado(NombreTable);
                Admi.setDesEstado(DesTable);
                Admi.InserEstado(mBsesion.codigoMiSesion());
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                this.NombreTable = "";
                this.DesTable = "";
                mbTodero.resetTable("formEst:EstTable");
                ListEstado.clear();
                ListEstado = Admi.ConsulEstad();
                Conexion.Conexion.CloseCon();
                RequestContext.getCurrentInstance().execute("PF('DlgEstado').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisEstado()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el estado, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiEstado() {
        try {
            mbTodero.resetTable("formEst:EstTable");
            if ("".equals(NombreTable) && "".equals(DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodEstado(CodTable);
                Admi.setNombreEstado(NombreTable);
                Admi.setDesEstado(DesTable);
                Admi.ActualizaEstado(mBsesion.codigoMiSesion());
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("formEst:EstTable");
                this.ListEstado.clear();
                ListEstado = Admi.ConsulEstad();
                Conexion.Conexion.CloseCon();
                RequestContext.getCurrentInstance().execute("PF('DlgEstado').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiEstado()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que selecciona el producto entidad o tipo producto entidad,
     * proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void seleccionproent() {
        try {
            switch (this.selectproent) {
                case "Producto_entidad": {
                    LogAdministracion Admi = new LogAdministracion();
                    estadoProent = true;
                    estadoTiproent = false;
                    mbTodero.resetTable("proenti:ProducEntTable");

                    ListProducEnt = Admi.ConsulProducEnt();
                    this.ListProducEnt.clear();
                    ListProducEnt = Admi.ConsulProducEnt();
                    if (ListProducEnt.size() > 0) {
                        AdmProdEnt.setCodProEnt(Adm.ConsulCodigoPrincipal("Cod_ProEnt", "Producto_Entidad"));
                    }
                    break;
                }
                case "Tipo_Producto_entidad": {
                    LogAdministracion Admi = new LogAdministracion();
                    estadoTiproent = true;
                    estadoProent = false;
                    mbTodero.resetTable("proenti:TipProEntTable");

                    ListTipProducEnt = Admi.ConsulTipProducEnt();
                    this.ListTipProducEnt.clear();
                    ListTipProducEnt = Admi.ConsulTipProducEnt();
                    if (ListTipProducEnt.size() > 0) {
                        AdmTipProdEnt.setCodTipProEnt(Adm.ConsulCodigoPrincipal("Cod_TipProEnt", "Tipo_ProducEntid"));

                    }
                    this.ProEntAll = new ArrayList<>();
                    this.ProEntAll = this.getConsulProEntAll();
                    break;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionproent()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra el producto entidad, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void registrarProEnt() {

        try {
            mbTodero.resetTable("proenti:ProducEntTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setNombreProEnt(NombreTable);
                Admi.setDescripcion_ProEnt(DesTable);
                Admi.InserProductEnt(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DlgProducEnti').hide()");
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                limpiarAdministracion();
                mbTodero.resetTable("proenti:ProducEntTable");

                ListProducEnt.clear();
                ListProducEnt = Admi.ConsulProducEnt();
                this.estadoProent = true;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".registrarProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el producto entidad, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiProEnt() {
        try {
            mbTodero.resetTable("proenti:ProducEntTable");
            if ("".equals(this.NombreTable) && "".equals(this.DesTable)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombreTable)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.DesTable)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodProEnt(CodTable);
                Admi.setNombreProEnt(NombreTable);
                Admi.setDescripcion_ProEnt(DesTable);
                Admi.ActualizaProductEnt(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DlgProducEnti').hide()");
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("proenti:ProducEntTable");

                ListProducEnt = Admi.ConsulProducEnt();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra un tipo producto entidad, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void regisTipProEnt() {
        try {
            mbTodero.resetTable("proenti:TipProEntTable");
            if ("".equals(this.NombretipoProducto) && "".equals(this.Destipoproducto) && "".equals(this.CodProEnt)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombretipoProducto)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.Destipoproducto)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else if ("".equals(this.CodProEnt)) {
                mbTodero.setMens("Debe seleccionar información de 'Producto entidad asociado'");
                mbTodero.warn();
            } else {
                Admin = new LogAdministracion();
                Admin.setNombre_TipProEnt(NombretipoProducto);
                Admin.setDescripcion_TipProEnt(Destipoproducto);
                Admin.setCodProEnt(Integer.parseInt(CodProEnt));
                Admin.InserTipProductEnt(mBsesion.codigoMiSesion());
                mbTodero.setMens("Registro Creado Correctamente");
                mbTodero.info();
                mbTodero.resetTable("proenti:TipProEntTable");
                ListTipProducEnt.clear();
                ListTipProducEnt = Admin.ConsulTipProducEnt();
                RequestContext.getCurrentInstance().execute("PF('DlgTipProducEnti').hide()");
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisTipProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica el tipo producto entidad, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiTipProEnt() {
        try {
            mbTodero.resetTable("proenti:TipProEntTable");
            if ("".equals(this.NombretipoProducto) && "".equals(this.Destipoproducto) && "".equals(this.CodProEnt)) {
                mbTodero.setMens("Favor ingrese toda la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.NombretipoProducto)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(this.Destipoproducto)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else if ("".equals(this.CodProEnt)) {
                mbTodero.setMens("Debe seleccionar información de 'Producto entidad asociado'");
                mbTodero.warn();
            } else {
                LogAdministracion Admi = new LogAdministracion();
                Admi.setCodTipProEnt(codTipProEnt);
                Admi.setNombre_TipProEnt(NombretipoProducto);
                Admi.setDescripcion_TipProEnt(Destipoproducto);
                Admi.setCodFKProEnt(Integer.parseInt(CodProEnt));
                Admi.ActualizaTipProductEnt(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DlgTipProducEnti').hide()");
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("proenti:TipProEntTable");

                ListTipProducEnt = Admi.ConsulTipProducEnt();
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiTipProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica si una regla de archivo para tipo 'Avaluo' ya fue
     * registrada,de lo contrario la registra, proceso del modulo de
     * administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void verifiReglaArcAvaluo() {
        mbTodero.resetTable("RegArcAva:ArchiAvaTable");
        if (!"0".equals(this.CodProEnt)) {
            if ((codModulo == 0) || (codTipAvaluo == 0) || (codParametro == 0)) {
                mbTodero.setMens("Favor seleccione un Modulo, Tipo Avaluo, Producto Entidad o Tipo de Archivo al cual genera la Regla del Proceso");
                mbTodero.warn();
            } else if ("".equals(estadoArcAva)) {
                mbTodero.setMens("Favor seleccione un estado para la regla del proceso");
                mbTodero.warn();
            } else {
                try {
                    if (codTipProEnt == 0) {
                        Adm.setCodModulo(codModulo);
                        Adm.setCodParametro(codParametro);
                        Adm.setCodTipAvaluo(codTipAvaluo);
                        Dat = Adm.ConsultRegAnteriores(1);
                        if (Dat.next()) {
                            RegArcAva = Integer.parseInt(Dat.getString("Resultado"));
                            if (RegArcAva >= 1) {
                                mbTodero.setMens("Ya existe una regla con estas especificaciones");
                                mbTodero.warn();
                            } else {
                                Adm.setEstadoArcAva(estadoArcAva);
                                Adm.setCodModulo(codModulo);
                                Adm.setCodParametro(codParametro);
                                Adm.setCodTipAvaluo(codTipAvaluo);
                                Adm.InserRegArcAva(1, mBsesion.codigoMiSesion());
                                mbTodero.setMens("La regla para archivos se ha generado correctamente");
                                mbTodero.info();
                                RequestContext.getCurrentInstance().execute("PF('DlgInserReglaArc').hide()");
                            }

                        }
                        Conexion.Conexion.CloseCon();
                    }
                    if (codTipProEnt == 0) {
                        Adm.setCodModulo(codModulo);
                        Adm.setCodParametro(codParametro);
                        Adm.setCodTipAvaluo(codTipAvaluo);
                        Adm.setCodProEnt(Integer.parseInt(CodProEnt));
                        Dat = Adm.ConsultRegAnteriores(2);
                        if (Dat.next()) {
                            RegArcAva = Integer.parseInt(Dat.getString("Resultado"));
                            if (RegArcAva >= 1) {
                                mbTodero.setMens("Ya existe una regla con estas especificaciones ");
                                mbTodero.warn();
                            } else {
                                Adm.setEstadoArcAva(estadoArcAva);
                                Adm.setCodModulo(codModulo);
                                Adm.setCodParametro(codParametro);
                                Adm.setCodTipAvaluo(codTipAvaluo);
                                Adm.setCodProEnt(Integer.parseInt(CodProEnt));
                                Adm.InserRegArcAva(2, mBsesion.codigoMiSesion());
                                mbTodero.setMens("La regla para archivos se ha generado correctamente");
                                mbTodero.info();
                                RequestContext.getCurrentInstance().execute("PF('DlgInserReglaArc').hide()");
                            }
                        }
                        Conexion.Conexion.CloseCon();
                    }
                    if (codTipProEnt != 0) {
                        Adm.setCodModulo(codModulo);
                        Adm.setCodParametro(codParametro);
                        Adm.setCodTipAvaluo(codTipAvaluo);
                        Adm.setCodProEnt(Integer.parseInt(CodProEnt));
                        Adm.setCodTipProEnt(codTipProEnt);
                        Dat = Adm.ConsultRegAnteriores(3);
                        if (Dat.next()) {
                            RegArcAva = Integer.parseInt(Dat.getString("Resultado"));
                            if (RegArcAva >= 1) {
                                mbTodero.setMens("Ya existe una regla con estas especificaciones ");
                                mbTodero.warn();
                            } else {
                                Adm.setEstadoArcAva(estadoArcAva);
                                Adm.setCodModulo(codModulo);
                                Adm.setCodParametro(codParametro);
                                Adm.setCodTipAvaluo(codTipAvaluo);
                                Adm.setCodProEnt(Integer.parseInt(CodProEnt));
                                Adm.setCodTipProEnt(codTipProEnt);
                                Adm.InserRegArcAva(3, mBsesion.codigoMiSesion());
                                mbTodero.setMens("La regla para archivos se ha generado correctamente");
                                mbTodero.info();
                                RequestContext.getCurrentInstance().execute("PF('DlgInserReglaArc').hide()");
                            }
                        }
                        Conexion.Conexion.CloseCon();
                    }
                    mbTodero.resetTable("RegArcAva:ArchiAvaTable");
                    this.ListArcAva.clear();
                    this.ListArcAva = Adm.ConsulRegArcAva();
                } catch (SQLException e) {
                    mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiReglaArcAvaluo()' causado por: " + e.getMessage());
                    mbTodero.error();
                }
            }
        } else {
            mbTodero.setMens("Favor seleccione un Modulo, Tipo Avaluo, Producto Entidad o Tipo de Archivo al cual genera la Regla del Proceso");
            mbTodero.fatal();
        }
    }

    /**
     * Metodo que verifica si una regla de archivo para tipo 'Cliente' ya fue
     * registrada,de lo contrario la registra, proceso del modulo de
     * administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void verifiReglaArcCliente() {
        mbTodero.resetTable("RegArcAva:ArchiCliTable");
        if (codModulo1 == 0 || codParametro1 == 0 || CodRegimen == 0) {
            mbTodero.setMens("Favor seleccione un Modulo,Regimen y Tipo de Archivo al cual genera la Regla del Proceso");
            mbTodero.warn();
        } else {
            try {
                Adm.setCodModulo(codModulo1);
                Adm.setCodParametro(codParametro1);
                Adm.setCodRegimen(CodRegimen);
                Dat = Adm.ConsultRegAnteriores(4);
                if (Dat.next()) {
                    if (Integer.parseInt(Dat.getString("Resultado")) >= 1) {
                        mbTodero.setMens("Ya existe una regla con estas especificaciones ");
                        mbTodero.warn();
                    } else {
                        Adm.setEstadoArcCli(estadoArcCli);
                        Adm.setCodModulo(codModulo1);
                        Adm.setCodParametro(codParametro1);
                        Adm.setCodRegimen(CodRegimen);
                        Adm.InserRegArcAva(4, mBsesion.codigoMiSesion());
                        mbTodero.setMens("La regla para Archivos se ha generado Correctamente");
                        mbTodero.info();
                        RequestContext.getCurrentInstance().execute("PF('DlgInserReglaCli').hide()");
                        mbTodero.resetTable("RegArcAva:ArchiCliTable");
                        this.ListArcCli.clear();
                        this.ListArcCli = Adm.ConsulRegArcCli();
                    }
                }
                Conexion.Conexion.CloseCon();
            } catch (SQLException ex) {
                mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiReglaArcCliente()' causado por: " + ex.getMessage());
                mbTodero.error();
            }
        }
    }

    /**
     * Metodo que consulta los accesos que tiene un usuario, proceso del modulo
     * de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void consultarAccesosRol() {
        try {
            Adm.setFK_Cod_Roles(String.valueOf(Adm.getCodRol()));
            this.ListAcessos = Adm.ConsulAcessos();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultarAccesosRol()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que funciona como evento ajax, carga el tipo producto entidad
     * dependiendo del cod del producto entidad, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void onTipProEnt() {
        try {
            if (Adm.getCodProEnt() != 0) {
                this.CodProEnt = String.valueOf(Adm.getCodProEnt());
                this.TipProEnt.clear();
                getConsulTipProEnt(1);
            } else {
                System.out.println("No funciona");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onTipProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica y valida que se seleccione un cargo de la respectiva
     * tabla, proceso del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void verifiSeleccionCargo() {
        try {
            this.setCodTable(0);
            this.setNombreTable("");
            this.setDesTable("");
            if (Adm == null) {
                mbTodero.setMens("Debe seleccionar un cargo");
                mbTodero.warn();
                mbTodero.resetTable("FRMGesCargo:CargoTable");
            } else {
                RequestContext.getCurrentInstance().execute("PF('DlgModifiCargo').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiSeleccionCargo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta los tipos de predios, proceso del modulo de
     * administracion
     *
     * @return ArrayList que carga los tipos de predios
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> getConsTipoPredios() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTiposPredio().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargaTipoPredios.add(new SelectItem(MBAdm.getCodTipoPre(), MBAdm.getNombreTipoPredio()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsTipoPredios()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargaTipoPredios;

    }

    /**
     * Metodo que consulta los tipos de maquinaria, proceso del modulo de
     * administracion
     *
     * @return ArrayList que carga los tipos de maquinaria
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> getConsTipoMaquinaria() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTiposMaquinaria().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargaTipoMaquinaria.add(new SelectItem(MBAdm.getCodTipoPre(), MBAdm.getNombreTipoPredio()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsTipoMaquinaria()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargaTipoMaquinaria;

    }

    /**
     * Metodo que habilita el tipo de archivo que se desea subir, proceso del
     * modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void cargaOpcionGestionArchivos() {
        try {
            if ("1".equals(this.OpcionArchivo)) {
                this.OpcionAvaluo = true;
            } else if (!"1".equals(this.OpcionArchivo)) {
                this.OpcionAvaluo = false;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargaOpcionGestionArchivos()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre el dialog para diligenciar la informacion de archivos,
     * proceso del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void opendialogoarchivo() {
        try {
            this.OpcionArchivo = "";
            cargaOpcionGestionArchivos();
            nombreTipAvaluo = "";
            nombre_Proceso = "";
            resultadoParametro = "";
            RequestContext.getCurrentInstance().execute("PF('DlgArchivo').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".opendialogoarchivo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que insertar un tipo de archivo, proceso del modulo de
     * administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void inserArchivoProceso() {
        try {
            mbTodero.resetTable("FRMGestArc:ArchivoTable");
            if ("".equals(nombreTipAvaluo) && "".equals(nombre_Proceso) && "".equals(resultadoParametro) && "".equals(this.OpcionArchivo)) {
                mbTodero.setMens("Falta Informacion para Ingresar el tipo de archivo");
                mbTodero.warn();
            } else if ("".equals(OpcionArchivo)) {
                mbTodero.setMens("Debe llenar el campo 'Opción de archivo'");
                mbTodero.warn();
            } else if ("".equals(nombreTipAvaluo) && "1".equals(OpcionArchivo)) {
                mbTodero.setMens("Debe llenar el campo 'Tipo de avaluo'");
                mbTodero.warn();
            } else if ("".equals(nombre_Proceso)) {
                mbTodero.setMens("Debe llenar el campo 'Proceso asociado'");
                mbTodero.warn();
            } else if ("".equals(resultadoParametro)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre del archivo'");
                mbTodero.warn();
            } else {
                Adm.setGrupoParametro("Archivos");
                if ("1".equals(this.OpcionArchivo)) {
                    switch (nombreTipAvaluo) {
                        case "Pre":
                            if ("Sol".equals(nombre_Proceso)) {
                                Adm.setArgumentoParametro("Sol_Pre");
                            } else {
                                Adm.setArgumentoParametro("Inf_Pre");
                            }
                            break;
                        case "Maq":
                            if ("Sol".equals(nombre_Proceso)) {
                                Adm.setArgumentoParametro("Sol_Maq");
                            } else {
                                Adm.setArgumentoParametro("Inf_Maq");
                            }
                            break;
                        default:
                            if ("Sol".equals(nombre_Proceso)) {
                                Adm.setArgumentoParametro("Sol_Ens");
                            } else {
                                Adm.setArgumentoParametro("Inf_Ens");
                            }
                            break;
                    }
                } else if ("Sol".equals(nombre_Proceso)) {
                    Adm.setArgumentoParametro("Sol_Cli");
                } else {
                    Adm.setArgumentoParametro("Inf_Cli");
                }
                Adm.setResultadoParametro(resultadoParametro);
                Adm.InsertParametro(1, mBsesion.codigoMiSesion());
                mbTodero.setMens("El Archivo ha sido guardado correctamente");
                mbTodero.info();
                OpcionArchivo = "";
                resultadoParametro = "";
                nombre_Proceso = "";
                nombreTipAvaluo = "";
                mbTodero.resetTable("FRMGestArc:ArchivoTable");

                this.ListArchivos = Adm.ConsulArchivos();
                RequestContext.getCurrentInstance().execute("PF('DlgArchivo').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".inserArchivoProceso()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que verifica y modifica la gestión de archivos, proceso del modulo
     * de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void modfiArchivoProceso() {
        try {
            mbTodero.resetTable("FRMGestArc:ArchivoTable");
            Admin.setCodParametro(codParametro);
            Admin.setGrupoParametro("Archivos");
            if (this.OpcionAvaluoModif == true) {
                if ("".equals(nombreTipAvaluo) && "".equals(nombre_Proceso) && "".equals(resultadoParametro)) {
                    mbTodero.setMens("Falta Informacion para ingresar el tipo de archivo");
                    mbTodero.warn();
                } else if ("".equals(nombreTipAvaluo)) {
                    mbTodero.setMens("Debe llenar el campo 'Tipo de avaluo'");
                    mbTodero.warn();
                } else if ("".equals(nombre_Proceso)) {
                    mbTodero.setMens("Debe llenar el campo 'Proceso asociado'");
                    mbTodero.warn();
                } else if ("".equals(resultadoParametro)) {
                    mbTodero.setMens("Debe llenar el campo 'Nombre del archivo'");
                    mbTodero.warn();
                } else {
                    switch (nombreTipAvaluo) {
                        case "Pre":
                            if ("Sol".equals(nombre_Proceso)) {
                                Admin.setArgumentoParametro("Sol_Pre");
                            } else {
                                Admin.setArgumentoParametro("Inf_Pre");
                            }
                            break;
                        case "Maq":
                            if ("Sol".equals(nombre_Proceso)) {
                                Admin.setArgumentoParametro("Sol_Maq");
                            } else {
                                Admin.setArgumentoParametro("Inf_Maq");
                            }
                            break;
                        default:
                            if ("Sol".equals(nombre_Proceso)) {
                                Admin.setArgumentoParametro("Sol_Ens");
                            } else {
                                Admin.setArgumentoParametro("Inf_Ens");
                            }
                            break;
                    }
                    Admin.setResultadoParametro(resultadoParametro);
                    Admin.ModifiParametro(mBsesion.codigoMiSesion());
                    mbTodero.setMens("El registro se ha modificado correctamente");
                    mbTodero.info();
                    Admin.setResultadoParametro("");
                    Admin.setNombre_Proceso("");
                    Admin.setNombreTipAvaluo("");
                    ListArchivos.clear();
                    this.ListArchivos = Adm.ConsulArchivos();
                    mbTodero.resetTable("FRMGestArc:ArchivoTable");
                    RequestContext.getCurrentInstance().execute("PF('DlgArchivo').hide()");
                }
            } else if ("".equals(resultadoParametro) && "".equals(nombre_Proceso)) {
                mbTodero.setMens("Por favor ingrese toda la información requerida");
                mbTodero.warn();
            } else if ("".equals(nombre_Proceso)) {
                mbTodero.setMens("Debe llenar el campo 'Proceso asociado'");
                mbTodero.warn();
            } else if ("".equals(resultadoParametro)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre del archivo'");
                mbTodero.warn();
            } else {//PARA LOS ARCHIVOS QUE SON DE CLIENTE 
                if ("Sol".equals(nombre_Proceso)) {
                    Admin.setArgumentoParametro("Sol_Cli");
                } else {
                    Admin.setArgumentoParametro("Inf_Cli");
                }
                Admin.setResultadoParametro(resultadoParametro);
                Admin.ModifiParametro(mBsesion.codigoMiSesion());
                mbTodero.setMens("El registro se ha modificado correctamente");
                mbTodero.info();
                Admin.setResultadoParametro("");
                Admin.setNombre_Proceso("");
                Admin.setNombreTipAvaluo("");
                ListArchivos.clear();
                this.ListArchivos = Adm.ConsulArchivos();
                mbTodero.resetTable("FRMGestArc:ArchivoTable");
                RequestContext.getCurrentInstance().execute("PF('DlgArchivo').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modfiArchivoProceso()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite escoger la opcion de proceso la cual sera configurada,
     * proceso del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void verifiReglaEscogida() {
        try {
            if ("".equals(this.OpcionRegla)) {
                mbTodero.setMens("Por favor escoga una Opcion del Proceso a Configurar");
                mbTodero.warn();
            } else {
                switch (OpcionRegla) {
                    case "1":
                        Adm = new LogAdministracion();
                        this.OpcionArchivoAvaluo = true;
                        this.OpcionArchivoCliente = false;
                        this.OpcionListaChequeo = false;
                        this.OpcionCondicion = false;
                        mbTodero.resetTable("RegArcAva:ArchiAvaTable");

                        ListArcAva.clear();
                        ListArcAva = Adm.ConsulRegArcAva();
                        Adm.setCodArcAva(1);
                        break;
                    case "2":
                        Adm = new LogAdministracion();
                        this.OpcionArchivoAvaluo = false;
                        this.OpcionArchivoCliente = true;
                        this.OpcionListaChequeo = false;
                        this.OpcionCondicion = false;
                        mbTodero.resetTable("RegArcAva:ArchiCliTable");

                        ListArcCli.clear();
                        ListArcCli = Adm.ConsulRegArcCli();
                        Adm.setCodArcCli(1);
                        break;
                    case "3": {
                        this.OpcionArchivoAvaluo = false;
                        this.OpcionArchivoCliente = false;
                        this.OpcionListaChequeo = false;
                        this.OpcionCondicion = true;
                        mbTodero.resetTable("RegArcAva:CondicionTable");

                        ListCondicion.clear();
                        ListCondicion = Adm.ConsulCondicion();
                        Adm.setCodCondicion(1);
                        Conexion.Conexion.CloseCon();
                        break;
                    }
                    default: {
                        Adm = new LogAdministracion();
                        this.OpcionArchivoAvaluo = false;
                        this.OpcionArchivoCliente = false;
                        this.OpcionListaChequeo = true;
                        this.OpcionCondicion = false;
                        mbTodero.resetTable("RegArcAva:PreCondicionTable");

                        ListPreCondicion.clear();
                        ListPreCondicion = Adm.ConsulPreCondicion();
                        Adm.setCodPreCondicion(1);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiReglaEscogida()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica si hay un numero de archivo seleccionado, proceso del
     * modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void verificSeleccArchiv() {
        try {
            if (Adm.getCodParametro() == 0) {
                mbTodero.setMens("Falta seleccionar que registro se desea modificar");
                mbTodero.warn();
            } else {
                try {
                    Dat = Adm.ConsulAllArchivos();
                    if (Dat.next()) {
                        switch (Dat.getString("Argumento_Parametro")) {
                            case "Sol_Cli":
                            case "Inf_Cli":
                                this.OpcionAvaluoModif = false;
                                if ("Sol_Cli".equals(Dat.getString("Argumento_Parametro"))) {
                                    Admin.setNombre_Proceso("Sol");
                                } else {
                                    Admin.setNombre_Proceso("Inf");
                                }
                                break;
                            case "Sol_Pre":
                            case "Inf_Pre":
                            case "Sol_Maq":
                            case "Inf_Maq":
                            case "Sol_Ens":
                            case "Inf_Ens":
                                this.OpcionAvaluoModif = true;
                                switch (Dat.getString("Argumento_Parametro")) {
                                    case "Sol_Pre":
                                        Admin.setNombre_Proceso("Sol");
                                        Admin.setNombreTipAvaluo("Pre");
                                        break;
                                    case "Inf_Pre":
                                        Admin.setNombre_Proceso("Inf");
                                        Admin.setNombreTipAvaluo("Pre");
                                        break;
                                    case "Sol_Maq":
                                        Admin.setNombre_Proceso("Sol");
                                        Admin.setNombreTipAvaluo("Maq");
                                        break;
                                    case "Inf_Maq":
                                        Admin.setNombre_Proceso("Inf");
                                        Admin.setNombreTipAvaluo("Maq");
                                        break;
                                    case "Inf_Ens":
                                        Admin.setNombre_Proceso("Inf");
                                        Admin.setNombreTipAvaluo("Ens");
                                        break;
                                    default:
                                        Admin.setNombre_Proceso("Sol");
                                        Admin.setNombreTipAvaluo("Ens");
                                        break;
                                }
                                break;
                        }
                        Admin.setResultadoParametro(Dat.getString("Resultado_Parametro"));
                    }
                    Conexion.Conexion.CloseCon();
                } catch (SQLException e) {
                    mbTodero.setMens("Error: " + e.getMessage());
                    mbTodero.fatal();
                }
                RequestContext.getCurrentInstance().execute("PF('DlgModificarArchivo').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificSeleccArchiv()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre diferentes dialogos de las reglas de archivos, proceso
     * del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @param op int que sirve de condicion para abrir diferentes dialogs de
     * reglas de archivos
     * @since 01-05-2015
     */
    public void opendilogReglaArchivo(int op) {
        try {
            switch (op) {
                case 1:
                    this.codModulo = 0;
                    this.codTipAvaluo = 0;
                    this.Adm.setCodProEnt(0);
                    this.codTipProEnt = 0;
                    this.codParametro = 0;
                    this.estadoArcAva = "";
                    RequestContext.getCurrentInstance().execute("PF('DlgInserReglaArc').show()");
                    break;
                case 2:
                    this.codModulo1 = 0;
                    this.codParametro1 = 0;
                    this.CodRegimen = 0;
                    this.estadoArcCli = "";
                    RequestContext.getCurrentInstance().execute("PF('DlgInserReglaCli').show()");
                    break;
                case 3:
                    this.Adm.setCodModulo(0);
                    this.Adm.setCodProEnt(0);
                    this.Adm.setCodTipProEnt(0);
                    this.Adm.setTablaCondicion("");
                    this.Adm.setCampoCondicion("");
                    this.Adm.setDescripcionCondicion("");
                    this.Adm.setEstadoCondicion("");
                    RequestContext.getCurrentInstance().execute("PF('DlgIngCondicion').show()");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".opendilogReglaArchivo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para verificar la informacion de las reglas de proceso, proceso
     * del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @param Op int que debe contener valor para condicionar los diferentes
     * tipos de reglas de proceso
     * @since 01-05-2015
     */
    public void verifiReglasProceso(int Op) {
        OpcionHabilitar = "";
        try {
            switch (Op) {
                case 1:
                    //Para reglas de Archivos de Proceso para los Avaluos
                    if (AdmArchivAval == null) {
                        mbTodero.setMens("No ha seleccionado ningun registro");
                        mbTodero.warn();
                    } else {
                        switch (AdmArchivAval.getEstadoArcAva()) {
                            case "Obligatorio":
                                OpcionHabilitar = "Si";
                                break;
                            case "No Obligatorio":
                                OpcionHabilitar = "No";
                                break;
                        }
                        TipoRegla = 1;
                        NumeroRegRegla = AdmArchivAval.getCodArcAva();
                        RequestContext.getCurrentInstance().execute("PF('DlgModRegPro').show()");
                    }
                    break;
                //Para Regla de archivos de Proceso para los Clientes
                case 2:
                    if (AdmArchvClie == null) {
                        mbTodero.setMens("No ha seleccionado ningun registro");
                        mbTodero.warn();
                    } else {
                        switch (AdmArchvClie.getEstadoArcCli()) {
                            case "Obligatorio":
                                OpcionHabilitar = "Si";
                                break;
                            case "No Obligatorio":
                                OpcionHabilitar = "No";
                                break;
                        }
                        TipoRegla = 2;
                        NumeroRegRegla = AdmArchvClie.getCodArcCli();
                        RequestContext.getCurrentInstance().execute("PF('DlgModRegPro').show()");
                    }
                    break;
                case 3:
                    if (AdmArchCondi == null) {
                        mbTodero.setMens("No ha seleccionado ningun registro");
                        mbTodero.warn();
                    } else {
                        switch (AdmArchCondi.getEstadoCondicion()) {
                            case "Obligatorio":
                                OpcionHabilitar = "Si";
                                break;
                            case "No Obligatorio":
                                OpcionHabilitar = "No";
                                break;
                        }
                        TipoRegla = 3;
                        NumeroRegRegla = AdmArchCondi.getCodCondicion();
                        RequestContext.getCurrentInstance().execute("PF('DlgModRegPro').show()");
                    }
                    break;
                case 4:
                    if (AdmArchPreCond == null) {
                        mbTodero.setMens("No ha seleccionado ningun registro");
                        mbTodero.warn();
                    } else {
                        switch (AdmArchPreCond.getEstadoPreCondicion()) {
                            case "Obligatorio":
                                OpcionHabilitar = "Si";
                                break;
                            case "No Obligatorio":
                                OpcionHabilitar = "No";
                                break;
                        }
                        TipoRegla = 4;
                        NumeroRegRegla = AdmArchPreCond.getCodPreCondicion();
                        RequestContext.getCurrentInstance().execute("PF('DlgModRegPro').show()");
                    }
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiReglasProceso()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica la informacion de las reglas de proceso, proceso del
     * modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void modifiReglasProceso() {
        try {

            switch (getTipoRegla()) {
                case 1:
                    switch (OpcionHabilitar) {
                        case "Si":
                            Adm.setEstadoArcAva("1");
                            break;
                        case "No":
                            Adm.setEstadoArcAva("0");
                            break;
                    }
                    Adm.setCodArcAva(NumeroRegRegla);
                    Adm.ModifiReglaProceso(1, mBsesion.codigoMiSesion());
                    this.ListArcAva.clear();
                    this.ListArcAva = Adm.ConsulRegArcAva();
                    mbTodero.resetTable("RegArcAva:ArchiAvaTable");
                    break;
                case 2:
                    switch (OpcionHabilitar) {
                        case "Si":
                            Adm.setEstadoArcCli("1");
                            break;
                        case "No":
                            Adm.setEstadoArcCli("0");
                            break;
                    }
                    Adm.setCodArcCli(NumeroRegRegla);
                    Adm.ModifiReglaProceso(2, mBsesion.codigoMiSesion());
                    this.ListArcCli.clear();
                    this.ListArcCli = Adm.ConsulRegArcCli();
                    mbTodero.resetTable("RegArcAva:ArchiCliTable");
                    break;
                case 3:
                    switch (OpcionHabilitar) {
                        case "Si":
                            Adm.setEstadoCondicion("1");
                            break;
                        case "No":
                            Adm.setEstadoCondicion("0");
                            break;
                    }
                    Adm.setCodCondicion(NumeroRegRegla);
                    Adm.ModifiReglaProceso(3, mBsesion.codigoMiSesion());
                    this.ListCondicion.clear();
                    this.ListCondicion = Adm.ConsulCondicion();
                    Conexion.Conexion.CloseCon();
                    mbTodero.resetTable("RegArcAva:CondicionTable");
                    break;
                case 4:
                    switch (OpcionHabilitar) {
                        case "Si":
                            Adm.setEstadoPreCondicion("1");
                            break;
                        case "No":
                            Adm.setEstadoPreCondicion("0");
                            break;
                    }
                    Adm.setCodPreCondicion(NumeroRegRegla);
                    Adm.ModifiReglaProceso(4, mBsesion.codigoMiSesion());
                    this.ListPreCondicion.clear();
                    this.ListPreCondicion = Adm.ConsulPreCondicion();
                    mbTodero.resetTable("RegArcAva:PreCondicionTable");
                    break;
                default:
                    break;
            }
            OpcionHabilitar = "";
            mbTodero.setMens("El registro se ha modificado correctamente");
            mbTodero.info();
            RequestContext.getCurrentInstance().execute("PF('DlgModRegPro').hide()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiReglasProceso()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que se utiliza para limpar y abrir el formulario de producto
     * entidaf, proceso del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void limpiar_froproent() {
        try {
            this.NombretipoProducto = "";
            this.Destipoproducto = "";
            this.CodProEnt = "";
            RequestContext.getCurrentInstance().execute("PF('DlgCrearTipProducEnti').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiar_froproent()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia todas las variables relacionadas con el modulo de
     * administracion, proceso del modulo de administracion
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void limpiarAdministracion() {
        try {

            this.CuentaContable = 0;
            /*Limpia variables de: Cargo, Estado,Producto Entidad,Regimen, Tipo Documento*/
            this.NombreTable = "";
            this.DesTable = "";
            this.estadoProent = false;
            this.panelregimen = false;
            this.selecRegimen = false;
            this.selectproent = "";
            this.selecRegimennn = "";

            /*Tipo Producto Entidad*/
            this.NombretipoProducto = "";
            this.Destipoproducto = "";
            this.paneltiporegimen = false;
            this.selectproent = "";

            /*Tipo Regimen*/
            this.NombreTipoRegimen = "";
            this.EstadoTipoRegimen = "";
            this.estadoTiproent = false;
            this.CodRegimen = 0;

       

            /*Entidades, Sucursales, Asesor*/
            mBEntidad.setNumEntidad("");
            mBEntidad.setNombreEntidad("");
            mBEntidad.setCodEntidadSucursal("");
            mBEntidad.setCodOficina("");
            mBEntidad.setNombreSucursal("");
            mBEntidad.setTelefonoSucursal("");
            mBEntidad.setDireccionSucursal("");
            mBEntidad.setCodDeptoSucursal("");
            mBEntidad.setCodCiudadSucursal("");
            mBEntidad.setFk_entidadAsesor("");
            mBEntidad.setFk_sucursalAsesor("");
            mBEntidad.setNombreAsesor("");
            mBEntidad.setCargoAsesor("");
            mBEntidad.setMailAsesor("");
            mBEntidad.setTelefonoAsesor("");
            mBEntidad.setEstadoAsesor("");

            mBEntidad.setEstPanelEntidad(false);
            mBEntidad.setEstPanelSucursal(false);
            mBEntidad.setEstPanelAsesor(false);

            this.nombreParametro = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarAdministracion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra un nuevo envio de avalúo, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void regisEnvio() {
        try {
            mbTodero.resetTable("FrmEnvio:EnvioTable");
            if ("".equals(this.nombreParametro)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else {
                LogAdministracion admin = new LogAdministracion();
                admin.setGrupoParametro("Pre_Radica");
                admin.setArgumentoParametro("Enviar a");
                admin.setResultadoParametro(nombreParametro);
                admin.InsertParametro(1, mBsesion.codigoMiSesion());
                this.nombreParametro = "";
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                mbTodero.resetTable("FrmEnvio:EnvioTable");
                this.listEnvio.clear();
                this.listEnvio = admin.ConsulEnvio();
                // this.Ad.setCodigoParametro(this.Ad.ConsulCodigoPrincipalParametro("Cod_Parametro", "Enviar a"));
                RequestContext.getCurrentInstance().execute("PF('DlgEnvio').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisEnvio()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica un envio de avalúo, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void modifiEnvio() {
        try {
            mbTodero.resetTable("FrmEnvio:EnvioTable");
            if ("".equals(nombreParametro)) {
                mbTodero.setMens("Favor ingrese la información correspondiente");
                mbTodero.warn();
            } else {
                LogAdministracion admin = new LogAdministracion();
                admin.setCodParametro(codParametro);
                admin.setGrupoParametro("Pre_Radica");
                admin.setArgumentoParametro("Enviar a");
                admin.setResultadoParametro(nombreParametro);
                admin.ModifiParametro(mBsesion.codigoMiSesion());
                codParametro = 0;
                nombreParametro = "";
                admin.setResultadoParametro("");
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("FrmEnvio:EnvioTable");
                this.listEnvio.clear();
                this.listEnvio = admin.ConsulEnvio();
                //this.Ad.setCodigoParametro(this.Ad.ConsulCodigoPrincipalParametro("Cod_Parametro", "Enviar a"));
                RequestContext.getCurrentInstance().execute("PF('DlgEnvio').hide()");

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiEnvio()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que selecciona un tipo de parametro, proceso del modulo de
     * administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void selctparametro() {
        try {
            switch (selectcionParametro) {
                case "Pisos":
                    this.estadofrmEnvio = false;
                    this.estadofrmPisos = true;
                    mbTodero.resetTable("FrmParametro:PisoTable");
                    break;
                case "EnviarA":
                    this.estadofrmPisos = false;
                    this.estadofrmEnvio = true;
                    mbTodero.resetTable("FrmParametro:EnvioTable");
                    break;
                default:
                    this.estadofrmPisos = false;
                    this.estadofrmEnvio = false;
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".selctparametro()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga los empleados para realizar la auditoria, proceso del
     * modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @return List que carga los empleados para filtros de auditoria
     * @since 01-05-2015
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
     * Metodo que consulta informacion necesaria para hacer consultas en la
     * auditoria segun un tipo de filtro , proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @throws java.sql.SQLException
     * @since 01-05-2015
     */
    public void filtrosAuditoria() throws SQLException {
        try {
            empleadoAuditoria = 0;
            fechainicial = null;
            fechafinal = null;
            fechainicialString = "";
            fechafinalString = "";

            switch (opcionFiltroAuditoria) {
                case "1":
                    ListEmpAuditoria = getCargEmpAuditoria();
                    estadoTablaAuditoria = false;

                    break;
                case "2":
                    estadoTablaAuditoria = false;
                    Calendar fecha = new GregorianCalendar();
                    Date fecha1;
                    fecha1 = fecha.getTime();
                    fecha_actual = Format.format(fecha1);
                    break;
                case "3":
                    consultaAuditoriaFiltros(1, 3);
                    estadoTablaAuditoria = true;
                    break;
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".filtrosAuditoria()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica la fecha de los filtros de auditoria, proceso del
     * modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void verifiFec() {
        try {
            if (null != fechafinal && null != fechainicial) {
                if (fechafinal.before(fechainicial)) {
                    fechafinal = null;
                    mbTodero.setMens("La fecha inicial no debe ser mayor a la fecha final");
                    mbTodero.warn();
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiFec()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta y muestra la informacion de la auditaria dependiendo
     * del filtro seleccionado, proceso del modulo de administracion
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int para saber que tipo de forma va a mostrar la
     * informacion de cada filtro de auditoria
     * @param tipo int para saber que por que tipo de filtro se esta consultando
     * @throws java.sql.SQLException
     * @since 01-05-2015
     */
    public void consultaAuditoriaFiltros(int proceso, int tipo) throws SQLException {
        try {
            //   proceso = 1 -- Consulta por filtros
            if (proceso == 1) {
                ListAuditoria = new ArrayList<>();
                // ListAuditoria.clear();
                switch (tipo) {
                    case 1:
                        // por empleado
                        if (empleadoAuditoria == 0) {
                            mbTodero.setMens("Debe seleccionar un empleado");
                            mbTodero.warn();
                        } else {
                            this.ListAuditoria = Adm.ConsulAuditoria(tipo, empleadoAuditoria, null, null);
                            estadoTablaAuditoria = true;
                        }
                        break;
                    case 2:
                        // por fechas

                        if (fechainicial == null && fechafinal == null) {
                            mbTodero.setMens("Debe elegir un rango de fechas");
                            mbTodero.warn();
                        } else if (fechainicial == null) {
                            mbTodero.setMens("Debe elegir la fecha y hora inicial del filtro de consulta");
                            mbTodero.warn();
                        } else if (fechafinal == null) {
                            mbTodero.setMens("Debe elegir la fecha y hora final del filtro de consulta");
                            mbTodero.warn();
                        } else {
                            fechainicialString = Format.format(fechainicial);
                            fechafinalString = Format.format(fechafinal);

                            this.ListAuditoria = Adm.ConsulAuditoria(tipo, 0, fechainicialString, fechafinalString);
                            estadoTablaAuditoria = true;
                        }
                        break;
                    default:
                        // todo
                        this.ListAuditoria = Adm.ConsulAuditoria(tipo, 0, fechainicialString, fechafinalString);
                        estadoTablaAuditoria = true;
                        break;
                }
            } else if (proceso == 2) {  //   proceso = 2 -- Consulta por reporte
                HashMap parametros = new HashMap();

                switch (tipo) {
                    case 1:
                        if (empleadoAuditoria == 0) {
                            mbTodero.setMens("Debe seleccionar un empleado");
                            mbTodero.warn();
                        } else {
                            parametros.put("cod_empleado", empleadoAuditoria);
                            parametros.put("fecha_inicial", null);
                            parametros.put("fecha_final", null);
                            parametros.put("tipo_consulta", 1);
                            String nombreEmpleado = "";
                            for (int i = 0; i <= ListEmpAuditoria.size() - 1; i++) {
                                if (empleadoAuditoria == Integer.parseInt(ListEmpAuditoria.get(i).getValue().toString())) {
                                    nombreEmpleado = ListEmpAuditoria.get(i).getLabel();
                                }
                            }

                            
                        }
                        break;
                    case 2:
                        if (fechainicial == null && fechafinal == null) {
                            mbTodero.setMens("Debe elegir un rango de fechas");
                            mbTodero.warn();
                        } else if (fechainicial == null) {
                            mbTodero.setMens("Debe elegir la fecha y hora inicial del filtro de consulta");
                            mbTodero.warn();
                        } else if (fechafinal == null) {
                            mbTodero.setMens("Debe elegir la fecha y hora final del filtro de consulta");
                            mbTodero.warn();
                        } else {
                            parametros.put("cod_empleado", 0);
                            parametros.put("fecha_inicial", fechainicialString);
                            parametros.put("fecha_final", fechafinalString);
                            parametros.put("tipo_consulta", 2);
                          
                        }
                        break;
                    case 3:
                        parametros.put("cod_empleado", 0);
                        parametros.put("fecha_inicial", null);
                        parametros.put("fecha_final", null);
                        parametros.put("tipo_consulta", 3);
                       
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaAuditoriaFiltros()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

}
/**
 * FIN Metodos de funcionalidad de la clase
 * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
