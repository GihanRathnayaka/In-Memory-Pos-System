package lk.ijs.my.dep.pos.model.Login;


import javafx.scene.control.Button;

public class SystemUsers {

    private String userType;
    private String UserName;
    private String password;
    private String name;
    private String isActive;
    Button delete = new Button("Delete");
    //{delete.setBackground(im);}
    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public SystemUsers() {
    }

    public SystemUsers(String userType, String userName, String password, String name, String isActive) {
        this.setUserType(userType);
        setUserName(userName);
        this.setPassword(password);
        this.setName(name);
        this.setIsActive(isActive);
    }




    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "SystemUsers{" +
                "userType='" + userType + '\'' +
                ", UserName='" + UserName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
