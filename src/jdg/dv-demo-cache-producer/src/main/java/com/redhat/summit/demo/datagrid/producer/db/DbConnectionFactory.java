package com.redhat.summit.demo.datagrid.producer.db;

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
public class DbConnectionFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionFactory.class);

	private static final String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";

	private static final String POSTGRESQL_HOST;

	private static final String POSTGRESQL_PORT;

	private static final String POSTGRESQL_DB_NAME;

	
	// Load the db driver class.
	static {
		POSTGRESQL_HOST = System.getProperty("dv.demo.postgresql.hostname");
		POSTGRESQL_PORT = System.getProperty("dv.demo.postgresql.port");
		POSTGRESQL_DB_NAME = System.getProperty("dv.demo.postgresql.dbname");
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
					"jdbc:postgresql://" + POSTGRESQL_HOST + ":" + POSTGRESQL_PORT + "/" + POSTGRESQL_DB_NAME, "postgres", "postgres");
		} catch (SQLException sqle) {
			String message = "Unable to create database connection.";
			LOGGER.error(message);
			throw new RuntimeException(message, sqle);
		}
		return connection;
	}

}
