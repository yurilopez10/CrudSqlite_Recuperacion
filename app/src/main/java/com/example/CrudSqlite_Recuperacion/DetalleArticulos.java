package com.example.CrudSqlite_Recuperacion;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetalleArticulos extends AppCompatActivity {

    private TextView tv_codigo, tv_descripcion, tv_precio;
    private TextView tv_codigo1, tv_descripcion1, tv_precio1, tv_fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulos);

        Toolbar TDetalleArti = (Toolbar) findViewById(R.id.TDetalleArticulo);
        setSupportActionBar(TDetalleArti);

        tv_codigo1 = (TextView)findViewById(R.id.tv_codigo1);
        tv_descripcion1 = (TextView)findViewById(R.id.tv_descripcion1);
        tv_precio1 = (TextView)findViewById(R.id.tv_precio1); tv_fecha = (TextView)findViewById(R.id.tv_fecha);

        Bundle objeto = getIntent().getExtras();
        Dto dto = null;

        if(objeto != null){

        }
            dto = (Dto)objeto.getSerializable("articulo");
            tv_codigo1.setText(""+dto.getCodigo());

        tv_descripcion1.setText(dto.getDescripcion());
        tv_precio1.setText(String.valueOf(dto.getPrecio()));
        tv_fecha.setText("Fecha de creación: "+getDateTime());
    }

    private void setSupportActionBar(Toolbar tDetalleArti) {
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a", Locale.getDefault());
    Date date = new Date();
    return dateFormat.format(date);
    }
}
