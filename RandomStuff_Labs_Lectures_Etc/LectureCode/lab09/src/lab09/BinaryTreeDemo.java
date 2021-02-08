package lab09;

/**
 * This class demonstrates how to call BinaryNode methods.
 * 
 * @author Erin Parker & Tina Ziemek
 * @version March 8, 2019
 */
public class BinaryTreeDemo {
	
	public static void main(String[] args) {

		BinaryNode<Integer> b3 = new BinaryNode<Integer>(9, new BinaryNode<Integer>(8),
				new BinaryNode<Integer>(29,
						new BinaryNode<Integer>(21, new BinaryNode<Integer>(4), new BinaryNode<Integer>(17)),
						new BinaryNode<Integer>(28)));
	//	BinaryNode.generateDotFile("src/lab09/Output", b3);
		
		
		
		BinaryNode<String> d = new BinaryNode<String>("d");
		BinaryNode<String> e = new BinaryNode<String>("e");
		BinaryNode<String> g = new BinaryNode<String>("g");
		BinaryNode<String> b = new BinaryNode<String>("b", d, e);
		BinaryNode<String> f = new BinaryNode<String>("f", null, g);
		BinaryNode<String> c = new BinaryNode<String>("c", null, f);
		BinaryNode<String> a = new BinaryNode<String>("a", b, c);
		//BinaryNode.generateDotFile("src/lab09/Output", a);
		
		//Implement your b tree.  What is the sequence of its preorder, postorder, inorder, and level-order traversals?
		//BinaryNode<String> b1 = new BinaryNode<String>();

		
		
		
		
		BinaryNode.generateDotFile("b.dot", b);

		System.out.println("Size: " + b.size());
		//System.out.println("Height:" + b.height());

		System.out.print("Preorder: ");
		b.printPreorder();
		System.out.println();

		System.out.print("Postorder: ");
		b.printPostorder();
		System.out.println();

		System.out.print("Inorder: ");
		b.printInorder();
		System.out.println();

		System.out.print("Level-order: ");
		b.printLevelorder();
		System.out.println();
		

		// What does this b2 tree look like? What is the sequence of its inorder
		// traversal?
		BinaryNode<String> a2 = a.duplicate();
		//a2.right().resetElement("5");

		
		BinaryNode.generateDotFile("src/lab09/Output", a2);

		System.out.print("\nNew expression tree: ");
		a2.printInorder();
		System.out.println();
		

		// What does this b3 tree look like? What is the sequence of its preorder,
		// postorder, and inorder traversals?
		

		/*
		 * 
		 * 
		BinaryNode.generateDotFile("b3.dot", b3);

		System.out.print("\nPreorder: ");
		b3.printPreorder();
		System.out.println();

		System.out.print("Postorder: ");
		b3.printPostorder();
		System.out.println();

		System.out.print("Inorder: ");
		b3.printInorder();
		System.out.println();
		*/
	}
}