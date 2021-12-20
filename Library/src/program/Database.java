package program;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import static program.LoginButtonListener.ID;

public class Database {
	private volatile static Database instance = null;
	private Connection connection = null;
	private Database() {
		initDatabase();
	}
	public static Database getInstance() {
		if(instance == null) {
			synchronized (Database.class) {
				if(instance == null)
					instance = new Database();
			}
		}
		return instance;
	}
	//NAME을 조회합니다.
	public boolean checkName(String name) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM member WHERE name='"+name+"'");
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	
	
	//NAME과 Password 확인
	public boolean checkNameAndPwd(String name, String password) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT COUNT(*) FROM member "
					+ "WHERE name='"+name+"' and pwd='"+password+"'");
			
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	public boolean checkAdmin(String name, String password) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT COUNT(*) FROM admin "
					+ "WHERE name='"+name+"' and pwd='"+password+"'");
			
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	
	public boolean checkBook(String book) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM book WHERE title='"+book+"'");
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	

	
	public void insertMemberData(String name, String password) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        statement.executeUpdate(
	        		"INSERT INTO member (name, pwd) "
	        		+ "values('"+name+"', '"+password+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	
	public void insertBookData(
			int id,
			int isbn,
			int number,
			String authors,
			String title,
			String publisher,
			String book_date,
			String status,
			int regist_date) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        statement.executeUpdate(
	        		"INSERT INTO book (id, isbn, number,"
	        		+ " authors, title, "
	        		+ "publisher, book_date, "
	        		+ "status, regist_date) "
	        		+ "values('"+id+"', '"+isbn+"' , '"+number+"',"
	        				+ "'"+authors+"' ,'"+title+"',"
	        				+ "'"+publisher+"', '"+book_date+"','"
	        				+ ""+status+"', '"+regist_date+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		
	}
	

	
	private void initDatabase() {
        // create a database connection
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            if( !checkExistTable("member") ) {
                statement.executeUpdate(
                        "CREATE TABLE member "
                        + "(id INTEGER NOT NULL, "
                        + "name STRING NOT NULL, "
                        + "pwd STRING NOT NULL, "
                        + "PRIMARY KEY(ID AUTOINCREMENT))");
            }    
            if( !checkExistTable("book") ) {
                statement.executeUpdate(
                        "CREATE TABLE book "
                                + "(id INTEGER NOT NULL, "
                                + "isbn INTEGER NOT NULL, "
                                + "number INTEGER NOT NULL, "
                                + "authors STRING NOT NULL, "
                                + "title STRING NOT NULL, "
                                + "publisher STRING NOT NULL, "
                                + "book_date STRING NOT NULL, "
                                + "status STRING NOT NULL,"
                                + "regist_date STRING NOT NULL) ");
            }
            if( !checkExistTable("admin") ) {
                statement.executeUpdate(
                        "CREATE TABLE admin "
                        + "(name STRING NOT NULL, "
                        + "pwd STRING NOT NULL) ");
            }    
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	boolean checkExistTable(String tableName) {
		boolean retValue = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE name='"+tableName+"'");
			if(rs.getInt(1) == 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	public void insertJTable(DefaultTableModel model) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM member");
			while(rs.next())
			{
				String []record = new String[3];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = rs.getString("name");
				record[2] = rs.getString("pwd");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertBookTable(DefaultTableModel model) {
		try {
			model.setRowCount(0);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Book");
			while(rs.next())
			{
				String []record = new String[9];
				record[0] = Integer.toString(rs.getInt("id"));
				record[1] = Integer.toString(rs.getInt("isbn"));
				record[2] = Integer.toString(rs.getInt("number"));
				record[3] = rs.getString("authors");
				record[4] = rs.getString("title");
				record[5] = rs.getString("publisher");
				record[6] = rs.getString("book_date");
				record[7] = rs.getString("status");
				record[8] = rs.getString("regist_date");
				
		
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void searchBook(DefaultTableModel model, String title) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE title like ?");
			statement.setString(1, "%"+title+"%");
			//Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				String []record = new String[5];
				record[0] = rs.getString("isbn");
				record[1] = rs.getString("authors");
				record[2] = rs.getString("title");
				record[3] = rs.getString("publisher");
				record[4] = rs.getString("status");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkTitle(String title) {
        boolean retValue = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM book WHERE title like ?");
            statement.setString(1, "%"+title+"%");
            ResultSet rs = statement.executeQuery();
            
            if(rs.getInt(1) >= 1)
                retValue = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return retValue;
    }
	

	public boolean checkStatus(String isbn) {
		boolean retValue = false;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM book "+ "WHERE status='대출가능' and isbn = '"+isbn+"'");
			ResultSet rs = statement.executeQuery();
			if(rs.getInt(1) >= 1)
				retValue = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	public void BorrowBook(String status, String isbn) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			statement.executeUpdate("UPDATE book SET status  = '"+status+"'" +"WHERE isbn='"+isbn+"'");
			statement.executeUpdate("UPDATE book SET id = '"+ID+"' WHERE isbn = '"+isbn+"'");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}
	
	public void returnBook(String isbn) {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			statement.executeUpdate("UPDATE book SET status  = '대출가능' WHERE isbn='"+isbn+"'");
			statement.executeUpdate("UPDATE book SET id = '0' WHERE isbn = '"+isbn+"'");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
	

	public void searchMyBook(DefaultTableModel model, int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE id like ?");
			statement.setString(1, "%"+id+"%");
			//Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				String []record = new String[5];
				record[0] = rs.getString("isbn");
				record[1] = rs.getString("authors");
				record[2] = rs.getString("title");
				record[3] = rs.getString("publisher");
				record[4] = rs.getString("status");
				
				model.addRow(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int whoseID(String name) {
		int a = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT id FROM member " + "WHERE name='"+name+"'");
			a = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
		
	}
	
	public void calendar() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String time1 = format1.format(time);
		System.out.println(time1);
	}
	
	public void deleteUser(String id) {
		try {
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			statement.executeUpdate("UPDATE book SET status = '대출가능' WHERE id = '"+id+"'");
			statement.executeUpdate("UPDATE book SET id = 0 WHERE id = '"+id+"'");
			statement.executeUpdate("DELETE from member where id = '"+id+"'");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}
	
	public void deleteBook(String isbn) {
			try {
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			statement.executeUpdate("DELETE from book where isbn = '"+isbn+"'");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	
	
}
