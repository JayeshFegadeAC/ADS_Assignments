class MaxMin {
    public static void main(String[] args) {
        
        int arr[] = {15,8,23,4,19,7};

        int min = 0;
        int max = 0;

        for(int i = 0;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
            }else{
                min = arr[i];
            }
        }

        System.out.println("Maximum : "+max);
        System.out.println("Minimum : "+min);
    }
}