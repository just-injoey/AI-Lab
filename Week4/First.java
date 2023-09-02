import java.util.Timer;
import java.util.TimerTask;

import traffic_signal2.RoadSensor;
import traffic_signal2.Lights;
import traffic_signal2.Logger;

//Simplex State Agent

// vehicle present = 1
// vehicle not present = 0
// -- road sensor (vehichle present or not -> Eg 1 0 0 1 )
// input - ?  
// input jugaad - random binary array generator
// return a binary array rs


public class First {
    private static int[] roadNum = {0};
    
    public static void main(String[] args) {
        // Create a timer to update lights every 3 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                int[] rs = RoadSensor.generateRandomBinaryArray(4);// RoadSensor.randomArrayGen(4);

                // Logger.logRoadSensor(rs);

                System.out.print("rs: ");
                for(int i = 0; i < rs.length; i++){
                    System.out.print(rs[i]);
                }
                System.out.println();

                

                Lights.updateLights(rs, roadNum);
                // int[] lightStates = Lights.updateLights(rs, roadNum);

                // // Log light state changes
                // Logger.logLightStateChange(roadNum, lightStates);


            }
        }, 0, 3000); // 3 seconds
    }    
}
