package com.example.modernartuicobrijani;

import java.util.ArrayList;
import java.util.Random;

import com.example.coloredelement.ColoredSurface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.RggbChannelVector;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	static private final String MOMAURL = "http://www.moma.org";
	static private final String CHOOSER_TEXT = "Load " + MOMAURL + " with:";

	static private final String TAG = "ModernArtUI";

	private SeekBar sbColorChange;

	private final int numOfCElements = 5;

	private ImageView firstLeftCElement;
	private ImageView secondLeftCElement;
	private ImageView firstRightCElement;
	private ImageView secondRightCElement;
	private ImageView thirdRightCElement;

	private ColoredSurface[] coloredElements;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

		firstLeftCElement = (ImageView) findViewById(R.id.firstLeftCElement);
		secondLeftCElement = (ImageView) findViewById(R.id.SecondLeftCElement);
		firstRightCElement = (ImageView) findViewById(R.id.firstRightCElement);
		secondRightCElement = (ImageView) findViewById(R.id.SecondRightCELement);
		thirdRightCElement = (ImageView) findViewById(R.id.ThirdRightCElement);

		// connecting every image view with their respected list of random
		// colors
		coloredElements = new ColoredSurface[numOfCElements];
		coloredElements[0] = new ColoredSurface(firstLeftCElement, ColoredSurface.generateRandomColors(10));
		coloredElements[1] = new ColoredSurface(secondLeftCElement, ColoredSurface.generateRandomColors(10));
		coloredElements[2] = new ColoredSurface(firstRightCElement, ColoredSurface.generateRandomColors(10));
		coloredElements[3] = new ColoredSurface(secondRightCElement, ColoredSurface.generateRandomColors(10));
		coloredElements[4] = new ColoredSurface(thirdRightCElement, ColoredSurface.generateRandomColors(10));

		// initial color
		coloredElements[0].changeColor(0);
		coloredElements[1].changeColor(0);
		coloredElements[2].changeColor(0);
		coloredElements[3].changeColor(0);
		coloredElements[4].changeColor(0);

		sbColorChange = (SeekBar) findViewById(R.id.sBcolorChange);

		// colors from 0 to 9
		sbColorChange.setMax(9);

		sbColorChange.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				for (int i = 0; i < coloredElements.length; ++i) {
					coloredElements[i].changeColor(progress);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.more_information: {
			startDialog();
			return true;

		}
		}
		return super.onOptionsItemSelected(item);
	}

	private void startBrowser() {
		Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MOMAURL));

		Intent chooserIntent = Intent.createChooser(baseIntent, CHOOSER_TEXT);

		startActivity(chooserIntent);
	}

	private void startDialog() {
		AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
		TextView msg = new TextView(this);
		msg.setText(
				" \n Inspired by work of artists such as \n Piet Mordian and Ben Nicholson. \n \n Click below to learn more!");
		msg.setGravity(Gravity.CENTER);
		dialog.setView(msg);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Visit Moma", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startBrowser();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		dialog.show();
	}
}
