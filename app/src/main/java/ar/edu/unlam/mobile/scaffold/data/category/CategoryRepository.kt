package ar.edu.unlam.mobile.scaffold.data.category

object CategoryRepository {
    private val categories = mutableListOf(
        Category("Ropa"),
        Category("Comida"),
        Category("Limpieza")
    )

    fun order(): List<Category> {
        return categories.sortedByDescending { it.totalAmount }
    }

    fun exist(name: String): Boolean {
        return (categories.any { category: Category -> category.name == name })
    }

    fun listOfNames(): List<String> {
        val names = mutableListOf<String>()
        categories.any { category: Category -> names.add(category.name) }
        return names
    }
}