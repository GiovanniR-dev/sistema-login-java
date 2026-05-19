import java.util.HashMap;
import java.util.Map;


public class UserRepository {
    private Map<String,Usuario> banco = new HashMap<>();

    public void salvar(Usuario usuario) {
        banco.put(usuario.getNome(), usuario);
    }

    public Usuario buscar(String nome){
        return banco.get(nome);
    }

    public boolean existe(String nome){
        return banco.containsKey(nome);
    }

}
