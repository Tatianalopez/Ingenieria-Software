/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Logic.LogAdministracion;
import Logic.LogPermiso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de permisos de acceso a los
 * diferentes modulos y submodulos de el sistema etc </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-10-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBPermiso")
@ViewScoped
@SessionScoped
public class BeanPermiso {

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogPermiso Per = new LogPermiso();
    LogAdministracion Adm = new LogAdministracion();
    LogPermiso AdmRoles = new LogPermiso();

    /**
     * Listas que cargan informacion relacionada a los permisis y roles que se
     * manejan*
     */
    ResultSet Dat = null;
    ResultSet Tab = null;
    private List<SelectItem> CargaProducEnt = new ArrayList<>();
    private List<SelectItem> CargaProducEntAsignado = new ArrayList<>();
    private List<SelectItem> CargaProducEntAsignadoRoles = new ArrayList<>();
    private List<SelectItem> CargaProducEntAsignadoRoles1 = new ArrayList<>();
    private List<SelectItem> CargProcesos;
    private List<SelectItem> CargProcesosExis = new ArrayList<>();
    private List<LogPermiso> ProdEntiRoles = new ArrayList<>();
    SelectItem prod_entidad_asignado;

    /**
     * Modificacion empleado*
     */
    private List<LogPermiso> ListPermisosAsig = new ArrayList<>();
    private List<LogPermiso> ListProdEntiAsig = new ArrayList<>();

    /**
     * Variables carga de picklist*
     */
    List<LogPermiso> CargaRoles;  //Carga los roles  (source)
    List<LogPermiso> CargaRolesAsig = new ArrayList<>(); //Carga los roles que van a ser asignados (target)    
    DualListModel<LogPermiso> teamsDualListModel; //creacion de lista de manejo para el componente picklist

    private String TituloDialogRoles;

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
     * Variables para el manejo de roles*
     */
    private int CodRolseleccionado;
    private int CodRoles;
    private String NombreRol;
    private String descripcionRol;
    /**
     * Variables para el manejo de acceso*
     */
    private int CodAcceso;
    private String NombreModulo;
    private String NombreAcceso;
    /**
     * Variables para el manejo de producto entidad*
     */
    private int CodProEnt;
    private List NombreProEnt;

    /**
     * Variables para el manejo de procesos*
     */
    private List NombreProceso;
    private List NombreProceso1;

    public BeanPermiso() {

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
    public List<SelectItem> getCargProcesos() {
        return CargProcesos;
    }

    public void setCargProcesos(List<SelectItem> CargProcesos) {
        this.CargProcesos = CargProcesos;
    }

    public List<SelectItem> getCargProcesosExis() {
        return CargProcesosExis;
    }

    public void setCargProcesosExis(List<SelectItem> CargProcesosExis) {
        this.CargProcesosExis = CargProcesosExis;
    }

    public List<LogPermiso> getListPermisosAsig() {
        return ListPermisosAsig;
    }

    public void setListPermisosAsig(List<LogPermiso> ListPermisosAsig) {
        this.ListPermisosAsig = ListPermisosAsig;
    }

    public List<LogPermiso> getListProdEntiAsig() {
        return ListProdEntiAsig;
    }

    public void setListProdEntiAsig(List<LogPermiso> ListProdEntiAsig) {
        this.ListProdEntiAsig = ListProdEntiAsig;
    }

    public List<SelectItem> getCargaProducEnt() {
        return CargaProducEnt;
    }

    public void setCargaProducEnt(List<SelectItem> CargaProducEnt) {
        this.CargaProducEnt = CargaProducEnt;
    }

    public int getCodRolseleccionado() {
        return CodRolseleccionado;
    }

    public void setCodRolseleccionado(int CodRolseleccionado) {
        this.CodRolseleccionado = CodRolseleccionado;
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

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public int getCodAcceso() {
        return CodAcceso;
    }

    public void setCodAcceso(int CodAcceso) {
        this.CodAcceso = CodAcceso;
    }

    public String getNombreModulo() {
        return NombreModulo;
    }

    public void setNombreModulo(String NombreModulo) {
        this.NombreModulo = NombreModulo;
    }

    public String getNombreAcceso() {
        return NombreAcceso;
    }

    public void setNombreAcceso(String NombreAcceso) {
        this.NombreAcceso = NombreAcceso;
    }

    public int getCodProEnt() {
        return CodProEnt;
    }

    public void setCodProEnt(int CodProEnt) {
        this.CodProEnt = CodProEnt;
    }

    public List<Integer> getNombreProEnt() {
        return NombreProEnt;
    }

    public void setNombreProEnt(List<Integer> NombreProEnt) {
        this.NombreProEnt = NombreProEnt;
    }

    public List<Integer> getNombreProceso() {
        return NombreProceso;
    }

    public void setNombreProceso(List<Integer> NombreProceso) {
        this.NombreProceso = NombreProceso;
    }

    public List getNombreProceso1() {
        return NombreProceso1;
    }

    public void setNombreProceso1(List NombreProceso1) {
        this.NombreProceso1 = NombreProceso1;
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

    public DualListModel<LogPermiso> getTeamsDualListModel() {
        return teamsDualListModel;
    }

    public void setTeamsDualListModel(DualListModel<LogPermiso> teamsDualListModel) {
        this.teamsDualListModel = teamsDualListModel;
    }

    public LogPermiso getAdmRoles() {
        return AdmRoles;
    }

    public void setAdmRoles(LogPermiso AdmRoles) {
        this.AdmRoles = AdmRoles;
    }

    public String getTituloDialogRoles() {
        return TituloDialogRoles;
    }

    public void setTituloDialogRoles(String TituloDialogRoles) {
        this.TituloDialogRoles = TituloDialogRoles;
    }

    public List<SelectItem> getCargaProducEntAsignado() {
        return CargaProducEntAsignado;
    }

    public void setCargaProducEntAsignado(List<SelectItem> CargaProducEntAsignado) {
        this.CargaProducEntAsignado = CargaProducEntAsignado;
    }

    public List<SelectItem> getCargaProducEntAsignadoRoles() {
        return CargaProducEntAsignadoRoles;
    }

    public void setCargaProducEntAsignadoRoles(List<SelectItem> CargaProducEntAsignadoRoles) {
        this.CargaProducEntAsignadoRoles = CargaProducEntAsignadoRoles;
    }

    public List<SelectItem> getCargaProducEntAsignadoRoles1() {
        return CargaProducEntAsignadoRoles1;
    }

    public void setCargaProducEntAsignadoRoles1(List<SelectItem> CargaProducEntAsignadoRoles1) {
        this.CargaProducEntAsignadoRoles1 = CargaProducEntAsignadoRoles1;
    }

    public SelectItem getProd_entidad_asignado() {
        return prod_entidad_asignado;
    }

    public void setProd_entidad_asignado(SelectItem prod_entidad_asignado) {
        this.prod_entidad_asignado = prod_entidad_asignado;
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
     * Metodo que consulta los roles que se tienen registrados
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
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
     * Metodo que carga los roles en la lista tipo picklist
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return List que retontna los roles
     * @since 01-05-2015
     */
    public List<LogPermiso> cargarTarget() {
        try {
            CargaRolesAsig = teamsDualListModel.getTarget();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarTarget()' causado por: " + e.getMessage());
            mbTodero.error();

        }
        return CargaRolesAsig;
    }

    /**
     * Metodo que carga el Producto Entidad
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return List que carga los productos entidad
     * @since 01-05-2015
     */
    public List<SelectItem> getcargaProEnt() {
        try {
            Iterator<LogPermiso> Ite;
            Ite = Per.getProEnt().iterator();
            while (Ite.hasNext()) {
                LogPermiso MBPer = Ite.next();
                this.CargaProducEnt.add(new SelectItem(MBPer.getCodProEnt(), MBPer.getNombreProEnt()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getcargaProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaProducEnt;
    }

    /**
     * Metodo que carga los Procesos existentes
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return List que retorna informacion de los procesos encontrados
     * @since 01-05-2015
     */
    public List<SelectItem> cargaProcesos() {
        try {
            this.CargProcesos = new ArrayList<>();
            Iterator<LogPermiso> Ite;
            Ite = Per.ConsulAllProcesos().iterator();
            while (Ite.hasNext()) {
                LogPermiso MBPer = Ite.next();
                this.CargProcesos.add(new SelectItem(MBPer.getCodProceso(), MBPer.getNombreProceso()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargaProcesos()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargProcesos;
    }

    /**
     * Metodo que carga los Procesos existentes de acuerdo a un rol seleccionado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return List que retorna informacion de los procesos encontrados
     * @since 01-05-2015
     */
    public List<SelectItem> cargaProcesosExis() {
        try {
            Iterator<LogPermiso> Ite;
            Ite = Per.ConsulProcesosExis(this.CodRolseleccionado).iterator();
            while (Ite.hasNext()) {
                LogPermiso MBPer = Ite.next();
                this.CargProcesosExis.add(new SelectItem(MBPer.getCodProceso(), MBPer.getNombreProceso1()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargaProcesosExis()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargProcesosExis;
    }

    /**
     * Metodo que consulta los productos entidad que tiene un usuario asignado
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void getcargaProEntAsignado() {
        try {
            CargaProducEntAsignado = new ArrayList<>();
            for (int j = 0; j <= this.NombreProEnt.size() - 1; j++) { //carga los producto entidad que fueron seleccionados para el empleado
                //prod_entidad_asignado = NombreProEnt.get(j);
                String codigo = NombreProEnt.get(j).toString();
                String nombre = Per.getProEntUnico(codigo);
                CargaProducEntAsignado.add(new SelectItem(codigo, nombre));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getcargaProEntAsignado()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que carga los productos entidad que tiene asignado un empleado de
     * acuerdo a los roles
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param tipo int que carga los productos ebtidad de acuerdo a: 1=regsitro
     * 2=modificar
     * @since 01-05-2015
     */
    public void getcargaProEntAsignadoRoles(int tipo) {
        try {
            if (tipo == 1) { //regsitro
                CargaProducEntAsignadoRoles = new ArrayList<>();
                for (int j = 0; j <= this.NombreProceso.size() - 1; j++) { //carga los producto entidad que fueron seleccionados para el empleado
                    //prod_entidad_asignado = NombreProEnt.get(j);
                    String codigo = NombreProceso.get(j).toString();
                    String nombre = Per.getRolesUnico(codigo);
                    CargaProducEntAsignadoRoles.add(new SelectItem(codigo, nombre));
                }
            } else if (tipo == 2) { // modificar
                CargaProducEntAsignadoRoles1 = new ArrayList<>();
                for (int j = 0; j <= this.NombreProceso1.size() - 1; j++) { //carga los producto entidad que fueron seleccionados para el empleado
                    //prod_entidad_asignado = NombreProEnt.get(j);
                    String codigo = NombreProceso1.get(j).toString();
                    String nombre = Per.getRolesUnico(codigo);
                    CargaProducEntAsignadoRoles1.add(new SelectItem(codigo, nombre));
                }
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getcargaProEntAsignadoRoles()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que abre el formulario tipo dialogo para crear los diferentes
     * roles
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void opencreateroles() {
        try {
            NombreRol = "";
            descripcionRol = "";
            NombreProceso = new ArrayList<>();
            this.CargaProducEntAsignadoRoles = new ArrayList<>();
            this.CargProcesos = cargaProcesos();
            TituloDialogRoles = "Registrar nuevo rol";
            RequestContext.getCurrentInstance().execute("PF('DlgRol').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".opencreateroles()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que inserta los roles ingresados
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void inserRoles() {
        try {
            mbTodero.resetTable("FrmRol:RolTable");
            if ("".equals(NombreRol)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(descripcionRol)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else if (this.NombreProceso.size() <= 0) {
                mbTodero.setMens("Debe seleccionar un 'Proceso por asignar'");
                mbTodero.warn();
            } else {
                Per.setNombreRol(NombreRol);
                Per.setDescripcionRol(descripcionRol);
                String CodPro;
                int CorR = 0;
                Tab = Per.ConsulCodRol(mBsesion.codigoMiSesion());
                if (Tab.next()) {
                    CorR = Tab.getInt("Cod_Roles");
                    for (int j = 0; j <= this.NombreProceso.size() - 1; j++) { //carga los procesos de los roles
                        CodPro = NombreProceso.get(j).toString();
                        Dat = Per.ConsulAcceProc(Integer.parseInt(CodPro));
                        while (Dat.next()) {
                            int CodAcc = Integer.parseInt(Dat.getString("Cod_Acceso"));
                            Per.setCodRoles(CorR);
                            Per.setCodAcceso(CodAcc);
                            Per.InserCar(mBsesion.codigoMiSesion());
                        }
                        Conexion.Conexion.CloseCon();
                    }
                }
                Conexion.Conexion.CloseCon();
                mbTodero.setMens("Se ha generado el registro");
                mbTodero.info();
                RequestContext.getCurrentInstance().execute("PF('DlgRol').hide()");
                mbTodero.resetTable("FrmRol:RolTable");

                mBAdministacion.setListRoles(Adm.ConsulRoles());
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".llamarBeanEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que valida y abre el dialogo para modificar un rol
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void validarDatatableRol() {
        try {
            if (mBAdministacion.AdmRoles == null) {
                mbTodero.setMens("Debe seleccionar un registro");
                mbTodero.warn();
            } else {
                CodRoles = mBAdministacion.AdmRoles.getCodRol();
                NombreRol = mBAdministacion.AdmRoles.getNombre_Rol();
                descripcionRol = mBAdministacion.AdmRoles.getDescripcion_Rol();
                this.CodRolseleccionado = mBAdministacion.AdmRoles.getCodRol();
                ProdEntiRoles = Per.ConsulProcesosExis(this.CodRolseleccionado);
                List<Integer> codigos = new ArrayList<>();
                for (int i = 0; i <= ProdEntiRoles.size() - 1; i++) {
                    Integer cod_prodEnti = ProdEntiRoles.get(i).getCodProceso();
                    codigos.add(cod_prodEnti);
                }
                NombreProceso1 = codigos;
                getcargaProEntAsignadoRoles(2);
                TituloDialogRoles = "Modificar rol";
                RequestContext.getCurrentInstance().execute("PF('DlgRol').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarDatatableRol()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que realiza la modificación de Roles
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void modifiRol() {
        try {
            mbTodero.resetTable("FrmRol:RolTable");
            if ("".equals(NombreRol)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else if ("".equals(descripcionRol)) {
                mbTodero.setMens("Debe llenar el campo 'Descripción'");
                mbTodero.warn();
            } else if (this.NombreProceso1.size() <= 0) {
                mbTodero.setMens("Debe seleccionar un 'Proceso por asignar'");
                mbTodero.warn();
            } else {
                Per.setNombreRol(NombreRol);
                Per.setDescripcionRol(descripcionRol);
                Per.Actualizar_rol(CodRoles);
                String CodPro;
                int CorR = CodRoles;
                if (CorR != 0) {
                    for (int j = 0; j <= this.NombreProceso1.size() - 1; j++) {
                        CodPro = NombreProceso1.get(j).toString();
                        Dat = Per.ConsulAcceProc(Integer.parseInt(CodPro));
                        while (Dat.next()) {
                            int CodAcc = Integer.parseInt(Dat.getString("Cod_Acceso"));
                            Per.setCodRoles(CorR);
                            Per.setCodAcceso(CodAcc);
                            Per.InserCar(mBsesion.codigoMiSesion());
                        }
                        Conexion.Conexion.CloseCon();
                    }
                }
                mbTodero.setMens("Se ha generado la actualizacion");
                mbTodero.info();
                RequestContext.getCurrentInstance().execute("PF('DlgRol').hide()");
//                NombreRol = "";
//                descripcionRol = "";
//                NombreProceso = new ArrayList<>();
//                this.CargaProducEntAsignadoRoles = new ArrayList<>();
//                this.CargProcesos = cargaProcesos();
                mbTodero.resetTable("FrmRol:RolTable");

                mBAdministacion.setListRoles(Adm.ConsulRoles());
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiRol()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
