
package eightqueensproject;


public class Board {
    
    public static int[] randomState() {
        int b[] = new int[8];
        for (int i=0; i<8; i++) {
            b[i] = (int) (Math.random()*8);
        }
        return b;
    }
    
    public static int getCost(int[] b) {
        int h = 0;
        for (int i =0; i<8; i++) {
            for(int j=i+1;j<8;j++) {
                if (b[i] == b[j] || Math.abs(b[i]-b[j]) == j-i) {
                    h+=1;
                }
            }
        }
        return h;
    }
    
}
