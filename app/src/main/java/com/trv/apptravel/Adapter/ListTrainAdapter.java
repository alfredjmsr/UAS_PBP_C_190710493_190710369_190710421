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

import com.trv.apptravel.Model.Train.GetTrain;
import com.trv.apptravel.R;
import com.trv.apptravel.Train.TrainUpdateDeleteActivity;

import java.util.List;

public class ListTrainAdapter extends RecyclerView.Adapter<ListTrainAdapter.MyViewHolder> {
    List<GetTrain> trainList;
    public static String idkuu = "";
    public ListTrainAdapter(List<GetTrain> gettrainList) {
        trainList = gettrainList;
    }


    @Override
    public ListTrainAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_data_train, parent, false);
        ListTrainAdapter.MyViewHolder mViewHolder = new ListTrainAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imgMaskapai.setImageResource(R.mipmap.kai_foreground);
        holder.txtFromflight.setText(trainList.get(position).getFromtrain());
        holder.txtToflight.setText(trainList.get(position).getTotrain());
        holder.txtTimefromflight.setText(trainList.get(position).getFromtimetrain());
        holder.txtTimetoflight.setText(trainList.get(position).getTotimetrain());
        holder.txtPrice.setText("Rp "+trainList.get(position).getPricetrain());
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), TrainUpdateDeleteActivity.class);
                mIntent.putExtra("id_train", trainList.get(position).getIdtrain());
                mIntent.putExtra("from_train", trainList.get(position).getFromtrain());
                mIntent.putExtra("to_train", trainList.get(position).getTotrain());
                mIntent.putExtra("fromtime_train", trainList.get(position).getFromtimetrain());
                mIntent.putExtra("totime_train", trainList.get(position).getTotimetrain());
                mIntent.putExtra("class_train", trainList.get(position).getClasstrain());
                mIntent.putExtra("price_train", trainList.get(position).getPricetrain());
                mIntent.putExtra("name_train", trainList.get(position).getNametrain());
                v.getContext().startActivity(mIntent);

            }
        });
    }



    @Override
    public int getItemCount() {
        Log.d("sizetrain ",""+trainList.size());
        return trainList.size();
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
