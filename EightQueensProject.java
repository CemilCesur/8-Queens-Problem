
package eightqueensproject;

import java.util.Arrays;


public class EightQueensProject {

  
    
    static int changeCount = 0;
    static int randomRestartCount = 0; 
    static double startTime;
    static double endTime;
    static int[] b;
    public static int[] hillClimbing() {
        startTime = System.nanoTime();
        b = Board.randomState();
        int currentCost = Board.getCost(b);        
        boolean flag = true;
        boolean flagChange = false;
        
        while (flag) {
            
            int tempCost = currentCost;

            for (int col=0; col<8 ; col++) {
                
                flagChange = false;
                
                for (int row=0; row<8; row++) {              
                    int[] bCopy = Arrays.copyOf(b,8);
                    bCopy[col] = row;
                    int cost = Board.getCost(bCopy);
                    if (currentCost > cost){
                        b[col] = row;
                        currentCost = cost;    
                        flagChange = true;                      
                    }                                       
                }
                
                if(flagChange)
                    changeCount++;
            }
            
            
            if(tempCost > currentCost && currentCost != 0)
                continue;
            
            
            if(currentCost == 0 )
                flag = false;
            
            else {
                b = Board.randomState();
                currentCost = Board.getCost(b);
                randomRestartCount++; 
            }
            
        }
        
        
        
        
        endTime = System.nanoTime();
        
        return b;
        
        
        
    }
    
    public static void main(String[] args) {
        
        
        String iterasyonlar[][] = new String[16][3];
        iterasyonlar[0][0] = "Yer Değiştirme";
        iterasyonlar[0][1] = "Random Restart" ;
        iterasyonlar[0][2] = "İşlem Süresi";
        int ortChange = 0;
        int ortRandom = 0;
        double ortTime = 0;
        int[] cikti = new int[8];
        String ciktilar[] = new String[15];
        for (int i = 0; i<15; i++) {
            
            cikti = hillClimbing();
            
            String cozum = "";
            for(int z = 0 ; z<8; z++) {
                cozum += cikti[z];
            }
             ciktilar[i] = cozum;       
            double estimatedTime = endTime - startTime;
        
            
            ortChange += changeCount;
            ortRandom += randomRestartCount;
            ortTime += estimatedTime;
            
            iterasyonlar[i+1][0] = String.valueOf(changeCount);
            iterasyonlar[i+1][1] = String.valueOf(randomRestartCount);
            iterasyonlar[i+1][2] = String.valueOf(estimatedTime/1000000);
            
            changeCount = 0;
            randomRestartCount = 0;
        }
        System.out.println(iterasyonlar[0][0] + " | " + iterasyonlar[0][1] + " | " + iterasyonlar[0][2] +  " | Çözüm");
        for(int j = 1; j<16; j++) {
            
            
          
            System.out.println(iterasyonlar[j][0] + "                " + iterasyonlar[j][1] + "                " + iterasyonlar[j][2] +
                    "         " + ciktilar[j-1]);
            
        }
        
        ortTime = ortTime/1000000;
        System.out.println(String.format("%,.2f", (double)ortChange/15) + "             " + String.format("%,.2f", (double)ortRandom/15) +
                "              " + String.format("%,.2f", ortTime/15));
        
       
        for(int k = 0; k<8; k++) {
            System.out.print(b[k]);
        }
      
         
        
        
    }
    
}
