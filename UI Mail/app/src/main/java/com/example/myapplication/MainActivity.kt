package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        // Sample data
        val emails = listOf(
            EmailItem(
                initial = "N",
                sender = "Netflix",
                subject = "Phim mới sắp ra mắt trong tháng!",
                preview = "Khám phá các bộ phim và series mới sắp có...",
                time = "10:20 AM",
                isStarred = true,
                backgroundColor = Color.parseColor("#E50914")
            ),
            EmailItem(
                initial = "T",
                sender = "Twitter",
                subject = "Cập nhật bảo mật mới",
                preview = "Đảm bảo rằng tài khoản của bạn luôn an toàn...",
                time = "9:45 AM",
                isStarred = false,
                backgroundColor = Color.parseColor("#1DA1F2")
            ),
            EmailItem(
                initial = "Y",
                sender = "YouTube",
                subject = "Video yêu thích của bạn vừa có phần tiếp theo!",
                preview = "Xem ngay phần tiếp theo của series bạn theo dõi...",
                time = "8:35 AM",
                isStarred = true,
                backgroundColor = Color.parseColor("#FF0000")
            ),
            EmailItem(
                initial = "B",
                sender = "Banking App",
                subject = "Giao dịch thành công",
                preview = "Giao dịch của bạn đã được thực hiện...",
                time = "8:00 AM",
                isStarred = false,
                backgroundColor = Color.parseColor("#00796B")
            ),
            EmailItem(
                initial = "D",
                sender = "Dropbox",
                subject = "File của bạn đã được chia sẻ",
                preview = "Một file mới đã được chia sẻ với bạn...",
                time = "7:50 AM",
                isStarred = true,
                backgroundColor = Color.parseColor("#007EE5")
            ),
            EmailItem(
                initial = "I",
                sender = "Instagram",
                subject = "Bài đăng mới từ bạn bè",
                preview = "Xem các hình ảnh và video mới từ bạn bè...",
                time = "7:15 AM",
                isStarred = false,
                backgroundColor = Color.parseColor("#E1306C")
            ),
            EmailItem(
                initial = "R",
                sender = "Reddit",
                subject = "Cộng đồng của bạn có bài đăng mới",
                preview = "Tham gia thảo luận trong các bài đăng mới...",
                time = "6:55 AM",
                isStarred = true,
                backgroundColor = Color.parseColor("#FF4500")
            ),
            EmailItem(
                initial = "M",
                sender = "Microsoft",
                subject = "Phiên bản mới của ứng dụng đã sẵn sàng",
                preview = "Tải bản cập nhật mới nhất để có trải nghiệm tốt hơn...",
                time = "6:30 AM",
                isStarred = false,
                backgroundColor = Color.parseColor("#737373")
            ),
            EmailItem(
                initial = "P",
                sender = "Pinterest",
                subject = "Ý tưởng mới cho dự án của bạn",
                preview = "Khám phá các ý tưởng trang trí và thiết kế...",
                time = "5:50 AM",
                isStarred = true,
                backgroundColor = Color.parseColor("#E60023")
            ),
            EmailItem(
                initial = "T",
                sender = "TikTok",
                subject = "Video thịnh hành hôm nay",
                preview = "Cùng xem các video đang hot trên TikTok...",
                time = "5:30 AM",
                isStarred = false,
                backgroundColor = Color.parseColor("#000000")
            )
        )

        listView.adapter = EmailAdapter(this, emails)
    }
}