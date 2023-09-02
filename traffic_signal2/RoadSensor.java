package traffic_signal2;


import java.util.Random;

//Simplex State Agent

// vehicle present = 1
// vehicle not present = 0
// -- road sensor (vehichle present or not -> Eg 1 0 0 1 )
// input - ?  
// input jugaad - random binary array generator
// return a binary array rs


public class RoadSensor {
    
    public static int[] generateRandomBinaryArray(int size) {
        int[] binaryArray = new int[size];
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            binaryArray[i] = random.nextInt(2); // Generates either 0 or 1
        }
        return binaryArray;
    }

    public static int[] randomArrayGen(int size){
        int[] binaryArray = new int[size];
        for (int i = 0; i < size; i++) {
            
            binaryArray[i] = 1; 
        }
        binaryArray[2]=0;
        return binaryArray;
    }
}
