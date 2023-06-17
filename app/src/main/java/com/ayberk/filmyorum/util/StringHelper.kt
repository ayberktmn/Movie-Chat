package com.ayberk.filmyorum.util

class StringHelper {

    fun getTrName(name: String):String {
        var tr_name = ""
        if (name == "Action") {
            tr_name = "Aksiyon"
        } else if (name == "Adventure") {
            tr_name = "Macera"
        }
        else if (name == "Animation") {
            tr_name = "Animasyon"
        }
        else if (name == "Comedy") {
            tr_name = "Komedi"
        }
        else if (name == "Crine") {
            tr_name = "Suç"
        }
        else if (name == "Documentary") {
            tr_name = "Belgesel"
        }
        else if (name == "Drama") {
            tr_name = "Drama"
        }
        else if (name == "Family") {
            tr_name = "Aile"
        }
        else if (name == "Fantasy") {
            tr_name = "Fantezi"
        }
        else if (name == "History") {
            tr_name = "Hikaye"
        }
        else if (name == "Horror") {
            tr_name = "Korku"
        }
        else if (name == "Muzic") {
            tr_name = "Muzik"
        }
        else if (name == "Mystery") {
            tr_name = "Gizem"
        }
        else if (name == "Romance") {
            tr_name = "Romantik"
        }
        else if (name == "Science Fiction") {
            tr_name = "Bilim Kurgu"
        }
        else if (name == "Tv Movie") {
            tr_name = "TV Filmi"
        }
        else if (name == "Thriller") {
            tr_name = "Gerilim"
        }
        else if (name == "War") {
            tr_name = "Savaş"
        }
        else if (name == "Western") {
            tr_name = "Batı"
        }
        return tr_name
    }
}