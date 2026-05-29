public class NodeRBT{
    PacketRule rule;
    NodeRBT left, right, parent;
    boolean color;

    public NodeRBT(PacketRule rule, boolean color){
        this.rule = rule;
        this.color = color; // Vermelho
    }

    public NodeRBT getRight() {
        return this.right;
    }

    public NodeRBT getLeft() {
        return this.left;
    }

    public boolean getColor() {
        return this.color;
    }

    public PacketRule getRule() {
        return this.rule;
    }
}
