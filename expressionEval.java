import java.util.*;
public class expressionEval {
    static Node root;
    static class Node{
    char data;
    Node left, right;
    public Node(char data) {
    this.data=data;
    left=right=null;
    }
    }
    public static void main(String[] args) {
    Scanner in=new Scanner(System.in);
    int continues=1;
    while(continues==1) {
    System.out.println("Menu:");
    System.out.println("1:Enter expression");
    System.out.println("2:Recursive Inorder");
    System.out.println("3:Non-Recursive Inorder");
    System.out.println("4:Recursive Preorder");
    System.out.println("5:Non-Recursive Preorder");
    System.out.println("6:Recursive Postorder");
    System.out.println("7:Non-Recursive Postorder");
    System.out.println("8:BFS");
    System.out.println("Other Keys: Exit");
    System.out.print("Enter choice: ");
    int choice;
    try {
    choice=in.nextInt();
    }
    catch (Exception e) {
    choice=9;
    }
    System.out.println("\n");
    switch(choice) {
    case 1:
    System.out.print("Enter expression: ");
    String str=in.next();
    convert(str);
    break;
    case 2:
    System.out.println("Recursive Inorder: ");
    
    recurinorder(root);
    break;
    case 3:
    System.out.println("Non-Recursive Inorder: ");
    nonrecurinorder(root);
    break;
    case 4:
    System.out.println("Recursive Preorder: ");
    recurpreorder(root);
    break;
    case 5:
    System.out.println(" Non-recursive Preorder: ");
    nonrecurpreorder(root);
    break;
    case 6:
    System.out.println("Recursive Postorder: ");
    recurpostorder(root);
    break;
    case 7:
    System.out.println("Non-Recursive Postorder:");
    nonrecurpostorder(root);
    break;
    case 8:
    System.out.println("BFS:");
    bfs(root);
    break;
    default:
    continues=0;
    break;
    }
    System.out.println("\n\n");
    }
    in.close();
    }
    public static boolean isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    public static void convert(String postfix) {
        Stack<Node> stack = new Stack<>();
        try {
            for (char ch : postfix.toCharArray()) {
                if (!isOperator(ch)) {
                    stack.push(new Node(ch));
                } else {
                    if (stack.size() < 2) {
                        System.out.println("Invalid postfix expression.");
                        return;
                    }
                    Node op1 = stack.pop();
                    Node op2 = stack.pop();
                    Node temp = new Node(ch);
                    temp.left = op2;
                    temp.right = op1;
                    stack.push(temp);
                }
            }
            root = stack.pop();
            if (!stack.isEmpty()) {
                System.out.println("Invalid postfix expression.");
                root = null;
            }
        } catch (EmptyStackException e) {
            System.out.println("Error while parsing expression: " + e.getMessage());
        }
    }
    public static void recurinorder(Node root) {
    if(root==null) {
    return;
    }
    else {
    Node temp=root;
    recurinorder(temp.left);
    System.out.print(temp.data+" ");
    recurinorder(temp.right);
    }
    }
    public static void recurpreorder(Node root) {
    if(root==null) {
    return;
    }
    else {
    Node temp=root;
    System.out.print(temp.data+" ");
    recurpreorder(temp.left);
    recurpreorder(temp.right);
    }
    }
    public static void recurpostorder(Node root) {
    if(root==null) {
    return;
    }
    else {
    Node temp=root;
    recurpostorder(temp.left);
    
    recurpostorder(temp.right);
    System.out.print(temp.data+" ");
    }
    }
    public static void nonrecurinorder(Node root) {
        Node node=root;
        Stack<Node> st=new Stack<>();
        while(true){
            if(node!=null){
                st.push(node);
                node=node.left;
            }
            else{
                if(st.isEmpty()){break;}
                node=st.pop();
                System.out.print(node.data+" ");
                node=node.right;
            }
        }
    }
    public static void nonrecurpreorder(Node root) {
        if(root==null){return;}
        Stack<Node> st= new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
         Node currRoot=st.pop();
         System.out.print(currRoot.data+" ");
         if(currRoot.right!=null){st.push(currRoot.right);}
         if(currRoot.left!=null){st.push(currRoot.left);}
        }
    }
    public static void nonrecurpostorder(Node root) {
        Stack<Node> st1= new Stack<>();
        Stack<Node> st2= new Stack<>();
        st1.push(root);
        while(!st1.isEmpty()){
            Node node=st1.pop();
            st2.add(node);
            if(node.left!=null){
                st1.push(node.left);
            }
            if(node.right!=null){
                st1.push(node.right);
            }
        }
        while(!st2.isEmpty()){
            System.out.print(st2.pop().data+" ");
        }
    }
    public static void bfs(Node root) {
        if(root==null){
            return ;
        }
        Queue<Node> q= new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node currNode=q.remove();
            if(currNode==null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }
                else{q.add(null);}
            }
            else{
                System.out.print(currNode.data+" ");
                if(currNode.left!=null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
        }
    }
    }
    