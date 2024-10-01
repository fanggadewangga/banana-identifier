package com.fangga.termscondition.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.components.feature.PointItem
import com.fangga.core.resource.bodyText12Medium
import com.fangga.core.resource.bodyText12Regular

@Composable
fun TermsConditionContent(modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            PointItem(
                number = "1",
                title = "Pengantar",
                content = {
                    AppText(
                        text = "Syarat dan Ketentuan ini mengatur penggunaan aplikasi kami. Dengan mengakses atau menggunakan aplikasi kami, Anda setuju untuk mematuhi syarat dan ketentuan ini. Jika Anda tidak setuju dengan syarat dan ketentuan ini, harap jangan gunakan aplikasi kami.",
                        textStyle = bodyText12Regular
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }

        item {
            PointItem(
                number = "2",
                title = "Penggunaan Aplikasi",
                content = {
                    AppText(
                        text = "Anda setuju untuk menggunakan aplikasi kami hanya untuk tujuan yang sah dan sesuai dengan hukum yang berlaku. Anda tidak boleh menggunakan aplikasi kami untuk:",
                        textStyle = bodyText12Regular
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        AppText(
                            text = "Melanggar hak kekayaan intelektual orang lain.",
                            textStyle = bodyText12Regular
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        AppText(
                            text = "Mengirimkan konten yang tidak sah, menyesatkan, atau berbahaya.",
                            textStyle = bodyText12Regular
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        AppText(
                            text = "Mengganggu atau merusak aplikasi atau server kami.",
                            textStyle = bodyText12Regular
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }

        item {
            PointItem(
                number = "3",
                title = "Hak Kekayaan Intelektual",
                content = {
                    AppText(
                        text = "Semua konten dan materi di aplikasi kami, termasuk tetapi tidak terbatas pada teks, grafik, logo, dan perangkat lunak, adalah milik kami atau pihak ketiga yang memberikan lisensi kepada kami. Anda tidak diperbolehkan untuk menyalin, mendistribusikan, atau menggunakan materi tersebut tanpa izin tertulis dari kami.",
                        textStyle = bodyText12Regular
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }

        item {
            PointItem(
                number = "4",
                title = "Pembatasan Tanggung Jawab",
                content = {
                    AppText(
                        text = "Kami tidak bertanggung jawab atas kerugian atau kerusakan yang timbul dari penggunaan aplikasi kami, termasuk tetapi tidak terbatas pada kerusakan langsung, tidak langsung, insidental, atau konsekuensial. Kami tidak menjamin bahwa aplikasi kami akan selalu tersedia atau bebas dari kesalahan.",
                        textStyle = bodyText12Regular
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }

        item {
            PointItem(
                number = "5",
                title = "Perubahan pada Syarat dan Ketentuan",
                content = {
                    AppText(
                        text = "Kami dapat memperbarui Syarat dan Ketentuan ini dari waktu ke waktu. Perubahan akan diposting di aplikasi kami, dan penggunaan Anda yang berkelanjutan terhadap aplikasi setelah perubahan tersebut menunjukkan penerimaan Anda terhadap syarat dan ketentuan yang diperbarui.",
                        textStyle = bodyText12Regular
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }

        item {
            PointItem(
                number = "6",
                title = "Kontak Kami",
                content = {
                    AppText(
                        text = "Jika Anda memiliki pertanyaan tentang Kebijakan Privasi ini, silakan hubungi Fakultas Ilmu Komputer Universitas Brawijaya di:",
                        textStyle = bodyText12Regular
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        Column {
                            AppText(
                                text = "Email",
                                textStyle = bodyText12Medium
                            )
                            AppText(
                                text = "filkom@ub.ac.id",
                                textStyle = bodyText12Medium
                            )
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        Column {
                            AppText(
                                text = "Alamat",
                                textStyle = bodyText12Medium
                            )
                            AppText(
                                text = "Jl. Veteran, Ketawanggede, Lowokwaru, Kota Malang, Jawa Timur, Indonesia – 65145",
                                textStyle = bodyText12Medium
                            )
                        }

                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
    }
}