package LogBean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de los elementos que comparten
 * varios ManagedBean dento de el proyecto </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-12-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBTodero")
@ViewScoped
@SessionScoped
public class BeanTodero {

    public BeanTodero() {
    }

    /**
     * Variable para los Mensajes*
     */
    private static String Mens = "";
    private String tamañoCa = "auto";
    private boolean EstadoColumnaTabla = false;
    /**
     * Variable de url de el path de las fotos de los usuarios*
     */
    private String UrlFotoEmp = File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "DBFotos" + File.separator;
    private String encabezado = "Ocultar";

    /**
     * Fecha actual*
     */
    private String fecha_actual;
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return
     */
    public String getMens() {
        return Mens;
    }

    public void setMens(String Mens) {
        this.Mens = Mens;
    }

    public String getTamañoCa() {
        return tamañoCa;
    }

    public void setTamañoCa(String tamañoCa) {
        this.tamañoCa = tamañoCa;
    }

    public boolean isEstadoColumnaTabla() {
        return EstadoColumnaTabla;
    }

    public void setEstadoColumnaTabla(boolean EstadoColumnaTabla) {
        this.EstadoColumnaTabla = EstadoColumnaTabla;
    }

    public String getUrlFotoEmp() {
        return UrlFotoEmp;
    }

    public void setUrlFotoEmp(String UrlFotoEmp) {
        this.UrlFotoEmp = UrlFotoEmp;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
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
     * Metodo que permite mostrar un mensaje de caracter informativo para el
     * usuario
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", Mens));
        Mens = "";
    }

    /**
     * Metodo que permite mostrar un mensaje de advertencia para los usuarios
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", Mens));
        Mens = "";
    }

    /**
     * Metodo que permite mostrar un mensaje de error para los usuarios
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", Mens));
        Mens = "";
    }

    /**
     * Metodo que permite mostrar un mensaje fatal para los usuarios
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", Mens));
        Mens = "";
    }

    /**
     * Metodo que permite el reseteo de las tablas
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param Terminal int que contiene el valor de identificador de una tabla
     * @since 01-05-2015
     */
    public void resetTable(String Terminal) {
        try {
            DataTable DatTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":contenedorGeneral:Contenedor:" + Terminal);
            DatTable.reset();
        } catch (Exception e) {
            //Mens = "Error en el metodo '" + this.getClass() + ".resetTable()' causado por: " + e.getMessage();
            //error();
        }
    }

    /**
     * Metodo para cargar todas la 'fecha actual' del sistema
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return String que retorna la fecha actual
     * @since 01-05-2015
     */
    public String traerFechaActual() {
        try {
            
        } catch (Exception e) {
            Mens = "Error en el metodo '" + this.getClass() + ".traerFechaActual()' causado por: " + e.getMessage();
            error();
        }
        Calendar fecha = new GregorianCalendar();
        Date fecha1 = new Date();
        fecha1 = fecha.getTime();
        fecha_actual = Format.format(fecha1);
        return fecha_actual;
    }

    /**
     * Metodo para cambiar el tamaño de diferente componentes que se utilizan,
     * para autoajustar estos a las diferentes pantallas
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void cambiotamaño() {
        try {
            if ("Ocultar".equals(this.encabezado)) {
                this.encabezado = "Mostrar";
                this.tamañoCa = "auto";
            } else {
                this.encabezado = "Ocultar";
                this.tamañoCa = "auto";
            }
        } catch (Exception e) {
            Mens = "Error en el metodo '" + this.getClass() + ".cambiotamaño()' causado por: " + e.getMessage();
            error();
        }
    }
    
    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
