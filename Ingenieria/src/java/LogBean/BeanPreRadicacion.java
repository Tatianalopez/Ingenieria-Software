package LogBean;

import Conexion.Conexion;
import Logic.LogAdministracion;
import Logic.LogAvaluo;
import Logic.LogCliente;
import Logic.LogEmpleado;
import Logic.LogEntidad;

import Logic.LogPermiso;
import java.util.List;
import javax.faces.bean.ManagedBean;
import Logic.LogPreRadicacion;

import static com.sun.faces.facelets.util.Path.context;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import javax.faces.event.AbortProcessingException; //GCH
import org.hibernate.criterion.Order;
import org.primefaces.component.api.UIData;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.FilterEvent;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de la pre-radicacion realizada
 * dentro del flujo de proceso, la cual consiste en una validación realizada a
 * la informacion que sea ingresada de una solicud de avaluo, donde se verifica
 * la existencia de un avaluo anterior, si el cliente ya ha solicitado avaluos
 * anteriores y el estado de el cliente o entidad con referente a la
 * organizacion, con esta informacion se da paso a la informacion que se tiene
 * en cuenta en la radicación del avalúo </b></p>
 *
 * @author ISA INMOBILIARIA LTDA.
 * @version 2.0.0
 * @since 15-11-2014 1.0.0
 * @author Jojhan Stiven Doncel Rodriguez
 * @author Maira Alejandra Carvajal Chaparro
 *
 */
@ManagedBean(name = "MBPreRadicacion")
@ViewScoped
@SessionScoped
public class BeanPreRadicacion {

    /**
     * Variables utilizadas para todo el proceso de la creacion de un registro
     * de solicitud de avalúo
     *
     */
    /**
     * Solicitud*
     */
    Date FecActual;
    private Date FechaSol = this.FecActual;
    private Date HoraSol;
    private String HoraSolisitud;
    private Integer CodEmp;
    private String NombreAnalista;

    /**
     * Cliente y bien*
     */
    private int cod_cliente;
    private String nombreDeptoBien;
    private String nombreCiudadBien;
    private boolean estadoBotonEstudio;

    private boolean estadotabsPreRadica;

    /**
     * Anulaciones e impedimentos de la solicitud*
     */
    private int codMotivoAnulacionPreRadica;
    private int codMotivoAnulacionPreRadicaRadica;
    private String ObserCambioEstadoPreRadica;
    private String ObserCambioEstadoPreRadicaRadica;
    private String ObserCambioEstadoImpedir;
    private String ObserCambioEstadoImpedirDesdePreRad;

    /**
     * Numeros de registros exixtentes, opcion de ejecucion de proceso*
     */
    private String opcionRadioEstados;
    private ResultSet DatObser;
    private boolean preRadicacionModificacion;
    private boolean preRadicacionRegistro;
    private ResultSet DatCampoEnviarA;
    private int NumeroRegistrosAsig;
    private int NumeroMisPreradicaciones;
    private int NumeroTotalPreRadicaciones;

    /**
     * Variables de validacion de paneles, radios, para visibilidad de campos de
     * cliente solicitante y entidad *
     */
    /**
     * Variables de validacion para los paneles, para la visibilidad en las
     * Tarifas a Pactar de la PreRadicacion*
     *
     */
    private String opcionRadioPersonFact;
    private boolean estadoPanelClientPreRadi = false;

    private String opcionRadioPerito;
    private boolean estadoPanelPeritoPreRad = false;
    private boolean estadoPanelEntidadPreRad = false;

    /**
     * Estado para los procesos que va a realizar el gestor*
     */
    private boolean estadoActivacionGestor = false;

    /**
     * Estado para los procesos que puede realizar mientras tenga rol Radicador*
     */
    private boolean estadoActivacionRadicador = false;

    /**
     * Cliente solicitante, variables que guardan la informacion de clientes
     * agregados*
     */
    private String cod_preRadica;
    private String opcionRadioEstudio;

    /**
     * CValida que el estuido de pre-radicación se haya realizado*
     */
    private boolean siEstudio;
    boolean botondem = true;

    /**
     * Persona a Facturar,variables que guardan la informacion de Clientes
     * agregados*
     */
    private boolean RequiereEntidad = false;
    private boolean RequiereUbicacion = false;
    private boolean Ubicacion = false;
    private boolean ExisteInformacion = false;

    private String OtroQuienEnviarA;
    private String OtroUbicacionEnviarA;
    private String OtroDireccionEnviarA;
    private String detalleEnviarAotro;
    private String EnviarALabel;
    private boolean estadoCajasEnviarA = false;

    private ArrayList<SelectItem> CargEnviarA = new ArrayList<>();
    private List<LogAdministracion> ListaArcRegla = null;

    private boolean Cotizacion = false;

    /**
     * Clase del bean avaluo*
     */
    /**
     * Clase del log cliente*
     */
    LogCliente Cli;

    /**
     * tipo de fila seleccionada*
     */
    private int tipo;

    /**
     * Nombre del Tipo de Avaluo*
     */
    private String NomTipAvaluo;

    /**
     * Lista que carga los clientes*
     */
    private List<LogCliente> ListClient = null;

    /**
     * Variables Funcionario*
     */
    private boolean EstadoFuncionario1;
    private boolean EstadoFuncionario2;
    private boolean EstadoFuncionario3;
    private boolean EstadoFuncionario4;
    private boolean EstadoFuncionario5;

    /**
     * Variables de Texto en el dialogo segun la Entidad a facturar*
     */
    private boolean TipoTitulEnt1;
    private boolean TipoTitulEnt2;
    private boolean TipoTitulEnt3;
    private boolean TipoTitulEnt4;
    private boolean TipoTitulEnt5;

    /**
     * Variables para ocultar/mostar paneles del dialog*
     */
    private boolean estadoPanelDialogSolicitante = false;
    private boolean estadoPanelDialogClienteFacturar = false;
    private boolean estadoPanelDialogEntidad = false;
    private boolean estadoPanelDialogEntidadfactu = false;
    private boolean estadoPanelDialogTempBien = false;

    /**
     * Variables para ocultar/mostrar paneles PreRadicados Dialog Ver
     * Informacion*
     */
    private boolean estadoPreRadicaDialogSolicitantesVI = false;
    private boolean estadoPreRadicaDialogPersonFacVI = false;
    private boolean estadoPreRadicaDialogEntidVI = false;
    private boolean estadoPreRadicaDialogAvaluoPredVI = false;
    private boolean estadoPreRadicaDialogAvaluoMaqVI = false;
    private boolean estadoPreRadicaDialogAvaluoEnseVI = false;
    private boolean estadoPreRadicaDialogAvaluoTempVI = false;

    /**
     * Variables para ocultar/mostrar paneles PreRadicados Dialog Generar
     * Impresion*
     */
    private boolean estadoPreRadicaDialogSolicitantesGI = false;
    private boolean estadoPreRadicaDialogPersonFacGI = false;
    private boolean estadoPreRadicaDialogEntidGI = false;
    private boolean estadoPreRadicaDialogAvaluoGI = false;
    private boolean estadoPreRadicaDialogAvaluoPredGI = false;
    private boolean estadoPreRadicaDialogAvaluoMaqGI = false;
    private boolean estadoPreRadicaDialogAvaluoEnseGI = false;
    private boolean estadoPreRadicaDialogAvaluoTempGI = false;

    /**
     * Variables para ocultar/mostrar paneles PreRadicados Dialog Asignacion
     * Perito*
     */
    /**
     * Variables para ocultar/mostrar paneles PreRadicados Dialog Registrar
     * Valores Pactados*
     */
    private boolean estadoPreRadicaDialogClienteVP = false;
    /**
     * En el caso de las personas a facturar que se hayan registrado
     * anteriormente*
     */

    private boolean estadoPreRadicaDialogPeritoVP = false;
    private boolean estadoPreRadicaDialogEntidadVP = false;

    /**
     * Variable para Entidad a Facturar*
     */
    private boolean estadoPreRadicaEntidadFac = false;
    private String FacturarEnti;

    /**
     * Variable para el envio de carta a el perito*
     */
    private String EnvCartaPerito;
    /**
     * Variables para el tipo de tarifa a aplicar a la Entidad*
     */
    private String TarifaEntidad;
    private String ValorTarifEntidad;

    /**
     * Variables para Informacion de Perito*
     */
    /**
     * Perito fila 1*
     */
    private String numeroDocPerito1;
    private String nombrePerito1;
    private String apellidoPerito1;

    /**
     * Perito fila 2*
     */
    private String numeroDocPerito2;
    private String nombrePerito2;
    private String apellidoPerito2;

    /**
     * Perito fila 3*
     */
    private String numeroDocPerito3;
    private String nombrePerito3;
    private String apellidoPerito3;

    /**
     * Perito fila 4*
     */
    private String numeroDocPerito4;
    private String nombrePerito4;
    private String apellidoPerito4;

    /**
     * Perito fila 5*
     */
    private String numeroDocPerito5;
    private String nombrePerito5;
    private String apellidoPerito5;

    private boolean estadoOpcionAsignado = false;

    /**
     * Variables para los valores pactados de los clientes a facturar*
     */
    private boolean estadoPreRadicaPerFacVP1 = false;
    private boolean estadoPreRadicaPerFacVP2 = false;
    private boolean estadoPreRadicaPerFacVP3 = false;
    private boolean estadoPreRadicaPerFacVP4 = false;
    private boolean estadoPreRadicaPerFacVP5 = false;
    private String TarifaPerFacVP1;
    private String ValorTarifPerFacVP1;
    private String TarifaPerFacVP2;
    private String ValorTarifPerFacVP2;
    private String TarifaPerFacVP3;
    private String ValorTarifPerFacVP3;
    private String TarifaPerFacVP4;
    private String ValorTarifPerFacVP4;
    private String TarifaPerFacVP5;
    private String ValorTarifPerFacVP5;

    /**
     * Variables para los valores pactados para los peritos*
     */
    private boolean estadoPreRadicaPeritoVP1 = false;
    private boolean estadoPreRadicaPeritoVP2 = false;
    private boolean estadoPreRadicaPeritoVP3 = false;
    private boolean estadoPreRadicaPeritoVP4 = false;
    private boolean estadoPreRadicaPeritoVP5 = false;
    private String TarifaPeritoVP1;
    private String ValorTarifPeritoVP1;
    private String TarifaPeritoVP2;
    private String ValorTarifPeritoVP2;
    private String TarifaPeritoVP3;
    private String ValorTarifPeritoVP3;
    private String TarifaPeritoVP4;
    private String ValorTarifPeritoVP4;
    private String TarifaPeritoVP5;
    private String ValorTarifPeritoVP5;

    private String ObservaRegistro = "";
    private String ObservaPreRadInicio = "";
    private String ObservaPreRadGeneral = "";

    private String ObservaPreRad = "";
    private String CodAnalista;
    private ArrayList<SelectItem> CargaAnali = new ArrayList<>();
    String opcionConsul;

    /*Variables de deshabilitacion de botones*/
    private boolean habilitarBotonPreRadica;

    /**
     * Variables tipo List para cagar las consultas que se realicen a la base de
     * datos*
     */
    private List<LogPreRadicacion> ListPreRadicados = null;
    private List<LogPreRadicacion> ListPreRadicadosGestor = null;
    private List<LogPreRadicacion> ListPreRadicaAsignados = null;
    private List<LogPreRadicacion> ListPreRadicaSeleccAsignados = null;
    private List<LogPreRadicacion> ListEmpAsignaciones = null;
    private List<LogPreRadicacion> ListMisAsignados = null;
    private List<LogPreRadicacion> ListAllMisAsignados = null;
    private List<LogPreRadicacion> ListAllMisAsignadosRadicacion = null;
    private List<LogPreRadicacion> ListMisAginadosPreRadica = null;
    private List<LogPreRadicacion> ListMisPreRadicaciones = null;
    private List<LogPreRadicacion> ListMisAginadosPreRad = null;
    private List<LogPreRadicacion> ListObserPreRadicados = null;


    private String tamañoTablas;

    /**
     * Variables para formatos de fechas y horas*
     */
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat FormatHora = new SimpleDateFormat("HH:mm");

    /**
     * Resultset*
     */
    ResultSet Dat = null;
    ResultSet Tab = null;

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogEntidad Ent = new LogEntidad();
    LogAdministracion Adm = new LogAdministracion();

    LogAvaluo Ava = new LogAvaluo();
    LogPreRadicacion PreRad = new LogPreRadicacion();
    LogPreRadicacion PreRadi = new LogPreRadicacion();
    LogPreRadicacion PreRadica = new LogPreRadicacion();
    LogEmpleado Emp = new LogEmpleado();
    LogPreRadicacion Emple = new LogPreRadicacion();
    LogPreRadicacion preRadRegiMisAsig = new LogPreRadicacion();
    LogPreRadicacion preRadMisPreRadic = new LogPreRadicacion();

    LogPermiso Perm = new LogPermiso();

    String fechaSoli;
    String CodigoCliente;
    String fecha_actual;
    private Date FecSolicitudPreRad;

    private List<LogPreRadicacion> ListPreMisAsig = null;

    public List<LogPreRadicacion> getListPreMisAsig() {
        return ListPreMisAsig;
    }

    public void setListPreMisAsig(List<LogPreRadicacion> ListPreMisAsig) {
        this.ListPreMisAsig = ListPreMisAsig;
    }

    /**
     * Metodos de /** FIN Metodos get y set de todas las variables / atributos,
     * listas, etc que se utilizaran para enviar y traer datos a las respectivas
     * variables ---funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo que verifica la fecha (solicitud)
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void verificarFecha() {
        //JOptionPane.showMessageDialog(null, "Sie ntra despues de dar click");
        mbTodero.setMens("Si entra despues de dar click");
        mbTodero.info();
    }

    /**
     * Metodo que compara las fecha de solicitud de Pre-Radicacion con la fecha
     * actual
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param op int que permite comparar los valores asi: 1: compara la fecha
     * 2: compara la hora
     * @since 01-11-2014
     */
    public void verifiFec(int op) {
        try {
            if (op == 1) {
                Calendar FecActuall = Calendar.getInstance();
                Date infor = FecActuall.getTime();
                if (!FechaSol.before(infor)) {
                    mbTodero.setMens("La fecha de solicitud no puede ser mayor a la fecha actual");
                    mbTodero.warn();
                } else {
                    fechaSoli = Format.format(FechaSol);
                }
            } else if (op == 2) {
                Calendar HoraActual = Calendar.getInstance();
                Date HoraInfo = HoraActual.getTime();
                if (!HoraSol.before(HoraInfo)) {
                    mbTodero.setMens("La Hora de solicitud no puede ser mayor a la Hora actual");
                    mbTodero.warn();
                } else {
                    HoraSolisitud = FormatHora.format(HoraSol);
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiFec()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cargar los 'enviar a' en la lista
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return ArrayList con la informacion de los enviar a
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulEnviarA() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.ConsulEnvio().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargEnviarA.add(new SelectItem(MBAdm.getCodigoParametro(), MBAdm.getNombreParametro()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulEnviarA()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargEnviarA;

    }

    /**
     * Metodo habilita y muestra los campos de enviar a cuando la persona a
     * enviar el avaluo es 'OTRO'
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void mostrarCamposEnviarA() {
        try {
            DatCampoEnviarA = PreRad.ConsulEnviarANombre(PreRad.getEnvioInformacion());
            if (DatCampoEnviarA.next()) {
                EnviarALabel = DatCampoEnviarA.getString("Resultado_Parametro");
                estadoCajasEnviarA = "OTRO".equals(DatCampoEnviarA.getString("Resultado_Parametro")) || "Otro".equals(DatCampoEnviarA.getString("Resultado_Parametro")) || "otro".equals(DatCampoEnviarA.getString("Resultado_Parametro")) || "Otra persona".equals(DatCampoEnviarA.getString("Resultado_Parametro"));
            }
            Conexion.CloseCon();
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarCamposEnviarA()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodos de validacion de radios y visibilidad de campos de cliente
     * solicitante y entidades ---------------Peritos y Personas a Facturar una
     * vez se encuentre PreRadicados, carga/oculta el panel de acuerdo a la
     * opcion seleccionada
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param tipo_panel
     * @since 01-11-2014
     */
    public void cargarPaneles(int tipo_panel) {
        try {
            if (tipo_panel == 1) {
                switch (mBCliente.getOpcionMostrarCliSolicitante()) {
                    case "Si":
                        mBCliente.setEstadoPanelClienteGeneral(true);
                        borrarInfoCliente("Seleccion");
                        break;
                    case "No":
                    case "":
                        mBCliente.setEstadoPanelClienteGeneral(false);
                        borrarInfoCliente("Seleccion");
                        break;
                }
            } else if (tipo_panel == 2) {
                switch (mBEntidad.getOpcionMostrarEntidad()) {
                    case "Si1":
                        this.mBEntidad.setEstPanelEntidad(true);
                        limpiarEntidades("Seleccion");
                        break;
                    case "No1":
                    case "1":

                        this.mBEntidad.setEstPanelEntidad(false);
                        limpiarEntidades("Todo");
                        break;
                }
                /*
                crear un switch para recargar la fila que corresponde al codref de la clase BeanEntidad
                 */
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarPaneles()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga lo relacionado al cliente que fue seleccionado en la
     * busqueda)
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void cargarInfo() {
        try {
            if (mBCliente.getCli() == null) {
                mbTodero.setMens("Debe seleccionar el cliente a agregar");
                mbTodero.warn();
            } else //agregar datos en cajas de cliente solicitante            
            {
                if (tipo == 1) {
                    mBCliente.setMensajeConsultaCliente1("Ok");
                    mBCliente.setNombreCliente1(mBCliente.getCli().getNombreCliente());
                    mBCliente.setNumeroDoccliente1(mBCliente.getCli().getNumeroDoccliente());
                    mBCliente.setTelefonoCliente1(mBCliente.getCli().getTelefonoCliente());
                    RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");
                    mBCliente.setEstadoConsultaCliente1(true);

                    mBCliente.setFila1(true);
                    mBCliente.setEditar1(true);
                    mBCliente.setAnalizar1(false);
                    mBCliente.setQuitar1(false);

                    if (mBCliente.isEstadocajasAgregarF1() == false) {
                        mBCliente.setOtro1(true);
                    } else {
                        mBCliente.setOtro1(false);
                    }

                } else if (tipo == 2) {
                    if (mBCliente.getNumeroDoccliente1().equals(mBCliente.getCli().getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        mBCliente.setMensajeConsultaCliente2("Ok");
                        mBCliente.setNombreCliente2(mBCliente.getCli().getNombreCliente());
                        mBCliente.setNumeroDoccliente2(mBCliente.getCli().getNumeroDoccliente());
                        mBCliente.setTelefonoCliente2(mBCliente.getCli().getTelefonoCliente());
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");
                        mBCliente.setEstadoConsultaCliente2(true);

                        mBCliente.setFila2(true);
                        mBCliente.setEditar2(true);
                        mBCliente.setAnalizar2(false);
                        mBCliente.setQuitar2(false);

                        if (mBCliente.isEstadocajasAgregarF2() == false) {
                            mBCliente.setOtro2(true);
                        } else {
                            mBCliente.setOtro2(false);
                        }
                    }
                } else if (tipo == 3) {
                    if (mBCliente.getNumeroDoccliente1().equals(mBCliente.getCli().getNumeroDoccliente()) || mBCliente.getNumeroDoccliente2().equals(mBCliente.getCli().getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        mBCliente.setMensajeConsultaCliente3("Ok");
                        mBCliente.setNombreCliente3(mBCliente.getCli().getNombreCliente());
                        mBCliente.setNumeroDoccliente3(mBCliente.getCli().getNumeroDoccliente());
                        mBCliente.setTelefonoCliente3(mBCliente.getCli().getTelefonoCliente());
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");
                        mBCliente.setEstadoConsultaCliente3(true);

                        mBCliente.setFila3(true);
                        mBCliente.setEditar3(true);
                        mBCliente.setAnalizar3(false);
                        mBCliente.setQuitar3(false);

                        if (mBCliente.isEstadocajasAgregarF3() == false) {
                            mBCliente.setOtro3(true);
                        } else {
                            mBCliente.setOtro3(false);
                        }
                    }
                } else if (tipo == 4) {
                    if (mBCliente.getNumeroDoccliente1().equals(mBCliente.getCli().getNumeroDoccliente()) || mBCliente.getNumeroDoccliente2().equals(mBCliente.getCli().getNumeroDoccliente())
                            || mBCliente.getNumeroDoccliente3().equals(mBCliente.getCli().getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        mBCliente.setMensajeConsultaCliente4("Ok");
                        mBCliente.setNombreCliente4(mBCliente.getCli().getNombreCliente());
                        mBCliente.setNumeroDoccliente4(mBCliente.getCli().getNumeroDoccliente());
                        mBCliente.setTelefonoCliente4(mBCliente.getCli().getTelefonoCliente());
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");
                        mBCliente.setEstadoConsultaCliente4(true);

                        mBCliente.setFila4(true);
                        mBCliente.setEditar4(true);
                        mBCliente.setAnalizar4(false);
                        mBCliente.setQuitar4(false);

                        if (mBCliente.isEstadocajasAgregarF4() == false) {
                            mBCliente.setOtro4(true);
                        } else {
                            mBCliente.setOtro4(false);
                        }
                    }
                } else if (tipo == 5) {
                    if (mBCliente.getNumeroDoccliente1().equals(mBCliente.getCli().getNumeroDoccliente()) || mBCliente.getNumeroDoccliente2().equals(mBCliente.getCli().getNumeroDoccliente())
                            || mBCliente.getNumeroDoccliente3().equals(mBCliente.getCli().getNumeroDoccliente()) || mBCliente.getNumeroDoccliente4().equals(mBCliente.getCli().getNumeroDoccliente())) {
                        mbTodero.setMens("El cliente seleccionado ya se encuentra agregado");
                        mbTodero.warn();
                    } else {
                        mBCliente.setMensajeConsultaCliente5("Ok");
                        mBCliente.setNombreCliente5(mBCliente.getCli().getNombreCliente());
                        mBCliente.setNumeroDoccliente5(mBCliente.getCli().getNumeroDoccliente());
                        mBCliente.setTelefonoCliente5(mBCliente.getCli().getTelefonoCliente());
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').hide()");
                        mBCliente.setEstadoConsultaCliente5(true);

                        mBCliente.setFila5(true);
                        mBCliente.setEditar5(true);
                        mBCliente.setAnalizar5(false);
                        mBCliente.setQuitar5(false);

                    }
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que agrega una nueva fila de cajas y componentes para agregar otro
     * cliente
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param tipo int que contiene el numero de la fila
     * @param proceso String que define el proceso que se esta manejando,
     * preradicacion, registro, radicacion
     * @since 01-11-2014
     */
    public void agregarCajas(int tipo, String proceso) {
        this.tipo = tipo;
        try {
            if (tipo == 1) {
                if ("".equals(mBCliente.getNombreCliente1()) || "".equals(mBCliente.getNumeroDoccliente1()) || "".equals(mBCliente.getTelefonoCliente1())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else if (!"Registro".equals(proceso) && "".equals(mBCliente.getMensajeConsultaCliente1())) {
                    mbTodero.setMens("Debe analizar la información del cliente 1");
                    mbTodero.warn();
                } else {
                    mBCliente.setEstadocajasAgregarF1(true);

                    mBCliente.setFila1(true);
                    mBCliente.setFila2(false);

                    mBCliente.setAnalizar1(false);
                    mBCliente.setAnalizar2(true);

                    mBCliente.setEditar1(true);

                    mBCliente.setQuitar1(false);

                    mBCliente.setQuitar2(true);

                    mBCliente.setAceptar2(true);

                    mBCliente.setOtro1(false);
                    mBCliente.setOtro2(mBCliente.isEstadocajasAgregarF2() != true);

                }
            } //si la tabla de maquinaria  tiene inforfmacion la va a habilitar
            else if (tipo == 2) {
                if ("".equals(mBCliente.getNombreCliente2()) || "".equals(mBCliente.getNumeroDoccliente2()) || "".equals(mBCliente.getTelefonoCliente2())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else if (!"Registro".equals(proceso) && "".equals(mBCliente.getMensajeConsultaCliente2())) {
                    mbTodero.setMens("Debe analizar la informacion del cliente 1");
                    mbTodero.warn();
                } else {
                    mBCliente.setEstadocajasAgregarF2(true);

                    mBCliente.setFila2(true);
                    mBCliente.setFila3(false);

                    mBCliente.setAnalizar2(false);
                    mBCliente.setAnalizar3(true);

                    mBCliente.setEditar2(true);

                    mBCliente.setQuitar2(false);
                    mBCliente.setQuitar3(true);

                    mBCliente.setOtro2(false);
                    mBCliente.setOtro3(mBCliente.isEstadocajasAgregarF3() != true);

                    mBCliente.setAceptar3(true);

                }
            } else if (tipo == 3) {
                if ("".equals(mBCliente.getNombreCliente3()) || "".equals(mBCliente.getNumeroDoccliente3()) || "".equals(mBCliente.getTelefonoCliente3())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else if (!"Registro".equals(proceso) && "".equals(mBCliente.getMensajeConsultaCliente3())) {
                    mbTodero.setMens("Debe analizar la informacion del cliente 3");
                    mbTodero.warn();
                } else {

                    mBEntidad.setEstPanelEntidad(true);

                    mBCliente.setEstadocajasAgregarF3(true);

                    mBCliente.setFila3(true);
                    mBCliente.setFila4(false);

                    mBCliente.setAnalizar3(false);
                    mBCliente.setAnalizar4(true);

                    mBCliente.setEditar3(true);

                    mBCliente.setQuitar3(false);
                    mBCliente.setQuitar4(true);

                    mBCliente.setOtro3(false);
                    mBCliente.setOtro4(mBCliente.isEstadocajasAgregarF4() != true);

                    mBCliente.setAceptar4(true);

                }
            } else if (tipo == 4) {
                if ("".equals(mBCliente.getNombreCliente4()) || "".equals(mBCliente.getNumeroDoccliente4()) || "".equals(mBCliente.getTelefonoCliente4())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else if (!"Registro".equals(proceso) && "".equals(mBCliente.getMensajeConsultaCliente4())) {
                    mbTodero.setMens("Debe analizar la informacion del cliente 4");
                    mbTodero.warn();
                } else {
                    mBCliente.setEstadocajasAgregarF4(true);

                    mBCliente.setFila4(true);
                    mBCliente.setFila5(false);

                    mBCliente.setAnalizar4(false);
                    mBCliente.setAnalizar5(true);

                    mBCliente.setEditar4(true);

                    mBCliente.setQuitar4(false);
                    mBCliente.setQuitar5(true);

                    mBCliente.setOtro4(false);

                    mBCliente.setAceptar5(true);

                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarCajas()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que quita una fila de cajas y componentes en la opcion de agregar
     * otro cliente
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param fila int que contiene el numero de fila a quitar
     * @since 01-11-2014
     */
    public void quitarCajas(int fila) {
        try {
            switch (fila) {
                case 1:

                    mBCliente.setClientFact1(false);
                    mBCliente.setFuncionario1(false);
                    mBCliente.setNumeroDoccliente1("");
                    mBCliente.setNombreCliente1("");
                    mBCliente.setTelefonoCliente1("");
                    mBCliente.setMensajeConsultaCliente1("");
                    mBCliente.setEstadoBtnRegistroF1(false);
                    mBCliente.setEstadoConsultaCliente1(false);
                    mBCliente.setEstadoAgregarTarifasPactCli1(false);
                    mBCliente.setEstadoTarifasPactCli1(false);
                    mBCliente.setOpcionRadioTarifasPactadosCli1("");
                    mBCliente.setValorTarifaCli1("");
                    mBCliente.setTipoTarifaCli1("");
                    break;
                case 2:
                    mBCliente.setEstadocajasAgregarF1(false);
                    mBCliente.setClientFact2(false);
                    mBCliente.setFuncionario2(false);
                    mBCliente.setNumeroDoccliente2("");
                    mBCliente.setNombreCliente2("");
                    mBCliente.setTelefonoCliente2("");
                    mBCliente.setMensajeConsultaCliente2("");
                    mBCliente.setEstadoBtnRegistroF2(false);
                    mBCliente.setEstadoConsultaCliente2(false);
                    mBCliente.setEstadoAgregarTarifasPactCli2(false);
                    mBCliente.setEstadoTarifasPactCli2(false);
                    mBCliente.setOpcionRadioTarifasPactadosCli2("");
                    mBCliente.setValorTarifaCli2("");
                    mBCliente.setTipoTarifaCli2("");
                    mBCliente.setFila1(false);
                    mBCliente.setEditar1(true);
                    mBCliente.setAnalizar1(false);
                    mBCliente.setOtro1(true);
                    mBCliente.setQuitar1(false);
                    mBCliente.setEditar2(false);
                    mBCliente.setAnalizar2(false);
                    mBCliente.setAceptar2(false);
                    mBCliente.setOtro2(false);
                    mBCliente.setQuitar2(false);
                    mBCliente.setClientFact2(false);
                    break;
                case 3:
                    mBCliente.setEstadocajasAgregarF2(false);
                    mBCliente.setClientFact3(false);
                    mBCliente.setFuncionario3(false);
                    mBCliente.setNumeroDoccliente3("");
                    mBCliente.setNombreCliente3("");
                    mBCliente.setTelefonoCliente3("");
                    mBCliente.setMensajeConsultaCliente3("");
                    mBCliente.setEstadoBtnRegistroF3(false);
                    mBCliente.setEstadoConsultaCliente3(false);
                    mBCliente.setEstadoAgregarTarifasPactCli3(false);
                    mBCliente.setEstadoTarifasPactCli3(false);
                    mBCliente.setOpcionRadioTarifasPactadosCli3("");
                    mBCliente.setValorTarifaCli3("");
                    mBCliente.setTipoTarifaCli3("");
                    mBCliente.setFila2(false);
                    mBCliente.setEditar2(true);
                    mBCliente.setAnalizar2(false);
                    mBCliente.setOtro2(true);
                    mBCliente.setQuitar2(false);
                    mBCliente.setEditar3(false);
                    mBCliente.setAnalizar3(false);
                    mBCliente.setAceptar3(false);
                    mBCliente.setOtro3(false);
                    mBCliente.setQuitar3(false);
                    mBCliente.setClientFact3(false);
                    break;
                case 4:
                    mBCliente.setEstadocajasAgregarF3(false);
                    mBCliente.setClientFact4(false);
                    mBCliente.setFuncionario4(false);
                    mBCliente.setNumeroDoccliente4("");
                    mBCliente.setNombreCliente4("");
                    mBCliente.setTelefonoCliente4("");
                    mBCliente.setMensajeConsultaCliente4("");
                    mBCliente.setEstadoBtnRegistroF4(false);
                    mBCliente.setEstadoConsultaCliente4(false);
                    mBCliente.setEstadoAgregarTarifasPactCli4(false);
                    mBCliente.setEstadoTarifasPactCli4(false);
                    mBCliente.setOpcionRadioTarifasPactadosCli4("");
                    mBCliente.setValorTarifaCli4("");
                    mBCliente.setTipoTarifaCli4("");
                    mBCliente.setFila3(false);
                    mBCliente.setEditar3(true);
                    mBCliente.setAnalizar3(false);
                    mBCliente.setOtro3(true);
                    mBCliente.setQuitar3(false);
                    mBCliente.setEditar4(false);
                    mBCliente.setAnalizar4(false);
                    mBCliente.setAceptar4(false);
                    mBCliente.setOtro4(false);
                    mBCliente.setQuitar4(false);
                    mBCliente.setClientFact4(false);
                    break;
                case 5:
                    mBCliente.setEstadocajasAgregarF4(false);
                    mBCliente.setClientFact5(false);
                    mBCliente.setFuncionario5(false);
                    mBCliente.setNumeroDoccliente5("");
                    mBCliente.setNombreCliente5("");
                    mBCliente.setTelefonoCliente5("");
                    mBCliente.setMensajeConsultaCliente5("");
                    mBCliente.setEstadoBtnRegistroF5(false);
                    mBCliente.setEstadoConsultaCliente5(false);
                    mBCliente.setEstadoAgregarTarifasPactCli5(false);
                    mBCliente.setEstadoTarifasPactCli5(false);
                    mBCliente.setOpcionRadioTarifasPactadosCli5("");
                    mBCliente.setValorTarifaCli5("");
                    mBCliente.setTipoTarifaCli5("");
                    mBCliente.setFila4(false);
                    mBCliente.setEditar4(true);
                    mBCliente.setAnalizar4(false);
                    mBCliente.setOtro4(true);
                    mBCliente.setQuitar4(false);
                    mBCliente.setEditar5(false);
                    mBCliente.setAnalizar5(false);
                    mBCliente.setAceptar5(false);
                    mBCliente.setQuitar5(false);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".quitarCajas()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite editar una fila de cajas y componentes en la opcion de
     * agregar otro cliente
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param cajas int que contiene el numero de fila/cajas a editar
     * @param proceso int que contiene el proceso que se utilizara asi: registro
     * 1, pre-radicacion 1, radicacion 2
     * @since 01-11-2014
     */
    public void editar_cajas(int cajas, int proceso) {
        try {

            if (proceso == 2) {
                if (cajas == 1) {

                    mBCliente.setAnalizar1(true);
                    mBCliente.setEditar1(false);

                } else if (cajas == 2) {

                    mBCliente.setAnalizar2(true);
                    mBCliente.setEditar2(false);

                } else if (cajas == 3) {

                    mBCliente.setAnalizar3(true);
                    mBCliente.setEditar3(false);

                } else if (cajas == 4) {

                    mBCliente.setAnalizar4(true);
                    mBCliente.setEditar4(false);

                } else if (cajas == 5) {

                    mBCliente.setAnalizar5(true);
                    mBCliente.setEditar5(false);
                }
            } else if (cajas == 1) {

                mBCliente.setAnalizar1(true);
                mBCliente.setAceptar1(true);
                mBCliente.setEditar1(false);
                mBCliente.setQuitar1(true);

                mBCliente.setFila1(false);
                mBCliente.setFila2(true);
                mBCliente.setFila3(true);
                mBCliente.setFila4(true);
                mBCliente.setFila5(true);

            } else if (cajas == 2) {

                mBCliente.setAnalizar2(true);
                mBCliente.setAceptar2(true);
                mBCliente.setEditar2(false);
                mBCliente.setQuitar2(true);

                mBCliente.setFila1(true);
                mBCliente.setFila2(false);
                mBCliente.setFila3(true);
                mBCliente.setFila4(true);
                mBCliente.setFila5(true);

            } else if (cajas == 3) {

                mBCliente.setAnalizar3(true);
                mBCliente.setAceptar3(true);
                mBCliente.setEditar3(false);
                mBCliente.setQuitar3(true);

                mBCliente.setFila1(true);
                mBCliente.setFila2(true);
                mBCliente.setFila3(false);
                mBCliente.setFila4(true);
                mBCliente.setFila5(true);

            } else if (cajas == 4) {

                mBCliente.setAnalizar4(true);
                mBCliente.setAceptar4(true);
                mBCliente.setEditar4(false);
                mBCliente.setQuitar4(true);

                mBCliente.setFila1(true);
                mBCliente.setFila2(true);
                mBCliente.setFila3(true);
                mBCliente.setFila4(false);
                mBCliente.setFila5(true);

            } else if (cajas == 5) {

                mBCliente.setAnalizar5(true);
                mBCliente.setAceptar5(true);
                mBCliente.setEditar5(false);
                mBCliente.setQuitar5(true);

                mBCliente.setFila1(true);
                mBCliente.setFila2(true);
                mBCliente.setFila3(true);
                mBCliente.setFila4(true);
                mBCliente.setFila5(false);

            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".editar_cajas()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite confirmar una fila de cajas y componentes en la opcion
     * de agregar otro cliente
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param cajanumero int que contiene el numero de fila/cajas a confirmar
     * @since 01-11-2014
     */
    public void confirmarCajas(int cajanumero) {
        this.tipo = cajanumero;
        try {
            //si la tabla de enseres o mubles tiene inforfmacion la va a habilitar
            if (cajanumero == 1) {
                if ("".equals(mBCliente.getNombreCliente1()) || "".equals(mBCliente.getNumeroDoccliente1()) || "".equals(mBCliente.getTelefonoCliente1())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else {
                    mbTodero.setMens("Datos modificados correctamente");
                    mbTodero.info();

                    mBCliente.setAnalizar1(false);
                    mBCliente.setAceptar1(false);
                    mBCliente.setQuitar1(false);
                    mBCliente.setEditar1(true);
                    mBCliente.setFila1(true);

                    if (mBCliente.isAnalizar5() == true) {
                        mBCliente.setFila5(false);
                    } else if (mBCliente.isAnalizar4() == true) {
                        mBCliente.setFila4(false);
                    } else if (mBCliente.isAnalizar3() == true) {
                        mBCliente.setFila3(false);
                    } else if (mBCliente.isAnalizar2() == true) {
                        mBCliente.setFila2(false);
                    }
                }
            } else if (cajanumero == 2) {
                if ("".equals(mBCliente.getNombreCliente2()) || "".equals(mBCliente.getNumeroDoccliente2()) || "".equals(mBCliente.getTelefonoCliente2())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else {
                    mbTodero.setMens("Datos modificados correctamente");
                    mbTodero.info();

                    mBCliente.setAnalizar2(false);
                    mBCliente.setAceptar2(false);
                    mBCliente.setQuitar2(false);
                    mBCliente.setEditar2(true);
                    mBCliente.setFila2(true);

                    if (mBCliente.isAnalizar5() == true) {
                        mBCliente.setFila5(false);
                    } else if (mBCliente.isAnalizar4() == true) {
                        mBCliente.setFila4(false);
                    } else if (mBCliente.isAnalizar3() == true) {
                        mBCliente.setFila3(false);
                    } else if (mBCliente.isAnalizar1() == true) {
                        mBCliente.setFila1(false);
                    }
                }
            } else if (cajanumero == 3) {
                if ("".equals(mBCliente.getNombreCliente3()) || "".equals(mBCliente.getNumeroDoccliente3()) || "".equals(mBCliente.getTelefonoCliente3())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else {
                    mbTodero.setMens("Datos modificados correctamente");
                    mbTodero.info();

                    mBCliente.setAnalizar3(false);
                    mBCliente.setAceptar3(false);
                    mBCliente.setQuitar3(false);
                    mBCliente.setEditar3(true);
                    mBCliente.setFila3(true);

                    if (mBCliente.isAnalizar5() == true) {
                        mBCliente.setFila5(false);
                    } else if (mBCliente.isAnalizar4() == true) {
                        mBCliente.setFila4(false);
                    } else if (mBCliente.isAnalizar2() == true) {
                        mBCliente.setFila2(false);
                    } else if (mBCliente.isAnalizar1() == true) {
                        mBCliente.setFila1(false);
                    }

                }
            } else if (cajanumero == 4) {
                if ("".equals(mBCliente.getNombreCliente4()) || "".equals(mBCliente.getNumeroDoccliente4()) || "".equals(mBCliente.getTelefonoCliente4())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else {
                    mbTodero.setMens("Datos modificados correctamente");
                    mbTodero.info();

                    mBCliente.setAnalizar4(false);
                    mBCliente.setAceptar4(false);
                    mBCliente.setQuitar4(false);
                    mBCliente.setEditar4(true);
                    mBCliente.setFila4(true);

                    if (mBCliente.isAnalizar5() == true) {
                        mBCliente.setFila5(false);
                    } else if (mBCliente.isAnalizar3() == true) {
                        mBCliente.setFila3(false);
                    } else if (mBCliente.isAnalizar2() == true) {
                        mBCliente.setFila2(false);
                    } else if (mBCliente.isAnalizar1() == true) {
                        mBCliente.setFila1(false);
                    }

                }
            } else if (cajanumero == 5) {
                if ("".equals(mBCliente.getNombreCliente5()) || "".equals(mBCliente.getNumeroDoccliente5()) || "".equals(mBCliente.getTelefonoCliente5())) {
                    mbTodero.setMens("Favor llene todos los campos requeridos de esta seccion");
                    mbTodero.warn();
                } else {
                    mbTodero.setMens("Datos modificados correctamente");
                    mbTodero.info();

                    mBCliente.setAnalizar5(false);
                    mBCliente.setAceptar5(false);
                    mBCliente.setQuitar5(false);
                    mBCliente.setEditar5(true);
                    mBCliente.setFila5(true);

                    if (mBCliente.isAnalizar4() == true) {
                        mBCliente.setFila4(false);
                    } else if (mBCliente.isAnalizar3() == true) {
                        mBCliente.setFila3(false);
                    } else if (mBCliente.isAnalizar2() == true) {
                        mBCliente.setFila2(false);
                    } else if (mBCliente.isAnalizar1() == true) {
                        mBCliente.setFila1(false);
                    }

                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".confirmarCajas()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que consulta los clientes segun los parametros ingresados
     * previamente
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param tipo int que contiene el numero de caja/fila en la que se va a
     * consultar
     * @since 01-11-2014
     */
    public void consultaCliente(int tipo) {
        try {
            this.tipo = tipo;
            Cli = new LogCliente();
            this.ListClient = null;
            //realiza la consulta por el parametro de las cajas de la fila 0
            if (tipo == 1) {
                if (("".equals(mBCliente.getNombreCliente1()) || mBCliente.getNombreCliente1() == null)
                        || ("".equals(mBCliente.getNumeroDoccliente1()) || mBCliente.getNumeroDoccliente1() == null)
                        || ("".equals(mBCliente.getTelefonoCliente1()) || mBCliente.getTelefonoCliente1() == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar al cliente");
                    mbTodero.warn();
                } else {
                    mbTodero.resetTable("PreRadica:ClTable");

                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(mBCliente.getNumeroDoccliente1(), mBCliente.getNombreCliente1(), mBCliente.getTelefonoCliente1(), 1);
                    mBCliente.setFilaRegistro(1);
                    if (this.ListClient.size() > 0) {
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        mBCliente.setMensajeConsultaCliente1("N/A");
                        mBCliente.setEstadoConsultaCliente1(true);
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones, por favor confirme datos y proceda a registrarlo");
                        mbTodero.info();
                        mBCliente.limpiarCajasRegistroCli();
                        mBCliente.setNumDocumento(mBCliente.getNumeroDoccliente1());
                        mBCliente.setNombreCliente(mBCliente.getNombreCliente1());
                        mBCliente.setTelefonoCl(mBCliente.getTelefonoCliente1());
                        mBCliente.contadDirecc = 1;
                        mBCliente.contadDocum = 1;
                        mBCliente.contadEmail = 1;
                        mBCliente.setEstadoActualizarClienteBtn(true);
                        mBCliente.setEstadoRegistroClienteBtn(false);

                        mBCliente.setEstadoNumDocCliTemp(true);
                        RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");

                    }
                }
            } else if (tipo == 2) {
                //realiza la consulta por el parametro de las cajas de la fila 1
                if (("".equals(mBCliente.getNombreCliente2()) || mBCliente.getNombreCliente2() == null)
                        || ("".equals(mBCliente.getNumeroDoccliente2()) || mBCliente.getNumeroDoccliente2() == null)
                        || ("".equals(mBCliente.getTelefonoCliente2()) || mBCliente.getTelefonoCliente2() == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar al cliente");
                    mbTodero.warn();
                } else {
                    mbTodero.resetTable("PreRadica:ClTable");

                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(mBCliente.getNumeroDoccliente2(), mBCliente.getNombreCliente2(), mBCliente.getTelefonoCliente2(), 1);
                    mBCliente.setFilaRegistro(2);
                    if (this.ListClient.size() > 0) {
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        mBCliente.setMensajeConsultaCliente2("N/A");
                        mBCliente.setEstadoConsultaCliente2(true);
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones, por favor confirme datos y proceda a registrarlo");
                        mbTodero.info();
                        mBCliente.limpiarCajasRegistroCli();
                        mBCliente.setNumDocumento(mBCliente.getNumeroDoccliente2());
                        mBCliente.setNombreCliente(mBCliente.getNombreCliente2());
                        mBCliente.setTelefonoCl(mBCliente.getTelefonoCliente2());
                        mBCliente.contadDirecc = 1;
                        mBCliente.contadDocum = 1;
                        mBCliente.contadEmail = 1;
                        mBCliente.setEstadoActualizarClienteBtn(true);
                        mBCliente.setEstadoRegistroClienteBtn(false);
                        mBCliente.setEstadoNumDocCliTemp(true);
                        RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
                    }
                }
            } else if (tipo == 3) {
                //realiza la consulta por el parametro de las cajas de la fila 2
                if (("".equals(mBCliente.getNombreCliente3()) || mBCliente.getNombreCliente3() == null)
                        || ("".equals(mBCliente.getNumeroDoccliente3()) || mBCliente.getNumeroDoccliente3() == null)
                        || ("".equals(mBCliente.getTelefonoCliente3()) || mBCliente.getTelefonoCliente3() == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar al cliente");
                    mbTodero.warn();
                } else {
                    mbTodero.resetTable("PreRadica:ClTable");

                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(mBCliente.getNumeroDoccliente3(), mBCliente.getNombreCliente3(), mBCliente.getTelefonoCliente3(), 1);
                    mBCliente.setFilaRegistro(3);
                    if (this.ListClient.size() > 0) {
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        mBCliente.setMensajeConsultaCliente3("N/A");
                        mBCliente.setEstadoConsultaCliente3(true);
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones, por favor confirme datos y proceda a registrarlo");
                        mbTodero.info();
                        mBCliente.limpiarCajasRegistroCli();
                        mBCliente.setNumDocumento(mBCliente.getNumeroDoccliente3());
                        mBCliente.setNombreCliente(mBCliente.getNombreCliente3());
                        mBCliente.setTelefonoCl(mBCliente.getTelefonoCliente3());
                        mBCliente.contadDirecc = 1;
                        mBCliente.contadDocum = 1;
                        mBCliente.contadEmail = 1;
                        mBCliente.setEstadoActualizarClienteBtn(true);
                        mBCliente.setEstadoRegistroClienteBtn(false);
                        mBCliente.setEstadoNumDocCliTemp(true);
                        RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
                    }
                }
            } else if (tipo == 4) {
                //realiza la consulta por el parametro de las cajas de la fila 3
                if (("".equals(mBCliente.getNombreCliente4()) || mBCliente.getNombreCliente4() == null)
                        || ("".equals(mBCliente.getNumeroDoccliente4()) || mBCliente.getNumeroDoccliente4() == null)
                        || ("".equals(mBCliente.getTelefonoCliente4()) || mBCliente.getTelefonoCliente4() == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar al cliente");
                    mbTodero.warn();
                } else {
                    mbTodero.resetTable("PreRadica:ClTable");

                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(mBCliente.getNumeroDoccliente4(), mBCliente.getNombreCliente4(), mBCliente.getTelefonoCliente4(), 1);
                    mBCliente.setFilaRegistro(4);
                    if (this.ListClient.size() > 0) {
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        mBCliente.setMensajeConsultaCliente4("N/A");
                        mBCliente.setEstadoConsultaCliente4(true);
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones, por favor confirme datos y proceda a registrarlo");
                        mbTodero.info();
                        mBCliente.limpiarCajasRegistroCli();
                        mBCliente.setNumDocumento(mBCliente.getNumeroDoccliente4());
                        mBCliente.setNombreCliente(mBCliente.getNombreCliente4());
                        mBCliente.setTelefonoCl(mBCliente.getTelefonoCliente4());
                        mBCliente.contadDirecc = 1;
                        mBCliente.contadDocum = 1;
                        mBCliente.contadEmail = 1;
                        mBCliente.setEstadoActualizarClienteBtn(true);
                        mBCliente.setEstadoRegistroClienteBtn(false);
                        mBCliente.setEstadoNumDocCliTemp(true);
                        RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
                    }
                }
            } else if (tipo == 5) {
                //realiza la consulta por el parametro de las cajas de la fila 4
                if (("".equals(mBCliente.getNombreCliente5()) || mBCliente.getNombreCliente5() == null)
                        || ("".equals(mBCliente.getNumeroDoccliente5()) || mBCliente.getNumeroDoccliente5() == null)
                        || ("".equals(mBCliente.getTelefonoCliente5()) || mBCliente.getTelefonoCliente5() == null)) {
                    mbTodero.setMens("Debe ingresar todos los filtros para analizar al cliente");
                    mbTodero.warn();
                } else {
                    mbTodero.resetTable("PreRadica:ClTable");

                    this.ListClient = null;
                    this.ListClient = Cli.getConsultarAllClientes(mBCliente.getNumeroDoccliente5(), mBCliente.getNombreCliente5(), mBCliente.getTelefonoCliente5(), 1);
                    mBCliente.setFilaRegistro(5);
                    if (this.ListClient.size() > 0) {
                        RequestContext.getCurrentInstance().execute("PF('DlgConsultaClientes').show()");
                    } else {
                        mBCliente.setMensajeConsultaCliente5("N/A");
                        mBCliente.setEstadoConsultaCliente5(true);
                        mbTodero.setMens("No se encontraron clientes con estas especificaciones, por favor confirme datos y proceda a registrarlo");
                        mbTodero.info();
                        mBCliente.limpiarCajasRegistroCli();
                        mBCliente.setNumDocumento(mBCliente.getNumeroDoccliente5());
                        mBCliente.setNombreCliente(mBCliente.getNombreCliente5());
                        mBCliente.setTelefonoCl(mBCliente.getTelefonoCliente5());
                        mBCliente.contadDirecc = 1;
                        mBCliente.contadDocum = 1;
                        mBCliente.contadEmail = 1;
                        mBCliente.setEstadoActualizarClienteBtn(true);
                        mBCliente.setEstadoRegistroClienteBtn(false);
                        mBCliente.setEstadoNumDocCliTemp(true);
                        RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
                    }
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaCliente()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite validar la informacion de un cliente y con ello
     * consultar la informacion de los clientes, si estos exixten y se
     * encuentran registrados, si no permite registrarlo
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param fila int que contiene el numero de caja/fila en la que se va a
     * validar la informacion de cliente
     * @since 01-11-2014
     */
    public void verCli(int fila) {
        try {
            Cli = new LogCliente();
            boolean estadoVlidarCliente = false;
            if (fila == 1) {
                if ("".equals(mBCliente.getMensajeConsultaCliente1())) {
                    mbTodero.setMens("Debe editar y analizar el cliente de la fila 1");
                    mbTodero.warn();
                } else {
                    Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente1());
                    estadoVlidarCliente = true;
                }
                Conexion.CloseCon();
            }

            if (fila == 2) {
                if ("".equals(mBCliente.getMensajeConsultaCliente2())) {
                    mbTodero.setMens("Debe editar y analizar el cliente de la fila 2");
                    mbTodero.warn();
                } else {
                    Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente2());
                    estadoVlidarCliente = true;
                }
                Conexion.CloseCon();
            }
            if (fila == 3) {
                if ("".equals(mBCliente.getMensajeConsultaCliente3())) {
                    mbTodero.setMens("Debe editar y analizar el cliente de la fila 3");
                    mbTodero.warn();
                } else {
                    Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente3());
                    estadoVlidarCliente = true;
                }
                Conexion.CloseCon();
            }
            if (fila == 4) {
                if ("".equals(mBCliente.getMensajeConsultaCliente4())) {
                    mbTodero.setMens("Debe editar y analizar el cliente de la fila 4");
                    mbTodero.warn();
                } else {
                    Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente4());
                    estadoVlidarCliente = true;
                }
                Conexion.CloseCon();
            }
            if (fila == 5) {
                if ("".equals(mBCliente.getMensajeConsultaCliente5())) {
                    mbTodero.setMens("Debe editar y analizar el cliente de la fila 5");
                    mbTodero.warn();
                } else {
                    Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente5());
                    estadoVlidarCliente = true;
                }
                Conexion.CloseCon();
            }
            if (estadoVlidarCliente == true) {
                if (Dat.next()) {
                    mBCliente.setTipoDocclienteInfo(Dat.getString("Nombre_Documento"));
                    mBCliente.setNumeroDocclienteInfo(Dat.getString("Numero_DocCliente"));
                    mBCliente.setNombreClienteInfo(Dat.getString("Nombre_Cliente"));
                    mBCliente.setCorreoClienteInfo(Dat.getString("Mail_Cliente"));
                    mBCliente.setDireccionClienteInfo(Dat.getString("Direccion_Cliente"));
                    mBCliente.setTelefonoClienteInfo(Dat.getString("Telefono_Cliente"));
                    mBCliente.setUbicacionClienteInfo(Dat.getString("Ubicacion"));
                    RequestContext.getCurrentInstance().execute("PF('InfoCliente').show()");
                } else {
                    mbTodero.setMens("El cliente seleccionado no se encuentra registrado");
                    mbTodero.warn();
                }
            }

        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verCli()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que verifica la informacion ingresada en el registro de solicitud,
     * si todo esrta en orden abre el formulario tipo dialog en donde se muestra
     * la toda la informacion que se ha ingresado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void verificarInformacion() {
        try {
            this.estadoPanelDialogSolicitante = false;
            this.estadoPanelDialogEntidad = false;
            String validar = "";
            if ("".equals(validar)) {
                validar = "ok1";
                if (FechaSol == null) {
                    mbTodero.setMens("Debe llenar el campo 'Fecha de solicitud");
                    mbTodero.warn();
                    validar = "";
                } else if (PreRad.getNumHojas() <= 0) {
                    mbTodero.setMens("Debe llenar el campo 'Numero de hojas'");
                    mbTodero.warn();
                    validar = "";
                } else if ("".equals(PreRad.getSolicitante())) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Remitido por'");
                    mbTodero.warn();
                    validar = "";
                } else if (PreRad.getEnvioInformacion() == 0) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Enviar a'");
                    mbTodero.warn();
                    validar = "";
                } else if (mBAdmi.Adm.getCodProEnt() == 0) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Producto entidad'");
                    mbTodero.warn();
                    validar = "";
                } else if (mBAdmi.Adm.getCodTipProEnt() == 0) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Tipo de producto entidad'");
                    mbTodero.warn();
                    validar = "";
                } else if (this.RequiereUbicacion == true) {//////////
                    if ("0".equals(mBAvaluo.getCodDeptoSol())) {
                        mbTodero.setMens("Debe seleccionar información del campo 'Departamento'");
                        mbTodero.warn();
                        validar = "";
                    } else if ("0".equals(mBAvaluo.getCodCiudadSol())) {
                        mbTodero.setMens("Debe seleccionar información del campo 'Ciudad'");
                        mbTodero.warn();
                        validar = "";
                    }
                }
                if ("ok1".equals(validar)) {
                    validar = "ok2";
                    if ("".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "".equals(mBEntidad.getOpcionMostrarEntidad()) || "No".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "No1".equals(mBCliente.getOpcionMostrarCliSolicitante())
                            || "".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "No1".equals(mBEntidad.getOpcionMostrarEntidad()) || "No".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "".equals(mBEntidad.getOpcionMostrarEntidad())) {
                        mbTodero.setMens("Debe haber informacion de cliente(s) o entidad(es)");
                        mbTodero.warn();
                        validar = "ok1";
                        this.estadoPanelDialogSolicitante = false;
                        this.estadoPanelDialogEntidad = false;
                        this.estadoPanelDialogClienteFacturar = false;
                        this.estadoPanelDialogEntidadfactu = false;

                    } else if ("Si".equals(mBCliente.getOpcionMostrarCliSolicitante())) {

                        if ("".equals(mBCliente.getNombreCliente1()) || "".equals(mBCliente.getNumeroDoccliente1()) || "".equals(mBCliente.getTelefonoCliente1())) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (1)");
                            mbTodero.warn();
                            validar = "ok1";
                        } else {
                            Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente1());
                            if (Dat.next()) {
                                mBCliente.setTipoDoccliente1(Dat.getString("Nombre_Documento"));
                                mBCliente.setNumeroDoccliente1(Dat.getString("Numero_DocCliente"));
                                mBCliente.setNombreCliente1(Dat.getString("Nombre_Cliente"));
                                mBCliente.setCorreoCliente1(Dat.getString("Mail_Cliente"));
                                mBCliente.setDireccionCliente1(Dat.getString("Direccion_Cliente"));
                                mBCliente.setTelefonoCliente1(Dat.getString("Telefono_Cliente"));
                                mBCliente.setUbicacionCliente1(Dat.getString("Ubicacion"));
                            }
                            Conexion.CloseCon();
                            if (mBCliente.isClientFact1() == true) {
                                this.estadoPanelDialogSolicitante = true;
                                this.estadoPanelDialogEntidadfactu = false;
                            } else {
                                this.estadoPanelDialogSolicitante = true;
                            }
                        }

                        if (mBCliente.isEstadocajasAgregarF1() && ("".equals(mBCliente.getNombreCliente2()) || "".equals(mBCliente.getNumeroDoccliente2()) || "".equals(mBCliente.getTelefonoCliente2()))) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (2)");
                            mbTodero.warn();
                            validar = "ok1";
                        } else {
                            Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente2());
                            if (Dat.next()) {
                                mBCliente.setTipoDoccliente2(Dat.getString("Nombre_Documento"));
                                mBCliente.setNumeroDoccliente2(Dat.getString("Numero_DocCliente"));
                                mBCliente.setNombreCliente2(Dat.getString("Nombre_Cliente"));
                                mBCliente.setCorreoCliente2(Dat.getString("Mail_Cliente"));
                                mBCliente.setDireccionCliente2(Dat.getString("Direccion_Cliente"));
                                mBCliente.setTelefonoCliente2(Dat.getString("Telefono_Cliente"));
                                mBCliente.setUbicacionCliente2(Dat.getString("Ubicacion"));
                            }
                            Conexion.CloseCon();
                            //Condicion segundo check
                            if (mBCliente.isClientFact2() == true) {

                                this.estadoPanelDialogClienteFacturar = true;

                                this.estadoPanelDialogSolicitante = true;
                                this.estadoPanelDialogEntidadfactu = false;
                            } else {
                                this.estadoPanelDialogSolicitante = true;

                            }
                        }

                        if (mBCliente.isEstadocajasAgregarF2() && ("".equals(mBCliente.getNombreCliente3()) || "".equals(mBCliente.getNumeroDoccliente3()) || "".equals(mBCliente.getTelefonoCliente3()))) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente  (3)");
                            mbTodero.warn();
                            validar = "ok1";
                        } else {
                            Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente3());
                            if (Dat.next()) {
                                mBCliente.setTipoDoccliente3(Dat.getString("Nombre_Documento"));
                                mBCliente.setNumeroDoccliente3(Dat.getString("Numero_DocCliente"));
                                mBCliente.setNombreCliente3(Dat.getString("Nombre_Cliente"));
                                mBCliente.setCorreoCliente3(Dat.getString("Mail_Cliente"));
                                mBCliente.setDireccionCliente3(Dat.getString("Direccion_Cliente"));
                                mBCliente.setTelefonoCliente3(Dat.getString("Telefono_Cliente"));
                                mBCliente.setUbicacionCliente3(Dat.getString("Ubicacion"));
                            }
                            Conexion.CloseCon();
                            //Condicion Tercer check
                            if (mBCliente.isClientFact3() == true) {

                                this.estadoPanelDialogClienteFacturar = true;

                                this.estadoPanelDialogSolicitante = true;
                                this.estadoPanelDialogEntidadfactu = false;
                            } else {
                                this.estadoPanelDialogSolicitante = true;
                            }
                        }
                        if (mBCliente.isEstadocajasAgregarF3() && ("".equals(mBCliente.getNombreCliente4()) || "".equals(mBCliente.getNumeroDoccliente4()) || "".equals(mBCliente.getTelefonoCliente4()))) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (4)");
                            mbTodero.warn();
                            validar = "ok1";
                        } else {
                            Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente4());
                            if (Dat.next()) {
                                mBCliente.setTipoDoccliente4(Dat.getString("Nombre_Documento"));
                                mBCliente.setNumeroDoccliente4(Dat.getString("Numero_DocCliente"));
                                mBCliente.setNombreCliente4(Dat.getString("Nombre_Cliente"));
                                mBCliente.setCorreoCliente4(Dat.getString("Mail_Cliente"));
                                mBCliente.setDireccionCliente4(Dat.getString("Direccion_Cliente"));
                                mBCliente.setTelefonoCliente4(Dat.getString("Telefono_Cliente"));
                                mBCliente.setUbicacionCliente4(Dat.getString("Ubicacion"));
                            }
                            Conexion.CloseCon();
                            //Condicion Cuarto check
                            if (mBCliente.isClientFact4() == true) {
                                this.estadoPanelDialogClienteFacturar = true;
                                this.estadoPanelDialogSolicitante = true;
                                this.estadoPanelDialogEntidadfactu = false;
                            } else {
                                this.estadoPanelDialogSolicitante = true;

                            }
                        }
                        if (mBCliente.isEstadocajasAgregarF4() && ("".equals(mBCliente.getNombreCliente5()) || "".equals(mBCliente.getNumeroDoccliente5()) || "".equals(mBCliente.getTelefonoCliente5()))) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente  (5)");
                            mbTodero.warn();
                            validar = "ok1";
                        } else {
                            Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente5());
                            if (Dat.next()) {
                                mBCliente.setTipoDoccliente5(Dat.getString("Nombre_Documento"));
                                mBCliente.setNumeroDoccliente5(Dat.getString("Numero_DocCliente"));
                                mBCliente.setNombreCliente5(Dat.getString("Nombre_Cliente"));
                                mBCliente.setCorreoCliente5(Dat.getString("Mail_Cliente"));
                                mBCliente.setDireccionCliente5(Dat.getString("Direccion_Cliente"));
                                mBCliente.setTelefonoCliente5(Dat.getString("Telefono_Cliente"));
                                mBCliente.setUbicacionCliente5(Dat.getString("Ubicacion"));
                            }
                            Conexion.CloseCon();
                            //Condicion Quinto check
                            if (mBCliente.isClientFact5() == true) {
                                this.estadoPanelDialogClienteFacturar = true;

                                this.estadoPanelDialogSolicitante = true;
                                this.estadoPanelDialogEntidadfactu = false;
                            } else {
                                this.estadoPanelDialogSolicitante = true;

                            }
                        }

                    }
                }
                if ("ok2".equals(validar)) {
                    validar = "ok3";
                    if ("Si1".equals(mBEntidad.getOpcionMostrarEntidad())) {
                        if ("".equals(mBEntidad.getCodEntidad1()) || "".equals(mBEntidad.getCodSucursal1()) || "".equals(mBEntidad.getCodAsesor1())) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (1)");
                            mbTodero.warn();
                            validar = "ok2";
                        } else if (!"".equals(mBEntidad.getCodEntidad1()) || !"".equals(mBEntidad.getCodSucursal1()) || !"".equals(mBEntidad.getCodAsesor1())) {
                            this.estadoPanelDialogEntidad = true;
                            //Condicion del primer check
                            if (mBEntidad.isEntidadFact1() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt1 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad1()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal1()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor1()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu1(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu1(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu1(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu1(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu1(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu1(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad1(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad1(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal1(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal1(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor1(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor1(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor1(Dat.getString("Mail_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt1 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad1()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal1()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor1()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad1(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad1(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal1(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal1(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor1(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor1(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor1(Dat.getString("Mail_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }
                        if (("".equals(mBEntidad.getCodEntidad2()) || "".equals(mBEntidad.getCodSucursal2()) || "".equals(mBEntidad.getCodAsesor2())) && mBEntidad.isEstadoAgregarFilasEntidad2()) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (2)");
                            mbTodero.warn();
                            validar = "ok2";
                        } else if ((!"".equals(mBEntidad.getCodEntidad2()) || !"".equals(mBEntidad.getCodSucursal2()) || !"".equals(mBEntidad.getCodAsesor2())) && mBEntidad.isEstadoAgregarFilasEntidad2()) {
                            //Condicion segundo check
                            if (mBEntidad.isEntidadFact2() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt2 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad2()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal2()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor2()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu2(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu2(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu2(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu2(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu2(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu2(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad2(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad2(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal2(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal2(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor2(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor2(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor2(Dat.getString("Mail_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt2 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad2()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal2()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor2()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad2(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad2(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal2(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal2(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor2(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor2(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor3(Dat.getString("Mail_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }
                        if (("".equals(mBEntidad.getCodEntidad3()) || "".equals(mBEntidad.getCodSucursal3()) || "".equals(mBEntidad.getCodAsesor3())) && mBEntidad.isEstadoAgregarFilasEntidad3()) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (3)");
                            mbTodero.warn();
                            validar = "ok2";
                        } else if ((!"".equals(mBEntidad.getCodEntidad3()) || !"".equals(mBEntidad.getCodSucursal3()) || !"".equals(mBEntidad.getCodAsesor3())) && mBEntidad.isEstadoAgregarFilasEntidad3()) {
                            //Condicion Tercer check
                            if (mBEntidad.isEntidadFact3() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt3 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad3()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal3()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor3()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu3(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu3(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu3(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu3(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu3(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu3(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad3(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad3(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal3(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal3(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor3(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor3(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor3(Dat.getString("Mail_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt3 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad3()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal3()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor3()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad3(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad3(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal3(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal3(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor3(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor3(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor3(Dat.getString("Mail_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }
                        if (("".equals(mBEntidad.getCodEntidad4()) || "".equals(mBEntidad.getCodSucursal4()) || "".equals(mBEntidad.getCodAsesor4())) && mBEntidad.isEstadoAgregarFilasEntidad4()) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (4)");
                            mbTodero.warn();
                            validar = "ok2";
                        } else if ((!"".equals(mBEntidad.getCodEntidad4()) || !"".equals(mBEntidad.getCodSucursal4()) || !"".equals(mBEntidad.getCodAsesor4())) && mBEntidad.isEstadoAgregarFilasEntidad4()) {
                            if (mBEntidad.isEntidadFact4() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt4 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad4()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal4()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor4()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu4(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu4(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu4(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu4(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu4(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu4(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad4(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad4(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal4(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal4(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor4(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor4(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor4(Dat.getString("Mail_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt4 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad4()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal4()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor4()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad4(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad4(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal4(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal4(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor4(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor4(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor4(Dat.getString("Mail_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }
                        if (("".equals(mBEntidad.getCodEntidad5()) || "".equals(mBEntidad.getCodSucursal5()) || "".equals(mBEntidad.getCodAsesor5())) && mBEntidad.isEstadoAgregarFilasEntidad5()) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (5)");
                            mbTodero.warn();
                            validar = "ok2";
                        } else if ((!"".equals(mBEntidad.getCodEntidad5()) || !"".equals(mBEntidad.getCodSucursal5()) || !"".equals(mBEntidad.getCodAsesor5())) && mBEntidad.isEstadoAgregarFilasEntidad5()) {
                            //Condicion Quinto check
                            if (mBEntidad.isEntidadFact5() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt5 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad5()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal5()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor5()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu5(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu5(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu5(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu5(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu5(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu5(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad5(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad5(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal5(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal5(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor5(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor5(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor5(Dat.getString("Mail_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt5 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad5()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal5()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor5()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad5(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad5(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal5(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal5(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor5(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor5(Dat.getString("Cargo_Asesor"));
                                    mBEntidad.setMailAsesor5(Dat.getString("Mail_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }
                    }
                }
                if ("ok3".equals(validar)) {
                    validar = "ok4";
                    if ("Si".equals(mBCliente.getOpcionMostrarCliSolicitante()) || "Si1".equals(mBEntidad.getOpcionMostrarEntidad())) {
                        if (mBCliente.isClientFact1() == false && mBCliente.isClientFact2() == false && mBCliente.isClientFact3() == false && mBCliente.isClientFact4() == false && mBCliente.isClientFact5() == false
                                && mBEntidad.isEntidadFact1() == false && mBEntidad.isEntidadFact2() == false && mBEntidad.isEntidadFact3() == false && mBEntidad.isEntidadFact4() == false && mBEntidad.isEntidadFact5() == false) {
                            mbTodero.setMens("Debe seleccionar por lo menos una persona a facturar");
                            mbTodero.warn();
                            validar = "ok3";
                        } else if ((mBCliente.isClientFact1() == true || mBCliente.isClientFact2() == true || mBCliente.isClientFact3() == true || mBCliente.isClientFact4() == true || mBCliente.isClientFact5() == true)
                                && (mBEntidad.isEntidadFact1() == true || mBEntidad.isEntidadFact2() == true || mBEntidad.isEntidadFact3() == true || mBEntidad.isEntidadFact4() == true || mBEntidad.isEntidadFact5() == true)) {
                            mbTodero.setMens("No puede haber persona a 'Facturar' en cliente(s) y entidad(s)");
                            mbTodero.warn();
                            validar = "ok3";
                        } else if ((mBAvaluo.getCodTipAvaluo() == 0
                                && mBAvaluo.isEstadoPanelGrande() == true)
                                && (mBAvaluo.isEstadoTablasResulPred() == false
                                && mBAvaluo.isEstadoTablasResulMaqui() == false
                                && mBAvaluo.isEstadoTablasResulEnser() == false)) {
                            mbTodero.setMens("Debe seleccionar un tipo de avalúo");
                            mbTodero.warn();
                            validar = "ok3";
                        } else if (mBAvaluo.getCodTipAvaluo() != 0) {
                            if (mBAvaluo.isEstadoInfoPred() == true) {
                                if ("".equals(mBAvaluo.getLlaveAvaluo())) {
                                    mbTodero.setMens("Debe llenar el campo 'Matricula'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if (!mBAvaluo.getLlaveAvaluo().equals(mBAvaluo.getConfirmatriculaAval())) {
                                    mbTodero.setMens("Las matriculas no coinciden");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if ("".equals(mBAvaluo.getTipoDireccion())) {
                                    mbTodero.setMens("Debe seleccionar información del campo 'Ubicación'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if ("".equals(mBAvaluo.getDireccionAval())) {
                                    mbTodero.setMens("Debe llenar el campo 'Dirección'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if (!this.mBAvaluo.getDireccionAval().equals(mBAvaluo.getConfirDireccionAval()) && "Rural".equals(mBAvaluo.getTipoDireccion())) {
                                    mbTodero.setMens("Las direcciones no coinciden");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } 
                            } else if (mBAvaluo.isEstadoInfoMaqui() == true) {
                                if ("".equals(mBAvaluo.getObservacMaquin())) {
                                    mbTodero.setMens("Debe llenar el campo 'Descripción'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if ("".equals(mBAvaluo.getTipoDireccion())) {
                                    mbTodero.setMens("Debe seleccionar información del campo 'Ubicación'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if ("".equals(this.mBAvaluo.getDireccionAval())) {
                                    mbTodero.setMens("Debe llenar el campo 'Dirección'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if (!this.mBAvaluo.getDireccionAval().equals(mBAvaluo.getConfirDireccionAval()) && "Rural".equals(mBAvaluo.getTipoDireccion())) {
                                    mbTodero.setMens("Las direcciones no coinciden");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } 
                            } else if (mBAvaluo.isEstadoInfoResulEnser() == true) {
                                if ("".equals(mBAvaluo.getLlaveAvaluo())) {
                                    mbTodero.setMens("Debe llenar el campo 'Descripción'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if ("".equals(mBAvaluo.getTipoDireccion())) {
                                    mbTodero.setMens("Debe seleccionar información del campo 'Ubicación'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if ("".equals(this.mBAvaluo.getDireccionAval())) {
                                    mbTodero.setMens("Debe llenar el campo 'Dirección'");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } else if (!this.mBAvaluo.getDireccionAval().equals(mBAvaluo.getConfirDireccionAval()) && "rural".equals(mBAvaluo.getTipoDireccion())) {
                                    mbTodero.setMens("Las direcciones no coinciden");
                                    mbTodero.warn();
                                    validar = "ok3";
                                } 
                            }
                  
                      
                            if (Tab.next()) {
                                nombreDeptoBien = Tab.getString("Nombre_Departamento");
                                nombreCiudadBien = Tab.getString("Nombre_Ciudad");
                            }
                            Conexion.CloseCon();
                            LogAvaluo Aval = new LogAvaluo();
                            Aval.setCodTipAvaluo(mBAvaluo.getCodTipAvaluo());
                            Dat = Aval.ConsulTipAva();
                            if (Dat.next()) {
                                NomTipAvaluo = Dat.getString("Nombre_TipAvaluo");

                            }
                            Conexion.CloseCon();
                            LogAdministracion Admi = new LogAdministracion();
                            Admi.setCodTipProEnt(mBAdmi.Adm.getCodTipProEnt());
                            Dat = Admi.ConsultTipProductPreRad();
                            if (Dat.next()) {
                                Adm.setNombreProEnt(Dat.getString("Nombre_ProEnt"));
                                Adm.setNombre_TipProEnt(Dat.getString("Nombre_TipProEnt"));
                            }
                            Conexion.CloseCon();
                        } else {
                            LogAdministracion Admi = new LogAdministracion();
                            Admi.setCodTipProEnt(mBAdmi.Adm.getCodTipProEnt());
                            Dat = Admi.ConsultTipProductPreRad();
                            if (Dat.next()) {
                                Adm.setNombreProEnt(Dat.getString("Nombre_ProEnt"));
                                Adm.setNombre_TipProEnt(Dat.getString("Nombre_TipProEnt"));
                            }
                            Conexion.CloseCon();
                            mBAvaluo.setEstadoPanelGrande(false);
                            mBAvaluo.mostrarTablaInfo();
                        }
                    }
                }

                if ("ok4".equals(validar)) {
                    validar = "ok5";
                    if ("Impedir".equals(opcionRadioEstados)) {
                        if ("Impedido".equals(preRadRegiMisAsig.getEstado())) {
                            mbTodero.setMens("No puede impedir este proceso por que ya se encuenta en este estado, seleccione un estado diferente");
                            mbTodero.warn();
                            validar = "ok4";
                        }
                    }
                }
                if ("ok5".equals(validar)) {
                    validar = "ok6";
                    if ("".equals(opcionRadioEstados) && preRadicacionRegistro == true) {
                        mbTodero.setMens("Debe derfinir un estado para este proceso");
                        mbTodero.warn();
                        validar = "ok5";
                    } else if ("".equals(ObservaPreRad)) {
                        mbTodero.setMens("Debe llenar el campo 'Observaciones'");
                        mbTodero.warn();
                        validar = "ok5";
                    } else if ("Paso_radicar".equals(opcionRadioEstados) && preRadicacionRegistro == true) {
                        if ("".equals(NombreAnalista)) {
                            mbTodero.setMens("Debe seleccionar un Radicador");
                            mbTodero.warn();
                            validar = "ok5";
                        }
                    }
                }
                if ("ok6".equals(validar)) {
                    validar = "ok7";
                    if (this.ExisteInformacion == true) {

             
                    } else//Esta condicion en el caso 
                    {
                        if (!ListaArcRegla.isEmpty()) {
                            mbTodero.setMens("Hay documentacion obligatoria dentro del proceso, por favor seleccione la opcion y verifique la informacion");
                            mbTodero.warn();
                            validar = "ok6";
                        }
                    }
                }
                if ("ok7".equals(validar)) {
                    if ("Otro".equals(EnviarALabel)) {
                        detalleEnviarAotro = OtroQuienEnviarA + " - " + OtroUbicacionEnviarA + " - " + OtroDireccionEnviarA;
                    } else {
                        detalleEnviarAotro = EnviarALabel;
                    }

             
                    PreRadi.setCod_Analista(mBsesion.codigoMiSesion());
                    habilitarBotonPreRadica = true;
                    RequestContext.getCurrentInstance().execute("PF('DlgPreRadicacion').show()");
                    botondem = true;
                }
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificarInformacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que inserta un registro de solicitud de avaluo, validando
     * previamente toda la informacion ingresada
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void insertRegistro() {
        try {
            String validarR = "";
            if ("".equals(validarR)) {
                validarR = "ok1";
                if (this.FechaSol == null) {
                    mbTodero.setMens("Debe llenar el campo 'Fecha de solicitud'");
                    mbTodero.warn();
                    validarR = "";
                } else if (this.HoraSol == null) {
                    mbTodero.setMens("Debe llenar el campo 'Hora de solicitud");
                    mbTodero.warn();
                    validarR = "";
                } else if (mBAdmi.Adm.getCodProEnt() == 0) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Producto entidad'");
                    mbTodero.warn();
                    validarR = "";
                } else if (mBAdmi.Adm.getCodTipProEnt() == 0) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Tipo de producto entidad'");
                    mbTodero.warn();
                    validarR = "";
                } else if ("".equals(this.PreRad.getSolicitante())) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Remitido por'");
                    mbTodero.warn();
                    validarR = "";
                } else if (this.RequiereUbicacion == true) {
                    if ("0".equals(mBAvaluo.getCodDeptoSol())) {
                        mbTodero.setMens("Debe seleccionar información del campo 'Departamento'");
                        mbTodero.warn();
                        validarR = "";
                    } else if ("0".equals(mBAvaluo.getCodCiudadSol())) {
                        mbTodero.setMens("Debe seleccionar información del campo 'Ciudad'");
                        mbTodero.warn();
                        validarR = "";
                    }
                }
            }
            if ("ok1".equals(validarR)) {
                validarR = "ok2";
                if ("".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "".equals(mBEntidad.getOpcionMostrarEntidad()) || "No".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "No1".equals(mBEntidad.getOpcionMostrarEntidad())
                        || "".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "No1".equals(mBEntidad.getOpcionMostrarEntidad()) || "No".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "".equals(mBEntidad.getOpcionMostrarEntidad())) {
                    mbTodero.setMens("Debe haber informacion de cliente(s) o entidad(es)");
                    mbTodero.warn();
                    validarR = "ok1";
                } else if ("Si".equals(mBCliente.getOpcionMostrarCliSolicitante())) {
                    if ("".equals(mBCliente.getNombreCliente1())
                            || "".equals(mBCliente.getNumeroDoccliente1()) || "".equals(mBCliente.getTelefonoCliente1())) {
                        mbTodero.setMens("Debe llenar los datos del cliente correspondiente (1)");
                        mbTodero.warn();
                        validarR = "ok1";
                    }
                    if (mBCliente.isEstadocajasAgregarF1() == true) {
                        if ("".equals(mBCliente.getNombreCliente2())
                                || "".equals(mBCliente.getNumeroDoccliente2()) || "".equals(mBCliente.getTelefonoCliente2())) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (2)");
                            mbTodero.warn();
                            validarR = "ok1";
                        }
                    }
                    if (mBCliente.isEstadocajasAgregarF2() == true) {
                        if ("".equals(mBCliente.getNombreCliente3())
                                || "".equals(mBCliente.getNumeroDoccliente3()) || "".equals(mBCliente.getTelefonoCliente3())) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (3)");
                            mbTodero.warn();
                            validarR = "ok1";
                        }
                    }
                    if (mBCliente.isEstadocajasAgregarF3() == true) {
                        if ("".equals(mBCliente.getNombreCliente4())
                                || "".equals(mBCliente.getNumeroDoccliente4()) || "".equals(mBCliente.getTelefonoCliente4())) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (4)");
                            mbTodero.warn();
                            validarR = "ok1";
                        }
                    }
                    if (mBCliente.isEstadocajasAgregarF4() == true) {
                        if ("".equals(mBCliente.getNombreCliente5())
                                || "".equals(mBCliente.getNumeroDoccliente5()) || "".equals(mBCliente.getTelefonoCliente5())) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente  (5)");
                            mbTodero.warn();
                            validarR = "ok1";
                        }
                    }
                }
                if ("ok2".equals(validarR)) {
                    validarR = "ok3";
                    if ("Si1".equals(mBEntidad.getOpcionMostrarEntidad())) {
                        if ("".equals(mBEntidad.getCodEntidad1()) || "".equals(mBEntidad.getCodSucursal1()) || "".equals(mBEntidad.getCodAsesor1())) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (1)");
                            mbTodero.warn();
                            validarR = "ok2";
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad2() == true) {
                            if ("".equals(mBEntidad.getCodEntidad2()) || "".equals(mBEntidad.getCodSucursal2()) || "".equals(mBEntidad.getCodAsesor2())) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (2)");
                                mbTodero.warn();
                                validarR = "ok2";
                            }
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad3() == true) {
                            if ("".equals(mBEntidad.getCodEntidad3()) || "".equals(mBEntidad.getCodSucursal3()) || "".equals(mBEntidad.getCodAsesor3())) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (3)");
                                mbTodero.warn();
                                validarR = "ok2";
                            }
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad4() == true) {
                            if ("".equals(mBEntidad.getCodEntidad4()) || "".equals(mBEntidad.getCodSucursal4()) || "".equals(mBEntidad.getCodAsesor4())) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (4)");
                                mbTodero.warn();
                                validarR = "ok2";
                            }
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad5() == true) {
                            if ("".equals(mBEntidad.getCodEntidad5()) || "".equals(mBEntidad.getCodSucursal5()) || "".equals(mBEntidad.getCodAsesor5())) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (5)");
                                mbTodero.warn();
                                validarR = "ok2";
                            }
                        }
                    }
                }
            }
            if ("ok3".equals(validarR)) {
                validarR = "ok4";
                if ("Si".equals(mBCliente.getOpcionMostrarCliSolicitante()) || "Si1".equals(mBEntidad.getOpcionMostrarEntidad())) {
                    if (mBCliente.isClientFact1() == false && mBCliente.isClientFact2() == false && mBCliente.isClientFact3() == false && mBCliente.isClientFact4() == false && mBCliente.isClientFact5() == false
                            && mBEntidad.isEntidadFact1() == false && mBEntidad.isEntidadFact2() == false && mBEntidad.isEntidadFact3() == false && mBEntidad.isEntidadFact4() == false && mBEntidad.isEntidadFact5() == false) {
                        mbTodero.setMens("Debe seleccionar por lo menos una persona a facturar");
                        mbTodero.warn();
                        validarR = "ok3";
                    } else if ((mBCliente.isClientFact1() == true || mBCliente.isClientFact2() == true || mBCliente.isClientFact3() == true || mBCliente.isClientFact4() == true || mBCliente.isClientFact5() == true)
                            && (mBEntidad.isEntidadFact1() == true || mBEntidad.isEntidadFact2() == true || mBEntidad.isEntidadFact3() == true || mBEntidad.isEntidadFact4() == true || mBEntidad.isEntidadFact5() == true)) {
                        mbTodero.setMens("No puede haber persona a 'Facturar' en cliente(s) y entidad(s)");
                        mbTodero.warn();
                        validarR = "ok3";
                    }
                }
            }
            if ("ok4".equals(validarR)) {
                validarR = "ok5";
                if ("".equals(this.ObservaRegistro)) {
                    mbTodero.setMens("Debe llenar el campo 'Observaciones'");
                    mbTodero.warn();
                    validarR = "ok4";
                } else if ("0".equals(this.CodAnalista)) {
                    mbTodero.setMens("Debe seleccinar información del campo 'Asignar a'");
                    mbTodero.warn();
                    validarR = "ok4";
                }
            }
            if ("ok5".equals(validarR)) {
                PreRad.setNom_Analista(NombreAnalista);
                fechaSoli = Format.format(FechaSol);
                PreRad.setFechaSolicitud(fechaSoli);
                this.HoraSolisitud = FormatHora.format(HoraSol);
                PreRad.setHoraSolicitud((HoraSolisitud));
                PreRad.setObservacionPreRadica(ObservaRegistro + " - Registro de solicitud");
                if (mBAvaluo.getCodCiudadSol() == null || "".equals(mBAvaluo.getCodCiudadSol())) {
                    PreRad.setCod_ciudad(0);
                } else if (mBAvaluo.getCodCiudadSol() != null || !"".equals(mBAvaluo.getCodCiudadSol())) {
                    PreRad.setCod_ciudad(Integer.parseInt(mBAvaluo.getCodCiudadSol()));
                }
                PreRad.setCod_Analista(Integer.parseInt(CodAnalista));
                if (this.Cotizacion == true) {
                    PreRad.InserPreRadicacion(1, 0, mBAdmi.Adm.getCodTipProEnt(), 1, mBsesion.codigoMiSesion());
                } else if (Cotizacion == false) {
                    PreRad.InserPreRadicacion(1, 0, mBAdmi.Adm.getCodTipProEnt(), 0, mBsesion.codigoMiSesion());
                }
                Dat = PreRad.ConsulInfPreRadicacion(mBsesion.codigoMiSesion());
                if (Dat.next()) {
                    int CodPreRad = Integer.parseInt(Dat.getString("Codigo"));
                    //REGISTRA INFORMACION DE LA ENTIDAD
                    String TipoPersona = "";
                    int cod_asesor = 0;
                    if (mBEntidad.isEstPanelEntidad() == true) {

                        PreRad.setCodPreRadica(CodPreRad);
                        if ("".equals(mBEntidad.getCodAsesor1()) || mBEntidad.getCodAsesor1() == null) {

                        } else {
                            cod_asesor = Integer.parseInt(mBEntidad.getCodAsesor1());
                            if (mBEntidad.isEntidadFact1() == true) {
                                TipoPersona = "T";
                            } else {
                                TipoPersona = "S";
                            }
                            PreRad.InsertOModificEntidadPreRadica(1, cod_asesor, TipoPersona, mBsesion.codigoMiSesion());
                        }

                        if ("".equals(mBEntidad.getCodAsesor2()) || mBEntidad.getCodAsesor2() == null) {

                        } else {
                            cod_asesor = Integer.parseInt(mBEntidad.getCodAsesor2());
                            if (mBEntidad.isEntidadFact2() == true) {
                                TipoPersona = "T";
                            } else {
                                TipoPersona = "S";
                            }
                            PreRad.InsertOModificEntidadPreRadica(1, cod_asesor, TipoPersona, mBsesion.codigoMiSesion());
                        }

                        if ("".equals(mBEntidad.getCodAsesor3()) || mBEntidad.getCodAsesor3() == null) {

                        } else {
                            cod_asesor = Integer.parseInt(mBEntidad.getCodAsesor3());
                            if (mBEntidad.isEntidadFact3() == true) {
                                TipoPersona = "T";
                            } else {
                                TipoPersona = "S";
                            }
                            PreRad.InsertOModificEntidadPreRadica(1, cod_asesor, TipoPersona, mBsesion.codigoMiSesion());
                        }

                        if ("".equals(mBEntidad.getCodAsesor4()) || mBEntidad.getCodAsesor4() == null) {

                        } else {
                            cod_asesor = Integer.parseInt(mBEntidad.getCodAsesor4());
                            if (mBEntidad.isEntidadFact4() == true) {
                                TipoPersona = "T";
                            } else {
                                TipoPersona = "S";
                            }
                            PreRad.InsertOModificEntidadPreRadica(1, cod_asesor, TipoPersona, mBsesion.codigoMiSesion());
                        }

                        if ("".equals(mBEntidad.getCodAsesor5()) || mBEntidad.getCodAsesor5() == null) {

                        } else {
                            cod_asesor = Integer.parseInt(mBEntidad.getCodAsesor5());
                            if (mBEntidad.isEntidadFact5() == true) {
                                TipoPersona = "T";
                            } else {
                                TipoPersona = "S";
                            }
                            PreRad.InsertOModificEntidadPreRadica(1, cod_asesor, TipoPersona, mBsesion.codigoMiSesion());
                        }
                    }
                    //REGISTRA INFORMACION DEL CLIENTE
                    String tipo_persona_cli;
                    String funcionario = "0";
                    if (mBCliente.isEstadoPanelClienteGeneral() == true) {
                        Cli = new LogCliente();
                        //Verificar el tipo de cliente 1
                        PreRad.setCodPreRadica(CodPreRad);
                        if ("".equals(mBCliente.getNumeroDoccliente1()) || mBCliente.getNumeroDoccliente1() == null) {

                        } else {
                            funcionario = "0";
                            if (mBCliente.isClientFact1()) {
                                tipo_persona_cli = "T";
                                if (mBCliente.isFuncionario1() == true) {
                                    funcionario = "1";
                                } else {
                                    funcionario = "0";
                                }
                            } else {
                                tipo_persona_cli = "S";
                            }
                            PreRad.InserTempClientPreRadica(mBCliente.getNumeroDoccliente1(), mBCliente.getNombreCliente1(), mBCliente.getTelefonoCliente1(), tipo_persona_cli, funcionario, mBsesion.codigoMiSesion());
                        }

                        if ("".equals(mBCliente.getNumeroDoccliente2()) || mBCliente.getNumeroDoccliente2() == null) {

                        } else {
                            funcionario = "0";
                            if (mBCliente.isClientFact2()) {
                                tipo_persona_cli = "T";
                                if (mBCliente.isFuncionario2() == true) {
                                    funcionario = "1";
                                } else {
                                    funcionario = "0";
                                }
                            } else {
                                tipo_persona_cli = "S";
                            }
                            PreRad.InserTempClientPreRadica(mBCliente.getNumeroDoccliente2(), mBCliente.getNombreCliente2(), mBCliente.getTelefonoCliente2(), tipo_persona_cli, funcionario, mBsesion.codigoMiSesion());
                        }
                        if ("".equals(mBCliente.getNumeroDoccliente3()) || mBCliente.getNumeroDoccliente3() == null) {

                        } else {
                            funcionario = "0";
                            if (mBCliente.isClientFact3()) {
                                tipo_persona_cli = "T";
                                if (mBCliente.isFuncionario3() == true) {
                                    funcionario = "1";
                                } else {
                                    funcionario = "0";
                                }
                            } else {
                                tipo_persona_cli = "S";
                            }
                            PreRad.InserTempClientPreRadica(mBCliente.getNumeroDoccliente3(), mBCliente.getNombreCliente3(), mBCliente.getTelefonoCliente3(), tipo_persona_cli, funcionario, mBsesion.codigoMiSesion());
                        }
                        if ("".equals(mBCliente.getNumeroDoccliente4()) || mBCliente.getNumeroDoccliente4() == null) {

                        } else {
                            funcionario = "0";
                            if (mBCliente.isClientFact4()) {
                                tipo_persona_cli = "T";
                                if (mBCliente.isFuncionario4() == true) {
                                    funcionario = "1";
                                } else {
                                    funcionario = "0";
                                }
                            } else {
                                tipo_persona_cli = "S";
                            }
                            PreRad.InserTempClientPreRadica(mBCliente.getNumeroDoccliente4(), mBCliente.getNombreCliente4(), mBCliente.getTelefonoCliente4(), tipo_persona_cli, funcionario, mBsesion.codigoMiSesion());
                        }
                        if ("".equals(mBCliente.getNumeroDoccliente5()) || mBCliente.getNumeroDoccliente5() == null) {

                        } else {
                            funcionario = "0";
                            if (mBCliente.isClientFact5()) {
                                tipo_persona_cli = "T";
                                if (mBCliente.isFuncionario5() == true) {
                                    funcionario = "1";
                                } else {
                                    funcionario = "0";
                                }
                            } else {
                                tipo_persona_cli = "S";
                            }
                            PreRad.InserTempClientPreRadica(mBCliente.getNumeroDoccliente5(), mBCliente.getNombreCliente5(), mBCliente.getTelefonoCliente1(), tipo_persona_cli, funcionario, mBsesion.codigoMiSesion());
                        }
                    }
                    mbTodero.setMens("El Registro numero " + PreRad.getCodPreRadica() + " se ha guardado con exito");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('confirmarcion_pre_radica').show()");
                    botondem = false;
                }
                Conexion.CloseCon();
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".insertRegistro()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite agregar al formulario un analista que fue seleccionado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void seleccionAnalistaPreRadica() {
        try {
            this.NombreAnalista = PreRadi.getNom_Analista();
            this.CodAnalista = String.valueOf(PreRadi.getCod_Analista());
            RequestContext.getCurrentInstance().execute("PF('DlgAnalista').hide()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionAnalistaPreRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite agregar al formulario un analista que fue seleccionado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void seleccionAnalistaRadica() {
        try {
            this.NombreAnalista = PreRadica.getNom_Analista();
            this.CodAnalista = String.valueOf(PreRadica.getCod_Analista());
            RequestContext.getCurrentInstance().execute("PF('DlgAnalista').hide()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionAnalistaRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que agrega toda la informacion de el form pre-radicacion en el
     * form de radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void agregarDatosPreRadic() {

        try {
            if (PreRad == null) {
                mbTodero.setMens("Seleccione un numero de Pre-Radicacion");
                mbTodero.warn();
            } else {
                //inhabilita loos componentes que no se van a poder modificar
//                estadoComponentesRadicPreRad = true;
//                estadoRadiosOpcionesRadicacion = false;
                //Carga la informacion de la pre-radicacion
                Dat = PreRad.ConsultaPreRadicacion(PreRad.getCodPreRadica());
                if (Dat.next()) {
                    cod_preRadica = Dat.getString("Cod_PreRadica");
                    FechaSol = Dat.getDate("Fecha_Solicitud_Preradica");
                    HoraSol = Dat.getTime("Hora_Preradica");
                    PreRad.setNumHojas(Dat.getInt("NumHojas_Preradica"));
                    PreRad.setSolicitante(Dat.getString("Solicitante_PreRadica"));
                    PreRad.setEnvioInformacion(Dat.getInt("EnvioInformacion_Preradica"));
                    NombreAnalista = Dat.getString("Radicador");

                    if ("OTRO".equals(Dat.getString("Resultado_Parametro"))) {
                        estadoCajasEnviarA = true;
                        String[] infoOtro = Dat.getString("DetalleEnvio_PreRadica").split(" - ");
                        OtroQuienEnviarA = infoOtro[0];
                        OtroDireccionEnviarA = infoOtro[1];
                        OtroUbicacionEnviarA = infoOtro[2];
                    } else {
                        // envioInformacion = Dat.getString("Resultado_Parametro");
                    }
                    mostrarCamposEnviarA();
                    // codEnvioInformacion = Dat.getString("EnvioInformacion_Preradica");
                    mBAdmi.Adm.setCodProEnt(Dat.getInt("Cod_ProEnt"));
                    mBAdmi.Adm.setNombreProEnt(Dat.getString("Nombre_ProEnt"));
                    mBAdmi.onTipProEnt();
                    mBAdmi.Adm.setCodTipProEnt(Dat.getInt("Fk_Cod_TipProEnt"));
                    Cotizacion = Dat.getBoolean("Cotizacion_PreRadica");
                    //estadoObservacionRadic = true;

                    DatObser = PreRad.ConsultaObservacionesPreRad(cod_preRadica);
                    ListObserPreRadicados = new ArrayList<>();
                    while (DatObser.next()) {
                        LogPreRadicacion PreRadObs = new LogPreRadicacion();
                        PreRadObs.setObservacionPreRadica(DatObser.getString("Obser"));
                        PreRadObs.setFechaObsPreRadica(DatObser.getString("Fecha"));
                        PreRadObs.setEmpleObservacionPreRadica(DatObser.getString("empleado"));
                        ListObserPreRadicados.add(PreRadObs);
                    }
                    Conexion.CloseCon();
                    //observacion = Dat.getString("Observacion_Preradica");
                    preRadRegiMisAsig.setEstado(Dat.getString("Estado_Preradica"));
                    if ("P".equals(preRadRegiMisAsig.getEstado())) {
                        preRadRegiMisAsig.setEstado("En Proceso");
                    }

                    if ("".equals(Dat.getString("Fk_Cod_Ciudad")) || Dat.getString("Fk_Cod_Ciudad") == null) {
                        RequiereUbicacion = false;
                    } else {
                        Ubicacion = true;
                        RequiereUbicacion = true;
                        mBAvaluo.setRequiereUbicacion(true);
                        mBAvaluo.setCodDeptoSol(Dat.getString("Cod_departamento"));

                        mBAvaluo.setCodCiudadSol(Dat.getString("FK_cod_ciudad"));
                  
                        if ("Impedido".equals(preRadRegiMisAsig.getEstado())) {
                            PreRad.setEnvioInformacion(Dat.getInt("EnvioInformacion_preradica"));
                            PreRad.setNumHojas(Dat.getInt("Numhojas_preradica"));
                        }

                    }
                }
                Conexion.CloseCon();
                if ("En Proceso".equals(preRadRegiMisAsig.getEstado())) {

                    //Consulta si exixten clientes temporales
                    /*
                     mBCliente.setCargaListaClientesTempo(Cli.consultaClientesTempor(PreRad.getCodPreRadica()));
                     //Si trae clientes temporales 
                     if (mBCliente.getCargaListaClientesTempo().size() > 0) {
                     mBCliente.setValidarClienteTempo(true);
                     mBCliente.setCod_preRadic(PreRad.getCodPreRadica());
                     mBCliente.setEstadoTablaClientesTempo(true);
                     mBCliente.setEstadoClosableClientesRegistro(false);
                     RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
                     } else {
                     mbTodero.setMens( "No hay clientes temporales, cargar resto de información";
                     mbTodero.info();*/
                    //Carga la informacion de los clientes                        
                    mBCliente.setCod_preRadic(Integer.parseInt(cod_preRadica));
                    mBCliente.cargarClientesEnRadicacion(1);
                    //}

                    //Carga la informacion de la entidad  
                    mBEntidad.setCod_preRadic(Integer.parseInt(cod_preRadica));
                    mBEntidad.cargarEntidadesPreRadic(1);

                    //Carga la informacion de los peritos       
                    //mBPerito.setCod_preRadic(PreRad.getCodPreRadica());
                    //mBPerito.CargarInfoPeritosRadic();
                    //Carga la informacion del avaluo
                    mBAvaluo.setCod_preRadica(Integer.parseInt(cod_preRadica));
                    mBAvaluo.cargarAvaluoRadicacion(1);

             
                    //Pone estado true el panel general de todos los datos
                    //  estadoPanelGeneral = true;
                    //Cierra el dialog que tiene las pre-radicaciones
                    //RequestContext.getCurrentInstance().execute("PF('InfoPreRadicaciones').hide()");
                } else {
                    mbTodero.setMens("La Pre-Radicación seleccionada no se puede asociar a una Radicación por el estado en que se encuentra");
                    mbTodero.warn();
                }

            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarDatosPreRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

   

    /**
     * Metodo que actualiza la informacion acerca de la realizacion del estudio
     * de pre-radicacion.
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void aceptarProcesoPreradica() {
        try {
            switch (opcionRadioEstudio) {
                case "Si":
                    PreRad.ActualizarEstadoPreRadica(1, cod_preRadica);
                    break;
                case "No":
                    PreRad.ActualizarEstadoPreRadica(0, cod_preRadica);
                    break;
            }
            cleear();
            RequestContext.getCurrentInstance().execute("PF('confirmacion_preRadica').show()");

            botondem = false;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".aceptarProcesoPreradica()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para iniciar el proceso de Radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void initRadicacion() {
        try {
            Dat = PreRad.ConsulRadUsu(mBsesion.codigoMiSesion());
            if (Dat.next()) {
            } else {
                mbTodero.setMens("No puede iniciar el Proceso de Radicacion por que no tiene Permisos");
                mbTodero.warn();
            }
            Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".initRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que consulta la informacion del Cliente en la Pre-Radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param Cod int que contiene la identificacion del cliente
     * @since 01-11-2014
     */
    public void ConsultaCodCliente(String Cod) {
        try {
            Cli = new LogCliente();
            Dat = Cli.ConsulCodCli(Cod);
            if (Dat.next()) {
                CodigoCliente = Dat.getString("Cod_Cliente");
            }
            Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".ConsultaCodCliente()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que captura el numero de pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void capturarNumPreRadica() {
        try {
            if (PreRad == null) {
                mbTodero.setMens("Seleccione un numero de Pre-Radicacion");
                mbTodero.warn();
            } else {
                RequestContext.getCurrentInstance().execute("PF('DlgInfPreRadicacion').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".ConsultaCodCliente()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite visualizar la informacion que se ha ingresado en
     * pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void visualizarInformacion() {
        try {
            Cli = new LogCliente();
            if (PreRad.getCodPreRadica() == 0) {
                mbTodero.setMens("No se ha seleccionado numero de Pre-Radicacion");
                mbTodero.warn();
            } else {
                borrarInfoCliente("");
                //INFORMACION DEL CLIENTE
                Dat = Cli.ConsulCliPreRadica(1, PreRad.getCodPreRadica());
                while (Dat.next()) {
                    if ("T".equals(Dat.getString("Tipo_Cliente"))) {
                        this.estadoPreRadicaDialogPersonFacVI = true;
                        //   llenadoInformacionPerFac(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), Dat.getString("Telefono_Cliente"), Dat.getString("Direccion_Cliente"));
                    }
                    /*else {*/

                    this.estadoPreRadicaDialogSolicitantesVI = true;
                    //llenadoInformacionCliente(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), Dat.getString("Telefono_Cliente"), Dat.getString("Direccion_Cliente"));
                    //}
                }
                Conexion.CloseCon();
                Dat = Cli.ConsulTempCliePreRadica(1, PreRad.getCodPreRadica());
                while (Dat.next()) {
                    if ("T".equals(Dat.getString("Tipo_Cliente"))) {
                        this.estadoPreRadicaDialogPersonFacVI = true;
                        //  llenadoInformacionPerFac(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), "", "");
                    }
                    /*else {*/

                    this.estadoPreRadicaDialogSolicitantesVI = true;
                    //  llenadoInformacionCliente(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), "", "");
                    //   }
                }
                Conexion.CloseCon();
                //INFORMACION DE LA ENTIDAD
                Dat = Ent.ConsultEntPreRadica(1, PreRad.getCodPreRadica());
                while (Dat.next()) {
                    this.estadoPreRadicaDialogEntidVI = true;
                    Ent.setNombreEntidad(Dat.getString("Nombre_Entidad"));
                    Ent.setNombreOfic(Dat.getString("Nombre_Oficina"));
                    Ent.setTelefonoOfic(Dat.getString("Telefono_Oficina"));
                    Ent.setNombreAsesor(Dat.getString("Nombre_Asesor"));
                    Ent.setCargoAsesor(Dat.getString("Cargo_Asesor"));
                    if ("T".equals(Dat.getString("Tipo_Entidad"))) {
                        estadoPreRadicaEntidadFac = true;
                        FacturarEnti = "La Entidad es Persona a Facturar";
                    } else {
                        estadoPreRadicaEntidadFac = false;
                    }
                }
                Conexion.CloseCon();
                //INFORMACION DE TIPO AVALUO
                String Val;
                Dat = Ava.ConsulTipAvaPreRad(PreRad.getCodPreRadica());
                if (Dat.next()) {
                    if ("Predio".equals(Dat.getString("Nombre_TipAvaluo"))) {
                        this.estadoPreRadicaDialogAvaluoPredVI = true;
                        this.estadoPreRadicaDialogAvaluoMaqVI = false;
                        this.estadoPreRadicaDialogAvaluoEnseVI = false;
                        mBAvaluo.carTipAvaluoPreRad(1, PreRad.getCodPreRadica());
                    } else if ("Maquinaria".equals(Dat.getString("Nombre_TipAvaluo"))) {
                        this.estadoPreRadicaDialogAvaluoPredVI = false;
                        this.estadoPreRadicaDialogAvaluoMaqVI = true;
                        this.estadoPreRadicaDialogAvaluoEnseVI = false;
                        mBAvaluo.carTipAvaluoPreRad(2, PreRad.getCodPreRadica());
                    } else {
                        this.estadoPreRadicaDialogAvaluoPredVI = false;
                        this.estadoPreRadicaDialogAvaluoMaqVI = false;
                        this.estadoPreRadicaDialogAvaluoEnseVI = true;
                        mBAvaluo.carTipAvaluoPreRad(3, PreRad.getCodPreRadica());
                    }
                } else {
                    //INFORMACION  DE TEMP TIPO AVALUO
                    Dat = Ava.ConsultTempBienPreRad(String.valueOf(PreRad.getCodPreRadica()));
                    if (Dat.next()) {
                        this.estadoPreRadicaDialogAvaluoTempVI = true;
                        Ava.setLlaveTB(Dat.getString("LLave"));
                        mBAvaluo.setDireccionAval(Dat.getString("Direccion"));
                        Ava.setNomTipAvaluo(Dat.getString("Nombre_TipAvaluo"));
                     
                    }
                    Conexion.CloseCon();
                }
                Conexion.CloseCon();
            }
            RolRadicador(mBsesion.codigoMiSesion());
            RequestContext.getCurrentInstance().execute("PF('DlgInfVerInformacion').show()");
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".visualizarInformacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para generar Impresion de la PreRadicacion del Avaluo
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     * @deprecated
     */
    public void generaImpresion() {
        try {
            Cli = new LogCliente();
            if (PreRad.getCodPreRadica() == 0) {
                mbTodero.setMens("No se ha seleccionado numero de Pre-Radicacion");
                mbTodero.warn();
            } else {
                borrarInfoCliente("");
                //INFORMACION DEL CLIENTE
                Dat = Cli.ConsulCliPreRadica(1, PreRad.getCodPreRadica());
                while (Dat.next()) {
                    if ("T".equals(Dat.getString("Tipo_Cliente"))) {
                        this.estadoPreRadicaDialogPersonFacGI = true;
                        //    llenadoInformacionPerFac(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), Dat.getString("Telefono_Cliente"), Dat.getString("Direccion_Cliente"));
                    }
                    /*else {*/

                    this.estadoPreRadicaDialogPersonFacGI = true;
                    //   llenadoInformacionCliente(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), Dat.getString("Telefono_Cliente"), Dat.getString("Direccion_Cliente"));
                    //}
                }
                Conexion.CloseCon();
                Dat = Cli.ConsulTempCliePreRadica(1, PreRad.getCodPreRadica());
                while (Dat.next()) {
                    if ("T".equals(Dat.getString("Tipo_Cliente"))) {
                        this.estadoPreRadicaDialogPersonFacGI = true;
                        //     llenadoInformacionPerFac(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), "", "");
                    }
                    /*else {*/

                    this.estadoPreRadicaDialogPersonFacGI = true;
                    //   llenadoInformacionCliente(Dat.getString("Numero_DocCliente"), Dat.getString("Nombre_Cliente"), Dat.getString("Apellido_Cliente"), "", "");
                    //   }
                }
                Conexion.CloseCon();
                //INFORMACION DE LA ENTIDAD
                Dat = Ent.ConsultEntPreRadica(1, PreRad.getCodPreRadica());
                while (Dat.next()) {
                    this.estadoPreRadicaDialogEntidGI = true;
                    Ent.setNombreEntidad(Dat.getString("Nombre_Entidad"));
                    Ent.setNombreOfic(Dat.getString("Nombre_Oficina"));
                    Ent.setTelefonoOfic(Dat.getString("Telefono_Oficina"));
                    Ent.setNombreAsesor(Dat.getString("Nombre_Asesor"));
                    Ent.setCargoAsesor(Dat.getString("Cargo_Asesor"));
                    if ("T".equals(Dat.getString("Tipo_Entidad"))) {
                        estadoPreRadicaEntidadFac = true;
                        FacturarEnti = "La Entidad es Persona a Facturar";
                    } else {
                        estadoPreRadicaEntidadFac = false;
                    }
                }
                Conexion.CloseCon();
                //INFORMACION DE TIPO AVALUO
                String Val;
                Dat = Ava.ConsulTipAvaPreRad(PreRad.getCodPreRadica());
                if (Dat.next()) {
                    if ("Predio".equals(Dat.getString("Nombre_TipAvaluo"))) {
                        this.estadoPreRadicaDialogAvaluoPredGI = true;
                        this.estadoPreRadicaDialogAvaluoMaqGI = false;
                        this.estadoPreRadicaDialogAvaluoEnseGI = false;
                        mBAvaluo.carTipAvaluoPreRad(1, PreRad.getCodPreRadica());
                    } else if ("Maquinaria".equals(Dat.getString("Nombre_TipAvaluo"))) {
                        this.estadoPreRadicaDialogAvaluoPredGI = false;
                        this.estadoPreRadicaDialogAvaluoMaqGI = true;
                        this.estadoPreRadicaDialogAvaluoEnseGI = false;
                        mBAvaluo.carTipAvaluoPreRad(2, PreRad.getCodPreRadica());
                    } else {
                        this.estadoPreRadicaDialogAvaluoPredGI = false;
                        this.estadoPreRadicaDialogAvaluoMaqGI = false;
                        this.estadoPreRadicaDialogAvaluoEnseGI = true;
                        mBAvaluo.carTipAvaluoPreRad(3, PreRad.getCodPreRadica());
                    }
                } else {
                    //INFORMACION  DE TEMP TIPO AVALUO
                    Dat = Ava.ConsultTempBienPreRad(String.valueOf(PreRad.getCodPreRadica()));
                    if (Dat.next()) {
                        this.estadoPreRadicaDialogAvaluoTempGI = true;
                        Ava.setLlaveTB(Dat.getString("LLave"));
                        mBAvaluo.setDireccionAval(Dat.getString("Direccion"));
                        Ava.setNomTipAvaluo(Dat.getString("Nombre_TipAvaluo"));
           
                    }
                    Conexion.CloseCon();
                }
                Conexion.CloseCon();
            }
            RequestContext.getCurrentInstance().execute("PF('DlgInfGenerarImpre').show()");
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".generarImpresion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite registrar Valores Pactados
     *

     * @deprecated
     */
    public void registrarValoresPactados() {
        try {
            //Verifica si el panel se muestra para saber si hay informacion de la Entidad
            if (this.estadoPanelEntidadPreRad == true) {
            }

            //Verifica si el panel se muestra para saber si hay informacion del Cliente a Facturar
            if (this.estadoPanelClientPreRadi == true) {
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".registrarValoresPactados()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para validar que la informacion de Perito que se encuentre llena
     * en las variables
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param DocCli
     * @param NomCli
     * @param ApeCli
     * @since 01-11-2014
     * @deprecated
     */
    public void llenadoInformacionPerito(String DocCli, String NomCli, String ApeCli) {
        try {
            if ("".equals(numeroDocPerito1)) {
                this.numeroDocPerito1 = DocCli;
                this.nombrePerito1 = NomCli;
                this.apellidoPerito1 = ApeCli;
                this.estadoPreRadicaPeritoVP1 = true;
            } else if ("".equals(numeroDocPerito2)) {
                this.numeroDocPerito2 = DocCli;
                this.nombrePerito2 = NomCli;
                this.apellidoPerito2 = ApeCli;
                this.estadoPreRadicaPeritoVP2 = true;
            } else if ("".equals(numeroDocPerito3)) {
                this.numeroDocPerito3 = DocCli;
                this.nombrePerito3 = NomCli;
                this.apellidoPerito3 = ApeCli;
                this.estadoPreRadicaPeritoVP3 = true;
            } else if ("".equals(numeroDocPerito4)) {
                this.numeroDocPerito4 = DocCli;
                this.nombrePerito4 = NomCli;
                this.apellidoPerito4 = ApeCli;
                this.estadoPreRadicaPeritoVP4 = true;
            } else {
                this.numeroDocPerito5 = DocCli;
                this.nombrePerito5 = NomCli;
                this.apellidoPerito5 = ApeCli;
                this.estadoPreRadicaPeritoVP5 = true;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".llenadoInformacionPerito()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta si un empleado tiene el rol de gestor
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param Id int que contiene el codigo del empleado
     * @since 01-11-2014
     */
    public void rolGestor(int Id) {
        try {
            Dat = Perm.consulRolEmpleado(Id, "Gestor");
            this.estadoActivacionGestor = Dat.next();
            Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".rolGestor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que verifica sin un empleado tiene Rol de Radicador
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    private void RolRadicador(int Id) {
        try {
            Dat = Perm.consulRolEmpleado(Id, "Radicador");
            this.estadoActivacionRadicador = Dat.next();
            Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".RolRadicador()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }
//

    /**
     * Metodo que se utiliza para anulacion de la Radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void anulaPreRad() {
        try {
            if ("".equals(PreRad.getObservacionAnulaPreRadica())) {
                mbTodero.setMens("Falta informacion por Llenar");
                mbTodero.warn();
            } else {
                int CodPR = PreRad.getCodPreRadica();
                PreRad.AnulaPreRad(mBsesion.codigoMiSesion());
                mbTodero.setMens("La PreRadicacion N*: " + CodPR + " ha sido anulada");
                mbTodero.info();
                this.ListPreRadicados = PreRad.ConsulPendientesRadicar(mBsesion.codigoMiSesion());
                RequestContext.getCurrentInstance().execute("PF('DlgAnulaPreRadicacion').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".anulaPreRad()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para realizar el cambio de estado de la PreRadicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void cambioEstadoPreRad() {
        try {
            if ("".equals(PreRad.getObservacionPreRadica()) || "".equals(PreRad.getEstado())) {
                mbTodero.setMens("Falta informacion por Llenar");
                mbTodero.warn();
            } else {
                PreRad.CambioEstPreRad(mBsesion.codigoMiSesion());//Lleva variable de Sesion
                mbTodero.setMens("La informacion ha sido guardada correctamente");
                mbTodero.info();
                borrarInfoCliente("");
                RequestContext.getCurrentInstance().execute("PF('DlgEstPreRadica').hide()");
                ListPreRadicados = PreRad.ConsulPendientesRadicar(1);//VARIABLES DE SESION
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambioEstadoPreRad()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite cambiar el estado de la pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param estado String que contiene el estado del avaluo
     * @param codigo String que contiene el numero de pre-radicacion
     * @since 01-11-2014
     */
    //GCH - AGO2016 - SE INCLUYO EXCEPTION
    public void cambioestado(String estado, String codigo) throws AbortProcessingException {
        try {
            switch (estado) {

                case "Impedido":
                    if ("".equals(this.ObserCambioEstadoImpedirDesdePreRad)) {
                        mbTodero.setMens("Debe ingresar información en el campo 'Observación'");
                        mbTodero.info();

                        throw new AbortProcessingException(); //GCH

                    } else {
                        PreRad.setObservacionPreRadica(ObserCambioEstadoImpedirDesdePreRad + " - IMPEDIDO PRE-RADICACIÓN");
                        PreRad.setEstado("I");
                        PreRad.setCodPreRadica(Integer.parseInt(codigo));
                        PreRad.CambioEstPreRad(mBsesion.codigoMiSesion());
                        mbTodero.setMens("El registro numero " + codigo + " fue cambiado a estado 'IMPEDIDO'");
                        mbTodero.info();
                        //RequestContext.getCurrentInstance().execute("PF('DlgEstadoPreRad').hide()");
                    }
                    break;
                case "En Proceso":
                    PreRad.setObservacionPreRadica(ObserCambioEstadoPreRadica + " - LIBERADO EN PROCESO PRE-RADICACIÓN");
                    PreRad.setEstado("P");
                    PreRad.setCodPreRadica(Integer.parseInt(codigo));
                    PreRad.CambioEstPreRad(mBsesion.codigoMiSesion());
                    mbTodero.setMens("El registro numero " + codigo + " fue cambiado a estado 'EN PROCESO'");
                    mbTodero.info();
                    RequestContext.getCurrentInstance().execute("PF('DlgEstadoPreRad').hide()");
                    break;
                case "AnuladoPreRadica":

                    if (this.codMotivoAnulacionPreRadica == 0) {
                        mbTodero.setMens("Debe seleccionar información del campo 'Motivo'");
                        mbTodero.info();

                        throw new AbortProcessingException(); //GCH

                    } else if ("".equals(this.ObserCambioEstadoPreRadica)) {
                        mbTodero.setMens("Debe ingresar información en el campo 'Observación'");
                        mbTodero.info();

                        throw new AbortProcessingException(); //GCH

                    } else {
                        PreRad.setCodMotivoAnulacion(codMotivoAnulacionPreRadica);
                        PreRad.setObservacionAnulaPreRadica(ObserCambioEstadoPreRadica + " - ANULACIÓN PRE-RADICACIÓN");
                        PreRad.setEstado("N");
                        PreRad.setCodPreRadica(Integer.parseInt(codigo));
                        PreRad.AnulaPreRad(mBsesion.codigoMiSesion());
                        String mens = "El registro numero " + codigo + " fue anulado correctamente"; //GCH
                        mbTodero.setMens(mens);
                        mbTodero.info();
                        //RequestContext.getCurrentInstance().execute("PF('DlgEstadoPreRad').hide()"); //GCH
                    }
                    break;

                case "AnuladoPreRadicaRadica":
                    if (this.codMotivoAnulacionPreRadicaRadica == 0) {
                        mbTodero.setMens("Debe seleccionar información del campo 'Motivo'");
                        mbTodero.info();

                        throw new AbortProcessingException("Debe seleccionar información del campo 'Motivo'"); //GCH

                    } else if ("".equals(this.ObserCambioEstadoPreRadicaRadica)) {
                        mbTodero.setMens("Debe ingresar información en el campo 'Observación'");
                        mbTodero.info();

                        throw new AbortProcessingException("Debe ingresar información en el campo 'Observación'"); //GCH

                    } else {
                        PreRad.setCodMotivoAnulacion(codMotivoAnulacionPreRadica);
                        PreRad.setObservacionAnulaPreRadica(ObserCambioEstadoPreRadicaRadica + " - ANULACIÓN PRE-RADICACIÓN");
                        PreRad.setEstado("N");
                        PreRad.setCodPreRadica(Integer.parseInt(codigo));
                        PreRad.AnulaPreRad(mBsesion.codigoMiSesion());
                        mbTodero.setMens("El registro numero " + codigo + " fue anulado correctamente");
                        mbTodero.info();
                        //RequestContext.getCurrentInstance().execute("PF('DlgEstadoPreRad').hide()"); //GCH
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambioestado()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para Llenar las Observaciones de la PreRadicacion Consultada
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void obserPreRadicacion() {
        try {
            ListObserPreRadicados = PreRad.ConsulObserPreRadica();
            RequestContext.getCurrentInstance().execute("PF('DlgObserPreRad').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".obserPreRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que realiza el borrado de variables
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param proceso String que contiene el dato para poder borrar toda la
     * información del formulario
     * @since 01-11-2014
     */
    public void borrarInfoCliente(String proceso) {
        try {
            if ("Todo".equals(proceso)) {
                mBCliente.setOpcionMostrarCliSolicitante("");
                mBCliente.setEstadoPanelClienteGeneral(false);
            }

            mBCliente.setEstadocajasAgregarF1(false);
            mBCliente.setEstadocajasAgregarF2(false);
            mBCliente.setEstadocajasAgregarF3(false);
            mBCliente.setEstadocajasAgregarF4(false);

            mBCliente.setClientFact1(false);
            mBCliente.setClientFact2(false);
            mBCliente.setClientFact3(false);
            mBCliente.setClientFact4(false);
            mBCliente.setClientFact5(false);

            this.EstadoFuncionario1 = false;
            this.EstadoFuncionario2 = false;
            this.EstadoFuncionario3 = false;
            this.EstadoFuncionario4 = false;
            this.EstadoFuncionario5 = false;

            mBCliente.setFuncionario1(false);
            mBCliente.setFuncionario2(false);
            mBCliente.setFuncionario3(false);
            mBCliente.setFuncionario4(false);
            mBCliente.setFuncionario5(false);

            mBCliente.setNombreCliente1("");
            mBCliente.setNombreCliente2("");
            mBCliente.setNombreCliente3("");
            mBCliente.setNombreCliente4("");
            mBCliente.setNombreCliente5("");

            mBCliente.setNumeroDoccliente1("");
            mBCliente.setNumeroDoccliente2("");
            mBCliente.setNumeroDoccliente3("");
            mBCliente.setNumeroDoccliente4("");
            mBCliente.setNumeroDoccliente5("");

            mBCliente.setTelefonoCliente1("");
            mBCliente.setTelefonoCliente2("");
            mBCliente.setTelefonoCliente3("");
            mBCliente.setTelefonoCliente4("");
            mBCliente.setTelefonoCliente5("");

            mBCliente.setEstadoConsultaCliente1(false);
            mBCliente.setEstadoConsultaCliente2(false);
            mBCliente.setEstadoConsultaCliente3(false);
            mBCliente.setEstadoConsultaCliente4(false);
            mBCliente.setEstadoConsultaCliente5(false);

            mBCliente.setMensajeConsultaCliente1("");
            mBCliente.setMensajeConsultaCliente2("");
            mBCliente.setMensajeConsultaCliente3("");
            mBCliente.setMensajeConsultaCliente4("");
            mBCliente.setMensajeConsultaCliente5("");

            mBCliente.setFila1(false);
            mBCliente.setFila2(false);
            mBCliente.setFila3(false);
            mBCliente.setFila4(false);
            mBCliente.setFila5(false);

            mBCliente.setEditar1(false);
            mBCliente.setEditar2(false);
            mBCliente.setEditar3(false);
            mBCliente.setEditar4(false);
            mBCliente.setEditar5(false);

            mBCliente.setAnalizar1(true);
            mBCliente.setAnalizar2(false);
            mBCliente.setAnalizar3(false);
            mBCliente.setAnalizar4(false);
            mBCliente.setAnalizar5(false);

            mBCliente.setAceptar1(true);
            mBCliente.setAceptar2(false);
            mBCliente.setAceptar3(false);
            mBCliente.setAceptar4(false);
            mBCliente.setAceptar5(false);

            mBCliente.setQuitar1(true);
            mBCliente.setQuitar2(false);
            mBCliente.setQuitar3(false);
            mBCliente.setQuitar4(false);
            mBCliente.setQuitar5(false);

            mBCliente.setOtro1(true);
            mBCliente.setOtro2(false);
            mBCliente.setOtro3(false);
            mBCliente.setOtro4(false);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".borrarInfoCliente()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia las variables utilizadas en las entidades
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    private void limpiarEntidades(String proceso) {

        try {
            if ("Todo".equals(proceso)) {
                mBEntidad.setEstPanelEntidad(false);

                //limpia el combo de entidades en general
                mBEntidad.getCargaEnt().clear();
                mBEntidad.setCargaEnt(mBEntidad.cargEntidad());

                //Fila 1
                mBEntidad.getCargaSucur1().clear();
                mBEntidad.getCargaAses1().clear();
                mBEntidad.setEntidadFact1(false);
                mBEntidad.setCodEntidad1("");
                mBEntidad.setCodSucursal1("");
                mBEntidad.setCodAsesor1("");
                mBEntidad.setTelefonoAsesor1("");
                mBEntidad.setMailAsesor1("");

                mBEntidad.setOpcionRadioTarifasPactadosEnti1("");
                mBEntidad.setTipoTarifaEnti1("");
                mBEntidad.setValorTarifaEnti1("");
                mBEntidad.setEstadoAgregarTarifasPactEnti1(false);
                mBEntidad.setEstadoTarifasPactEnti1(false);

                //Fila 2
                mBEntidad.getCargaSucur2().clear();
                mBEntidad.getCargaAses2().clear();
                mBEntidad.setEstadoAgregarFilasEntidad2(false);
                mBEntidad.setEntidadFact2(false);
                mBEntidad.setCodEntidad2("");
                mBEntidad.setCodSucursal2("");
                mBEntidad.setCodAsesor2("");
                mBEntidad.setTelefonoAsesor2("");
                mBEntidad.setMailAsesor2("");

                mBEntidad.setOpcionRadioTarifasPactadosEnti2("");
                mBEntidad.setTipoTarifaEnti2("");
                mBEntidad.setValorTarifaEnti2("");
                mBEntidad.setEstadoAgregarTarifasPactEnti2(false);
                mBEntidad.setEstadoTarifasPactEnti2(false);

                //Fila 3
                mBEntidad.getCargaSucur3().clear();
                mBEntidad.getCargaAses3().clear();
                mBEntidad.setEstadoAgregarFilasEntidad3(false);
                mBEntidad.setEntidadFact3(false);
                mBEntidad.setCodEntidad3("");
                mBEntidad.setCodSucursal3("");
                mBEntidad.setCodAsesor3("");
                mBEntidad.setTelefonoAsesor3("");
                mBEntidad.setMailAsesor3("");

                mBEntidad.setOpcionRadioTarifasPactadosEnti3("");
                mBEntidad.setTipoTarifaEnti3("");
                mBEntidad.setValorTarifaEnti3("");
                mBEntidad.setEstadoAgregarTarifasPactEnti3(false);
                mBEntidad.setEstadoTarifasPactEnti3(false);

                //Fila 4
                mBEntidad.getCargaSucur4().clear();
                mBEntidad.getCargaAses4().clear();
                mBEntidad.setEstadoAgregarFilasEntidad4(false);
                mBEntidad.setEntidadFact4(false);
                mBEntidad.setCodEntidad4("");
                mBEntidad.setCodSucursal4("");
                mBEntidad.setCodAsesor4("");
                mBEntidad.setTelefonoAsesor4("");
                mBEntidad.setMailAsesor4("");

                mBEntidad.setOpcionRadioTarifasPactadosEnti4("");
                mBEntidad.setTipoTarifaEnti4("");
                mBEntidad.setValorTarifaEnti4("");
                mBEntidad.setEstadoAgregarTarifasPactEnti4(false);
                mBEntidad.setEstadoTarifasPactEnti4(false);

                //Fila 5
                mBEntidad.getCargaSucur5().clear();
                mBEntidad.getCargaAses5().clear();
                mBEntidad.setEstadoAgregarFilasEntidad5(false);
                mBEntidad.setEntidadFact5(false);
                mBEntidad.setCodEntidad5("");
                mBEntidad.setCodSucursal5("");
                mBEntidad.setCodAsesor5("");
                mBEntidad.setTelefonoAsesor5("");
                mBEntidad.setMailAsesor5("");

                mBEntidad.setOpcionRadioTarifasPactadosEnti5("");
                mBEntidad.setTipoTarifaEnti5("");
                mBEntidad.setValorTarifaEnti5("");
                mBEntidad.setEstadoAgregarTarifasPactEnti5(false);
                mBEntidad.setEstadoTarifasPactEnti5(false);

                mBEntidad.setEstadoInfoAsesor1(false);
                mBEntidad.setEstadoInfoAsesor2(false);
                mBEntidad.setEstadoInfoAsesor3(false);
                mBEntidad.setEstadoInfoAsesor4(false);
                mBEntidad.setEstadoInfoAsesor5(false);

                mBEntidad.setAceptarFila1(false);
                mBEntidad.setAceptarFila2(false);
                mBEntidad.setAceptarFila3(false);
                mBEntidad.setAceptarFila4(false);
                mBEntidad.setAceptarFila5(false);

                mBEntidad.setAgregarFila1(true);
                mBEntidad.setAgregarFila2(false);
                mBEntidad.setAgregarFila3(false);
                mBEntidad.setAgregarFila4(false);

                mBEntidad.setQuitarFila1(true);
                mBEntidad.setQuitarFila2(false);
                mBEntidad.setQuitarFila3(false);
                mBEntidad.setQuitarFila4(false);
                mBEntidad.setQuitarFila5(false);

                mBEntidad.setEditarFila1(false);
                mBEntidad.setEditarFila2(false);
                mBEntidad.setEditarFila3(false);
                mBEntidad.setEditarFila4(false);
                mBEntidad.setEditarFila5(false);

                mBEntidad.setDeshabilitarAsesor1(false);
                mBEntidad.setDeshabilitarAsesor2(false);
                mBEntidad.setDeshabilitarAsesor3(false);
                mBEntidad.setDeshabilitarAsesor4(false);
                mBEntidad.setDeshabilitarAsesor5(false);
            } else {
                mbTodero.setMens("Tatiana");
                mbTodero.info();
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia y consulta la informacion en listas para agregarlo en
     * tablas
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void tablas() {
        try {
            this.ListPreRadicados.clear();
            this.ListPreRadicados = PreRad.ConsulPendientesRadicar(mBsesion.codigoMiSesion());
            this.ListPreRadicadosGestor.clear();
            this.ListPreRadicadosGestor = PreRad.ConsulRadicaGestor(mBsesion.codigoMiSesion());
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".tablas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limpia la informacion de toda la pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void cleear() {
        try {
            PreRad = new LogPreRadicacion();
            //Solicitud        
            this.FechaSol = null;
            this.HoraSol = null;
            PreRad.setNumHojas(1);
            PreRad.setSolicitante("");
            PreRad.setEnvioInformacion(0);
            mBAdmi.Adm.setCodProEnt(0);
            mBAdmi.Adm.setCodTipProEnt(0);
            RequiereEntidad = false;
            Cotizacion = false;
            RequiereUbicacion = false;
            Ubicacion = false;
            mBAvaluo.setCodDeptoSol("");
        
            mBAvaluo.setCodCiudadSol("");
            mBAvaluo.getCargaCiuSolic().clear();

            borrarInfoCliente("Todo");
            limpiarEntidades("Todo");

            mBEntidad.setOpcionMostrarEntidad("");
            mBEntidad.setEstPanelEntidad(false);

            mBEntidad.Ent.setCodEntidad(0);
            mBEntidad.Ent.setCodSucursal(0);
            mBEntidad.Ent.setCodAsesor(0);

            mBAvaluo.setEstadoPanelGrande(false);
            mBAvaluo.setCodTipAvaluo(0);
            mBAvaluo.setEstadoPanel(false);
            mBAvaluo.setEstadoInfoPred(false);
            mBAvaluo.setEstadoInfoMaqui(false);
            mBAvaluo.setEstadoInfoResulEnser(false);
            mBAvaluo.setCodTipoBien("");
            Ava.setLlaveTB("");
            mBAvaluo.setDireccionAval("");
           
       
            mBAvaluo.setEstadoTablasResulPred(false);
            mBAvaluo.setEstadoTablasResulEnser(false);
            mBAvaluo.setEstadoTablasResulMaqui(false);
            mBAvaluo.setSelectPredios(null);
            mBAvaluo.setSelectMaquinaria(null);
            mBAvaluo.setSelectEnseres(null);
            this.Cli = new LogCliente();
            this.fechaSoli = "";
            this.estadoPanelDialogSolicitante = false;

            this.estadoPanelDialogEntidad = false;
            Ent.setNombreEntidad("");
            Ent.setNombreOfic("");
            Ent.setTelefonoOfic("");
            Ent.setNombreAsesor("");
            Ent.setCargoAsesor("");
            Adm.setNombreProEnt("");
            Adm.setNombre_TipProEnt("");
            mBAvaluo.setSelectPredios(null);
            mBAvaluo.setSelectMaquinaria(null);
            mBAvaluo.setSelectEnseres(null);
            this.estadoPanelDialogTempBien = false;
            Ava.setNomTipAvaluo("");
            PreRad.setObservacionPreRadica("");
            this.botondem = true;
            mBEntidad.setOpcionRadiosMostrarEntidad(true);
            mBCliente.setEstadoPanelRadiosCli(true);
            //   this.ListPreRadicados.clear();
            //  this.ListPreRadicados = PreRad.ConsulPendientesRadicar(mBsesion.codigoMiSesion());
            mBAdmi.getProEnt().clear();
            mBAdmi.getConsulProEnt();

            this.RequiereEntidad = false;

            this.CodAnalista = "0";
            this.ObservaRegistro = "";
            this.NombreAnalista = "";
            this.Cotizacion = false;

            this.ExisteInformacion = false;
            this.opcionRadioEstudio = "";
            this.siEstudio = false;

            this.ObserCambioEstadoPreRadica = "";
            this.ObserCambioEstadoPreRadicaRadica = "";

            this.ObservaPreRad = "";
            opcionRadioEstados = "";
            PreRadica = new LogPreRadicacion();
            NombreAnalista = "";

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cleear()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que cierra el boton que aparecia
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param estadoboton
     * @since 01-11-2014
     * @deprecated
     */
    public void cerrarboton(Integer estadoboton) {
        if (estadoboton == 1) {
            this.botondem = true;
        } else if (estadoboton == 2) {
            this.botondem = true;
        }
    }

    /**
     * Metodo que valida la informacion de cuando se agrega un cliente o
     * entidad.
     *
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void validarsolicitado() {
        try {
            if ("Entidad".equals(PreRad.getSolicitante())) {
                mBEntidad.setOpcionMostrarEntidad("Si1");
                mBEntidad.setEstPanelEntidad(true);
                mBEntidad.setOpcionRadiosMostrarEntidad(false);
                RequiereEntidad = true;
                mBCliente.setOpcionMostrarCliSolicitante("");
                mBCliente.setEstadoPanelClienteGeneral(false);
                mBCliente.setEstadoPanelRadiosCli(true);

                mBEntidad.setCodEntidad1("");
                mBEntidad.setCodEntidad2("");
                mBEntidad.setCodEntidad3("");
                mBEntidad.setCodEntidad4("");
                mBEntidad.setCodEntidad5("");
                mBEntidad.setCodSucursal1("");
                mBEntidad.setCodSucursal2("");
                mBEntidad.setCodSucursal3("");
                mBEntidad.setCodSucursal4("");
                mBEntidad.setCodSucursal5("");
                mBEntidad.setCodAsesor1("");
                mBEntidad.setCodAsesor2("");
                mBEntidad.setCodAsesor3("");
                mBEntidad.setCodAsesor4("");
                mBEntidad.setCodAsesor5("");

                mBEntidad.setEntidadFact1(false);
                mBEntidad.setEntidadFact2(false);
                mBEntidad.setEntidadFact3(false);
                mBEntidad.setEntidadFact4(false);
                mBEntidad.setEntidadFact5(false);

            }
            if ("Cliente".equals(PreRad.getSolicitante())) {
                mBCliente.setOpcionMostrarCliSolicitante("Si");
                mBCliente.setEstadoPanelClienteGeneral(true);
                mBCliente.setEstadoPanelRadiosCli(false);

                mBEntidad.setOpcionMostrarEntidad("");
                mBEntidad.setEstPanelEntidad(false);
                mBEntidad.setOpcionRadiosMostrarEntidad(true);

                mBCliente.setEstadoPanelClienteGeneral(true);

                mBCliente.setFila1(false);
                mBCliente.setFila2(true);
                mBCliente.setFila3(true);
                mBCliente.setFila4(true);
                mBCliente.setFila5(true);

                mBCliente.setEditar1(false);
                mBCliente.setEditar2(false);
                mBCliente.setEditar3(false);
                mBCliente.setEditar4(false);
                mBCliente.setEditar5(false);

                mBCliente.setAceptar1(false);
                mBCliente.setAceptar2(false);
                mBCliente.setAceptar3(false);
                mBCliente.setAceptar4(false);
                mBCliente.setAceptar5(false);

                mBCliente.setAnalizar1(true);
                mBCliente.setAnalizar2(false);
                mBCliente.setAnalizar3(false);
                mBCliente.setAnalizar4(false);
                mBCliente.setAnalizar5(false);

                mBCliente.setQuitar1(true);
                mBCliente.setQuitar2(false);
                mBCliente.setQuitar3(false);
                mBCliente.setQuitar4(false);
                mBCliente.setQuitar5(false);

                mBCliente.setOtro1(true);
                mBCliente.setOtro2(false);
                mBCliente.setOtro3(false);
                mBCliente.setOtro4(false);

                mBCliente.setEstadocajasAgregarF1(false);
                mBCliente.setEstadocajasAgregarF2(false);
                mBCliente.setEstadocajasAgregarF3(false);
                mBCliente.setEstadocajasAgregarF4(false);

            }
            if (this.RequiereEntidad == true) {

                mBEntidad.setOpcionMostrarEntidad("Si1");
                mBEntidad.setEstPanelEntidad(true);
                mBEntidad.setOpcionRadiosMostrarEntidad(false);

//              this.opcionRadioSolicitante = "";
//            this.estadoPanelClienteGeneral = false;
//            this.radioPersona = true;
                mBEntidad.setCodEntidad1("");
                mBEntidad.setCodEntidad2("");
                mBEntidad.setCodEntidad3("");
                mBEntidad.setCodEntidad4("");
                mBEntidad.setCodEntidad5("");
                mBEntidad.setCodSucursal1("");
                mBEntidad.setCodSucursal2("");
                mBEntidad.setCodSucursal3("");
                mBEntidad.setCodSucursal4("");
                mBEntidad.setCodSucursal5("");
                mBEntidad.setCodAsesor1("");
                mBEntidad.setCodAsesor2("");
                mBEntidad.setCodAsesor3("");
                mBEntidad.setCodAsesor4("");
                mBEntidad.setCodAsesor5("");

                mBEntidad.setEntidadFact1(false);
                mBEntidad.setEntidadFact2(false);
                mBEntidad.setEntidadFact3(false);
                mBEntidad.setEntidadFact4(false);
                mBEntidad.setEntidadFact5(false);

            } else if (this.RequiereEntidad == false && !"Entidad".equals(PreRad.getSolicitante())) {

                mBEntidad.setOpcionMostrarEntidad("");
                mBEntidad.setEstPanelEntidad(false);

                mBEntidad.setAceptarFila1(false);
                mBEntidad.setAceptarFila2(false);
                mBEntidad.setAceptarFila3(false);
                mBEntidad.setAceptarFila4(false);
                mBEntidad.setAceptarFila5(false);

                mBEntidad.setAgregarFila1(true);
                mBEntidad.setAgregarFila2(false);
                mBEntidad.setAgregarFila3(false);
                mBEntidad.setAgregarFila4(false);

                mBEntidad.setQuitarFila1(true);
                mBEntidad.setQuitarFila2(false);
                mBEntidad.setQuitarFila3(false);
                mBEntidad.setQuitarFila4(false);
                mBEntidad.setQuitarFila5(false);

                mBEntidad.setEditarFila1(false);
                mBEntidad.setEditarFila2(false);
                mBEntidad.setEditarFila3(false);
                mBEntidad.setEditarFila4(false);
                mBEntidad.setEditarFila5(false);

                mBEntidad.setEstadoAgregarFilasEntidad1(true);
                mBEntidad.setEstadoAgregarFilasEntidad2(false);
                mBEntidad.setEstadoAgregarFilasEntidad3(false);
                mBEntidad.setEstadoAgregarFilasEntidad4(false);
                mBEntidad.setEstadoAgregarFilasEntidad5(false);

                mBEntidad.setDeshabilitarAsesor1(false);
                mBEntidad.setDeshabilitarAsesor2(false);
                mBEntidad.setDeshabilitarAsesor3(false);
                mBEntidad.setDeshabilitarAsesor4(false);
                mBEntidad.setDeshabilitarAsesor5(false);

                mBEntidad.setEntidadFact1(false);
                mBEntidad.setEntidadFact2(false);
                mBEntidad.setEntidadFact3(false);
                mBEntidad.setEntidadFact4(false);
                mBEntidad.setEntidadFact5(false);
                mBEntidad.setEstadoInfoAsesor1(false);
                mBEntidad.setEstadoInfoAsesor2(false);
                mBEntidad.setEstadoInfoAsesor3(false);
                mBEntidad.setEstadoInfoAsesor4(false);
                mBEntidad.setEstadoInfoAsesor5(false);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarsolicitado()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que valida la informacion de el solicitante, si es cliente o
     * entudad
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void validarsolicitadoPreRad() {
        try {
            if ("Entidad".equals(PreRad.getSolicitante())) {

                mBEntidad.setOpcionMostrarEntidad("Si1");
                mBEntidad.setEstPanelEntidad(true);
                mBEntidad.setOpcionRadiosMostrarEntidad(false);

                if (mBCliente.isEstadoPanelClienteGeneral() == true) {
                    mBCliente.setOpcionMostrarCliSolicitante("Si");
                    mBCliente.setEstadoPanelRadiosCli(true);
                } else {
                    mBCliente.setOpcionMostrarCliSolicitante("");
                    mBCliente.setEstadoPanelRadiosCli(true);
                }
            }
            if ("Cliente".equals(PreRad.getSolicitante())) {
                mBCliente.setOpcionMostrarCliSolicitante("Si");
                mBCliente.setEstadoPanelClienteGeneral(true);
                mBCliente.setEstadoPanelRadiosCli(false);

                if (mBEntidad.isEstPanelEntidad() == true) {
                    mBEntidad.setOpcionMostrarEntidad("Si1");
                    mBEntidad.setOpcionRadiosMostrarEntidad(true);
                } else {
                    mBEntidad.setOpcionMostrarEntidad("");
                    mBEntidad.setOpcionRadiosMostrarEntidad(true);
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarsolicitadoPreRad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que verifica que se habiliten los cuando se requiere de ubicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void validarUbicacion() {
        try {
            if (this.RequiereUbicacion == true) {
                this.Ubicacion = true;
                mBAvaluo.setCodDeptoSol("0");
                mBAvaluo.setCodCiudadSol("0");
            } else if (this.RequiereUbicacion == false) {
                this.Ubicacion = false;
                mBAvaluo.setCodDeptoSol("0");
                mBAvaluo.setCodCiudadSol("0");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarUbicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite validad si se selecciona a un cliente como funcionario
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param fila int que contiene el numero de la fila
     * @since 01-11-2014
     */
    public void validarfuncionario(int fila) {
        try {
            if (fila == 1) {
                if (mBCliente.isClientFact1() == true) {
                    EstadoFuncionario1 = true;
                } else if (mBCliente.isClientFact1() == false) {
                    EstadoFuncionario1 = false;
                }
            } else if (fila == 2) {
                if (mBCliente.isClientFact2() == true) {
                    EstadoFuncionario2 = true;
                } else if (mBCliente.isClientFact2() == false) {
                    EstadoFuncionario2 = false;
                }
            } else if (fila == 3) {
                if (mBCliente.isClientFact3() == true) {
                    EstadoFuncionario3 = true;
                } else if (mBCliente.isClientFact3() == false) {
                    EstadoFuncionario3 = false;
                }
            } else if (fila == 4) {
                if (mBCliente.isClientFact4() == true) {
                    EstadoFuncionario4 = true;
                } else if (mBCliente.isClientFact4() == false) {
                    EstadoFuncionario4 = false;
                }
            } else if (fila == 5) {
                if (mBCliente.isClientFact5() == true) {
                    EstadoFuncionario5 = true;
                } else if (mBCliente.isClientFact5() == false) {
                    EstadoFuncionario5 = false;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarFuncionario()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre el formulario tipo dialogo que permite la generacion de
     * una direccion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void abrirDialogDirecciones() {
        try {
            /*
             Map<String, Object> options = new HashMap<>();
             options.put("modal", true);
             options.put("resizable", false);
             options.put("contentHeight", 500);
             options.put("contentWidth", 800);
             RequestContext.getCurrentInstance().openDialog("XHTML/Radicacion/Direcciones.xhtml", options, null);*/
            mBAvaluo.limpiarTodosCamposDirecciones();
     
            RequestContext.getCurrentInstance().execute("PF('DLGDireccion').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogDirecciones()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga la informacion de los analistas
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return ArrayList que carga los analistas de la preradicacion
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> cargAnalista() {
        try {
            Iterator<LogPreRadicacion> Ite;
            Ite = PreRad.getAnalista(mBAdmi.Adm.getCodProEnt()).iterator();
            while (Ite.hasNext()) {
                LogPreRadicacion mbPreRad = Ite.next();
                this.CargaAnali.add(new SelectItem(mbPreRad.getCod_Analista(), mbPreRad.getNom_Analista()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargAnalista()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaAnali;
    }

    /**
     * Metodo tipo ajax que carga el metodo cargAnalista() para agregar a los
     * analistas
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void onAnalista() {
        try {
            PreRadi = new LogPreRadicacion();
            mbTodero.resetTable("FRegistro:AnalistaTable");

            ListAllMisAsignados.clear();
            ListAllMisAsignados = PreRadi.getConsultarAllAnalistas(mBAdmi.Adm.getCodProEnt(), 1);
            RequestContext.getCurrentInstance().execute("PF('DlgAnalista').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onAnalista()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo tipo ajax que carga los analistas de pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void onAnalistaPre() {
        try {
            mbTodero.resetTable("PreRadica:AnalistaTable");
            PreRadi = new LogPreRadicacion();
            PreRadica = new LogPreRadicacion();
            ListAllMisAsignados = new ArrayList<>();
            ListAllMisAsignados = PreRadi.getConsultarAllAnalistas(mBAdmi.Adm.getCodProEnt(), 2);
            PreRadica.setCod_Analista(mBsesion.codigoMiSesion());
            RequestContext.getCurrentInstance().execute("PF('DlgAnalista').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onAnalistaPre()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

   

    /**
     * Metodo que genera el estudio de pre-radicación, este estudio consiste en
     * la informacion que se pueda tener registrada de un avaluo anterormente
     * realizado, del cliente solicitante, si este ys ha realizado avaluos con
     * la organizacion, si tiene deudas pendientes por cancelar etc, ademas de
     * informacion sobre los avaluadores que se encuentren, y los campos cara
     * agregar tarifas pactadas tanto a clientes como a los avaluadores
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param event ActionEvent que recive el escuchador de el evento que se
     * genera cuando se ejecuta la accion sobre el componente que llama al
     * metodo
     * @since 01-11-2014
     */
    public void generarEstudio(ActionEvent event) {
        try {
            String cod_llave_bien = "0";
            Cli = new LogCliente();
            if ("Si".equals(opcionRadioEstudio)) {
                //Valida que exista un cliente registrado
                if ("".equals(mBCliente.getNumeroDoccliente1()) || mBCliente.getNumeroDoccliente1() == null) {
                    mbTodero.setMens("No se ha ingresado ningún cliente");
                    mbTodero.warn();
                    //Valida que exista un bien seleccionado                 
                } else if (mBAvaluo.getSelectPredios().isEmpty() && mBAvaluo.getSelectMaquinaria().isEmpty() && mBAvaluo.getSelectEnseres().isEmpty() && mBAvaluo.getCodTipAvaluo() != 0) {
//                    mbTodero.setMens("No se ha seleccionado ningun bien");
//                    mbTodero.warn();  

                    //Consulta para el Cliente
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente1());
                    if (Dat.next()) {
                        cod_cliente = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.CloseCon();
                    Dat = PreRad.consultaEstudioCliente(cod_cliente, cod_preRadica);

                    HashMap parametros = new HashMap<>();

                    parametros.put("cod_cliente", String.valueOf(cod_cliente));
                    parametros.put("cod_pre_radica", String.valueOf(cod_preRadica));
                    parametros.put("llave_bien", cod_llave_bien);

                    if (Dat.next()) {
              
                    } else {
                        mbTodero.setMens("No se tiene información sobre el cliente ingresado");
                        mbTodero.info();
                        //Generar otro reporte
                    }
    
                    Conexion.CloseCon();
                } else {
                    cod_llave_bien = "";

                    //Consulta para el Cliente
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente1());
                    if (Dat.next()) {
                        cod_cliente = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.CloseCon();
                    Dat = PreRad.consultaEstudioCliente(cod_cliente, cod_preRadica);

                    //Consulta para el avaluo
                    if (mBAvaluo.getSelectPredios().size() > 0) {
                        if (mBAvaluo.getSelectPredios().size() == 1) {
                            cod_llave_bien = String.valueOf(mBAvaluo.getSelectPredios().get(0).getCodSeguimiento());
                        } else if (mBAvaluo.getSelectPredios().size() > 1) {
                            for (int i = 0; i <= mBAvaluo.getSelectPredios().size() - 1; i++) {
                                if (i == mBAvaluo.getSelectPredios().size() - 1) {
                                    cod_llave_bien = cod_llave_bien + String.valueOf(mBAvaluo.getSelectPredios().get(i).getCodSeguimiento());
                                } else {
                                    cod_llave_bien = cod_llave_bien + String.valueOf(mBAvaluo.getSelectPredios().get(i).getCodSeguimiento()) + ",";
                                }
                            }
                        }
                    }

                    if (mBAvaluo.getSelectMaquinaria().size() > 0) {
                        if (mBAvaluo.getSelectMaquinaria().size() == 1) {
                            cod_llave_bien = String.valueOf(mBAvaluo.getSelectMaquinaria().get(0).getCodSeguimiento());
                        } else if (mBAvaluo.getSelectMaquinaria().size() > 1) {
                            for (int i = 0; i <= mBAvaluo.getSelectMaquinaria().size() - 1; i++) {
                                if (i == mBAvaluo.getSelectMaquinaria().size() - 1) {
                                    cod_llave_bien = cod_llave_bien + String.valueOf(mBAvaluo.getSelectMaquinaria().get(i).getCodSeguimiento());
                                } else {
                                    cod_llave_bien = cod_llave_bien + String.valueOf(mBAvaluo.getSelectMaquinaria().get(i).getCodSeguimiento()) + ",";
                                }
                            }
                        }
                    }

                    if (mBAvaluo.getSelectEnseres().size() > 0) {
                        if (mBAvaluo.getSelectEnseres().size() == 1) {
                            cod_llave_bien = String.valueOf(mBAvaluo.getSelectEnseres().get(0).getCodSeguimiento());
                        } else if (mBAvaluo.getSelectEnseres().size() > 1) {
                            for (int i = 0; i <= mBAvaluo.getSelectEnseres().size() - 1; i++) {
                                if (i == mBAvaluo.getSelectEnseres().size() - 1) {
                                    cod_llave_bien = cod_llave_bien + String.valueOf(mBAvaluo.getSelectEnseres().get(i).getCodSeguimiento());
                                } else {
                                    cod_llave_bien = cod_llave_bien + String.valueOf(mBAvaluo.getSelectEnseres().get(i).getCodSeguimiento()) + ",";
                                }
                            }
                        }
                    }

                    HashMap parametros = new HashMap<>();

                    parametros.put("cod_cliente", String.valueOf(cod_cliente));
                    parametros.put("cod_pre_radica", String.valueOf(cod_preRadica));
                    parametros.put("llave_bien", cod_llave_bien);

                 
       
                    Conexion.CloseCon();
                }

            } else {
                mbTodero.setMens("No ha habilidato la opcion 'Si' Para realizar el estudio");
                mbTodero.warn();
            }

//            RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(1)");
//            RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(3)");
//            RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(3)");
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".generarEstudio()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que verifica, valida que todos los datos de la pre-radicación
     * esten ingresados correctamente, validando cada paso del flujo que se
     * maneja a travez de los tabs
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param form_num String que contiene un identificador para cada paso
     * siguiente y paso atras de los tabs
     * @since 01-11-2014
     */
    public void validarForm(String form_num) {
        try {
            if (("AdelanteTb0".equals(form_num))) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(1)");
            }
            if ("AdelanteTb1".equals(form_num)) {
                String validar1 = "";
                if ("".equals(validar1)) {
                    validar1 = "ok1";
                    if (this.FechaSol == null) {
                        mbTodero.setMens("Debe llenar el campo 'Fecha de solicitud'");
                        mbTodero.warn();
                        validar1 = "";
                    } else if (this.HoraSol == null) {
                        mbTodero.setMens("Debe llenar el campo 'Hora de solicitud'");
                        mbTodero.warn();
                        validar1 = "";
                    } else if (mBAdmi.Adm.getCodTipProEnt() == 0) {
                        mbTodero.setMens("Debe llenar el campo 'Tipo de producto entidad'");
                        mbTodero.warn();
                        validar1 = "";
                    } else if ("".equals(this.PreRad.getSolicitante())) {
                        mbTodero.setMens("Debe llenar el campo 'Remitido por'");
                        mbTodero.warn();
                        validar1 = "";
                    } else if (this.PreRad.getEnvioInformacion() == 0) {
                        mbTodero.setMens("Debe llenar el campo 'Enviar A'");
                        mbTodero.warn();
                        validar1 = "";
                    } else if ("".equals(this.OtroQuienEnviarA) && "Otro".equals(this.EnviarALabel)) {
                        mbTodero.setMens("Debe especificar quien sera la persona a enviar el avalúo");
                        mbTodero.warn();
                        validar1 = "";
                    } else if ("".equals(this.OtroUbicacionEnviarA) && "Otro".equals(this.EnviarALabel)) {
                        mbTodero.setMens("Debe especificar la ubicación a donde enviar el avalúo");
                        mbTodero.warn();
                        validar1 = "";
                    } else if ("".equals(this.OtroDireccionEnviarA) && "Otro".equals(this.EnviarALabel)) {
                        mbTodero.setMens("Debe especificar la dirección a donde enviar el avalúo");
                        mbTodero.warn();
                        validar1 = "";
                    } else if (this.PreRad.getNumHojas() == 0) {
                        mbTodero.setMens("Debe llenar el campo 'Numero de Hojas'");
                        mbTodero.warn();
                        validar1 = "";
                    } else if (this.RequiereUbicacion == true) {
                        if ("0".equals(mBAvaluo.getCodDeptoSol())) {
                            mbTodero.setMens("Debe llenar el campo 'Departamento'");
                            mbTodero.warn();
                            validar1 = "";
                        } else if ("0".equals(mBAvaluo.getCodCiudadSol())) {
                            mbTodero.setMens("Debe llenar el campo 'Ciudad'");
                            mbTodero.warn();
                            validar1 = "";
                        }
                    }
                }
                if ("ok1".equals(validar1)) {
                    validar1 = "ok2";

                    if ("".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "".equals(mBEntidad.getOpcionMostrarEntidad()) || "No".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "No1".equals(mBEntidad.getOpcionMostrarEntidad())
                            || "".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "No1".equals(mBEntidad.getOpcionMostrarEntidad()) || "No".equals(mBCliente.getOpcionMostrarCliSolicitante()) && "".equals(mBEntidad.getOpcionMostrarEntidad())) {
                        mbTodero.setMens("Debe haber información en cliente o entidad");
                        mbTodero.warn();
                        validar1 = "ok1";
                        this.estadoPanelDialogSolicitante = false;
                        this.estadoPanelDialogEntidad = false;
                        this.estadoPanelDialogClienteFacturar = false;
                        this.estadoPanelDialogEntidadfactu = false;
                    } else if ("Si".equals(mBCliente.getOpcionMostrarCliSolicitante())) {
                        if ("".equals(mBCliente.getNombreCliente1()) || "".equals(mBCliente.getNumeroDoccliente1())
                                || "".equals(mBCliente.getTelefonoCliente1())) {
                            mbTodero.setMens("Debe llenar los datos del cliente correspondiente (1)");
                            mbTodero.warn();
                            validar1 = "ok1";
                        } else {
                            //Condicion del primer check
                            if (mBCliente.isClientFact1() == true) {
                                this.estadoPanelDialogClienteFacturar = true;
                                this.estadoPanelDialogSolicitante = true;
                                this.estadoPanelDialogEntidadfactu = false;
                            } else {
                                this.estadoPanelDialogSolicitante = true;
                            }
                            switch (mBCliente.getMensajeConsultaCliente1()) {
                                case "":
                                    mbTodero.setMens("Debe validar los datos del cliente");
                                    mbTodero.warn();
                                    validar1 = "ok1";
                                    break;
                                case "N/A":
                                    mbTodero.setMens("Debe realizar el registro del cliente o selecionar un cliente existente");
                                    mbTodero.warn();
                                    validar1 = "ok1";
                                    break;
                            }
                        }
                        if (mBCliente.isEstadocajasAgregarF1() == true) {
                            if ("".equals(mBCliente.getNombreCliente2()) || "".equals(mBCliente.getNumeroDoccliente2())
                                    || "".equals(mBCliente.getTelefonoCliente2())) {
                                mbTodero.setMens("Debe llenar los datos del cliente correspondiente (2)");
                                mbTodero.warn();
                                validar1 = "ok1";
                            } else {
                                //Condicion segundo check
                                if (mBCliente.isClientFact2() == true) {

                                    this.estadoPanelDialogClienteFacturar = true;
                                    this.estadoPanelDialogSolicitante = true;
                                    this.estadoPanelDialogEntidadfactu = false;
                                } else {
                                    this.estadoPanelDialogSolicitante = true;
                                }
                                switch (mBCliente.getMensajeConsultaCliente2()) {
                                    case "":
                                        mbTodero.setMens("Debe validar los datos del cliente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                    case "N/A":
                                        mbTodero.setMens("Debe realizar el registro del cliente o selecionar un cliente existente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                }
                            }
                        }
                        if (mBCliente.isEstadocajasAgregarF2() == true) {
                            if ("".equals(mBCliente.getNombreCliente3()) || "".equals(mBCliente.getNumeroDoccliente3())
                                    || "".equals(mBCliente.getTelefonoCliente3())) {
                                mbTodero.setMens("Debe llenar los datos del Cliente correspondiente (3)");
                                mbTodero.warn();
                                validar1 = "ok1";
                            } else {
                                //Condicion Tercer check
                                if (mBCliente.isClientFact3() == true) {

                                    this.estadoPanelDialogClienteFacturar = true;
                                    this.estadoPanelDialogSolicitante = true;
                                    this.estadoPanelDialogEntidadfactu = false;
                                } else {
                                    this.estadoPanelDialogSolicitante = true;
                                }
                                switch (mBCliente.getMensajeConsultaCliente3()) {
                                    case "":
                                        mbTodero.setMens("Debe validar los datos del cliente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                    case "N/A":
                                        mbTodero.setMens("Debe realizar el registro del cliente o selecionar un cliente existente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                }
                            }
                        }
                        if (mBCliente.isEstadocajasAgregarF3() == true) {
                            if ("".equals(mBCliente.getNombreCliente4()) || "".equals(mBCliente.getNumeroDoccliente4())
                                    || "".equals(mBCliente.getTelefonoCliente4())) {
                                mbTodero.setMens("Debe llenar los datos del cliente correspondiente (4)");
                                mbTodero.warn();
                                validar1 = "ok1";
                            } else {
                                //Condicion Cuarto check
                                if (mBCliente.isClientFact4() == true) {
                                    this.estadoPanelDialogClienteFacturar = true;
                                    this.estadoPanelDialogSolicitante = true;
                                    this.estadoPanelDialogEntidadfactu = false;
                                } else {
                                    this.estadoPanelDialogSolicitante = true;
                                }
                                switch (mBCliente.getMensajeConsultaCliente4()) {
                                    case "":
                                        mbTodero.setMens("Debe validar los datos del cliente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                    case "N/A":
                                        mbTodero.setMens("Debe realizar el registro del cliente o selecionar un cliente existente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                }
                            }
                        }
                        if (mBCliente.isEstadocajasAgregarF4() == true) {
                            if ("".equals(mBCliente.getNombreCliente5()) || "".equals(mBCliente.getNumeroDoccliente5())
                                    || "".equals(mBCliente.getTelefonoCliente5())) {
                                mbTodero.setMens("Debe llenar los datos del cliente correspondiente (5)");
                                mbTodero.warn();
                                validar1 = "ok1";
                            } else {
                                //Condicion Quinto check
                                if (mBCliente.isClientFact5() == true) {
                                    this.estadoPanelDialogClienteFacturar = true;
                                    this.estadoPanelDialogSolicitante = true;
                                    this.estadoPanelDialogEntidadfactu = false;
                                } else {
                                    this.estadoPanelDialogSolicitante = true;
                                }
                                switch (mBCliente.getMensajeConsultaCliente5()) {
                                    case "":
                                        mbTodero.setMens("Debe validar los datos del cliente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                    case "N/A":
                                        mbTodero.setMens("Debe realizar el registro del cliente o selecionar un cliente existente");
                                        mbTodero.warn();
                                        validar1 = "ok1";
                                        break;
                                }
                            }
                        }
                    }
                }
                if ("ok2".equals(validar1)) {
                    validar1 = "ok3";
                    if ("Si1".equals(mBEntidad.getOpcionMostrarEntidad())) {
                        if ("".equals(mBEntidad.getCodEntidad1()) || "".equals(mBEntidad.getCodSucursal1()) || "".equals(mBEntidad.getCodAsesor1())
                                || ("".equals(mBEntidad.getCodEntidad1()) && "".equals(mBEntidad.getCodSucursal1()) && "".equals(mBEntidad.getCodAsesor1()))) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (1)");
                            mbTodero.warn();
                            validar1 = "ok2";
                        } else /*if (!"".equals(mBEntidad.getCodEntidad1()) && !"".equals(mBEntidad.getCodSucursal1()) && !"".equals(mBEntidad.getCodAsesor1()))*/ {
                            this.estadoPanelDialogEntidad = true;
                            //Condicion del primer check
                            if (mBEntidad.isEntidadFact1() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt1 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad1()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal1()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor1()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu1(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu1(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu1(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu1(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu1(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu1(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad1(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad1(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal1(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal1(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor1(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor1(Dat.getString("Cargo_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt1 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad1()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal1()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor1()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad1(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad1(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal1(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal1(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor1(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor1(Dat.getString("Cargo_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }

                        if (mBEntidad.isEstadoAgregarFilasEntidad2() == true) {
                            if ("".equals(mBEntidad.getCodEntidad2()) || "".equals(mBEntidad.getCodSucursal2()) || "".equals(mBEntidad.getCodAsesor2())
                                    || ("".equals(mBEntidad.getCodEntidad2()) && "".equals(mBEntidad.getCodSucursal2()) && "".equals(mBEntidad.getCodAsesor2()))) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (2)");
                                mbTodero.warn();
                                validar1 = "ok2";
                            } else if (mBEntidad.isEntidadFact2() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt2 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad2()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal2()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor2()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu2(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu2(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu2(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu2(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu2(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu2(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad2(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad2(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal2(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal2(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor2(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor2(Dat.getString("Cargo_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt2 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad2()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal2()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor2()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad2(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad2(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal2(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal2(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor2(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor2(Dat.getString("Cargo_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }

                        if (mBEntidad.isEstadoAgregarFilasEntidad3() == true) {
                            if ("".equals(mBEntidad.getCodEntidad3()) || "".equals(mBEntidad.getCodSucursal3()) || "".equals(mBEntidad.getCodAsesor3())
                                    || ("".equals(mBEntidad.getCodEntidad3()) && "".equals(mBEntidad.getCodSucursal3()) && "".equals(mBEntidad.getCodAsesor3()))) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (3)");
                                mbTodero.warn();
                                validar1 = "ok2";
                            } else //Condicion Tercer check
                             if (mBEntidad.isEntidadFact3() == true) {
                                    this.estadoPanelDialogEntidadfactu = true;
                                    this.TipoTitulEnt3 = true;
                                    LogEntidad Enti = new LogEntidad();
                                    Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad3()));
                                    Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal3()));
                                    Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor3()));
                                    Dat = Enti.ConsulInfEntAsesor();
                                    if (Dat.next()) {
                                        mBEntidad.setNombreEntidadfactu3(Dat.getString("Nombre_Entidad"));
                                        mBEntidad.setDocumentoEntidadfactu3(Dat.getString("Documento_Entidad"));
                                        mBEntidad.setNombreSucursalfactu3(Dat.getString("Nombre_Oficina"));
                                        mBEntidad.setTelefonoSucursalfactu3(Dat.getString("Telefono_Oficina"));
                                        mBEntidad.setNombreAsesorfactu3(Dat.getString("Nombre_Asesor"));
                                        mBEntidad.setCargoAsesorfactu3(Dat.getString("Cargo_Asesor"));

                                        mBEntidad.setNombreEntidad3(Dat.getString("Nombre_Entidad"));
                                        mBEntidad.setDocumentoEntidad3(Dat.getString("Documento_Entidad"));
                                        mBEntidad.setNombreSucursal3(Dat.getString("Nombre_Oficina"));
                                        mBEntidad.setTelefonoSucursal3(Dat.getString("Telefono_Oficina"));
                                        mBEntidad.setNombreAsesor3(Dat.getString("Nombre_Asesor"));
                                        mBEntidad.setCargoAsesor3(Dat.getString("Cargo_Asesor"));

                                        this.estadoPanelDialogClienteFacturar = false;
                                    }
                                    Conexion.CloseCon();
                                } else {
                                    this.TipoTitulEnt3 = false;
                                    LogEntidad Enti = new LogEntidad();
                                    Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad3()));
                                    Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal3()));
                                    Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor3()));
                                    Dat = Enti.ConsulInfEntAsesor();
                                    if (Dat.next()) {
                                        mBEntidad.setNombreEntidad3(Dat.getString("Nombre_Entidad"));
                                        mBEntidad.setDocumentoEntidad3(Dat.getString("Documento_Entidad"));
                                        mBEntidad.setNombreSucursal3(Dat.getString("Nombre_Oficina"));
                                        mBEntidad.setTelefonoSucursal3(Dat.getString("Telefono_Oficina"));
                                        mBEntidad.setNombreAsesor3(Dat.getString("Nombre_Asesor"));
                                        mBEntidad.setCargoAsesor3(Dat.getString("Cargo_Asesor"));
                                    }
                                    Conexion.CloseCon();
                                }
                        }

                        if (mBEntidad.isEstadoAgregarFilasEntidad4() == true) {
                            if ("".equals(mBEntidad.getCodEntidad4()) || "".equals(mBEntidad.getCodSucursal4()) || "".equals(mBEntidad.getCodAsesor4())
                                    || ("".equals(mBEntidad.getCodEntidad4()) && "".equals(mBEntidad.getCodSucursal4()) && "".equals(mBEntidad.getCodAsesor4()))) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (4)");
                                mbTodero.warn();
                                validar1 = "ok2";
                            } else if (mBEntidad.isEntidadFact4() == true) {
                                this.estadoPanelDialogEntidadfactu = true;
                                this.TipoTitulEnt4 = true;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad4()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal4()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor4()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidadfactu4(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidadfactu4(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursalfactu4(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursalfactu4(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesorfactu4(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesorfactu4(Dat.getString("Cargo_Asesor"));

                                    mBEntidad.setNombreEntidad4(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad4(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal4(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal4(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor4(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor4(Dat.getString("Cargo_Asesor"));

                                    this.estadoPanelDialogClienteFacturar = false;
                                }
                                Conexion.CloseCon();
                            } else {
                                this.TipoTitulEnt4 = false;
                                LogEntidad Enti = new LogEntidad();
                                Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad4()));
                                Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal4()));
                                Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor4()));
                                Dat = Enti.ConsulInfEntAsesor();
                                if (Dat.next()) {
                                    mBEntidad.setNombreEntidad4(Dat.getString("Nombre_Entidad"));
                                    mBEntidad.setDocumentoEntidad4(Dat.getString("Documento_Entidad"));
                                    mBEntidad.setNombreSucursal4(Dat.getString("Nombre_Oficina"));
                                    mBEntidad.setTelefonoSucursal4(Dat.getString("Telefono_Oficina"));
                                    mBEntidad.setNombreAsesor4(Dat.getString("Nombre_Asesor"));
                                    mBEntidad.setCargoAsesor4(Dat.getString("Cargo_Asesor"));
                                }
                                Conexion.CloseCon();
                            }
                        }

                        if (mBEntidad.isEstadoAgregarTarifasPactEnti5() == true) {
                            if ("".equals(mBEntidad.getCodEntidad5()) || "".equals(mBEntidad.getCodSucursal5()) || "".equals(mBEntidad.getCodAsesor5())
                                    || ("".equals(mBEntidad.getCodEntidad5()) && "".equals(mBEntidad.getCodSucursal5()) && "".equals(mBEntidad.getCodAsesor5()))) {
                                mbTodero.setMens("Debe llenar los datos de la entidad correspondiente (5)");
                                mbTodero.warn();
                                validar1 = "ok2";
                            } else //Condicion Quinto check
                             if (mBEntidad.isEntidadFact5() == true) {
                                    this.estadoPanelDialogEntidadfactu = true;
                                    this.TipoTitulEnt5 = true;
                                    LogEntidad Enti = new LogEntidad();
                                    Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad5()));
                                    Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal5()));
                                    Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor5()));
                                    Dat = Enti.ConsulInfEntAsesor();
                                    if (Dat.next()) {
                                        mBEntidad.setNombreEntidadfactu5(Dat.getString("Nombre_Entidad"));
                                        mBEntidad.setDocumentoEntidadfactu5(Dat.getString("Documento_Entidad"));
                                        mBEntidad.setNombreSucursalfactu5(Dat.getString("Nombre_Oficina"));
                                        mBEntidad.setTelefonoSucursalfactu5(Dat.getString("Telefono_Oficina"));
                                        mBEntidad.setNombreAsesorfactu5(Dat.getString("Nombre_Asesor"));
                                        mBEntidad.setCargoAsesorfactu5(Dat.getString("Cargo_Asesor"));

                                        mBEntidad.setNombreEntidad5(Dat.getString("Nombre_Entidad"));
                                        mBEntidad.setDocumentoEntidad5(Dat.getString("Documento_Entidad"));
                                        mBEntidad.setNombreSucursal5(Dat.getString("Nombre_Oficina"));
                                        mBEntidad.setTelefonoSucursal5(Dat.getString("Telefono_Oficina"));
                                        mBEntidad.setNombreAsesor5(Dat.getString("Nombre_Asesor"));
                                        mBEntidad.setCargoAsesor5(Dat.getString("Cargo_Asesor"));

                                        this.estadoPanelDialogClienteFacturar = false;
                                    }
                                    Conexion.CloseCon();
                                } else {
                                    this.TipoTitulEnt5 = false;
                                    LogEntidad Enti = new LogEntidad();
                                    Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad5()));
                                    Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal5()));
                                    Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor5()));
                                    Dat = Enti.ConsulInfEntAsesor();
                                    if (Dat.next()) {
                                        mBEntidad.setNombreEntidad5(Dat.getString("Nombre_Entidad"));
                                        mBEntidad.setDocumentoEntidad5(Dat.getString("Documento_Entidad"));
                                        mBEntidad.setNombreSucursal5(Dat.getString("Nombre_Oficina"));
                                        mBEntidad.setTelefonoSucursal5(Dat.getString("Telefono_Oficina"));
                                        mBEntidad.setNombreAsesor5(Dat.getString("Nombre_Asesor"));
                                        mBEntidad.setCargoAsesor5(Dat.getString("Cargo_Asesor"));
                                    }
                                    Conexion.CloseCon();
                                }
                        }
                    }
                }
                if ("ok3".equals(validar1)) {
                    validar1 = "ok4";
                    if ((mBCliente.isClientFact1() == false && mBCliente.isClientFact2() == false && mBCliente.isClientFact3() == false && mBCliente.isClientFact4() == false && mBCliente.isClientFact5() == false)
                            && (mBEntidad.isEntidadFact1() == false && mBEntidad.isEntidadFact2() == false && mBEntidad.isEntidadFact3() == false && mBEntidad.isEntidadFact4() == false && mBEntidad.isEntidadFact5() == false)) {
                        mbTodero.setMens("Debe seleccionar por lo menos un cliente o entidad a facturar");
                        mbTodero.warn();
                        validar1 = "ok3";
                    } else if ((mBCliente.isClientFact1() == true || mBCliente.isClientFact2() == true || mBCliente.isClientFact3() == true || mBCliente.isClientFact4() == true || mBCliente.isClientFact5() == true)
                            && (mBEntidad.isEntidadFact1() == true || mBEntidad.isEntidadFact2() == true || mBEntidad.isEntidadFact3() == true || mBEntidad.isEntidadFact4() == true || mBEntidad.isEntidadFact5() == true)) {
                        mbTodero.setMens("No puede facturar al cliente y a la entidad");
                        mbTodero.warn();
                        validar1 = "ok3";
                    }
                }
                if ("ok4".equals(validar1)) {
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(1)");
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(2)");
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(2)");
                }
            }
            if ("AdelanteTb2".equals(form_num)) {
                String validar2 = "";
                if ("".equals(validar2)) {
                    validar2 = "ok1";
                    if ((mBAvaluo.getCodTipAvaluo() == 0
                            && mBAvaluo.isEstadoPanelGrande() == true)
                            && (mBAvaluo.isEstadoTablasResulPred() == false
                            && mBAvaluo.isEstadoTablasResulMaqui() == false
                            && mBAvaluo.isEstadoTablasResulEnser() == false)) {
                        mbTodero.setMens("Debe seleccionar un tipo de avalúo");
                        mbTodero.warn();
                        validar2 = "";
                    } else if (mBAvaluo.getCodTipAvaluo() != 0) {
                        if (mBAvaluo.isEstadoInfoPred() == true) {
                            if ("".equals(mBAvaluo.getLlaveAvaluo())) {
                                mbTodero.setMens("Debe llenar el campo 'Matricula'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if (!mBAvaluo.getLlaveAvaluo().equals(mBAvaluo.getConfirmatriculaAval())) {
                                mbTodero.setMens("Las matriculas no coinciden");
                                mbTodero.warn();
                                validar2 = "";
                            } else if ("".equals(mBAvaluo.getTipoDireccion())) {
                                mbTodero.setMens("Debe seleccionar información del campo 'Ubicación'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if ("".equals(mBAvaluo.getDireccionAval())) {
                                mbTodero.setMens("Debe llenar el campo 'Dirección'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if (!this.mBAvaluo.getDireccionAval().equals(mBAvaluo.getConfirDireccionAval()) && "Rural".equals(mBAvaluo.getTipoDireccion())) {
                                mbTodero.setMens("Las direcciones no coinciden");
                                mbTodero.warn();
                                validar2 = "";
                            } 
                        } else if (mBAvaluo.isEstadoInfoMaqui() == true) {
                            if ("".equals(mBAvaluo.getObservacMaquin())) {
                                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if ("".equals(mBAvaluo.getTipoDireccion())) {
                                mbTodero.setMens("Debe seleccionar información del campo 'Ubicación'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if ("".equals(this.mBAvaluo.getDireccionAval())) {
                                mbTodero.setMens("Debe llenar el campo 'Dirección'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if (!this.mBAvaluo.getDireccionAval().equals(mBAvaluo.getConfirDireccionAval()) && "Rural".equals(mBAvaluo.getTipoDireccion())) {
                                mbTodero.setMens("Las direcciones no coinciden");
                                mbTodero.warn();
                                validar2 = "";
                            }  
                        } else if (mBAvaluo.isEstadoInfoResulEnser() == true) {
                            if ("".equals(mBAvaluo.getLlaveAvaluo())) {
                                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if ("".equals(mBAvaluo.getTipoDireccion())) {
                                mbTodero.setMens("Debe seleccionar información del campo 'Ubicación'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if ("".equals(this.mBAvaluo.getDireccionAval())) {
                                mbTodero.setMens("Debe llenar el campo 'Dirección'");
                                mbTodero.warn();
                                validar2 = "";
                            } else if (!this.mBAvaluo.getDireccionAval().equals(mBAvaluo.getConfirDireccionAval()) && "rural".equals(mBAvaluo.getTipoDireccion())) {
                                mbTodero.setMens("Las direcciones no coinciden");
                                mbTodero.warn();
                                validar2 = "";
                            } 
                        }
                    } else if ((mBAvaluo.getSelectPredios().isEmpty()) && (mBAvaluo.getSelectMaquinaria().isEmpty()) && (mBAvaluo.getSelectEnseres().isEmpty())) {
                        mbTodero.setMens("Debe seleccionar un tipo de avaluo");
                        mbTodero.warn();
                        validar2 = "";
                    }
                }
                if ("ok1".equals(validar2)) {
                    cargarArchivosObligatorios();
                    this.ExisteInformacion = false;
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(2)");
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(3)");
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(3)");
                }
            }
            if ("AdelanteTb3".equals(form_num)) {
                String validar3 = "";
                if ("".equals(validar3)) {
                    validar3 = "ok1";
                    if (this.ExisteInformacion == true) {

            
                    } else//Esta condicion en el caso 
                     if (!ListaArcRegla.isEmpty()) {
                            mbTodero.setMens("Hay documentacion obligatoria dentro del proceso, por favor seleccione la opcion y verifique la informacion");
                            mbTodero.warn();
                            validar3 = "";
                        }
                }
                if ("ok1".equals(validar3)) {
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(3)");
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(4)");
                    RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(4)");
                    ObservaPreRad = ObservaPreRadInicio;
                }
            }
            if ("AdelanteTb4".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(4)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(5)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(5)");
            }
            if ("AtrasTb1".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(0)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(0)");
            }
            if ("AtrasTb2".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(1)");
            }
            if ("AtrasTb3".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(2)");
            }
            if ("AtrasTb4".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(4)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(3)");

            }
            if ("AtrasTb5".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').disable(5)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').enable(4)");
                RequestContext.getCurrentInstance().execute("PF('tabPreRadicacion').select(4)");
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarForm()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que consulta que archivos son obligatorios dentro del proceso de
     * pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void cargarArchivosObligatorios() {
        try {
            ListaArcRegla = new ArrayList<>();
            int TipoA = 0;

            //Predio
            if ((mBAvaluo.getSelectPredios() == null || mBAvaluo.getSelectPredios().isEmpty())) {

            } else if (mBAvaluo.getSelectPredios().size() > 0) {
                TipoA = 1;
            }

            if (mBAvaluo.getCodTipAvaluo() == 1) {
                TipoA = 1;
            }

            //Maquinaria
            if ((mBAvaluo.getSelectMaquinaria() == null || mBAvaluo.getSelectMaquinaria().isEmpty())) {

            } else if (mBAvaluo.getSelectMaquinaria().size() > 0) {
                TipoA = 2;
            }
            if (mBAvaluo.getCodTipAvaluo() == 2) {
                TipoA = 2;
            }

            //Muebles
            if ((mBAvaluo.getSelectEnseres() == null || mBAvaluo.getSelectEnseres().isEmpty())) {

            } else if (mBAvaluo.getSelectEnseres().size() > 0) {
                TipoA = 3;
            }
            if (mBAvaluo.getCodTipAvaluo() == 3) {
                TipoA = 3;
            }
            ListaArcRegla = new ArrayList<>();
            ListaArcRegla = Adm.ConsulListaReglasProceso(1, mBAdmi.Adm.getCodTipProEnt(), TipoA, "Pre-Radicacion");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarArchivosObligatorios()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

  

    /**
     * Metodo que habilita las opciones para registrar un nuevo bien en el form
     * de pre-radicacion, ya sea predio, maquinaria o enser/mueble
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param n_bien int que contiene el numero que identifica a cada tipo de
     * bien= 1: predio 2: maquinaria 3: enser/mueble
     * @since 01-11-2014
     */
    private final int n_bien = 0;

    public void permitirNuevoBien(int n_bien) {
        try {
            if (n_bien == 1) {
            
                mBAvaluo.setSelectMaquinaria(null);  //limpia la lista y deschequea lo seleccionado
                mBAvaluo.setSelectEnseres(null);
                mBAvaluo.setEstadoPanelGrande(true);
                mBAvaluo.setCodTipAvaluo(1);
                mBAvaluo.cambiarEstadoInfo();
                RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoPredio').hide()");
            }
            if (n_bien == 2) {
              
                mBAvaluo.setSelectPredios(null);  //limpia la lista y deschequea lo seleccionado
                mBAvaluo.setSelectEnseres(null);
                mBAvaluo.setEstadoPanelGrande(true);
                mBAvaluo.setCodTipAvaluo(2);
                mBAvaluo.cambiarEstadoInfo();

                RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoMaquinaria').hide()");
            }
            if (n_bien == 3) {
                
                mBAvaluo.setSelectPredios(null);  //limpia la lista y deschequea lo seleccionado
                mBAvaluo.setSelectMaquinaria(null);
                mBAvaluo.setEstadoPanelGrande(true);
                mBAvaluo.setCodTipAvaluo(3);
                mBAvaluo.cambiarEstadoInfo();

                RequestContext.getCurrentInstance().execute("PF('DlgConsultaAvaluoEnser').hide()");
            }

            mBAvaluo.setSelectPredios(null);
            mBAvaluo.setEstadoTablasResulPred(false);
            mBAvaluo.setSelectMaquinaria(null);
            mBAvaluo.setEstadoTablasResulMaqui(false);
            mBAvaluo.setSelectEnseres(null);
            mBAvaluo.setEstadoTablasResulEnser(false);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".permitirNuevoBien()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta todas las pre-radicaciones que tiene un usuario por
     * realizar
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void cargaListaAsignadosPreRadica() {
        try {
            this.ListPreRadicaAsignados = new ArrayList<>();
            this.ListPreRadicaAsignados.clear();
            this.ListPreRadicaAsignados = PreRad.ConsulPreRadicaAsigna(onSelectTipoProReasigna(1), 1);
            this.estadoOpcionAsignado = true;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargaListaAsignadosPreRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        mbTodero.resetTable("PreRadicaAsignadosTable");
    }

    /**
     * Metodo que carga los registros para que estos puedan ser cambiados de
     * analista
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param proceso int que contiene el proceso que se realizara
     * @since 01-11-2014
     */
    public void seleccionListaReAsignacion(int proceso) {
        try {
            if (ListPreRadicaSeleccAsignados.isEmpty()) {
                mbTodero.setMens("No ha seleccionado ningun registro");
                mbTodero.warn();
            } else {
                //Metodo que consulta las asignaciones que tiene cada empleado
                Emp = new LogEmpleado();
                if (proceso == 1) {
                    ListEmpAsignaciones = PreRadi.getConsultarAllAnalistas(onSelectTipoProReasigna(1), 1);
                    mbTodero.resetTable("FRMAsignPreRadica:EmpAsignadosTable");
                } else if (proceso == 2) {
                    ListEmpAsignaciones = PreRadi.getConsultarAllAnalistas(onSelectTipoProReasigna(2), 2);
                    mbTodero.resetTable("FRMAsigRadic:EmpAsignadosTablePreRadica");
                }
                RequestContext.getCurrentInstance().execute("PF('DLGReAsignacion').show()");

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionListaReAsignacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que realiza un cambio de analista de uno o mas registros de
     * pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param proceso int que contiene el proceso que se realizara
     * @since 01-11-2014
     */
    public void generarReAsignacion(int proceso) {
        try {
            if (Emple.getCod_Analista() == 0) {
                mbTodero.setMens("No ha seleccionado un analista para generar la Asignacion de Registros");
                mbTodero.warn();
            } else if (Emple.getCod_Analista() == Integer.parseInt(mBEmpleado.getEmpleado())) {
                mbTodero.setMens("No se puede asignar a la Misma Persona los Registros, seleccione un Analista diferente");
                mbTodero.warn();
            } else {
                for (int i = 0; i < ListPreRadicaSeleccAsignados.size(); i++) {
                    PreRad.setCodPreRadica(ListPreRadicaSeleccAsignados.get(i).getCodPreRadica());
                    PreRad.ModifiAsignacionAnalista(Emple.getCod_Analista(), mBsesion.codigoMiSesion());
                }
                this.ListPreRadicaAsignados.clear();
                if (proceso == 1) {
                    this.ListPreRadicaAsignados = PreRad.ConsulPreRadicaAsigna(this.onSelectTipoProReasigna(1), 1);
                    mbTodero.resetTable("FRMAsignPreRadica:PreRadicaAsignadosTable");
                } else if (proceso == 2) {
                    this.ListPreRadicaAsignados = PreRad.ConsulPreRadicaAsigna(this.onSelectTipoProReasigna(2), 2);
                    mbTodero.resetTable("FRMAsigRadic:PreRadicaAsignadosTable");
                }

                mbTodero.setMens("Se ha generado el cambio correctamente");
                mbTodero.info();
                RequestContext.getCurrentInstance().execute("PF('DLGReAsignacion').hide()");
                this.ListPreRadicaSeleccAsignados.clear();
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".generarReAsignacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que selecciona el tipo de producto entidad con el que cargara los
     * registros, con ello se consultaran unos u otros de acuerdo al producto
     * entidad que tengan registrado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param proceso int que contiene el tipo de proceso a realizar
     * @return retorna el codigo
     * @since 01-11-2014
     */
    public int onSelectTipoProReasigna(int proceso) {
        int codigo = 0;
        try {

            if (mBAdmi.Adm.getCodProEnt() != 0) {
                codigo = mBAdmi.Adm.getCodProEnt();
                if (proceso == 1) {
                    mbTodero.resetTable("FRMAsignPreRadica:PreRadicaAsignadosTable");
                    this.ListPreRadicaAsignados = new ArrayList<>();
                    this.ListPreRadicaAsignados = PreRad.ConsulPreRadicaAsigna(codigo, proceso);
                    mbTodero.resetTable("FRMAsignPreRadica:PreRadicaAsignadosTable");
                    this.estadoOpcionAsignado = true;
                } else if (proceso == 2) {
                    mbTodero.resetTable("FRMAsigRadic:PreRadicaAsignadosTable");
                    this.ListPreRadicaAsignados = new ArrayList<>();
                    this.ListPreRadicaAsignados = PreRad.ConsulPreRadicaAsigna(codigo, proceso);
                    mbTodero.resetTable("FRMAsigRadic:PreRadicaAsignadosTable");
                    this.estadoOpcionAsignado = true;
                }
            } else {
                this.estadoOpcionAsignado = false;

            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onSelectTipoProReasigna()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return codigo;
    }

    /**
     * Metodo que abre el formulario tipo dialogo en donde se pueden registrar,
     * modificar entidades, sucursales, asesores en los form de registro,
     * pre-radicacion y radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param tipo int que funciona para diferenciar el proceso que se hara asi:
     * 1: registro 2: pre-radicacion 3: radicacion
     * @since 01-11-2014
     */
    public void abrirDialogEntidades(int tipo) {
        mBEntidad.setEstadoRadioSeleccion("Entidades");
        mBEntidad.visiblePanel();
       

        if (tipo == 1) {
            RequestContext.getCurrentInstance().execute("PF('DlgRegistrarOModifEntidadDesdeRegistro').hide()");
            RequestContext.getCurrentInstance().execute("PF('DlgRegistrarOModifEntidadDesdeRegistro').show()");
        } else if (tipo == 2) {
            RequestContext.getCurrentInstance().execute("PF('DlgRegistrarOModifEntidadDesdeRegistro').show()");
        }
        try {

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta la cantidad de registros representadas en numeros,
     * aquellas se muestran al lado de cada nombre de submenus de el modulo de
     * pre-radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void consultaTotalRegistrosTablas() {
        try {
            PreRadi = new LogPreRadicacion();
            NumeroRegistrosAsig = PreRadi.consultaNumeroRegistros(mBsesion.codigoMiSesion());
            NumeroMisPreradicaciones = PreRadi.consultaNumeroMisPreRadicaciones(mBsesion.codigoMiSesion());
            NumeroTotalPreRadicaciones = PreRadi.consultaNumeroTotalPreRadicaciones(mBsesion.codigoMiSesion());

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaTotalRegistrosTablas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para abrir el dialog para agregar una observacion para un
     * pre-radicacion que se encuentre en estado asignado
     *
     * @author Jhojan Stiven Rodríguez
     *
     */
    public void onAbrirDialogAgregarObserv() {
        try {
            if (preRadRegiMisAsig == null) {
                mbTodero.setMens("Debe seleccionar un registro");
                mbTodero.warn();
            } else {
                RequestContext.getCurrentInstance().execute("PF('DlgAgregarObservacion').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onAbrirDialogAgregarObserv()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para agregar una observacion para un pre-radicacion que se
     * encuentre en estado asignado
     *
     * @author Jhojan Stiven Rodríguez
     *
     */
    public void agregarObservacionPreRadicacion() {
        try {
            if ("".equals(ObservaPreRadGeneral) || ObservaPreRadGeneral == null) {
                mbTodero.setMens("Debe llenar información del campo 'Observaciones'");
                mbTodero.warn();
            } else {
                PreRad.setObservacionPreRadicaGeneral(ObservaPreRadGeneral + " - PRE-RADICACIÓN");
                PreRad.setCodPreRadica(preRadRegiMisAsig.getCodPreRadica());
                PreRad.agregarObservacionPreRadicacion(mBsesion.codigoMiSesion());
                mbTodero.setMens("Observación realizada correctamente");
                mbTodero.info();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarObservacionPreRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Variable tipo BeanAvaluo para traer los atributos y metodos de el
     * ManagedBean BeanAvaluo.java
     *
     *
     * @see BeanAvaluo.java
     */
    @ManagedProperty("#{MBAvaluo}")
    private BeanAvaluo mBAvaluo;

    public BeanAvaluo getmBAvaluo() {
        return mBAvaluo;
    }

    public void setmBAvaluo(BeanAvaluo mBAvaluo) {
        this.mBAvaluo = mBAvaluo;
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
     * Variable tipo BeanAdministracion para traer los atributos y metodos de el
     * ManagedBean BeanAdministracion.java
     *
     *
     * @see BeanAdministracion.java
     */
    @ManagedProperty("#{MBAdministacion}")
    private BeanAdministracion mBAdmi;

    public BeanAdministracion getmBAdmi() {
        return mBAdmi;
    }

    public void setmBAdmi(BeanAdministracion mBAdmi) {
        this.mBAdmi = mBAdmi;
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
     * Variable tipo BeanPerito para traer los atributos y metodos de el
     * ManagedBean BeanPerito.java
     *
     *
     * @see BeanPerito.java
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
     * Constructor de la clase*
     */
    public BeanPreRadicacion() {
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
    public Date getFechaSol() {
        return FechaSol;
    }

    public void setFechaSol(Date FechaSol) {
        this.FechaSol = FechaSol;
    }

    public Date getHoraSol() {
        return HoraSol;
    }

    public void setHoraSol(Date HoraSol) {
        this.HoraSol = HoraSol;
    }

    public Integer getCodEmp() {
        return CodEmp;
    }

    public void setCodEmp(Integer CodEmp) {
        this.CodEmp = CodEmp;
    }

    public String getNombreAnalista() {
        return NombreAnalista;
    }

    public void setNombreAnalista(String NombreAnalista) {
        this.NombreAnalista = NombreAnalista;
    }

    public String getOpcionRadioPersonFact() {
        return opcionRadioPersonFact;
    }

    public void setOpcionRadioPersonFact(String opcionRadioPersonFact) {
        this.opcionRadioPersonFact = opcionRadioPersonFact;
    }

    public boolean isEstadoPanelClientPreRadi() {
        return estadoPanelClientPreRadi;
    }

    public void setEstadoPanelClientPreRadi(boolean estadoPanelClientPreRadi) {
        this.estadoPanelClientPreRadi = estadoPanelClientPreRadi;
    }

    public LogEmpleado getEmp() {
        return Emp;
    }

    public void setEmp(LogEmpleado Emp) {
        this.Emp = Emp;
    }

    public LogPreRadicacion getEmple() {
        return Emple;
    }

    public void setEmple(LogPreRadicacion Emple) {
        this.Emple = Emple;
    }

    public String getOpcionRadioPerito() {
        return opcionRadioPerito;
    }

    public void setOpcionRadioPerito(String opcionRadioPerito) {
        this.opcionRadioPerito = opcionRadioPerito;
    }

    public boolean isEstadoPanelPeritoPreRad() {
        return estadoPanelPeritoPreRad;
    }

    public void setEstadoPanelPeritoPreRad(boolean estadoPanelPeritoPreRad) {
        this.estadoPanelPeritoPreRad = estadoPanelPeritoPreRad;
    }

    public boolean isEstadoPanelEntidadPreRad() {
        return estadoPanelEntidadPreRad;
    }

    public void setEstadoPanelEntidadPreRad(boolean estadoPanelEntidadPreRad) {
        this.estadoPanelEntidadPreRad = estadoPanelEntidadPreRad;
    }

    public boolean isEstadoActivacionGestor() {
        return estadoActivacionGestor;
    }

    public void setEstadoActivacionGestor(boolean estadoActivacionGestor) {
        this.estadoActivacionGestor = estadoActivacionGestor;
    }

    public boolean isEstadoActivacionRadicador() {
        return estadoActivacionRadicador;
    }

    public void setEstadoActivacionRadicador(boolean estadoActivacionRadicador) {
        this.estadoActivacionRadicador = estadoActivacionRadicador;
    }

    public String getCod_preRadica() {
        return cod_preRadica;
    }

    public void setCod_preRadica(String cod_preRadica) {
        this.cod_preRadica = cod_preRadica;
    }

    public String getOpcionRadioEstudio() {
        return opcionRadioEstudio;
    }

    public void setOpcionRadioEstudio(String opcionRadioEstudio) {
        this.opcionRadioEstudio = opcionRadioEstudio;
    }

    public boolean isSiEstudio() {
        return siEstudio;
    }

    public void setSiEstudio(boolean siEstudio) {
        this.siEstudio = siEstudio;
    }

    public boolean isBotondem() {
        return botondem;
    }

    public void setBotondem(boolean botondem) {
        this.botondem = botondem;
    }

    public boolean isRequiereEntidad() {
        return RequiereEntidad;
    }

    public void setRequiereEntidad(boolean RequiereEntidad) {
        this.RequiereEntidad = RequiereEntidad;
    }

    public boolean isRequiereUbicacion() {
        return RequiereUbicacion;
    }

    public void setRequiereUbicacion(boolean RequiereUbicacion) {
        this.RequiereUbicacion = RequiereUbicacion;
    }

    public boolean isUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(boolean Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public boolean isExisteInformacion() {
        return ExisteInformacion;
    }

    public void setExisteInformacion(boolean ExisteInformacion) {
        this.ExisteInformacion = ExisteInformacion;
    }

    public String getOtroQuienEnviarA() {
        return OtroQuienEnviarA;
    }

    public void setOtroQuienEnviarA(String OtroQuienEnviarA) {
        this.OtroQuienEnviarA = OtroQuienEnviarA;
    }

    public String getOtroUbicacionEnviarA() {
        return OtroUbicacionEnviarA;
    }

    public void setOtroUbicacionEnviarA(String OtroUbicacionEnviarA) {
        this.OtroUbicacionEnviarA = OtroUbicacionEnviarA;
    }

    public String getOtroDireccionEnviarA() {
        return OtroDireccionEnviarA;
    }

    public void setOtroDireccionEnviarA(String OtroDireccionEnviarA) {
        this.OtroDireccionEnviarA = OtroDireccionEnviarA;
    }

    public String getDetalleEnviarAotro() {
        return detalleEnviarAotro;
    }

    public void setDetalleEnviarAotro(String detalleEnviarAotro) {
        this.detalleEnviarAotro = detalleEnviarAotro;
    }

    public String getEnviarALabel() {
        return EnviarALabel;
    }

    public void setEnviarALabel(String EnviarALabel) {
        this.EnviarALabel = EnviarALabel;
    }

    public boolean isEstadoCajasEnviarA() {
        return estadoCajasEnviarA;
    }

    public void setEstadoCajasEnviarA(boolean estadoCajasEnviarA) {
        this.estadoCajasEnviarA = estadoCajasEnviarA;
    }

    public ArrayList<SelectItem> getCargEnviarA() {
        return CargEnviarA;
    }

    public void setCargEnviarA(ArrayList<SelectItem> CargEnviarA) {
        this.CargEnviarA = CargEnviarA;
    }

    public List<LogAdministracion> getListaArcRegla() {
        return ListaArcRegla;
    }

    public void setListaArcRegla(List<LogAdministracion> ListaArcRegla) {
        this.ListaArcRegla = ListaArcRegla;
    }

    public boolean isCotizacion() {
        return Cotizacion;
    }

    public void setCotizacion(boolean Cotizacion) {
        this.Cotizacion = Cotizacion;
    }

    public LogCliente getCli() {
        return Cli;
    }

    public void setCli(LogCliente Cli) {
        this.Cli = Cli;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNomTipAvaluo() {
        return NomTipAvaluo;
    }

    public void setNomTipAvaluo(String NomTipAvaluo) {
        this.NomTipAvaluo = NomTipAvaluo;
    }

    public List<LogCliente> getListClient() {
        return ListClient;
    }

    public void setListClient(List<LogCliente> ListClient) {
        this.ListClient = ListClient;
    }

    public boolean isEstadoFuncionario1() {
        return EstadoFuncionario1;
    }

    public void setEstadoFuncionario1(boolean EstadoFuncionario1) {
        this.EstadoFuncionario1 = EstadoFuncionario1;
    }

    public boolean isEstadoFuncionario2() {
        return EstadoFuncionario2;
    }

    public void setEstadoFuncionario2(boolean EstadoFuncionario2) {
        this.EstadoFuncionario2 = EstadoFuncionario2;
    }

    public boolean isEstadoFuncionario3() {
        return EstadoFuncionario3;
    }

    public void setEstadoFuncionario3(boolean EstadoFuncionario3) {
        this.EstadoFuncionario3 = EstadoFuncionario3;
    }

    public boolean isEstadoFuncionario4() {
        return EstadoFuncionario4;
    }

    public void setEstadoFuncionario4(boolean EstadoFuncionario4) {
        this.EstadoFuncionario4 = EstadoFuncionario4;
    }

    public boolean isEstadoFuncionario5() {
        return EstadoFuncionario5;
    }

    public void setEstadoFuncionario5(boolean EstadoFuncionario5) {
        this.EstadoFuncionario5 = EstadoFuncionario5;
    }

    public boolean isTipoTitulEnt1() {
        return TipoTitulEnt1;
    }

    public void setTipoTitulEnt1(boolean TipoTitulEnt1) {
        this.TipoTitulEnt1 = TipoTitulEnt1;
    }

    public boolean isTipoTitulEnt2() {
        return TipoTitulEnt2;
    }

    public void setTipoTitulEnt2(boolean TipoTitulEnt2) {
        this.TipoTitulEnt2 = TipoTitulEnt2;
    }

    public boolean isTipoTitulEnt3() {
        return TipoTitulEnt3;
    }

    public void setTipoTitulEnt3(boolean TipoTitulEnt3) {
        this.TipoTitulEnt3 = TipoTitulEnt3;
    }

    public boolean isTipoTitulEnt4() {
        return TipoTitulEnt4;
    }

    public void setTipoTitulEnt4(boolean TipoTitulEnt4) {
        this.TipoTitulEnt4 = TipoTitulEnt4;
    }

    public boolean isTipoTitulEnt5() {
        return TipoTitulEnt5;
    }

    public void setTipoTitulEnt5(boolean TipoTitulEnt5) {
        this.TipoTitulEnt5 = TipoTitulEnt5;
    }

    public boolean isEstadoPanelDialogSolicitante() {
        return estadoPanelDialogSolicitante;
    }

    public void setEstadoPanelDialogSolicitante(boolean estadoPanelDialogSolicitante) {
        this.estadoPanelDialogSolicitante = estadoPanelDialogSolicitante;
    }

    public boolean isEstadoPanelDialogClienteFacturar() {
        return estadoPanelDialogClienteFacturar;
    }

    public void setEstadoPanelDialogClienteFacturar(boolean estadoPanelDialogClienteFacturar) {
        this.estadoPanelDialogClienteFacturar = estadoPanelDialogClienteFacturar;
    }

    public boolean isEstadoPanelDialogEntidad() {
        return estadoPanelDialogEntidad;
    }

    public void setEstadoPanelDialogEntidad(boolean estadoPanelDialogEntidad) {
        this.estadoPanelDialogEntidad = estadoPanelDialogEntidad;
    }

    public boolean isEstadoPanelDialogEntidadfactu() {
        return estadoPanelDialogEntidadfactu;
    }

    public void setEstadoPanelDialogEntidadfactu(boolean estadoPanelDialogEntidadfactu) {
        this.estadoPanelDialogEntidadfactu = estadoPanelDialogEntidadfactu;
    }

    public boolean isEstadoPanelDialogTempBien() {
        return estadoPanelDialogTempBien;
    }

    public void setEstadoPanelDialogTempBien(boolean estadoPanelDialogTempBien) {
        this.estadoPanelDialogTempBien = estadoPanelDialogTempBien;
    }

    public boolean isEstadoPreRadicaDialogSolicitantesVI() {
        return estadoPreRadicaDialogSolicitantesVI;
    }

    public void setEstadoPreRadicaDialogSolicitantesVI(boolean estadoPreRadicaDialogSolicitantesVI) {
        this.estadoPreRadicaDialogSolicitantesVI = estadoPreRadicaDialogSolicitantesVI;
    }

    public boolean isEstadoPreRadicaDialogPersonFacVI() {
        return estadoPreRadicaDialogPersonFacVI;
    }

    public void setEstadoPreRadicaDialogPersonFacVI(boolean estadoPreRadicaDialogPersonFacVI) {
        this.estadoPreRadicaDialogPersonFacVI = estadoPreRadicaDialogPersonFacVI;
    }

    public boolean isEstadoPreRadicaDialogEntidVI() {
        return estadoPreRadicaDialogEntidVI;
    }

    public void setEstadoPreRadicaDialogEntidVI(boolean estadoPreRadicaDialogEntidVI) {
        this.estadoPreRadicaDialogEntidVI = estadoPreRadicaDialogEntidVI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoPredVI() {
        return estadoPreRadicaDialogAvaluoPredVI;
    }

    public void setEstadoPreRadicaDialogAvaluoPredVI(boolean estadoPreRadicaDialogAvaluoPredVI) {
        this.estadoPreRadicaDialogAvaluoPredVI = estadoPreRadicaDialogAvaluoPredVI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoMaqVI() {
        return estadoPreRadicaDialogAvaluoMaqVI;
    }

    public void setEstadoPreRadicaDialogAvaluoMaqVI(boolean estadoPreRadicaDialogAvaluoMaqVI) {
        this.estadoPreRadicaDialogAvaluoMaqVI = estadoPreRadicaDialogAvaluoMaqVI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoEnseVI() {
        return estadoPreRadicaDialogAvaluoEnseVI;
    }

    public void setEstadoPreRadicaDialogAvaluoEnseVI(boolean estadoPreRadicaDialogAvaluoEnseVI) {
        this.estadoPreRadicaDialogAvaluoEnseVI = estadoPreRadicaDialogAvaluoEnseVI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoTempVI() {
        return estadoPreRadicaDialogAvaluoTempVI;
    }

    public void setEstadoPreRadicaDialogAvaluoTempVI(boolean estadoPreRadicaDialogAvaluoTempVI) {
        this.estadoPreRadicaDialogAvaluoTempVI = estadoPreRadicaDialogAvaluoTempVI;
    }

    public boolean isEstadoPreRadicaDialogSolicitantesGI() {
        return estadoPreRadicaDialogSolicitantesGI;
    }

    public void setEstadoPreRadicaDialogSolicitantesGI(boolean estadoPreRadicaDialogSolicitantesGI) {
        this.estadoPreRadicaDialogSolicitantesGI = estadoPreRadicaDialogSolicitantesGI;
    }

    public boolean isEstadoPreRadicaDialogPersonFacGI() {
        return estadoPreRadicaDialogPersonFacGI;
    }

    public void setEstadoPreRadicaDialogPersonFacGI(boolean estadoPreRadicaDialogPersonFacGI) {
        this.estadoPreRadicaDialogPersonFacGI = estadoPreRadicaDialogPersonFacGI;
    }

    public boolean isEstadoPreRadicaDialogEntidGI() {
        return estadoPreRadicaDialogEntidGI;
    }

    public void setEstadoPreRadicaDialogEntidGI(boolean estadoPreRadicaDialogEntidGI) {
        this.estadoPreRadicaDialogEntidGI = estadoPreRadicaDialogEntidGI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoGI() {
        return estadoPreRadicaDialogAvaluoGI;
    }

    public void setEstadoPreRadicaDialogAvaluoGI(boolean estadoPreRadicaDialogAvaluoGI) {
        this.estadoPreRadicaDialogAvaluoGI = estadoPreRadicaDialogAvaluoGI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoPredGI() {
        return estadoPreRadicaDialogAvaluoPredGI;
    }

    public void setEstadoPreRadicaDialogAvaluoPredGI(boolean estadoPreRadicaDialogAvaluoPredGI) {
        this.estadoPreRadicaDialogAvaluoPredGI = estadoPreRadicaDialogAvaluoPredGI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoMaqGI() {
        return estadoPreRadicaDialogAvaluoMaqGI;
    }

    public void setEstadoPreRadicaDialogAvaluoMaqGI(boolean estadoPreRadicaDialogAvaluoMaqGI) {
        this.estadoPreRadicaDialogAvaluoMaqGI = estadoPreRadicaDialogAvaluoMaqGI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoEnseGI() {
        return estadoPreRadicaDialogAvaluoEnseGI;
    }

    public void setEstadoPreRadicaDialogAvaluoEnseGI(boolean estadoPreRadicaDialogAvaluoEnseGI) {
        this.estadoPreRadicaDialogAvaluoEnseGI = estadoPreRadicaDialogAvaluoEnseGI;
    }

    public boolean isEstadoPreRadicaDialogAvaluoTempGI() {
        return estadoPreRadicaDialogAvaluoTempGI;
    }

    public void setEstadoPreRadicaDialogAvaluoTempGI(boolean estadoPreRadicaDialogAvaluoTempGI) {
        this.estadoPreRadicaDialogAvaluoTempGI = estadoPreRadicaDialogAvaluoTempGI;
    }

    public boolean isEstadoPreRadicaDialogClienteVP() {
        return estadoPreRadicaDialogClienteVP;
    }

    public void setEstadoPreRadicaDialogClienteVP(boolean estadoPreRadicaDialogClienteVP) {
        this.estadoPreRadicaDialogClienteVP = estadoPreRadicaDialogClienteVP;
    }

    public boolean isEstadoPreRadicaDialogPeritoVP() {
        return estadoPreRadicaDialogPeritoVP;
    }

    public void setEstadoPreRadicaDialogPeritoVP(boolean estadoPreRadicaDialogPeritoVP) {
        this.estadoPreRadicaDialogPeritoVP = estadoPreRadicaDialogPeritoVP;
    }

    public boolean isEstadoPreRadicaDialogEntidadVP() {
        return estadoPreRadicaDialogEntidadVP;
    }

    public void setEstadoPreRadicaDialogEntidadVP(boolean estadoPreRadicaDialogEntidadVP) {
        this.estadoPreRadicaDialogEntidadVP = estadoPreRadicaDialogEntidadVP;
    }

    public boolean isEstadoPreRadicaEntidadFac() {
        return estadoPreRadicaEntidadFac;
    }

    public void setEstadoPreRadicaEntidadFac(boolean estadoPreRadicaEntidadFac) {
        this.estadoPreRadicaEntidadFac = estadoPreRadicaEntidadFac;
    }

    public String getFacturarEnti() {
        return FacturarEnti;
    }

    public void setFacturarEnti(String FacturarEnti) {
        this.FacturarEnti = FacturarEnti;
    }

    public String getEnvCartaPerito() {
        return EnvCartaPerito;
    }

    public void setEnvCartaPerito(String EnvCartaPerito) {
        this.EnvCartaPerito = EnvCartaPerito;
    }

    public String getTarifaEntidad() {
        return TarifaEntidad;
    }

    public void setTarifaEntidad(String TarifaEntidad) {
        this.TarifaEntidad = TarifaEntidad;
    }

    public String getValorTarifEntidad() {
        return ValorTarifEntidad;
    }

    public void setValorTarifEntidad(String ValorTarifEntidad) {
        this.ValorTarifEntidad = ValorTarifEntidad;
    }

    public String getNumeroDocPerito1() {
        return numeroDocPerito1;
    }

    public void setNumeroDocPerito1(String numeroDocPerito1) {
        this.numeroDocPerito1 = numeroDocPerito1;
    }

    public String getNombrePerito1() {
        return nombrePerito1;
    }

    public void setNombrePerito1(String nombrePerito1) {
        this.nombrePerito1 = nombrePerito1;
    }

    public String getApellidoPerito1() {
        return apellidoPerito1;
    }

    public void setApellidoPerito1(String apellidoPerito1) {
        this.apellidoPerito1 = apellidoPerito1;
    }

    public String getNumeroDocPerito2() {
        return numeroDocPerito2;
    }

    public void setNumeroDocPerito2(String numeroDocPerito2) {
        this.numeroDocPerito2 = numeroDocPerito2;
    }

    public String getNombrePerito2() {
        return nombrePerito2;
    }

    public void setNombrePerito2(String nombrePerito2) {
        this.nombrePerito2 = nombrePerito2;
    }

    public String getApellidoPerito2() {
        return apellidoPerito2;
    }

    public void setApellidoPerito2(String apellidoPerito2) {
        this.apellidoPerito2 = apellidoPerito2;
    }

    public String getNumeroDocPerito3() {
        return numeroDocPerito3;
    }

    public void setNumeroDocPerito3(String numeroDocPerito3) {
        this.numeroDocPerito3 = numeroDocPerito3;
    }

    public String getNombrePerito3() {
        return nombrePerito3;
    }

    public void setNombrePerito3(String nombrePerito3) {
        this.nombrePerito3 = nombrePerito3;
    }

    public String getApellidoPerito3() {
        return apellidoPerito3;
    }

    public void setApellidoPerito3(String apellidoPerito3) {
        this.apellidoPerito3 = apellidoPerito3;
    }

    public String getNumeroDocPerito4() {
        return numeroDocPerito4;
    }

    public void setNumeroDocPerito4(String numeroDocPerito4) {
        this.numeroDocPerito4 = numeroDocPerito4;
    }

    public String getNombrePerito4() {
        return nombrePerito4;
    }

    public void setNombrePerito4(String nombrePerito4) {
        this.nombrePerito4 = nombrePerito4;
    }

    public String getApellidoPerito4() {
        return apellidoPerito4;
    }

    public void setApellidoPerito4(String apellidoPerito4) {
        this.apellidoPerito4 = apellidoPerito4;
    }

    public String getNumeroDocPerito5() {
        return numeroDocPerito5;
    }

    public void setNumeroDocPerito5(String numeroDocPerito5) {
        this.numeroDocPerito5 = numeroDocPerito5;
    }

    public String getNombrePerito5() {
        return nombrePerito5;
    }

    public void setNombrePerito5(String nombrePerito5) {
        this.nombrePerito5 = nombrePerito5;
    }

    public String getApellidoPerito5() {
        return apellidoPerito5;
    }

    public void setApellidoPerito5(String apellidoPerito5) {
        this.apellidoPerito5 = apellidoPerito5;
    }

    public boolean isEstadoOpcionAsignado() {
        return estadoOpcionAsignado;
    }

    public void setEstadoOpcionAsignado(boolean estadoOpcionAsignado) {
        this.estadoOpcionAsignado = estadoOpcionAsignado;
    }

    public boolean isEstadoPreRadicaPerFacVP1() {
        return estadoPreRadicaPerFacVP1;
    }

    public void setEstadoPreRadicaPerFacVP1(boolean estadoPreRadicaPerFacVP1) {
        this.estadoPreRadicaPerFacVP1 = estadoPreRadicaPerFacVP1;
    }

    public boolean isEstadoPreRadicaPerFacVP2() {
        return estadoPreRadicaPerFacVP2;
    }

    public void setEstadoPreRadicaPerFacVP2(boolean estadoPreRadicaPerFacVP2) {
        this.estadoPreRadicaPerFacVP2 = estadoPreRadicaPerFacVP2;
    }

    public boolean isEstadoPreRadicaPerFacVP3() {
        return estadoPreRadicaPerFacVP3;
    }

    public void setEstadoPreRadicaPerFacVP3(boolean estadoPreRadicaPerFacVP3) {
        this.estadoPreRadicaPerFacVP3 = estadoPreRadicaPerFacVP3;
    }

    public boolean isEstadoPreRadicaPerFacVP4() {
        return estadoPreRadicaPerFacVP4;
    }

    public void setEstadoPreRadicaPerFacVP4(boolean estadoPreRadicaPerFacVP4) {
        this.estadoPreRadicaPerFacVP4 = estadoPreRadicaPerFacVP4;
    }

    public boolean isEstadoPreRadicaPerFacVP5() {
        return estadoPreRadicaPerFacVP5;
    }

    public void setEstadoPreRadicaPerFacVP5(boolean estadoPreRadicaPerFacVP5) {
        this.estadoPreRadicaPerFacVP5 = estadoPreRadicaPerFacVP5;
    }

    public String getTarifaPerFacVP1() {
        return TarifaPerFacVP1;
    }

    public void setTarifaPerFacVP1(String TarifaPerFacVP1) {
        this.TarifaPerFacVP1 = TarifaPerFacVP1;
    }

    public String getValorTarifPerFacVP1() {
        return ValorTarifPerFacVP1;
    }

    public void setValorTarifPerFacVP1(String ValorTarifPerFacVP1) {
        this.ValorTarifPerFacVP1 = ValorTarifPerFacVP1;
    }

    public String getTarifaPerFacVP2() {
        return TarifaPerFacVP2;
    }

    public void setTarifaPerFacVP2(String TarifaPerFacVP2) {
        this.TarifaPerFacVP2 = TarifaPerFacVP2;
    }

    public String getValorTarifPerFacVP2() {
        return ValorTarifPerFacVP2;
    }

    public void setValorTarifPerFacVP2(String ValorTarifPerFacVP2) {
        this.ValorTarifPerFacVP2 = ValorTarifPerFacVP2;
    }

    public String getTarifaPerFacVP3() {
        return TarifaPerFacVP3;
    }

    public void setTarifaPerFacVP3(String TarifaPerFacVP3) {
        this.TarifaPerFacVP3 = TarifaPerFacVP3;
    }

    public String getValorTarifPerFacVP3() {
        return ValorTarifPerFacVP3;
    }

    public void setValorTarifPerFacVP3(String ValorTarifPerFacVP3) {
        this.ValorTarifPerFacVP3 = ValorTarifPerFacVP3;
    }

    public String getTarifaPerFacVP4() {
        return TarifaPerFacVP4;
    }

    public void setTarifaPerFacVP4(String TarifaPerFacVP4) {
        this.TarifaPerFacVP4 = TarifaPerFacVP4;
    }

    public String getValorTarifPerFacVP4() {
        return ValorTarifPerFacVP4;
    }

    public void setValorTarifPerFacVP4(String ValorTarifPerFacVP4) {
        this.ValorTarifPerFacVP4 = ValorTarifPerFacVP4;
    }

    public String getTarifaPerFacVP5() {
        return TarifaPerFacVP5;
    }

    public void setTarifaPerFacVP5(String TarifaPerFacVP5) {
        this.TarifaPerFacVP5 = TarifaPerFacVP5;
    }

    public String getValorTarifPerFacVP5() {
        return ValorTarifPerFacVP5;
    }

    public void setValorTarifPerFacVP5(String ValorTarifPerFacVP5) {
        this.ValorTarifPerFacVP5 = ValorTarifPerFacVP5;
    }

    public boolean isEstadoPreRadicaPeritoVP1() {
        return estadoPreRadicaPeritoVP1;
    }

    public void setEstadoPreRadicaPeritoVP1(boolean estadoPreRadicaPeritoVP1) {
        this.estadoPreRadicaPeritoVP1 = estadoPreRadicaPeritoVP1;
    }

    public boolean isEstadoPreRadicaPeritoVP2() {
        return estadoPreRadicaPeritoVP2;
    }

    public void setEstadoPreRadicaPeritoVP2(boolean estadoPreRadicaPeritoVP2) {
        this.estadoPreRadicaPeritoVP2 = estadoPreRadicaPeritoVP2;
    }

    public boolean isEstadoPreRadicaPeritoVP3() {
        return estadoPreRadicaPeritoVP3;
    }

    public void setEstadoPreRadicaPeritoVP3(boolean estadoPreRadicaPeritoVP3) {
        this.estadoPreRadicaPeritoVP3 = estadoPreRadicaPeritoVP3;
    }

    public boolean isEstadoPreRadicaPeritoVP4() {
        return estadoPreRadicaPeritoVP4;
    }

    public void setEstadoPreRadicaPeritoVP4(boolean estadoPreRadicaPeritoVP4) {
        this.estadoPreRadicaPeritoVP4 = estadoPreRadicaPeritoVP4;
    }

    public boolean isEstadoPreRadicaPeritoVP5() {
        return estadoPreRadicaPeritoVP5;
    }

    public void setEstadoPreRadicaPeritoVP5(boolean estadoPreRadicaPeritoVP5) {
        this.estadoPreRadicaPeritoVP5 = estadoPreRadicaPeritoVP5;
    }

    public String getTarifaPeritoVP1() {
        return TarifaPeritoVP1;
    }

    public void setTarifaPeritoVP1(String TarifaPeritoVP1) {
        this.TarifaPeritoVP1 = TarifaPeritoVP1;
    }

    public String getValorTarifPeritoVP1() {
        return ValorTarifPeritoVP1;
    }

    public void setValorTarifPeritoVP1(String ValorTarifPeritoVP1) {
        this.ValorTarifPeritoVP1 = ValorTarifPeritoVP1;
    }

    public String getTarifaPeritoVP2() {
        return TarifaPeritoVP2;
    }

    public void setTarifaPeritoVP2(String TarifaPeritoVP2) {
        this.TarifaPeritoVP2 = TarifaPeritoVP2;
    }

    public String getValorTarifPeritoVP2() {
        return ValorTarifPeritoVP2;
    }

    public void setValorTarifPeritoVP2(String ValorTarifPeritoVP2) {
        this.ValorTarifPeritoVP2 = ValorTarifPeritoVP2;
    }

    public String getTarifaPeritoVP3() {
        return TarifaPeritoVP3;
    }

    public void setTarifaPeritoVP3(String TarifaPeritoVP3) {
        this.TarifaPeritoVP3 = TarifaPeritoVP3;
    }

    public String getValorTarifPeritoVP3() {
        return ValorTarifPeritoVP3;
    }

    public void setValorTarifPeritoVP3(String ValorTarifPeritoVP3) {
        this.ValorTarifPeritoVP3 = ValorTarifPeritoVP3;
    }

    public String getTarifaPeritoVP4() {
        return TarifaPeritoVP4;
    }

    public void setTarifaPeritoVP4(String TarifaPeritoVP4) {
        this.TarifaPeritoVP4 = TarifaPeritoVP4;
    }

    public String getValorTarifPeritoVP4() {
        return ValorTarifPeritoVP4;
    }

    public void setValorTarifPeritoVP4(String ValorTarifPeritoVP4) {
        this.ValorTarifPeritoVP4 = ValorTarifPeritoVP4;
    }

    public String getTarifaPeritoVP5() {
        return TarifaPeritoVP5;
    }

    public void setTarifaPeritoVP5(String TarifaPeritoVP5) {
        this.TarifaPeritoVP5 = TarifaPeritoVP5;
    }

    public String getValorTarifPeritoVP5() {
        return ValorTarifPeritoVP5;
    }

    public void setValorTarifPeritoVP5(String ValorTarifPeritoVP5) {
        this.ValorTarifPeritoVP5 = ValorTarifPeritoVP5;
    }

    public String getObservaRegistro() {
        return ObservaRegistro;
    }

    public void setObservaRegistro(String ObservaRegistro) {
        this.ObservaRegistro = ObservaRegistro;
    }

    public String getObservaPreRad() {
        return ObservaPreRad;
    }

    public void setObservaPreRad(String ObservaPreRad) {
        this.ObservaPreRad = ObservaPreRad;
    }

    public String getObservaPreRadInicio() {
        return ObservaPreRadInicio;
    }

    public void setObservaPreRadInicio(String ObservaPreRadInicio) {
        this.ObservaPreRadInicio = ObservaPreRadInicio;
    }

    public String getObservaPreRadGeneral() {
        return ObservaPreRadGeneral;
    }

    public void setObservaPreRadGeneral(String ObservaPreRadGeneral) {
        this.ObservaPreRadGeneral = ObservaPreRadGeneral;
    }

    public String getCodAnalista() {
        return CodAnalista;
    }

    public void setCodAnalista(String CodAnalista) {
        this.CodAnalista = CodAnalista;
    }

    public ArrayList<SelectItem> getCargaAnali() {
        return CargaAnali;
    }

    public void setCargaAnali(ArrayList<SelectItem> CargaAnali) {
        this.CargaAnali = CargaAnali;
    }

    public List<LogPreRadicacion> getListPreRadicados() {
        return ListPreRadicados;
    }

    public void setListPreRadicados(List<LogPreRadicacion> ListPreRadicados) {
        this.ListPreRadicados = ListPreRadicados;
    }

    public List<LogPreRadicacion> getListPreRadicadosGestor() {
        return ListPreRadicadosGestor;
    }

    public void setListPreRadicadosGestor(List<LogPreRadicacion> ListPreRadicadosGestor) {
        this.ListPreRadicadosGestor = ListPreRadicadosGestor;
    }

    public List<LogPreRadicacion> getListPreRadicaAsignados() {
        return ListPreRadicaAsignados;
    }

    public void setListPreRadicaAsignados(List<LogPreRadicacion> ListPreRadicaAsignados) {
        this.ListPreRadicaAsignados = ListPreRadicaAsignados;
    }

    public List<LogPreRadicacion> getListPreRadicaSeleccAsignados() {
        return ListPreRadicaSeleccAsignados;
    }

    public void setListPreRadicaSeleccAsignados(List<LogPreRadicacion> ListPreRadicaSeleccAsignados) {
        this.ListPreRadicaSeleccAsignados = ListPreRadicaSeleccAsignados;
    }

    public List<LogPreRadicacion> getListEmpAsignaciones() {
        return ListEmpAsignaciones;
    }

    public void setListEmpAsignaciones(List<LogPreRadicacion> ListEmpAsignaciones) {
        this.ListEmpAsignaciones = ListEmpAsignaciones;
    }

    public List<LogPreRadicacion> getListMisAsignados() {
        return ListMisAsignados;
    }

    public void setListMisAsignados(List<LogPreRadicacion> ListMisAsignados) {
        this.ListMisAsignados = ListMisAsignados;
    }

    public List<LogPreRadicacion> getListAllMisAsignados() {
        return ListAllMisAsignados;
    }

    public void setListAllMisAsignados(List<LogPreRadicacion> ListAllMisAsignados) {
        this.ListAllMisAsignados = ListAllMisAsignados;
    }

    public List<LogPreRadicacion> getListAllMisAsignadosRadicacion() {
        return ListAllMisAsignadosRadicacion;
    }

    public void setListAllMisAsignadosRadicacion(List<LogPreRadicacion> ListAllMisAsignadosRadicacion) {
        this.ListAllMisAsignadosRadicacion = ListAllMisAsignadosRadicacion;
    }

    public List<LogPreRadicacion> getListMisAginadosPreRad() {
        return ListMisAginadosPreRad;
    }

    public void setListMisAginadosPreRad(List<LogPreRadicacion> ListMisAginadosPreRad) {
        this.ListMisAginadosPreRad = ListMisAginadosPreRad;
    }

    public List<LogPreRadicacion> getListMisAginadosPreRadica() {
        return ListMisAginadosPreRadica;
    }

    public void setListMisAginadosPreRadica(List<LogPreRadicacion> ListMisAginadosPreRadica) {
        this.ListMisAginadosPreRadica = ListMisAginadosPreRadica;
    }

    public List<LogPreRadicacion> getListMisPreRadicaciones() {
        return ListMisPreRadicaciones;
    }

    public void setListMisPreRadicaciones(List<LogPreRadicacion> ListMisPreRadicaciones) {
        this.ListMisPreRadicaciones = ListMisPreRadicaciones;
    }

    public List<LogPreRadicacion> getListObserPreRadicados() {
        return ListObserPreRadicados;
    }

    public void setListObserPreRadicados(List<LogPreRadicacion> ListObserPreRadicados) {
        this.ListObserPreRadicados = ListObserPreRadicados;
    }


    public LogEntidad getEnt() {
        return Ent;
    }

    public void setEnt(LogEntidad Ent) {
        this.Ent = Ent;
    }

    public LogAdministracion getAdm() {
        return Adm;
    }

    public void setAdm(LogAdministracion Adm) {
        this.Adm = Adm;
    }

    
    public LogAvaluo getAva() {
        return Ava;
    }

    public void setAva(LogAvaluo Ava) {
        this.Ava = Ava;
    }

    public LogPreRadicacion getPreRad() {
        return PreRad;
    }

    public void setPreRad(LogPreRadicacion PreRad) {
        this.PreRad = PreRad;
    }

    public LogPreRadicacion getPreRadi() {
        return PreRadi;
    }

    public void setPreRadi(LogPreRadicacion PreRadi) {
        this.PreRadi = PreRadi;
    }

    public LogPreRadicacion getPreRadica() {
        return PreRadica;
    }

    public void setPreRadica(LogPreRadicacion PreRadica) {
        this.PreRadica = PreRadica;
    }

    public LogPreRadicacion getPreRadRegiMisAsig() {
        return preRadRegiMisAsig;
    }

    public void setPreRadRegiMisAsig(LogPreRadicacion preRadRegiMisAsig) {
        this.preRadRegiMisAsig = preRadRegiMisAsig;
    }

    public LogPreRadicacion getPreRadMisPreRadic() {
        return preRadMisPreRadic;
    }

    public void setPreRadMisPreRadic(LogPreRadicacion preRadMisPreRadic) {
        this.preRadMisPreRadic = preRadMisPreRadic;
    }


    public LogPermiso getPerm() {
        return Perm;
    }

    public void setPerm(LogPermiso Perm) {
        this.Perm = Perm;
    }

    public String getFechaSoli() {
        return fechaSoli;
    }

    public void setFechaSoli(String fechaSoli) {
        this.fechaSoli = fechaSoli;
    }

    public String getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(String fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public Date getFecSolicitudPreRad() {
        return FecSolicitudPreRad;
    }

    public void setFecSolicitudPreRad(Date FecSolicitudPreRad) {
        this.FecSolicitudPreRad = FecSolicitudPreRad;
    }

    public String getHoraSolisitud() {
        return HoraSolisitud;
    }

    public void setHoraSolisitud(String HoraSolisitud) {
        this.HoraSolisitud = HoraSolisitud;
    }

    public String getTamañoTablas() {
        return tamañoTablas;
    }

    public void setTamañoTablas(String tamañoTablas) {
        this.tamañoTablas = tamañoTablas;
    }

    public boolean isEstadoBotonEstudio() {
        return estadoBotonEstudio;
    }

    public void setEstadoBotonEstudio(boolean estadoBotonEstudio) {
        this.estadoBotonEstudio = estadoBotonEstudio;
    }

    public boolean isEstadotabsPreRadica() {
        return estadotabsPreRadica;
    }

    public void setEstadotabsPreRadica(boolean estadotabsPreRadica) {
        this.estadotabsPreRadica = estadotabsPreRadica;
    }

    public int getCodMotivoAnulacionPreRadica() {
        return codMotivoAnulacionPreRadica;
    }

    public void setCodMotivoAnulacionPreRadica(int codMotivoAnulacionPreRadica) {
        this.codMotivoAnulacionPreRadica = codMotivoAnulacionPreRadica;
    }

    public int getCodMotivoAnulacionPreRadicaRadica() {
        return codMotivoAnulacionPreRadicaRadica;
    }

    public void setCodMotivoAnulacionPreRadicaRadica(int codMotivoAnulacionPreRadicaRadica) {
        this.codMotivoAnulacionPreRadicaRadica = codMotivoAnulacionPreRadicaRadica;
    }

    public String getObserCambioEstadoPreRadica() {
        return ObserCambioEstadoPreRadica;
    }

    public void setObserCambioEstadoPreRadica(String ObserCambioEstadoPreRadica) {
        this.ObserCambioEstadoPreRadica = ObserCambioEstadoPreRadica;
    }

    public String getObserCambioEstadoPreRadicaRadica() {
        return ObserCambioEstadoPreRadicaRadica;
    }

    public void setObserCambioEstadoPreRadicaRadica(String ObserCambioEstadoPreRadicaRadica) {
        this.ObserCambioEstadoPreRadicaRadica = ObserCambioEstadoPreRadicaRadica;
    }

    public String getObserCambioEstadoImpedir() {
        return ObserCambioEstadoImpedir;
    }

    public void setObserCambioEstadoImpedir(String ObserCambioEstadoImpedir) {
        this.ObserCambioEstadoImpedir = ObserCambioEstadoImpedir;
    }

    public String getObserCambioEstadoImpedirDesdePreRad() {
        return ObserCambioEstadoImpedirDesdePreRad;
    }

    public void setObserCambioEstadoImpedirDesdePreRad(String ObserCambioEstadoImpedirDesdePreRad) {
        this.ObserCambioEstadoImpedirDesdePreRad = ObserCambioEstadoImpedirDesdePreRad;
    }

    public String getOpcionRadioEstados() {
        return opcionRadioEstados;
    }

    public void setOpcionRadioEstados(String opcionRadioEstados) {
        this.opcionRadioEstados = opcionRadioEstados;
    }

    public boolean isPreRadicacionRegistro() {
        return preRadicacionRegistro;
    }

    public void setPreRadicacionRegistro(boolean preRadicacionRegistro) {
        this.preRadicacionRegistro = preRadicacionRegistro;
    }

    public boolean isPreRadicacionModificacion() {
        return preRadicacionModificacion;
    }

    public void setPreRadicacionModificacion(boolean preRadicacionModificacion) {
        this.preRadicacionModificacion = preRadicacionModificacion;
    }

    public int getNumeroRegistrosAsig() {
        return NumeroRegistrosAsig;
    }

    public void setNumeroRegistrosAsig(int NumeroRegistrosAsig) {
        this.NumeroRegistrosAsig = NumeroRegistrosAsig;
    }

    public int getNumeroMisPreradicaciones() {
        return NumeroMisPreradicaciones;
    }

    public void setNumeroMisPreradicaciones(int NumeroMisPreradicaciones) {
        this.NumeroMisPreradicaciones = NumeroMisPreradicaciones;
    }

    public int getNumeroTotalPreRadicaciones() {
        return NumeroTotalPreRadicaciones;
    }

    public void setNumeroTotalPreRadicaciones(int NumeroTotalPreRadicaciones) {
        this.NumeroTotalPreRadicaciones = NumeroTotalPreRadicaciones;
    }

    public String getNombreDeptoBien() {
        return nombreDeptoBien;
    }

    public void setNombreDeptoBien(String nombreDeptoBien) {
        this.nombreDeptoBien = nombreDeptoBien;
    }

    public String getNombreCiudadBien() {
        return nombreCiudadBien;
    }

    public void setNombreCiudadBien(String nombreCiudadBien) {
        this.nombreCiudadBien = nombreCiudadBien;
    }

    public String getOpcionConsul() {
        return opcionConsul;
    }

    public void setOpcionConsul(String opcionConsul) {
        this.opcionConsul = opcionConsul;
    }

    public boolean isHabilitarBotonPreRadica() {
        return habilitarBotonPreRadica;
    }

    public void setHabilitarBotonPreRadica(boolean habilitarBotonPreRadica) {
        this.habilitarBotonPreRadica = habilitarBotonPreRadica;
    }
    /*
    public void onRowSelect(SelectEvent event) {
        Valor = String.valueOf(getPreRadRegiMisAsig().getCodPreRadica());
    }
     */
    public String Valor = "";

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public void listenFilter(FilterEvent event) {

        DataTable table = (DataTable) event.getSource();
        Map<String, Object> filters = table.getFilters();
        //grab the value from the required map key (somePropertyName if your filterBy 
        Iterator it = filters.keySet().iterator();
        while (it.hasNext()) {
            it.next();
            Valor = (String) filters.get("codPreRadica");
            Valor = (String) filters.get("nomProEnt");
        }
    }
    private int RowKey;

    public int getRowKey() {
        return RowKey;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg1;
        getListPreMisAsig().get(0).getCodPreRadica();
        msg1 = new FacesMessage("Car Selected", String.valueOf(getListPreMisAsig().get(0).getCodPreRadica()));
        FacesMessage msg;
        msg = new FacesMessage("Car Selected", String.valueOf(((LogPreRadicacion) event.getObject()).getCodPreRadica()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

        getListPreMisAsig().get(0).getCodPreRadica();
        /*UIData data = (UIData) event.getComponent().findComponent("DtgMisAsignados");
  
        int rowIndex = data.getRowIndex();       
        
//Valor = String.valueOf(getRowKey());
       // System.out.println("Hola Baby" + event.getObject());
        DataTable table = (DataTable) event.getSource();
        int Valor1 = table.getRowIndex();
        String Valor2 = table.getRowIndexVar();
     /*
        String Valor3 = table.getId();
        String Valor4 = (String) table.getRowData("rowIndex");
        getRowKey();
         */

    }

    /*
    public void filterListener(FilterEvent filterEvent)
    {
        preRadRegiMisAsig.getCodPreRadica();
         mbTodero.setMens("Mensaje de info selexcionada " + preRadRegiMisAsig.getCodPreRadica());
        mbTodero.info();
    }
     */
    public void tatiana() {
        mbTodero.setMens("Hola Tatiana");
        mbTodero.info();
        int Valor = preRadRegiMisAsig.getCodPreRadica();
        mbTodero.setMens( "Valor " + Valor);
        mbTodero.info();
    }

    public void editBook(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        // Object o = table.getRowData();
        // Eventually, if you need the index of the line, simply do:
        int index = table.getRowIndex();
        // ...
    }

// Method to get the HtmlDataTable.
    private HtmlDataTable getParentDatatable(UIComponent compo) {
        if (compo == null) {
            return null;
        }
        if (compo instanceof HtmlDataTable) {
            return (HtmlDataTable) compo;
        }
        return getParentDatatable(compo.getParent());
    }

    public void changeClient(SelectEvent selectEvent) {
        LogPreRadicacion client = (LogPreRadicacion) selectEvent.getObject();
        LogPreRadicacion selectedClient = client;
        int cclnCode = client.getCodPreRadica();
        //LogPreRadicacion   selectedAccounts =   getService().listAccounts(cclnCode);
    }

    /**
     * FIN Metodos get y set de todas las variables / atributos, listas, etc que
     * se utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    public void displayRowIndex() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String pIndex = (String) map.get("index");
        int index = Integer.parseInt(pIndex);
    }
         private int selectedRowIndex = -1;

    public int getSelectedRowIndex() {
        return selectedRowIndex;
    }

    public void setSelectedRowIndex(int selectedRowIndex) {
        this.selectedRowIndex = selectedRowIndex;
    }
         
         
          private Order val;
    /*

     public void method(){
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("FormMisAsignados:DtgMisAsignados");
        MyDataTableObject myObject = (MyDataTableObject) dataTable.getSelection();            
    } 
*/
     
       public void moveNext() {
        if(val != null && selectedRowIndex > -1)
            selectedRowIndex++;
    }

public void action(){
	   String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenId");
mbTodero.setMens("Hola" + value);
mbTodero.info();
}
}
