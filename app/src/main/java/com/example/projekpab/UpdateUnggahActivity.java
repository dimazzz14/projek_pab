package com.example.projekpab;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projekpab.databinding.ActivityUpdateUnggahBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUnggahActivity extends AppCompatActivity {
    private ActivityUpdateUnggahBinding binding;
    private Unggah unggah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateUnggahBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        unggah = getIntent().getParcelableExtra("EXTRA_DATA");
        String id = unggah.getId();

        binding.etNama.setText(unggah.getNama());
        binding.etAlamat.setText(unggah.getAlamat());
        binding.etTelepon.setText(unggah.getTelepon());
        binding.etMotor.setText(unggah.getMotor());
        binding.etJenis.setText(unggah.getJenis());
        binding.etServis.setText(unggah.getServis());
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.etNama.getText().toString();
                String alamat = binding.etAlamat.getText().toString();
                String telepon = binding.etTelepon.getText().toString();
                String motor = binding.etMotor.getText().toString();
                String jenis = binding.etJenis.getText().toString();
                String servis = binding.etServis.getText().toString();

                boolean bolehUpdate = true;

                if (TextUtils.isEmpty(nama)) {
                    bolehUpdate = false;
                    binding.etNama.setError("Nama tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(alamat)) {
                    bolehUpdate = false;
                    binding.etAlamat.setError("Alamat tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(telepon)) {
                    bolehUpdate = false;
                    binding.etTelepon.setError("Nomor telepon tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(motor)) {
                    bolehUpdate = false;
                    binding.etMotor.setError("Nama motor tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(jenis)) {
                    bolehUpdate = false;
                    binding.etJenis.setError("Jenis motor tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(servis)) {
                    bolehUpdate = false;
                    binding.etServis.setError("Servis yang diinginkan tidak boleh kosong!");
                }

                if(bolehUpdate){
                    updateUnggah(id, nama, alamat, telepon, motor, jenis, servis);
                }
            }
        });
    }

    private void updateUnggah(String id, String nama, String alamat, String telepon, String motor, String jenis, String servis) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.updateUnggah(id,nama,alamat,telepon,motor,jenis,servis);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code()==200){
                    int succes = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (succes == 1){
                        Toast.makeText(UpdateUnggahActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(UpdateUnggahActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(UpdateUnggahActivity.this, "Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error :"+t.getMessage());
                Toast.makeText(UpdateUnggahActivity.this, "Retrofit Error :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
