/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author KuroZabulus
 */
public class CartDTO {
    private Map<String,ClothesDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String,ClothesDTO> cart) {
        this.cart = cart;
    }

    public Map<String,ClothesDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String,ClothesDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ClothesDTO clothes) {
        boolean check = false;
        try{
            if(this.cart==null){
                this.cart = new HashMap<>();
            }
            if(this.cart.containsKey(clothes.getID())){
                int currentQuantity = this.cart.get(clothes.getID()).getQuantity();
                clothes.setQuantity(currentQuantity+clothes.getQuantity());
            }
            this.cart.put(clothes.getID(), clothes);
            check = true;
        } catch (Exception e) {
            e.toString();
        }
        return check;
    }

    public boolean remove(String id) {
        boolean check=false;
        try{
            if(this.cart!=null){
                if(this.cart.containsKey(id)){
                    this.cart.remove(id);
                    check=true;
                }
            }   
        }catch(Exception e){
            e.toString();
        }
        return check;
    }

    public boolean edit(String id, int quantity) {
        boolean check=false;
        try{
            if(this.cart!=null){
                if(this.cart.containsKey(id)){
                    ClothesDTO clothes=this.cart.get(id);
                    clothes.setQuantity(quantity);
                    this.cart.replace(id, clothes);
                    check=true;
                }
            }   
        }catch(Exception e){
            e.toString();
        }
        return check;
    }
    
    
}
