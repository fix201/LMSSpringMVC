package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;

public class LibrarianService extends GeneralService {

	public boolean updateBranch(LibraryBranch libraryBranch) {

		try {
			lbDao.updateLibraryBranch(libraryBranch);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBranchCopies(BookCopies bookCopies) {

		try {
			bcDao.updateBookCopies(bookCopies);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addBookCopy(Book book, BookCopies bookCopies) {

		try {
			bDao.addBook(book);
			bcDao.updateBookCopies(bookCopies);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	public List<Book> getBookCopiesForBranch(Integer branchId) {
		
		try {
			List<Book> bc = bDao.readAllBooksByBranch(branchId);
			return bc;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get books from branch");
		} 
		return null;
	}

	public BookCopies getNoOfCopiesForBranch(Integer bookId, Integer branchId) {
		
		try {
			List<BookCopies> bc = bcDao.readNoOfCopiesForBranch(bookId, branchId);
			return bc.get(0);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
