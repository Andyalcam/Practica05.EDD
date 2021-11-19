
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
    public T retrieve(K k) {
        return retrieve(root, k);
    }

    private T retrieve(BinaryNode node, K key){
        if(node.getKey().compareTo(key) > 0){
            if(node.hasLeft())
                return retrieve(node.getLeft(), key);
            else {
                return null;
            }
        }else if(node.getKey().compareTo(key) == 0){
            return (T) node.getElement();
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
        return null;
    }

    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     *
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public T findMin() {
        return null;
    }

    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     *
     * @return el elemento con llave de peso máximo.
     */
    @Override
    public T findMax() {
        return null;
    }

    /**
     * Recorre el árbol en preorden.
     */
    @Override
    public void preorden() {
        aux = false;
        System.out.println("El preorden es:");
        System.out.println(root.getElement());
        if(root.hasLeft()){
            preorden(root.getLeft());
        }else if(root.hasRight()){
            preorden(root.getRight());
        }
    }


    public void preorden(BinaryNode node){
        System.out.println(node.getElement());
        if(!node.hasLeft()){
            if(node.hasRight()){
                preorden(node.getRight());
            }else{
                if(!node.getParent().hasRight() || node.getParent().getRight().equals(node) || node.getParent().equals(root)){
                    if(!aux){
                        aux = true;
                        if(root.hasRight())
                            preorden(root.getRight());
                        else
                            return;
                    }else
                        return;
                }else
                    preorden(node.getParent().getRight());
            }
        }else{
            preorden(node.getLeft());
        }


    }

    /**
     * Recorre el árbol en inorden.
     */
    @Override
    public void inorden() {
        aux = false;
        System.out.println("El inorden es: ");
        if(!root.hasLeft()){
            System.out.println(root.getElement());
            if(root.hasRight()){
                inorden(root.getRight());
            }
        }else{
            inorden(root.getLeft());
        }
    }

    public void inorden(BinaryNode node){
        if(!node.hasLeft()){
            System.out.println(node.getElement());
            if(node.hasRight()){
                inorden(node.getRight());
            }else{
                if(!node.getParent().hasRight() || node.getParent().getRight().equals(node) || node.getParent().equals(root)){
                    if(!aux){
                        aux = true;
                        System.out.println(root.getElement());
                        if(root.hasRight())
                            inorden(root.getRight());
                        else
                            return;
                    }else
                        return;
                }else {
                    System.out.println(node.getParent().getElement());
                    inorden(node.getParent().getRight());
                }
            }
        }else{
            inorden(node.getLeft());
        }
    }


    /**
     * Recorre el árbol en postorden.
     */
    @Override
    public void postorden() {

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
        binarySearchTree.insert("A",5);
        binarySearchTree.insert("B", 2);
        binarySearchTree.insert("J", 1);
        binarySearchTree.insert("E", 3);
        binarySearchTree.insert("Y", 4);
        binarySearchTree.insert("Z", 8);
        binarySearchTree.insert("H", 10);
       // binarySearchTree.inorden();

        System.out.println(binarySearchTree.retrieve(19));

        //System.out.println(binarySearchTree.root.getElement());

    }

}
