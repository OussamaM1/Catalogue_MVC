package metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
		private static Connection connection;
		//le bloc static s'exécute au moment du chargement de la classe en mémoire !
		static {
			 try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cat_Prod","root","");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static Connection getConnection() {
			return connection;
		}
		
}
