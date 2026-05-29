import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainTest {
    public static void main(String[] args) {
        AVL_Router_Tree avl = new AVL_Router_Tree();
        TreeTesteAVL auditor = new TreeTesteAVL();

        //seed de teste
        Random random = new Random(42);
        ArrayList<Integer> idsInseridos = new ArrayList<>();

        System.out.println("Iniciando teste");

        for (int i = 0; i < 100000; i++) {
            int id = random.nextInt(1000000);
            if (!idsInseridos.contains(id)) {
                avl.insert(new PacketRule(id, "192.168.0.1", "10.0.0.1", 1));
                idsInseridos.add(id);
            }
        }

        System.out.println("Inserções concluídas");
        System.out.println("Rotações na inserção: " + avl.totalRotations);
        System.out.println("AVL pós-inserção: " + (auditor.isAVLValid(avl.getRoot()) ? "APROVADA" : "REPROVADA"));

        // processo da deleção começa aqui
        avl.totalRotations = 0;

        System.out.println("\n remoção de 20%");
        Collections.shuffle(idsInseridos, random);

        int nosParaRemover = (int) (idsInseridos.size() * 0.20);
        for (int i = 0; i < nosParaRemover; i++) {
            avl.delete(idsInseridos.get(i));
        }

        System.out.println("Deleções concluídas!");
        System.out.println("Rotações na Deleção: " + avl.totalRotations);
        System.out.println("Avl pós-deleção: " + (auditor.isAVLValid(avl.getRoot()) ? "APROVADA" : "REPROVADA"));

        System.out.println("\n Teste integridade IP");
        int idTeste= idsInseridos.get(nosParaRemover+1);
        Node noEncontrado=avl.search(idTeste);

        if (noEncontrado!=null){
            System.out.println("Regra encontrada. ID:" + noEncontrado.rule.id);
            System.out.println("A árvore guardou o IP?" + (noEncontrado.rule.IP_source.equals("192.168.0.1") ? " Sim" : "Não"));
        } else{
            System.out.println("Regra não encontrada após deleção");
        }
    }
}