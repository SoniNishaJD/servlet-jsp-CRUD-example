package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.StuckThreadDetectionValve;

import model.Book;

public class BookDao {

	private String jdbcURl;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public BookDao(String jdbcURl, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURl = jdbcURl;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public void connect() throws SQLException {

		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {

				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURl, jdbcUsername, jdbcPassword);
		}
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && jdbcConnection.isClosed()) {

			jdbcConnection.close();
		}

	}

	public List<Book> ListAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<>();
		String sql = "Select * from book";
		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");

			Book book = new Book(id, title, author, price);
			listBook.add(book);
		}

		resultSet.close();
		statement.close();

		disconnect();
		return listBook;
	}

	public Book getBookById(int id) throws SQLException {
		Book book = new Book();
		String sql = "Select * from book where id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
       
		if (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");

			 book = new Book(id, title, author, price);
			
		}
		disconnect();
		return book;

	}

	public boolean insertBook(Book b1) throws SQLException {

		String sql = "Insert into book (title,author,price) values (?,?,?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, b1.getTitle());
		statement.setString(2, b1.getAuthor());
		statement.setFloat(3, b1.getPrice());

		boolean b = statement.executeUpdate() > 0;
		statement.close();

		disconnect();
		return b;

	}

	public boolean updateBook(Book b1) throws SQLException {
		String sql = "Update book SET title=?,author=?,price=? where id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, b1.getTitle());
		statement.setString(2, b1.getAuthor());
		statement.setFloat(3, b1.getPrice());
		statement.setInt(4, b1.getId());

		boolean b = statement.executeUpdate() > 0;
		statement.close();

		disconnect();
		return b;
	}

	public boolean deleteBook(int id) throws SQLException {
		String sql = "Delete from book where id=?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		boolean b = statement.executeUpdate() > 0;
		statement.close();

		disconnect();
		return b;
	}

}
