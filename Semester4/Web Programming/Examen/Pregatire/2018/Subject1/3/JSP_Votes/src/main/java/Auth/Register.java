package Auth;
import DB.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String mail = req.getParameter("mail");
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//        String repeatPassword = req.getParameter("repeatPassword");
//
//        // Password are not equal
//        if(!password.equals(repeatPassword)) {
//            resp.sendRedirect("/index.jsp?error=incorectRepeteadPassword");
//            return;
//        }
//
//
//        // Mail is not unique
//        if(!AuthManager.isMailUnique(mail)) {
//            resp.sendRedirect("/index.jsp?error=mailAlreadyExists");
//            return;
//        }
//
//        // Encrypt the password
//        String encryptedPassword = AuthManager.encrypt(password);
//
//        // Save the password to database
//        AuthManager.addUserToDataBase(mail, name, encryptedPassword);
//
//        // Login the user
//        Login.loginUser(req.getSession(), mail);

        // Redirect the user to /
        resp.sendRedirect("/index.jsp");
    }


}
