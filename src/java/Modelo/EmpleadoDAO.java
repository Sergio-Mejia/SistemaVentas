/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author daser
 */
public class EmpleadoDAO {

    Conexion con = new Conexion();
    Connection conex;
    PreparedStatement ps;
    ResultSet res;

    public Empleado Validar(String User, String DNI) {
        Empleado emp = new Empleado();
        String sql = "select * from empleado where User=? and Dni=?";
        try {
            conex = con.Conexion();
            ps = conex.prepareStatement(sql);
            ps.setString(1, User);
            ps.setString(2, DNI);
            res = ps.executeQuery();

            while (res.next()) {
                emp.setId(res.getInt("IdEmpleado"));
                emp.setUser(res.getString("User"));
                emp.setDNI(res.getString("Dni"));
                emp.setNombre(res.getString("Nombres"));
            }
        } catch (Exception e) {
        }
        return emp;
    }
}
