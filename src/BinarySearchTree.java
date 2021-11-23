import java.util.Scanner;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.InputMismatchException;
public class BinarySearchTree <K extends Comparable,T> implements TDABinarySearchTree<K,T>{

    private BinaryNode<K, T> root;

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
            return node;
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

        if(node == null){
            throw new NullPointerException();
        }
        T element = (T) node.getElement();
        if(node.hasLeft()){
            BinaryNode aux = findMax(node.getLeft());
            node.setElement(aux.getElement());
            node.setKey(aux.getKey());
            if(node.getLeft().hasRight())
                findMax(node.getLeft()).getParent().setRight(null);
            else
                node.getLeft().getParent().setLeft(null);
            return element;
        }else{
            if(node.hasRight()){
                if(node.getKey().compareTo(node.getParent().getKey()) < 0){
                    node.getParent().setLeft(node.getRight());
                    node.getRight().setParent(node.getParent());
                }else{
                    node.getParent().setRight(node.getRight());
                    node.getRight().setParent(node.getParent());
                }
                return element;
            }else{
                if(node.getKey().compareTo(node.getParent().getKey()) < 0)
                    node.getParent().setLeft(null);
                else
                    node.getParent().setRight(null);
                return element;
            }
        }
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

    private BinaryNode findMinAux(BinaryNode node){
        if(node.hasLeft())
            return findMinAux(node.getLeft());
        else
            return node;
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

        // Colores de letra
        String red="\033[31m"; 
        String green="\033[32m"; 
        String yellow="\033[33m";
        String purple="\033[35m"; 
        String cyan="\033[36m";
        // Reset
        String reset="\u001B[0m";

        BinarySearchTree<Integer,String> binarySearchTree = new BinarySearchTree<Integer, String>();
        boolean excep = true,repe;
        Scanner in = new Scanner(System.in);
        Scanner on = new Scanner(System.in);
        int opc,b;
        int clave = 0;
        String tupla = "",a;

        System.out.println("*** BIENVENIDO ***");

        while (excep) {
            try{
                System.out.println("\n\t\t*** Menu ***");
                System.out.println("--------------------------------------------");
                System.out.println("1. Insertar");
                System.out.println("2. Borrar");
                System.out.println("3. Mostrar Preorden");
                System.out.println("4. Mostrar Inorden");
                System.out.println("5. Mostrar Postorden");
                System.out.println("6. Verificar si el árbol es vacío");
                System.out.println("7. Obtener elemento");
                System.out.println("8. Encontrar Máximo");
                System.out.println("9. Encontrar Mínimo");
                System.out.println("10. Salir");
                System.out.println("--------------------------------------------");
                System.out.println("Ingresa una opcion del menu: ");
                opc = in.nextInt();

                switch (opc) {
                    case 1:
                            System.out.println("Ingresa el elemento que quieres insertar y su clave. Ej: 2,3");
                            repe = true;
                            while(repe){
                                try{
                                    tupla = on.nextLine().trim();
                                    a = (tupla.split(",")[0]).trim();
                                    b = Integer.parseInt(tupla.split(",")[1]);
                                    binarySearchTree.insert(a,b);
                                    repe = false;
                                }catch(Exception e){
                                    System.out.println(yellow + "\t Intentalo de nuevo. Sigue el ejemplo :)" + reset);
                                }
                            }
                        break;
                    case 2:
                            System.out.println("Ingresa la clave del elemento que deseas borrar");
                            repe = true;
                            while (repe) {
                                try{
                                    clave = in.nextInt();
                                    if (!binarySearchTree.isEmpty()){
                                        System.out.println(green + "El elemento que se borro es: " + binarySearchTree.delete(clave) + reset);
                                    }else{
                                        System.out.println(yellow + "\tEl árbol es vacío" + reset);
                                    }
                                    repe = false;
                                }catch (InputMismatchException e) {
                                    System.out.println(yellow + "\t Debes ingresar un número" + reset);
                                    in.next();
                                }catch (NullPointerException e) {
                                    System.out.println(e + red + "No hay ningún elemento contenido en el árbol con clave " + clave +reset);
                                }
                            }
                        break;
                    case 3:
                            if (!binarySearchTree.isEmpty()) {
                                System.out.print(green + "El preorden es: ");
                                binarySearchTree.preorden();
                                System.out.println(reset);
                            }else{
                                System.out.println(yellow + "\tEl árbol es vacío"+reset);
                            }
                        break;
                    case 4:
                            if (!binarySearchTree.isEmpty()) {
                                System.out.print(green + "El inorden es: ");
                                binarySearchTree.inorden();
                                System.out.println(reset);
                            }else{
                                System.out.println(yellow + "\tEl árbol es vacío"+reset);
                            }
                        break;
                    case 5:
                            if (!binarySearchTree.isEmpty()) {
                                System.out.print(green + "El postorden es: ");
                                binarySearchTree.postorden();
                                System.out.println(reset);
                            }else{
                                System.out.println(yellow + "\tEl árbol es vacío"+reset);
                            }
                        break;
                    case 6:
                            if (!binarySearchTree.isEmpty()) {
                                System.out.println(green + "\tEl árbol no es vacío" + reset);
                            }else{
                                System.out.println(yellow + "\tEl árbol es vacío"+reset);
                            }
                        break;
                    case 7:
                            System.out.println("Ingresa la clave del elemento que deseas obtener");
                            repe = true;
                            while (repe) {
                                try{
                                    clave = in.nextInt();
                                    if (!binarySearchTree.isEmpty()){
                                        if (binarySearchTree.retrieve(clave) != null) {
                                            System.out.println(green + "El elemento con clave " + clave + " es: " + binarySearchTree.retrieve(clave) + reset);
                                        }else{
                                            System.out.println(red + "No hay ningún elemento contenido en el árbol con clave " + clave +reset);
                                        }
                                    }else{
                                        System.out.println(yellow + "\tEl árbol es vacío" + reset);
                                    }
                                    repe = false;
                                }catch (Exception e) {
                                    System.out.println(yellow + "\t Debes ingresar un número" + reset + "\n");
                                    in.next();
                                }
                            }
                        break;
                    case 8:
                            if (!binarySearchTree.isEmpty()) {
                                System.out.println(green + "El elemento máximo del árbol es: " + binarySearchTree.findMax() + reset);
                            }else{
                                System.out.println(yellow + "\tEl árbol es vacío"+reset);
                            }
                        break;
                    case 9:
                            if (!binarySearchTree.isEmpty()) {
                                System.out.println(green + "El elemento mínimo del árbol es: " + binarySearchTree.findMin() + reset);
                            }else{
                                System.out.println(yellow + "\tEl árbol es vacío"+reset);
                            }
                        break;
                    case 10:
                            System.out.println(purple + "\tHasta luego :)" + reset + "\n");
                            excep = false;
                        break;
                    default:
                            System.out.println(yellow + "\tElige una opcion de menu plis :c" + reset);
                        break;
                }

            }catch(Exception e){
                System.out.println(yellow + "\tDebes ingresar un número\tIntentalo de nuevo" + reset);
                in.next();
                excep = true;
            }
            
        }





        /*BinarySearchTree<Integer,String> binarySearchTree = new BinarySearchTree<Integer, String>();
        binarySearchTree.insert("A",17);
        binarySearchTree.insert("B", 8);
        binarySearchTree.insert("J", 3);
        binarySearchTree.insert("E", 10);
        binarySearchTree.insert("Y", 12);
        binarySearchTree.insert("C", 11);
        binarySearchTree.insert("D", 14);
        binarySearchTree.insert("K", 13);
        binarySearchTree.insert("V", 15);
        binarySearchTree.insert("Z", 20);
        binarySearchTree.insert("H", 26);

       // binarySearchTree.inorden();

        //System.out.println(binarySearchTree.retrieve(12).getElement());

        //System.out.println("Se borró el elemento: " + binarySearchTree.delete(9));

        binarySearchTree.preorden();
        //binarySearchTree.inorden();
        //binarySearchTree.postorden();

        //System.out.println(binarySearchTree.root.getElement());*/

    }

}
