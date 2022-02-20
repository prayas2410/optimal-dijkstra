public class Node{
    int vtx, wsf; // vtx = vertex , wsf = weight so far or total overall weight
    int height, bf; // height of subtree, bf = balancing factor
    Node left, right;
    public Node(int vtx, int wsf){
        this.vtx = vtx;
        this.wsf = wsf;
        this.height = this.bf = 0;
        this.left = this.right = null;
    }
}