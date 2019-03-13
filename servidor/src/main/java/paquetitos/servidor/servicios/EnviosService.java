package paquetitos.servidor.servicios;

import java.util.List;

import javax.persistence.PersistenceException;
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
 * Ruta raíz expuesta en "urlPrincipal/envios"
 */
@Path("envios")
public class EnviosService {

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
     * @return devuelve una lista de envios
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envios> getEnvios() {

        Session session = sesFactory.openSession();

        List<Envios> listaEnvios = null;

        try {
            listaEnvios = session.createQuery("FROM Envios").list();
            

        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return listaEnvios;

    }

    /**
     * Método que recogerá la petición GET con un parámetro
     *
     * @param envioId id del envío cuya información queremos conseguir
     * @return objeto envio con su información
     */
    @GET
    @Path("/{envioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Envios getEnvio(@PathParam("envioId") int envioId) {

        Session session = sesFactory.openSession();

        Envios envio = null;

        try {
            envio = session.get(Envios.class, envioId);
        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return envio;
    }

    /**
     * Método que recogerá la petición POST
     *
     * @param envio objeto envio que queremos insertar en la base de datos
     * @return respuesta del servidor
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response putEmpleado(Envios envio) {

        Session session = sesFactory.openSession();
        Response res;
        try {
            Transaction tx = session.beginTransaction();

            envio.setClientes(new Clientes().getClienteById(envio.getIdCliente()));
            envio.setEstadosenvio(new Estadosenvio().getEstado(envio.getIdEstado()));

            session.saveOrUpdate(envio); // Crear un nuevo registro o actualizar el existente
            tx.commit();
            res = Response.created(uriInfo.getAbsolutePath()).build();
        } catch (PersistenceException e) {
            e.printStackTrace();
            res = Response.noContent().build();
        } finally {
            session.close();
        }
        
        return res;
    }

}
