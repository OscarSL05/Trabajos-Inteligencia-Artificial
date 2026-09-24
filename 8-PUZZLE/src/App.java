package src;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{


        String initialState = "7621 3458"; // Estado inicial del puzzle
        String goalState = "12345678 "; // Estado objetivo del puzzle
        SearchTree searchTree = new SearchTree(initialState, goalState);
        Scanner sc = new Scanner(System.in);

        System.out.println("8-PUZZLE");
        System.out.println("Initial State:" + initialState);
        System.out.println("Goal State:" + goalState);
        System.out.println("--------------------------------");
        System.out.println("SELECCIONE UNA OPCION:");
        System.out.println("1. BUSQUEDA PRIMERO EN ANCHURA");
        System.out.println("2. BUSQUEDA PRIMERO EN PROFUNDIDAD");
        System.out.println("3. BUSQUEDA DE COSTO UNIFORME");
        System.out.println("4. BUSQUEDA EN PROFUNDIDAD ITERATIVA");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                searchTree.breadthFirstSearch();
                break;
            case 2:
                searchTree.deepFirstSearch();
                break;
            case 3:
                searchTree.UniformCostSearch();
                break;
            case 4:
                searchTree.IterativeDeepeningSearch();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        sc.close();

        

    }
}
