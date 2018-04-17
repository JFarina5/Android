package com.example.jfarina.easypill.model.and.db.directory;

public class UserModel {

    private String firstName, lastName, password, email;
    private int age, userId;

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }
    public void setLastName(String lastName){
        this.lastName= lastName;
    }
    public void setPassword(String password){
        this.password= password;
    }
    public void setEmail(String email){
        this.email= email;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

    public int getAge(){
        return age;
    }
    public int getUserId(){
        return userId;
    }

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String password,
                     String email, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", userId=" + userId +
                '}';
    }
}
