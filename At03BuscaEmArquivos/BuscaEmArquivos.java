package At03BuscaEmArquivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.regex.Pattern;

public class BuscaEmArquivos {
    private final Semaphore semaphore;
    private final String diretorio;
    private final Pattern pattern;

    public BuscaEmArquivos(String diretorio, String palavraBusca, int limiteThreads) {
        this.diretorio = diretorio;
        this.semaphore = new Semaphore(limiteThreads);
        this.pattern = Pattern.compile(Pattern.quote(palavraBusca), Pattern.CASE_INSENSITIVE);
    }

    public void buscar() {
        File dir = new File(diretorio);
        if (dir.exists() && dir.isDirectory()) {
            File[] arquivos = dir.listFiles();
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    try {
                        semaphore.acquire(); 
                        new Thread(() -> {
                            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                                String linha;
                                while ((linha = reader.readLine()) != null) {
                                	if (pattern.matcher(linha).find()) {
                                        System.out.println("Nome encontrado no arquivo " + arquivo.getName() + ": " + linha);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                semaphore.release(); 
                            }
                        }).start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
