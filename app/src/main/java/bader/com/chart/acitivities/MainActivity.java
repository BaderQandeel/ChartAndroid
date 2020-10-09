package bader.com.chart.acitivities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import bader.com.chart.R;
import bader.com.chart.constants.API;
import bader.com.chart.data.TempChartEntry;
import bader.com.chart.data.TempData;
import bader.com.chart.utils.MyXAxisRenderer;
import bader.com.chart.utils.VolleySingleton;


public class MainActivity extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {
    LineChart mChart;
    SwipeRefreshLayout swipeRefreshLayout;
    LineDataSet set1;
    XAxis xAxis;
    YAxis leftAxis;
    String[] strings;
    Gson gson;
    List<TempChartEntry> tempChartEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        initViews();
        getTempDataFromServer();
    }

    private void initViews() {
        /*init views
        * init mChart and init x axis ,yaxis views
        * add swipe refresh to show loading and to refresh data*/
        setContentView(R.layout.activity_main);
        mChart = findViewById(R.id.linechart);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        // no description text
        Description description = new Description();
        description.setText("Temp °C");
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        mChart.setDescription(description);
        mChart.setNoDataText("Please Wait ..");
        mChart.setNoDataTextColor(Color.BLACK);
        // enable touch gestures
        mChart.setTouchEnabled(true);
        // enable scaling and dragging
        mChart.setDragEnabled(true);
        leftAxis = mChart.getAxisLeft();
        float percent = 10;
        leftAxis.setSpaceTop(percent);
        leftAxis.setSpaceBottom(percent);
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setDrawZeroLine(false);

         xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-30f);
//        xAxis.setCenterAxisLabels(true);
        xAxis.setAvoidFirstLastClipping(true);
        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        mChart.getAxisRight().setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTempDataFromServer();
            }
        });
    }


    private ArrayList<Entry> getChartEntries() {
        /*add entries from tempChartEntries to use it in chart*/
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (TempChartEntry temp :
                tempChartEntries) {
            entries.add(new Entry(temp.getX(), temp.getY()));

        }
        return entries;
    }

    private String[] getXLabels() {
        /*add dates to string array*/
        String[] arr = new String[tempChartEntries.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = tempChartEntries.get(i).getDateLabel();
        }
        return arr;
    }

    private void addChartDataEntries() {
        /*
         * Create date set1 LineDataSet add (x,y)
         * Customize line set ui (font color,size,circle,draw values
         * Add x label (Dates) via strings array
         * set setVisibleXRangeMaximum to 6 and move view to last 6 entries*/

        mChart.animateX(500, Easing.EaseInOutQuart);
        ArrayList<Entry> entries = getChartEntries();
        // create a dataset and give it a type
        set1 = new LineDataSet(entries, "Temp °C");
        set1.setFillAlpha(110);
        set1.setColor(ContextCompat.getColor(this, R.color.blue));
        set1.setCircleColor(ContextCompat.getColor(this, R.color.blue));
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(false);
        set1.setDrawValues(true);
        set1.setValueTextColor(ContextCompat.getColor(this, R.color.blue));
        strings = getXLabels();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        mChart.setXAxisRenderer(new MyXAxisRenderer(mChart.getViewPortHandler(), xAxis, mChart.getTransformer(YAxis.AxisDependency.LEFT), 20));
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets
        // create a data object with the datasets
        LineData data = new LineData(set1);
        // set data
        mChart.setData(data);
        mChart.setVisibleXRangeMaximum(6);
        mChart.setScrollContainer(true);
        mChart.setHorizontalScrollBarEnabled(true);
        mChart.setScaleXEnabled(true);
//        mChart.setHorizontalScrollbarThumbDrawable();
//        mChart.moveViewToX(2);
        if (entries.size() > 6) {
            mChart.setHorizontalScrollBarEnabled(true);
            mChart.moveViewToX(entries.size() - 6);
        }
        mChart.invalidate();
    }

    public void getTempDataFromServer() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        mChart.setNoDataText("Please Wait ..");
        mChart.setNoDataTextColor(Color.BLACK);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.GET_TEMP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            TempData tempData = gson.fromJson(response, TempData.class);
                            tempChartEntries = tempData.getTempChartEntries();
                            addChartDataEntries();
                            mChart.setNoDataText("Error with data");
                            mChart.setNoDataTextColor(Color.RED);
                            swipeRefreshLayout.setRefreshing(false);

                        } catch (Exception e) {
                            Log.e("FormatError", response);
                            mChart.setNoDataText("Error with data");
                            mChart.setNoDataTextColor(Color.RED);
                            e.printStackTrace();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        String responseBody = "";
                        if (error instanceof ServerError) {

                            try {
                                responseBody = new String(error.networkResponse.data, "utf-8");
                                JSONObject jsonObject = new JSONObject(responseBody);
                                Log.e("ServerError", jsonObject.toString());
                                mChart.setNoDataText("Server Error");
                                mChart.setNoDataTextColor(Color.RED);

                            } catch (UnsupportedEncodingException | JSONException | NullPointerException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e("ServerError", error.getMessage());
                            mChart.setNoDataText(getString(R.string.NetworkError));
                            mChart.setNoDataTextColor(Color.RED);
                            Toast.makeText(getApplicationContext(), R.string.NetworkError, Toast.LENGTH_LONG).show();
                        }
                    }
                });


        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, strings[(int) e.getX()].replace("\n", " ") + String.format("\nTemp %.2f °C", e.getY()), Toast.LENGTH_SHORT).show();

        Log.i("Entry selected", e.toString());
//        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex() + ", high: " + mChart.getHighestVisibleXIndex());
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());

    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}