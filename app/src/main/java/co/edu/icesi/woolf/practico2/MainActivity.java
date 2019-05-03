package co.edu.icesi.woolf.practico2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final int VOTAR = 0;

    private Button btn_Votar, btn_Filtrar;

    private TextView txt_Resultado;


    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_Resultado = findViewById(R.id.txt_Resultados);

        spinner = findViewById(R.id.sp_Filtro);

        ArrayList<String> grupo = new ArrayList<>();
        String a = getString(R.string.Todos);
        grupo.add(a);
        a = getString(R.string.TodoElPublico);
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

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grupo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        btn_Votar = findViewById(R.id.btn_Votar);
        btn_Votar.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, Votar.class);
            startActivityForResult(i,VOTAR);
        });

        btn_Filtrar = findViewById(R.id.btn_Filtrar);
        btn_Filtrar.setOnClickListener(v->{
            new Thread(()->{
                new ServiceManager.UsuariosGET(responce -> runOnUiThread(()->{

                    Type tipo = new TypeToken<HashMap<String,Usuario>>(){

                    }.getType();
                    Gson g = new Gson();
                    HashMap<String,Usuario> usuarios = g.fromJson(responce, tipo);


                    if (spinner.getSelectedItem().toString()==getString(R.string.Todos)){


                        double numUsuarios = usuarios.size()+0.0;
                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;

                        for (Usuario user:usuarios.values()){

                            if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                spiderman+=1;
                            }
                            else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                ironman+=1;
                            }
                            else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                capitanAmerica+=1;
                            }
                            else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                CapitanaMarvel+=1;
                            }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                Hulk+=1;
                            }
                            else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                LaViuda+=1;
                            }
                            else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                Thor+=1;
                            }
                            else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                DoctorStrange+=1;
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });


                    }
                    else if(spinner.getSelectedItem().toString()==getString(R.string.TodoElPublico)){
                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;
                        double numUsuarios = 0.0;


                        for (Usuario user:usuarios.values()){
                            if (user.preferenciaGrupo.equalsIgnoreCase(getString(R.string.TodoElPublico))){
                                numUsuarios ++;
                                if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                    spiderman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                    ironman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                    capitanAmerica+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                    CapitanaMarvel+=1;
                                }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                    Hulk+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                    LaViuda+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                    Thor+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                    DoctorStrange+=1;
                                }
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });

                    }
                    else if(spinner.getSelectedItem().toString()==getString(R.string.MujeresAdultas)){

                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;
                        double numUsuarios = 0.0;

                        for (Usuario user:usuarios.values()){
                            if (user.preferenciaGrupo.equalsIgnoreCase(getString(R.string.MujeresAdultas))){
                                numUsuarios ++;
                                if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                    spiderman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                    ironman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                    capitanAmerica+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                    CapitanaMarvel+=1;
                                }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                    Hulk+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                    LaViuda+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                    Thor+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                    DoctorStrange+=1;
                                }
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });

                    }
                    else if(spinner.getSelectedItem().toString()==getString(R.string.HombresAdultos)){

                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;
                        double numUsuarios = 0.0;

                        for (Usuario user:usuarios.values()){
                            if (user.preferenciaGrupo.equalsIgnoreCase(getString(R.string.HombresAdultos))){
                                numUsuarios ++;
                                if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                    spiderman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                    ironman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                    capitanAmerica+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                    CapitanaMarvel+=1;
                                }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                    Hulk+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                    LaViuda+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                    Thor+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                    DoctorStrange+=1;
                                }
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });

                    }
                    else if(spinner.getSelectedItem().toString()==getString(R.string.MujeresAdolecentes)){

                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;
                        double numUsuarios = 0.0;

                        for (Usuario user:usuarios.values()){
                            if (user.preferenciaGrupo.equalsIgnoreCase(getString(R.string.MujeresAdolecentes))){
                                numUsuarios ++;
                                if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                    spiderman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                    ironman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                    capitanAmerica+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                    CapitanaMarvel+=1;
                                }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                    Hulk+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                    LaViuda+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                    Thor+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                    DoctorStrange+=1;
                                }
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });

                    }
                    else if(spinner.getSelectedItem().toString()==getString(R.string.HombresAdolecentes)){

                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;

                        double numUsuarios = 0.0;

                        for (Usuario user:usuarios.values()){
                            if (user.preferenciaGrupo.equalsIgnoreCase(getString(R.string.HombresAdolecentes))){
                                numUsuarios ++;
                                if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                    spiderman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                    ironman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                    capitanAmerica+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                    CapitanaMarvel+=1;
                                }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                    Hulk+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                    LaViuda+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                    Thor+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                    DoctorStrange+=1;
                                }
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });

                    }
                    else if(spinner.getSelectedItem().toString()==getString(R.string.Niños)){

                        double spiderman = 0,ironman = 0,capitanAmerica = 0,CapitanaMarvel=0,
                                Hulk=0,LaViuda=0,Thor=0,DoctorStrange=0;
                        double numUsuarios = 0.0;

                        for (Usuario user:usuarios.values()){
                            if (user.preferenciaGrupo.equalsIgnoreCase(getString(R.string.Niños))){
                                numUsuarios ++;
                                if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Spiderman))){
                                    spiderman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.IronMan))){
                                    ironman+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanAmerica))){
                                    capitanAmerica+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.CapitanaMarvel))){
                                    CapitanaMarvel+=1;
                                }else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Hulk))){
                                    Hulk+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.LaViudaNegra))){
                                    LaViuda+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.Thor))){
                                    Thor+=1;
                                }
                                else if (user.SuperHeroeFavorito.equalsIgnoreCase(getString(R.string.DoctorStrange))){
                                    DoctorStrange+=1;
                                }
                            }

                        }


                        String r = "Los superheroes mas populares para el publico son: \n Spiderman (" + (spiderman/numUsuarios)*100.0 + "%)\n ironman ("
                                + (ironman/numUsuarios)*100.0 + "%)\ncapitan america("+ (capitanAmerica/numUsuarios)*100.0 +"%)\n capitana marvel ("
                                + (CapitanaMarvel/numUsuarios)*100.0 + "%)\n Hulk("+ (Hulk/numUsuarios)*100.0 +"%)\nLa viuda negra("
                                + (LaViuda/numUsuarios)*100.0 +"%)\n Thor("+ (Thor/numUsuarios)*100.0 + "%)\nDoctor Strange("+ (spiderman/numUsuarios)*100.0 +"%)";

                        runOnUiThread(()->{
                            txt_Resultado.setText(r);
                        });

                    }

                }));

            }).start();

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case RESULT_OK:



                new Thread(()->{

                    Usuario usuario = new Usuario();
                    usuario.preferenciaGrupo=(data.getExtras().get("grupo").toString());
                    usuario.SuperHeroeFavorito=(data.getExtras().get("heroe").toString());
                    usuario.id=(UUID.randomUUID().toString());

                    new ServiceManager.UsuariosPOST(usuario,responce -> {

                    });

                }).start();

                break;
        }

    }
}
