package paquetitos.servidor.servicios;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {

		response.getHeaders().add("Access-Control-Allow-Origin", "*");
		response.getHeaders().add("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,Accept,Authorization,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
		response.getHeaders().add("Access-Control-Allow-Credentials", "true");
		response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");

		//Access-Control-Allow-Headers: "Content-Type,X-Requested-With,Accept,Authorization,Origin,Access-Control-Request-Method,Access-Control-Request-Headers"
	}
}
