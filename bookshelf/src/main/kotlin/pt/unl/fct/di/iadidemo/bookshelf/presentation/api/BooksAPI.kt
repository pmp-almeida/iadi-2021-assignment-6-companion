package pt.unl.fct.di.iadidemo.bookshelf.presentation.api

import org.springframework.web.bind.annotation.*
import pt.unl.fct.di.iadidemo.bookshelf.presentation.api.dto.*

@RequestMapping("user/books")
interface BooksAPI : GenAPI<BookDTO, BookListDTO, BookListDTO> {

    @GetMapping("count")
    fun getCount():Number
}