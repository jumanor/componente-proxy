package info.kaminosoft.config;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;

@Provider
@Priority( Priorities.AUTHENTICATION )
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
       //
    }
    
}
