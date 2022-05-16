package dat.startcode.model.services;

import dat.startcode.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
    public static boolean isRoleAllowed(String role, HttpServletRequest request)
    {
        // extract user object from session scope and check role
        return true;
    }
}
