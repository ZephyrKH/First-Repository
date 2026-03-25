package ui;

import entity.ItemGroup;
import service.DpService;
import util.FileUtil;
import util.SortUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainUI extends JFrame {
    private List<ItemGroup> dataList;
    private double maxValue;
    private long useTime;

    private JTextArea resultArea;

    public MainUI() {
        setTitle("D0-1背包问题求解系统");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顶部按钮面板
        JPanel panel = new JPanel();
        JButton loadBtn = new JButton("读取数据");
        JButton sortBtn = new JButton("按第三项比值排序");
        JButton calcBtn = new JButton("计算最优解");
        JButton saveBtn = new JButton("保存结果");

        panel.add(loadBtn);
        panel.add(sortBtn);
        panel.add(calcBtn);
        panel.add(saveBtn);
        add(panel, BorderLayout.NORTH);

        // 结果区域
        resultArea = new JTextArea();
        resultArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 事件监听
        loadBtn.addActionListener(e -> loadData());
        sortBtn.addActionListener(e -> sortData());
        calcBtn.addActionListener(e -> calculate());
        saveBtn.addActionListener(e -> saveResult());
    }

    // 读取数据
    private void loadData() {
        JFileChooser chooser = new JFileChooser("data/");
        if (chooser.showOpenDialog(this) == 0) {
            try {
                dataList = FileUtil.readData(chooser.getSelectedFile().getPath());
                resultArea.setText("读取成功！项集数量：" + dataList.size());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "读取失败");
            }
        }
    }

    // 排序 + 显示排序后的数据（已修复）
    private void sortData() {
        if (dataList == null) {
            JOptionPane.showMessageDialog(this, "请先读取数据");
            return;
        }

        // 执行排序
        SortUtil.sortByRatio3(dataList);

        // ====================== 关键修复：显示排序后的所有项集数据 ======================
        StringBuilder sb = new StringBuilder();
        sb.append("========== 排序完成（按第三项价值重量比降序）==========\n");
        sb.append("序号\tw1\tv1\tw2\tv2\tw3\tv3\t比值3\n");

        int index = 1;
        for (ItemGroup g : dataList) {
            sb.append(index++).append("\t")
                    .append(g.getW1()).append("\t").append(g.getV1()).append("\t")
                    .append(g.getW2()).append("\t").append(g.getV2()).append("\t")
                    .append(g.getW3()).append("\t").append(g.getV3()).append("\t")
                    .append(String.format("%.2f", g.getRatio3())).append("\n");
        }

        resultArea.setText(sb.toString());
    }

    // 计算最优解
    private void calculate() {
        if (dataList == null) {
            JOptionPane.showMessageDialog(this, "请先读取数据");
            return;
        }
        String capStr = JOptionPane.showInputDialog("请输入背包容量：");
        double cap = Double.parseDouble(capStr);

        long start = System.currentTimeMillis();
        DpService dp = new DpService();
        maxValue = dp.solveDp(dataList, cap);
        useTime = System.currentTimeMillis() - start;

        resultArea.setText("=== 求解结果 ==="
                + "\n背包容量：" + cap
                + "\n最大价值：" + maxValue
                + "\n耗时：" + useTime + "ms");
    }

    // 保存结果
    private void saveResult() {
        try {
            String content = resultArea.getText();
            FileUtil.saveResult("result.txt", content);
            JOptionPane.showMessageDialog(this, "已保存到 result.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "保存失败");
        }
    }
}