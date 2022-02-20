public class Node{
    int vtx, wsf;
    int height, bf;
    Node left, right;
    public Node(int vtx, int wsf){
        this.vtx = vtx;
        this.wsf = wsf;
        this.height = this.bf = 0;
        this.left = this.right = null;
    }
}