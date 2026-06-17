
import java.sql.*;


public class UserRepository {
    public void salvar(Usuario usuario){
        String sql = "INSERT INTO usuarios (nome, senha_hash, email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,usuario.getNome());
            ps.setString(2,usuario.getSenhaHash());
            ps.setString(3,usuario.getEmail());

            ps.executeUpdate();
            System.out.println("Salvo com sucesso!");

        }catch (SQLException e){
            System.out.println("erro ao salvar"+e.getMessage());
        }
    }

    public Usuario buscar(String nome){
        String sql="SELECT nome, senha_hash, email, data_cadastro FROM usuarios WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,nome);

            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                return new Usuario(
                        rs.getString("nome"),
                        rs.getString("senha_hash"),
                        rs.getString("email"),
                        rs.getString("data_cadastro")
                );
            }
        } catch (SQLException e){
            System.out.println("erro ao buscar"+e.getMessage());
        }
        return null;
    }

    public boolean existe(String nome){
        return buscar(nome) != null;
    }

    public void deletar(String nome){
        String sql = "DELETE FROM usuarios WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.executeUpdate();
            System.out.println("Usuario deletado do banco.");

        } catch (SQLException e){
            System.out.println("erro ao deletar"+e.getMessage());
        }
    }
}
