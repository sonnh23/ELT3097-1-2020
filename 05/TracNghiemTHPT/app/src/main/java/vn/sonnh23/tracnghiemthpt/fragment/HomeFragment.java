package vn.sonnh23.tracnghiemthpt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import cn.iwgang.countdownview.CountdownView;
import vn.sonnh23.tracnghiemthpt.R;
import vn.sonnh23.tracnghiemthpt.activity.ExamActivity;
import vn.sonnh23.tracnghiemthpt.adapter.SubjectAdapter;
import vn.sonnh23.tracnghiemthpt.common.Common;
import vn.sonnh23.tracnghiemthpt.helper.IOHelper;
import vn.sonnh23.tracnghiemthpt.model.Subject;

public class HomeFragment extends Fragment {

    public static final String EXTRA_RAW_NAME = "extraRawID";
    public static final String EXTRA_STRING_ICON = "extraStringIcon";

    private ArrayList<Subject> mSubjects = new ArrayList<>();
    private  GridView gridSubject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        long currentTime = System.currentTimeMillis();
        long timeInMillis = Common.timeToUnix(1620000002);

        if (getActivity() != null) {
            CountdownView countdownView = getActivity().findViewById(R.id.countdownView);
            countdownView.start(timeInMillis - currentTime);
            gridSubject = getActivity().findViewById(R.id.gridSubject);
        }
        IOHelper helper = new IOHelper(getActivity());
        try {
            mSubjects = helper.getSubject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SubjectAdapter adapter = new SubjectAdapter(getActivity(), mSubjects);
        gridSubject.setAdapter(adapter);

        gridSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSubjects.get(position).mSrcExam.equals("")){
                    Toast.makeText(getActivity(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), ExamActivity.class);
                    intent.putExtra(EXTRA_RAW_NAME, mSubjects.get(position).mSrcExam);
                    intent.putExtra(EXTRA_STRING_ICON, mSubjects.get(position).mIcon);
                    startActivity(intent);
                }
            }
        });
    }
}
