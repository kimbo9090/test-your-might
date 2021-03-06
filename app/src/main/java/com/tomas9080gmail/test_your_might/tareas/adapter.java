package com.tomas9080gmail.test_your_might.tareas;
import java.util.ArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tomas9080gmail.test_your_might.R;

public class adapter
        extends RecyclerView.Adapter<adapter.PreguntaViewHolder>
        implements View.OnClickListener {

    private ArrayList<Pregunta> items;
    private View.OnClickListener listener;
    private View.OnLongClickListener listenerLargo;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará¡
    // de almacenar la vista del elemento y sus datos
    public static class PreguntaViewHolder
            extends RecyclerView.ViewHolder {

        private TextView TextView_categoria;
        private TextView TextView_nombre;

        public PreguntaViewHolder(View itemView) {
            super(itemView);
            TextView_categoria = (TextView) itemView.findViewById(R.id.textView2_categoria);
            TextView_nombre = (TextView) itemView.findViewById(R.id.textView_nombre);
        }

        public void PreguntaBind(Pregunta item) {
            TextView_categoria.setText(item.getCategoria());
            TextView_nombre.setText(item.getTitulo());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public adapter(@NonNull ArrayList<Pregunta> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colecciÃ³n.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public PreguntaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        row.setOnClickListener(this);

        PreguntaViewHolder avh = new PreguntaViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(PreguntaViewHolder viewHolder, int position) {
        Pregunta item = items.get(position);
        viewHolder.PreguntaBind(item);
    }

    // Indica el numero de elementos de la coleccion de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setOnLongListener(View.OnLongClickListener listenerLargo) {this.listenerLargo = listenerLargo;};

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}