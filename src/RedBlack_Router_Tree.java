public class RedBlack_Router_Tree{
    private final boolean red = true;
    private final boolean black = false;
    private NodeRBT nil;
    private NodeRBT root;

    public RedBlack_Router_Tree(NodeRBT nil, NodeRBT root){
        nil = new NodeRBT(null, black);
        root = nil;
    }

    //1. Todo nó é VERMELHO ou PRETO.
    //2. A raiz é sempre PRETA.
    //3. Toda folha (NIL) é PRETA.
    //4. Se um nó é VERMELHO, ambos os seus filhos são PRETOS (Não há dois vermelhos seguidos).
    //5. Para cada nó, todos os caminhos do nó até as folhas descendentes contêm o mesmo número de nós PRETOS (Altura Preta).

    private void recolorir(NodeRBT z) {
        // Enquanto o pai do nó inserido (z) for Vermelho, há violação
        while(z.parent.color == red){  // Se o pai de z é o filho ESQUERDO do avô
            if (z.parent == z.parent.parent.left){
                NodeRBT y = z.parent.parent.right; // y é o TIO de z
                // Recoloração
                if(y.color == red){
                    z.parent.color = black; // Pai torna-se preto
                    y.color = black; // Tio torna-se preto
                    z.parent.parent.color = red;  // Avô torna-se vermelho
                    z = z.parent.parent; // O problema sobe para o avô
                } else{
                    // Triângulo
                    if(z == z.parent.right){
                        z = z.parent;
                        rotacaoSimplesEsquerda(z); // Implementar
                    }
                    // Linha
                    z.parent.color = black;
                    z.parent.parent.color = red;
                    rotacaoSimplesDireita(z.parent.parent); // Implementar
                }
            } else{ // Se o pai de z é o filho DIREITO do avô (Lógica Simétrica)
                NodeRBT y = z.parent.parent.left; // tio
                // Recoloração
                if (y.color == red) {
                    z.parent.color = black;
                    y.color = black;
                    z.parent.parent.color = red;
                    z = z.parent.parent;
                } else {
                    // Triângulo
                    if (z == z.parent.left) {
                        z = z.parent;
                        rotacaoSimplesDireita(z);
                    }
                    // Linha
                    z.parent.color = black;
                    z.parent.parent.color = red;
                    rotacaoSimplesEsquerda(z.parent.parent);
                }
            }
        }
        // Garante que a raiz seja sempre preta ao final
        root.color = black;
    }

    private void rotacaoSimplesEsquerda(NodeRBT x){
        NodeRBT y = x.right;
        x.right = y.left;

        if(y.left != nil){
            y.left.parent = x;
        }

        y.parent = x.parent;

        if(x.parent == nil){
            root = y;
        } else if(x == x.parent.left){
            x.parent.left = y;
        } else{
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotacaoSimplesDireita(NodeRBT x){
        NodeRBT y = x.left;
        x.left = y.right;

        if (y.right != nil){
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil){
            root = y;
        }else if(x == x.parent.right){
            x.parent.right = y;
        } else{
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void insert(){

    }

    private void delete(){

    }
}

