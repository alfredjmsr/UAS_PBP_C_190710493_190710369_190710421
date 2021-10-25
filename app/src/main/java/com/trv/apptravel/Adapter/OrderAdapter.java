package com.trv.apptravel.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trv.apptravel.Model.Order.GetOrder;
import com.trv.apptravel.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    List<GetOrder> orderList;
    public static String idkuu = "";
    public OrderAdapter(List<GetOrder> getOrders) {
        orderList = getOrders;
    }


    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_data_order, parent, false);
        OrderAdapter.MyViewHolder mViewHolder = new OrderAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if(orderList.get(position).getNameflight_order().toString().equalsIgnoreCase("Batikair")){
            holder.imgMaskapai.setImageResource(R.mipmap.batikair_pic_foreground);
        }else if(orderList.get(position).getNameflight_order().toString().equalsIgnoreCase("Garuda")){
            holder.imgMaskapai.setImageResource(R.mipmap.garudaair_pic_foreground);
        }else{
            holder.imgMaskapai.setImageResource(R.mipmap.kai_foreground);
        }
        holder.txtFromflight.setText(orderList.get(position).getFromflight_order());
        holder.txtToflight.setText(orderList.get(position).getToflight_order());
        holder.txtTimefromflight.setText(orderList.get(position).getFromflighttime_order());
        holder.txtTimetoflight.setText(orderList.get(position).getToflighttime_order());
        holder.txtPrice.setText("Rp "+orderList.get(position).getPrice_order());
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
    }



    @Override
    public int getItemCount() {
        Log.d("sizeflight ",""+orderList.size());
        return orderList.size();
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
