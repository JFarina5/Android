package com.example.jfarina.gamesystem720.model.and.db.directory;

public class UserModel {

    private String firstName, lastName, password, username, dateOfBirth, email;
    private int userId;

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }
    public void setLastName(String lastName){
        this.lastName= lastName;
    }
    public void setPassword(String password){
        this.password= password;
    }
    public void setUsername(String username){
        this.username= username;
    }
    public void setEmail(String email){
        this.email= email;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.email= dateOfBirth;
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
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public String getEmail(){
        return email;
    }

    public int getUserId(){
        return userId;
    }

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String dateOfBirth, String username, String password,
                     String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", userId=" + userId +
                '}';
    }
}
