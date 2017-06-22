package edu.umd.cs.datastructures.davis;

/**
 *
 * @author aakash
 *
 * @param <
 *            T >
 */
public class Node<T> {

	private T data;
	private Node<T> left = null;
	private Node<T> right = null;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> l) {
		this.left = l;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> r) {
		this.right = r;
	}

	public Node(T data) {
		this.data = data;
	}

	/**
	 * @param data
	 * @param lt
	 * @param rt
	 */
	public Node(T data, Node<T> lt, Node<T> rt) {
		super();
		this.data = data;
		this.left = lt;
		this.right = rt;
	}

	@Override
	public String toString() {
		StringBuilder sbr = new StringBuilder();
		sbr.append("[");
		sbr.append("{data:").append(data.toString());
		// sbr.append(" tag:").append(tag);
		sbr.append("}");
		if (left != null)
			sbr.append(",{left:").append(left.getData()).append("}");
		else
			sbr.append(",{left:").append(left).append("}");
		if (right != null)
			sbr.append(",{right:").append(right.getData()).append("}");
		else
			sbr.append(",{right:").append(right).append("}");
		// @formatter:off -- used in conjunction with Eclipse formatter settings
//		if (lt != null)
//			sbr.append(",{left:").append(lt.toString()).append("}");
//		else
//			sbr.append(",{left:").append(lt).append("}");
//		if (rt != null)
//			sbr.append(",{right:").append(rt.toString()).append("}");
//		else
//			sbr.append(",{right:").append(rt).append("}");
		// @formatter:on
		sbr.append("]");
		return sbr.toString();
	}

	public static void main(String args[]) {
		System.out.println(new Node<Integer>(new Integer(10)).toString());
	}
}
