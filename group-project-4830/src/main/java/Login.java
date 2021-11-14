
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.DataParser;
import datamodels.Account;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "Account");
		
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//MySQL query for user Email
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	    	DBConnection.getDBConnection(getServletContext());
	        connection = DBConnection.connection;

	        String selectSQL = "SELECT * FROM Account WHERE Username LIKE ?";
	        String theUsername = username + "%";
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1,  theUsername);
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        //Does username exist?
	        try {
	        	Account user = DataParser.parseAccount(rs).get(0);
		        //Check password
		        String reply = user.login(password);
		        request.setAttribute(reply, "Login reply");
	        }
	        catch (Exception e)
	        {
	        	request.setAttribute("Invalid Username", "Login reply");
	        }
	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } 
	    catch (SQLException se) {
	         se.printStackTrace();
	    } 
	    catch (Exception e) {
	         e.printStackTrace();
	    } 
	    finally {
	         try {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } catch (SQLException se2) {
	         }
	         try {
	            if (connection != null)
	               connection.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	}

}
