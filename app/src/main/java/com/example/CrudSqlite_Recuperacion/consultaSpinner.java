package com.example.CrudSqlite_Recuperacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class consultaSpinner extends AppCompatActivity {

    private Spinner sp_options;
    private TextView tv_cod, tv_descripcion, tv_codigo, tv_precio;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);

        Toolbar Spinner = (Toolbar) findViewById(R.id.Sppinerr);
        setSupportActionBar(Spinner);
        
        sp_options = (Spinner)findViewById(R.id.sp_options);
        tv_cod = (TextView)findViewById(R.id.tv_codigo);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_precio = (TextView)findViewById(R.id.tv_precio);

        //conexion.obtenerListaArticulos();
        conexion.consultaListaArticulos();

        //ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaArticulos);
         ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());
         sp_options.setAdapter(adaptador);

        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (position!=0){
                    tv_cod.setText("Código: "
                            +conexion.consultaListaArticulos().get(position-1).getCodigo());
                                 tv_descripcion.setText("Descripción: "
                            +conexion.consultaListaArticulos().get(position-1).getDescripcion());
                tv_precio.setText("Precio: "+String.valueOf(conexion.consultaListaArticulos().get(position-1).getPrecio()));

            } else{
                    tv_cod.setText("Código: ");
                    tv_descripcion.setText("Descripción: ");
                    tv_precio.setText("Precio: ");
                 }
            }

                @Override public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }

    private void setSupportActionBar(Toolbar spinner) {
    }
}
