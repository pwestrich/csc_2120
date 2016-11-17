
public class AdaptableBinarySearchTree extends BinarySearchTree {

     public AdaptableBinarySearchTree(){
          
          super();
          
     }
     
     public KeyedItem retrieve(Comparable searchKey) {
          
          try{
          
               return retrieveItemAdapt(getRootNode(), searchKey);
          
          }
          
          catch (TreeException it){
               
               return null;
               
          }
          
     }
     
     private TreeNode retrieveItemAdapt(TreeNode tNode, Comparable searchKey) throws TreeException{
          
          //Case where item is not in tree
          if (tNode == null){
               
               throw new TreeException("Item not found.");
               
          }
          
          //Compare key to item
          int compareInt = tNode.compareTo(searchKey);
          
          //Case where item < tnode
          if (compareInt < 0){
               
               return retrieveItemAdapt(tNode.getLeft(), searchKey);

          }
          
          //Case where item > tNode
          else if (compareInt > 0){
               
               return retrieveItemAdapt(tNode.getRight(), searchKey);

          }
          
          //Case where we found the item
          else {
               
               
               
          }
     
     }

}