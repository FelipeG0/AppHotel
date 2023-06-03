package ContDatos;

public class Usuario {
    
    private String username;
    private String password;
    private int userType;
    
    public Usuario(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public int getUserType() {
        return userType;
    }
    
}
