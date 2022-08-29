package com.modyo.dcifuentes.pokeindex.controllers;

import com.modyo.dcifuentes.pokeindex.persistence.entity.*;
import com.modyo.dcifuentes.pokeindex.persistence.models.EvolucionDto;
import com.modyo.dcifuentes.pokeindex.persistence.models.PageDto;
import com.modyo.dcifuentes.pokeindex.persistence.models.PokemonDetailDto;
import com.modyo.dcifuentes.pokeindex.persistence.models.PokemonDto;
import com.modyo.dcifuentes.pokeindex.services.IPokemonService;
import com.modyo.dcifuentes.pokeindex.services.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class PokeRestController {

	IPokemonService pokemonService;

	@Autowired
	public PokeRestController (ApplicationContext applicationContext)
	{
		pokemonService = applicationContext.getBean(PokemonServiceImpl.class);
	}

	@GetMapping("/pokemon")
	public PageDto showPage(@RequestParam(defaultValue = "0") String page,
									 @RequestParam(defaultValue = "20") String size) {
		int nPage;
		int nSize;

		try {
			nPage = Integer.parseInt(page);
			nSize = Integer.parseInt(size);
		}
		catch (NumberFormatException e){
			nPage = 0;
			nSize = 20;
			e.printStackTrace();
		}

		PokemonList pokemonList = pokemonService.findAll(nPage,nSize);
		PageDto listPage = new PageDto();
		listPage.setCount(pokemonList.getCount());
		listPage.setPokemonList(new ArrayList<>());

		for (Resource pokemonItem: pokemonList.getResults()) {
			PokemonDto item = new PokemonDto();
			Pokemon detail = pokemonService.findPokemonByResource(pokemonItem);
			item.setId(detail.getId());
			item.setName(detail.getName().substring(0,1).toUpperCase() +
					detail.getName().substring(1));
			item.setWeight(detail.getWeight());
			// Tipos del pokemon:
			item.setTypes(detail.getTypes().stream()
					.map(PokemonType::getType)
					.map(Resource::getName)
					.map(x -> x.substring(0,1).toUpperCase()+x.substring(1))
					.toList());
			// Habilidades del pokemon:
			item.setAbilities(detail.getAbilities().stream()
					.map(PokemonAbility::getAbility)
					.map(Resource::getName)
					.map(x -> x.substring(0,1).toUpperCase()+x.substring(1))
					.toList());
			// La imagen principal la obtenemos del "other/default":
			item.setUrlDefault(detail.getSprites().getUrlFrontDefault());
			listPage.getPokemonList().add(item);
		}
		listPage.setSize(listPage.getPokemonList().size());
		return listPage;
	}

	@GetMapping("/pokemon/{id}")
	public PokemonDetailDto showPokemon(@PathVariable Long id) {
		Pokemon pokemon = pokemonService.findById(id);
		PokemonDetailDto pokemonDetail = new PokemonDetailDto();
		pokemonDetail.setId(pokemon.getId());
		pokemonDetail.setName(pokemon.getName().substring(0,1).toUpperCase()+pokemon.getName().substring(1));
		pokemonDetail.setWeight(pokemon.getWeight());
		pokemonDetail.setHeight(pokemon.getHeight());

		pokemonDetail.setUrlBackDefault(pokemon.getSprites().getUrlBackDefault());
		pokemonDetail.setUrlBackFemale(pokemon.getSprites().getUrlBackFemale());
		pokemonDetail.setUrlBackShiny(pokemon.getSprites().getUrlBackShiny());
		pokemonDetail.setUrlBackShinyFemale(pokemon.getSprites().getUrlBackShinyFemale());
		pokemonDetail.setUrlFrontDefault(pokemon.getSprites().getUrlFrontDefault());
		pokemonDetail.setUrlFrontFemale(pokemon.getSprites().getUrlFrontFemale());
		pokemonDetail.setUrlFrontShiny(pokemon.getSprites().getUrlFrontShiny());
		pokemonDetail.setUrlFrontShinyFemale(pokemon.getSprites().getUrlFrontShinyFemale());

		// Obtenemos los tipos del pokemon:
		pokemonDetail.setTypes(pokemon.getTypes().stream()
				.map(PokemonType::getType)
				.map(Resource::getName)
				.map(x -> x.substring(0,1).toUpperCase()+x.substring(1))
				.toList());

		// Obtenemos las habilidades del pokemon:
		pokemonDetail.setAbilities(pokemon.getAbilities().stream()
				.map(PokemonAbility::getAbility)
				.map(Resource::getName)
				.map(x -> x.substring(0,1).toUpperCase()+x.substring(1))
				.toList());

		// La descripción (Pokedex) y la cadena de evoluciones la obtenemos de Species
		Species species = pokemonService.findSpeciesByResource(pokemon.getSpecies());
		EvolutionChain evolutionChain = pokemonService.findEvolutionByResource(species.getEvolutionChain());

		// Enviamos la descripción, la calculamos a partir de la generación del pokemon.
		pokemonDetail.setDescripcion(species.getFlavorTextEntries().stream()
				.filter(flavorText -> flavorText.getLanguage().getName().equals("es") ||
						flavorText.getLanguage().getName().equals("en"))
				.map(FlavorText::getFlavorTextEntry)
				.map( x -> x.replaceAll("\n", " ")
					.replaceAll("\r", " ")
					.replaceAll("\t", " ")
					.replaceAll("\b", " ")
					.replaceAll("\f", " "))
				.filter(x -> x != null && !x.isEmpty())
				.findFirst()
				.orElse(null));

		// Armamos la cadena de evoluciones con un Map<Nombre, Nivel>, esto servirá en el front
		// ya que un pokemon puede evolucionar a más de un tipo de pokemon, en el mismo front se
		// validará de qué pokemon estamos consultando el detalle para hacer una diferenciación
		// dentro de la cadena.
		ChainLink evolution = evolutionChain.getChain();
		EvolucionDto cadenaEvolutiva = new EvolucionDto();

		// Obtenemos el primer nivel de Pokemon, este será el nivel 0
		cadenaEvolutiva.setNivel(0);
		cadenaEvolutiva.setName(evolution.getSpecies().getName().substring(0,1).toUpperCase() +
				evolution.getSpecies().getName().substring(1));


		// Las siguientes evoluciones, se obtienen de una posible lista con un nivel
		// que no se conoce a priori por lo que usaremos una función recursiva:
		obtenerCadenaEvolutiva(cadenaEvolutiva,
				evolution.getEvolvesTo(),
				cadenaEvolutiva.getNivel()+1);

		pokemonDetail.setCadenaEvolutiva(cadenaEvolutiva);
		return pokemonDetail;
	}

	private void obtenerCadenaEvolutiva(EvolucionDto cadenaEvolutiva,
										List<ChainLink> arbolEvolutivo,
										Integer nivelactual) {
		// Si no hay evoluciones, se retorna
		if(arbolEvolutivo == null || arbolEvolutivo.isEmpty())
			return;
		// Si hay evoluciones, se crea la lista si no está creada
		if(cadenaEvolutiva.getSigNivel() == null)
			cadenaEvolutiva.setSigNivel(new ArrayList<>());

		// Una vez que se encuentra creada, pasamos a iterar este nivel:
		for (ChainLink nodo : arbolEvolutivo) {
			EvolucionDto evolucionActual = new EvolucionDto();
			evolucionActual.setNivel(nivelactual);
			evolucionActual.setName(nodo.getSpecies().getName().substring(0,1).toUpperCase() +
					nodo.getSpecies().getName().substring(1));
			obtenerCadenaEvolutiva(evolucionActual,
					nodo.getEvolvesTo(),
					evolucionActual.getNivel()+1);
			cadenaEvolutiva.getSigNivel().add(evolucionActual);
		}
	}


}

