package connection.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPoolingService {
	
	// static variable for saving our instance (singleton)
	private static volatile ConnectionPoolingService poolService;
	
	// our database connection pool
	private ComboPooledDataSource dataSource;
	
	private ConnectionPoolingService() throws Exception {
		try {
			// Create the pool instance and configure it
			dataSource = new ComboPooledDataSource();
			// every kind of driver is usable, for example JDBC 
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
			dataSource.setUser("testuser");
			dataSource.setPassword("testpassword");
			
			// set minimum and maximum connection pool size
			dataSource.setMinPoolSize(5);
			dataSource.setMaxPoolSize(20);
		} catch (PropertyVetoException ex) {
			ex.printStackTrace();
			throw new Exception("Error while creating database pool: " + ex.getMessage());
		}
	}
	
	// method to get the pooling instance
	public static ConnectionPoolingService getInstance() throws Exception {
		if (poolService == null) {
			poolService = new ConnectionPoolingService();
		}
		return poolService;
	}
	
	// method to get our connection
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
