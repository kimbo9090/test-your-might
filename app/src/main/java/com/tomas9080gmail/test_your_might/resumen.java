package com.tomas9080gmail.test_your_might;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Xml;
import android.view.View;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;

import com.tomas9080gmail.test_your_might.tareas.Pregunta;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

public class resumen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context myContext = this;
        this.importarXML();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final Button botonImportar = (Button) findViewById(R.id.importar);
        botonImportar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {



            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resumen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acercaDe) {
            Intent myIntent = new Intent(resumen.this, acercaDe.class);
            resumen.this.startActivity(myIntent);
        }else if (id == R.id.listado){
            Intent myIntent = new Intent(resumen.this, listadoPreguntas.class);
            resumen.this.startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void importarXML() {
        System.out.println("Bueno mira");

        Pregunta p;

        String titulo="";

        String respuestaCorrecta="";

        String respuestaIncorrecta1="";

        String respuestaIncorrecta2="";

        String respuestaIncorrecta3="";

        String categoria="";

        String foto = "";

        int count = 0;

        Intent miIntent2 = getIntent();

        if (miIntent2 != null) {
            String accion = miIntent2.getAction();
            System.out.println("aqui");
            System.out.println(accion);
            if (accion == "android.intent.action.SEND") {
                Uri datos = miIntent2.getParcelableExtra(Intent.EXTRA_STREAM);
                System.out.println(datos);

                try {
                    InputStream in = getContentResolver().openInputStream(datos);
                    XmlPullParserFactory xp = XmlPullParserFactory.newInstance();
                    xp.setNamespaceAware(false);
                    XmlPullParser parser = xp.newPullParser();
                    parser.setInput(in, null);
                    parser.nextTag();
                    parser.require(XmlPullParser.START_TAG, null, "quiz");
                    int a;
                    String tag="";
                    while((a=parser.next()) != XmlPullParser.END_DOCUMENT) {

                        switch  (a) {
                            case XmlPullParser.START_TAG:
                                tag = parser.getName();
                                break;
                            case XmlPullParser.TEXT:
                                if (tag.equals("text")) {
                                    if ( a == 0) {
                                        titulo = parser.getText();
                                        a++;

                                    }
                                    else if (a == 1) {
                                        categoria = parser.getText();
                                        a++;
                                    }
                                    else if ( a == 2 ) {
                                        a++;
                                    }
                                    else if ( a==3 ) {
                                        respuestaCorrecta = parser.getText();
                                        a++;
                                    }
                                    else if (a == 4) {
                                        respuestaIncorrecta1 = parser.getText();
                                        a++;
                                    }
                                    else if ( a==5 ) {
                                        respuestaIncorrecta2 = parser.getText();
                                        a++;
                                    }
                                    else if ( a==6 ) {
                                        respuestaIncorrecta3 = parser.getText();

                                        p = new Pregunta(titulo,respuestaCorrecta,  respuestaIncorrecta1,
                                                respuestaIncorrecta2,  respuestaIncorrecta3, categoria,foto);

                                    }
                                    a = 0;
                                } else {

                                }
                                if (tag.equals("file")) {
                                    foto= parser.getText();
                                }
                                tag = "";
                                break;

                        }

                    }
                    in.close();
                } catch (IOException | XmlPullParserException e) {
                }
            }
        }
    }

    public static String CreateXMLString() throws IllegalArgumentException, IllegalStateException, IOException
    {
        ArrayList<Pregunta> preguntaXML = new ArrayList<Pregunta>();
        preguntaXML= Repositorio.getMisPreguntas();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        xmlSerializer.setOutput(writer);

        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);


        xmlSerializer.startTag("", "preguntas");

        for (Pregunta p: preguntaXML) {


            xmlSerializer.startTag("", "question");
            xmlSerializer.attribute("", "type", p.getCategoria());

            xmlSerializer.startTag("", "category");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text(p.getCategoria());
            xmlSerializer.endTag("", "text");
            xmlSerializer.endTag("", "category");

            xmlSerializer.endTag("", "question");
            xmlSerializer.startTag("", "question");
            xmlSerializer.attribute("", "type", "multichoice");

            xmlSerializer.startTag("", "name");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text(p.getTitulo());
            xmlSerializer.endTag("", "text");
            xmlSerializer.endTag("", "name");

            xmlSerializer.startTag("","questiontext");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text("<![CDATA[ <p>"+p.getTitulo()+"</p><p><img src='imagen.jpg' /></p>]]>");
            xmlSerializer.endTag("", "text");
            xmlSerializer.startTag("","file");
            xmlSerializer.attribute("", "name","imagen.jpg");
            xmlSerializer.attribute("", "path", "/");
            xmlSerializer.attribute("", "encoding", "base64");
            xmlSerializer.text( p.getFoto());
            xmlSerializer.endTag("", "file");
            xmlSerializer.endTag("", "questiontext");

            xmlSerializer.startTag("","answernumbering");
            xmlSerializer.endTag("", "answernumbering");

            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "100");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text(p.getRespuestaCorrecta());
            xmlSerializer.endTag("", "text");
            xmlSerializer.endTag("", "answer");

            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "0");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text(p.getRespuestaIncorrecta1());
            xmlSerializer.endTag("", "text");
            xmlSerializer.endTag("", "answer");

            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "0");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text(p.getRespuestaIncorrecta2());
            xmlSerializer.endTag("", "text");
            xmlSerializer.endTag("", "answer");

            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "0");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.startTag("", "text");
            xmlSerializer.text(p.getRespuestaIncorrecta3());
            xmlSerializer.endTag("", "text");
            xmlSerializer.endTag("", "answer");

            xmlSerializer.endTag("","question");
        }

        xmlSerializer.endTag("","preguntas");



        xmlSerializer.endDocument();



        return writer.toString();


    }

    public static void exportarXML(Context myContext) {
        String root = Environment.getExternalStorageDirectory().toString();
        System.out.println(root);
        File myDir = new File(root + "/exportarPreguntas");
        String fname = "preguntas.xml";
        File file = new File(myDir, fname);
        try {

            if (!myDir.exists()) {
                myDir.mkdirs();

            }
            if (file.exists())
                file.delete();


            FileWriter fw = new FileWriter(file);
            fw.write(CreateXMLString());


            //Cierro el stream
            fw.close();


        } catch (Exception ex) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
