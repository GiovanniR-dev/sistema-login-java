import java.util.HashMap;
import java.util.Map;


public class UserRepository {
    private Map<String,Usuario> banco = new HashMap<>();
    private ArquivoRepository arquivoRepository;

    public UserRepository(){
        this.arquivoRepository = new ArquivoRepository();
        this.banco= arquivoRepository.carregar();
        System.out.println("Usuarios carregados: "+banco.size());
    }

    public void salvar(Usuario usuario) {
        banco.put(usuario.getNome(), usuario);
        arquivoRepository.salvar(banco);
    }

    public Usuario buscar(String nome){
        return banco.get(nome);
    }

    public boolean existe(String nome){
        return banco.containsKey(nome);
    }

    public void deletar(String nome){
        banco.remove(nome);
        arquivoRepository.salvar(banco);
    }

}
