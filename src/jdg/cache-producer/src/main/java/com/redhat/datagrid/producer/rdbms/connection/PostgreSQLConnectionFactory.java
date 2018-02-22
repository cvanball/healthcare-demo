package com.redhat.datagrid.producer.rdbms.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>ConnectionFactory</code> for database connections.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class PostgreSQLConnectionFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostgreSQLConnectionFactory.class);

	private static final String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";

	private static final String POSTGRESQL_HOST;

	private static final String POSTGRESQL_PORT;

	private static final String POSTGRESQL_DB_NAME;

	private static final String POSTGRESQL_USERNAME;

	private static final String POSTGRESQL_PASSWORD;
	
	// Load the db driver class.
	static {
		POSTGRESQL_HOST = System.getProperty("postgresql.hostname");
		POSTGRESQL_PORT = System.getProperty("postgresql.port");
		POSTGRESQL_DB_NAME = System.getProperty("postgresql.dbname");
		POSTGRESQL_USERNAME = System.getProperty("postgresql.username");
		POSTGRESQL_PASSWORD = System.getProperty("postgresql.password");

		try {
			Class.forName(DB_DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			String message = "Can not find PostgreSQL database driver.";
			LOGGER.error(message, cnfe);
			throw new RuntimeException(message, cnfe);
		}
	}

	public Connection getConnection() {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://" + POSTGRESQL_HOST + ":" + POSTGRESQL_PORT + "/" + POSTGRESQL_DB_NAME, POSTGRESQL_USERNAME, POSTGRESQL_PASSWORD);
		} catch (SQLException sqle) {
			String message = "Unable to create database connection.";
			LOGGER.error(message);
			throw new RuntimeException(message, sqle);
		}
		return connection;
	}

}
