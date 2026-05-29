public class TreeTesteAVL {

    public static void main(String[] args) {
        System.out.println("--- Teste Local do Auditor QA (AVL) ---");

        AVL_Router_Tree avl = new AVL_Router_Tree();
        TreeTesteAVL auditor = new TreeTesteAVL();

        // Inserções manuais mínimas para a árvore não ficar vazia
        avl.insert(new PacketRule(10, "192.168.0.1", "10.0.0.1", 1));
        avl.insert(new PacketRule(20, "192.168.0.2", "10.0.0.2", 1));
        avl.insert(new PacketRule(30, "192.168.0.3", "10.0.0.3", 1));

        // 1. Testando a contagem de rotações
        long rotacoes = auditor.getRotacoes(avl);
        System.out.println("Rotações contabilizadas: " + rotacoes);

        // 2. Testando a validação de invariantes do FB
        boolean valida = auditor.isAVLValid(avl);
        System.out.println("Árvore passou nas invariantes? " + (valida ? "SIM (OK)" : "NÃO (ERRO)"));
    }

    // Retorna true se a árvore for uma AVL válida e false se o balanceamento for quebrado
    public boolean isAVLValid(AVL_Router_Tree tree) {
        if (tree.getRoot() == null) {
            return true;
        }
        return validaBalanceamentoRecursivo(tree.getRoot());
    }

    private boolean validaBalanceamentoRecursivo(Node node) {
        if (node == null) return true;

        int alturaEsq = (node.left == null) ? -1 : node.left.height;
        int alturaDir = (node.right == null) ? -1 : node.right.height;
        int fb = alturaEsq - alturaDir;

        if (Math.abs(fb) > 1) {
            System.out.println("ERRO: Nó ID " + node.rule.id + " está desbalanceado (FB = " + fb + ")");
            return false;
        }

        return validaBalanceamentoRecursivo(node.left) && validaBalanceamentoRecursivo(node.right);
    }

    public long getRotacoes(AVL_Router_Tree tree) {
        return tree.totalRotations;
    }
}