
package Classes;


public class Main {

   
    public static void main(String[] args) {
            Usuario user = new Usuario();
            UsuarioDAO dao = new UsuarioDAO();
            
            ConnectionFactory.getConnection();
    }
    
}
