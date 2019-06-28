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

import com.gcit.lms.entity.Borrower;

/**
 * @borrower Incognito
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower> implements ResultSetExtractor<List<Borrower>> {

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("insert into tbl_borrower (borrower_name) values (?)",
				new Object[] { borrower.getName() });
	}
	
	public Integer addBorrowerGetPK(Borrower borrower) throws ClassNotFoundException, SQLException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		mysqlTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into tbl_borrower (borrower_name) values (?)");
			ps.setString(1, borrower.getName());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}

	public void addBorrowerAddress(Integer borrowerId, String borrowerAddress)
			throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("update tbl_publisher set borrowerAddress = ? where borrowerId = ?",
				new Object[] { borrowerAddress, borrowerId });
	}

	public void addBorrowerPhone(Integer borrowerId, String phone) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("update tbl_publisher set borrowerPhone = ? where borrowerId = ?",
				new Object[] { phone, borrowerId });
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("update tbl_borrower set borrower_name = ? where borrower_id = ?",
				new Object[] { borrower.getName(), borrower.getCardNo() });
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		mysqlTemplate.update("delete from tbl_borrower where borrower_id = ?", new Object[] { borrower.getCardNo() });
	}

	public boolean validateBorrower(Borrower borrower) throws SQLException {
		ResultSet rs = (ResultSet) mysqlTemplate.query("select cardNo from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardNo() }, this);
		if (rs.next() && rs.getString("cardNo") != null) {
			return true;
		}
		return false;
	}

	public String readBorrowerName(Integer cardNo) throws ClassNotFoundException, SQLException {
		ResultSet rs = (ResultSet) mysqlTemplate.query("select * from tbl_borrower where cardNo = ?",
				new Object[] { cardNo }, this);
		if (rs.next() && rs.getString("cardNo") != null) {
			return rs.getString("name");
		}
		return null;
	}

	public List<Borrower> readAllBorrowers() throws  SQLException {
		return mysqlTemplate.query("select * from tbl_borrower", this);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
}
