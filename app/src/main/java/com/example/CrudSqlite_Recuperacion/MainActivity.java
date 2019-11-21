package com.example.CrudSqlite_Recuperacion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.CrudSqlite_Recuperacion.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnGuardar, btnConsultar, btnConsultar1, btnModificar, btnEliminar;
    private EditText etcodigo, etprecio, etdescripcion;

    boolean estadoCodigo = false;
    boolean estadoDescripcion = false;
    boolean estadoPrecio = false;
    int estadoInsert = 0;
    final Context context = this;

    Modal ventanas = new Modal();
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();
    AlertDialog.Builder dialogo;

    public void limpiarD(View view) {
        etcodigo.setText(null);
        etdescripcion.setText(null);
        etprecio.setText(null);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Warning")
                    .setMessage("¿Realmente desea cerrar esta actividad?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion

                        public void onClick(DialogInterface dialog, int which) {
                            //*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class);
                            // startActivity(intent);*
                            // / //MainActivity.this.finishAffinity();
                            // finish();
                            finishAffinity();
                        }
                    })
                    .show();
            // Si el listener devuelve true, significa que el evento esta procesado, y nadie debe hacer nada mas
            return true;
        } //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.salir));
        toolbar.setTitleTextColor(getResources().getColor(R.color.tituloColor));
        toolbar.setTitleMargin(0, 0, 0, 0);
        setSupportActionBar(toolbar);

        //y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                confirmacion();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
                ventanas.VentanaEmergente(MainActivity.this);
            }
        });

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnConsultar1 = (Button) findViewById(R.id.btnConsultar1);
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        etcodigo = (EditText) findViewById(R.id.etcodigo);
        etdescripcion = (EditText) findViewById(R.id.etdescripcion);
        etprecio = (EditText) findViewById(R.id.etprecio);


        String senal = "";
        String codigo = "";
        String descripcion = "";
        String precio = "";

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                codigo = bundle.getString("codigo");
                senal = bundle.getString("senal");
                descripcion = bundle.getString("descripcion");
                precio = bundle.getString("precio");
                if (senal.equals("1")) {
                    etcodigo.setText(codigo);
                    etdescripcion.setText(descripcion);
                    etprecio.setText(precio);
                }
            }
        } catch (Exception e) {

        }
    }

    private void confirmacion() {
        String mensaje = "¿Realmente desea cerrar esta pantalla?";
        dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setIcon(R.drawable.ic_close);
        dialogo.setTitle("Está seguro");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) { /*Intent intent = new Intent(DashboardLuces.this, luces_control_sms.class); startActivity(intent);*/
                //DashboardLuces.this.finishAffinity();
                MainActivity.this.finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                // Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        dialogo.show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_listaArticulos) {
            Intent spinnerActivity = new Intent(MainActivity.this,consultaSpinner.class);
            startActivity(spinnerActivity);
            return true;

        } else if (id == R.id.action_listaArticulos1) {
            Intent listViewActivity = new Intent(MainActivity.this,ListViewArticulos.class);

            startActivity(listViewActivity);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Acercade){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            // Establecer el título
            alertDialogBuilder.setTitle("Proyecto creado por:");
            // Establecer mensaje de diálogo
            alertDialogBuilder
                    .setMessage("Kathy Flores \nSis 21B")
                    .setCancelable(false)
                    .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Si presiona que Aceptar se cerrara el mensaje de dialogo
                            dialog.cancel();
                        }
                    });
            // Crear mensaje AlertDialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // Mostrar alert
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardar(View v) {

        if (etcodigo.getText().toString().length() == 0) {
            estadoCodigo = false;
            etcodigo.setError("Campo obligatorio");
        } else {
            estadoCodigo = true;
        }

        if (etdescripcion.getText().toString().length() == 0) {
            estadoDescripcion = false;
            etdescripcion.setError("Campo obligatorio");
        } else {
            estadoDescripcion = true;
        }

        if (etprecio.getText().toString().length() == 0) {
            estadoPrecio = false;
            etprecio.setError("Campo obligatorio");
        } else {
            estadoPrecio = true;
        }
        if (estadoCodigo && estadoDescripcion && estadoPrecio) {
            // Toast.makeText(this, "Vamos bien", Toast.LENGTH_LONG_SHORT).show();

            try {
                datos.setCodigo(Integer.parseInt(etcodigo.getText().toString()));
                datos.setDescripcion(etdescripcion.getText().toString());
                datos.setPrecio(Double.parseDouble(etprecio.getText().toString()));
                //if(conexion.insertardatos(datos)){ //if(conexion.InsertRegister(datos)){
                if (conexion.InserTradicional(datos)) {
                    Toast.makeText(this, "Registro agregado satisfactoriamente!", Toast.LENGTH_SHORT).show();
                    limpiarDatos();
                } else {
                    Toast.makeText(getApplicationContext(), "Error. Ya existe un registro\n" + " Código: " + etcodigo.getText().toString(), Toast.LENGTH_LONG).show();
                    limpiarDatos();
                }
            } catch (Exception e) {
                Toast.makeText(this, "ERROR. Ya existe.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarDatos() {
        etcodigo.setText(null);
        etdescripcion.setText(null);
        etprecio.setText(null);
        etcodigo.requestFocus();
    }

    public void consultaporcodigo(View v) {

        if (etcodigo.getText().toString().length() == 0) {
            etcodigo.setError("Campo obligatorio");
            estadoCodigo = false;
        } else {
            estadoCodigo = true;
        }

        if (estadoCodigo) {
            String codigo = etcodigo.getText().toString();
            datos.setCodigo(Integer.parseInt(codigo));
            //if(conexion.consultaCodigo(datos)){
            if (conexion.consultaArticulos(datos)) {
                etdescripcion.setText(datos.getDescripcion());
                etprecio.setText("" + datos.getPrecio());
                //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        } else {
            Toast.makeText(this, "Ingrese el código del articulo a buscar.", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultapordescripcion(View v) {
        if (etdescripcion.getText().toString().length() == 0) {
            etdescripcion.setError("Campo obligatorio");
            estadoDescripcion = false;
        } else {
            estadoDescripcion = true;
        }
        if (estadoDescripcion) {
            String descripcion = etdescripcion.getText().toString();
            datos.setDescripcion(descripcion);
            if (conexion.consultarDescripcion(datos)) {
                etcodigo.setText("" + datos.getCodigo());
                etdescripcion.setText(datos.getDescripcion());
                etprecio.setText("" + datos.getPrecio()); //Toast.makeText(this, "Se encontro uno", Toast.LENGTH_SHORT).show();

            }else{ Toast.makeText(this, "No existe un artículo con dicha descripción", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        } else {
            Toast.makeText(this, "Ingrese la descripción del articulo a buscar.", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarporcodigo(View v) {
        if(etcodigo.getText().toString().length()==0){
            etcodigo.setError("campo obligatorio");
            estadoCodigo = false;

        }else { estadoCodigo=true; }

        if(estadoCodigo){
            String cod = etcodigo.getText().toString();
            datos.setCodigo(Integer.parseInt(cod));
            if(conexion.ElimiarPORCodigo(MainActivity.this,datos)){ //Toast.makeText(this, "Registro eliminado satisfactoriamente.", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }else{
                Toast.makeText(this, "No existe un artículo con dicho código.", Toast.LENGTH_SHORT).show();
                limpiarDatos();
            }
        }
    }


    public void modificacion(View v) {
        if(etcodigo.getText().toString().length()==0){
            etcodigo.setError("campo obligatorio");
            estadoCodigo = false;

        }else { estadoCodigo=true;
        }

        if(estadoCodigo) {
            String cod = etcodigo.getText().toString();
            String descripcion = etdescripcion.getText().toString();
            double precio = Double.parseDouble(etprecio.getText().toString());
            datos.setCodigo(Integer.parseInt(cod));
            datos.setDescripcion(descripcion);
            datos.setPrecio(precio);

            if(conexion.modificar(datos)){
                Toast.makeText(this, "Registro Modificado Correctamente.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se han encontrado resultados para la busqueda especificada.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
