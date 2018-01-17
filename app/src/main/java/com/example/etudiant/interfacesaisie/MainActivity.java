package com.example.etudiant.interfacesaisie;

import android.Manifest;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity implements View.OnClickListener{
	private Button bChoice1;
	private Button bChoice2;
	private Button bChoice3;
	private Button bSave;
	private Button bReset;
	private ToggleButton bToggle;  //En mode Démonstration par défault
	private Button bCancelChoice;
	private DrawingView mDrawingView;
    private TextView texteDescription;
	private boolean mode;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutmain);
		initializeUI();
		setListeners();
		ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},101);
	}

	// Création des objets représentant la zone de dessin, la zone de texte et les boutons de sauvegarde et de remise à zéro
	private void initializeUI() {
		mDrawingView = (DrawingView) findViewById(R.id.scratch_pad);
		bChoice1 = (Button) findViewById(R.id.choice_1);
		bChoice2 = (Button) findViewById(R.id.choice_2);
		bChoice3 = (Button) findViewById(R.id.choice_3);
		bSave = (Button) findViewById(R.id.save_button);
		bReset = (Button) findViewById(R.id.reset_button);
		bCancelChoice = (Button) findViewById(R.id.annulerChoix);
		bCancelChoice.setVisibility(View.GONE);
        texteDescription = findViewById(R.id.texteDescription);
		bToggle = findViewById(R.id.toggleButton);


		bChoice1.setPaintFlags(bChoice1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		bChoice1.setVisibility(View.GONE);
		bChoice2.setPaintFlags(bChoice2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		bChoice2.setVisibility(View.GONE);
		bChoice3.setPaintFlags(bChoice3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		bChoice3.setVisibility(View.GONE);

	}

	// Création des événements d'écoute une action sur un bouton
	private void setListeners() {
		bChoice1.setOnClickListener(this);
		bChoice2.setOnClickListener(this);
		bChoice3.setOnClickListener(this);
		bSave.setOnClickListener(this);
		bReset.setOnClickListener(this);
		bCancelChoice.setOnClickListener(this);
		bToggle.setOnClickListener(this);


	}

	// Que faire si on appuie sur un bouton
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			// appuie sur le bouton de proposition 1
			case R.id.choice_1:
				this.hide_choice_button(0);  //Met à jour l'application en sachant que un choix a été fait
				break;

			// appuie sur le bouton de proposition 2
			case R.id.choice_2:
				this.hide_choice_button(0); //Met à jour l'application en sachant que un choix a été fait
				break;

			// appuie sur le bouton de proposition 3
			case R.id.choice_3:
				this.hide_choice_button(0); //Met à jour l'application en sachant que un choix a été fait
				break;

			//Change le mode de fonctionnement de l'application
			case R.id.toggleButton:
				mode = !mode;
			break;

				// appuie sur le bouton de sauvegarde
			case R.id.save_button:
			    if(mDrawingView.dessinEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.texteDesciption, Toast.LENGTH_SHORT).show();
                }
                else {
					//Traitement de l'image
                }
				break;

			// appuie sur le bouton de remise à zéro
			case R.id.reset_button:
				mDrawingView.reset();
				if(!mode){
					texteDescription.setText(R.string.texteDesciption);
				}
				break;
			case R.id.annulerChoix: //Met à jour l'application en sachant qu'il y a eu une annulation
				this.hide_choice_button(1);

		}
	}


	// Lorsque le l'utilisateur clique sur l'envoi: Cache les boutons de proposition de mots et change leurs labels
	public void hide_choice_button(int button) {
		bChoice1.setVisibility(Button.GONE);
		bChoice2.setVisibility(Button.GONE);
		bChoice3.setVisibility(Button.GONE);
		bCancelChoice.setVisibility(View.GONE);
		bSave.setVisibility(EditText.VISIBLE);
		bReset.setVisibility(EditText.VISIBLE);
        texteDescription.setVisibility(View.VISIBLE);
        mDrawingView.showmPaint();

		// Si l'utilisateur annule on ne lui supprime pas son image
		if(button==0) { // Si l'utilisateur à fait un choix parmi les mots proposé, on supprime le dessin
            resetAppuiBouton();
		}
	}

	public void resetAppuiBouton(){
        mDrawingView.reset();
    }
}