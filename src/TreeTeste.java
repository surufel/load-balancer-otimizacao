public class TreeTeste {

    // Retorna true se a árvore for uma avl válida e false se quebrar
    public boolean isAVLValid(Node node) {
        if (node == null) {
            return true;
        }

        int alturaEsq = (node.left == null) ? -1 : node.left.height;
        int alturaDir = (node.right == null) ? -1 : node.right.height;
        int fb = alturaEsq - alturaDir;

        // se |FB| >= 2, falhou no teste
        if (Math.abs(fb) > 1) {
            System.out.println("ERRO: Nó ID " + node.rule.id + " está desbalanceado. FB = " + fb);
            return false;
        }

        // teste recursivo
        return isAVLValid(node.left) && isAVLValid(node.right);
    }
}