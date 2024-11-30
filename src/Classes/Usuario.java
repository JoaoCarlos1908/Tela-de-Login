
package Classes;

class Usuario {
    //Atributos
    private int id;
    private String user;
    private String senha;
    
    //Construtor

    public Usuario() {
        this.id = 0;
        this.user = "Root";
        this.senha = "admin";
    }
    
    //Metodos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
