package com.suanfa.tree;

public class Tree {
	private Node	root;

	public Node getRoot() {
		return root;
	}

	// 构造树，构造的过程相当于将data中的数据插入
	public void construct(int[] data) {

		for (int i = 0; i < data.length; i++) {
			insert(data[i]);
		}
	}

	/**
	 * 插入节点
	 * 
	 * @param value
	 *            节点的值
	 */
	public void insert(int value) {
		Node pre = null;
		Node current = root;
		// 插入的过程：
		//      每次都从根节点开始查找
		//      如果值比根节点小，则插入的节点应该在根节点的左侧
		//      否则，应该在根节点的右侧
		while (current != null) {
			pre = current;
			// 如果根节点的值比value大，
			// 则新节点应该插入在根节点的左侧
			if (current.value > value) {
				current = current.left;
				//否则，应该插入在右侧
			} else {
				current = current.right;
			}
		}
		current = new Node(value, pre, null, null);
		// 如果pre是null，说明此时是空树
		if (pre == null) {
			root = current;
		} else {
			// 如果current的值比pre大，则current是pre的右节点
			if (current.value > pre.value) {
				pre.right = current;
				// 否则，current是pre的左节点
			} else {
				pre.left = current;
			}
		}
	}

	/**
	 * 中序遍历树，可以用于校验树是否成功创建
	 * 
	 * @param current
	 *            当前节点
	 */
	public void show(Node current) {
		if (current != null) {
			show(current.left);
			System.out.print(current.value + " ");
			show(current.right);
		}
	}

	// 获取树中的最小值
	public Integer getMin() {
		Node current = root;
		if (current == null) {
			return null;
		}
		// 寻找最左子树
		while (current.left != null) {
			current = current.left;
		}
		return current.value;
	}

	// 获取树中的最大值
	public Integer getMax() {
		Node current = root;
		if (current == null) {
			return null;
		}
		// 寻找最右子树
		while (current.right != null) {
			current = current.right;
		}
		return current.value;
	}

	// 查找包含某一值的节点
	public Node search(int value) {
		Node current = root;
		while (current != null) {
			// 如果该值比当前节点的值小，则
			// 找当前节点的左子树
			if (current.value > value) {
				current = current.left;
				// 如果该值比当前节点的值大，则
				// 找当前节点的右子树
			} else
				if (current.value < value) {
					current = current.right;
				} else {
					return current;
				}
		}
		// 找不到则返回null
		return null;
	}

	// 前驱
	public Node getPre(Node target) {
		if (target == null) {
			return null;
		}
		// 如果左子树非空，则前驱为左子树的最右子树
		if (target.left != null) {
			target = target.left;
			// 寻找最右子树
			while (target.right != null) {
				target = target.right;
			}
			return target;
		} else {
			// 否则，查找该节点是某个节点的右子树的最左子树的节点
			// 也就是沿着父亲路径往上走，第一个该节点不是其父亲节点的左节点的节点
			Node parent = target.parent;
			while (parent != null && target == parent.left) {
				target = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	// 后继
	public Node getSuc(Node target) {

		if (target == null) {
			return null;
		}
		// 查找右子树的最左子树
		if (target.right != null) {
			target = target.right;
			while (target.left != null) {
				target = target.left;
			}
			return target;
		} else {
			// 沿着父亲路径向上走，第一个该节点不是父亲节点的右子树的节点
			Node parent = target.parent;
			while (parent != null && target == parent.right) {
				target = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	// 删除节点
	public void remove(Node target) {
		if (target == null) {
			return;
		}

		// 只有右子树
		if (target.left == null) {
			// 如果target是其父亲的左子树
			if (target.parent.left == target) {
				// 将target的右孩子连接到父亲的左孩子，
				// 也就是target的右孩子替代父亲
				target.parent.left = target.right;
			} else {
				// 如果target是右孩子，则连接到parent的右孩子
				target.parent.right = target.right;
			}
			// 如果右孩子非空，右孩子的parent指向target.parent
			if (target.right != null) {
				target.right.parent = target.parent;
			}
			// 如果target的右子树为空，而且此时左子树不为空
			// 操作基本同上
		} else
			if (target.right == null) {
				if (target.parent.left == target) {
					target.parent.left = target.left;
				} else {
					target.parent.right = target.left;
				}
				target.left.parent = target.parent;
				// 如果左右子树都非空，则用右子树的最左子树进行替代
			} else {
				// 如果target的右子树没有左子树，直接拿右子树进行替代
				if (target.right.left == null) {
					if (target.parent.left == target) {
						target.parent.left = target.right;
					} else {
						target.parent.right = target.right;
					}
					// 指向target的parent
					target.right.parent = target.parent;
					// 接管target的左子树
					target.right.left = target.left;
				} else {
					// 寻找target的右子树的最左子树
					Node current = target;
					target = target.right;
					while (target.left != null) {
						target = target.left;
					}
					// 直接替换其值即可
					current.value = target.value;
					// 此时target为右子树的最左子树，但是target可能有右子树
					// 所以删除只有，target.parent.left需要接管target的右子树
					target.parent.left = target.right;
				}

			}
	}

	/**
	 * 树的节点
	 */
	private class Node {
		int		value;
		Node	parent;
		Node	left;
		Node	right;

		public Node(int value, Node parent, Node left, Node right) {
			this.value = value;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
}
