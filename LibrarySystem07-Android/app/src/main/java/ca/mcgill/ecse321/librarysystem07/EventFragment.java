package ca.mcgill.ecse321.librarysystem07;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import cz.msebera.android.httpclient.Header;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.mcgill.ecse321.librarysystem07.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.HttpEntity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

public class EventFragment.java extends AppCompatActivity{

/**
 * @author Tutorial Notes
  */
public void addEvent(View v){
        // start time
        TextView tv=(TextView)findViewById(R.id.starttime);
        String text=tv.getText().toString();
        String comps[]=text.split(":");

        TextView tv2=(TextView)findViewById(R.id.endtime);
        String text2=tv2.getText().toString();
        String comps2[]=text2.split(":");

        int startHours=Integer.parseInt(comps[0]);
        int startMinutes=Integer.parseInt(comps[1]);

        int endHours=Integer.parseInt(comps2[0]);
        int endMinutes=Integer.parseInt(comps2[1]);

        // TODO get end time

        // date
        tv=(TextView)findViewById(R.id.newevent_date);
        text=tv.getText().toString();
        comps=text.split("-");

        int year=Integer.parseInt(comps[2]);
        int month=Integer.parseInt(comps[1]);
        int day=Integer.parseInt(comps[0]);

        // name
        tv=(TextView)findViewById(R.id.newevent_name);
        String name=tv.getText().toString();

        RequestParams rp=new RequestParams();

        NumberFormat formatter=new DecimalFormat("00");
        rp.add("date",year+"-"+formatter.format(month)+"-"+formatter.format(day));
        rp.add("startTime",formatter.format(startHours)+":"+formatter.format(startMinutes));
        rp.add("date",year+"-"+formatter.format(month)+"-"+formatter.format(day));
        rp.add("endTime",formatter.format(endHours)+":"+formatter.format(endMinutes));

        HttpUtils.post("events/"+name,rp,new JsonHttpResponseHandler(){
/**
 * @author Tutorial Notes
  */
@Override
public void onSuccess(int statusCode,Header[]headers,JSONObject response){
        refreshErrorMessage();
        ((TextView)findViewById(R.id.newevent_name)).setText("");
        }
/**
 * @author Tutorial Notes
  */
@Override
public void onFailure(int statusCode,Header[]headers,Throwable throwable,JSONObject errorResponse){
        try{
        error+=errorResponse.get("message").toString();
        }catch(JSONException e){
        error+=e.getMessage();
        }
        refreshErrorMessage();
        }
        });
        }


/**
 * @author Tutorial Notes
  */
private Bundle getTimeFromLabel(String text){
        Bundle rtn=new Bundle();
        String comps[]=text.toString().split(":");
        int hour=12;
        int minute=0;

        if(comps.length==2){
        hour=Integer.parseInt(comps[0]);
        minute=Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour",hour);
        rtn.putInt("minute",minute);

        return rtn;
        }
/**
 * @author Tutorial Notes
  */
private Bundle getDateFromLabel(String text){
        Bundle rtn=new Bundle();
        String comps[]=text.toString().split("-");
        int day=1;
        int month=1;
        int year=1;

        if(comps.length==3){
        day=Integer.parseInt(comps[0]);
        month=Integer.parseInt(comps[1]);
        year=Integer.parseInt(comps[2]);
        }

        rtn.putInt("day",day);
        rtn.putInt("month",month-1);
        rtn.putInt("year",year);

        return rtn;
        }
/**
 * @author Tutorial Notes
  */
public void showTimePickerDialog(View v){
        TextView tf=(TextView)v;
        Bundle args=getTimeFromLabel(tf.getText().toString());
        args.putInt("id",v.getId());

        TimePickerFragment newFragment=new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(),"timePicker");
        }
/**
 * @author Tutorial Notes
  */
public void showDatePickerDialog(View v){
        TextView tf=(TextView)v;
        Bundle args=getDateFromLabel(tf.getText().toString());
        args.putInt("id",v.getId());

        DatePickerFragment newFragment=new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(),"datePicker");
        }
/**
 * @author Tutorial Notes
  */
public void setTime(int id,int h,int m){
        TextView tv=(TextView)findViewById(id);
        tv.setText(String.format("%02d:%02d",h,m));
        }
/**
 * @author Tutorial Notes
  */
public void setDate(int id,int d,int m,int y){
        TextView tv=(TextView)findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d",d,m+1,y));
        }
}
