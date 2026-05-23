public class NodeRBT{
    PacketRule rule;
    NodeRBT left, right, parent;
    boolean color;

    public NodeRBT(PacketRule rule, boolean color){
        this.rule = rule;
        this.color = true; // Vermelho
    }
}
