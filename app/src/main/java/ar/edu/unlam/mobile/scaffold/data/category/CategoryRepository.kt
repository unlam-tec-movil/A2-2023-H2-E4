package ar.edu.unlam.mobile.scaffold.data.category

object CategoryRepository {
    private val categories = mutableListOf(
        Category("Ropa", 5000.0),
        Category("Comida", 20000.0),
        Category("Limpieza", 1000.0),
        Category("Servicios", 50000.0),
        Category("Impuestos", 30000.0),
        Category("Tecnología", 100000.0),
        Category("Mascotas", 40000.0)
    )

    fun order(): List<Category> {
        return categories.sortedByDescending { it.totalAmount }
    }

    fun exist(name: String): Boolean {
        return (categories.any { category: Category -> category.name == name })
    }

    fun listOfNames(): List<String> {
        val names = mutableListOf<String>()
        categories.forEach { category: Category -> names.add(category.name) }
        return names
    }
}