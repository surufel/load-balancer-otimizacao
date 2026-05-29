public class TreeTesteRBT {

    public static void main(String[] args) {
        System.out.println("--- Teste Local do Auditor QA ---");

        RedBlack_Router_Tree rbt = new RedBlack_Router_Tree();
        TreeTesteRBT auditor = new TreeTesteRBT();

        // Inserções manuais mínimas
        rbt.insert(new PacketRule(10, "192.168.0.1", "10.0.0.1", 1));
        rbt.insert(new PacketRule(20, "192.168.0.2", "10.0.0.2", 1));
        rbt.insert(new PacketRule(5, "192.168.0.3", "10.0.0.3", 1));

        // 1. Testando a contagem de rotações
        long rotacoes = auditor.getRotacoes(rbt);
        System.out.println("Rotações contabilizadas: " + rotacoes);

        // 2. Testando a validação de invariantes
        boolean valida = auditor.isRBTValid(rbt);
        System.out.println("Árvore passou nas invariantes? " + (valida ? "SIM (OK)" : "NÃO (ERRO)"));
    }

    // Retorna true se a árvore for uma RBT válida e false se alguma regra for quebrada
    public boolean isRBTValid(RedBlack_Router_Tree tree) {
        if (tree.getRoot() == tree.getNil()) {
            return true;
        }

        if (tree.getRoot().getColor() == true) {
            System.out.println("ERRO: A raiz da árvore ficou vermelha.");
            return false;
        }

        int blackHeight = validateNode(tree.getRoot(), tree);
        return blackHeight != -1;
    }

    private int validateNode(NodeRBT node, RedBlack_Router_Tree tree) {
        if (node == tree.getNil()) {
            return 1;
        }

        NodeRBT left = node.getLeft();
        NodeRBT right = node.getRight();

        if (node.getColor() == true) {
            if (left.getColor() == true || right.getColor() == true) {
                System.out.println("ERRO: Violação de cor. Nó vermelho (ID " + node.getRule().getId() + ") tem filho vermelho.");
                return -1;
            }
        }

        int leftBlackHeight = validateNode(left, tree);
        int rightBlackHeight = validateNode(right, tree);

        if (leftBlackHeight == -1 || rightBlackHeight == -1) {
            return -1;
        }

        if (leftBlackHeight != rightBlackHeight) {
            System.out.println("ERRO: Desequilíbrio de altura preta no nó ID " + node.getRule().getId());
            return -1;
        }

        return leftBlackHeight + (node.getColor() == false ? 1 : 0);
    }

    public long getRotacoes(RedBlack_Router_Tree tree) {
        return tree.totalRotations;
    }
}