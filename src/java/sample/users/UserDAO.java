/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import sample.shopping.CartDTO;
import sample.shopping.ClothesDAO;
import sample.shopping.ClothesDTO;
import sample.utils.DBUtils;

/**
 *
 * @author KuroZabulus
 */
public class UserDAO {
    private static final String LOGIN = "SELECT fullName, roleID FROM tblUsers WHERE userID=? AND password=?";
    private static final String SEARCH = "SELECT userID, fullName, roleID FROM tblUsers WHERE fullName LIKE ?";
    private static final String DELETE = "DELETE tblUsers WHERE userID=?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName=?, roleID=? WHERE userID=?";
    private static final String CHECK_DUPLICATE = "SELECT fullName FROM tblUsers WHERE userID=?";
    private static final String INSERT = "INSERT INTO tblUsers(userID, fullName, roleID, password) VALUES(?,?,?,?)";
    private static final String CHECK_CART = "SELECT productID FROM tblProducts WHERE productID=? AND quantity>=?";
    private static final String ORDER = "INSERT INTO tblOrders(orderID, userID, date, total) VALUES(?,?,?,?)";
    private static final String ORDER_DETAIL = "INSERT INTO tblOrderDetail(orderID, productID, quantity, price) VALUES(?,?,?,?)";
    private static final String ITERATE = "SELECT orderID FROM tblOrders";
    private static final String INVENTORY_MANAGE = "UPDATE tblProducts SET quantity=? WHERE productID=?";
    private static final String BOTTOM_ONE = "SELECT TOP 1 * FROM tblUsers";
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        UserDTO user = null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs=ptm.executeQuery();
                if(rs.next()){
                    String fullName=rs.getString("fullName");
                    String roleID=rs.getString("roleID");
                    user=new UserDTO(userID, fullName, roleID, "********");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list=new ArrayList<>();
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(SEARCH);
                ptm.setString(1, "%"+search+"%");
                rs=ptm.executeQuery();
                while(rs.next()){
                    String userID= rs.getString("userID");
                    String fullName= rs.getString("fullName");
                    String roleID= rs.getString("roleID");
                    String password= "********";
                    list.add(new UserDTO(userID, fullName, roleID, password));
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
    public boolean delete(String userID) throws SQLException {
        boolean check=false;
        Connection conn=null;
        PreparedStatement ptm=null;
        try {
            conn=DBUtils.getConnection();
            if (conn!=null) {
                ptm=conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                check=ptm.executeUpdate()>0? true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }

    public boolean update(UserDTO user) throws SQLException {
        boolean check=false;
        Connection conn=null;
        PreparedStatement ptm=null;
        try {
            conn=DBUtils.getConnection();
            if (conn!=null) {
                ptm=conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getUserID());
                check=ptm.executeUpdate()>0? true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs=ptm.executeQuery();
                if(rs.next()){
                    check=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return check;
    }

    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword()); 
                check = ptm.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return check;
    }

    public boolean checkItem(ClothesDTO clothes) throws SQLException {
        boolean check = false;
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(CHECK_CART);
                ptm.setString(1, clothes.getID());
                ptm.setInt(2, clothes.getQuantity());
                rs = ptm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return check;      
    }
    
    public static String generateCode () throws SQLException{
        int count=0;
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(ITERATE);
                rs = ptm.executeQuery();
                while(rs.next()==true){
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }  
        String formatStr="%0"+6+"d";
        return "O" + String.format(formatStr, count);
    }
    
    public static String getcurrDate(String dateFormat){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        String date = dateObj.format(formatter);
        return date;
    }

    public boolean placeOrder(CartDTO cart, UserDTO loginUser, double total) throws SQLException {
        boolean check,checkInv= false;
        boolean error = false;
        ClothesDAO cDAO = new ClothesDAO();
        Connection conn=null;
        PreparedStatement ptm1=null;
        PreparedStatement ptm2=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String code = generateCode(); //handmade tools
                String date = getcurrDate("dd-MM-yyyy"); //handmade tools
                String userID = loginUser.getUserID();
                ptm1=conn.prepareStatement(ORDER);
                ptm1.setString(1, code);
                ptm1.setString(2, userID);
                ptm1.setString(3, date);
                ptm1.setDouble(4, total);
                check = ptm1.executeUpdate()>0;
                if(!check){error=true;}
                check = false;
                ptm1 = null;
                for(ClothesDTO clothes:cart.getCart().values()){
                    ptm1=conn.prepareStatement(ORDER_DETAIL);
                    ptm2=conn.prepareStatement(INVENTORY_MANAGE);
                    ptm1.setString(1, code);
                    String productID = clothes.getID();
                    ptm2.setString(2, productID);
                    ptm1.setString(2, productID);
                    int quantity= clothes.getQuantity();
                    ptm1.setInt(3, quantity);
                    int quantityInv = cDAO.getItemQuantity(productID);
                    int finalQuantity = quantityInv-quantity;
                    ptm2.setInt(1,finalQuantity);
                    double price = clothes.getPrice();
                    ptm1.setDouble(4, price); 
                    check = ptm1.executeUpdate()>0;
                    checkInv = ptm2.executeUpdate()>0;
                    if(!check||!checkInv){error=true;}
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm2!=null) ptm2.close();
            if(ptm1!=null) ptm1.close();
            if(conn!=null) conn.close();
        }
         return error;
    }

    public UserDTO getUser() throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        UserDTO user = null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(BOTTOM_ONE);
                rs = ptm.executeQuery();
                if(rs.next()){
                    String userID = rs.getString("userID");
                    String pass = "******";
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    user = new UserDTO(userID, fullName, roleID, pass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
         return user; 
    }

}
