import org.junit.jupiter.api.Test;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AVL_Router_TreeTest {

    int[] volumes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 1000000};
    Locale ptBR = Locale.of("pt", "BR");

    @Test
    void realizarStressTest() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)");
        System.out.println("-------------------------------------------------------------------------");
        for (int N : volumes) {
            AVL_Router_Tree arvore = new AVL_Router_Tree();

            for (int i = 1; i <= N; i++) {
                arvore.insert(new PacketRule(i, "Rule " + i, "destino " + i, 1));
            }

            int qntdDelete = (int) (N * .20);

            long inicioDelete = System.nanoTime();
            for (int i = 1; i<= qntdDelete; i++) {
                arvore.delete(i);
            }

            long finalDelete = System.nanoTime();
            long tempoDelete = finalDelete - inicioDelete;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d |%n", N, "AVL", "Deleção 20%", tempoDelete);
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    @Test
    void realizarBenchmarkTest() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)");
        System.out.println("-------------------------------------------------------------------------");

        for (int N : volumes) {
            // Instanciando a árvore AVL
            AVL_Router_Tree arvore = new AVL_Router_Tree();

            // Iniciar a contagem de tempo em nanosegundos,
            // Long foi usado no lugar de int por se tratar de números grandes
            long inicioInsercao = System.nanoTime();

            // For usado para criar a inserção na árvore
            for (int i = 1; i <= N; i++) {
                arvore.insert(new PacketRule(i, "Rule " + i,"destino " + i ,1));
            }

            long finalInsercao = System.nanoTime();
            long tempoInsercao = finalInsercao - inicioInsercao;
            // O código %,-20d alinha à esquerda e adiciona os pontos no número
            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d |%n", N, "AVL", "Inserção", tempoInsercao);

            long inicioBusca = System.nanoTime();

            // For usado para fazer a busca na árvore
            for (int i = 1; i<= N; i++) {
                arvore.search(i);
            }

            long finalBusca = System.nanoTime();
            long tempoBusca = finalBusca - inicioBusca;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d |%n", N, "AVL", "Busca", tempoBusca);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}