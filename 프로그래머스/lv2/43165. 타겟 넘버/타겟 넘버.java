class Solution {

    static int count;
    static int target;

    void dfs(int[] arr, int sum, int index) {
        if (index == arr.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        
        dfs(arr, sum + arr[index], index+1);
        dfs(arr, sum - arr[index], index+1);

    }

    public int solution(int[] numbers, int target) {

        this.target = target;

        dfs(numbers, 0, 0);

        return count;
    }
}