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

}
