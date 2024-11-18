import java.util.Calendar;
import java.util.Scanner;

class SocialNetworkGraph {
    static class DOB {
        int day, month, year;

        DOB(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    static class Node {
        Link e; // List of friends (edges)
        Node next; // Pointer to the next person (node)
        String name;
        int comments; // Number of comments
        DOB dob; // Date of birth
        boolean visited; // For traversal

        Node(String name, int comments, DOB dob) {
            this.name = name;
            this.comments = comments;
            this.dob = dob;
            this.visited = false;
        }
    }

    static class Link {
        Link next; // Next friend
        Node ptr;  // Pointer to the friend node

        Link(Node p) {
            this.ptr = p;
            this.next = null;
        }
    }

    private Node head, insert;

    // Create graph
    void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details of person " + (i + 1));

            System.out.print("Enter name: ");
            String name = scanner.next();

            System.out.print("Enter date of birth (day month year): ");
            int day = scanner.nextInt();
            int month = scanner.nextInt();
            int year = scanner.nextInt();
            DOB dob = new DOB(day, month, year);

            System.out.print("Enter number of comments: ");
            int comments = scanner.nextInt();

            if (i == 0)
                head = insert = new Node(name, comments, dob);
            else {
                insert.next = new Node(name, comments, dob);
                insert = insert.next;
            }
        }

        // Establish friendships
        for (Node i = head; i != null; i = i.next) {
            System.out.println("Who are friends of " + i.name + "?");
            for (Node j = head; j != null; j = j.next) {
                if (j == i) continue;

                System.out.print("Is " + j.name + " a friend? (y/n): ");
                char c = scanner.next().charAt(0);

                if (c == 'y' || c == 'Y') {
                    Link temp;
                    if (i.e == null) {
                        i.e = new Link(j);
                        continue;
                    }
                    for (temp = i.e; temp.next != null; temp = temp.next);
                    temp.next = new Link(j);
                }
            }
        }
    }

    // Display the graph
    void display() {
        for (Node i = head; i != null; i = i.next) {
            System.out.print("\nName: " + i.name + " DOB: " + i.dob.day + "/" + i.dob.month + "/" + i.dob.year + 
                             " Comments: " + i.comments + "\nFriends: ");
            for (Link temp = i.e; temp != null; temp = temp.next) {
                System.out.print(temp.ptr.name + " ");
            }
            System.out.println();
        }
    }

    // Calculate max and min number of friends
    void friends() {
        int min = Integer.MAX_VALUE, max = 0;

        Node[] stack = new Node[30];
        int top = 0;
        stack[top] = head;
        head.visited = true;

        while (top > -1) {
            Node temp = stack[top--];
            int friendCount = 0;

            for (Link l = temp.e; l != null; l = l.next) {
                friendCount++;
                if (!l.ptr.visited) {
                    stack[++top] = l.ptr;
                    l.ptr.visited = true;
                }
            }

            max = Math.max(max, friendCount);
            min = Math.min(min, friendCount);
        }

        System.out.println("Maximum friends: " + max + " Minimum friends: " + min);
    }

    // Calculate max and min comments
    void comments() {
        int min = Integer.MAX_VALUE, max = 0;

        Node[] queue = new Node[30];
        int front = -1, rear = 0;

        queue[rear++] = head;
        head.visited = true;

        while (front != rear - 1) {
            Node temp = queue[++front];
            max = Math.max(max, temp.comments);
            min = Math.min(min, temp.comments);

            for (Link l = temp.e; l != null; l = l.next) {
                if (!l.ptr.visited) {
                    queue[rear++] = l.ptr;
                    l.ptr.visited = true;
                }
            }
        }

        System.out.println("Maximum comments: " + max + " Minimum comments: " + min);
    }

    // Reset visited flags
    void resetVisited() {
        for (Node t = head; t != null; t = t.next)
            t.visited = false;
    }

    // Display birthdays in the current month
    void birthdays() {
        Calendar now = Calendar.getInstance();
        int currentMonth = now.get(Calendar.MONTH) + 1;

        boolean found = false;
        System.out.println("Birthdays in current month:");
        for (Node i = head; i != null; i = i.next) {
            if (i.dob.month == currentMonth) {
                System.out.println(i.name + " " + i.dob.day + "-" + i.dob.month + "-" + i.dob.year);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No birthdays in this month!");
        }
    }

    public static void main(String[] args) {
        SocialNetworkGraph graph = new SocialNetworkGraph();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.print("\n1. Create \n2. Display \n3. Friends \n4. Comments \n5. Birthdays \n6. Exit\nChoose: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    graph.create();
                    break;
                case 2:
                    graph.display();
                    break;
                case 3:
                    graph.resetVisited();
                    graph.friends();
                    break;
                case 4:
                    graph.resetVisited();
                    graph.comments();
                    break;
                case 5:
                    graph.birthdays();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
