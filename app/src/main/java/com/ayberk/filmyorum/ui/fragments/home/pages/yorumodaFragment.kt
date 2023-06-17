package com.ayberk.filmyorum.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.filmyorum.R
import com.ayberk.filmyorum.adapter.ChatRecycclerAdapter
import com.ayberk.filmyorum.databinding.FragmentYorumodaBinding
import com.ayberk.filmyorum.di.retrofit.models.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class yorumodaFragment : Fragment() {

    private var _binding : FragmentYorumodaBinding? = null
    private val binding get() = _binding!!
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ChatRecycclerAdapter
    private var chats = arrayListOf<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firestore = Firebase.firestore
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYorumodaBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatRecycclerAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.backimage.setOnClickListener {
            findNavController().navigate(R.id.yorumFragment)
        }
        arguments?.let {

            val odaismi = yorumodaFragmentArgs.fromBundle(it).odaisim

            if (odaismi == "Aksiyon") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.aksiyon)

                binding.gonderBtn.setOnClickListener {
                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Aksiyon").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }
                        firestore.collection("Aksiyon").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }


            }
            if (odaismi == "Animasyon") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.animation)

                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Animasyon").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }

                        firestore.collection("Animasyon").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
            }
            if (odaismi == "Korku") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.korku)

                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Korku").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }

                        firestore.collection("Korku").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
            }
            if (odaismi == "Bilim Kurgu") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.bilimkurgu)
                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("BilimKurgu").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }

                        firestore.collection("BilimKurgu").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
            }
            if (odaismi == "Romantik") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.romantik)
                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Romantik").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }


                firestore.collection("Romantik").orderBy(
                    "date",
                    Query.Direction.ASCENDING
                ).addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            requireContext(),
                            error.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        if (value != null) {
                            if (value.isEmpty) {
                                Toast.makeText(requireContext(), "Mesaj Yok", Toast.LENGTH_LONG)
                                    .show()
                            } else {

                                val documents = value.documents
                                chats.clear()
                                for (document in documents) {
                                    val text = document.get("text") as String
                                    val user = document.get("user") as String
                                    val chat = Chat(user, text)
                                    chats.add(chat)
                                    adapter.chats = chats
                                }
                            }
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            if (odaismi == "Fantezi") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.fantezi)
                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Fantezi").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }

                        firestore.collection("Fantezi").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
            }
            if (odaismi == "Belgesel") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.belgesel)
                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Belgesel").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }

                        firestore.collection("Belgesel").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                }
           }
            if (odaismi == "Drama") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.drama)
                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Drama").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }

                        firestore.collection("Drama").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
            }
            if (odaismi == "Komedi") {
                binding.txtodaismi.text = odaismi
                binding.imageView6.setImageResource(R.drawable.komedi)
                binding.gonderBtn.setOnClickListener {

                    if (binding.yorumText.text.toString().trim().equals("")) {
                        Toast.makeText(requireContext(), "Lütfen Mesaj Giriniz", Toast.LENGTH_LONG)
                            .show()
                    } else {

                        auth.currentUser?.let {
                            val chatText = binding.yorumText.text.toString()
                            val date = FieldValue.serverTimestamp()
                            val user = it.email

                            val dataMap = HashMap<String, Any>()
                            dataMap.put("text", chatText)
                            dataMap.put("user", user!!)
                            dataMap.put("date", date)

                            firestore.collection("Komedi").add(dataMap).addOnSuccessListener {
                                binding.yorumText.text.clear()
                            }.addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.yorumText.setText("")
                            }
                        }
                    }
                }
                        firestore.collection("Komedi").orderBy(
                            "date",
                            Query.Direction.ASCENDING
                        ).addSnapshotListener { value, error ->

                            if (error != null) {
                                Toast.makeText(
                                    requireContext(),
                                    error.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (value != null) {
                                    if (value.isEmpty) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Mesaj Yok",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {

                                        val documents = value.documents
                                        chats.clear()
                                        for (document in documents) {
                                            val text = document.get("text") as String
                                            val user = document.get("user") as String
                                            val chat = Chat(user, text)
                                            chats.add(chat)
                                            adapter.chats = chats
                                        }
                                    }
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                }
     }
}