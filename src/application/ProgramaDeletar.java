package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramaDeletar {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        String sql = null;

        try {
            sql = "DELETE FROM public.department WHERE \"ID\" = ?";
            conn = DB.getConnection();
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1,2); // Change the ID to the one you want to delete

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Done! Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
