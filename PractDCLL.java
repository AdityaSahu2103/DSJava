import java.util.*;

public class PractDCLL {
    static class Node{
        int data;
        Node prev;
        Node next;
        public Node(int data){
            this.data=data;
            this.prev=null;
            this.next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    public void insertAtFront(int data){
        Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            head.next=head.prev=head;
        }
        else{
            newNode.next=head;
            newNode.prev=tail;
            head.prev=newNode;
            tail.next=newNode;
            head=newNode;
        }
    }
    public void insertAtEnd(int data){
        Node newNode = new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            head.next=head.prev=head;
            return;
         }
         newNode.next=head;
         newNode.prev=tail;
         tail.next=newNode;
         head.prev=newNode;
         tail=newNode;
        }
    
    public void insertAtPosition(int data, int position){
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position! Please enter a position between 1 and " + (size + 1));
            return;
        }
        Node newNode = new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            head.next=head.prev=head;
            return;
        }
        Node temp=head;
        int count=1;
        while(count<position-1 && temp.next!=head){
            temp=temp.next;
            count++;
        }
        newNode.next=temp.next;
            newNode.prev=temp;
            temp.next.prev=newNode;
            temp.next=newNode;
    }

    public void deleteAtFront(){
        if(head==null){
            System.out.println("List is Empty!");
            return;
        }
        else if(head==tail){
            head=tail=null;
            size=0;
            return;
        }
        else{
            head=head.next;
            head.prev=tail;
            tail.next=head;
            size--;
        }
    }

    public void deleteAtEnd(){
        if(head==null){
            System.out.println("List is Empty!");
            return;
        }
        else if(head==tail){
            head=tail=null;
            size=0;
            return;
        }
        else{
            tail=tail.prev;
            tail.next=head;
            head.prev=tail;
            size--;
        }
    }

    public void deleteAtPosition(int position){
        if (position < 1 || position > size ) {
            System.out.println("Invalid position! Please enter a position between 1 and " + (size + 1));
            return;
        }
        if(head==null){
            System.out.println("List is Empty!");
            return;
        }
        Node temp=head;
        int count=1;
        while(count<position-1 && temp.next!=head){
             temp=temp.next;
             count++;
        }
        if(position==1){
            deleteAtFront();
        }
        else if(position==size){
            deleteAtEnd();;
        }
        else{
            Node nodeToDelete = temp.next;
            temp.next = nodeToDelete.next;
            nodeToDelete.next.prev = temp;
            size--;
        }
        
    }
    void search(int data) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        int position = 1;
        do {
            if (temp.data == data) {
                System.out.println("Data found at position: " + position);
                return;
            }
            temp = temp.next;
            position++;
        } while (temp != head);
        System.out.println("Data not found in the list.");
    }
    public void printList(){
        if(head==null){
            System.out.println("List is Empty!");
            return;
        }
        Node temp=head;
        do{
            System.out.print(temp.data);
            temp=temp.next;
            if (temp!=head){
                System.out.print(" <-> ");
            }
        }while(temp!=head);
        System.out.println();
    }
    public static void main(String[] args) {
        PractDCLL lt=new PractDCLL();
        Scanner sc= new Scanner (System.in);
        int choice,data, position;
        char contChoice;
        
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert at front");
            System.out.println("2. Insert at end");
            System.out.println("3. Insert in Position");
            System.out.println("4. Delete front");
            System.out.println("5. Delete end");
            System.out.println("6. Delete from Position");
            System.out.println("7. Display");
            System.out.println("8. Size of List");
            System.out.println("9. Search");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the data: ");
                    data = sc.nextInt();
                    lt.insertAtFront(data);
                    lt.printList();
                    break;
                case 2:
                    System.out.print("Enter the data: ");
                    data = sc.nextInt();
                    lt.insertAtEnd(data);
                    lt.printList();
                    break;
                case 3:
                    System.out.print("Enter the data: ");
                    data = sc.nextInt();
                    System.out.print("Enter position( 1 to "+(lt.size+1)+" ): ");
                    position = sc.nextInt();
                    lt.insertAtPosition(data, position);
                    lt.printList();
                    break;
                case 4:
                    lt.deleteAtFront();
                    lt.printList();
                    break;
                case 5:
                    lt.deleteAtEnd();
                    lt.printList();
                    break;
                case 6:
                    System.out.print("Enter position (1 to " + lt.size + "): ");
                    position = sc.nextInt();
                    lt.deleteAtPosition(position);
                    lt.printList();
                    break;
                case 7:
                    lt.printList();
                    break;
                case 8:
                    System.out.print("The size of the list is: "+ lt.size);
                    break;
                case 9:
                    System.out.print("Enter data to search: ");
                    data = sc.nextInt();
                    lt.search(data);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.print("Do you want to continue (y/n)? ");
            contChoice = sc.next().charAt(0);
        } while (contChoice == 'y' || contChoice == 'Y');

        System.out.println("Program terminated.");
        sc.close();
    }
    
}
