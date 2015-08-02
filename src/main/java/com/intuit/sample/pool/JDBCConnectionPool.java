package com.intuit.sample.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends GenericPool<Connection> {

	private String dsn, usr, pwd;

	public JDBCConnectionPool(int maxSize, int expiryTime, String driver, String dsn, String usr, String pwd) {
		super(maxSize, expiryTime);
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dsn = dsn;
		this.usr = usr;
		this.pwd = pwd;
	}

	@Override
	protected Connection create() {
		try {
			return (DriverManager.getConnection(dsn, usr, pwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	public void expire(Connection o) {
		try {
			((Connection) o).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validate(Connection o) {
		try {
			return (!((Connection) o).isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}
}