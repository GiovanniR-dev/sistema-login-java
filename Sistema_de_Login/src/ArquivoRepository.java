import java.io.*;
import java.util.HashMap;
import java.util.Map;



public class ArquivoRepository {
    private static final String ARQUIVO = "usuarios.txt";

    public void salvar(Map<String, Usuario> banco) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario u : banco.values()) {
                writer.write(u.getNome() + ";" + u.getSenhaHash() + ";" + u.getEmail() + ";" + u.getDataCadastro());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public Map<String, Usuario> carregar() {
        Map<String, Usuario> banco = new HashMap<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) return banco; // primeira vez, arquivo ainda não existe

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    banco.put(partes[0], new Usuario(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        return banco;
    }
}
