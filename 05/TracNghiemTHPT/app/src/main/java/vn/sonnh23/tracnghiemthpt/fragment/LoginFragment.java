package vn.sonnh23.tracnghiemthpt.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vn.sonnh23.tracnghiemthpt.R;
import vn.sonnh23.tracnghiemthpt.activity.MainActivity;
import vn.sonnh23.tracnghiemthpt.activity.QuizActivity;
import vn.sonnh23.tracnghiemthpt.adapter.ScoreAdapter;
import vn.sonnh23.tracnghiemthpt.adapter.SpinnerSubjectAdapter;
import vn.sonnh23.tracnghiemthpt.common.Common;
import vn.sonnh23.tracnghiemthpt.helper.IOHelper;
import vn.sonnh23.tracnghiemthpt.model.Exam;
import vn.sonnh23.tracnghiemthpt.model.HighScore;
import vn.sonnh23.tracnghiemthpt.model.Subject;

public class LoginFragment extends Fragment {
    EditText userName;
    EditText passWord;
    Button btnClick;
    Boolean status;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userName = (EditText) getActivity().findViewById(R.id.username);
        passWord = (EditText) getActivity().findViewById(R.id.password);
        btnClick = (Button) getActivity().findViewById(R.id.button_login);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usr = userName.getText().toString();
                String pwd = passWord.getText().toString();
                if (usr != null && pwd != null) {
                    Toast.makeText(getActivity(),"Đăng nhập thành công" , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

}