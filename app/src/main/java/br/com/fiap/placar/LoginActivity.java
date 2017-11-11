package br.com.fiap.placar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;
    private Button btConectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btConectar = (Button) findViewById(R.id.btConectar);

        btConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setLogin(etLogin.getText().toString());
                usuario.setSenha(etSenha.getText().toString());

                Intent validarLogin = new Intent(LoginActivity.this,
                        ValidaLoginActivity.class);

                validarLogin.putExtra("USUARIO", usuario);
                startActivityForResult(validarLogin, Constantes.KEY_REQUEST_CODE_VALIDA_LOGIN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case  Constantes.KEY_REQUEST_CODE_VALIDA_LOGIN:
                switch (resultCode){
                    case RESULT_OK:

                    boolean isLoginValido =  data.getBooleanExtra(Constantes.KEY_RESULT_LOGIN, false);

                    if (isLoginValido){
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else{
                        Toast.makeText(this, "Login ou senha inválido", Toast.LENGTH_SHORT).show();
                    }

                    break;
                }
                break;
        }
    }
}
