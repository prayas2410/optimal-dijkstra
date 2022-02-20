public class AVL{
    private Node root;
    private int size;

    public AVL(){
        this.root = null;
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    private void updateHeightAndBf(Node root){
        int lh = (root.left == null ? -1 : root.left.height);
        int rh = (root.right == null ? -1 : root.right.height);
        root.height = Math.max(lh, rh) + 1;
        root.bf = lh - rh;
    }

    private Node leftRotate(Node A){
        Node B = A.right;
        Node LB = B.left;
        B.left = A;
        A.right = LB;

        updateHeightAndBf(A);
        updateHeightAndBf(B);
        return B;
    }
    private Node rightRotate(Node A){
        Node B = A.left;
        Node RB = B.right;
        B.right = A;
        A.left = RB;

        updateHeightAndBf(A);
        updateHeightAndBf(B);
        return B;
    }

    private Node rotate(Node root){
        updateHeightAndBf(root);

        if( root.bf == 2 ){
            if(root.left.bf != 1){
                root.left = leftRotate(root.left);
            }
            return rightRotate(root);

        }else if( root.bf == -2 ){
            if(root.right.bf != -1){
                root.right = rightRotate(root.right);
            }
            return leftRotate(root);
        }

        return root;
    }

    public void add(Node node){
        Node curr = this.root;
        this.root = add(curr, node);
        this.size++;
    }
    private Node add(Node root, Node node){
        if(root == null){
            return node;
        }
        if(node.wsf <= root.wsf){
            root.left = add(root.left, node);
        }else {
            root.right = add(root.right, node);
        }

        return rotate(root);
    }

    public Node removeMin(){
        Node curr = this.root;
        Node ans = getMin(curr);
        remove(ans);
        return ans;
    }
    private Node getMin(Node root){
        if( root.left == null ) return root;
        return getMin(root.left);
    }

    public void remove(Node node){
        if(this.size == 0) return;

        Node curr = this.root;
        this.root = remove(curr, node);
        this.size--;
    }
    private Node remove(Node root, Node node){
        if(root == null) return null;

        if(root.vtx == node.vtx && root.wsf == node.wsf){
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            Node rightMostOfLeft = rightMostOfLeft(root.left);
            root.vtx = rightMostOfLeft.vtx;
            return remove(root.left, rightMostOfLeft);

        }else if( node.wsf <= root.wsf ){
            root.left = remove(root.left, node);

        }else{
            root.right = remove(root.right, node);
        }

        return rotate(root);
    }
    private Node rightMostOfLeft(Node root){
        if(root.right == null) return root;
        return rightMostOfLeft(root.right);
    }
}