import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Random;

class RedBlack_Router_TreeTest {
    int[] volumes = {1000, 10000, 100000, 1000000};
    Locale ptBR = Locale.of("pt", "BR");

    @Test
    void realizarStressTest() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s | %-10s | %-10s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)", "Rotações", "Status QA");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int N : volumes) {
            RedBlack_Router_Tree arvore = new RedBlack_Router_Tree();
            TreeTesteRBT auditor = new TreeTesteRBT();

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

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",
                    N, "RBT", "Inserção", timeInsercao, auditor.getRotacoes(arvore), auditor.isRBTValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";RBT;Insercao;" + timeInsercao + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isRBTValid(arvore) ? "OK" : "ERRO"));

            int qntdDelete = (int) (N * .20);
            long inicioDelete = System.nanoTime();

            for (int i = 0; i < qntdDelete; i++) {
                arvore.delete(pacotes[i].id);
            }

            long tempoDelete = System.nanoTime() - inicioDelete;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",
                    N, "RBT", "Deleção 20%", tempoDelete, auditor.getRotacoes(arvore), auditor.isRBTValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";RBT;Delecao;" + tempoDelete + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isRBTValid(arvore) ? "OK" : "ERRO"));
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    @Test
    void realizarBenchmarkTest() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-9s | %-10s | %-15s | %-20s | %-10s | %-10s |%n", "Volume", "Estrutura", "Operação", "Tempo (nanos)", "Rotações", "Status QA");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int N : volumes) {
            RedBlack_Router_Tree arvore = new RedBlack_Router_Tree();
            TreeTesteRBT auditor = new TreeTesteRBT();

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

            long tempoInsercao = System.nanoTime() - inicioInsercao;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",
                    N, "RBT", "Inserção", tempoInsercao, auditor.getRotacoes(arvore), auditor.isRBTValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";RBT;Insercao;" + tempoInsercao + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isRBTValid(arvore) ? "OK" : "ERRO"));

            long inicioBusca = System.nanoTime();

            for (int i = 0; i < N; i++) {
                arvore.search(pacotes[i].id);
            }

            long tempoBusca = System.nanoTime() - inicioBusca;

            System.out.printf(ptBR, "| %-9d | %-10s | %-15s | %,-20d | %-10d | %-10s |%n",
                    N, "RBT", "Busca", tempoBusca, auditor.getRotacoes(arvore), auditor.isRBTValid(arvore) ? "OK" : "ERRO");
            System.out.println(N + ";RBT;Busca;" + tempoBusca + ";" + auditor.getRotacoes(arvore) + ";" + (auditor.isRBTValid(arvore) ? "OK" : "ERRO"));
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
}