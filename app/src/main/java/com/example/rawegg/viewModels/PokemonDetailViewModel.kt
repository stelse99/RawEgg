package com.example.rawegg.viewModels

import androidx.lifecycle.ViewModel
import com.example.rawegg.models.remote.responses.Pokemon
import com.example.rawegg.repository.PokemonRepository
import com.example.rawegg.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel
@Inject
constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}