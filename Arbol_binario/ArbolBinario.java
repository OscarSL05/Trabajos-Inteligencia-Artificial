package Arbol_binario;

//CLASE NODO
class NodoArbol {
    int valor;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(int valor) {
        this.valor = valor;
        this.izquierdo = null; //AL CREARS UN NODO, SUS HIJOS IZQUIERDO Y DERECHO SON NULL TODAVIA, YA QUE NO TIENE HIJOS
        this.derecho = null;
    }
}

//CLASE ARBOL
public class ArbolBinario {
    NodoArbol raiz; //NODO DE HASTA ARRIBA DEL ARBOL

    public ArbolBinario() {
        this.raiz = null; //AL CREAR UN ARBOL, SU RAIZ ES NULL TODAVIA, YA QUE NO TIENE NODOS
    }

    //INSERTAR 
    //EL USUARIO LLAMA A ESTE METODO, Y ESTE LLAMA AL METODO RECURSIVO QUE HACE EL TRABAJO REAL
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    //METODO RECURSIVO QUE RECORRE EL ARBOL COMPARANDO EL VALOR A INSERTAR CON EL 
    //VALOR DEL NODO ACTUAL, HASTA LLEGAR A UN ESPACIO VACIO DONDE SE CREA EL NUEVO NODO
    public NodoArbol insertarRecursivo(NodoArbol actual, int valor) {
        //LLEGAMOS A UN ESPACIO VACIO
        //SE CREA Y SE DEVUELVE EL NODO NUEVO
        if (actual == null) {
            return new NodoArbol(valor);
        }
        //SI EL VALOR ES MENOR QUE EL NODO ACTUAL, SE VA A LA IZQUIERDA
        //GUARDAMOS EL NODO DEVUELTO EN actual.izquierdo, YA QUE PUEDE HABER CAMBIADO EL NODO ACTUAL (SI SE CREO UN NUEVO NODO)
        if (valor < actual.valor) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        //SI EL VALOR ES MAYOR QUE EL NODO ACTUAL, SE VA A LA DERECHA
        //MISMA LOGICA QUE LA ANTERIOR, PERO PARA EL HIJO DERECHO
        } else if (valor > actual.valor) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        //SI EL VALOR ES IGUAL, NO SE HACE NADA (NO SE PERMITEN DUPLICADOS)
        //DEVOLVEMOS EL NODO ACTUAL SIN CAMBIOS

        return actual;
    }

    //BUSCAR
    //REGRESA TRUE SI EL VALOR SE ENCUENTRA EN EL ARBOL, FALSE SI NO
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    public boolean buscarRecursivo(NodoArbol actual, int valor) {
        //SI LLEGAMOS A UN ESPACIO VACIO, EL VALOR NO SE ENCUENTRA EN EL ARBOL
        if (actual == null) {
            return false;
        }
        //SI EL VALOR ES IGUAL AL NODO ACTUAL, LO ENCONTRAMOS
        if (valor == actual.valor) {
            return true;
        }
        
        //SI EL VALOR ES MENOR QUE EL NODO ACTUAL, SE VA A LA IZQUIERDA
        //SI ES MAYOR, SE VA A LA DERECHA
        if (valor < actual.valor) {
            return buscarRecursivo(actual.izquierdo, valor);
        } else {
            return buscarRecursivo(actual.derecho, valor);
        }
    }

    //INORDER (IZQUIERDA, RAIZ, DERECHA)
    public void inOrder() {
        inOrderRecursivo(raiz);
        System.out.println();
    }
    public void inOrderRecursivo(NodoArbol actual) {
        //NO HAY NODO QUE VISITAR, SE TERMINA LA RECURSION
        if (actual == null) {
            return;
        }
        inOrderRecursivo(actual.izquierdo); // LO MENOR PRIMERO
        System.out.print(actual.valor + " ");//LUEGO EL NODO ACTUAL
        inOrderRecursivo(actual.derecho);// LO MAYOR AL FINAL
    }

    //PREORDER (RAIZ, IZQUIERDA, DERECHA)
    public void preOrder() {
        preOrderRecursivo(raiz);
        System.out.println();
    }

    public void preOrderRecursivo(NodoArbol actual) {
        //NO HAY NODO QUE VISITAR, SE TERMINA LA RECURSION
        if (actual == null) {
            return;
        }
        System.out.print(actual.valor + " ");//LUEGO EL NODO ACTUAL
        preOrderRecursivo(actual.izquierdo); // LO MENOR PRIMERO
        preOrderRecursivo(actual.derecho);// LO MAYOR AL FINAL
    }

    //POSTORDER (IZQUIERDA, DERECHA, RAIZ)
    public void postOrder() {
        postOrderRecursivo(raiz);
        System.out.println();
    }

    public void postOrderRecursivo(NodoArbol actual) {
        //NO HAY NODO QUE VISITAR, SE TERMINA LA RECURSION
        if (actual == null) {
            return;
        }
        postOrderRecursivo(actual.izquierdo); // LO MENOR PRIMERO
        postOrderRecursivo(actual.derecho);// LO MAYOR AL FINAL
        System.out.print(actual.valor + " ");//LUEGO EL NODO ACTUAL
    }


    
}

class PruebaArbolBinario {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        //INSERTAMOS VARIOS VALORES, EL ORDEM DE INSERCION DEFINE LA FORMA FINAL DEL ARBOL, 
        //YA QUE EL PRIMERO INSERTADO SE CONVIERTE EN LA RAIZ, Y LOS DEMAS SE VAN INSERTANDO 
        //A LA IZQUIERDA O DERECHA SEGUN SU VALOR
        arbol.insertar(5);
        arbol.insertar(3);
        arbol.insertar(7);
        arbol.insertar(2);
        arbol.insertar(4);
        arbol.insertar(6);
        arbol.insertar(8);

        /*ARBOL BINARIO DE BUSQUEDA RESULTANTE
                5
              /   \
             3     7
            / \   / \
           2   4 6   8
*/
        System.out.println("RECORRIDO INORDER:");
        arbol.inOrder();// 2 3 4 5 6 7 8
    
        System.out.println("RECORRIDO PREORDER:");
        arbol.preOrder();// 5 3 2 4 7 6 8

        System.out.println("RECORRIDO POSTORDER:");
        arbol.postOrder();// 2 4 3 6 8 7 5

        System.out.println("Buscar 4: " + arbol.buscar(4)); // true
        System.out.println("Buscar 10: " + arbol.buscar(10)); // false
        
    }
}
