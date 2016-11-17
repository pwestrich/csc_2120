import queue.*;

class BinaryTreeIterator implements TreeIterator
{
   private TreeNode root;  
   private QueueInterface queue;       

   public BinaryTreeIterator(TreeNode root) 
   {
      this.root = root;
      queue = new QueueLinked();
   }
     
     public void setLevelOrder(){
          
          if (root == null) return; //Don't do anything if root is null
          
          QueueInterface localQueue = new QueueLinked<TreeNode>(); //Local queue
          localQueue.enqueue(root); //Add root to queue to start with
          TreeNode currentItem = null; //Current item to be worked with in following loop
          queue.dequeueAll(); //Clear instance queue to start with
          
          while (!localQueue.isEmpty()){
               
               currentItem = localQueue.dequeue(); //Get current item
               queue.enqueue(currentItem.getItem()); //Add it to the queue
               
               if (currentItem.getLeft() != null){ //Add left subtree to local queue if not null
                    
                    localQueue.enqueue(currentItem.getLeft());
                    
               }
               
               if (currentItem.getRight != null){ //Add right subtree to local queue if not null
                    
                    localQueue.enqueue(currentItem.getRight());
                    
               }
               
          }
          
     }

   public boolean hasNext() 
   {
      return !queue.isEmpty();
   }  

   public Object next() throws TreeException
   {
      try 
      {
         return queue.dequeue();
      }  
      catch (QueueException e) 
      {
         throw new TreeException("End of tree traversal.");
      }  
   }  

   public void remove() throws UnsupportedOperationException 
   {
      throw new UnsupportedOperationException();
   }  

   public void setPreorder() 
   {
      queue.dequeueAll();
      preorder(root);
   } 

   public void setInorder() 
   {
      queue.dequeueAll();
      inorder(root);
   }  

   public void setPostorder() 
   {
      queue.dequeueAll();
      postorder(root);
   }   

   private void preorder(TreeNode tNode) 
   {
      if (tNode != null) 
      {  
         queue.enqueue(tNode.getItem());
         preorder(tNode.getLeft());
         preorder(tNode.getRight());
      } 
   } 

   private void inorder(TreeNode tNode) 
   {
      if (tNode != null) 
      {  
         inorder(tNode.getLeft());
         queue.enqueue(tNode.getItem());
         inorder(tNode.getRight());
      }
   }  

   private void postorder(TreeNode tNode) 
   {
      if (tNode != null) 
      {  
         postorder(tNode.getLeft());
         postorder(tNode.getRight());
         queue.enqueue(tNode.getItem());
      } 
   } 
}  