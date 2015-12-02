package helpers;

import com.google.common.collect.Maps;
import models.UserPrincipal;
import ninja.Context;

import java.util.Map;

/**
 * @author kawasima
 */
public class VariablesHelper {
    public static Map<String, Object> create(Context context) {
        Map<String, Object> variables = Maps.newHashMap();
        UserPrincipal principal = (UserPrincipal) context.getAttribute("principal");
        variables.put("principal", principal);
        return variables;
    }
}
