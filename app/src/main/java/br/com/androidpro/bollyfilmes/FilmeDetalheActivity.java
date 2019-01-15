package br.com.androidpro.bollyfilmes;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class FilmeDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detalhe);

        ItemFilme filme = (ItemFilme) getIntent().getSerializableExtra(MainActivity.KEY_FILME);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.KEY_FILME, filme);

        FilmeDetalheFragment fragment = new FilmeDetalheFragment();
        fragment.setArguments(bundle);

        fragmentTransaction.add(R.id.fragment_filme_detalhe, fragment);
        fragmentTransaction.commit();

    }
}
