package controllers;

import com.google.inject.Singleton;
import ninja.Result;
import ninja.Results;

import javax.annotation.security.PermitAll;

/**
 * @author kawasima
 */
@Singleton
public class ApplicationController extends ProtectedBaseController {
    @PermitAll
    public Result index() {
        return Results.html();
    }
}
