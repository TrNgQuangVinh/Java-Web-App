/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

/**
 *
 * @author KuroZabulus
 */
public class UserError {
    private String userIDError;
    private String roleIDError;
    private String fullNameError;
    private String passwordError;
    private String confirmError;
    private String error;

    public UserError() {
        this.userIDError = "";
        this.roleIDError = "";
        this.fullNameError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.error = "";
    }

    public UserError(String userIDError, String roleIDError, String fullNameError, String passwordError, String confirmError, String error) {
        this.userIDError = userIDError;
        this.roleIDError = roleIDError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}   
