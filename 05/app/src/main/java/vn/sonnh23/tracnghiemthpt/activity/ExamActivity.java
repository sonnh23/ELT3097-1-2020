package vn.huyld247.tracnghiemthpt.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import vn.huyld247.tracnghiemthpt.R;
import vn.huyld247.tracnghiemthpt.adapter.ExamAdapter;
import vn.huyld247.tracnghiemthpt.common.Common;
import vn.huyld247.tracnghiemthpt.fragment.HomeFragment;
import vn.huyld247.tracnghiemthpt.helper.IOHelper;
import vn.huyld247.tracnghiemthpt.model.Exam;
import vn.huyld247.tracnghiemthpt.model.HighScore;

public class ExamActivity extends AppCompatActivity {

    public static String rawName;
    public static String icon;

    private ArrayList<Exam> mExams = new ArrayList<>();
    private ArrayList<ArrayList<HighScore>> mArrHighScores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Toolbar toolbar = findViewById(R.id.exam_toolbar);
        setSupportActionBar(toolbar);

        Intent getIntent = getIntent();
        rawName = getIntent.getStringExtra(HomeFragment.EXTRA_RAW_NAME);

        RecyclerView recyclerExam = findViewById(R.id.recycler_exam);

        IOHelper helper = new IOHelper(this);
        try {
            mExams = helper.getExam(Common.getId(rawName, R.raw.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView tvName = findViewById(R.id.tvExamName);
        tvName.setText(String.format("Môn: %s", mExams.get(0).mSubject));

        loadHighScore();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerExam.setLayoutManager(layoutManager);

        ExamAdapter adapter = new ExamAdapter(this, mExams, mArrHighScores);
        recyclerExam.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exam, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ex_home:
                loadHomePage();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        loadHomePage();
    }

    private void loadHighScore(){
        SharedPreferences pref = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        for (int i = 0; i < mExams.size(); i++){
            String key_prefs = rawName + "_" + mExams.get(i).mExamNum;
            Gson gson = new Gson();
            String json = pref.getString(key_prefs, null);
            Type type = new TypeToken<ArrayList<HighScore>>(){}.getType();
            ArrayList<HighScore> mHighScores = gson.fromJson(json, type);
            if (mHighScores == null){
                HighScore highScore = new HighScore(null, 0, 0, 0, 0, 0, 0);
                mHighScores = new ArrayList<>();
                mHighScores.add(highScore);
            }
            mArrHighScores.add(mHighScores);
        }
    }

    private void loadHomePage(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
