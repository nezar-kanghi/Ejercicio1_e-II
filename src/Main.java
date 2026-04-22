import java.sql.*;
import java.util.Scanner;

public class CiclistasPais {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url ="jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "RIBERA";
        String password = "ribera";



        try(Connection conn = DriverManager.getConnection(url, usuario, password)){

            System.out.println("Desea filtrar por pais? (Si/No)"); //preguntamos si desea filtrar por pais
            String respuesta = sc.nextLine();

            if(respuesta.equalsIgnoreCase("si")) { //ignoramos mayusculas
 
                System.out.println("¿Que pais del equipo desea filtrar?");
                String pais = sc.nextLine();

                String sql = "SELECT CICLISTA.ID_CICLISTA, CICLISTA.NOMBRE AS NOMBRE_CICLISTA, CICLISTA.NACIONALIDAD, CICLISTA.EDAD, EQUIPO.NOMBRE AS NOMBRE_EQUIPO " +
                        "FROM CICLISTA JOIN EQUIPO ON CICLISTA.ID_EQUIPO = EQUIPO.ID_EQUIPO " +
                        "WHERE NACIONALIDAD = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, pais); //añadimos el pais

                ResultSet rs = stmt.executeQuery();

                //mostramos el ciclista por pais
                
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

                //lo mismo sin el where = pais
                String sql = "SELECT CICLISTA.ID_CICLISTA, CICLISTA.NOMBRE AS NOMBRE_CICLISTA, CICLISTA.NACIONALIDAD, CICLISTA.EDAD, EQUIPO.NOMBRE AS NOMBRE_EQUIPO " +
                        "FROM CICLISTA JOIN EQUIPO ON CICLISTA.ID_EQUIPO = EQUIPO.ID_EQUIPO";

                PreparedStatement stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery();


                //mostramos los ciclistas
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
