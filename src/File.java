import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class File {

    public static void showData(List<Irys> irysList) {
        for (Irys irys : irysList) {
            System.out.println(irys);
        }
    }

    public static List<Irys> readIrysData(String path) {

        List<Irys> irysList = new ArrayList<>();

        try {
            java.io.File file = new java.io.File(path);
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String line = input.nextLine();
                String[] values = line.split(",");

                double v1 = Double.parseDouble(values[0]);
                double v2 = Double.parseDouble(values[1]);
                double v3 = Double.parseDouble(values[2]);
                double v4 = Double.parseDouble(values[3]);

                switch (values[4]) {
                    case "Iris-setosa" -> values[4] = "IRYS_SETOSA";
                    case "Iris-versicolor" -> values[4] = "IRYS_VERSICOLOR";
                    case "Iris-virginica" -> values[4] = "IRYS_VIRGINICA";
                    default -> values[4] = null;
                }

                IrysEnum name = IrysEnum.valueOf(values[4]);

                irysList.add(new Irys(v1, v2, v3, v4, name));
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return irysList;
    }
}
