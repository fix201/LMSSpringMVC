/**
 * 
 */
package com.gcit.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.entity.Book;

/**
 * @book Incognito
 *
 */
public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>> {

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("insert into tbl_book (title) values (?)", new Object[] { book.getTitle() });
	}

	public Integer addBookGetPK(Book book) throws ClassNotFoundException, SQLException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		mysqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into tbl_book (title) values (?)");
			ps.setString(1, book.getTitle());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}

	public void addBookAuthors(Integer bookId, Integer authorId) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("insert into tbl_book_authors values (?, ?)", new Object[] { bookId, authorId });
	}

	public void addGenres(Integer genreId, Integer bookId) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("insert into tbl_book_genres values (?, ?)", new Object[] { genreId, bookId });
	}

	public void addPublisher(Integer pubId, Integer bookId) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("update tbl_book set pubId = ? where bookId = ?", new Object[] { pubId, bookId });
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("update tbl_book set title = ? where bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("delete from tbl_book where bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException {
		return mysqlTemplate.query("select * from tbl_book", this);
	}

	public List<Book> readAllBooksByBranch(Integer branchId) throws ClassNotFoundException, SQLException {
		return mysqlTemplate.query(
				"select * from tbl_book where tbl_book.bookId IN (select tbl_book_copies.bookId from tbl_book_copies where branchId = ?)",
				new Object[] { branchId }, this);
	}
	
	public List<Book> readAllBooksByAuthorId(Integer authorId) throws ClassNotFoundException, SQLException {
		return mysqlTemplate.query("select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId = ?)", 
				new Object[]{authorId}, this);
	}

	public List<Book> readAllBooksBorrowed(Integer cardNo, Integer branchId) throws ClassNotFoundException, SQLException {
		return mysqlTemplate.query(
				"select * from tbl_book where bookId IN (select bookId from tbl_book_loans where cardNo = ? and branchId = ? and dateIn is null)",
				new Object[] { cardNo, branchId }, this);
	}

	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			books.add(book);
		}
		return books;
	}

}
