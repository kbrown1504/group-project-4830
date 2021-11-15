

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.BookListing;

/**
 * Servlet implementation class Book
 */
@WebServlet("/book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file
		request.setAttribute("pageTitle", "View Book");
		
		String i = request.getParameter("id");
		int id = Integer.parseInt(i);
		BookListing test1 = new BookListing(0, -1, "Software Engineering", "Ian Sommerville", 9781292096131.0, 40.00, 0, 1, "test info");
		//TODO: Need to grab book by ID from DB
		
		request.setAttribute("title", test1.getTitle());
		request.setAttribute("author", test1.getAuthor());
		request.setAttribute("isbn", test1.getISBN());
		request.setAttribute("price", test1.getPrice());
		request.setAttribute("seller", "John Doe");
		request.setAttribute("info", test1.getInfo());
		
		RequestDispatcher view = request.getRequestDispatcher("book.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
