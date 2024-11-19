package br.com.connectattoo.ui.screen_app

import br.com.connectattoo.domain.model.TagHomeScreen
import br.com.connectattoo.domain.model.TattoosBasedOnTagsHomeScreen

val tattooByTagsUrl = mutableListOf(
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_tesoura.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_cartas.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_passaro_na_mao.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_calavera.png",
)

 val tattooByNearbyArtistsUrl = mutableListOf(
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Ftattoo_borboleta.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Ftattoo_lion.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Ftattoo_tartaruga.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Ftattoo_Pezkoi.png",
)

 val randomTattoosUrl = mutableListOf(
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Ftattoo_rosto_cobras.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Ftattoo_escorpiao.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Ftattoo_olho.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Ftattoo_tigre.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Ftattoo_cobra.png"
)

 val nearbyProfileImage = mutableListOf(
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Favatar%2Favatar_larissa_dias.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/" +
            "home%2Fsecond_carousel%2Favatar%2Favatar_marcus_freites.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home" +
            "%2Fsecond_carousel%2Favatar%2Favatar_tatiana_oliveira.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Favatar%2Favatar_diogo_almeida.png"
)

 val randomProfileImage = mutableListOf(
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Favatar%2Favatar_maria_carla.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Favatar%2Favatar_sara_ferreira.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Favatar%2Favatar_maya_tattoo.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fthird_carousel%2Favatar%2Favatar_jose_fernades.png",
    "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Fsecond_carousel%2Favatar%2Favatar_diogo_almeida.png"
)

val listTattoosBasedOnTagsHomeScreen = listOf(
   TattoosBasedOnTagsHomeScreen(id= 1,
      imageTattoo = "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_tesoura.png",
      listTagHomeScreens = listOf(
         TagHomeScreen(id = 1, title = "1 Old Schol", backgroundDeepPurple = true),  TagHomeScreen(id = 1, title = "PB", backgroundDeepPurple = false), TagHomeScreen(id = 1, title = "1 Old", backgroundDeepPurple = false), TagHomeScreen(id = 1, title = "1 old 2", backgroundDeepPurple = true),
      )
   ),
   TattoosBasedOnTagsHomeScreen(id= 2,
      imageTattoo = "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_cartas.png",
      listTagHomeScreens = listOf(
         TagHomeScreen(id = 1, title = "2 Schol"),  TagHomeScreen(id = 1, title = "2 PBBBB"), TagHomeScreen(id = 1, title = "2 OldDDD"), TagHomeScreen(id = 1, title = "2 OldDDD"), TagHomeScreen(id = 1, title = "2 OldDDD"),
      )
   ),
   TattoosBasedOnTagsHomeScreen(id= 2,
      imageTattoo = "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_passaro_na_mao.png",
      listTagHomeScreens = listOf(
         TagHomeScreen(id = 1, title = "3 Schol"),  TagHomeScreen(id = 1, title = "3 PBBBB"), TagHomeScreen(id = 1, title = "3 OldDDD"),
      )
   ),
   TattoosBasedOnTagsHomeScreen(id= 2,
      imageTattoo = "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_calavera.png",
      listTagHomeScreens = listOf(
         TagHomeScreen(id = 1, title = "4 Schol"),  TagHomeScreen(id = 1, title = "4 PBBBB"), TagHomeScreen(id = 1, title = "4 OldDDD"),
      )
   ),
   TattoosBasedOnTagsHomeScreen(id= 2,
      imageTattoo = "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_tesoura.png",
      listTagHomeScreens = listOf(
         TagHomeScreen(id = 1, title = "5 Schol"),  TagHomeScreen(id = 1, title = "5 PBBBB"), TagHomeScreen(id = 1, title = "5 OldDDD"),
      )
   ),
   TattoosBasedOnTagsHomeScreen(id= 2,
      imageTattoo = "https://pub-777ce89a8a3641429d92a32c49eac191.r2.dev/home%2Ffirst_carousel%2Ftattoo_tesoura.png",
      listTagHomeScreens = listOf(
         TagHomeScreen(id = 1, title = "6 Schol"),  TagHomeScreen(id = 1, title = "6 PBBBB"), TagHomeScreen(id = 1, title = "5 OldDDD"),
      )
   )
)