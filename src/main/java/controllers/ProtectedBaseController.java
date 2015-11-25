package controllers;

import com.google.inject.Singleton;
import com.sun.net.httpserver.Authenticator;
import filters.AuthenticationFilter;
import filters.AuthorizationFilter;
import ninja.FilterWith;

/**
 * @author kawasima
 */
@Singleton
@FilterWith({AuthenticationFilter.class, AuthorizationFilter.class})
public class ProtectedBaseController {
}
