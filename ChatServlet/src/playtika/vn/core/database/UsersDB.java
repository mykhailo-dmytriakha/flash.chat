package playtika.vn.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.log4j.Logger;

import playtika.vn.config.GeneralSqlQuery;

public class UsersDB {
    private final Logger logger = Logger.getLogger(this.getClass());
    private Connection connection;
    private ResultSet result;

    public UsersDB() {
	createConnection();
    }

    private void createConnection() {
	logger.debug(String.format("UsersDB : createConnection()"));
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	}

	try {
	    // вынести в конфигурацию
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "Gfhjkm1");
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	if (connection == null) {
	    logger.warn(String.format("No connection to DataBase"));
	}

	// getRows(); // test for select from DB
    }

    public String getRows() {
	String rows = null;
	Statement statement = null;

	try {
	    statement = connection.createStatement();
	    result = statement.executeQuery(GeneralSqlQuery.SQLSELECT);
	    while (result.next()) {
		logger.debug(String.format(result.getRow() + ". " + result.getString("UserName") + "\t" + result.getString("Password") + "\t"
			+ result.getString("Time")));
	    }
	    statement.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	    logger.warn(String.format("UsersDB : getRows() : SQLException"));
	}

	return rows;
    }

    public void addRow(String login, String pass) {
	PreparedStatement statement = null;
	logger.debug(String.format("UsersDB : addRow() : login = %s, pass = %s", login, pass));
	try {
	    statement = connection.prepareStatement(GeneralSqlQuery.SQLINSERT);
	    statement.setString(1, login);
	    statement.setString(2, pass);
	    statement.setString(3, Calendar.getInstance().getTime().toString());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	    logger.warn(String.format("UsersDB : addRow() : SQLException"));
	}

    }
}
