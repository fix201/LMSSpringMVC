/**
 * 
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

/**
 * @author Incognito
 *
 */
public abstract class GeneralService {

	@Autowired
	BookDAO bDao;
	@Autowired
	AuthorDAO aDao;
	@Autowired
	GenreDAO gDao;
	@Autowired
	PublisherDAO pDao;
	@Autowired
	LibraryBranchDAO lbDao;
	@Autowired
	BorrowerDAO brwDao;
	@Autowired
	BookLoanDAO blDao;
	@Autowired
	BookCopiesDAO bcDao;
	
	public List<LibraryBranch> readAllBranches() {
		try {
			return lbDao.readAllLibraryBranches();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public List<Author> readAllAuthors() {
		
		try {
			return aDao.readAllAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public List<Book> readAllBooks() {
		
		try {
			return bDao.readAllBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public List<Book> readAllBooksForBranch(Integer branchId) {
		
		BookCopies books = new BookCopies();
		books.setBranchId(branchId);
		try {
			
			return bDao.readAllBooksByBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public LibraryBranch getLibraryBranch(Integer branchId) {
		try {
			return lbDao.readLibraryBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Genre> getAllGenres(){
		
		try {
			return gDao.readAllGenres();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		return null;
	}
	
	public List<Publisher> getAllPublishers(){
		try {
			return pDao.readAllPublishers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		} 
		return null;
	}
	
	public List<Borrower> getAllBorrowers(){
		try {
			return brwDao.readAllBorrowers();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Author> getAllAuthors(){
		try {
			return aDao.readAllAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

}
