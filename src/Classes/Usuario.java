
package Classes;

public class Usuario {
    //Atributos
    private int id;
    private String user;
    private String senha;
    private String nome;
    private int idade;
    private String email;
    
    //Construtor

    public Usuario() {
        this.id = 0;
        this.user = "";
        this.senha = "";
        this.nome = "";
        this.idade = 0;
        this.email = "";
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
