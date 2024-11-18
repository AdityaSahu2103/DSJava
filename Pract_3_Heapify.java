import java.util.*;
public class Pract_3_Heapify {
    public static void heapify(int[]nums, int i, int size){
        int left=2*i+1;
        int right=2*i+2;
        int maxIndex=i;
        if(left<size && nums[left]>nums[i]){
            maxIndex=left;
        }
        if(right<size && nums[right]>nums[maxIndex]){
            maxIndex=right;
        }
        if(maxIndex!=i){
            int temp=nums[i];
            nums[i]=nums[maxIndex];
            nums[maxIndex]=temp;
            heapify(nums, maxIndex, size);

        }
    }
    public static void create(int[]nums){
        for(int i=(nums.length/2)-1;i>=0;i--){
            heapify(nums, i, nums.length);
        }
    }
    public static void heapSort(int[]nums){
        create(nums);
        for(int i=nums.length-1;i>=0;i--){
            int temp=nums[0];
            nums[0]=nums[i];
            nums[i]=temp;
            heapify(nums, 0, i);;
        }
    }
    public static void print(int[]nums){
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        System.out.println("Max Element:" + nums[nums.length-1]);
        System.out.println("Min Element:" + nums[0]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = null;
        boolean initialized = false;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Input Array");
            System.out.println("2. Create Max Heap");
            System.out.println("3. Perform Heap Sort");
            System.out.println("4. Print Array");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the size of the array: ");
                    int n = sc.nextInt();
                    nums = new int[n];
                    System.out.println("Enter " + n + " elements:");
                    for (int i = 0; i < n; i++) {
                        nums[i] = sc.nextInt();
                    }
                    initialized = true;
                    System.out.println("Array initialized successfully.");
                    break;

                case 2:
                    if (!initialized) {
                        System.out.println("Please input an array first.");
                    } else {
                        create(nums);
                        System.out.println("Max heap created.");
                    }
                    break;

                case 3:
                    if (!initialized) {
                        System.out.println("Please input an array first.");
                    } else {
                        heapSort(nums);
                        System.out.println("Heap Sort performed.");
                    }
                    break;

                case 4:
                    if (!initialized) {
                        System.out.println("Please input an array first.");
                    } else {
                        print(nums);
                    }
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Prompt to continue
            System.out.print("Do you want to continue? (yes/no): ");
            String continueChoice = sc.next().trim().toLowerCase();
            if (!continueChoice.equals("yes")) {
                System.out.println("Exiting program.");
                sc.close();
                break;
            }
        }
    }
    
}
