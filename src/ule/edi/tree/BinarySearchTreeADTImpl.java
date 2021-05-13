package ule.edi.tree;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Ã�rbol binario de bÃºsqueda (binary search tree, BST).
 * 
 * El cÃ³digo fuente estÃ¡ en UTF-8, y la constante EMPTY_TREE_MARK definida en
 * AbstractTreeADT del proyecto API deberÃ­a ser el sÃ­mbolo de conjunto vacÃ­o:
 * âˆ…
 * 
 * Si aparecen caracteres "raros", es porque el proyecto no estÃ¡ bien
 * configurado en Eclipse para usar esa codificaciÃ³n de caracteres.
 *
 * En el toString() que estÃ¡ ya implementado en AbstractTreeADT se usa el
 * formato:
 * 
 * Un Ã¡rbol vacÃ­o se representa como "âˆ…". Un Ã¡rbol no vacÃ­o como
 * "{(informaciÃ³n raÃ­z), sub-Ã¡rbol 1, sub-Ã¡rbol 2, ...}".
 * 
 * Por ejemplo, {A, {B, âˆ…, âˆ…}, âˆ…} es un Ã¡rbol binario con raÃ­z "A" y un
 * Ãºnico sub-Ã¡rbol, a su izquierda, con raÃ­z "B".
 * 
 * El mÃ©todo render() tambiÃ©n representa un Ã¡rbol, pero con otro formato; por
 * ejemplo, un Ã¡rbol {M, {E, âˆ…, âˆ…}, {S, âˆ…, âˆ…}} se muestra como:
 * 
 * M | E | | âˆ… | | âˆ… | S | | âˆ… | | âˆ…
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para adjuntar
 * informaciÃ³n extra. Si es el caso, tanto toString() como render() mostrarÃ¡n
 * los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor) y con
 * {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en los
 * elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> serÃ­a muy restrictivo; en su lugar se
 * permiten tipos que sean comparables no sÃ³lo con exactamente T sino tambiÃ©n
 * con tipos por encima de T en la herencia.
 * 
 * @param <T> tipo de la informaciÃ³n en cada nodo, comparable.
 */
public class BinarySearchTreeADTImpl<T extends Comparable<? super T>> extends AbstractBinaryTreeADT<T> {

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda izquierdo.
	 */
	protected BinarySearchTreeADTImpl<T> getLeftBST() {
		// El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		// aquÃ­ se sabe que es ademÃ¡s de bÃºsqueda binario
		//
		return (BinarySearchTreeADTImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeADTImpl<T> left) {
		this.leftSubtree = left;
	}

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda derecho.
	 */
	protected BinarySearchTreeADTImpl<T> getRightBST() {
		return (BinarySearchTreeADTImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeADTImpl<T> right) {
		this.rightSubtree = right;
	}

	/**
	 * Ã�rbol BST vacÃ­o
	 */
	public BinarySearchTreeADTImpl() {

		setContent(null);

		setLeftBST(null);
		setRightBST(null);
	}

	private BinarySearchTreeADTImpl<T> emptyBST() {
		return new BinarySearchTreeADTImpl<T>();
	}

	/**
	 * Inserta todos los elementos de una colecciÃ³n en el Ã¡rbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements valores a insertar.
	 */
	public void insert(Collection<T> elements) {
		// O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
		for (T el : elements) {// si uno es null no continua
			if (el == null)
				throw new IllegalArgumentException();
		}

		for (T el : elements) {
			this.insert(el);
		}

	}

	/**
	 * Inserta todos los elementos de un array en el Ã¡rbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 */
	public void insert(T... elements) {
		for (T el : elements) {// si uno es null no continua
			if (el == null)
				throw new IllegalArgumentException();
		}
		for (T el : elements) {
			this.insert(el);
		}

	}

	/**
	 * Inserta de forma recursiva (como hoja) un nuevo elemento en el Ã¡rbol de
	 * bÃºsqueda.
	 * 
	 * No se permiten elementos null. Si el elemento ya existe en el Ã¡rbol NO lo
	 * inserta.
	 * 
	 * @param element valor a insertar.
	 */
	public void insert(T element) {
		// No se admiten null
		if (element == null) {
			throw new IllegalArgumentException("No se aceptan elementos nulos");
		}
 
		if (isEmpty()) {

			this.setContent(element);
			this.setLeftBST(emptyBST());
			this.setRightBST(emptyBST());

		} else {

			if (this.getContent().compareTo(element) > 0) {

				this.getLeftBST().insert(element);

			} else if (this.getContent().compareTo(element) < 0) {

				this.getRightBST().insert(element);
			}
		}

	}

	/**
	 * Elimina los elementos de la colecciÃ³n del Ã¡rbol.
	 */
	public void withdraw(Collection<T> elements) {
		// O todos o ninguno; si alguno es 'null', no se eliminarÃ¡ ningÃºn elemento

		for (T el : elements) {// si uno es null no continua
			if (el == null)
				throw new IllegalArgumentException();
		}
		for (T el : elements) {
			this.withdraw(el);
		}
	}

	/**
	 * Elimina los valores en un array del Ã¡rbol.
	 */
	public void withdraw(T... elements) {
		// O todos o ninguno; si alguno es 'null', no se eliminarÃ¡ ningÃºn elemento

		for (T el : elements) {// si uno es null no continua
			if (el == null)
				throw new IllegalArgumentException();
		}
		for (T el : elements) {
			this.withdraw(el);
		}
	}

	/**
	 * Elimina un elemento del Ã¡rbol.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no estÃ¡ en el
	 *                                Ã¡rbol
	 */
	public void withdraw(T element) {
		
		if (element == null) {
			throw new IllegalArgumentException("No se aceptan elementos nulos");
		}
	
		if (this.content.compareTo(element) == 0) {
		

			if ((this.getLeftBST() == null || this.getLeftBST().content == null)
					&& (this.getLeftBST() == null || this.getRightBST().content == null)) {
				// si no tiene hijos, se elimina el elemento
				System.out.println("El elemento no tiene hijos");
				this.setContent(null);
				this.setRightBST(null);
				this.setLeftBST(null);
			} else if (this.getLeftBST().content == null || this.getRightBST().content == null) {
				//solo tiene un hijo
				
				if (this.getLeftBST().content == null) {
					System.out.println("entro izquierda");
					this.content = this.getRightBST().content;
					System.out.println(this.getRightBST().content);
					if (this.getRightBST().getLeftBST() != null) {
						this.setLeftBST(this.getRightBST().getLeftBST());
					}
					if (this.getRightBST().getRightBST() != null) {
						this.setRightBST(this.getRightBST().getRightBST());
					}
					
				} else {
					System.out.println("entro derecha");
					this.content = this.getLeftBST().content;
					System.out.println(this.content);
					System.out.println(this.getLeftBST().getRightBST().content);
					if (this.getLeftBST().getRightBST() != null) {
						this.setRightBST(this.getLeftBST().getRightBST());
					}
					if (this.getLeftBST().getLeftBST() != null) {
						this.setLeftBST(this.getLeftBST().getLeftBST());
					}
							
				}	
			} else {
				//sin hijos
				
				BinarySearchTreeADTImpl<T> aux = this.getLeftBST();
				while (aux.getRightBST().content != null) {
					aux = aux.getRightBST();
				}
				T elementoABorrar = aux.content;
				this.content = elementoABorrar;
				getLeftBST().withdraw(elementoABorrar);

			}

		} else {
			//si no encuentra repetimos recursivo

			if (this.getContent().compareTo(element) > 0) {

				this.getLeftBST().withdraw(element);

			} else if (this.getContent().compareTo(element) < 0) {

				this.getRightBST().withdraw(element);
			}
		
			
		}

	}

	/**
	 * Devuelve el sub-Ã¡rbol indicado. (para tests) path serÃ¡ el camino para
	 * obtener el sub-arbol. EstÃ¡ formado por 0 y 1. Si se codifica "bajar por la
	 * izquierda" como "0" y "bajar por la derecha" como "1", el camino desde un
	 * nodo N hasta un nodo M (en uno de sus sub-Ã¡rboles) serÃ¡ la cadena de 0s y
	 * 1s que indica cÃ³mo llegar desde N hasta M.
	 *
	 * Se define tambiÃ©n el camino vacÃ­o desde un nodo N hasta Ã©l mismo, como
	 * cadena vacÃ­a.
	 * 
	 * Si el subarbol no existe lanzarÃ¡ la excepciÃ³n NoSuchElementException.
	 * 
	 * @param path
	 * @return
	 * @throws NoSuchElementException si el subarbol no existe
	 */
	public BinarySearchTreeADTImpl<T> getSubtreeWithPath(String path) {
		// TODO implementar el mÃ©todo

		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		return this.getSubtreeWithPathRec(path);
	}

	private BinarySearchTreeADTImpl<T> getSubtreeWithPathRec(String path) {

		if (path.isEmpty()) {

			return this;
		}

	

		if (path.charAt(0) == '0') {

			if (this.getLeftBST().isEmpty())
				throw new NoSuchElementException();

			return this.getLeftBST().getSubtreeWithPathRec(path.substring(1));

		} else {

			if (this.getRightBST().isEmpty())
				throw new NoSuchElementException();

			return this.getRightBST().getSubtreeWithPathRec(path.substring(1));
		}
	}

	/**
	 * Acumula en orden descendente, una lista con los pares 'padre-hijo' en este
	 * árbol.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	 * 
	 * 10 | 5 | | 2 | | | ∅ | | | ∅ | | ∅ | 20 | | ∅ | | 30 | | | ∅ | | | ∅
	 * 
	 * el resultado sería una lista de cadenas:
	 * 
	 * [(20,30), (10,20), (10,5), (5,2)]
	 * 
	 * y además quedaría etiquetado como:
	 * 
	 * {10 [(descend, 3)], {5 [(descend, 4)], {2 [(descend, 5)], ∅, ∅}, ∅}, {20
	 * [(descend, 2)], ∅, {30 [(descend, 1)], ∅, ∅}}}
	 * 
	 * @param buffer lista con el resultado.
	 */
	public void parentChildPairsTagDescend(List<String> buffer) {
		if (this.getContent() != null) {

			this.parentChildPairsTagDescendRec(buffer, new int[1]);
		}

	}

	private void parentChildPairsTagDescendRec(List<String> buffer, int[] n) {

		if (!this.isEmpty()) {

			this.getRightBST().parentChildPairsTagDescendRec(buffer, n);
			n[0]++;
			this.setTag("descend", n[0]);

			if (!this.getRightBST().isEmpty()) {

				String AnhadirBuffer = "(" + this.content + ", " + this.getRightBST().content + ")";
				buffer.add(AnhadirBuffer);
			}
			if (!this.getLeftBST().isEmpty()) {
				String AnhadirBuffer = "(" + this.content + ", " + this.getLeftBST().content + ")";
				buffer.add(AnhadirBuffer);

			}

			this.getLeftBST().parentChildPairsTagDescendRec(buffer, n);

		}

		// TODO Implementar el mÃ©todo
	}

	/**
	 * Importante: Solamente se debe recorrer el Ã¡rbol una vez
	 * 
	 * Comprueba si los elementos de la lista coinciden con algÃºn camino desde la
	 * raiz. AdemÃ¡s, si existe algÃºn camino que coincida con los elementos de la
	 * lista, los etiqueta en el Ã¡rbol, numerandolos empezando por la raiz como 1.
	 * 
	 * Por ejemplo, el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * si path = [50, 30, 10]
	 * 
	 * devolverÃ­a true y el Ã¡rbol quedarÃ­a asÃ­ etiquetado:
	 * 
	 * {50 [(path, 1)], {30 [(path, 2)], {10 [(path, 3)], âˆ…, âˆ…}, {40, âˆ…,
	 * âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * Para el mismo Ã¡rbol, si path es [50, 40] devolverÃ­a true y el Ã¡rbol
	 * quedarÃ­a asÃ­ etiquetado: {50 [(path, 1)], {30, {10, âˆ…, âˆ…}, {40 [(path,
	 * 2)], âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * Para el mismo Ã¡rbol, si path es [50, 80] devolverÃ­a false y el Ã¡rbol no se
	 * etiqueta: {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…},
	 * âˆ…}}
	 * 
	 * 
	 * @return true si los elementos de la lista coinciden con algÃºn camino desde
	 *         la raiz, falso si no es asÃ­
	 */
	public boolean isPathIn(List<T> path) {

		T aux = path.get(0);

		int contador = 1;
		if (this.content.compareTo(aux) == 0) {
			this.setTag("path", 1);
			BinarySearchTreeADTImpl<T> hijos = emptyBST();
			aux = path.get(contador);
			if (this.getRightBST().content.compareTo(aux) == 0) {
				hijos = this.getRightBST();
				hijos.setTag("path", contador+1);
			} else if (this.getLeftBST().content.compareTo(aux) == 0) {
				hijos = this.getLeftBST();
				hijos.setTag("path", contador +1);
			} else {
				return false;
			}
			contador++;
			while (hijos.content != null && path.size() != contador) {

				aux = path.get(contador);
				if (hijos.getRightBST().content.compareTo(aux) == 0) {
					hijos = hijos.getRightBST();
					hijos.setTag("path", contador +1);
				} else if (hijos.getLeftBST().content.compareTo(aux) == 0) {
					hijos = hijos.getLeftBST();
					hijos.setTag("path", contador +1);
				} else {
					return false;
				}
				contador++;

			}

		} else {

			return false;
		}
		return true;
	}

	/**
	 * 
	 * Etiqueta cada nodo con su posiciÃ³n en el recorrido en anchura, con la
	 * etiqueta "width"
	 * 
	 * Por ejemplo, el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * queda etiquetado como
	 * 
	 * {50 [(width, 1)], {30 [(width, 2)], {10 [(width, 4)], âˆ…, âˆ…},{40 [(width,
	 * 5)], âˆ…, âˆ…}}, {80 [(width, 3)], {60 [(width, 6)], âˆ…, âˆ…}, âˆ…}}
	 * 
	 * 
	 */
	public void tagWidth() {
		BinarySearchTreeADTImpl<T> arbol = emptyBST();
		arbol.content = this.content;
		arbol.setLeftBST(this.getLeftBST());
		arbol.setRightBST(this.getRightBST());
		int i = 1;
		boolean flag = true;

		this.setTag("width", 1);
		if (arbol.getLeftBST() != null) {
			arbol.getLeftBST().setTag("width", 2);
			arbol.getLeftBST().tagWidthrec(2);
		}

		if (arbol.getRightBST() != null) {
			arbol.getRightBST().setTag("width", 2);
			arbol.getRightBST().tagWidthrec(2);
		}

	
	}

	public void tagWidthrec(int i) {
		i++;
		if (this.getLeftBST() != null) {
			this.getLeftBST().setTag("width", i);
			this.getLeftBST().tagWidthrec(i);
		}

		if (this.getRightBST() != null) {
			this.getRightBST().setTag("width", i);
			this.getRightBST().tagWidthrec(i);
		}

	}

	/**
	 * Devuelve un iterador que recorre los elementos del arbol en inorden (de menor
	 * a mayor)
	 * 
	 * Por ejemplo, con el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * y devolverÃ­a el iterador que recorrerÃ­a los ndos en el orden: 10, 30, 40,
	 * 50, 60, 80
	 * 
	 * 
	 * 
	 * @return iterador para el recorrido inorden o ascendente
	 */
	public Iterator<T> iteratorInorden() {

		
		if(this.isEmpty()){
			return null;
		}

		return new InordenIterator();	
	}
	
	private class InordenIterator implements Iterator<T> {

        int i;
        List<BinarySearchTreeADTImpl<T>> lista;

         public InordenIterator() {
            i = 0;
            BinarySearchTreeADTImpl<T> aux = emptyBST();
            aux.content = getContent();
            aux.setLeftBST(getLeftBST());
            aux.setRightBST(getRightBST());
            lista = new ArrayList<BinarySearchTreeADTImpl<T>>();
            collecion(lista, aux);
        }

         private void collecion(List<BinarySearchTreeADTImpl<T>> coll, BinarySearchTreeADTImpl<T> aux) {

             if(aux.getLeftBST().content != null) {
                 collecion(coll, aux.getLeftBST());
             }

             coll.add(aux);
             if(aux.getRightBST().content != null) {
                 collecion(coll, aux.getRightBST());

             }
         }

        @Override
        public boolean hasNext() {

            return i < lista.size();
        }

        @Override
        public T next() {

            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            i++;

            return lista.get(i -1).content;
        }

    }

}