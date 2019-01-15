package br.com.androidpro.bollyfilmes;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainFragment.CallBack {
    public static final String KEY_FILME = "FILME";

    private boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_filme_detalhe) != null) {

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_filme_detalhe, new FilmeDetalheFragment())
                        .commit();
            }

            isTablet = true;
        }
        else {
            isTablet = false;
        }

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        mainFragment.setUseFilmeDestaque(!isTablet);
    }

    @Override
    public void onItemSelected(ItemFilme itemFilme) {
        if (isTablet) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(this.KEY_FILME, itemFilme);

            FilmeDetalheFragment detalheFragment = new FilmeDetalheFragment();
            detalheFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_filme_detalhe, detalheFragment);
            fragmentTransaction.commit();
        }
        else {
            Intent intent = new Intent(this, FilmeDetalheActivity.class);
            intent.putExtra(this.KEY_FILME, itemFilme);
            startActivity(intent);
        }
    }
}
