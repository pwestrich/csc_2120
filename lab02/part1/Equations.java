
import matrix.*;

/**
*    Lab #2, Part 1
*    for CSC 2121-001
*    Sept. 5, 2013
*    This program solves a matrix equation with the Matrix.jar package
*/

public class Equations{

     public static void main(String[] args){

          System.out.println("Lab 2, Part 1");
          System.out.println("Creating variables...");
          
          MatrixOperationsInterface leftSide = MatrixCreator.create(3, 3);
          MatrixOperationsInterface rightSide = MatrixCreator.create(3, 1);
          MatrixOperationsInterface answers;
          MatrixOperationsInterface invertedLeftSide;
          
          System.out.println("Seting elements...");
          
          //Set the coefficent matrix
          leftSide.setElement(1, 1, 1.6);
          leftSide.setElement(1, 2, 2.4);
          leftSide.setElement(1, 3, -3.7);
          leftSide.setElement(2, 1, 17.6);
          leftSide.setElement(2, 2, -5.6);
          leftSide.setElement(2, 3, 0.05);
          leftSide.setElement(3, 1, -2);
          leftSide.setElement(3, 2, 2);
          leftSide.setElement(3, 3, 25.3);
          
          //Set the RHS matrix
          rightSide.setElement(1, 1, -22.1);
          rightSide.setElement(2, 1, -4.35);
          rightSide.setElement(3, 1, 233.70);
          
          System.out.println("Solving matrix...");
          
          invertedLeftSide = leftSide.inverse();
          answers = invertedLeftSide.multiply(rightSide);
          
          System.out.println("Here is your answer:");
          System.out.println("X1 = " + answers.getElement(1, 1));
          System.out.println("X2 = " + answers.getElement(2, 1));
          System.out.println("X3 = " + answers.getElement(3, 1));
          
          System.out.println("Done!");
          
     }

}
