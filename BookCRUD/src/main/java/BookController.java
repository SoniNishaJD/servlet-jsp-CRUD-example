
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDao;
import model.Book;

@WebServlet("/")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao Dao;

	@Override
	public void init() throws ServletException {
		String jdbcURL = "jdbc:mysql://localhost:3306/book_db";
		String jdbcUsername = "root";
		String jdbcPassword = "";

		Dao = new BookDao(jdbcURL, jdbcUsername, jdbcPassword);

		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				newBook(request, response);
				break;

			case "/insert":
				insertBook(request, response);
				break;

			case "/edit":
				editBook(request, response);
				break;

			case "/update":
				updateBook(request, response);
				break;

			case "/delete":

				deleteBook(request, response);

				break;

			default:
				listBook(request, response);
				break;
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Book> list = Dao.ListAllBooks();
		request.setAttribute("listBook", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);

	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));

		
		Dao.deleteBook(id);
		response.sendRedirect("list");

	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		Book book = new Book(id, title, author, price);
		Dao.updateBook(book);
		response.sendRedirect("list");
	}

	private void editBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = Dao.getBookById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		Book newbook = new Book(title, author, price);
		Dao.insertBook(newbook);
		response.sendRedirect("list");

	}

	private void newBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("entry.jsp");
		dispatcher.forward(request, response);

	}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response);
}
}
