class Solution {
    static ArrayList<Integer> subarraySum(int[] arr, int target) {
        // code here
        int start = 0;
        int sum = 0;
        for (int end = 0; end < arr.length; end++)
        {
            sum += arr[end];
            while(sum > target && start <= end)
            sum -= arr[start++];
            if(sum == target)
            return new ArrayList<>(Arrays.asList(start + 1, end + 1));
        }
        return new ArrayList<>(Arrays.asList(-1));
    }
}
