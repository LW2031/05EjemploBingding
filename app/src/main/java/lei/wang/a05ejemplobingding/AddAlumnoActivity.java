package lei.wang.a05ejemplobingding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import lei.wang.a05ejemplobingding.databinding.ActivityAddAlumnoBinding;
import lei.wang.a05ejemplobingding.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {

    private ActivityAddAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //  setContentView(R.layout.activity_add_alumno);

        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Añadir lo que escriben al alumno
                Alumno alumno = crearAlumno();

                if (alumno == null){
                    Toast.makeText(AddAlumnoActivity.this, "No has rellenado todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    //Enviar la información al principal junto con resultado ok
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO",alumno);
                    intent.putExtras(bundle);

                    setResult(RESULT_OK,intent);

                    //Terminar
                    finish();
                }

            }
        });

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private Alumno crearAlumno() {
        if (binding.txtNombre.getText().toString().isEmpty() ||
                binding.txtApellidos.getText().toString().isEmpty()){
            return null;
        }

        if (binding.spCicloAlumnos.getSelectedItemPosition() == 0){
            return null;
        }

        if (!binding.rbGrupoAaddAlumno.isSelected() && !binding.rbGrupoBaddAlumno.isSelected() && !binding.rbGrupoCaddAlumno.isSelected()){
            return null;
        }
        RadioButton rb = findViewById(binding.rgGrupoAlumno.getCheckedRadioButtonId());
        char letra = rb.getText().charAt(rb.getText().length()-1);

        Alumno alumno = new Alumno(binding.txtNombre.getText().toString(),binding.txtApellidos.getText().toString(),
                binding.spCicloAlumnos.getSelectedItem().toString(), letra);

        return alumno;
    }
}