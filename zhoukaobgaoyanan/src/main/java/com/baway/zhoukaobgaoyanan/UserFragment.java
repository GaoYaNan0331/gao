package com.baway.zhoukaobgaoyanan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private ListView mLv;

    public static UserFragment getFragment(String url){
    UserFragment userFragment=new UserFragment();
    Bundle bundle=new Bundle();
    bundle.putString("url",url);
    userFragment.setArguments(bundle);
    return userFragment;

}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment_user,null);
        mLv = (ListView) view.findViewById(R.id.lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        arguments.getBundle("url");
        
    }
}
