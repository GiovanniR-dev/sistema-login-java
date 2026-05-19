import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class AuthService {
    private UserRepository repositorio;

    public AuthService(UserRepository repositorio){
        this.repositorio = repositorio;
    }

    public boolean cadastrar(String nome,String senha){
        if (repositorio.existe(nome)){
            System.out.println("Usuario:"+nome+" Ja existe");
            return false;
        }
        String hash=hashSenha(senha);
        if (hash==null)return false;

        repositorio.salvar(new Usuario(nome,hash));
        System.out.println("Usuário '" + nome + "' cadastrado com sucesso!");
        return true;

    }

    public boolean login (String nome, String senha){
        Usuario usuario=repositorio.buscar(nome);

        if (usuario==null){
            System.out.println("Usuario nao encontrado");
            return false;
        }

        String hashDigitado=hashSenha(senha);
        if(usuario.getSenhaHash().equals(hashDigitado)){
            System.out.println("Login realizado com sucesso! Bemv vindo  "+nome+"!");
            return true;
        } else {
            System.out.println("Senha incorreta.");
            return false;
        }
    }

    private String hashSenha(String senha){
        try {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] bytes=md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao gerar hash: " + e.getMessage());
            return null;
        }
    }
}
