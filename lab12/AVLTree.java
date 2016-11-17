
public final class AVLTree extends BinarySearchTree implements Execute
{
   //keep checking balancing rules?
   private boolean avlFlag;

   public AVLTree(){

      super();

   }
   
   public void execute(Command command){
      
      for (Object item : this){ //fix?
         
         command.execute((CD) item);
         
      }
      
   }

   /**
    * Inserts an item into the tree.
    *
    * @param item Item to be inserted. Must not be null
    */
   
   public void insert(KeyedItem item) throws TreeException{

      super.insert(item);
      avlFlag = false;
      assert isBalanced() : ("Unbalanced. KeyedItem\"" + item.getKey()
                             + "\" was inserted.");

   }
   
   /**
    * Deletes an item from the tree.
    *
    * @param sk Item to be deleted. Must not be null
    */

   public void delete(Comparable sk) throws TreeException{

      super.delete(sk);
      avlFlag = false;
      assert isBalanced() : ("Unbalanced. KeyedItem\""
                             + sk + "\" was deleted.");

   }
   
   /**
    * Creates a node to be inserted into the tree.
    *
    * @param   item     Item to be put in the node.
    *
    * @return  TreeNode Node with item in it.
    */
   
   protected TreeNode createNode(KeyedItem item){

    AVLTreeNode tNode = new AVLTreeNode(item);
    avlFlag = true;
    return tNode;

    }
   
   /**
    * Inserts an item into the left of the tree.
    *
    * @param   tNode    Node to be inderted
    * @param   item     Key of item to be inserted
    *
    * @return  TreeNode ???
    */
    
   protected TreeNode insertLeft(TreeNode tNode, KeyedItem item){

      super.insertLeft(tNode, item);

      if (avlFlag)  //still need to check
         tNode = avlFixAddLeft((AVLTreeNode)tNode);  //came from the left

      return tNode;

   }
   
   /**
    * Inserts an item into the right of the tree.
    *
    * @param   tNode    Node to be inderted
    * @param   item     Key of item to be inserted
    *
    * @return  TreeNode ???
    */

   protected TreeNode insertRight(TreeNode tNode, KeyedItem item){

      super.insertRight(tNode,item);

      if (avlFlag)
         tNode = avlFixAddRight((AVLTreeNode)tNode);  //came from the right

      return tNode;

   }

   //determine if the delete is an easy case or a hard case
   protected TreeNode deleteNode(TreeNode tNode){

      KeyedItem replacementItem;

      // test for a leaf --  this test is taken care of by the next two
      if ((tNode.getLeft() == null) && (tNode.getRight() == null)) {

         return null;

      }

      // test for no left child
      else if (tNode.getLeft() == null){

         return tNode.getRight();

      }

      // test for no right child
      else if (tNode.getRight() == null){

         return tNode.getLeft();

      }  

      // there are two children:
      // retrieve and delete the inorder successor
      else{

         replacementItem = findLeftmost(tNode.getRight());
         tNode.setItem(replacementItem);
         tNode.setRight(deleteLeftmost(tNode.getRight()));

         if (avlFlag){

            //inorder successor: take a right, then keep going left
            tNode = avlFixDeleteRight((AVLTreeNode)tNode);  //came from right

         }

         return tNode;

      }

   } 

   //find the inorder successor
   //this method is overridden to allow checking for balancing
   protected TreeNode deleteLeftmost(TreeNode tNode){

      if (tNode.getLeft() == null){

         avlFlag = true;
         return tNode.getRight();

      }

      else {

         tNode.setLeft(deleteLeftmost(tNode.getLeft()));

         if (avlFlag){

            tNode = avlFixDeleteLeft((AVLTreeNode)tNode);  //keep going left

         }

         return tNode;

      }

   }
   
   /**
    * Performs a double-right-left rotation on the two given nodes
    *
    * @param   tNode       Root of tree to be rotated
    * @param   right       Its right node
    *
    * @return  AVLTreeNode Root of rotated tree
    */

   protected AVLTreeNode DRL(AVLTreeNode tNode, AVLTreeNode right){

       AVLTreeNode temp;
       AVLTreeNode rightLeft = right.getLeft();
       AVL bF = rightLeft.getBalanceFactor();
       tNode.setBalanceFactor(AVL.BALANCED);
       right.setBalanceFactor(AVL.BALANCED);

       if (bF == AVL.RIGHT)
          tNode.setBalanceFactor(AVL.LEFT);
      
       else if (bF == AVL.LEFT)
          right.setBalanceFactor(AVL.RIGHT);

       rightLeft.setBalanceFactor(AVL.BALANCED);

       temp = (AVLTreeNode) super.rotateRight(right);
       tNode.setRight(temp);
       temp = (AVLTreeNode) super.rotateLeft(tNode);
       System.out.println("DRL");
       return temp;

    }
   
   /**
    * Performs a double-left-right rotation on the two given nodes
    *
    * @param   tNode       Root of tree to be rotated
    * @param   left        Its left node
    *
    * @return  AVLTreeNode Root of rotated tree
    */
   
   protected AVLTreeNode DLR(AVLTreeNode tNode, AVLTreeNode left){

      AVLTreeNode temp;
       AVLTreeNode leftRight = left.getRight();
       AVL bF = leftRight.getBalanceFactor();
       tNode.setBalanceFactor(AVL.BALANCED);
       left.setBalanceFactor(AVL.BALANCED);

       if (bF == AVL.RIGHT)
          left.setBalanceFactor(AVL.LEFT);
      
       else if (bF == AVL.LEFT)
          tNode.setBalanceFactor(AVL.RIGHT);

       leftRight.setBalanceFactor(AVL.BALANCED);

       temp = (AVLTreeNode) super.rotateLeft(left);
       tNode.setLeft(temp);
       temp = (AVLTreeNode) super.rotateRight(tNode);  //don't want to set parent links just yet (done in insert method)
       System.out.println("DLR");
       return temp;

       }
   
   /**
    * Rotates the given node left.
    *
    * @param   tNode       Node to be rotated
    * 
    * @return  AVLTreeNode Root of rotated tree
    */
       
   protected AVLTreeNode rotateLeft(AVLTreeNode tNode){

      tNode.setBalanceFactor(AVL.BALANCED);
      
      AVLTreeNode right = tNode.getRight();
      right.setBalanceFactor(AVL.BALANCED);
      
      System.out.println("SL");

      return (AVLTreeNode) super.rotateLeft(tNode);

    }
   
   /**
    * Rotates the given node right.
    *
    * @param   tNode       Node to be rotated
    *
    * @return  AVLTreeNode Root of rotated tree
    */
   
   protected AVLTreeNode rotateRight(AVLTreeNode tNode){

      tNode.setBalanceFactor(AVL.BALANCED);
      
      AVLTreeNode left = tNode.getLeft();
      left.setBalanceFactor(AVL.BALANCED);
      
      System.out.println("SR");
      
      return (AVLTreeNode) super.rotateRight(tNode);

    }
   
   /**
    * Checks for a rotation, and calls for it to be performed if needed. <br>
    * Only checks the left, for adding nodes only. <br>
    *
    * @param   tNode       Root of tree to be checked
    *
    * @return  AVLTreeNode Root of fixed tree.
    */
   
   protected AVLTreeNode avlFixAddLeft(AVLTreeNode tNode){

      //first must update the balance factors
      tNode.insertLeft();
      AVL factor = tNode.getBalanceFactor();

      if (factor == AVL.BALANCED){  //change to balanced, stop

         avlFlag = false; //no more to do this time around

      }

      //find the rotation to perform, change balance factors, rotate, stop
      else if(factor == AVL.LEFT_HEAVY){

         AVLTreeNode left = tNode.getLeft();

         //double rotate left then right
         if (tNode.getLeft().getBalanceFactor() == AVL.RIGHT){

            tNode = DLR(tNode, left);

         }

         //single rotate right
         else {

            tNode = rotateRight(tNode);

         }

         avlFlag = false;

      }

      return tNode;  //return the node for this position, linked in recursively

   }
   
   /**
    * Checks for a rotation, and calls for it to be performed if needed. <br>
    * Only checks the right, for adding nodes only. <br>
    *
    * @param   tNode       Root of tree to be checked
    *
    * @return  AVLTreeNode Root of fixed tree.
    */
  
   protected AVLTreeNode avlFixAddRight(AVLTreeNode tNode){

      tNode.insertRight();
      AVL factor = tNode.getBalanceFactor();

      if (factor == AVL.BALANCED){  //change to balanced, stop

         avlFlag = false;

      }

      //find the rotation to perform, change balance factors, rotate, stop
      else if(factor == AVL.RIGHT_HEAVY) {

         AVLTreeNode right = tNode.getRight();
         
         //double rotate right then left
         if (right.getBalanceFactor() == AVL.LEFT){

            tNode = DRL(tNode, right);

         }

         //single rotate left
        else {

           tNode = rotateLeft(tNode);
        }

         avlFlag = false; //basically, stop checking

      }

      return tNode;

   }
   
   /**
    * Checks for a rotation, and calls for it to be performed if needed. <br>
    * Only checks the right, for deleting nodes only. <br>
    *
    * @param   tNode       Root of tree to be checked
    *
    * @return  AVLTreeNode Root of fixed tree.
    */

   protected AVLTreeNode avlFixDeleteRight(AVLTreeNode tNode){

      AVLTreeNode temp;
      AVL factor = tNode.getBalanceFactor();
      tNode.deleteRight();
               
      if (factor == AVL.BALANCED){ //change from zero--  STOP
                  
      avlFlag = false; //no more to do this time around
      return tNode;
         
      }
      
      else if (factor == AVL.LEFT || factor == AVL.RIGHT || factor == AVL.BALANCED){
         
         return tNode;  //need to keep checking, but no rotations necessary as yet
         
      }
      
      //rotations necessary for deleting a node
      AVLTreeNode right = tNode.getRight();
      AVLTreeNode left = tNode.getLeft();
      
      if (factor == AVL.LEFT_HEAVY
          && left.getBalanceFactor() == AVL.BALANCED){
         
         temp = rotateRight(tNode);
         avlFlag = false;  //STOP
         System.out.println("SR0");
         
      }
      
      else if (factor == AVL.RIGHT_HEAVY
               && right.getBalanceFactor() == AVL.BALANCED){
         
         temp = rotateLeft(tNode);
         avlFlag = false;  //STOP
         System.out.println("SL0");
         
      }
      
      else if (factor == AVL.LEFT_HEAVY
               && left.getBalanceFactor() == AVL.LEFT){
         
         temp = rotateRight(tNode);
         System.out.println("SR");
         
      }
      
      else if (factor == AVL.RIGHT_HEAVY
               && right.getBalanceFactor() == AVL.RIGHT){
         
         temp = rotateLeft(tNode);
         System.out.println("SL");
         
      }
      
      else if (factor == AVL.LEFT_HEAVY
               && left.getBalanceFactor() == AVL.RIGHT){
         
         AVLTreeNode leftRight = left.getRight();
         AVL bF = leftRight.getBalanceFactor();
         
         if (bF == AVL.BALANCED){
            
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.BALANCED);
            
         }
         
         else if (bF == AVL.RIGHT){
            
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.LEFT);
            
         }
         
         else{
            
            tNode.setBalanceFactor(AVL.RIGHT);
            left.setBalanceFactor(AVL.BALANCED);
            
         }
         
         leftRight.setBalanceFactor(AVL.BALANCED);
         temp = DLR(tNode, left);
         System.out.println("DLR");
         
      }
      
      else{
         
         AVLTreeNode rightLeft = right.getLeft();
         AVL bF = rightLeft.getBalanceFactor();
         
         if (bF == AVL.BALANCED){
            
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.BALANCED);
            
         }
         
         else if (bF == AVL.RIGHT){
            
            tNode.setBalanceFactor(AVL.LEFT);
            right.setBalanceFactor(AVL.BALANCED);
            
         }
         
         else{
            
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.RIGHT);
            
         }
         
         rightLeft.setBalanceFactor(AVL.BALANCED);
         temp = DRL(tNode, right);
         System.out.println("DRL");
         
      }
      
      return temp;
               
   }
   
   /**
    * Checks for a rotation, and calls for it to be performed if needed. <br>
    * Only checks the left, for deleting nodes only. <br>
    *
    * @param   tNode       Root of tree to be checked
    *
    * @return  AVLTreeNode Root of fixed tree.
    */

   protected AVLTreeNode avlFixDeleteLeft(AVLTreeNode tNode){

      AVL factor;
      AVLTreeNode temp;
      
      factor = tNode.getBalanceFactor();
      tNode.deleteLeft();
               
      if (factor == AVL.BALANCED){  //change from zero--  STOP
                  
         avlFlag = false; //no more to do this time around
         return tNode;
                  
      }
      
      else if (factor == AVL.LEFT || factor == AVL.RIGHT || factor == AVL.BALANCED){
         
         return tNode;  //need to keep checking, but no rotations necessary as yet
         
      }
      
      //rotations necessary for deleting a node
      AVLTreeNode right = tNode.getRight();
      AVLTreeNode left = tNode.getLeft();
      
      if (factor == AVL.LEFT_HEAVY
          && left.getBalanceFactor() == AVL.BALANCED){
         
         temp = rotateRight(tNode);
         avlFlag = false;  //STOP
         System.out.println("SR0");
         
      }
      
      else if (factor == AVL.RIGHT_HEAVY
               && right.getBalanceFactor() == AVL.BALANCED){
         
         temp = rotateLeft(tNode);
         avlFlag = false;  //STOP
         System.out.println("SL0");
         
      }
      
      else if (factor == AVL.LEFT_HEAVY
               && left.getBalanceFactor() == AVL.LEFT){
         
         temp = rotateRight(tNode);
         System.out.println("SR");
         
      }
      
      else if (factor == AVL.RIGHT_HEAVY
               && right.getBalanceFactor() == AVL.RIGHT){
         
         temp = rotateLeft(tNode);
         System.out.println("SL");
         
      }
      
      else if (factor == AVL.LEFT_HEAVY
               && left.getBalanceFactor() == AVL.RIGHT){
         
         AVLTreeNode leftRight = left.getRight();
         AVL bF = leftRight.getBalanceFactor();
         
         if (bF == AVL.BALANCED){
            
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.BALANCED);
            
         }
         
         else if (bF == AVL.RIGHT){
            
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.LEFT);
            
         }
         
         else{
            
            tNode.setBalanceFactor(AVL.RIGHT);
            left.setBalanceFactor(AVL.BALANCED);
            
         }
         
         leftRight.setBalanceFactor(AVL.BALANCED);
         
         temp = DLR(tNode, left);
         System.out.println("DLR");
         
      }
      
      else{
         
         AVLTreeNode rightLeft = right.getLeft();
         AVL bF = rightLeft.getBalanceFactor();
         
         if (bF == AVL.BALANCED){
            
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.BALANCED);
            
         }
         
         else if (bF == AVL.RIGHT){
            
            tNode.setBalanceFactor(AVL.LEFT);
            right.setBalanceFactor(AVL.BALANCED);
            
         }
         
         else{
            
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.RIGHT);
            
         }
         
         rightLeft.setBalanceFactor(AVL.BALANCED);
         
         temp = DRL(tNode, right);
         System.out.println("DRL");
         
      }
      
      return temp;
      
   }

}  
