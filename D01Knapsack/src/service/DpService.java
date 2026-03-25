package service;

import entity.ItemGroup;
import java.util.List;

/**
 * D0-1背包动态规划求解服务
 */
public class DpService {

    /**
     * 动态规划求解D0-1背包问题
     * @param groupList 项集列表
     * @param capacity 背包容量
     * @return 最大价值
     */
    public double solveDp(List<ItemGroup> groupList, double capacity) {
        int c = (int) capacity;
        int n = groupList.size();
        double[] dp = new double[c + 1];

        for (int i = 0; i < n; i++) {
            ItemGroup group = groupList.get(i);
            double w1 = group.getW1(), v1 = group.getV1();
            double w2 = group.getW2(), v2 = group.getV2();
            double w3 = group.getW3(), v3 = group.getV3();

            // 逆序遍历，保证每个项集只选一个
            for (int j = c; j >= 0; j--) {
                // 选第一个物品
                if (j >= w1) {
                    dp[j] = Math.max(dp[j], dp[j - (int) w1] + v1);
                }
                // 选第二个物品
                if (j >= w2) {
                    dp[j] = Math.max(dp[j], dp[j - (int) w2] + v2);
                }
                // 选第三个物品
                if (j >= w3) {
                    dp[j] = Math.max(dp[j], dp[j - (int) w3] + v3);
                }
            }
        }
        return dp[c];
    }
}