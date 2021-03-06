package farmapp.service.handlers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class InsertarProductos extends Handler {


	private Connection connection;
	private Statement statement;

	public InsertarProductos() {
		super();
	}

	@Override
	public String process(HttpServletRequest request)
			throws MissingRequiredParameter {
		
		String nombre = request.getParameter("producto"); //El email del admin de farmacia para comprobar su idtipo
		String tipo = request.getParameter("tipo");
		String descripcion = request.getParameter("descripcion");
		
System.out.println("producto: "+nombre+" descipción: "+descripcion+ " tipo: "+tipo);
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = null;

				String QUERY = "INSERT INTO productos (nombre, tipo, descripcion) VALUES ('"+nombre+"', '"+tipo+"', '"+descripcion+"');";
				//statement.execute("insert into productos (nombre, tipo, descripcion) values ('"+nombre+"', '"+tipo+"''"+descripcion+"');");
			System.out.println(QUERY);
				statement.execute(QUERY);


			
		} catch (SQLException e) {
			// e.printStackTrace();
			return "{\"status\":\"KO\", \"result\": \"Error en el acceso a la base de datos.\"}";
		}


		String result = "{\"status\":\"OK\", \"result\": \"Producto creado correctamente.\"}";
		return result;
	}



	

}
