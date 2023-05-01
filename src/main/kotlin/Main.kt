import classes.COrders
import classes.CProducts
import classes.CUser
import classes.DataSourceImpl
import entities.Orders
import entities.Products
import entities.User

fun main() {
    val myDataSource = DataSourceImpl()
    //insert information in products
    /*
    val productList = listOf(
        Products(name = "Bag", description = "Leather bag", price = 60.0f, taxes = 21, stock = 15),
        Products(name = "Sneakers", description = "Sports sneakers", price = 80.0f, taxes = 19, stock = 10),
        Products(name = "Shirt", description = "Cotton shirt", price = 35.0f, taxes = 18, stock = 20),
        Products(name = "Jeans", description = "Denim jeans", price = 50.0f, taxes = 20, stock = 12),
        Products(name = "Jacket", description = "Leather jacket", price = 150.0f, taxes = 21, stock = 5),
        Products(name = "Cap", description = "Baseball cap", price = 20.0f, taxes = 16, stock = 25),
        Products(name = "Scarf", description = "Wool scarf", price = 30.0f, taxes = 19, stock = 18),
        Products(name = "Sunglasses", description = "Polarized sunglasses", price = 45.0f, taxes = 17, stock = 8),
        Products(name = "Watch", description = "Wristwatch", price = 100.0f, taxes = 22, stock = 6),
        Products(name = "Perfume", description = "Women's perfume", price = 70.0f, taxes = 18, stock = 9),
        Products(name = "Bed Sheets", description = "Cotton bed sheets", price = 45.0f, taxes = 20, stock = 7),
        Products(name = "Pillow", description = "Memory foam pillow", price = 25.0f, taxes = 19, stock = 15),
        Products(name = "Towel", description = "Bath towel", price = 10.0f, taxes = 16, stock = 30),
        Products(name = "Curtain", description = "Fabric curtain", price = 35.0f, taxes = 21, stock = 11),
        Products(name = "Blanket", description = "Wool blanket", price = 40.0f, taxes = 19, stock = 13),
        Products(name = "Laptop", description = "14-inch laptop", price = 800.0f, taxes = 23, stock = 3),
        Products(name = "Headphones", description = "Wireless headphones", price = 120.0f, taxes = 20, stock = 8),
        Products(name = "Keyboard", description = "Mechanical keyboard", price = 70.0f, taxes = 18, stock = 12),
        Products(name = "Mouse", description = "Gaming mouse", price = 50.0f, taxes = 16, stock = 15),
        Products(name = "Monitor", description = "27-inch monitor", price = 300.0f, taxes = 19, stock = 10)
    )
    for (product in productList) {
        CProducts(myDataSource).create(product)
    }
    */
    //insert information in user
    /*
    val userList = listOf(
        User("12345678A", "Juan Pérez", 623456789, "juanperez@gmail.com"),
        User("23456789B", "María Gómez", 687654321, "mariagomez@hotmail.com"),
        User("34567890C", "Pedro Ruiz", 654321987, "pedroruiz@yahoo.com"),
        User("45678901D", "Ana Martínez", 647258369, "anamartinez@gmail.com"),
        User("56789012E", "Carlos González", 669852147, "carlosgonzalez@hotmail.com"),
        User("67890123F", "Laura Sánchez", 651357246, "laurasanchez@yahoo.com"),
        User("78901234G", "Javier Rodríguez", 689456123, "javierrodriguez@gmail.com"),
        User("89012345H", "Sara Fernández", 656123789, "sarafernandez@hotmail.com"),
        User("90123456I", "David Torres", 621654987, "davidtorres@yahoo.com"),
        User("01234567J", "Carmen Pérez", 659753468, "carmenperez@gmail.com")
    )
    for (user in userList) {
        CUser(myDataSource).create(user)
    }
    */
    //insert information in oders

    val orderList = listOf(
        Orders(owner = "45678901D", products = "72176b23-bd95-493c-a09b-7c2d1c1c1a34", debt = "72176b23-bd95-493c-a09b-7c2d1c1c1a34", amount = 5),
        Orders(owner = "45678901D", products = "c85733f2-3a5a-4e21-9206-ee8e91b91faa", debt = "c85733f2-3a5a-4e21-9206-ee8e91b91faa", amount = 1),
        Orders(owner = "45678901D", products = "6df0e2a1-042e-49cf-b344-9e39164df998", debt = "6df0e2a1-042e-49cf-b344-9e39164df998", amount = 1),
        Orders(owner = "45678901D", products = "23ebdfbe-8d56-4f0b-8daf-2cfdc43c18ed", debt = "23ebdfbe-8d56-4f0b-8daf-2cfdc43c18ed", amount = 3)
    )
    for (order in orderList) {
        COrders(myDataSource).create(order)
    }

    //get the amount to pay for te user with the dni 45678901D
    var debt = COrders(myDataSource).getDebt("45678901D")
    println("La deuda es de $debt")
}