package controllers;

import com.google.inject.Singleton;
import helpers.VariablesHelper;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import javax.annotation.security.PermitAll;

/**
 * @author kawasima
 */
@Singleton
public class ApplicationController extends ProtectedBaseController {
    @PermitAll
    public Result index(Context context) {
        return Results.html().render(VariablesHelper.create(context));
    }
}
