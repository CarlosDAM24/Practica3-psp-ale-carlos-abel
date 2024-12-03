package model;

// Clase genérica MiList
// Clase MiList modificada
public class MiList<E> {
    private Node<E> inicio;

    public MiList() {
        this.inicio = null;
    }

    // Mostrar los elementos de la lista
    public void mostrarLista() {
        Node<E> aux = this.inicio;
        while (aux != null) {
            System.out.println(aux.getPrincipal().toString());
            aux = aux.getSiguiente();
        }
    }

    // Insertar nodo al inicio de la lista
    public void insertarNodo(E p) {
        Node<E> nuevoNodo = new Node<>(p);
        nuevoNodo.setSiguiente(this.inicio);
        this.inicio = nuevoNodo;
    }

    // Obtener el primer nodo de la lista
    public Node<E> getInicio() {
        return this.inicio;
    }

    // Obtener el nodo anterior a un nodo dado
    public Node<E> getAnterior(Node<E> nodo) {
        if (nodo == null || nodo == this.inicio) {
            return null; // No hay anterior si es el primer nodo
        }

        Node<E> actual = this.inicio;
        while (actual != null && actual.getSiguiente() != nodo) {
            actual = actual.getSiguiente();
        }
        return actual; // Devuelve el nodo anterior o null si no se encuentra
    }

    // Obtener el nodo siguiente a un nodo dado
    public Node<E> getSiguiente(Node<E> nodo) {
        if (nodo == null) {
            return null; // Nodo inválido
        }
        return nodo.getSiguiente();
    }

    // Clase interna Node
    public static class Node<E> {
        private Node<E> siguiente;
        private E principal;

        public Node(E p) {
            this.siguiente = null;
            this.principal = p;
        }

        public Node<E> getSiguiente() {
            return this.siguiente;
        }

        public void setSiguiente(Node<E> siguiente) {
            this.siguiente = siguiente;
        }

        public E getPrincipal() {
            return this.principal;
        }

        public void setPrincipal(E p) {
            this.principal = p;
        }
    }
}

