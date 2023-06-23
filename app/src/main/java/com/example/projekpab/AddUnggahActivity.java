package com.example.projekpab;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projekpab.databinding.ActivityAddUnggahBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUnggahActivity extends AppCompatActivity {
    private ActivityAddUnggahBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddUnggahBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.etNama.getText().toString();
                String alamat = binding.etAlamat.getText().toString();
                String telepon = binding.etTelepon.getText().toString();
                String motor = binding.etMotor.getText().toString();
                String jenis = binding.etJenis.getText().toString();
                String servis = binding.etServis.getText().toString();

                boolean bolehUnggah = true;

                if (TextUtils.isEmpty(nama)) {
                    bolehUnggah = false;
                    binding.etNama.setError("Nama tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(alamat)) {
                    bolehUnggah = false;
                    binding.etAlamat.setError("Alamat tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(telepon)) {
                    bolehUnggah = false;
                    binding.etTelepon.setError("Nomor telepon tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(motor)) {
                    bolehUnggah = false;
                    binding.etMotor.setError("Nama motor tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(jenis)) {
                    bolehUnggah = false;
                    binding.etJenis.setError("Jenis motor tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(servis)) {
                    bolehUnggah = false;
                    binding.etServis.setError("Servis yang diinginkan tidak boleh kosong!");
                }

                if (bolehUnggah) {
                    String userId = Utilities.getValue(AddUnggahActivity.this, "xUserId");
                    addUnggah(nama,alamat,telepon,motor,jenis,servis,userId);
                }
            }
        });
    }

    private void addUnggah(String nama, String alamat, String telepon, String motor, String jenis, String servis, String userId) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.addUnggah(nama,alamat,telepon,motor,jenis,servis,userId);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(AddUnggahActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddUnggahActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddUnggahActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error :" + t.getMessage());
                Toast.makeText(AddUnggahActivity.this, "Retrofit Error :" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
