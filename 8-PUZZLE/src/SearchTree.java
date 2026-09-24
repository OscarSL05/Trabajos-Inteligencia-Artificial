package src;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SearchTree {
    Node root;
    String goalState;
    String initialState;

    //YA QUE EN IDS visited Y stack ESTAN EN OTRO METODO
    private int timeIDS=0;
    private int maxStackIDS=0;
    private int visitedIDS=0;

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
    }

    /* TAREA, UNA VEZ TERMINADO, HAY QUE IMPRIMIR LAS COMPLEJIDADES de tiempo y espacio usado
    E IMPRIMIR ALGO BONITO COMO IMPRIMIR DESDE EL ESTADO INICIAL HASTA EL ESTADO META
    NECESITAMOS CREAR UN METODO DENTRO DE SearchTree */

    //BUSQUEDA PRIMERO EN ANCHURA
    public void breadthFirstSearch() {
        System.out.println("breadthFirstSearch (BUSQUEDA PRIMERO EN ANCHURA)");
        long startTime = System.currentTimeMillis();
        int time=0;
        int maxStack=0;

        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();
        // 1. Buscar el nodo raíz y agregarlo a la cola
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(currentNode);
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!queue.isEmpty()) {
            time++;
            if(queue.size() > maxStack){
                maxStack = queue.size();
            }
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = queue.poll();
            visited.add(currentNode.getState());
            //System.out.println(NodeUtils.formatState(currentNode.getState()));
            if(currentNode.getState().equals(goalState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                // print the path from root to goal
                printPath(currentNode);
                break;
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState()))
                    queue.add(child);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--------------------------------");
        System.out.println("DATOS DE EJECUCION");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + visited.size());
        System.out.println("Time Used: " + (endTime - startTime) + " ms");
        System.out.println("Stack: " + queue.size());
        System.out.println("Max Stack : " + maxStack);
        System.out.println("--------------------------------");
    }

    //BUSQUEDA PRIMERO EN PROFUNDIDAD
    public void deepFirstSearch() {
        System.out.println("deepFirstSearch (BUSQUEDA PRIMERO EN PROFUNDIDAD)");
        long startTime = System.currentTimeMillis();
        int time=0;
        int maxStack=0;
        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();
        // 1. Buscar el nodo raíz y agregarlo a la cola
        Node currentNode = root;
        Stack<Node> stack = new Stack<>(); //Cambiar de cola 
        stack.push(currentNode);
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!stack.isEmpty()) {
            time++;
            if(stack.size() > maxStack){
                maxStack = stack.size();
            }
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = stack.pop();
            visited.add(currentNode.getState());
            //System.out.println(NodeUtils.formatState(currentNode.getState()));
            if(currentNode.getState().equals(goalState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                // print the path from root to goal
                printPath(currentNode);
                break;
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState()))
                    stack.add(child);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("--------------------------------");
        System.out.println("DATOS DE EJECUCION");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + visited.size());
        System.out.println("Time Used: " + (endTime - startTime) + " ms");
        System.out.println("Stack: " + stack.size());
        System.out.println("Max Stack : " + maxStack);
        System.out.println("--------------------------------");
    }


    //BUSQUEDA DE COSTO UNIFORME
    public void UniformCostSearch(){
        System.out.println("UniformCostSearch (BUSQUEDA DE COSTO UNIFORME)");
        long startTime = System.currentTimeMillis();
        int time=0;
        int maxStack=0;
        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();
        // 1. Buscar el nodo raíz y agregarlo a la cola
        Node currentNode = root;
        PriorityQueue <Node> queue = new PriorityQueue<>(new NodePriorityComparator());
        queue.add(currentNode);
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!queue.isEmpty()) {
            time++;
            if(queue.size() > maxStack){
                maxStack = queue.size();
            }
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = queue.poll();
            visited.add(currentNode.getState());
            //System.out.println(NodeUtils.formatState(currentNode.getState()));
            if(currentNode.getState().equals(goalState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                // print the path from root to goal
                printPath(currentNode);
                break;
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState())){
                    child.setCost(child.getParent().getCost() + 1);
                    queue.add(child);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--------------------------------");
        System.out.println("DATOS DE EJECUCION");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + visited.size());
        System.out.println("Time Used: " + (endTime - startTime) + " ms");
        System.out.println("Stack: " + queue.size());
        System.out.println("Max Stack : " + maxStack);
        System.out.println("--------------------------------");
    }



    //BSUQUEDA LIMITADA EN PROFUNDIDAD PARA IMPLEMENTAR LA BUSQUEDA ITERATIVA
    public Node depthLimitedSearch(int limit) {
        
        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();

        // 1. Iniciar con la raiz y asegurar que su profundidad sea 0
        Node currentNode = root;
        currentNode.setDepth(0);
        

        Stack<Node> stack = new Stack<>();
        stack.push(currentNode);
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!stack.isEmpty()) {
            timeIDS++; //INCREMENTA LA VARIABLE GLOBAL 
            if(stack.size() > maxStackIDS){
                maxStackIDS = stack.size(); //GUARDAMOS EL MAXIMO GLOBAL
            }

            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = stack.pop();
            visited.add(currentNode.getState());
            visitedIDS=visited.size(); //INCREMENTA LA VARIABLE DE VISITADOS GLOBAL

            if(currentNode.getState().equals(goalState)) {
                return currentNode; //REGRESAR EL NODO OBJETIVO SI SE ENCUENTRA
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            if(currentNode.getDepth() < limit) {


                List<Node> children = NodeUtils.generateChildren(currentNode);
                for (Node child : children) {
                    if (!visited.contains(child.getState())) {
                        child.setDepth(currentNode.getDepth() + 1);
                        stack.add(child);
                    }
                }
            }
        
        }

        return null;
    }



    //BUSQUEDA EN PROFUNDIDAD ITERATIVA
    public void IterativeDeepeningSearch() {
        System.out.println("IterativeDeepeningSearch (BUSQUEDA EN PROFUNDIDAD ITERATIVA)");
        long startTime = System.currentTimeMillis();
        timeIDS = 0;
        maxStackIDS = 0;
        visitedIDS = 0;
        int maxDepth = 50;//LIMITE DE PROFUNDIDAD MAXIMO PARA EVITAR BUCLES INFINITOS
        boolean encontrado = false;//VARIABLE PARA SABER SI SE ENCONTRO EL NODO OBJETIVO

        //CICLO ITERATIVO PARA AUMENTAR EL LIMITE DE PROFUNDIDAD PROFUNDIDAD 0, 1, 2, 3,...
        for (int limit = 0; limit <= maxDepth; limit++) {

            //LLAMADA AL METODO DE BUSQUEDA LIMITADA EN PROFUNDIDAD
            Node result = depthLimitedSearch(limit);
            //SI NO ES NULL ENCONTRAMOS EL NODO OBJETIVO
            if (result != null) {
                System.out.println("Goal state found at depth " + limit + ": " + result.getState());
                printPath(result);
                encontrado = true;
                break; //SALIMOS DEL CICLO YA QUE ENCONTRAMOS EL NODO OBJETIVO
            }
        }
        if(!encontrado){
            System.out.println("NO SE ENCONTRO SOLUCION CON LIMITE DE PROFUNDIDAD DE " + maxDepth);

        }
        long endTime = System.currentTimeMillis();

    
        System.out.println("--------------------------------");
        System.out.println("DATOS DE EJECUCION");
        System.out.println("Time: " + timeIDS);
        System.out.println("Estados visitados: " + visitedIDS);
        System.out.println("Time Used: " + (endTime - startTime) + " ms");
        System.out.println("Stack: 0");//NO HAY STACK YA QUE SE REINICIA CADA VEZ
        System.out.println("Max Stack : " + maxStackIDS);
        System.out.println("--------------------------------");
    }



        
    public void printPath(Node node) {
        if (node == null) {
            return;
        }
        printPath(node.getParent());
        System.out.println(NodeUtils.formatState(node.getState()));
    }

}
