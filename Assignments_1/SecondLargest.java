public class SecondLargest {
    
    public static void main(String[] args) {
        
        int arr[] = {12,5,8,20,15,20,7};

        int largest = 0;
        int secondlargest = -1;

        for(int i = 1;i<arr.length;i++){
            if(arr[i]>largest)
                largest = arr[i];
        }

        System.out.println("Largest : "+largest);

        for(int i = 0;i<arr.length;i++){
            if(arr[i]>secondlargest && arr[i]<largest)
                secondlargest = arr[i];
        }

        System.out.println("Second largest : "+secondlargest);


    }
}
