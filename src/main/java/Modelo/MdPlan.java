package Modelo;
import Clases.*;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import com.sun.source.tree.CatchTree;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MdPlan {

    public MdPlan() {
    }
    
    

    Data variableData = new Data();
    public boolean crearPlan(String Codigo, String Nombre, Integer Precio){
    try ( Connection conn = DriverManager.getConnection(variableData.getUrl(), variableData.getUser(), variableData.getPassword())) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO plan(codigo, nombre, precio) VALUES(?,?,?)",  Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Codigo);
            ps.setString(2, Nombre);
            ps.setInt(3, Precio);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                 JOptionPane.showMessageDialog(null, "El plan fue agregado en la base de datos");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El plan no fue agregado en la base de datos por else");
                return false;
            }
            
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El plan no fue agregado en la base de datos por catch");
            System.out.println("Error " + e);
            return false;
           
        }
    }

    public Plan consultarPlan(String Codigo){
        Plan p = null;
        try {
            Connection conn = DriverManager.getConnection(variableData.getUrl(), variableData.getUser(), variableData.getPassword());
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `plan` WHERE codigo = 'AK49'", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Codigo);
            ResultSet li = ps.executeQuery();
            while(li.next()){
                String Code = li.getString(3);
                String Name = li.getString(4);
                int Price = li.getInt(4);
                p = new Plan(Code, Name, Price);
            }
            return p;
        } catch (Exception e) {
        }
        return p;
    }
    
        
    
   
}