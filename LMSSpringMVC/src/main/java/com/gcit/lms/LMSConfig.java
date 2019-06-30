/**
 * 
 */
package com.gcit.lms;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

/**
 * @author Incognito
 *
 */
@Configuration
public class LMSConfig {

	public String driverName = "com.mysql.cj.jdbc.Driver";
	public String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
	public String username = "root";
	public String password = "password";
	
	
	@Bean
//	@Scope(value="prototype")
	public BasicDataSource mysqlDataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	@Qualifier(value="mysqlTemplate")
	public JdbcTemplate mysqlTemplate(){
		return new JdbcTemplate(mysqlDataSource());
	}
	
	@Bean
//	@Scope(value="prototype")
	public BasicDataSource oracleDataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	@Qualifier(value="oracleTemplate")
	public JdbcTemplate oracleTemplate(){
		return new JdbcTemplate(oracleDataSource());
	}
	
	@Bean
	public BookDAO bDao(){
		return new BookDAO();
	}
	
	@Bean
	public AuthorDAO aDao(){
		return new AuthorDAO();
	}
	
	@Bean
	public GenreDAO gDao(){
		return new GenreDAO();
	}
	
	@Bean
	public PublisherDAO pDao(){
		return new PublisherDAO();
	}
	
	@Bean
	public LibraryBranchDAO lbDao(){
		return new LibraryBranchDAO();
	}
	
	@Bean
	public BorrowerDAO brwDao(){
		return new BorrowerDAO();
	}
	
	@Bean
	public BookLoanDAO blDao(){
		return new BookLoanDAO();
	}
	
	@Bean
	public BookCopiesDAO bcDao(){
		return new BookCopiesDAO();
	}
	
	@Bean
	public AdminService adminService(){
		return new AdminService();
	}
	
	@Bean
	public LibrarianService librarianService(){
		return new LibrarianService();
	}
	
	@Bean
	public BorrowerService borrowerService(){
		return new BorrowerService();
	}
	
	@Bean
	DataSourceTransactionManager txManager(){
		return new DataSourceTransactionManager(mysqlDataSource());
	}
}
