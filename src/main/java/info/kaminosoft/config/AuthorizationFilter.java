package info.kaminosoft.config;

import java.io.IOException;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import info.kaminosoft.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.ws.rs.Priorities;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Provider
@Priority( Priorities.AUTHORIZATION )
public class AuthorizationFilter implements javax.ws.rs.container.ContainerRequestFilter{
	
	@Context
	private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
        final String httpMethod = requestContext.getMethod();

        Method method = resourceInfo.getResourceMethod();
        RolesAllowed rolesAnnotation = method.getAnnotation( RolesAllowed.class );
        Set<String> rolesSet = new HashSet<String>( Arrays.asList( rolesAnnotation.value() ) );
        if(rolesSet.contains("sin_restriccion")) {
        	
        	
        	                	
        }//end if
        else if(rolesSet.contains("restringido")) {

            if ("GET".equalsIgnoreCase(httpMethod)) {
               handleGetRequest(requestContext);
            }
    
            else if ("POST".equalsIgnoreCase(httpMethod)) {
                handlePostRequest(requestContext,headers);
            } 

            else{
                requestContext.abortWith( 
        			Response.status( Response.Status.UNAUTHORIZED ).entity("El recurso solicitado esta restringido").build());
            }

        }
        else{
            requestContext.abortWith( 
        			Response.status( Response.Status.UNAUTHORIZED ).entity("El recurso solicitado esta restringido").build());        	              
        }
    }

    private void handleGetRequest(ContainerRequestContext requestContext) {
       
        List<PathSegment> pathSegments = requestContext.getUriInfo().getPathSegments();

        // Buscar el token en los segmentos de la URL
        String jwt = null;
        for (PathSegment segment : pathSegments) {
            String path=segment.getPath();
            if (isToken(path)) { // Verificar si el segmento es un JWT
                jwt = path;
                break;
            }
        }

        // Si no se encuentra un token, abortar la solicitud
        if (jwt == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token no proporcionado.")
                    .build());
            return;
        }

        // Validar el token
        Claims claims=JwtUtil.validateToken(jwt);
        if(claims==null) {
            requestContext.abortWith( 
                    Response.status( Response.Status.UNAUTHORIZED ).entity("El token no es valido").build()
            );
        }  

    }
    private void handlePostRequest(ContainerRequestContext requestContext,MultivaluedMap<String, String> headers ) {
        final List<String> authProperty = headers.get("Authorization");
	    			
            if( authProperty == null || authProperty.isEmpty() )
            {
                requestContext.abortWith( 
                    Response.status( Response.Status.UNAUTHORIZED ).entity("Token no proporcionado.").build()
                );
                return;
            }

            String jwt = authProperty.get(0).substring(7); // Elimina "Bearer "
            Claims claims=JwtUtil.validateToken(jwt);
            if(claims==null) {
            	requestContext.abortWith( 
            			Response.status( Response.Status.UNAUTHORIZED ).entity("El token no es valido").build()
            	);
            }  
    }

    // Método para verificar si un segmento parece ser un JWT
    private boolean isToken(String segment) {
        // Verificar si el segmento tiene el formato de un JWT (3 partes separadas por puntos)
        return segment.matches("^[A-Za-z0-9\\-_]+\\.[A-Za-z0-9\\-_]+\\.[A-Za-z0-9\\-_]+$");
    }
    
}
