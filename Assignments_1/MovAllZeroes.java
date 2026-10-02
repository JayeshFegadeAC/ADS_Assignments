public class MovAllZeroes {
    
    public static void main(String[] args) {

        int arr[] = {0,5,0,3,8,0,2};

        int j = 0;

        for(int i = 0;i<arr.length;i++){
            if(arr[i]!=0){
                arr[j] = arr[i];
                j++;
            }
        }

        while(j<arr.length){
            arr[j] = 0;
            j++;
        }

        for(int i=0;i<arr.length;i++){
        System.out.print(arr[i]+" ");

        }
        
    }
}
