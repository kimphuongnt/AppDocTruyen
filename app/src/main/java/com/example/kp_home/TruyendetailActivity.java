package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class TruyendetailActivity extends AppCompatActivity {

    private TextView tenTruyenTextView, tacGiaTextView, ngayRaMatTextView, soluotlike, soluotdislike;
    private ImageView anhBiaImageView;
    private Button btndoc, btnDangBinhLuan, btnDangNhap;
    private ImageView btnLike, btnDislike;
    private LinearLayout commentSection;
    private EditText editTextBinhLuan;
    private UserBusiness userBusiness;
    private LikeBusiness likeBusiness;
    public BinhLuanBusiness binhLuanBusiness;
    private TruyenDaLuuBusiness truyenDaLuuBusiness;
    private ImageButton btnLuu;
    boolean checkSignIn = SignInActivity.checkSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyendetail);

        tenTruyenTextView = findViewById(R.id.tenTruyenTextView);
        anhBiaImageView = findViewById(R.id.anhBiaImageView);
        tacGiaTextView = findViewById(R.id.tacGiaTextView);
        ngayRaMatTextView = findViewById(R.id.ngayRaMatTextView);
        btndoc = findViewById(R.id.btndocsach);
        commentSection = findViewById(R.id.commentSection);
        editTextBinhLuan = findViewById(R.id.edittextbinhluan);
        btnDangBinhLuan = findViewById(R.id.btnDangBinhLuan);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnLike = findViewById(R.id.btnlike);
        btnDislike = findViewById(R.id.btndislike);
        soluotlike = findViewById(R.id.soluotlike);
        soluotdislike = findViewById(R.id.soluotdislike);
        btnLuu = findViewById(R.id.btnluu);


        userBusiness = new UserBusiness(this);
        likeBusiness = new LikeBusiness(this);
        truyenDaLuuBusiness = new TruyenDaLuuBusiness(this);

        if (userBusiness.isLoggedIn() && checkSignIn) {
            btnDangNhap.setVisibility(View.GONE);
            editTextBinhLuan.setVisibility(View.VISIBLE);
            btnDangBinhLuan.setVisibility(View.VISIBLE);
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.VISIBLE);
            User user = userBusiness.getLoggedInUser();
            if (user != null) {
                btnDangBinhLuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment = editTextBinhLuan.getText().toString().trim();
                        int userId = userBusiness.getCurrentUserId();
                        if (userId != -1) {
                            dangBinhLuan(comment, userId);
                        }
                    }
                });
                btnLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int userId = userBusiness.getCurrentUserId();
                        if (userId != -1) {
                            int maTruyen = getIntent().getIntExtra("maTruyen", -1);
                            if (maTruyen != -1) {
                                boolean isSuccess = likeBusiness.addOrUpdateLike(userId, maTruyen, true);
                                if (isSuccess) {
                                    Toast.makeText(TruyendetailActivity.this, "Đã like", Toast.LENGTH_SHORT).show();
                                    updateLikeDislike(maTruyen);
                                } else {
                                    Toast.makeText(TruyendetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
                btnDislike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int userId = userBusiness.getCurrentUserId();
                        if (userId != -1) {
                            int maTruyen = getIntent().getIntExtra("maTruyen", -1);
                            if (maTruyen != -1) {
                                boolean isSuccess = likeBusiness.addOrUpdateLike(userId, maTruyen, false);
                                if (isSuccess) {
                                    Toast.makeText(TruyendetailActivity.this, "Đã dislike", Toast.LENGTH_SHORT).show();
                                    updateLikeDislike(maTruyen);
                                } else {
                                    Toast.makeText(TruyendetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int userId = userBusiness.getCurrentUserId();
                        if (userId != -1) {
                            int maTruyen = getIntent().getIntExtra("maTruyen", -1);
                            if (maTruyen != -1) {
                                boolean isSuccess = truyenDaLuuBusiness.addTruyenDaLuu(userId, maTruyen);
                                boolean checkLuu = truyenDaLuuBusiness.checkTruyenDaLuu(userId, maTruyen);
                                if (isSuccess) {
                                    Toast.makeText(TruyendetailActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();

                                } else if (checkLuu) {
                                    Toast.makeText(TruyendetailActivity.this, "Đã lưu trước đó", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(TruyendetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                });
            }
        } else {
            btnDangNhap.setVisibility(View.VISIBLE);
            editTextBinhLuan.setVisibility(View.GONE);
            btnDangBinhLuan.setVisibility(View.GONE);
            btnLike.setVisibility(View.GONE);
            btnDislike.setVisibility(View.GONE);

            btnDangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TruyendetailActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            });
        }

        int maTruyen = getIntent().getIntExtra("maTruyen", -1);
        if (maTruyen != -1) {
            TruyenBusiness truyenBusiness = new TruyenBusiness(this);
            Truyen truyen = truyenBusiness.getTruyenByMaTruyen(maTruyen);

            tenTruyenTextView.setText(truyen.getTenTruyen());
            tacGiaTextView.setText(truyen.getTacGia());
            ngayRaMatTextView.setText(truyen.getNgayRaMat());
            int imageResource = getResources().getIdentifier(truyen.getAnhBia(), "drawable", TruyendetailActivity.this.getPackageName());

            if (imageResource != 0) {
                Glide.with(this)
                        .load(imageResource)
                        .into(anhBiaImageView);
            }

            binhLuanBusiness = new BinhLuanBusiness(this);
            List<BinhLuan> comments = binhLuanBusiness.getBinhLuan(maTruyen);
            hienThiLikeDislike();
            hienThiComment(comments);
        }

        btndoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TruyendetailActivity.this, ChuongDetailActivity.class);
                intent.putExtra("maTruyen", maTruyen);
                startActivity(intent);
            }
        });
    }

    private void hienThiComment(List<BinhLuan> comments) {
        commentSection.removeAllViews();

        for (BinhLuan comment : comments) {
            View commentView = getLayoutInflater().inflate(R.layout.binhluan_item, null);

            TextView userIdTextView = commentView.findViewById(R.id.tenNDTextView);
            TextView noiDungTextView = commentView.findViewById(R.id.noiDungTextView);
            TextView ngayBinhLuanTextView = commentView.findViewById(R.id.ngayBinhLuanTextView);

            userIdTextView.setText(String.valueOf(comment.getTenND()));
            noiDungTextView.setText(comment.getNoiDung());
            ngayBinhLuanTextView.setText(comment.getNgayBinhLuan());

            commentSection.addView(commentView);
        }
    }


    private void hienThiLikeDislike() {
        int maTruyen = getIntent().getIntExtra("maTruyen", -1);
        if (maTruyen != -1) {
            int demLike = likeBusiness.getSoLike(maTruyen);
            int demDislike = likeBusiness.getSoDislike(maTruyen);

            TextView soluotlike = findViewById(R.id.soluotlike);
            TextView soluotdislike = findViewById(R.id.soluotdislike);

            soluotlike.setText(String.valueOf(demLike));
            soluotdislike.setText(String.valueOf(demDislike));
        } else {
            Toast.makeText(this, "Không tìm thấy truyện", Toast.LENGTH_SHORT).show();
        }
    }


    private void dangBinhLuan(String comment, int userId) {
        if (comment.isEmpty()) {
            Toast.makeText(this, "Nội dung bình luận không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }

        int maTruyen = getIntent().getIntExtra("maTruyen", -1);
        if (maTruyen == -1) {
            Toast.makeText(this, "Không tìm thấy truyện", Toast.LENGTH_SHORT).show();
            return;
        }

        BinhLuanBusiness binhLuanBusiness = new BinhLuanBusiness(this);
        boolean isAdded = binhLuanBusiness.addBinhLuan(maTruyen, userId, comment);

        if (isAdded) {
            Toast.makeText(this, "Đã thêm bình luận", Toast.LENGTH_SHORT).show();
            editTextBinhLuan.setText("");


            List<BinhLuan> comments = binhLuanBusiness.getBinhLuan(maTruyen);
            hienThiComment(comments);
        } else {
            Toast.makeText(this, "Thêm bình luận thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLikeDislike(int maTruyen) {
        int likeCount = likeBusiness.getSoLike(maTruyen);
        int dislikeCount = likeBusiness.getSoDislike(maTruyen);

        soluotlike.setText(String.valueOf(likeCount));
        soluotdislike.setText(String.valueOf(dislikeCount));
    }
}
