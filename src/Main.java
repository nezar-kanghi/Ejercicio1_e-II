import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url ="jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String password = "ribera";



        try(Connection conn = DriverManager.getConnection(url, usuario, password)){

            System.out.println("Desea filtrar por pais? (Si/No)");
            String respuesta = sc.nextLine();

            if(respuesta.equalsIgnoreCase("si")) {

                System.out.println("¿Que pais del equipo desea filtrar?");
                String pais = sc.nextLine();

                String sql = "SELECT CICLISTA.ID_CICLISTA, CICLISTA.NOMBRE AS NOMBRE_CICLISTA, CICLISTA.NACIONALIDAD, CICLISTA.EDAD, EQUIPO.NOMBRE AS NOMBRE_EQUIPO " +
                        "FROM CICLISTA JOIN EQUIPO ON CICLISTA.ID_EQUIPO = EQUIPO.ID_EQUIPO " +
                        "WHERE NACIONALIDAD = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, pais);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("ID_CICLISTA");
                    String Cnombre = rs.getString("NOMBRE_CICLISTA");
                    String nacionalidad = rs.getString("NACIONALIDAD");
                    int edad = rs.getInt("EDAD");
                    String Enombre = rs.getString("NOMBRE_EQUIPO");


                    System.out.println("ID CICLISTA: " + id);
                    System.out.println("Nombre del ciclista: " + Cnombre);
                    System.out.println("Nacionalidad: " + nacionalidad);
                    System.out.println("Edad: " + edad);
                    System.out.println("Nombre del equipo: " + Enombre);
                    System.out.print("");
                    System.out.println("--------------------------------------");
                }
            }else{
                String sql = "SELECT CICLISTA.ID_CICLISTA, CICLISTA.NOMBRE AS NOMBRE_CICLISTA, CICLISTA.NACIONALIDAD, CICLISTA.EDAD, EQUIPO.NOMBRE AS NOMBRE_EQUIPO " +
                        "FROM CICLISTA JOIN EQUIPO ON CICLISTA.ID_EQUIPO = EQUIPO.ID_EQUIPO";

                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("ID_CICLISTA");
                    String Cnombre = rs.getString("NOMBRE_CICLISTA");
                    String nacionalidad = rs.getString("NACIONALIDAD");
                    int edad = rs.getInt("EDAD");
                    String Enombre = rs.getString("NOMBRE_EQUIPO");


                    System.out.println("ID CICLISTA: " + id);
                    System.out.println("Nombre del ciclista: " + Cnombre);
                    System.out.println("Nacionalidad: " + nacionalidad);
                    System.out.println("Edad: " + edad);
                    System.out.println("Nombre del equipo: " + Enombre);
                    System.out.print("");
                    System.out.println("--------------------------------------");
                }
            }

        }catch(SQLException e ){
            System.out.println("Error al conectar" + e.getMessage());
        }
    }
}