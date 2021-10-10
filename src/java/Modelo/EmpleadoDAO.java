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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daser
 */
public class EmpleadoDAO {

    Conexion con = new Conexion();
    Connection conex;
    PreparedStatement ps;
    ResultSet res;
    int r;

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
    
    public List Listar(){
        String SQL = "SELECT * FROM empleado";
        List<Empleado> lista = new ArrayList<>();
         
        try{
            conex = con.Conexion();
            ps = conex.prepareStatement(SQL);
            res = ps.executeQuery();
            
            while(res.next()){
                Empleado emp = new Empleado();
                emp.setId(res.getInt(1));
                emp.setDNI(res.getString(2));
                emp.setNombre(res.getString(3));
                emp.setTel(res.getString(4));
                emp.setEstado(res.getString(5));
                emp.setUser(res.getString(6));
                
                lista.add(emp);
            }
        }catch(SQLException e){
            System.out.println("Error Listar" + e.getMessage());
        }
        return lista;
    }
    
    
    public int Agregar(Empleado e){
        String SQL = "INSERT INTO empleado(Dni, Nombres, Telefono, Estado, User) VALUES(?, ?, ?, ?, ?)";
        try {
            conex = con.Conexion();
            ps = conex.prepareStatement(SQL);
            ps.setString(1,e.getDNI());
            ps.setString(2,e.getNombre());
            ps.setString(3,e.getTel());
            ps.setString(4,e.getEstado());
            ps.setString(5,e.getUser());
            ps.executeUpdate();
        } catch (Exception ex) {
        }
        return r;
    }
    
    public Empleado ListarId(int id){
        Empleado em = new Empleado();
        
        String SQL = "SELECT * FROM empleado WHERE IdEmpleado="+id;
        
        try {
            conex = con.Conexion();
            ps = conex.prepareStatement(SQL);
            res = ps.executeQuery();
            
            while(res.next()){
                em.setDNI(res.getString(2));
                em.setNombre(res.getString(3));
                em.setTel(res.getString(4));
                em.setEstado(res.getString(5));
                em.setUser(res.getString(6));
            }
        } catch (Exception e) {
        }
        return em;
    }
    
    
    public int Editar(Empleado e){
        String SQL = "UPDATE empleado SET Dni=?, Nombres=?, Telefono=?, Estado=?, User=? WHERE IdEmpleado=?";
        try {
            conex = con.Conexion();
            ps = conex.prepareStatement(SQL);
            ps.setString(1,e.getDNI());
            ps.setString(2,e.getNombre());
            ps.setString(3,e.getTel());
            ps.setString(4,e.getEstado());
            ps.setString(5,e.getUser());
            ps.setInt(6, e.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
        }
        return r;
    }
    
    public void Eliminar(int id){
        String SQL = "DELETE FROM empleado WHERE IdEmpleado="+id;
        
        try {
            conex = con.Conexion();
            ps = conex.prepareStatement(SQL);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
