package com.arjonasoftware.mortgagecalculator;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Esta clase es un Adapter para representar Rectangulos en ListView.
 * 
 * Lo que tendremos que hacer ser� heredar de BaseAdapter e implementar los
 * m�todos que faltan.
 * 
 * @author Miguel �ngel L�pez
 */
public class ListAdapter extends BaseAdapter {

	/**
	 * Aqu� guardaremos todos los rect�ngulos que queremos representar en
	 * nuestro ListView. Es recomendable usar sistemas con acceso directo por
	 * posici�n, como el ArrayList, un Vector, un Array...
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
	 * Este m�todo simplemente nos devuelve el n�mero de elementos de nuestro
	 * ListView. Evidentemente es el tama�o del arraylist
	 */
	public int getCount() {
		return rowMonth.size();
	}

	/**
	 * Este m�todo nos devuele el elemento de una posici�n determinada. El
	 * elemento es el Rect�ngulo, as� que...
	 */
	public Object getItem(int position) {
		return rowMonth.get(position);
	}

	/**
	 * Aqu� tenemos que devolver el ID del elemento. Del ELEMENTO, no del View.
	 * Por lo general esto no se usa, as� que return 0 y listo.
	 */
	public long getItemId(int position) {
		return 0;
	}

	/**
	 * El m�todo m�s complicado. Aqu� tenemos que devolver el View a
	 * representar. En este m�todo nos pasan 3 valores. El primero es la
	 * posici�n del elemento, el segundo es el View a utilizar que ser� uno que
	 * ya no est� visible y que lo reutilizaremos, y el �ltimo es el ViewGroup,
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
		 * m�todo setRectangulo pas�ndole el rect�ngulo a mostrar y finalmente
		 * devolvemos el view.
		 */

		view.setRow(rowMonth.get(position), rowDebt.get(position),
				rowAmortization.get(position), rowInterests.get(position));

		return view;
	}
}