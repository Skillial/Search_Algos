public class BinaryTree {
    binaryTreeNode rootNode;

    public binaryTreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(binaryTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public void insertNode(String value) { // while loop copy pasted from a yt video, must change
        binaryTreeNode newNode = new binaryTreeNode(value);
        if (rootNode == null) {
            rootNode = newNode;
            rootNode.setDepth(0);
            return;
        }
        binaryTreeNode referenceNode = rootNode;
        binaryTreeNode tempParent;
        while (true) {
            tempParent = referenceNode;
            if (value.compareTo(referenceNode.value)==0)
            {
                referenceNode.cnt++;
                break;
            }
            else if (value.compareTo(referenceNode.value) < 0) {//this one
                referenceNode = referenceNode.leftChild;
                if (referenceNode == null) {
                    tempParent.leftChild = newNode;
                    break;
                }
            } else {
                referenceNode = referenceNode.rightChild;
                if (referenceNode == null) {
                    tempParent.rightChild = newNode;
                    break;
                }
            }
        }
        newNode.parent = tempParent;
        newNode.depth = newNode.parent.depth + 1; //++ doesnt work for some reason

        System.out.printf("Node %s added! Depth of %d. Child of %s.\n", value, newNode.depth, newNode.parent.value);
    }

    public binaryTreeNode searchNode(String value, binaryTreeNode startNode) {
        if (rootNode == null || startNode == null) return null;
        binaryTreeNode referenceNode = startNode;
        if (startNode == null) referenceNode = rootNode;
        if (referenceNode.value == value) return referenceNode;
        if (referenceNode.value.compareTo(value) < 0) return searchNode(value, referenceNode.getRightChild());//this one
        return searchNode(value, referenceNode.getLeftChild());
    }

    public void printTree() {   //modified from https://www.baeldung.com/java-print-binary-tree-diagram
        if (rootNode == null) {
            System.out.println("Tree has no nodes");
            return;
        }

        System.out.println("Top is left child, bottom is right child.");
        System.out.printf("%s", rootNode.value);
        StringBuilder line = new StringBuilder();
        String leftChildArrow;
        String rightChildArrow = "╚═══";
        if (rootNode.rightChild != null && rootNode.leftChild != null)
            leftChildArrow = "╠═══";
        else
            leftChildArrow = "╚═══";

        printTree(rootNode.leftChild, line, "", leftChildArrow);
        printTree(rootNode.rightChild, line, "", rightChildArrow);
        System.out.printf("%s\n", line);
    }

    public void printTree(binaryTreeNode currentNode, StringBuilder line, String tabs, String arrow) {
        if (currentNode == null) return;
        line.append("\n").append(tabs).append(arrow).append(currentNode.value).append(" "+currentNode.cnt);

        StringBuilder tabBuilder = new StringBuilder(tabs);
        if (currentNode.parent.rightChild != null && currentNode.parent.rightChild != currentNode)
            tabBuilder.append("║   ");
        else
            tabBuilder.append("\t");

        String leftChildArrow;
        String rightChildArrow = "╚═══";
        if (currentNode.rightChild != null && rootNode.leftChild != null)
            leftChildArrow = "╠═══";
        else
            leftChildArrow = "╚═══";

        printTree(currentNode.leftChild, line, tabBuilder.toString(), leftChildArrow);
        printTree(currentNode.rightChild, line, tabBuilder.toString(), rightChildArrow);

    }

    public BinaryTree(binaryTreeNode root) {
        rootNode = root;
        root.depth = 0;
    }
}

class binaryTreeNode {
    String value;
    int depth;
    int cnt=1;
    binaryTreeNode parent;
    binaryTreeNode leftChild;
    binaryTreeNode rightChild;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public binaryTreeNode getParent() {
        return parent;
    }

    public void setParent(binaryTreeNode parent) {
        this.parent = parent;
    }

    public binaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(binaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public binaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(binaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public binaryTreeNode(String value) {
        this.value = value;
    }

}