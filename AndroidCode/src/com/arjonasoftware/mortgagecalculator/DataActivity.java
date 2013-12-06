package com.arjonasoftware.mortgagecalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends Activity {

	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);

		final EditText editText_mortgage_capital = (EditText) findViewById(R.id.editText_mortgage_capital);
		final EditText editText_annual_interest = (EditText) findViewById(R.id.editText_annual_interest);
		final EditText editText_years = (EditText) findViewById(R.id.editText_years);

		editText_mortgage_capital.clearFocus();

		editText_mortgage_capital.addTextChangedListener(TW);
		editText_annual_interest.addTextChangedListener(TW);
		editText_years.addTextChangedListener(TW);

		Button button_see_amortization_tables = (Button) findViewById(R.id.button_see_amortization_tables);
		button_see_amortization_tables
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// Perform action on click

						String s_mortgageCapital = editText_mortgage_capital
								.getText().toString();
						String s_annualInterest = editText_annual_interest
								.getText().toString();
						String s_years = editText_years.getText().toString();

						if (validate(s_mortgageCapital)
								&& validate(s_annualInterest)
								& validate(s_years)) {

							new Calculate().execute(s_mortgageCapital,
									s_annualInterest, s_years);

						} else {
							Toast.makeText(
									getApplicationContext(),
									getResources().getString(
											R.string.data_incorrect),
									Toast.LENGTH_LONG).show();
						}
					}
				});

		Button button_why_important = (Button) findViewById(R.id.button_why_important);
		button_why_important.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataActivity.this,
						ImportantActivity.class);
				startActivity(intent);
			}
		});

	}

	public boolean validate(String s) {

		if (!(s.trim().length() == 0) && Float.parseFloat(s) > 0)
			return true;
		else
			return false;
	}

	private TextWatcher TW = new TextWatcher() {

		public boolean validate(String s) {

			if (!(s.trim().length() == 0) && Float.parseFloat(s) > 0)
				return true;
			else
				return false;
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			EditText editText_mortgage_capital = (EditText) findViewById(R.id.editText_mortgage_capital);
			EditText editText_annual_interest = (EditText) findViewById(R.id.editText_annual_interest);
			EditText editText_years = (EditText) findViewById(R.id.editText_years);

			String s_mortgageCapital = editText_mortgage_capital.getText()
					.toString();
			String s_annualInterest = editText_annual_interest.getText()
					.toString();
			String s_years = editText_years.getText().toString();

			TextView interest_good = (TextView) findViewById(R.id.TextView_interest_good);
			TextView interest_bad = (TextView) findViewById(R.id.TextView_interest_bad);
			TextView monthly_fee_good = (TextView) findViewById(R.id.TextView_monthly_fee_good);
			TextView monthly_fee_bad = (TextView) findViewById(R.id.TextView_monthly_fee_bad);
			TextView payment_more = (TextView) findViewById(R.id.TextView_payment_more);
			TextView payment_more_lending = (TextView) findViewById(R.id.TextView_payment_more_lending);

			if (validate(s_mortgageCapital) && validate(s_annualInterest)
					& validate(s_years)) {
				Double mortgage_capital = Double.parseDouble(s_mortgageCapital);
				Double annual_interest = (Double.parseDouble(s_annualInterest)) / 100;
				Double years = Double.parseDouble(s_years);

				double monthlyInterest = Math
						.pow(1 + annual_interest, 1f / 12f) - 1;
				double monthlyInterestApproximate = annual_interest / 12;

				double monthlyFee = (mortgage_capital * monthlyInterest)
						/ (1 - 1 / Math.pow(1f + monthlyInterest, 12f * years));

				double monthlyFeeApproximate = (mortgage_capital * monthlyInterestApproximate)
						/ (1 - 1 / Math.pow(1f + monthlyInterestApproximate,
								12f * years));

				DecimalFormat df2 = new DecimalFormat("0.00");
				DecimalFormat df3 = new DecimalFormat("0.000");

				Currency currency = Currency.getInstance(Locale.getDefault());
				String symbolCurrency = currency.getSymbol();

				interest_good.setText(getResources().getString(
						R.string.interest_good)
						+ " " + df3.format(monthlyInterest * 100) + "%");
				interest_bad.setText(getResources().getString(
						R.string.interest_bad)
						+ " "
						+ df3.format(monthlyInterestApproximate * 100)
						+ "%");

				monthly_fee_good.setText(getResources().getString(
						R.string.monthly_fee_good)
						+ " "
						+ df2.format(monthlyFee)
						+ " "
						+ symbolCurrency
						+ "/" + getResources().getString(R.string.month_minus));

				monthly_fee_bad.setText(getResources().getString(
						R.string.monthly_fee_bad)
						+ " "
						+ df2.format(monthlyFeeApproximate)
						+ " "
						+ symbolCurrency
						+ "/"
						+ getResources().getString(R.string.month_minus));

				double d_payment_more = (monthlyFeeApproximate - monthlyFee) * 12;

				payment_more.setText(getResources().getString(
						R.string.payment_more)
						+ " " + df2.format(d_payment_more) + symbolCurrency);
				payment_more_lending.setText(getResources().getString(
						R.string.payment_more_lending)
						+ " "
						+ df2.format(d_payment_more * years)
						+ symbolCurrency);
			} else {
				interest_good.setText(getResources().getString(
						R.string.interest_good));
				interest_bad.setText(getResources().getString(
						R.string.interest_bad));

				monthly_fee_good.setText(getResources().getString(
						R.string.monthly_fee_good));
				monthly_fee_bad.setText(getResources().getString(
						R.string.monthly_fee_bad));

				payment_more.setText(getResources().getString(
						R.string.payment_more));
				payment_more_lending.setText(getResources().getString(
						R.string.payment_more_lending));
			}
		}

	};

	private class Calculate extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String s_mortgageCapital = params[0];
			String s_annualInterest = params[1];
			String s_years = params[2];

			float mortgageCapital = Float.parseFloat(s_mortgageCapital);
			float annualInterest = (Float.parseFloat(s_annualInterest)) / 100;
			int years = Integer.parseInt(s_years);

			double monthlyInterest = Math.pow(1 + annualInterest, 1f / 12f) - 1;
			double monthlyFee = (mortgageCapital * monthlyInterest)
					/ (1 - 1 / Math.pow(1f + monthlyInterest, 12f * years));

			double monthlyInterestApproximate = annualInterest / 12;
			double monthlyFeeApproximate = (mortgageCapital * monthlyInterestApproximate)
					/ (1 - 1 / Math.pow(1f + monthlyInterestApproximate,
							12f * years));

			ArrayList<String> rowMonth = new ArrayList<String>();
			ArrayList<String> rowDebt = new ArrayList<String>();
			ArrayList<String> rowAmortization = new ArrayList<String>();
			ArrayList<String> rowInterests = new ArrayList<String>();
			double interestsCount = 0;

			ArrayList<String> rowDebtApproximate = new ArrayList<String>();
			ArrayList<String> rowAmortizationApproximate = new ArrayList<String>();
			ArrayList<String> rowInterestsApproximate = new ArrayList<String>();
			double interestsCountApproximate = 0;

			DecimalFormat df2 = new DecimalFormat("0.00");

			double DRowInterests, DRowInterests2;
			double DRowAmortization, DRowAmortization2;
			double DRowDebt = mortgageCapital, DRowDebt2 = mortgageCapital;

			int tenPercentProgressBar = (12 * years) / 10;
			int progressBar = 0;
			int progressBarIterations = tenPercentProgressBar;
			for (int i = 1; i <= 12 * years; i++) {
				rowMonth.add("" + i);

				// CORRECT
				DRowInterests = DRowDebt * monthlyInterest;
				interestsCount = interestsCount + DRowInterests;

				DRowAmortization = monthlyFee - DRowInterests;

				DRowDebt = DRowDebt - DRowAmortization;

				rowDebt.add(df2.format(DRowDebt));
				rowAmortization.add(df2.format(DRowAmortization));
				rowInterests.add(df2.format(DRowInterests));

				// APPROXIMATE
				DRowInterests2 = DRowDebt2 * monthlyInterestApproximate;
				interestsCountApproximate = interestsCountApproximate
						+ DRowInterests2;

				DRowAmortization2 = monthlyFeeApproximate - DRowInterests2;

				DRowDebt2 = DRowDebt2 - DRowAmortization2;

				rowDebtApproximate.add(df2.format(DRowDebt2));
				rowAmortizationApproximate.add(df2.format(DRowAmortization2));
				rowInterestsApproximate.add(df2.format(DRowInterests2));

				if (i == progressBarIterations) {
					progressBar += 10;
					progressBarIterations += tenPercentProgressBar;
					publishProgress(progressBar);
				}
			}

			Currency currency = Currency.getInstance(Locale.getDefault());
			String symbolCurrency = currency.getSymbol();

			rowMonth.add("");
			rowDebt.add("");
			rowDebtApproximate.add("");
			rowAmortization.add("");
			rowAmortizationApproximate.add("");

			rowInterests.add(getResources().getString(R.string.amount) + "\n"
					+ df2.format(interestsCount) + " " + symbolCurrency);
			rowInterestsApproximate.add(getResources().getString(
					R.string.amount)
					+ "\n"
					+ df2.format(interestsCountApproximate)
					+ " "
					+ symbolCurrency);

			MyApplication myApplication = (MyApplication) getApplicationContext();

			myApplication.setRowMonth(rowMonth);
			myApplication.setRowDebt(rowDebt);
			myApplication.setRowDebtApproximate(rowDebtApproximate);

			myApplication.setRowAmortization(rowAmortization);
			myApplication
					.setRowAmortizationApproximate(rowAmortizationApproximate);

			myApplication.setRowInterests(rowInterests);
			myApplication.setRowInterestsApproximate(rowInterestsApproximate);

			return true;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			int progreso = values[0].intValue();

			pDialog.setProgress(progreso);
		}

		@Override
		protected void onPreExecute() {
			pDialog = new ProgressDialog(DataActivity.this);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setMessage(getResources().getString(R.string.calculating));
			pDialog.setCancelable(true);
			pDialog.setProgress(0);
			pDialog.show();
			pDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					cancel(true);
				}
			});
		}

		@Override
		protected void onPostExecute(Boolean b) {
			if (pDialog.isShowing())
				pDialog.dismiss();

			Intent intent = new Intent(DataActivity.this, ResultActivity.class);
			startActivity(intent);
		}

		@Override
		protected void onCancelled() {
			if (pDialog.isShowing())
				pDialog.dismiss();
			Toast.makeText(DataActivity.this, "Cancelado", Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_important:
			Intent intent = new Intent(DataActivity.this,
					ImportantActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_share:
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
					getResources().getString(R.string.share_text));
			startActivity(Intent.createChooser(sharingIntent,
					"Compartir mediante"));
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

}