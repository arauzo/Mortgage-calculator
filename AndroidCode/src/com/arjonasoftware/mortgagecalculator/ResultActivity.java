package com.arjonasoftware.mortgagecalculator;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ResultActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_activity);
		// Show the Up button in the action bar.
		// setupActionBar();

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	/*
	 * private void setupActionBar() {
	 * 
	 * getActionBar().setDisplayHomeAsUpEnabled(true);
	 * 
	 * }
	 */

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
			Intent intent = new Intent(ResultActivity.this,
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

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.

			Fragment fragment = null;
			Bundle args;
			switch (position) {
			case 0:
				fragment = new Fragment1();
				args = new Bundle();
				args.putInt(Fragment1.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			case 1:
				fragment = new Fragment2();
				args = new Bundle();
				args.putInt(Fragment2.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			case 2:
				fragment = new Fragment3();
				args = new Bundle();
				args.putInt(Fragment3.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			default:
				return fragment;
			}

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class Fragment1 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public Fragment1() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment, container,
					false);

			MyApplication myApplication = (MyApplication) getActivity()
					.getApplicationContext();

			ArrayList<String> rowMonth = myApplication.getRowMonth();
			ArrayList<String> rowDebt = myApplication.getRowDebt();
			ArrayList<String> rowAmortization = myApplication
					.getRowAmortization();
			ArrayList<String> rowInterests = myApplication.getRowInterests();

			ListView lv = (ListView) rootView.findViewById(R.id.list);
			lv.setAdapter(new ListAdapter(rowMonth, rowDebt, rowAmortization,
					rowInterests));

			return rootView;
		}
	}

	public static class Fragment2 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public Fragment2() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment, container,
					false);

			MyApplication myApplication = (MyApplication) getActivity()
					.getApplicationContext();

			ArrayList<String> rowMonth = myApplication.getRowMonth();
			ArrayList<String> rowDebt = myApplication.getRowDebtApproximate();
			ArrayList<String> rowAmortization = myApplication
					.getRowAmortizationApproximate();
			ArrayList<String> rowInterests = myApplication
					.getRowInterestsApproximate();

			ListView lv = (ListView) rootView.findViewById(R.id.list);
			lv.setAdapter(new ListAdapter(rowMonth, rowDebt, rowAmortization,
					rowInterests));
			return rootView;
		}
	}

	public static class Fragment3 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public Fragment3() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_important,
					container, false);

			Button button_sign = (Button) rootView
					.findViewById(R.id.button_sign);
			button_sign.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// Perform action on click

					String url = getResources().getString(R.string.petition);

					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
				}
			});

			return rootView;
		}
	}

}
