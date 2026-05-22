import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Usuario {
    private  String nome;
    private  String senhaHash;
    private  String email;
    private  String DataCadastro;

    public Usuario(String nome, String senhaHash, String email) {
        this.nome=nome;
        this.senhaHash=senhaHash;
        this.email=email;
        this.DataCadastro= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public Usuario(String nome, String senhaHash, String email, String dataCadastro) {
        this.nome = nome;
        this.senhaHash = senhaHash;
        this.email = email;
        this.DataCadastro = dataCadastro;
    }

    public String getNome()        { return nome; }
    public String getSenhaHash()   { return senhaHash; }
    public String getEmail()       { return email; }
    public String getDataCadastro(){ return DataCadastro; }

    public void exibirPerfil() {
        System.out.println("========== PERFIL ==========");
        System.out.println("Nome:     " + nome);
        System.out.println("Email:    " + email);
        System.out.println("Cadastro: " + DataCadastro);
        System.out.println("============================");
    }




}
