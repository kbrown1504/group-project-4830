import datamodels.Account;
import datamodels.DataParser;
import java.util.ArrayList;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import datamodels.Account;
 
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public Login() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
         
        try {
        	Connection connection = null;
    		DBConnection.getDBConnection(getServletContext());
            connection = DBConnection.connection;
    		String sql = "SELECT * FROM Account WHERE Username = ? and Password = ?";
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setString(1, username);
    		statement.setString(2, password);

    		ResultSet result = statement.executeQuery();

    		Account user = null;

    		if (result.next()) {
    			result.previous();
    			ArrayList<Account> pUser = DataParser.parseAccount(result);
    			user = pUser.get(0);
    		}

    		connection.close();
            String destPage = "login.jsp";
             
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "home";
                response.sendRedirect("home");
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            }
             
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Log In");
		
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}
}