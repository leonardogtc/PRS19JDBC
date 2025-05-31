package application;

import db.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Traansactions {
    public static void main(String[] args) {
        // Transações são usadas para garantir que um conjunto de operações seja executado de forma atômica.
        // Se uma operação falhar, todas as operações anteriores podem ser revertidas.

        Connection conn = null;
        Statement st = null;
        String sql = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();

            sql = "UPDATE public.seller SET \"BaseSalary\"=2090 WHERE \"DepartmentId\"=1);";
            int rows1 = st.executeUpdate(sql);

            // Simulando uma falha para testar a transação
            int i = 1;
            if (i == 1) {
                throw new SQLException("Simulated failure");
            }

            sql = "UPDATE public.seller SET \"BaseSalary\"=3090 WHERE \"DepartmentId\"=2);";
            int rows2 = st.executeUpdate(sql);

            System.out.println("rows1: " + rows1);
            System.out.println("rows2: " + rows2);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
            DB.closeConnection();
        }

    }
}
