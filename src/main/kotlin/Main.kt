import entities.Products

fun main() {
    val myDataSource = DataSourceImpl()
    val productList = listOf(
        Products(name = "Camisa", description = "Camisa de algodón", price = 20.0f, taxes = 19, stock = 50),
        Products(name = "Pantalón", description = "Pantalón de mezclilla", price = 30.0f, taxes = 19, stock = 30),
        Products(name = "Zapatos", description = "Zapatos de piel", price = 50.0f, taxes = 19, stock = 10),
        Products(name = "Gorra", description = "Gorra deportiva", price = 10.0f, taxes = 19, stock = 100),
        Products(name = "Calcetines", description = "Calcetines de algodón", price = 5.0f, taxes = 19, stock = 200),
        Products(name = "Vestido", description = "Vestido de seda", price = 80.0f, taxes = 19, stock = 20),
        Products(name = "Traje de baño", description = "Traje de baño para mujer", price = 25.0f, taxes = 19, stock = 40),
        Products(name = "Bolso", description = "Bolso de cuero", price = 60.0f, taxes = 19, stock = 15),
        Products(name = "Reloj", description = "Reloj de pulsera", price = 100.0f, taxes = 19, stock = 5),
        Products(name = "Lentes de sol", description = "Lentes de sol polarizados", price = 35.0f, taxes = 19, stock = 30)
    )
    for (product in productList) {
        val productId = CProducts(myDataSource).create(product)
        println("Product ID: $productId")
    }
}