/**
 * Created by TKK on 2016-12-28.
 */
public class Node {
    String nodeName;
    Node leftChild;
    Node rightChild;
    Node parent;
    Boolean status;

    public Node(String a, Node lc, Node rc, Node par, boolean status) {
        nodeName = a;
        leftChild = lc;
        rightChild = rc;
        parent = par;
        status = false;
    }

    public void setParent(Node par) {
        parent = par;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Node getParent() {
        return parent;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Node(Node n) {
    }

    public Node newTop(char nameToCheck) {
        if (getLeftChild().getNodeName().toString().contains(Character.toString(nameToCheck))) {
            return leftChild;
        } else
            return rightChild;
    }

    public String leftOrRight(Node childToCheck) {
        if (getLeftChild().equals(childToCheck)) {
            return "0";
        } else
            return "1";
    }


    public boolean hasChildren(){
        //System.err.println(getLeftChild()!=null);
        return getLeftChild()!=null;
    }
}
