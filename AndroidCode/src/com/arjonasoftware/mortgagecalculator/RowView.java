package com.arjonasoftware.mortgagecalculator;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RowView extends LinearLayout {

	private TextView textViewMes;
	private TextView textViewDeuda;

	private TextView textViewAmortizacion;
	private TextView textViewIntereses;

	public RowView(Context context) {
		super(context);
		inflate(context, R.layout.row_view, this);

		/**
		 * Es muy importante guardar las direcciones de los elementos que
		 * vayamos a cambiar pues el findViewById requiere mucho tiempo y si
		 * cada vez que hacemos scroll tenemos que buscar todos los elementos
		 * cargaremos inecesariamente el terminal y perderemos fluidez
		 */

		textViewMes = (TextView) findViewById(R.id.TextViewMes);
		textViewDeuda = (TextView) findViewById(R.id.TextViewDeuda);
		textViewAmortizacion = (TextView) findViewById(R.id.TextViewAmortizacion);
		textViewIntereses = (TextView) findViewById(R.id.TextViewIntereses);
	}

	/**
	 * Este método nos permitirá asignar los valores a los diferentes
	 * componéntes gráficos según el objeto que queramos ver.
	 * 
	 * @param rectangulo
	 */
	public void setRow(String mes, String deuda, String amortizacion,
			String intereses) {
		textViewMes.setText("" + mes);
		textViewDeuda.setText("" + deuda);
		textViewAmortizacion.setText("" + amortizacion);
		textViewIntereses.setText("" + intereses);
	}

}