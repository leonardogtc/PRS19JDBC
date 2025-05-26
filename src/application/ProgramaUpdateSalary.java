package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramaUpdateSalary {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = null;

        try {
            sql = "UPDATE public.seller SET \"BaseSalary\" = \"BaseSalary\" + ? WHERE \"DepartmentId\" = ?";
            conn = db.DB.getConnection();
            st = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS
            );

            st.setDouble(1, 200.0);
            st.setInt(2,2);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Done! Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows affected!");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
