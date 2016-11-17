
public class BinarySearchTree extends BinaryTreeBasis implements SearchTreeInterface {

     /** Number of items in the tree */
     private int numItems;
   
     /**
      *   The only constructor.
      *
      *   Preconditions: None.
      *   Postconditions: The object is initialized.
      */
     
   public BinarySearchTree() {
      
        super();
        numItems = 0;
        
   }

     /**
      *   Finds an item in the tree, and returns it.
      *
      *   @param    searchKey a Comparable item to be searched for.
      *
      *   @return   KeyedItem the item found, or null if not found
      */
     
     public KeyedItem retrieve(Comparable searchKey) {
        
          return retrieveItem(getRootNode(), searchKey);
        
     }
     
     //no
     public void insert(KeyedItem item) throws TreeException {
        
          setRootNode(insertItem(getRootNode(), item));
          numItems++;
          checkInvariants();
        
     }
     
     //no
     public void delete(Comparable searchKey) throws TreeException {
        
          setRootNode(deleteItem(getRootNode(), searchKey));
          numItems--;
          checkInvariants();
        
     }
     
     //no
     public int size(){
          
          return numItems;
          
     }
     
     /**
      *   Checks the invariants of the BST.
      *   Assertions must be enabled.
      */
     
     private void checkInvariants(){
          
          //assert validateBSTProperty()  : "Tree not as BST!";
          assert validateSize()         : "Size is incorrect!";
          //assert isBalanced()           : "Tree is not balanced!";
          
     }
     
     /**
      *   Validates that the tree is a BST.
      *
      *   @return boolean     if the tree is a BST or not.
      */
     
     private boolean validateBSTProperty(){
          
          TreeIterator iterator = iterator();
          iterator.setInorder();
          
          KeyedItem firstItem = (KeyedItem) iterator.next();
          KeyedItem secondItem = null;
          
          while (iterator.hasNext()){
               
               secondItem = (KeyedItem) iterator.next();
               
               if (firstItem.getKey().compareTo(secondItem.getKey()) != -1){
                    
                    return false;
                    
               }
               
               else {
                    
                    firstItem = secondItem;
                    secondItem = (KeyedItem) iterator.next();
                    
               }
               
          }
          
          return true;
          
     }
     
     /**
      *   Checks if the actual number of items in the tree is the same
      *   as the counted number when inserting and/or deleting.
      *
      *   @return   boolean   whether or not the size is correct
      */
     
     private boolean validateSize(){
          
          int size = size();
          int computedSize = computeSize(getRootNode());
          boolean isCorrect = (size == computedSize);
          
          if ((size < 0) || (computedSize < 0)){
               
               return false;
               
          }
          
          else return isCorrect;
          
     }
     
     /**
      *   Computes the size of the tree
      *
      *   @return   int  the size of the tree
      */
     
     private int computeSize(TreeNode tNode){
          
          TreeIterator iterator = iterator();
          iterator.setInorder();
          int count = 0;
          
          while (iterator.hasNext()){
               
               count++;
               iterator.next();
               
          }
          
          return count;
          
     }
     
     /**
      *   Checks to see if the tree is balanced.
      *
      *   @return   boolean   if the tree is balanced or not.
      */
     
     private boolean isBalanced(){
          
          TreeNode root = getRootNode();
          
          int leftHeight = getHeight(root.getLeft());
          int rightHeight = getHeight(root.getRight());
          int difference = 0;
          
          if (leftHeight > rightHeight){
               
               difference = leftHeight - rightHeight;
               
          }
          
          else {
               
               difference = rightHeight - leftHeight;
               
          }
          
          if (difference > 1) {
               
               return false;
               
          }
          
          else return true;
          
     }
     
     /**
      *   Calculates the height of the tree.
      *
      *   @return   int  the height of the tree.
      */
     
     public int height(){
          
          return getHeight(getRootNode());
     
     }
     
     /**
      *   Recursivley calculates the height of the tree.
      *
      *   @param    tNode     Tree to be calculated
      *
      *   @return   int       The height of the given tree
      */
     
     private int getHeight(TreeNode tNode){
          
          if (tNode == null) {
               
               return 0;
          
          }
          
          int height = 0;
          int leftHeight =  1 + getHeight(tNode.getLeft());
          int rightHeight = 1 + getHeight(tNode.getRight());
          
          if (leftHeight >= rightHeight) {
               
               height = leftHeight;
               
          }
          
          else {
               
               height = rightHeight;
               
          }
          
          return height;
          
     }

     /**
      *   Recursive function to retrieve items/
      *
      *   @param    tNode     Node to search
      *   @param    searchKey Key to search by
      *
      *   @return   KeyedItem The item found, or null if not
      */
     
   protected KeyedItem retrieveItem(TreeNode tNode, Comparable searchKey)
   {
      //disallow duplicates so that you always know which object to retrieve (or delete)
      //you could return a list with all duplicate search keys (but delete is still a problem)
      KeyedItem treeItem;

      if (tNode == null) 
      {
         treeItem = null;
      }
      else 
      {
         KeyedItem nodeItem = (KeyedItem) tNode.getItem();
         int comparison = searchKey.compareTo(nodeItem.getKey());

         if (comparison == 0) 
         {
            // item is in the root of some subtree
            treeItem = nodeItem;
         }
         else if (comparison < 0) 
         {
            // search the left subtree
            treeItem = retrieveItem(tNode.getLeft(), searchKey);
         }
         else  
         { 
            // search the right subtree
            treeItem = retrieveItem(tNode.getRight(), searchKey);
         }  
      }
        
      return treeItem;
   }
     
     /**
      *   Recursive function to insert an item.
      *
      *   @param    tNode     Node to be searched
      *   @param    item      Item to be inserted
      *
      *   @return   TreeNode  ???
      */

   protected TreeNode insertItem(TreeNode tNode, KeyedItem item) throws TreeException
   {

      if (tNode == null) 
      {
         // position of insertion found; insert after leaf
         // create a new node
         tNode = new TreeNode(item);
         return tNode;
      }  

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = item.getKey().compareTo(nodeItem.getKey());

      // search for the insertion position
      if (comparison == 0)
      {
         throw new TreeException ("Cannot add duplicate.");
      }
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = insertItem(tNode.getLeft(), item);
         tNode.setLeft(subtree);
      }
      else 
      { 
         // search the right subtree
         subtree = insertItem(tNode.getRight(), item);
         tNode.setRight(subtree);
      }  

      return tNode;
   }
     
     /**
      *   Recursive function to delete an item
      *
      *   @param    tNode          Node to be searched
      *   @param    searchKey      Key to search with
      *
      *   @throws   TreeException  if the item is not found
      *
      *   @return   TreeNode       ???
      */

   protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) throws TreeException
   {

      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = deleteItem(tNode.getLeft(), searchKey);
         tNode.setLeft(subtree);
      }
      else 
      { 
         // search the right subtree
         subtree = deleteItem(tNode.getRight(), searchKey);
         tNode.setRight(subtree);
      } 

      return tNode;
   }  

   protected TreeNode deleteNode(TreeNode tNode) 
   {
      // Algorithm note: There are four cases to consider:
      //   1. The tNode is a leaf.
      //   2. The tNode has no left child.
      //   3. The tNode has no right child.
      //   4. The tNode has two children.
      // Calls: findLeftmost and deleteLeftmost

      // test for a leaf --  this test is taken care of by the next two
      if ((tNode.getLeft() == null) && (tNode.getRight() == null)) 
      {
         return null;
      }  

      // test for no left child
      else if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      } 

      // test for no right child
      else if (tNode.getRight() == null) 
      {
         return tNode.getLeft();
      } 

      // there are two children:
      // retrieve and delete the inorder successor
      else 
      {
         KeyedItem replacementItem = findLeftmost(tNode.getRight());
         tNode.setItem(replacementItem);
         TreeNode subtree = deleteLeftmost(tNode.getRight());
         tNode.setRight(subtree);
         return tNode;
      } 
   }  

     /**
      *   Finds the leftmost item in the given tree
      *
      *   @param    tNode     Tree to be searched
      *
      *   @return   KeyedItem The leftmost item in the tree
      */
     
   protected KeyedItem findLeftmost(TreeNode tNode)  
   {
      if (tNode.getLeft() == null) 
      {
         return (KeyedItem)tNode.getItem();
      }
      else 
      {
         return findLeftmost(tNode.getLeft());
      }  
   } 

     /**
      *   Deletes the leftmost item in the tree.
      *
      *   @param    tNode     Tree to be searched
      *
      *   @return   TreeNode  ???
      */
     
   protected TreeNode deleteLeftmost(TreeNode tNode) 
   {
      if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      }
      else 
      {
         TreeNode subtree = deleteLeftmost(tNode.getLeft());
         tNode.setLeft(subtree);
         return tNode;
      }  
   }
     
     /**
      *   Rotates the tree to the left.
      *
      *   @param    tNode     Node to be rotated about
      *
      *   @return   TreeNode  ???
      */

   protected TreeNode rotateLeft(TreeNode tNode)
   {
      TreeNode right = tNode.getRight();
      TreeNode rightleft = right.getLeft();

      tNode.setRight(rightleft);
      right.setLeft(tNode);

      return right;
   }
     
     /**
      *   Rotates the tree to the right/
      *
      *   @param    tNode     Node to be rotataed about
      *
      *   @return   TreeNode  ???
      */
     

   protected TreeNode rotateRight(TreeNode tNode)
   {
      TreeNode left = tNode.getLeft();
      TreeNode leftright = left.getRight();

      tNode.setLeft(leftright);
      left.setRight(tNode);

      return left;
   }

}
