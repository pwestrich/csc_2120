
public abstract class BinaryTreeBasis
{
     /** The root of the tree */
   private TreeNode root;

     /**
      *   The only constructor <br>
      *
      *   Preconditions:      None <br>
      *   Postconditins:      Tree is initialized. <br>
      */
     
   public BinaryTreeBasis() 
   {
      root = null;
   }
     
      /**
       *  Determines if the tree is empty or not. <br>
       *
       *  Preconditins:       None. <br>
       *  Postconditins:      The tree is checked if it is empty. <br>
       *
       *  @return   boolean   If the tree is empty or not
       */

   public boolean isEmpty() 
   {
      return root == null;
   }
     
     /**
      *   Makes the tree empty.
      *
      *   Preconditions:      None. <br>
      *   Postconditins:      The tree is empty. <br>
      */

   public void makeEmpty() 
   {
      root = null;
   }
     
     /**
      *   Gets the root item of the tree <br>
      *
      *   Preconditins:       There is at least one item in the tree <br>
      *   Postconditins:      The root item is returned <br>
      *
      *   @throws   TreeException  if tree is empty
      *
      *   @return   Object    the root irem
      */

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
     
     /**
      *   Gets the root node of the tree. <br>
      *
      *   Preconditins:       None. <br>
      *   Postconditins:      The root item is returned <br>
      *
      *   @return   TreeNode  The root item
      *
      */

   protected TreeNode getRootNode()
   {
      return root;
   }
     
     /**
      *   Sets the root note to tNode <br>
      *
      *   Preconditions:      None. <br>
      *   Postconditins:      root = tNode
      *
      *   @param    tNode     Item to set root to
      */

   protected void setRootNode(TreeNode tNode)
   {
      root = tNode;
   }
     
     /**
      *   Returns the iterator for the tree. <br>
      *
      *   Preconditions:      None.
      *   Postconditins:      A new BinaryTreeIterator is created and returned
      *
      *   @return   TreeIterator   an iteratoor through the tree
      */

   public TreeIterator iterator()
   {
      return new BinaryTreeIterator(root);
   }
}  
