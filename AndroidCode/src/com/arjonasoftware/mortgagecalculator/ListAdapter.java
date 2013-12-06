package com.arjonasoftware.mortgagecalculator;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Esta clase es un Adapter para representar Rectangulos en ListView.
 * 
 * Lo que tendremos que hacer será heredar de BaseAdapter e implementar los
 * métodos que faltan.
 * 
 * @author Miguel Ángel López
 */
public class ListAdapter extends BaseAdapter {

	/**
	 * Aquí guardaremos todos los rectángulos que queremos representar en
	 * nuestro ListView. Es recomendable usar sistemas con acceso directo por
	 * posición, como el ArrayList, un Vector, un Array...
	 */
	ArrayList<String> rowMonth;
	ArrayList<String> rowDebt;
	ArrayList<String> rowAmortization;
	ArrayList<String> rowInterests;

	/**
	 * El constructor
	 * 
	 * @param rectangulos
	 */
	public ListAdapter(ArrayList<String> rowMonth, ArrayList<String> rowDebt,
			ArrayList<String> rowAmortization, ArrayList<String> rowInterests) {
		this.rowMonth = rowMonth;
		this.rowDebt = rowDebt;
		this.rowAmortization = rowAmortization;
		this.rowInterests = rowInterests;

		// Cada vez que cambiamos los elementos debemos noficarlo
		notifyDataSetChanged();
	}

	/**
	 * Este método simplemente nos devuelve el número de elementos de nuestro
	 * ListView. Evidentemente es el tamaño del arraylist
	 */
	public int getCount() {
		return rowMonth.size();
	}

	/**
	 * Este método nos devuele el elemento de una posición determinada. El
	 * elemento es el Rectángulo, así que...
	 */
	public Object getItem(int position) {
		return rowMonth.get(position);
	}

	/**
	 * Aquí tenemos que devolver el ID del elemento. Del ELEMENTO, no del View.
	 * Por lo general esto no se usa, así que return 0 y listo.
	 */
	public long getItemId(int position) {
		return 0;
	}

	/**
	 * El método más complicado. Aquí tenemos que devolver el View a
	 * representar. En este método nos pasan 3 valores. El primero es la
	 * posición del elemento, el segundo es el View a utilizar que será uno que
	 * ya no esté visible y que lo reutilizaremos, y el último es el ViewGroup,
	 * es en nuestro caso, el ListView.
	 */

	public View getView(int position, View convertView, ViewGroup parent) {
		/**
		 * Lo primero que haremos es comprobar si el View ya existe y tenemos
		 * que reciclarlo o por el contrario debemos crear uno nuevo.
		 */

		RowView view;
		if (convertView == null) // NO existe, creamos uno
			view = new RowView(parent.getContext());
		else
			// Existe, reutilizamos
			view = (RowView) convertView;

		/**
		 * Ahora tenemos que darle los valores correctos, para ello usamos el
		 * método setRectangulo pasándole el rectángulo a mostrar y finalmente
		 * devolvemos el view.
		 */

		view.setRow(rowMonth.get(position), rowDebt.get(position),
				rowAmortization.get(position), rowInterests.get(position));

		return view;
	}
}