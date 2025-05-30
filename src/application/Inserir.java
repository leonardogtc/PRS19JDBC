package application;

import db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Inserir {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        String sql = null;

        try {
            conn = DB.getConnection();
            /*
            st = conn.prepareStatement(
                    "INSERT INTO public.seller "
                    + "(\"Name\", \"Email\", \"BirthDate\", \"BaseSalary\", \"DepartmentId\") "
                    +"VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, "Carlos Silva");
            st.setString(2, "carlos@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("25/05/1971").getTime()));
            st.setDouble(4, 13000.0);
            st.setInt(5, 4);
            */

            sql = "INSERT INTO public.department (\"Name\") VALUES ('D1'),('D2');";
            st = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS
            );

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id: " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.DB.closeStatement(st);
            db.DB.closeConnection();
        }
    }
}
