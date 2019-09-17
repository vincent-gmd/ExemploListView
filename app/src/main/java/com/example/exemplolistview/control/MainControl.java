package com.example.exemplolistview.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.exemplolistview.R;
import com.example.exemplolistview.model.vo.Estado;

import java.util.ArrayList;
import java.util.List;

public class MainControl {
    private Activity activity;
    private EditText editNome;
    private EditText editSigla;
    private TextView tvContagem;
    private ListView lvEstado;

    private List<Estado> listEstado;
    private ArrayAdapter<Estado> adapterEstado;
    private Estado estado;

    public MainControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    private void initComponents() {
        editNome = activity.findViewById(R.id.editNome);
        editSigla = activity.findViewById(R.id.editSigla);
        tvContagem = activity.findViewById(R.id.textViewContagem);
        lvEstado = activity.findViewById(R.id.lvEstado);
        configListView();
    }

    private void configListView() {
        listEstado =new ArrayList<>();
        adapterEstado =new ArrayAdapter<Estado>(activity,android.R.layout.simple_list_item_1,listEstado);
        lvEstado.setAdapter(adapterEstado);
        cliqueLongo();
        cliqueCurto();
    }

    private void cliqueLongo(){
        lvEstado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                estado=adapterEstado.getItem(i);
                dialogExcluirEstado(estado);
                return true;
            }
        });
    }
    private void cliqueCurto(){
        lvEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                estado=adapterEstado.getItem(i);
                dialogEditarEstado(estado);
            }
        });
    }
    private void addEstadoLv(Estado e){
        adapterEstado.add(e);
    }
    private void excluiEstadoLv(Estado e){
        adapterEstado.remove(e);
    }
    private void atualizaEstadoLv(Estado e){
        estado.setNome(e.getNome());
        estado.setSigla(e.getSigla());
        adapterEstado.notifyDataSetChanged();
    }
    private Estado getDadosForm(){
        Estado e=new Estado();
        e.setNome(editNome.getText().toString());
        e.setSigla(editSigla.getText().toString());
        return e;
    }
    private void carregarForm(Estado e){
        editNome.setText(e.getNome());
        editSigla.setText(e.getSigla());
    }
    private void atualizarContador(){
        tvContagem.setText(activity.getString(R.string.contagem)+": "+adapterEstado.getCount());
    }
    private void dialogExcluirEstado(final Estado e){
        AlertDialog.Builder alerta= new AlertDialog.Builder(activity);
        alerta.setTitle("Excluindo estado");
        alerta.setMessage(e.toString());
        alerta.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                estado=null;
            }
        });
        alerta.setPositiveButton("excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                excluiEstadoLv(e);
                atualizarContador();
                estado=null;
            }
        });
        alerta.show();
    }
    private void dialogEditarEstado(final Estado e){
        AlertDialog.Builder alerta= new AlertDialog.Builder(activity);
        alerta.setTitle("Editando estado");
        alerta.setMessage(e.toString());
        alerta.setNegativeButton("fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                estado=null;
            }
        });
        alerta.setPositiveButton("editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carregarForm(e);
            }
        });
        alerta.show();
    }
    public void salvarAction(){
        if(estado==null){
            Estado e=getDadosForm();
            addEstadoLv(e);
            atualizarContador();
        }else {
            Estado e=getDadosForm();
            atualizaEstadoLv(e);
        }
        estado=null;
    }


}
