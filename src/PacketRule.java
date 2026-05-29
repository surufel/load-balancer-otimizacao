public class PacketRule{
    int id;
    String IP_source;
    String IP_dest;
    int priority;

    public PacketRule(int id, String IP_source, String IP_dest, int priority){
            this.id = id;
            this.IP_source = IP_source;
            this.IP_dest = IP_dest;
            this.priority = priority;
        }

    public int getId() {
        return id;
    }
}
