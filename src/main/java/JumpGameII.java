
import java.util.Arrays;

class JumpGameII {
  public static void main(String[] args) {
    Assert.assertEquals(jump(new int[] {2, 3, 1, 1, 4}), 2);
  }

  public static int jump(int[] nums) {
    int steps = 0, rangeEnd = 0, rangeMax = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      rangeMax = Math.max(rangeMax, i + nums[i]);
      if (rangeMax >= nums.length - 1) {
        return steps + 1;
      }
      if (i == rangeEnd) {
        steps++;
        rangeEnd = rangeMax;
      }
    }
    return steps;
  }

  public static int jumpDP(int[] nums) {
    int[] dp = new int[nums.length];
    for (int i = nums.length - 2; i >= 0; i--) {
      int tempMin = nums.length;
      for (int j = 1; j < nums.length - i && j <= nums[i]; j++) {
        tempMin = Math.min(tempMin, dp[i + j] + 1);
      }
      dp[i] = tempMin;
    }
    System.out.println("dp " + Arrays.toString(dp));

    return dp[0];
  }
}
