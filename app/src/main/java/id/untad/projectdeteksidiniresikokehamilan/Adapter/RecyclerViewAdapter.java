package id.untad.projectdeteksidiniresikokehamilan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.untad.projectdeteksidiniresikokehamilan.Model.Deteksi;
import id.untad.projectdeteksidiniresikokehamilan.Model.Question;
import id.untad.projectdeteksidiniresikokehamilan.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<Deteksi> dataList;
    private Context context;

    //Membuat Konstruktor pada Class RecyclerViewAdapter
    public RecyclerViewAdapter(ArrayList<Deteksi> dataList){
        this.dataList = dataList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Q1, Q2, Q3,Q4;

        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            Q1 = itemView.findViewById(R.id.option1);
            Q2 = itemView.findViewById(R.id.tv_answernr);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String q1 = dataList.get(position).getPertanyaan();//Mengambil data (Nama) sesuai dengan posisi yang telah ditentukan
        final String q2 = dataList.get(position).getJawaban();//Mengambil data (Jurusan) sesuai dengan posisi yang telah ditentukan//Mengambil data (NIM) sesuai dengan posisi yang telah ditentukan
        holder.Q1.setText(q1);
        holder.Q2.setText("Jawaban :" + q2);
        //holder.Q4.setText(q4);
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return dataList.size();
    }

    void setFilter(ArrayList<Deteksi> filterList){
        dataList = new ArrayList<>();
        dataList.addAll(filterList);
        notifyDataSetChanged();
    }

}