package br.com.androidpro.bollyfilmes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class FilmeDetalheFragment extends Fragment {
    private ItemFilme filme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            filme = (ItemFilme) getArguments().getSerializable(MainActivity.KEY_FILME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme_detalhe, container, false);

        if (filme == null) { return view; }

        TextView titulo = view.findViewById(R.id.item_titulo);
        titulo.setText(filme.getTitulo());

        TextView data = view.findViewById(R.id.item_data);
        data.setText(filme.getDataLancamento());

        TextView desc = view.findViewById(R.id.item_desc);
        desc.setText(filme.getDescricao());

        RatingBar avaliacao = view.findViewById(R.id.item_avaliacao);
        avaliacao.setRating(filme.getAvaliacao());


        // Inflate the layout for this fragment
        return view;
    }
}