package Auth;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("user");
        Integer secretNumber = Integer.valueOf(req.getParameter("secretNumber"));

        // Encrypt the password

         //If the login data is valid -> log the user in
        if(AuthManager.validUserLogin(user, secretNumber)) {
            loginUser(req.getSession(), user);
            resp.sendRedirect("/index.jsp");
            return;
        }

        // Redirect the user to "/"
        resp.sendRedirect("/index.jsp?error=UserOrSecretNumberInvalid");
    }

    public static void loginUser(HttpSession session, String user) {
        session.setAttribute("user", user);
    }


}
