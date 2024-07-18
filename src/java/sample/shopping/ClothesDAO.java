/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author KuroZabulus
 */
public class ClothesDAO {
    
    private static final String GET = "SELECT * FROM tblProducts WHERE status = 1";
    private static final String GET_ONE = "SELECT * FROM tblProducts WHERE status = 1 AND productID = ?";

    public List<ClothesDTO> getItems() throws SQLException {
        List<ClothesDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(GET);
                rs=ptm.executeQuery();
                while(rs.next()){
                    String ID= rs.getString("productID");
                    String name= rs.getString("itemName");
                    int quantity= rs.getInt("quantity");
                    double price= rs.getDouble("price");
                    list.add(new ClothesDTO(ID, name, quantity, price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return list;
    }

    public int getItemQuantity(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int quantity=0;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(GET_ONE);
                ptm.setString(1, productID);
                rs=ptm.executeQuery();
                while(rs.next()){
                    quantity= rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return quantity;
    }
    
}
