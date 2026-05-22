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
            System.out.println("3. Deletar conta");
            System.out.println("0. Sair");

            int opcao= sc.nextInt();
            sc.nextLine();

            if(opcao==0){
                System.out.println("Ate logo");
                break;
            }

            System.out.println("Nome de usuario");
            String nome = sc.nextLine().trim();

            System.out.println("Senha: ");
            String senha = sc.nextLine().trim();

            if (opcao==1){
                authService.cadastrar(nome, senha);
            } else if(opcao==2){
                authService.login(nome, senha);
            } else if (opcao == 3) {
                authService.deletarConta(nome, senha);
            }
            else{
                System.out.println("Opcao invalida");
            }
        }
        sc.close();
    }
}