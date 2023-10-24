import Weeeek4.mypackage.RoadSensor;

public class Traffic {
    public static void main(String[] args) {
        int[] binaryArray = RoadSensor.generateRandomBinaryArray(4);
        for (int num : binaryArray) {
            System.out.print(num + " ");
        }
    }

}
