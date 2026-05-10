public class Node{
    PacketRule rule;
    Node left, right;
    int height;

    public Node(PacketRule rule) {
            this.rule = rule;
            this.height = 1;
        }
}