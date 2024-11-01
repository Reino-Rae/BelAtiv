public class MultiplicacaoMatrizParalela {

    private static final int[][] matrizAdjacencia = {
            {0, 1, 0, 1, 0},
            {0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0}
    };
    private static int[][] matrizResultado = new int[5][5];

    public static void main(String[] args) throws InterruptedException {
        long tempoInicio = System.currentTimeMillis();

        // Etapa 1: Multiplicacao da matriz usando threads
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int linha = i;
            threads[i] = new Thread(() -> multiplicarLinha(linha));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join(); // Espera todas as threads terminarem
        }

        long tempoMultiplicacao = System.currentTimeMillis() - tempoInicio;
        System.out.println("Matriz A^2:");
        imprimirMatriz(matrizResultado);

        // Etapa 2: Encontrar usuarios com mais amigos em comum
        long tempoInicioAmigos = System.currentTimeMillis();
        int[] amigosComuns = new int[5];
        for (int i = 0; i < 5; i++) {
            int soma = 0;
            for (int j = 0; j < 5; j++) {
                soma += matrizResultado[i][j];
            }
            amigosComuns[i] = soma;
        }

        int max1 = -1, max2 = -1;
        int pessoa1 = -1, pessoa2 = -1;
        for (int i = 0; i < 5; i++) {
            if (amigosComuns[i] > max1) {
                max2 = max1;
                pessoa2 = pessoa1;
                max1 = amigosComuns[i];
                pessoa1 = i;
            } else if (amigosComuns[i] > max2) {
                max2 = amigosComuns[i];
                pessoa2 = i;
            }
        }

        System.out.println("Usuarios com mais amigos em comum: " + indiceParaNome(pessoa1) + " e " + indiceParaNome(pessoa2));

        long tempoAmigos = System.currentTimeMillis() - tempoInicioAmigos;

        // Etapa 3: Encontrar o usuario mais influente
        long tempoInicioInfluencia = System.currentTimeMillis();
        int[] influencia = new int[5];
        for (int j = 0; j < 5; j++) {
            int soma = 0;
            for (int i = 0; i < 5; i++) {
                soma += matrizResultado[i][j];
            }
            influencia[j] = soma;
        }

        int maxInfluencia = -1;
        int influenciador = -1;
        for (int i = 0; i < 5; i++) {
            if (influencia[i] > maxInfluencia) {
                maxInfluencia = influencia[i];
                influenciador = i;
            }
        }

        System.out.println("Usuario mais influente: " + indiceParaNome(influenciador));

        long tempoInfluencia = System.currentTimeMillis() - tempoInicioInfluencia;
        long tempoTotal = System.currentTimeMillis() - tempoInicio;

        // Exibir os tempos
        System.out.println("Tempo para multiplicacao da matriz: " + tempoMultiplicacao + " ms");
        System.out.println("Tempo para encontrar amigos em comum: " + tempoAmigos + " ms");
        System.out.println("Tempo para encontrar o usuario mais influente: " + tempoInfluencia + " ms");
        System.out.println("Tempo total de execucao: " + tempoTotal + " ms");
    }

    // Metodo para multiplicar uma linha da matriz de forma paralela
    private static void multiplicarLinha(int linha) {
        for (int j = 0; j < 5; j++) {
            matrizResultado[linha][j] = 0;
            for (int k = 0; k < 5; k++) {
                matrizResultado[linha][j] += matrizAdjacencia[linha][k] * matrizAdjacencia[k][j];
            }
        }
    }

    // Metodo para imprimir a matriz
    private static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Metodo para converter indice numerico em nome
    private static String indiceParaNome(int indice) {
        switch (indice) {
            case 0: return "Alice";
            case 1: return "Bob";
            case 2: return "Carol";
            case 3: return "David";
            case 4: return "Paul";
            default: return "Desconhecido";
        }
    }
}
