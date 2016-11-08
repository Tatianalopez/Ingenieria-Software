/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de los correos</b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @version 2.0.0
 * @since 01-12-2014 1.0.0
 *
 *
 */
@ManagedBean(name = "MBCorreo")
@ViewScoped
@SessionScoped
public class BeanCorreo {

    /**
     * Variables Implicitas*
     */
    Properties props = new Properties();

    public BeanCorreo() {
    }

    /*Variables nenesarias para enviar correos**/
    String Mens = "";
    private String MailEnvia;
    private String MailDestino;
    private String MailToReply;
    private String Asunto;
    private String Mensaje;
    private String PathAdjunto;
    private String[] PathsAdjuntos;

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
    public String getMailDestino() {
        return MailDestino;
    }

    public void setMailDestino(String MailDestino) {
        this.MailDestino = MailDestino;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getMailEnvia() {
        return MailEnvia;
    }

    public void setMailEnvia(String MailEnvia) {
        this.MailEnvia = MailEnvia;
    }

    public String getPathAdjunto() {
        return PathAdjunto;
    }

    public void setPathAdjunto(String PathAdjunto) {
        this.PathAdjunto = PathAdjunto;
    }

    public String getMailToReply() {
        return MailToReply;
    }

    public void setMailToReply(String MailToReply) {
        this.MailToReply = MailToReply;
    }

    public String[] getPathsAdjuntos() {
        return PathsAdjuntos;
    }

    public void setPathsAdjuntos(String[] PathsAdjuntos) {
        this.PathsAdjuntos = PathsAdjuntos;
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
     * Metodo permite enviar un correo electronico con un archivo adjunto, en
     * donde reune las variables necesarias, hace un enlace de conexion y envia
     * el email a un determinado destinarario
     *
     * @author Jhojan Stiven Rodriguez
     * @param nombreAdjunto String que contiene el nombre que se va a mostrar
     * del archivo adjunto que se envie
     * @since 01-11-2014
     */
    public void enviarCorreoAdjunto(String nombreAdjunto) {
        try {

            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", "smtp.gmail.com");

            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");

            // Nombre del usuario
            props.setProperty("mail.smtp.user", "backup@isainmobiliaria.com.co");

            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            //Mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(getMensaje(), "text/html");

            //Adjunto
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(getPathAdjunto())));
            adjunto.setFileName(nombreAdjunto);

            //Variable para enviar multiples items
            MimeMultipart multiParte = new MimeMultipart();

            //Agregar los items al cuerpo del correo
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            //mensaje
            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress("backup@isainmobiliaria.com.co"));

            // A quien va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getMailDestino()));
            //A quien sera respondido el correo
            message.setReplyTo(new javax.mail.Address[]{
                new javax.mail.internet.InternetAddress(getMailToReply())
            }
            );
            // contenido del correo --------------
            //asunto
            message.setSubject(getAsunto());
            //mensaje
            message.setContent(multiParte);

            //envio del correo ----------------
            //clase transport y se le establece el protocolo de transporte de envio
            Transport t = session.getTransport("smtp");

            //conectarse a la cuenta desde la cual se enviara el mensaje
            t.connect("backup@isainmobiliaria.com.co", "isa3230450");
            t.sendMessage(message, message.getAllRecipients());
            //terminar el envio y cerrar la conexion
            t.close();

        } catch (MessagingException e) {
            //JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
            mbTodero.setMens("No hay conexion disponible");
            mbTodero.fatal();
        }
    }

    /**
     * Metodo permite enviar un correo electronico con varios archivos adjuntos,
     * en donde reune las variables necesarias, hace un enlace de conexion y
     * envia el email a un determinado destinarario
     *
     * @author Jhojan Stiven Rodriguez
     * @param nombreAdjunto String que contiene el nombre que se va a mostrar
     * del archivo adjunto que se envie
     * @param pathsAdjuntos List que carga los multiples archivos que se quieran
     * adjuntar en el correo
     * @since 01-11-2014
     */
    public void enviarCorreoVariosAdjuntos(String nombreAdjunto, List<String> pathsAdjuntos) {
        try {

            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", "smtp.gmail.com");

            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");

            // Nombre del usuario
            props.setProperty("mail.smtp.user", "backup@isainmobiliaria.com.co");

            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            //Mensaje
            BodyPart texto = new MimeBodyPart();
            texto.setContent(getMensaje(), "text/html");

            //Variable para enviar multiples items
            MimeMultipart multiParte = new MimeMultipart();

            //Adjunto
            BodyPart adjunto = new MimeBodyPart();
            //Se adjuntan los archivos al correo
            if (pathsAdjuntos != null && pathsAdjuntos.size() > 0) {
                for (String rutaAdjunto : pathsAdjuntos) {
                    adjunto = new MimeBodyPart();
                    File f = new File(rutaAdjunto);
                    if (f.exists()) {
                        adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaAdjunto)));
                        adjunto.setFileName(f.getName());
                        multiParte.addBodyPart(adjunto);
                    }
                }
            }

            //Agregar los items al cuerpo del correo
            multiParte.addBodyPart(texto);

            //mensaje
            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress("backup@isainmobiliaria.com.co"));

            // A quien va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getMailDestino()));
//A quien sera respondido el correo
            message.setReplyTo(new javax.mail.Address[]{
                new javax.mail.internet.InternetAddress(getMailToReply())
            }
            );
            // contenido del correo --------------
            //asunto
            message.setSubject(getAsunto());
            //mensaje
            message.setContent(multiParte);

            //envio del correo ----------------
            //clase transport y se le establece el protocolo de transporte de envio
            Transport t = session.getTransport("smtp");

            //conectarse a la cuenta desde la cual se enviara el mensaje
            t.connect("backup@isainmobiliaria.com.co", "isa3230450");
            t.sendMessage(message, message.getAllRecipients());
            //terminar el envio y cerrar la conexion
            t.close();

        } catch (MessagingException e) {
            //JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
            mbTodero.setMens("No hay conexion disponible");
            mbTodero.fatal();
        }
    }

    /**
     * Metodo permite enviar un correo electronico, en donde reune las variables
     * necesarias, hace un enlace de conexion y envia el email a un determinado
     * destinarario
     *
     * @author Jhojan Stiven Rodriguez
     * @param tipo int que define si el correo llevara incluido el atributo
     * .setReplyTo(), el cual se utiliza para agregar un email al cual el
     * destinatario podra responder el correo enviado
     * @since 01-11-2014
     */
    public void enviarCorreo(int tipo) {
        try {

            // Nombre del host de correo, es smtp.gmail.com
            props.setProperty("mail.smtp.host", "smtp.gmail.com");

            // TLS si está disponible
            props.setProperty("mail.smtp.starttls.enable", "true");

            // Puerto de gmail para envio de correos
            props.setProperty("mail.smtp.port", "587");

            // Nombre del usuario
            props.setProperty("mail.smtp.user", "backup@isainmobiliaria.com.co");

            // Si requiere o no usuario y password para conectarse.
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress("backup@isainmobiliaria.com.co"));

            // A quien va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getMailDestino()));
            //A quien sera respondido el correo
            if (tipo == 2) {
                message.setReplyTo(new javax.mail.Address[]{
                    new javax.mail.internet.InternetAddress(getMailToReply())
                }
                );
            }
            // contenido del correo --------------
            //asunto
            message.setSubject(getAsunto());
            //mensaje
            message.setText(getMensaje(), "ISO-8859-1", "html");

            //envio del correo ----------------
            //clase transport y se le establece el protocolo de transporte de envio
            Transport t = session.getTransport("smtp");

            //conectarse a la cuenta desde la cual se enviara el mensaje
            t.connect("backup@isainmobiliaria.com.co", "isa3230450");
            t.sendMessage(message, message.getAllRecipients());
            //terminar el envio y cerrar la conexion
            t.close();

        } catch (MessagingException e) {
            //JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
            mbTodero.setMens("No hay conexion disponible");
            mbTodero.fatal();
        }
    }
    
    
    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
