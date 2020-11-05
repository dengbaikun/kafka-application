package simple;

/**
 * @author DK
 * @version 1.
 * 0
 * @date 2020/11/5 11:48 下午
 */
public class HashCalculate {
    public static void main(String[] args) {
        // 要放在第17个分区存储
        System.out.println(Math.abs("gp-assign-group-1".hashCode()) % 50);
    }
}
