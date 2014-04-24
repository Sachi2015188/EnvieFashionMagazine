package team.envie.fashion.enviefashion.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;

import team.envie.fashion.enviefashion.R;


/**
 * MainActivity
 * <p>
 * MainActivity
 * orientation portrait
 * </p>
 *
 * @version 1.0.0
 */
public class MainActivity extends FragmentActivity
        implements team.envie.fashion.enviefashion.ui.fragment.NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private team.envie.fashion.enviefashion.ui.fragment.NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // get Fragment view
        mNavigationDrawerFragment = (team.envie.fashion.enviefashion.ui.fragment.NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        onSectionAttached(position + 1);
        // update the main content by replacing fragments
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }


    public void onSectionAttached(int number) {
        switch (number) {
            case 1:

                break;
            case 2:
                // change Locale
                team.envie.fashion.enviefashion.view.dialog.ListDialogFragment.create().show(getSupportFragmentManager(), getPackageName());
                break;
            case 3:
                // go to sns of Envie' page
                team.envie.fashion.enviefashion.view.dialog.SnsDialog.create().show(getSupportFragmentManager(), getPackageName());
                break;
            case 4:
                break;
        }
    }
}
