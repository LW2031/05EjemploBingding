package lei.wang.a05ejemplobingding;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import lei.wang.a05ejemplobingding.databinding.ActivityMainBinding;
import lei.wang.a05ejemplobingding.modelos.Alumno;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Alumno> listaAlumnos;
    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        listaAlumnos = new ArrayList<>();

        inicializarLauncher();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lanzar la actividad AddAlumno
                launcherAlumno.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));
            }
        });
    }

    private void inicializarLauncher() {
        launcherAlumno = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                                listaAlumnos.add(alumno);
                                Toast.makeText(MainActivity.this, alumno.toString(), Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(MainActivity.this, "Ops no funcionado", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "ACCIÓN CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /**
     *
     * TODO:
     * 1. Elemento para mostrar la informacion del alumno en el principal (TextView)
     * 2. El conjunto de datos a mostrar (listaAlumnos)
     * 3. Contenedor para poner cada elemento alumno (Scroll)
     * 4. La lógica para mostrar los elementos en el Scroll del principal
     */

}