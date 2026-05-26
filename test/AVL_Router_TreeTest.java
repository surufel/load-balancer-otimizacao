import org.junit.jupiter.api.Test;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AVL_Router_TreeTest {

    @Test
    void realizarStressTest() {
        int[] volumes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};

        for (int N : volumes) {
            // Instanciando a árvore AVL
            AVL_Router_Tree arvore = new AVL_Router_Tree();
            // For usado para criar a inserção na árvore
            for (int i = 1; i <= N; i++) {
                arvore.insert(new PacketRule(i, "Rule " + i,"destino " + i ,1));

            }
        }
    }

    @Test
    void realizarBenchmarkTest() {

    }
}