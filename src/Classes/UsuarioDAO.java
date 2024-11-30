package Classes;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
 
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    //Metodos
    public void adicionarTarefa(Usuario user){//create
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement("INSERT INTO user(usuario, senha)VALUES(?,?)");
        
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getSenha());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closerConnection(con, stmt);
        }
    }
    
     public void remover(Usuario user) {
         try {
            // Obter a conexão (substitua pelo seu método de conexão)
            con = ConnectionFactory.getConnection();
            // Preparar a instrução SQL para deletar a tarefa pelo ID
            String sql = "DELETE FROM user WHERE id = ?";
            stmt = con.prepareStatement(sql);

            //Definir o ID da tarefa como parâmetro
            stmt.setInt(1, user.getId());

            // Executar a instrução
            int rowsAffected = stmt.executeUpdate();

            // Feedback para o usuário
            if (rowsAffected > 0) {
                 
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma tarefa encontrada com o ID especificado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover a tarefa: " + ex.getMessage());
        } finally {
            // Fechar a conexão e a declaração
            ConnectionFactory.closerConnection(con, stmt);
        }
        this.listarTarefas();
    }


    
    public void editar(Usuario user, Boolean estado) {
        try {
            // Obter a conexão (substitua pelo seu método de conexão)
            con = ConnectionFactory.getConnection();
            // Query SQL para atualizar a tarefa existente
            String sql = "UPDATE uer SET usuario = ?, senha = ? WHERE id = ?";
            
            if (estado) {
                stmt = con.prepareStatement(sql);

                //Preenchendo os parâmetros da query
                stmt.setString(1, user.getUser());
                stmt.setString(2, user.getSenha());
                stmt.setInt(3, user.getId()); // ID da tarefa para atualização

                // Executa a query
                stmt.executeUpdate();
            
            } else {
                // Chama o método responsável por adicionar uma nova tarefa
                this.adicionarTarefa(user);
            }
        } catch (SQLException ex) {
            // Exibe mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao salvar ou editar a tarefa: " + ex.getMessage());
        } finally {
            // Fecha conexão
            ConnectionFactory.closerConnection(con, stmt);
        }
    }   

    public ArrayList<Usuario> listarTarefas() {
        String sql = "SELECT * FROM user";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setUser(rs.getString("usuario"));
                user.setSenha(rs.getString("senha"));
            
                usuarios.add(user); // Adiciona a tarefa à lista*/
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tarefas: " + ex.getMessage());
        } finally {
            ConnectionFactory.closerConnection(con, stmt, rs);
        }
    
        return usuarios;
    }
    
    public ArrayList<Usuario> buscarTarefasPorTexto(String textoPesquisa) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE usuario LIKE ?";
        con = ConnectionFactory.getConnection();

        if (con == null) {
            System.err.println("Erro ao conectar ao banco de dados.");
            return usuarios; // Retorna uma lista vazia
        }

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            String textoBusca = "%" + textoPesquisa + "%";
            stmt.setString(1, textoBusca);
            stmt.setString(2, textoBusca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario user = new Usuario();
                    user.setId(rs.getInt("id"));
                    user.setUser(rs.getString("usuario"));
                    user.setSenha(rs.getString("senha"));

                    usuarios.add(user);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar tarefas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionFactory.closerConnection(con, stmt, rs);
        }
        return usuarios;
    }

}
