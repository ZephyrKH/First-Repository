package entity;

/**
 * D{0-1}背包问题 项集实体类
 * 每个项集包含3个物品，最多选择一个
 */
public class ItemGroup {
    // 三个物品的重量与价值
    private double w1;
    private double v1;
    private double w2;
    private double v2;
    private double w3;
    private double v3;

    // 第三项的价值重量比（用于排序）
    private double ratio3;

    /**
     * 构造方法
     * @param w1 第一个物品重量
     * @param v1 第一个物品价值
     * @param w2 第二个物品重量
     * @param v2 第二个物品价值
     * @param w3 第三个物品重量
     * @param v3 第三个物品价值
     */
    public ItemGroup(double w1, double v1, double w2, double v2, double w3, double v3) {
        this.w1 = w1;
        this.v1 = v1;
        this.w2 = w2;
        this.v2 = v2;
        this.w3 = w3;
        this.v3 = v3;
        // 计算第三项价值重量比
        this.ratio3 = v3 / w3;
    }

    // ==================== 必须添加的 Getter 方法 ====================
    public double getW1() { return w1; }
    public double getV1() { return v1; }
    public double getW2() { return w2; }
    public double getV2() { return v2; }
    public double getW3() { return w3; }
    public double getV3() { return v3; }
    public double getRatio3() { return ratio3; }
}