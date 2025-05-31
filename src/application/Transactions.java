package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {
    public static void main(String[] args) {
        // Transações são usadas para garantir que um conjunto de operações seja executado de forma atômica.
        // Se uma operação falhar, todas as operações anteriores podem ser revertidas.

        Connection conn = null;
        Statement st = null;
        String sql = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false); // Desabilita o auto-commit para iniciar uma transação

            st = conn.createStatement();
            sql = "UPDATE public.seller SET \"BaseSalary\"=2090 WHERE \"DepartmentId\"=1;";
            int rows1 = st.executeUpdate(sql);

            // Simulando uma falha para testar a transação
//            int i = 1;
//            if (i == 1) {
//                throw new SQLException("Simulated failure");
//            }

            sql = "UPDATE public.seller SET \"BaseSalary\"=3090 WHERE \"DepartmentId\"=2;";
            int rows2 = st.executeUpdate(sql);

            conn.commit(); // Confirma a transação se tudo correr bem

            System.out.println("rows1: " + rows1);
            System.out.println("rows2: " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back due to: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Rollback failed: " + ex.getMessage());
            }
        } finally {
            DB.closeConnection();
            DB.closeConnection();
        }

    }
}
