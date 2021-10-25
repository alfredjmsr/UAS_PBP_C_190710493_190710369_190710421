package com.trv.apptravel.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trv.apptravel.Flight.FlightUpdateDeleteActivity;
import com.trv.apptravel.Model.Flight.GetFlight;
import com.trv.apptravel.R;

import java.util.List;

public class ListFlightAdapter extends RecyclerView.Adapter<ListFlightAdapter.MyViewHolder> {
    List<GetFlight> flightList;
    public static String idkuu = "";
    public ListFlightAdapter(List<GetFlight> getFlightList) {
        flightList = getFlightList;
    }


    @Override
    public ListFlightAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_data_flight, parent, false);
        ListFlightAdapter.MyViewHolder mViewHolder = new ListFlightAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if(flightList.get(position).getNameFlight().toString().equalsIgnoreCase("Batikair")){
            holder.imgMaskapai.setImageResource(R.mipmap.batikair_pic_foreground);
        }
        if(flightList.get(position).getNameFlight().toString().equalsIgnoreCase("Garuda")){
            holder.imgMaskapai.setImageResource(R.mipmap.garudaair_pic_foreground);
        }
        holder.txtFromflight.setText(flightList.get(position).getFromFlight());
        holder.txtToflight.setText(flightList.get(position).getToFlight());
        holder.txtTimefromflight.setText(flightList.get(position).getFromtimeFlight());
        holder.txtTimetoflight.setText(flightList.get(position).getTotimeFlight());
        holder.txtPrice.setText("Rp "+flightList.get(position).getPriceFlight());
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), FlightUpdateDeleteActivity.class);
                mIntent.putExtra("id_flight", flightList.get(position).getIdFlight());
                mIntent.putExtra("from_flight", flightList.get(position).getFromFlight());
                mIntent.putExtra("to_flight", flightList.get(position).getToFlight());
                mIntent.putExtra("fromtime_flight", flightList.get(position).getFromtimeFlight());
                mIntent.putExtra("totime_flight", flightList.get(position).getTotimeFlight());
                mIntent.putExtra("class_flight", flightList.get(position).getClassFlight());
                mIntent.putExtra("price_flight", flightList.get(position).getPriceFlight());
                mIntent.putExtra("name_flight", flightList.get(position).getNameFlight());
                v.getContext().startActivity(mIntent);

            }
        });
    }



    @Override
    public int getItemCount() {
        Log.d("sizeflight ",""+flightList.size());
        return flightList.size();
    }


    //public String getIddelete(int position){
    // return kendaraanList.get(position).getId_diskon();
    // }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public  TextView txtPrice, txtFromflight,
                txtToflight, txtTimefromflight,
                txtTimetoflight;
        ImageView imgMaskapai;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtFromflight = (TextView) itemView.findViewById(R.id.txtFromflight);
            txtToflight = (TextView) itemView.findViewById(R.id.txtToflight);
            txtTimefromflight = (TextView) itemView.findViewById(R.id.txtFromtimeflight);
            txtTimetoflight = (TextView) itemView.findViewById(R.id.txtTotimeflight);
            imgMaskapai = (ImageView) itemView.findViewById(R.id.imgMaskapai);
        }
    }
}
