package paquetitos.servidor.servicios;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import paquetitos.servidor.entidades.*;

/**
 * Ruta raíz expuesta en "urlPrincipal/destinatarios"
 */
@Path("destinatarios")
public class DestinatariosService {

    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;
    private static SessionFactory sesFactory = HibernateUtil.getSessionFactory();

    public static SessionFactory getSesFactory() {
        return sesFactory;
    }

    /**
     * Método que recogerá la petición GET
     *
     * @return devuelve una lista de destinatarios
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destinatarios> getDestinatarios() {

        Session session = sesFactory.openSession();

        List<Destinatarios> listaDestinatarios = null;

        try {
            listaDestinatarios = session.createQuery("FROM Destinatarios").list();

        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return listaDestinatarios;

    }

    /**
     * Método que recogerá la petición GET con un parámetro
     *
     * @param destinatarioId id del destinatario cuya información queremos
     * conseguir
     * @return objeto destinatario con su información
     */
    @GET
    @Path("/{destinatarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destinatarios getCliente(@PathParam("destinatarioId") int destinatarioId) {

        Session session = sesFactory.openSession();

        Destinatarios destinatario = null;

        try {
            //listaClientes = session.createQuery("FROM Clientes").list();
            destinatario = session.get(Destinatarios.class, destinatarioId);
        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return destinatario;
    }

    /**
     * Método que recogerá la petición GET con un parámetro
     *
     * @param clienteId id del cliente usado para recoger los destinatarios
     * asociados a él
     * @return lista de destinatarios
     */
    @GET
    @Path("/porCliente/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destinatarios> getDestinatarioByCliente(@PathParam("clienteId") int clienteId) {

        Session session = sesFactory.openSession();

        List<Destinatarios> listaDestinos = null;

        try {

            Query<Destinatarios> query = session.createQuery("FROM Destinatarios WHERE clientes.idCliente = :id");
            query.setParameter("id", clienteId);
            listaDestinos = query.getResultList();

        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return listaDestinos;
    }
}
