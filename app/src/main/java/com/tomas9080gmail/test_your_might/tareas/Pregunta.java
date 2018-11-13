package com.tomas9080gmail.test_your_might.tareas;

public class Pregunta {
    // Inicializamos las variables
    private String titulo;

    private String respuestaCorrecta;

    private String respuestaIncorrecta1;

    private String respuestaIncorrecta2;

    private String respuestaIncorrecta3;

    private int codigo;
    // Dos contructores, uno con codigo y otro sin codigo
    public Pregunta(String titulo, String respuestaCorrecta, String respuestaIncorrecta1,
                    String respuestaIncorrecta2, String respuestaIncorrecta3, int codigo) {
        this.titulo = titulo;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
        this.codigo = codigo;
    }

    public Pregunta(String titulo, String respuestaCorrecta, String respuestaIncorrecta1,
                    String respuestaIncorrecta2, String respuestaIncorrecta3) {
        this.titulo = titulo;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getRespuestaIncorrecta1() {
        return respuestaIncorrecta1;
    }

    public void setRespuestaIncorrecta1(String respuestaIncorrecta1) {
        this.respuestaIncorrecta1 = respuestaIncorrecta1;
    }

    public String getRespuestaIncorrecta2() {
        return respuestaIncorrecta2;
    }

    public void setRespuestaIncorrecta2(String respuestaIncorrecta2) {
        this.respuestaIncorrecta2 = respuestaIncorrecta2;
    }

    public String getRespuestaIncorrecta3() {
        return respuestaIncorrecta3;
    }

    public void setRespuestaIncorrecta3(String respuestaIncorrecta3) {
        this.respuestaIncorrecta3 = respuestaIncorrecta3;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}