package co.edu.icesi.woolf.practico2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Votar extends AppCompatActivity {

    Spinner sp_Heroe,sp_Grupo;
    private Button btn_Aceptar,btn_Cancelar;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);

        sp_Grupo = findViewById(R.id.sp_Grupo);

        ArrayList<String> grupo = new ArrayList<>();
        String a = getString(R.string.TodoElPublico);
        grupo.add(a);
        a = getString(R.string.MujeresAdultas);
        grupo.add(a);
        a = getString(R.string.HombresAdultos);
        grupo.add(a);
        a = getString(R.string.MujeresAdolecentes);
        grupo.add(a);
        a = getString(R.string.HombresAdolecentes);
        grupo.add(a);
        a = getString(R.string.Niños);
        grupo.add(a);

        i = getIntent();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grupo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Grupo.setAdapter(adapter);

        sp_Heroe = findViewById(R.id.sp_Heroe);

        ArrayList<String> heroe = new ArrayList<>();
        a = getString(R.string.Spiderman);
        heroe.add(a);
        a = getString(R.string.IronMan);
        heroe.add(a);
        a = getString(R.string.CapitanAmerica);
        heroe.add(a);
        a = getString(R.string.CapitanaMarvel);
        heroe.add(a);
        a = getString(R.string.Hulk);
        heroe.add(a);
        a = getString(R.string.LaViudaNegra);
        heroe.add(a);
        a = getString(R.string.Thor);
        heroe.add(a);
        a = getString(R.string.DoctorStrange);
        heroe.add(a);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, heroe);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Heroe.setAdapter(adapter2);

        btn_Aceptar = findViewById(R.id.btn_Aceptar);
        btn_Aceptar.setOnClickListener(v->{
            i.putExtra("grupo",sp_Grupo.getSelectedItem().toString());
            i.putExtra("heroe",sp_Heroe.getSelectedItem().toString());
            setResult(RESULT_OK,i);
            finish();
        });
        btn_Cancelar = findViewById(R.id.btn_Cancelar);
        btn_Cancelar.setOnClickListener(v->{
            setResult(RESULT_CANCELED,i);
            finish();
        });



    }




}
