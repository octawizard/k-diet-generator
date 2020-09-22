import ui.printDiet

fun main() {
    val proteins: Set<Protein> = setOfProtein(
        "chicken",
        "tuna",
        "shrimps",
        "eggs",
        "beans",
        "chickpeas",
        "lentil",
        "cottage cheese",
        "clams",
        "cod",
        "salmon"
    )
    val vegetables = setOfVegetable(
        "broccoli",
        "spinach",
        "beetroot",
        "cauliflower",
        "eggplant",
        "zucchini",
        "red pepper",
        "green beans",
        "edamame",
        "cabbage",
        "pumpkin"
    )
    val sideVegetables = setOfVegetable(
        "cucumber",
        "tomato",
        "carrot",
        "rucola",
        "lettuce",
        "canon",
        "dried tomato"
    )
    val carbs = setOfCarb("pasta", "brown rice", "brown bread", "potato", "sweet potato", "quinoa")

    val weeklyDiet = WeeklyDietGenerator(proteins, vegetables, sideVegetables, carbs)

    val meals = weeklyDiet.generate()
    println(meals)

    printDiet(meals)
}
