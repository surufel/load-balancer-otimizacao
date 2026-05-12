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
}
