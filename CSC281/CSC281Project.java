import java.util.*;
import java.lang.Math.*;
public class CSC281Project{
   public static void main(String[]args){
      Scanner input = new Scanner(System.in);
      int k = 0, n=0;
      do{
         System.out.print("Enter the number of times to test for primality: ");
         k = input.nextInt();
         if(k<=0)
            System.out.println("The number must be greater or equal to 1.");
      }while(k<=0);
      do{
         System.out.print("Enter a value to test for primality: ");
         n = input.nextInt();
         if(n<=0)
            System.out.println("The number must be greater or equal to 1.");
      }while(n<=0);
      
      System.out.println("\n====== METHOD 1 ======");
      int[] arr=new int[300];
      long startTime = System.nanoTime();
      boolean test=trailDivition(n);
      if(!test){
         System.out.println("Not prime factors are: ");
         arr=factor(n);
         for(int ar:arr){
            if(ar != 0)
               System.out.print(ar+" ");
         }
         System.out.println();
      }
      else
         System.out.println("Prime");
      long endTime = System.nanoTime();
      System.out.println("That took " + (endTime - startTime) + " nanoseconds");
      
      System.out.println("\n====== METHOD 2 ======");
      startTime = System.nanoTime();
      System.out.println("Result: " + FermatIsPrime(n, k));
      endTime = System.nanoTime();
      System.out.println("That took " + (endTime - startTime) + " nanoseconds");
      
      System.out.println("\n====== METHOD 3 ======");
      startTime = System.nanoTime();
      System.out.println("Result: " + millerRabinIsPrime(n, k));
      endTime = System.nanoTime();
      System.out.println("That took " + (endTime - startTime) + " nanoseconds");
   }
   
   public static int[] factor(int k){
      int j=0;
      int[] arr=new int[300];
      for(int i=1;i<=k;i++){
         if(k%i==0)
         arr[j++]=i;
      }
      return arr;
   }
   
   public static boolean trailDivition(int c){
      if(c==2)
         return true;
      int k=(int)Math.ceil(Math.sqrt(c));
      for(int i=2;i<=k;i++){
         if(c%i==0)
            return false;
      }
      return true;
   }
   
   static long PowerMod(int a, long n, long p) {
      long result = 1;    
      while (n>0) {
         result = (result * a) % p;
         n--;
      }
      return result;
   }
   public static String FermatIsPrime(long n, int k){   
      if(n==1 || (n%2==0&& n!=2))
         return"composite" ;
      if(n==2||n==3)
         return "probably prime";
      while(k>0){
         int a = (int)(Math.random()*(n -3))+2;
         if (PowerMod(a, n - 1, n) == 1)
            return "probably prime";
         k--;
      }
      return "composite";
   }

   public static String millerRabinIsPrime(long n, int k){
      //If n is even or 1
      if(n%2 == 0 && n!=2 || n==1)
         return "Composite";
      if(n==2 || n==3)
         return "Prime";
         
      //Find n-1 = d*2^r
      long d = n-1;
      long r = 0;
      while(d%2 == 0){
         d/=2;
         r++;
      }
      
      //Repeating it k times
      long min = 2, max = n-2;//1<a<n-1
      long a = (long)Math.floor(Math.random()*(max-min+1)+min);
      for(int i=0; i<k; i++){
         //To be able to compute large numbers
         long s=1;
         for(int m=0; m<d; m++){
            s = (s*a)%n;
         }
         
         if(s==1 || s==n-1){
            return "Probably prime";
         }      
         for(int j=0; j<r; j++){
            s = (long)Math.pow(s,2)%n;
            if(s == n-1)
               return "Probably prime";
            if(s == 1)
               return "Composite";
         }
      }
      return "Composite";
   }
}