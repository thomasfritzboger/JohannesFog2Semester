package dat.startcode.model.services;

import javax.servlet.http.HttpServletRequest;

// bruges ikke
public class Authentication
{
    public static boolean isRoleAllowed(String role, HttpServletRequest request)
    {
        // extract user object from session scope and check role
        return true;
    }
}
