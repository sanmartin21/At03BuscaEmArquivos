package At03BuscaEmArquivos;

public class Main {
	public static void main(String[] args) {
	    String diretorio = "C:\\Users\\jvsan\\OneDrive\\Área de Trabalho\\7º Semestre\\ProcessAltoDesempenho\\At03BuscaEmArquivos\\arquivosNomes";
	    String palavraBusca = "GLA";
	    int limiteThreads = 2;

	    BuscaEmArquivos busca = new BuscaEmArquivos(diretorio, palavraBusca, limiteThreads);
	    busca.buscar();
	}
}
