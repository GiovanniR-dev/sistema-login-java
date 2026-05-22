import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        UserRepository repositorio = new UserRepository();
        AuthService authService = new AuthService(repositorio);
        Scanner sc = new Scanner(System.in);

        System.out.println("====SISTEMA DE LOGIN=====");

        while(true){
            System.out.println("1. Cadastrar");
            System.out.println("2. Fazer login");
            System.out.println("3. Ver perfil");   // NOVO
            System.out.println("4. Deletar conta");
            System.out.println("0. Sair");

            int opcao= sc.nextInt();
            sc.nextLine();

            if(opcao==0){
                System.out.println("Ate logo");
                break;
            }

            if(opcao==1){
                System.out.println("Nome de usuario: ");
                String nome = sc.nextLine().trim();
                System.out.println("Email: ");           // NOVO
                String email = sc.nextLine().trim();     // NOVO
                System.out.println("Senha: ");
                String senha = sc.nextLine().trim();
                authService.cadastrar(nome, senha, email);
            }else if (opcao == 2) {
                System.out.println("Nome de usuario: ");
                String nome = sc.nextLine().trim();
                System.out.println("Senha: ");
                String senha = sc.nextLine().trim();
                authService.login(nome, senha);

            } else if (opcao == 3) {                    // NOVO
                System.out.println("Nome de usuario: ");
                String nome = sc.nextLine().trim();
                Usuario u = repositorio.buscar(nome);
                if (u != null) u.exibirPerfil();
                else System.out.println("Usuario nao encontrado.");

            } else if (opcao == 4) {
                System.out.println("Nome de usuario: ");
                String nome = sc.nextLine().trim();
                System.out.println("Senha: ");
                String senha = sc.nextLine().trim();
                authService.deletarConta(nome, senha);
            }
        }
        sc.close();
    }
}