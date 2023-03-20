import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnnClassification {
    public Map<Irys, IrysEnum> badIrysMap = new HashMap<>();
    public double distance(Irys irysTraining, Irys irysTest) {
        double[] values1 = irysTraining.getValuesTabe();
        double[] values2 = irysTest.getValuesTabe();
        double sum = 0;
        for (int i = 0; i < values1.length; i++) {
            sum += Math.pow(values1[i] - values2[i], 2);
        }
        return Math.sqrt(sum);
    }

    public IrysEnum classify(Irys irysTest, List<Irys> irysList, int k) {

        List<Irys> irysListCopy = new ArrayList<>(irysList);

        irysListCopy.sort((o1, o2) -> {
            double distance1 = distance(o1, irysTest);
            double distance2 = distance(o2, irysTest);
            return Double.compare(distance1, distance2);
        });

//        for (int i = 0; i < k; i++) {
//            System.out.println(irysListCopy.get(i));
//            System.out.println("Distance: " + distance(irysListCopy.get(i), irysTest));
//        }

        Map<IrysEnum, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            IrysEnum name = irysListCopy.get(i).getName();
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }

        int max = 0;
        IrysEnum name = null;
        for (Map.Entry<IrysEnum, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                name = entry.getKey();
            }
        }
        return name;
    }

    public double accuracy(List<Irys> irysList, List<Irys> irysListTest, int k) {
        int correct = 0;
        for (Irys irys : irysListTest) {
            IrysEnum name = classify(irys, irysList, k);
            if (name == irys.getName()) {
                correct++;
            }else {
                badIrysMap.put(irys, name);
            }
        }
        return Math.round((double) correct / irysListTest.size() * 10000) / 100.0;
    }
}
