
public class BinarySearchTree <K extends Comparable,T> implements TDABinarySearchTree<K,T>{

    private BinaryNode<K, T> root;
    private BinaryNode<K, T> actual;
    Boolean aux;

    /**
     * Recupera el objeto con clave k.
     *
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    @Override
    public BinaryNode retrieve(K k) {
        return retrieve(root, k);
    }

    private BinaryNode retrieve(BinaryNode node, K key){
        if(node.getKey().compareTo(key) > 0){
            if(node.hasLeft())
                return retrieve(node.getLeft(), key);
            else {
                return null;
            }
        }else if(node.getKey().compareTo(key) == 0){
            return (BinaryNode) node;
        }else{
            if(node.hasRight())
                return retrieve(node.getRight(), key);
            else {
                return null;
            }
        }
    }

    /**
     * Inserta un nuevo elemento al árbol.
     *
     * @param e el elemento a ingresar.
     * @param k la clave del elemento a ingresar.
     */
    @Override
    public void insert(T e, K k) {
        if(root == null){
            root = new BinaryNode<>(e, k, null);
            return;
        }
        insert(e, k, root);
    }

    private void insert(T e, K key, BinaryNode node){
        if(node.getKey().compareTo(key) >= 0){
            if(node.hasLeft()){
                insert(e, key, node.getLeft());
            }else{
                node.setLeft(new BinaryNode<>(e, key, node));
            }
        }else{
            if(node.hasRight()){
                insert(e, key, node.getRight());
            }else{
                node.setRight(new BinaryNode<>(e, key, node));
            }
        }
    }

    /**
     * Elimina el nodo con clave k del árbol.
     *
     * @param k la clave perteneciente al nodo a eliminar.
     * @return el elemento almacenado en el nodo a eliminar.
     * null si el nodo con clave k no existe.
     */
    @Override
    public T delete(K k) {
        if(this.isEmpty())
            return null;
        else
            return delete(retrieve(k));
    }

    private T delete(BinaryNode node){
        BinaryNode aux = findMax(node.getLeft());
        T element = (T) node.getElement();
        node.setElement(aux.getElement());
        node.setKey(aux.getKey());
        if(node.getLeft().hasRight())
            findMax(node.getLeft()).getParent().setRight(null);
        else
            node.getLeft().getParent().setLeft(null);
        return element;
    }


    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     *
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public BinaryNode findMin(BinaryNode node) {
        if(this.isEmpty())
            return null;
        else{
            return findMinAux(node);
        }
    }

    public BinaryNode findMin(){
       if(this.isEmpty())
           return null;
       else
           return findMinAux(root);
    }

    private BinaryNode findMinAux(BinaryNode actual){
        if(actual.hasLeft())
            return findMinAux(actual.getLeft());
        else
            return actual;
    }

    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     *
     * @return el elemento con llave de peso máximo.
     */
    @Override
    public BinaryNode findMax(BinaryNode node) {
        if(this.isEmpty())
            return null;
        else
            return findMaxAux(node);
    }

    public BinaryNode findMax(){
        if(this.isEmpty())
            return null;
        else
            return findMax(root);
    }

    private BinaryNode findMaxAux(BinaryNode node){
        if(node.hasRight())
            return findMax(node.getRight());
        else
            return node;
    }

    /**
     * Recorre el árbol en preorden.
     */
    @Override
    public void preorden() {
        preorden(root);
    }


    public void preorden(BinaryNode node){
        if(node == null)
            return;
        
        System.out.print(node.getElement() + " ");   
        preorden(node.getLeft());  
        preorden(node.getRight());
    }

    /**
     * Recorre el árbol en inorden.
     */
    @Override
    public void inorden() {
        inorden(root);
    }

    public void inorden(BinaryNode node){
        if(node == null)
            return;
        
        inorden(node.getLeft());
        System.out.print(node.getElement() + " ");
        inorden(node.getRight());
    }


    /**
     * Recorre el árbol en postorden.
     */
    @Override
    public void postorden() {
        System.out.println("El postorden es:");
        postorden(root);
    }

    public void postorden(BinaryNode node){
        if( node == null )
            return;
        
        postorden(node.getLeft());
        postorden(node.getRight());
        System.out.print(node.getElement() + " ");
    }

    /**
     * Verifica si el árbol es vacío.
     *
     * @return true si el árbol es vacío, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer,String> binarySearchTree = new BinarySearchTree<Integer, String>();
        binarySearchTree.insert("A",17);
        binarySearchTree.insert("B", 5);
        binarySearchTree.insert("J", 3);
        binarySearchTree.insert("E", 15);
        binarySearchTree.insert("L", 6);
        binarySearchTree.insert("M", 8);
        binarySearchTree.insert("N", 7);
        binarySearchTree.insert("R", 9);
        binarySearchTree.insert("Y", 16);
        /*binarySearchTree.insert("C", 11);
        binarySearchTree.insert("D", 14);
        binarySearchTree.insert("K", 13);
        binarySearchTree.insert("V", 15);*/
        binarySearchTree.insert("Z", 20);
        binarySearchTree.insert("H", 26);

        // binarySearchTree.inorden();

        //System.out.println(binarySearchTree.retrieve(12).getElement());

        //System.out.println("Se borró el elemento: " + binarySearchTree.delete(12));

        binarySearchTree.inorden();

        //System.out.println(binarySearchTree.root.getElement());

    }

}
