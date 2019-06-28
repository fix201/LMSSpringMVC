package com.gcit.lms.service;

import java.sql.SQLException;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoan;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService extends GeneralService {

	public void add(Object obj) {

		try {
			if (obj instanceof Book) {
				Book book = (Book) obj;
				Integer bookId = bDao.addBookGetPK(book);
				// add authors
				for (Author a : book.getAuthors()) {
					Integer authorId = aDao.addAuthorGetPK(a);
					bDao.addBookAuthors(bookId, authorId);
				}
				// add genres
				for (Genre g : book.getGenres()) {
					Integer genreId = gDao.addGenreGetPK(g);
					bDao.addGenres(genreId, bookId);
				}
				// add publisher
				Publisher p = book.getPublisher();
				Integer publisherId = pDao.addPublisherGetPK(p);
				bDao.addPublisher(publisherId, bookId);
				System.out.println("\n[+] Book Added Successfully!");
			} else if (obj instanceof Author) {
				Author author = ((Author) obj);
				Integer authorId = aDao.addAuthorGetPK(author);
				for (Book b : author.getBooks()) {
					aDao.addBook(b.getBookId(), authorId);
				}
			} else if (obj instanceof Genre) {
				gDao.addGenre((Genre) obj);
				System.out.println("\n[+] Genre Added Successfully!");
			} else if (obj instanceof Publisher) {
				Integer publisherId = pDao.addPublisherGetPK((Publisher) obj);
				pDao.addPublisherAddress(publisherId, ((Publisher) obj).getPublisherAddress());
				pDao.addPublisherPhone(publisherId, ((Publisher) obj).getPublisherPhone());
				System.out.println("\n[+] Publisher Added Successfully!");
			} else if (obj instanceof LibraryBranch) {
				Integer branchId = lbDao.addLibraryBranchGetPK((LibraryBranch) obj);
				lbDao.addLibraryBranchAddress(branchId, ((LibraryBranch) obj).getBranchAddress());
				System.out.println("\n[+] Library Branch Added Successfully!");
			} else if (obj instanceof Borrower) {
				Integer borrowerId = brwDao.addBorrowerGetPK((Borrower) obj);
				brwDao.addBorrowerAddress(borrowerId, ((Borrower) obj).getAddress());
				brwDao.addBorrowerPhone(borrowerId, ((Borrower) obj).getAddress());
				System.out.println("\n[+] Borrower Added Successfully!");
			} else if (obj instanceof BookLoan) {
				blDao.addBookLoan((BookLoan) obj);
				System.out.println("\n[+] Book Loan Added Successfully!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all authors");
		}
	}

	public void update(Object obj) {

		try {
			if (obj instanceof Book) {
				Book book = (Book) obj;
				bDao.updateBook(book);
			} else if (obj instanceof Author) {
				Author author = (Author) obj;
				aDao.updateAuthor(author);
			} else if (obj instanceof Genre) {
				Genre genre = (Genre) obj;
				gDao.updateGenre(genre);
			} else if (obj instanceof Publisher) {
				Publisher publisher = (Publisher) obj;
				pDao.updatePublisher(publisher);
			} else if (obj instanceof LibraryBranch) {
				LibraryBranch libraryBranch = (LibraryBranch) obj;
				lbDao.updateLibraryBranch(libraryBranch);
			} else if (obj instanceof Borrower) {
				Borrower borrower = (Borrower) obj;
				brwDao.updateBorrower(borrower);
			} else if (obj instanceof BookLoan) {
				BookLoan bookLoan = (BookLoan) obj;
				blDao.updateBookLoan(bookLoan);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Object obj) {

		try {
			if (obj instanceof Book) {
				Book book = (Book) obj;
				bDao.deleteBook(book);
			} else if (obj instanceof Author) {
				Author author = (Author) obj;
				aDao.deleteAuthor(author);
			} else if (obj instanceof Genre) {
				Genre genre = (Genre) obj;
				gDao.deleteGenre(genre);
			} else if (obj instanceof Publisher) {
				Publisher publisher = (Publisher) obj;
				pDao.deletePublisher(publisher);
			} else if (obj instanceof LibraryBranch) {
				LibraryBranch libraryBranch = (LibraryBranch) obj;
				lbDao.deleteLibraryBranch(libraryBranch);
			} else if (obj instanceof Borrower) {
				Borrower borrower = (Borrower) obj;
				brwDao.deleteBorrower(borrower);
			} else if (obj instanceof BookLoan) {
				BookLoan bookLoan = (BookLoan) obj;
				blDao.deleteBookLoan(bookLoan);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong. Failed to get all authors");
		}

	}

	public Author getAuthor(Integer authorId) {

		Author author = null;
		try {
			author = aDao.readAuthorById(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return author;
	}

	public Genre getGenre(Integer genreId) {

		Genre genre = null;
		try {
			genre = gDao.readGenreById(genreId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return genre;
	}

	public Publisher getPublisher(Integer publisherId) {
		Publisher publisher = null;
		try {
			publisher = pDao.readPublisher(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return publisher;
	}

	public boolean overrideBookLoan(Integer bookId, Integer branchId, Integer cardNo) {
		BookLoan bookLoan = new BookLoan();
		bookLoan.setBookId(bookId);
		bookLoan.setBranchId(branchId);
		bookLoan.setCardNo(cardNo);
		try {
			blDao.override(bookLoan);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public BookLoan getBookLoan(Integer bookId, Integer branchId, Integer cardNo) {
		BookLoan bookLoan = new BookLoan();
		bookLoan.setBookId(bookId);
		bookLoan.setBranchId(branchId);
		bookLoan.setCardNo(cardNo);
		try {
			bookLoan = blDao.readBookLoan(bookLoan);

			return bookLoan;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
