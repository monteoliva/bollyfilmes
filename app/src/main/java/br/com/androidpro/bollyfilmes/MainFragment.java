package br.com.androidpro.bollyfilmes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private CallBack callBack;
    private int posicaoItem = ListView.INVALID_POSITION;
    private static final String KEY__POSICAO = "SELECIONADO";
    private ListView list;
    private FilmesAdapter adapter;
    private boolean useFilmeDestaque = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // cria o CallBack com a MainActivity
        callBack = (CallBack) getActivity();

        list = view.findViewById(R.id.list_filmes);

        final ArrayList<ItemFilme> arrayList = new ArrayList<>();
        arrayList.add(new ItemFilme("Homem Aranha", "Filme de heroi picado por uma aranha", "10/04/2016", 4));
        arrayList.add(new ItemFilme("Homem Cobra", "Filme de heroi picado por uma cobra", "06/01/2016", 2));
        arrayList.add(new ItemFilme("Homem Javali", "Filme de heroi mordido por uma javali", "30/06/2016", 3));
        arrayList.add(new ItemFilme("Homem Passaro", "Filme de heroi picado por uma passaro", "23/05/2016", 5));
        arrayList.add(new ItemFilme("Homem Cachorro", "Filme de heroi mordido por uma cachorro", "14/02/2016", 3.5f));
        arrayList.add(new ItemFilme("Homem Gato", "Filme de heroi mordido por uma gato", "10/04/2016", 2.5f));

        adapter = new FilmesAdapter(getContext(), arrayList);
        adapter.setUseFilmeDestaque(useFilmeDestaque);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemFilme itemFilme = arrayList.get(position);
                callBack.onItemSelected(itemFilme);
                posicaoItem = position;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY__POSICAO)) {
            posicaoItem = savedInstanceState.getInt(KEY__POSICAO);
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (posicaoItem != ListView.INVALID_POSITION) {
            outState.putInt(KEY__POSICAO, posicaoItem);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (posicaoItem != ListView.INVALID_POSITION && list != null) {
            list.smoothScrollToPosition(posicaoItem);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_atualizar:
                Toast.makeText(getContext(), "Atualizando os filmes...", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUseFilmeDestaque(boolean useFilmeDestaque) {
        this.useFilmeDestaque = useFilmeDestaque;

        if (adapter != null) {
            adapter.setUseFilmeDestaque(useFilmeDestaque);
        }
    }

    public interface CallBack {
        void onItemSelected(ItemFilme itemFilme);
    }
}