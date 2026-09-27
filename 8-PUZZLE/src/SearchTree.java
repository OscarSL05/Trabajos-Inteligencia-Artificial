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
    public Node depthLimitedSearchMetodoFuncion(int limit) {
        
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

    //BUSQUEDA LIMITADA EN PROFUNDIDAD 
    public void depthLimitedSearch(int limit) {
        System.out.println("depthLimitedSearch (BUSQUEDA LIMITADA EN PROFUNDIDAD)");
        long startTime = System.currentTimeMillis();
        //USO LAS MISMAS VARIABLES GLOBALES QUE EN IDS PARA NO REPETIR CODIGO
        timeIDS = 0;
        maxStackIDS = 0;
        visitedIDS = 0;
        //LLAMAMOS AL METODO DE BUSQUEDA LIMITADA EN PROFUNDIDAD
        Node result = depthLimitedSearchMetodoFuncion(limit);
        //si encontramos el nodo objetivo lo imprimimos si no lo encontramos imprimimos un mensaje de error
        if(result != null) {
            System.out.println("Goal state found at depth " + limit + ": " + result.getState());
            printPath(result);
        } else {
            System.out.println("NO SE ENCONTRO SOLUCION CON LIMITE DE PROFUNDIDAD DE " + limit);
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("--------------------------------");
        System.out.println("DATOS DE EJECUCION");
        System.out.println("Time: " + timeIDS);
        System.out.println("Estados visitados: " + visitedIDS);
        System.out.println("Time Used: " + (endTime - startTime) + " ms");
        System.out.println("Stack: " + 0);//NO HAY STACK YA QUE SE REINICIA CADA VEZ
        System.out.println("Max Stack : " + maxStackIDS);
        System.out.println("--------------------------------");
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
            Node result = depthLimitedSearchMetodoFuncion(limit);
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

    //BUSQUEDA BIDRECTIONAL (OPCIONAL)
    //BUSCAMOS DESDE AMBOS EXTREMOS AL MISMO TIEMPO, CUANDO LOS DOS LADOS CHOCAN, ENTONCES HEMOS ENCONTRADO EL NODO OBJETIVO Y SE 
    //ARMA EL CAMINO DESDE EL NODO RAIZ HASTA EL NODO OBJETIVO Y DESDE EL NODO OBJETIVO HASTA EL NODO RAIZ
    public void bidirectionalSearch() {
        System.out.println("bidirectionalSearch (BUSQUEDA BIDIRECCIONAL)");
        // Implementación de la búsqueda bidireccional
        long startTime = System.currentTimeMillis();
        int time=0;
        int maxStack=0;

        //DOS COLAS PARA ALMACENAR LOS NODOS VISITADOS DESDE EL INICIO Y DESDE EL OBJETIVO
        Queue<Node> queueStart = new LinkedList<>();
        Queue<Node> queueGoal = new LinkedList<>();
        
        //MAPAS PARA ALMACENAR LOS NODOS VISITADOS DESDE EL INICIO Y DESDE EL OBJETIVO 
        //Y EN LUGAR DE SET USAMOS MAPAS PARA PODER GUARDAR EL NODO COMPLETO Y NO SOLO EL ESTADO
        java.util.Map<String, Node> visitedStart = new java.util.HashMap<>();
        java.util.Map<String, Node> visitedGoal = new java.util.HashMap<>();

        //AGREGAMOS EL NODO RAIZ A LA COLA DE INICIO Y EL NODO OBJETIVO A LA COLA DE OBJETIVO
        Node currentNodeStart = root;
        Node currentNodeGoal = new Node(goalState, null);

        //AGREGAMOS LOS NODOS A LAS COLAS Y A LOS MAPAS DE VISITADOS
        queueStart.add(currentNodeStart);
        queueGoal.add(currentNodeGoal);
        visitedStart.put(currentNodeStart.getState(), currentNodeStart);
        visitedGoal.put(currentNodeGoal.getState(), currentNodeGoal);
        
        Node meetingNodeStart = null; //NODO DONDE SE ENCUENTRAN LAS DOS BUSQUEDAS DESDE EL INICIO
        Node meetingNodeGoal = null; //NODO DONDE SE ENCUENTRAN4 LAS DOS BUSQUEDAS DESDE EL OBJETIVO

        //CICLO PRINCIPAL DE LA BUSQUEDA BIDIRECCIONAL MIENTRAS AMBAS COLAS NO ESTEN VACIAS
        //AQUI EXPANDIMOS UN NODO DE CADA LADO POR ITERACION (NO UN NIVEL COMPLETO).
        while (!queueStart.isEmpty() && !queueGoal.isEmpty()) {
            time++;
            int totalStack = queueStart.size() + queueGoal.size();
            if (totalStack > maxStack) {
                maxStack = totalStack;
            }
        
            //BUSQUEDA DESDE EL INICIO
            currentNodeStart = queueStart.poll();
            //VERIFICAMOS SI EL NODO ACTUAL DESDE EL INICIO YA FUE VISITADO DESDE EL OBJETIVO
            if (visitedGoal.containsKey(currentNodeStart.getState())) {
                meetingNodeStart = currentNodeStart;
                meetingNodeGoal = visitedGoal.get(currentNodeStart.getState());
                break;
            }
            //GENERAMOS LOS HIJOS DEL NODO ACTUAL DESDE EL INICIO Y LOS AGREGAMOS A LA COLA DE INICIO SI NO HAN SIDO VISITADOS
            //USAMOS GENERATECHILDREN PARA GENERAR LOS HIJOS Y LUEGO LOS AGREGAMOS A LA COLA DE INICIO SI NO HAN SIDO VISITADOS
            List<Node> childrenStart = NodeUtils.generateChildren(currentNodeStart);
            for (Node child : childrenStart) {
                if (!visitedStart.containsKey(child.getState())) {
                    queueStart.add(child);
                    visitedStart.put(child.getState(), child);
                }
            }

            //BUSQUEDA DESDE EL OBJETIVO
            currentNodeGoal = queueGoal.poll();
            //VERIFICAMOS SI EL NODO ACTUAL DESDE EL OBJETIVO YA FUE VISITADO DESDE EL NODO DEL INICIO
            if (visitedStart.containsKey(currentNodeGoal.getState())) {
                meetingNodeStart = visitedStart.get(currentNodeGoal.getState());
                meetingNodeGoal = currentNodeGoal;
                break;
            }
            //GENERAMOS LOS HIJOS DEL NODO ACTUAL DESDE EL OBJETIVO Y LOS AGREGAMOS A LA COLA DE OBJETIVO SI NO HAN SIDO VISITADOS
            List<Node> childrenGoal = NodeUtils.generateChildren(currentNodeGoal);
            for (Node child : childrenGoal) {
                if (!visitedGoal.containsKey(child.getState())) {
                    queueGoal.add(child);
                    visitedGoal.put(child.getState(), child);
                }
            }            
        }
        //CALCULAMOS EL TIEMPO DE EJECUCION
        long endTime = System.currentTimeMillis();

        //SI ENCONTRAMOS EL NODO OBJETIVO DESDE EL INICIO Y DESDE EL OBJETIVO, 
        // IMPRIMIMOS EL CAMINO DESDE EL INICIO HASTA EL NODO OBJETIVO Y DESDE EL OBJETIVO HASTA EL NODO OBJETIVO
        if (meetingNodeStart != null && meetingNodeGoal != null) {
            System.out.println("Goal state found: " + meetingNodeStart.getState());
            //IMPRIMIMOS EL CAMINO DESDE EL NODO RAIZ HASTA EL NODO OBJETIVO
            printPath(meetingNodeStart);

            //IMPRIMIMOS EL CAMINO DESDE EL NODO OBJETIVO HASTA EL NODO RAIZ
            //COMO EL CAMINO DESDE EL NODO OBJETIVO HASTA EL NODO RAIZ ES INVERSO, LO IMPRIMIMOS EN ORDEN INVERSO
            //A DIFERENCIA DE printPath, AQUI NO USAMOS RECURSION SINO UN CICLO WHILE PARA IMPRIMIR EL CAMINO DESDE EL NODO OBJETIVO HASTA EL NODO RAIZ
            Node currentNode = meetingNodeGoal.getParent(); //EMPEZAMOS DESDE EL PADRE DEL NODO OBJETIVO PARA NO IMPRIMIRLO DOS VECES
            while (currentNode != null) {
                System.out.println(NodeUtils.formatState(currentNode.getState()));
                currentNode = currentNode.getParent();
            }
        } else {
            System.out.println("NO SE ENCONTRO SOLUCION");
        }
        System.out.println("--------------------------------");
        System.out.println("DATOS DE EJECUCION");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + (visitedStart.size() + visitedGoal.size()));
        System.out.println("Time Used: " + (endTime - startTime) + " ms");
        System.out.println("Stack: " + (queueStart.size() + queueGoal.size()));
        System.out.println("Max Stack : " + maxStack);
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
