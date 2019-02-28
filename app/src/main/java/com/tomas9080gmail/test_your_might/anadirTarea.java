package com.tomas9080gmail.test_your_might;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.net.Uri;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.tomas9080gmail.test_your_might.tareas.Pregunta;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.tomas9080gmail.test_your_might.Constantes.CODE_CAMERA_PERMISSION;

public class anadirTarea extends AppCompatActivity {
    private Uri uri;
    private Bitmap bitmap;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private Context context;
    private ConstraintLayout constraint;
    private ArrayAdapter <String> adapter;
    private Spinner spinnerCategoria;
    private int codigoPregunta = -1;
    private static final int REQUEST_CAPTURE_IMAGE = 200;
    Repositorio miRepo = new Repositorio();
    final String pathFotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/demoAndroid/";



    public int getCodigoPregunta() {
        return codigoPregunta;
    }

    public void setCodigoPregunta(int codigoPregunta) {
        this.codigoPregunta = codigoPregunta;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_anadir_tarea);
        super.onCreate(savedInstanceState);
        constraint = (ConstraintLayout) findViewById(R.id.constraint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button botonCategorias = (Button) findViewById(R.id.anadir_categoria);
        final ImageView photo = (ImageView)findViewById(R.id.imageView2);
        EditText pregunta1 = (EditText) findViewById(R.id.pregunta1);
        EditText pregunta2 = (EditText) findViewById(R.id.pregunta2);
        EditText pregunta3 = (EditText) findViewById(R.id.pregunta3);
        EditText pregunta4 = (EditText) findViewById(R.id.pregunta4);
        EditText enunciado = (EditText) findViewById(R.id.enunciado);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = anadirTarea.this;
        ArrayList<String>categorias = new ArrayList<>();
        Repositorio.cargarCategorias(context);
        categorias = Repositorio.getMisCategorias();
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, categorias);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategoria = (Spinner) findViewById(R.id.categoriaSpinner);
        spinnerCategoria.setAdapter(adapter);

        botonCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutActivity = LayoutInflater.from(context);
            }
        });
        if(getIntent().hasExtra("codigo") ){

            //Bundle bundle = this.getIntent().getExtras();

            codigoPregunta = getIntent().getExtras().getInt("codigo");

            Pregunta c = miRepo.encuentraPreguntaID(context,codigoPregunta);

            if (c != null) {
                System.out.println("Ee");
            }
            enunciado.setText(c.getTitulo());
            pregunta1.setText(c.getRespuestaCorrecta());
            pregunta2.setText(c.getRespuestaIncorrecta1());
            pregunta3.setText(c.getRespuestaIncorrecta2());
            pregunta4.setText(c.getRespuestaIncorrecta3());

            spinnerCategoria.setSelection(Repositorio.getMisCategorias().indexOf(c.getCategoria()));
            System.out.println("QUE COJONES");
            byte[] decodedString = Base64.decode(c.getFoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            photo.setImageBitmap(decodedByte);



        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //Rellenamos el spinner cuando se crea la pantalla



        //Añadimos un listener para el boton guardar
        final Button btnBotonSimple = (Button)findViewById(R.id.guardaPregunta);
        btnBotonSimple.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnBotonSimple.getWindowToken(), 0);
                EditText pregunta1 = (EditText) findViewById(R.id.pregunta1);
                EditText pregunta2 = (EditText) findViewById(R.id.pregunta2);
                EditText pregunta3 = (EditText) findViewById(R.id.pregunta3);
                EditText pregunta4 = (EditText) findViewById(R.id.pregunta4);
                EditText enunciado2 = (EditText) findViewById(R.id.enunciado);
                final String spinner;
                //spinner = ((Spinner) findViewById(R.id.categoriaSpinner)).getSelectedItem().toString();
                //TODO hacer las categorias
                //TODO limpiar el código
                //TODO hacer funcionamiento con camara 

                if (getCodigoPregunta() != -1) {
                    ImageView imageView= findViewById(R.id.imageView2);
                    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                    if(drawable!= null){
                        bitmap = drawable.getBitmap();
                    }else{
                        bitmap=null;
                    }
                    context = anadirTarea.this;
                    if (compruebaPermisos(context) == 1) {
                        Pregunta preguntaActualizar = new Pregunta(enunciado2.getText().toString(), pregunta1.getText().toString(), pregunta2.getText().toString(), pregunta3.getText().toString(), pregunta4.getText().toString(), getCodigoPregunta(),"php",convertirBase64(bitmap));
                        Repositorio.actualizarPregunta(context, preguntaActualizar);
                    }
                } else {

                    // Metemos en variables el String de las preguntas

                    String respuestaCorrecta = pregunta1.getText().toString();
                    String respuestaIncorrecta1 = pregunta2.getText().toString();
                    String respuestaIncorrecta2 = pregunta3.getText().toString();
                    String respuestaIncorrecta3 = pregunta4.getText().toString();
                    //Necesitamos mirar si todas las preguntas están rellenas
                    //Si una respuesta está sin rellenar, mandamos un mensaje a la snackbar
                    //Si una respuesta está sin rellenar, le cambiamos el color de fondo.
                    if (pregunta1.getText().toString().isEmpty()) {
                        pregunta1.setBackgroundColor(pregunta1.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else if (pregunta2.getText().toString().isEmpty()) {
                        pregunta2.setBackgroundColor(pregunta2.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else if (pregunta3.getText().toString().isEmpty()) {
                        pregunta3.setBackgroundColor(pregunta3.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else if (pregunta4.getText().toString().isEmpty()) {
                        pregunta4.setBackgroundColor(pregunta4.getCurrentHintTextColor());
                        Snackbar.make(view, "Rellena los campos", Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    } else {
                        //TODO arreglar mal funcionamiento permisos
                        context = anadirTarea.this;
                        if (compruebaPermisos(context) == 1) {
                            Snackbar.make(constraint, getResources().getString(R.string.write_permission_granted), Snackbar.LENGTH_LONG)
                                    .show();
                            Repositorio miRepo = new Repositorio();
                            EditText enunciado = (EditText) findViewById(R.id.enunciado);
                            String enunciadoS = enunciado.getText().toString();
                            Pregunta miPregunta = new Pregunta(enunciadoS, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3, "php",convertirBase64(bitmap));
                            miRepo.insertaPregunta(context, miPregunta);
                            uri = null;
                        } else if (compruebaPermisos(context) == 2 ){
                            Snackbar.make(constraint, " Pidiendo permisos ", Snackbar.LENGTH_LONG)
                                    .show();

                        } else if ( compruebaPermisos(context) == 0 ) {
                            Snackbar.make(constraint, "Permisos denegados, no se guardaran las preguntas.", Snackbar.LENGTH_LONG)
                                    .show();
                        }

                    }

                }
                finish();

            }


        }

        );


        //TODO boton





        final Button botonFoto = (Button) findViewById(R.id.fotoButton);
        botonFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                hazFoto();

            }
        });


        final Button botonGaleria = (Button) findViewById(R.id.galeriaButton);
        botonGaleria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                fotoGaleria();

            }
        });



    }


    public int compruebaPermisos(Context context){
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(anadirTarea.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
            } else {
                // 0 si no se obtuvieron permisos
                return 0;
            }
        } else {
            // uno si si se obtuvieron permisos
          return 1;
        }
        // 2 si se esta pidiendo los permisos
        return 2;
    }

    private void compruebaPermisosCamara() {
        int CameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (CameraPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                ActivityCompat.requestPermissions(anadirTarea.this, new String[] {Manifest.permission.CAMERA}, CODE_CAMERA_PERMISSION);
            } else {
                myLog.e("Los permisos: ","SIn permisos");
            }
        } else {
            myLog.e(",Los permisos: ","Sin permisos");
        }
    }


    public static String aBase64(Bitmap m) {
        String encoded = "";
        if (m!=null) {
            Bitmap resized = Bitmap.createScaledBitmap(m, 500, 500, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            encoded = Base64.encodeToString(b, Base64.DEFAULT);
            return encoded;
        } else {
            return encoded;
        }
    }



    private String getFileCode(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss", java.util.Locale.getDefault());
        String date = dateFormat.format(new Date());
        // Se devuelve el código
        return "pic_" + date;
    }

    private void fotoGaleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Elige tu foto"),
                REQUEST_SELECT_IMAGE);
    }



    private void hazFoto(){
        compruebaPermisosCamara();

        try {
            File dirFotos = new File(pathFotos);
            dirFotos.mkdirs();
            File fileFoto = File.createTempFile(getFileCode(),".jpg", dirFotos);
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            uri = Uri.fromFile(fileFoto);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(cameraIntent, REQUEST_CAPTURE_IMAGE);

        } catch (IOException ex) {

            ex.printStackTrace();
        }


    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        switch(requestCode)  {
            case (REQUEST_CAPTURE_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    ImageView imageView = findViewById(R.id.imageView2);
                    imageView.setImageURI(uri);
                    imageView.setRotation(90);
                    BitmapDrawable draw = (BitmapDrawable) imageView.getDrawable();
                    bitmap = draw.getBitmap();

                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);
                }else if (resultCode == Activity.RESULT_CANCELED){
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;


            case (REQUEST_SELECT_IMAGE):
                if(resultCode == Activity.RESULT_OK) {
                    Uri imagenSeleccionada = data.getData();
                    String pathSeleccionada = imagenSeleccionada.getPath();
                    if (pathSeleccionada != null) {
                        InputStream imageStream = null;

                        try {
                            imageStream = getContentResolver().openInputStream(imagenSeleccionada);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        ImageView imageView = findViewById(R.id.imageView2);
                        imageView.setImageBitmap(bmp);

                        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                        bitmap = drawable.getBitmap();
                    }
                }
                break;
        }
    }

    public static String convertirBase64(Bitmap bm) {
        String encodedImage = "";
        if (bm != null) {
            Bitmap rescala = Bitmap.createScaledBitmap(bm,500,500,true);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            rescala.compress(Bitmap.CompressFormat.JPEG,100,b);
            byte [] ba = b.toByteArray();
            encodedImage = Base64.encodeToString(ba,Base64.DEFAULT);
            return encodedImage;
        } else {
            return encodedImage;
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }
    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }
}

