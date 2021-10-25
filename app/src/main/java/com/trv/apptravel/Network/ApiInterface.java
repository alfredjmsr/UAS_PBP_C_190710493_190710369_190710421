package com.trv.apptravel.Network;

import com.trv.apptravel.Model.Flight.Flight;
import com.trv.apptravel.Model.Flight.GetFlight;
import com.trv.apptravel.Model.Laporan.LaporanList;
import com.trv.apptravel.Model.Login.Login;
import com.trv.apptravel.Model.Login.LoginUsers;
import com.trv.apptravel.Model.Order.Order;
import com.trv.apptravel.Model.Train.GetTrain;
import com.trv.apptravel.Model.Train.Train;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    //===========================Login==================================//
    @POST("users/login")
    @FormUrlEncoded
    Call<Login> loginUsers(@Field("nama_user") String nama_user,
                           @Field("password_user") String password_user);

    @POST("users/register")
    @FormUrlEncoded
    Call<LoginUsers> regisUser(@Field("nama_user") String nama_user,
                               @Field("password_user") String password_user,
                               @Field("email_user") String email_user,
                               @Field("phonenumber_user") String phonenumber_user,
                               @Field("birthdate_user") String birthdate_user);

    @Multipart
    @POST("users/updateprofile")
    Call<LoginUsers> updateUser(@Part MultipartBody.Part image_user,
                                @Part("id_user") RequestBody id_user,
                                @Part("nama_user") RequestBody nama_user,
                                @Part("phonenumber_user") RequestBody phonenumber_user,
                                @Part("birthdate_user") RequestBody birthdate_user);
    @POST("users/profil")
    @FormUrlEncoded
    Call<Login> getUser(@Field("nama_user") String nama_user);
    //===========================Flight==================================//
    @POST("flights/addflights")
    @FormUrlEncoded
    Call<GetFlight> addflight(@Field("from_flight") String from_flight,
                              @Field("to_flight") String to_flight,
                              @Field("fromtime_flight") String fromtime_flight,
                              @Field("totime_flight") String totime_flight,
                              @Field("price_flight") String price_flight,
                              @Field("class_flight") String class_flight,
                              @Field("name_flight") String name_flight);
    @POST("flights/updateFlights")
    @FormUrlEncoded
    Call<GetFlight> updateflights(@Field("id_flight") String id_flight,
                                  @Field("from_flight") String from_flight,
                                  @Field("to_flight") String to_flight,
                                  @Field("fromtime_flight") String fromtime_flight,
                                  @Field("totime_flight") String totime_flight,
                                  @Field("price_flight") String price_flight,
                                  @Field("class_flight") String class_flight,
                                  @Field("name_flight") String name_flight);
    @POST("flights/deleteFlights")
    @FormUrlEncoded
    Call<GetFlight> deleteflights(@Field("id_flight") String id_flight);

    @GET("flights")
    Call<Flight> getFlight();
    @POST("flights/buyticket")
    @FormUrlEncoded
    Call<Order> buyFlightTicket(@Field("firstname_order") String firstname_order,
                                @Field("lastname_order") String lastname_order,
                                @Field("email_order") String email_order,
                                @Field("address_order") String address_order,
                                @Field("phonenumber_order") String phonenumber_order,
                                @Field("fromflight_order") String fromflight_order,
                                @Field("toflight_order") String toflight_order,
                                @Field("fromflighttime_order") String fromflighttime_order,
                                @Field("toflighttime_order") String toflighttime_order,
                                @Field("nameflight_order") String nameflight_order,
                                @Field("iduser_order") String iduser_order,
                                @Field("price_order") String price_order
    );
    @POST("trains/buyticket")
    @FormUrlEncoded
    Call<Order> buyTrainTicket(@Field("firstname_order") String firstname_order,
                               @Field("lastname_order") String lastname_order,
                               @Field("email_order") String email_order,
                               @Field("address_order") String address_order,
                               @Field("phonenumber_order") String phonenumber_order,
                               @Field("fromflight_order") String fromflight_order,
                               @Field("toflight_order") String toflight_order,
                               @Field("fromflighttime_order") String fromflighttime_order,
                               @Field("toflighttime_order") String toflighttime_order,
                               @Field("nameflight_order") String nameflight_order,
                               @Field("iduser_order") String iduser_order,
                               @Field("price_order") String price_order
    );

    @POST("orders/showorder")
    @FormUrlEncoded
    Call<Order> showOrder(@Field("iduser_order") String iduser_order);
    @GET("orders/showTransaksi")
    Call<Order> showTransaksi();
    @GET("orders/showLaporan")
    Call<LaporanList> showLaporan();
    //===========================Train==================================//
    @POST("trains/addtrains")
    @FormUrlEncoded
    Call<GetTrain> addTrain(@Field("from_train") String from_train,
                            @Field("to_train") String to_train,
                            @Field("fromtime_train") String fromtime_train,
                            @Field("totime_train") String totime_train,
                            @Field("price_train") String price_train,
                            @Field("class_train") String class_train,
                            @Field("name_train") String name_train);
    @GET("trains")
    Call<Train> getTrain();

    @POST("trains/updateTrains")
    @FormUrlEncoded
    Call<GetTrain> updatetrains(@Field("id_train") String id_train,
                                @Field("from_train") String from_train,
                                @Field("to_train") String to_train,
                                @Field("fromtime_train") String fromtime_train,
                                @Field("totime_train") String totime_train,
                                @Field("price_train") String price_train,
                                @Field("class_train") String class_train,
                                @Field("name_train") String name_train);
    @POST("trains/deleteTrains")
    @FormUrlEncoded
    Call<GetTrain> deletetrains(@Field("id_train") String id_train);


}
