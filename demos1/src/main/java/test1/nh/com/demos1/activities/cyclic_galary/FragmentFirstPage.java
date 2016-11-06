package test1.nh.com.demos1.activities.cyclic_galary;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import test1.nh.com.demos1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFirstPage extends Fragment {


    public FragmentFirstPage() {
        // Required empty public constructor
    }

    CoordinatorLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_fragment_first_page, container, false);
//        layout= (CoordinatorLayout) v.findViewById(R.id.f1_coordinator);
//        layout.setFitsSystemWindows(true);
//        Button test= (Button) v.findViewById(R.id.test_btn);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                layout.setFitsSystemWindows(false);
//            }
//        });


        return v;
    }


//    @Override
//    public void onPause() {
//        layout.setFitsSystemWindows(false);
//        super.onPause();
//    }


}
