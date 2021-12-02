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
 
@WebServlet("/signUp")
public class SignUp extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public SignUp() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        //Password syntax check
        for (char c : password.toCharArray())
        if (!Character.isLetterOrDigit(c) || password.length() == 0)
        {
        	request.setAttribute("message", "Password contains an invalid character, must be letter or number");
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("signUp.jsp");
            dispatcher.forward(request, response);
            return; //Early exit
        }
        
        try {
        	Connection connection = null;
    		DBConnection.getDBConnection(getServletContext());
            connection = DBConnection.connection;
            //Check if username is in use
    		String sql = "SELECT * FROM Account WHERE Username = ?";
    		PreparedStatement statement = connection.prepareStatement(sql);
    		statement.setString(1, username);

    		ResultSet result = statement.executeQuery();

    		Account user = null;

    		if (result.next()) {
    			result.previous();
    			ArrayList<Account> pUser = DataParser.parseAccount(result);
    			user = pUser.get(0);
    		}

            String destPage = "signUp.jsp";
             
            //User already exists
            if (user != null) {
            	String message = "Username already in use";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
                
            } 
            //User does not exist
            else {
            	//Create in db and update for proper id reference
            	user = new Account(0,username,email,password);
            	user.create(connection).execute();
            	PreparedStatement temp = connection.prepareStatement(sql);
            	temp.setString(1, username);
            	ResultSet rs = temp.executeQuery();
            	user = DataParser.parseAccount(rs).get(0);
            	HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "home";
                
                response.sendRedirect(destPage);
            }
            
            connection.close();
             
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Sign Up");
		
		RequestDispatcher view = request.getRequestDispatcher("signUp.jsp");
		view.forward(request, response);
	}
}