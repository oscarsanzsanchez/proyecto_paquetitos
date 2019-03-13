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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import paquetitos.servidor.entidades.*;

/**
 * Ruta raíz expuesta en "urlPrincipal/clientes"
 */
@Path("clientes")
public class ClientesService {

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
     * @return devuelve una lista de clientes
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clientes> getClientes() {

        Session session = sesFactory.openSession();

        List<Clientes> listaClientes = null;

        try {
            listaClientes = session.createQuery("FROM Clientes").list();

        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return listaClientes;

    }

    /**
     * Método que recogerá la petición GET con un parámetro
     *
     * @param clienteId id del cliente cuya información queremos conseguir
     * @return objeto cliente con su información
     */
    @GET
    @Path("/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Clientes getCliente(@PathParam("clienteId") int clienteId) {

        Session session = sesFactory.openSession();

        Clientes cliente = null;

        try {
            cliente = session.get(Clientes.class, clienteId);
        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return cliente;
    }

}
