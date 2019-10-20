package Project02;


/**
 * @author ：Brandon
 * @date ：Created in 2019/9/29 16:44
 * @description：TODO
 * @modified By：
 * @version: 2.0$
 */
public class LinkedString implements LinkedList{
    /**
     * The head of characters of this doubly linked string
     */
    private Project02.Node head;

    /**
     * The end of characters of this doubly linked string
     */
    private Project02.Node end;

    /**
     * The number of characters of this linked string
     */
    private int count;


//初始化

    /**
     * Constructs an empty linked string.
     */
    public LinkedString() {
        head = null;
        end = null;
        count = 0;
    }

    /**
     * Constructs a linked string with a list of characters.
     *
     * @param characters The list of characters of this string
     */
    public LinkedString(char[] characters) {
        for (int i = 0; i < characters.length; i++) {
            char c = characters[i];
            this.insert(c);
        }
    }

    /**
     * Constructs a linked string with a string.
     *
     * @param string A string used to create this linked string
     */
    public LinkedString(String string) {
        insert(string);
    }

//增

    /**
     * Put an elememt at the end of the list
     *
     * @param element the element need to be inserted
     */
    public void insert(Object element) {
        Project02.Node insertNode = new Project02.Node(element);
        if (count == 0) {
            head = insertNode;
            end = head;
            count++;
        } else {
            end.setNext(insertNode);
            insertNode.setPre(end);
            end = insertNode;
            count++;
        }
    }

    /**
     * Put a node at the position of the list
     *
     * @param position the position you want to insert
     * @param element  the element need to be inserted
     */
    public void insert(int position, Object element) {
        Node node = getNode(position);
        Node insertNode = new Node(element);
        insertNode.setNext(node);
        insertNode.setPre(node.getPre());
        node.setPre(insertNode);
        node.getPre().setNext(insertNode);
        count++;
    }

    /**
     * Put a node after the position of the list
     *
     * @param position the position you want to insert
     * @param element  the element need to be inserted
     */
    public void insertAfter(int position, Object element) {
        insert(position + 1, element);
    }

    /**
     * Put a node before the position of the list
     *
     * @param position the position you want to insert
     * @param element  the element need to be inserted
     */
    public void insertBefore(int position, Object element) {
        insert(position, element);
    }

    /**
     * concatenate the specified linked character string to the end of this linked character string
     *
     * @param linkedString the list need to be connected
     */
    public void concat(LinkedString linkedString) {
        Node insertNode = linkedString.getNode(0);
        end.setNext(insertNode);
        insertNode.setPre(end);
        end = linkedString.getNode(getCount());
    }

//查

    /**
     * find the node in the double linked list
     *
     * @param indexOf the node in this position you want to get it
     * @return Node the node you want to
     */
    public Node getNode(int indexOf) {
        isOutOfBound(indexOf);
        if (indexOf < (count / 2)) {
            Node getNode = head;
            for (int i = 0; i < count && i != indexOf; i++) {
                getNode = getNode.getNext();
            }
            return getNode;
        }
        if (indexOf >= (count / 2)) {
            Node getNode = end;
            for (int i = (getCount() - 1); i > 0 && i != indexOf; i--) {
                getNode = getNode.getPre();
//                if (count==19){
//                    System.out.println(1);
//                }
            }
            return getNode;
        }
        return null;
    }

    /**
     * Gets the previous location of the specified content node
     *
     * @param element
     * @return the previous location of the specified content node
     */
    public int getPosition(Object element) {
        Node node = head;
        int position = 0;
        while (!(node.overwriteEquals(element))) {
            node = node.getNext();
            position++;
            if (position == getCount() - 1) {
                return -1;
            }
        }
        return position;
    }

    /**
     * Gets the previous node at the specified location
     *
     * @param indexOf
     * @return the previous node at the specified location
     */
    public Node getPreNode(int indexOf) {
        return getNode(indexOf - 1);
    }

    /**
     * get the length of the double linked list
     *
     * @return the length of the double linked list
     */
    public int getCount() {
        return count;
    }

    /**
     * get the length of the double linked list
     *
     * @return the length of the double linked list
     */
    public int length() {
        return count;
    }



    /**
     * return a new linked character string that is a substring of this linked character string
     *
     * @param start
     * @param end
     * @return a new linked character string that is a substring of this linked character string
     */
    public String substring(int start, int end) {
        String string = new String();
        for (int i = start-1; i < end; i++) {
            string = string + (String) this.getNode(i).getElement();
        }
        return null;
    }

//    public boolean contains(Object element) {
//        Node node = new Node(element);
//        if (getPosition(node) ==-1) {
//            return false;
//        }
//        return true;
//    }

    /**
     * to find out whether the position you input is out of the list
     *
     * @param indexOf the position you want to find out whether
     */
    private void isOutOfBound(int indexOf) {
        if (indexOf < 0 || indexOf > count) {
            throw new IndexOutOfBoundsException("Error, out of linked list scope");
        }
    }


//改

    /**
     * change the specific position's element
     *
     * @param position the position you want to change
     * @param element  the element you want to replace the original element
     */
    public void changeElement(int position, Object element) {
        getNode(position).setElement(element);
    }

    //删

    /**
     * Deletes the node at the specified position
     *
     * @param position
     */
    public void deleteNode(int position) {
        isOutOfBound(position);
        Node node = getNode(position);
        node.getPre().setNext(node.getNext());
        node.getNext().setPre(node.getPre());
        node = null;
        count--;

        //deleteNode(getNode(position));
    }

    /**
     * Deletes the node for the specified content
     *
     * @param element
     */
    public void deleteNode(Object element) {
        deleteNode(getPosition(element));
    }
}
