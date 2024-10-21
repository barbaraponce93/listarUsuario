package com.example.appincrementar;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appincrementar.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModel userVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        userVM=new ViewModelProvider(this).get(UserViewModel.class);
        tarea();


    }
    public void tarea(){
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre=binding.teNombre.getText().toString();
                int edad;
                if(!nombre.isEmpty()&& !binding.teEdad.getText().toString().isEmpty()){
                    try {
                        edad=Integer.parseInt(binding.teEdad.getText().toString());
                        Usuario usuario=new Usuario(nombre,edad);
                        userVM.addUser(usuario);
                        binding.teNombre.setText("");
                        binding.teEdad.setText("");

                    }catch (NumberFormatException e){
                        binding.tvVerUsuario.setText("Error");
                    }
                    binding.teNombre.setText("");
                    binding.teEdad.setText("");
                }

            }
        });
        binding.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List <Usuario> lista=userVM.getUserList();
                String texto="";
                for (Usuario u:lista){
                    texto+="Nombre: "+u.getNombre()+" Edad: "+u.getEdad()+"\n";
                }
                binding.tvVerUsuario.setText(texto);
            }
        });
    }
}