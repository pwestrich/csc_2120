import java.util.List;
import java.util.ArrayList;

public class Permutation
{
   private int r;
   private List<Integer> numbers;

   //pick r of them from 1 to n
   public Permutation(int r, int n)
   {
      this.r = r;

      numbers = new ArrayList<Integer>();
      for (int i = 1; i <= n; i++)
      {
         numbers.add(i);
      }
   }

   public int next()
   {
      if (r == 0) return -1;

      int rand = Random.getRandomNumberGenerator().randomInt(1, numbers.size());
      int value = numbers.get(rand - 1);
      numbers.remove(rand - 1);
      r--;

      return value;
   }
}
