public class AVL_Router_Tree {

    private Node root;

    /*
    Para efeito de entendimento e acesso rápido a informação,
     vou deixar o pseudocódigo aprendido em aula.
     */

    /*
    Função obter_altura(no):
        se no == nulo: retorne -1
        retorne no.altura
    */

    private int obter_altura(Node no){
        if(no == null){
            return -1;
        }
        return no.height;
    }

    /*
    Função calcular_FB(no):
        se no == nulo: retorne 0
        retorne obter_altura(no.esquerda) - obter_altura(no.direita)
    */

    private int calcular_FB(Node no){ // Fator de Balanceamento (FB = h(esq) - h(dir))
        if(no == null){
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

    private Node rebalanceamento(Node no){
        no.height = 1 + Math.max(obter_altura(no.left), obter_altura(no.right));
        int FB = calcular_FB(no);

        // Esquerda pesada (FB > 1)
        if(FB > 1){
            if(calcular_FB(no.left) >= 0){
                return rotacao_Simples_Direita(no); // LL, a fazer
            } else{
                return rotacao_Dupla_Esq_Dir(no); // LR, a fazer
            }
        }

        // Direita pesada (FB < -1)
        if(FB < -1){
            if (calcular_FB(no.right) <= 0){
                return rotacao_Simples_Esquerda(no); // RR, a fazer
            } else{
                return rotacao_Dupla_Dir_Esq(no); // RL, a fazer
            }
        }

        return no; // Nó Balanceado
    }
}