package LogBean;

import Logic.LogAdministracion;
import Logic.LogCliente;
import Logic.LogPreRadicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import javax.faces.bean.ManagedBean;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de los clientes relacionados con
 * los avalúos</b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @version 2.0.0
 * @since 01-12-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBCliente")
@ViewScoped
public class BeanCliente {

    /**
     * Variables Impicitas*
     */
    ResultSet Tab = null;
    private List<LogCliente> ListClient = null;
    BeanAvaluo MBAval;

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA*
     */
    LogCliente Cli = new LogCliente();
    LogPreRadicacion PreRad = new LogPreRadicacion();
    LogCliente SelectClie;

    /**
     * Variables de cliente solicitante, guardan la informacion de clientes
     * agregados Para radicacion y pre-radicacion
     *
     */
    private String numeroDocclienteInfo;
    private String tipoDocclienteInfo;
    private String nombreClienteInfo;
    private String correoClienteInfo;
    private String telefonoClienteInfo;
    private String direccionClienteInfo;
    private String ubicacionClienteInfo;

    /**
     * cliente 1*
     */
    private String tipoDoccliente1;
    private String numeroDoccliente1;
    private String nombreCliente1;
    private String telefonoCliente1;
    private String correoCliente1;
    private String direccionCliente1;
    private String ubicacionCliente1;

    private boolean fila1 = false;
    private boolean Editar1 = false;
    private boolean Aceptar1 = false;
    private boolean analizar1 = true;
    private boolean quitar1 = true;
    private boolean otro1 = true;

    /**
     * cliente 2*
     */
    private String tipoDoccliente2;
    private String numeroDoccliente2;
    private String nombreCliente2;
    private String telefonoCliente2;
    private String correoCliente2;
    private String direccionCliente2;
    private String ubicacionCliente2;
    private boolean fila2 = false;
    private boolean Editar2 = false;
    private boolean Aceptar2 = false;
    private boolean analizar2 = false;
    private boolean quitar2 = false;
    private boolean otro2 = false;

    /**
     * cliente 3*
     */
    private String tipoDoccliente3;
    private String numeroDoccliente3;
    private String nombreCliente3;
    private String telefonoCliente3;
    private String correoCliente3;
    private String direccionCliente3;
    private String ubicacionCliente3;
    private boolean fila3 = false;
    private boolean Editar3 = false;
    private boolean Aceptar3 = false;
    private boolean analizar3 = false;
    private boolean quitar3 = false;
    private boolean otro3 = false;

    /**
     * cliente 4*
     */
    private String tipoDoccliente4;
    private String numeroDoccliente4;
    private String nombreCliente4;
    private String telefonoCliente4;
    private String correoCliente4;
    private String direccionCliente4;
    private String ubicacionCliente4;
    private boolean fila4 = false;
    private boolean Editar4 = false;
    private boolean Aceptar4 = false;
    private boolean analizar4 = false;
    private boolean quitar4 = false;
    private boolean otro4 = false;

    /**
     * cliente 5*
     */
    private String tipoDoccliente5;
    private String numeroDoccliente5;
    private String nombreCliente5;
    private String telefonoCliente5;
    private String correoCliente5;
    private String direccionCliente5;
    private String ubicacionCliente5;
    private boolean fila5 = false;
    private boolean Editar5 = false;
    private boolean Aceptar5 = false;
    private boolean analizar5 = false;
    private boolean quitar5 = false;

    /**
     * Variables de las Cinco personas a facturar 1*
     */
    private boolean ClientFact1;
    private boolean ClientFact2;
    private boolean ClientFact3;
    private boolean ClientFact4;
    private boolean ClientFact5;

    /**
     * Variables funcionario 1*
     */
    private boolean Funcionario1;
    private boolean Funcionario2;
    private boolean Funcionario3;
    private boolean Funcionario4;
    private boolean Funcionario5;

    /**
     * Variables para registrar cliente*
     */
    private String codTipDoc;
    private ArrayList<SelectItem> TipDocEmp = new ArrayList<>();
    private String numDocumento;
    private String numDocumentoOK;
    private String confir_numDocumento;
    private String nombreCliente;
    private String telefonoCl;
    private String direccionCliente;
    private String direccionClienteOK;
    private String confir_direccionCliente;
    private boolean infoCorreocliente;
    private String emailCliente = "";
    private String confir_emailCliente = "";

    private String codDeptoCli;
    private String codCiudCli;
    private String codRegimenCli;
    private String codTipRegimenCli;

    private boolean estadoTablaClientesTempo = false;
    private boolean estadoClosableClientesRegistro = true;

    private boolean estadoConsultaCliente1 = false;
    private boolean estadoConsultaCliente2 = false;
    private boolean estadoConsultaCliente3 = false;
    private boolean estadoConsultaCliente4 = false;
    private boolean estadoConsultaCliente5 = false;

    /**
     * cliente solicitante*
     */
    private String mensajeConsultaCliente1 = "";
    private String mensajeConsultaCliente2 = "";
    private String mensajeConsultaCliente3 = "";
    private String mensajeConsultaCliente4 = "";
    private String mensajeConsultaCliente5 = "";

    /**
     * cliente facturar*
     */
    private String mensajeConsultaClienteFacturar1;
    private String mensajeConsultaClienteFacturar2;
    private String mensajeConsultaClienteFacturar3;
    private String mensajeConsultaClienteFacturar4;
    private String mensajeConsultaClienteFacturar5;

    /**
     * cliente solicitante*
     */
    private boolean estadocajasAgregarF1 = false;
    private boolean estadocajasAgregarF2 = false;
    private boolean estadocajasAgregarF3 = false;
    private boolean estadocajasAgregarF4 = false;

    /**
     * botones registrar*
     */
    private boolean estadoBtnRegistroF1 = false;
    private boolean estadoBtnRegistroF2 = false;
    private boolean estadoBtnRegistroF3 = false;
    private boolean estadoBtnRegistroF4 = false;
    private boolean estadoBtnRegistroF5 = false;

    /**
     * tarifas pactadas de clientes*
     */
    private String opcionRadioTarifasPactadosCli1;
    private String opcionRadioTarifasPactadosCli2;
    private String opcionRadioTarifasPactadosCli3;
    private String opcionRadioTarifasPactadosCli4;
    private String opcionRadioTarifasPactadosCli5;

    private boolean estadoAgregarTarifasPactCli1 = false;
    private boolean estadoAgregarTarifasPactCli2 = false;
    private boolean estadoAgregarTarifasPactCli3 = false;
    private boolean estadoAgregarTarifasPactCli4 = false;
    private boolean estadoAgregarTarifasPactCli5 = false;

    private boolean estadoRadioAgregarTarifaCli1;
    private boolean estadoRadioAgregarTarifaCli2;
    private boolean estadoRadioAgregarTarifaCli3;
    private boolean estadoRadioAgregarTarifaCli4;
    private boolean estadoRadioAgregarTarifaCli5;
    private boolean estadoTarifasPactCli1 = false;
    private boolean estadoTarifasPactCli2 = false;
    private boolean estadoTarifasPactCli3 = false;
    private boolean estadoTarifasPactCli4 = false;
    private boolean estadoTarifasPactCli5 = false;

    private String tipoTarifaCli;
    private String valorTarifaCli;

    private String tipoTarifaCli1;
    private String valorTarifaCli1;

    private String tipoTarifaCli2;
    private String valorTarifaCli2;

    private String tipoTarifaCli3;
    private String valorTarifaCli3;

    private String tipoTarifaCli4;
    private String valorTarifaCli4;

    private String tipoTarifaCli5;
    private String valorTarifaCli5;

    /**
     * cliente facturar*
     */
    private boolean estadocajasAgregarFacturarF1 = false;
    private boolean estadocajasAgregarFacturarF2 = false;
    private boolean estadocajasAgregarFacturarF3 = false;
    private boolean estadocajasAgregarFacturarF4 = false;

    /**
     * Verificar envio a correos de cliente*
     */
    private boolean enviarClienteCarta;
    private boolean enviarCartaCli1;
    private boolean enviarCartaCli2;
    private boolean enviarCartaCli3;
    private boolean enviarCartaCli4;
    private boolean enviarCartaCli5;

    /**
     * Demas variables para uso de clientes*
     */
    private String opcionRadio;
    private String opcionRadioSolicitante;
    private String opcionMostrarCliSolicitante;

    private boolean estadoDialogClientes = false;
    private int tipo;

    private boolean estadoPanelClienteGeneral = false;
    private boolean estadoPanelRadiosCli = true;

    private boolean estadoSeparadorClienteEnti = true;

    private boolean estadoPanelClienteFacturar;

    private ArrayList<SelectItem> CargaCiuClie = new ArrayList<>();
    private ArrayList<SelectItem> CargaRegimenCli = new ArrayList<>();
    private ArrayList<SelectItem> CargaClienteFac = new ArrayList<>();//Este ArrayList Se va a Cargar una ves el Cliente sea Persona a Factura en la Radicacion para la Subida de Archivos//

    LogAdministracion Adm = new LogAdministracion();
    private int filaRegistro;
    private int num_cliente;
    private String cod_cli_Temp1;
    private String cod_cli_Temp2;
    private String cod_cli_Temp3;
    private String cod_cli_Temp4;
    private String cod_cli_Temp5;
    private String cod_cli_Temp;
    private boolean estadoNumDocCliTemp;
    private boolean estadoRegistroClienteBtn;
    private boolean estadoActualizarClienteBtn;
    ;

    private LogCliente CliTemp = new LogCliente();

    /**
     * variables clientes post-radicacion*
     */
    private ArrayList<LogCliente> CargaListaClientesRadic = new ArrayList<>();

    /**
     * variables clientes temporales*
     */
    private List<LogCliente> CargaListaClientesTempo = new ArrayList<>();
    private int cont = 0;
    private boolean validarClienteTempo = false;
    public int cod_preRadic;
    private ResultSet Dat;
    private int cod_cliente;
    private boolean estadoTarifasCliTemp = false;

    /**
     * variables clientes pre-radicacion*
     */
    private List<LogCliente> CargaListaClientesrPreRadic = new ArrayList<>();

    /**
     * variables para cargar informacion de propietarios*
     */
    private List<LogCliente> ListPropietarios = new ArrayList<>();

    private int cod_avaluo;

    /**
     * variables utilizadas para el conteo de las cajas de texto cuando se
     * desordenan *
     */
    int contadDirecc = 1;
    int contadEmail = 1;
    int contadDocum = 1;

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
    public LogCliente getCli() {
        return Cli;
    }

    public void setCli(LogCliente Cli) {
        this.Cli = Cli;
    }

    public LogCliente getCliTemp() {
        return CliTemp;
    }

    public void setCliTemp(LogCliente CliTemp) {
        this.CliTemp = CliTemp;
    }

    public ArrayList<LogCliente> getCargaListaClientesRadic() {
        return CargaListaClientesRadic;
    }

    public void setCargaListaClientesRadic(ArrayList<LogCliente> CargaListaClientesRadic) {
        this.CargaListaClientesRadic = CargaListaClientesRadic;
    }

    public LogCliente getSelectClie() {
        return SelectClie;
    }

    public void setSelectClie(LogCliente SelectClie) {
        this.SelectClie = SelectClie;
    }

    public List<LogCliente> getListClient() {
        return ListClient;
    }

    public void setListClient(List<LogCliente> ListClient) {
        this.ListClient = ListClient;
    }

    public String getTipoDoccliente1() {
        return tipoDoccliente1;
    }

    public void setTipoDoccliente1(String tipoDoccliente1) {
        this.tipoDoccliente1 = tipoDoccliente1;
    }

    public String getNumeroDoccliente1() {
        return numeroDoccliente1;
    }

    public void setNumeroDoccliente1(String numeroDoccliente1) {
        this.numeroDoccliente1 = numeroDoccliente1;
    }

    public String getNombreCliente1() {
        return nombreCliente1;
    }

    public void setNombreCliente1(String nombreCliente1) {
        this.nombreCliente1 = nombreCliente1;
    }

    public String getTelefonoCliente1() {
        return telefonoCliente1;
    }

    public void setTelefonoCliente1(String telefonoCliente1) {
        this.telefonoCliente1 = telefonoCliente1;
    }

    public String getCorreoCliente1() {
        return correoCliente1;
    }

    public void setCorreoCliente1(String correoCliente1) {
        this.correoCliente1 = correoCliente1;
    }

    public String getDireccionCliente1() {
        return direccionCliente1;
    }

    public void setDireccionCliente1(String direccionCliente1) {
        this.direccionCliente1 = direccionCliente1;
    }

    public String getUbicacionCliente1() {
        return ubicacionCliente1;
    }

    public void setUbicacionCliente1(String ubicacionCliente1) {
        this.ubicacionCliente1 = ubicacionCliente1;
    }

    public boolean isFila1() {
        return fila1;
    }

    public void setFila1(boolean fila1) {
        this.fila1 = fila1;
    }

    public boolean isEditar1() {
        return Editar1;
    }

    public void setEditar1(boolean Editar1) {
        this.Editar1 = Editar1;
    }

    public boolean isAceptar1() {
        return Aceptar1;
    }

    public void setAceptar1(boolean Aceptar1) {
        this.Aceptar1 = Aceptar1;
    }

    public boolean isAnalizar1() {
        return analizar1;
    }

    public void setAnalizar1(boolean analizar1) {
        this.analizar1 = analizar1;
    }

    public boolean isQuitar1() {
        return quitar1;
    }

    public void setQuitar1(boolean quitar1) {
        this.quitar1 = quitar1;
    }

    public boolean isOtro1() {
        return otro1;
    }

    public void setOtro1(boolean otro1) {
        this.otro1 = otro1;
    }

    public String getTipoDoccliente2() {
        return tipoDoccliente2;
    }

    public void setTipoDoccliente2(String tipoDoccliente2) {
        this.tipoDoccliente2 = tipoDoccliente2;
    }

    public String getNumeroDoccliente2() {
        return numeroDoccliente2;
    }

    public void setNumeroDoccliente2(String numeroDoccliente2) {
        this.numeroDoccliente2 = numeroDoccliente2;
    }

    public String getNombreCliente2() {
        return nombreCliente2;
    }

    public void setNombreCliente2(String nombreCliente2) {
        this.nombreCliente2 = nombreCliente2;
    }

    public String getTelefonoCliente2() {
        return telefonoCliente2;
    }

    public void setTelefonoCliente2(String telefonoCliente2) {
        this.telefonoCliente2 = telefonoCliente2;
    }

    public String getCorreoCliente2() {
        return correoCliente2;
    }

    public void setCorreoCliente2(String correoCliente2) {
        this.correoCliente2 = correoCliente2;
    }

    public String getDireccionCliente2() {
        return direccionCliente2;
    }

    public void setDireccionCliente2(String direccionCliente2) {
        this.direccionCliente2 = direccionCliente2;
    }

    public String getUbicacionCliente2() {
        return ubicacionCliente2;
    }

    public void setUbicacionCliente2(String ubicacionCliente2) {
        this.ubicacionCliente2 = ubicacionCliente2;
    }

    public boolean isFila2() {
        return fila2;
    }

    public void setFila2(boolean fila2) {
        this.fila2 = fila2;
    }

    public boolean isEditar2() {
        return Editar2;
    }

    public void setEditar2(boolean Editar2) {
        this.Editar2 = Editar2;
    }

    public boolean isAceptar2() {
        return Aceptar2;
    }

    public void setAceptar2(boolean Aceptar2) {
        this.Aceptar2 = Aceptar2;
    }

    public boolean isAnalizar2() {
        return analizar2;
    }

    public void setAnalizar2(boolean analizar2) {
        this.analizar2 = analizar2;
    }

    public boolean isQuitar2() {
        return quitar2;
    }

    public void setQuitar2(boolean quitar2) {
        this.quitar2 = quitar2;
    }

    public boolean isOtro2() {
        return otro2;
    }

    public void setOtro2(boolean otro2) {
        this.otro2 = otro2;
    }

    public String getTipoDoccliente3() {
        return tipoDoccliente3;
    }

    public void setTipoDoccliente3(String tipoDoccliente3) {
        this.tipoDoccliente3 = tipoDoccliente3;
    }

    public String getNumeroDoccliente3() {
        return numeroDoccliente3;
    }

    public void setNumeroDoccliente3(String numeroDoccliente3) {
        this.numeroDoccliente3 = numeroDoccliente3;
    }

    public String getNombreCliente3() {
        return nombreCliente3;
    }

    public void setNombreCliente3(String nombreCliente3) {
        this.nombreCliente3 = nombreCliente3;
    }

    public String getTelefonoCliente3() {
        return telefonoCliente3;
    }

    public void setTelefonoCliente3(String telefonoCliente3) {
        this.telefonoCliente3 = telefonoCliente3;
    }

    public String getCorreoCliente3() {
        return correoCliente3;
    }

    public void setCorreoCliente3(String correoCliente3) {
        this.correoCliente3 = correoCliente3;
    }

    public String getDireccionCliente3() {
        return direccionCliente3;
    }

    public void setDireccionCliente3(String direccionCliente3) {
        this.direccionCliente3 = direccionCliente3;
    }

    public String getUbicacionCliente3() {
        return ubicacionCliente3;
    }

    public void setUbicacionCliente3(String ubicacionCliente3) {
        this.ubicacionCliente3 = ubicacionCliente3;
    }

    public boolean isFila3() {
        return fila3;
    }

    public void setFila3(boolean fila3) {
        this.fila3 = fila3;
    }

    public boolean isEditar3() {
        return Editar3;
    }

    public void setEditar3(boolean Editar3) {
        this.Editar3 = Editar3;
    }

    public boolean isAceptar3() {
        return Aceptar3;
    }

    public void setAceptar3(boolean Aceptar3) {
        this.Aceptar3 = Aceptar3;
    }

    public boolean isAnalizar3() {
        return analizar3;
    }

    public void setAnalizar3(boolean analizar3) {
        this.analizar3 = analizar3;
    }

    public boolean isQuitar3() {
        return quitar3;
    }

    public void setQuitar3(boolean quitar3) {
        this.quitar3 = quitar3;
    }

    public boolean isOtro3() {
        return otro3;
    }

    public void setOtro3(boolean otro3) {
        this.otro3 = otro3;
    }

    public String getTipoDoccliente4() {
        return tipoDoccliente4;
    }

    public void setTipoDoccliente4(String tipoDoccliente4) {
        this.tipoDoccliente4 = tipoDoccliente4;
    }

    public String getNumeroDoccliente4() {
        return numeroDoccliente4;
    }

    public void setNumeroDoccliente4(String numeroDoccliente4) {
        this.numeroDoccliente4 = numeroDoccliente4;
    }

    public String getNombreCliente4() {
        return nombreCliente4;
    }

    public void setNombreCliente4(String nombreCliente4) {
        this.nombreCliente4 = nombreCliente4;
    }

    public String getTelefonoCliente4() {
        return telefonoCliente4;
    }

    public void setTelefonoCliente4(String telefonoCliente4) {
        this.telefonoCliente4 = telefonoCliente4;
    }

    public String getCorreoCliente4() {
        return correoCliente4;
    }

    public void setCorreoCliente4(String correoCliente4) {
        this.correoCliente4 = correoCliente4;
    }

    public String getDireccionCliente4() {
        return direccionCliente4;
    }

    public void setDireccionCliente4(String direccionCliente4) {
        this.direccionCliente4 = direccionCliente4;
    }

    public String getUbicacionCliente4() {
        return ubicacionCliente4;
    }

    public void setUbicacionCliente4(String ubicacionCliente4) {
        this.ubicacionCliente4 = ubicacionCliente4;
    }

    public boolean isFila4() {
        return fila4;
    }

    public void setFila4(boolean fila4) {
        this.fila4 = fila4;
    }

    public boolean isEditar4() {
        return Editar4;
    }

    public void setEditar4(boolean Editar4) {
        this.Editar4 = Editar4;
    }

    public boolean isAceptar4() {
        return Aceptar4;
    }

    public void setAceptar4(boolean Aceptar4) {
        this.Aceptar4 = Aceptar4;
    }

    public boolean isAnalizar4() {
        return analizar4;
    }

    public void setAnalizar4(boolean analizar4) {
        this.analizar4 = analizar4;
    }

    public boolean isQuitar4() {
        return quitar4;
    }

    public void setQuitar4(boolean quitar4) {
        this.quitar4 = quitar4;
    }

    public boolean isOtro4() {
        return otro4;
    }

    public void setOtro4(boolean otro4) {
        this.otro4 = otro4;
    }

    public String getTipoDoccliente5() {
        return tipoDoccliente5;
    }

    public void setTipoDoccliente5(String tipoDoccliente5) {
        this.tipoDoccliente5 = tipoDoccliente5;
    }

    public String getNumeroDoccliente5() {
        return numeroDoccliente5;
    }

    public void setNumeroDoccliente5(String numeroDoccliente5) {
        this.numeroDoccliente5 = numeroDoccliente5;
    }

    public String getNombreCliente5() {
        return nombreCliente5;
    }

    public void setNombreCliente5(String nombreCliente5) {
        this.nombreCliente5 = nombreCliente5;
    }

    public String getTelefonoCliente5() {
        return telefonoCliente5;
    }

    public void setTelefonoCliente5(String telefonoCliente5) {
        this.telefonoCliente5 = telefonoCliente5;
    }

    public String getCorreoCliente5() {
        return correoCliente5;
    }

    public void setCorreoCliente5(String correoCliente5) {
        this.correoCliente5 = correoCliente5;
    }

    public String getDireccionCliente5() {
        return direccionCliente5;
    }

    public void setDireccionCliente5(String direccionCliente5) {
        this.direccionCliente5 = direccionCliente5;
    }

    public String getUbicacionCliente5() {
        return ubicacionCliente5;
    }

    public void setUbicacionCliente5(String ubicacionCliente5) {
        this.ubicacionCliente5 = ubicacionCliente5;
    }

    public boolean isFila5() {
        return fila5;
    }

    public void setFila5(boolean fila5) {
        this.fila5 = fila5;
    }

    public boolean isEditar5() {
        return Editar5;
    }

    public void setEditar5(boolean Editar5) {
        this.Editar5 = Editar5;
    }

    public boolean isAceptar5() {
        return Aceptar5;
    }

    public void setAceptar5(boolean Aceptar5) {
        this.Aceptar5 = Aceptar5;
    }

    public boolean isAnalizar5() {
        return analizar5;
    }

    public void setAnalizar5(boolean analizar5) {
        this.analizar5 = analizar5;
    }

    public boolean isQuitar5() {
        return quitar5;
    }

    public void setQuitar5(boolean quitar5) {
        this.quitar5 = quitar5;
    }

    public String getNumeroDocclienteInfo() {
        return numeroDocclienteInfo;
    }

    public void setNumeroDocclienteInfo(String numeroDocclienteInfo) {
        this.numeroDocclienteInfo = numeroDocclienteInfo;
    }

    public String getTipoDocclienteInfo() {
        return tipoDocclienteInfo;
    }

    public void setTipoDocclienteInfo(String tipoDocclienteInfo) {
        this.tipoDocclienteInfo = tipoDocclienteInfo;
    }

    public String getNombreClienteInfo() {
        return nombreClienteInfo;
    }

    public void setNombreClienteInfo(String nombreClienteInfo) {
        this.nombreClienteInfo = nombreClienteInfo;
    }

    public String getCorreoClienteInfo() {
        return correoClienteInfo;
    }

    public void setCorreoClienteInfo(String correoClienteInfo) {
        this.correoClienteInfo = correoClienteInfo;
    }

    public String getTelefonoClienteInfo() {
        return telefonoClienteInfo;
    }

    public void setTelefonoClienteInfo(String telefonoClienteInfo) {
        this.telefonoClienteInfo = telefonoClienteInfo;
    }

    public String getDireccionClienteInfo() {
        return direccionClienteInfo;
    }

    public void setDireccionClienteInfo(String direccionClienteInfo) {
        this.direccionClienteInfo = direccionClienteInfo;
    }

    public String getUbicacionClienteInfo() {
        return ubicacionClienteInfo;
    }

    public void setUbicacionClienteInfo(String ubicacionClienteInfo) {
        this.ubicacionClienteInfo = ubicacionClienteInfo;
    }

    public boolean isEstadoConsultaCliente1() {
        return estadoConsultaCliente1;
    }

    public void setEstadoConsultaCliente1(boolean estadoConsultaCliente1) {
        this.estadoConsultaCliente1 = estadoConsultaCliente1;
    }

    public boolean isEstadoConsultaCliente2() {
        return estadoConsultaCliente2;
    }

    public void setEstadoConsultaCliente2(boolean estadoConsultaCliente2) {
        this.estadoConsultaCliente2 = estadoConsultaCliente2;
    }

    public boolean isEstadoConsultaCliente3() {
        return estadoConsultaCliente3;
    }

    public void setEstadoConsultaCliente3(boolean estadoConsultaCliente3) {
        this.estadoConsultaCliente3 = estadoConsultaCliente3;
    }

    public boolean isEstadoConsultaCliente4() {
        return estadoConsultaCliente4;
    }

    public void setEstadoConsultaCliente4(boolean estadoConsultaCliente4) {
        this.estadoConsultaCliente4 = estadoConsultaCliente4;
    }

    public boolean isEstadoConsultaCliente5() {
        return estadoConsultaCliente5;
    }

    public void setEstadoConsultaCliente5(boolean estadoConsultaCliente5) {
        this.estadoConsultaCliente5 = estadoConsultaCliente5;
    }

    public String getMensajeConsultaCliente1() {
        return mensajeConsultaCliente1;
    }

    public void setMensajeConsultaCliente1(String mensajeConsultaCliente1) {
        this.mensajeConsultaCliente1 = mensajeConsultaCliente1;
    }

    public String getMensajeConsultaCliente2() {
        return mensajeConsultaCliente2;
    }

    public void setMensajeConsultaCliente2(String mensajeConsultaCliente2) {
        this.mensajeConsultaCliente2 = mensajeConsultaCliente2;
    }

    public String getMensajeConsultaCliente3() {
        return mensajeConsultaCliente3;
    }

    public void setMensajeConsultaCliente3(String mensajeConsultaCliente3) {
        this.mensajeConsultaCliente3 = mensajeConsultaCliente3;
    }

    public String getMensajeConsultaCliente4() {
        return mensajeConsultaCliente4;
    }

    public void setMensajeConsultaCliente4(String mensajeConsultaCliente4) {
        this.mensajeConsultaCliente4 = mensajeConsultaCliente4;
    }

    public String getMensajeConsultaCliente5() {
        return mensajeConsultaCliente5;
    }

    public void setMensajeConsultaCliente5(String mensajeConsultaCliente5) {
        this.mensajeConsultaCliente5 = mensajeConsultaCliente5;
    }

    public boolean isEstadocajasAgregarF1() {
        return estadocajasAgregarF1;
    }

    public void setEstadocajasAgregarF1(boolean estadocajasAgregarF1) {
        this.estadocajasAgregarF1 = estadocajasAgregarF1;
    }

    public boolean isEstadocajasAgregarF2() {
        return estadocajasAgregarF2;
    }

    public void setEstadocajasAgregarF2(boolean estadocajasAgregarF2) {
        this.estadocajasAgregarF2 = estadocajasAgregarF2;
    }

    public boolean isEstadocajasAgregarF3() {
        return estadocajasAgregarF3;
    }

    public void setEstadocajasAgregarF3(boolean estadocajasAgregarF3) {
        this.estadocajasAgregarF3 = estadocajasAgregarF3;
    }

    public boolean isEstadocajasAgregarF4() {
        return estadocajasAgregarF4;
    }

    public void setEstadocajasAgregarF4(boolean estadocajasAgregarF4) {
        this.estadocajasAgregarF4 = estadocajasAgregarF4;
    }

    public boolean isEstadoBtnRegistroF1() {
        return estadoBtnRegistroF1;
    }

    public void setEstadoBtnRegistroF1(boolean estadoBtnRegistroF1) {
        this.estadoBtnRegistroF1 = estadoBtnRegistroF1;
    }

    public boolean isEstadoBtnRegistroF2() {
        return estadoBtnRegistroF2;
    }

    public void setEstadoBtnRegistroF2(boolean estadoBtnRegistroF2) {
        this.estadoBtnRegistroF2 = estadoBtnRegistroF2;
    }

    public boolean isEstadoBtnRegistroF3() {
        return estadoBtnRegistroF3;
    }

    public void setEstadoBtnRegistroF3(boolean estadoBtnRegistroF3) {
        this.estadoBtnRegistroF3 = estadoBtnRegistroF3;
    }

    public boolean isEstadoBtnRegistroF4() {
        return estadoBtnRegistroF4;
    }

    public void setEstadoBtnRegistroF4(boolean estadoBtnRegistroF4) {
        this.estadoBtnRegistroF4 = estadoBtnRegistroF4;
    }

    public boolean isEstadoBtnRegistroF5() {
        return estadoBtnRegistroF5;
    }

    public void setEstadoBtnRegistroF5(boolean estadoBtnRegistroF5) {
        this.estadoBtnRegistroF5 = estadoBtnRegistroF5;
    }

    public String getMensajeConsultaClienteFacturar1() {
        return mensajeConsultaClienteFacturar1;
    }

    public void setMensajeConsultaClienteFacturar1(String mensajeConsultaClienteFacturar1) {
        this.mensajeConsultaClienteFacturar1 = mensajeConsultaClienteFacturar1;
    }

    public String getMensajeConsultaClienteFacturar2() {
        return mensajeConsultaClienteFacturar2;
    }

    public void setMensajeConsultaClienteFacturar2(String mensajeConsultaClienteFacturar2) {
        this.mensajeConsultaClienteFacturar2 = mensajeConsultaClienteFacturar2;
    }

    public String getMensajeConsultaClienteFacturar3() {
        return mensajeConsultaClienteFacturar3;
    }

    public void setMensajeConsultaClienteFacturar3(String mensajeConsultaClienteFacturar3) {
        this.mensajeConsultaClienteFacturar3 = mensajeConsultaClienteFacturar3;
    }

    public String getMensajeConsultaClienteFacturar4() {
        return mensajeConsultaClienteFacturar4;
    }

    public void setMensajeConsultaClienteFacturar4(String mensajeConsultaClienteFacturar4) {
        this.mensajeConsultaClienteFacturar4 = mensajeConsultaClienteFacturar4;
    }

    public String getMensajeConsultaClienteFacturar5() {
        return mensajeConsultaClienteFacturar5;
    }

    public void setMensajeConsultaClienteFacturar5(String mensajeConsultaClienteFacturar5) {
        this.mensajeConsultaClienteFacturar5 = mensajeConsultaClienteFacturar5;
    }

    public boolean isEstadocajasAgregarFacturarF1() {
        return estadocajasAgregarFacturarF1;
    }

    public void setEstadocajasAgregarFacturarF1(boolean estadocajasAgregarFacturarF1) {
        this.estadocajasAgregarFacturarF1 = estadocajasAgregarFacturarF1;
    }

    public boolean isEstadocajasAgregarFacturarF2() {
        return estadocajasAgregarFacturarF2;
    }

    public void setEstadocajasAgregarFacturarF2(boolean estadocajasAgregarFacturarF2) {
        this.estadocajasAgregarFacturarF2 = estadocajasAgregarFacturarF2;
    }

    public boolean isEstadocajasAgregarFacturarF3() {
        return estadocajasAgregarFacturarF3;
    }

    public void setEstadocajasAgregarFacturarF3(boolean estadocajasAgregarFacturarF3) {
        this.estadocajasAgregarFacturarF3 = estadocajasAgregarFacturarF3;
    }

    public boolean isEstadocajasAgregarFacturarF4() {
        return estadocajasAgregarFacturarF4;
    }

    public void setEstadocajasAgregarFacturarF4(boolean estadocajasAgregarFacturarF4) {
        this.estadocajasAgregarFacturarF4 = estadocajasAgregarFacturarF4;
    }

    public boolean isEnviarCartaCli1() {
        return enviarCartaCli1;
    }

    public void setEnviarCartaCli1(boolean enviarCartaCli1) {
        this.enviarCartaCli1 = enviarCartaCli1;
    }

    public boolean isEnviarCartaCli2() {
        return enviarCartaCli2;
    }

    public void setEnviarCartaCli2(boolean enviarCartaCli2) {
        this.enviarCartaCli2 = enviarCartaCli2;
    }

    public boolean isEnviarCartaCli3() {
        return enviarCartaCli3;
    }

    public void setEnviarCartaCli3(boolean enviarCartaCli3) {
        this.enviarCartaCli3 = enviarCartaCli3;
    }

    public boolean isEnviarCartaCli4() {
        return enviarCartaCli4;
    }

    public void setEnviarCartaCli4(boolean enviarCartaCli4) {
        this.enviarCartaCli4 = enviarCartaCli4;
    }

    public boolean isEnviarCartaCli5() {
        return enviarCartaCli5;
    }

    public void setEnviarCartaCli5(boolean enviarCartaCli5) {
        this.enviarCartaCli5 = enviarCartaCli5;
    }

    public boolean isEstadoPanelClienteFacturar() {
        return estadoPanelClienteFacturar;
    }

    public void setEstadoPanelClienteFacturar(boolean estadoPanelClienteFacturar) {
        this.estadoPanelClienteFacturar = estadoPanelClienteFacturar;
    }

    public String getOpcionRadio() {
        return opcionRadio;
    }

    public void setOpcionRadio(String opcionRadio) {
        this.opcionRadio = opcionRadio;
    }

    public String getOpcionRadioSolicitante() {
        return opcionRadioSolicitante;
    }

    public void setOpcionRadioSolicitante(String opcionRadioSolicitante) {
        this.opcionRadioSolicitante = opcionRadioSolicitante;
    }

    public boolean isEstadoDialogClientes() {
        return estadoDialogClientes;
    }

    public void setEstadoDialogClientes(boolean estadoDialogClientes) {
        this.estadoDialogClientes = estadoDialogClientes;
    }

    public boolean isEstadoPanelClienteGeneral() {
        return estadoPanelClienteGeneral;
    }

    public void setEstadoPanelClienteGeneral(boolean estadoPanelClienteGeneral) {
        this.estadoPanelClienteGeneral = estadoPanelClienteGeneral;
    }

    public boolean isEstadoPanelRadiosCli() {
        return estadoPanelRadiosCli;
    }

    public void setEstadoPanelRadiosCli(boolean estadoPanelRadiosCli) {
        this.estadoPanelRadiosCli = estadoPanelRadiosCli;
    }

    public boolean isEstadoSeparadorClienteEnti() {
        return estadoSeparadorClienteEnti;
    }

    public void setEstadoSeparadorClienteEnti(boolean estadoSeparadorClienteEnti) {
        this.estadoSeparadorClienteEnti = estadoSeparadorClienteEnti;
    }

    public String getOpcionRadioTarifasPactadosCli1() {
        return opcionRadioTarifasPactadosCli1;
    }

    public void setOpcionRadioTarifasPactadosCli1(String opcionRadioTarifasPactadosCli1) {
        this.opcionRadioTarifasPactadosCli1 = opcionRadioTarifasPactadosCli1;
    }

    public String getOpcionRadioTarifasPactadosCli2() {
        return opcionRadioTarifasPactadosCli2;
    }

    public void setOpcionRadioTarifasPactadosCli2(String opcionRadioTarifasPactadosCli2) {
        this.opcionRadioTarifasPactadosCli2 = opcionRadioTarifasPactadosCli2;
    }

    public String getOpcionRadioTarifasPactadosCli3() {
        return opcionRadioTarifasPactadosCli3;
    }

    public void setOpcionRadioTarifasPactadosCli3(String opcionRadioTarifasPactadosCli3) {
        this.opcionRadioTarifasPactadosCli3 = opcionRadioTarifasPactadosCli3;
    }

    public String getOpcionRadioTarifasPactadosCli4() {
        return opcionRadioTarifasPactadosCli4;
    }

    public void setOpcionRadioTarifasPactadosCli4(String opcionRadioTarifasPactadosCli4) {
        this.opcionRadioTarifasPactadosCli4 = opcionRadioTarifasPactadosCli4;
    }

    public String getOpcionRadioTarifasPactadosCli5() {
        return opcionRadioTarifasPactadosCli5;
    }

    public void setOpcionRadioTarifasPactadosCli5(String opcionRadioTarifasPactadosCli5) {
        this.opcionRadioTarifasPactadosCli5 = opcionRadioTarifasPactadosCli5;
    }

    public boolean isEstadoTarifasPactCli1() {
        return estadoTarifasPactCli1;
    }

    public void setEstadoTarifasPactCli1(boolean estadoTarifasPactCli1) {
        this.estadoTarifasPactCli1 = estadoTarifasPactCli1;
    }

    public boolean isEstadoTarifasPactCli2() {
        return estadoTarifasPactCli2;
    }

    public void setEstadoTarifasPactCli2(boolean estadoTarifasPactCli2) {
        this.estadoTarifasPactCli2 = estadoTarifasPactCli2;
    }

    public boolean isEstadoTarifasPactCli3() {
        return estadoTarifasPactCli3;
    }

    public void setEstadoTarifasPactCli3(boolean estadoTarifasPactCli3) {
        this.estadoTarifasPactCli3 = estadoTarifasPactCli3;
    }

    public boolean isEstadoTarifasPactCli4() {
        return estadoTarifasPactCli4;
    }

    public void setEstadoTarifasPactCli4(boolean estadoTarifasPactCli4) {
        this.estadoTarifasPactCli4 = estadoTarifasPactCli4;
    }

    public boolean isEstadoTarifasPactCli5() {
        return estadoTarifasPactCli5;
    }

    public void setEstadoTarifasPactCli5(boolean estadoTarifasPactCli5) {
        this.estadoTarifasPactCli5 = estadoTarifasPactCli5;
    }

    public boolean isEstadoAgregarTarifasPactCli1() {
        return estadoAgregarTarifasPactCli1;
    }

    public void setEstadoAgregarTarifasPactCli1(boolean estadoAgregarTarifasPactCli1) {
        this.estadoAgregarTarifasPactCli1 = estadoAgregarTarifasPactCli1;
    }

    public boolean isEstadoAgregarTarifasPactCli2() {
        return estadoAgregarTarifasPactCli2;
    }

    public void setEstadoAgregarTarifasPactCli2(boolean estadoAgregarTarifasPactCli2) {
        this.estadoAgregarTarifasPactCli2 = estadoAgregarTarifasPactCli2;
    }

    public boolean isEstadoAgregarTarifasPactCli3() {
        return estadoAgregarTarifasPactCli3;
    }

    public void setEstadoAgregarTarifasPactCli3(boolean estadoAgregarTarifasPactCli3) {
        this.estadoAgregarTarifasPactCli3 = estadoAgregarTarifasPactCli3;
    }

    public boolean isEstadoAgregarTarifasPactCli4() {
        return estadoAgregarTarifasPactCli4;
    }

    public void setEstadoAgregarTarifasPactCli4(boolean estadoAgregarTarifasPactCli4) {
        this.estadoAgregarTarifasPactCli4 = estadoAgregarTarifasPactCli4;
    }

    public boolean isEstadoAgregarTarifasPactCli5() {
        return estadoAgregarTarifasPactCli5;
    }

    public void setEstadoAgregarTarifasPactCli5(boolean estadoAgregarTarifasPactCli5) {
        this.estadoAgregarTarifasPactCli5 = estadoAgregarTarifasPactCli5;
    }

    public boolean isEstadoRadioAgregarTarifaCli1() {
        return estadoRadioAgregarTarifaCli1;
    }

    public void setEstadoRadioAgregarTarifaCli1(boolean estadoRadioAgregarTarifaCli1) {
        this.estadoRadioAgregarTarifaCli1 = estadoRadioAgregarTarifaCli1;
    }

    public boolean isEstadoRadioAgregarTarifaCli2() {
        return estadoRadioAgregarTarifaCli2;
    }

    public void setEstadoRadioAgregarTarifaCli2(boolean estadoRadioAgregarTarifaCli2) {
        this.estadoRadioAgregarTarifaCli2 = estadoRadioAgregarTarifaCli2;
    }

    public boolean isEstadoRadioAgregarTarifaCli3() {
        return estadoRadioAgregarTarifaCli3;
    }

    public void setEstadoRadioAgregarTarifaCli3(boolean estadoRadioAgregarTarifaCli3) {
        this.estadoRadioAgregarTarifaCli3 = estadoRadioAgregarTarifaCli3;
    }

    public boolean isEstadoRadioAgregarTarifaCli4() {
        return estadoRadioAgregarTarifaCli4;
    }

    public void setEstadoRadioAgregarTarifaCli4(boolean estadoRadioAgregarTarifaCli4) {
        this.estadoRadioAgregarTarifaCli4 = estadoRadioAgregarTarifaCli4;
    }

    public boolean isEstadoRadioAgregarTarifaCli5() {
        return estadoRadioAgregarTarifaCli5;
    }

    public void setEstadoRadioAgregarTarifaCli5(boolean estadoRadioAgregarTarifaCli5) {
        this.estadoRadioAgregarTarifaCli5 = estadoRadioAgregarTarifaCli5;
    }

    public String getTipoTarifaCli() {
        return tipoTarifaCli;
    }

    public void setTipoTarifaCli(String tipoTarifaCli) {
        this.tipoTarifaCli = tipoTarifaCli;
    }

    public String getValorTarifaCli() {
        return valorTarifaCli;
    }

    public void setValorTarifaCli(String valorTarifaCli) {
        this.valorTarifaCli = valorTarifaCli;
    }

    public String getTipoTarifaCli1() {
        return tipoTarifaCli1;
    }

    public void setTipoTarifaCli1(String tipoTarifaCli1) {
        this.tipoTarifaCli1 = tipoTarifaCli1;
    }

    public String getValorTarifaCli1() {
        return valorTarifaCli1;
    }

    public void setValorTarifaCli1(String valorTarifaCli1) {
        this.valorTarifaCli1 = valorTarifaCli1;
    }

    public String getTipoTarifaCli2() {
        return tipoTarifaCli2;
    }

    public void setTipoTarifaCli2(String tipoTarifaCli2) {
        this.tipoTarifaCli2 = tipoTarifaCli2;
    }

    public String getValorTarifaCli2() {
        return valorTarifaCli2;
    }

    public void setValorTarifaCli2(String valorTarifaCli2) {
        this.valorTarifaCli2 = valorTarifaCli2;
    }

    public String getTipoTarifaCli3() {
        return tipoTarifaCli3;
    }

    public void setTipoTarifaCli3(String tipoTarifaCli3) {
        this.tipoTarifaCli3 = tipoTarifaCli3;
    }

    public String getValorTarifaCli3() {
        return valorTarifaCli3;
    }

    public void setValorTarifaCli3(String valorTarifaCli3) {
        this.valorTarifaCli3 = valorTarifaCli3;
    }

    public String getTipoTarifaCli4() {
        return tipoTarifaCli4;
    }

    public void setTipoTarifaCli4(String tipoTarifaCli4) {
        this.tipoTarifaCli4 = tipoTarifaCli4;
    }

    public String getValorTarifaCli4() {
        return valorTarifaCli4;
    }

    public void setValorTarifaCli4(String valorTarifaCli4) {
        this.valorTarifaCli4 = valorTarifaCli4;
    }

    public String getTipoTarifaCli5() {
        return tipoTarifaCli5;
    }

    public void setTipoTarifaCli5(String tipoTarifaCli5) {
        this.tipoTarifaCli5 = tipoTarifaCli5;
    }

    public String getValorTarifaCli5() {
        return valorTarifaCli5;
    }

    public void setValorTarifaCli5(String valorTarifaCli5) {
        this.valorTarifaCli5 = valorTarifaCli5;
    }

    public boolean isClientFact1() {
        return ClientFact1;
    }

    public void setClientFact1(boolean ClientFact1) {
        this.ClientFact1 = ClientFact1;
    }

    public boolean isClientFact2() {
        return ClientFact2;
    }

    public void setClientFact2(boolean ClientFact2) {
        this.ClientFact2 = ClientFact2;
    }

    public boolean isClientFact3() {
        return ClientFact3;
    }

    public void setClientFact3(boolean ClientFact3) {
        this.ClientFact3 = ClientFact3;
    }

    public boolean isClientFact4() {
        return ClientFact4;
    }

    public void setClientFact4(boolean ClientFact4) {
        this.ClientFact4 = ClientFact4;
    }

    public boolean isClientFact5() {
        return ClientFact5;
    }

    public void setClientFact5(boolean ClientFact5) {
        this.ClientFact5 = ClientFact5;
    }

    public boolean isFuncionario1() {
        return Funcionario1;
    }

    public void setFuncionario1(boolean Funcionario1) {
        this.Funcionario1 = Funcionario1;
    }

    public boolean isFuncionario2() {
        return Funcionario2;
    }

    public void setFuncionario2(boolean Funcionario2) {
        this.Funcionario2 = Funcionario2;
    }

    public boolean isFuncionario3() {
        return Funcionario3;
    }

    public void setFuncionario3(boolean Funcionario3) {
        this.Funcionario3 = Funcionario3;
    }

    public boolean isFuncionario4() {
        return Funcionario4;
    }

    public void setFuncionario4(boolean Funcionario4) {
        this.Funcionario4 = Funcionario4;
    }

    public boolean isFuncionario5() {
        return Funcionario5;
    }

    public void setFuncionario5(boolean Funcionario5) {
        this.Funcionario5 = Funcionario5;
    }

    //Get y set para registrar cliente
    public boolean isEstadoNumDocCliTemp() {
        return estadoNumDocCliTemp;
    }

    public void setEstadoNumDocCliTemp(boolean estadoNumDocCliTemp) {
        this.estadoNumDocCliTemp = estadoNumDocCliTemp;
    }

    public boolean isEstadoRegistroClienteBtn() {
        return estadoRegistroClienteBtn;
    }

    public void setEstadoRegistroClienteBtn(boolean estadoRegistroClienteBtn) {
        this.estadoRegistroClienteBtn = estadoRegistroClienteBtn;
    }

    public boolean isEstadoActualizarClienteBtn() {
        return estadoActualizarClienteBtn;
    }

    public void setEstadoActualizarClienteBtn(boolean estadoActualizarClienteBtn) {
        this.estadoActualizarClienteBtn = estadoActualizarClienteBtn;
    }

    public String getCodTipDoc() {
        return codTipDoc;
    }

    public void setCodTipDoc(String codTipDoc) {
        this.codTipDoc = codTipDoc;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNumDocumentoOK() {
        return numDocumentoOK;
    }

    public void setNumDocumentoOK(String numDocumentoOK) {
        this.numDocumentoOK = numDocumentoOK;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCl() {
        return telefonoCl;
    }

    public void setTelefonoCl(String telefonoCl) {
        this.telefonoCl = telefonoCl;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public String getDireccionClienteOK() {
        return direccionClienteOK;
    }

    public void setDireccionClienteOK(String direccionClienteOK) {
        this.direccionClienteOK = direccionClienteOK;
    }

    public String getConfir_numDocumento() {
        return confir_numDocumento;
    }

    public void setConfir_numDocumento(String confir_numDocumento) {
        this.confir_numDocumento = confir_numDocumento;
    }

    public String getConfir_direccionCliente() {
        return confir_direccionCliente;
    }

    public void setConfir_direccionCliente(String confir_direccionCliente) {
        this.confir_direccionCliente = confir_direccionCliente;
    }

    public boolean isInfoCorreocliente() {
        return infoCorreocliente;
    }

    public void setInfoCorreocliente(boolean infoCorreocliente) {
        this.infoCorreocliente = infoCorreocliente;
    }

    public String getConfir_emailCliente() {
        return confir_emailCliente;
    }

    public void setConfir_emailCliente(String confir_emailCliente) {
        this.confir_emailCliente = confir_emailCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCodDeptoCli() {
        return codDeptoCli;
    }

    public void setCodDeptoCli(String codDeptoCli) {
        this.codDeptoCli = codDeptoCli;
    }

    public String getCodCiudCli() {
        return codCiudCli;
    }

    public void setCodCiudCli(String codCiudCli) {
        this.codCiudCli = codCiudCli;
    }

    public String getCodRegimenCli() {
        return codRegimenCli;
    }

    public void setCodRegimenCli(String codRegimenCli) {
        this.codRegimenCli = codRegimenCli;
    }

    public String getCodTipRegimenCli() {
        return codTipRegimenCli;
    }

    public void setCodTipRegimenCli(String codTipRegimenCli) {
        this.codTipRegimenCli = codTipRegimenCli;
    }

    public ArrayList<SelectItem> getCargaCiuClie() {
        return CargaCiuClie;
    }

    public void setCargaCiuClie(ArrayList<SelectItem> CargaCiuClie) {
        this.CargaCiuClie = CargaCiuClie;
    }

    public ArrayList<SelectItem> getCargaRegimenCli() {
        return CargaRegimenCli;
    }

    public void setCargaRegimenCli(ArrayList<SelectItem> CargaRegimenCli) {
        this.CargaRegimenCli = CargaRegimenCli;
    }

    public ArrayList<SelectItem> getCargaClienteFac() {
        return CargaClienteFac;
    }

    public void setCargaClienteFac(ArrayList<SelectItem> CargaClienteFac) {
        this.CargaClienteFac = CargaClienteFac;
    }

    public ArrayList<SelectItem> getTipDocEmp() {
        return TipDocEmp;
    }

    public void setTipDocEmp(ArrayList<SelectItem> TipDocEmp) {
        this.TipDocEmp = TipDocEmp;
    }

    public boolean isEstadoTablaClientesTempo() {
        return estadoTablaClientesTempo;
    }

    public void setEstadoTablaClientesTempo(boolean estadoTablaClientesTempo) {
        this.estadoTablaClientesTempo = estadoTablaClientesTempo;
    }

    public boolean isEstadoClosableClientesRegistro() {
        return estadoClosableClientesRegistro;
    }

    public void setEstadoClosableClientesRegistro(boolean estadoClosableClientesRegistro) {
        this.estadoClosableClientesRegistro = estadoClosableClientesRegistro;
    }

    public BeanCliente() {
//        this.TipDocEmp = this.getConsulTipDocEmp();
    }

    public void onCharge() {
        ListClient.clear();
    }

    public String getOpcionMostrarCliSolicitante() {
        return opcionMostrarCliSolicitante;
    }

    public void setOpcionMostrarCliSolicitante(String opcionMostrarCliSolicitante) {
        this.opcionMostrarCliSolicitante = opcionMostrarCliSolicitante;
    }

    public List<LogCliente> getCargaListaClientesTempo() {
        return CargaListaClientesTempo;
    }

    public void setCargaListaClientesTempo(List<LogCliente> CargaListaClientesTempo) {
        this.CargaListaClientesTempo = CargaListaClientesTempo;
    }

    public boolean isValidarClienteTempo() {
        return validarClienteTempo;
    }

    public void setValidarClienteTempo(boolean validarClienteTempo) {
        this.validarClienteTempo = validarClienteTempo;
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

    public boolean isEstadoTarifasCliTemp() {
        return estadoTarifasCliTemp;
    }

    public void setEstadoTarifasCliTemp(boolean estadoTarifasCliTemp) {
        this.estadoTarifasCliTemp = estadoTarifasCliTemp;
    }

    public List<LogCliente> getCargaListaClientesrPreRadic() {
        return CargaListaClientesrPreRadic;
    }

    public void setCargaListaClientesrPreRadic(List<LogCliente> CargaListaClientesrPreRadic) {
        this.CargaListaClientesrPreRadic = CargaListaClientesrPreRadic;
    }

    public List<LogCliente> getListPropietarios() {
        return ListPropietarios;
    }

    public void setListPropietarios(List<LogCliente> ListPropietarios) {
        this.ListPropietarios = ListPropietarios;
    }

    public int getFilaRegistro() {
        return filaRegistro;
    }

    public void setFilaRegistro(int filaRegistro) {
        this.filaRegistro = filaRegistro;
    }

    public String getCod_cli_Temp1() {
        return cod_cli_Temp1;
    }

    public void setCod_cli_Temp1(String cod_cli_Temp1) {
        this.cod_cli_Temp1 = cod_cli_Temp1;
    }

    public String getCod_cli_Temp2() {
        return cod_cli_Temp2;
    }

    public void setCod_cli_Temp2(String cod_cli_Temp2) {
        this.cod_cli_Temp2 = cod_cli_Temp2;
    }

    public String getCod_cli_Temp3() {
        return cod_cli_Temp3;
    }

    public void setCod_cli_Temp3(String cod_cli_Temp3) {
        this.cod_cli_Temp3 = cod_cli_Temp3;
    }

    public String getCod_cli_Temp4() {
        return cod_cli_Temp4;
    }

    public void setCod_cli_Temp4(String cod_cli_Temp4) {
        this.cod_cli_Temp4 = cod_cli_Temp4;
    }

    public String getCod_cli_Temp5() {
        return cod_cli_Temp5;
    }

    public void setCod_cli_Temp5(String cod_cli_Temp5) {
        this.cod_cli_Temp5 = cod_cli_Temp5;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public boolean isEnviarClienteCarta() {
        return enviarClienteCarta;
    }

    public void setEnviarClienteCarta(boolean enviarClienteCarta) {
        this.enviarClienteCarta = enviarClienteCarta;
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
     * Metodo que consulta los tipos de documentos
     *
     * @author Jhojan Stiven Rodriguez
     * @return ArratList con la informacion de todos los tipos de documentos
     * encontrados
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> getConsulTipDocEmp() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTipDocumento().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdmin = Ite.next();
                this.TipDocEmp.add(new SelectItem(MBAdmin.getCodTipDocumento(), MBAdmin.getNomTipDocumento()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulTipDocEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.TipDocEmp;
    }

    /**
     * Metodo carga los clientes en la radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param cod_avaluo int que contiene la informacion del numero de avaluo
     * @since 01-05-2015
     */
    public void cargarClientesRadicacion(int cod_avaluo) {
        try {
            Cli = new LogCliente();
            CargaListaClientesRadic = new ArrayList<>();
            if (enviarClienteCarta) {
                CargaListaClientesRadic = Cli.ConsulCliRadica(cod_avaluo);
            } else {
                CargaListaClientesRadic = new ArrayList<>();
                enviarCartaCli1 = false;
                enviarCartaCli2 = false;
                enviarCartaCli3 = false;
                enviarCartaCli4 = false;
                enviarCartaCli5 = false;

                correoCliente1 = "";
                correoCliente2 = "";
                correoCliente3 = "";
                correoCliente4 = "";
                correoCliente5 = "";
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarClientesRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para Consultar el Cliente que es persona a facturar para subir los
     * documentos
     *
     * @author Jhojan Stiven Rodriguez
     * @param CodAva int que contiene la informacion del numero de avaluo
     * @return ArrayList con la informacion de los clientes a facturar
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> CargClienteFact(int CodAva) {
        try {
            CargaClienteFac.clear();
            Cli = new LogCliente();
            Iterator<LogCliente> Ite;
            Ite = Cli.getClienteFacturRadica(CodAva).iterator();
            while (Ite.hasNext()) {
                LogCliente MBClien = Ite.next();
                this.CargaClienteFac.add(new SelectItem(MBClien.getCodCliente(), MBClien.getNombreCliente()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".CargClienteFact()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargaClienteFac;
    }

    /**
     * Metodo tipo evento ajax para verificar que tipo de Calificacion_Regimen
     * se desea cargar documentos
     *
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void onCalifiReg() {
        try {
            if (this.codRegimenCli != null || this.codRegimenCli.equals("")) {
                CargaRegimenCli.clear();
                getConsulCalifiRegPer();
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCalifiReg()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo tipo evento ajax para consultar el tipo de Calificacion_Regimen
     *
     * @author Maira Alejandra Carvajal
     * @return ArrayList que carga los tipos de calificacion de regimen
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> getConsulCalifiRegPer() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getCalificacion(Integer.parseInt(this.codRegimenCli)).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargaRegimenCli.add(new SelectItem(MBAdm.getCodCalificacion(), MBAdm.getNombreCalificacion()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulCalifiRegPer()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaRegimenCli;

    }


    
    /**
     * Metodo que Agrega los datos del cliente temporal, de la tabla a las cajas
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void agregarDatosCliTemp() {
        try {
            if (CliTemp == null) {
                mbTodero.setMens("Seleccione un registro");
                mbTodero.warn();
            } else {
                estadoNumDocCliTemp = true;
                cod_cli_Temp = CliTemp.getCod_clienteTemp();
                numDocumento = CliTemp.getNum_clienteTemp();
                nombreCliente = CliTemp.getNombre_clienteTemp();
                cont++;
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarDatosCliTemp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga los clientes en la radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @param proceso int que define el proceso en el que se va a relizar la
     * consulta de clientes: 1=Desde Pre-Radicacion, 2=Desde Radicacion
     * @since 01-05-2015
     */
    public void cargarClientesEnRadicacion(int proceso) {
        try {
            Cli = new LogCliente();
            if (proceso == 1) {
                CargaListaClientesrPreRadic = Cli.consultaClientesPreRadi(cod_preRadic);
            } else if (proceso == 2) {
                CargaListaClientesrPreRadic = Cli.consultaClientesRadi(cod_avaluo);
            }
            if (CargaListaClientesrPreRadic.size() <= 0) {
                //No carga clientes
                estadoPanelRadiosCli = true;
                estadoSeparadorClienteEnti = true;
                this.estadoPanelClienteGeneral = false;
            } else {
                opcionMostrarCliSolicitante = "Si";
                this.estadoPanelClienteGeneral = true;
                estadoPanelRadiosCli = false;
                estadoSeparadorClienteEnti = false;
                estadoClosableClientesRegistro = true;
                for (int i = 0; i <= CargaListaClientesrPreRadic.size() - 1; i++) {
                    if ("".equals(numeroDoccliente1) || numeroDoccliente1 == null) {

                        if (proceso == 1) {
                            numeroDoccliente1 = CargaListaClientesrPreRadic.get(i).getNum_cliePreRadic();
                            nombreCliente1 = CargaListaClientesrPreRadic.get(i).getNombre_cliePreRadic();
                            telefonoCliente1 = CargaListaClientesrPreRadic.get(i).getTelefono_cliePreRadic();

                            Editar1 = true;
                            analizar1 = false;
                            quitar1 = false;
                            otro1 = false;
                            fila1 = true;

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_cliePreRadic())) {
                                ClientFact1 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic()) {
                                    Funcionario1 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic();
                                }
                                estadoAgregarTarifasPactCli1 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli1 = "Si_Cli1";
                                    estadoRadioAgregarTarifaCli1 = true;
                                    estadoTarifasPactCli1 = true;
                                    tipoTarifaCli1 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic();
                                    valorTarifaCli1 = CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic();
                                }
                            }
                            estadoConsultaCliente1 = true;
                            mensajeConsultaCliente1 = "Ok";

                        } else if (proceso == 2) {
                            tipoDoccliente1 = CargaListaClientesrPreRadic.get(i).getNombre_TipDocumentoRadic();
                            numeroDoccliente1 = CargaListaClientesrPreRadic.get(i).getNum_clieRadic();
                            nombreCliente1 = CargaListaClientesrPreRadic.get(i).getNombre_clieRadic();
                            telefonoCliente1 = CargaListaClientesrPreRadic.get(i).getTelefono_clieRadic();
                            direccionCliente1 = CargaListaClientesrPreRadic.get(i).getDireccion_clieRadic();
                            correoCliente1 = CargaListaClientesrPreRadic.get(i).getMail_clieRadic();
                            ubicacionCliente1 = CargaListaClientesrPreRadic.get(i).getUbicacion_clieRadic();

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_clieRadic())) {
                                ClientFact1 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic()) {
                                    Funcionario1 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic();
                                }
                                estadoAgregarTarifasPactCli1 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli1 = "Si_Cli1";
                                    estadoRadioAgregarTarifaCli1 = true;
                                    estadoTarifasPactCli1 = true;
                                    tipoTarifaCli1 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic();
                                    valorTarifaCli1 = CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic();
                                }
                            }
                            estadoConsultaCliente1 = true;
                            mensajeConsultaCliente1 = "Ok";
                        }

                    } else if ("".equals(numeroDoccliente2) || numeroDoccliente2 == null) {
                        estadocajasAgregarF1 = true;

                        if (proceso == 1) {

                            numeroDoccliente2 = CargaListaClientesrPreRadic.get(i).getNum_cliePreRadic();
                            nombreCliente2 = CargaListaClientesrPreRadic.get(i).getNombre_cliePreRadic();
                            telefonoCliente2 = CargaListaClientesrPreRadic.get(i).getTelefono_cliePreRadic();

                            Editar2 = true;
                            analizar2 = false;
                            quitar2 = false;
                            otro2 = false;
                            fila2 = true;

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_cliePreRadic())) {
                                ClientFact2 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic()) {
                                    Funcionario2 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic();
                                }
                                estadoAgregarTarifasPactCli2 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic() == null)) {

                                } else {

                                    opcionRadioTarifasPactadosCli2 = "Si_Cli2";
                                    estadoRadioAgregarTarifaCli2 = true;
                                    estadoTarifasPactCli2 = true;
                                    tipoTarifaCli2 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic();
                                    valorTarifaCli2 = CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic();
                                }
                            }
                            estadoConsultaCliente2 = true;
                            mensajeConsultaCliente2 = "Ok";
                        } else if (proceso == 2) {
                            tipoDoccliente2 = CargaListaClientesrPreRadic.get(i).getNombre_TipDocumentoRadic();
                            numeroDoccliente2 = CargaListaClientesrPreRadic.get(i).getNum_clieRadic();
                            nombreCliente2 = CargaListaClientesrPreRadic.get(i).getNombre_clieRadic();
                            telefonoCliente2 = CargaListaClientesrPreRadic.get(i).getTelefono_clieRadic();
                            direccionCliente2 = CargaListaClientesrPreRadic.get(i).getDireccion_clieRadic();
                            correoCliente2 = CargaListaClientesrPreRadic.get(i).getMail_clieRadic();
                            ubicacionCliente2 = CargaListaClientesrPreRadic.get(i).getUbicacion_clieRadic();

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_clieRadic())) {
                                ClientFact2 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic()) {
                                    Funcionario2 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic();
                                }
                                estadoAgregarTarifasPactCli2 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli2 = "Si_Cli2";
                                    estadoRadioAgregarTarifaCli2 = true;
                                    estadoTarifasPactCli2 = true;
                                    tipoTarifaCli2 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic();
                                    valorTarifaCli2 = CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic();
                                }
                            }
                            estadoConsultaCliente2 = true;
                            mensajeConsultaCliente2 = "Ok";
                        }

                    } else if ("".equals(numeroDoccliente3) || numeroDoccliente3 == null) {
                        estadocajasAgregarF2 = true;

                        if (proceso == 1) {
                            numeroDoccliente3 = CargaListaClientesrPreRadic.get(i).getNum_cliePreRadic();
                            nombreCliente3 = CargaListaClientesrPreRadic.get(i).getNombre_cliePreRadic();
                            telefonoCliente3 = CargaListaClientesrPreRadic.get(i).getTelefono_cliePreRadic();

                            Editar3 = true;
                            analizar3 = false;
                            quitar3 = false;
                            otro3 = false;
                            fila3 = true;

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_cliePreRadic())) {
                                ClientFact3 = true;
                                estadoAgregarTarifasPactCli3 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic()) {
                                    Funcionario3 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic();
                                }
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli3 = "Si_Cli3";
                                    estadoRadioAgregarTarifaCli3 = true;
                                    estadoTarifasPactCli3 = true;
                                    tipoTarifaCli3 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic();
                                    valorTarifaCli3 = CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic();
                                }
                            }
                            estadoConsultaCliente3 = true;
                            mensajeConsultaCliente3 = "Ok";

                        } else if (proceso == 2) {

                            tipoDoccliente3 = CargaListaClientesrPreRadic.get(i).getNombre_TipDocumentoRadic();
                            numeroDoccliente3 = CargaListaClientesrPreRadic.get(i).getNum_clieRadic();
                            nombreCliente3 = CargaListaClientesrPreRadic.get(i).getNombre_clieRadic();
                            telefonoCliente3 = CargaListaClientesrPreRadic.get(i).getTelefono_clieRadic();
                            direccionCliente3 = CargaListaClientesrPreRadic.get(i).getDireccion_clieRadic();
                            correoCliente3 = CargaListaClientesrPreRadic.get(i).getMail_clieRadic();
                            ubicacionCliente3 = CargaListaClientesrPreRadic.get(i).getUbicacion_clieRadic();

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_clieRadic())) {
                                ClientFact3 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic()) {
                                    Funcionario3 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic();
                                }
                                estadoAgregarTarifasPactCli3 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli3 = "Si_Cli3";
                                    estadoRadioAgregarTarifaCli3 = true;
                                    estadoTarifasPactCli3 = true;
                                    tipoTarifaCli3 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic();
                                    valorTarifaCli3 = CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic();
                                }
                            }
                            estadoConsultaCliente3 = true;
                            mensajeConsultaCliente3 = "Ok";
                        }

                    } else if ("".equals(numeroDoccliente4) || numeroDoccliente4 == null) {
                        estadocajasAgregarF3 = true;

                        if (proceso == 1) {
                            numeroDoccliente4 = CargaListaClientesrPreRadic.get(i).getNum_cliePreRadic();
                            nombreCliente4 = CargaListaClientesrPreRadic.get(i).getNombre_cliePreRadic();
                            telefonoCliente4 = CargaListaClientesrPreRadic.get(i).getTelefono_cliePreRadic();

                            Editar4 = true;
                            analizar4 = false;
                            quitar4 = false;
                            otro4 = false;
                            fila4 = true;

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_cliePreRadic())) {
                                ClientFact4 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic()) {
                                    Funcionario4 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic();
                                }
                                estadoAgregarTarifasPactCli4 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli4 = "Si_Cli4";
                                    estadoRadioAgregarTarifaCli4 = true;
                                    estadoTarifasPactCli4 = true;
                                    tipoTarifaCli4 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic();
                                    valorTarifaCli4 = CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic();
                                }
                            }
                            estadoConsultaCliente4 = true;
                            mensajeConsultaCliente4 = "Ok";
                        } else if (proceso == 2) {

                            tipoDoccliente4 = CargaListaClientesrPreRadic.get(i).getNombre_TipDocumentoRadic();
                            numeroDoccliente4 = CargaListaClientesrPreRadic.get(i).getNum_clieRadic();
                            nombreCliente4 = CargaListaClientesrPreRadic.get(i).getNombre_clieRadic();
                            telefonoCliente4 = CargaListaClientesrPreRadic.get(i).getTelefono_clieRadic();
                            direccionCliente4 = CargaListaClientesrPreRadic.get(i).getDireccion_clieRadic();
                            correoCliente4 = CargaListaClientesrPreRadic.get(i).getMail_clieRadic();
                            ubicacionCliente4 = CargaListaClientesrPreRadic.get(i).getUbicacion_clieRadic();

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_clieRadic())) {
                                ClientFact4 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic()) {
                                    Funcionario4 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic();
                                }
                                estadoAgregarTarifasPactCli4 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli4 = "Si_Cli4";
                                    estadoRadioAgregarTarifaCli4 = true;
                                    estadoTarifasPactCli4 = true;
                                    tipoTarifaCli4 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic();
                                    valorTarifaCli4 = CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic();
                                }
                            }
                            estadoConsultaCliente4 = true;
                            mensajeConsultaCliente4 = "Ok";
                        }

                    } else if ("".equals(numeroDoccliente5) || numeroDoccliente5 == null) {
                        estadocajasAgregarF4 = true;

                        if (proceso == 1) {
                            numeroDoccliente5 = CargaListaClientesrPreRadic.get(i).getNum_cliePreRadic();
                            nombreCliente5 = CargaListaClientesrPreRadic.get(i).getNombre_cliePreRadic();
                            telefonoCliente5 = CargaListaClientesrPreRadic.get(i).getTelefono_cliePreRadic();

                            Editar5 = true;
                            analizar5 = false;
                            quitar5 = false;
                            fila5 = true;

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_cliePreRadic())) {
                                ClientFact5 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic()) {
                                    Funcionario5 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliPreRadic();
                                }
                                estadoAgregarTarifasPactCli5 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli5 = "Si_Cli5";
                                    estadoRadioAgregarTarifaCli5 = true;
                                    estadoTarifasPactCli5 = true;
                                    tipoTarifaCli5 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_cliePreRadic();
                                    valorTarifaCli5 = CargaListaClientesrPreRadic.get(i).getValorTatifa_cliePreRadic();
                                }
                            }
                            estadoConsultaCliente5 = true;
                            mensajeConsultaCliente5 = "Ok";
                        } else if (proceso == 2) {
                            tipoDoccliente5 = CargaListaClientesrPreRadic.get(i).getNombre_TipDocumentoRadic();
                            numeroDoccliente5 = CargaListaClientesrPreRadic.get(i).getNum_clieRadic();
                            nombreCliente5 = CargaListaClientesrPreRadic.get(i).getNombre_clieRadic();
                            telefonoCliente5 = CargaListaClientesrPreRadic.get(i).getTelefono_clieRadic();
                            direccionCliente5 = CargaListaClientesrPreRadic.get(i).getDireccion_clieRadic();
                            correoCliente5 = CargaListaClientesrPreRadic.get(i).getMail_clieRadic();
                            ubicacionCliente5 = CargaListaClientesrPreRadic.get(i).getUbicacion_clieRadic();

                            if ("Persona a facturar".equals(CargaListaClientesrPreRadic.get(i).getTipo_clieRadic())) {
                                ClientFact5 = true;
                                if (CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic()) {
                                    Funcionario5 = CargaListaClientesrPreRadic.get(i).isFuncionario_cliRadic();
                                }
                                estadoAgregarTarifasPactCli5 = true;
                                if (("".equals(CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic() == null)
                                        && ("".equals(CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic()) || CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic() == null)) {

                                } else {
                                    opcionRadioTarifasPactadosCli5 = "Si_Cli5";
                                    estadoRadioAgregarTarifaCli5 = true;
                                    estadoTarifasPactCli5 = true;
                                    tipoTarifaCli5 = CargaListaClientesrPreRadic.get(i).getTipoTatifa_clieRadic();
                                    valorTarifaCli5 = CargaListaClientesrPreRadic.get(i).getValorTatifa_clieRadic();
                                }
                            }
                            estadoConsultaCliente5 = true;
                            mensajeConsultaCliente5 = "Ok";
                        }
                    }
                }
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarClientesEnRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que muestra los radios para agregar tarifas a los clientes, si es
     * persona facturar
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que define numero de cliente del que se va a mostrar la
     * informacion de las tarifas pactadas
     * @since 01-05-2015
     */
    public void mostrarAgregarTarPac(int tipo) {
        try {
            if (tipo == 1) {
                if (ClientFact1 == true) {
                    estadoAgregarTarifasPactCli1 = true;
                } else {
                    estadoAgregarTarifasPactCli1 = false;
                    opcionRadioTarifasPactadosCli1 = "";
                    estadoTarifasPactCli1 = false;
                    tipoTarifaCli1 = "";
                    valorTarifaCli1 = "";
                }
            }
            if (tipo == 2) {
                if (ClientFact2 == true) {
                    estadoAgregarTarifasPactCli2 = true;
                } else {
                    estadoAgregarTarifasPactCli2 = false;
                    opcionRadioTarifasPactadosCli2 = "";
                    estadoTarifasPactCli2 = false;
                    tipoTarifaCli2 = "";
                    valorTarifaCli2 = "";
                }

            }
            if (tipo == 3) {
                if (ClientFact3 == true) {
                    estadoAgregarTarifasPactCli3 = true;
                } else {
                    estadoAgregarTarifasPactCli3 = false;
                    opcionRadioTarifasPactadosCli3 = "";
                    estadoTarifasPactCli3 = false;
                    tipoTarifaCli3 = "";
                    valorTarifaCli3 = "";
                }

            }
            if (tipo == 4) {
                if (ClientFact4 == true) {
                    estadoAgregarTarifasPactCli4 = true;
                } else {
                    estadoAgregarTarifasPactCli4 = false;
                    opcionRadioTarifasPactadosCli4 = "";
                    estadoTarifasPactCli4 = false;
                    tipoTarifaCli4 = "";
                    valorTarifaCli4 = "";
                }
            }
            if (tipo == 5) {
                if (ClientFact5 == true) {
                    estadoAgregarTarifasPactCli5 = true;
                } else {
                    estadoAgregarTarifasPactCli5 = false;
                    opcionRadioTarifasPactadosCli5 = "";
                    estadoTarifasPactCli5 = false;
                    tipoTarifaCli5 = "";
                    valorTarifaCli5 = "";
                }
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarAgregarTarPac()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que muestra los radios para agregar tarifas a los clientes, si es
     * persona facturar
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_caja int que define numero de cliente del que se va a mostrar
     * la informacion de las tarifas pactadas
     * @since 01-05-2015
     */
    public void limpiarConfirmarAjax(String tipo_caja) {
        try {
            switch (tipo_caja) {
                case "Direccion":
                    confir_direccionCliente = "";
                    break;
                case "Email":
                    confir_emailCliente = "";
                    break;
                case "Documento":
                    confir_numDocumento = "";
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarConfirmarAjax()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo tipo ajax que desordena las cajas de texto de direccion, email y
     * numero de documento
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo_caja String que contiene el tipo de caja a la que se le va a
     * aplicar el desordenamiento de caracteres
     * @since 01-05-2015
     */
    public void desornaCamposAjax(String tipo_caja) {
        try {
            switch (tipo_caja) {

                case "Direccion":

                    if (contadDirecc == 1) {
                        direccionClienteOK = direccionCliente;

                        List<String> desordendireccion = Arrays.asList(direccionCliente.split(""));
                        Collections.shuffle(desordendireccion);

                        direccionCliente = "";
                        desordendireccion.stream().forEach((s) -> {
                            direccionCliente += s;
                        });

                        contadDirecc++;
                    } else if (direccionClienteOK.equals(confir_direccionCliente)) {
                        direccionCliente = direccionClienteOK;
                        contadDirecc = 1;
                    }

                    break;
                case "Email":
                    if (contadEmail == 1) {
                        contadEmail++;
                    }

                    break;
                case "Documento":
                    if (contadDocum == 1) {
                        numDocumentoOK = numDocumento;

                        List<String> desordenDocumento = Arrays.asList(numDocumento.split(""));
                        Collections.shuffle(desordenDocumento);

                        numDocumento = "";
                        desordenDocumento.stream().forEach((s) -> {
                    numDocumento += s;
                });

                        contadDocum++;
                    } else if (numDocumentoOK.equals(confir_numDocumento)) {
                        numDocumento = numDocumentoOK;
                        contadDocum = 1;
                    }

                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarConfirmarAjax()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre el dialog para agregar las tarifas pactadas
     *
     * @author Jhojan Stiven Rodriguez
     * @param num_cliente
     * @since 01-05-2015
     */
    public void agregarTarifasPactadas(int num_cliente) {

        this.num_cliente = num_cliente;
        try {
            if (num_cliente == 1) {
                switch (opcionRadioTarifasPactadosCli1) {
                    case "Si_Cli1":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').show()");
                        tipoTarifaCli = tipoTarifaCli1;
                        valorTarifaCli = valorTarifaCli1;
                        break;
                    case "No_Cli1":
                        estadoTarifasPactCli1 = false;
                        tipoTarifaCli1 = "";
                        valorTarifaCli1 = "";
                        tipoTarifaCli = "";
                        valorTarifaCli = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').hide()");
                        break;
                }
            }

            if (num_cliente == 2) {
                switch (opcionRadioTarifasPactadosCli2) {
                    case "Si_Cli2":
                        tipoTarifaCli = tipoTarifaCli2;
                        valorTarifaCli = valorTarifaCli2;
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').show()");
                        break;
                    case "No_Cli2":
                        estadoTarifasPactCli2 = false;
                        tipoTarifaCli2 = "";
                        valorTarifaCli2 = "";
                        tipoTarifaCli = "";
                        valorTarifaCli = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').hide()");
                        break;
                }
            }

            if (num_cliente == 3) {
                switch (opcionRadioTarifasPactadosCli3) {
                    case "Si_Cli3":
                        tipoTarifaCli = tipoTarifaCli3;
                        valorTarifaCli = valorTarifaCli3;
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').show()");
                        break;
                    case "No_Cli3":
                        estadoTarifasPactCli3 = false;
                        tipoTarifaCli3 = "";
                        valorTarifaCli3 = "";
                        tipoTarifaCli = "";
                        valorTarifaCli = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').hide()");
                        break;
                }
            }

            if (num_cliente == 4) {
                switch (opcionRadioTarifasPactadosCli4) {
                    case "Si_Cli4":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').show()");
                        tipoTarifaCli = tipoTarifaCli4;
                        valorTarifaCli = valorTarifaCli4;
                        break;
                    case "No_Cli4":
                        estadoTarifasPactCli4 = false;
                        tipoTarifaCli4 = "";
                        valorTarifaCli4 = "";
                        tipoTarifaCli = "";
                        valorTarifaCli = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').hide()");
                        break;
                }
            }

            if (num_cliente == 5) {
                switch (opcionRadioTarifasPactadosCli5) {
                    case "Si_Cli5":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').show()");
                        tipoTarifaCli = tipoTarifaCli5;
                        valorTarifaCli = valorTarifaCli5;
                        break;
                    case "No_Cli5":
                        estadoTarifasPactCli5 = false;
                        tipoTarifaCli5 = "";
                        valorTarifaCli5 = "";
                        tipoTarifaCli = "";
                        valorTarifaCli = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').hide()");
                        break;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarTarifasPactadas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que agrega las tarifas pactadas al cliente, tipo y valor de tarifa
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void agregarTarifasClientes() {
        try {
            if (("".equals(tipoTarifaCli) || tipoTarifaCli == null) && ("".equals(valorTarifaCli) || valorTarifaCli == null)) {
                mbTodero.setMens("Debe llenar los campos para el tipo de tarifa");
                mbTodero.warn();
            } else if (("".equals(tipoTarifaCli) || tipoTarifaCli == null)) {
                mbTodero.setMens("Debe llenar el campo Tipo de tarifa");
                mbTodero.warn();
            } else if (("".equals(valorTarifaCli) || valorTarifaCli == null)) {
                mbTodero.setMens("Debe llenar el campo Valor de tarifa");
                mbTodero.warn();
            } else {
                if (num_cliente == 1) {
                    estadoTarifasPactCli1 = true;
                    tipoTarifaCli1 = tipoTarifaCli;
                    valorTarifaCli1 = valorTarifaCli;
                }
                if (num_cliente == 2) {
                    estadoTarifasPactCli2 = true;
                    tipoTarifaCli2 = tipoTarifaCli;
                    valorTarifaCli2 = valorTarifaCli;
                }
                if (num_cliente == 3) {
                    estadoTarifasPactCli3 = true;
                    tipoTarifaCli3 = tipoTarifaCli;
                    valorTarifaCli3 = valorTarifaCli;
                }
                if (num_cliente == 4) {
                    estadoTarifasPactCli4 = true;
                    tipoTarifaCli4 = tipoTarifaCli;
                    valorTarifaCli4 = valorTarifaCli;
                }
                if (num_cliente == 5) {
                    estadoTarifasPactCli5 = true;
                    tipoTarifaCli5 = tipoTarifaCli;
                    valorTarifaCli5 = valorTarifaCli;
                }
                tipoTarifaCli = "";
                valorTarifaCli = "";
                RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaCli').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarTarifasClientes()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que agrega los clientes en las cajas segun el analizar del
     * cliente, si fue correcto o no o si no debe registrarlo
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void cargarInfo() {
        try {

            if (this.Cli == null) {
                mbTodero.setMens("Debe seleccionar el cliente a agregar");
                mbTodero.warn();
            } else {
                //agregar datos en cajas de cliente solicitante            
                if (tipo == 1) {

                    this.mensajeConsultaCliente1 = "Ok";
                    this.estadoConsultaCliente1 = true;
                    this.nombreCliente1 = Cli.getNombreCliente();
                    this.numeroDoccliente1 = Cli.getNumeroDoccliente();
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");

                    fila1 = true;
                    Editar1 = true;
                    analizar1 = false;
                    // quitar1 = false;
                    //otro1 = estadocajasAgregarF1 == false;

                } else if (tipo == 2) {

                    if (numeroDoccliente1.equals(Cli.getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {

                        this.mensajeConsultaCliente2 = "Ok";
                        this.estadoConsultaCliente2 = true;
                        this.nombreCliente2 = Cli.getNombreCliente();
                        this.numeroDoccliente2 = Cli.getNumeroDoccliente();
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");

                        fila2 = true;
                        Editar2 = true;
                        analizar2 = false;
                        //quitar2 = false;
                        //otro2 = estadocajasAgregarF2 == false;
                    }

                } else if (tipo == 3) {
                    if (numeroDoccliente1.equals(Cli.getNumeroDoccliente()) || numeroDoccliente2.equals(Cli.getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        this.mensajeConsultaCliente3 = "Ok";
                        this.estadoConsultaCliente3 = true;
                        this.nombreCliente3 = Cli.getNombreCliente();
                        this.numeroDoccliente3 = Cli.getNumeroDoccliente();
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");

                        fila3 = true;
                        Editar3 = true;
                        analizar3 = false;
                        // quitar3 = false;
                        // otro3 = estadocajasAgregarF3 == false;
                    }

                } else if (tipo == 4) {
                    if (numeroDoccliente1.equals(Cli.getNumeroDoccliente()) || numeroDoccliente2.equals(Cli.getNumeroDoccliente())
                            || numeroDoccliente3.equals(Cli.getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        this.mensajeConsultaCliente4 = "Ok";
                        this.estadoConsultaCliente4 = true;
                        this.nombreCliente4 = Cli.getNombreCliente();
                        this.numeroDoccliente4 = Cli.getNumeroDoccliente();
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");

                        fila4 = true;
                        Editar4 = true;
                        analizar4 = false;
//                        quitar4 = false;
//                        otro4 = estadocajasAgregarF4 == false;
                    }

                } else if (tipo == 5) {
                    if (numeroDoccliente1.equals(Cli.getNumeroDoccliente()) || numeroDoccliente2.equals(Cli.getNumeroDoccliente())
                            || numeroDoccliente3.equals(Cli.getNumeroDoccliente()) || numeroDoccliente4.equals(Cli.getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        this.mensajeConsultaCliente5 = "Ok";
                        this.estadoConsultaCliente4 = true;
                        this.nombreCliente5 = Cli.getNombreCliente();
                        this.numeroDoccliente5 = Cli.getNumeroDoccliente();

                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");

                        fila5 = true;
                        Editar5 = true;
                        analizar5 = false;
//                        quitar5 = false;
                    }
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que agrega las cajas cuando se da click en el boton de mas
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que contiere el numero de siguiente cliente que se
     * habilita para ser ingresado
     * @since 01-05-2015
     */
    public void agregarCajas(int tipo) {
        this.tipo = tipo;
        try {
            //si la tabla de enseres o mubles tiene inforfmacion la va a habilitar
            if (tipo == 1) {
                if ("".equals(nombreCliente1) || "".equals(numeroDoccliente1)) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta sección");
                    mbTodero.warn();
                } else if ("".equals(mensajeConsultaCliente1) || mensajeConsultaCliente1 == null) {
                    mbTodero.setMens("Debe analizar la información ingresada");
                    mbTodero.warn();
                } else if ("N/A".equals(mensajeConsultaCliente1)) {
                    mbTodero.setMens("Debe registrar el cliente");
                    mbTodero.warn();
                } else {

                    estadocajasAgregarF1 = true;

                    fila1 = true;
                    fila2 = false;
                    analizar1 = false;
                    analizar2 = true;

                    Editar1 = true;

                    quitar1 = false;

                    quitar2 = true;

                    otro1 = false;
                    otro2 = estadocajasAgregarF2 != true;

                }

            } //si la tabla de maquinaria  tiene inforfmacion la va a habilitar
            else if (tipo == 2) {
                if ("".equals(nombreCliente2) || "".equals(numeroDoccliente2)) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta sección");
                    mbTodero.warn();
                } else if ("".equals(mensajeConsultaCliente2) || mensajeConsultaCliente2 == null) {
                    mbTodero.setMens("Debe analizar la información ingresada");
                    mbTodero.warn();
                } else if ("N/A".equals(mensajeConsultaCliente2)) {
                    mbTodero.setMens("Debe registrar el cliente");
                    mbTodero.warn();
                } else {

                    estadocajasAgregarF2 = true;

                    fila2 = true;
                    fila3 = false;
                    analizar2 = false;
                    analizar3 = true;

                    Editar2 = true;
                    quitar2 = false;
                    quitar3 = true;

                    otro2 = false;
                    otro3 = estadocajasAgregarF3 != true;
                }

            } else if (tipo == 3) {
                if ("".equals(nombreCliente3) && "".equals(numeroDoccliente3)) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta sección");
                    mbTodero.warn();
                } else if ("".equals(mensajeConsultaCliente3) || mensajeConsultaCliente3 == null) {
                    mbTodero.setMens("Debe analizar la información ingresada");
                    mbTodero.warn();
                } else if ("N/A".equals(mensajeConsultaCliente3)) {
                    mbTodero.setMens("Debe registrar el cliente");
                    mbTodero.warn();
                } else {

                    estadocajasAgregarF3 = true;

                    fila3 = true;
                    fila4 = false;
                    analizar3 = false;
                    analizar4 = true;

                    Editar3 = true;
                    quitar3 = false;
                    quitar4 = true;

                    otro3 = false;
                    otro4 = estadocajasAgregarF4 != true;

                }

            } else if (tipo == 4) {
                if ("".equals(nombreCliente4) && "".equals(numeroDoccliente4)) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta sección");
                    mbTodero.warn();
                } else if ("".equals(mensajeConsultaCliente4) || mensajeConsultaCliente4 == null) {
                    mbTodero.setMens("Debe analizar la información ingresada");
                    mbTodero.warn();
                } else if ("N/A".equals(mensajeConsultaCliente4)) {
                    mbTodero.setMens("Debe registrar el cliente");
                    mbTodero.warn();
                } else {

                    estadocajasAgregarF4 = true;

                    fila4 = true;
                    fila5 = false;
                    analizar4 = false;
                    analizar5 = true;

                    Editar4 = true;
                    quitar4 = false;
                    quitar5 = true;

                    otro4 = false;

                }

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarCajas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que valida cuando el cliente esta o no registrado
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que contiere el numero de siguiente cliente que se
     * habilita para ser ingresado
     * @since 01-05-2015
     */
    public void consultaCliente(int tipo) {
        try {
            this.tipo = tipo;
            Cli = new LogCliente();
            this.ListClient = null;
            //realiza la consulta por el parametro de las cajas de la fila 0
            if (tipo == 1) {
                filaRegistro = 1;
                if (("".equals(this.numeroDoccliente1) || numeroDoccliente1 == null) && ("".equals(nombreCliente1) || nombreCliente1 == null) && ("".equals(telefonoCliente1) || telefonoCliente1 == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar un cliente");
                    mbTodero.warn();
                } else {
                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(this.numeroDoccliente1, this.nombreCliente1, telefonoCliente1, 2);
                    if (this.ListClient.size() > 0) {
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        this.mensajeConsultaCliente1 = "N/A";
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones");
                        mbTodero.info();
                        this.estadoBtnRegistroF1 = true;
                        mbTodero.setMens("Debe realizar el registro del cliente");
                        mbTodero.info();
                        mostrarFormRegistro(tipo);
                    }
                }
            } else if (tipo == 2) {
                filaRegistro = 2;
                //realiza la consulta por el parametro de las cajas de la fila 1
                if (("".equals(this.numeroDoccliente2) || numeroDoccliente2 == null) && ("".equals(nombreCliente2) || nombreCliente2 == null) && ("".equals(telefonoCliente2) || telefonoCliente2 == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar un cliente");
                    mbTodero.warn();
                } else {
                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(this.numeroDoccliente2, this.nombreCliente2, telefonoCliente2, 2);
                    if (this.ListClient.size() > 0) {

                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        this.mensajeConsultaCliente2 = "N/A";
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones");
                        mbTodero.info();

                        this.estadoBtnRegistroF2 = true;
                        mbTodero.setMens("Debe realizar el registro del cliente");
                        mbTodero.info();
                        mostrarFormRegistro(tipo);
                    }
                }
            } else if (tipo == 3) {
                filaRegistro = 3;
                //realiza la consulta por el parametro de las cajas de la fila 2
                if (("".equals(this.numeroDoccliente3) || numeroDoccliente3 == null) && ("".equals(nombreCliente3) || nombreCliente3 == null) && ("".equals(telefonoCliente3) || telefonoCliente3 == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar un cliente");
                    mbTodero.warn();

                } else {
                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(this.numeroDoccliente3, this.nombreCliente3, telefonoCliente3, 2);
                    if (this.ListClient.size() > 0) {

                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        this.mensajeConsultaCliente3 = "N/A";
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones");
                        mbTodero.info();
                        this.estadoBtnRegistroF3 = true;
                        mbTodero.setMens("Debe realizar el registro del cliente");
                        mbTodero.info();
                        mostrarFormRegistro(tipo);
                    }
                }
            } else if (tipo == 4) {
                filaRegistro = 4;
                //realiza la consulta por el parametro de las cajas de la fila 3
                if (("".equals(this.numeroDoccliente4) || numeroDoccliente4 == null) && ("".equals(nombreCliente4) || nombreCliente4 == null) && ("".equals(telefonoCliente4) || telefonoCliente4 == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar un cliente");
                    mbTodero.warn();

                } else {
                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(this.numeroDoccliente4, this.nombreCliente4, telefonoCliente4, 2);
                    if (this.ListClient.size() > 0) {

                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        this.mensajeConsultaCliente4 = "N/A";
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones");
                        mbTodero.info();
                        this.estadoBtnRegistroF4 = true;
                        mbTodero.setMens("Debe realizar el registro del cliente");
                        mbTodero.info();
                        mostrarFormRegistro(tipo);
                    }
                }
            } else if (tipo == 5) {
                filaRegistro = 5;
                //realiza la consulta por el parametro de las cajas de la fila 4
                if (("".equals(this.numeroDoccliente5) || numeroDoccliente5 == null) && ("".equals(nombreCliente5) || nombreCliente5 == null) && ("".equals(telefonoCliente5) || telefonoCliente5 == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar un cliente");
                    mbTodero.warn();
                } else {
                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(this.numeroDoccliente5, this.nombreCliente5, telefonoCliente5, 2);
                    if (this.ListClient.size() > 0) {

                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        this.mensajeConsultaCliente5 = "N/A";
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones");
                        mbTodero.info();

                        this.estadoBtnRegistroF5 = true;
                        mbTodero.setMens("Debe realizar el registro del cliente");
                        mbTodero.info();
                    }
                }

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que ingresa informacion a las cajas de texto para registrar el
     * cliente desde el dialoog
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void cargarInfoClienteRegistro() {
        try {
            limpiarCajasRegistroCli();
            estadoRegistroClienteBtn = false;
            estadoNumDocCliTemp = true;
            estadoActualizarClienteBtn = true;
            contadDirecc = 1;
            contadDocum = 1;
            contadEmail = 1;
            if (filaRegistro == 1) {
                nombreCliente = nombreCliente1;

                numDocumento = numeroDoccliente1;
                telefonoCl = telefonoCliente1;
            }
            if (filaRegistro == 2) {
                nombreCliente = nombreCliente2;
                numDocumento = numeroDoccliente2;
                telefonoCl = telefonoCliente2;
            }
            if (filaRegistro == 3) {
                nombreCliente = nombreCliente3;
                numDocumento = numeroDoccliente3;
                telefonoCl = telefonoCliente3;
            }
            if (filaRegistro == 4) {
                nombreCliente = nombreCliente4;
                numDocumento = numeroDoccliente4;
                telefonoCl = telefonoCliente4;
            }
            if (filaRegistro == 5) {
                nombreCliente = nombreCliente5;
                numDocumento = numeroDoccliente5;
                telefonoCl = telefonoCliente5;
            }
            RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarInfoClienteRegistro()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga la informacion de los clientes en el formulario tipo
     * dialog, para actualizar el cliente
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void cargarInfoClienteModificar() {
        try {
            if (Cli == null) {
                mbTodero.setMens("Debe seleccionar un cliente");
                mbTodero.error();
            } else {

                limpiarCajasRegistroCli();

                estadoNumDocCliTemp = true;
                estadoActualizarClienteBtn = false;
                estadoRegistroClienteBtn = true;

                cod_cliente = Cli.getCodCliente();

                numDocumento = Cli.getNumeroDoccliente();
                numDocumentoOK = Cli.getNumeroDoccliente();
                confir_numDocumento = Cli.getNumeroDoccliente();

                nombreCliente = Cli.getNombreCliente();

                direccionCliente = Cli.getDireccionCliente();
                confir_direccionCliente = Cli.getDireccionCliente();
                direccionClienteOK = Cli.getDireccionCliente();

                emailCliente = Cli.getMailCliente();
                infoCorreocliente = !"".equals(emailCliente) && emailCliente != null;

                confir_emailCliente = Cli.getMailCliente();
                telefonoCl = Cli.getTelefonoCliente();

                codTipDoc = String.valueOf(Cli.getFK_TipDocumento());

                codDeptoCli = String.valueOf(Cli.getFK_CodDepto());

                codCiudCli = String.valueOf(Cli.getFK_CodCiudad());

                codRegimenCli = String.valueOf(Cli.getFK_CodRegimen());
                onCalifiReg();
                codTipRegimenCli = String.valueOf(Cli.getFK_CodCalificacion());

                RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");

            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarInfoClienteModificar()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo habilita los paneles para registra los clientes a facturar
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo
     * @since 01-05-2015
     */
    public void cargarPanelClienteFacturar(int tipo) {
        try {
            switch (this.opcionRadio) {
                case "Si":
                    this.estadoPanelClienteFacturar = true;
                    break;
                case "No":
                    this.estadoPanelClienteFacturar = false;
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarPanelClienteFacturar()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que abre el formulario de registro de clientes y carga la
     * informacion previamente ingresada
     *
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene informacion sobre la fila desde la que se
     * esta abriendo el dialog de registro de clientes
     * @since 01-05-2015
     */
    public void mostrarFormRegistro(int fila) {
        try {
            this.filaRegistro = fila;
            estadoRegistroClienteBtn = false;
            estadoActualizarClienteBtn = true;
            limpiarCajasRegistroCli();
            RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
            contadDirecc = 1;
            contadDocum = 1;
            contadEmail = 1;
            if (filaRegistro == 1) {

                numDocumento = numeroDoccliente1;
                nombreCliente = nombreCliente1;
                telefonoCl = telefonoCliente1;
            } else if (filaRegistro == 2) {
                estadoTablaClientesTempo = false;
                numDocumento = numeroDoccliente2;
                nombreCliente = nombreCliente2;
                telefonoCl = telefonoCliente2;
            } else if (filaRegistro == 3) {
                estadoTablaClientesTempo = false;
                numDocumento = numeroDoccliente3;
                nombreCliente = nombreCliente3;
                telefonoCl = telefonoCliente3;
            } else if (filaRegistro == 4) {
                estadoTablaClientesTempo = false;
                numDocumento = numeroDoccliente4;
                nombreCliente = nombreCliente4;
                telefonoCl = telefonoCliente4;
            } else if (filaRegistro == 5) {
                estadoTablaClientesTempo = false;
                numDocumento = numeroDoccliente5;
                nombreCliente = nombreCliente5;
                telefonoCl = telefonoCliente5;
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarFormRegistro()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite registrar a los clientes que no se encuentren en base
     * de datos permite validar todos los datos, registra la informacion y
     * agrega lo que se guardo a las respectivas cajas de cada fila
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que contiene la informacion para saber que tipo de opcion
     * realizar 1: registrar, 2 : actualizar
     * @param proceso int que contine la informacion para saber que proceso
     * realizar, 1: registro/modificacion en pre-radicacion 2:
     * registro/modificacion en radicacion
     * @since 01-05-2015
     */
    public void registrarClientes(int tipo, int proceso) {
        try {
            boolean OkValidar = true;
            Cli = new LogCliente();
            Dat = Cli.ConsulCodCli(numDocumentoOK);
 
            if ("0".equals(this.codTipDoc) && "".equals(this.numDocumentoOK) && "".equals(this.nombreCliente)
                    && "".equals(this.direccionCliente) && "0".equals(this.codDeptoCli) && "0".equals(this.codCiudCli)
                    && "0".equals(this.codRegimenCli) && "0".equals(this.codTipRegimenCli)) {
                mbTodero.setMens("Debe llenar todos los campos obligatorios (*)");
                mbTodero.warn();
                OkValidar = false;
            } else if ("0".equals(this.codTipDoc) || this.codTipDoc == null) {
                mbTodero.setMens("El campo 'Tipo Documento' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if ("".equals(this.numDocumento) || this.numDocumento == null) {
                mbTodero.setMens("El campo 'N° Documento' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if ("".equals(this.confir_numDocumento) || this.confir_numDocumento == null) {
                mbTodero.setMens("Debe confirmar el N° de Documento'");
                mbTodero.warn();
                OkValidar = false;
            } else if ("".equals(this.nombreCliente) || this.nombreCliente == null) {
                mbTodero.setMens("El campo 'Nombres' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if ("".equals(this.direccionCliente) || this.direccionCliente == null) {
                mbTodero.setMens("El campo 'Dirección' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if ("".equals(this.telefonoCl) || this.telefonoCl == null) {
                mbTodero.setMens("El campo 'Telefono' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if ("0".equals(this.codDeptoCli) || this.codDeptoCli == null) {
                mbTodero.setMens("El campo 'Departamento' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if ("0".equals(this.codCiudCli) || this.codCiudCli == null) {
                mbTodero.setMens("El campo 'Ciudad' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if (!numDocumentoOK.equals(confir_numDocumento)) {
                mbTodero.setMens("Los n° de documento no coinciden, favor verifique la información");
                mbTodero.warn();
                OkValidar = false;
            } else if ("".equals(emailCliente) && infoCorreocliente == true) {
                mbTodero.setMens("El campo 'Correo' es obligatorio");
                mbTodero.warn();
                OkValidar = false;
            } else if (!emailCliente.equals(confir_emailCliente) && infoCorreocliente == true) {
                mbTodero.setMens("Los Email no coinciden, favor verifique la información");
                mbTodero.warn();
                OkValidar = false;
            } else if ((tipo == 1) && (Dat.next())) {
                mbTodero.setMens("El cliente ya se encuentra registrado, favor verifique y edite la información");
                mbTodero.warn();
                OkValidar = false;

            } else if ((tipo == 1) || (tipo == 2)) {

                Cli.setFK_TipDocumento(Integer.parseInt(codTipDoc));
                Cli.setNumeroDoccliente(numDocumentoOK);
                Cli.setNombreCliente(nombreCliente);
                Cli.setDireccionCliente(direccionCliente);
                Cli.setMailCliente(emailCliente);
                Cli.setTelefonoCliente(telefonoCl);
                Cli.setFK_CodCiudad(Integer.parseInt(codCiudCli));
                Cli.setFK_CodCalificacion(Integer.parseInt(codTipRegimenCli));

                Cli.InsertarCliente(tipo, mBsesion.codigoMiSesion());
                //Consulta el codigo del cliente que se acabo de registrar
                Dat = Cli.ConsulCodCli(numDocumentoOK);
                if (tipo == 1) {
                    if (Dat.next()) {
                        cod_cliente = Dat.getInt("Cod_Cliente");
                    }
                }

                if (tipo == 1) {
                    mbTodero.setMens("Cliente Registrado");
                    mbTodero.info();
                } else {
                    mbTodero.setMens("Cliente Actualizado");
                    mbTodero.info();
                }

                switch (filaRegistro) {
                    case 1:
                        if (tipo == 1) {
                            if (cod_cli_Temp1 == null) {
                            } else {
                                Cli.ActualizarClienteTempo(Integer.parseInt(cod_cli_Temp1));
                            }
                        }   mensajeConsultaCliente1 = "Ok";
                        numeroDoccliente1 = numDocumentoOK;
                        nombreCliente1 = nombreCliente;
                        telefonoCliente1 = telefonoCl;
                        if (proceso == 2) {
                            fila1 = true;
                            Editar1 = true;
                            analizar1 = false;
                        } else {
                            fila1 = true;
                            Editar1 = true;
                            analizar1 = false;
                            quitar1 = false;
                            otro1 = estadocajasAgregarF1 == false;
                        }   break;
                    case 2:
                        if (tipo == 1) {
                            if (cod_cli_Temp2 == null) {
                            } else {
                                Cli.ActualizarClienteTempo(Integer.parseInt(cod_cli_Temp2));
                            }
                        }   mensajeConsultaCliente2 = "Ok";
                        numeroDoccliente2 = numDocumentoOK;
                        nombreCliente2 = nombreCliente;
                        telefonoCliente2 = telefonoCl;
                        if (proceso == 2) {
                            fila2 = true;
                            Editar2 = true;
                            analizar2 = false;
                        } else {
                            fila2 = true;
                            Editar2 = true;
                            analizar2 = false;
                            quitar2 = false;
                            otro1 = estadocajasAgregarF2 == false;
                        }   break;
                    case 3:
                        if (tipo == 1) {
                            if (cod_cli_Temp3 == null) {
                            } else {
                                Cli.ActualizarClienteTempo(Integer.parseInt(cod_cli_Temp3));
                            }
                        }   numeroDoccliente3 = numDocumentoOK;
                        nombreCliente3 = nombreCliente;
                        mensajeConsultaCliente3 = "Ok";
                        telefonoCliente3 = telefonoCl;
                        if (proceso == 2) {
                            fila3 = true;
                            Editar3 = true;
                            analizar3 = false;
                        } else {
                            fila3 = true;
                            Editar3 = true;
                            analizar3 = false;
                            quitar3 = false;
                            otro3 = estadocajasAgregarF3 == false;
                        }   break;
                    case 4:
                        if (tipo == 1) {
                            if (cod_cli_Temp4 == null) {
                            } else {
                                Cli.ActualizarClienteTempo(Integer.parseInt(cod_cli_Temp4));
                            }
                        }   mensajeConsultaCliente4 = "Ok";
                        numeroDoccliente4 = numDocumentoOK;
                        nombreCliente4 = nombreCliente;
                        telefonoCliente4 = telefonoCl;
                        if (proceso == 2) {
                            fila4 = true;
                            Editar4 = true;
                            analizar4 = false;
                        } else {
                            fila4 = true;
                            Editar4 = true;
                            analizar4 = false;
                            quitar4 = false;
                            otro4 = estadocajasAgregarF4 == false;
                        }   break;
                    case 5:
                        if (tipo == 1) {
                            if (cod_cli_Temp5 == null) {
                            } else {
                                Cli.ActualizarClienteTempo(Integer.parseInt(cod_cli_Temp5));
                            }
                        }   mensajeConsultaCliente5 = "Ok";
                        numeroDoccliente5 = numDocumentoOK;
                        nombreCliente5 = nombreCliente;
                        telefonoCliente5 = telefonoCl;
                        if (proceso == 2) {
                            fila5 = true;
                            Editar5 = true;
                            analizar5 = false;
                        } else {
                            fila5 = true;
                            Editar5 = true;
                            analizar5 = false;
                            quitar5 = false;
                        }   break;
                    default:
                        break;
                }
                limpiarCajasRegistroCli();
                RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");
                RequestContext.getCurrentInstance().execute("PF('RegistroClientes').hide()");
                Conexion.Conexion.CloseCon();
            }
            if (OkValidar == false) {
                RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".registrarClientes()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpa todas las variables utilizadas en el
     * registro/modificacion de los clientes cliente desde el dialoog
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void limpiarCajasRegistroCli() {
        try {
            codTipDoc = "0";
            numDocumento = "";
            numDocumentoOK = "";
            confir_numDocumento = "";
            nombreCliente = "";
            direccionCliente = "";
            direccionClienteOK = "";
            confir_direccionCliente = "";
            infoCorreocliente = false;
            emailCliente = "";
            confir_emailCliente = "";
            telefonoCl = "";
            codDeptoCli = "0";
            codCiudCli = "0";
            codTipDoc = "0";
            codRegimenCli = "0";
            codTipRegimenCli = "0";
            estadoNumDocCliTemp = false;
            estadoRegistroClienteBtn = false;
            estadoActualizarClienteBtn = false;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCajasRegistroCli()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * (No se usa)
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     * @deprecated 15-02-2015
     */
    public void verificarInfo() {
        try {
            MBAval = new BeanAvaluo();
            if ("".equals(this.nombreCliente1) && "".equals(this.numeroDoccliente1)) {
                mbTodero.setMens("Debe llenar por lo menos un cliente solicitante");
                mbTodero.warn();
            } else {
                RequestContext.getCurrentInstance().execute("PF('DlgPreRadicacion').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCajasRegistroCli()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que ingresa informacion a las cajas de texto para registrar el
     * cliente desde el dialoog
     *
     * @author Jhojan Stiven Rodriguez
     * @param fila int que contiene el numero de fila de cliente para saber que
     * mail de cliente consultar
     * @since 01-05-2015
     */
    public void cargarCorreosCliente(int fila) {
        try {
            if (fila == 1) {
                correoCliente1 = CargaListaClientesRadic.get(0).getMailCliente();
            }
            if (fila == 2) {
                correoCliente2 = CargaListaClientesRadic.get(1).getMailCliente();
            }
            if (fila == 3) {
                correoCliente3 = CargaListaClientesRadic.get(2).getMailCliente();
            }
            if (fila == 4) {
                correoCliente4 = CargaListaClientesRadic.get(3).getMailCliente();
            }
            if (fila == 5) {
                correoCliente5 = CargaListaClientesRadic.get(4).getMailCliente();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarCorreosCliente()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta la informacion de los propietarios dentro de los form
     * de entrega > impresion
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void consulPropietarios() {
        try {
            mbTodero.resetTable("FRMImpAva:DatPropiet");
            this.ListPropietarios = new ArrayList<>();
            this.ListPropietarios = Cli.ConsultaPropietarios();
            RequestContext.getCurrentInstance().execute("PF('DlgPropiet').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consulPropietarios()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void Limpiar() {
        setValidarClienteTempo(false);
        setEstadoTarifasPactCli1(false);
        setEstadoTarifasPactCli2(false);
        setEstadoTarifasPactCli3(false);
        setEstadoTarifasPactCli4(false);
        setEstadoTarifasPactCli5(false);
        setEstadoTarifasCliTemp(false);
        setEstadoAgregarTarifasPactCli1(false);
        setEstadoAgregarTarifasPactCli2(false);
        setEstadoAgregarTarifasPactCli3(false);
        setEstadoAgregarTarifasPactCli4(false);
        setEstadoAgregarTarifasPactCli5(false);
        setTipoTarifaCli("");
        setTipoTarifaCli1("");
        setTipoTarifaCli2("");
        setTipoTarifaCli3("");
        setTipoTarifaCli4("");
        setTipoTarifaCli5("");
        setNombreCliente("");
        setNombreCliente1("");
        setNombreCliente2("");
        setNombreCliente3("");
        setNombreCliente4("");
        setNombreCliente5("");
        setNumeroDoccliente1("");
        setNumeroDoccliente2("");
        setNumeroDoccliente3("");
        setNumeroDoccliente4("");
        setNumeroDoccliente5("");
        setTelefonoCl("");
        setTelefonoCliente1("");
        setTelefonoCliente2("");
        setTelefonoCliente3("");
        setTelefonoCliente4("");
        setTelefonoCliente5("");
        setValorTarifaCli("");
        setValorTarifaCli1("");
        setValorTarifaCli2("");
        setValorTarifaCli3("");
        setValorTarifaCli4("");
        setValorTarifaCli5("");
        setValorTarifaCli1("");
        setClientFact1(false);
        setClientFact2(false);
        setClientFact3(false);
        setClientFact4(false);
        setClientFact5(false);
        setEstadoTarifasPactCli5(false);
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
