/**
 * 
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;

/**
 * @author Incognito
 *
 */
public class BorrowerService extends GeneralService {

	public String getBorrowerName(Integer cardNo) {
		
		try {
			return brwDao.readBorrowerByCardNo(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validateBorrower(Borrower borrower) {

		try {
			if (brwDao.validateBorrower(borrower)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addBookLoan(BookLoan bookLoan) {

		try {
			blDao.addBookLoan(bookLoan);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeBookLoan(BookLoan bookLoan) {

		try {
			blDao.updateAddCopies(bookLoan);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateBookLoan(BookLoan bookLoan) {

		try {
			blDao.updateBookLoan(bookLoan);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public List<Book> getBooksBorrowed(Integer cardNo, Integer branchNo) {

		try {
			return bDao.readAllBooksBorrowedByBranch(cardNo, branchNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
