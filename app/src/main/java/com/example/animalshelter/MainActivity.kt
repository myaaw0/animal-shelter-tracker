package com.example.animalshelter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.animalshelter.AnimalClasses.Cat
import com.example.animalshelter.AnimalClasses.Dog
import com.example.animalshelter.AnimalClasses.Shelter.Companion.SelectedAnimal
import com.example.animalshelter.AnimalClasses.Shelter.Companion.SelectedShelter

import com.example.animalshelter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnDataPass {
    private lateinit var app: MyApplication
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        app = application as MyApplication

        setCurrentFragment(MapsFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemInput -> setCurrentFragment(InputFragment())
                R.id.itemList -> setCurrentFragment(AnimalListFragment())
                R.id.itemMap -> setCurrentFragment(MapsFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

    override fun onDataPass(data: String) {
        val strings = data.split(",").toTypedArray()
        val name = strings[0]
        val age = strings[1].toInt()
        val breed = strings[2]
        val isAdopted = strings[3].toBoolean()
        val isWild = strings[4].toBoolean()

        val animal = when (val type = strings[5]) {
            "Cat" -> Cat(name, age, isAdopted, breed, isWild)
            "Dog" -> Dog(name, age, isAdopted, breed, isWild)
            else -> throw IllegalArgumentException("Invalid animal type: $type")
        }

        if (SelectedAnimal != null) {
            SelectedShelter?.getList()
                ?.set(SelectedShelter!!.getList().indexOf(SelectedAnimal), animal)
        } else {
            SelectedShelter?.getList()?.add(animal)
        }
    }


}


