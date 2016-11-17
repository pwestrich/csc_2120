
import java.util.Iterator;

public abstract class BinaryTreeBasis
{
   private TreeNode root;

   public BinaryTreeBasis() 
   {
      root = null;
   }  

   public boolean isEmpty() 
   {
      return root == null;
   }  

   public void makeEmpty() 
   {
      root = null;
   }  

   public Object getRootItem() throws TreeException 
   {
      if (root == null) 
      {
         throw new TreeException("Empty tree");
      }
      else 
      {
         return root.getItem();
      } 
   }  

   protected TreeNode getRootNode()
   {
      return root;
   }

   protected void setRootNode(TreeNode tNode)
   {
      root = tNode;
   }
   
   public TreeIterator getTreeIterator()
   {
      return new BinaryTreeIterator(root);
   }
   
   public Iterator iterator(){
      
      TreeIterator temp = getTreeIterator();
      temp.setInorder();
      return temp;
      
   }
   
}  
