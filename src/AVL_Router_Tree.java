public class AVL_Router_Tree {

    private Node root;
    public long totalRotations = 0;
    /*
    Para efeito de entendimento e acesso rápido a informação,
     vou deixar o pseudocódigo aprendido em aula.
     */

    /*
    Função obter_altura(no):
        se no == nulo: retorne -1
        retorne no.altura
    */

    private int obter_altura(Node no) {
        if (no == null) {
            return -1;
        }
        return no.height;
    }

    /*
    Função calcular_FB(no):
        se no == nulo: retorne 0
        retorne obter_altura(no.esquerda) - obter_altura(no.direita)
    */

    private int calcular_FB(Node no) { // Fator de Balanceamento (FB = h(esq) - h(dir))
        if (no == null) {
            return 0;
        }
        return obter_altura(no.left) - obter_altura(no.right);
    }

    /*
    Algoritmo de Re-balanceamento (Geral)• Ao subir o caminho da inserção/remoção:
        no.altura = 1 + max(obter_altura(no.esq), obter_altura(no.dir))
        FB = calcular_FB(no)
            se FB > 1 (Esq pesada):
            se calcular_FB(no.esq) >= 0:
            return Rotação_Simples_Direita(no) (LL)
            senão:
            return Rotação_Dupla_Esq_Dir(no) (LR)

            se FB < -1 (Dir pesada):
            se calcular_FB(no.dir) <= 0:
            return Rotação_Simples_Esquerda(no) (RR)
            senão:
            return Rotação_Dupla_Dir_Esq(no) (RL)
*/

    private Node rebalanceamento(Node no) {
        no.height = 1 + Math.max(obter_altura(no.left), obter_altura(no.right));
        int FB = calcular_FB(no);

        // Esquerda pesada (FB > 1)
        if (FB > 1) {
            if (calcular_FB(no.left) >= 0) {
                return rotacao_Simples_Direita(no); // LL
            } else {
                return rotacao_Dupla_Esq_Dir(no); // LR, a fazer
            }
        }

        // Direita pesada (FB < -1)
        if (FB < -1) {
            if (calcular_FB(no.right) <= 0) {
                return rotacao_Simples_Esquerda(no); // RR, a fazer
            } else {
                return rotacao_Dupla_Dir_Esq(no); // RL, a fazer
            }
        }

        return no; // Nó Balanceado
    }
    /*
    Função rotacionar_direita(A):
        B = A.esquerda
        T2 = B.direita
        // Rearranjo
        B.direita = A
        A.esquerda = T2
        // Atualizar alturas (A primeiro, depois B)
        A.altura = 1 + max(obter_altura(A.esq), obter_altura(A.dir))B.altura = 1 + max(obter_altura(B.esq), obter_altura(B.dir))retorne B // B é a nova raiz local
     */

    private Node rotacao_Simples_Direita(Node A) {
        Node B = A.left;
        Node T2 = B.right;

        B.right = A;
        A.left = T2;

        A.height = 1 + Math.max(obter_altura(A.left), obter_altura(A.right));
        B.height = 1 + Math.max(obter_altura(B.left), obter_altura(B.right));
        return B;
    }

    private Node rotacao_Simples_Esquerda(Node A) {
        Node B = A.right;
        Node T2 = B.left;

        B.left = A;
        A.right = T2;

        A.height = 1 + Math.max(obter_altura(A.left), obter_altura(A.right));
        B.height = 1 + Math.max(obter_altura(B.left), obter_altura(B.right));

        return B;
    }

    /*
    •Passo 1:
     •Rotação ESQ no filho (transforma emLL).
    •Passo 2:
     •Rotação DIR no pai (finaliza).
    */

    private Node rotacao_Dupla_Esq_Dir(Node A) {
        A.left = rotacao_Simples_Esquerda(A.left); // Rotaciona B para esquerda
        return rotacao_Simples_Direita(A);          // Rotaciona A para direita
    }

    private Node rotacao_Dupla_Dir_Esq(Node A) {
        A.right = rotacao_Simples_Direita(A.right); // Rotaciona B para direita
        return rotacao_Simples_Esquerda(A);          // Rotaciona A para esquerda
    }

    public void insert(PacketRule rule) {
        root = insert(root, rule);
    }

    private Node insert(Node no, PacketRule rule) {
        // BST
        if (no == null){
            return new Node(rule);
        }

        if(rule.id < no.rule.id){
            no.left = insert(no.left, rule);
        }
        else if(rule.id > no.rule.id){
            no.right = insert(no.right, rule);
        } else {
            return no;
        }
        return rebalanceamento(no);
    }

    public Node search(int id){
        return search(root, id);
    }

    private Node search(Node no, int id){
        if (no == null){
            return null;
        }

        if (id < no.rule.id){
            return search(no.left, id);
        }
        else if(id > no.rule.id){
            return search(no.right, id);
        } else {
            return no;
        }
    }

    private Node delete(Node no, int id) {
        if (no == null)
            return null;

        if (id < no.rule.id)
            no.left = delete(no.left, id);
        else if (id > no.rule.id)
            no.right = delete(no.right, id);
        else {
            if (no.left == null)
                return no.right;
            if (no.right == null)
                return no.left;

            // Encontra o menor da subárvore direita
            Node sucessor = no.right;
            while (sucessor.left != null)
                sucessor = sucessor.left;

            no.rule = sucessor.rule;
            no.right = delete(no.right, sucessor.rule.id);
        }

        return rebalanceamento(no);
    }

    public Node getRoot() {
        return this.root;
    }

    public void delete(int id) {
        root = delete(root, id);
    }
}
