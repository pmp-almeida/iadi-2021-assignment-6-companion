package pt.unl.fct.di.iadidemo.bookshelf

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import pt.unl.fct.di.iadidemo.bookshelf.domain.*
import javax.transaction.Transactional

@SpringBootApplication
class SecurityApplication(
    val books:BookRepository,
    val users:UserRepository,
    val roles:RoleRepository,
    val authors:AuthorRepository
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

        val r1 = RoleDAO("ADMIN")
        val r2 = RoleDAO("REVIEWER")
        val r3 = RoleDAO("USER")
        roles.saveAll(listOf(r1, r2, r3))

        // Dummy users
        users.save(UserDAO("user",BCryptPasswordEncoder().encode("password"), listOf(r3),"User"))
        users.save(UserDAO("admin",BCryptPasswordEncoder().encode("password"), listOf(r1),"Admin"))

        val u1 = UserDAO("user1",BCryptPasswordEncoder().encode("password1"),listOf(r3,r2),"User 1")
        users.save(u1)

        val u2 = UserDAO("admin1",BCryptPasswordEncoder().encode("password1"), listOf(r1),"Admin 1")
        users.save(u2)

        val a1 = AuthorDAO(0,"Philip K. Dick")
        val a2 = AuthorDAO(0,"João Costa Seco")
        val a3 = AuthorDAO(0,"Artur Miguel Dias")
        val a4 = AuthorDAO(0,"João Leitão")
        val a5 = AuthorDAO(0, "José Legatheaux Martins")
        val a6 = AuthorDAO(0, "Colleen Hoover");
        val a7 = AuthorDAO(0, "Madeline Miller");
        val a8 = AuthorDAO(0, "Dummy1");
        val a9 = AuthorDAO(0, "Dummy2");
        val a10 = AuthorDAO(0, "Dummy3");
        val a11 = AuthorDAO(0, "Dummy4");

        authors.saveAll(listOf(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11))

        val b1 = BookDAO(0,"Ubik", mutableListOf(a1), listOf(ImageDAO(0, "https://covers.openlibrary.org/b/id/9251896-L.jpg")))
        val b2 = BookDAO(0,"Do Androids Dream of Electric Sheep?", mutableListOf(a1), listOf(ImageDAO(0, "https://covers.openlibrary.org/b/id/11153217-L.jpg")))
        val b3 = BookDAO(0,"The Man in the High Castle", mutableListOf(a1), listOf(ImageDAO(0, "https://covers.openlibrary.org/b/id/10045188-L.jpg")))

        val b4 = BookDAO(0,"It Ends With Us", mutableListOf(a2), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4711/9781471156267.jpg")))
        val b5 = BookDAO(0,"Ugly Love", mutableListOf(a2), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4711/9781471136726.jpg")))
        val b6 = BookDAO(0,"Confess", mutableListOf(a3), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4711/9781471148590.jpg")))

        val b7 = BookDAO(0,"The Hating Game", mutableListOf(a1), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/3494/9780349414263.jpg")))
        val b8 = BookDAO(0,"The Love Hypothesis", mutableListOf(a1), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/5933/9780593336823.jpg")))
        val b9 = BookDAO(0,"Verity", mutableListOf(a6), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/5387/9781538724736.jpg")))

        val b10 = BookDAO(0,"The Song of Achilles", mutableListOf(a7), listOf(ImageDAO(0, "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/4088/9781408891384.jpg")))

        books.saveAll(listOf(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10))
    }

}

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)
}
