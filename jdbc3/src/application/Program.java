package application;

import db.DB;
import db.DbException;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

//            st = conn.prepareStatement(
//                    "INSERT INTO seller "
//                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
//                    + "VALUES "
//                    + "(?, ?, ?, ?, ?);",
//                    Statement.RETURN_GENERATED_KEYS);
//
//            st.setString(1,"Yuri");
//            st.setString(2,"yuri@teste.com");
//            st.setDate(3, new java.sql.Date(sdf.parse("20/12/1995").getTime()));
//            st.setDouble(4, 3000.0);
//            st.setInt(5, 4);

            st = conn.prepareStatement("INSERT INTO department (Name) " +
                    "VALUES ('D1'),('D2')", Statement.RETURN_GENERATED_KEYS);


            int rowsAffeected = st.executeUpdate(); //Atribuindo a variavel para contabilizar

            if (rowsAffeected > 0) {
               ResultSet rs = st.getGeneratedKeys();
               while (rs.next()) {
                   int id = rs.getInt(1); //Valor da primeira coluna
                   System.out.println("Done! Id = " + id);
               }

            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection(); // Sempre fechar a conexão por último.
        }



    }
}
