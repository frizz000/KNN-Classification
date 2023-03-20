import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isRunning = true;
        int choice;

        List<Irys> irysListTraining = File.readIrysData("Data/training_set.csv");
        List<Irys> irysListTest = File.readIrysData("Data/test_set.csv");

        while (isRunning) {
            System.out.println("  _  __  _  _   _  _     ___                   \n" +
                    " | |/ / | \\| | | \\| |   |_ _|  _ _   _  _   ___\n" +
                    " | ' <  | .` | | .` |    | |  | '_| | || | (_-<\n" +
                    " |_|\\_\\ |_|\\_| |_|\\_|   |___| |_|    \\_, | /__/\n" +
                    "                                     |__/      ");
            System.out.println("Menu");
            System.out.println("1. Wyswietl dane");
            System.out.println("2. Testowanie klasyfikatora");
            System.out.println("3. Obliczanie dokładności klasyfikatora");
            System.out.println("4. Zakończ");

            System.out.println("Wybierz opcję: ");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Wyswietl dane");
                    System.out.println("1. Dane treningowe");
                    System.out.println("2. Dane testowe");
                    System.out.println("Wybierz opcję: ");

                    int choice1;
                    choice1 = input.nextInt();

                    switch (choice1) {
                        case 1:
                            System.out.println("Dane treningowe");
                            File.showData(irysListTraining);
                            break;
                        case 2:
                            System.out.println("Dane testowe");
                            File.showData(irysListTest);
                            break;
                        default:
                            System.out.println("Nie ma takiej opcji");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Test");
                    KnnClassification knnClasification = new KnnClassification();
                    System.out.println("Podaj wartości wekorów: ");
                    double v1 = input.nextDouble();
                    double v2 = input.nextDouble();
                    double v3 = input.nextDouble();
                    double v4 = input.nextDouble();

                    Irys irysTest = new Irys(v1, v2, v3, v4, null);

                    System.out.println("Podaj k: ");
                    int k = input.nextInt();
                    IrysEnum name = knnClasification.classify(irysTest, irysListTraining, k);

                    System.out.println("Irys test: " + irysTest);
                    System.out.println("Klasa: " + name);
                    break;

                case 3:
                    System.out.println("Obliczanie dokładności klasyfikatora");
                    KnnClassification knnClasification1 = new KnnClassification();
                    System.out.println("Podaj k: ");
                    int k1 = input.nextInt();
                    double accuracy = knnClasification1.accuracy(irysListTraining, irysListTest, k1);
                    System.out.println("Dokładność: " + accuracy + "%\n");

                    System.out.println("Zle sklasyfikowane irysy: ");
                    for (Irys badIrys : knnClasification1.badIrysMap.keySet()) {
                        System.out.println(badIrys + " --------> " + knnClasification1.badIrysMap.get(badIrys));
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Zakończ");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
                    break;
            }
        }
    }
}