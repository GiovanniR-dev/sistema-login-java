import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private UserRepository repositorio;

    private Map<String, Integer> tentativas=new HashMap<>();
    private static final int MAX_TENTAJAS=3;


    public AuthService(UserRepository repositorio){
        this.repositorio = repositorio;
    }

    public boolean cadastrar(String nome,String senha){
        if (repositorio.existe(nome)){
            System.out.println("Usuario:"+nome+" Ja existe");
            return false;
        }

        if(!senhaValida(senha)){
            System.out.println("Senha Fraca");
            return false;
        }

        String hash=hashSenha(senha);
        if (hash==null)return false;

        repositorio.salvar(new Usuario(nome,hash));
        System.out.println("Usuário '" + nome + "' cadastrado com sucesso!");
        return true;

    }

    public boolean senhaValida(String senha){
        if(senha.length()<6)return false;
        boolean temLetra=false;
        boolean temnumero=false;

        for(char c: senha.toCharArray()){
            if(Character.isLetter(c))temnumero=true;
            if(Character.isDigit(c))temLetra=true;
        }
        return temnumero && temLetra;
    }



    public boolean login (String nome, String senha){
        Usuario usuario=repositorio.buscar(nome);

        if (usuario==null){
            System.out.println("Usuario nao encontrado");
            return false;
        }

        int erros=tentativas.getOrDefault(nome,0);
        if (erros>MAX_TENTAJAS){
            System.out.println("Tentativa excedida");
            return false;
        }

        String hashDigitado=hashSenha(senha);
        if (usuario.getSenhaHash().equals(hashDigitado)) {
            tentativas.remove(nome); // NOVO: reseta os erros ao logar com sucesso
            System.out.println("Login realizado! Bem-vindo, " + nome + "!");
            return true;
        } else {
            tentativas.put(nome, erros + 1); // NOVO: incrementa o contador de erros
            int restantes = MAX_TENTAJAS - (erros + 1);
            if (restantes > 0) {
                System.out.println("Senha incorreta. Tentativas restantes: " + restantes);
            } else {
                System.out.println("Conta bloqueada! Numero de tentativas excedido.");
            }
            return false;
        }
    }

    public boolean deletarConta(String nome, String senha){
        if(!login(nome,senha)){
            System.out.println("Nao e possivel concluir seu login");
            return false;
        }
        repositorio.deletar(nome);
        System.out.println("Conta de: "+nome+" Deletada com sucesso!");
        return true;
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
