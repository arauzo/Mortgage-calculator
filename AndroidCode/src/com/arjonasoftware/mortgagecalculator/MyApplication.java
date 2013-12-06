package com.arjonasoftware.mortgagecalculator;

import java.util.ArrayList;

import android.app.Application;

/**
 * Clase que permite añadir y eliminar datos en la aplicación
 * 
 * @author Francisco Arjona Lopez
 * 
 */
public class MyApplication extends Application {
	private ArrayList<String> rowMonth = new ArrayList<String>();
	private ArrayList<String> rowDebt = new ArrayList<String>();
	private ArrayList<String> rowAmortization = new ArrayList<String>();
	private ArrayList<String> rowInterests = new ArrayList<String>();

	private ArrayList<String> rowDebtApproximate = new ArrayList<String>();
	private ArrayList<String> rowAmortizationApproximate = new ArrayList<String>();
	private ArrayList<String> rowInterestsApproximate = new ArrayList<String>();

	/**
	 * @return the rowDebt
	 */
	public ArrayList<String> getRowDebt() {
		return rowDebt;
	}

	/**
	 * @param rowDebt
	 *            the rowDebt to set
	 */
	public void setRowDebt(ArrayList<String> rowDebt) {
		this.rowDebt = rowDebt;
	}

	/**
	 * @return the rowAmortization
	 */
	public ArrayList<String> getRowAmortization() {
		return rowAmortization;
	}

	/**
	 * @param rowAmortization
	 *            the rowAmortization to set
	 */
	public void setRowAmortization(ArrayList<String> rowAmortization) {
		this.rowAmortization = rowAmortization;
	}

	/**
	 * @return the rowInterests
	 */
	public ArrayList<String> getRowInterests() {
		return rowInterests;
	}

	/**
	 * @param rowInterests
	 *            the rowInterests to set
	 */
	public void setRowInterests(ArrayList<String> rowInterests) {
		this.rowInterests = rowInterests;
	}

	/**
	 * @return the rowDebtApproximate
	 */
	public ArrayList<String> getRowDebtApproximate() {
		return rowDebtApproximate;
	}

	/**
	 * @param rowDebtApproximate
	 *            the rowDebtApproximate to set
	 */
	public void setRowDebtApproximate(ArrayList<String> rowDebtApproximate) {
		this.rowDebtApproximate = rowDebtApproximate;
	}

	/**
	 * @return the rowAmortizationApproximate
	 */
	public ArrayList<String> getRowAmortizationApproximate() {
		return rowAmortizationApproximate;
	}

	/**
	 * @param rowAmortizationApproximate
	 *            the rowAmortizationApproximate to set
	 */
	public void setRowAmortizationApproximate(
			ArrayList<String> rowAmortizationApproximate) {
		this.rowAmortizationApproximate = rowAmortizationApproximate;
	}

	/**
	 * @return the rowInterestsApproximate
	 */
	public ArrayList<String> getRowInterestsApproximate() {
		return rowInterestsApproximate;
	}

	/**
	 * @param rowInterestsApproximate
	 *            the rowInterestsApproximate to set
	 */
	public void setRowInterestsApproximate(
			ArrayList<String> rowInterestsApproximate) {
		this.rowInterestsApproximate = rowInterestsApproximate;
	}

	/**
	 * @return the rowMonth
	 */
	public ArrayList<String> getRowMonth() {
		return rowMonth;
	}

	/**
	 * @param rowMonth the rowMonth to set
	 */
	public void setRowMonth(ArrayList<String> rowMonth) {
		this.rowMonth = rowMonth;
	}

}
