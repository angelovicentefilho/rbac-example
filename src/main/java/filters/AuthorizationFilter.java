package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;

import java.lang.reflect.Method;

/**
 * @author kawasima
 */
public class AuthorizationFilter implements Filter{
    public Result filter(FilterChain filterChain, Context context) {
        Method method = context.getRoute().getControllerMethod();
        return filterChain.next(context);
    }
}
