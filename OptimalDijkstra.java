import java.util.ArrayList;
import java.util.Arrays;

public class OptimalDijkstra{

    //----------------------graph construction------------------------//
    private static class Edge{
        int v, wt;
        Edge(int v, int wt){
            this.v = v;
            this.wt = wt;
        }
    }
    private void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt){
        graph[u].add( new Edge( v, wt ) );
        graph[v].add( new Edge( u, wt ) );
    }
    public ArrayList<Edge>[] constructGraph(){
        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V];

        for( int i = 0; i < V; i++ ){
            graph[i] = new ArrayList<>();
        }

        /* ----------------------------dense graph--------------------------
        addEdge(graph, 0, 1, 3);
        addEdge(graph, 0, 2, 7);
        addEdge(graph, 0, 3, 0);
        addEdge(graph, 0, 4, 0);
        addEdge(graph, 1, 2, 5);
        addEdge(graph, 1, 3, 2);
        addEdge(graph, 1, 4, 10);
        addEdge(graph, 2, 3, 6);
        addEdge(graph, 2, 4, 9);
        addEdge(graph, 3, 4, 1);
        */

        // ----------------------sample sparse graph--------------------------
        addEdge(graph, 0, 1, 3);
        addEdge(graph, 0, 4, 0);
        addEdge(graph, 1, 2, 5);
        addEdge(graph, 2, 3, 6);
        addEdge(graph, 3, 4, 1);

        return graph;
    }
    //---------------------------------------------------------------------//

    //-----------------------implementation via AVL------------------------//

    private int[] dist;
    private int[] pre;

    public void dijkstra_optimal(ArrayList<Edge>[] graph, int V, int src){
        AVL tree = new AVL();

        dist = new int[V]; // distance array
        pre = new int[V];  // predecessor array
        Arrays.fill(dist, (int)1e9);
        Arrays.fill(pre, -1);

        tree.add( new Node(src, 0) );
        dist[src] = 0;

        while( tree.size() > 0 ){
            Node min = tree.removeMin();

            for(Edge e: graph[min.vtx]){
                if( dist[e.v] != (int)1e9 && e.wt + min.wsf < dist[e.v] ){
                    tree.remove( new Node(e.v, dist[e.v]) );
                    dist[e.v] = (int)1e9;
                    pre[e.v] = -1;
                }
                if( dist[e.v] == (int)1e9 ){
                    tree.add( new Node(e.v, e.wt + min.wsf) );
                    dist[e.v] = e.wt + min.wsf;
                    pre[e.v] = min.vtx;
                }
            }
        }
    }

    public void getShortestDistanceAndPath(int src, int V){
        for( int i = 0; i < V; i++ ){
            if(i == src) continue;

            System.out.println(src + " -> " + i);
            System.out.println("Shortest Distance -> " + dist[i]);
            StringBuilder sb = new StringBuilder();
            for(int j = i; j != src; j = pre[j]){
                sb.append(j + " ");
            }
            sb.append(src);
            sb = sb.reverse();
            System.out.println("Shortest Path -> " + sb.toString());

        }
    }
    
    public static void main(String[] args) {
        OptimalDijkstra obj = new OptimalDijkstra();
        ArrayList<Edge>[] graph = obj.constructGraph();
        obj.dijkstra_optimal(graph, 5, 0);
        obj.getShortestDistanceAndPath(0, 5);
    }
}