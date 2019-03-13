package paquetitos.servidor.entidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Estadosenvio generated by hbm2java
 */
@Entity
@Table(name = "estadosenvio", catalog = "paquetitos")
public class Estadosenvio implements java.io.Serializable {

    private String idEstadoEnvio;
    private String descripcionEstado;
    private Set<Envios> envioses = new HashSet<Envios>(0);

    public Estadosenvio() {
    }

    public Estadosenvio(String idEstadoEnvio, String descripcionEstado) {
        this.idEstadoEnvio = idEstadoEnvio;
        this.descripcionEstado = descripcionEstado;
    }

    public Estadosenvio(String idEstadoEnvio, String descripcionEstado, Set<Envios> envioses) {
        this.idEstadoEnvio = idEstadoEnvio;
        this.descripcionEstado = descripcionEstado;
        this.envioses = envioses;
    }

    @Id

    @Column(name = "IdEstadoEnvio", unique = true, nullable = false, length = 10)
    public String getIdEstadoEnvio() {
        return this.idEstadoEnvio;
    }

    public void setIdEstadoEnvio(String idEstadoEnvio) {
        this.idEstadoEnvio = idEstadoEnvio;
    }

    @Column(name = "DescripcionEstado", nullable = false, length = 60)
    public String getDescripcionEstado() {
        return this.descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estadosenvio")
    public Set<Envios> getEnvioses() {
        return this.envioses;
    }

    public void setEnvioses(Set<Envios> envioses) {
        this.envioses = envioses;
    }

    /*===============================================================*/
    public Estadosenvio getEstado(String id) {
        SessionFactory sesFactory = HibernateUtil.getSessionFactory();
        Session session = sesFactory.openSession();
        List<Estadosenvio> listaEstados = null;
        Estadosenvio estado = null;

        try {
            Query query = session.createQuery("FROM Estadosenvio where idEstadoEnvio = :id");
            query.setParameter("id", id);
            listaEstados = query.list();
            estado = listaEstados.get(0);

        } catch (HibernateException e) {

        } finally {
            session.close();
        }

        return estado;
    }
}
