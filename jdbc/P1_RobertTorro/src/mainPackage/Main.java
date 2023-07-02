package mainPackage;

import java.sql.SQLException;

import manager.Manager;

public class Main {

	public static void main(String[] args) throws SQLException {
		Manager manager = Manager.getInstance();
		manager.init();
	}

}
