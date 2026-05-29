import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AVL_Router_TreeTest {

    int[] volumes = {1000, 10000, 100000, 1000000};
    Locale ptBR = Locale.of("pt", "BR");

    @Test
    void realizarStressTest() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s | %-10s | %-10s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)", "Rotações", "Status QA");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int N : volumes) {
            AVL_Router_Tree arvore = new AVL_Router_Tree();
            TreeTesteAVL auditor = new TreeTesteAVL();
            Random random = new Random(42);
            PacketRule[] pacotes = new PacketRule[N];

            for (int i = 0; i < N; i++) {
                int randomId = random.nextInt(1000000);
                pacotes[i] = new PacketRule(randomId, "Rule " + randomId, "Destino " + randomId, 1);
            }

            long startInsercao = System.nanoTime();

            for (int i = 0; i < N; i++) {
                arvore.insert(pacotes[i]);
            }

            long timeInsercao = System.nanoTime() - startInsercao;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",N, "AVL", "Inserção", timeInsercao, auditor.getRotacoes(arvore), auditor.isAVLValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";AVL;Insercao;" + timeInsercao + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isAVLValid(arvore) ? "OK" : "ERRO"));

            int qntdDelete = (int) (N * .20);
            long inicioDelete = System.nanoTime();

            for (int i = 0; i < qntdDelete; i++) {
                arvore.delete(pacotes[i].id);
            }

            long finalDelete = System.nanoTime();
            long tempoDelete = finalDelete - inicioDelete;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",N, "AVL", "Deleção 20%", tempoDelete, auditor.getRotacoes(arvore), auditor.isAVLValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";AVL;Delecao;" + tempoDelete + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isAVLValid(arvore) ? "OK" : "ERRO"));
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    @Test
    void realizarBenchmarkTest() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s | %-10s | %-10s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)", "Rotações", "Status QA");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int N : volumes) {
            AVL_Router_Tree arvore = new AVL_Router_Tree();
            TreeTesteAVL auditor = new TreeTesteAVL();
            Random random = new Random(42);
            PacketRule[] pacotes = new PacketRule[N];

            for (int i = 0; i < N; i++) {
                int randomId = random.nextInt(1000000);
                pacotes[i] = new PacketRule(randomId, "Rule " + randomId, "Destino " + randomId, 1);
            }

            long inicioInsercao = System.nanoTime();

            for (int i = 0; i < N; i++) {
                arvore.insert(pacotes[i]);
            }

            long finalInsercao = System.nanoTime();
            long tempoInsercao = finalInsercao - inicioInsercao;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",N, "AVL", "Inserção", tempoInsercao, auditor.getRotacoes(arvore), auditor.isAVLValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";AVL;Insercao;" + tempoInsercao + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isAVLValid(arvore) ? "OK" : "ERRO"));

            long inicioBusca = System.nanoTime();

            for (int i = 0; i < N; i++) {
                arvore.search(pacotes[i].id);
            }

            long finalBusca = System.nanoTime();
            long tempoBusca = finalBusca - inicioBusca;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",N, "AVL", "Busca", tempoBusca, auditor.getRotacoes(arvore), auditor.isAVLValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";AVL;Busca;" + tempoBusca + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isAVLValid(arvore) ? "OK" : "ERRO"));
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
}