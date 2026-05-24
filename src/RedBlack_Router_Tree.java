public class RedBlack_Router_Tree{
    private final boolean red = true;
    private final boolean black = false;
    private NodeRBT nil;
    private NodeRBT root;

    public RedBlack_Router_Tree(){
        this.nil = new NodeRBT(null, black);
        this.root = nil;
    }

    //1. Todo nó é VERMELHO ou PRETO.
    //2. A raiz é sempre PRETA.
    //3. Toda folha (NIL) é PRETA.
    //4. Se um nó é VERMELHO, ambos os seus filhos são PRETOS (Não há dois vermelhos seguidos).
    //5. Para cada nó, todos os caminhos do nó até as folhas descendentes contêm o mesmo número de nós PRETOS (Altura Preta).

    private void rb_insert_fixup(NodeRBT z) {
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

    private void insert(PacketRule rule){
        NodeRBT y = nil;
        NodeRBT x = root;

        NodeRBT z = new NodeRBT(rule, red);
        z.left = nil;
        z.right = nil;
        z.parent = nil;

        while (x != nil){
            y = x;
            if(z.rule.id < x.rule.id){
                x = x.left;
            } else if(z.rule.id > x.rule.id) {
                x = x.right;
            } else{
                return;
            }

            z.parent = y;

            if (y == nil){
                root = z;
            } else if(z.rule.id < y.rule.id){
                y.left = z;
            } else{
                y.right = z;
            }
            rb_insert_fixup(z); // Correção
        }
    }

    private NodeRBT menorNo(NodeRBT no){
        while(no.left != nil){
            no = no.left;
        }
        return no;
    }

    public NodeRBT search(int id){
        return search(root, id);
    }

    private NodeRBT search(NodeRBT no, int id){
        if(no == nil){
            return nil;
        }

        if(id < no.rule.id){
            return search(no.left, id);
        } else if(id > no.rule.id){
            return search(no.right, id);
        } else{
            return no;
        }
    }

    private void transplantar(NodeRBT u, NodeRBT v){ // substitui uma subárvore por outra, é necessário pro delete
        if(u.parent == nil){
            root = v;
        } else if(u == u.parent.left){
            u.parent.left = v;
        } else{
            u.parent.right = v;
            v.parent = u.parent;
        }
    }

    public void delete(int id){
        NodeRBT z = search(root, id);
        if(z == nil){
            return;
        }
        NodeRBT y = z;
        NodeRBT x;
        boolean originalColor = y.color;

        // zero ou um filho
        if(z.left == nil){
            x = z.right;
            transplantar(z, z.right);
        } else if(z.right == nil){
            x = z.left;
            transplantar(z, z.left);
        } else{
            // dois filhos, encontra sucessor
            y = menorNo(z.right);
            originalColor = y.color;
            x = y.right;

            if(y.parent == z){
                x.parent = y;
            } else{
                transplantar(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplantar(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        // Se o nó removido era PRETO, é necessário corrigir
        if (originalColor == black)
            deleteFixup(x);
    }

    private void deleteFixup(NodeRBT x){
        while(x != root && x.color == black){
            if(x == x.parent.left){
                NodeRBT w = x.parent.right; // irmão de x
                // Irmão vermelho
                if(w.color == red){
                    w.color = black;
                    x.parent.color = red;
                    rotacaoSimplesEsquerda(x.parent);
                    w = x.parent.right;
                }
                // Irmão preto, ambos filhos do irmão pretos
                if(w.left.color == black && w.right.color == black){
                    w.color = red;
                    x = x.parent;
                } else{
                    // Irmão preto, filho direito do irmão preto
                    if(w.right.color == black){
                        w.left.color = black;
                        w.color = red;
                        rotacaoSimplesDireita(w);
                        w = x.parent.right;
                    }
                    // Irmão preto, filho direito do irmão vermelho
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.right.color = black;
                    rotacaoSimplesEsquerda(x.parent);
                    x = root;
                }
            } else{ // simétrico
                NodeRBT w = x.parent.left; // irmão de x
                // Irmão vermelho
                if(w.color == red){
                    w.color = black;
                    x.parent.color = red;
                    rotacaoSimplesDireita(x.parent);
                    w = x.parent.left;
                }
                // Irmão preto, ambos filhos do irmão pretos
                if(w.right.color == black && w.left.color == black){
                    w.color = red;
                    x = x.parent;
                } else{
                    // Irmão preto, filho esquerdo do irmão preto
                    if(w.left.color == black){
                        w.right.color = black;
                        w.color = red;
                        rotacaoSimplesEsquerda(w);
                        w = x.parent.left;
                    }
                    // Irmão preto, filho esquerdo do irmão vermelho
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.left.color = black;
                    rotacaoSimplesDireita(x.parent);
                    x = root;
                }
            }
        }
        x.color = black; // garante que x termina preto
    }
}