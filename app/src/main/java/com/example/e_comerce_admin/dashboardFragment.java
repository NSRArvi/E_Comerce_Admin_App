package com.example.e_comerce_admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.e_comerce_admin.adapters.DashboardAdapter;
import com.example.e_comerce_admin.callbacks.OnDashboardItemSelectedListener;
import com.example.e_comerce_admin.models.DashboardItem;
import com.example.e_comerce_admin.viewmodels.LoginViewModel;
import com.example.e_comerce_admin.databinding.FragmentDashboardBinding;
import com.example.e_comerce_admin.utils.Constants;


public class dashboardFragment extends Fragment implements OnDashboardItemSelectedListener {

    private FragmentDashboardBinding binding;
    private LoginViewModel loginViewModel;
    public dashboardFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(requireActivity())
                .get(LoginViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater);
        loginViewModel.authLiveData
                .observe(getViewLifecycleOwner(), authState -> {
                    if (authState == LoginViewModel.AuthState.UNAUTHENTICATED) {
                        Navigation.findNavController(container)
                                .navigate(R.id.action_dashboardFragment_to_adminLoginFragment);
                    }
                });
        final DashboardAdapter adapter = new DashboardAdapter(this,
                DashboardItem.getDashboardItems());
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        binding.dashboardRv.setLayoutManager(llm);
        binding.dashboardRv.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onDashboardItemSelect(String item) {
        switch (item) {
            case Constants.Item.ADD_PRODUCT:
                Navigation.findNavController(getActivity(),
                        R.id.fragmentContainerView)
                        .navigate(R.id.action_dashboardFragment_to_addProductFragment);
                break;
            case Constants.Item.VIEW_PRODUCT:
                Navigation.findNavController(getActivity(),
                        R.id.fragmentContainerView)
                        .navigate(R.id.action_dashboardFragment_to_productListFragment);
                break;
            case Constants.Item.VIEW_ORDERS:

                break;
            case Constants.Item.ADD_CATEGORY:

                break;
            case Constants.Item.VIEW_REPORTS:

                break;
            case Constants.Item.SETTINGS:

                break;
            case Constants.Item.VIEW_USERS:

                break;
        }
    }
}