package com.fangga.tips.data

import com.fangga.core.resource.tipsBananaType
import com.fangga.core.resource.tipsRipenessType
import com.fangga.core.resource.tipsScan
import com.fangga.tips.domain.model.TipsDetail
import com.fangga.tips.domain.model.TipsStep

object Static {
    val tipsDetail = listOf(
        TipsDetail(
            tipsId = "scan_tips",
            title = "Tips Mudah untuk Memindai Pisang dengan Akurat",
            description = "Mendapatkan hasil scan yang akurat adalah kunci untuk mengetahui jenis dan tingkat kematangan pisang dengan tepat. Aplikasi kami dapat mengenali berbagai jenis pisang seperti Ambon, Lumut, Morosebo, Kepok, Hijau, Susu, dan Raja. Berikut adalah beberapa tips mudah untuk memastikan pemindaian Anda optimal:",
            steps = listOf(
                TipsStep(
                    number = 1,
                    title = "Pilih Pencahayaan yang Baik",
                    description = "Pastikan pisang berada di area dengan pencahayaan yang cukup. Hindari pencahayaan yang terlalu terang atau terlalu redup agar kamera dapat menangkap gambar dengan jelas."
                ),
                TipsStep(
                    number = 2,
                    title = "Posisikan Pisang di Tengah Frame",
                    description = "Saat memindai, pastikan pisang berada di tengah-tengah layar dan sepenuhnya terlihat dalam frame kamera."
                ),
                TipsStep(
                    number = 3,
                    title = "Jangan Terlalu Dekat atau Jauh",
                    description = "Jaga jarak yang tepat antara pisang dan kamera. Jarak ideal memungkinkan kamera untuk fokus dengan baik tanpa mengaburkan gambar."
                ),
                TipsStep(
                    number = 4,
                    title = "Hindari Gambar Buram",
                    description = "Pastikan kamera Anda fokus dengan baik sebelum memotret. Gambar buram dapat membuat aplikasi sulit untuk mendeteksi jenis dan tingkat kematangan pisang secara akurat"
                ),
            ),
            imageSource = tipsScan
        ),
        TipsDetail(
            tipsId = "banana_type_tips",
            title = "Pisang yang Bisa Anda Scan di Aplikasi: Mengenal Jenis dan Karakteristiknya",
            description = "Aplikasi ini memudahkan dalam mengenali berbagai jenis pisang hanya dengan sekali scan. Beberapa jenis pisang yang bisa dikenali oleh aplikasi ini memiliki karakteristik unik yang mungkin belum Anda ketahui. Yuk, simak berbagai jenis pisang yang dapat Anda scan:",
            steps = listOf(
                TipsStep(
                    number = 1,
                    title = "Pisang Ambon",
                    description = "Pisang Ambon terkenal dengan ukuran yang cukup besar dan kulit yang kuning cerah saat matang. Daging buahnya lembut dan manis, sering dijadikan bahan smoothie atau dimakan langsung. Pisang ini juga populer karena memiliki aroma harum yang khas."
                ),
                TipsStep(
                    number = 2,
                    title = "Pisang Lumut",
                    description = "Sesuai namanya, pisang lumut memiliki kulit hijau keabuan bahkan saat matang. Namun, jangan tertipu oleh tampilannya. Pisang ini tetap manis saat dikonsumsi dan cocok dimakan langsung maupun sebagai pelengkap dalam berbagai olahan makanan."
                ),
                TipsStep(
                    number = 3,
                    title = "Pisang Morosebo",
                    description = "Pisang morosebo sering digunakan dalam masakan tradisional. Ukurannya besar, daging buahnya kenyal, dan sering kali dipanggang atau digoreng. Morosebo memiliki rasa yang agak netral sehingga dapat menyerap bumbu dengan baik."
                ),
                TipsStep(
                    number = 4,
                    title = "Pisang Kepok",
                    description = "Jenis pisang ini sangat populer untuk dijadikan pisang goreng atau pisang rebus. Kulitnya tebal dan daging buahnya padat serta sedikit bertepung. Pisang kepok memiliki rasa yang lebih manis setelah dimasak, dan sering kali digunakan dalam berbagai hidangan lokal."
                ),
                TipsStep(
                    number = 5,
                    title = "Pisang Hijau",
                    description = "Meskipun dikenal sebagai pisang hijau karena kulitnya berwarna hijau saat belum matang, pisang ini berubah menjadi kuning saat matang sempurna. Daging buahnya lembut dan manis, sangat cocok untuk dikonsumsi langsung atau dibuat menjadi dessert."
                ),
                TipsStep(
                    number = 6,
                    title = "Pisang Susu",
                    description = "Pisang susu berukuran lebih kecil dibandingkan jenis pisang lainnya. Kulitnya tipis dengan warna kuning cerah saat matang. Daging buahnya sangat manis dengan tekstur lembut, sehingga sering disukai oleh anak-anak dan dijadikan sebagai camilan sehat."
                ),
                TipsStep(
                    number = 7,
                    title = "Pisang Raja",
                    description = "Pisang raja sering dianggap sebagai pisang \"premium\" karena rasanya yang manis dan aroma yang khas. Ukurannya sedang dengan kulit yang tebal dan kuning saat matang. Pisang ini sering dijadikan bahan dasar dalam pembuatan makanan tradisional seperti kolak atau pisang goreng."
                ),
            ),
            imageSource = tipsBananaType
        ),
        TipsDetail(
            tipsId = "ripeness_tips",
            title = "Memahami Jenis & Tipe Kematangan Pisang",
            description = "Mengetahui jenis dan tipe kematangan pisang dapat membantu Anda memutuskan cara terbaik untuk mengonsumsinya atau menggunakannya. Berikut adalah penjelasannya:",
            steps = listOf(
                TipsStep(
                    number = 1,
                    title = "Matang Alami",
                    description = "Pisang yang matang secara alami adalah pisang yang telah mencapai kematangan sempurna tanpa bantuan bahan kimia. Ini adalah tahap di mana pisang memiliki rasa dan tekstur terbaik. \n Pisang ini sudah siap dimakan langsung atau digunakan dalam berbagai resep seperti smoothie, kue, atau pancake. Nikmati kematangan alami ini untuk rasa dan tekstur terbaik."
                ),
                TipsStep(
                    number = 2,
                    title = "Matang Kimia",
                    description = "Pisang yang matang secara kimia adalah pisang yang dipercepat kematangannya menggunakan bahan kimia, seperti etilen. Proses ini dilakukan untuk mempercepat pematangan pisang agar siap dijual lebih cepat. \n Meskipun pisang ini siap dimakan, rasanya mungkin tidak sebanding dengan pisang yang matang secara alami. Jika memungkinkan, pilih pisang matang alami untuk kualitas dan rasa terbaik."
                ),
            ),
            imageSource = tipsRipenessType
        )
    )
}