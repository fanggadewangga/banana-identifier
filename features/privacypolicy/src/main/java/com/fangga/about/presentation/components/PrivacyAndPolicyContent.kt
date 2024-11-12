package com.fangga.about.presentation.components

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
fun PrivacyAndPolicyContent(modifier: Modifier = Modifier) {
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
                        text = "Kami sangat menghargai privasi Anda. Kebijakan Privasi ini menjelaskan bagaimana kami mengumpulkan, menggunakan, dan melindungi informasi pribadi Anda ketika Anda menggunakan aplikasi kami. Dengan menggunakan aplikasi kami, Anda setuju dengan pengumpulan dan penggunaan informasi sesuai dengan kebijakan ini.",
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
                title = "Informasi yang Kami Kumpulkan",
                content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        AppText(text = "•")
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            AppText(text = "Informasi Penggunaan", textStyle = bodyText12Medium)
                            AppText(
                                text = "Data tentang bagaimana Anda menggunakan aplikasi, termasuk fitur yang digunakan dan waktu penggunaan.",
                                textStyle = bodyText12Regular
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        AppText(text = "•")
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            AppText(text = "Informasi Teknologi", textStyle = bodyText12Medium)
                            AppText(
                                text = "Alamat IP, jenis perangkat, sistem operasi, dan informasi teknis lainnya.",
                                textStyle = bodyText12Regular
                            )
                        }
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
                title = "Penggunaan Informasi",
                content = {
                    AppText(
                        text = "Kami menggunakan informasi yang kami kumpulkan untuk:",
                        textStyle = bodyText12Regular
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        AppText(
                            text = "Menyediakan dan meningkatkan layanan kami.",
                            textStyle = bodyText12Regular
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        AppText(
                            text = "Memperbaiki pengalaman pengguna dan menyesuaikan konten.",
                            textStyle = bodyText12Regular
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AppText(text = "•")
                        AppText(
                            text = "Menanggapi pertanyaan dan dukungan pengguna.",
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
                number = "4",
                title = "Perlindungan Informasi",
                content = {
                    AppText(
                        text = "Kami mengambil langkah-langkah keamanan yang wajar untuk melindungi informasi pribadi Anda dari akses, perubahan, pengungkapan, atau penghancuran yang tidak sah. Namun, tidak ada metode pengiriman data melalui internet atau metode penyimpanan elektronik yang 100% aman",
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
                title = "Pengungkapan kepada Pihak Ketiga",
                content = {
                    AppText(
                        text = "Kami tidak akan menjual, menyewa, atau membagikan informasi pribadi Anda kepada pihak ketiga tanpa izin Anda, kecuali jika diwajibkan oleh hukum atau dalam hal ada kebutuhan untuk menjalankan layanan kami.",
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
                title = "Hak Anda",
                content = {
                    AppText(
                        text = "Anda memiliki hak untuk mengakses, memperbarui, atau menghapus informasi pribadi Anda yang kami miliki. Untuk melakukannya, Anda dapat menghubungi kami melalui informasi kontak yang tertera di bawah.",
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
                number = "7",
                title = "Perubahan pada Kebijakan Privasi",
                content = {
                    AppText(
                        text = "Kami dapat memperbarui Kebijakan Privasi ini dari waktu ke waktu. Kami akan memberi tahu Anda tentang perubahan tersebut dengan memposting kebijakan baru di aplikasi kami. Anda diharapkan untuk memeriksa kebijakan ini secara berkala.",
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
                number = "8",
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
                                text = "dewi_candra@ub.ac.id",
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