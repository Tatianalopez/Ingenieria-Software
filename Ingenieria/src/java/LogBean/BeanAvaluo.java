package LogBean;

import Logic.LogAvaluo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el control de avalúos realizados en el
 * aplicativo</b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-10-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBAvaluo")
@ViewScoped
public class BeanAvaluo {

//Variables de Radicacion / Pre-radicacion
    /**
     * Variables / que abarcan toda la clase BeanAvaluo
     * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     */
    /**
     * Variables para la creacion del avaluo (solicitud) *
     */
    private Date FechaSol;
    private Date FechaRadicado;
    private String EstadoAvaluo;
    private boolean RequiereUbicacion;
    private String codDeptoSol;
    private String nombreDeptoSol;
    private String codCiudadSol;
    private String nombreCiudadSol;

    /**
     * Variables para la creacion del avaluo (tipo de avaluo) *
     */
    private String fkCodTipAvaluo;

    /**
     * Variables para la creacion del avaluo (Predio) *
     */
    private String matriculaAval;
    private String confirmatriculaAval;
    private String codTipoPredio;
    private String areaTerreno;
    private String valorAreaTerreno;

    /**
     * Variables para la creacion del avaluo (Maquinaria) *
     */
    private String SerieAval;
    private String confirSerieAval;
    private String marcaMaquin;
    private String modeloMaquin;
    private String observacMaquin;

    /**
     * Variables para la creacion del avaluo (enser) *
     */
    private String observacAval;

    /**
     * Variables para la creacion del avaluo (los tres tipos de avaluos) *
     */
    private String llaveAvaluo;
    private String codTipoBien;
    private String tipoDireccion;
    private String direccionAval;
    private String ConfirDireccionAval;
    private String codDeptoAval;
    private String codCiudadAval;
    private String barrioAval;
    private String Confirm_barrioAval;

    /**
     * Variables para cargar el avaluo en radicacion (tipo de avaluo) *
     */
    private String fkCodTipAvaluoRadic;
    private String nombreTipAvaluoRadic;
    private boolean estadoCmbTipoAval = false;

    /**
     * Variables para cargar el avaluo en radicacion (Predio) *
     */
    private List<String> ListMatriculasPredio = new ArrayList<>();
    private String[] matriculasPredio;
    private String matriculaAvalRadic;

    private String codTipoPredioRadic;
    private String codTipoBienRadic;
    private String nombreTipoPredioRadic;
    private String nombreTipoBienRadic;

    private String areaTerrenoRadic;
    private String valorAreaTerrenoRadic;

    /**
     * Variables para cargar el avaluo en radicacion (Maquinaria) *
     */
    private String SerieAvalRadic;
    private String marcaMaquinRadic;
    private String modeloMaquinRadic;
    private String observacMaquinRadic;

    /**
     * Variables para cargar el avaluo en radicacion (enser) *
     */
    private String observacAvalRadic;

    /**
     * Variables para cargar el avaluo en radicacion (los tres tipos) *
     */
    private String direccionAvalRadic;
    private String tipoDireccionAvalRadic;
    private String codDeptoAvalRadic;
    private String nombreDeptoAvalRadic;

    private String codCiudadAvalRadic;
    private String nombreCiudAvalRadic;

    private String barrioAvalRadic;
    private boolean estadoPanelAvalRadic = false;
    private boolean estadoTipoAvalRadic = false;

    String FechaActual;

    /**
     * Variables para Radicacion (Avaluos temporales) *
     */
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
    private List<LogAvaluo> ListAvaluosTempo = new ArrayList<>();
    private boolean estadoTablaAvaluosTempo = false;
    private boolean validarAvalTemp;

    /**
     * Variables para Radicacion (Generales) *
     */
    private int cod_preRadica;
    private int cod_avaluo;
    private int num_bien_Seguimiento;

    private boolean estadoClosableInfoRegistro = true;
    private boolean estadoVisibleCombosUbiSolic = false;

    private ArrayList<LogAvaluo> DatosTipoAval;

    private ArrayList<SelectItem> CargaCiuSolic = new ArrayList<>();
    private ArrayList<SelectItem> CargaCiuAval = new ArrayList<>();
    ArrayList<SelectItem> CiudadesRadicacion = new ArrayList<>();

    /**
     * Variables para Radicacion (Consulta de bienes) *
     */
    private boolean estadoPanelConsultaBienes = false;
    private boolean estadoRadiosInfoConsultaSeleccion;
    private String opcionConsultaBienes;

    private String estadoRadioSeleccionConsulta;
    private boolean estadoTablaConsultaPredios = false;
    private boolean estadoTablaConsultaMaquin = false;
    private boolean estadoTablaConsultaMueble = false;

    private String tituloDialogBienes = "";

    private List<LogAvaluo> ListConsultaPredios = new ArrayList<>();
    private List<LogAvaluo> ListConsultaMaquin = new ArrayList<>();
    private List<LogAvaluo> ListConsultaMueble = new ArrayList<>();

    private List<LogAvaluo> ListSelectAvaluoXAnti = new ArrayList<>();

    private ArrayList<SelectItem> CargaTipAvaluo = new ArrayList<>();
    private String Sentencia;

    /**
     * Variables que cargan la informacion de los avaluos seleccionados en la
     * preradicacion *
     */
    private List<LogAvaluo> ListPredio = null;
    private List<LogAvaluo> ListMaquinaria = null;
    private List<LogAvaluo> ListEnser = null;
    private List<LogAvaluo> ListAvaluo = null;

    private List<LogAvaluo> PRListPredio = null;
    private List<LogAvaluo> PRListMaquinaria = null;
    private List<LogAvaluo> PRListEnser = null;
    //Sel
    private List<LogAvaluo> SelectPredios;
    private List<LogAvaluo> SelectMaquinaria;
    private List<LogAvaluo> SelectEnseres;
    private List<LogAvaluo> SelectEnser;

    private List<LogAvaluo> SelectResultadoPredios;
    private List<LogAvaluo> SelectResultadoMaquinaria;
    private List<LogAvaluo> SelectResultadoEnseres;

    private boolean estadoTablasResulPred = false;
    private boolean estadoTablasResulMaqui = false;
    private boolean estadoTablasResulEnser = false;

    /**
     * Variables la carga de los avaluos seleccionados (estados de visibilidad)
     * *
     */
    private boolean estadoPanelClienteFacturar = false;
    private boolean estadoPanelGrande = false;
    private boolean estadoPanel = false;
    private boolean estadoInfoPred = false;
    private boolean estadoInfoMaqui = false;
    private boolean estadoInfoResulEnser = false;

    /**
     * Instancia de objetos tipo LogAvaluo que obtienen los atributos y metodos
     * de la clase LogAvaluo.java
     *
     * @see LogAvaluo.java
     */
    LogAvaluo AdmTipoPredios = new LogAvaluo();
    LogAvaluo AdmTipoMaquin = new LogAvaluo();
    LogAvaluo SelectPredio;
    LogAvaluo SelectMaquin;
    LogAvaluo Ava = new LogAvaluo();
    LogAvaluo BienPredio = new LogAvaluo();
    LogAvaluo BienMaquin = new LogAvaluo();
    LogAvaluo BienMueble = new LogAvaluo();
    LogAvaluo AvalTempo = new LogAvaluo();

    /**
     * Instancia de objetos tipo LogUbicacion que obtienen los atributos y
     * metodos de la clase LogUbicacion.java
     *
     * @see LogUbicacion.java
     */
    /**
     * Variable tipo BeanSesion para traer los atributos y metodos de el
     * ManagedBean BeanSesion.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBSesion}")
    private BeanSesion mBSesion;

    /**
     * Variable tipo BeanSesion para traer los atributos y metodos de el
     * ManagedBean BeanSesion.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBSesion}")
    private BeanSesion mBsesion;

    /**
     * Variable tipo BeanTodero para traer los atributos y metodos de el
     * ManagedBean BeanTodero.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBTodero}")
    private BeanTodero mbTodero;

    /**
     * Variables que cargan informacion de los avaluos anteriores *
     */
    private boolean estadoDisableCamposConPreRadica;
    private int cod_AvaluoRadic;
    private String valorAvalAnterior;
    DecimalFormat myFormatter = new DecimalFormat("$ ###,###,###,###,###,###,###.##");

    /**
     * Variables para la gestion de tipo predios y tipo maquinaria *
     */
    private List<LogAvaluo> ListTipPrediosGest = new ArrayList<>();
    private List<LogAvaluo> ListTipMaquienGest = new ArrayList<>();

    private String TituloDialogTipPredios;
    private String TituloDialogTipMaquinaria;

    private int codTipPredioGest;
    private String nombreTipPredioGest;

    private int codTipMaquinGest;
    private String nombreTipMaquinGest;

    private String estadoRadioSeleccionTipBien;

    /**
     * Variables tipo ResultSet que cargan la informacion respectova de los
     * avaluos *
     */
    private ResultSet Dat;
    private ResultSet DatosAvaluos;
    private ResultSet DatoTipoAval;
    private ResultSet DatoTipoPredio;
    private ResultSet DatoTipoDepto;
    private ResultSet DatoTipoCiudad;

    /**
     * Variables que cargan la informacion respectiva de los tipos de avaluos *
     */
    private int CodTipAvaluo;
    private String NomTipAvaluo;
    private String DescripTipAvaluo;
    private String cod_ciudad_aval;

    /**
     * Variable List que carga la lista de avaluos por anticipo *
     */
    private List<LogAvaluo> ListAvaluoXAnti = new ArrayList<>();

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return
     */
    public Date getFechaSol() {
        return FechaSol;
    }

    public void setFechaSol(Date FechaSol) {
        this.FechaSol = FechaSol;
    }

    public Date getFechaRadicado() {
        return FechaRadicado;
    }

    public void setFechaRadicado(Date FechaRadicado) {
        this.FechaRadicado = FechaRadicado;
    }

    public String getEstadoAvaluo() {
        return EstadoAvaluo;
    }

    public void setEstadoAvaluo(String EstadoAvaluo) {
        this.EstadoAvaluo = EstadoAvaluo;
    }

    public boolean isRequiereUbicacion() {
        return RequiereUbicacion;
    }

    public void setRequiereUbicacion(boolean RequiereUbicacion) {
        this.RequiereUbicacion = RequiereUbicacion;
    }

    public String getCodDeptoSol() {
        return codDeptoSol;
    }

    public void setCodDeptoSol(String codDeptoSol) {
        this.codDeptoSol = codDeptoSol;
    }

    public String getNombreDeptoSol() {
        return nombreDeptoSol;
    }

    public void setNombreDeptoSol(String nombreDeptoSol) {
        this.nombreDeptoSol = nombreDeptoSol;
    }

    public String getCodCiudadSol() {
        return codCiudadSol;
    }

    public void setCodCiudadSol(String codCiudadSol) {
        this.codCiudadSol = codCiudadSol;
    }

    public String getNombreCiudadSol() {
        return nombreCiudadSol;
    }

    public void setNombreCiudadSol(String nombreCiudadSol) {
        this.nombreCiudadSol = nombreCiudadSol;
    }

    public String getFkCodTipAvaluo() {
        return fkCodTipAvaluo;
    }

    public void setFkCodTipAvaluo(String fkCodTipAvaluo) {
        this.fkCodTipAvaluo = fkCodTipAvaluo;
    }

    public String getMatriculaAval() {
        return matriculaAval;
    }

    public void setMatriculaAval(String matriculaAval) {
        this.matriculaAval = matriculaAval;
    }

    public String getConfirmatriculaAval() {
        return confirmatriculaAval;
    }

    public void setConfirmatriculaAval(String confirmatriculaAval) {
        this.confirmatriculaAval = confirmatriculaAval;
    }

    public String getCodTipoPredio() {
        return codTipoPredio;
    }

    public void setCodTipoPredio(String codTipoPredio) {
        this.codTipoPredio = codTipoPredio;
    }

    public String getAreaTerreno() {
        return areaTerreno;
    }

    public void setAreaTerreno(String areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public String getValorAreaTerreno() {
        return valorAreaTerreno;
    }

    public void setValorAreaTerreno(String valorAreaTerreno) {
        this.valorAreaTerreno = valorAreaTerreno;
    }

    public String getSerieAval() {
        return SerieAval;
    }

    public void setSerieAval(String SerieAval) {
        this.SerieAval = SerieAval;
    }

    public String getConfirSerieAval() {
        return confirSerieAval;
    }

    public void setConfirSerieAval(String confirSerieAval) {
        this.confirSerieAval = confirSerieAval;
    }

    public String getMarcaMaquin() {
        return marcaMaquin;
    }

    public void setMarcaMaquin(String marcaMaquin) {
        this.marcaMaquin = marcaMaquin;
    }

    public String getModeloMaquin() {
        return modeloMaquin;
    }

    public void setModeloMaquin(String modeloMaquin) {
        this.modeloMaquin = modeloMaquin;
    }

    public String getObservacMaquin() {
        return observacMaquin;
    }

    public void setObservacMaquin(String observacMaquin) {
        this.observacMaquin = observacMaquin;
    }

    public String getObservacAval() {
        return observacAval;
    }

    public void setObservacAval(String observacAval) {
        this.observacAval = observacAval;
    }

    public String getLlaveAvaluo() {
        return llaveAvaluo;
    }

    public void setLlaveAvaluo(String llaveAvaluo) {
        this.llaveAvaluo = llaveAvaluo;
    }

    public String getCodTipoBien() {
        return codTipoBien;
    }

    public void setCodTipoBien(String codTipoBien) {
        this.codTipoBien = codTipoBien;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public String getDireccionAval() {
        return direccionAval;
    }

    public void setDireccionAval(String direccionAval) {
        this.direccionAval = direccionAval;
    }

    public String getConfirDireccionAval() {
        return ConfirDireccionAval;
    }

    public void setConfirDireccionAval(String ConfirDireccionAval) {
        this.ConfirDireccionAval = ConfirDireccionAval;
    }

    public String getCodDeptoAval() {
        return codDeptoAval;
    }

    public void setCodDeptoAval(String codDeptoAval) {
        this.codDeptoAval = codDeptoAval;
    }

    public String getCodCiudadAval() {
        return codCiudadAval;
    }

    public void setCodCiudadAval(String codCiudadAval) {
        this.codCiudadAval = codCiudadAval;
    }

    public String getBarrioAval() {
        return barrioAval;
    }

    public void setBarrioAval(String barrioAval) {
        this.barrioAval = barrioAval;
    }

    public String getConfirm_barrioAval() {
        return Confirm_barrioAval;
    }

    public void setConfirm_barrioAval(String Confirm_barrioAval) {
        this.Confirm_barrioAval = Confirm_barrioAval;
    }

    public String getFkCodTipAvaluoRadic() {
        return fkCodTipAvaluoRadic;
    }

    public void setFkCodTipAvaluoRadic(String fkCodTipAvaluoRadic) {
        this.fkCodTipAvaluoRadic = fkCodTipAvaluoRadic;
    }

    public String getNombreTipAvaluoRadic() {
        return nombreTipAvaluoRadic;
    }

    public void setNombreTipAvaluoRadic(String nombreTipAvaluoRadic) {
        this.nombreTipAvaluoRadic = nombreTipAvaluoRadic;
    }

    public boolean isEstadoCmbTipoAval() {
        return estadoCmbTipoAval;
    }

    public void setEstadoCmbTipoAval(boolean estadoCmbTipoAval) {
        this.estadoCmbTipoAval = estadoCmbTipoAval;
    }

    public List<String> getListMatriculasPredio() {
        return ListMatriculasPredio;
    }

    public void setListMatriculasPredio(List<String> ListMatriculasPredio) {
        this.ListMatriculasPredio = ListMatriculasPredio;
    }

    public String[] getMatriculasPredio() {
        return matriculasPredio;
    }

    public void setMatriculasPredio(String[] matriculasPredio) {
        this.matriculasPredio = matriculasPredio;
    }

    public String getMatriculaAvalRadic() {
        return matriculaAvalRadic;
    }

    public void setMatriculaAvalRadic(String matriculaAvalRadic) {
        this.matriculaAvalRadic = matriculaAvalRadic;
    }

    public String getCodTipoPredioRadic() {
        return codTipoPredioRadic;
    }

    public void setCodTipoPredioRadic(String codTipoPredioRadic) {
        this.codTipoPredioRadic = codTipoPredioRadic;
    }

    public String getCodTipoBienRadic() {
        return codTipoBienRadic;
    }

    public void setCodTipoBienRadic(String codTipoBienRadic) {
        this.codTipoBienRadic = codTipoBienRadic;
    }

    public String getNombreTipoPredioRadic() {
        return nombreTipoPredioRadic;
    }

    public void setNombreTipoPredioRadic(String nombreTipoPredioRadic) {
        this.nombreTipoPredioRadic = nombreTipoPredioRadic;
    }

    public String getNombreTipoBienRadic() {
        return nombreTipoBienRadic;
    }

    public void setNombreTipoBienRadic(String nombreTipoBienRadic) {
        this.nombreTipoBienRadic = nombreTipoBienRadic;
    }

    public String getAreaTerrenoRadic() {
        return areaTerrenoRadic;
    }

    public void setAreaTerrenoRadic(String areaTerrenoRadic) {
        this.areaTerrenoRadic = areaTerrenoRadic;
    }

    public String getValorAreaTerrenoRadic() {
        return valorAreaTerrenoRadic;
    }

    public void setValorAreaTerrenoRadic(String valorAreaTerrenoRadic) {
        this.valorAreaTerrenoRadic = valorAreaTerrenoRadic;
    }

    public String getSerieAvalRadic() {
        return SerieAvalRadic;
    }

    public void setSerieAvalRadic(String SerieAvalRadic) {
        this.SerieAvalRadic = SerieAvalRadic;
    }

    public String getMarcaMaquinRadic() {
        return marcaMaquinRadic;
    }

    public void setMarcaMaquinRadic(String marcaMaquinRadic) {
        this.marcaMaquinRadic = marcaMaquinRadic;
    }

    public String getModeloMaquinRadic() {
        return modeloMaquinRadic;
    }

    public void setModeloMaquinRadic(String modeloMaquinRadic) {
        this.modeloMaquinRadic = modeloMaquinRadic;
    }

    public String getObservacMaquinRadic() {
        return observacMaquinRadic;
    }

    public void setObservacMaquinRadic(String observacMaquinRadic) {
        this.observacMaquinRadic = observacMaquinRadic;
    }

    public String getObservacAvalRadic() {
        return observacAvalRadic;
    }

    public void setObservacAvalRadic(String observacAvalRadic) {
        this.observacAvalRadic = observacAvalRadic;
    }

    public String getDireccionAvalRadic() {
        return direccionAvalRadic;
    }

    public void setDireccionAvalRadic(String direccionAvalRadic) {
        this.direccionAvalRadic = direccionAvalRadic;
    }

    public String getTipoDireccionAvalRadic() {
        return tipoDireccionAvalRadic;
    }

    public void setTipoDireccionAvalRadic(String tipoDireccionAvalRadic) {
        this.tipoDireccionAvalRadic = tipoDireccionAvalRadic;
    }

    public String getCodDeptoAvalRadic() {
        return codDeptoAvalRadic;
    }

    public void setCodDeptoAvalRadic(String codDeptoAvalRadic) {
        this.codDeptoAvalRadic = codDeptoAvalRadic;
    }

    public String getNombreDeptoAvalRadic() {
        return nombreDeptoAvalRadic;
    }

    public void setNombreDeptoAvalRadic(String nombreDeptoAvalRadic) {
        this.nombreDeptoAvalRadic = nombreDeptoAvalRadic;
    }

    public String getCodCiudadAvalRadic() {
        return codCiudadAvalRadic;
    }

    public void setCodCiudadAvalRadic(String codCiudadAvalRadic) {
        this.codCiudadAvalRadic = codCiudadAvalRadic;
    }

    public String getNombreCiudAvalRadic() {
        return nombreCiudAvalRadic;
    }

    public void setNombreCiudAvalRadic(String nombreCiudAvalRadic) {
        this.nombreCiudAvalRadic = nombreCiudAvalRadic;
    }

    public String getBarrioAvalRadic() {
        return barrioAvalRadic;
    }

    public void setBarrioAvalRadic(String barrioAvalRadic) {
        this.barrioAvalRadic = barrioAvalRadic;
    }

    public boolean isEstadoPanelAvalRadic() {
        return estadoPanelAvalRadic;
    }

    public void setEstadoPanelAvalRadic(boolean estadoPanelAvalRadic) {
        this.estadoPanelAvalRadic = estadoPanelAvalRadic;
    }

    public boolean isEstadoTipoAvalRadic() {
        return estadoTipoAvalRadic;
    }

    public void setEstadoTipoAvalRadic(boolean estadoTipoAvalRadic) {
        this.estadoTipoAvalRadic = estadoTipoAvalRadic;
    }

    public String getFechaActual() {
        return FechaActual;
    }

    public void setFechaActual(String FechaActual) {
        this.FechaActual = FechaActual;
    }

    public List<LogAvaluo> getListAvaluosTempo() {
        return ListAvaluosTempo;
    }

    public void setListAvaluosTempo(List<LogAvaluo> ListAvaluosTempo) {
        this.ListAvaluosTempo = ListAvaluosTempo;
    }

    public boolean isEstadoTablaAvaluosTempo() {
        return estadoTablaAvaluosTempo;
    }

    public void setEstadoTablaAvaluosTempo(boolean estadoTablaAvaluosTempo) {
        this.estadoTablaAvaluosTempo = estadoTablaAvaluosTempo;
    }

    public boolean isValidarAvalTemp() {
        return validarAvalTemp;
    }

    public void setValidarAvalTemp(boolean validarAvalTemp) {
        this.validarAvalTemp = validarAvalTemp;
    }

    public int getCod_preRadica() {
        return cod_preRadica;
    }

    public void setCod_preRadica(int cod_preRadica) {
        this.cod_preRadica = cod_preRadica;
    }

    public int getCod_avaluo() {
        return cod_avaluo;
    }

    public void setCod_avaluo(int cod_avaluo) {
        this.cod_avaluo = cod_avaluo;
    }

    public int getNum_bien_Seguimiento() {
        return num_bien_Seguimiento;
    }

    public void setNum_bien_Seguimiento(int num_bien_Seguimiento) {
        this.num_bien_Seguimiento = num_bien_Seguimiento;
    }

    public boolean isEstadoClosableInfoRegistro() {
        return estadoClosableInfoRegistro;
    }

    public void setEstadoClosableInfoRegistro(boolean estadoClosableInfoRegistro) {
        this.estadoClosableInfoRegistro = estadoClosableInfoRegistro;
    }

    public boolean isEstadoVisibleCombosUbiSolic() {
        return estadoVisibleCombosUbiSolic;
    }

    public void setEstadoVisibleCombosUbiSolic(boolean estadoVisibleCombosUbiSolic) {
        this.estadoVisibleCombosUbiSolic = estadoVisibleCombosUbiSolic;
    }

    public ArrayList<LogAvaluo> getDatosTipoAval() {
        return DatosTipoAval;
    }

    public void setDatosTipoAval(ArrayList<LogAvaluo> DatosTipoAval) {
        this.DatosTipoAval = DatosTipoAval;
    }

    public ArrayList<SelectItem> getCargaCiuSolic() {
        return CargaCiuSolic;
    }

    public void setCargaCiuSolic(ArrayList<SelectItem> CargaCiuSolic) {
        this.CargaCiuSolic = CargaCiuSolic;
    }

    public ArrayList<SelectItem> getCargaCiuAval() {
        return CargaCiuAval;
    }

    public void setCargaCiuAval(ArrayList<SelectItem> CargaCiuAval) {
        this.CargaCiuAval = CargaCiuAval;
    }

    public ArrayList<SelectItem> getCiudadesRadicacion() {
        return CiudadesRadicacion;
    }

    public void setCiudadesRadicacion(ArrayList<SelectItem> CiudadesRadicacion) {
        this.CiudadesRadicacion = CiudadesRadicacion;
    }

    public boolean isEstadoPanelConsultaBienes() {
        return estadoPanelConsultaBienes;
    }

    public void setEstadoPanelConsultaBienes(boolean estadoPanelConsultaBienes) {
        this.estadoPanelConsultaBienes = estadoPanelConsultaBienes;
    }

    public boolean isEstadoRadiosInfoConsultaSeleccion() {
        return estadoRadiosInfoConsultaSeleccion;
    }

    public void setEstadoRadiosInfoConsultaSeleccion(boolean estadoRadiosInfoConsultaSeleccion) {
        this.estadoRadiosInfoConsultaSeleccion = estadoRadiosInfoConsultaSeleccion;
    }

    public String getOpcionConsultaBienes() {
        return opcionConsultaBienes;
    }

    public void setOpcionConsultaBienes(String opcionConsultaBienes) {
        this.opcionConsultaBienes = opcionConsultaBienes;
    }

    public String getEstadoRadioSeleccionConsulta() {
        return estadoRadioSeleccionConsulta;
    }

    public void setEstadoRadioSeleccionConsulta(String estadoRadioSeleccionConsulta) {
        this.estadoRadioSeleccionConsulta = estadoRadioSeleccionConsulta;
    }

    public boolean isEstadoTablaConsultaPredios() {
        return estadoTablaConsultaPredios;
    }

    public void setEstadoTablaConsultaPredios(boolean estadoTablaConsultaPredios) {
        this.estadoTablaConsultaPredios = estadoTablaConsultaPredios;
    }

    public boolean isEstadoTablaConsultaMaquin() {
        return estadoTablaConsultaMaquin;
    }

    public void setEstadoTablaConsultaMaquin(boolean estadoTablaConsultaMaquin) {
        this.estadoTablaConsultaMaquin = estadoTablaConsultaMaquin;
    }

    public boolean isEstadoTablaConsultaMueble() {
        return estadoTablaConsultaMueble;
    }

    public void setEstadoTablaConsultaMueble(boolean estadoTablaConsultaMueble) {
        this.estadoTablaConsultaMueble = estadoTablaConsultaMueble;
    }

    public String getTituloDialogBienes() {
        return tituloDialogBienes;
    }

    public void setTituloDialogBienes(String tituloDialogBienes) {
        this.tituloDialogBienes = tituloDialogBienes;
    }

    public List<LogAvaluo> getListConsultaPredios() {
        return ListConsultaPredios;
    }

    public void setListConsultaPredios(List<LogAvaluo> ListConsultaPredios) {
        this.ListConsultaPredios = ListConsultaPredios;
    }

    public List<LogAvaluo> getListConsultaMaquin() {
        return ListConsultaMaquin;
    }

    public void setListConsultaMaquin(List<LogAvaluo> ListConsultaMaquin) {
        this.ListConsultaMaquin = ListConsultaMaquin;
    }

    public List<LogAvaluo> getListConsultaMueble() {
        return ListConsultaMueble;
    }

    public void setListConsultaMueble(List<LogAvaluo> ListConsultaMueble) {
        this.ListConsultaMueble = ListConsultaMueble;
    }

    public List<LogAvaluo> getListSelectAvaluoXAnti() {
        return ListSelectAvaluoXAnti;
    }

    public void setListSelectAvaluoXAnti(List<LogAvaluo> ListSelectAvaluoXAnti) {
        this.ListSelectAvaluoXAnti = ListSelectAvaluoXAnti;
    }

    public ArrayList<SelectItem> getCargaTipAvaluo() {
        return CargaTipAvaluo;
    }

    public void setCargaTipAvaluo(ArrayList<SelectItem> CargaTipAvaluo) {
        this.CargaTipAvaluo = CargaTipAvaluo;
    }

    public List<LogAvaluo> getListPredio() {
        return ListPredio;
    }

    public void setListPredio(List<LogAvaluo> ListPredio) {
        this.ListPredio = ListPredio;
    }

    public List<LogAvaluo> getListMaquinaria() {
        return ListMaquinaria;
    }

    public void setListMaquinaria(List<LogAvaluo> ListMaquinaria) {
        this.ListMaquinaria = ListMaquinaria;
    }

    public List<LogAvaluo> getListEnser() {
        return ListEnser;
    }

    public void setListEnser(List<LogAvaluo> ListEnser) {
        this.ListEnser = ListEnser;
    }

    public List<LogAvaluo> getListAvaluo() {
        return ListAvaluo;
    }

    public void setListAvaluo(List<LogAvaluo> ListAvaluo) {
        this.ListAvaluo = ListAvaluo;
    }

    public List<LogAvaluo> getPRListPredio() {
        return PRListPredio;
    }

    public void setPRListPredio(List<LogAvaluo> PRListPredio) {
        this.PRListPredio = PRListPredio;
    }

    public List<LogAvaluo> getPRListMaquinaria() {
        return PRListMaquinaria;
    }

    public void setPRListMaquinaria(List<LogAvaluo> PRListMaquinaria) {
        this.PRListMaquinaria = PRListMaquinaria;
    }

    public List<LogAvaluo> getPRListEnser() {
        return PRListEnser;
    }

    public void setPRListEnser(List<LogAvaluo> PRListEnser) {
        this.PRListEnser = PRListEnser;
    }

    public List<LogAvaluo> getSelectPredios() {
        return SelectPredios;
    }

    public void setSelectPredios(List<LogAvaluo> SelectPredios) {
        this.SelectPredios = SelectPredios;
    }

    public List<LogAvaluo> getSelectMaquinaria() {
        return SelectMaquinaria;
    }

    public void setSelectMaquinaria(List<LogAvaluo> SelectMaquinaria) {
        this.SelectMaquinaria = SelectMaquinaria;
    }

    public List<LogAvaluo> getSelectEnseres() {
        return SelectEnseres;
    }

    public void setSelectEnseres(List<LogAvaluo> SelectEnseres) {
        this.SelectEnseres = SelectEnseres;
    }

    public List<LogAvaluo> getSelectEnser() {
        return SelectEnser;
    }

    public void setSelectEnser(List<LogAvaluo> SelectEnser) {
        this.SelectEnser = SelectEnser;
    }

    public List<LogAvaluo> getSelectResultadoPredios() {
        return SelectResultadoPredios;
    }

    public void setSelectResultadoPredios(List<LogAvaluo> SelectResultadoPredios) {
        this.SelectResultadoPredios = SelectResultadoPredios;
    }

    public List<LogAvaluo> getSelectResultadoMaquinaria() {
        return SelectResultadoMaquinaria;
    }

    public void setSelectResultadoMaquinaria(List<LogAvaluo> SelectResultadoMaquinaria) {
        this.SelectResultadoMaquinaria = SelectResultadoMaquinaria;
    }

    public List<LogAvaluo> getSelectResultadoEnseres() {
        return SelectResultadoEnseres;
    }

    public void setSelectResultadoEnseres(List<LogAvaluo> SelectResultadoEnseres) {
        this.SelectResultadoEnseres = SelectResultadoEnseres;
    }

    public boolean isEstadoTablasResulPred() {
        return estadoTablasResulPred;
    }

    public void setEstadoTablasResulPred(boolean estadoTablasResulPred) {
        this.estadoTablasResulPred = estadoTablasResulPred;
    }

    public boolean isEstadoTablasResulMaqui() {
        return estadoTablasResulMaqui;
    }

    public void setEstadoTablasResulMaqui(boolean estadoTablasResulMaqui) {
        this.estadoTablasResulMaqui = estadoTablasResulMaqui;
    }

    public boolean isEstadoTablasResulEnser() {
        return estadoTablasResulEnser;
    }

    public void setEstadoTablasResulEnser(boolean estadoTablasResulEnser) {
        this.estadoTablasResulEnser = estadoTablasResulEnser;
    }

    public boolean isEstadoPanelClienteFacturar() {
        return estadoPanelClienteFacturar;
    }

    public void setEstadoPanelClienteFacturar(boolean estadoPanelClienteFacturar) {
        this.estadoPanelClienteFacturar = estadoPanelClienteFacturar;
    }

    public boolean isEstadoPanelGrande() {
        return estadoPanelGrande;
    }

    public void setEstadoPanelGrande(boolean estadoPanelGrande) {
        this.estadoPanelGrande = estadoPanelGrande;
    }

    public boolean isEstadoPanel() {
        return estadoPanel;
    }

    public void setEstadoPanel(boolean estadoPanel) {
        this.estadoPanel = estadoPanel;
    }

    public boolean isEstadoInfoPred() {
        return estadoInfoPred;
    }

    public void setEstadoInfoPred(boolean estadoInfoPred) {
        this.estadoInfoPred = estadoInfoPred;
    }

    public boolean isEstadoInfoMaqui() {
        return estadoInfoMaqui;
    }

    public void setEstadoInfoMaqui(boolean estadoInfoMaqui) {
        this.estadoInfoMaqui = estadoInfoMaqui;
    }

    public boolean isEstadoInfoResulEnser() {
        return estadoInfoResulEnser;
    }

    public void setEstadoInfoResulEnser(boolean estadoInfoResulEnser) {
        this.estadoInfoResulEnser = estadoInfoResulEnser;
    }

    public LogAvaluo getAdmTipoPredios() {
        return AdmTipoPredios;
    }

    public void setAdmTipoPredios(LogAvaluo AdmTipoPredios) {
        this.AdmTipoPredios = AdmTipoPredios;
    }

    public LogAvaluo getAdmTipoMaquin() {
        return AdmTipoMaquin;
    }

    public void setAdmTipoMaquin(LogAvaluo AdmTipoMaquin) {
        this.AdmTipoMaquin = AdmTipoMaquin;
    }

    public LogAvaluo getSelectPredio() {
        return SelectPredio;
    }

    public void setSelectPredio(LogAvaluo SelectPredio) {
        this.SelectPredio = SelectPredio;
    }

    public LogAvaluo getSelectMaquin() {
        return SelectMaquin;
    }

    public void setSelectMaquin(LogAvaluo SelectMaquin) {
        this.SelectMaquin = SelectMaquin;
    }

    public LogAvaluo getAva() {
        return Ava;
    }

    public void setAva(LogAvaluo Ava) {
        this.Ava = Ava;
    }

    public LogAvaluo getBienPredio() {
        return BienPredio;
    }

    public void setBienPredio(LogAvaluo BienPredio) {
        this.BienPredio = BienPredio;
    }

    public LogAvaluo getBienMaquin() {
        return BienMaquin;
    }

    public void setBienMaquin(LogAvaluo BienMaquin) {
        this.BienMaquin = BienMaquin;
    }

    public LogAvaluo getBienMueble() {
        return BienMueble;
    }

    public void setBienMueble(LogAvaluo BienMueble) {
        this.BienMueble = BienMueble;
    }

    public LogAvaluo getAvalTempo() {
        return AvalTempo;
    }

    public void setAvalTempo(LogAvaluo AvalTempo) {
        this.AvalTempo = AvalTempo;
    }

    public BeanSesion getmBSesion() {
        return mBSesion;
    }

    public void setmBSesion(BeanSesion mBSesion) {
        this.mBSesion = mBSesion;
    }

    public BeanSesion getmBsesion() {
        return mBsesion;
    }

    public void setmBsesion(BeanSesion mBsesion) {
        this.mBsesion = mBsesion;
    }

    public BeanTodero getMbTodero() {
        return mbTodero;
    }

    public void setMbTodero(BeanTodero mbTodero) {
        this.mbTodero = mbTodero;
    }

    public boolean isEstadoDisableCamposConPreRadica() {
        return estadoDisableCamposConPreRadica;
    }

    public void setEstadoDisableCamposConPreRadica(boolean estadoDisableCamposConPreRadica) {
        this.estadoDisableCamposConPreRadica = estadoDisableCamposConPreRadica;
    }

    public int getCod_AvaluoRadic() {
        return cod_AvaluoRadic;
    }

    public void setCod_AvaluoRadic(int cod_AvaluoRadic) {
        this.cod_AvaluoRadic = cod_AvaluoRadic;
    }

    public String getValorAvalAnterior() {
        return valorAvalAnterior;
    }

    public void setValorAvalAnterior(String valorAvalAnterior) {
        this.valorAvalAnterior = valorAvalAnterior;
    }

    public DecimalFormat getMyFormatter() {
        return myFormatter;
    }

    public void setMyFormatter(DecimalFormat myFormatter) {
        this.myFormatter = myFormatter;
    }

    public List<LogAvaluo> getListTipPrediosGest() {
        return ListTipPrediosGest;
    }

    public void setListTipPrediosGest(List<LogAvaluo> ListTipPrediosGest) {
        this.ListTipPrediosGest = ListTipPrediosGest;
    }

    public List<LogAvaluo> getListTipMaquienGest() {
        return ListTipMaquienGest;
    }

    public void setListTipMaquienGest(List<LogAvaluo> ListTipMaquienGest) {
        this.ListTipMaquienGest = ListTipMaquienGest;
    }

    public String getTituloDialogTipPredios() {
        return TituloDialogTipPredios;
    }

    public void setTituloDialogTipPredios(String TituloDialogTipPredios) {
        this.TituloDialogTipPredios = TituloDialogTipPredios;
    }

    public String getTituloDialogTipMaquinaria() {
        return TituloDialogTipMaquinaria;
    }

    public void setTituloDialogTipMaquinaria(String TituloDialogTipMaquinaria) {
        this.TituloDialogTipMaquinaria = TituloDialogTipMaquinaria;
    }

    public int getCodTipPredioGest() {
        return codTipPredioGest;
    }

    public void setCodTipPredioGest(int codTipPredioGest) {
        this.codTipPredioGest = codTipPredioGest;
    }

    public String getNombreTipPredioGest() {
        return nombreTipPredioGest;
    }

    public void setNombreTipPredioGest(String nombreTipPredioGest) {
        this.nombreTipPredioGest = nombreTipPredioGest;
    }

    public int getCodTipMaquinGest() {
        return codTipMaquinGest;
    }

    public void setCodTipMaquinGest(int codTipMaquinGest) {
        this.codTipMaquinGest = codTipMaquinGest;
    }

    public String getNombreTipMaquinGest() {
        return nombreTipMaquinGest;
    }

    public void setNombreTipMaquinGest(String nombreTipMaquinGest) {
        this.nombreTipMaquinGest = nombreTipMaquinGest;
    }

    public String getEstadoRadioSeleccionTipBien() {
        return estadoRadioSeleccionTipBien;
    }

    public void setEstadoRadioSeleccionTipBien(String estadoRadioSeleccionTipBien) {
        this.estadoRadioSeleccionTipBien = estadoRadioSeleccionTipBien;
    }

    public int getCodTipAvaluo() {
        return CodTipAvaluo;
    }

    public void setCodTipAvaluo(int CodTipAvaluo) {
        this.CodTipAvaluo = CodTipAvaluo;
    }

    public String getNomTipAvaluo() {
        return NomTipAvaluo;
    }

    public void setNomTipAvaluo(String NomTipAvaluo) {
        this.NomTipAvaluo = NomTipAvaluo;
    }

    public String getDescripTipAvaluo() {
        return DescripTipAvaluo;
    }

    public void setDescripTipAvaluo(String DescripTipAvaluo) {
        this.DescripTipAvaluo = DescripTipAvaluo;
    }

    public String getCod_ciudad_aval() {
        return cod_ciudad_aval;
    }

    public void setCod_ciudad_aval(String cod_ciudad_aval) {
        this.cod_ciudad_aval = cod_ciudad_aval;
    }

    public List<LogAvaluo> getListAvaluoXAnti() {
        return ListAvaluoXAnti;
    }

    public void setListAvaluoXAnti(List<LogAvaluo> ListAvaluoXAnti) {
        this.ListAvaluoXAnti = ListAvaluoXAnti;
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
     * Metodo init para cargar los tipos de avaluós
     *
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    @PostConstruct
    public void init() {
        try {
            this.CargaTipAvaluo = cargTipAvaluo();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".init()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo constructor para cargar TODOS los avaluos que se tienen hechos
     * hasta el momento
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public BeanAvaluo() {
        try {
            LogAvaluo Aval = new LogAvaluo();
            this.ListPredio = Aval.getConsultarAllPredios();
            this.ListMaquinaria = Aval.getConsultarAllMaquinaria();
            this.ListEnser = Aval.getConsultarAllEnser();
            Calendar fecha = new GregorianCalendar();
            Date fecha1 = new Date();
            fecha1 = fecha.getTime();
            FechaActual = fecha1.toString();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".BeanAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga los tipos de avaluos en un SelectOneMenu
     *
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList con los tipos de avaluos
     * @since 01-10-2014
     */
    public List getSelectTipoAv() {
        return SelectTipoAv;
    }

    public void setSelectTipoAv(List SelectTipoAv) {
        this.SelectTipoAv = SelectTipoAv;
    }

    private List SelectTipoAv = null;

    public ArrayList<SelectItem> cargTipAvaluo() {
        try {
            LogAvaluo Aval = new LogAvaluo();
            Iterator<LogAvaluo> Ite;
            Ite = Aval.getTipAvaluo().iterator();
            SelectTipoAv = new ArrayList<>();
            while (Ite.hasNext()) {
                LogAvaluo MBAva = Ite.next();
                this.CargaTipAvaluo.add(new SelectItem(MBAva.getCodTipAvaluo(), MBAva.getNomTipAvaluo()));
                //     SelectTipoAv.add(new SelectItem(MBAva.getCodTipAvaluo(), MBAva.getNomTipAvaluo()));

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargTipAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaTipAvaluo;

    }

    /**
     * Metodo que cambia los estados de las tablas de los tipos de avaluos
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_tabla String con el tipo de tabla que se va a mostrar
     * @since 01-10-2014
     */
    public void cambarEstadoTablas(String tipo_tabla) {
        try {
            Ava.setLlaveTB("");
            Ava.setDireccionTB("");
            this.estadoInfoMaqui = false;
            this.estadoInfoPred = false;
            this.estadoInfoResulEnser = false;

            if ("Pre".equals(tipo_tabla)) {
                if (this.SelectPredios.isEmpty()) {

                    mbTodero.setMens("Debe seleccionar un Predio");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoPredio').show()");
                } else if (this.SelectPredios.size() == 1) {

                    this.estadoTablasResulPred = true; //pone visible la tabla
                    this.estadoTablasResulMaqui = false; //pone invisible la tabla
                    this.estadoTablasResulEnser = false; //pone invisible la tabla
                    this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
                    this.estadoPanelGrande = false;

                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoPredio').hide()");
                } else {
                    this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectPredios = null;
                    mbTodero.setMens("Solo debe seleccionar un  unico predio");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoPredio').show()");
                }
            }
            if ("Maq".equals(tipo_tabla)) {
                if (this.SelectMaquinaria.isEmpty()) {

                    mbTodero.setMens("Debe seleccionar una Maquinaria");
                    mbTodero.warn();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoMaquinaria').show()");
                } else if (this.SelectMaquinaria.size() == 1) {
                    this.estadoTablasResulPred = false; //pone visible la tabla
                    this.estadoTablasResulMaqui = true; //falsa
                    this.estadoTablasResulEnser = false; //falsa
                    this.SelectPredios = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
                    this.estadoPanelGrande = false;
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoMaquinaria').hide()");
                } else {
                    this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectPredios = null;
                    mbTodero.setMens("Solo debe seleccionar una maquinaria");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoMaquinaria').show()");
                }
            }
            if ("ens".equals(tipo_tabla)) {
                if (this.SelectEnseres.isEmpty()) {
                    mbTodero.setMens("Debe seleccionar un Mueble o Enser");
                    mbTodero.warn();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoEnser').show()");
                } else if (this.SelectEnseres.size() == 1) {
                    this.estadoTablasResulPred = false; //pone visible la tabla
                    this.estadoTablasResulMaqui = false; //pone invisible la tabla
                    this.estadoTablasResulEnser = true; //pone invisible la tabla
                    this.SelectPredios = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
                    this.estadoPanelGrande = false;
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoEnser').hide()");
                } else {
                    this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
                    this.SelectPredios = null;
                    mbTodero.setMens("Solo debe seleccionar un enser");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoEnser').show()");
                }
            }
            RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(1)");
            RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(2)");
            RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(2)");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambarEstadoTablas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cambiar el estado de las cajas y label que se muestran segun
     * el tipo de predio
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void cambiarEstadoInfo() {
        try {
            this.SelectPredios = null;
            this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
            this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
            Ava.setLlaveTB("");
            Ava.setDireccionTB("");
            if (this.CodTipAvaluo == 1) {

                this.estadoPanel = true;
                estadoInfoPred = true;
                estadoInfoMaqui = false;
                estadoInfoResulEnser = false;
                limpiarCajasRegistroBien();
                if (RequiereUbicacion == true) {
                } else {
                }
            } else if (this.CodTipAvaluo == 2) {
                this.estadoPanel = true;
                estadoInfoPred = false;
                estadoInfoMaqui = true;
                estadoInfoResulEnser = false;
                limpiarCajasRegistroBien();
                if (RequiereUbicacion == true) {

                } else {

                }

            } else if (this.CodTipAvaluo == 3) {
                estadoPanel = true;
                estadoInfoPred = false;
                estadoInfoMaqui = false;
                estadoInfoResulEnser = true;
                limpiarCajasRegistroBien();
                if (RequiereUbicacion == true) {

                } else {

                }

            } else {
                this.estadoPanel = false;

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambiarEstadoInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para cargar la informacion de que Anticipo se Desea Subir segun el
     * Tipo de Avaluo Seleccionado
     *
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public void cargueTipAvaAnticipo() {
        if (getCodTipAvaluo() == 0) {
            mbTodero.setMens("Debe seleccionar un tipo de avalúo para continuar");
            mbTodero.warn();
        } else {
            try {
                Ava.setCodTipAvaluo(getCodTipAvaluo());
                Dat = Ava.ConsulTipAva();
                if (Dat.next()) { //Si la consulta traer informacion sobre el tipo de avaluo que fue seleccionado
                    switch (Dat.getString("Nombre_TipAvaluo")) {
                        case "Predio":
                            break;
                        case "Maquinaria":
                            break;
                        default:
                            break;
                    }
                }
                Conexion.Conexion.CloseCon();
                ListAvaluoXAnti = new ArrayList<>();
                ListAvaluoXAnti.clear();
                ListAvaluoXAnti = new ArrayList<>();
                //Consulta la informacion de los Avaluos que se encuentran sin facturar y ya radicados
                ListAvaluoXAnti = Ava.ConsultaAvaXAntic(mBSesion.codigoMiSesion());
                Conexion.Conexion.CloseCon();
                mbTodero.resetTable("FRMAntic:RadicadosTable");
            } catch (SQLException e) {
                mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargueTipAvaAnticipo()' causado por: " + e.getMessage());
                mbTodero.error();
            }
        }
    }

    /**
     * Metodo que Verifica la información que se desea subir de anticipos
     *
     * @author Maira Alejandra Carvajal
     * @since 01-10-2014
     */
    public void verificacionAnticipo() {
        try {
            if (getCodTipAvaluo() == 0 || FechaSol == null) {
                mbTodero.setMens("Falta informacion obligatoria, favor verifique nuevamente");
                mbTodero.warn();
            } else if (this.ListSelectAvaluoXAnti.isEmpty()) {
                mbTodero.setMens("No ha seleccionado ningun Avaluo para asociar el anticipo");
                mbTodero.warn();
            } else {
                //mBArchi.setNAvaluo(ListSelectAvaluoXAnti.get(i).getCodAvaluo());

                String fechaSoli = Format.format(FechaSol);

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificacionAnticipo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que limpia las variables de las tablas que muestran los avalúos
     * existentes
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void cargar() {
        try {
            this.estadoPanelGrande = true;
            this.estadoPanel = false;
            this.estadoTablasResulEnser = false;
            this.estadoTablasResulMaqui = false;
            this.estadoTablasResulPred = false;
            this.SelectPredios = null;
            this.SelectMaquinaria = null; //limpia la lista y deschequea lo seleccionado
            this.SelectEnseres = null; //limpia la lista y deschequea lo seleccionado
            this.CodTipAvaluo = 0;
            Ava.setLlaveTB("");
            Ava.setDireccionTB("");
            this.cod_ciudad_aval = "";

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargar()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para seleccionar el tipo de avaluo que se carga en los datatable
     * de la preradicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param Tip int que contiene el tipo de avaluo que se va a cargar
     * @param CodPR int que contiene el codigo de la preradicacion a cargar
     * @since 01-10-2014
     */
    public void carTipAvaluoPreRad(int Tip, int CodPR) {
        try {
            if (Tip == 1) {
                PRListPredio = Ava.getConsultarPredioPreRad(CodPR, 1);
            } else if (Tip == 2) {
                PRListMaquinaria = Ava.getConsultarMaquinariaPreRad(CodPR, 1);
            } else {
                PRListEnser = Ava.getConsultarEnserPreRad(CodPR, 1);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".carTipAvaluoPreRad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que agrega la informacion seleccionada de avaluos temporales en la
     * cajas de informacion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void agregarDatosAvalTemp() {
        try {
            if (AvalTempo == null) {
                mbTodero.setMens("Debe seleccionar el registro");
                mbTodero.warn();
            } else {
                switch (AvalTempo.getFk_tipo_avalTempo()) {
                    case "1":
                        CodTipAvaluo = Integer.parseInt(AvalTempo.getFk_tipo_avalTempo());
                        cambiarEstadoInfo();
                        matriculaAval = AvalTempo.getLlaveTB();
                        break;
                    case "2":
                        CodTipAvaluo = Integer.parseInt(AvalTempo.getFk_tipo_avalTempo());
                        cambiarEstadoInfo();
                        SerieAval = AvalTempo.getLlaveTB();
                        break;
                    case "3":
                        CodTipAvaluo = Integer.parseInt(AvalTempo.getFk_tipo_avalTempo());
                        cambiarEstadoInfo();
                        observacAval = AvalTempo.getLlaveTB();
                        break;
                }
                direccionAval = AvalTempo.getDireccionTB();
                codDeptoAval = AvalTempo.getFk_cod_deptoTempo();

                codCiudadAval = AvalTempo.getFk_cod_ciudadTempo();
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarDatosAvalTemp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que limpia los campos usados para generar la dirección
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void limpiarCampoDireccion() {
        try {
            direccionAval = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCampoDireccion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia TODOS los campos usados para generar la dirección
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void limpiarTodosCamposDirecciones() {
        try {

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCampoDireccion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que realiza las validaciones de los campos de dirección
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void validacionDireccion() {
        try {

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validacionDireccion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que agrega todos los datos del avalúo ingresados en el formulario
     * de Radicación
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que permite agregar o actualizar la informacion del bien
     * segun sea el caso
     * @since 01-10-2014
     */
    public void registrarAvaluo(int tipo) {
        /*
         Tipo = 1: Agregar datos del bien, 2: Actualizar bien
         */
        try {
            if (CodTipAvaluo == 0) {
                mbTodero.setMens("Debe seleccional el campo 'Tipo de bien'");
                mbTodero.warn();
            } else if (CodTipAvaluo == 1) {
                if (("".equals(matriculaAval) || matriculaAval == null) && (("".equals(codTipoPredio) || codTipoPredio == null))
                        && (("".equals(areaTerreno) || areaTerreno == null)) && (("".equals(valorAreaTerreno) || valorAreaTerreno == null))
                        && (("".equals(direccionAval) || direccionAval == null)) && (("".equals(codDeptoAval) || codDeptoAval == null))
                        && (("".equals(codCiudadAval) || codCiudadAval == null))) {
                    mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                    mbTodero.warn();
                } else if (("".equals(codTipoBien) || codTipoBien == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Tipo de predio'");
                    mbTodero.warn();
                } else if (("".equals(matriculaAval) || matriculaAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Matrícula'");
                    mbTodero.warn();
                } else if (("".equals(direccionAval) || direccionAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Dirección'");
                    mbTodero.warn();
                } else if (("".equals(ConfirDireccionAval) || ConfirDireccionAval == null) && "Rural".equals(tipoDireccion)) {
                    mbTodero.setMens("Debe confirmar la Dirección");
                    mbTodero.warn();
                } else if ((!direccionAval.equals(ConfirDireccionAval)) && "Rural".equals(tipoDireccion)) {
                    mbTodero.setMens("Las direcciones no coinciden");
                    mbTodero.warn();
                } else if (("".equals(codDeptoAval) || codDeptoAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Departamento'");
                    mbTodero.warn();
                } else if (("".equals(codCiudadAval) || codCiudadAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Ciudad'");
                    mbTodero.warn();
                } else if ((CodTipAvaluo == 1) && (!"".equals(barrioAval) || barrioAval != null) && ("".equals(Confirm_barrioAval) || Confirm_barrioAval == null)) {
                    mbTodero.setMens("Debe confirmar el Barrio");
                    mbTodero.warn();
                } else if ((CodTipAvaluo == 1) && (!barrioAval.equals(Confirm_barrioAval))) {
                    mbTodero.setMens("Los barrios no coinciden");
                    mbTodero.warn();
                } else {

                    estadoInfoPred = true;
                    estadoInfoMaqui = false;
                    estadoInfoResulEnser = false;
                    fkCodTipAvaluoRadic = String.valueOf(CodTipAvaluo);
                    //Consulta tipo_avaluo
                    Ava.setCodTipAvaluo(Integer.parseInt(fkCodTipAvaluoRadic));
                    DatoTipoAval = Ava.ConsulTipAva();
                    if (DatoTipoAval.next()) {
                        nombreTipAvaluoRadic = DatoTipoAval.getString("Nombre_tipAvaluo");
                    }
                    Conexion.Conexion.CloseCon();
                    matriculaAvalRadic = matriculaAval;
                    codTipoBienRadic = codTipoBien;
                    //Consulta tipo_predio
                    Ava.setCodTipBien(Integer.parseInt(codTipoBienRadic));
                    DatoTipoPredio = Ava.ConsulTipPredio();
                    if (DatoTipoPredio.next()) {
                        nombreTipoBienRadic = DatoTipoPredio.getString("Resultado_Parametro");
                    }
                    Conexion.Conexion.CloseCon();
                    areaTerrenoRadic = areaTerreno;
                    valorAreaTerrenoRadic = valorAreaTerreno;
                    direccionAvalRadic = direccionAval;
                    tipoDireccionAvalRadic = tipoDireccion;
                    codDeptoAvalRadic = codDeptoAval;
                    //Consulta depto

                    if (DatoTipoDepto.next()) {
                        nombreDeptoAvalRadic = DatoTipoDepto.getString("Nombre_departamento");
                    }
                    Conexion.Conexion.CloseCon();
                    codCiudadAvalRadic = codCiudadAval;
                    //consulta ciudad

                    if (DatoTipoCiudad.next()) {
                        nombreCiudAvalRadic = DatoTipoCiudad.getString("Nombre_Ciudad");
                    }
                    Conexion.Conexion.CloseCon();
                    barrioAvalRadic = barrioAval;

                    estadoTipoAvalRadic = true;
                    estadoPanelAvalRadic = true;
                    if (tipo == 1) {
                        RequestContext.getCurrentInstance().execute("PF('RegistroBienes').hide()");
                        mbTodero.setMens("Bien agregado correctamente");
                        mbTodero.info();
                        limpiarCajasRegistroBien();
                    }

                    if (tipo == 2) {
                        Ava.setCodTipBien(Integer.parseInt(codTipoBien));
                        Ava.setCodAvaluo(cod_avaluo);
                        Ava.setNumeroBienSeguimiento(String.valueOf(num_bien_Seguimiento));
                        Ava.setTipoDireccion(tipoDireccion);
                        Ava.setDireccion(direccionAval);
                        Ava.setCiudad(codCiudadAval);
                        Ava.setBarrio(barrioAval);
                        Ava.setMatricula(matriculaAval);
                        Ava.setMarcaMaquinaria(marcaMaquin);
                        Ava.setModeloMaquinaria(modeloMaquin);
                        Ava.setSerieMaquinaria(SerieAval);
                        Ava.setObservacionMaquinaria(observacMaquin);
                        Ava.setObservacionMueble(observacAval);
                        Ava.ActualizarInfoBien(4, mBSesion.codigoMiSesion());
                        RequestContext.getCurrentInstance().execute("PF('RegistroBienes').hide()");
                        estadoTipoAvalRadic = true;
                        estadoPanelAvalRadic = true;
                        mbTodero.setMens("Bien actualizado correctamente");
                        mbTodero.info();
                        limpiarCajasRegistroBien();
                    }

                }
                //Maquinaria
            } else if (CodTipAvaluo == 2) {
                if (("".equals(SerieAval) || SerieAval == null) && (("".equals(marcaMaquin) || marcaMaquin == null))
                        && (("".equals(modeloMaquin) || modeloMaquin == null)) && (("".equals(observacMaquin) || observacMaquin == null))
                        && (("".equals(direccionAval) || direccionAval == null)) && (("".equals(codDeptoAval) || codDeptoAval == null))
                        && (("".equals(codCiudadAval) || codCiudadAval == null))) {
                    mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                    mbTodero.warn();
                } else if (("".equals(codTipoBien) || codTipoBien == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Tipo de maquinaria'");
                    mbTodero.warn();
                } else if (("".equals(SerieAval) || SerieAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Serie'");
                    mbTodero.warn();
                } else if (("".equals(marcaMaquin) || marcaMaquin == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Marca'");
                    mbTodero.warn();
                } else if (("".equals(modeloMaquin) || modeloMaquin == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Modelo'");
                    mbTodero.warn();
                } else if (("".equals(observacMaquin) || observacMaquin == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Observación'");
                    mbTodero.warn();
                } else if (("".equals(direccionAval) || direccionAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Dirección'");
                    mbTodero.warn();
                } else if (("".equals(ConfirDireccionAval) || ConfirDireccionAval == null) && "Rural".equals(tipoDireccion)) {
                    mbTodero.setMens("Debe confirmar la Dirección");
                    mbTodero.warn();
                } else if ((!direccionAval.equals(ConfirDireccionAval))) {
                    mbTodero.setMens("Las direcciones no coinciden");
                    mbTodero.warn();
                } else if (("".equals(codDeptoAval) || codDeptoAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Departamento'");
                    mbTodero.warn();
                } else if (("".equals(codCiudadAval) || codCiudadAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Ciudad'");
                    mbTodero.warn();
                } else {

                    if (tipo == 1) {
                        estadoInfoPred = false;
                        estadoInfoMaqui = true;
                        estadoInfoResulEnser = false;
                        fkCodTipAvaluoRadic = String.valueOf(CodTipAvaluo);
                        //Consulta tipo_avaluo
                        Ava.setCodTipAvaluo(Integer.parseInt(fkCodTipAvaluoRadic));
                        DatoTipoAval = Ava.ConsulTipAva();
                        if (DatoTipoAval.next()) {
                            nombreTipAvaluoRadic = DatoTipoAval.getString("Nombre_tipAvaluo");
                        }
                        Conexion.Conexion.CloseCon();
                        codTipoBienRadic = codTipoBien;
                        Ava.setCodTipBien(Integer.parseInt(codTipoBienRadic));

                        DatoTipoPredio = Ava.ConsulTipMaquinaria();
                        if (DatoTipoPredio.next()) {
                            nombreTipoBienRadic = DatoTipoPredio.getString("Resultado_Parametro");
                        }
                        Conexion.Conexion.CloseCon();
                        SerieAvalRadic = SerieAval;
                        marcaMaquinRadic = marcaMaquin;
                        modeloMaquinRadic = modeloMaquin;
                        observacMaquinRadic = observacMaquin;
                        tipoDireccionAvalRadic = tipoDireccion;
                        direccionAvalRadic = direccionAval;
                        codDeptoAvalRadic = codDeptoAval;
                        //Consulta depto

                        if (DatoTipoDepto.next()) {
                            nombreDeptoAvalRadic = DatoTipoDepto.getString("Nombre_departamento");
                        }
                        Conexion.Conexion.CloseCon();
                        codCiudadAvalRadic = codCiudadAval;
                        //consulta ciudad

                        if (DatoTipoCiudad.next()) {
                            nombreCiudAvalRadic = DatoTipoCiudad.getString("Nombre_Ciudad");
                        }
                        Conexion.Conexion.CloseCon();
                        barrioAvalRadic = barrioAval;
                        RequestContext.getCurrentInstance().execute("PF('RegistroBienes').hide()");
                        estadoTipoAvalRadic = true;
                        estadoPanelAvalRadic = true;
                        mbTodero.setMens("Bien agregado correctamente");
                        mbTodero.info();
                        limpiarCajasRegistroBien();
                    }

                    if (tipo == 2) {
                        Ava.setCodTipBien(Integer.parseInt(codTipoBien));
                        Ava.setCodAvaluo(cod_avaluo);
                        Ava.setNumeroBienSeguimiento(String.valueOf(num_bien_Seguimiento));
                        Ava.setTipoDireccion(tipoDireccion);
                        Ava.setDireccion(direccionAval);
                        Ava.setCiudad(codCiudadAval);
                        Ava.setBarrio(barrioAval);
                        Ava.setMatricula(matriculaAval);
                        Ava.setMarcaMaquinaria(marcaMaquin);
                        Ava.setModeloMaquinaria(modeloMaquin);
                        Ava.setSerieMaquinaria(SerieAval);
                        Ava.setObservacionMaquinaria(observacMaquin);
                        Ava.setObservacionMueble(observacAval);
                        Ava.ActualizarInfoBien(5, mBSesion.codigoMiSesion());
                        RequestContext.getCurrentInstance().execute("PF('RegistroBienes').hide()");
                        estadoTipoAvalRadic = true;
                        estadoPanelAvalRadic = true;
                        mbTodero.setMens("Bien actualizado correctamente");
                        mbTodero.info();
                        // limpiarCajasRegistroBien();
                    }

                }
                //enser
            } else if (CodTipAvaluo == 3) {
                if (("".equals(observacAval) || observacAval == null)
                        && (("".equals(direccionAval) || direccionAval == null)) && (("".equals(codDeptoAval) || codDeptoAval == null))
                        && (("".equals(codCiudadAval) || codCiudadAval == null))) {
                    mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                    mbTodero.warn();
                } else if (("".equals(observacAval) || observacAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Observación'");
                    mbTodero.warn();
                } else if (("".equals(ConfirDireccionAval) || ConfirDireccionAval == null)) {
                    mbTodero.setMens("Debe confirmar la Dirección");
                    mbTodero.warn();
                } else if (("".equals(direccionAval) || direccionAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Dirección'");
                    mbTodero.warn();
                } else if ((!direccionAval.equals(ConfirDireccionAval)) && "Rural".equals(tipoDireccion)) {
                    mbTodero.setMens("Las direcciones no coinciden");
                    mbTodero.warn();
                } else if (("".equals(codDeptoAval) || codDeptoAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Departamento'");
                    mbTodero.warn();
                } else if (("".equals(codCiudadAval) || codCiudadAval == null)) {
                    mbTodero.setMens("Debe llenar el campo 'Ciudad'");
                    mbTodero.warn();
                } else {

                    if (tipo == 1) {
                        estadoInfoPred = false;
                        estadoInfoMaqui = false;
                        estadoInfoResulEnser = true;
                        fkCodTipAvaluoRadic = String.valueOf(CodTipAvaluo);
                        //Consulta tipo_avaluo
                        Ava.setCodTipAvaluo(Integer.parseInt(fkCodTipAvaluoRadic));
                        DatoTipoAval = Ava.ConsulTipAva();
                        if (DatoTipoAval.next()) {
                            nombreTipAvaluoRadic = DatoTipoAval.getString("Nombre_tipAvaluo");
                        }
                        Conexion.Conexion.CloseCon();
                        observacAvalRadic = observacAval;
                        tipoDireccionAvalRadic = tipoDireccion;
                        direccionAvalRadic = direccionAval;
                        codDeptoAvalRadic = codDeptoAval;
                        //Consulta depto

                        if (DatoTipoDepto.next()) {
                            nombreDeptoAvalRadic = DatoTipoDepto.getString("Nombre_departamento");
                        }
                        Conexion.Conexion.CloseCon();
                        codCiudadAvalRadic = codCiudadAval;
                        //consulta ciudad

                        if (DatoTipoCiudad.next()) {
                            nombreCiudAvalRadic = DatoTipoCiudad.getString("Nombre_Ciudad");
                        }
                        Conexion.Conexion.CloseCon();

                        barrioAvalRadic = barrioAval;
                        RequestContext.getCurrentInstance().execute("PF('RegistroBienes').hide()");
                        estadoTipoAvalRadic = true;
                        estadoPanelAvalRadic = true;
                        mbTodero.setMens("Bien agregado correctamente");
                        mbTodero.info();
                        limpiarCajasRegistroBien();
                    }

                    if (tipo == 2) {
                        Ava.setCodTipBien(Integer.parseInt(codTipoBien));
                        Ava.setCodAvaluo(cod_avaluo);
                        Ava.setNumeroBienSeguimiento(String.valueOf(num_bien_Seguimiento));
                        Ava.setTipoDireccion(tipoDireccion);
                        Ava.setDireccion(direccionAval);
                        Ava.setCiudad(codCiudadAval);
                        Ava.setBarrio(barrioAval);
                        Ava.setMatricula(matriculaAval);
                        Ava.setMarcaMaquinaria(marcaMaquin);
                        Ava.setModeloMaquinaria(modeloMaquin);
                        Ava.setSerieMaquinaria(SerieAval);
                        Ava.setObservacionMaquinaria(observacMaquin);
                        Ava.setObservacionMueble(observacAval);
                        Ava.ActualizarInfoBien(6, mBSesion.codigoMiSesion());
                        RequestContext.getCurrentInstance().execute("PF('RegistroBienes').hide()");
                        estadoTipoAvalRadic = true;
                        estadoPanelAvalRadic = true;
                        mbTodero.setMens("Bien actualizado correctamente");
                        mbTodero.info();
                        // limpiarCajasRegistroBien();
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".registrarAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que limpia el formulario de registro de bien cuanto el tipo es
     * predio
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void limpiarCamposBienPredio() {
        try {
            codTipoPredio = "0";
            llaveAvaluo = "";
            tipoDireccion = "";
            matriculaAval = "";
            confirmatriculaAval = "";
            areaTerreno = "";
            valorAreaTerreno = "";
            direccionAval = "";
            ConfirDireccionAval = "";
            codDeptoAval = "0";
            codCiudadAval = "0";
            barrioAval = "";
            Confirm_barrioAval = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCamposBienPredio()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia el formulario de registro de bien cuanto el tipo es
     * maquinaria
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void limpiarCamposBienMaquin() {
        try {
            llaveAvaluo = "";
            tipoDireccion = "";
            SerieAval = "";
            marcaMaquin = "";
            modeloMaquin = "";
            observacMaquin = "";

            direccionAval = "";
            ConfirDireccionAval = "";
            codDeptoAval = "0";

            codCiudadAval = "0";
            barrioAval = "";
            Confirm_barrioAval = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCamposBienMaquin()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia el formulario de registro de bien cuanto el tipo es
     * mueble
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void limpiarCamposBienMueble() {
        try {
            llaveAvaluo = "";
            tipoDireccion = "";
            observacAval = "";
            direccionAval = "";
            ConfirDireccionAval = "";
            codDeptoAval = "0";
            codCiudadAval = "0";
            barrioAval = "";
            Confirm_barrioAval = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCamposBienMueble()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia las variables de los campos de el registro del bien
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void limpiarCajasRegistroBien() {
        try {
            if (CodTipAvaluo == 1) {
                limpiarCamposBienPredio();
            } else if (CodTipAvaluo == 2) {
                limpiarCamposBienMaquin();
            } else if (CodTipAvaluo == 3) {
                limpiarCamposBienMueble();
            } else {
                estadoRadioSeleccionConsulta = "";
                estadoTablaConsultaPredios = false;
                estadoTablaConsultaMaquin = false;
                estadoTablaConsultaMueble = false;
                CodTipAvaluo = 0;
                limpiarCamposBienPredio();
                limpiarCamposBienMaquin();
                limpiarCamposBienMueble();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCajasRegistroBien()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta la informacion de los bienes en las tablas
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void consultaBienes() {
        try {
            if (cod_preRadica == 0 && ("".equals(nombreTipAvaluoRadic) || nombreTipAvaluoRadic == null)) {

                ListConsultaPredios = Ava.getConsultarAllPredios();
                ListConsultaMaquin = Ava.getConsultarAllMaquinaria();
                ListConsultaMueble = Ava.getConsultarAllEnser();
                limpiarCajasRegistroBien();
                CodTipAvaluo = 0;
                estadoPanel = false;
                estadoRadiosInfoConsultaSeleccion = true;
                estadoDisableCamposConPreRadica = false;
                estadoPanelConsultaBienes = false;
                opcionConsultaBienes = "";
                estadoCmbTipoAval = false;
                estadoTablaAvaluosTempo = false;
            } else {
                mbTodero.setMens("Por favor complete la información del bien para continuar con el proceso");
                mbTodero.info();
                estadoCmbTipoAval = true;
                estadoRadiosInfoConsultaSeleccion = false;
                estadoDisableCamposConPreRadica = true;
                estadoPanelConsultaBienes = false;
                opcionConsultaBienes = "";
                estadoCmbTipoAval = true;
                estadoTablaAvaluosTempo = false;

                if ("Predio".equals(nombreTipAvaluoRadic) || ("Predio".equals(nombreTipAvaluoRadic) && cod_preRadica == 0)) {
                    CodTipAvaluo = 1;
                    cambiarEstadoInfo();
                    codTipoBien = codTipoBienRadic;
                    matriculaAval = matriculaAvalRadic;

                } else if ("Maquinaria".equals(nombreTipAvaluoRadic) || ("Maquinaria".equals(nombreTipAvaluoRadic) && cod_preRadica == 0)) {
                    CodTipAvaluo = 2;
                    cambiarEstadoInfo();
                    SerieAval = SerieAvalRadic;
                    codTipoBien = codTipoBienRadic;
                    marcaMaquin = marcaMaquinRadic;
                    modeloMaquin = modeloMaquinRadic;
                    observacMaquin = observacMaquinRadic;

                } else if ("Mueble".equals(nombreTipAvaluoRadic) || ("Mueble".equals(nombreTipAvaluoRadic) && cod_preRadica == 0)) {
                    CodTipAvaluo = 3;
                    cambiarEstadoInfo();
                    observacAval = observacAvalRadic;

                }
                tipoDireccion = tipoDireccionAvalRadic;
                direccionAval = direccionAvalRadic;
                ConfirDireccionAval = direccionAvalRadic;
                CargaCiuAval.clear();
                codDeptoAval = codDeptoAvalRadic;

                codCiudadAval = codCiudadAvalRadic;
                barrioAval = barrioAvalRadic;
                Confirm_barrioAval = barrioAvalRadic;
            }

            tituloDialogBienes = "Información de bienes";
            estadoClosableInfoRegistro = true;
            RequestContext.getCurrentInstance().execute("PF('RegistroBienes').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaBienes()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que hace que se muestre la informacion de los bienes en las
     * respectivas tablas
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void mostrarTablaConsultaBienes() {
        try {
            switch (opcionConsultaBienes) {
                case "Si_Consulta":
                    estadoPanelConsultaBienes = true;
                    estadoRadioSeleccionConsulta = "";
                    estadoTablaConsultaPredios = false;
                    estadoTablaConsultaMaquin = false;
                    estadoTablaConsultaMueble = false;
                    estadoCmbTipoAval = true;
                    estadoPanel = false;
                    CodTipAvaluo = 0;
                    limpiarCajasRegistroBien();
                    break;
                case "No_Consulta":
                    estadoPanelConsultaBienes = false;
                    estadoRadioSeleccionConsulta = "";
                    estadoTablaConsultaPredios = false;
                    estadoTablaConsultaMaquin = false;
                    estadoTablaConsultaMueble = false;
                    estadoCmbTipoAval = false;
                    estadoPanel = false;
                    CodTipAvaluo = 0;
                    cod_AvaluoRadic = 0;
                    limpiarCajasRegistroBien();
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarTablaConsultaBienes()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que cambia de estado la visibilidad de las tablas de los bienes
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void visibleTablasConsulta() {
        try {
            switch (estadoRadioSeleccionConsulta) {
                case "Predios":
                    limpiarCamposBienMueble();
                    limpiarCamposBienMaquin();
                    estadoPanel = false;
                    estadoTablaConsultaPredios = true;
                    estadoTablaConsultaMaquin = false;
                    estadoTablaConsultaMueble = false;
                    mbTodero.resetTable("FormRadicacion:ClTableConsultaBienPredio");
                    ListConsultaPredios.clear();
                    ListConsultaPredios = Ava.getConsultarAllPredios();
                    break;
                case "Maquinaria":
                    limpiarCamposBienPredio();
                    limpiarCamposBienMueble();
                    estadoPanel = false;
                    estadoTablaConsultaPredios = false;
                    estadoTablaConsultaMaquin = true;
                    estadoTablaConsultaMueble = false;
                    mbTodero.resetTable("FormRadicacion:ClTableConsultaBienMaquin");
                    ListConsultaMaquin.clear();
                    ListConsultaMaquin = Ava.getConsultarAllMaquinaria();
                    break;
                case "MuebleEnser":
                    limpiarCamposBienPredio();
                    limpiarCamposBienMaquin();
                    estadoPanel = false;
                    estadoTablaConsultaPredios = false;
                    estadoTablaConsultaMaquin = false;
                    estadoTablaConsultaMueble = true;
                    mbTodero.resetTable("FormRadicacion:ClTableConsultaBienMueble");
                    ListConsultaMueble.clear();
                    ListConsultaMueble = Ava.getConsultarAllEnser();
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".visibleTablasConsulta()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que agrega los datos del bien en la pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_bien int que carga el tipo de avaluo
     * @since 01-10-2014
     */
    public void agregarDatosBienes(int tipo_bien) {
        try {
            estadoCmbTipoAval = true;
            estadoDisableCamposConPreRadica = true;
            if (tipo_bien == 1) {
                if (BienPredio == null) {
                    mbTodero.setMens("Debe seleccionar un predio");
                    mbTodero.warn();
                } else {
                    cod_AvaluoRadic = BienPredio.getCodAvaluo();
                    CodTipAvaluo = BienPredio.getCodTipAvaluo();
                    cambiarEstadoInfo();
                    codTipoPredio = String.valueOf(BienPredio.getCodTipPredio());
                    matriculaAval = BienPredio.getMatricula_predio();
                    areaTerreno = BienPredio.getAreaTerreno();
                    valorAreaTerreno = String.valueOf(BienPredio.getValorMetroTerreno());
                    direccionAval = BienPredio.getDireccion();
                    codDeptoAval = BienPredio.getFk_codDepto();

                    codCiudadAval = BienPredio.getFk_codCiudad();
                    barrioAval = BienPredio.getBarrio();

                }
            } else if (tipo_bien == 2) {
                if (BienMaquin == null) {
                    mbTodero.setMens("Debe seleccionar una maquinaria");
                    mbTodero.warn();
                } else {
                    cod_AvaluoRadic = BienMaquin.getCodAvaluo();
                    CodTipAvaluo = BienMaquin.getCodTipAvaluo();
                    cambiarEstadoInfo();
                    SerieAval = BienMaquin.getSerieMaquinaria();
                    marcaMaquin = BienMaquin.getMarcaMaquinaria();
                    modeloMaquin = BienMaquin.getModeloMaquinaria();
                    observacMaquin = BienMaquin.getObservacionMaquinaria();
                    direccionAval = BienMaquin.getDireccion();
                    codDeptoAval = BienMaquin.getFk_codDepto();

                    codCiudadAval = BienMaquin.getFk_codCiudad();
                    barrioAval = BienMaquin.getBarrio();
                }
            } else if (tipo_bien == 3) {
                if (BienMueble == null) {
                    mbTodero.setMens("Debe seleccionar un mueble");
                    mbTodero.warn();
                } else {
                    cod_AvaluoRadic = BienMueble.getCodAvaluo();
                    CodTipAvaluo = BienMueble.getCodTipAvaluo();
                    cambiarEstadoInfo();
                    observacAval = BienMueble.getObservacionMueble();
                    direccionAval = BienMueble.getDireccion();
                    codDeptoAval = BienMueble.getFk_codDepto();

                    codCiudadAval = BienMueble.getFk_codCiudad();
                    barrioAval = BienMueble.getBarrio();
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarDatosBienes()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que funciona como ajax que limpia las confirmaciones de
     * direcciones
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_caja String tipo que trae cual es el tipo de confirmacion a
     * limpiar
     * @since 01-10-2014
     */
    public void limpiarConfirmarAjax(String tipo_caja) {
        try {
            switch (tipo_caja) {
                case "Direccion":
                    ConfirDireccionAval = "";
                    break;
                case "Barrio":
                    Confirm_barrioAval = "";
                    break;
                case "Matricula":
                    confirmatriculaAval = "";
                    break;
                case "Serie":
                    confirSerieAval = "";
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarConfirmarAjax()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga toda la informacion del bien seleccionado en
     * pre-radicacion en la radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int que contiene que tipo de carga de infomacion realizara
     * @since 01-10-2014
     */
    public void cargarAvaluoRadicacion(int proceso) {
        try {
            // proceso: 1=Desde Pre-Radicacion, 2=Desde Radicacion

            //Si el bien es temporal
            if (proceso == 1) {
                ListAvaluosTempo = Ava.ConsultaTempoBienRadi(String.valueOf(cod_preRadica));
                estadoRadiosInfoConsultaSeleccion = false;
                if (ListAvaluosTempo.size() > 0) {
                    //Pone visibles los paneles de informacion de bien
                    estadoTipoAvalRadic = true;
                    estadoPanelAvalRadic = true;

                    //Pre-Radicacion form
                    estadoPanelGrande = true;
                    estadoPanel = true;

                    for (int i = 0; i <= ListAvaluosTempo.size() - 1; i++) {
                        switch (ListAvaluosTempo.get(0).getNombreTipoAvalTempo()) {
                            case "Predio":
                                estadoInfoPred = true;
                                estadoInfoMaqui = false;
                                estadoInfoResulEnser = false;

                                //Pre-radicacion                                 
                                codTipoBien = String.valueOf(ListAvaluosTempo.get(0).getCodTipBien());
                                llaveAvaluo = ListAvaluosTempo.get(i).getLlaveTB();
                                confirmatriculaAval = ListAvaluosTempo.get(i).getLlaveTB();

                                matriculaAvalRadic = ListAvaluosTempo.get(i).getLlaveTB();
                                codTipoBienRadic = String.valueOf(ListAvaluosTempo.get(0).getCodTipBien());
                                nombreTipoBienRadic = ListAvaluosTempo.get(0).getNombreTipBien();
                                break;
                            case "Maquinaria":
                                estadoInfoPred = false;
                                estadoInfoMaqui = true;
                                estadoInfoResulEnser = false;

                                //Pre-radicacion  
                                codTipoBien = String.valueOf(ListAvaluosTempo.get(0).getCodTipBien());
                                llaveAvaluo = ListAvaluosTempo.get(i).getLlaveTB();
                                confirSerieAval = ListAvaluosTempo.get(i).getLlaveTB();
                                observacMaquin = ListAvaluosTempo.get(0).getObservacionMaquinaria();
                                SerieAvalRadic = ListAvaluosTempo.get(0).getLlaveTB();
                                observacMaquinRadic = ListAvaluosTempo.get(0).getObservacionMaquinaria();
                                codTipoBienRadic = String.valueOf(ListAvaluosTempo.get(0).getCodTipBien());
                                nombreTipoBienRadic = ListAvaluosTempo.get(0).getNombreTipBien();
                                break;
                            case "Mueble":
                                estadoInfoPred = false;
                                estadoInfoMaqui = false;
                                estadoInfoResulEnser = true;

                                //Pre-radicacion  
                                llaveAvaluo = ListAvaluosTempo.get(i).getLlaveTB();
                                observacAvalRadic = ListAvaluosTempo.get(0).getLlaveTB();
                                break;
                        }

                        //Pre-radicacion
                        CodTipAvaluo = Integer.parseInt(ListAvaluosTempo.get(0).getFk_tipo_avalTempo());
                        tipoDireccion = ListAvaluosTempo.get(0).getTipoDireccion();
                        direccionAval = ListAvaluosTempo.get(i).getDireccionTB();
                        ConfirDireccionAval = ListAvaluosTempo.get(i).getDireccionTB();

                        fkCodTipAvaluoRadic = ListAvaluosTempo.get(0).getFk_tipo_avalTempo();
                        nombreTipAvaluoRadic = ListAvaluosTempo.get(0).getNombreTipoAvalTempo();

                        tipoDireccionAvalRadic = ListAvaluosTempo.get(i).getTipoDireccion();
                        direccionAvalRadic = ListAvaluosTempo.get(i).getDireccionTB();

                        codDeptoAvalRadic = ListAvaluosTempo.get(i).getFk_cod_deptoTempo();
                        nombreDeptoAvalRadic = ListAvaluosTempo.get(i).getNombreDeptoTempo();

                        codCiudadAvalRadic = ListAvaluosTempo.get(i).getFk_cod_ciudadTempo();
                        nombreCiudAvalRadic = ListAvaluosTempo.get(i).getNombreCiudadTempo();

                        // RequestContext.getCurrentInstance().execute("PF('RegistroBienes').show()");
                    }

                } else {
                    DatosAvaluos = Ava.ConsulTipAvaPreRad(cod_preRadica);
                    if (DatosAvaluos.next()) {
                        estadoVisibleCombosUbiSolic = true;
                        codDeptoSol = DatosAvaluos.getString("Fk_Cod_Departamento");

                        codCiudadSol = DatosAvaluos.getString("Fk_Cod_Ciudad");

                        cod_AvaluoRadic = DatosAvaluos.getInt("Cod_Avaluo");

                        fkCodTipAvaluoRadic = DatosAvaluos.getString("Cod_TipAvaluo");
                        nombreTipAvaluoRadic = DatosAvaluos.getString("Nombre_TipAvaluo");

                        //Pone visibles los paneles de informacion de bien
                        estadoTipoAvalRadic = true;
                        estadoPanelAvalRadic = true;

                        //Predio
                        switch (nombreTipAvaluoRadic) {
                            case "Predio":
                                //Pre-Radicacion
                                estadoTablasResulPred = true;
                                SelectPredios = Ava.getConsultarPredioPreRad(cod_preRadica, 1);
                                DatosTipoAval = Ava.getConsultarPredioPreRad(cod_preRadica, 1);
                                ListMatriculasPredio.clear();
                                for (int i = 0; i <= DatosTipoAval.size() - 1; i++) {

                                    estadoInfoPred = true;
                                    estadoInfoMaqui = false;
                                    estadoInfoResulEnser = false;

                                    ListMatriculasPredio.add(DatosTipoAval.get(i).getMatricula_predio());
                                    matriculaAvalRadic = DatosTipoAval.get(i).getMatricula();
                                    codTipoBienRadic = String.valueOf(DatosTipoAval.get(i).getCodTipPredio());
                                    nombreTipoBienRadic = DatosTipoAval.get(i).getNombreTipPredio();

                                    areaTerrenoRadic = DatosTipoAval.get(i).getAreaTerreno();
                                    valorAreaTerrenoRadic = String.valueOf(DatosTipoAval.get(i).getValorMetroTerreno());

                                    direccionAvalRadic = DatosTipoAval.get(i).getDireccion();
                                    tipoDireccionAvalRadic = DatosTipoAval.get(0).getTipoDireccion();

                                    codDeptoAvalRadic = DatosTipoAval.get(i).getFk_codDepto();
                                    nombreDeptoAvalRadic = DatosTipoAval.get(i).getNombreDepto();

                                    codCiudadAvalRadic = DatosTipoAval.get(i).getFk_codCiudad();
                                    nombreCiudAvalRadic = DatosTipoAval.get(i).getCiudad();

                                    barrioAvalRadic = DatosTipoAval.get(i).getBarrio();

                                }
//                            matriculaAvalRadic = "";
//                            for (int i = 0; i <= ListMatriculasPredio.size() - 1; i++) {
//
//                                matriculaAvalRadic += " " + ListMatriculasPredio.get(i);
//                            }
                                //Maquinaria
                                break;
                            case "Maquinaria":

                                //Pre-Radicacion
                                estadoTablasResulMaqui = true;
                                SelectMaquinaria = Ava.getConsultarMaquinariaPreRad(cod_preRadica, 1);

                                DatosTipoAval = Ava.getConsultarMaquinariaPreRad(cod_preRadica, 1);

                                if (DatosTipoAval.size() > 0) {

                                    estadoInfoPred = false;
                                    estadoInfoMaqui = true;
                                    estadoInfoResulEnser = false;

                                    codTipoBienRadic = String.valueOf(DatosTipoAval.get(0).getCodTipMaquin());
                                    nombreTipoBienRadic = DatosTipoAval.get(0).getNombreTipMaquinaria();

                                    SerieAvalRadic = DatosTipoAval.get(0).getSerieMaquinaria();
                                    marcaMaquinRadic = DatosTipoAval.get(0).getMarcaMaquinaria();
                                    modeloMaquinRadic = DatosTipoAval.get(0).getModeloMaquinaria();
                                    observacMaquinRadic = DatosTipoAval.get(0).getObservacionMaquinaria();

                                    tipoDireccionAvalRadic = DatosTipoAval.get(0).getTipoDireccion();
                                    direccionAvalRadic = DatosTipoAval.get(0).getDireccion();

                                    codDeptoAvalRadic = DatosTipoAval.get(0).getFk_codDepto();
                                    nombreDeptoAvalRadic = DatosTipoAval.get(0).getNombreDepto();

                                    codCiudadAvalRadic = DatosTipoAval.get(0).getFk_codCiudad();
                                    nombreCiudAvalRadic = DatosTipoAval.get(0).getCiudad();

                                }

                                //enser
                                break;
                            case "Mueble":

                                //Pre-Radicacion
                                estadoTablasResulEnser = true;
                                SelectEnseres = Ava.getConsultarEnserPreRad(cod_preRadica, 1);
                                DatosTipoAval = Ava.getConsultarEnserPreRad(cod_preRadica, 1);
                                if (DatosTipoAval.size() > 0) {

                                    estadoInfoPred = false;
                                    estadoInfoMaqui = false;
                                    estadoInfoResulEnser = true;

                                    observacAvalRadic = DatosTipoAval.get(0).getObservacionMueble();

                                    tipoDireccionAvalRadic = DatosTipoAval.get(0).getTipoDireccion();
                                    direccionAvalRadic = DatosTipoAval.get(0).getDireccion();

                                    codDeptoAvalRadic = DatosTipoAval.get(0).getFk_codDepto();
                                    nombreDeptoAvalRadic = DatosTipoAval.get(0).getNombreDepto();

                                    codCiudadAvalRadic = DatosTipoAval.get(0).getFk_codCiudad();
                                    nombreCiudAvalRadic = DatosTipoAval.get(0).getCiudad();

                                }
                                break;
                        }

                    }
                    Conexion.Conexion.CloseCon();
                }
            } else if (proceso == 2) {
                DatosAvaluos = Ava.ConsulTipAvaRad(cod_avaluo);
                if (DatosAvaluos.next()) {
                    estadoVisibleCombosUbiSolic = true;
                    codDeptoSol = DatosAvaluos.getString("Fk_Cod_Departamento");

                    codCiudadSol = DatosAvaluos.getString("Fk_Cod_Ciudad");

                    cod_AvaluoRadic = DatosAvaluos.getInt("CodAvalAnterior");

                    if (DatosAvaluos.getString("ValorAvaluo") != null) {
                        valorAvalAnterior = myFormatter.format(Double.parseDouble(DatosAvaluos.getString("ValorAvaluo")));
                    } else {
                        valorAvalAnterior = "No registra";
                    }

                    fkCodTipAvaluoRadic = DatosAvaluos.getString("Cod_TipAvaluo");
                    nombreTipAvaluoRadic = DatosAvaluos.getString("Nombre_TipAvaluo");

                    //Pone visibles los paneles de informacion de bien
                    estadoTipoAvalRadic = true;
                    estadoPanelAvalRadic = true;

                    //Predio
                    switch (nombreTipAvaluoRadic) {
                        case "Predio":
                            DatosTipoAval = Ava.getConsultarPredioPreRad(cod_avaluo, 2);
                            ListMatriculasPredio.clear();
                            for (int i = 0; i <= DatosTipoAval.size() - 1; i++) {

                                estadoInfoPred = true;
                                estadoInfoMaqui = false;
                                estadoInfoResulEnser = false;

                                ListMatriculasPredio.add(DatosTipoAval.get(i).getMatricula_predio());
                                matriculaAvalRadic = DatosTipoAval.get(i).getMatricula();
                                codTipoBienRadic = String.valueOf(DatosTipoAval.get(i).getCodTipPredio());
                                nombreTipoBienRadic = DatosTipoAval.get(i).getNombreTipPredio();

                                areaTerrenoRadic = DatosTipoAval.get(i).getAreaTerreno();
                                valorAreaTerrenoRadic = String.valueOf(DatosTipoAval.get(i).getValorMetroTerreno());

                                tipoDireccionAvalRadic = DatosTipoAval.get(i).getTipoDireccion();
                                direccionAvalRadic = DatosTipoAval.get(i).getDireccion();

                                codDeptoAvalRadic = DatosTipoAval.get(i).getFk_codDepto();
                                nombreDeptoAvalRadic = DatosTipoAval.get(i).getNombreDepto();

                                codCiudadAvalRadic = DatosTipoAval.get(i).getFk_codCiudad();
                                nombreCiudAvalRadic = DatosTipoAval.get(i).getCiudad();

                                barrioAvalRadic = DatosTipoAval.get(i).getBarrio();

                            }
//                            matriculaAvalRadic = "";
//                            for (int i = 0; i <= ListMatriculasPredio.size() - 1; i++) {
//
//                                matriculaAvalRadic += " " + ListMatriculasPredio.get(i);
//                            }
                            //Maquinaria
                            break;
                        case "Maquinaria":
                            DatosTipoAval = Ava.getConsultarMaquinariaPreRad(cod_avaluo, 2);
                            if (DatosTipoAval.size() > 0) {

                                estadoInfoPred = false;
                                estadoInfoMaqui = true;
                                estadoInfoResulEnser = false;

                                codTipoBienRadic = String.valueOf(DatosTipoAval.get(0).getCodTipMaquin());
                                nombreTipoBienRadic = DatosTipoAval.get(0).getNombreTipMaquinaria();

                                SerieAvalRadic = DatosTipoAval.get(0).getSerieMaquinaria();
                                marcaMaquinRadic = DatosTipoAval.get(0).getMarcaMaquinaria();
                                modeloMaquinRadic = DatosTipoAval.get(0).getModeloMaquinaria();
                                observacMaquinRadic = DatosTipoAval.get(0).getObservacionMaquinaria();

                                tipoDireccionAvalRadic = DatosTipoAval.get(0).getTipoDireccion();
                                direccionAvalRadic = DatosTipoAval.get(0).getDireccion();

                                codDeptoAvalRadic = DatosTipoAval.get(0).getFk_codDepto();
                                nombreDeptoAvalRadic = DatosTipoAval.get(0).getNombreDepto();

                                codCiudadAvalRadic = DatosTipoAval.get(0).getFk_codCiudad();
                                nombreCiudAvalRadic = DatosTipoAval.get(0).getCiudad();

                            }

                            //enser
                            break;
                        case "Mueble":
                            DatosTipoAval = Ava.getConsultarEnserPreRad(cod_avaluo, 2);
                            if (DatosTipoAval.size() > 0) {

                                estadoInfoPred = false;
                                estadoInfoMaqui = false;
                                estadoInfoResulEnser = true;

                                observacAvalRadic = DatosTipoAval.get(0).getObservacionMueble();

                                tipoDireccionAvalRadic = DatosTipoAval.get(0).getTipoDireccion();
                                direccionAvalRadic = DatosTipoAval.get(0).getDireccion();

                                codDeptoAvalRadic = DatosTipoAval.get(0).getFk_codDepto();
                                nombreDeptoAvalRadic = DatosTipoAval.get(0).getNombreDepto();

                                codCiudadAvalRadic = DatosTipoAval.get(0).getFk_codCiudad();
                                nombreCiudAvalRadic = DatosTipoAval.get(0).getCiudad();
                            }
                            break;
                    }

                }
                Conexion.Conexion.CloseCon();
            }

        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarAvaluoRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que muestra la tabla dependiendo de que tipo de bien sea
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void mostrarTablaInfo() {
        try {
            if (SelectPredios.size() <= 0 && SelectMaquinaria.size() <= 0 && SelectEnseres.size() > 0) {
                estadoTablasResulEnser = true;
                estadoTablasResulMaqui = false;
                estadoTablasResulPred = false;

            } //si la tabla de maquinaria  tiene inforfmacion la va a habilitar
            else if (SelectPredios.size() <= 0 && SelectMaquinaria.size() > 0 && SelectEnseres.size() <= 0) {
                estadoTablasResulEnser = false;
                estadoTablasResulMaqui = true;
                estadoTablasResulPred = false;

            } else if (SelectPredios.size() > 0 && SelectMaquinaria.size() <= 0 && SelectEnseres.size() <= 0) {
                estadoTablasResulEnser = false;
                estadoTablasResulMaqui = false;
                estadoTablasResulPred = true;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarTablaInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que hace visible los paneles seguin ls opcion seleccionada
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void visiblePanelTipoBienes() {
        try {
            switch (estadoRadioSeleccionTipBien) {
                case "GesTipPred":
                    mbTodero.resetTable("FrmGestionTiposBienes:TipPredioTable");
                    ListTipPrediosGest = Ava.getTiposPredio();
                    //EntEnt.setCodEntidad(Adm.ConsulCodigoPrincipal("Cod_Entidad", "Entidad"));
                    break;
                case "GesTipMaquin":
                    mbTodero.resetTable("FrmGestionTiposBienes:TipMaquinTable");
                    ListTipMaquienGest = Ava.getTiposMaquinaria();
                    //Entsucu.setCodSucursal(Adm.ConsulCodigoPrincipal("Cod_Sucursal", "Sucursal"));
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".visiblePanelTipoBienes()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre el dialog para registrar un tipo de predio o maquinaria
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int que define que tipo de bien abrira, tipo de predio o
     * maquinaria
     * @since 01-10-2014
     */
    public void abrirDialogRegistrar(int proceso) {
        try {
            if (proceso == 1) {
                codTipPredioGest = 0;
                nombreTipPredioGest = "";
                TituloDialogTipPredios = "Registrar nuevo tipo de predio";
                RequestContext.getCurrentInstance().execute("PF('DlgTipPredio').show()");
            } else if (proceso == 2) {
                codTipMaquinGest = 0;
                nombreTipMaquinGest = "";
                TituloDialogTipMaquinaria = "Registrar nuevo tipo de maquinaria";
                RequestContext.getCurrentInstance().execute("PF('DlgTipMaquinar').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogRegistrar()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre el dialog para modificar un tipo de predio o maquinaria
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso
     * @since 01-10-2014
     */
    public void abrirInfodialog(int proceso) {
        try {
            if (proceso == 1) {
                if (AdmTipoPredios == null) {
                    mbTodero.setMens("Debe seleccionar un registro");
                    mbTodero.warn();
                } else {
                    codTipPredioGest = AdmTipoPredios.getCodTipPredio();
                    nombreTipPredioGest = AdmTipoPredios.getNombreTipPredio();
                    TituloDialogTipPredios = "Modificar tipo de predio";
                    RequestContext.getCurrentInstance().execute("PF('DlgTipPredio').show()");
                }
            } else if (proceso == 2) {
                if (AdmTipoMaquin == null) {
                    mbTodero.setMens("Debe seleccionar un registro");
                    mbTodero.warn();
                } else {
                    TituloDialogTipMaquinaria = "Modificar tipo de maquinaria";
                    codTipMaquinGest = AdmTipoMaquin.getCodTipMaquin();
                    nombreTipMaquinGest = AdmTipoMaquin.getNombreTipMaquinaria();
                    RequestContext.getCurrentInstance().execute("PF('DlgTipMaquinar').show()");
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirInfodialog()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre el dialog para registrar un tipo de bien desde
     * pre-radicación
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int que contiene el valor para abrir el dialog seguin sea
     * el proceso
     * @since 01-10-2014
     */
    public void abrirDialogsCrearTipBien(int proceso) {
        try {
            if (proceso == 1) {
                Map<String, Object> options = new HashMap<>();
                options.put("modal", true);
                options.put("resizable", false);
                options.put("contentHeight", 500);
                options.put("contentWidth", 800);
                estadoRadioSeleccionTipBien = "GesTipPred";
                RequestContext.getCurrentInstance().openDialog("XHTML/Radicacion/SubForm-GestionTiposBienesRadic", options, null);
            } else if (proceso == 2) {
                Map<String, Object> options = new HashMap<>();
                options.put("modal", true);
                options.put("resizable", false);
                options.put("contentHeight", 500);
                options.put("contentWidth", 800);
                estadoRadioSeleccionTipBien = "GesTipMaquin";
                RequestContext.getCurrentInstance().openDialog("XHTML/Radicacion/SubForm-GestionTiposBienesRadic", options, null);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogsCrearTipBien()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra un tipo de predio
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void regisTipPredios() {
        try {
            mbTodero.resetTable("FrmGestionTiposBienes:TipPredioTable");
            if ("".equals(this.nombreTipPredioGest)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else {
                LogAvaluo Aval = new LogAvaluo();
                Aval.setNombreTipPredio(nombreTipPredioGest);
                Aval.InsertarTipoPredio(mBsesion.codigoMiSesion());
                codTipPredioGest = 0;
                nombreTipPredioGest = "";
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                mbTodero.resetTable("FrmGestionTiposBienes:TipPredioTable");

                ListTipPrediosGest.clear();
                ListTipPrediosGest = Ava.getTiposPredio();
                //   Ubi.setIdPiso(Adm.ConsulCodigoPrincipalParametro("Cod_Parametro", "Ubicacion"));
                RequestContext.getCurrentInstance().execute("PF('DlgTipPredio').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisTipPredios()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica un tipo de predio
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void modifiTipoPredios() { //ok
        try {
            mbTodero.resetTable("FrmGestionTiposBienes:TipPredioTable");
            if ("".equals(this.nombreTipPredioGest)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else {
                LogAvaluo Aval = new LogAvaluo();
                Aval.setCodTipPredio(codTipPredioGest);
                Aval.setNombreTipPredio(nombreTipPredioGest);
                Aval.ActualizaTipoPredio(mBsesion.codigoMiSesion());
                codTipPredioGest = 0;
                nombreTipPredioGest = "";
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("FrmGestionTiposBienes:TipPredioTable");

                ListTipPrediosGest.clear();
                ListTipPrediosGest = Ava.getTiposPredio();
                //Ubi.setIdPiso(Adm.ConsulCodigoPrincipalParametro("Cod_Parametro", "Ubicacion"));
                RequestContext.getCurrentInstance().execute("PF('DlgTipPredio').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiTipoPredios()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que registra un tipo de maquinaria
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void regisTipoMaquin() {

        try {
            mbTodero.resetTable("FrmGestionTiposBienes:TipMaquinTable");
            if ("".equals(this.nombreTipMaquinGest)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else {
                LogAvaluo Aval = new LogAvaluo();
                Aval.setNombreTipMaquinaria(nombreTipMaquinGest);
                Aval.InsertarTipoMaquin(mBsesion.codigoMiSesion());
                codTipMaquinGest = 0;
                nombreTipMaquinGest = "";
                mbTodero.setMens("Registro creado correctamente");
                mbTodero.info();
                mbTodero.resetTable("FrmGestionTiposBienes:TipMaquinTable");

                ListTipMaquienGest.clear();
                ListTipMaquienGest = Ava.getTiposMaquinaria();
                //   Ubi.setIdPiso(Adm.ConsulCodigoPrincipalParametro("Cod_Parametro", "Ubicacion"));
                RequestContext.getCurrentInstance().execute("PF('DlgTipMaquinar').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisTipoMaquin()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que modifica un tipo de predio
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-10-2014
     */
    public void modifiTipoMaquin() { //ok
        try {
            mbTodero.resetTable("FrmGestionTiposBienes:TipMaquinTable");
            if ("".equals(this.nombreTipMaquinGest)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else {
                LogAvaluo Aval = new LogAvaluo();
                Aval.setCodTipMaquin(codTipMaquinGest);
                Aval.setNombreTipMaquinaria(nombreTipMaquinGest);
                Aval.ActualizaTipoMaquin(mBsesion.codigoMiSesion());
                codTipMaquinGest = 0;
                nombreTipMaquinGest = "";
                mbTodero.setMens("Informacion actualizada correctamente");
                mbTodero.info();
                mbTodero.resetTable("FrmGestionTiposBienes:TipMaquinTable");
                ListTipMaquienGest.clear();
                ListTipMaquienGest = Ava.getTiposMaquinaria();
                //Ubi.setIdPiso(Adm.ConsulCodigoPrincipalParametro("Cod_Parametro", "Ubicacion"));
                RequestContext.getCurrentInstance().execute("PF('DlgTipMaquinar').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiTipoMaquin()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
