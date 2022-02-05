package com.example.e_comerce_admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e_comerce_admin.ViewModels.LoginViewModel;
import com.example.e_comerce_admin.databinding.FragmentAdminLoginBinding;

public class adminLoginFragment extends Fragment {
    FragmentAdminLoginBinding binding;
    private LoginViewModel loginViewModel;



    public adminLoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminLoginBinding.inflate(inflater);
        loginViewModel = new ViewModelProvider(requireActivity())
                .get(LoginViewModel.class);
        binding.loginBtn.setOnClickListener(v -> {
            final String email = binding.emailEt.getText().toString();
            final String password = binding.passwordEt.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Please provide both email and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.login(email, password);

        });
        loginViewModel.authLiveData
                .observe(getViewLifecycleOwner(), authState -> {
                    if (authState == LoginViewModel.AuthState.AUTHENTICATED) {
                        Navigation.findNavController(container)
                                .navigate(R.id.action_adminLoginFragment_to_dashboardFragment);
                    }
                });

        loginViewModel.getErrMsgLiveData()
                .observe(getViewLifecycleOwner(), errMsg -> {
                    binding.errorTv.setText(errMsg);
                });
        return binding.getRoot();
    }
}