import java.util.*;
public class Pract_2_BST {
    static class  Node{
        Node left, right;
        int data;
        public Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static Node insert(Node root, int val){
       if(root==null){
        return new Node(val);
       }
       if(root.data>val){
        root.left=insert(root.left, val);
       }
       else{
        root.right=insert(root.right, val);
       }
       return root;
    }
    public static void inorder(Node root){
        if(root==null){return ;}
        inorder(root.left);
        System.out.println(root.data+" ");
        inorder(root.right);
    }
    public static boolean search(Node root, int key){
        if(root==null){return false;}
        if(root.data==key){
            return true;
        }
        else if(root.data>key){return search(root.left, key);}
        else{
            return search(root.right, key);
        }
    }
    public static Node delete(Node root, int val){
        if(root==null){return null;}
        if(val<root.data){
            root.left=delete(root.left, val);
        }
        else if(val>root.data){
            root.right=delete(root.right, val);
        }
        else{
            //case 1
            if(root.left==null && root.right==null){
                return null;
            }
            //case 2
            if(root.left==null && root.right!=null){
               return root.right;
            }
            else if(root.right==null){return root.left;}
            //case 3
            root.data=minValue(root.right);
            root.right=delete(root.right, root.data);
        }
        return root;
    }
    public static int minValue(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root.data;
    }
    public static void levelOrderTraversal(Node root){
        if(root==null){return;}
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Node curr=q.remove();
                System.out.print(curr.data+" ");
                if(curr.left!=null){q.add(curr.left);}
                if(curr.right!=null){q.add(curr.right);}
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root=null;
        while(true){
            System.out.println("\nMenu:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Inorder Display");
            System.out.println("5. Level Order Display (BFS)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                System.out.print("Enter the number of elements to insert: ");
                int n = sc.nextInt();
                System.out.println("Enter the elements:");
                for (int i = 0; i < n; i++) {
                    int val = sc.nextInt();
                    root = insert(root, val);
                }
                break;

                case 2:
                System.out.print("Enter the value to delete: ");
                int val = sc.nextInt();
                root = delete(root, val);
                break;

                case 3:
                System.out.print("Enter the value to search: ");
                int key = sc.nextInt();
                if (search(root, key)) {
                    System.out.println(key + " is found in the BST.");
                } else {
                    System.out.println(key + " is not found in the BST.");
                }
                break;

                case 4:
                System.out.println("Inorder Traversal:");
                inorder(root);
                System.out.println();
                break;

                case 5:
                System.out.println("Level Order Traversal (BFS):");
                levelOrderTraversal(root);
                System.out.println();
                break;

                case 6:
                System.out.println("Exiting program.");
                sc.close();
                return;

                default:
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
}
