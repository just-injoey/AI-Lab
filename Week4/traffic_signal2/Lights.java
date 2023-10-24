package traffic_signal2;

public class Lights {
    public static int[] light = new int[4];
    
    public static int[] updateLights(int[] rs, int[] roadNum) {
        
        light[roadNum[0]] = 0; // Turn off the current light
        roadNum[0] = (roadNum[0] + 1) % 4; // Move to the next index
        // Find the next available index in rs array where rs[index] is 1
        int nextIndex = roadNum[0];
        while (rs[nextIndex] != 1) {
            nextIndex = (nextIndex + 1) % 4;
            if (nextIndex == roadNum[0]) {
                // If we looped through all indices and didn't find a valid one, stop updating
                return light;
            }
        }
        
        // Reset light array to all zeros
        for(int i = 0; i < light.length; i++) {
            light[i] = 0;
        }
        
        light[nextIndex] = 1; // Turn on the light at the next available index
        roadNum[0] = nextIndex; // update the roadNum to the most recently turned on light
        System.out.print("Light: ");
        for(int i = 0; i < light.length; i++){
            System.out.print(light[i]);
        }
        System.out.println("\n----------------------------------------");
        return light;
    }
}


//     public static int[] updateLights(int[] rs, int roadNum) {
        
    //     light[roadNum] = 0; // Turn off the current light
    //     roadNum = (roadNum + 1) % 4; // Move to the next index
        
    //     // Find the next available index in rs array where rs[index] is 1
    //     int nextIndex = roadNum;
    //     while (rs[nextIndex] != 1) {
    //         nextIndex = (nextIndex + 1) % 4;
    //         if (nextIndex == roadNum) {
    //             // If we looped through all indices and didn't find a valid one, stop updating
    //             return light;
    //         }
    //     }
        
    //     // Reset light array to all zeros
    //     for(int i = 0; i < light.length; i++) {
    //         light[i] = 0;
    //     }
        
    //     light[nextIndex] = 1; // Turn on the light at the next available index
    //     roadNum = nextIndex; // update the roadNum to the most recently turned on light
    //     System.out.print("Light: ");
    //     for(int i = 0; i < light.length; i++){
    //         System.out.print(light[i]);
    //     }
    //     System.out.println("\n----------------------------------------");
    //     return light;
    // }