import java.util.Random;

public class RandomNumbers {
    
    // public void SimpleSimulate(int rolls){
    //   Random rand = new Random(); 
      
    //   int two=0;
    //   int twelves=0;

    //   for(int i =0; i<rolls; i++){
    //     int d1 = rand.nextInt(6) +1;
    //     int d2 = rand.nextInt(6) +1;
    //     if(d1+d2 ==2){
    //       two+=1;
    //     } else if(d1 +d2 == 12){
    //       twelves+=1;
    //     }
    //   }
    //   System.out.println("Numbers\t"+"Hakae\t"+"Average\t"+"\n"+"2's=\t" + two + "\t"+ 100.0 * two/rolls);
    //   System.out.println("Numbers\t"+"Hakae\t"+"Average\t"+"\n"+"12's=\t" + twelves + "\t"+ 100.0 * two/rolls);
    // }


    // Print through a loop
    public void Simulate(int rolls){
      Random rand = new Random(); 
      
      int [] count = new int[13];
     

      for(int i =0; i<rolls; i++){
        int d1 = rand.nextInt(6) +1;
        int d2 = rand.nextInt(6) +1;

        // for testing purposes to see if the simulate works as expected
        System.out.println("Roll is "+ d1 + "+" + d2+ "=" + (d1+d2));
        count[d1+d2] +=1;
      }

      for(int k =2; k<=12; k++){
        System.out.println(k + "'s =\t" +count[k] +"\t"+ 100.0 * count[k]/rolls);
        
      }
     
    }
    

}
