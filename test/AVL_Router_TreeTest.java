import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AVL_Router_TreeTest {

    int[] volumes = {1000, 10000, 100000, 1000000};
    Locale ptBR = Locale.of("pt", "BR");

    @Test
    void realizarStressTest() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)");
        System.out.println("-------------------------------------------------------------------------");

        for (int N : volumes) {
            AVL_Router_Tree arvore = new AVL_Router_Tree();
            Random random = new Random(42);
            PacketRule[] pacotes = new PacketRule[N];

            //setup do packetRule
            for (int i = 0; i < N; i++) {
                int randomId = random.nextInt(1000000);
                pacotes[i] = new PacketRule(randomId, "Rule " + randomId, "Destino " + randomId, 1);
            }

            long startInsercao = System.nanoTime();

            //inserção
            for (int i = 0; i < N; i++) {
                arvore.insert(pacotes[i]);
            }

            long timeInsercao = System.nanoTime() - startInsercao;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d |%n", N, "AVL", "Inserção", timeInsercao);

            int qntdDelete = (int) (N * .20);
            long inicioDelete = System.nanoTime();

            for (int i = 0; i < qntdDelete; i++) {
                arvore.delete(pacotes[i].id);
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
            //gerando a seed e Instanciando o packetRule
            Random random = new Random(42);
            PacketRule[] pacotes = new PacketRule[N];
            //for para preencher o packetRule
            for (int i = 0; i < N; i++) {
                int randomId = random.nextInt(1000000);
                pacotes[i] = new PacketRule(randomId, "Rule " + randomId, "Destino " + randomId, 1);
            }

            // Iniciar a contagem de tempo em nanosegundos,
            // Long foi usado no lugar de int por se tratar de números grandes
            long inicioInsercao = System.nanoTime();

            // For usado para criar a inserção na árvore
            for (int i = 0; i < N; i++) {
                arvore.insert(pacotes[i]);
            }

            long finalInsercao = System.nanoTime();
            long tempoInsercao = finalInsercao - inicioInsercao;
            // O código %,-20d alinha à esquerda e adiciona os pontos no número
            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d |%n", N, "AVL", "Inserção", tempoInsercao);

            long inicioBusca = System.nanoTime();

            // For usado para fazer a busca na árvore
            for (int i = 0; i < N; i++) {
                arvore.search(pacotes[i].id);
            }

            long finalBusca = System.nanoTime();
            long tempoBusca = finalBusca - inicioBusca;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d |%n", N, "AVL", "Busca", tempoBusca);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}